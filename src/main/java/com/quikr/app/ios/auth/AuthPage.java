package com.quikr.app.ios.auth;

import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by Manvendra Singh on 6/10/15.
 */
public class AuthPage extends com.quikr.app.ios.AppiOSPageBase {
    public AuthPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_AUTH_DOM_FILE");


    /**
     * function to click on sign up button
     */
    public void clickOnSignUpButton()
    {
        Mapper.find(domFile,"signUpButton").click();
    }

    /**
     * function to set user name
     */
    public void setUserName(String str)
    {
        Mapper.waitForElementToBeVisible(domFile,"userName");
        Mapper.find(domFile,"userName").sendKeys(str);
    }

    /**
     * function to click on email box
     */
    public void clickOnEmailBox()
    {
        Mapper.find(domFile,"clickOnSignUpEmail").click();
    }
    /**
     * function to generate random email id every time  while sign up
     */
    public void  setRegisterEmailId()
    {
        String emailId= "monu_" +(int)(10000000* Math.random()) + "@gmail.com";
        Mapper.waitForElementToBeVisible(domFile, "signUpEmail");
        Mapper.find(domFile, "signUpEmail").sendKeys(emailId);
    }

    /**
     * function to set password
     */
    public void setPassword(String str)
    {
        Mapper.find(domFile,"password").sendKeys(str);
    }

    /**
     * function to click on reenter password box
     */
    public void clickOnReenterPasswordBox()
    {
        Mapper.find(domFile,"clickOnReenterPasswordBox").click();
    }

    /**
     * function to re-enter password
     */
    public void reenterPassword(String str)
    {
        Mapper.find(domFile,"reenterPassword").sendKeys(str);
    }

    /**
     * function to click on mobile no box
     */
    public void clickOnMobileNoBox()
    {
        Mapper.find(domFile,"clickOnMobileNoBox").click();
    }

    /**
     * function to set mobile no in sign up
     */
    public void setMobileNoInSignUp(String str)
    {
        Mapper.find(domFile,"mobileNo").sendKeys(str);
        Mapper.hideKeyboard();
    }

    /**
     * function to click on select city on auth page
     */
    public void clickOnSelectCityOnAuthPage()
    {
        Mapper.find(domFile,"citySelect").click();
    }

    /**
     * function select city name
     */
    public void selectCityName()
    {
        Mapper.find(domFile,"cityName").click();
    }

    /**
     * function to select register button
     */
    public void selectRegisterButton()
    {
        Mapper.finds(domFile,"registerButton").get(4).click();
        Mapper.waitForElementToBeInvisible(domFile,"registerButton");
    }

    /**
     * function to validate sign up
     */
    public boolean validateSignUp()
    {
        Mapper.waitForElementToBeVisible(domFile,"logOutButton");
        return isElementPresent("logOutButton");
    }

    /**
     * function to set email in login
     */
    public void setEmailInLogin(String str)
    {
        Mapper.find(domFile,"loginEmail").sendKeys(str);
    }

    /**
     * function to click on forgot password
     */
    public void selectForgotPassword()
    {
        Mapper.finds(domFile, "forgotPasswordButton").get(4).click();
    }

    /**
     * function to click on OK button in forgot password pop up
     */
    public void selectOkButtonInPopUp()
    {
        Mapper.waitForElementToBeVisible(domFile,"okButton");
        Mapper.find(domFile,"okButton").click();
        Mapper.waitForElementToBeInvisible(domFile,"okButton");
    }

    /**
     * function to validate forgot password
     */
    public boolean validateForgotPassword()
    {
        return isElementPresent("forgotPasswordMsg");
    }

    /**
     * function to set password in login
     */
    public void setLoginPassword(String str)
    {
        Mapper.find(domFile,"passwordLogin").sendKeys(str);
    }

    /**
     * function to click on login button
     */
    public void selectLoginButton()
    {
        Mapper.finds(domFile,"loginButton").get(4).click();
    }

    /**
     * function to click on ok button after login
     */
    public void clickOnOkButtonAfterLogin()
    {
        Mapper.waitForElementToBeVisible(domFile,"afterLoginOkButton");
        Mapper.find(domFile,"afterLoginOkButton").click();
    }

    /**
     * function to click on login or signUp button
     */
    public void clickOnLoginOrSignUp()
    {
        Mapper.waitForElementToBeClickable(domFile,"loginOrSignUpButton");
        Mapper.find(domFile,"loginOrSignUpButton").click();
    }

    /**
     * function to click on edit button in auth page
     */
    public void clickOnEditButtonInLoginPage()
    {
        Mapper.find(domFile,"editButton").click();
    }

    /**
     * function to set user name for edit in auth page
     */
    public void setUserNameForEditInLoginPage(String str)
    {
        Mapper.waitForElementToBeVisible(domFile,"userNameInLoginScreen");
        Mapper.find(domFile,"userNameInLoginScreen").clear();
        Mapper.find(domFile,"userNameInLoginScreen").sendKeys(str);
    }

    /**
     * function to select save button in login page
     */
    public void selectSaveButton()
    {
        Mapper.find(domFile,"saveButton").click();
    }

    /**
     * function to validate msg after edit details on login page
     */
    public boolean validateMsgAfterEditDetailInLoginPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgAfterEditDetailInLoginPage");
        return isElementPresent("msgAfterEditDetailInLoginPage");
    }

    /**
     * function to click on cuurent selected city in login page
     */
    public void clickOnCurrentSelectedCityInLoginScreen()
    {
        Mapper.find(domFile,"clickOnCurrentSelectedCityInLoginScreen").click();
    }
}
