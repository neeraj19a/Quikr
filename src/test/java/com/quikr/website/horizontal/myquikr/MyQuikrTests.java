package com.quikr.website.horizontal.myquikr;

import com.quikr.website.TestBase;
import com.quikr.website.electronics.electronicsPap.ElectronicsPapPage;
import com.quikr.website.horizontal.header.HeaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by Manvendra Singh on 30/7/15.
 */
public class MyQuikrTests extends TestBase
{
    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("MYQUIKR_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /*
    WEB-57:Delete Reply
     */
    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal","dev"})
    public void validateDeleteReply()
    {
//        HeaderPage headerPage=new HeaderPage(driver);
//        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
//        HomePage homePage=new HomePage(driver);
//
//        homePage.clickonCityCloseButton();
//        headerPage.login(username,password);
//        waitForPageToLoad(testData.get("myQuikrUrl"));
//        myQuikrPage.clickOnRepliesToMyAd();
//        waitForPageToLoad(testData.get("repliesToAdUrl"));
//        myQuikrPage.deleteReply();
//        Assert.assertTrue(myQuikrPage.verifyDeleteReplySuccess(),"Reply Contents has not been deleted successfully");
    }

    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal"})
    public void validateActiveAd()
    {
        /*HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
        HomePage homePage=new HomePage(driver);

        homePage.clickonCityCloseButton();
        headerPage.login(username, password);
        waitForPageToLoad(testData.get("homeUrl"));
        myQuikrPage.clickActiveAdMngmnt();
        Assert.assertTrue(myQuikrPage.ActiveAd(),"Active Ad status is not reflected");*/
    }


    @Test(groups = "horizontal")
    public void validateForgotPassword()
    {
        HeaderPage headerPage=new HeaderPage(driver);

        waitForPageToLoad(testData.get("homeUrl"));
        Assert.assertTrue(headerPage.forgotPassword(testData.get("forgotPasswordEmail")));
    }

    //@Test(groups = "horizontal")
    public void validateUpdateProfileFromMyQuikrPage()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        headerPage.letsLogin("noCityRequired", "", username, password);
        waitForPageToLoad(testData.get("myQuikrUrl"));
        Assert.assertTrue(myQuikrPage.updateProfile());
    }

