package com.quikr.app.android.auth;


import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.RandomStringUtils;

import static com.quikr.utils.PropertyReader.getProperties;


/**
 * Created by swatantra singh on 14/8/15.
 */
public class AuthPage  extends AppAndroidPageBase {
    public AuthPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    // const string
    private static final String domFile = getProperties().get("ANDROID_AUTH_DOM_FILE");

    /**
     * set login email
     * @param email
     */
    public void setSignInEmail(String email)
    {
        Mapper.waitForElementToBeVisible(domFile, "signInEmail");
        Mapper.find(domFile,"signInEmail").sendKeys(email);

    }

    /**
     * provide password
     * @param Password
     */
    public void setSignInPassword(String Password)
    {
        if (!isElementPresent("submitLogin"))
            navigateBack();
        Mapper.find(domFile,"password").sendKeys(Password);
    }

    /**
     * Click on login icon
     * @return
     */
    public  AuthPage submitsignin()
    {
        if (!isElementPresent("submitLogin"))
            navigateBack();
        Mapper.find(domFile,"submitLogin").click();
        return this;

    }

    /**
     * provide forgot Password emial
     * @param email
     */
    public void setForgotPasswordInEmail(String email)
    {
        Mapper.waitForElementToBeVisible(domFile,"forgotPasswordEmial");
        Mapper.find(domFile,"forgotPasswordEmial").sendKeys(email);
    }

    /**
     * click on reset password
     * @return
     */
    public  AuthPage ClickOnResetPassword()
    {
        Mapper.find(domFile,"resetPassword").click();
        return this;
    }

    /**
     * click on forgot password link
     * @return
     */

    public AuthPage clickOnForgotPassword()
    {
        Mapper.find(domFile,"forgotpassword").click();
        return  this;
    }

    /**
     * validate login
     * @return
     */
    public boolean validateLogin()
    {
//        Mapper.waitForElementToBeVisible(domFile, "validateLogin");
//        if (isElementPresent("validateLogin"))
        Mapper.waitForElementToBeVisible(domFile, "logout");
        if(isElementPresent("logout"))
            return true;
        return false;
    }

    /**
     * validate forgotpassword
     * @return
     */
    public boolean validatForgotPassword()
    {
        Mapper.waitForElementToBeVisible(domFile, "forgotPasswordValidation");
        if (isElementPresent("forgotPasswordValidation"))
            return true;
        return false;
    }

    /**
     * click on signup to open SignUP form
     * @return
     */
    public AuthPage clickOnSignUp()
    {
        Mapper.find(domFile,"signUp").click();
        return  this;
    }

    /**
     * usingh rand function to create unique email id for every run
     * swatantra singh
     * @return
     */
    public AuthPage setRegisterEmailId()
    {
        String emailId= "swat_" +(int)(10000000* Math.random()) + "@gmail.com";
        Mapper.waitForElementToBeVisible(domFile, "signUpemail");
        Mapper.find(domFile,"signUpemail").sendKeys(emailId);
        return  this;
    }

    /**
     * provide password for signup
     * swatantra singh
     * @param password
     * @return
     */
    public AuthPage setRegistrationPassword(String password)
    {
        if (!isElementPresent("signupPassword"))
            navigateBack();
        Mapper.find(domFile,"signupPassword").sendKeys(password);
        return this;
    }

    /**
     * re enter password
     * @param repassword
     * @return
     */
    public AuthPage setReRegistrationPassword(String repassword)
    {
        Mapper.find(domFile,"repassword").sendKeys(repassword);
        return this;
    }

    /**
     * provide number for registration
     * @param number
     * @return
     */
    public AuthPage setRegistrationMobileNumber(String number)
    {
        Mapper.find(domFile,"signUpnumber").sendKeys(number);
        return this;
    }

    /**
     * submit details
     * @return
     */

    public AuthPage clickOnRegister()
    {
        Mapper.find(domFile,"signUpRegister").click();
        return this;
    }

    public AuthPage agreeRegistrationToTermsAndConditions()
    {
        Mapper.find(domFile,"registrationTerms").click();
        return this;
    }

    /**
     * validate successful registration
     * swatantra singh
     * @return
     */
    public boolean validatesuccessFulRegistration()
    {
        if (isElementPresent("validateReg"))
            return true;
        return false;
    }

    /**
     * click on location button
     */
    public  void selectLocation()
    {
        Mapper.find(domFile,"location").click();
    }

    /**
     * function to validate sign up button
     */
    public boolean validateSignUpButton()
    {
        return isElementPresent("signUp");
    }

    /**
     * function to validate login button
     */
    public boolean validateLoginButton()
    {
        return isElementPresent("submitLogin");
    }

