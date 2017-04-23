package com.quikr.app.android.cars.shortlist;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra singh on 25/8/15.
 */
public class ShortlistAppAndroidTest extends AppAndroidTestBase
{



    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_Shortlist_TESTDATA_FILE"));


    /** Android 217
     * Premium,Inspected tags and normal ad's should be displayed on "Recently Viewed Ad's" screen for every ad viewed recently.
     */
    @Test(groups = "horizontal")
    public  void validateInspectedAdsDisplayedOnRecentlyViewedAd()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        String VapTitle=carsNewPage.AdTitle();
        menuePage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectelementWithoutScroll(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(menuePage.checkInspected());
        menuePage.openAdFromRecentlyViewedAds();
        String recentlyViewedAdTitle=carsNewPage.AdTitle();
        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));

    }


    /**
     * Premium tag should not be displayed on "My Shortlist" screen for every non premium shortlisted ad's
     */

//    @Test
    public  void validateONSearchNonPremiumAdsDisplayedInMyShortlist()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage =new CarsNewPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.ApplaunchPopup();
        headerPage.selectHeaderSearchIcon();
        headerPage.searchTextFromHomePage(testData.get("Honda"));
        headerPage.clickOnSearchSuggestionAfterInputSomeText();
        snbPage.hideOverlayLayout();
        carsNewPage.swipeOnCarsSNB();
        carsNewPage.swipeOnCarsSNB();
        carsNewPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        Assert.assertTrue(!menuePage.checkPremium());
    }

    /** android 216
     *  Inspected tag should be displayed on 'My Shortlist' screen for every ad.
     */
    @Test(groups = "horizontal")
    public  void validateONSearchInspectedAdsDisplayedInMyShortlist()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        CarsNewPage carsNewPage =new CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        snbPage.clickOnSearchTextFromSnbHeadre();
        headerPage.searchTextFromHomePage(testData.get("Honda"));
        headerPage.clickOnSearchSuggestionAfterInputSomeText();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        Assert.assertTrue(menuePage.checkInspected());
    }

    /**
     * Inspected tag should be displayed on "Recently Viewed Ad's" screen for every ad viewed recently.
     */
    @Test(groups = "horizontal")
    public  void validateOnSearchInspectedAdsDisplayedOnRecentlyViewedAd()
    {
        MenuPage menuePage=new MenuPage(driver);
        SnbPage snbPage =new SnbPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        CarsNewPage carsNewPage =new CarsNewPage(driver);
        headerPage.selectHeaderSearchIcon();
        headerPage.searchTextFromHomePage(testData.get("Honda"));
        headerPage.clickOnSearchSuggestionAfterInputSomeText();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        String VapTitle=carsNewPage.AdTitle();
        menuePage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(menuePage.checkInspected());
        menuePage.openAdFromRecentlyViewedAds();
        String recentlyViewedAdTitle=carsNewPage.AdTitle();
        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));
    }




    /**
     * Android 431:To verify the checkboxes for the Ad in "My Shortlist" screen.
     */
    @Test(groups = "compareAndShortlist")
    public void validateCompareCheckBoxOnMyShortlist()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        Assert.assertTrue(menuePage.verifyCheckboxOnMyShortlist());

    }

    /**
     * sanity :3469
     * Androi 433:To verify whether we can compare Ads other than Cars,bikes and commercial vehicles.

     */
    @Test(groups = "compareAndShortlist")
    public void validateCompareCheckBoxOnMyShortlistForOtherCategories()
    { MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectelementWithoutScroll(testData.get("home"), QuikrAppEnums.CATEGORY_MENU);
        hompage.selectelementWithoutScroll(testData.get("Mobiles"), QuikrAppEnums.Home_Categories);
        snbPage.viewMoreAds(0);
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        menuePage.selectMobilesOnMyShortlist();
        Assert.assertTrue(!menuePage.verifyCheckboxOnMyShortlist());
        menuePage.selectCarsOnMyShortlist();
        Assert.assertTrue(menuePage.verifyCheckboxOnMyShortlist());
    }

    /**
     * Sanity :3471
     * Android 434: To verify the behaviour of  "Compare this Ad" checkbox in My Shortlist page.
     */
    @Test(groups = "compareAndShortlist")
    public void validateUserRediredtedToComparePage()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        carsNewPage.openAdFromSnb(1);
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        menuePage.selectCompareCheckBox(0);
        menuePage.selectCompareCheckBox(1);
        Assert.assertTrue(menuePage.verifyCompareButtonMyShortlist());
        menuePage.clickOnCompareButton();
        Assert.assertTrue(menuePage.validateComparePage());

    }
    /**
     * Android 435:To verify the number of Ads that can be compared.
     */
    @Test(groups = "compareAndShortlist")
    public void validateNumberOfAdsComparable()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        carsNewPage.openAdFromSnb(1);
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        carsNewPage.swipeOnCarsSNB();
        carsNewPage.swipeOnCarsSNB();
        carsNewPage.openAdFromSnb(0);
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        int count=menuePage.CountNumberofAdsOnMyShortlist();
        Assert.assertEquals(3, count, "all shortlisted ads not reflected");
        menuePage.selectCompareCheckBox(0);
        menuePage.selectCompareCheckBox(1);
        int countAfterSelectingCheckbox=menuePage.CountNumberofAdsOnMyShortlist();
        Assert.assertEquals(2, countAfterSelectingCheckbox, "more than two ads selected for comparing");
    }

    /**AndroidSanity:2274
     * To verify the behaviour of 'Remove Ad' checkbox on My Shortlist screen.
     */
    @Test(groups = "compareAndShortlist")
    public void validateRemovedAdsFromMyShortlist()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        menuePage.removeShortlistedAd();
        Assert.assertTrue(!menuePage.verifyCheckboxOnMyShortlist());

    }
    /**
     * Android 468:To verify the format of attributes for cars in Compare screen
     */
    @Test(groups = "compareAndShortlist")
    public void validateComparingAttributesForCArs()throws Exception
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        carsNewPage.openAdFromSnb(1);
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        menuePage.selectCompareCheckBox(0);
        menuePage.selectCompareCheckBox(1);
        Assert.assertTrue(menuePage.verifyCompareButtonMyShortlist());
        menuePage.clickOnCompareButton();
        Assert.assertTrue(menuePage.validateCompareCarsAttributes("Power"));

    }

    /**
     * Android 467: To verify the format for cars in Compare screen
     * Android 469:To verify the format for bikes in Compare screen
     * Android 473:To verify the format for bicycle in Compare screen
     * Android 475:To verify the format for commercial vehicle in Compare screen
     * @throws Exception
     */
    @Test(groups = "compareAndShortlist")
    public void validateComparePageFormatForCarsSubCategory()throws  Exception
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        for (int i=0;i<4;i++)
        {
            Thread.sleep(5000);
            snbPage.clickOnChangeSubCAtFromSNB();
            snbPage.selectSubCatFromSNB(i);
            carsNewPage.openAdFromSnb(0);
            switch (i)
            {
                case 0:
                    snbPage.hideOverlayLayout();
            }
            snbPage.selectFavouriteButton();
            snbPage.navigateBack();
            Thread.sleep(2000);
            carsNewPage.swipeOnCarsSNB();
            carsNewPage.swipeOnCarsSNB();
            carsNewPage.openAdFromSnb(0);
            snbPage.selectFavouriteButton();
            snbPage.navigateBack();
            snbPage.selectMenuButtonAtSnbPage();
            menuePage.selectMyShortlist();
            menuePage.selectCompareCheckBox(0);
            menuePage.selectCompareCheckBox(1);
            Assert.assertTrue(menuePage.verifyCompareButtonMyShortlist());
            menuePage.clickOnCompareButton();
            Assert.assertTrue(menuePage.validateComparePageFormat()|menuePage.ComparisonError());
            menuePage.navigateBack();
            menuePage.navigateBack();
        }
    }
    @Test
    /**
     *  3466 sanity:validate inspected ad displayed on my shortlist
     */

    public void validatedInspectedAdInMyShortlist()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        Assert.assertTrue(menuePage.checkInspected());

    }

    /**
     *  sanity :Android 3470
     *  User should not be able to compare two shortlisted Ads of sub category spare parts
     */
    @Test
    public void validateCompareButtonForSpareParts()throws  Exception
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        Thread.sleep(5000);
        snbPage.clickOnChangeSubCAtFromSNB();
        snbPage.selectSubCatFromSNB(4);
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        Thread.sleep(2000);
        carsNewPage.swipeOnCarsSNB();
        carsNewPage.swipeOnCarsSNB();
        carsNewPage.openAdFromSnb(0);
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        Assert.assertTrue(!menuePage.verifyCheckboxOnMyShortlist());

    }

    /**
     * Android Sanity:3472
     * android sanity :When user selects two shortlisted Ads of different sub category(One Ad in Cars and another Ad in Bikes&scooters) to compare, compare button should not appear at the bottom
     */

    @Test
    public void validateCompareButtonWhenAdFromDifferentSubCatIsSelected()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new  CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        carsNewPage.navigateBack();
        snbPage.clickOnChangeSubCAtFromSNB();
        snbPage.selectSubCatFromSNB(1);
        carsNewPage.openAdFromSnb(0);
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        menuePage.selectCompareCheckBox(0);
        menuePage.selectCarsOnMyShortlist();
        menuePage.selectCompareCheckBox(0);
        Assert.assertTrue(!menuePage.verifyCompareButtonMyShortlist());

    }

}





