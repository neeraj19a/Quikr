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
public class CarsFooterTests extends CarsTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars HomePage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateCarsSNBFromHomePage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Cars", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("carsSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Cars"), "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars HomePage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateBikes_ScootersSNBFromHomePage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Bikes_Scooter", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("bikesScootersSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Bikes & Scooters"),"The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars HomePage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateCommercial_VehiclesSNBFromHomePage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Commercial_Vehicles", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("commercialVehiclesSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Commercial Vehicles"), "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars HomePage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateBicyclesSNBFromHomePage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Bicycles", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("bicyclesSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Bicycles"), "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars HomePage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateSpare_PartsSNBFromHomePage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Spare_Parts", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("sparePartsNSBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Spare Parts - Accessories"), "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to Cars SNB Page from Cars HomePage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateOther_VehiclesSNBFromHomePage()
    {
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        navigateToPage("Footer_Others", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("otherSNBURL")), "The SNB URL formed is incorrect");
        Assert.assertTrue(carsSNBPage.heading().contains("Other Vehicles") , "The title of the SNB Page is incorrect");
    }

    /**
     * Testcase - Validate navigation to About Us Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateAboutUsFromHomePage()
    {
        navigateToPage("AboutUs", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("aboutUsURL")), "Navigation to About Us Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Contact Us Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateContactUsFromHomePage()
    {
        navigateToPage("ContactUs", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("contactUsURL")), "Navigation to Contact Us Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Careers Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateCareersFromHomePage()
    {
        navigateToPage("Careers", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("careersURL")), "Navigation to Careers Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Quikr Videos Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateQuikrVideosFromHomePage()
    {
        navigateToPage("QuikrVideos", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("quikrVideosURL")), "Navigation to Quikr Videos Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Advertise Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateAdvertiseFromHomePage()
    {
        navigateToPage("Advertise", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("advertiseURL")), "Navigation to Advertise Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Blog Page from Cars HomePage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateBlogFromHomePage()
    {
        navigateToPage("Blog", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("blogURL")), "Navigation to Blog Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Blog Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateHelpFromHomePage()
    {
        navigateToPage("Help", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("helpURL")), "Navigation to Help Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to IOS App Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateIOSAppFromHomePage()
    {
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);

        navigateToCarsHomePage();
        carsFooterPage.clickIOSApp();

        Assert.assertTrue(waitForPageToLoad(testData.get("iosAppURL")), "Navigation to IOS App Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Android App Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateAndroidAppFromHomePage()
    {
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);

        navigateToCarsHomePage();
        carsFooterPage.clickAndroidApp();

        Assert.assertTrue(waitForPageToLoad(testData.get("androidAppURL")), "Navigation to Android App Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Windows App Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateWindowsAppFromHomePage()
    {
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);

        navigateToCarsHomePage();
        carsFooterPage.clickWindowsApp();

        Assert.assertTrue(waitForPageToLoad(testData.get("windowsAppURL")), "Navigation to Windows App Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Listing Policy Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateListing_PolicyFromHomePage()
    {
        navigateToPage("Listing_Policy", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("listingPolicyURL")), "Navigation to Listing Policy Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Terms Of Use Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateTermsOfUseFromHomePage()
    {
        navigateToPage("TermsOfUse", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("termsOfUseURL")), "Navigation to Terms Of Use Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Privacy Policy Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigatePrivacy_PolicyFromHomePage()
    {
        navigateToPage("Privacy_Policy", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("privacyPolicyURL")), "Navigation to Privacy Policy Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to QuikrX Policy Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateQuikrX_PolicyFromHomePage()
    {
        navigateToPage("QuikrX_Policy", "CarsHomePage", false);

        Assert.assertTrue(waitForPageToLoad(testData.get("quikrXPolicyURL")), "Navigation to QuikrX Policy Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Footer City Page from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateFooterCityFromHomePage()
    {
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);

        navigateToCarsHomePage();
        carsFooterPage.clickFooterCity(testData.get("city"));

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("quikrHomePageURL"))), "Navigation to New City from Cars CityPage Footer was unsuccessful");
        Assert.assertTrue(carsFooterPage.citySelected().equalsIgnoreCase(testData.get("city")), "The City is not appearing selected on CityPage");
    }
}