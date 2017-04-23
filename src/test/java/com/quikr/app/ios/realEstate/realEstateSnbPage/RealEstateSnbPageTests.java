package com.quikr.app.ios.realEstate.realEstateSnbPage;

import com.quikr.app.ios.AppiOSTestBase;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 20/11/15.
 */
public class RealEstateSnbPageTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_REALESTATESNBPAGE_TESTDATA_FILE"));

    /**
     * iOS-2441:Verify the results can be sorted in SNB screen
     */
    @Test
    public void verifyAscendingSortByPriceForRealEstate()
    {
//        HomePage homePage=new HomePage(driver);
//        SnbPage snbPage=new SnbPage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
////        homePage.selectDontAllowOption();
//       // homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
//        realEstateSnbPage.clickOnSortButtonInRealEstateSnbPage();
//        realEstateSnbPage.selectLowestPrice();
//       // snbPage.selectPriceButton();
//        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage(), "some issue in sorting as needed");
    }

    /**
     *  iOS-2443:Tap on 'Filter' icon and verify
     *
     *  iOS-2444:Verify the tabs present in Residential Filter
     */
    @Test
    public void verifyResidentialFilter()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//  //      homePage.selectDontAllowOption();
//       // homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validateResidentialFilterTabs(),"all residential filter option don't present");
    }

    /**
     * iOS-2443:Tap on 'Filter' icon and verify
     *
     * iOS-2445:Verify the tabs present in Commercial Filter
     */
    @Test
    public void verifyCommercialFilterTabs()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//    //    homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateCommercialCategory"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validateCommercialFilterTabs(),"all tabs of commercial filter are not present");
    }


    /**
     * iOS-2443:Tap on 'Filter' icon and verify
     *
     * iOS-2446:Verify the tabs present in Agriculture Filter
     */
    @Test
    public void verifyAgricultureFilterTabs()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//      //  homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateAgricultureCategory"));;
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityNameforAgriculture"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validateAgricultureFilterTabs(),"all the tabs of agriculture are not present");
    }

    /**
     *  iOS-2439:Verify the number of search results is displayed below the Locality in SNB screen
     *
     *
     *  iOS-2451:Verify the Locality searched is displayed in SNB and VAP screens
     */
    @Test
    public void verifyLocalitySameAsInHeaderOnSnbPageOfRealEstate() throws Exception
    {
//        HomePage homePage=new HomePage(driver);
//        SnbPage snbPage=new SnbPage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//        VapPage vapPage=new VapPage(driver);
//
//
//     //   homePage.selectDontAllowOption();
////        homePage.clickOnSelectCity();
////        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//
//        System.out.println("$$$$$" + realEstateSnbPage.getAdCount(testData.get("localityName")));
////        for (int i=0;i<5;i++)
////        {
////            snbPage.openAdOnSnbPageForGeneralCategory(i);
////            Assert.assertTrue(realEstateVapPage.validateLocalityOnVapPage(),"locality not match correctly as selected");
////            vapPage.clickOnVapBackButton();
////        }

    }


    /**
     *  iOS-2448:Update few fields in the Filter UI, tap on RESET and verify
     */
    @Test
    public void verifyFilterResetFunctionality()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//        PapPage papPage=new PapPage(driver);
//
//       // homePage.selectDontAllowOption();
//       // homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.clickOnLocalityButton();
//        papPage.selectLocalityName();
//        papPage.selectApplyButton();
//        Assert.assertTrue(realEstateSnbPage.validateLocalityOptionName(),"locality name is not selected");
//        realEstateSnbPage.selectResetButton();
//        Assert.assertTrue(!realEstateSnbPage.validateLocalityOptionName(),"reset functionality is not working");
    }

    /**
     * iOS-2527:Verify the options present in Commercial Buy 'Posted By' field
     */
    @Test
    public void verifyCommercialOptionsPostedBy()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //    homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateCommercialCategory"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("postedBy"));
//        Assert.assertTrue(realEstateSnbPage.validateCommercialOptionsOrResidentialPostedBy(),"all the options are not present in filter's posted by ");
    }

    /**
     * iOS-2528:Verify the fields present in Agriculture Buy Filter UI
     */
    @Test
    public void verifyAgricultureFilterUiAllFilterOptions()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //  homePage.selectDontAllowOption();
