package com.quikr.website.horizontal.home;

import com.quikr.website.TestBase;
import com.quikr.website.electronics.electronicsPap.ElectronicsPapPage;
import com.quikr.website.electronics.electronicsSnb.ElectronicsSnbPage;
import com.quikr.website.electronics.electronicsVap.ElectronicsVapPage;
import com.quikr.website.horizontal.alert.AlertPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.recommendsection.RecommendSectionPage;
import com.quikr.website.realEstate.realEstateHome.RealEstateHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;



/**
 * Created by akhil.singla
 */
public class HomeTests extends TestBase {

    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("HOME_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /**
     * validate after selecting city, navigated to city page
     */
    @Test
    public void validateCity() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectACity(testData.get("CitySelect"));
        Assert.assertTrue(waitForPageToLoad(testData.get("expectedURL")), "Fail to load URL : " + testData.get("expectedURL") + " expected : " + homePage.getCurrentLocation());
    }

    //@Test(groups = "horizontal")
    public void validatePopularCategoriesOnQuikr() {

        HomePage homePage = new HomePage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectACity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("expectedURL"));
        String title = homePage.selectPopularCategory();
        waitForPageToLoad(title.split(" ")[0]);
        Assert.assertTrue(horizontalSnbPage.isAdsOnSnbPageVisible());
    }

    /*
    WEB-6:Post free ad - without login
     */
    @Test(groups = {"horizontal" ,"horizontalPRI0","dev"})
    public void validatePostAdButton()
    {
        ElectronicsPapPage pap = new ElectronicsPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage=new HomePage(driver);

        //homePage.clickonCityCloseButton();
        headerPage.clickPostFreeAdButton();
        Assert.assertTrue((pap.isCategoryListVisible()),"Category list was not visible.");
    }

    @Test(groups = "horizontal")
    public void validateNewTileLayout()
    {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue((homePage.isPopularCategoriesVisible()));
        Assert.assertTrue(homePage.isCreateAlertWindowVisible());
    }

    /*
    WEB-21:Alert Creation (Intent creation)
    Update : Seperate Tests for all the alerts has been made.
     */
    //@Test(groups = {"horizontal","dev"})
    public void createAlertFromHomePage() {
        AlertPage alertPage = new AlertPage(driver);
        int brandOption=Integer.parseInt(getRandomInteger(1));

        alertPage.selectCategory("");
        alertPage.selectSubCategory("");
        alertPage.selectadType(1);
        alertPage.selectBrand();
        alertPage.selectYear();
        alertPage.clickOwner();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(8000, email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "horizontal")
    public void validateFooter()  {
        HomePage homePage = new HomePage(driver);

        scrollVerticallWithCords(300,1200);
        Assert.assertTrue(homePage.validateFooter(), "Footer not found....");
    }

    /* WEB-450:To Test Social Media Icons on Home Page
    */
    @Test(groups = "horizontal")
    public void validateSocialMediaIcons()
    {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.checkicons(),"One or more icons are not being displayed properly. Please check!");
    }

    /**
     * WEB-448:To Test Recommended for You Section, On Home Page.
     */

    @Test(groups = "dev")
    public void validateRecommendedSection() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        RecommendSectionPage recommendSectionPage = new RecommendSectionPage(driver);

        headerPage.letsLogin("", testData.get("city"), username, password);
        headerPage.clickOnQuikrLogo();
        homePage.selectnotificationbell();
        Assert.assertEquals(recommendSectionPage.getRecommendedForYouText(), testData.get("recommendedforyoutext"), "Failed to Load Recommend Section");

    }


    /**
     * WEB-715:Ads should be translated to requested languages with templatization in Recommended-section page
     */

    @Test(groups = "dev")
    public void validateRecommendedSectionVernacSNB() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);

        headerPage.letsLogin("", testData.get("city"),username, password);
        headerPage.clickOnQuikrLogo();
        Assert.assertTrue(homePage.gettextRecommenededAdsHomePage().contains(testData.get("recommendedforyoutext").substring(0, 12)), "Failed to Load Recommend Section");
        homePage.clickLanguageLink();
        homePage.selectHindiLanguage();
        homePage.selectnotificationbell();
        Assert.assertTrue(horizontalSnbPage.isRecommendedForYouSectionavailable());
        Assert.assertEquals(horizontalSnbPage.gettextGoQuikrHomeLinkRecommendedAdsSNB(),testData.get("RecommendedAdsGoToQuikrHomeLinkSNB"),"Failed to Load RecommendedAds in Hindi Language");
    }

    /*
    * WEB-448:To Test user is taken to VAP Page on clicking View details from Home Page
    */
    //@Test(groups = "horizontal")
    public void validateVapOpen()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);

        headerPage.selectACity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickOnQuikrLogo();
        homePage.clickviewdetails();
        Assert.assertEquals(electronicsVapPage.viewdetailstext(), testData.get("viewadd_text"), "Failed to load VAP Page");
    }

    /*WEB-449:Home Page and Real Estate City selection
    */
    //@Test(groups = {"ProdHorizontal"})
    public void validateCityFrommRealEstate() {
        HomePage homePage = new HomePage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);

        //homePage.clickonCityCloseButton();
        homePage.selectRealEstateOptionFromHome();
        realEstateHomePage.selectCity(testData.get("city"));
        Assert.assertTrue(waitForPageToLoad(testData.get("quikrHomesURL")), "Fail to load URL : " + testData.get("quikrHomesURL") + " expected : " + realEstateHomePage.getCurrentLocation());
    }

    /*WEB-1:To test Popular Categories on Quikr Home Page
    */
    //@Test(groups = "horizontal")
    public void validatePopularCategories() {
        HomePage homePage = new HomePage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        homePage.selectCarsandBikes();
        //horizontalSnbPage.cityPopupClose();
        Assert.assertEquals(horizontalSnbPage.gettextBikesandScooters(), testData.get("bikesandscooters_text"), "Failed to load page Bikes & Scooters");
        homePage.clickHamburgerMenu();
        homePage.clickQuikrHomeHamburgerMenu();
        homePage.selectHousesApartmentsRent();
        Assert.assertEquals(horizontalSnbPage.gettextHousesApartmentsforRent(), testData.get("house_apartment_rent_pagetext"), "Failed to load Houses-Apartments-for-Rent page");
        headerPage.clickOnQuikrLogo();
        homePage.selectHousesApartmentsSale();
        Assert.assertEquals(horizontalSnbPage.gettextHousesApartmentsforSale(), testData.get("house_apartment_sale_pagetext"), "Failed to load page Houses - Apartments for Sale");
        headerPage.clickOnQuikrLogo();
        homePage.selectLaptopsComputers();
        Assert.assertEquals(horizontalSnbPage.gettextLaptopsandComputers(), testData.get("laptopsandcomputers_pagetext"), "Failed to load page Laptops - Computers");
        headerPage.clickOnQuikrLogo();
        homePage.selectHomeFurniture();
        Assert.assertEquals(horizontalSnbPage.gettextHomeOfficeFurniture(), testData.get("homeofficefurniture_pagetext"), "Failed to load page Home - Office Furniture");
        headerPage.clickOnQuikrLogo();
        homePage.selectKitchenAppliances();
        Assert.assertEquals(horizontalSnbPage.gettextHomeKitchenAppliances(), testData.get("homeokitchenappliances_pagetext"), "Failed to load page Home - Kitchen Appliances");
    }

    /*WEB-1:To test Popular Categories on Quikr Home Page
   */
    /*@Test(groups = "horizontal")
    public void validatePopularCategoriesNewUI() {
        homePage.selectCarsandBikes();
        snbPage.cityPopupClose();
        Assert.assertEquals(snbPage.gettextBikesandScooters(), testData.get("bikesandscooters_text"), "Failed to load page Bikes & Scooters");
        homePage.clickHamburgerMenu();
        homePage.clickQuikrHomeHamburgerMenu();
        homePage.selectHousesApartmentsRent();
        RealEstatePAPPage.selectCityQuikrHomes(testData.get("quikrhomescity"));
        Assert.assertTrue(snbPage.verifyHousesApartments(), "Failed to load Quikr Homes page");
        homePage.navigateback();
        homePage.selectHousesApartmentsSale();
        Assert.assertTrue(snbPage.verifyHousesApartments(), "Failed to load Quikr Homes page");
        homePage.navigateback();
        homePage.selectLaptopsComputers();
        Assert.assertEquals(snbPage.gettextLaptopsandComputers(), testData.get("laptopsandcomputers_pagetext"), "Failed to load page Laptops - Computers");
        headerPage.clickOnQuikrLogo();
        homePage.selectHomeFurniture();
        Assert.assertEquals(snbPage.gettextHomeOfficeFurniture(), testData.get("homeofficefurniture_pagetext"), "Failed to load page Home - Office Furniture");
        headerPage.clickOnQuikrLogo();
        homePage.selectKitchenAppliances();
        Assert.assertEquals(snbPage.gettextHomeKitchenAppliances(), testData.get("homeokitchenappliances_pagetext"), "Failed to load page Home - Kitchen Appliances");
    }
*/

    /*WEB-463:View Ad from For , Replies to my Ads section.
   * */

    @Test(groups = "dev")
    public void validateViewAdRepliesToMyAds_Recommendsection()
    {
        RecommendSectionPage recommendSectionPage = new RecommendSectionPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //validateRecommendedSection();
        headerPage.selectACity(testData.get("city"));
        headerPage.letsLogin("randomPageLoginNonResponsive","",username, password);
        headerPage.clickOnQuikrLogo();
        homePage.selectnotificationbell();
        Assert.assertEquals(recommendSectionPage.getRecommendedForYouText(), testData.get("recommendedforyoutext"), "Failed to Load Recommend Section");

        recommendSectionPage.clickRepliesToMyAds();
        recommendSectionPage.clickViewAd();
        Assert.assertEquals(electronicsVapPage.viewdetailstext(), testData.get("viewadd_text"), "Failed to load VAP Page");
    }

    /*WEB-464: Convert to Premium, For , Replies to my Ads section.
    * */

    @Test(groups = "dev")
    public void validateConvertPremiumRepliesToMyAds_Recommendsection()
    {
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        RecommendSectionPage recommendSectionPage = new RecommendSectionPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //validateRecommendedSection();
        headerPage.selectCity(testData.get("city"));
        headerPage.letsLogin("randomPageLoginNonResponsive","",username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectnotificationbell();
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertEquals(recommendSectionPage.getRecommendedForYouText(), testData.get("recommendedforyoutext"), "Failed to Load Recommend Section");
        recommendSectionPage.clickRepliesToMyAds();
        recommendSectionPage.clickConvertToPremium();
        waitForPageToLoad("upgrade");
        Assert.assertEquals(electronicsPapPage.converttopremiumtext(), testData.get("convertpremiumad_text"), "Failed to load Convert Premium Ad Page");
    }

    /*WEB-465: Edit Ad For , Replies to my Ads section.
    * */
    @Test(groups = "dev")
    public void validateEditAdRepliesToMyAds_Recommendsection() {
        RecommendSectionPage recommendSectionPage = new RecommendSectionPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //validateRecommendedSection();
        headerPage.selectCity(testData.get("city"));
        headerPage.letsLogin("randomPageLoginNonResponsive", "", username, password);
        headerPage.clickOnQuikrLogo();
        homePage.selectnotificationbell();
        Assert.assertEquals(recommendSectionPage.getRecommendedForYouText(), testData.get("recommendedforyoutext"), "Failed to Load Recommend Section");
        recommendSectionPage.clickRepliesToMyAds();
        recommendSectionPage.clickEditAd();
        Assert.assertEquals(electronicsPapPage.editadtext(), testData.get("edityourad_text"), "Failed to load Edit Your Ad Page");
    }

    /*WEB-466: Delete Ad For , Replies to my Ads section.
    * */
    //Recommend Section No More
    @Test(groups = "dev")
    public void validateDeleteAdRepliesmToMyAds_Recommendsection() {
        RecommendSectionPage recommendSectionPage = new RecommendSectionPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //validateRecommendedSection();
        headerPage.selectCity(testData.get("city"));
        //headerPage.loginWithCity(username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectnotificationbell();
        Assert.assertEquals(recommendSectionPage.getRecommendedForYouText(), testData.get("recommendedforyoutext"), "Failed to Load Recommend Section");
        recommendSectionPage.clickRepliesToMyAds();
        recommendSectionPage.clickDeleteAd();
        Assert.assertEquals(electronicsPapPage.deleteadtext(), testData.get("deletead_text"), "Failed to load Delete Ad Text");
        waitForPageToLoad("recommended-section");
    }

    /*WEB-451: Change cities from Popular Cities.
    **/
    @Test(groups = "horizontal")
    public void validateChangePopularCities()
    {
        HeaderPage headerpage = new HeaderPage(driver);
        headerpage.selectCity(testData.get("CitySelect"));
        headerpage.selectCity(testData.get("changecity"));
        Assert.assertTrue(waitForPageToLoad(testData.get("changecity")), "Page with new city didn't load.");
    }


    /*
 WEB-504:Six Trending Ads should be displayed when Recommended Ads are not present
 */
    @Test(groups="horizontal")
    public void verfiyTrendingAdsNewUICities()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("responsiveHPCity"));
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.checkTrendingAdsNewUICities(),"Trending ads not visible. please check!");
    }

    @Test(groups = "horizontal")
    public void validateLogin()
    {
        HeaderPage headerPage = new HeaderPage(driver);

        //headerPage.selectCity(testData.get("city"));
        Assert.assertTrue(headerPage.letsLogin("", testData.get("city"), username, password));
    }

}