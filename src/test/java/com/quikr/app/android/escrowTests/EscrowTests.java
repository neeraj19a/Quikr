package com.quikr.app.android.escrowTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.chat.ChatPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

public class EscrowTests extends AppAndroidTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_ESCROW_TESTDATA_FILE"));
    String AdId=null;

    @Title("PostAd : List Price = Min Price : Only buy now button")
    @Description("To check presence of only BuyNow button when an Ad is posted with same min and max price")
    @Test(groups = {"Stage"},dataProvider = "EscrowFlowPrerequisite",dataProviderClass = Data.class)
    public void buyNowOnSameListingAndMinimumPrice(String FlowPrerequisite) {
        VapPage vapPage = new VapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        EscrowApiBase escrowApiBase = new EscrowApiBase();
        switch (FlowPrerequisite) {
            case "Precondition":
                JsonNode response = escrowApiBase.postAdWithMinPrice(testData.get("sellerEmail"),testData.get("sellerMobile"),"23",testData.get("mobilesPrice"),testData.get("mobilesPrice"),"0");
                AdId = response.get("Ad").get("id").asText();
                escrowApiBase.publishAd(AdId);
                System.out.println(AdId);
                break;

            case "ActualTest":
                headerPage.selectHeaderSearchIcon();
                headerPage.searchTextFromHomePage(AdId);
                snbPage.hideOverlayLayout();
                Assert.assertTrue(snbPage.ifBuyNowPresent(),"BuyNow Not present on SNB");
                snbPage.openAdFromSnb(0);
                vapPage.hideOverlayLayout();
                Assert.assertTrue(vapPage.ifBuyNowPresent(),"BuyNow Not Present on VAP");
                Assert.assertTrue(vapPage.ifChatPresent(),"Chat Not Present on VAP");
                Assert.assertTrue(vapPage.ifCallPresent(),"Call Not Present on VAP");
                break;
        }
    }

    @Title("MAO : Offer between Listing Price &  Reserved Price")
    @Description("To check auto acceptance of offer if made between LP and RP limit.")
    @Test(groups = {"Stage"},dataProvider = "EscrowFlowPrerequisite",dataProviderClass = Data.class)
    public void maoBetweenListPriceAndMinPrice(String FlowPrerequisite) {

        VapPage vapPage = new VapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        EscrowApiBase escrowApiBase = new EscrowApiBase();
        switch (FlowPrerequisite) {
            case "Precondition":
                JsonNode response = escrowApiBase.postAdWithMinPrice(testData.get("sellerEmail"),testData.get("sellerMobile"),"23",testData.get("mobilesPrice"),testData.get("mobilesMinPrice"),"0");
                AdId = response.get("Ad").get("id").asText();
                escrowApiBase.publishAd(AdId);
                System.out.println(AdId);
                break;

            case "ActualTest":
                headerPage.selectHeaderSearchIcon();
                headerPage.searchTextFromHomePage(AdId);
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(0);
                vapPage.hideOverlayLayout();
                vapPage.clickMakeAnOffer();
                vapPage.enterOfferPrice(testData.get("offerPrice"));
                vapPage.enterOfferEmail(testData.get("buyerEmail"));
                vapPage.enterOfferMobile(testData.get("buyerMobile"));
                vapPage.hideKeyboardIfMAOnotVisibile();
                Assert.assertTrue(vapPage.ifOfferAutoAcceptance(),"Offer was not auto accepted");
                break;
        }
    }

    @Title("PostAd : MAO on SNB page")
    @Description("To check if MAO button is displayed on SNB if LP>MP or no MP opted cases for all escrow cities.")
    @Test(groups = {"Stage"},dataProvider = "EscrowFlowPrerequisite",dataProviderClass = Data.class)
    public void maoButtonOnSnB(String FlowPrerequisite) {
        VapPage vapPage = new VapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        EscrowApiBase escrowApiBase = new EscrowApiBase();
        switch (FlowPrerequisite) {
            case "Precondition":
                JsonNode response = escrowApiBase.postAdWithMinPrice(testData.get("sellerEmail"),testData.get("sellerMobile"),"23",testData.get("mobilesPrice"),testData.get("mobilesMinPrice"),"0");
                AdId = response.get("Ad").get("id").asText();
                escrowApiBase.publishAd(AdId);
                System.out.println(AdId);
                break;

            case "ActualTest":
                headerPage.selectHeaderSearchIcon();
                headerPage.searchTextFromHomePage(AdId);
                snbPage.hideOverlayLayout();
                Assert.assertTrue(snbPage.ifMAOPresent(),"MAO Not present on SNB");
                Assert.assertFalse(snbPage.ifBuyNowPresent(),"BuyNow present on SNB");
                break;
        }
    }

    @Title("Sync & Scan : Verify VAP screen for true Value score")
    @Description("Verify the Ad's VAP screen for true Value score which comes below Ad images.")
    @Test(groups = {"Stage","Prod"})
    public void checkQuikrScannerScoreDisplayed()
    {
        Hompage hompage = new Hompage(driver);
        VapPage vapPage = new VapPage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);

        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        escrowPage.selectQuikrScannerTestedFilter();
        escrowPage.applyFilter();
        snbPage.openAdFromSnb(0);
        vapPage.hideOverlayLayout();
        Assert.assertTrue(vapPage.ifInspectionScorePresent(),"Inspection Score NOT present");
    }

    @Title("Sync & Scan : Tap on View Report on VAP screen. Score must be same.")
    @Description("Tap on View Report on VAP screen. Score must be same.")
    @Test(groups = {"Stage","Prod"})
    public void matchVapScannerScoreInReport()
    {
        Hompage hompage = new Hompage(driver);
        VapPage vapPage = new VapPage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);

        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        escrowPage.selectQuikrScannerTestedFilter();
        escrowPage.applyFilter();
        snbPage.openAdFromSnb(0);
        vapPage.hideOverlayLayout();
        Assert.assertTrue(vapPage.ifInspectionScorePresent(),"Inspection Score NOT present");
        String scoreVap = vapPage.getScannerScoreFromVap();
        vapPage.viewScannerReport();
        String scoreReport= vapPage.getScannerScoreFromReport();
        Assert.assertTrue(scoreReport.contentEquals(scoreVap),"Score on VAP doesnot match with Report score");
    }

    /*
    @Title("Sync & Scan : Tap on 'i' on VAP to get a pop up displaying how to check IMEI number.")
    @Description("Tap on 'i' on VAP to get a pop up displaying how to check IMEI number.")
    @Test(groups = {"Stage","Prod"})
    public void checkIMEIInfo()
    {
        Hompage hompage = new Hompage(driver);
        VapPage vapPage = new VapPage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);

        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        escrowPage.selectQuikrScannerTestedFilter();
        escrowPage.applyFilter();
        snbPage.openAdFromSnb(0);
        vapPage.hideOverlayLayout();
        Assert.assertTrue(vapPage.ifInspectionScorePresent(),"Inspection Score NOT present");
        vapPage.viewIMEIinfo();
        Assert.assertTrue(vapPage.ifImeiInfoDisplayed(),"IMEI check info NOT displayed");
    }
    */

    @Title("Verify Chat MAO Form in Chat Cities.")
    @Description("Verify tap on MAO on SNB will take land user on chat and open the chat MAO form.")
    @Test(groups = {"Stage","Prod"},dataProvider = "ChatCities",dataProviderClass = Data.class)
    public void verifySnbMAOChatFormInChatCities(String chatCity)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        ChatPage chatPage=new ChatPage(driver);

        hompage.selectCity();
        headerPage.searchCity(chatCity);
        hompage.selectelementWithoutScroll(chatCity, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectChatMaoButton();
        chatPage.enterSenderDetails(testData.get("buyerName"),testData.get("buyerEmail"),testData.get("buyerMobile"));
        Assert.assertTrue(chatPage.ifChatMAOFormOpen(),"MAO form on Chat is NOT opening");
    }

    @Title("Verify Chat MAO Form in Chat Cities.")
    @Description("Verify tap on MAO on VAP which will take user to MAO chat form.")
    @Test(groups = {"Stage","Prod"},dataProvider = "ChatCities",dataProviderClass = Data.class)
    public void verifyVapMAOChatFormInChatCities(String chatCity)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        ChatPage chatPage=new ChatPage(driver);

        hompage.selectCity();
        headerPage.searchCity(chatCity);
        hompage.selectelementWithoutScroll(chatCity, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectMAOAd();
        vapPage.hideOverlayLayout();
        vapPage.selectMaoAndChatButton();
        chatPage.enterSenderDetails(testData.get("buyerName"),testData.get("buyerEmail"),testData.get("buyerMobile"));
        Assert.assertTrue(chatPage.ifChatMAOFormOpen(),"MAO form on Chat is NOT opening");
    }

    @Title("Verify Offer details in Chat window")
    @Description("Verify confirming the Make an Offer Chat form will take user to Chat window with the details of the offer made and CTAs as to Edit Offer.")
    @Test(groups = {"Stage","Prod"},dataProvider = "ChatCities",dataProviderClass = Data.class)
    public void verifyMaoDetailsOnChatScreen(String chatCity)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        ChatPage chatPage=new ChatPage(driver);

        hompage.selectCity();
        headerPage.searchCity(chatCity);
        hompage.selectelementWithoutScroll(chatCity, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectChatMaoButton();
        chatPage.enterSenderDetails(testData.get("buyerName"),testData.get("buyerEmail"),testData.get("buyerMobile"));
        Assert.assertTrue(chatPage.ifChatMAOFormOpen(),"MAO form on Chat is NOT opening");
        chatPage.maoAndChat(testData.get("buyerOffer"),testData.get("buyerEmail"),testData.get("buyerMobile"));
//        Assert.assertTrue(chatPage.ifOfferAmountSent(testData.get("buyerOffer")),"Offer amount sent in chat is Wrong");
        Assert.assertTrue(chatPage.ifEditOfferPresent(),"Edit offer not present");
    }

    @Title("Verify tap on Chat on VAP for escrow cities and categories will take user to Chat.")
    @Description("Verify tap on Chat on VAP for escrow cities and categories will take user to Chat.")
    @Test(groups = {"Stage","Prod"},dataProvider = "ChatCities",dataProviderClass = Data.class)
    public void verifyVapChat(String chatCity)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ChatPage chatPage = new ChatPage(driver);

        hompage.selectCity();
        headerPage.searchCity(chatCity);
        hompage.selectelementWithoutScroll(chatCity, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectMAOAd();
        vapPage.hideOverlayLayout();
        vapPage.selectMaoAndChatButton();
        chatPage.enterSenderDetails(testData.get("buyerName"),testData.get("buyerEmail"),testData.get("buyerMobile"));
        Assert.assertTrue(chatPage.ifChatMAOFormOpen(),"MAO form on Chat is NOT opening");
    }

    @Title("Verify Login is mandatory for My Offers.")
    @Description("My offers > Verify Login is mandatory for My Offers.")
    @Test(groups = {"Stage","Prod"})
    public void loginRequiredForMyOffers()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);

        headerPage.tapQuikrNext();
        headerPage.tapMyOffers();
        Assert.assertTrue(authPageMobileLogin.verifyOnLoginPage(),"Login not required for viewing MyOffers");
    }


    @Title("MOffers : Verify Buyer and Seller details under My Offers.")
    @Description("Verify Buyer and Seller details under My Offers.")
    @Test(groups = {"Stage"},dataProvider = "EscrowFlowPrerequisite",dataProviderClass = Data.class)
    public void myOffersUserDetailsCheck(String FlowPrerequisite) {

        VapPage vapPage = new VapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        EscrowApiBase escrowApiBase = new EscrowApiBase();
        switch (FlowPrerequisite) {
            case "Precondition":
                JsonNode response = escrowApiBase.postAdWithMinPrice(testData.get("sellerEmail"),testData.get("sellerMobile"),"23",testData.get("mobilesPrice"),testData.get("mobilesMinPrice"),"0");
                AdId = response.get("Ad").get("id").asText();
                escrowApiBase.publishAd(AdId);
                System.out.println(AdId);
                headerPage.selectHeaderSearchIcon();
                headerPage.searchTextFromHomePage(AdId);
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(0);
                vapPage.clickMakeAnOffer();
                vapPage.enterOfferPrice(testData.get("offerPrice"));
                vapPage.enterOfferEmail(testData.get("buyerEmail"));
                vapPage.enterOfferMobile(testData.get("buyerMobile"));
                vapPage.hideKeyboardIfMAOnotVisibile();
                break;
//
//            case "ActualTest":
//                headerPage.selectHeaderSearchIcon();
//                headerPage.searchTextFromHomePage(AdId);
//                snbPage.hideOverlayLayout();
//                snbPage.openAdFromSnb(0);
//                vapPage.clickMakeAnOffer();
//                vapPage.enterOfferPrice(testData.get("offerPrice"));
//                vapPage.enterOfferEmail(testData.get("buyerEmail"));
//                vapPage.enterOfferMobile(testData.get("buyerMobile"));
//                vapPage.hideKeyboardIfMAOnotVisibile();
//                Assert.assertTrue(vapPage.ifOfferAutoAcceptance(),"Offer was not auto accepted");
//                break;
        }
    }
