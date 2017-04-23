package com.quikr.website.monetization;

import com.quikr.website.TestBase;
import com.quikr.website.electronics.electronicsPap.ElectronicsPapPage;
import com.quikr.website.electronics.electronicsSnb.ElectronicsSnbPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.horizontal.myquikr.MyQuikrPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Adil on 26/8/15.
 */
public class MonetizationTests extends TestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("MONETIZATION_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");
    /**
     * WEB-326 : To verify Post Ad with Price when user is online
     */
    @Test
    public void VerifyPostAdUserOnline()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        Monetizationpage montPage = new Monetizationpage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);

        /*montPage.closeCitySelectionPopUp();
        headerPage.login(username, password);
        */
        headerPage.letsLogin("randomPageLoginNonResponsive", "",username, password);
        montPage.closeModalCityPopUp();
        electronicsPapPage.openPap();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subCategory"));
        //electronicsSnbPage.SelectMobileCategory();
        montPage.closeModalCityPopUp();
        String number=9+getRandomInteger(9);
        postAnAdMobiles(testData.get("adTitle"), "", "", testData.get("brandname"), testData.get("prodDescription"),testData.get("address"),testData.get("pincode"),number);
        montPage.closeModalCityPopUp();
        montPage.closeMobVerifyWindow();
        montPage.getAdIdMobileAd();
        String adId = montPage.getAdIdMobileAd();
        Assert.assertTrue(electronicsPapPage.CheckIfAdPosted(), "Probably ad not posted successfully. Please check!");
        headerPage.search(adId);
        montPage.closeModalCityPopUp();
        Assert.assertTrue(montPage.VerifyPostedAdWithSearch(testData.get("adTitle")), "Same posted ad not visible in Snb page. Please check!");
        montPage.closeModalCityPopUp();
        Assert.assertTrue(montPage.CheckCodOptions(), "Cod options not visible. Please check!");
    }

    /**
     * WEB-327 : To verify Post Ad with Price when user is online in Bangalore
     */
    @Test
    public void VerifyAdPostedUserOnlineCitySelected()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        Monetizationpage montPage = new Monetizationpage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.login(username, password);
        */
        headerPage.letsLogin("randomPageLoginNonResponsive", (testData.get("city")),username, password);
        electronicsPapPage.openPap();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subCategory"));
        //electronicsSnbPage.SelectMobileCategory();
        String number=9+getRandomInteger(9);
        postAnAdMobiles(testData.get("adTitle"), "", "", testData.get("brandname"), testData.get("prodDescription"), testData.get("address"), testData.get("pincode"),number);
        montPage.closeMobVerifyWindow();
        Assert.assertTrue(electronicsPapPage.CheckIfAdPosted(), "Probably ad not posted successfully. Please check!");
    }

    /**
     * WEB-328 : To verify Post Ad with Reserved Price < Listing Price when user is online
     */
    @Test
    public void VerifyAdPostedResPriceLessthanListPrice()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        Monetizationpage montPage = new Monetizationpage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.login(username, password);
        */
        headerPage.letsLogin("randomPageLoginNonResponsive", (testData.get("city")),username, password);
        electronicsPapPage.openPap();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsSnbPage.SelectMobileCategory();
        String number=9+getRandomInteger(9);
        postAnAdMobiles(testData.get("adTitle"), testData.get("adGreaterListPrice"), testData.get("adLowerReservePrice"), testData.get("brandname"), testData.get("prodDescription"),testData.get("address"),testData.get("pincode"),number);
        montPage.closeMobVerifyWindow();
        Assert.assertTrue(electronicsPapPage.CheckIfAdPosted(), "Probably ad not posted successfully. Please check!");
    }

    /**
     * WEB-606:Verify "Make an offer" on "Make an offer pop up window when user is offline
     */
    @Test(groups = "horizontal")
    public void VerfiyMakeAnOfferPopUpUserOffline()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        Monetizationpage montPage = new Monetizationpage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);

        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        homePage.selectMobiles_ResponsiveHP();
        waitForPageToLoad(testData.get("responsiveHPCityURL"));
        electronicsSnbPage.SelectMobileCategory();
        waitForPageToLoad(testData.get("responsiveHPCityURL"));
        montPage.ClickFirstMakeAnOffer();
        Assert.assertTrue(montPage.CheckMakeAnOfferPopUp(), "Make an offer pop up is not being displayed. Please check!");
    }

    /**
     * WEB-607: Verify "Make an offer" on "Make an offer pop up window when user is online
     */
    @Test(groups = "horizontal")
    public void VerfiyMakeAnOfferPopUpUserOnline()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        Monetizationpage montPage = new Monetizationpage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);


        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.loginWithCity(username, password);
        */
        homePage.clickonCityCloseButton();
        headerPage.letsLogin("randomPageLoginNonResponsive", "",username, password);
        headerPage.clickOnQuikrLogo();
        montPage.SelectsMobileAndTablets();
        electronicsSnbPage.SelectMobileCategory();
        Assert.assertTrue(montPage.ClickFirstMakeAnOffer(),"Not able to click on Make Offer Button, Pls Check");
        Assert.assertTrue(montPage.CheckMakeAnOfferPopUp(), "Make an offer pop up is not being displayed. Please check!");
    }

    /**
     * WEB-608:To verify with Reserved Price < Listing Price for Mumbai/Bangalore
     */
    @Test(groups="horizontal")
    public void VerifyAdPostingListPriceGreaterThanReservedprice()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);


        headerPage.letsLogin("", testData.get("city"),username, password);
        waitForPageToLoad(testData.get("cityUrl"));
        clickOnQuikrLogo();
        electronicsPapPage.openPap();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subCategory"));
        String number=9+getRandomInteger(9);
        postAnAdMobiles(testData.get("adTitle"), testData.get("adGreaterListPrice"), testData.get("adLowerReservePrice"), testData.get("brandname"), testData.get("prodDescription"), testData.get("address"), testData.get("pincode"),number);
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(electronicsPapPage.CheckIfAdPosted(), "Probably ad not posted successfully. Please check!");
    }

    /**
     * WEB-609: To verify with Reserved Price equal to the Listing Price for Mumbai/Bangalore
     */
    @Test(groups = "horizontal")
    public void VerifyAdPostingListPriceEqualReservedPrice()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        headerPage.letsLogin("", testData.get("city"),username, password);
        waitForPageToLoad(testData.get("cityUrl"));
        clickOnQuikrLogo();
        electronicsPapPage.openPap();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subCategory"));
        String number="9"+getRandomInteger(9);
        postAnAdMobiles(testData.get("adTitle"), testData.get("commonPrice"), testData.get("commonPrice"), testData.get("brandname"), testData.get("prodDescription"), testData.get("address"), testData.get("pincode"),number);
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertTrue(electronicsPapPage.CheckIfAdPosted(), "Probably ad not posted successfully. Please check!");
    }

    /**
     * WEB-623:To verify post premium ad
     */
    @Test(groups = {"ProdHorizontal","horizontalPRI0","UATHorizontal"})
    public void ConvertNormalAdToPremium()
    {
        /*HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);
        HomePage homePage=new HomePage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.loginWithCity(username, password);
        myQuikrPage.ActiveAd();
        myQuikrPage.clickConvertToPremiumLink();
        waitForPageToLoad(testData.get("upgradeAdToPremiumUrl"));
        Assert.assertTrue(electronicsPapPage.ispremiumAdRadioButtonavailable(), "Failed to Load Convert Premium Ad Page");
        electronicsPapPage.clickPostPremiumAdButton();
        waitForPageToLoad("redesignCitrus");
        Assert.assertTrue(electronicsPapPage.validatePaymentPageavailable(), "Not able to Load Premium Ad payment Page");
 */   }


    @Title("WEB-615:Verify external ads on Home screen , Snb , VAP and post ad page")
    @Test(groups = "UATHorizontal")
    public void VerifyExternalAds()
    {
        /*HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        Monetizationpage montPage = new Monetizationpage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.loginWithCity(username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("cityUrl"));
        montPage.SelectsMobileAndTablets();
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertTrue(montPage.checkExternalAdsMobileHomescreen(), "Probably the ads at mobile homescreen are not being displayed properly");
        electronicsSnbPage.SelectMobileCategory();
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertTrue(montPage.checkExternalAdsMobileSnb(), "Probably the ads are not being displayed properly in Snb  page. Please check!");
        waitForPageToLoad(testData.get("validURL"));
        montPage.ClickFirstMakeAnOffer();
        waitForPageToLoad(testData.get("validURL"));
        montPage.clickMakeAnOfferPopUpClose();
        waitForPageToLoad(testData.get("cityUrl"));
        electronicsSnbPage.OpenAd();
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertTrue(montPage.checkExternalAdsMobileVap(), "Probably the ad are not being displayed properly in Vap. Please check!");
*/    }

    /*
    WEB-614:To verify "Create alert" functionality
     */
    @Test(groups = "horizontal")
    public void CreateAlertforallCategories()
    {
        /*HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        Monetizationpage montPage = new Monetizationpage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.loginWithCity(username, password);
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("cityUrl"));
        scrollVerticallWithCords(0,1800);
        montPage.createAlertWithUserLoggedIn();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Alert not created since recommended-section page didn't load");
*/    }

    /*
    WEB-624:To verify edit premium ad
    Not complete Since the ads present are not eligible for editing. Need to figure out a work around.
     */
    //@Test(groups="horizontal")
    public void validateEditPremiumAd()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.loginWithCity(username, password);
        */
        headerPage.letsLogin("randomPageLoginNonResponsive", (testData.get("city")),username, password);
        myQuikrPage.clickPremiumAdManagement();
        myQuikrPage.clickEditPremiumAdsManagement();
    }
}
