package com.quikr.website.jobs.jobsSnb;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.jobs.jobsHome.JobsHomePage;
import com.quikr.website.jobs.jobsSNB.JobsSNBPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by Adil on 15/10/15.
 */
public class JobsSnbTests extends TestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("JOBS_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");
    /*
    WEB-677:Landing on SnB page
     */
    @Test(groups = "JobsP0")
    public void verifyLandingSnbPage()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));

        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.validateSnbHeaders(),"Assertion failed for header elements. Check logger debug message.");
    }
    /*
    WEB-762:Sort by "Recent First" order
     */
    @Test
    public void verifySortingRecentMost()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.validateDateSortingRecent(), "Assertion failed.");
    }
    /*
    WEB-763:Sort by "OldestFirst" order
    Need to rework.
     */
    @Test
    public void verifySortingOldestMost()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.validateDateSortingOldest(), "Assertion failed.");
    }
    /*
    WEB-780:Verify the breadcrumb displayed.
     */
    @Test
    public void verifyBreadCrumbs()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.checkBreadCrumb(),"breadcrumbs not displayed.");
    }
    /*
    WEB-760:Verify Reset button
     */
    @Test
    public void verifyResetButton()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.verifyResetButton(),"Reset button is not working properly.");
    }
    /*
    WEB-747:Verify SnB Ad results when Company values are selected from Company filter
     */
    @Test
    public void verifySnbSearchResultsCompanyFilter()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.selectOptionFromCompanyFilter();
        Assert.assertTrue(jobsSNBPage.validateSnbResults(testData.get("jobRole")), "Not even one fourth of the search results have the search string.");
    }
    /* WEB-722:Verify City Filter
    */
    @Test
    public void validateCityFilterdisappear() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("jobRole")), testData.get("jobRole"), " Failed to type");
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        jobsSNBPage.selectCityFromCityFilter(testData.get("delhicity"));
        Assert.assertFalse(jobsSNBPage.isselectCityFromCityFilterVisible(), "Select City Filter is visible even after Selecting City");
    }
    /*
    WEB-723:Verify SnB Ad results when Locality values are selected from locality filter
     */
    @Test(groups = "JobsP0")
    public void verifySnbResultLocalityFilter()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String localityText = jobsSNBPage.selectOptionFromLocalityFilter();
        Assert.assertTrue(jobsSNBPage.validateLocalitySnbResults(localityText), "Some or all localities doesn't has the selected locality string.");
    }
    /*
    WEB-724:Verify SnB Ad results when Locality values are deselected from locality filter
     */
    @Test
    public void verifyResultsLocalityFilterDeselected()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        int adCount = jobsSNBPage.getAdCount();
        jobsSNBPage.selectOptionFromLocalityFilter();
        jobsSNBPage.selectOptionFromLocalityFilter();
        int newAdCount = jobsSNBPage.getAdCount();
        Assert.assertEquals(newAdCount, adCount, "The ad count before and  after deselecting the filter are not the same. Please check!");
    }
    /*
    WEB-725:Verify SnB Ad results when Salary values are selected from Salary filter
     */
    @Test
    public void verifyResultsSalaryFilter()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.selectOptionFromSalaryFilter();
        Assert.assertTrue(jobsSNBPage.validateSalarySearchResults(), "Assertion failed. Please check!");
    }
    /*
    WEB-726: Verify SnB Ad results when Salary values are deselected from Salary filter
     */
    @Test
    public void verifyResultsSalaryDeselected()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        int adCount = jobsSNBPage.getAdCount();
        jobsSNBPage.selectOptionFromSalaryFilter();
        jobsSNBPage.selectOptionFromSalaryFilter();
        int newAdCount = jobsSNBPage.getAdCount();
        Assert.assertEquals(newAdCount, adCount, "The ad count before and after deselecting the filter are not the same. Please check!");
    }
    /*
    WEB-751:Verify SnB Ad results when Company values are deselected from
     */
    @Test
    public void verifySnbSearchResultsCompanyFilterDeselected() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        int adCount = jobsSNBPage.getAdCount();
        jobsSNBPage.selectOptionFromCompanyFilter();
        jobsSNBPage.selectOptionFromCompanyFilter();
        int newAdCount = jobsSNBPage.getAdCount();
        Assert.assertEquals(newAdCount, adCount, "The ad count before and after deselecting the filter are not the same. Please check!");
    }
    /*WEB-764:Verify Ads are fetched when user does free text search.
    */
    @Test
    public void validateFreeAdsSearchResults(){
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        jobsHomePage.enterFreeTextJobRole(testData.get("jobRoleFreeText"));
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        jobsSNBPage.checkBreadCrumb();
        Assert.assertTrue(jobsSNBPage.isFreeTextSearchReflectsonSNB().contains(testData.get("jobRoleFreeText").toLowerCase()), "Failed to Load Free Ad jobRole ");
    }
    /*WEB-790:Verify the ad details displayed in ad card
  */@Test
    public void validateSNBAdDetails() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("jobRole")), testData.get("jobRole"), " Failed to type");
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        Assert.assertTrue(jobsSNBPage.isAdAllDetailsdiplayed(), "Failed to Load Ad with all details");
        Assert.assertTrue(jobsSNBPage.isApplyButtonavailable(), "Failed to Load Apply Button for JOBS SNB Page");
    }
    /* WEB-767:Verify Premium Ad when displayed in top3.
    */
    @Test
    public void validatePremiumAds() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        jobsHomePage.setJobRole(testData.get("jobRole"));
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        Assert.assertTrue(jobsSNBPage.isPremiumBandavailable(), "Failed to Load Premium Band Jobs");
        jobsSNBPage.clickApplyButtonPremiumJobwithAllDetails();
        Assert.assertTrue(jobsSNBPage.isApplyJobFormOpen(), "Failed to Open Apply Job Form for Premium Ads");
    }
    /* WEB-769:Verify Premium & Urgent Ad when displayed in top3.
    */
    @Test
    public void validatePremiumandUrgentAds(){
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("Architect")), testData.get("Architect"), " Failed to type");
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        scrollVerticallWithCords(0, 1200);
        scrollVerticallWithCords(0, 1200);
        Assert.assertTrue(jobsSNBPage.jobImageSNB(), "Failed to Load Jobs Images on SNB");
        Assert.assertTrue(jobsSNBPage.isPremiumandUrgentBandavailable(), "Failed to Load Premium and Urgent Band Jobs");
        jobsSNBPage.clickApplyButtonPremiumandUrgentJobwithAllDetails();
        Assert.assertTrue(jobsSNBPage.isApplyJobFormOpen(), "Failed to Open Apply Job Form for Premium and Urgent Ads");
    }
    /* WEB-761:Verify the order of ads displayed on SnB page
    */
    @Test
    public void validateSortByText(){
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("Architect")), testData.get("Architect"), " Failed to type");
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        Assert.assertEquals(jobsSNBPage.getSortByText().substring(0, jobsSNBPage.getSortByText().length() - 2), testData.get("Popular"), "Failed to Load Popular Ads");
    }
    /* WEB-758:Verify SnB Ad results when all filters values are selected
    */
    @Test
    public void validateSNBFilterResult()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();

        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));

        //Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        //Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        //Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("Architect")), testData.get("Architect"), " Failed to type");
        //jobsHomePage.searchCity(testData.get("jobsCity"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        jobsSNBPage.clickLocality();
        jobsSNBPage.selectDropDownOptionsLocation();
        jobsSNBPage.clickSalary();
        jobsSNBPage.selectDropDownOptionsSalary();
        jobsSNBPage.clickJobType();
        jobsSNBPage.selectDropDownOptionsJobType();
        Assert.assertTrue(jobsSNBPage.isJobSearchResultsDisplayJobs(), "Failed to Load Jobs From Jobs Search Results");
    }
    /*
    WEB-755:Verify SnB Ad results when Job Type values are deselected from Job Type filter
     */
    @Test
    public void verifySnbResultsJobsFilterDeselcted()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        int adCount = jobsSNBPage.getAdCount();
        jobsSNBPage.selectOptionFromJobsType();
        jobsSNBPage.selectOptionFromJobsType();
        int newAdCount = jobsSNBPage.getAdCount();
        Assert.assertEquals(newAdCount, adCount, "The ad count before and after deselecting the filter are not the same. Please check!");
    }
    /*
    WEB-860:Visible Filter Attributes on SnB page
     */
    @Test
    public void validateVisibleFilters()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.checkFilters(), "Filters not displayed as expected.");
    }
    /*
    WEB-861:Verify 'Reset' button functionality when job type is selected.
     */
    @Test
    public void validateResetButtonWithJobTypeFilter()
    {
        HomePage homePage = new HomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.selectOptionFromJobsType();
        Assert.assertTrue(jobsSNBPage.validateResetWithJobFilter(),"Assertion failed.");

    }
    /*
    WEB-862:Verify the deselect functionality of Job Type filter attribute
     */
    @Test
    public void verifyDeselectWithJobFilter()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        int adCount = jobsSNBPage.getAdCount();
        jobsSNBPage.selectOptionFromJobsType();
        jobsSNBPage.selectOptionFromJobsType();
        int newAdCount = jobsSNBPage.getAdCount();
        Assert.assertEquals(newAdCount, adCount, "The ad count before and after deselecting the filter are not the same. Please check!");
    }

    /*
    WEB-903:Reset button visibility
     */
    @Test
    public void validateResetButtonVisibility()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertEquals(jobsSNBPage.isResetButtonDisplayed(), false, "Reset button is displayed even when filters are not selected. Please check!");
        jobsSNBPage.selectOptionFromSalaryFilter();
        Assert.assertEquals(jobsSNBPage.isResetButtonDisplayed(), true, "Reset button is not displayed even when filters are selected. Please check!");
    }

    /*
    WEB-900:DFP ads on SnB page
     */
    @Test
    public void validateDFPAds()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.checkGoogleAds(), "DFP ads not visible. Please check!");
        

    }

    /*WEB-978:Scroll to Top icon.
    */
    @Test
    public void validateJobsSNBScrollIconWorking() {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        Assert.assertTrue(jobsSNBPage.isScrollUpIconSNBworking(), "Scroll Icon not working");
    }

    /*WEB-975:Apply functionality for Ported Ad for existing user logged in user
    */
    @Test(groups = "JobsP0")
    public void validateJobsSNBApplyLoggedInUser()  {

        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);

        /*headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("",(testData.get("city")),username,password);
        headerPage.clickOnQuikrLogo();
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.clickRecentAds();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String title=jobsSNBPage.clickApplyButton(1);
        System.out.println("Clicked Ad " + title);
        String MobNumber=95+getRandomInteger(8);
        jobsSNBPage.fillApplyForm1WithLogin(testData.get("JobApplyName"), MobNumber);
        jobsSNBPage.fillApplyForm2();
        Assert.assertTrue(jobsSNBPage.isAppliedButtonavailable(1), "Apply Button not changing to Applied");
    }

    /*WEB-976:Apply functionality for Ported Ad for existing user.
    */
    @Test(groups = "JobsP0")
    public void validateJobsSNBApplyExistingUser()  {

        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.clickRecentAds();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String title=jobsSNBPage.clickApplyButton(1);
        System.out.println("Clicked Ad " + title);
        String MobNumber=95+getRandomInteger(8);
        String Email="auto"+getRandomString(5)+"@gmail.com";
        jobsSNBPage.fillApplyForm1WithoutLogin(testData.get("JobApplyName"), MobNumber, Email);
        jobsSNBPage.fillApplyForm2();
        Assert.assertTrue(jobsSNBPage.isAppliedButtonavailable(1), "Apply Button not changing to Applied");

    }

    /*WEB-967:Pre-population of Apply functionality
    */
    @Test(groups = "JobsP0")
    public void validatePrePopulatedOnApply()  {

        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.clickRecentAds();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String title=jobsSNBPage.clickApplyButton(1);
        System.out.println("Clicked Ad " + title);
        String MobNumber=95+getRandomInteger(8);
        String Email="auto"+getRandomString(5)+"@gmail.com";
        jobsSNBPage.fillApplyForm1WithoutLogin(testData.get("JobApplyName"), MobNumber, Email);
        jobsSNBPage.fillApplyForm2();
        Assert.assertTrue(jobsSNBPage.isAppliedButtonavailable(1), "Apply Button not changing to Applied");
        jobsSNBPage.clickApplyButton(1);
        Assert.assertTrue(jobsSNBPage.isApplyForm1Prefilled(testData.get("JobApplyName"), MobNumber, Email));
    }

    /*WEB-977:Apply functionality for Cities with "No Localities"
    */
    @Test
    public void validateLocalitiesOnApply()  {

        HomePage homePage=new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);

        homePage.clickonCityCloseButton();
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.setCity(testData.get("Dharampur"));
        jobsHomePage.setJobRole(testData.get("jobRole"));
        jobsHomePage.clickSearchButton();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsSNBPage.clickRecentAds();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        String title=jobsSNBPage.clickApplyButton(1);
        System.out.println("Clicked Ad " + title);
        String MobNumber=95+getRandomInteger(8);
        String Email="auto"+getRandomString(5)+"@gmail.com";
        jobsSNBPage.fillApplyForm1WithoutLogin(testData.get("JobApplyName"), MobNumber, Email);
        Assert.assertTrue(jobsSNBPage.isEntireLocalityPresent(), "Failed to Get Entire Locality");
        Assert.assertTrue(jobsSNBPage.isCheckBoxForPresentonApplyForm(),"Failed to Load CheckBox for Mandatory Field");
    }

}