/*
/**
     * Android Sanity:3981
     * Tap on Make an Offer, Buy in SNB and verify Make an Offer form loads in Escrow Cities (except Delhi, Lucknow)
     *//*

    @Test
    public void onClickingMakeAnOfferUserIsRedirectedTOMAkeAnOffer()
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        escrowPage.clickOnMakeANOffer();
        Assert.assertTrue(escrowPage.validateRdirectionToMakeAnOffer(),"user is not redirected to make an Offer page on clciking make an offer from SNB");

    }
    */
/**
     * Android Sanity 3977
     * Verify Listing Price is displayed in Make an Offer form after Ad title
     *//*

    @Test
    public void verifyListingPriceIsDisplayedInMAkeANOfferForm()
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        escrowPage.clickOnMakeANOffer();
        Assert.assertTrue(escrowPage.validateListedPriceIsPresent(),"listed price is not present");
    }
    */
/**
     * Make An Offer button should be displayed for escrow category Ads in High Touch cities (Mumbai, Bangalore)
     *//*

    @Test(dataProvider = "EscrowHighTouchcities",dataProviderClass = Data.class)
    public void MakeAnOfferIsPresentForHighTouchCity(String HIghTouchEscrowCities)
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.selectCity();
        headerPage.searchCity(HIghTouchEscrowCities);
        hompage.selectelementWithoutScroll(HIghTouchEscrowCities, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(escrowPage.MakeAnOfferButtonIsPresentOnVAp(),"Make an offer button is not present");
    }
    */
