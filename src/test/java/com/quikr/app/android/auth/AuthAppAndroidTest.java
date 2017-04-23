package com.quikr.app.android.auth;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.Constants.MobileConstants;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

//import org.testng.Assert;
//import org.testng.annotations.Test;

/**
 * Created by swatantra on 14/8/15.
 */
public class AuthAppAndroidTest extends AppAndroidTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_AUTH_TESTDATA_FILE"));

    /**Android Sanity:2558
     * ANDROID-1013:Verify login with Quikr by providing the details
     */
    @Stories("Login")
    @Title("User is able to Login Using Quikr Account ")
    @Test(groups = "horizontal")
    public void validateLogin()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(testData.get("email"));
        authPage.setSignInPassword(testData.get("password"));
        authPage.submitsignin();
        Assert.assertTrue(authPage.validateLogin(), "usr not able to login");

    }

    /**
     * Android Sanity:2564
     */
    @Stories("Login")
    @Title("User Is able to Reset Password")
    @Test(groups = "horizontal")
    public void validateForgotPassword()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.clickOnForgotPassword();
        authPage.setForgotPasswordInEmail(testData.get("forgotPasswordEmail"));
        authPage.ClickOnResetPassword();
        Assert.assertTrue(authPage.validatForgotPassword());
    }


    /**Android Sanity:2506
     * ANDROID-1016:Verify user is able to sign up by filling all the details
     */
    @Stories("Login")
    @Title("User is Able to SignUp ")
    @Test(groups = "horizontal")

    public void validateSignUp()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.clickOnSignUp();
        authPage.setRegisterEmailId();
        authPage.setRegistrationPassword(testData.get("password"));
        authPage.setReRegistrationPassword(testData.get("password"));
        authPage.setRegistrationMobileNumber(testData.get("number"));
        authPage.navigateBack();
//        authPage.selectLocation();
//        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
//        alertPage.selectCheckBox();
        authPage.agreeRegistrationToTermsAndConditions();
        authPage.clickOnRegister();
        Assert.assertTrue(authPage.validatesuccessFulRegistration());
    }

    /**
     * ANDROID-1003:Verify the 'My Account' page when not logged in
     *
     * ANDROID-1005:Tap on the image tile when not logged in and verify
     */
    @Stories("Login")
    @Title("Verify the 'My Account' page when not logged in")
    @Test(groups = "horizontal")
    public void verifyMyAccountPageWithoutLogin()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        Assert.assertTrue(authPage.validateLoginButton(), " login button is ot present");
        Assert.assertTrue(authPage.validateSignUpButton(), "signUp button not present");
        Assert.assertTrue(authPage.validateFacebookButton(), "facebook button not present");
    }

    /**
     *  ANDROID-1014:Tap on 'SIGNUP' option in Login/SignUp page and verify
     */
    @Stories("Login")
    @Title("Tap on 'SIGNUP' option in Login/SignUp page and verify")
    @Test(groups = "horizontal")
    public void verifyAllOptionsOfSignUp()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.clickOnSignUp();
        Assert.assertTrue(authPage.validateSignUpOptions(),"all options are not present");
    }

    /**
     * ANDROID-1018:Verify the error message when a option is not filled during signup operation
     */
    @Stories("Login")
    @Title("Verify the error message when a option is not filled during signup operation")
    @Test(groups = "horizontal")
    public void verifyAllOptionsNotFill()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.clickOnSignUp();
        authPage.setRegisterEmailId();
        authPage.setRegistrationPassword(testData.get("password"));
        authPage.setReRegistrationPassword(testData.get("password"));
        authPage.navigateBack();
//        authPage.selectLocation();
//        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
//        alertPage.selectCheckBox();
        authPage.agreeRegistrationToTermsAndConditions();
        authPage.clickOnRegister();
        Assert.assertTrue(authPage.validateErrorMsgForSignUpAnyOptionAreNotFill(),"unfilled options compulsory fill in sign up");
    }

    /**
     * ANDROID-1020:Tap on 'Back' button in the Login/SignUp page and verify
     */
    @Stories("Login")
    @Title("ap on 'Back' button in the Login/SignUp page and verify")
    @Test(groups = "horizontal")
    public void verifyLoginAfterBackButtonAction()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(testData.get("email"));
        authPage.setSignInPassword(testData.get("password"));
        authPage.submitsignin();
        authPage.clickOnBackButtonOnLoginPage();
        Assert.assertTrue(menuePage.validateBackButtonActionAfterLogin(),"back button can't work");
    }
    /**
     * Android 2647
     * Able to change password after logging in with Quikr Credentials
     */
    @Stories("Login")
    @Title("Able to change password after logging in with Quikr Credentials")
    @Test(groups = "horizontal")
    public void ValidateUserIsAbleToChangePassword()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(testData.get("email"));
        authPage.setSignInPassword(testData.get("password"));
        authPage.submitsignin();
        authPage.clickOnEditProfile();
        authPage.ClickOnchangePassword();
        authPage.enterCurrentPassword(testData.get("password"));
        authPage.enterNewPassword(testData.get("password"));
        authPage.confirmPassword(testData.get("password"));
        authPage.ClickOnSaveChanges();
        Assert.assertTrue(authPage.validateLogin(), "usr not able to login");

    }
    /**
     * Android 2649
     * Able to edit account details after changing password
     */
    @Stories("Login")
    @Title("Able to edit account details after changing password")
    @Test(groups = "horizontal")
    public void userIsAbleToChangeAcountDetailsAfterChangingPassword()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(testData.get("email"));
        authPage.setSignInPassword(testData.get("password"));
        authPage.submitsignin();
        authPage.clickOnEditProfile();
        authPage.ClickOnchangePassword();
        authPage.enterCurrentPassword(testData.get("password"));
        authPage.enterNewPassword(testData.get("password"));
        authPage.confirmPassword(testData.get("password"));
        authPage.ClickOnSaveChanges();
        Assert.assertTrue(authPage.validateLogin(), "usr not able to login");
        authPage.clickOnEditProfile();
        authPage.cleanExistingPhoneNumber();
        String newNumber=authPage.generateMobileNumber();
        authPage.updateProfilePhoneNumber(newNumber);
        authPage.ClickOnSaveChanges();
        authPage.clickOnEditProfile();
        String profileNumber=authPage.profilePhoneNumber();
        Assert.assertEquals(profileNumber, newNumber);

    }
    /**Android Sanity:3060
     * Verify user is able to edit the alert successfully
     */
