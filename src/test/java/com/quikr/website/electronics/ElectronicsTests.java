package com.quikr.website.electronics;

import com.quikr.website.TestBase;
import com.quikr.website.electronics.electronicsHome.ElectronicsHome;
import com.quikr.website.electronics.electronicsPap.ElectronicsPapPage;
import com.quikr.website.electronics.electronicsSnb.ElectronicsSnbPage;
import com.quikr.website.electronics.electronicsVap.ElectronicsVapPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 29/9/15.
 */
public class ElectronicsTests extends TestBase {

    private HashMap<String,String> testData=getTestData(getProperties().get("ELECTRONICS_AND_APPLIANCES_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /*WEB-618:Reply and Chat: On Clicking Reply buuton on VAP Page User should should be shown message to download app Sepcific to Amritsar City
    */
    //Commenting as Amritsar Pop up not in Scope
    //@Test(groups = "UATHorizontal")
    public void verifydownloadAppPopUponReplyandChatVAP()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsHome electronicsHome =new ElectronicsHome(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        homePage.selectStateorUnionTerritories(testData.get("amritsarcity"));
        waitForPageToLoad(testData.get("amritsarcity").toLowerCase());
        homePage.selectElectronicsAndAppliances();
        electronicsHome.clickHomeKitchenAppliances();
        horizontalSnbPage.clickAdswithReplySNB();
        electronicsVapPage.clickOnReplyButton();
        Assert.assertTrue(electronicsVapPage.verifyDownloadAppPopUp(), "Failed to Load Pop up For APP Download");
    }

    /*WEB-618:Reply and Chat: On Clicking Reply button on SNB Page User should should be shown message to download app Sepcific to Amritsar City
    */
    //Commenting as Amritsar Pop up not in Scope
    //@Test(groups = "UATHorizontal")
    public void verifydownloadAppPopUponReplyandChatSNB()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsHome electronicsHome =new ElectronicsHome(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        homePage.selectStateorUnionTerritories(testData.get("amritsarcity"));
        waitForPageToLoad(testData.get("amritsarcity").toLowerCase());
        homePage.selectElectronicsAndAppliances();
        electronicsHome.clickHomeKitchenAppliances();
        horizontalSnbPage.clickReplybuttonOnAdswithReplySNB();
        Assert.assertTrue(horizontalSnbPage.downloadAppPopUp(), "Failed to Load Pop up For APP Download");
    }

    /*
    WEB-618:Reply and Chat: On Clicking Reply button for recommended Ads on VAP Page User should should be shown message to download app Sepcific to Amritsar City
    */
    //Commenting as Amritsar Pop up not in Scope
    //@Test(groups = "UATHorizontal")
    public void verifydownloadApponPopUpReplyandChatVapRecommended()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsHome electronicsHome =new ElectronicsHome(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);


        waitForPageToLoad(testData.get("validURL"));
        homePage.selectStateorUnionTerritories(testData.get("amritsarcity"));
        waitForPageToLoad(testData.get("amritsarcity").toLowerCase());
        homePage.selectElectronicsAndAppliances();
        electronicsHome.clickTV_DVD_Multimedia();
        horizontalSnbPage.clickAdswithReplySNB();
        Assert.assertTrue(electronicsVapPage.verifyRecommendedAdsPresence(), "Recommeded Ads are not getting displayed");
        electronicsVapPage.clickReplybuttonforRecommendedAds();
        Assert.assertTrue(electronicsVapPage.verifyDownloadAppPopUp(), "Failed to Load Pop up For APP Download");
    }

    /*WEB-619:User should only be alowed to post an Ad through mobile app
    */
    //Commenting as Amritsar Pop up not in Scope
    //@Test(groups = "UATHorizontal")
    public void verifyPostAdPopUpDownloadApp()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        homePage.selectStateorUnionTerritories(testData.get("amritsarcity"));
        waitForPageToLoad(testData.get("amritsarcity").toLowerCase());
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        Assert.assertTrue(electronicsPapPage.verifyDownloadAppPopUp(), "Failed to Load Pop up For APP Download");
    }