/**
     * AndroidSanity:2646
     * Verify Terms of Use and Privacy Policy links are not tappable on Make An Offer Pop Up
     *//*

    @Test
    public void termsContioionAndPrivacyPolicyIsNotTapable()
    {

        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        escrowPage.clickOnMakeANOffer();
        escrowPage.clickOnTermsAndConditions();
        Assert.assertTrue(escrowPage.validateTermsAndConditionButtonIsNotClickable(),"terms and Condition Buttons are not clickAble");
    }
    */
/**
     * Android Sanity2653
     * Verify user is able to Make an offer from VAP screen
     *//*

    @Test
    public void userIsAbleToMAkeOfferFromVAp()
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        escrowPage.clickOnMakeAnOfferFromVap();
        Assert.assertTrue(escrowPage.validateRdirectionToMakeAnOffer(), "user is not redirected to make an Offer page on clciking make an offer from VAP");


    }
    */
/**
     * Android SAnity
     * Verify City selection field is populated with NxN cities in Make an Offer Page
     *//*

    @Test
    public void verifyCitySelectionFieldIsPopulated()
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        escrowPage.clickOnMakeAnOfferFromVap();
        escrowPage.clickOnCityOnMAkeOfferPage();
        List<String>cities=escrowPage.CityFieldPopulated();
        Assert.assertTrue(cities.size()>2,"number of cities populated" +cities.size());
    }
    */
