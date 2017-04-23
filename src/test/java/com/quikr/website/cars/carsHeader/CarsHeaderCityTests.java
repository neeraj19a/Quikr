package com.quikr.website.cars.carsHeader;

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
public class CarsHeaderCityTests extends CarsTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate navigation to Quikr CityPage from Cars CityPage
     */
    @Test(enabled=true)
    public void navigateQuikrCityPage()
    {
        navigateToPage("QuikrHome", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("quikrHomePageURL"))),"Navigation to Quikr CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Mobiles & Tablets CityPage from Cars CityPage
    */
    @Test(enabled=true)
    public void navigateMobiles_TabletsCityPage()
    {
        navigateToPage("Mobiles_Tablets", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("mobileHomePageURL"))),"Navigation to Mobiles & Tablets CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Electronics & Appliances CityPage from Cars CityPage
    */
    @Test(enabled=true)
    public void navigateElectronics_AppliancesCityPage()
    {
        navigateToPage("Electronics_Appliances", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("electronicsHomePageURL"))),"Navigation to Electronics & Appliances CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to RealEstate CityPage from Cars CityPage
    */
    @Test(enabled=true)
    public void navigateRealEstateCityPage()
    {
        navigateToPage("RealEstate", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("realEstateHomePageURL"))), "Navigation to RealEstate CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Home & Lifestyle CityPage from Cars CityPage
    */
    @Test(enabled=true)
    public void navigateHome_LifestyleCityPage()
    {
        navigateToPage("Home_Lifestyle", "CarsCityPage",true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("homeLifestyleHomePageURL"))), "Navigation to Home & Lifestyle CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Jobs CityPage from Cars CityPage
    */
    @Test(enabled=true)
    public void navigateJobsCityPage()
    {
        navigateToPage("Jobs", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("jobsHomePageURL"))), "Navigation to Jobs CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Services CityPage from Cars CityPage
    */
    @Test(enabled=true)
    public void navigateServicesCityPage()
    {
        navigateToPage("Services", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("servicesHomePageURL"))), "Navigation to Services CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Pets & Pet Care CityPage from Cars CityPage
    */
    @Test(enabled=true)
    public void navigatePets_PetcareCityPage()
    {
        navigateToPage("Pets_Petcare", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("petsHomePageURL"))), "Navigation to Pets & Pet Care CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Education & Learning CityPage from Cars CityPage
    */
    @Test(enabled=true)
    public void navigateEducation_LearningCityPage()
    {
        navigateToPage("Education_Learning", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("educationHomePageURL"))), "Navigation to Education & Learning CityPage from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Check MSP Page from Hamburger from Cars CityPage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMSPFromCityPage()
    {
        CarsMSPPage carsMSPPage = new CarsMSPPage(driver);

        navigateToCarsCityPage();
        navigateToPage("Check_MSP_Hamburger", "CarsCityPage", true);

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("checkMSPPageURL"))), "Navigation to Check MSP Page from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsMSPPage.heading().equalsIgnoreCase("Used Vehicle Valuation"), "Something wrong with MSP Page");
    }

    /**
     * Testcase - Validate navigation to Cars CityPage
    */
    @Test(enabled=true)
    public void navigateCarsCityPageFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        carsHeaderPage.clickQuikrLogo();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("carsHomePageURL"))), "Navigation to Cars CityPage from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.citySelected().equalsIgnoreCase(testData.get("city")), "The City is not appearing selected on CityPage");
    }

    /**
     * Testcase - Validate navigation to PAP from Cars CityPage
    */
    @Test(enabled=true, groups = {"carsP0","carsSanity"})
    public void navigatePAPFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);
        CarsPAPPage carsPAPPage = new CarsPAPPage(driver);

        navigateToCarsCityPage();
        carsHeaderPage.clickPostAd();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("PAPURL"))), "Navigation to PAP from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsPAPPage.toCamelCase(carsPAPPage.PAPHeading()).contains("Free Ad"), "Something wrong with PAP Page");
    }

    /**
     * Testcase - Validate Sign Up from Cars CityPage
     */
    @Test(enabled=true, groups = {"carsP0","carsSanity"})
    public void signupFromCityPage()
    {
        String emailid = getRandomString(6)+"@abcd.in";
        String phone = "9"+getRandomInteger(9);

        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        carsHeaderPage.signupUser("Individual", emailid, testData.get("password"), phone, testData.get("city"));
        waitForPageToLoad(covertToCityURL(testData.get("signupURL")));

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("signupURL"))), "Sign Up from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate Login from Cars CityPage
    */
    @Test(enabled=true, groups = {"carsP0","carsSanity"})
    public void loginFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        loginUser(testData.get("emailId"), testData.get("password"));

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("dashboardURL"))), "Login from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myQuikrValidate().contains("My Dashboard"), "My Dashboard not loaded properly");
    }

    /**
     * Testcase - Validate navigation to myProfile Page from Cars CityPage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMyProfileFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMyProfile();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("myProfileURL"))), "Navigation to myProfile Page from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myQuikrValidate().contains("Update My Profile"), "My Profile not loaded properly");
    }

    /**
     * Testcase - Validate navigation to myAds Page from Cars CityPage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMyAdsFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMyAds();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("myAdsURL"))), "Navigation to myAds Page from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myQuikrValidate().contains("Active Ads Management"), "My Ads not loaded properly");
    }

    /**
     * Testcase - Validate navigation to myAlerts Page from Cars CityPage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMyAlertsFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMyAlerts();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("myAlertsURL"))), "Navigation to myAlerts Page from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myQuikrValidate().contains("Email Alerts Management"), "My Alerts not loaded properly");
    }

    /**
     * Testcase - Validate navigation to myChats Page from Cars CityPage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMyChatsFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMyChats();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("myChatsURL"))), "Navigation to myChats Page from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.myChatValidate(), "My Chats not loaded properly");
    }

    /**
     * Testcase - Validate navigation to mySavedAds Page from Cars CityPage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void navigateMySavedAdsFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMySavedAds();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("mySavedAdsURL"))), "Navigation to mySavedAds Page from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.heading().equalsIgnoreCase("My Shortlist"), "My Saved Ads not loaded properly");
    }

    /**
     * Testcase - Validate SignOut from Cars CityPage
    */
    @Test(enabled=true, groups = "carsSanity")
    public void signOutFromCityPage()
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsCityPage();
        loginUser(testData.get("emailId"), testData.get("password"));
        carsHeaderPage.navigateToURL(testData.get("carsURL"));
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.signOutUser();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("logoutURL"))), "SignOut from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHeaderPage.heading().contains("You have Signed Out"), "User is not logged out properly");
    }
}