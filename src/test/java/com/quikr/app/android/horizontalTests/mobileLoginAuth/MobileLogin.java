package com.quikr.app.android.horizontalTests.mobileLoginAuth;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.Constants.MobileConstants;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.menu.MenuPage;
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
 * Created by swatantra singh on 15/3/16.
 */
public class MobileLogin extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_AUTH_TESTDATA_FILE"));
    /**
     * Login with verified email id and valid password
     */
    @Features("MobileLOgin")
    @Stories("Email login")
    @Title("Login with verified email id and valid password")
    @Test
    public void userIsAbleToLogin()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("email"));
        authPageMobileLogin.enterLoginpassword(testData.get("password"));
        authPageMobileLogin.submitLoginCredentials();
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(authPageMobileLogin.validateUserIsLoggedIn(),"user not able to singin");
    }

    /**
     * Verify user is able to register by providing both mobile and email
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title(" Verify user is able to register by providing both mobile and email")
    @Test
    public void userIsAbleToSignUpWithBothMobileAndEmail()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnRegister();
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.registerNewUserNAme(testData.get("name"));
        String numb=authPageMobileLogin.number();
        authPageMobileLogin.registerMobileNumber(numb);
        authPageMobileLogin.registerEmailId();
        authPageMobileLogin.registrationPassword(testData.get("password"));
        authPageMobileLogin.confirmRegistrationPassword(testData.get("password"));
        authPageMobileLogin.registerUser();
        Assert.assertTrue(authPageMobileLogin.validateUserRegistration());
//        Assert.assertTrue(authPageMobileLogin.validateUserRegistration().contains(numb),"Actual OTP msg="+authPageMobileLogin.validateUserRegistration());

    }
    /**
     * Android Sanity:2564
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("User Is able to Reset Password")
    @Test(groups = "horizontal")
    public void validateForgotPassword()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.clickOnForgotPasswordLink();
        authPageMobileLogin.provideEmailOrNUmberToResetPassword(testData.get("forgotPasswordEmail"));
        authPageMobileLogin.submitForgetPasswordCredentials();
        Assert.assertTrue(authPageMobileLogin.validateForgotPassword(testData.get("forgotPasswordEmail")),"unable to reset password");
    }
    /**
     * ANDROID-1003:Verify the 'My Account' page when not logged in
     *
     * ANDROID-1005:Tap on the image tile when not logged in and verify
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("Verify the 'My Account' page when not logged in")
    @Test(groups = "horizontal")
    public void verifyMyAccountPageWithoutLogin()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(authPageMobileLogin.verifyMyAccountPageWithoutLogin(),"all fields not present on login home screen");


    }
    /**
     *  ANDROID-1014:Tap on 'SIGNUP' option in Login/SignUp page and verify
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("Tap on 'SIGNUP' option in Login/SignUp page and verify")
    @Test(groups = "horizontal")
    public void verifyAllOptionsOfSignUp()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnRegister();
        authPageMobileLogin.clickOnLogin();
        Assert.assertTrue(authPageMobileLogin.verifySignUpFields(), "all signUp fields are not present");

    }
    /**
     * Android 2647
     * Able to change password after logging in with Quikr Credentials
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("Able to change password after logging in with Quikr Credentials")
    @Test(groups = "horizontal")
    public void ValidateUserIsAbleToChangePassword()
    {
        MenuPage menuePage = new MenuPage(driver);
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


    }
    /**
     * Android 2649
     * Able to edit account details after changing password
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("Able to edit account details after changing password")
    // @Test(groups = "horizontal")
    public void userIsAbleToChangeAcountDetailsAfterChangingPassword()
    {
        MenuPage menuePage = new MenuPage(driver);
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
        authPageMobileLogin.clickOnEditProfileMobileLOgin();
        authPageMobileLogin.changeName(testData.get("name"));
        authPageMobileLogin.saveEditedChanges();
        //will complete after bug is resolved

    }
    /**
     * Verify UI of view profile screen from My account screen
     */
    @Features("MobileLOgin")
    @Stories({"MobileLogin","VAP"})
    @Title("Verify UI of view profile screen from My account screen")
    //@Test
    public void verifyUiOfProfileScreen()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("email"));
        authPageMobileLogin.enterLoginpassword(testData.get("password"));
        authPageMobileLogin.submitLoginCredentials();
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnViewProfile();
        ////will complete after bug is resolved


    }
    /**Verify user is able to logout successfully if he is logged in.
     * Able to logout from My Account screen
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("Able to logout from My Account screen")
    @Test
    public void userIsAbleToLogout()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("email"));
        authPageMobileLogin.enterLoginpassword(testData.get("password"));
        authPageMobileLogin.submitLoginCredentials();
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnSettingsOnMyAcountPage();
        authPageMobileLogin.logout();
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(!authPageMobileLogin.validateUserIsLoggedIn(), "user not logged out");


    }
    /**
     * Should be able to login using Google
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("Test to validate Google login")
    @Test
    public void validateGoogleLogin()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        //authPage.clickONLOginSignUPIcon();
        authPage.clickOnLoginWithGoogle();
        authPage.selectGoogleAccount();
        Assert.assertTrue(authPage.validateLogin(), "User not able to login using Google");
    }

    /**
     * Tapping on My Ads, My Chats, My Alerts, My Cart, My Orders, My Offers should open appropriate screens

     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("Tapping on  My Cart, My Orders, My Offers should open appropriate screens")
    @Test(dataProvider = "myAccountOptionsDomElement",dataProviderClass = Data.class)
    public void verifyOnTappingMyAccountsOPtionScreens(String[] domElements)
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        WebElement myAccountScrollArea = authPageMobileLogin.getScrollableMyAccountArea();
        for (int i=0;i<domElements.length;i++)
        {
            //menuePage.scroll("Quikr Doorstep");
            menuePage.loopScrollToElementByName(myAccountScrollArea,"quikrDoorstep",true,1000);
            if (domElements[i].equalsIgnoreCase("Orders"))
            {
                authPageMobileLogin.clickOnMyAccountOPtions(domElements, i);
                Assert.assertTrue(authPageMobileLogin.validateActionAfterClickingOrders());
                authPageMobileLogin.navigateBack();
            }
            else {
                authPageMobileLogin.clickOnMyAccountOPtions(domElements, i);
                String options = authPageMobileLogin.getHeaderText();
                Assert.assertTrue(MobileConstants.MyAccountOptionsMobileLogin.get(i).equalsIgnoreCase(options), "Options miss match" + "Expected=  " + MobileConstants.MyAccountOptionsMobileLogin.get(i) + "Actual=" + options);
                authPageMobileLogin.navigateBack();
            }
        }

    }
    /**
     * Verify user is able to register by providing only mobile number and other details
     */
    @Features("MobileLOgin")
    @Stories("Register with only mobile")
    @Title("Verify user is able to register by providing only mobile number and other detail")
    @Test
    public void verifyUSerIsAbleToRegisterWithoutEmail()
    {

        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnRegister();
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.registerNewUserNAme(testData.get("name"));
        String numb=authPageMobileLogin.number();
        authPageMobileLogin.registerMobileNumber(numb);
        authPageMobileLogin.registrationPassword(testData.get("password"));
        authPageMobileLogin.confirmRegistrationPassword(testData.get("password"));
        authPageMobileLogin.registerUser();
        Assert.assertTrue(authPageMobileLogin.validateUserRegistration());
        //Assert.assertTrue(authPageMobileLogin.validateUserRegistration().contains(numb),"Actual OTP msg="+authPageMobileLogin.validateUserRegistration());
    }
    /**
     * Verify user is able to register by providing only email and other details
     */
    @Features("MobileLOgin")
    @Stories("Register with only Email")
    @Title("Verify user is able to register by providing only Email and other detail")
    @Test
    public void verifyUSerIsAbleToRegisterWithoutNumber()
    {

        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnRegister();
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.registerNewUserNAme(testData.get("name"));
        authPageMobileLogin.registerEmailId();
        String numb=authPageMobileLogin.number();
        authPageMobileLogin.registerMobileNumber(numb);
        authPageMobileLogin.registrationPassword(testData.get("password"));
        authPageMobileLogin.confirmRegistrationPassword(testData.get("password"));
        authPageMobileLogin.registerUser();
        Assert.assertTrue(authPageMobileLogin.validateUserRegistration());
        //Assert.assertTrue(authPageMobileLogin.validateUserRegistration().contains(numb),"Actual OTP msg="+authPageMobileLogin.validateUserRegistration());
    }

    /**
     *Verify user is not able to register with already registered mobile number
     */
    @Features("MobileLOgin")
    @Stories("Already registered")
    @Title("Verify user is not able to register with already registered mobile number")
    @Test
    public void userIsNotAbleToRegisterWithAlreadyRegisteredNumber()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnRegister();
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.registerNewUserNAme(testData.get("name"));
        authPageMobileLogin.registerMobileNumberVerified();
        authPageMobileLogin.registerEmailId();
        authPageMobileLogin.registrationPassword(testData.get("password"));
        authPageMobileLogin.confirmRegistrationPassword(testData.get("password"));
        authPageMobileLogin.registerUser();
        // “Email already registered” or “Mobile already registered” or "“Email and mobile already registered”
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase(testData.get("signUpErrorMsg2")), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());

    }

    /**
     * Verify user is not able to register with already registered email
     */
    @Features("MobileLOgin")
    @Stories("Already registered")
    @Title("Verify user is not able to register with already registered Email")
    @Test
    public void userIsNotAbleToRegisterWithAlreadyRegisteredEmail()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnRegister();
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.registerNewUserNAme(testData.get("name"));
        authPageMobileLogin.registeredEmailId();
        authPageMobileLogin.registrationPassword(testData.get("password"));
        authPageMobileLogin.confirmRegistrationPassword(testData.get("password"));
        authPageMobileLogin.registerUser();
        // “Email already registered” or “Mobile already registered” or "“Email and mobile already registered”
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase(testData.get("signUpEmailError")), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());
    }
    /**
     * Verify error is displayed if password length is less than six characters
     */
    @Features("MobileLOgin")
    @Stories("Field validations")
    @Title("Verify error is displayed if password length is less than six characters")
    @Test
    public void verifyErrorMsgForPasswordLenght()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnRegister();
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.registerNewUserNAme(testData.get("name"));
        authPageMobileLogin.registerEmailId();
        authPageMobileLogin.registrationPassword(testData.get("ShortPassword"));
        authPageMobileLogin.confirmRegistrationPassword(testData.get("ShortPassword"));
        authPageMobileLogin.registerUser();
        String errorMsg=authPageMobileLogin.passwordErrorMsg();
        Assert.assertTrue(errorMsg.equalsIgnoreCase(testData.get("passwordError")),"actual="+errorMsg +"expected ="+testData.get("passwordError"));
    }
    /**
     * Verify error displayed if password and confirm password does not match
     */
    @Features("MobileLOgin")
    @Stories("Field validations")
    @Title("Verify error displayed if password and confirm password does not match")
    @Test
    public void verifyErrorMsgForPasswordNotMatch()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnRegister();
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.registerNewUserNAme(testData.get("name"));
        authPageMobileLogin.registerEmailId();
        authPageMobileLogin.registrationPassword(testData.get("password"));
        authPageMobileLogin.confirmRegistrationPassword(testData.get("password1"));
        authPageMobileLogin.registerUser();
        String errorMsg=authPageMobileLogin.confirmPasswordMissmatch();
        Assert.assertTrue(errorMsg.equalsIgnoreCase(testData.get("confirmPasswordError")),"actual="+errorMsg +"expected ="+testData.get("confirmPasswordError"));
    }
    /**
     * Verify user is not able to login by providing unregistered email id.
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title("Verify user is not able to login by providing unregistered email id.")
    @Test
    public void verifyUserNotAbleToLoginWithUnregisteredEmail()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(authPageMobileLogin.EmailId());
        authPageMobileLogin.enterLoginpassword(testData.get("password"));
        authPageMobileLogin.submitLoginCredentials();
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase(testData.get("unregisteredAccountLogin")), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());

    }
    /**
     * Verify user is not able to login by providing unregistered mobile number.
     */
    @Features("MobileLOgin")
    @Stories("MobileLogin")
    @Title(" Verify user is not able to login by providing unregistered mobile number.")
    @Test
    public void verifyUserNotAbleToLoginWithUnregisteredPhone()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        String numb=authPageMobileLogin.number();
        authPageMobileLogin.enterLoginEmailNumber(numb);
        authPageMobileLogin.enterLoginpassword(testData.get("password"));
        authPageMobileLogin.submitLoginCredentials();
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase(testData.get("unregisteredAccountLogin")), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());

    }
    /**
     * Login with unverified email id and valid password
     */
    @Features("MobileLOgin")
    @Stories("Email login")
    @Title("Login with unverified email id and valid password")
    @Test
    public void verifyUserNotAbleToLoginWithUverifiedEmail()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("unverifiedEmail"));
        authPageMobileLogin.enterLoginpassword(testData.get("unverifiedEmailPassword"));
        authPageMobileLogin.submitLoginCredentials();
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase(testData.get("loginWithUnverifiedEmailError")), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());

    }
    /**
     * Login with verified email id and invalid password
     */
    @Features("MobileLOgin")
    @Stories("Email login")
    @Title("Login with verified email id and invalid password")
    @Test
    public void verifyUserloginWithVerifiedEmailAndInvalidPassword() {
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("email"));
        authPageMobileLogin.enterLoginpassword(testData.get("unverifiedEmailPassword"));
        authPageMobileLogin.submitLoginCredentials();
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase(testData.get("loginWithInvalidPassword")), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());

    }
    /**
     * Login with verified mobile number and valid password
     */
    @Features("MobileLOgin")
    @Stories("Mobile login")
    @Title("Login with verified email id and valid password")
    @Test
    public void userIsAbleToLoginWithVErifiedNumber()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("RegisteredNumber"));
        authPageMobileLogin.enterLoginpassword(testData.get("unverifiedEmailPassword"));
        authPageMobileLogin.submitLoginCredentials();
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(authPageMobileLogin.validateUserIsLoggedIn(),"user not able to singin");
    }
    /**
     * Login with verified mobile number and invalid password
     */
    @Features("MobileLOgin")
    @Stories("Mobile login")
    @Title("Login with verified email id and invalid password")
    @Test
    public void verifyUserloginWithVerifiedNumberAndInvalidPassword() {
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("RegisteredNumber"));
        authPageMobileLogin.enterLoginpassword(testData.get("password1"));
        authPageMobileLogin.submitLoginCredentials();
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase(testData.get("loginWithInvalidPassword")), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());

    }
    /**
     * Login with unverified mobile number and valid password.
     */
    @Features("MobileLOgin")
    @Stories("Mobile login")
    @Title(" Login with unverified mobile number and valid password.")
    //@Test
    public void verifyUserNotAbleToLoginWithUverifiedNumber()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        String numb=authPageMobileLogin.number();
        authPageMobileLogin.enterLoginEmailNumber(numb);
        authPageMobileLogin.enterLoginpassword(testData.get("unverifiedEmailPassword"));
        authPageMobileLogin.submitLoginCredentials();
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase(testData.get("loginWithUnverifiedEmailError")), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());

    }

}
