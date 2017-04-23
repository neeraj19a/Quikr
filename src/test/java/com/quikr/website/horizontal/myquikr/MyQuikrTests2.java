package com.quikr.website.horizontal.myquikr;

import com.quikr.website.TestBase;
import com.quikr.website.electronics.electronicsSnb.ElectronicsSnbPage;
import com.quikr.website.electronics.electronicsVap.ElectronicsVapPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.mail.YopMailPage;
import com.quikr.website.mobilesandtablets.Mobilespage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 8/2/16.
 */
public class MyQuikrTests2 extends TestBase
{
    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("MYQUIKR_TESTDATA_FILE"));

    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");
    String email=getRandomString(9)+"@yopmail.com";
    String passwordregistration = getRandomString(9);
    String number = "9" + getRandomInteger(9);
    String name= getRandomString(9);
    /*
    WEB-1096:Verify 'View Profile' button in VAP for non escrow cities
     */
    @Test(groups="horizontal")
    public void verifyViewProfileButtonVapNonEscrow()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);
        headerPage.letsLogin("", testData.get("NonEscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        //electronicsSnbPage.SelectMobileCategory();
        mobilespage.clickSeeAllUsedMobiles();
        //myQuikrPage.clickOptionOtherThanAllOverIndia();
        waitForPageToLoad(testData.get("homeUrl"));
        electronicsVapPage.clickFirstAd();
        Assert.assertTrue(myQuikrPage.checkViewProfileButtonVap(), "View profile button not present. Please check!");
    }

    /*
    WEB-1097:User should redirect to User Public Profile page upon clicking 'View Profile' button from VAP
     */
    @Test(groups="horizontal")
    public void verifyViewProfileButtonRedirection()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        Mobilespage mobilespage = new Mobilespage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("NonEscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        //electronicsSnbPage.SelectMobileCategory();
        //myQuikrPage.clickOptionOtherThanAllOverIndia();
        mobilespage.clickSeeAllUsedMobiles();
        waitForPageToLoad(testData.get("homeUrl"));
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickViewProfileButtonVap();
        Assert.assertTrue(waitForPageToLoad("userProfile"),"Didn't redirect to user profile page. Please check!");
    }

    /*
    WEB-1222:Account name should be clickable in VAP for escrow cities
     */
    @Test(groups="horizontal")
    public void verifyAccountNameClickableVapEscrowCities()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        mobilespage.clickSeeAllUsedMobiles();
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        Assert.assertTrue(waitForPageToLoad("userProfile"),"Didn't redirect to user profile page. Probably account name was not properly clicked!");
    }

    /*
    WEB-1223:User should redirect to User Public Profile page upon clicking 'Accout name' from VAP
     */
    @Test(groups="horizontal")
    public void verifyUserPublicProfileRedirection()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        mobilespage.clickSeeAllUsedMobiles();
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        Assert.assertTrue(waitForPageToLoad("userProfile"),"Didn't redirect to user profile page. Probably account name was not properly clicked!");
    }

    /*
    WEB-1225:Verify, Profile hedaer in User public profile page
     */
    @Test(groups="horizontal")
    public void verifyProfileHeaderInPublicProfile()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        mobilespage.clickSeeAllUsedMobiles();
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        Assert.assertTrue(myQuikrPage.verifyProfileName(), "Either profile name is not displayed or it is null. Please check!");
    }

    /*
    WEB-1275:User should redirect to VAP on clicking Ad Title/Image
     */
    @Test(groups="horizontal")
    public void vapRedirectionFromUserProfilePage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        mobilespage.clickSeeAllUsedMobiles();
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        myQuikrPage.clickFirstAdUserProfileList();
        Assert.assertTrue(waitForPageToLoad("AdId"),"Vap page didn't load. Please check!");
    }


    @Title("Verify Make an Offer popup in user profile page for Escrow cities")

    @Test(groups="horizontal")
    public void validateMakeAnOfferFromUserProfilePage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        mobilespage.clickSeeAllUsedMobiles();
        String adTitle = electronicsSnbPage.clickMakeAnOfferAdTitle();
        waitForPageToLoad(adTitle.split(" ")[0]);
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        Assert.assertTrue(myQuikrPage.makeAnOffer(testData.get("offerPrice"), testData.get("emailAddrs"), testData.get("PhnNum"), testData.get("EscrowCity")), "Probably the <Make an offer> button is not present in the profile page. Please check!");
        Assert.assertTrue(myQuikrPage.validateSuccessInMakeAnOffer(),"Make an offer was not successful. Please check!");
    }

    /*
    WEB-1282:'Buy Now' popup should be displayed for in user profile Escrow ads
     */
    @Test(groups="horizontal")
    public void validateBuyNowFromUserProfilePage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        mobilespage.clickSeeAllUsedMobiles();
        String adTitle = electronicsSnbPage.clickBuyNowAdTitle();
        waitForPageToLoad(adTitle.split(" ")[0]);
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        myQuikrPage.buyNow(testData.get("emailAddrs"), testData.get("PhnNum"), testData.get("EscrowCity"));
        Assert.assertTrue(myQuikrPage.validateSuccessInBuyNow(), "Buy now was not successful. Please check!");
    }


    @Title("WEB-1277:Ads should get listed according to the chickets in SnB on clicking chicklets from profile page")

    @Test(groups="horizontal")
    public void verifyChickLetsUserProfilePage()
    {
       /* HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        //homePage.SelectCity(testData.get("EscrowCity"));
        waitForPageToLoad(testData.get("homeUrl"));
        headerPage.login(username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        electronicsSnbPage.SelectMobileCategory();
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        String extractedChicklet = myQuikrPage.getChickletTextUserProfilePage();
        myQuikrPage.clickLocalityChickletUserProfile();
        waitForPageToLoad("quikr");
        Assert.assertTrue(myQuikrPage.validateProperChickletInSnb(extractedChicklet),"Chicklets didn't match. Please check!");
*/    }

    @Title("WEB-1271:Ad images/default images, Chicklets should be displayed for Ads in user profile page")

    @Test
    public void verifyAdImagesChickletsUserProfilePage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        mobilespage.clickSeeAllUsedMobiles();
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        Assert.assertTrue(myQuikrPage.checkChickletsUserProfilePage(), "Either user profile page doesn't have any ad to show or Chicklets are not visible. Please check!");
    }

    @Title("WEB-1273:Verify, Previous (<) & Next (>) buttons in user profile for pagination")

    @Test
    public void verifyPaginationButtonsUserProfilePage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);

        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        mobilespage.clickSeeAllUsedMobiles();
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        Assert.assertTrue(myQuikrPage.validatePagination(), "Pagination left and right buttons have some problem or there are not enough ads. Please check!");
    }


    @Title("WEB-1272:Verify, Pagination when Ad count is morethan 10 in user profile page")

    @Test
    public void verifyPaginationUserProfilePage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        headerPage.letsLogin("", testData.get("EscrowCity"), username, password);
        clickOnQuikrLogo();
        homePage.selectMobiles_ResponsiveHP();
        electronicsSnbPage.SelectMobileCategory();
        electronicsVapPage.clickFirstAd();
        myQuikrPage.clickUserProfileNameVAP_ResponsiveHp();
        Assert.assertTrue(myQuikrPage.validatePagination(),"Proper pagination didn't happen. Please check!");
    }

    @Title(" WEB-1354:Verify Name given by user while registration is reflecting on My account page")
    @Test
    public void verifyNameOnMyAccount(){

        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
        YopMailPage yopMailPage=new YopMailPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(headerPage.isRegisterbuttonWorking(name, email, passwordregistration, number), "Unable To Register User");
        headerPage.closeResponsiveLoginPopUp();
        Assert.assertFalse(headerPage.letsLogin("randomPageLoginResponsive", "", email, password), "User is able to Login without Verifying via Email");
        String windowhandle=headerPage.returnCurrentWindowHandle();
        navigatethirdparty(driver, "http://yopmail.com");
        waitForPageToLoad("yopmail.com");
        yopMailPage.logintoYopMailInbox(email.replace("@yopmail.com", ""));
        waitForPageToLoad("yopmail.com");
        yopMailPage.switchToIFrameMailContent();
        yopMailPage.clickVerifyEmail();
        navigatethirdparty(driver, "http://quikr.com");
        homePage.switchTo().window(windowhandle);
        Assert.assertTrue(headerPage.letsLogin("randomPageLoginResponsive", "", email, passwordregistration), "Failed To Login");
        Assert.assertTrue(myQuikrPage.isEmailVerified(), "Email has been verified but on My Quikr Page it does not show Email as Verified");
        String username=myQuikrPage.getUserNamefromLoggedInMenu();
        String name1=myQuikrPage.getUserNamefromMyQuikrResponsivePage();
        Assert.assertEquals(username,name1,"Name is not matching or visible on Responsive My Account Page, Pls check");
    }

    @Title("WEB-1355:Verify Mobile number get updated and marked as verified in My account")
    @Test
    public void verifyMobileNumber(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        /*waitForPageToLoad(testData.get("HomeURL"));
        //homePage.SelectCity(testData.get("responsiveHPCity"));
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        */
        Assert.assertTrue(headerPage.letsLogin("",(testData.get("responsiveHPCity")),email, passwordregistration), "Failed To Login");
        Assert.assertEquals(myQuikrPage.getMobileNumber(), number, "Mobile Number given by user at registration is not shown on My Quikr Respnsive Page");
    }

    @Title("WEB-1356:Verify user able to update email id in My Account")
    @Test
    public void verifyEmailMyAccount(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        /*waitForPageToLoad(testData.get("HomeURL"));
        //homePage.SelectCity(testData.get("responsiveHPCity"));
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        */
        Assert.assertTrue(headerPage.letsLogin("", (testData.get("responsiveHPCity")),email, passwordregistration), "Failed To Login");
        Assert.assertTrue(myQuikrPage.isEmailVerified(), "Failed to load Verify Email icon on My Quikr Resposive Page");
    }

}


