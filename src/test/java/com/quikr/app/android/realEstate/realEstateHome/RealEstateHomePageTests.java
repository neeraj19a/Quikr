package com.quikr.app.android.realEstate.realEstateHome;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.Constants.MobileConstants;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.realEstate.realEstateHomePage.RealEstateHomePage;
import com.quikr.app.android.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra on 8/10/15.
 */
public class RealEstateHomePageTests extends AppAndroidTestBase {



    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_REALESTATEHOME_TESTDATA_FILE"));

    /**
     * Android Sanity:2430
     * Android 1316:Verify posting the ad without providing mobile No. (both Offering and Wanted)
     */
    @Test(groups = "horizontal")
    public void postAdWithoutNumberOfferType() {

        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("subCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("rent"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Real Estate";
        System.out.println(papPage.getPapCategory());
        papPage.PostAdAsIndividual();
        papPage.scroll("Price");
        papPage.setPrice(testData.get("price"));
        papPage.clickOnNumberOfRooms();
        papPage.selectelementWithoutScroll(testData.get("rooms"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.clickOnSelectArea(testData.get("area"));
        papPage.clickOnFurnished();
        papPage.selectelementWithoutScroll(testData.get("SemiFurnished"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"),category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd());


    }

    /**
     * Android 1316:Verify posting the ad without providing mobile No. (both Offering and Wanted)
     */
    @Test(groups = "horizontal")
    public void postAdWithoutNumberWantType() {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("subCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("rent"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Real Estate";
        System.out.println(papPage.getPapCategory());
        papPage.PostAdAsTenantWantType();
        papPage.PostAdAsIndividual();
        papPage.scroll("Price");
        papPage.setPrice(testData.get("price"));
        papPage.selectElements("No. of Rooms", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("rooms"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Area Sq Ft", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("area"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Furnished", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("furnished"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"),category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd(),"user is not able to post as Want type without number");
    }

    /**Android Sanity:2433
     * Android 1319: Verify the categories present in Real Estate UI
     */
    @Test
    public void validateRealEstateHomeCategories()
    {
        Hompage hompage = new Hompage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(realEstateHomePage.validateHomeCAtegories());
    }

    /**
     * Android sanity:2434-2436
     *Android :1320-22 :Verify the subcategory present in 'Agriculture','Residential','Commercial'
     */
    @Test
    public void validateRealEstateHomeSubCategories()
    {

        Hompage hompage = new Hompage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        MobileConstants mobileConstants = new MobileConstants();
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(realEstateHomePage.validateHomeCAtegories());
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSpinner();
        List<String> subcat = realEstateHomePage.findRelatedSubCat();
        for (int i = 0; i < subcat.size(); i++)
        {
            Assert.assertTrue(subcat.get(i).equals(mobileConstants.RealEstateResendetialSubCAt.get(i)),"subcat cat mismatch"+subcat.get(i) +mobileConstants.RealEstateResendetialSubCAt.get(i));
        }
        realEstateHomePage.navigateBack();
        realEstateHomePage.clickOnCommercial();
        realEstateHomePage.clickOnSpinner();
        List<String> subcat1 = realEstateHomePage.findRelatedSubCat();
        for (int i = 0; i < subcat1.size(); i++)
        {
            Assert.assertTrue(subcat1.get(i).equals(mobileConstants.RealEstateCommercialSubCAt.get(i)),"subcat cat mismatch" +subcat1.get(i)+mobileConstants.RealEstateCommercialSubCAt.get(i));
        }
        realEstateHomePage.navigateBack();
        realEstateHomePage.clickOnAgriculture();
        realEstateHomePage.clickOnSpinner();
        List<String> subcat2 = realEstateHomePage.findRelatedSubCat();
        for (int i = 0; i < subcat2.size(); i++)
        {
            Assert.assertTrue(subcat2.get(i).equals(mobileConstants.RealEstateAgricultureSubCAt.get(i)));
        }

    }

    /**
     * Android Sanity:2437
'    * Android Sanity:2438
     * Verify user can search for locality
     * Verify auto suggest is displayed when 3 characters is typed in 'Search' engine
     */

    @Test
    public void verifyAutosuggestOnTypingThreeCharacter()
    {
        Hompage hompage = new Hompage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(realEstateHomePage.validateHomeCAtegories());
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.SendSearchText(testData.get("search"));
        List<String> AutoSuggest =realEstateHomePage.findAutogestedResults();
        for (int i =0;i<AutoSuggest.size();i++)
        {
            Assert.assertTrue(AutoSuggest.get(i).contains("Mar"));
        }
    }
/**
 * Android Sanity:3251
 * Search for a locality, tap on the search bar and observe the 'Recent Search' section
 */
@Test
    public void validateRecentlySelectedCityOnSearch()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.SendSearchText(testData.get("search"));
        realEstateHomePage.selecOptionsFromAutoSuggest(1);
        snbPage.hideOverlayLayout();
        realEstateHomePage.navigateBack();
        realEstateHomePage.clickOnSearch();
        String locality=realEstateHomePage.recentlySelectedLocality();
        Assert.assertTrue(testData.get("locality").equalsIgnoreCase(locality),"expected" +  testData.get("locality") + "actual" + locality);
    }

    // Banner ad should be displayed when the city is selected
    @Test
    public void validateRealEstateChpBanner()
    {
        Hompage hompage = new Hompage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(realEstateHomePage.isChpBannerPresent());
    }

    // Verify the options present in 'Popular Searches'
    @Test
    public void validatePopularSearchCategories() {
        Hompage hompage = new Hompage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        MobileConstants mobileConstants = new MobileConstants();
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        List<String> popularCategoriesList = realEstateHomePage.getPopularSearchCategories();
        for (int i = 0; i < popularCategoriesList.size(); i++) {
            Assert.assertTrue(popularCategoriesList.get(i).equals(mobileConstants.optionsForPopularSearch.get(i)), "Popular search category mismatch " + popularCategoriesList.get(i) + mobileConstants.optionsForPopularSearch.get(i));
        }
    }

    // 'Clock' icon should be present for the recent search items
    // 'Search' icon should be present for the popular search items
    @Test
    public void validateRecentandPopularSearchIcons()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.SendSearchText(testData.get("search"));
        realEstateHomePage.selecOptionsFromAutoSuggest(1);
        snbPage.hideOverlayLayout();
        realEstateHomePage.navigateBack();
        realEstateHomePage.clickOnSearch();
        String locality=realEstateHomePage.recentlySelectedLocality();
        Assert.assertTrue(realEstateHomePage.isClockAndSearchIconPresent(), "Clock/search icon not present");
    }

    // 'Locality' text should be displayed at the right corner of the locality searched in 'Recent Search' section
    @Test
    public void validateLocalityTextOnRealEstateChp()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.SendSearchText(testData.get("search"));
        realEstateHomePage.selecOptionsFromAutoSuggest(1);
        snbPage.hideOverlayLayout();
        realEstateHomePage.navigateBack();
        realEstateHomePage.clickOnSearch();
        String locality=realEstateHomePage.recentlySelectedLocality();
        Assert.assertTrue(realEstateHomePage.isLocalityTextPresent(), "Locality text not present for recent search");
    }

    // 'Project' text should be displayed at the right corner of the project searched in 'Recent Search' section
    @Test
    public void validateProjectTextOnRealEstateChp()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.SendSearchText(testData.get("project"));
        realEstateHomePage.selecOptionsFromAutoSuggest(1);
        snbPage.hideOverlayLayout();
        realEstateHomePage.navigateBack();
        realEstateHomePage.clickOnSearch();
        Assert.assertTrue(realEstateHomePage.isProjectTextPresent(), "Project text not present for recent search");
    }





    

}
