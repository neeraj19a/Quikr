package com.quikr.app.android.auth;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Swatantra singh on 16/3/16.
 */
public class AuthPageMobileLogin extends AppAndroidPageBase {
    public AuthPageMobileLogin(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    // const string
    private static final String domFile = getProperties().get("ANDROID_AUTH_DOM_FILE");



/**
 * *****************************************************************MOBILE LOGIN METHODS *************************************************************************
 */


    /**
     * procced to login /signUp
     */
    public void clickOnLogin()
    {
        Mapper.waitForElementToBeVisible(domFile,"mobileLogin");
        Mapper.find(domFile,"mobileLogin").click();

    }
    /**
     * enter login mobile number or email
     */

    public void enterLoginEmailNumber(String emailNumber)
    {

        Mapper.waitForElementToBeVisible(domFile,"LoginmobileNumberOrEmail");
        Mapper.find(domFile,"LoginmobileNumberOrEmail").sendKeys(emailNumber);
    }

    /**
     * enter login password
     */

    public void enterLoginpassword(String password)
    {

        Mapper.waitForElementToBeVisible(domFile,"loginPassword");
        Mapper.find(domFile,"loginPassword").sendKeys(password);
    }
    /**
     * click on login button
     */
    public void submitLoginCredentials()
    {
        Mapper.waitForElementToBeVisible(domFile,"performlogin");
        explicitWait(2);
        Mapper.find(domFile,"performlogin").click();
    }
    /**
     * validate my accounts page
     */
    public boolean validateInfoMyAccountsPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "profileImage", 10);
        return (isElementPresent("profileImage")&&isElementPresent("userNameOnProfilePage")&&isElementPresent("userEMailOnProfilePage")&&isElementPresent("userMobileOnProfilePage"));
    }

    /**
     * change name in edit profile
     */
    public void changeName(String  name)
    {
        Mapper.waitForElementToBeVisible(domFile,"registerNewUserNAMe");
        Mapper.find(domFile,"registerNewUserNAMe").sendKeys(name);
    }
    /**
     * register mobile number
     */
    public void registerMobileNumber(String phNo)
    {
        Mapper.waitForElementToBeVisible(domFile,"registerNewuserMobile");
        Mapper.find(domFile,"registerNewuserMobile").sendKeys(phNo);
    }
    /**
     * register email
     */
    public void registerEmailId()
    {
        String emailId= "swat_" +(int)(10000000* Math.random()) + "@gmail.com";
        Mapper.waitForElementToBeVisible(domFile, "registerNewUSerEmail");
        Mapper.find(domFile,"registerNewUSerEmail").sendKeys(emailId);

    }
    public void registrationPassword(String password)
    {

        if (!isElementPresent("registerNewUserPassword"))
            navigateBack();
        Mapper.find(domFile,"registerNewUserPassword").sendKeys(password);

    }

    /**
     * re enter password
     * @param repassword
     * @return
     */
    public void confirmRegistrationPassword(String repassword)
    {
        if(!isElementPresent("confirmNewUserPassword"))
            navigateBack();
        Mapper.find(domFile,"confirmNewUserPassword").sendKeys(repassword);

    }
    /**
     * enter register user
     */
    public void registerUser()
    {
        Mapper.scroll("registerUserButton");
        if(!isElementPresent("registerUserButton"))
            navigateBack();
        Mapper.find(domFile,"registerUserButton").click();
    }
    /**
     * register new user name
     */
    public void registerNewUserNAme(String name)
    {
        Mapper.waitForElementToBeVisible(domFile,"registerNewUserNAMe");
        Mapper.find(domFile,"registerNewUserNAMe").sendKeys(name);

    }
    /**
     * validate  user registration
     */
    public boolean validateUserRegistration()
    {
        return isElementPresent("resendOtp");
//        Mapper.waitForElementToBeVisible(domFile,"otpSentNumber");
//        String number =Mapper.find(domFile,"otpSentNumber").getText();
//        System.out.println(number);
//         return number;

    }
    /**
     * click on register
     */
    public void clickOnRegister()
    {
        Mapper.waitForElementToBeVisible(domFile,"registerMobileLogin");
        Mapper.find(domFile,"registerMobileLogin").click();
    }
    /**
     * validate login
     */
    public boolean validateUserIsLoggedIn()
    {
        return isElementPresent("userNameOnProfilePage");
    }
    /**
     * generate mobile number
     */
    public String number()
    {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        String phNo = 99393+randomNumbers;
        return phNo;
    }
    /**
     * click on view profile
     */
    public void clickOnViewProfile()
    {
        Mapper.waitForElementToBeVisible(domFile,"viewProfileMobileLogin",10);
        Mapper.find(domFile,"viewProfileMobileLogin").click();

    }
    /**
     * click on edit profile
     */
    public void clickOnEditProfileMobileLOgin()
    {
        Mapper.waitForElementToBeVisible(domFile,"editProfilMobileLogin");
        Mapper.find(domFile,"editProfilMobileLogin").click();

    }
    /**
     * click on add new mobile number under ediot profile
     */
    public void clickAddNewMobileNumberUnderEditProfile()
    {
        Mapper.waitForElementToBeVisible(domFile,"addMobileNumbeToProfile");
        Mapper.find(domFile,"addMobileNumbeToProfile").click();
    }

    /**
     * click on add new email under ediot profile
     */
    public void clickAddNewEmailUnderEditProfile()
    {
        Mapper.waitForElementToBeVisible(domFile,"addEmailToProfile");
        Mapper.find(domFile,"addEmailToProfile").click();
    }
    /**
     * validate edit image link on edit profile
     */
    public boolean checkEditImageLinkIsPresent()
    {
        return isElementPresent("editImage");
    }
    /**
     * save edited changes
     */
    public void saveEditedChanges()
    {

        Mapper.waitForElementToBeVisible(domFile,"saveEditedChanges");
        Mapper.find(domFile, "saveEditedChanges").click();
    }
    /**
     * logout from account
     */
    public void logout()
    {
        Mapper.waitForElementToBeVisible(domFile,"changePassword/LogOut");
        Mapper.finds(domFile,"changePassword/LogOut").get(1).click();
    }
    /**
     * change password of account
     */
    public void clickChangePassword()
    {
        Mapper.waitForElementToBeVisible(domFile,"changePassword/LogOut");
        Mapper.finds(domFile,"changePassword/LogOut").get(0).click();
    }
    /**
     * enter current password
     */
    public void enterCurrentPassword(String currentPassword)
    {
        Mapper.waitForElementToBeVisible(domFile,"currentPasswordMobileLogin");
        Mapper.find(domFile, "currentPasswordMobileLogin").sendKeys(currentPassword);
    }
    /**
     * enter new password
     */
    public void enterNewPassword(String newPassword)
    {
        Mapper.waitForElementToBeVisible(domFile,"NewPasswordMobileLogin");
        Mapper.find(domFile, "NewPasswordMobileLogin").sendKeys(newPassword);
    }
    /**
     * enter confirm password
     */
    public void enterConfirmPassword(String confirmPassword)
    {
        Mapper.waitForElementToBeVisible(domFile,"ConfirmPasswordMobileLogin");
        Mapper.find(domFile,"ConfirmPasswordMobileLogin").sendKeys(confirmPassword);
    }
    /**
     * change user's password
     */
    public void changePassword()
    {
        Mapper.waitForElementToBeVisible(domFile,"changePassword");
        Mapper.find(domFile,"changePassword").click();
    }
    /**
     * validate password is changed
     */
    public boolean validateChangePassword()
    {
        return isElementPresent("profileDetails");
    }
    /**
     *
     */
    public List userAdDetails()
    {
        List <WebElement>status=Mapper.finds(domFile,"heading");

        List<String> list = new ArrayList<String>();
        for (WebElement e : status) {
            list.add(e.getText().replaceAll("\\D", ""));  // Will Keep only digits, rest all will be removed
        }
        return list;

    }
    /**
     * click on forget password link to set password
     */
    public void clickOnForgotPasswordLink()
    {
        Mapper.waitForElementToBeVisible(domFile,"forgotPassword",10);
        Mapper.find(domFile,"forgotPassword").click();
    }
    /**
     * provide user email/number to reset password
     */
    public void provideEmailOrNUmberToResetPassword(String eamilNumber)
    {
        Mapper.waitForElementToBeVisible(domFile,"forgetPAsswoerdEmail/NUmber",10);
        Mapper.find(domFile,"forgetPAsswoerdEmail/NUmber").sendKeys(eamilNumber);

    }
    /**
     * submit credentials to reset password password
     */
    public void submitForgetPasswordCredentials()
    {
        Mapper.waitForElementToBeVisible(domFile,"submitLoginCredentialForgetPAassword",10);
        Mapper.find(domFile,"submitLoginCredentialForgetPAassword").click();
    }
    /**
     * validater success of forget password
     */
    public boolean validateForgotPassword(String email) {
        Mapper.waitForElementToBeVisible(domFile, "forgetPasswordSuccessMsg", 10);
        String msg = Mapper.find(domFile, "forgetPasswordSuccessMsg").getText();
        return (msg.contains("email"));
    }
    /**
     *
     */
    public boolean verifyMyAccountPageWithoutLogin()
    {
        return (isElementPresent("mobileLogin")&&isElementPresent("facebookButtonMobileLogin")&&isElementPresent("googleLoginMobiuleLogin")&&isElementPresent("HomeImageMobileLogin"));
    }
    public boolean verifySignUpFields()
    {
        if (!isElementPresent("confirmNewUserPassword"))
            navigateBack();
        return (isElementPresent("registerNewUserNAMe")&&isElementPresent("registerNewuserMobile")&&isElementPresent("registerNewUSerEmail")&&isElementPresent("registerNewUserPassword")&&isElementPresent("confirmNewUserPassword")&&isElementPresent("registerUserButton"));
    }
    /**
     * click on settings on myAccounts page
     */
    public void clickOnSettingsOnMyAcountPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"settingsOnProfilePAge");
        Mapper.find(domFile,"settingsOnProfilePAge").click();
    }
    /**
     * click on my accounts options
     */
    public void clickOnMyAccountOPtions(String[] options,int i)
    {
        switch (options[i])
        {
            case "Cart":
                Mapper.find(domFile,"viewCartFromProfilePAge").click();
                break;
            case "Orders":
                Mapper.find(domFile,"viewOrderFromProfilPage").click();
                break;
            case "Quikr Doorstep":
                Mapper.find(domFile,"viewoffersFromProfilePAge").click();
        }
    }
    public String getHeaderText()
    {
        explicitWait(10);
        Mapper.waitForElementToBeVisible(domFile, "textView");
        return (Mapper.find(domFile,"textView").getText());
    }
    /**
     * usingh rand function to create unique email id for every run
     * swatantra singh
     * @return
     */
    public String EmailId()
    {
        String emailId= "swat_" +(int)(10000000* Math.random()) + "@gmail.com";
        return emailId;
    }

    /**
     * get error msg text
     * @return
     */
    public String errorMessageText()
    {
        explicitWait(5);
        Mapper.waitForElementToBeVisible(domFile, "errorMessageMobileLogin",10);
        return (Mapper.find(domFile,"errorMessageMobileLogin").getText());
    }

    /**
     * Get error msg CTA TEXT
     * @return
     */
    public String loginFlowCTAText()
    {
        Mapper.waitForElementToBeVisible(domFile, "forgotPasswordErrorCTA");
        return (Mapper.find(domFile,"forgotPasswordErrorCTA").getText());
    }
    /**
     * select login through otp
     */
    public void clickOnLoginThroughOtp()
    {
        Mapper.waitForElementToBeVisible(domFile,"loginThroughOTP");
        Mapper.find(domFile,"loginThroughOTP").click();
    }
    /**
     * register mobile number
     */
    public void registerMobileNumberVerified()
    {
        Mapper.waitForElementToBeVisible(domFile,"registerNewuserMobile");
        Mapper.find(domFile,"registerNewuserMobile").sendKeys("9886709210");
    }
    /**
     * Get error msg CTA TEXT
     * @return
     */
    public String validatePostAdErrorMsgCTA()
    {
        Mapper.waitForElementToBeVisible(domFile, "AddVerifyEmailNumber",20);
        String CTA=Mapper.find(domFile, "AddVerifyEmailNumber").getText();

        return CTA;
    }
    /**'
     * click on send otp
     */
    public void clickonSendOtp()
    {
        Mapper.waitForElementToBeVisible(domFile,"sendOtp");
        Mapper.find(domFile,"sendOtp").click();
    }
    /**
     * registered email
     */
    public void registeredEmailId()
    {

        Mapper.waitForElementToBeVisible(domFile, "registerNewUSerEmail");
        Mapper.find(domFile,"registerNewUSerEmail").sendKeys("automation.quikr@gmail.com");

    }
    /**
     * get password error msg
     */
    public String passwordErrorMsg()
    {
        Mapper.waitForElementToBeVisible(domFile, "passwordError");
        return (Mapper.find(domFile,"passwordError").getText());
    }

    /**
     * get password error msg
     */
    public String confirmPasswordMissmatch()
    {
        Mapper.waitForElementToBeVisible(domFile,"confirmMisMAtch");
        return (Mapper.find(domFile,"confirmMisMAtch").getText());
    }
