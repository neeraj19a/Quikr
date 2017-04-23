package com.quikr.msite.mJobs.mJobsPAP;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mHorizontal.mAlert.MAlertPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mHorizontal.mLogin.MLoginpage;
import com.quikr.msite.mHorizontal.mMyAccount.MMyAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 1/12/15.
 */
public class MJobsPAPTests extends MTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("mJOBS_TESTDATA_FILE"));

    /*
    MS-341:Half User, Post an ad for Offer ad Type
    Incomplete
     */
    @Test

    public void postAdOfferType()
    {
        MJobsPAP mJobsPAP = new MJobsPAP(driver);

        mJobsPAP.clickPostFreeAdBtn();
        mJobsPAP.clickNext();
        mJobsPAP.selectJobs();
        mJobsPAP.selectEmployer();
        mJobsPAP.selectJobRole(testData.get("RoleForPAP"));
        mJobsPAP.selectEducation(testData.get("EduForPAP"));
        mJobsPAP.selectExperience(testData.get("ExpForPAP"));
        mJobsPAP.selectCity(testData.get("CityForPAP"));
        mJobsPAP.selectLocality(testData.get("LocalityForPAP"));
        mJobsPAP.clickNext();
        mJobsPAP.inputAdTitle(testData.get("adTitlePap"));
        mJobsPAP.inputAdDesc(testData.get("adDescForPAP"));
        mJobsPAP.postAd();
        Assert.assertTrue(mJobsPAP.validateAdPostSuccess(), "Ad not posted successfully. Please check!");
    }

    /*
    MS-354:Full User, Post an ad for Offer ad Type
     */
    @Test
    public void postAdOfferTypeLoggedIn()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);
        MJobsPAP mJobsPAP = new MJobsPAP(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mHomePage.clickMenu();
        mMyAccountPage.navigateHomepageThroughMenu();
        mJobsPAP.clickPostFreeAdBtn();
        mJobsPAP.clickNext();
        mJobsPAP.selectJobs();
        mJobsPAP.selectEmployer();
        mJobsPAP.selectJobRole(testData.get("RoleForPAP"));
        mJobsPAP.selectEducation(testData.get("EduForPAP"));
        mJobsPAP.selectExperience(testData.get("ExpForPAP"));
        mJobsPAP.selectCity(testData.get("CityForPAP"));
        mJobsPAP.selectLocality(testData.get("LocalityForPAP"));
        mJobsPAP.clickNext();
        mJobsPAP.inputAdTitle(testData.get("adTitlePap"));
        mJobsPAP.inputAdDesc(testData.get("adDescForPAP"));
        mJobsPAP.postAd();
        Assert.assertTrue(mJobsPAP.validateAdPostSuccess(), "Ad not posted successfully. Please check!");
    }

    /*
    MS-356:Full User, Post an ad for Want ad Type
     */
    @Test
    public void postAdWantTypeLoggedIn()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);
        MJobsPAP mJobsPAP = new MJobsPAP(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mHomePage.clickMenu();
        mMyAccountPage.navigateHomepageThroughMenu();
        mJobsPAP.clickPostFreeAdBtn();
        mJobsPAP.clickNext();
        mJobsPAP.selectJobs();
        mJobsPAP.selectJobSeeker();
        mJobsPAP.selectJobRole(testData.get("RoleForPAP"));
        mJobsPAP.selectEducation(testData.get("EduForPAP"));
        mJobsPAP.selectExperience(testData.get("ExpForPAP"));
        mJobsPAP.selectCity(testData.get("CityForPAP"));
        mJobsPAP.selectLocality(testData.get("LocalityForPAP"));
        mJobsPAP.clickNext();
        mJobsPAP.inputAdTitle(testData.get("adTitlePap"));
        mJobsPAP.inputAdDesc(testData.get("adDescForPAP"));
        mJobsPAP.postAd();
        Assert.assertTrue(mJobsPAP.validateAdPostSuccess(), "Ad not posted successfully. Please check!");
    }

    /*
    MS-357:Half User, Post an ad for Want Ad Type
     */
    @Test
    public void postAdWantType()
    {
        MJobsPAP mJobsPAP = new MJobsPAP(driver);

        mJobsPAP.clickPostFreeAdBtn();
        mJobsPAP.clickNext();
        mJobsPAP.selectJobs();
        mJobsPAP.selectJobSeeker();
        mJobsPAP.selectJobRole(testData.get("RoleForPAP"));
        mJobsPAP.selectEducation(testData.get("EduForPAP"));
        mJobsPAP.selectExperience(testData.get("ExpForPAP"));
        mJobsPAP.selectCity(testData.get("CityForPAP"));
        mJobsPAP.selectLocality(testData.get("LocalityForPAP"));
        mJobsPAP.clickNext();
        mJobsPAP.inputAdTitle(testData.get("adTitlePap"));
        mJobsPAP.inputAdDesc(testData.get("adDescForPAP"));
        mJobsPAP.postAd();
        Assert.assertTrue(mJobsPAP.validateAdPostSuccess(), "Ad not posted successfully. Please check!");
    }
}
