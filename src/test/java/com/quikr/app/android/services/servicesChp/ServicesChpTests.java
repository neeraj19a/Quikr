package com.quikr.app.android.services.servicesChp;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.services.ServicesPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by rohanbajaj on 12/10/15.
 */
public class ServicesChpTests extends AppAndroidTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_SERVICES_TESTDATA_FILE"));

    // ANDROID-766, 3018:Services Sub categories List View
//    @Test
    public void verifyServicesHomeSubcatsInListView()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage= new ServicesPage(driver);
//        homePage.ApplaunchPopup();
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.clickOnHomeServices();
        Assert.assertTrue(servicesPage.validateSubcategories(), "Mismatch in subcategories of Home Services");
    }

    // ANDROID-3019:Verify Other Services
//    @Test
    public void verifyServicesOtherSubcatsInListView()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage= new ServicesPage(driver);
//        homePage.ApplaunchPopup();
//        homePage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.clickOnOtherServices();
        Assert.assertTrue(servicesPage.validateOtherSubcategories(), "Mismatch in subcategories of Other Services");
    }

    // ANDROID-767:Post Free Ad in Services Page
    @Test(groups = "horizontal")
    public void verifyPostFreeAd()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("category"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String categoty="Services";
        papPage.setAdTitle(testData.get("adTitle"), categoty);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("adDescription"),categoty);
        papPage.navigateBack();
        papPage.scroll("Locality");
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd());
    }

    // ANDROID-768:More Button for Services sub categories
    @Test
    public void verifyMoreButtonNotDisplayed()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage= new ServicesPage(driver);
//        homePage.ApplaunchPopup();
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        Assert.assertFalse(servicesPage.isMoreButtonDisplayed(), "More button is displayed");
    }

    // ANDROID-769:Popular Ads on Services Page
    @Test
    public void verifyPopularAdsNotDisplayed()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage= new ServicesPage(driver);
//        homePage.ApplaunchPopup();
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        Assert.assertFalse(servicesPage.isPopularAdsDisplayed(), "Popular ads are displayed");
    }

    // ANDROID-1040:Verify user is able to create an alert for the target city and subcategory
    @Test(groups = "horizontal")
    public void createAlertForServices()
    {
        Hompage hompage = new Hompage(driver);
        MenuPage menuPage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        PapPage papPage = new PapPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickOnMyAlerts();
        alertPage.clickOnCreateAlert();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("category"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.clickonAdlocation();
        alertPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        //alertPage.enterPhoneNumber(testData.get("phoneNumber"));
        alertPage.scroll(testData.get("createAlert"));
//        papPage.dontSyncPhonebook();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateAlert(), "Alert creation for Services failed");
    }

    // ANDROID-3003:Verify Services Home Screen for instaConnect target city
    //Verify Services home screen for Search, Book Now, Evaluate & Choose, financial Services(If  present in api response) and Browse Ads
    @Test
    public void verifyServicesHomeScreen()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(servicesPage.isSearchBarPresent(), "Search bar not present in services home page");
        Assert.assertTrue(servicesPage.isBookNowCardPresent(), "Book Now not present in services home page");
        Assert.assertTrue(servicesPage.isEvaluateAndChooseCardPresent(),"Evaluate and Choose card not present in services home page");
//        Assert.assertTrue(servicesPage.isBrowseAdsCardPresent(), "Browse ads card not present in services home page");

//        Assert.assertTrue(servicesPage.isHomeServicesPresent(), "Home services not present in services home page");
//        Assert.assertTrue(servicesPage.isOtherServicesPresent(), "Other services not present in services home page");
    }

    // ANDROID-3007:Services Home Screen for non-target city
    @Test
    public void verifyServicesHomeScreenForNonTargetCity()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectCity();
        homePage.selectelementWithoutScroll(testData.get("NewCity"), QuikrAppEnums.Hompage_SelectCity);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(servicesPage.isSearchBarPresent(), "Search bar not present in services home page");
//        Assert.assertTrue(servicesPage.isHomeServicesPresent(), "Home services not present in services home page");
//        Assert.assertTrue(servicesPage.isOtherServicesPresent(), "Other services not present in services home page");
        Assert.assertTrue(homePage.isPostAdButtonPresent(), "Post ad button not present");
    }

    // ANDROID-3017:Verify Search screen
    @Test
    public void verifySearchScreen()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.clickOnSearchBar();
        Assert.assertTrue(servicesPage.isServicesLocalityDropdownPresent(), "Services locality dropdown not present");
        Assert.assertTrue(servicesPage.isServicesCategorySearchBarPresent(), "Services search bar not present");
    }

    // ANDROID-3024:Verify subcategories under InstaConnect
//    @Test
    public void verifySubcategoriesUnderInstaconnect()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        snbPage.connectNow();
        Assert.assertTrue(servicesPage.validateInstaconnectSubcategories(), "Services instaconnect category mismatch");

    }

    // Verify categories under book now takes to either book now partner url or quikr helpr intermediate screen
    @Test(dataProvider = "servicesBookNowCategories", dataProviderClass = Data.class)
    public void verifyCategoriesUnderBookNow(String bookNowCategory)
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(servicesPage.validateBookNowCategory(bookNowCategory));
    }

    // Verfiy categories under Evaluate and Choose for target subcategory takes to corresponding Quikr Connect or Quikr InstaConnect screen.
    @Test
    public void verifyCategoriesUnderEvaluateAndChoose() throws Exception
    {
        Hompage homePage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.swipeVertically(testData.get("showAllOption"),testData.get("servicesSearchBar"));
        servicesPage.selectelementWithoutScroll(testData.get("evaluateAndChooseCategory"), QuikrAppEnums.SERVICES_INSTACONNECT_CATEGORY);
        servicesPage.selectelementWithoutScroll(testData.get("evaluateAndChooseSubcategory"), QuikrAppEnums.SERVICES_INSTACONNECT_SUBCAT);
        servicesPage.clickOnContinueButton();
        Assert.assertTrue(snbPage.isInstaconnectNowOptionPresent(), "Instaconnect now option not present for subcategory " + testData.get("evaluateAndChooseCategory")+ " -> " + testData.get("evaluateAndChooseSubcategory"));
    }

    // For Non-target city-subcat, it will go to SNB screen.
    // Verify Browse ads for non-target cities
    // For non-target cities, only Browse ads will come.
    @Test
    public void verifyNonTargetCitySubcat()
    {
        Hompage homePage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectCity();
        homePage.selectelementWithoutScroll(testData.get("NewCity"), QuikrAppEnums.Hompage_SelectCity);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        Assert.assertFalse(servicesPage.isBookNowCardPresent(), "Book Now card present");
        Assert.assertFalse(servicesPage.isEvaluateAndChooseCardPresent(), "Evaluate and Choose card present");
        servicesPage.clickOnBrowseAds();
        servicesPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.validateNavigationToSnb(), "Did not navigate to Services SNB");

    }

    // Verify search for non-target city takes user to respective SNB screen.
    @Test
    public void verifySearchRedirectingToSnbPageForNonTargetCity()
    {
        Hompage homePage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectCity();
        homePage.selectelementWithoutScroll(testData.get("NewCity"), QuikrAppEnums.Hompage_SelectCity);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.searchFromServicesChp();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.validateNavigationToSnb(), "Did not navigate to Services SNB");
    }







}
