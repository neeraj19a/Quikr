package com.quikr.app.ios.alerts;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.auth.AuthPage;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by Manvendra Singh on 12/10/15.
 */
public class AlertsTests extends AppiOSTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_ALERTS_TESTDATA_FILE"));

    /**
     * iOS-136:create Alert Without Login
     */
    @Title("create Alert Without Login")
    @Test(groups = "horizontal")
    public void verifyCreateAlerts()
    {
        HomePage homePage=new HomePage(driver);
        AlertsPage alertsPage=new AlertsPage(driver);
        PapPage papPage=new PapPage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
      //  homePage.clickOnAlertButton();
        homePage.selectAccountButton();
        homePage.clickOnAlertButton();
        Assert.assertTrue(alertsPage.validateAddAlertsCreateButton(),"add alert create button is not display");
        alertsPage.clickOnAddAlertsCreateButton();
        alertsPage.clickOnSelectCategory();
       // homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryValue"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.scroll(testData.get("brandName"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.selectBrandName(testData.get("brandNameOptionForMobilesCategory"));
        papPage.selectApplyButton();
//        papPage.clickOnModel();
//        papPage.selectModelOptionName();
//        papPage.selectApplyButton();
        alertsPage.clickOnLocalityOnAlerts();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        alertsPage.selectLocalityName();
        papPage.selectApplyButton();
        alertsPage.clickOnOptionalDetailsForMobileCategoryInCreateAlerts();
        papPage.clickOnModel();
        papPage.selectModelOptionName();
        papPage.selectApplyButton();
        papPage.clickOnOperatingSystem();
        papPage.selectOperatingOptionName();
        papPage.selectApplyButton();
        papPage.clickOnNoOfSims();
        papPage.selectNoOfSimsOption();
        papPage.selectApplyButton();
        alertsPage.selectCreateAlertButton();
        Assert.assertTrue(alertsPage.validateCreateAlert(),"alerts can't be created");
    }

    /**
     * test for create alerts without optional details
     */
    @Title("create alerts without optional details")
    @Test(groups = "horizontal")
    public void verifyCreateAlertsWithoutOptionalDetailsAndWithoutLogin()
    {
        HomePage homePage=new HomePage(driver);
        AlertsPage alertsPage=new AlertsPage(driver);
        PapPage papPage=new PapPage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectAccountButton();
        homePage.clickOnAlertButton();
        Assert.assertTrue(alertsPage.validateAddAlertsCreateButton(),"add alert create button is not display");
        alertsPage.clickOnAddAlertsCreateButton();
        alertsPage.clickOnSelectCategory();
       // homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryValue"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.scroll(testData.get("brandName"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.selectBrandName(testData.get("brandNameOptionForMobilesCategory"));
        papPage.selectApplyButton();
//        papPage.clickOnModel();
//        papPage.selectModelOptionName();
//        papPage.selectApplyButton();
        alertsPage.clickOnLocalityOnAlerts();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        alertsPage.selectLocalityName();
        papPage.selectApplyButton();
        alertsPage.selectCreateAlertButton();
        Assert.assertTrue(alertsPage.validateCreateAlert(),"alerts can't be created");
    }

    /**
     *  iOS-3:User can create new Alert
     */
    @Title("create alerts with login")
    @Test(groups = "horizontal")
    public void verifyCreateAlertWithLogin()
    {
        HomePage homePage=new HomePage(driver);
        AlertsPage alertsPage=new AlertsPage(driver);
        PapPage papPage=new PapPage(driver);
//        MenuPage menuPage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);

       // homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
       // homePage.clickOnMenuIconButton();
        homePage.selectAccountButton();
        //menuPage.clickOnMyAccount();
        authPage.clickOnLoginOrSignUp();
        authPage.setEmailInLogin(testData.get("email"));
        authPage.setLoginPassword(testData.get("password"));
        authPage.selectLoginButton();
        authPage.clickOnOkButtonAfterLogin();
        authPage.navigateBack();
        homePage.clickOnAlertButton();
        alertsPage.clickOnAddAlertsCreateButton();
        alertsPage.clickOnSelectCategory();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryValue"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.scroll(testData.get("brandName"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.selectBrandName(testData.get("brandNameOptionForMobilesCategory"));
        papPage.selectApplyButton();
//        papPage.clickOnModel();
//        papPage.selectModelOptionName();
//        papPage.selectApplyButton();
        alertsPage.clickOnLocalityOnAlerts();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        alertsPage.selectLocalityName();
        papPage.selectApplyButton();
        alertsPage.clickOnOptionalDetailsForMobileCategoryInCreateAlerts();
        papPage.clickOnModel();
        papPage.selectModelOptionName();
        papPage.selectApplyButton();
        papPage.clickOnOperatingSystem();
        papPage.selectOperatingOptionName();
        papPage.selectApplyButton();
        papPage.clickOnNoOfSims();
        papPage.selectNoOfSimsOption();
        papPage.selectApplyButton();
        alertsPage.selectCreateAlertButton();
        Assert.assertTrue(alertsPage.validateCreateAlert(),"alerts can't be created");
    }
}