/**
     * Android Sanity:3084
     * Verify Buyer should be able to Make an offer from chat screen
     *//*

    // @Test
    public void userIsAbleToMakeOfferFromChat()throws Exception {
        EscrowPage escrowPage = new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        RealEstateVapPage realEstateVapPage=new RealEstateVapPage(driver);
        //escrowPage.login(testData.get("password"), testData.get("loginEmail"));
        //escrowPage.navigateBackToHOme();
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        escrowPage.clickOnChatFronVAp();
        realEstateVapPage.chatNumber("9955199163");
        Assert.assertTrue(escrowPage.validateMakeAnofferButtonIsPresentOnChat());
        escrowPage.clickOnMAkeAnOfferFromChat();
        escrowPage.sendOfferPriceFromChat(testData.get("price"));
        escrowPage.makeOfferFromChat();
        escrowPage.provideEmail("inde@gmail.com");
        escrowPage.submitPersonalInfoAfterMAkeAnOffer();
        Assert.assertTrue(escrowPage.validateHintMessageAfterMAkeAnOffer(), "user unable to make offer");
    }
    */
/**
     * AndroidSanity:3128
     * Verify Low Touch text appears in Post Ad screen for escrow categories and c2c escrow cities
     *//*

    @Test(dataProvider = "EscrowC2CCities",dataProviderClass = Data.class)
    public void verifyLowTouchTextForC2cEscrowCitites(String city, String locality)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.selectCity();
        headerPage.searchCity(city);
        hompage.selectelementWithoutScroll(city, QuikrAppEnums.Hompage_SelectCity);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        System.out.println(city + "&" + locality);
        String LowTouchText=escrowPage.getLowTouchText();
        Assert.assertTrue(testData.get("LowTouchText").equalsIgnoreCase(LowTouchText), "actual text ="+LowTouchText+  "  expectedText="+testData.get("LowTouchText"));

    }
    */