////        homePage.clickOnSelectCity();
////        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateAgricultureCategory"));;
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityNameforAgriculture"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validateAgricultureFilterUiAllFilterOptions(),"all options are not present on agriculture ui filter");
    }

    /**
     *  iOS-2529:Verify the Max price displayed in Agriculture Filter UI
     */
    @Test
    public void verifyMaxPriceForAgriculture()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //  homePage.selectDontAllowOption();
//       // homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateAgricultureCategory"));;
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityNameforAgriculture"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validateMaxPriceForResidentialOrCommercialOrAgriculture(),"max price can't be match as expected for agriculture");
    }

    /**
     * iOS-2526:Verify the max price displayed in Commercial Buy Filter UI
     */
    @Test
    public void verifyMaxPriceForCommercial()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //    homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateCommercialCategory"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validateMaxPriceForResidentialOrCommercialOrAgriculture(),"max price can't be match as expected for commercial");
    }

    /**
     * iOS-2525:Verify the options in 'Property Type' in Commercial Buy Filter UI
     */
    @Test
    public void verifyPropertyTypeOptionsInCommercial()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //    homePage.selectDontAllowOption();
//        //homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateCommercialCategory"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validateAllPropertyOptionsInCommercial(),"all property's options can't be present");
    }

    /**
     * iOS-2523:Verify the options present in Posted By field
     */
    @Test
    public void verifyPropertyTypeAllOptionsInCommercialForRent()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //    homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateCommercialCategory"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectRentButton();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("postedBy"));
//        Assert.assertTrue(realEstateSnbPage.validateCommercialOptionsPostedByForRent(),"all options can't be present in commercial category for rent in filter");
    }

    /**
     * iOS-2522:Verify the max Area displayed in Commercial Rent Filter UI
     */
    @Test
    public void verifyMaxAreaInCommercialForRent()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //    homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateCommercialCategory"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectRentButton();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("area"));
//        Assert.assertTrue(realEstateSnbPage.validateMaxAreaInFilterInCommercialCategoryForRent(),"area can be diff from as expected");
    }

    /**
     * iOS-2521:Verify the max price displayed in Commercial Rent Filter UI
     */
    @Test
    public void verifyMaxPriceForRentInCommercial()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //    homePage.selectDontAllowOption();
//     //   homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("realEstateCommercialCategory"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectRentButton();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validateMaxPriceInFilterInCommercialForRent(),"diff in max price as expected");
    }

    /**
     * iOS-2515:Verify the options present in Gender field
     */
    @Test
    public void verifyOptionsPresentsInGenderFieldInResidential()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectPgOrFlat();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("gender"));
//        Assert.assertTrue(realEstateSnbPage.validateGenderOptionsInResidential(),"all the gender option are not present");
    }

    /**
     * iOS-2513:Verify the options in 'Property Type' in Residential PG Flatmates Filter UI
     */
    @Test
    public void verifyPropertyTypeInResidentialForPgOrFlat()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectPgOrFlat();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validatePropertyTypeInResidentialForPgOrFlat(),"all options are not present in property type");
    }

    /**
     * iOS-2512:Verify the options in 'Property Type' in Residential Buy Filter UI
     */
    @Test
    public void verifyPropertyTypeOptionsInResidentialForBuy()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//       // homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectBuyButton();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        Assert.assertTrue(realEstateSnbPage.validatePropertyTypeOptionsInResidentialForBuy(),"all the required options aren't present");
    }

    /**
     * iOS-2511:Verify the max Price Range displayed in Residential Buy Filter UI
     */
    @Test
    public void verifyMaxPriceRangeInResidentialForBuy()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectBuyButton();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("maxPrice"));
//        Assert.assertTrue(realEstateSnbPage.validateMaxPriceForResidentialOrCommercialOrAgriculture(),"max price correctly not reflect for residential for buy");
    }

    /**
     *  iOS-2509:Tap on Back button and verify
     */
    @Test
    public void verifyBackButtonAfterClickOnFilterButton()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //homePage.selectDontAllowOption();
//       // homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.selectCrossButtonOnFilterPage();
//        Assert.assertTrue(realEstateSnbPage.validateLocalityOptionName(),"filter back button is not working");
    }

    /**
     * iOS-2508:Select the options in all the 3 tabs, tap on RESET and verify
     */
    @Test
    public void verifySelectFewOptionsInPropertyAndAmenitiesAndFurnishingTabsAnDCheckResetFunctionalityInResidentialCategory()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        //homePage.selectDontAllowOption();
