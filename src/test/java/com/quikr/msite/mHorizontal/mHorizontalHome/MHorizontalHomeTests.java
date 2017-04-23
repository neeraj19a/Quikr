package com.quikr.msite.mHorizontal.mHorizontalHome;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mElectronics.mElectronicsSNB.MElectronicsSnBPage;
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
 * Created by quikr on 3/11/15.
 */
public class MHorizontalHomeTests extends MTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("mHORIZONTAL_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");


    /*MS-468:Verify Home page landing #FIXED
      */
    @Test(groups = "horizontal")
    public void validateHomePage(){
        MHomePage mHomePage=new MHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        Assert.assertTrue(mHomePage.isHomePagevalid(), "Failed to Load Home page");
    }


    /*MS-469:Verify Categories on Quikr Home page #FIXED
    */
    @Test(groups = "horizontal")
    public void validateCategories(){
        MHomePage mHomePage=new MHomePage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        Assert.assertTrue(mHomePage.numberofCategories(),"Failed to Load All categories on Home Page");
    }

    /*MS-470:Verify 'Show more Categories' is present #FIXED
 */
    @Test(groups = "horizontal")
    public void validateShowMoreCategory(){
        MHomePage mHomePage=new MHomePage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        Assert.assertTrue(mHomePage.isShowMoreCategoryAvailable(), "Failed to Load Show More Category on Home Page");
    }

    /*MS-471:Verify "My Chats and replies " option is present #FIXED
    */
    @Test(groups = "horizontal")
    public void validateMyChatsandreplies(){
        MHomePage mHomePage=new MHomePage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        Assert.assertTrue(mHomePage.isMyChatandRepliesMenuIconavilable(), "Failed to Load My Chats and replies on Home Page ");
    }

    /* MS-472:Verify "MSP " option is present*/ //#Fixed

    @Test(groups = "horizontal")
    public void validateMSP(){
        MHomePage mHomePage=new MHomePage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        Assert.assertTrue(mHomePage.isMSPavailable(), "Failed to Load MSP on Home Page");
    }

    /*MS-473:Verify "My Account " option is present #FIXED
    */
    @Test(groups = "horizontal")
    public void validateMyAccount(){
        MHomePage mHomePage=new MHomePage(driver);
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        mHomePage.clickMenu();
        mMyAccountPage.clickSigninSignupBtn();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        Assert.assertTrue(waitForPageToLoad("MyQuikr"), "Failed to Load My Page");
    }

    /*MS-474:Verify "Select City" option is present #FIXED
     */
    @Test(groups = "horizontal")
    public void validateSelectCity(){
        MHomePage mHomePage=new MHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        Assert.assertTrue(mHomePage.isSelectCityavailable(), "Failed to Load Select City on Home Page");
    }

    /* MS-475:Verify "Post free Ad" option is present #FIXED
     */
    @Test(groups = "horizontal")
    public void validatePostFreeAd(){
        MHomePage mHomePage=new MHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        Assert.assertTrue(mHomePage.isPostAdButtonavailable(), "Failed to Load Post Ad on Home Page");
    }

    /*MS-476:Verify "Search" option is present #FIXED
    */
    @Test(groups = "horizontal")
    public void validateSearch(){
        MHomePage mHomePage=new MHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectWebFromQuikrLems();
        Assert.assertTrue(mHomePage.isSearchavailable(), "Failed to Load Search on Home Page");
    }

    /* MS-594:Verify Home page in vernacular languages
 */
    @Test(groups = "horizontal")
    public void validatePostAdVernac() {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickLanguage(2);
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertEquals(mHomePage.postAdButtonText(), testData.get("postAdTextinHindi"), "Failed to Load Home Page in Hindi Vernac");
        mHomePage.clickLanguage(3);
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertEquals(mHomePage.postAdButtonText(), testData.get("postAdTextinTamil"), "Failed to Load Home Page in Tamil Vernac");
        mHomePage.clickLanguage(4);
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertEquals(mHomePage.postAdButtonText(), testData.get("postAdTextinTelugu"), "Failed to Load Home Page in Telugu Vernac");
        mHomePage.clickLanguage(5);
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertEquals(mHomePage.postAdButtonText(), testData.get("postAdTextinMalyalam"), "Failed to Load Home Page in Malyalam Vernac");
        mHomePage.clickLanguage(6);
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertEquals(mHomePage.postAdButtonText(), testData.get("postAdTextinKannada"), "Failed to Load Home Page in Kannada Vernac");
        mHomePage.clickLanguage(7);
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertEquals(mHomePage.postAdButtonText(), testData.get("postAdTextinMarathi"), "Failed to Load Home Page in Marathi Vernac");
        mHomePage.clickLanguage(8);
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertEquals(mHomePage.postAdButtonText(), testData.get("postAdTextinGujarati"), "Failed to Load Home Page in Gujarati Vernac");
    }

    /*
    MS-668:Verify Menu in Vernacular languages
     */
    @Test
    public void validateMenuVernacularLanguages()
    {
        MHomePage mHomePage=new MHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        Assert.assertTrue(mHomePage.checkCorrectLanguageTranslation("hindi"), "Language translation didn't happen properly.");
        Assert.assertTrue(mHomePage.checkCorrectLanguageTranslation("telgu"),"Language translation didn't happen properly.");
    }

    /*
    MS-671:My_ACC: Verify login flow in all vernacular language
     */
    @Test
    public void validateLoginFlowInVernacLang()
    {
        MHomePage mHomePage=new MHomePage(driver);
        MHeaderPage mHeaderPage = new MHeaderPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickHindiLink();
        mHomePage.clickMenu();
        mHomePage.clickMyAccount();
        mHeaderPage.login(username, password);
        Assert.assertTrue(mHomePage.verifySuccessfulLogin(),"Login was not successful. Please check!");
    }
}
