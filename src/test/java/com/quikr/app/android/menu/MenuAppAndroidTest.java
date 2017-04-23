package com.quikr.app.android.menu;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.chat.ChatPage;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by quikr on 13/8/15.
 */
public class MenuAppAndroidTest extends AppAndroidTestBase
{


    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_MENU_TESTDATA_FILE"));

    @Stories("Homepage")
    @Title("Verify App version IS Displayed")
    @Test(groups  ="horizontal")
    public void VerifyAppVersion()
    {
        MenuPage menuePage=new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.scroll(testData.get("Appversion"));
        menuePage.clickOnAboutQuikr();
        Assert.assertTrue(menuePage.validateAppVersion(),"app version not reflected");
    }
    //old UI
//
//    @Test(groups = "horizontal")
//    public void CheckMsp()
//    {
//        // hompage.ApplaunchPopup();
//        menuePage.clickOnMenuIcon();
//        menuePage.selectelementWithoutScroll(testData.get("Msp"), QuikrAppEnums.CATEGORY_MENU);
//        Assert.assertTrue(menuePage.checkMSP());
//    }
    @Stories("Recently Viewed Ads")
    @Title("All viewed ads should be displayed in Recently Viewed ads")
    @Test(groups = "horizontal")
    public void verifyRecentlyViewedAds()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Mobiles"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        String AdTitle=escrowPage.escrowSnbTitle();
        System.out.println(AdTitle);
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        menuePage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        String recentlyViewedAdTitle=menuePage.recentlyViewedAdtitle();
        System.out.println(recentlyViewedAdTitle);
        Assert.assertTrue(recentlyViewedAdTitle.contains(AdTitle),"Expected ="+AdTitle+"\nActual="+recentlyViewedAdTitle);
    }
@Stories("Menu Drawer")
@Title("Post Ad should be displayed in Menu When opened from Snb")
    @Test(groups = "horizontal")
    public void validateMenuOnAdPage()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        snbPage.selectMenuButtonAtSnbPage();
        Assert.assertTrue(menuePage.validateMenuOnAd());

    }
    @Stories("Menu Drawer")
    @Title("USer should be able to Rate Quikr App")
    @Test(groups = "horizontal")
    public  void rateQuikrApp()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectElements(testData.get("rateQuikr"), QuikrAppEnums.CATEGORY_MENU);
        menuePage.clickOnYesiconOnRateus();
        String rateQuikr = menuePage.validateRateQuikr();
        Assert.assertTrue(rateQuikr.contains(testData.get("Quikr")));
    }

    // @Test(groups = "horizontal")
    public void chatWithoutLoginFromMenu()
    {
        MenuPage menuePage=new MenuPage(driver);
        ChatPage chatPage = new ChatPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("myChats"), QuikrAppEnums.CATEGORY_MENU);
        chatPage.clickOnChatConversation();
        chatPage.sendChat(testData.get("chatContent"));
        Assert.assertTrue(chatPage.validateChatSent());
    }

    //@Test(groups = "horizontal")
    public void
    chatWithLoginFromMenu()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        ChatPage chatPage = new ChatPage(driver);
        AuthPage authPage=new AuthPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
//        hompage.ApplaunchPopup();
        menuePage.clickOnMenuIcon();
        menuePage.selectElements(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(testData.get("email"));
//        menuePage.hideKeyboard();
        authPage.setSignInPassword(testData.get("password"));
        authPage.navigateBack();
        authPage.submitsignin();
        headerPage.navigateToBackPage();
        menuePage.clickOnMenuIcon();
        menuePage.selectElements(testData.get("myChats"), QuikrAppEnums.CATEGORY_MENU);
        chatPage.clickOnChatConversation();
        chatPage.sendChat(testData.get("chatContent"));
        Assert.assertTrue(chatPage.validateChatSent());
    }

    /**
     * Verify all shortlist ads across all categories are displayed
     */
    @Stories({"Shortlist","VAP"})
    @Title("User is able Shortlist Ad  and Remove Them ")
    @Test(groups = "horizontal")
    public void verifyStarAndUnStarAd()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage =new SnbPage(driver);
        CarsNewPage carsNewPage=new CarsNewPage(driver);
            hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectMyShortlist();
        snbPage.openAdFromSnb(0);
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        Assert.assertTrue(menuePage.validateMyShortList(),"can not perform star and unStar action");
    }