/**AndroidSanity 3132
     * Verify Escrow Benefits are displayed for escrow Ads in Low/High touch cities on SNB and other pages
     *//*

   @Test(dataProvider = "escrowTest3132",dataProviderClass = Data.class)
    public void validateEscrowFreeDeliveryAndCashBackMsgOnSnbVApAndPostAd(String test3132)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        VapPage vapPage=new VapPage(driver);
        switch (test3132)
        {
            case "postAd":

                hompage.clickonPostAd();
                papPage.selectelementWithoutScroll(testData.get("escrowPoastAdCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
                papPage.selectelementWithoutScroll(testData.get("laptops"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
                String category=papPage.getPapCategory();
                System.out.println(papPage.getPapCategory());
                papPage.PostAdAsIndividual();
                papPage.selectConditionAsUsed();
                papPage.scroll("Enter Price");
                papPage.setPrice(testData.get("price"));
                papPage.papEscrowSelectDesktop();
                papPage.selectBrand();
                papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.clickOnEscrowEscreenSize();
                papPage.selectelementWithoutScroll(testData.get("screenSize"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.setAdTitle(testData.get("AdTitle"), category);
                papPage.navigateBack();
                papPage.setAdDescription(testData.get("Desc"), category);
                papPage.navigateBack();
                papPage.papEscrowCategoryAddress(testData.get("address"));
                papPage.clickonAdlocation();
                papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
                papPage.papEscrowCategorypin(testData.get("pin"));
                papPage.provideName(testData.get("name"));
                papPage.sendNumber("9955799169");
                papPage.submitAd();
                Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
                Assert.assertTrue(papPage.escrowBenefitLinkIsPresent(),"escrow benefits msg not visible on post ad");
                break;
            case "SNB":
                hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.HOMEPAGE_CATEGORY);
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                escrowPage.selectCityAsCurrentCityOnVapEscrow();
                Assert.assertTrue(snbPage.escrowCashBackAndFreeDeliveryMsgIsPresentOnSnb(),"escrow cashBack and free delivery msg not displayed On Snb for escrow categories");
                break;
            case "VAP":
                hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.HOMEPAGE_CATEGORY);
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                escrowPage.selectCityAsCurrentCityOnVapEscrow();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                Assert.assertTrue(vapPage.EscrowCashBackAndFreeDeliveryMsgIsPresentOnVap(), "escrow cashBack and free delivery msg not displayed On VAP for escrow categories");
        }

    }
    */