//     //   homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.selectResidentialPropertyTypeOptionName(1);
//        realEstateSnbPage.selectAmenitiesTab();
//        realEstateSnbPage.selectAmenitiesPropertyTypeOptionName(3);
//        realEstateSnbPage.selectFurnishingTab();
//        realEstateSnbPage.selectFurnishingPropertyTypeOptionName(3);
//        realEstateSnbPage.selectResetButton();
//        realEstateSnbPage.selectAmenitiesTab();
//        Assert.assertTrue(realEstateSnbPage.validateAmenitiesPropertyTypeOptionNameAfterReset(3),"in amenities property type option name can't be reset");
//        realEstateSnbPage.selectPropertyTab();
//        Assert.assertTrue(realEstateSnbPage.validateResidentialPropertyTypeOptionNameAfterReset(1),"in Property property type option name can't be reset");
//        realEstateSnbPage.selectFurnishingTab();
//        Assert.assertTrue(realEstateSnbPage.validateFurnishingPropertyTypeOptionName(3),"in furnishing property type option name can't be reset");
    }

    /**
     * iOS-5178:Verify the min price displayed in Residential Rent Filter UI
     */
    @Test
    public void verifyMinPriceRangeInResidentialForRent()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("maxPrice"));
//        Assert.assertTrue(realEstateSnbPage.validateMinPriceForResidentialOrCommercialOrAgriculture(),"min price is not present as expected");
    }

    /**
     * Verify the min price displayed in Residential Buy Filter UI
     */
    @Test
    public void verifyMinPriceRangeInResidentialForBuy()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//       // homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectBuyButton();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("maxPrice"));
//        Assert.assertTrue(realEstateSnbPage.validateMinPriceForResidentialOrCommercialOrAgriculture(),"min price is not present as expected for buy in residential category");
    }

    /**
     * Verify the min price displayed in Residential pg/flat Filter UI
     */
    @Test
    public void verifyMinPriceRangeInResidentialForPgOrFlat()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectPgOrFlat();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("maxPrice"));
//        Assert.assertTrue(realEstateSnbPage.validateMinPriceForResidentialOrCommercialOrAgriculture(),"min price is not present as expected for pg or flat in residential category");

    }

    /**
     *  iOS-5179:Verify the No of Rooms displayed in Filter UI
     */
    @Test
    public void verifyNoOfRoomDisplayInFilterUi()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//     //   homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("NoOfBedRooms"));
//        Assert.assertTrue(realEstateSnbPage.validateNoOfBedRooms(),"No of bed-room aren't present as expected");
    }

    /**
     * iOS-5180:Tap on the rooms and verify
     */
    @Test
    public void verifyTapOnRoomIsHighLightedOrNot()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//     //   homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("NoOfBedRooms"));
//        realEstateSnbPage.selectRoomInResidentialFilterOption();
//        Assert.assertTrue(realEstateSnbPage.validateTapOnRoomIsHighLightedOrNot(2),"selected room is not high lighted");
    }

    /**
     * Verify the min Area displayed in Filter UI for all the residential subcategories
     */
    @Test
    public void verifyMinAreaInResidentialAllSubcategory()
    {
//        HomePage homePage=new HomePage(driver);
//        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
//        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
//        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//
//        // homePage.selectDontAllowOption();
//       // homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("area"));
//        Assert.assertTrue(realEstateSnbPage.validateMinAreaForResidentialCategory(),"min area not meet with the expectation");
//        realEstateSnbPage.selectCrossButtonOnFilterPage();
//        realEstateSnbPage.clickOnBackButtonOnRealEstateSnbPage();
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectBuyButton();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("area"));
//        Assert.assertTrue(realEstateSnbPage.validateMinAreaForResidentialCategory(),"min area not meet with the expectation");
//        realEstateSnbPage.selectCrossButtonOnFilterPage();
//        realEstateSnbPage.clickOnBackButtonOnRealEstateSnbPage();
//        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
//        realEstateHomePage.selectPgOrFlat();
//        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
//        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
//        realEstateHeaderPage.selectSearchButtonOfKeyboard();
//        realEstateSnbPage.selectFilterButtonOnRealSnbPage();
//        realEstateSnbPage.scroll(testData.get("area"));
//        Assert.assertTrue(realEstateSnbPage.validateMinAreaForResidentialCategory(),"min area not meet with the expectation");

    }
}
