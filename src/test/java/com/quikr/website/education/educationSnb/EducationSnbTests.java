package com.quikr.website.education.educationSnb;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.education.educationSNB.EducationSNBPage;
import com.quikr.website.education.educationVAP.EducationVAPPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 6/23/16.
 */
public class EducationSnbTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("EDUCATION_SNB_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    @BeforeMethod(alwaysRun = true)
    public void EditCookieAdRand(){
        waitForPageToLoad(testData.get("validUrl"));
        createorEditCookieValue(testData.get("cookieName"),testData.get("cookieValue"));
    }

    @Test
    public void Colleges_verifyDisciplines(){

        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        Assert.assertTrue(esnb.verifyDisciplineTitles(), "Titles of disciplines are not as per expected. Please check!");
    }

    @Test
    public void Colleges_DisciplineRedirection(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        esnb.clickEngineeringDiscipline();
        Assert.assertTrue(waitForPageToLoad("engineering-technology"),"Proper redirection for college discipline didn't happen. Please check!");
    }

    @Test
    public void Colleges_verifyChicklets(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        Assert.assertTrue(esnb.verifyCollegeChicklets(), "Chicklets were not present.");
    }

    @Test
    public void Colleges_verifyDisciplineFilters(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        Assert.assertTrue(esnb.verifyCollegeDisciplineFilter(), "College discipline filters are working fine.");
    }

    @Test
    public void Colleges_verifyInfiniteScroll(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        Assert.assertTrue(esnb.verifyInfiniteScroll(),"Scroll not giving more titles.");
    }

    //@Test
    public void Colleges_verifySearch(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        headerPage.search("New Horizon College");
    }

    //to be re-written after the bug is fixed
    //@Test
    public void Colleges_verifyShowContact(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.letsLogin("randomPageLoginResponsive", "", username, password);
        clickOnQuikrLogo();
        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        esnb.clickViewContactDetails();
        Assert.assertTrue(esnb.verifyBlurredContactNumber(), "The contact is not blurred. But it should be. Please check!");
        esnb.clickVapShowDetails();
        Assert.assertEquals(esnb.verifyBlurredContactNumber(), false, "The contact are still blurred. Please check!");
    }

    //to be written after the bug is fixed
    @Test
    public void Colleges_verifyDownloadBrochures(){

    }

    //Need to be written
    @Test
    public void Colleges_verifyCityFilter(){

    }

    @Test
    public void SchoolTutions_verifyClassroomCoachingFilterVerification(){

        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.letsLogin("randomPageLoginResponsive", "", username, password);
        clickOnQuikrLogo();
        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickSchoolTutions();
        String[] arr = esnb.clickClassroomCoachingFilters();
        esnb.clickSearchResults();
        String[] newArr = esnb.getClassroomResultantChicklets();
        Assert.assertTrue(esnb.ClassroomCoachingFilterVerification(arr,newArr),"Something went wrong. Please check!");
    }

    @Test
    public void College_validateCityChangeSnb(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        headerPage.selectACity("All india");
        Assert.assertEquals(waitForPageToLoad("system-error"), false, "On switching to All india it is giving system-error page.");
        headerPage.selectACity("Bangalore");
        Assert.assertTrue(waitForPageToLoad("http://www.quikr.com/education-training"), "Proper city page is not loaded. Please check!");
    }

    @Test
    public void College_verifyPostAdButton(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);
        EducationVAPPage evap = new EducationVAPPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        evap.clickPostAdBtn();
        Assert.assertTrue(waitForPageToLoad("/post-classifieds-ads"),"Post ad page was not loaded. Please check!");
    }

    @Test
    public void College_verifyHamburger(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        Assert.assertTrue(homePage.isHamburgerMenuPresentResponsiveHP(), "Failed to Load Hamburger Menu");
        homePage.clickHamburgerMenu();
        Assert.assertTrue(esnb.verifyHamburger(),"Something went wrong with the Hamburger menu. Please check!");
    }

    @Test
    public void Colleges_validateSkipShowResults(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        Assert.assertTrue(esnb.verifySkipShowResultsLink(), "Skip and show results link not working.");
    }

    @Test
    public void Colleges_verifyCollegeFinder(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        String disciplineSelected = esnb.clickRandomCollegeDiscipline();
        String citySelected = esnb.clickRandomCollegeFinderCity();
        String gradSelected = esnb.clickRandomCollegeFinderGrad();
        Assert.assertTrue(esnb.verifyFiltersApplied(disciplineSelected, citySelected, gradSelected), "Something went wrong!");
    }




}
