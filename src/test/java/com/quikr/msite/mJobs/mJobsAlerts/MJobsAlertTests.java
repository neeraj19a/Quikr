package com.quikr.msite.mJobs.mJobsAlerts;

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
 * Created by quikr on 19/11/15.
 */
public class MJobsAlertTests extends MTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("mJOBS_TESTDATA_FILE"));

    /*
    MS-359:Create Alert for Full Time Jobs for Offer Ad Type
     */
    @Test
    public void createAlertFulltimeJobsOfferAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.createAlertJobs(testData.get("selectedCity"), testData.get("alertCreationCat"), testData.get("alertCreationSubCatFullTimeJob"), testData.get("alertCreationRole"), testData.get("alertCreationEdu"), testData.get("alertCreationExp"));
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-361:Create Alert for Full Time Jobs for Want Ad Type
     */
    @Test
    public void createAlertFulltimeJobsWantAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatFullTimeJob"));
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-362:Create Alert for Part Time Jobs for Offer Ad Type
     */
    @Test
    public void createAlertParttimeJobsOfferAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatPartTimeJob"));
        mAlertPage.selectEmployerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-363:Create Alert for Part Time Jobs for Want Ad Type
     */
    @Test
    public void createAlertParttimeJobsWantAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatPartTimeJob"));
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-364:Create Alert for Freelancers Jobs for Offer Ad Type
     */
    @Test
    public void createAlertFreelanceJobsOfferAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatFreelancers"));
        mAlertPage.selectEmployerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-365:Create Alert for Freelancers Jobs for Want Ad Type
     */
    @Test
    public void createAlertFreelanceJobsWantAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatFreelancers"));
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-366:Create Alert for Work From Home Jobs for Offer Ad Type
     */
    @Test
    public void createAlertWorkFromHomeOfferAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatWFHJobs"));
        mAlertPage.selectEmployerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-367:Create Alert for Work From Home Jobs for Want Ad Type
     */
    @Test
    public void createAlertWorkFromHomeWantAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatWFHJobs"));
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-368:Create Alert for Non- Profit NGO's Jobs for Offer Ad Type
     */
    @Test
    public void createAlertNGOOfferAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatNGOJobs"));
        mAlertPage.selectEmployerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-369:Create Alert for Non- Profit NGO's Jobs for Want Ad Type
     */
    @Test
    public void createAlertNGOOfferWantAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatNGOJobs"));
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-370:Create Alert for Placement - Recruitment Agencies Jobs for Want Ad Type
     */
    @Test
    public void createAlertPlacementWantAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatPlacements"));
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-371:Create Alert for Placement - Recruitment Agencies Jobs for Offer Ad Type
     */
    @Test
    public void createAlertPlacementOfferAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatPlacements"));
        mAlertPage.selectEmployerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-372:Create Alert for Summer Trainees - Interns Jobs for Offer Ad Type
     */
    @Test
    public void createAlertSummerTraineeOfferAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatInterns"));
        mAlertPage.selectEmployerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-373:Create Alert for Summer Trainees - Interns Jobs for Want Ad Type
     */
    @Test
    public void createAlertSummerTraineeWantAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatInterns"));
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-374:Create Alert for Others Jobs for Offer Ad Type
     */
    @Test
    public void createAlertOtherJobsOfferAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatOtherJobs"));
        mAlertPage.selectEmployerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-375:Create Alert for Others Jobs for Want Ad Type
     */
    @Test
    public void createAlertOtherJobsWantAd()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickRecommendedAds();
        mMyAccountPage.clickAddAlertRecommAds();
        mAlertPage.selectCityAlertCreation(testData.get("selectedCity"));
        mAlertPage.selectCat(testData.get("alertCreationCat"));
        mAlertPage.selectSubCatAlertCreationJobs(testData.get("alertCreationSubCatOtherJobs"));
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectJobRoleAlertCreation(testData.get("alertCreationRole"));
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.clickSubmitAlertCreation();
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-392:Offer type, Edit alert created on home page.
     */
    @Test
    public void editAlertOfferType()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickMyAlerts();
        mAlertPage.clickEditAlert();
        mAlertPage.selectEmployerAlertCreation();
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }

    /*
    MS-395:Want type, Edit alert created on home page.
     */
    @Test
    public void editAlertWantType()
    {
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MHomePage mHomePage = new MHomePage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MAlertPage mAlertPage = new MAlertPage(driver);

        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        Assert.assertTrue(mLoginpage.checkLoginPage(), "Login page not loaded. Please check!");
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickMyAlerts();
        mAlertPage.clickEditAlert();
        mAlertPage.selectJobSeekerAlertCreation();
        mAlertPage.selectLocalityAlertCreation();
        mAlertPage.selectEducationAlertCreation(testData.get("alertCreationEdu"));
        mAlertPage.selectExpAlertCreation(testData.get("alertCreationExp"));
        Assert.assertTrue(waitForPageToLoad("livefeed=true"), "Recommended ads page was not loaded. Please check!");
    }



}
