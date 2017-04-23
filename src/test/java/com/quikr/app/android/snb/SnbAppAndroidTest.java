package com.quikr.app.android.snb;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.cars.CarsPage;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.app.android.realEstate.realEstateHomePage.RealEstateHomePage;
import com.quikr.app.android.services.ServicesPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by Manvendra Singh on 13/8/15.
 */
public class  SnbAppAndroidTest extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_SNB_TESTDATA_FILE"));


    // @Test
    public void verifyCreateAlertOnSnBPage()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        PapPage papPage=new PapPage(driver);
        headerPage.selectHeaderSearchIcon();
        headerPage.searchTextFromHomePage(testData.get("query"));
        headerPage.clickOnSearchSuggestionAfterInputSomeText();
        snbPage.hideOverlayLayout();
        snbPage.selectCreateAlertButtonOnSnbPage();
        papPage.ClickonCategory();
        papPage.selectElements(testData.get("cars"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectElements(testData.get("car"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.selectBrand();
        alertPage.selectElements(testData.get("brand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectModel(testData.get("model"));
        alertPage.selectElements(testData.get("model"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectYear();
        alertPage.selectElements(testData.get("year"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.scroll(testData.get("Colors"));
        papPage.selectColor();
        alertPage.selectElements(testData.get("color"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        snbPage.scroll("fuel");
        papPage.selectFuelType();
        alertPage.selectElements(testData.get("fuel"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectOwners();
        alertPage.selectElements(testData.get("owner"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.clickonAdlocation();
        alertPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateAlert(),"Alert can not be created from snb page");

    }

    @Test(groups = "horizontal")
    public void verifyFilterApplyOnSnbPage()
    {
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        CarsPage carsPage=new CarsPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Home_Categories);
//        jobsPage.selectelementWithoutScroll(testData.get("mobilesPhone"), QuikrAppEnums.CHP_SUBCATEGORY);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        snbPage.selectBrandName();
        alertPage.selectelementWithoutScroll(testData.get("mobileBrandName"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        snbPage.selectApplyButton();
        Assert.assertTrue(snbPage.validateFilterOnSnbPage(), "can not filter by brand name");
    }

    @Test(groups = "horizontal")
    public void verifyAscendingSortOnSnbPage()
    {
        Hompage hompage=new Hompage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        CarsPage carsPage=new CarsPage(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        hompage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Home_Categories);
        //jobsPage.selectelementWithoutScroll(testData.get("mobilesPhone"), QuikrAppEnums.CHP_SUBCATEGORY);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
//        snbPage.selectGalleryViewButton();
        snbPage.selectSortButton();
        snbPage.selectelementWithoutScroll(testData.get("lowestPrice"),QuikrAppEnums.SNB_SORT_OPTIONS);
        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage(),"Can not sort as lowest price");

    }

//    @Test(groups = "horizontal", dataProvider = "getCategoriesforSorting",dataProviderClass = Data.class)
//    public void SortPriceOnAscendingOrder(String homePageCategoriesForSorting)
//    {
//        Hompage hompage=new Hompage(driver);
//        HeaderPage headerPage=new HeaderPage(driver);
//        SnbPage snbPage=new SnbPage(driver);
//        AlertPage alertPage=new AlertPage(driver);
//        PapPage papPage=new PapPage(driver);
//        CarsPage carsPage=new CarsPage(driver);
//        hompage.ApplaunchPopup();
//        hompage.selectElements(homePageCategoriesForSorting, QuikrAppEnums.HOMEPAGE_CATEGORY);
////        switch (homePageCategoriesForSorting)
////        {
////            case "Cars":
////                jobsPage.selectelementWithoutScroll(testData.get("Cars"),QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////            case "Mobiles":
////                jobsPage.selectelementWithoutScroll(testData.get("Mobile"),QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////            case "Electronics":
////                jobsPage.selectelementWithoutScroll(testData.get("Laptops"),QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////            case "Real Estate":
////                jobsPage.selectelementWithoutScroll(testData.get("appartment"),QuikrAppEnums.CHP_SUBCATEGORY);
////            case "Home":
////                jobsPage.selectelementWithoutScroll(testData.get("Antiques"),QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////            case "Pets":
////                jobsPage.selectelementWithoutScroll(testData.get("Accessories"),QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////        }
//        carsPage.selectMoreButton();
//        snbPage.selectGalleryViewButton();
//        snbPage.selectSortButton();
//        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage());
//    }

//    @Test(groups = "horizontal", dataProvider = "getCategoriesforSorting",dataProviderClass = Data.class)
//    public void SortPriceOnDescendingOrder(String homePageCategoriesForSorting)
//    {
//        Hompage hompage=new Hompage(driver);
//        HeaderPage headerPage=new HeaderPage(driver);
//        SnbPage snbPage=new SnbPage(driver);
//        AlertPage alertPage=new AlertPage(driver);
//        PapPage papPage=new PapPage(driver);
//        CarsPage carsPage=new CarsPage(driver);
//        hompage.ApplaunchPopup();
//        hompage.selectElements(homePageCategoriesForSorting, QuikrAppEnums.HOMEPAGE_CATEGORY);
////        switch (homePageCategoriesForSorting)
////        {
////            case "Cars":
////                jobsPage.selectelementWithoutScroll(testData.get("Cars"), QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////            case "Mobiles":
////                jobsPage.selectelementWithoutScroll(testData.get("Mobile"), QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////            case "Electronics":
////                jobsPage.selectelementWithoutScroll(testData.get("Laptops"), QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////            case "Real Estate":
////                jobsPage.selectelementWithoutScroll(testData.get("appartment"), QuikrAppEnums.CHP_SUBCATEGORY);
////            case "Home":
////                jobsPage.selectelementWithoutScroll(testData.get("Antiques"), QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////            case "Pets":
////                jobsPage.selectelementWithoutScroll(testData.get("Accessories"), QuikrAppEnums.CHP_SUBCATEGORY);
////                break;
////        }
//        carsPage.selectMoreButton();
//        snbPage.selectGalleryViewButton();
//        snbPage.selectSortHighestPriceButton();
//        Assert.assertTrue(snbPage.validateDescendingSortOnSnbPage());
//    }

//    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
//    public void verifyRoleForOfferAd(String categoriesForJobs)
//    {
//        hompage.ApplaunchPopup();
//        hompage.selectElements(testData.get("jobs"),QuikrAppEnums.HOMEPAGE_CATEGORY);
//        jobsPage.selectElements(categoriesForJobs,QuikrAppEnums.CHP_SUBCATEGORY);
//        for(int i=1;i<4;i++)
//        {
//            snbPage.openAdFromSnb(i);
//            vapPage.waitForTitleToBeVisibleAtVap();
//            Assert.assertTrue( vapPage.validateJobRoleForOfferAd(),"job role is not display for offer ad");
//            vapPage.navigateBack();
//        }
//    }

//    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
//    public void verifyRoleFrWantedAds(String categoriesForJobs)
//    {
//        hompage.ApplaunchPopup();
//        hompage.selectElements(testData.get("jobs"),QuikrAppEnums.HOMEPAGE_CATEGORY);
//        jobsPage.selectElements(categoriesForJobs,QuikrAppEnums.CHP_SUBCATEGORY);
//        snbPage.selectFilterButtonOnSnbPage();
//        snbPage.selectShowMoreFiltersButton();
//        snbPage.selectWantedAdsButton();
//        snbPage.selectApplyButton();
//        for(int i=1;i<4;i++)
//        {
//            snbPage.openAdFromSnb(i);
//            vapPage.waitForTitleToBeVisibleAtVap();
//            Assert.assertTrue( vapPage.validateJobRoleForWantedAds(),"job role is not display for offer ad");
//            vapPage.navigateBack();
//        }
//    }
    /**
     * Verify the header of the Filter screen
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfFilterScreen()
    {
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        CarsPage carsPage=new CarsPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Home_Categories);
//        jobsPage.selectelementWithoutScroll(testData.get("mobilesPhone"), QuikrAppEnums.CHP_SUBCATEGORY);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        Assert.assertTrue(snbPage.verifyHeaderOfFilterScreen());
    }
    /**
     * Verify the header in SNB of Cars category
     */

    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfCarsSNB()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Home_Categories);
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.verifyHeaderOfCarsSnb());
    }
    /**
     * Verify the buttons below the header in SNB of Mobiles category
     */

    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyButtonsBelowSnbMobiles()
    {
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        CarsPage carsPage=new CarsPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Home_Categories);
//        jobsPage.selectelementWithoutScroll(testData.get("mobilesPhone"), QuikrAppEnums.CHP_SUBCATEGORY);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        Assert.assertTrue(snbPage.verifyButtonsBelowSnb());
    }
    /**
     * Verify the header in SNB of Real Estate category
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfRealEstateHeader()
    {
        Hompage hompage=new Hompage(driver);
        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("realEstate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.verifyRealEstateHeader());
        Assert.assertTrue(snbPage.getHeaderText().contains("Results"));
    }

    /**
     * Verify the buttons below the header in SNB of Real Estate category
     **/

    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyButtonsBelowSRealEstateSnb()
    { Hompage hompage=new Hompage(driver);
        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("realEstate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.verifyButtonsBelowSnb());
    }
    /**
     * Verify the header in SNB of Services category
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfServices()
    {
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        ServicesPage servicesPage=new ServicesPage(driver);
        hompage.selectelementWithoutScroll("Services", QuikrAppEnums.Home_Categories);
        servicesPage.scroll("Browse Ads");
        servicesPage.clickOnBookNowCategories();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.verifyHeaderOfServicesSnb());
    }

    /**
     * verify buttons below services snb
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyButtonBelowServicesSnb()
    {
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        ServicesPage servicesPage=new ServicesPage(driver);
        hompage.selectelementWithoutScroll("Services", QuikrAppEnums.Home_Categories);
        servicesPage.scroll("Browse Ads");
        servicesPage.clickOnBookNowCategories();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.verifyButtonsBelowSnb());

    }

    @Test(groups="Horizontal")
    public void verifyBuyNowButton(){
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobiles"),QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        //snbPage.hideOverlayLayout();
        hompage.closeFullScreenInterstitialFromCategoryPage();
        hompage.closeFullScreenInterstitialFromCategoryPage();
        snbPage.scroll("Buy Now");
        snbPage.verifyBuyNowButton();
        Assert.assertTrue(snbPage.ifBuyNowPresent(),"Buy now button is present");

    }

}
