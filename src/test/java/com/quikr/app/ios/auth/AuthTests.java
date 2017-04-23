package com.quikr.app.ios.auth;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

//import org.junit.Test;

/**
 * Created by Manvendra Singh on 6/10/15.
 */
public class AuthTests extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_AUTH_TESTDATA_FILE"));

    /**
     * iOS-116:Signup
     */
    @Title("SignUp")
    @Test(groups = "horizontal")
    public void verifySignUp()
    {

        HomePage homePage=new HomePage(driver);
       // MenuPage menuPage= new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);

      //  homePage.selectDontAllowOption();
       // homePage.clickOnMenuIconButton();
        homePage.selectCityName();
        homePage.selectAccountButton();
       // menuPage.clickOnMyAccount();
        authPage.clickOnLoginOrSignUp();
        authPage.clickOnSignUpButton();
        authPage.setUserName(testData.get("userName"));
        authPage.clickOnEmailBox();
        authPage.setRegisterEmailId();
        authPage.setPassword(testData.get("psw"));
        authPage.clickOnReenterPasswordBox();
        authPage.reenterPassword(testData.get("psw"));
        authPage.clickOnMobileNoBox();
        authPage.setMobileNoInSignUp(testData.get("mobileNo"));
       // authPage.navigateBack();
        authPage.clickOnSelectCityOnAuthPage();
        authPage.selectCityName();
        authPage.selectRegisterButton();
        Assert.assertTrue(authPage.validateSignUp(),"some issue in sign up");
    }

    /**
     * iOS-115:Forgot password
     */
    @Title("Forgot password")
    @Test(groups = "horizontal")
    public void verifyForgotPassword()
    {

        HomePage homePage=new HomePage(driver);
        //MenuPage menuPage= new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);

//        homePage.selectDontAllowOption();
        //homePage.clickOnMenuIconButton();
       // menuPage.clickOnMyAccount();
        homePage.selectCityName();
        homePage.selectAccountButton();
        authPage.clickOnLoginOrSignUp();
        authPage.setEmailInLogin(testData.get("forgotPasswordEmail"));
        authPage.selectForgotPassword();
        authPage.selectOkButtonInPopUp();
        Assert.assertTrue(authPage.validateForgotPassword(),"forgot password can't be sent");

    }

    /**
     * Login test
     */
    @Title("Login test")
    @Test(groups = "horizontal")
    public void verifyLogin()
    {

        HomePage homePage=new HomePage(driver);
     //   MenuPage menuPage= new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);

  //      homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
//        homePage.clickOnMenuIconButton();
//        menuPage.clickOnMyAccount();

        homePage.selectAccountButton();
        authPage.clickOnLoginOrSignUp();
        authPage.setEmailInLogin(testData.get("email"));
        authPage.setLoginPassword(testData.get("password"));
        authPage.selectLoginButton();
       // authPage.clickOnOkButtonAfterLogin();
        Assert.assertTrue(authPage.validateSignUp(),"login screen can't display");
    }

    /**
     * H_020
     *
     * Check if the user is able to edit the account details
     */
    @Title("Check if the user is able to edit the account details after login")
    @Test(groups = "horizontal")
    public void verifyEditDetailsAfterLogin()
    {
        HomePage homePage=new HomePage(driver);
        AuthPage authPage=new AuthPage(driver);

        homePage.selectCityName();
        homePage.selectAccountButton();
        authPage.clickOnLoginOrSignUp();
        authPage.setEmailInLogin(testData.get("email"));
        authPage.setLoginPassword(testData.get("password"));
        authPage.selectLoginButton();
        authPage.clickOnOkButtonAfterLogin();
        Assert.assertTrue(authPage.validateSignUp(),"login screen can't display");
        authPage.clickOnEditButtonInLoginPage();
        authPage.setUserNameForEditInLoginPage(testData.get("userName"));
        authPage.clickOnCurrentSelectedCityInLoginScreen();
        authPage.selectSaveButton();
        Assert.assertTrue(authPage.validateMsgAfterEditDetailInLoginPage(),"Not able to edit the detail in login page or popup is not present");
    }
}