    /*WEB-620:User should not be allowed to enter more than 10 digits in mobile number field
    */
    //Commenting as Amritsar Pop up not in Scope
    //@Test(groups = "UATHorizontal")
    public void verifyInvalidMobNumberPostAdDownloadAppPopUp()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        homePage.selectStateorUnionTerritories(testData.get("amritsarcity"));
        waitForPageToLoad(testData.get("amritsarcity").toLowerCase());
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        Assert.assertTrue(electronicsPapPage.verifyDownloadAppPopUp(), "Failed to Load Pop up For APP Download");
        Assert.assertTrue(electronicsPapPage.validatInvalidNumberErrorMsgonAppPopUp("9" + getRandomInteger(10).toString()));
    }

    /*WEB-621:User should redirect to previous page on closing App-Only popup
*/
    //Commenting as Amritsar Pop up not in Scope
    //@Test(groups = "UATHorizontal")
    public void verifyPageRedirectionPostAdDownloadAppPopUpClose()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        homePage.selectStateorUnionTerritories(testData.get("amritsarcity"));
        waitForPageToLoad(testData.get("amritsarcity").toLowerCase());
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        Assert.assertTrue(electronicsPapPage.verifyDownloadAppPopUp(), "Failed to Load Pop up For APP Download");
        electronicsPapPage.closeDownloadAppPopUp();
        Assert.assertTrue(homePage.isCreateAlertWindowVisible(),"Failed to redirect to Previous Page");
    }


    @Title("WEB-845:Similar Ads in VAP should get translated to requested languages with templatization")

    //Under discussion phase, might be need to remove the case, So commenting below case
    //@Test(groups = "horizontal")
    public void validateElectronicsAndAppliancesSimilarAdsVernac()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage =new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        //homePage.SelectCity(testData.get("selectedCity"));
        waitForPageToLoad(testData.get("selectedCity"));
        //headerPage.loginWithCity(username,password);
        waitForPageToLoad(testData.get("validURL"));
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("validURL"));
        homePage.selectMobilesAndTabletsOptionFromHome();
        waitForPageToLoad("Mobiles-Tablets");
        horizontalSnbPage.SelectMobileCategory();
        horizontalSnbPage.clickOnAd(1);
        homePage.clickLanguageLink();
        homePage.selectHindiLanguage();
        Assert.assertTrue(electronicsVapPage.gettextSimilarAdsVapPage().equals(testData.get("SimilarAdsTitleinHindi")) , "Failed to Transalte Similar Ads in Hindi");
        homePage.clickLanguageLink();
        homePage.selectTamilLanguage();
        Assert.assertEquals(electronicsVapPage.gettextSimilarAdsVapPage(), testData.get("SimilarAdsTitleinTamil"), "Failed to Transalte Similar Ads in Tamil");
        homePage.clickLanguageLink();
        homePage.selectTeluguLanguage();
        Assert.assertEquals(electronicsVapPage.gettextSimilarAdsVapPage(), testData.get("SimilarAdsTitleinTelugu"), "Failed to Transalte Similar Ads in Telugu");
    }

    /*WEB-496:Post Ad:PAP
    */
    //@Test(groups = "horizontal")
    public void validatePostAdVernac(){
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage electronicsPapPage =new ElectronicsPapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        headerPage.letsLogin("", testData.get("selectedCity"),username,password);
        clickOnQuikrLogo();
        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("categoryurl"));
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        electronicsPapPage.clickLanguageLinkPap();
        homePage.selectHindiLanguage();
        Assert.assertTrue(electronicsPapPage.isVirtualKeyboardPapavailable(), "Failed to Load virtual Keyboard");
        electronicsPapPage.clickpostAdTitle();
        electronicsPapPage.setMobileKeywordinHindiPAPVernacKeyboard();
        electronicsPapPage.clickAdDesceription();
        electronicsPapPage.setMobileKeywordinHindiPAPVernacKeyboard();
        electronicsPapPage.clickLanguageLinkPap();
        homePage.selectEnglishLanguage();
        Assert.assertTrue(electronicsPapPage.istitleBlank(), "Title does not get blank on Changing Language from Hindi to English");
        Assert.assertTrue(electronicsPapPage.isAdDescriptionBlank(), "Ad Description does not get blank on Changing Language from Hindi to English");
        electronicsPapPage.clickLanguageLinkPap();
        homePage.selectTamilLanguage();
        Assert.assertTrue(electronicsPapPage.isVirtualKeyboardPapavailable(), "Failed to Load virtual Keyboard");
        electronicsPapPage.clickpostAdTitle();
        electronicsPapPage.setMobileKeywordRegionalLanguagePAPVernacKeyboard();
        electronicsPapPage.clickAdDesceription();
        electronicsPapPage.setMobileKeywordRegionalLanguagePAPVernacKeyboard();
        electronicsPapPage.clickLanguageLinkPap();
        homePage.selectEnglishLanguage();
        Assert.assertTrue(electronicsPapPage.istitleBlank(), "Title does not get blank on Changing Language from Tamil to English");
        Assert.assertTrue(electronicsPapPage.isAdDescriptionBlank(),"Ad Description does not get blank on Changing Language from Tamil to English");
        electronicsPapPage.clickLanguageLinkPap();
        homePage.selectTeluguLanguage();
        Assert.assertTrue(electronicsPapPage.isVirtualKeyboardPapavailable(), "Failed to Load virtual Keyboard");
        electronicsPapPage.clickpostAdTitle();
        electronicsPapPage.setMobileKeywordRegionalLanguagePAPVernacKeyboard();
        electronicsPapPage.clickAdDesceription();
        electronicsPapPage.setMobileKeywordRegionalLanguagePAPVernacKeyboard();
        electronicsPapPage.clickLanguageLinkPap();
        homePage.selectEnglishLanguage();
        Assert.assertTrue(electronicsPapPage.istitleBlank(), "Title does not get blank on Changing Language from Telugu to English");
        Assert.assertTrue(electronicsPapPage.isAdDescriptionBlank(),"Ad Description does not get blank on Changing Language from Telugu to English");

    }
}
