package com.quikr.app.android.horizontalTests.mobileLoginAuth;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Swatantra singh on 11/4/16.
 */
public class MobileLogin1 extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_AUTH_TESTDATA_FILE"));

    /**
     * Verify error is displayed if we give invalid email or mobile while login.
     */
    @Features("MobileLOgin")
    @Stories("Add email")
    @Title("Verify tapping on (+) icon to add a new email in account.")
    @Test
    public void verifyActionOnTappingAddNewEmail()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickAddNewEmailUnderEditProfile();
        String text=authPageMobileLogin.addNumberEmailHeaderText();
        Assert.assertTrue(text.equalsIgnoreCase(testData.get("AddNewEmailPopUp")),"on tappng add email error msg PopUp displayed is"+text);

    }
    /**
     * Verify adding a new email id(account does not get orphaned).
     */
    @Features("MobileLOgin")
    @Stories("Add email")
    @Title("Verify adding a new email id(account does not get orphaned)")
    @Test
    public void verifyOnAddingNewEmailAccountIsNOtOrphaned()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickAddNewEmailUnderEditProfile();
        authPageMobileLogin.addNewEmailNumberToAccount(mobileNumber() + "sw@gmail.com");
        authPageMobileLogin.clickOnAddAndVerifyEmailNumber();
       // Assert.assertTrue(authPageMobileLogin.getTextOfMessageAfterClickingAddAndVerifyEmailNumber().contains(mobileNumber()+"sw@gmail.com"),authPageMobileLogin.getTextOfMessageAfterClickingAddAndVerifyEmailNumber());
        Assert.assertTrue(authPageMobileLogin.newEmailAddedSuccessfully());

    }
    /**
     * Verify tapping on (+) icon to add a new mobilel in account.
     */
    @Features("MobileLOgin")
    @Stories("Add number")
    @Title("Verify tapping on (+) icon to add a new number in account.")
    @Test
    public void verifyActionOnTappingAddNewMobile()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickAddNewMobileNumberUnderEditProfile();
        String text=authPageMobileLogin.addNumberEmailHeaderText();
        Assert.assertTrue(text.equalsIgnoreCase(testData.get("AddNewNumber")),"on tappng add email error msg PopUp displayed is"+text);

    }
    /**
     * Verify adding a new number id(account does not get orphaned).
     */
    @Features("MobileLOgin")
    @Stories("Add number")
    @Title("Verify adding a new mobile number.(account does not get orphened).")
    @Test
    public void verifyOnAddingNewNumberAccountIsNOtOrphaned()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickAddNewMobileNumberUnderEditProfile();
        authPageMobileLogin.addNewEmailNumberToAccount(mobileNumber());
        authPageMobileLogin.clickOnAddAndVerifyEmailNumber();
        Assert.assertTrue(authPageMobileLogin.validateUserRegistration());

    }
    /**
     * Verify adding a new mobile (account gets orphened).
     */
    @Features("MobileLOgin")
    @Stories("Add number")
    @Title("Verify adding a new mobile number.(account does not get orphened).")
    //@Test
    public void verifyOnAddingVerifiedNumberAccountIsOrphaned()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickAddNewMobileNumberUnderEditProfile();
        authPageMobileLogin.addNewEmailNumberToAccount(testData.get("verifiedNumber"));
        authPageMobileLogin.clickOnAddAndVerifyEmailNumber();
        String text=authPageMobileLogin.errorMessageText();
        Assert.assertTrue(text.equalsIgnoreCase(testData.get("AddExistingNumberError")), "on adding a verified number error displayed" + text);


    }
    /**
     * Verify if user is logged in, Logout option is available in my account screen.
     */
    @Features("MobileLOgin")
    @Stories("Logout")
    @Title("Verify if user is logged in, Logout option is available in my account screen.")
    @Test
    public void verifyLogoutOptionIsVisiBletTologgedInUser()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnSettingsOnMyAcountPage();
        Assert.assertTrue(authPageMobileLogin.logoutIsPresent(),"logout is not visible");


    }
    /**
     Verify if user is logged in, his name is displayed in side menu if available.
     */
    @Features("MobileLOgin")
    @Stories("Logout")
    @Title(" Verify if user is logged in, his name is displayed in side menu if available")
    @Test
    public void LoggedInUserNameIsPresentInMenuDrawer()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        Assert.assertTrue(authPageMobileLogin.validateUserNameISPresentInMenuDrawer(), "User NAme is not present in menu drawer");


    }
    /**
     * After logging lout of the app, user's name is not displayed in side menu.
     */
    @Features("MobileLOgin")
    @Stories("Logout")
    @Title(" After logging lout of the app, user's name is not displayed in side menu.")
    @Test
    public void LoggedOutUserNameIsPresentInMenuDrawer() {
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnSettingsOnMyAcountPage();
        authPageMobileLogin.logout();
        menuePage.clickOnMenuIcon();
        Assert.assertFalse(authPageMobileLogin.validateUserNameISPresentInMenuDrawer(), "User NAme is  present in menu drawer for logged out user");


    }
    /**
     * If entered email already exists in the system, and user did not provide email while registering
     */
    @Features("MobileLOgin")
    @Stories("UserLoggedIn")
    @Title(" If entered email already exists in the system, and user did not provide email while registering")
    @Test
    public void verifyErrorMsgforPostAdWithRegisteredEmail (){
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        Hompage hompage=new Hompage(driver);
        PapPage papPage=new PapPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("verifiedNumber"), testData.get("verifiednumberPassword"));
        hompage.clickonPostAd();
        postAdV2Page.waitForPapToLoad();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        WebElement postAdScreen = postAdV2Page.getPostAdScrollView();
        postAdV2Page.swipeToPostAd(cord[0] + 50, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadTitle"));
        postAdV2Page.hideKeyboard();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadDescription"));
        postAdV2Page.hideKeyboard();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        WebElement email = postAdV2Page.loopScrollToElementByName(postAdScreen,"enterEmail",true,1000);
        //postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, testData.get("verifiedEmail1"));
        email.sendKeys(testData.get("verifiedEmail1"));
        postAdV2Page.hideKeyboard();
        postAdV2Page.postYourAd();
        Assert.assertTrue(postAdV2Page.errorMessageText().equalsIgnoreCase(testData.get("verifiedEmailPostAdError")), "wrong error message displayed =" + postAdV2Page.errorMessageText());

    }
    /**
     *
     */
    /**
     * If entered email already exists in the system, and user did not provide email while registering
     */
    @Features("MobileLOgin")
    @Stories("UserLoggedIn")
    @Title(" Email and mobile field should be non editable")
    @Test
    public void verifyForLoggedInUserMobileAndEmailIsNonEditable(){
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        Hompage hompage=new Hompage(driver);
        PapPage papPage=new PapPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.scroll("Post Your Ad");
        Assert.assertTrue(authPageMobileLogin.forloggedInUserEmailAndMobileIsNonEditAble("automation.quikr@gmail.com"));


    }

    /**
     * verify header of user profile screen
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfProfileScreen()
    {
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnViewProfile();
        Assert.assertTrue(authPageMobileLogin.verifyBackButtonOnUserProfilePage());
        Assert.assertTrue(authPageMobileLogin.getUserProfileTitle().contains("User Profile"),"user profile title"+authPageMobileLogin.getUserProfileTitle());

    }
    @Features("MobileLOgin")
    @Stories("Login")
    @Title("Verify when user clicks on login through OTP entered email/mobile number is prefilling in login screen")
    @Test
    public void verifyPrefillingInLoginScreen()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("verifiedNumber"));
        authPageMobileLogin.clickOnLoginThroughOtp();
        Assert.assertTrue(authPageMobileLogin.prifilledLoginNumber().equalsIgnoreCase(testData.get("verifiedNumber")),"Prefilled mobile number"+authPageMobileLogin.prifilledLoginNumber()+"Actual number Entered"+testData.get("verifiedNumber"));
    }
    @Features("MobileLOgin")
    @Stories("Login")
    @Title("Verify when user clicks on forgot password entered email/mobile number is prefilling in forgot password screen")
    @Test
    public void verifyPrefillingInForgotPassword()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("verifiedNumber"));
        authPageMobileLogin.clickOnForgotPasswordLink();
        Assert.assertTrue(authPageMobileLogin.prifilledForgotPAsswordNumber().equalsIgnoreCase(testData.get("verifiedNumber")),"Prefilled mobile number"+authPageMobileLogin.prifilledForgotPAsswordNumber()+"Actual number Entered"+testData.get("verifiedNumber"));

    }

}
