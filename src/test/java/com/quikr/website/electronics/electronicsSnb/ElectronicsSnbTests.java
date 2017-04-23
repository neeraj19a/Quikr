package com.quikr.website.electronics.electronicsSnb;

import com.quikr.website.TestBase;
import com.quikr.website.electronics.electronicsHome.ElectronicsHome;
import com.quikr.website.horizontal.alert.AlertPage;
import com.quikr.website.horizontal.chat.ChatPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.mobilesandtablets.Mobilespage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
import static org.testng.Assert.assertTrue;

/**
 * Created by rohanbajaj on 27/07/15.
 */

public class ElectronicsSnbTests extends TestBase
{
    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("ELECTRONICS_AND_APPLIANCES_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    @Test(groups = "horizontal")
    public void SnbSearch()
    {
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("query"));
        Assert.assertTrue(waitForPageToLoad(testData.get("query")));
    }

    @Title("WEB-53:Search ad - With login user")
    @Test(groups = {"horizontalPRI0","horizontal","dev"})
    public void SnbSearchLogin()
    {
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.letsLogin("randomPageLoginResponsive", "", username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad("quikr");
        headerPage.search(testData.get("query"));
        Assert.assertTrue(waitForPageToLoad(testData.get("query")),"Search didn't happen as expected. Please check!");
    }

    /*WEB-16:Inline reply from all the pages should be sent successfully
    */
    @Test(groups = {"ProdHorizontal","UATHorizontal"})
    public void SnbInlineReply()
    {
//        HeaderPage headerPage =new HeaderPage(driver);
//        ElectronicsSnbPage electronicsSnbPage=new ElectronicsSnbPage(driver);
//
//        headerPage.loginWithCity(username,password);
//        headerPage.clickOnQuikrLogo();
//        waitForPageToLoad("quikr");
//        headerPage.search(testData.get("query"));
//        Assert.assertTrue(waitForPageToLoad(testData.get("query")));
//        electronicsSnbPage.clickOnReplyButton();
//        Assert.assertTrue(electronicsSnbPage.validateInlineReply(),"Failed to make Inline Reply to ads on SNB Page");
    }

    @Title("WEB-17:Search a keyword on homepage, result page should show all metacats and subcats in result")

    @Test(groups = {"horizontalPRI0","horizontal","dev"})
    public void SnbSearchKeyword()
    {
        HeaderPage headerPage =new HeaderPage(driver);
        ElectronicsSnbPage electronicsSnbPage=new ElectronicsSnbPage(driver);

        headerPage.letsLogin("randomPageLoginResponsive", "", username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad("quikr");
        headerPage.search(testData.get("query"));
        Assert.assertTrue(waitForPageToLoad(testData.get("query")));
        Assert.assertTrue(electronicsSnbPage.validateCategory(testData.get("query")), "Failed to Load Category");
        Assert.assertTrue(electronicsSnbPage.getCategoriestext().toLowerCase().contains(testData.get("query")), "Failed to Load Category on LHS");
    }


    /*WEB-287:Tile SNB - Reply and Chat
    */
    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal"})
    public void SnbTileReplyandChat()
    {
//        HeaderPage headerPage =new HeaderPage(driver);
//        ElectronicsSnbPage electronicsSnbPage=new ElectronicsSnbPage(driver);
//        ChatPage chatPage=new ChatPage(driver);
//
//        headerPage.loginWithCity(username,password);
//        headerPage.clickOnQuikrLogo();
//        waitForPageToLoad("quikr");
//        headerPage.search(testData.get("query"));
//        waitForPageToLoad(testData.get("searchResultUrl"));
//        electronicsSnbPage.clickAdsWithPhotos();
//        waitForPageToLoad("quikr");
//        electronicsSnbPage.clickOnReplyButton();
//        Assert.assertTrue(electronicsSnbPage.validateInlineReply(), "Failed to make Inline Reply to ads on SNB Page");
//        refreshPage();
//        waitForPageToLoad("quikr");
        /*chatPage.validateChatOnSnb(testData.get("chatName"), testData.get("chatNumber"), testData.get("emailId"), testData.get("chatContent"));
        Assert.assertEquals(testData.get("chatContent"), chatPage.validatechat(), "Not able to chat");*/

    }



    @Test(groups = {"ProdHorizontal"})
    public void validateChat()
    {
        ChatPage chatPage=new ChatPage(driver);
        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("query"));
        waitForPageToLoad(testData.get("searchResultUrl"));
        /*chatPage.validateChatOnSnb(testData.get("chatName"), testData.get("chatNumber"), testData.get("emailId"), testData.get("chatContent"));
        //System.out.println(testData.get("chatContent") + "\n" + chatPage.validatechat());
        Assert.assertEquals(testData.get("chatContent"), chatPage.validatechat(), "Not able to chat");*/
    }

    @Test(groups = "horizontal")
    public void ValidateFiltersPaging()
    {
        HomePage homePage=new HomePage(driver);
        ElectronicsSnbPage electronicsSnbPage=new ElectronicsSnbPage(driver);
        Mobilespage mobilespage=new Mobilespage(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("bhopalCity"));
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        electronicsSnbPage.SelectMobileCategory();
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        assertTrue(mobilespage.isFiltersvisible());
    }

    /*WEB-51:Filter search results with Price ASC
    */
    @Test(groups = {"horizontal","horizontalPRI0","dev"})
    public void ValidateAsndSort()
    {
        /*HomePage homePage=new HomePage(driver);
        HeaderPage headerPage =new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("query"));
        waitForPageToLoad(testData.get("searchResultUrl"));
        assertTrue(horizontalSnbPage.SnbPageAsndSort(), "Element is not in ascending order");
 */   }

    /*WEB-52:Filter search results with Price DESC
    */
    @Test(groups = {"horizontal","horizontalPRI0","dev"})
    public void ValidateDsndSort()
    {
        /*HomePage homePage=new HomePage(driver);
        HeaderPage headerPage =new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("query"));
        waitForPageToLoad(testData.get("searchResultUrl"));
        assertTrue(horizontalSnbPage.SnbPageDsndSort(), "Element is not in descending order");
*/    }

    @Title("WEB-22:MSP Price Engine")

    @Test(groups = {"ProdHorizontal","horizontalPRI0"})
    public void testMsp()
    {
        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.setmspcategory(testData.get("categoryMSP"));
        headerPage.setmspSubCategory(testData.get("subcategorycar"));
        headerPage.setmspBrand(testData.get("brandname"));
        headerPage.setmspModel(testData.get("model"));
        headerPage.setmspYear(testData.get("year"));
        headerPage.clickmspVariant(testData.get("variant"));
        headerPage.setmspKms(testData.get("kms"));
        Assert.assertTrue(headerPage.validatemsp());
    }

    @Test(groups = "horizontal")
    public void ValidateBrandName()
    {
        HomePage homePage=new HomePage(driver);
        ElectronicsHome electronicsHome =new ElectronicsHome(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectElectronicsAndAppliances();
        electronicsHome.clickLaptopsComputeers();
        waitForPageToLoad(testData.get("cityUrl"));
        assertTrue(electronicsHome.FilterApplyByBrandName(), "Element is not filter according to brand name");
    }

    @Title("WEB-20:Suggestions should appear down in a list when user enters keywords in Searh box")
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void ValidateAutosuggestWords()
    {
        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        assertTrue(headerPage.Autosuggest(testData.get("query")), "In autosuggestion related word is not present");
    }

    @Test
    public void createAlertFromSnb()
    {
        HomePage homePage=new HomePage(driver);
        AlertPage alertPage =new AlertPage(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        String email = testData.get("emailId");
        String phoneNumber = testData.get("phoneNumber");
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.SelectsMobiles();
        assertTrue(alertPage.createAlertFromSnbPage(email, phoneNumber));
    }

    @Title("WEB-32:Chicklets on SNB")

    @Test(groups = {"horizontal","horizontalPRI0"})
    public void ValidateChickletsSnb()
    {
        HomePage homePage=new HomePage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("bhopalCity"));
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        homePage.SelectsMobiles();
        horizontalSnbPage.SelectMobileCategory();
        Assert.assertTrue(horizontalSnbPage.ValidateChickletsPresent(), "Chicklets are not present.");
    }

    @Test(groups = "horizontal")
    public void CheckDate()
    {
        HomePage homePage=new HomePage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        waitForPageToLoad(testData.get("homeUrl"));
        headerPage.selectCity(testData.get("bhopalCity"));
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        horizontalSnbPage.SelectMobileCategory();
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        horizontalSnbPage.SortDateAsc();
        Assert.assertTrue(horizontalSnbPage.CheckIfTimeSortedAsc());
    }

    /*To Test WEB-33:SNB L.H.S. Filters and pagination.
    */
    /*WEB-18:From homepage click on a metacat, subcat page will appear click on any subcat
    */
    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal"})
    public void validateSnbFilterAndPagination()
    {
        ElectronicsSnbPage electronicsSnbPage =new ElectronicsSnbPage(driver);

        validateFilterVisibleForMobileCategory();
        electronicsSnbPage.clickBrandFilter(testData.get("mobilebrand"));
        Assert.assertTrue(electronicsSnbPage.validateCategory(testData.get("query")), "Failed to Load Category");
        Assert.assertEquals(electronicsSnbPage.matchFilterandSearchResults().trim(), testData.get("mobilebrand") + " " + testData.get("top_of_pagead_text"), "Failed to match search results");
        scrollVerticallWithCords(0,18000);
        Assert.assertTrue(electronicsSnbPage.ispaginationavailable(), "Pagination is not Loading");
    }

    /*WEB-286:Tile SNB page*/
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void validatePhotosOnAdswithPhotos()
    {
        HomePage homePage=new HomePage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);
        ElectronicsHome electronicsHome =new ElectronicsHome(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectElectronicsAndAppliances();
        electronicsHome.clickLaptopsComputeers();
        horizontalSnbPage.clickAdsWithPhotos();
        Assert.assertTrue(horizontalSnbPage.isimagesDisplayedOnAds(),"Failed to Load Images");
    }

    @Title("To Test WEB-11:Ad sense: Matching Ads from google is shown here.")
    @Test(groups = {"horizontal","horizontalPRI0"})
    public  void validateGoogleAdsonSnbpage()
    {
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);

        validateFilterVisibleForMobileCategory();
        horizontalSnbPage.clickBrandFilter(testData.get("mobilebrand"));
        horizontalSnbPage.checkMatchingAdonsnb();
        Assert.assertTrue(horizontalSnbPage.checkMatchingAdonsnb(), "Google ads Not coming ");
    }

    /*Utility set of functions
    */

    /*function To Test the filter are visible for mobile category
    */
    public void validateFilterVisibleForMobileCategory()
    {
        HomePage homePage=new HomePage(driver);
        Mobilespage mobilespage=new Mobilespage(driver);
        ElectronicsSnbPage electronicsSnbPage=new ElectronicsSnbPage(driver);
        HeaderPage headerPage =new HeaderPage(driver);

        headerPage.selectCity(testData.get("bhopalCity"));
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        electronicsSnbPage.SelectMobileCategory();
        assertTrue(mobilespage.isFiltersvisible());
    }

    /**
     * WEB-11:Ad sense: Matching Ads from google is shown here
     */
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void verifyGoogleAd()
    {
        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage =new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);

        headerPage.letsLogin("",(testData.get("bhopalCity")), username, password);
        headerPage.clickOnQuikrLogo();
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        horizontalSnbPage.SelectMobileCategory();
        Assert.assertTrue(verifyGoogleAds(testData.get("snbGoogleAdIFrameId")), "Probably google ads are not being displayed. Please check!");

    }

