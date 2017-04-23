package com.quikr.website.jobs.jobsHome;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.alert.AlertPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.jobs.jobsHeader.JobsHeaderPage;
import com.quikr.website.jobs.jobsSNB.JobsSNBPage;
import com.quikr.website.jobs.jobsVAP.JobsVAPPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by quikr on 13/10/15.
 */
public class JobsHomeTests extends TestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("JOBS_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /*WEB-742:Landing on quikr jobs home page
  */
    @Test(groups = "JobsP0")
    public void validateQuikrJobsPageLanding() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.clickonCityCloseButton();
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + homePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isCitySelectionScreenPresent(),"Failed to Load City Selection Pop Up Window");
    }
    /*WEB-744:Free search text
     */
    @Test
    public void validateQuikrJobsSearchBox() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + homePage.getCurrentLocation());
        jobsHomePage.isSearchJobsBoxPresent();
        Assert.assertEquals(jobsHomePage.gettextSearchJobs(), testData.get("SearchJobsComboBox"), "Failed to load text");
        Assert.assertEquals(jobsHomePage.getJobfromSuggestion(testData.get("jobRole")), testData.get("jobRole"), " Failed to type");
    }
    /*WEB-746:Functionality of City list box
   */
    @Test
    public void validateSearchBoxfunctionality() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        String cityEntered = testData.get("inputCity");
        jobsHomePage.inputCity(cityEntered);
        Assert.assertTrue(jobsHomePage.validateCitySelectionScreenFunctionality(cityEntered), "Auto suggest didn't display corresponding suggestion. Please check!");
        String randomString = testData.get("inputRandomString");
        jobsHomePage.inputCity(randomString);
        Assert.assertEquals(jobsHomePage.validateCitySelectionScreenFunctionality(randomString), false, "Auto suggest didn't sugges <All India>. Please check!");


    }
    /*WEB-750:Search_0.1
   */
    @Test
    public void validateSearchBoxValidations() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHomePage.validateEmptySearch(), "Validation didn't work for empty search. Please check!");
    }
    /*WEB-752:Search_0.2
*/
    @Test
    public void validateRoleSearchBoxfunctionality()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + homePage.getCurrentLocation());
        jobsHomePage.setCity(testData.get("jobsCity"));
        jobsHomePage.clickAllRole(testData.get("role"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
    }


    /*
    WEB-756:Search_0.4
    */
    @Test
    public void validateCityandJobSearchBoxfunctionality()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.searchJobsWithoutSelectingCity(testData.get("jobRole"));
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
    }
    /*WEB-757:Search_0.5
      */
    @Test(groups = "JobsP0")
    public void validateJobFreeTextSearchwithCity() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + homePage.getCurrentLocation());
        jobsHomePage.setCity(testData.get("jobsCity"));
        jobsHomePage.enterFreeTextJobRole(testData.get("jobRoleFreeText"));
        jobsHomePage.clickSearchButton();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
    }
    /*WEB-759:Top Roles
      */
    @Test
    public void  validateTopJobRoles()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHomePage.verifyTopJobRoles(), "Failed to load top Job Roles. Please check!");
    }
    /* WEB-765:Top Roles Functionality
    */
    @Test
    public void validateTopJobsOptionsFunctionality()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHomePage.verifyTopJobRoles(), "Failed to load top Job Roles. Please check!");

        jobsHomePage.clickATopRole();
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
    }
    /* WEB-766:Hamburger Menu
    */
    @Test
    public void validateHamMenuOptions() {
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);

        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + homePage.getCurrentLocation());
        Assert.assertTrue(jobsHeaderPage.isHamMenupresent());
        jobsHeaderPage.clickHamMenu();
        jobsHeaderPage.isQuikrHomepresentHamMenu();
        Assert.assertTrue(jobsHeaderPage.isAllHamMenuOptionsavailable());
    }
    /* WEB-768:Hamburger Menu Functionality
    */
    @Test
    public void validateHamMenuFunctionality() {
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);

        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHeaderPage.isHamMenupresent());
        jobsHeaderPage.clickHamMenu();
        jobsHeaderPage.isQuikrHomepresentHamMenu();
        jobsHeaderPage.isAllHamMenuOptionsavailable();
        jobsHeaderPage.closeHemuMenu();
        Assert.assertFalse(jobsHeaderPage.isQuikrHomeHamMenuopen());
        //jobsHomePage.clickHamMenu();
        jobsHeaderPage.clickAllHamMenuOptions();
        Assert.assertTrue(waitForPageToLoad(testData.get("validURL")), "Fail to load URL : " + testData.get("validURL") + " expected : " + jobsHomePage.getCurrentLocation());
        waitForPageToLoad(testData.get("validURL"));
        homePage.selectJobsOptionFromHome();
        jobsHeaderPage.clickHamMenu();
        jobsHeaderPage.isQuikrHomepresentHamMenu();
        jobsHeaderPage.clickQuikrHomeHamMenu();
        Assert.assertTrue(waitForPageToLoad(testData.get("validURL")), "Fail to load URL : " + testData.get("validURL") + " expected : " + jobsHomePage.getCurrentLocation());
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
    }
    /*WEB-771:Jobs Home page header menu
    */
    @Test
    public void validateJobsHomePageHeaderMenu()
    {
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHeaderPage.isPostAdbuttonpresent(), "Failed to Load Post Ad button.");
        Assert.assertTrue(jobsHeaderPage.isJobsLogopresent(),"Failed to Load Quikr Jobs Logo.");
        Assert.assertTrue(jobsHomePage.isEmployerZonepresent(), "Failed to load employer zone.");
    }

    /*WEB-774:Vernac Functionality
    */
    @Test
    public void validateVernacFunctionality()
    {
        HomePage homePage=new HomePage(driver);
        JobsHomePage jobsHomePage=new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));

        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHomePage.verifyLanguagesPresent(), "All language labels not present. Please check!");
        jobsHomePage.clickHindiLanguage();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        Assert.assertEquals(jobsHomePage.returnTopRoLeLabelText(), testData.get("topRoleLabel"), "Failed in conversion to hindi. Please check!");
    }


    /* WEB-779:City Pre-populate_0.2
*/
    @Test
    public void validateCityPrePopulateSNB() {

        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        Assert.assertEquals(jobsHomePage.CityPrePopulated(), testData.get("city"), "Failed to Load the city Selected");
    }
    /* WEB-781:City pre-populate _0.3
*/
    @Test
    public void validateCityPrePopulateJobsHomePage() {
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage = new JobsSNBPage(driver);
        JobsVAPPage jobsVAPPage = new JobsVAPPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(waitForPageToLoad(testData.get("NewJobsUrl")), "Fail to load URL : " + testData.get("NewJobsUrl") + " expected : " + jobsHomePage.getCurrentLocation());
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to load City Search Box");
        jobsHomePage.searchCity(testData.get("jobsCity"));
        jobsHomePage.selectJobRole(testData.get("jobRole"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load SNB Page");
        Assert.assertTrue(jobsHeaderPage.isCityPrePopulated(testData.get("jobsCity")), "Failed to Load Pre Populated City on SNB Page");
        jobsSNBPage.clickAdTitleJobs();
        jobsVAPPage.navigatefromVAPtoSNBHomes();
        jobsSNBPage.navigatefromJobsSNBPagetoJobsHomePage();
        Assert.assertEquals(jobsHomePage.CityPrePopulated(), testData.get("city"), "Failed to Load the city Selected");
    }

    /*
    WEB-856:Job Type Functionality
     */
    @Test
    public void validateJobTypes() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHomePage.verifyJobTypeLabels(), "All job type labels not as expected. Please check!");
    }
    /*WEB-915:Jobs Home page
    */
    @Test
    public void validateJobsHomePage()
    {
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHeaderPage.isHamMenupresent(), "Failed to Load Hem Menu");
        Assert.assertTrue(jobsHeaderPage.isJobsLogopresent(), "Failed to Load Quikr jobs Logo");
        Assert.assertTrue(jobsHomePage.isEmployerZonepresent(), "Failed to Load Employer Zone");
        Assert.assertTrue(jobsHeaderPage.isPostAdbuttonpresent(), "Failed to Load Post Ad Button");
        Assert.assertEquals(getToolTipTextEditAd(jobsHomePage.EmployerZone()), testData.get("EmployerZoneToolTip"), "Failed to Load Tool Tip");
        Assert.assertTrue(jobsHomePage.isSelectCityPresent(), "Failed to Load SelectCity");
        Assert.assertTrue(jobsHomePage.isSearchJobsBoxPresent(), "Failed to Load Jobs Search Box");
        Assert.assertTrue(jobsHomePage.isTopRolesBoxpresent(), "Failed to Load Top Roles");
        Assert.assertTrue(jobsHomePage.isAllLanguageAvailable(), "Failed To Load Languages on Jobs Home Page");
        Assert.assertTrue(jobsHomePage.verifyJobTypeLabels(), "Failed to Load All Jobs Types");
        Assert.assertTrue(jobsHomePage.verifyTopJobRoles(), "Failed to Load All Top Roles");
        Assert.assertTrue(jobsHomePage.isAllRolesPresent(), "Failed To Load All Roles");
    }
    /*WEB-916:Go To Employer Zone fucntionality
    */
    @Test
    public void validateEmployerZone() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickEmployerZone();
        Assert.assertTrue(getCurrentUrl().contains(testData.get("QuikrHireURL")),"Failed to Load Quikr Hire");
    }
    /*WEB-917:DFP AD display
    */
    @Test
    public void validateAdsonJobsHomePage() {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        waitForPageToLoad(testData.get("validURL"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHomePage.checkAdsonJobsHomePage(), "Failed to Load Google Ads on Jobs Home Page");
    }

    /*
    WEB-745:City selection screen
     */
    @Test
    public void validateCitySelectionScreen()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        Assert.assertTrue(jobsHomePage.isCitySelectionScreenPresent(), "City selection screen missing. Please check!");
    }

    /*WEB-980:TJ icon display
    */
    @Test
    public void validateTatkalJobIconDisplayed()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("Punecity"));
        waitForPageToLoad(testData.get("PunecityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(jobsHomePage.isTatkalJobIconDisplayed(), "Tatkal Job Icon Not Displayed");
    }

    /*WEB-981:TJ page
    */

    @Test
    public void validateTatkalJobPageDisplayed()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("Punecity"));
        waitForPageToLoad(testData.get("PunecityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(jobsHomePage.isTatkalJobIconDisplayed(), "Tatkal Job Icon Not Displayed");
        jobsHomePage.clickTatkalJob();
        Assert.assertTrue(jobsHomePage.isTatkalPageDisplayed(),"Failed to Load TatKal Jobs Page");
    }

    /*WEB-984:TJ - click on close icon
    */
    @Test
    public void validateTatkalJobPagenotDisplayedOnClosing()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("Punecity"));
        waitForPageToLoad(testData.get("PunecityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(jobsHomePage.isTatkalJobIconDisplayed(), "Tatkal Job Icon Not Displayed");
        jobsHomePage.clickTatkalJob();
        Assert.assertTrue(jobsHomePage.isTatkalPageDisplayed(), "Failed to Load TatKal Jobs Page");
        jobsHomePage.clickTatkalJobCloseIcon();
        waitForPageToLoad(testData.get("PunecityUrl"));
        Assert.assertTrue(jobsHomePage.isSearchJobsBoxPresent(),"Failed to Close Tatkal Jobs and redirecting to Jobs Home Page");
    }

    /*
     WEB-992:Role card is completely clickable.
     */
    @Test
    public void validateIfRoleCardClickable()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("Punecity"));
        waitForPageToLoad(testData.get("PunecityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(jobsHomePage.validateRoleCards(),"Role cards are not clickable.");
    }

    /*
    WEB-991:"All India" default city.
     */
    @Test
    public void validateDefaultCity()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("Punecity"));
        waitForPageToLoad(testData.get("PunecityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(jobsHomePage.validateDefaultCitySelection(),"Default city is not <All India>.");
    }

    /*
    WEB-990:Scroll to Top icon.
     */
    @Test
    public void validateScrollToTopButton()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("Punecity"));
        waitForPageToLoad(testData.get("PunecityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        waitForPageToLoad(testData.get("validURL"));
        jobsHomePage.scrollToPageEnd();
        Assert.assertTrue(jobsHomePage.validateScrollToTopBtn(),"Scroll to top button not visible.");
    }

    /*
    WEB-984:TJ - click on close icon
     */
    //Yet to do :: locators for close icon are not proper.

    /*
    WEB-986:"Select Your City" screen display
     */
    @Test
    public void verifyCities()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);

        homePage.clickonCityCloseButton();
        homePage.selectJobsOptionFromHome();
        Assert.assertTrue(jobsHomePage.validateCitiesJobsHomePage(),"All the required cities not present in city selection box in Jobs homepage. Please check!");
    }

    /*
    WEB-987:Search in "Select Your City" screen
     */
    @Test
    public void verifyCityAutoSuggest()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);

        String citySearchString = testData.get("citySearchString");

        homePage.clickonCityCloseButton();
        homePage.selectJobsOptionFromHome();
        homePage.searchCityInCitySearchBox(citySearchString);
        Assert.assertTrue(homePage.checkCityAutoSuggest(citySearchString), "Auto suggest didn't give proper result. Please check!");
    }

    /*
    WEB-988:select any city from "Select your city" screen on HP
     */
    @Test
    public void verifySelectedCityLabels()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        String city = testData.get("city");

        headerPage.selectCity(city);
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickCitySelection();
        Assert.assertTrue(jobsHomePage.checkCityLabelsAcrossHP(city),"labels for selected city across is either not same or not visible. Please check!");

    }

    @Test
    public void validateAutocomplete(){

        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        String city = testData.get("city");
        headerPage.selectCity(city);
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHomePage.validatesetJobRoleAutocomplete(testData.get("role"));
        Assert.assertTrue(jobsSNBPage.isJobsSNBPageDisplayed(), "Failed to Load Jobs SNB Page");
    }

    /*
    WEB-985:TJ success page
     */
    @Test(groups = "JobsP0")
    public void validateTatkalJobFlowSuccess()
    {
        HomePage homePage = new HomePage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("Punecity"));
        waitForPageToLoad(testData.get("PunecityUrl"));
        homePage.selectJobsOptionFromHome();
        jobsHomePage.clickTatkalJob();
        jobsHomePage.inputNameTJ(testData.get("CitrusCCname"));
        jobsHomePage.inputEmailTJ(testData.get("CitrusCCemail"));
        jobsHomePage.inputMobNumTJ(testData.get("phoneNumber"));
        jobsHomePage.selectRoleTJ(testData.get("role"));
        jobsHomePage.selectLocalityTJ(testData.get("CitrusCCLocality"));
        jobsHomePage.clickPayButtonTJ();
        jobsHomePage.inputCreditCardNumTJ(testData.get("CitrusCCnumber"));
        jobsHomePage.enterExpiryMonthYearCVVTJ();
        jobsHomePage.enterCardHolderName();
        jobsHomePage.clickPayCitrus();
        Assert.assertTrue(waitForPageToLoad("payment-success=true"), "Payment didn't happen successfully. Please check!");
    }


    @Test(groups = "JobsP0", dataProvider = "jobSubCatForAlertCreation", dataProviderClass = Data.class)
    public void createAlertJobs(int argForSubCatSelection)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);
        JobsHomePage jobsHomePage = new JobsHomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        alertPage.setCategory(testData.get("category"));
        jobsHomePage.selectSubCategoryJobAlertCreation(argForSubCatSelection);
        alertPage.setAdType(testData.get("wantAd"));
        alertPage.setRole(testData.get("role"));
        alertPage.setEducation(testData.get("education"));
        alertPage.setExperience(testData.get("experience"));
        alertPage.setLocality(testData.get("locality"));
        alertPage.setEmail(testData.get("emailId"));
        alertPage.setPhoneNumber(testData.get("phoneNumber"));
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateCreateAlert(), "Alert not created");

    }
}