    /**
     * function validate facebook button
     */
    public boolean validateFacebookButton()
    {
        return isElementPresent("facebookButton");
    }

    /**
     * function to validate all options of sign up
     */
    public boolean validateSignUpOptions()
    {
        return (isElementPresent("signUpemail") && isElementPresent("signupPassword") && isElementPresent("repassword")&&isElementPresent("signUpnumber"));
    }

    /**
     * function to validate error msg after if sign up any  option don't fill
     */
    public boolean validateErrorMsgForSignUpAnyOptionAreNotFill()
    {
        return isElementPresent("errorMsg");
    }

    /**
     * function to select back button on after login page
     */
    public void clickOnBackButtonOnLoginPage()
    {
        Mapper.find(domFile,"backButtonOnLogin").click();
    }
    /**
     * click on login SignUp to proceed to login page
     *
     */
    public void clickONLOginSignUPIcon()
    {
        Mapper.waitForElementToBeVisible(domFile,"redirectToligin");
        Mapper.find(domFile,"redirectToligin").click();
    }

    /**
     * clck on login with quikr account
     */
    public void clickONLoginWithQuikr()
    {
        Mapper.waitForElementToBeVisible(domFile, "loginWithQuikr");
        Mapper.find(domFile,"loginWithQuikr").click();
    }
    /**
     * click on my alerts from my Account Page
     */
    public void clickOnMyAlerts()
    {
        Mapper.waitForElementToBeClickable(domFile, "myAlert");
        Mapper.find(domFile,"myAlert").click();
    }
    /**
     * click on edit profile to edit personal info
     */
    public void clickOnEditProfile()
    {
        Mapper.waitForElementToBeVisible(domFile, "editProfile");
        Mapper.find(domFile,"editProfile").click();
    }
    /**
     * click on change Password
     */
    public void ClickOnchangePassword()
    {
        Mapper.waitForElementToBeVisible(domFile,"changePassword");
        Mapper.find(domFile,"changePassword").click();

    }

    /**
     * enter existing quikr password
     * @param password
     */
    public void enterCurrentPassword(String password)
    {
        Mapper.waitForElementToBeVisible(domFile, "currentPassword");
        Mapper.find(domFile,"currentPassword").sendKeys(password);

    }

    /**
     * enter new passowrd
     * @param password
     */
    public void enterNewPassword(String password)
    {
        int Y1=Mapper.find(domFile,"changePassword").getLocation().getY();
        int Y2=Mapper.find(domFile,"actionBar").getLocation().getY();
        verticalSwipeWithCordinates(Y1, Y2);
        Mapper.find(domFile,"newPassword").sendKeys(password);

    }

    /**
     * confirm password
     * @param password
     */
    public void confirmPassword(String password)
    {
        int Y1=Mapper.find(domFile,"changePassword").getLocation().getY();
        int Y2=Mapper.find(domFile,"actionBar").getLocation().getY();
        verticalSwipeWithCordinates(Y1, Y2);
        Mapper.find(domFile,"confirmPassword").sendKeys(password);

    }
    /**
     * submit edit profile
     */

