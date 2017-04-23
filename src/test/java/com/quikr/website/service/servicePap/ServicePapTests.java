package com.quikr.website.service.servicePap;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.service.servicePAP.ServicePAP;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 12/10/15.
 */
public class ServicePapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("SERVICES_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /*
    Fixed
     */
    @Test
    public void postAdWithoutLoginAsServiceProvider()
    {
        HomePage homePage = new HomePage(driver);
        ServicePAP servicePAP = new ServicePAP(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        servicePAP.openPap();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.navigateToServices();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.selectAdLocality();
        servicePAP.giveAdTitle(testData.get("adTitle"));
        servicePAP.selectAdType();
        servicePAP.giveAdDescription(testData.get("description"));
        servicePAP.YourEmail(testData.get("email"));
        servicePAP.postAd();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(servicePAP.checkSuccessMsg(), "Failed to post ad.");

    }

    /*
    Fixed
     */
    @Test
    public void postAdWithLoginAsServiceProvider()
    {
        HomePage homePage = new HomePage(driver);
        ServicePAP servicePAP = new ServicePAP(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        //headerPage.loginWithCity(username,password);
        waitForPageToLoad("MyQuikr");
        servicePAP.openPap();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.navigateToServices();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.selectAdLocality();
        servicePAP.giveAdTitle(testData.get("adTitle"));
        servicePAP.selectAdType();
        servicePAP.giveAdDescription(testData.get("description"));
        servicePAP.postAd();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(servicePAP.checkSuccessMsg(), "Failed to post ad.");

    }

    /*
    Fixed
     */
    @Test
    public void postAdWithLoginAsWantType()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ServicePAP servicePAP = new ServicePAP(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        //headerPage.loginWithCity(username,password);
        waitForPageToLoad("MyQuikr");
        servicePAP.openPap();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.navigateToServices();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.selectAdLocality();
        servicePAP.selectAdAsCustomer();
        servicePAP.giveAdTitle(testData.get("adTitle"));
        servicePAP.selectAdType();
        servicePAP.giveAdDescription(testData.get("description"));
        servicePAP.postAd();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(servicePAP.checkSuccessMsg(), "Failed to post ad.");

    }

    /*
    Fixed
     */
    @Test
    public void postAdWithoutLoginAsWantType()
    {
        HomePage homePage = new HomePage(driver);
        ServicePAP servicePAP = new ServicePAP(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        servicePAP.openPap();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.navigateToServices();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.selectAdLocality();
        servicePAP.selectAdAsCustomer();
        servicePAP.giveAdTitle(testData.get("adTitle"));
        servicePAP.selectAdType();
        servicePAP.giveAdDescription(testData.get("description"));
        servicePAP.postAd();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(servicePAP.checkSuccessMsg(), "Failed to post ad.");

    }

    /*
    WEB-566:Validate Post an Ad once after login to the application.
    This test needs to be worked on. Post ad is not working.
    Fixed.
     */
    //@Test()
    public void ValidatePostAnAd()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ServicePAP servicePAP = new ServicePAP(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        //headerPage.loginWithCity(username,password);
        waitForPageToLoad("MyQuikr");
        servicePAP.openPap();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.navigateToServices();
        waitForPageToLoad(testData.get("pap"));
        servicePAP.selectAdLocality();
        servicePAP.selectAdAsCustomer();
        servicePAP.giveAdTitle(testData.get("adTitle"));
        servicePAP.selectAdType();
        servicePAP.giveAdDescription(testData.get("description"));
        servicePAP.postAd();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(servicePAP.checkSuccessMsg(), "Ad posting was unsuccessful. Please check");
    }
}
