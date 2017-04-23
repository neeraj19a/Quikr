package com.quikr.website.cars.carsFooter;

import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.cars.carsSNB.CarsSNBPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Saurabh on 22/10/15.
 */
public class CarsFooterCityTests extends CarsTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars CityPage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateCarsSNBFromCityPage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Cars", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("carsSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Cars"), "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars CityPage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateBikes_ScootersSNBFromCityPage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Bikes_Scooter", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("bikesScootersSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Bikes & Scooters"),"The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars CityPage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateCommercial_VehiclesSNBFromCityPage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Commercial_Vehicles", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("commercialVehiclesSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Commercial Vehicles"), "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars CityPage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateBicyclesSNBFromCityPage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Bicycles", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("bicyclesSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Bicycles"), "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars CityPage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateSpare_PartsSNBFromCityPage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Spare_Parts", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("sparePartsNSBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Spare Parts - Accessories"), "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars CityPage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateOther_VehiclesSNBFromCityPage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Others", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("otherSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Other Vehicles") , "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to About Us Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateAboutUsFromCityPage()
    {
        navigateToPage("AboutUs", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("aboutUsURL")), "Navigation to About Us Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Contact Us Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateContactUsFromCityPage()
    {
        navigateToPage("ContactUs", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("contactUsURL")), "Navigation to Contact Us Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Careers Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateCareersFromCityPage()
    {
        navigateToPage("Careers", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("careersURL")), "Navigation to Careers Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Quikr Videos Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateQuikrVideosFromCityPage()
    {
        navigateToPage("QuikrVideos", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("quikrVideosURL")), "Navigation to Quikr Videos Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Advertise Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateAdvertiseFromCityPage()
    {
        navigateToPage("Advertise", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("advertiseURL")), "Navigation to Advertise Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Blog Page from Cars CityPage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateBlogFromCityPage()
    {
        navigateToPage("Blog", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("blogURL")), "Navigation to Blog Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Blog Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateHelpFromCityPage()
    {
        navigateToPage("Help", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("helpURL")), "Navigation to Help Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to IOS App Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateIOSAppFromCityPage()
    {
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);

        navigateToCarsCityPage();
        carsFooterPage.clickIOSApp();

        Assert.assertTrue(waitForPageToLoad(testData.get("iosAppURL")), "Navigation to IOS App Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Android App Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateAndroidAppFromCityPage()
    {
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);

        navigateToCarsCityPage();
        carsFooterPage.clickAndroidApp();

        Assert.assertTrue(waitForPageToLoad(testData.get("androidAppURL")), "Navigation to Android App Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Windows App Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateWindowsAppFromCityPage()
    {
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);

        navigateToCarsCityPage();
        carsFooterPage.clickWindowsApp();

        Assert.assertTrue(waitForPageToLoad(testData.get("windowsAppURL")), "Navigation to Windows App Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Listing Policy Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateListing_PolicyFromCityPage()
    {
        navigateToPage("Listing_Policy", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("listingPolicyURL")), "Navigation to Listing Policy Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Terms Of Use Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateTermsOfUseFromCityPage()
    {
        navigateToPage("TermsOfUse", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("termsOfUseURL")), "Navigation to Terms Of Use Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Privacy Policy Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigatePrivacy_PolicyFromCityPage()
    {
        navigateToPage("Privacy_Policy", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("privacyPolicyURL")), "Navigation to Privacy Policy Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to QuikrX Policy Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateQuikrX_PolicyFromCityPage()
    {
        navigateToPage("QuikrX_Policy", "CarsCityPage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("quikrXPolicyURL")), "Navigation to QuikrX Policy Page from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Footer City Page from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateFooterCityFromCityPage()
    {
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);

        navigateToCarsCityPage();
        carsFooterPage.clickFooterCity(testData.get("city"));

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("quikrHomePageURL"))), "Navigation to New City from Cars CityPage Footer was unsuccessful");
        Assert.assertTrue(carsFooterPage.citySelected().equalsIgnoreCase(testData.get("city")), "The City is not appearing selected on CityPage");
    }
}