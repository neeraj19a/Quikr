package com.quikr.website.jobs.jobsVAP;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.jobs.jobsHeader.JobsHeaderPage;
import com.quikr.website.jobs.jobsHome.JobsHomePage;
import com.quikr.website.jobs.jobsSNB.JobsSNBPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by quikr on 15/10/15.
 */
public class JobsVAPTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("JOBS_TESTDATA_FILE"));


    /*
    WEB-788:Landing on VAP page
    */
    @Test(groups = "JobsP0")
    public void validateVAPLanding() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        //headerPage.selectCity(testData.get("city"));
        //waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.clickAdTitleJobs();
        Assert.assertTrue(jobsVAPPage.isVAPPageDisplayed(), "Failed To Load VAP Page");
    }

    /*
    WEB-789:Verify the breadcrumb displayed
    */
    @Test
    public void validateVAPBreadCrumbwithCity() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);


        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String City = jobsSNBPage.getCityFromAds();
        jobsSNBPage.clickAdTitleJobs();
        ArrayList<String> breadcrumbtext;
        breadcrumbtext = jobsVAPPage.isBreadCrumbDisplayedwithCity();
        Assert.assertEquals(breadcrumbtext.get(0), testData.get("category"));
        Assert.assertEquals(breadcrumbtext.get(1).toLowerCase(), testData.get("cityUrl"));
        Assert.assertEquals(breadcrumbtext.get(2), testData.get("role"));
    }

    /*
    WEB-789:Verify the breadcrumb displayed
    */
    @Test
    public void validateVAPBreadCrumbwithoutCity() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String City = jobsSNBPage.getCityFromAds();
        jobsSNBPage.clickAdTitleJobs();
        ArrayList<String> breadcrumbtext;
        breadcrumbtext = jobsVAPPage.isBreadCrumbDisplayedwithCity();
        Assert.assertTrue(!breadcrumbtext.get(0).isEmpty(),"Failed To Load Role");
        Assert.assertTrue(!breadcrumbtext.get(1).isEmpty(),"Failed To Load Locality");
        Assert.assertTrue(!breadcrumbtext.get(2).isEmpty(), "Failed to Load Salary");
    }

    /*WEB-776:Verify the ad details displayed in ad card
    */
    @Test
    public void validateVAPAdDetails() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("jobRole")), testData.get("jobRole"), " Failed to type");
        jobsHomePage.clickSearchButton();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        Assert.assertTrue(jobsSNBPage.isAdAllDetailsdiplayed(), "Failed to Load Job with all details");
        jobsSNBPage.clickAdwithAlldetails();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsVAPPage.validateAdDetails(), "Failed to Load Ad Details on VAP Page");
    }

    /*WEB-797:Verify Urgent + Premium ad tag handling in VAP Page
*/
    @Test
    public void validatePremiumAds() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("jobRole")), testData.get("jobRole"), " Failed to type");
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        jobsSNBPage.clickPremiumAds();
        Assert.assertTrue(jobsVAPPage.isPremiumTagavailable().toLowerCase().contains(testData.get("Premium").substring(2, 4)), "Premium Tag not available on VAP Page");
        Assert.assertTrue(jobsVAPPage.jobImageVAP());
    }

    /*WEB-798:Similar ad Success Launch
*/
    @Test
    public void validateSimilarAds() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("jobRole")), testData.get("jobRole"), " Failed to type");
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        jobsSNBPage.clickAdTitleJobs();
        jobsVAPPage.clickSimilarAds();
        Assert.assertTrue(jobsVAPPage.isVAPPageDisplayed(), "Failed To Load VAP Page");
    }

    /*
    WEB-845:Similar Ads in VAP should get translated to requested languages with templatization.
 */
    @Test()
    public void validateJobsSimilarAdsVernac() {
        JobsHeaderPage jobsHeaderPage = new JobsHeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRoleAccountant"));
        //jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        jobsSNBPage.clickAdTitleJobs();
        jobsHeaderPage.clickHindiLanguageJobsHeader();
        Assert.assertEquals(jobsVAPPage.SimilarJobsTitle(), testData.get("SimilarAdsTitleinHindi"), "Failed to Load Translation of Simlar Ads in Hindi");
        jobsHeaderPage.clickTamilLanguageJobsHeader();
        Assert.assertEquals(jobsVAPPage.SimilarJobsTitle(), testData.get("SimilarAdsTitleinTamil"), "Failed to Load Translation of Simlar Ads in Tamil");
        jobsHeaderPage.clickTeluguLanguageJobsHeader();
        Assert.assertEquals(jobsVAPPage.SimilarJobsTitle(), testData.get("SimilarAdsTitleinTelugu"), "Failed to Load Translation of Simlar Ads in Telugu");
        jobsHeaderPage.clickMalyalamLanguageJobsHeader();
        Assert.assertEquals(jobsVAPPage.SimilarJobsTitle(), testData.get("SimilarAdsTitleinMalyalam"), "Failed to Load Translation of Simlar Ads in Malyalam");
    }

    /*WEB-970:DFP AD display
    */
    @Test
    public void validateGoogleAdsonJobsVap() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("jobRole")), testData.get("jobRole"), " Failed to type");
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        jobsSNBPage.clickAdwithAlldetails();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsVAPPage.isGoogleAdsDisplayedonJobsVAP(), "Failed to Load all Ads on Jobs Vap Page");
    }

    /*WEB-971:"Views" count updation
    */
    @Test
    public void isJobsVapViewCount() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.clickAdwithAlldetails();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String count=jobsVAPPage.jobsViewCount();
        refreshPage();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String newcount=jobsVAPPage.jobsViewCount();
        Assert.assertEquals(Integer.parseInt(count) + 1, Integer.parseInt(newcount), "Count is not Updating on Refreshing");
    }

    /*WEB-973:Scroll to Top icon.
    */
    @Test
    public void validateJobsVapScrollIconWorking() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String title=jobsSNBPage.getAdTitleJobs().toLowerCase();
        jobsSNBPage.clickAdTitleJobs();
        waitForPageToLoad(title);
        Assert.assertTrue(jobsVAPPage.isScrollUpIconworking(), "Scroll Icon not working");
    }

    /*WEB-974:Search functionality on VAP
    */
    @Test
    public void validateSearchFunctionalityVAP() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.clickAdTitleJobs();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHeaderPage.enterJobRoleHeader(testData.get("Architect"));
        jobsHeaderPage.clickJobSearchIconHeader();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.isJobSearchResultRelatedtoJobRole(testData.get("Architect")));

    }


}
