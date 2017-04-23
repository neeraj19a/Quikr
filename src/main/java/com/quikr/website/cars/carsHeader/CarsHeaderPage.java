package com.quikr.website.cars.carsHeader;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Saurabh on 15/10/15.
 */

public class CarsHeaderPage extends CarsPageBase
{
    public CarsHeaderPage(RemoteWebDriver driver)
    {
        super(driver);
    }

    private static final String domFile = getProperties().get("CARS_HEADER_DOM_FILE");

    public void clickHamburger()
    {
        Mapper.waitForElementToBeVisible(domFile, "hamburger");
        Mapper.find(domFile,"hamburger").click();
    }

    public void clickQuikrHome()
    {
        Mapper.waitForElementToBeVisible(domFile, "quikrHome");
        Mapper.find(domFile,"quikrHome").click();
    }

    public void closeHamburger()
    {
        Mapper.waitForElementToBeVisible(domFile, "hamburgerClose");
        Mapper.find(domFile,"hamburgerClose").click();
    }

    public void clickMobiles_Tablets()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "mobiles_Tablets",10)==true) {
        Mapper.find(domFile, "mobiles_Tablets").click();
    }
    }

    public void clickElectronics_Appliances()
    {
        Mapper.waitForElementToBeVisible(domFile, "electronics_Appliances");
        Mapper.find(domFile,"electronics_Appliances").click();
    }
    public void clickRealEstate()
    {
        Mapper.waitForElementToBeVisible(domFile, "realEstate");
        Mapper.find(domFile,"realEstate").click();
    }

    public void clickHome_Lifestyle()
    {
        Mapper.waitForElementToBeVisible(domFile, "home_Lifestyle");
        Mapper.find(domFile,"home_Lifestyle").click();
    }

    public void clickJobs()
    {
        Mapper.waitForElementToBeVisible(domFile, "jobs");
        Mapper.find(domFile,"jobs").click();
    }

    public void clickServices()
    {
        Mapper.waitForElementToBeVisible(domFile, "services");
        Mapper.find(domFile,"services").click();
    }

    public void clickPets_Petcare()
    {
        Mapper.waitForElementToBeVisible(domFile, "pets_Petcare");
        Mapper.find(domFile,"pets_Petcare").click();
    }

    public void clickEducation_Learning()
    {
        Mapper.waitForElementToBeVisible(domFile, "education_Learning");
        Mapper.find(domFile,"education_Learning").click();
    }

    public void clickNewCars()
    {
        Mapper.waitForElementToBeVisible(domFile, "newCars");
        Mapper.find(domFile,"newCars").click();
    }

    public void clickCheckMSPHamburger()
    {
        Mapper.waitForElementToBeVisible(domFile, "checkMSPHamburger");
        Mapper.find(domFile,"checkMSPHamburger").click();
    }

    public void clickQuikrLogo()
    {
        Mapper.waitForElementToBeVisible(domFile, "quikrLogo");
        Mapper.find(domFile,"quikrLogo").click();
    }

    public void clickQuikrLogoOldUI()
    {
        Mapper.waitForElementToBeVisible(domFile, "quikrLogoOldUI");
        Mapper.find(domFile,"quikrLogoOldUI").click();
    }


    public void clickUserIcon()
    {
        Mapper.waitForElementToBeVisible(domFile, "userDropdown");
        WebElement element=Mapper.find(domFile,"userDropdown");
        if(element==null){
            element=Mapper.find(domFile,"userDropdownLoggedIn");
        }
        getElementintoView(element);
        element.click();
    }

    public void login()
    {
        Mapper.waitForElementToBeVisible(domFile, "login");
        Mapper.find(domFile, "login").click();
    }

    public void enterUsername(String username)
    {
        Mapper.waitForElementToBeVisible(domFile, "username");
        Mapper.find(domFile,"username").sendKeys(username);
    }

    public void enterPassword(String password)
    {
        Mapper.waitForElementToBeVisible(domFile, "password");
        Mapper.find(domFile,"password").sendKeys(password);
    }

    public void clickLoginButton()
    {
        Mapper.waitForElementToBeVisible(domFile, "loginButton");
        Mapper.find(domFile,"loginButton").click();
    }

    public void loginUser(String username, String password)
    {
        clickUserIcon();
        login();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void signup()
    {
        Mapper.waitForElementToBeVisible(domFile, "signup");
        Mapper.find(domFile, "signup").click();
    }

    public void selectUserType(String userType)
    {
        Mapper.waitForElementToBeVisible(domFile, "individual_Business", new String[] {userType});
        Mapper.findAndReplace(domFile, "individual_Business", new String[]{userType}).click();
    }

    public void enterEmailid(String emailid)
    {
        Mapper.waitForElementToBeVisible(domFile, "emailid");
        Mapper.find(domFile, "emailid").sendKeys(emailid);
    }

    public void enterPassWord(String password)
    {
        Mapper.waitForElementToBeVisible(domFile, "passWord");
        Mapper.find(domFile,"passWord").sendKeys(password);
    }

    public void enterConfirmPassWord(String password)
    {
        Mapper.waitForElementToBeVisible(domFile, "confirmpassWord");
        Mapper.find(domFile,"confirmpassWord").sendKeys(password);
    }

    public void enterPhone(String phone)
    {
        Mapper.waitForElementToBeVisible(domFile, "phone");
        Mapper.find(domFile, "phone").sendKeys(phone);
    }

    public void selectCity(String cityToSelect)
    {
        Mapper.waitForElementToBeVisible(domFile, "city");
        Mapper.find(domFile,"city").click();
        Mapper.scrollElementIntoView(Mapper.findAndReplace(domFile, "citySelect", new String[] {cityToSelect}));

        Mapper.waitForElementToBeVisible(domFile, "citySelect", new String[]{cityToSelect});
        Mapper.findAndReplace(domFile, "citySelect", new String[]{cityToSelect}).click();
    }

    public void clickConfirm()
    {
        Mapper.waitForElementToBeVisible(domFile, "confirmCheckBox");
        Mapper.find(domFile, "confirmCheckBox").click();
    }

    public void clickSignupButton()
    {
        Mapper.waitForElementToBeVisible(domFile, "signupButton");
        Mapper.find(domFile,"signupButton").click();
    }

    public void closeSignupConfirmationLayer()
    {
        Mapper.waitForElementToBeVisible(domFile, "signupCloseBtn");
        Mapper.find(domFile,"signupCloseBtn").click();
    }

    public void closeSignupConfirmationLayer(String name)
    {
        Mapper.waitForElementToBeVisible(domFile, "nameOnRegistrationPopUp");
        Mapper.find(domFile,"nameOnRegistrationPopUp").click();
        Mapper.find(domFile,"nameOnRegistrationPopUp").sendKeys(name);
    }

    public void signupUser(String name, String emailid, String password, String phone, String cityToSelect) {
        clickUserIcon();
        login();
        signup();
        closeSignupConfirmationLayer(name);
        enterEmailid(emailid);
        enterPassWord(password);
        enterConfirmPassWord(password);
        enterPhone(phone);
        clickConfirm();
        clickSignupButton();
    }


    public void clickForgotPassword()
    {
        Mapper.waitForElementToBeVisible(domFile, "forgotPassword");
        Mapper.find(domFile,"forgotPassword").click();
    }

    public String forgotPasswordValidate()
    {
        Mapper.waitForElementToBeVisible(domFile, "forgotPasswordValidate");
        return Mapper.find(domFile, "forgotPasswordValidate").getText();
    }

    public void clickMyProfile()
    {
        Mapper.waitForElementToBeVisible(domFile, "myProfile");
        Mapper.find(domFile,"myProfile").click();
    }

    public void clickMyAds()
    {
        Mapper.waitForElementToBeVisible(domFile, "myAds");
        Mapper.find(domFile,"myAds").click();
    }

    public void clickMyAlerts()
    {
        Mapper.waitForElementToBeVisible(domFile, "myAlerts");
        Mapper.find(domFile,"myAlerts").click();
    }

    public void clickMyChats()
    {
        Mapper.waitForElementToBeVisible(domFile, "myChats");
        Mapper.find(domFile,"myChats").click();
    }

    public void clickMySavedAds()
    {
        Mapper.waitForElementToBeClickable(domFile, "mySavedAds");
        Mapper.find(domFile,"mySavedAds").click();
    }

    public boolean myChatValidate()
    {
        Mapper.waitForElementToBeVisible(domFile, "myChatValidate");
        return Mapper.waitForElementToBeVisible(domFile, "myChatValidate");
    }

    public boolean isLoginRegisterButtonavailable(){
        if(Mapper.find(domFile,"loginRegisterButton").getText().trim().equals("Login/Register")){
            return true;
        }
        else
            return false;
    }
    public String myQuikrValidate()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "myQuikrPageHeading"))
            return Mapper.find(domFile, "myQuikrPageHeading").getText();

        else
            return "Something wrong with the page";
    }

    public String myQuikrValidate2()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "myAdsHeading"))
            return Mapper.find(domFile, "myAdsHeading").getText();
        else  if(Mapper.waitForElementToBeVisible(domFile,"myAdsHeadingOld"))
            return Mapper.find(domFile,"myAdsHeadingOld").getText();

        else
            return "Something wrong with the page";
    }

    public void signOut()
    {
        Mapper.waitForElementToBeVisible(domFile, "signOut");
        Mapper.find(domFile,"signOut").click();
    }

    public void signOutUser()
    {
        clickUserIcon();
        signOut();
    }

    public void clickPostAd()
    {
        Mapper.waitForElementToBeVisible(domFile, "postAd");
        Mapper.find(domFile,"postAd").click();
    }
}