//    /**
//     * validate Password is masked
//     */
//    public String PasswordIsMAsked()
//    {
//        explicitWait(3);
//        System.out.println("assss" + executeScript("return arguments[0].attributes", Mapper.find(domFile,"loginPassword")));
//        return Mapper.find(domFile,"loginPassword").getAttribute("password");
//
//    }
    /**
     * ad new email or number to account
     */

    public void addNewEmailNumberToAccount(String Eamail)
    {
        Mapper.waitForElementToBeVisible(domFile,"AddNewEmailNumber");
        Mapper.find(domFile,"AddNewEmailNumber").sendKeys(Eamail);
    }
    /**
     * Add and verify numver Email
     */
    public void clickOnAddAndVerifyEmailNumber()
    {
        Mapper.waitForElementToBeVisible(domFile,"AddVerifyEmailNumber");
        Mapper.find(domFile,"AddVerifyEmailNumber").click();
    }
    /**
     * get msg after clicking add and verify email
     */
    public String getTextOfMessageAfterClickingAddAndVerifyEmailNumber()
    {
        Mapper.waitForElementToBeVisible(domFile, "userMsg", 10);
        return (Mapper.find(domFile, "userMsg").getText());
    }
    /**
     * verify logout option is visible for logged in user
     */
    public boolean logoutIsPresent()
    {
        return (Mapper.finds(domFile, "changePassword/LogOut").get(1).getText().equalsIgnoreCase("Logout"));
    }
    /**
     * verify user name is present in menu drawer for logged in user
     */
    public boolean validateUserNameISPresentInMenuDrawer()
    {
        return isElementPresent("userNameInSideMenu");
    }