//    @Test
    public void verifyUserIsAbleToEditALert()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickOnMyAlerts();
        authPage.selectMenuIcOnAlertPage();
        authPage.clickOnEditAlerts();
//        String category=authPage.getAlertCategory();
        authPage.clicokOnLocation();
        alertPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateAlert());
//        switch (category)
//        {
//            case "Services":
//                alertPage.submitAlert();
//                Assert.assertTrue(alertPage.validateAlert());
//                break;
//            case "Cars & Bikes":
//                papPage.scroll(testData.get("KiloMeter"));
//                papPage.selectDistanceDriven(testData.get("distance"));
//                papPage.selectYear();
//                alertPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_ALERT);
//                alertPage.selectCheckBox();
//                alertPage.scroll("Save");
//                alertPage.submitAlert();
//                Assert.assertTrue(alertPage.validateAlertNewFormat());
//
//
//        }
    }
    /**
     * Verify UI of view profile screen from My account screen
     */
    @Stories({"Login","VAP"})
    @Title("Verify UI of view profile screen from My account screen")
    @Test
    public void verifyUiOfProfileScreen()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(testData.get("email"));
        authPage.setSignInPassword(testData.get("password"));
        authPage.submitsignin();
        authPage.clickOnViewProfileLink();
        authPage.explicitWait(20);
        Assert.assertTrue(authPage.validateUiOfProfilePageActiveStatusIsPresent());
        Assert.assertTrue(authPage.validateUiOfProfilePageAdCountIsPresent());
        Assert.assertTrue(authPage.validateUiOfProfilePageNAmeISPresent());
        Assert.assertTrue(authPage.validateUiOfProfilePageProfileTypeisPresent());
        Assert.assertTrue(authPage.validateUiOfProfileLiveAdIsPresent());

    }
    /**
     * Able to logout from My Account screen
     */
    @Stories("Login")
    @Title("Able to logout from My Account screen")
    @Test
    public void userIsAbleToLogout()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(testData.get("email"));
        authPage.setSignInPassword(testData.get("password"));
        authPage.submitsignin();
        authPage.logout();
        Assert.assertTrue(authPage.validateUserIsLoggedOut(),"on clicking logOut button user is not logged out");

    }

    /**
     * Should be able to login using Google
     */
    @Stories("Login")
    @Title("Test to validate Google login")
    @Test
    public void validateGoogleLogin()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickOnLoginWithGoogle();
        authPage.selectGoogleAccount();
        Assert.assertTrue(authPage.validateLogin(), "User not able to login using Google");
    }
    /**
     * Verify options in My Account screen,
     */
    @Stories("My Account")
    @Title("Verify options in My Account screen")
    @Test()
    public void verifyMyAccountOPtions()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(authPage.myAccountOptions(),"All options are not present in My Accounts");
    }
    /**
     * Tapping on My Ads, My Chats, My Alerts, My Cart, My Orders, My Offers should open appropriate screens

     */
    @Stories("My Account")
    @Title("Tapping on My Ads, My Chats, My Alerts, My Cart, My Orders, My Offers should open appropriate screens")
    @Test(dataProvider = "myAccountOptionsDomElement",dataProviderClass = Data.class)
    public void verifyOnTappingMyAccountsOPtionScreens(String[] domElements)
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        for (int i=0;i<domElements.length;i++)
        {
            authPage.clicOnMyAccountOptions(domElements[i]);
            String options=authPage.getHeaderText();
            authPage.navigateBack();
            if((domElements[i].equalsIgnoreCase(MobileConstants.MyAccountOptions.get(i)))&& options.equalsIgnoreCase("Login/SignUp"))
                Assert.assertTrue(options.equalsIgnoreCase("Login/SignUP"));
            else
                Assert.assertTrue(MobileConstants.MyAccountOptions.get(i).equalsIgnoreCase(options),"Options miss match"+"Expercted="+MobileConstants.MyAccountOptions.get(i)+"Actual="+options);

        }

    }
}
