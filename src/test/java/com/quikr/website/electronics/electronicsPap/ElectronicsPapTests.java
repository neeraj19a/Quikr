package com.quikr.website.electronics.electronicsPap;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 29/10/15.
 */
public class ElectronicsPapTests extends TestBase {

    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("ELECTRONICS_AND_APPLIANCES_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /*WEB-548:If user closes login window, then user should stay at PAP
   */
    //Commenting as Skip Login Feature is no more in support
    //@Test(groups = "horizontal")
    public void verifycloseloginwindowPAP() throws Exception {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("skiplogincookie"));
        homePage.clickPostFreeAdButton();
        headerPage.clickSignInPAP();
        headerPage.closeLoginPAP();
        Assert.assertEquals(electronicsPapPage.gettextPostAd(), testData.get("PostAdPageHeadertext"), "Failed to Load PAP Page");
    }


    /*
        WEB-546:On Forgot Password, user should redirect back to PAP
    */
    //Staging
    //@Test(dataProvider = "MandatoryCitiesLogin", dataProviderClass = Data.class,groups = "UATHorizontal")
    public void verifyPAPredirectionforgetPasswordMandatoryLogin(String City,String CityURL) {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //createorEditCookieValue(testData.get("cookiename"), testData.get("mandatorylogincookie"));
        waitForPageToLoad(testData.get("quikrURL"));
        headerPage.selectCity(City);
        waitForPageToLoad(CityURL);
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        headerPage.forgotPasswordPAP(testData.get("emailId"));
        Assert.assertEquals(electronicsPapPage.gettextPostAd(), testData.get("PostAdPageHeadertext"), "Failed to Load PAP Page");
    }