    //@Test(groups = "horizontal")
    public void validateUpdateProfileFromDropDown()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        headerPage.letsLogin("noCityRequired", "", username, password);
        headerPage.clickOnQuikrLogo();
        Assert.assertTrue(myQuikrPage.updateProfileFromDropDown());
    }


    @Title("Validates ad deletion")

    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal","dev"})
    public void validateDeleteAd()
    {
        /*HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
        HomePage homePage=new HomePage(driver);

        homePage.clickonCityCloseButton();
        headerPage.login(username, password);
        myQuikrPage.clickActiveAdMngmnt();
        Assert.assertTrue(myQuikrPage.DeleteAd(),"Issue in deleting Ad");*/
    }

    @Test(groups = "dev")
    public void validateRecommendedForMeAds()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        headerPage.letsLogin("noCityRequired", "", username, password);
        waitForPageToLoad(testData.get("myQuikrUrl"));
        Assert.assertTrue(myQuikrPage.ValidateRecommendedAds(), "No ads are being displayed");
    }

    /*
    WEB-47:Edit Ad
     */
    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal","dev"})
    public void validateEditAd()
    {
//        HeaderPage headerPage=new HeaderPage(driver);
//        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
//        JobsPAPPage jobsPAPPage = new JobsPAPPage(driver);
//        HomePage homePage=new HomePage(driver);
//
//        homePage.clickonCityCloseButton();
//        headerPage.login(username, password);
//        waitForPageToLoad(testData.get("myQuikrUrl"));
//        myQuikrPage.ActiveAd();
//        myQuikrPage.clickEditAdLink();
//        jobsPAPPage.postAdTitle(testData.get("editAdTitle"));
//        jobsPAPPage.clickPostAdButton();
//        waitForPageToLoad(testData.get("postAdSucceedUrl"));
//        Assert.assertTrue(jobsPAPPage.getPageSource().contains(testData.get("adUpdateSuccessMessage")),"Ad update is unsuccessful");
    }

    @Title("WEB-50:Convert free ad to premium ad (F2P)")

    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal","dev"})
        public void convertFreeAdToPremium()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        headerPage.letsLogin("randomPageLoginResponsive","",username, password);
        waitForPageToLoad(testData.get("myQuikrUrl"));
        myQuikrPage.ActiveAd();
        waitForPageToLoad(testData.get("myQuikrUrl"));
        myQuikrPage.clickConvertToPremiumLink();
        waitForPageToLoad(testData.get("upgradeAdToPremiumUrl"));
        electronicsPapPage.clickPostPremiumAdButton();
        waitForPageToLoad("redesignCitrus?");
        Assert.assertTrue(electronicsPapPage.validatePaymentPageavailable(), "Not able to Load Premium Ad payment Page");
    }

    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal"})
    public void deleteReplyToAd()
    {
//        HeaderPage headerPage=new HeaderPage(driver);
//        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
//        HomePage homePage=new HomePage(driver);
//
//        homePage.clickonCityCloseButton();
//        headerPage.login(username, password);
//        waitForPageToLoad(testData.get("myQuikrUrl"));
//        myQuikrPage.ActiveAd();
//        waitForPageToLoad(testData.get("activeAdUrl"));
//        myQuikrPage.clickOnRepliesToMyAd();
//        waitForPageToLoad(testData.get("repliesToAdUrl"));
//        myQuikrPage.deleteReply();
//        Assert.assertTrue(myQuikrPage.verifyDeleteReplySuccess(), "Not able to delete reply to ad in MyQuikr page");
    }

    @Title("WEB-468:Edit Alert, From Ads Matching my Alerts section.")
    // Recommend Section No More
    @Test(groups = "dev")
    public void editAlertsRecommendedSection()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        headerPage.letsLogin("",testData.get("city"),username,password);
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickOnQuikrLogo();
        myQuikrPage.navigateToRecommendedAdForMe();
        Assert.assertTrue(myQuikrPage.editAlertRecommendedSection(), "Probably the ad was not successfully edited. Please check!");
    }

    @Title("WEB-467:Unsubscribe Alert, From Ads Matching my Alerts section.")

    @Test(groups="dev")
    public void unsubscribeAlertsRecommendedSection()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("city"), username,password);
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickOnQuikrLogo();
        myQuikrPage.navigateToRecommendedAdForMe();
        Assert.assertTrue(myQuikrPage.unsubscribeAlertRecommendedSection(), "The unsubscribe didn't successfully happen or the confirmation message disappeared too fast. Please check!");
    }

    /*
     * It will fail if there are no expired ads for the logged in profile.
     */
    @Title("WEB-49:Repost Ad")

    @Test(groups = {"ProdHorizontal","horizontalPRI0","dev"})
    public void verifyAdRepost()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        headerPage.letsLogin("randomPageLoginResponsive", testData.get("city"), username, password);
        myQuikrPage.clickManageMyAds();
        Assert.assertTrue(myQuikrPage.repostExpiredAds(), "Either there were no expired ads or the ad was not reposted");
    }

    
    @Title("Edit Alert")

    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal","dev"})
    public void validateEditAlert()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("city"), username, password);
        waitForPageToLoad(testData.get("cityUrl"));
        //myQuikrPage.clickMyQuikrIcon();
        myQuikrPage.clickManagaeAlerts();
        myQuikrPage.clickEditAlert();
        myQuikrPage.clickSubmitAlerts();
        Assert.assertTrue(myQuikrPage.validateEditAlertConfirmation(), "Alert Edit didn't happen. Please check!");
    }

    @Title("WEB-54:Unsubscribe Alert")

    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal"})
    public void validateUnsubscribeAlert()
    {
        /*HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
        HomePage homePage = new HomePage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.loginWithCity(username, password);
        myQuikrPage.clickMyQuikrIcon();
        myQuikrPage.clickMyAlerts();
        myQuikrPage.clickUnsubscribeAlerts();
        Assert.assertTrue(waitForPageToLoad("alertunsubscribeSuccess"), "Alert was not unsubscribed. Please check!");
*/    }

    @Title("WEB-1224:Verify, Responsive header in user profile page")

    @Test
    public void verifyResponsiveHeadersUserProfilePage()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("city"), username, password);
        waitForPageToLoad(testData.get("cityUrl"));
        clickOnQuikrLogo();
        Assert.assertTrue(myQuikrPage.validateHeader(), "Header validation failed. Please check!");
    }
}
