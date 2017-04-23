package com.quikr.website.cars.carsVAP;

import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.cars.carsHeader.CarsHeaderPage;
import com.quikr.website.cars.carsHome.CarsHomePage;
import com.quikr.website.cars.carsSNB.CarsSNBPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 22/11/15.
 */
public class CarsVAPTests extends CarsTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate Chat or Reply Button present on VAP Page
     */
    @Test(groups = "carsSanity")
    public void testChatorReplybuttonOnVapPage() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        CarsVAPPage carsVAPPage=new CarsVAPPage(driver);
        CarsSNBPage carsSNBPage=new CarsSNBPage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.clickonAdwithProperPrice();
        waitForPageToLoad("quikr.com/Cars");
        Assert.assertTrue(carsVAPPage.validateChatReplyButton(),"Looks like Chat or Reply window is not Present");
    }

    /**
     * Testcase - Validate ShortList Button present on VAP Page
     */
    @Test(groups = "carsSanity")
    public void testShortListAdbuttonOnVapPage() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        CarsVAPPage carsVAPPage = new CarsVAPPage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsHeaderPage carsHeaderPage=new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedCarsinIndia"), "Looks like SNB Page is not loaded");
        carsSNBPage.clickonAdwithProperPrice();
        Assert.assertTrue(carsVAPPage.clickAdShortList(),"Looks like VAP Page ShortList is not working");
        waitForPageToLoad("quikr.com/Cars");
        loginUser(testData.get("emailId"), testData.get("password"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.closeCityPopup();
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMySavedAds();
        Assert.assertTrue(waitForPageToLoad(testData.get("mySavedAdsURL")), "Navigation to mySavedAds Page from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.heading().equalsIgnoreCase("MyShortlist"), "My Saved Ads not loaded properly");
    }

    /**
     * Testcase - Validate Share Button present on VAP Page
     */
    @Test(groups = "carsSanity")
    public void testShareButtonOnVapPage() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        CarsVAPPage carsVAPPage = new CarsVAPPage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsPageBase.closeCityPopup();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedCarsinIndia"), "Looks like SNB Page is not loaded");
        carsSNBPage.clickonAdwithProperPrice();
        waitForPageToLoad("quikr.com");
        Assert.assertTrue(carsVAPPage.validateShareIconVAP(),"Looks like Share Feature is not properly present on Vap");
    }
}
