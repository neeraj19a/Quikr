package com.quikr.msite.mHorizontal;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mHorizontal.mAlert.MAlertPage;
import com.quikr.msite.mHorizontal.mHeader.MHeaderPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mHorizontal.mLogin.MLoginpage;
import com.quikr.msite.mHorizontal.mMyAccount.MMyAccountPage;
import com.quikr.msite.mHorizontal.mMyChatandReplies.MMyChatandReply;
import com.quikr.msite.mQuikrX.mQuikrListingPage.MQuikrXListingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 4/11/15.
 */
public class MHorizontalTests extends MTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("mHORIZONTAL_TESTDATA_FILE"));



/*
 MS-564:Verify user click in home page user should redirect on home page
  */
    @Test(groups = "horizontal")
    public void validateHomePageRedirect(){
        MHomePage mHomePage = new MHomePage(driver);
        MHeaderPage mHeaderPage=new MHeaderPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mHeaderPage.goToQuikrHome();
        Assert.assertTrue(mHomePage.isHomePagevalid(),"Failed to redirect to Home Page on Clicking Quikr HomePage Link from the Menu");
    }

    /*MS-565:Verify user click on MCR(My Chat and Replies) page user should redirect on MCR page
    */
    @Test(groups = "horizontal")
    public void validateMCRRedirect(){
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage=new MLoginpage(driver);
        MMyChatandReply mMyChatandReply=new MMyChatandReply(driver);
        MMyAccountPage mMyAccountPage=new MMyAccountPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickMyChatandRepliesMenuIcon();
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickmyChatAndRepliesLinkOnMyAccount();
        Assert.assertTrue(mMyChatandReply.isValidMyChatandRepliesPageavailble(), "Failed to Load My Chat and Reply Page");
    }

    /*MS-567:Verify user click on Create alert page user should redirect on Create alert page
        */
    @Test(groups = "horizontal")
    public void validateAlertPage(){
        MHomePage mHomePage = new MHomePage(driver);
        MAlertPage mAlertPage=new MAlertPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickCreateAlert();
        Assert.assertTrue(mAlertPage.isMyAlertPageavailable(), "Failed to Load Alert Page");
    }

    /*MS-568:Verify user is logged in create alert button is not present
    */
    //@Test(groups ="horizontal")
    public void validateCreateAlertLoggedIn(){
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage=new MLoginpage(driver);
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        Assert.assertFalse(mHomePage.isCreateAlertavailable(), "Create Alert Page is available");
    }

    /*MS-570:Verify User click on Quikr X user should redirect on Quikr X page
    */
    @Test(groups ="horizontal")
    public void validateQuikrX() {
        MHomePage mHomePage = new MHomePage(driver);
        MQuikrXListingPage mQuikrXListingPage=new MQuikrXListingPage(driver);


        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.selectCity(testData.get("selectedCity"));
        mHomePage.clickQuikrX();
        waitForPageToLoad(testData.get("validpage"));
        Assert.assertTrue(mQuikrXListingPage.isQuikrXPageavailable(),"Failed to Load Quikr X Page");

    }

}
