package com.quikr.msite.mHorizontal.mHorizontalMyAccount;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mCars.mCarsVAP.MCarsVap;
import com.quikr.msite.mElectronics.mElectronicsSNB.MElectronicsSnBPage;
import com.quikr.msite.mHorizontal.mAlert.MAlertPage;
import com.quikr.msite.mHorizontal.mHeader.MHeaderPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mHorizontal.mLogin.MLoginpage;
import com.quikr.msite.mHorizontal.mMyAccount.MMyAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 12/11/15.
 */
public class MMyAccountTests extends MTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("mHORIZONTAL_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /*
    MS-491:My Account page access #FIXED
     */
    @Test(groups = "horizontal")
    public void checkMyAccountPageAccess()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        Assert.assertTrue(mMyAccountPage.checkSuccessfulLogin(), "Couldn't login successfully. Please check!");

    }

    /*
    MS-492:My_ACC: Verify My Account page #FIXED
     */
    @Test(groups = "horizontal")
    public void validateMyAccountPage()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        Assert.assertTrue(mMyAccountPage.validateMyAccountPage(), "Account page is not populated properly. Please check!");
    }

    /*
    MS-553:My Profile: Verify 'My Profile' on My Accounts page #FIXED
     */
    @Test(groups = "horizontal")
    public void verifyMyProfile()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        Assert.assertTrue(mMyAccountPage.checkSuccessfulLogin(), "Probably my profile link is not present. Please check!");
    }

    /*
    MS-554:My Profile: Verify the contents of My Profile page  #FIXED
     */
    @Test(groups = "horizontal")
    public void verifyMyProfileContents()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickEditMyProfile();
        Assert.assertTrue(mMyAccountPage.validateMyProfilePage(testData.get("emailId"), testData.get("profileName"), testData.get("profilePhNum")), "My profile page is not populated properly. PLease check!");
    }


    /*
    MS-555:My Profile: Verify 'Edit & Update' functionality on My Profile page #FIXED
    */
    @Test(groups = "horizontal")
    public void validateEditUpdateMyProfile()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickEditMyProfile();
        Assert.assertTrue(waitForPageToLoad("MyQuikr"), "Edit and save of profile didn't happen properly. Please check!");
    }

    /*
    MS-521:Recommended Ads: Verify 'Recommended Ads' on My Accounts page
     */
    //@Test(groups = "horizontal")
    public void validateRecommendedAdPage()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        Assert.assertTrue(waitForPageToLoad("c=ads"), "Recommended Ads page was not loaded properly. Please check!");
    }

    /*
    MS-522:Recommended Ads: Verify the contents of Recommended Ads page
     */
    //@Test(groups = "horizontal")
    public void validateContentsRecommendedAds()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        Assert.assertTrue(mMyAccountPage.verifyRecommAdsPageContent(), "Contents of recommended ads page are not as expected. Please check!");
    }

    /*
    MS-533:Replies to my Ads: Verify 'Replies to my Ads' option on My Accounts page
     */
    //@Test(groups = "horizontal")
    public void validateRepliesToMyReply()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRepliesToMyAds();
        Assert.assertTrue(waitForPageToLoad("reply"),"It didn't navigate to replies to my ads page. Please check!");
    }

    /*
    MS-542:Replies to my Ads: Verify the contents of the Replies to my Ads page
     */
    //@Test(groups = "horizontal")
    public void validateContentRepliesToMyAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRepliesToMyAds();
        mMyAccountPage.clickOnAReplyToAnAd();
        Assert.assertTrue(waitForPageToLoad("chatMobileUser?from=replies_chat"),"The chat window didn't open. Please check!");
    }

    /*
    MS-529:Recommended Ads: Verify 'Manage' option on Recommended Ads page
     */
    //@Test(groups = "horizontal")
    public void validateManageRecommAds()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickManageRecommAds();
        Assert.assertTrue(waitForPageToLoad("MyQuikr?myfeed=true"),"Alerts page didn't load. Please check!");
    }

    /*
    MS-514:My Alerts: Verify 'My Alerts' on My Accounts page #FIXED
     */
    @Test(groups = "horizontal")
    public void validateMyAlertsPage()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickMenuFromAccountPage();
        mMyAccountPage.clickManageAlertsFromAccountPage();
        Assert.assertTrue(waitForPageToLoad("MyQuikr?myfeed="), "Alerts page didn't load. Please check!");
    }

    /*
    MS-515:My Alerts: Verify the contents of My Alerts page #FIXED
     */
    @Test(groups="horizontal")
    public void validateContentMyAlerts()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickMenuFromAccountPage();
        mMyAccountPage.clickManageAlertsFromAccountPage();
        Assert.assertTrue(mMyAccountPage.verifyMyAlertsPageContents(),"Alerts page doesn't have edit and unsubscribe buttons. Please check!");

    }

    /*
    MS-517:My Alerts: Verify 'Unsubscribe' functionality on My Alerts page #FIXED
     */
    @Test(groups = "horizontal")
    public void validateAlertsUnsubscribe()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickMenuFromAccountPage();
        mMyAccountPage.clickManageAlertsFromAccountPage();
        mMyAccountPage.clickAlertUnsubscribe();
        mMyAccountPage.clickMyNeedIsMet();
        mMyAccountPage.submitUnsubscribeAlert();
        Assert.assertTrue(waitForPageToLoad("MyQuikr?myfeed="), "Alerts page didn't load. Please check!");
    }


    /*
        MS-494:My_ACC: Verify 'My Ads' page #NOT FIXED
        Need to add few things.
    */
    @Test(groups = "horizontal")
        public void validateMyAdsPage()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickManageFreeAds();
        Assert.assertTrue(mMyAccountPage.checkMyAdsPageContents(), "Contents of my ads page are not displayed properly. Please check!");
    }


    /*
    MS-495:My_ACC: Verify VAP access from My Ads page #FIXED
     */
    @Test(groups = "horizontal")
    public void validateVapAccessFromAdsPage()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickManageFreeAds();
        mMyAccountPage.clickFirstViewAdLinkAdsPage();
        Assert.assertTrue(waitForPageToLoad("AdId"),"Vap page didn't load. Please check!");
    }

    /*
    MS-496:My_ACC: Verify the contents on VAP page (497 is a duplicate of 496.) #NOT FIXED< AD CAN BE FROM ANY VERTICAL
     */
    @Test(groups = "horizontal")
    public void validateVapPageContents()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MCarsVap mCarsVap = new MCarsVap(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickManageFreeAds();
        mMyAccountPage.clickFirstViewAdLinkAdsPage();
        Assert.assertTrue(waitForPageToLoad("AdId"),"Vap page didn't load. Please check!");
        Assert.assertTrue(mCarsVap.verifyVapContents(),"Vap page doesn't have proper contents. Please check!");
    }

    /*
    MS-498:My_ACC: Verify Edit Ad action from My Ads page #FIXED
     */
    @Test(groups = "horizontal")
    public void validateEditAdAdsPage()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickManageFreeAds();
        Assert.assertTrue(mMyAccountPage.verifyAdEditPage(), "Edit ad page not loaded. Please check!");
    }

    /*
    MS-499:My_ACC: Verify the contents of Edit Ad page #FIXED
     */
    @Test(groups = "horizontal")
    public void validateEditAdPageContent()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickManageFreeAds();
        mMyAccountPage.clickFirstEditLinkAdsPage();
        Assert.assertTrue(mMyAccountPage.verifyEditAdPageContent(),"Edit ad page contents are not proper. Please check!");
    }

    /*
    MS-501:My_ACC: Verify Edit Ad functionality #NOT FIXED
     */
    @Test(groups = "horizontal")
    public void validateAdEdit()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickManageFreeAds();
        mMyAccountPage.clickFirstEditLinkAdsPage();
        mMyAccountPage.clickTitleTextFieldEditAd();
        mMyAccountPage.clickUpdateAdEditAd();
        Assert.assertTrue(waitForPageToLoad("PostAd/?succeed"), "Edit ad didn't happen successfully. Please check!");
    }

    /*
    MS-500:My_ACC: Verify 'Load more Ad details' link on Edit Ad page #FIXED
     */
    @Test(groups = "horizontal")
    public void validateLoadMoreAdDetailEditAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);


        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickManageFreeAds();
        mMyAccountPage.clickFirstEditLinkAdsPage();
        mMyAccountPage.clickLoadMoreDetailsEditAd();
        Assert.assertTrue(mMyAccountPage.verifyLoadMoreAdDetailEditAd(),"Load more ad details link didn't work properly. Please check!");
    }

    /*
    MS-516:My Alerts: Verify 'Edit' functionality on My Alerts page #FIXED
     */
    @Test(groups = "horizontal")
    public void validateEditAlert()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickMenuFromAccountPage();
        mMyAccountPage.clickManageAlertsFromAccountPage();
        mMyAccountPage.clickEditAlert();
        mAlertPage.clickSubmit();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"),"Alert edit didn't happen successfully. Please check!");
    }

    /*
    MS-526:Recommended Ads: Verify create Alert functionality on Recommended Ads page
     */
    //@Test(groups = "horizontal")
    public void validateAlertCreate()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.createAlertCars(testData.get("alertCreationDesc"), testData.get("selectedCity"), testData.get("alertCreationCars"));
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }


    /*
    MS-672:My_ACC: Verify My Account page in all vernacular languages
     */
    //@Test
    public void verifyMyAccountPageInVernacularLang()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MHeaderPage mHeaderPage = new MHeaderPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickHindiLink();
        Assert.assertTrue(mHomePage.checkCorrectLanguageTranslation("hindi"), "The language translation did not happen. Please check!");
        mHomePage.clickMyAccount();
        mHeaderPage.login(username, password);
        Assert.assertTrue(mHomePage.verifySuccessfulLogin(),"Login was not successful. Please check!");
    }
}
