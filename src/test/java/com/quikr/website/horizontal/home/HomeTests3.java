package com.quikr.website.horizontal.home;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 10/2/16.
 */
public class HomeTests3 extends TestBase {

    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("HOME_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /*
    WEB-523:Able to see related labels for alert fileds in Create Free Alert box.
    Feature no longer present
     */
    //@Test(groups ="horizontal")
    public void verifyCreateAlertLabels()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage= new HeaderPage(driver);

        headerPage.letsLogin("", (testData.get("city")), username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad("quikr");
        Assert.assertTrue(homePage.verifyCreateAlertLabels(), "Alert label not being displayed properly");
    }

    /*
    WEB-523:Able to see related labels for alert fileds in Create Free Alert box, New UI Cities
    Feature no longer present
     */
    //@Test(groups ="horizontal")
    public void verifyCreateAlertLabelsNewUICities()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("responsiveHPCity"));
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.verifyCreateAlertLabelsNewUiCities(testData.get("responsiveHPCity")), "Alert label not being displayed properly");
    }

    /*
    WEB-709:Ads should be translated to requested languages with templatization in Recommended For You section
    */
    //Recommended Section No More
    @Test(groups = "dev")
    public void validateRecommendedAdsVernac()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("LucknowCity"));
        waitForPageToLoad(testData.get("LucknowCityUrl"));
        headerPage.letsLogin("", testData.get("LucknowCity"),username, password);
        headerPage.clickOnQuikrLogo();
        homePage.clickLanguageLink();
        homePage.selectHindiLanguage();
        Assert.assertTrue(homePage.gettextRecommenededAdsHomePage().contains(testData.get("RecommendedForYouHindi").substring(0, 12)), "Failed to Load Hindi Translation of the Recommended Ads on Home Page");
        homePage.clickLanguageLink();
        homePage.selectTamilLanguage();
        Assert.assertTrue(homePage.gettextRecommenededAdsHomePage().contains(testData.get("RecommendedForYouTamil").substring(0, 12)), "Failed to Load Tamil Translation of the Recommended Ads on Home Page");
        homePage.clickLanguageLink();
        homePage.selectTeluguLanguage();
        Assert.assertTrue(homePage.gettextRecommenededAdsHomePage().contains(testData.get("RecommendedForYouTelugu").substring(0, 12)), "Failed to Load Telugu Translation of the Recommended Ads on Home Page");
    }
    /*
    WEB-504:Six Trending Ads should be displayed when Recommended Ads are not present
    */
    @Test(groups="horizontal")
    public void verifyTrendingAds()
    {
        HomePage homePage = new HomePage(driver);
        
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(homePage.checkTrendingAds(), "Trending ads not visible. please check!");
    }

    /*WEB-1257:Verify, Social Media icons and App Store icons in Footer
    */
    @Test(groups="horizontal")
    public void verifyAppStoreinFooter()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.isAppDownloadLinkWorking(),"Not working pls check");
    }

    @Title("WEB-1257:Verify, Social Media icons and App Store icons in Footer")

    @Test(groups="horizontal")
    public void verifySocialMediaInFooter()
    {
        HomePage homePage = new HomePage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        //homePage.clickonCityCloseButton();
        Assert.assertTrue(homePage.isFacebookLinkworking(), "Facebook Link not working");
        Assert.assertTrue(homePage.isLinkedInLinkworking(), "Linked In Link not working");
        Assert.assertTrue(homePage.isTwitterLinkworking(), "Twitter Link not working");
        Assert.assertTrue(homePage.isYouTubeLinkworking(), "You Tube Link not working");
        Assert.assertTrue(homePage.isGooglePlusLinkworking(), "Google Plus Link not working");
    }

    /*WEB-1092:Verify, Hamburger Menu on header
    */
    @Test(groups="horizontal")
    public void verifyHamburgerMenu()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(homePage.isHamburgerMenuPresentResponsiveHP(), "Failed to Load Hamburger Menu");