    /*
    WEB-549:If user click on Forgot Password, it will redirect to forgot password page and then back to PAP
*/
    //Commenting as Skip Login Feature is no more in support
    //@Test(groups = "horizontal")
    public void verifyPAPredirectionforgetPasswordSkipLogin() {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("skiplogincookie"));
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        headerPage.forgotPasswordPAP(testData.get("emailId"));
        Assert.assertEquals(electronicsPapPage.gettextPostAd(), testData.get("PostAdPageHeadertext"), "Failed to Load PAP Page");
    }

    /*WEB-550:If user login through FB on PAP, after login, it should redirect back to PAP
    */
    //Commenting as Skip Login Feature is no more in support
    //@Test(groups = "horizontal")
    public void verifyFBredirectionPAPSkipLogin() {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("skiplogincookie"));
        String parentWindowHandle = homePage.returnCurrentWindowHandle();
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        homePage.switchtoPopup(headerPage.clickFacebooklogin());
        headerPage.enterfacebookdetails(testData.get("facebookemail"), testData.get("facebookpassword"));
        headerPage.clickfacebookloginbutton();
        homePage.switchtoParentfromPopUp(parentWindowHandle);
        Assert.assertEquals(electronicsPapPage.gettextPostAd(), testData.get("PostAdPageHeadertext"), "Failed to Load PAP Page");
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        electronicsPapPage.switchtoPopup(headerPage.clickFacebooklogin());
        headerPage.enterfacebookdetails(testData.get("facebookemail"), testData.get("facebookpassword"));
        headerPage.clickfacebookloginbutton();
        headerPage.switchtoParentfromPopUp(parentWindowHandle);
        Assert.assertEquals(electronicsPapPage.gettextPostAd(), testData.get("PostAdPageHeadertext"), "Failed to Load PAP Page");
    }


    /*WEB-547:If user login through FB on PAP, after login, it will redirect back to PAP
    */
    //Commmenting as this test case is not supported for Nashik and Gandhinagar
    //@Test(dataProvider = "MandatoryCitiesLogin", dataProviderClass = Data.class,groups = "UATHorizontal")
    public void verifyFBredirectionPAPmandatoryLogin(String City, String CityURL) {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //createorEditCookieValue(testData.get("cookiename"), testData.get("mandatorylogincookie"));
        //homePage.clickonCityCloseButton();
        headerPage.selectCity(City);
        waitForPageToLoad(CityURL);
        String parentWindowHandle = headerPage.returnCurrentWindowHandle();
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        homePage.switchtoPopup(headerPage.clickFacebooklogin());
        headerPage.enterfacebookdetails(testData.get("facebookemail"), testData.get("facebookpassword"));
        headerPage.clickfacebookloginbutton();
        homePage.switchtoParentfromPopUp(parentWindowHandle);
        //Assert.assertTrue(waitForPageToLoad("nashik.quikr.com/post-classifieds"));
        Assert.assertEquals(electronicsPapPage.gettextPostAd(), testData.get("PostAdPageHeadertext"), "Failed to Load PAP Page");

    }

    /*WEB-545:On registration on PAP, user should redirect back to PAP
    */
    //Staging
    @Test(dataProvider = "MandatoryCitiesLogin", dataProviderClass = Data.class,groups = "UATHorizontal")
    public void verifyRegistration(String City,String CityURL) {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        headerPage.selectCity(City);
        String email = "auto" + getRandomString(9) + "@gmail.com";
        String password = getRandomString(8);
        String phone = "9" + getRandomInteger(9);
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        headerPage.clicksignup();
        headerPage.clickSignupLoginWindow();
        headerPage.enterNameSignupPAP("adil imroz");
        headerPage.enterEmailSignUpPAP(email);
        headerPage.enterPasswordSignUpPAP(password);
        headerPage.enterPhonenumberSignUpPAP(phone);
        headerPage.clickSignUpAgeCheckBox();
        headerPage.clickCreateAccount();
        Assert.assertEquals(headerPage.checkMobileAndVerificationPage(),true,"Registration process didn't go well. Please check.");
    }

    /*WEB-544:If user is coming from third party to PAP, and closes login window, then user will be redirected to Quikr HP
    */
    //Staging

    //@Test(dataProvider = "MandatoryCitiesLogin", dataProviderClass = Data.class,groups = "UATHorizontal")
    public void verifyThirdPartyRedirection(String City,String CityURL) {
        HomePage homePage=new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        navigatethirdparty(driver, testData.get("ThirdPartyURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("mandatorylogincookie"));
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("validURL"));
        homePage.searchCityByNameCityPopUpClosed(City);
        waitForPageToLoad(CityURL);
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(CityURL);
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        headerPage.closeLoginPAP();
        Assert.assertTrue(headerPage.verifyQuikrLogo(), "Failed to Load Home Page");
    }

    /*WEB-543:If user closes login window, then user will be redirected back to previous page
    */
    //Staging
    //@Test(dataProvider = "MandatoryCitiesLogin", dataProviderClass = Data.class,groups = "UATHorizontal")
    public void verifyRedirectionPreviousPage(String City,String CityURL) {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //createorEditCookieValue(testData.get("cookiename"), testData.get("mandatorylogincookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("mandatorylogincookie"));
        waitForPageToLoad(testData.get("quikrURL"));
        headerPage.selectCity(City);
        waitForPageToLoad(CityURL);
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        headerPage.closeLoginPAP();
        Assert.assertTrue(headerPage.verifyQuikrLogo(), "Failed to Load Home Page");
    }

    /*
    WEB-551:For abRand - 11-100: Existing PAP
    This test case isn't valid anymore.
    */
    @Test(groups = "horizontal")
    public void verifyExistingPAP() {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("existingPapCookie"));
        homePage.clickonCityCloseButton();
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        Assert.assertEquals(electronicsPapPage.gettextPostAd(), testData.get("PostAdPageHeadertext"), "Failed to Load PAP Page");
    }

    /*
    WEB-491:Edit ad:PAP (Vernac language related test)
     */
    @Test(groups = "horizontal")
    public void editAdInPapVernac() {
        /*HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        waitForPageToLoad(testData.get("quikrURL"));
        headerPage.selectCity(testData.get("LucknowCity"));
        waitForPageToLoad(testData.get("LucknowCityUrl"));
        headerPage.loginWithCity(username,password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("LucknowCityUrl"));
        homePage.clickLanguageLink();
        homePage.selectHindiLanguage();
        homePage.clickLanguageLink();
        homePage.selectHindiLanguage();
        waitForPageToLoad(testData.get("LucknowCityUrl"));
        Assert.assertTrue(homePage.checkKeyboardPresence(), "Virtual keyboard is not visible. Please check!");
        headerPage.clickOnQuikrLogo();
        Assert.assertTrue(homePage.checkLanguageTranslationHomepage(testData.get("HeaderTextHindi"), testData.get("CarsAndBikesHindi"), testData.get("JobsTextHindi")), "The translation not happening as expected. Please check!");
        homePage.clickLanguageLink();
        homePage.selectEnglishLanguage();
        waitForPageToLoad(testData.get("LucknowCityUrl"));
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        electronicsPapPage.clickLanguageLinkPap();
        homePage.selectHindiLanguage();
        waitForPageToLoad(testData.get("LucknowCityUrl"));
        Assert.assertTrue(electronicsPapPage.checkLanguageTranslationPap(testData.get("PostAdHeadingTextHindi"), testData.get("PostAdSubHeadingTextHindi"), testData.get("TitleForYourAdTextHindi")), "Check language translation in PAP.");
*/    }

    /*
    WEB-492:Edit ad:Edit Ad
     */
    @Test(groups = "horizontal")
    public void editAdVernac() {
//        HomePage homePage = new HomePage(driver);
//        HeaderPage headerPage = new HeaderPage(driver);
//        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);
//        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
//
//        headerPage.selectCity(testData.get("LucknowCity"));
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.loginWithCity(username,password);
//        headerPage.clickOnQuikrLogo();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        homePage.clickLanguageLink();
//        homePage.selectHindiLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        homePage.clickLanguageLink();
//        homePage.selectHindiLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertTrue(homePage.checkKeyboardPresence(), "Virtual keyboard is not visible. Please check!");
//        headerPage.clickOnQuikrLogo();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertTrue(homePage.checkLanguageTranslationHomepage(testData.get("HeaderTextHindi"), testData.get("CarsAndBikesHindi"), testData.get("JobsTextHindi"), testData.get("RecommendedForYouHindi")), "The translation not happening as expected. Please check!");
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        myQuikrPage.clickEditAdLink();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertTrue(electronicsPapPage.isSubmitAdEnabled(), "Update Ad Button is Enabled");
//        Assert.assertTrue(electronicsPapPage.ispostAdTitleEnabled(), "Update Title Text Box is Enabled");
//        Assert.assertTrue(electronicsPapPage.isAdDesceriptionEnabled(), "Update Title Text Box is Enabled");
//        electronicsPapPage.clickLanguageLinkPap();
//        homePage.selectTamilLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertTrue(electronicsPapPage.isSubmitAdEnabled(), "Update Ad Button is Enabled");
//        Assert.assertTrue(electronicsPapPage.ispostAdTitleEnabled(), "Update Title Text Box is Enabled");
//        Assert.assertTrue(electronicsPapPage.isAdDesceriptionEnabled(), "Update Description Text Box is Enabled");
//        electronicsPapPage.clickLanguageLinkPap();
//        homePage.selectTeluguLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertTrue(electronicsPapPage.isSubmitAdEnabled(), "Update Ad Button is Enabled");
//        Assert.assertTrue(electronicsPapPage.ispostAdTitleEnabled(), "Update Title Text Box is Enabled");
//        Assert.assertTrue(electronicsPapPage.isAdDesceriptionEnabled(), "Update Description Text Box is Enabled");
//        electronicsPapPage.clickLanguageLinkPap();
//        homePage.selectMalayalamLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertTrue(electronicsPapPage.isSubmitAdEnabled(), "Update Ad Button is Enabled");
//        Assert.assertTrue(electronicsPapPage.ispostAdTitleEnabled(), "Update Title Text Box is Enabled");
//        Assert.assertTrue(electronicsPapPage.isAdDesceriptionEnabled(), "Update Description Text Box is Enabled");
    }

    /*
    WEB-494:Edit Ad: Active Ads Management
     */
    @Test(groups = "horizontal")
    public void validateEditAdVernacToolTip() {
//        HomePage homePage = new HomePage(driver);
//        HeaderPage headerPage = new HeaderPage(driver);
//        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
//
//        headerPage.selectCity(testData.get("LucknowCity"));
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.loginWithCity(username,password);
//        headerPage.clickOnQuikrLogo();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        homePage.clickLanguageLink();
//        homePage.selectHindiLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        System.out.println("Tool Tip Text " + testData.get("EditAdToolTipText"));
//        Assert.assertEquals(getToolTipTextEditAd(myQuikrPage.EditAdlink()), testData.get("EditAdToolTipText"), "Tool Tip Message is not appearing on Editing Ad on changing language other than base base language");
//        homePage.clickLanguageLink();
//        homePage.selectTeluguLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertEquals(getToolTipTextEditAd(myQuikrPage.EditAdlink()), testData.get("EditAdToolTipText"), "Tool Tip Message is not appearing on Editing Ad on changing language other than base base language");
//        homePage.clickLanguageLink();
//        homePage.selectTamilLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertEquals(getToolTipTextEditAd(myQuikrPage.EditAdlink()), testData.get("EditAdToolTipText"), "Tool Tip Message is not appearing on Editing Ad on changing language other than base base language");
//        homePage.clickLanguageLink();
//        homePage.selectMalayalamLanguage();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        headerPage.clickMyAds();
//        waitForPageToLoad(testData.get("LucknowCityUrl"));
//        Assert.assertEquals(getToolTipTextEditAd(myQuikrPage.EditAdlink()), testData.get("EditAdToolTipText"),"Tool Tip Message is not appearing on Editing Ad on changing language other than base base language");
    }

}
