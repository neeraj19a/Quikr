package com.quikr.app.android.horizontalTests.mobileLoginAuth;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Swatantra on 10/12/15.
 */
public class MyAdsTests extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_AUTH_TESTDATA_FILE"));

    /**
     * Android Sanity:2297
     */
    // @Test(groups = "horizontal")
    public void verifyRePostDeletedAd()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        VapPage vapPage=new VapPage(driver);
//        hompage.ApplaunchPopup();
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        menuePage.selectInactiveButton();
        Assert.assertTrue(menuePage.validateDeletedAdViewedAndStatusReflected(), "deleted ad status is not correctly reflected");
        if(menuePage.validateRePostAdButton())
        {
            menuePage.selectRePostButton();
            vapPage.clickOnRePostAdButtonAtVap();
            vapPage.selectChoiceInRePostAdPopUp();
            Assert.assertTrue(vapPage.validationRePostAd(), "rePost ad success msg can't display");
        }
    }

    /**
     * Android Sanity:2314
     * User should be able to edit Ad from Preview screen
     */

    // @Test(groups = "horizontal")
    public void editAdFromPreviewScreen()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        // papPage.setAdTitle(testData.get("AdTitle"));
        // papPage.setAdDescription(testData.get("desc"));
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.navigateToNextPage();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectElements(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Year");
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll(testData.get("KiloMeter"));
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd());
        papPage.selectViewMyAdButton();
        papPage.clickOnEditAd();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.scroll("Save");
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd());

    }
    /**
     * Android Sanity:2650
     * Able to view ads in "My Ads" section after changing password
     */
    @Stories("Login")
    @Title("User is Able to view ads in My Ads section after changing password")
    @Test(groups = "horizontal")
    public void userIsAbleToSeeMyAdsAfterChangingPasswords()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("email"));
        authPageMobileLogin.enterLoginpassword(testData.get("password"));
        authPageMobileLogin.submitLoginCredentials();
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnSettingsOnMyAcountPage();
        authPageMobileLogin.clickChangePassword();
        authPageMobileLogin.enterCurrentPassword(testData.get("password"));
        authPageMobileLogin.enterNewPassword(testData.get("password"));
        authPageMobileLogin.enterConfirmPassword(testData.get("password"));
        authPageMobileLogin.changePassword();
        Assert.assertTrue(authPageMobileLogin.validateUserIsLoggedIn(), "User is  not able to change password Successfully");
        authPageMobileLogin.navigateBack();
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("myAd"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(authPage.userIsAbleToSeeMyAdsAfterChangingPassword(),"user is not able see his/her ads under 'my ads' after changing password ");

    }
    /**AndroidSanity:3055
     * Verify View profile button is displayed on VAP
     */
    @Stories("VAP")
    @Title("Verify View profile button is displayed on VAP")
    @Test(groups = "horizontal")
    public void onClickingViewProfileOnVApUserIsRedirectedProfilePage()
    {
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        VapPage vapPage=new VapPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.hideOverlayLayout();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        snbPage.scroll(testData.get("viewProfile"));
        vapPage.clickOnViewProfileOnVap();
        Assert.assertTrue(vapPage.validateUserProfile(),"on clcikoin view profile user is not redirected profile page");

    }
    /**
     * Verify if the CTA "Edit " and "Make Premium" are present if the ad is not premium
     */
    @Stories("MyAds")
    @Title("CTA \"Edit \" and \"Make Premium\" are present if the ad is not premium")
    @Test
    public void CTAEditAndMAkePremiumIsPresentForNOnPremiumAds()
    {
        MenuPage menuePage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        snbPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(authPage.CTAEditIsPresent()&&authPage.CTAPremiumIsPresent(),"CTA EDIT & Premium is not present for non premium ads");
    }
    /**
     * Able to Edit Ads across all categories
     * Carousal screen should be displayed appripriately in Post Ad success screen when there are more than 1 card (Post an Ad in Cars & Bikes)

     - Should be able to swipe between cards
     - Tapping on "No" button for Cars card should display the next ("Make Premium") Card
     - Tapping on "Yes" in Cars card should display the appropriate screen
     */
    @Stories("Edit Ad")
    @Title("Able to Edit Ads & Carousal screen should be displayed ")
    @Description("Should be able to swipe between cards\n" +
            "     - Tapping on \"No\" button for Cars card should display the next (\"Make Premium\") Card\n" +
            "     - Tapping on \"Yes\" in Cars card should display the appropriate screen")
    @Test
    public void userIsAbleToEditAd()
    {
        MenuPage menuePage=new MenuPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        PapPage papPage=new PapPage(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        snbPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        papPage.clickOnEditAd();
        postAdV2Page.selectcategory();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.scroll("Save");
        postAdV2Page.editMobileNumber(mobileNumber());
        postAdV2Page.navigateBack();
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to edit ad");

    }

    @Stories("GetMoreResponse")
//    @Test
    public void validateChatAvailibilityButtonTurnsGreenAfterClicking()
    {  MenuPage menuePage=new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        authPageMobileLogin.selectMenuIcOnMyAds();
        authPageMobileLogin.getMoreResponses();
        authPageMobileLogin.makeChatAvailability();
        Assert.assertTrue(authPageMobileLogin.verifyChatAvailabilityButtonTurnsGreen());
    }
    @Stories("GetMoreResponse")
//    @Test
    public void onclickingMAkePremiumUserIsRedirectedtoPremiumPAge()
    {   MenuPage menuePage=new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        authPageMobileLogin.selectMenuIcOnMyAds();
        authPageMobileLogin.getMoreResponses();
        authPageMobileLogin.makeAdPremiumFromGetResponse();
        Assert.assertTrue(postAdV2Page.validatePostAd());
    }
    @Stories("GetMoreResponse")
//    @Test
    public void validateShareLocationButtonTurnsGreenAfterClicking()
    {  MenuPage menuePage=new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        authPageMobileLogin.selectMenuIcOnMyAds();
        authPageMobileLogin.getMoreResponses();
        authPageMobileLogin.makeChatAvailability();
        Assert.assertTrue(authPageMobileLogin.verifyChatAvailabilityButtonTurnsGreen());
    }

}