/**
     * AndroidSanity3138
     * Verify Make an Offer button is available in VAP for Low Touch enabled Ads
     *//*

    @Test(dataProvider = "lowTouchCity",dataProviderClass = Data.class)
    public void verifyMakeAnOfferIsPresentOnVApforLowTouchCity(String lowTouchCity)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.selectCity();
        headerPage.searchCity(lowTouchCity);
        hompage.selectelementWithoutScroll(lowTouchCity, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(escrowPage.MakeAnOfferButtonIsPresentOnVAp(),"make an offer is not present on Vap");

    }
    */
/**
     * Android Sanity3984
     * Verify Warehouse strip is displayed in Post Ad for High Touch cities, Escrow categories
     *//*

    @Test
    public void wareHouseStripIsPresentOnPostAdPage()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("escrowPoastAdCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("laptops"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.scroll("check benefits");
        Assert.assertTrue(papPage.wareHouseStorageIsPresent(),"quikr ware house storage option is not present on Post Ad 2nd page");

    }
    */
/**
     * AndroidSanity-
     * Verify user is able to post Ad in High Touch cities without opting for Warehouse3987
     *//*

    @Test(dataProvider = "EscrowHighTouchcitiesWithLocality",dataProviderClass = Data.class ,groups = "horizontal")
    public void userIsAbleToPostAdWithoutOptingWareHOuseforHighTouchCity(String city,String locality)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.selectCity();
        headerPage.searchCity(city);
        hompage.selectelementWithoutScroll(city, QuikrAppEnums.Hompage_SelectCity);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("escrowPoastAdCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("laptops"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category=papPage.getPapCategory();
        System.out.println(papPage.getPapCategory());
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.papEscrowSelectDesktop();
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.clickOnEscrowEscreenSize();
        papPage.selectelementWithoutScroll(testData.get("screenSize"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("Desc"), category);
        papPage.navigateBack();
        papPage.papEscrowCategoryAddress(testData.get("address"));
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(locality, QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.papEscrowCategorypin(testData.get("pin"));
        papPage.provideName(testData.get("name"));
        papPage.sendNumber("9955799169");
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
    }
    */
/**
     * AndroidSanity3988
     * Verify user is able to post Ad in High Touch cities after opting for Warehouse
     *//*

    @Test(dataProvider = "EscrowHighTouchcitiesWithLocality",dataProviderClass = Data.class ,groups = "horizontal")
    public void userIsAbleToPostAdWithOptingWareHOuseforHighTouchCity(String city,String locality)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.selectCity();
        headerPage.searchCity(city);
        hompage.selectelementWithoutScroll(city, QuikrAppEnums.Hompage_SelectCity);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("escrowPoastAdCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("laptops"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category=papPage.getPapCategory();
        System.out.println(papPage.getPapCategory());
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        //selecting quikr storage and providing personal info
        papPage.selectQuikrWareHouse();
        papPage.selectMinimumPriceSameAsProductPrice();
//        papPage.selectQuikrStorage();
        papPage.papEscrowSelectDesktop();
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.clickOnEscrowEscreenSize();
        papPage.selectelementWithoutScroll(testData.get("screenSize"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("Desc"), category);
        papPage.navigateBack();
        papPage.papEscrowCategoryAddress(testData.get("address"));
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(locality, QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.papEscrowCategorypin(testData.get("pin"));
        papPage.provideName(testData.get("name"));
        papPage.sendNumber("9955799169");
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
    }
    */
/**AndroidSanity3994
     *
     Verify Minimum Price strip and input field is displayed in Low Touch cities for Escrow categories
     *//*

    //  @Test(dataProvider = "EscrowC2CCities",dataProviderClass = Data.class)
    public void verifyMinumumPriceStripIsNotPresentForNonEscrowCategories(String city,String locality)
    {

        Hompage hompage = new Hompage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.selectCity();
        headerPage.searchCity(city);
        PapPage papPage = new PapPage(driver);
        hompage.selectelementWithoutScroll(locality, QuikrAppEnums.Hompage_SelectCity);
        hompage.clickonPostAd();
        //papPage.setAdTitle(testData.get("AdTitle"));
        // papPage.setAdDescription(testData.get("Desc"));
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.navigateToNextPage();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
    }
*/

}