    public void ClickOnSaveChanges()
    {
        Mapper.waitForElementToBeVisible(domFile, "submitEditProfileChanges");
        Mapper.find(domFile,"submitEditProfileChanges").click();

    }
    /**
     * click on clity to change city
     */
    public void changeCity()
    {
        Mapper.waitForElementToBeVisible(domFile,"profileCity");
        Mapper.find(domFile,"profileCity").click();

    }
    /**
     * edit profilr Phone Number
     */
    public void updateProfilePhoneNumber(String phNo)
    {
        Mapper.waitForElementToBeVisible(domFile,"editProfilePhone");
        Mapper.find(domFile,"editProfilePhone").sendKeys(phNo);
    }
    /**
     * clear existing phone Number
     */
    public void cleanExistingPhoneNumber()
    {
        Mapper.waitForElementToBeVisible(domFile,"editProfilePhone");
        Mapper.find(domFile,"editProfilePhone").clear();
    }
    /**
     * generate unique 10 digit mobile number
     */
    public String generateMobileNumber()
    {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        String phNo = 99393+randomNumbers;
        return phNo;
    }
    /**
     *
     * get phone number displayed on profile
     */
    public  String profilePhoneNumber()
    {
        Mapper.waitForElementToBeVisible(domFile,"editProfilePhone");
        String number=Mapper.find(domFile,"editProfilePhone").getText();
        return  number;
    }
    /**
     * validate user is able to see ads ubder my ads section after changing password
     */
    public  boolean userIsAbleToSeeMyAdsAfterChangingPassword()
    {
        return (isElementPresent("myAdsTitle")&&isElementPresent("inactiveAds")&&isElementPresent("activeAds"));
    }
    /**
     * clcick on edit adler
     */
    public void clickOnEditAlerts()
    {
        Mapper.waitForElementToBeVisible(domFile,"editAlert");
        Mapper.finds(domFile,"editAlert").get(0).click();
    }
    /**
     * clcik on menu icon on alert page to select edit alert
     */
    public void selectMenuIcOnAlertPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "menuIcon");
        Mapper.find(domFile, "menuIcon").click();
    }
    /**
     * edit location during edit alert
     */
    public void clicokOnLocation()
    {
        Mapper.waitForElementToBeVisible(domFile,"editPage");
        Mapper.scroll("Location");
        Mapper.find(domFile,"location").click();
    }
    /**
     * get alert category
     */
    public String getAlertCategory()
    {
        Mapper.waitForElementToBeVisible(domFile,"alertCategory");
        String text=Mapper.find(domFile,"alertCategory").getText();
        return text;
    }
    /**
     *
     * positive acknowledgement
     */
    public void clickOnPositiveAcknowledgement()
    {
        if (isElementPresent("validateReg"))
            Mapper.find(domFile,"validateReg").click();
    }
    /**
     * Verify if the CTA "Edit "is present
     */
    public boolean CTAEditIsPresent()
    {
        return (isElementPresent("editAd"));
    }
    /**
     * Verify if the CTA "Premium "is present
     */
    public boolean CTAPremiumIsPresent()
    {
        return (isElementPresent("premiumButton"));
    }
    /**
     * view profile link is present on MY account Page
     */
    public boolean viewProfileLinkIsPresent()
    {
        return (isElementPresent("viewProfile"));
    }
    /**
     * click on view profile button on myAccounts page
     */
    public void clickOnViewProfileLink()
    {
        Mapper.waitForElementToBeVisible(domFile,"viewProfile");
        Mapper.find(domFile,"viewProfile").click();
    }
    /**
     * validate profile page elements
     */
    public boolean validateUiOfProfilePageNAmeISPresent()
    {
        return(isElementPresent("profileNAme"));
    }
    public boolean validateUiOfProfilePageProfileTypeisPresent()
    {
        return(isElementPresent("profileType"));
    }
    public boolean validateUiOfProfilePageAdCountIsPresent()
    {
        return(isElementPresent("numberOfAdPostedByUSer"));
    }
    public boolean validateUiOfProfilePageActiveStatusIsPresent()
    {
        return(isElementPresent("activeSince"));
    }
    public boolean validateUiOfProfileLiveAdIsPresent()
    {
        return(isElementPresent("liveAdsCount"));
    }

    /**
     * click on logout button to logOut
     */
    public void logout()
    {
        Mapper.waitForElementToBeVisible(domFile,"logout",20);
        Mapper.find(domFile,"logout").click();
    }
    /**
     * validate logout
     */
    public boolean validateUserIsLoggedOut()
    {
        return (!isElementPresent("logout"));
    }

    public void clickOnLoginWithGoogle()
    {
        Mapper.waitForElementToBeVisible(domFile, "googleLogin");
        Mapper.find(domFile, "googleLogin").click();
    }

    public void selectGoogleAccount()
    {
        Mapper.waitForElementToBeVisible(domFile, "chooseGoogleAccount");
        Mapper.find(domFile, "chooseGoogleAccount").click();
    }
    /**
     * validate My Account Options
     */
    public boolean myAccountOptions()
    {
        Mapper.waitForElementToBeVisible(domFile, "myAccountMyAds", 10);
        return (Mapper.find(domFile,"myAccountMyAds").getText().equalsIgnoreCase("My Ads")&&Mapper.find(domFile,"MyChats").getText().equalsIgnoreCase("My Chats")&&Mapper.find(domFile,"myAlert").getText().equalsIgnoreCase("My Alerts")&&Mapper.find(domFile,"MyCArt").getText().equalsIgnoreCase("My Cart")&&Mapper.find(domFile,"myOrders").getText().equalsIgnoreCase("My Orders")&&Mapper.find(domFile,"Quikr Doorstep").getText().equalsIgnoreCase("Quikr Doorstep"));
    }
    /**
     * Tapping on My Ads, My Chats, My Alerts, My Cart, My Orders, My Offers should open appropriate screens
     */
    public void clicOnMyAccountOptions(String Element)
    {
        Mapper.waitForElementToBeVisible(domFile,Element,10);
        Mapper.find(domFile,Element).click();
    }
    public String getHeaderText()
    {
        Mapper.waitForElementToBeVisible(domFile, "textView");
        return (Mapper.find(domFile,"textView").getText());
    }

}
