package com.quikr.website.service.serviceVap;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.service.serviceHomepage.ServiceHomepage;
import com.quikr.website.service.serviceSNB.ServiceSNBpage;
import com.quikr.website.service.serviceVAP.ServiceVAPpage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 13/10/15.
 */
public class ServiceVapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("SERVICES_TESTDATA_FILE"));

    /*
   WEB-567:View Ad Page
   Fixed
    */
    @Test()
    public void viewAndVerifyAdVap()
    {
        HomePage homePage = new HomePage(driver);
        //ServicePage servicePage = new ServicePage(driver);
        ServiceVAPpage serviceVAPpage = new ServiceVAPpage(driver);
        ServiceSNBpage serviceSNBpage = new ServiceSNBpage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        serviceHomepage.searchFromServiceHomepage(testData.get("searchService"), "Koramangala");
        waitForPageToLoad(testData.get("searchService"));
        String FirstAdTitleFromSnb = serviceSNBpage.getFirstAdTitleSnb();
        serviceSNBpage.navigateToFirstAd();
        Assert.assertTrue(serviceVAPpage.verifyAdTitleVap(FirstAdTitleFromSnb), "Ad titles doesn't match in snb and vap.");
    }
}
