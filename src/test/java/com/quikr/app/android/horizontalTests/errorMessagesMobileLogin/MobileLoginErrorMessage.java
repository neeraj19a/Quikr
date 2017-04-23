package com.quikr.app.android.horizontalTests.errorMessagesMobileLogin;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.dataProvider.Data;
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
 * Created by Swatantra singh on 1/4/16.
 */
public class MobileLoginErrorMessage extends AppAndroidTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_MOBILELOGINERRORMEAASGES_TESTDATA_FILE"));
    @Stories("LoginFlow")
    @Features("MobileLOgin")
    @Title("verify Error msg for forget password for Unregistered User")
    @Test
    public void verifyErrorMessageForForgotPassword()
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.clickOnForgotPasswordLink();
        String emailId= "Automation_" +(int)(10000000* Math.random()) + "@gmail.com";
        authPageMobileLogin.provideEmailOrNUmberToResetPassword(emailId);
        authPageMobileLogin.submitForgetPasswordCredentials();
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase("Account does not exist. Please register"), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());
        Assert.assertTrue(authPageMobileLogin.loginFlowCTAText().equalsIgnoreCase("Register"),"CTA Mismatch");
    }
    @Stories("LoginFlow")
    @Features("MobileLOgin")
    @Title("verify Error msg for Login with incorrect credentials")
    @Test
    public void verifyErrorMessageForLoginWithIncorrectCredentials()
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(testData.get("email"));
        authPageMobileLogin.enterLoginpassword(testData.get("wrongpassword"));
        authPageMobileLogin.submitLoginCredentials();
        Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase("Incorrect credentials. Try again or use Forgot password"), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());
        Assert.assertTrue(authPageMobileLogin.loginFlowCTAText().equalsIgnoreCase("OK"),"CTA Mismatch");
    }

    @Stories("LoginFlow")
    @Features("MobileLOgin")
    @Title("verify OTP verification screen is displayed while trying to Login with unregistered number")
    @Test
    public void verifyErrorMessageForLoginWithOtp() {
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.clickOnLoginThroughOtp();
        authPageMobileLogin.registerMobileNumber(testData.get("unRegisteredNumber"));
        authPageMobileLogin.clickonSendOtp();
        Assert.assertTrue(authPageMobileLogin.isOTPVerificationScreen(),"OTP Verification screen was not displayed");
        //Assert.assertTrue(authPageMobileLogin.errorMessageText().equalsIgnoreCase("Number not registered. Please register."), "wrong error message displayed =" + authPageMobileLogin.errorMessageText());
        //Assert.assertTrue(authPageMobileLogin.loginFlowCTAText().equalsIgnoreCase("Register"), "CTA Mismatch");
    }
    @Stories("LoginFlow")
    @Features("MobileLOgin")
    @Title("verify Error msg forNew user Registration with already Registered number")
    @Test
    public void userIsAbleToSignUp()
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
    @Stories("PostAdFlow")
    @Features("MobileLOgin")
    @Title("verify Error msg forPostAd with already Registered credentials")
    @Test(dataProvider = "MobileLoginUserCreationErrorMsgFlow",dataProviderClass = Data.class)
    public void validateErrorMsgForPostADFlow(String userCreation,String[]Data) {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.waitForPapToLoad();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.swipeToPostAd(cord[0] + 50, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadTitle"));
        postAdV2Page.hideKeyboard();
        //postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadDescription"));
        postAdV2Page.hideKeyboard();
        //postAdV2Page.navigateBack();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        switch (userCreation)
        //registeredMobile  RegisteriedEmailAndMobile
        {
            case "registeredEmail":
                WebElement postAdScreen = postAdV2Page.getPostAdScrollView();
                System.out.println(Data[0]);
                //postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, Data[0]);
                WebElement email = postAdV2Page.loopScrollToElementByName(postAdScreen, "enterEmail", true, 1000);
                email.sendKeys(Data[0]);
                postAdV2Page.hideKeyboard();
                //postAdV2Page.navigateBack();
                //postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, "8004487215");
                WebElement mobile = postAdV2Page.loopScrollToElementByName(postAdScreen, "enterMobileNumber", true, 1000);
                mobile.sendKeys("8004487215");
                postAdV2Page.postYourAd();
                String postAdCTA=authPageMobileLogin.validatePostAdErrorMsgCTA();
                Assert.assertTrue(postAdV2Page.errorMessageText().equalsIgnoreCase(testData.get("registeredEmailErrorMsg")), "wrong error message displayed =" + postAdV2Page.errorMessageText());
                Assert.assertTrue(postAdCTA.equals(testData.get("PostAdCta")));
                break;
            case"registeredMobile":
                postAdScreen = postAdV2Page.getPostAdScrollView();
                System.out.println(Data[0]);
                //postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, testData.get("email"));
                email = postAdV2Page.loopScrollToElementByName(postAdScreen, "enterEmail", true, 1000);
                email.sendKeys(testData.get("email"));
                //postAdV2Page.navigateBack();
                postAdV2Page.hideKeyboard();
                //postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, Data[0]);
                mobile = postAdV2Page.loopScrollToElementByName(postAdScreen,"enterMobileNumber",true,1000);
                mobile.sendKeys(Data[0]);
                postAdV2Page.postYourAd();
                String postAdCTA1=authPageMobileLogin.validatePostAdErrorMsgCTA();
                Assert.assertTrue(postAdV2Page.errorMessageText().equalsIgnoreCase(testData.get("registeredMobileErrorMsg")), "wrong error message displayed =" + postAdV2Page.errorMessageText());
                Assert.assertTrue(postAdCTA1.equals(testData.get("PostAdCta")));

                break;
        }
    }




}
