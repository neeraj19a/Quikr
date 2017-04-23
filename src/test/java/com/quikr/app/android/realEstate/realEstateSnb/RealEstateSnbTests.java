package com.quikr.app.android.realEstate.realEstateSnb;

import com.quikr.app.android.AppAndroidTestBase;
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
public class RealEstateSnbTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_REALESTATEHOME_TESTDATA_FILE"));

    /**
     *Android 1325, 2439: Verify the number of search results is displayed below the Locality in SNB screen
     * Android 1326:Verify the options  present in the SNB header
     */
    @Test
    public void validateResultInSnbHeader()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage =new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(realEstateHomePage.validateHomeCAtegories());
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
//        String SnbResultCount=realEstateSnbPage.ValidateResultCountOnSnbHeader();
//        Assert.assertTrue(SnbResultCount.contains("results"));
        Assert.assertTrue(realEstateSnbPage.validateSnbHeader());

    }

    /**
     * Android Sanity:2442
     *Android 1328:Create an Alert and verify
     */

    @Test
    public void createAlertFromSnb()
    {
        PapPage papPage=new PapPage(driver);
        Hompage hompage =new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage =new AlertPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(realEstateHomePage.validateHomeCAtegories());
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        realEstateSnbPage.clickOnCreateAlert();
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("rent"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.PostAdAsIndividual();
        papPage.selectElements("No. of Rooms", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("rooms"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Area Sq Ft", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("area"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Furnished",QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("furnished"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.clickonAdlocation();
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateAlert());

    }

    /**
     * Android Sanity 2444-2447
     * Android 329-33
     * filter validation tests for different Home UI categories
     */

    @Test
    public void validateFilterPageForDifferentCAtegories()
    {

        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
//        hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        for (int i = 0; i < 2; i++)
        {
            switch (i)
            {
                case 0:
                    realEstateHomePage.clickOnResidential();
                    realEstateHomePage.clickOnSearch();
                    realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
                    snbPage.hideOverlayLayout();
                    realEstateSnbPage.ClickFilterIcon();
                    Assert.assertTrue(realEstateSnbPage.validateFilterPageResidential());
                    realEstateHomePage.navigateBack();
                    realEstateHomePage.navigateBack();
                    break;
                case 1:
                    realEstateHomePage.clickOnCommercial();
                    realEstateHomePage.clickOnSearch();
                    realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
                    realEstateSnbPage.ClickFilterIcon();
                    Assert.assertTrue(realEstateSnbPage.validateFilterPageCommercial());
                    realEstateHomePage.navigateBack();
                    realEstateHomePage.navigateBack();
                    break;

                case 2:
                    realEstateHomePage.clickOnAgriculture();
                    realEstateHomePage.clickOnSearch();
                    realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
                    realEstateSnbPage.ClickFilterIcon();
                    Assert.assertTrue(realEstateSnbPage.validateFilterPageAgrriculture());
                    break;
            }
        }
    }

    /**
     * Android Sanity 2448
     * Android 1334 :Update few fields in the Filter UI, tap on RESET and verify
     */
    @Test
    public void validateResetOptionAfterApplyingFilter()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        realEstateSnbPage.ClickFilterIcon();
        realEstateSnbPage.SelectFilterCheckBox(0);
        realEstateSnbPage.SelectFilterCheckBox(1);
        realEstateSnbPage.SelectFilterCheckBox(2);
        realEstateSnbPage.clickOnResetFilterButton();
        Assert.assertTrue(realEstateSnbPage.validateResetFilterButtonAction());
    }

    /**Android sanity:2465
     * Verify the usertype displayed in SNB screen
     */
    @Test
    public void validateUserTypeDisplayedOnSnb()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(realEstateSnbPage.validateSellerNAmeOnsnb());
        Assert.assertTrue(realEstateSnbPage.validateSellerTypeOnSnb());
    }

    /**
     * Android sanity:2484
     * User should be able to select the localities from the list of selected localities from filter
     */

    @Test
    public void validateLocalitySelectedInFilter()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        AlertPage alertPage=new AlertPage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
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
    }
    /**Android Sanity:3253
     * Tap on the auto suggested option in 'Recent Search' section and verify
     */

    @Test
    public void validateSnbRecentlySelectedCityOnSearch()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.SendSearchText(testData.get("search"));
        realEstateHomePage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        realEstateHomePage.navigateBack();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selecOptionsFromAutoSuggest(0);
//        realEstateHomePage.selectelementWithoutScroll(testData.get("locality"),QuikrAppEnums.RealEstate_Locality);
        String snbLocality=realEstateSnbPage.AdLocalityOnSnb();
        Assert.assertTrue(testData.get("locality").equalsIgnoreCase(snbLocality),"fetched locality" + snbLocality);
    }


}