/**
 *for logged in user Email and mobile field should be non editable.
 */

    public boolean forloggedInUserEmailAndMobileIsNonEditAble(String element)
    {
        explicitWait(2);
        Mapper.waitForElementToBeVisible(domFile, "postAdTextBox", 10);
        List<WebElement> elements = Mapper.finds(domFile,"postAdTextBox");
        List<String> list = new ArrayList<String>();
        for (WebElement e : elements) {
            list.add(e.getText());
        }
        int retval=0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().contains(element.toLowerCase().trim()) || equals(element.toLowerCase().trim())) {
                retval = i;
                System.out.println(retval);
                explicitWait(2);
//
            }

        }
        System.out.println(Mapper.finds(domFile, "postAdTextBox").get(retval).getAttribute("focusable"));

        return (Mapper.finds(domFile, "postAdTextBox").get(retval).getAttribute("focusable").equalsIgnoreCase("false"));

    }
    /**
     * verify adding new emamil
     */
    public boolean newEmailAddedSuccessfully()
    {
        return isElementPresent("resendEmail");
    }
    /**
     * validate action after clicking orders
     */
    public boolean validateActionAfterClickingOrders()
    {
        return (Mapper.find(domFile,"headerText").getText().equalsIgnoreCase("My Orders"));
    }
    /**
     * Verify the header of the User profile screen
     */
    public boolean verifyBackButtonOnUserProfilePage()
    {
        Mapper.waitForElementToBeVisible(domFile,"backButtonOnLogin");
        return (isElementPresent("backButtonOnLogin"));
    }
    /**
     * get user profile title text
     */
    public String getUserProfileTitle()
    {
        Mapper.waitForElementToBeVisible(domFile,"userProfileTitle");
        return (Mapper.find(domFile,"userProfileTitle").getText());
    }

    public boolean verifyOnLoginPage(){
        Mapper.waitForElementToBeVisible(domFile,"mobileLogin",5);
        return (isElementPresent("mobileLogin"));
    }
    /**
     * click on get more response
     */
    public void getMoreResponses()
    {
        Mapper.waitForElementToBeVisible(domFile, "getMoreResponse");
        Mapper.find(domFile, "getMoreResponse").click();
    }
    /**
     * click on delete ad
     */
    public void deleteAd()
    {
        Mapper.waitForElementToBeVisible(domFile, "deleteAd");
        Mapper.find(domFile, "deleteAd").click();
    }
    /**
     * clcik on move ad to top
     */
    public void moveAdToTop()
    {
        Mapper.waitForElementToBeVisible(domFile, "moveAdToTop");
        Mapper.find(domFile, "moveAdToTop").click();
    }
    /**
     * clcik on menu icon on Myads page
     */
    public void selectMenuIcOnMyAds()
    {
        Mapper.waitForElementToBeVisible(domFile, "MyAdsmenuIcon");
        Mapper.find(domFile, "MyAdsmenuIcon").click();
    }
    public void makeChatAvailability()
    {
        Mapper.waitForElementToBeVisible(domFile, "chatAvailabilityFromGetResponse");
        Mapper.find(domFile, "chatAvailabilityFromGetResponse").click();
    }

    public boolean verifyChatAvailabilityButtonTurnsGreen()
    {
        String attribute=Mapper.find(domFile,"chatAvailabilityFromGetResponse").getAttribute("clickable");
        System.out.println(attribute);
        return (attribute.equalsIgnoreCase("false"));
    }
    public void shareLocation()
    {
        Mapper.waitForElementToBeVisible(domFile, "shareLocationFromGetResponse");
        Mapper.find(domFile, "shareLocationFromGetResponse").click();
    }
    public boolean verifyShareLocationButtonTurnsGreen()
    {
        String attribute=Mapper.find(domFile,"chatAvailabilityFromGetResponse").getAttribute("clickable");
        System.out.println(attribute);
        return (attribute.equalsIgnoreCase("false"));
    }

    public void makeAdPremiumFromGetResponse()
    {
        Mapper.waitForElementToBeVisible(domFile, "makePremiumFromGEtResponse");
        Mapper.find(domFile, "makePremiumFromGEtResponse").click();
    }
    public String addNumberEmailHeaderText()
    {
        Mapper.waitForElementToBeClickable(domFile, "addemailNumber");
        return (Mapper.find(domFile,"addemailNumber").getText());
    }

    public WebElement getScrollableMyAccountArea()
    {
        WebElement view = Mapper.find(domFile,"myAccountPage");
        return view.findElement(By.className("android.widget.FrameLayout"));
    }

    public boolean isOTPVerificationScreen()
    {
        if (isElementPresent("enterOtpManualy"))
            return true;
        return false;
    }
    /**
     *Verify when user clicks on login through OTP entered email/mobile number is prefilling in login screen
     */
    public String prifilledLoginNumber()
    {
        Mapper.waitForElementToBeVisible(domFile,"registerNewuserMobile");
       return( Mapper.find(domFile,"registerNewuserMobile").getText());
    }

    /**
     *Verify when user clicks on forgot password entered email/mobile number is prefilling in forgot password screen
     * @return
     */
    public String prifilledForgotPAsswordNumber()
    {
        Mapper.waitForElementToBeVisible(domFile,"forgetPAsswoerdEmail/NUmber");
        return( Mapper.find(domFile,"forgetPAsswoerdEmail/NUmber").getText());
    }
}
