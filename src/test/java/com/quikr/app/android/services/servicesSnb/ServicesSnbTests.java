package com.quikr.app.android.services.servicesSnb;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.android.services.ServicesPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by rohanbajaj on 12/10/15.
 */
public class ServicesSnbTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_SERVICES_TESTDATA_FILE"));

    // ANDROID-770, 3038, 4341 :S&B screen for sub categories
//    @Test
    public void verifyNavigationToSnb()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        PapPage papPage = new PapPage(driver);
        AlertPage alertPage = new AlertPage(driver);
//        hompage.ApplaunchPopup();
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.clickOnHomeServices();
        servicesPage.selectelementWithoutScroll(testData.get("categoryName"), QuikrAppEnums.CATEGORY_SERVICES);
        servicesPage.selectelementWithoutScroll(testData.get("subcategoryName"), QuikrAppEnums.Services_HomeSubcategories);
        Assert.assertTrue(servicesPage.isSkipOptionPresent(), "Skip option not present");
        Assert.assertTrue(servicesPage.isConnectNowButtonPresent(), "Book Now button not present");
        snbPage.viewAdListing();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.validateNavigationToSnb(), "Navigation to SNB failed");
        snbPage.selectCreateAlertButtonOnSnbPage();
        papPage.clickonAdlocation();
        alertPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateAlert());
    }

    // ANDROID-771:Sort button on S&B screen for Services
    // Create Alert should be present.
    @Test
    public void verifySortAndCreateAlertButtonSnb() throws Exception
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.swipeVertically(testData.get("showAllOption"), testData.get("servicesSearchBar"));
        servicesPage.clickOnBrowseAds();
        servicesPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.validateSortButtonOnSnb(), "Sort button not present on SNB");
        Assert.assertTrue(snbPage.validateCreateAlertButton(), "Create Alert button not present on SNB");

    }

    // ANDROID-772, 3020:View Change in S&B screen for Services
    // Default view on SNB should be List View.
    // All premium ads must be sorted and viewed first and then all free ads.
    @Test
    public void verifyDefaultViewInSnbIsListView() throws Exception
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.swipeVertically(testData.get("showAllOption"), testData.get("servicesSearchBar"));
        servicesPage.clickOnBrowseAds();
        servicesPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
        snbPage.hideOverlayLayout();
        Assert.assertEquals(snbPage.getAdCount(), 4, "Not opened list view");
        Assert.assertEquals(snbPage.getPremiumAdCount(), 4, "Premium ads not listed first");
        realEstateSnbPage.ClickFilterIcon();
        realEstateSnbPage.clickOnLocality();
        alertPage.selectelementWithoutScroll(testData.get("filterLocality1"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectelementWithoutScroll(testData.get("filterLocality2"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectelementWithoutScroll(testData.get("filterLocality3"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        realEstateSnbPage.clickOnApplyFilter();
        List<String> locality=realEstateSnbPage.snbLaocality();
        for (int i=0;i<locality.size();i++)
        {
            Assert.assertTrue(locality.get(i).contains(testData.get("filterLocality1"))|locality.get(i).contains(testData.get("filterLocality2"))|locality.get(i).contains(testData.get("filterLocality3")),"fetched Snb locality" + locality);
        }
//        snbPage.selectGalleryViewButton();
//        Assert.assertEquals(snbPage.getAdCount(), 6, "Not opened grid view");
    }

    // ANDROID-852:Verify the 'Quikr Connect' option under the category 'Services'
//    @Test
    public void verifyQuikrConnectOption()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
//        hompage.ApplaunchPopup();
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
//        servicesPage.selectelementWithoutScroll(testData.get("categoryName"), QuikrAppEnums.CATEGORY_SERVICES);
        Assert.assertTrue(snbPage.isQuikrConnectOptionPresent(), "Quikr connect option not present");
    }

    // ANDROID-853 to 865, 3028:Verify whether Quikr Connect is available for subcategories
//    @Test
    public void verifyQuikrConnectOptionForSubcategories()
    {
        Hompage homePage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        snbPage.clickOnQuikrConnectOption();
        servicesPage.selectelementWithoutScroll(testData.get("homeRepair"), QuikrAppEnums.SERVICES_INSTACONNECT_CATEGORY);
        servicesPage.selectelementWithoutScroll(testData.get("homeServicesSubcategory"), QuikrAppEnums.SERVICES_INSTACONNECT_SUBCAT);
        servicesPage.selectNotAServiceProvider();
        Assert.assertTrue(snbPage.isInstaconnectNowOptionPresent(), "Instaconnect now option not present for subcategory " + testData.get("instaConnectCategory")+ testData.get("instaConnectSubcategory"));
    }

//    @Test
    public void verifyInstaConnectPage()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        snbPage.clickOnQuikrConnectOption();
        servicesPage.selectelementWithoutScroll(testData.get("homeRepair"), QuikrAppEnums.SERVICES_INSTACONNECT_CATEGORY);
        servicesPage.selectelementWithoutScroll(testData.get("homeServicesSubcategory"),QuikrAppEnums.SERVICES_INSTACONNECT_SUBCAT);
        servicesPage.selectNotAServiceProvider();
        snbPage.clickOnInstaconnectNowOption();
        Assert.assertTrue(snbPage.isInstaconnectNamePresent(), "Name not present in instaconnect page");
        Assert.assertTrue(snbPage.isInstaconnectMobileNoPresent(), "Mobile no not present in instaconnect page");

    }
//
//    // ANDROID-866 to 878:Verify View Ad listing for subcategories
//    @Test(dataProvider = "getSubcategoriesForServices",dataProviderClass = Data.class)
//    public void verifyViewAdListingForSubcategories(String subcategory)
//    {
//        Hompage homePage = new Hompage(driver);
//        ServicesPage servicesPage = new ServicesPage(driver);
//        SnbPage snbPage = new SnbPage(driver);
////        hompage.ApplaunchPopup();
//        homePage.selectcategoriesFromHomePageCategoryMenu();
//        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
//        servicesPage.selectelementWithoutScroll(subcategory, QuikrAppEnums.CATEGORY_SERVICES);
//        snbPage.viewAdListing();
//        Assert.assertTrue(snbPage.validateNavigationToSnb(), "Navigation to SNB failed for subcategory " + subcategory);
//    }
//
//    // ANDROID-879 to 891:Verify click on the 'Connect Now' for subcategories
//    @Test(dataProvider = "getSubcategoriesForServices", dataProviderClass = Data.class)
//    public void verifyClickConnectNowForSubcategories(String subcategory)
//    {
//        Hompage homePage = new Hompage(driver);
//        ServicesPage servicesPage = new ServicesPage(driver);
//        SnbPage snbPage = new SnbPage(driver);
//        QuikrConnectPage quikrConnectPage = new QuikrConnectPage(driver);
////        hompage.ApplaunchPopup();
//        homePage.selectcategoriesFromHomePageCategoryMenu();
//        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
//        servicesPage.selectelementWithoutScroll(subcategory, QuikrAppEnums.CATEGORY_SERVICES);
//        snbPage.connectNow();
//        Assert.assertTrue(quikrConnectPage.isQuikrConnectTextPresent(), "Quikr Connect text not present on Quikr Connect UI");
////        Assert.assertTrue(snbPage.validateNavigationToSnb(), "Navigation to SNB failed for subcategory "+subcategory);
//    }

    // ANDROID-3033, 3037, 3081, 3088, 3120:Verify Quikr Connect for target cities.
//    @Test
    public void verifyQuikrConnectForTargetCities()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.clickOnHomeServices();
        servicesPage.selectelementWithoutScroll(testData.get("homeServicesCategory"), QuikrAppEnums.CATEGORY_SERVICES);
        servicesPage.selectelementWithoutScroll(testData.get("homeServicesSubcategory"), QuikrAppEnums.Services_HomeSubcategories);
        Assert.assertTrue(servicesPage.isInstaconnectInterstitialPresent(), "Instaconnect interstitial not present");
        Assert.assertTrue(servicesPage.isSkipOptionPresent(), "Skip option not present on interstitial");
        Assert.assertTrue(servicesPage.isConnectNowButtonPresent(), "Talk Now button not present on interstitial");
        servicesPage.clickOnTalkNow();
        Assert.assertTrue(servicesPage.verifyInstaconnectAttributesPresent(), "Instaconnect attributes not present");
        Assert.assertTrue(servicesPage.verifyInstaconnectCallingScreen(), "Not on instaconnect calling screen");
    }

    // ANDROID-3036, 3041:Verify QuikrConnect Interestitial image
//    @Test
    public void verifyQuikrConnectInterstitialImage()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.clickOnHomeServices();
        servicesPage.selectelementWithoutScroll(testData.get("quikrConnectCategory"), QuikrAppEnums.CATEGORY_SERVICES);
        servicesPage.selectelementWithoutScroll(testData.get("quikrConnectSubcategory"), QuikrAppEnums.Services_HomeSubcategories);
        Assert.assertTrue(servicesPage.isSkipOptionPresent(), "Skip option not present on interstitial");
        Assert.assertTrue(servicesPage.isConnectNowButtonPresent(), "Connect Now button not present on interstitial");
        Assert.assertTrue(servicesPage.verifyTappingOnConnectNow(), "Not redirecting to Quikr Services webview");
    }

    // ANDROID-3040:Verify tapping on Book Now
//    @Test
    public void verifyTappingOnBookNow()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
//        hompage.ApplaunchPopup();
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.clickOnHomeServices();
        servicesPage.selectelementWithoutScroll(testData.get("categoryName"), QuikrAppEnums.CATEGORY_SERVICES);
        servicesPage.selectelementWithoutScroll(testData.get("subcategoryName"), QuikrAppEnums.Services_HomeSubcategories);
        Assert.assertTrue(servicesPage.verifyTappingOnBookNow(), "Not redirecting to Quikr Helpr app");
    }






}
