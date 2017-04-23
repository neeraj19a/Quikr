package com.quikr.app.android.alert;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra on 17/8/15.
 */
public class AlertAppAndroidTest extends AppAndroidTestBase
{


    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_ALERT_TESTDATA_FILE"));
    @Stories("Alert")
    @Title("User is able to Create Alert From MyAccount")
    @Test(groups = "horizontal")
    public void createAlertFromMainMenu()
    {
        MenuPage menuPage=new MenuPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        PapPage papPage=new PapPage(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        alertPage.clickOnCreateAlert();
        alertPage.hideKeyboard();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.CATEGORY_LOCATION);
        alertPage.selectelementWithoutScroll("Brand Name", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectElements(testData.get("brand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectelementWithoutScroll("Model", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectElements("Year of Registration", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectelementWithoutScroll("Fuel Type", QuikrAppEnums.Alert_Form);
        alertPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectelementWithoutScroll("Color", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.selectElements("  Individual", QuikrAppEnums.PostAd_RadioButton);
        papPage.clickonAdlocation();
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.alertEmail();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateAlert(),"User is not able to create Alert");

    }
    /**
     * Android Sanity:2235
     * Verify if new alerts can be created with single/multiple brands localities and models.
     */
    @Stories("Alert")
    @Title("User is able to create Alert with multiple values")
    @Test(groups = "horizontal")
    public void createAlertUsingMultipleValues()
    {
        MenuPage menuPage=new MenuPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        PapPage papPage=new PapPage(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        alertPage.clickOnCreateAlert();
        alertPage.hideKeyboard();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.CATEGORY_LOCATION);
        Integer []cordinates=alertPage.getCordinates();
        alertPage.selectelementWithoutScroll("Brand Name", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectelementWithoutScroll(testData.get("brand1"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectelementWithoutScroll("Fuel Type", QuikrAppEnums.Alert_Form);
        alertPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.verticalSwipeWithCordinates(cordinates[0], cordinates[1]);
        alertPage.selectelementWithoutScroll("Year of Registration", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.selectElements("  Individual", QuikrAppEnums.PostAd_RadioButton);
        alertPage.selectelementWithoutScroll("Color", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.clickonAdlocation();
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectelementWithoutScroll(testData.get("location2"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.alertEmail();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateAlert(),"User is not able to create Alert");
    }
    /**
     * Check with Single And Multiple Locality
     */
    @Stories("Alert")
    @Title("User is able to create Alert with Multiiple Locality")
    @Test
    public void createAlertWithMultipleLoaclity()
    {
        MenuPage menuPage=new MenuPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        PapPage papPage=new PapPage(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        alertPage.clickOnCreateAlert();
        alertPage.alertEmail();
        alertPage.hideKeyboard();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectConditionAsUsed();
        alertPage.selectelementWithoutScroll("Brand Name", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectelementWithoutScroll("Model", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectElements("Year of Registration", QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectelementWithoutScroll("Fuel Type", QuikrAppEnums.Alert_Form);
        alertPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.selectelementWithoutScroll("Color",QuikrAppEnums.Alert_Widget_Elements);
        alertPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.selectElements("  Individual", QuikrAppEnums.PostAd_RadioButton);
        papPage.clickonAdlocation();
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectelementWithoutScroll(testData.get("location2"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        alertPage.alertEmail();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateAlert(),"User is not able to create Alert with multiple loaclities");
    }
    // Able to delete alert from Alerts screen without login

    @Stories("Alert")
    @Title("User is  Able to delete alert from Alerts screen without login ")
    @Test(groups = "horizontal")
    public void validateDeleteAlert()
    {
        MenuPage menuPage = new MenuPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        alertPage.clickOnMenuIcon();
        alertPage.deleteAlert();
        alertPage.confirmDeleteAlert();
        Assert.assertTrue(alertPage.validateAlertDeletion(), "Unable to delete alert");
    }

    // Able to delete alert from Alerts screen with login
    @Stories("Alert")
    @Title("User is Able to delete alert from Alerts screen with login")
    @Test(groups = "horizontal")
    public void validateDeleteAlertWithLogin()
    {
        MenuPage menuPage = new MenuPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        alertPage.clickOnMenuIcon();
        alertPage.deleteAlert();
        alertPage.confirmDeleteAlert();
        Assert.assertTrue(alertPage.validateAlertDeletion(), "Unable to delete alert");
    }

    // Able to Edit an alert without login
    @Stories("Alert")
    @Title("User is  Able to Edit an alert without login")
    @Test(groups = "horizontal")
    public void verifyUserIsAbleToEditALert()
    {
        MenuPage menuPage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        PapPage papPage = new PapPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        authPage.selectMenuIcOnAlertPage();
        authPage.clickOnEditAlerts();
         alertPage.hideKeyBoardAfterClickingEditAlert();
        alertPage.scroll("Describe your need");
        String cat=alertPage.getAlertCategory();
        if (cat.equalsIgnoreCase("Cars & Bikes"))
        {
            alertPage.selectElements("Year of Registration", QuikrAppEnums.Alert_Widget_Elements);
            alertPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_ALERT);
        }
        String city = papPage.getSelectedCity();
        System.out.println("City:" + city + "$$$");
//        authPage.clicokOnLocation();
        papPage.clickonAdlocation();
        if(city.equals("Bangalore"))
            alertPage.selectLocality(testData.get("location"));
        else if(city.contains("Mumbai"))
            alertPage.selectLocality(testData.get("mumbaiLocality"));
        alertPage.selectCheckBox();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateEditAlert());
    }

    // Able to Edit an alert with login
    @Stories("Alert")
    @Title(" User is Able to Edit an alert with login")
    @Test(groups = "horizontal")
    public void verifyUserIsAbleToEditALertWithLogin()
    {
        MenuPage menuPage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        PapPage papPage = new PapPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        authPage.selectMenuIcOnAlertPage();
        authPage.clickOnEditAlerts();
        //alertPage.hideKeyboard();
        alertPage.scroll("Describe your need");
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("services"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("servicesSubCAt"), QuikrAppEnums.CATEGORY_LOCATION);
        String city = papPage.getSelectedCity();
        papPage.clickonAdlocation();
        if(city.equals("Bangalore"))
            alertPage.selectLocality(testData.get("location"));
        else if(city.contains("Mumbai"))
            alertPage.selectLocality(testData.get("mumbaiLocality"));
        alertPage.selectCheckBox();
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateEditAlert());
    }

    // Able to view all alerts of a logged in user from Alerts screen
    @Stories("Alert")
    @Title("Test to verify view all alerts of a logged in user from Alerts screen")
    @Test(groups = "horizontal")
    public void viewAlertsForALoggedInUser()
    {
        MenuPage menuPage = new MenuPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(alertPage.validateViewAllAlerts(), "Unable to view alerts in Alert screen");
    }



}