//        Assert.assertTrue(homePage.validateHamburgerMenuResponsiveHP(), "Failed to Load Check MSP under Hamburger Menu");
//        Assert.assertFalse(homePage.closeHamburgerSectionResponsiveHP(), "Hamburger Menu not closing");
    }

    /*WEB-1256:'Post Free Ad' button should be present in Footer
    */
    @Test(groups="horizontal")
    public void verifyPostAdButtonFooterResponsiveHP()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        //homePage.skipLoginWindow();
        Assert.assertTrue(homePage.isPostAdbuttonpresentFooterResponsiveHP(), "Failed to Load Post Ad Button on Footer of Responsive HP");
    }

    /*
    WEB-1252:Validate mobile number field in 'Download the Quikr App' section
    */
    @Test(groups="horizontal")
    public void verifyMobileNumberQuikrAppDownloadResponsiveHP()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        scrollVerticallWithCords(300,800);
        Assert.assertTrue(homePage.validatemobileNumberDownloadQuikrAppResponsiveHP(""), "Failed to Load Alert Message for Mobile Number ");
        String number=getRandomInteger(9);
        scrollVerticallWithCords(300, 800);
        Assert.assertTrue(homePage.validatemobileNumberDownloadQuikrAppResponsiveHP(number), "Failed to Load Alert Message for Mobile Number ");
        String aplhanumeric=getRandomInteger(5)+"abc";
        scrollVerticallWithCords(300, 800);
        Assert.assertTrue(homePage.validatemobileNumberDownloadQuikrAppResponsiveHP(aplhanumeric), "Failed to Load Alert Message for Mobile Number ");
    }


    /*
    WEB-1258:Quikr Logo should be present in Footer
     */
    @Test
    public void validateQuikrLogoAtFooter()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("EscrowCity"));
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        Assert.assertTrue(homePage.checkQuikrLogoAtFooter(),"Quikr logo is absent Please check!");
    }

    /*
    WEB-1254:Verify, Popular cities list in Footer
     */
    @Test
    public void validatePopularCitiesAtFooter()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("EscrowCity"));
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        Assert.assertTrue(homePage.validatePopularCitiesAtFooter(),"Some city is mising from popular cities list at footer. Please check!");
    }

    /*
    WEB-1253:Verify, All static page's links in Footer
     */
    @Test
    public void verifyStaticLinksAtFooter()
    {

        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("EscrowCity"));
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        homePage.clickAboutUs();
        Assert.assertTrue(waitForPageToLoad("about"), "About us link not working.");
        navigateBack();
        homePage.clickContactUs();
        Assert.assertTrue(waitForPageToLoad("contact"), "COntact us link not working.");
        navigateBack();
        homePage.clickCareers();
        Assert.assertTrue(waitForPageToLoad("jobs"), "Careers link not working,.");
        navigateBack();
        homePage.clickQuikrVideos();
        Assert.assertTrue(waitForPageToLoad("video"), "Quikr videos link not working.");
        navigateBack();
        homePage.clickAdvertiseWithUs();
        Assert.assertTrue(waitForPageToLoad("adsales"), "Advertise with us link not working.");
        navigateBack();
        homePage.clickHelp();
        Assert.assertTrue(waitForPageToLoad("help"), "Help link not working.");
        navigateBack();
        homePage.clickPremiumAds();
        Assert.assertTrue(waitForPageToLoad("premium_ads"), "Premium ads link not working.");
        navigateBack();
        homePage.clickListingPolicy();
        Assert.assertTrue(waitForPageToLoad("listing-policy"), "Lisitng policy link not working.");
        navigateBack();
        homePage.clickTermsOfUse();
        Assert.assertTrue(waitForPageToLoad("termsandconditions"), "Terms and conditions link not working.");
        navigateBack();
        homePage.clickPrivacyPolicy();
        Assert.assertTrue(waitForPageToLoad("privacy-policy"), "Privacy policy link not working.");
        navigateBack();
        homePage.clickQuikrXPolicy();
        Assert.assertTrue(waitForPageToLoad("quikrX-policy"), "QuikrX policy link not working.");
        navigateBack();
        homePage.clickSitemap();
        Assert.assertTrue(waitForPageToLoad("sitemap"), "sitemap link not working.");
        navigateBack();
        homePage.clickNews();
        Assert.assertTrue(waitForPageToLoad("news"),"News link not working.");
        navigateBack();
    }

    /*
     WEB-1249:Able to see Alert section (I Want To Receive Alerts) below blogs section
     */
    @Test
    public void validateAlertSection()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("EscrowCity"));
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        Assert.assertTrue(homePage.validateCreateAlertSection(),"Please check create alert section.");
    }

    /*
    WEB-1232:User should redirect to 'Homes' home page on clicking 'Explore Homes' link
     */
    @Test
    public void validateRealEstateRedirection()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("EscrowCity"));
        homePage.clickOnExploreHomes();
        Assert.assertTrue(waitForPageToLoad("homes"),"Real estate didn't load, please check.");
    }

    @Title("WEB-1094:Verify, FAB icon (+) for Post Free Ad, Check MSP, Create Free Alert")

    @Test(groups="horizontal")
    public void verifyFabIconResponsiveHP()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        scrollVerticallWithCords(0, 900);
        scrollVerticallWithCords(0, 900);
        homePage.openFabIcon();
        Assert.assertTrue(homePage.isFabIconDisplayed(),"Fab Icon not working");
    }

    /*WEB-1088:Post Ad Page should open on clicking Post Free Ad button
    */
    @Test(groups="horizontal")
    public void verifyPostAdButtonHeaderResponsiveHP()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        refreshPage();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.isPostAdbuttonpresentHeaderResponsiveHP(), "Failed to Load Post Ad Button on Header of Responsive HP");
    }


    /* WEB-1250:Verify, 'Download The Quikr App' section is displayed below Alert section
  */
    @Test(groups="horizontal")
    public void verifyDownloadQuikrAppSection()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(homePage.isAppDownloadLinkWorking(),"App Download Link is not working");
    }


    /*  WEB-1071:Verify, Download The App link on responsive home page header
  */
    @Test(groups="horizontal")
    public void verifyDownloadAppLinkSection()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        Assert.assertTrue(homePage.validateDownloadAppTopLeftResponsiveHP(), "Android App Link present on Home Page Top is not working");
    }

    /*   WEB-1230:User should redirect to 'Cars' home page on clicking 'Explore Cars' link
    */
    @Test(groups="horizontal")
    public void verifyExploreCarsLink()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        Assert.assertTrue(homePage.isExploreOptionworking(1), "Explore Cars is not working");
    }


    /*    WEB-1237:User should redirect to 'Services' home page on clicking 'Explore Services' link
    */
    @Test(groups="horizontal")
    public void verifyServicesLink()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0,900);
        Assert.assertTrue(homePage.isExploreOptionworking(3), "Explore Services is not working");
    }

    /*WEB-1236:User should redirect to 'Jobs' home page on clicking 'Explore Jobs' link
    */
    @Test(groups="horizontal")
    public void verifyJobsLink()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        Assert.assertTrue(homePage.isExploreOptionworking(4), "Explore Jobs is not working");
    }

}