    /**
     *WEB-40:Search & Browse : Grid view
     */
    @Test(groups = {"horizontal","horizontalPRI0","dev"})
    public void verifySNBGridviewKitchenAds()
    {
        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage =new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);
        ElectronicsHome electronicsHome =new ElectronicsHome(driver);

        //headerPage.letsLogin("randomPageLoginNonResponsive", (testData.get("city")), username, password);
        headerPage.letsLogin("",(testData.get("city")),username,password);
        headerPage.clickOnQuikrLogo();
        homePage.selectElectronicsAndAppliances();
        electronicsHome.clickHomeKitchenAppliances();
        horizontalSnbPage.clickAdsWithPhotos();
        Assert.assertTrue(horizontalSnbPage.checkMatchingAdonsnb(), "Google ads Not coming ");
    }

    /**
     *WEB-40:Search & Browse : Grid view    
     */
    @Test(groups = "horizontal")
    public void verifySNBGridviewMobileAds()
    {
        HomePage homePage=new HomePage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);

        waitForPageToLoad(testData.get("quikrURL"));
        homePage.clickonCityCloseButton();
        homePage.SelectsMobiles();
        horizontalSnbPage.SelectMobileCategory();
        Assert.assertTrue(horizontalSnbPage.checkMatchingAdonsnb(), "Google ads Not coming ");
    }


    @Title("WEB-495:SnB: Search")

    //@Test(groups = {"ProdHorizontal","UATHorizontal","dev"})
    public void validateSnbSearchVernac()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);

        waitForPageToLoad(testData.get("quikrURL"));
        headerPage.letsLogin("",(testData.get("city")),username,password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("quikrURL"));
        homePage.clickLanguageLink();
        homePage.selectHindiLanguage();
        waitForPageToLoad(testData.get("city").toLowerCase());
        Assert.assertTrue(homePage.checkKeyboardPresence(), "Virtual keyboard is not visible. Please check!");
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("quikrURL"));
        Assert.assertTrue(homePage.checkLanguageTranslationHomepage(testData.get("HeaderTextHindi"), testData.get("CarsinHindi"), testData.get("JobsTextHindi")), "The translation not happening as expected. Please check!");
        homePage.setMobileSearchKeywordVernacKeyboard();
        headerPage.clickSearchButton();
        waitForPageToLoad(testData.get("quikrURL"));
        Assert.assertEquals(horizontalSnbPage.isSNBPageloaded().trim(), testData.get("SNBPageCreateAlertInHindi").trim(),"Failed to Load SNB Page in Hindi Language");
        homePage.clickLanguageLink();
        homePage.selectTamilLanguage();
        waitForPageToLoad(testData.get("quikrURL"));
        Assert.assertEquals(horizontalSnbPage.isSNBPageloaded().trim(),testData.get("SNBPageCreateAlertInTamil").trim(), "Failed to Load SNB Page in Tamil Language");

    }
}
