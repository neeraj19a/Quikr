package com.quikr.website.cars.carsHeader;

import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.cars.carsMSP.CarsMSPPage;
import com.quikr.website.cars.carsPAP.CarsPAPPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Saurabh on 22/10/15.
 */
public class CarsHeaderTests extends CarsTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate navigation to Quikr HomePage from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateQuikrHomePage()
    {
        navigateToPage("QuikrHome", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("quikrHomePageURL")), "Navigation to Quikr HomePage from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Mobiles & Tablets HomePage from Cars HomePage
     */
    @Test(enabled=true)
    public void navigateMobiles_TabletsHomePage()
    {
        navigateToPage("Mobiles_Tablets", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("mobileHomePageURL")),"Navigation to Mobiles & Tablets HomePage from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Electronics & Appliances HomePage from Cars HomePage
    */
    @Test(enabled=true)
    public void navigateElectronics_AppliancesHomePage()
    {
        navigateToPage("Electronics_Appliances", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("electronicsHomePageURL")),"Navigation to Electronics & Appliances HomePage from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to RealEstate HomePage from Cars HomePage
    */
    @Test(enabled=true)
    public void navigateRealEstateHomePage()
    {
        navigateToPage("RealEstate", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("realEstateHomePageURL")), "Navigation to RealEstate HomePage from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Home & Lifestyle HomePage from Cars HomePage
    */
    @Test(enabled=true)
    public void navigateHome_LifestyleHomePage()
    {
        navigateToPage("Home_Lifestyle", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("homeLifestyleHomePageURL")), "Navigation to Home & Lifestyle HomePage from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Jobs HomePage from Cars HomePage
    */
    @Test(enabled=true)
    public void navigateJobsHomePage()
    {
        navigateToPage("Jobs", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("jobsHomePageURL")), "Navigation to Jobs HomePage from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Services HomePage from Cars HomePage
    */
    @Test(enabled=true)
    public void navigateServicesHomePage()
    {
        navigateToPage("Services", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("servicesHomePageURL")), "Navigation to Services HomePage from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Pets & Pet Care HomePage from Cars HomePage
    */
    @Test(enabled=true)
    public void navigatePets_PetcareHomePage()
    {
        navigateToPage("Pets_Petcare", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("petsHomePageURL")), "Navigation to Pets & Pet Care HomePage from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Education & Learning HomePage from Cars HomePage
    */
    @Test(enabled=true)
    public void navigateEducation_LearningHomePage()
    {
        navigateToPage("Education_Learning", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("educationHomePageURL")), "Navigation to Education & Learning HomePage from Cars HomePage was unsuccessful");
    }


    /**
     * Testcase - Validate navigation to New Cars from Hamburger from Cars HomePage
     */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateNewCarsFromHomePage()
    {
        CarsMSPPage carsMSPPage = new CarsMSPPage(driver);

        navigateToCarsHomePage();
        navigateToPage("NewCars", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("newCarsPageURL")), "Navigation to New Cars Page from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsMSPPage.heading().contains("Carsin"), "Something wrong with New Cars Page");
    }

    /**
     * Testcase - Validate navigation to Check MSP Page from Hamburger from Cars HomePage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMSPFromHomePage()
    {
        CarsMSPPage carsMSPPage = new CarsMSPPage(driver);

        navigateToCarsHomePage();
        navigateToPage("Check_MSP_Hamburger", "CarsHomePage", true);

        Assert.assertTrue(waitForPageToLoad(testData.get("checkMSPPageURL")), "Navigation to Check MSP Page from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsMSPPage.heading().equalsIgnoreCase("UsedVehicleValuation"), "Something wrong with MSP Page");
    }

    /**
     * Testcase - Validate navigation to Cars HomePage
    */
    @Test(enabled=true)
    public void navigateCarsHomePageFromHomePage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        carsHeaderPage.clickQuikrLogo();

        Assert.assertTrue(waitForPageToLoad(testData.get("carsHomePageURL")), "Navigation to Cars HomePage from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.citySelected().equalsIgnoreCase("All India"), "All India is not appearing selected on Homepage");
    }

    /**
     * Testcase - Validate navigation to PAP from Cars HomePage
    */
    @Test(enabled=true, groups = {"carsP0","carsSanity"})
    public void navigatePAPFromHomePage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);
        CarsPAPPage carsPAPPage = new CarsPAPPage(driver);

        navigateToCarsHomePage();
        carsHeaderPage.clickPostAd();

        Assert.assertTrue(waitForPageToLoad(testData.get("PAPURL")), "Navigation to PAP from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsPAPPage.toCamelCase(carsPAPPage.PAPHeading()).contains("Free Ad"), "Something wrong with PAP Page");
    }

    /**
     * Testcase - Validate Sign Up from Cars HomePage
     */
    @Test(enabled=true, groups = {"carsP0","carsSanity"})
    public void signupFromHomePage()
    {
        String emailid = getRandomString(6)+"@abcd.in";
        String phone = "9"+getRandomInteger(9);

        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        carsHeaderPage.signupUser(getRandomString(9), emailid, testData.get("password"), phone, testData.get("city"));

    }

    /**
     * Testcase - Validate Login from Cars HomePage
    */
    @Test(enabled=true, groups = {"carsP0","carsSanity"})
    public void loginFromHomePage()
    {
        navigateToCarsHomePage();
        loginUser(testData.get("emailId"), testData.get("password"));

        boolean horizontalDashboard=waitForPageToLoad(testData.get("dashboardURLHorizontal"));
        boolean carsDashboard=waitForPageToLoad(testData.get("dashboardURLCars"));

        Assert.assertTrue(horizontalDashboard || carsDashboard, "Login from Cars HomePage was unsuccessful");

    }

    /**
     * Testcase - Validate navigation to myProfile Page from Cars HomePage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMyProfileFromHomePage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);
        CarsPageBase carsPageBase=new CarsPageBase(driver);

        navigateToCarsHomePage();
        loginUser(testData.get("emailId"), testData.get("password"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsPageBase.closeCityPopup();
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMyProfile();

        Assert.assertTrue(waitForPageToLoad(testData.get("myProfileURL")), "Navigation to myProfile Page from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myQuikrValidate().contains("My Account"), "My Profile not loaded properly");
    }

    /**
     * Testcase - Validate navigation to myAds Page from Cars HomePage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMyAdsFromHomePage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.closeCityPopup();
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMyAds();

        Assert.assertTrue(waitForPageToLoad(testData.get("myAdsURL")), "Navigation to myAds Page from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myQuikrValidate2().contains("Active Ads"), "My Ads not loaded properly");

    }

    /**
     * Testcase - Validate navigation to myAlerts Page from Cars HomePage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMyAlertsFromHomePage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.closeCityPopup();
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMyAlerts();

        Assert.assertTrue(waitForPageToLoad(testData.get("myAlertsURL")), "Navigation to myAlerts Page from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myQuikrValidate2().contains("Email Alerts Management"), "My Alerts not loaded properly");
    }

    /**
     * Testcase - Validate navigation to myChats Page from Cars HomePage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMyChatsFromHomePage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.closeCityPopup();
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMyChats();

        Assert.assertTrue(waitForPageToLoad(testData.get("myChatsURL")), "Navigation to myChats Page from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myChatValidate(), "My Chats not loaded properly");
    }

    /**
     * Testcase - Validate navigation to mySavedAds Page from Cars HomePage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMySavedAdsFromHomePage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.closeCityPopup();
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMySavedAds();

        Assert.assertTrue(waitForPageToLoad(testData.get("mySavedAdsURL")), "Navigation to mySavedAds Page from Cars HomePage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.heading().equalsIgnoreCase("MyShortlist"), "My Saved Ads not loaded properly");
    }

    /**
     * Testcase - Validate SignOut from Cars HomePage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void signOutFromHomePage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.closeCityPopup();
        carsHeaderPage.signOutUser();

        Assert.assertTrue(carsHeaderPage.isLoginRegisterButtonavailable(), "SignOut from Cars HomePage was unsuccessful");
    }
}