//    @Test(groups = "horizontal")
//    public void verifyFeedPageSort()
//    {
//        MenuPage menuePage=new MenuPage(driver);
//        Hompage hompage=new Hompage(driver);
//        SnbPage snbPage =new SnbPage(driver);
//        AuthPage authPage=new AuthPage(driver);
//        hompage.ApplaunchPopup();
//        menuePage.clickOnMenuIcon();
//        menuePage.selectElements(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
//        authPage.clickOnMyAlerts();
//        snbPage.selectSortButton();
//        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage(),"Can not sort as lowest price");
//    }

    @Test(groups = "horizontal")
    public void verifyDeletedAdViewedAndStatusReflected()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
//        hompage.ApplaunchPopup();
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        menuePage.selectInactiveButton();
        Assert.assertTrue(menuePage.validateDeletedAdViewedAndStatusReflected(), "deleted ad status is not correctly reflected");
    }



    /**
     * ANDROID-357:Verify recently viewed ads through “Alerts & Recommended” under “Recently Viewed Ads'' tab
     */
//    @Test
    public void verifyRecentlyViewedAdForAlertsAndRecommended()
    {
        MenuPage menuePage=new MenuPage(driver);
        VapPage vapPage=new VapPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AuthPage authPage=new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"),QuikrAppEnums.CATEGORY_MENU);
        authPage.clickOnMyAlerts();
        menuePage.openAdInRecommendedForYou();
        snbPage.hideOverlayLayout();
        String VapTitle=vapPage.AdTitle();
        menuePage.navigateBack();
        menuePage.navigateBack();
        menuePage.navigateBack();
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        String recentlyViewedAdTitle=menuePage.recentlyViewedAdtitle();
        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));
    }
    /**
     * Android Sanity
     * Should be able to tap and view "Terms & Conditions" link
     */
    @Stories("Menu Drawer")

    @Test(groups = "horizontal")
    public void validateTermAndConditionLinkIsPresent()
    {
        MenuPage menuePage=new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.scroll(testData.get("Appversion"));
        menuePage.clickOnAboutQuikr();
        Assert.assertTrue(menuePage.validateTermsAndConditionLink(),"term and condition link is not present");
    }
    /**
     * Android Sanity
     * Should be able to tap and view "Terms & Conditions" link
     */
    @Stories("Menu Drawer")

    @Title("Verify Privacy Policy Link Is Present ")
    @Test(groups = "horizontal")
    public void validatePrivacyPolicyLinkIsPresent()
    {
        MenuPage menuePage=new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.scroll(testData.get("Appversion"));
        menuePage.clickOnAboutQuikr();
        Assert.assertTrue(menuePage.validatePrivacyPolicyLink(),"privacy Policy link is not present");
    }
    /**
     * Android sanity:2552
     * Check product MSP in More screen
     */
   // @Stories("Menu Drawer")

    //@Test(groups = "horizontal")
    public void validetUserIsRedirectedToMspPage()
    {
        MenuPage menuePage=new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.clickOnMsp();
        Assert.assertTrue(menuePage.validateUserIsRedirectedToMspPage(),"user is not redirected to Msp Page");
    }
    /**
     * Android Sanity:2553
     * User should be navigated to the My Ads page
     */
    @Stories("My Ads")
    @Test(groups = "horizontal")
    public void verifyOnClickingMyAdsUserIsRedirectedToMyAdsPage()
    {
        MenuPage menuePage=new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        Assert.assertTrue(menuePage.validateUSerIsRedirectedToMyAdsPage(),"user is not redirected to my Ads Page");
    }
    /**
     * Android Sanity:2554
     * Verify "My Shortlist" in More screen
     */
    @Stories("Menu Drawer")
    @Title("On clicking Shortlisted ad button user is redirected to shortlistAd Page")
    @Test(groups = "horizontal")
    public void verifyUserIsRedirectedToShortlistAdsPage()
    {
        MenuPage menuePage=new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyShortlist();
        Assert.assertTrue(menuePage.validateUSerIsRedirectedToShortlistPage(),"on clicking Shortlist Ads User is not redirected to shortlist page");
    }


    // Tapping Matching Ads button from "Alerts" tab should display matching ads related to that alert
    @Stories("Alert")
    @Title("\"Alerts\" tab should display matching ads related to that alert")
    @Test(groups = "horizontal")
    public void validateMatchingAds()
    {
        MenuPage menuPage = new MenuPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        alertPage.clickOnMatchingAds();
        Assert.assertTrue(snbPage.validateNavigationToSnb(), "Matching ads not present");
    }

    // Should be able to Sort Matching Ads from Sort menu
    @Stories("Alert")
    @Title("Should be able to Sort Matching Ads from Sort menu")
    @Test(groups = "horizontal")
    public void validateSortMatchingAds()
    {
        MenuPage menuPage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        alertPage.clickOnMatchingAds();
        alertPage.clickOnSortButtOnAlertPage();
        alertPage.clickOnLowestPriceOnTopOption();
        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage(), "Matching ads not sorted");
    }

    // Tapping "Matching Ads" tab in Alerts screen should display all matching ads related to all alerts that have been created
    @Stories("Alert")
    @Title(" display all matching ads related to all alerts that have been created")
    @Description(" Tapping \"Matching Ads\" tab in Alerts screen should display all matching ads related to all alerts that have been created")
    @Test(groups = "horizontal")
    public void validateTappingMatchingAds()
    {
        MenuPage menuPage = new MenuPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        alertPage.clickOnMatchingAdsTab();
        Assert.assertTrue(alertPage.validateMatchingAdsDisplayedAfterClickingmatchingtab(), "Matching ads not displayed");
    }

    // User should be able to submit Feedback
    @Stories("Menu Drawer")
    @Title("User should be able to submit Feedback")
  //  @Test(groups = "horizontal")
    public void validateFeedback() throws Exception
    {
        MenuPage menuPage = new MenuPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.swipeVertically("msp", "MyAds");
        menuPage.clickOnFeedback();
        menuPage.clickOnGmailOption();
        Assert.assertTrue(menuPage.isGmailTextPresent(), "Submit feedback via gmail not displayed");
    }





    /**
     * Tapping on Customercare number , the phone has to be populated with the number and user should be able to call
     */
    @Stories("Menu Drawer")
    @Title(" Tapping on Customercare number ")
    @Description(" Tapping on Customercare number , the phone has to be populated with the number and user should be able to call")
    @Test
    public void userIAbleToMakeCAllOnTappingCustomerCareNumber()
    {
        MenuPage menuePage=new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.scroll("Appversion");
        menuePage.clickOnAboutQuikr();
        Assert.assertTrue(menuePage.customerCareNumberIsPresent()," customer care number is not present");
//        menuePage.tapOnCustomerCareNumber();
//        Assert.assertTrue(menuePage.validateUserIsAbleToMakeCall(),"on tapping customer care number user phone not populated with number");
    }

   // @Test(groups  ="horizontal")
    public void getMoreResponses(){
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage = new Hompage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        menuePage.clickOnMenuIconInActiveAds(2);
        menuePage.clickOnGetMoreResponses();
        menuePage.clickOnAnnounceChatAvailability();
        Assert.assertTrue(menuePage.verifyIfDone(), "Announce your chat availability is Done");
    }

//    @Test(groups="horizontal")
    public void adsMoveToTop(){
        MenuPage menuePage = new MenuPage(driver);
        Hompage hompage = new Hompage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        String secondAd= menuePage.verifyAdsTitle(1);
        menuePage.verifyAdsTitle(1);
        menuePage.clickActiveAdsMenuIcon(1);
        menuePage.clickMoveToTop();
        menuePage.verifySuccessMsg();
        driver.navigate().back();
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        String firstAd = menuePage.verifyAdsTitle(0);
        Assert.assertTrue(firstAd.contains(secondAd));

    }
}
