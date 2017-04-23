package com.quikr.website.education.educationVap;

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
 * Created by quikr on 9/9/16.
 */
public class EducationVapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("EDUCATION_VAP_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    @BeforeMethod(alwaysRun = true)
    public void EditCookieAdRand(){
        logger.info(2);
        waitForPageToLoad(testData.get("validUrl"));
        createorEditCookieValue(testData.get("cookieName"),testData.get("cookieValue"));
    }

    @Test
    public void Colleges_verifyEducationHeaders(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);
        EducationVAPPage evap = new EducationVAPPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        esnb.clickFirstCollegeTitle();
        Assert.assertTrue(evap.isContactPresent(), "Contact link is not present.");
        Assert.assertTrue(evap.isCoursesPresent(), "COurses link not present.");
        Assert.assertTrue(evap.isFacilitiesPresent(), "Facilities link not present.");
        Assert.assertTrue(evap.isLocalityPresent(), "Locality link not present.");
    }

    @Test
    public void College_verifyRespectiveVap(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);
        EducationVAPPage evap = new EducationVAPPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        String collegeTitle = esnb.getCollegeTitle();
        esnb.clickFirstCollegeTitle();
        String collegeTitleFromVap = evap.getCollegeTitle();
        Assert.assertTrue(evap.verifyRespectiveCollege(collegeTitle,collegeTitleFromVap),"College titles do not match. Please check!");
    }

    @Test
    public void College_LoginFromVap(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        esnb.clickFirstCollegeTitle();
        headerPage.letsLogin("randomPageLoginResponsive", (testData.get("city")), username, password);
        Assert.assertTrue(waitForPageToLoad("http://www.quikr.com/education-training"), "Didn't login. Please check!");
    }

    @Test
    public void verifyPostAdPage(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);
        EducationVAPPage evap = new EducationVAPPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        esnb.clickFirstCollegeTitle();
        evap.clickPostAdBtn();
        Assert.assertTrue(waitForPageToLoad("/post-classifieds-ads"), "Post ad page was not loaded. Please check!");
    }

    @Test
    public void College_verifyHamburgerFromVap(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationSNBPage esnb = new EducationSNBPage(driver);

        headerPage.selectACity("Lucknow");
        waitForPageToLoad("Lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        esnb.clickColleges();
        esnb.clickFirstCollegeTitle();
        Assert.assertTrue(homePage.isHamburgerMenuPresentResponsiveHP(), "Failed to Load Hamburger Menu");
        homePage.clickHamburgerMenu();
        Assert.assertFalse(homePage.closeHamburgerSectionResponsiveHP(), "Hamburger Menu not closing");
        Assert.assertTrue(esnb.verifyHamburger(),"Something wrong with the hamburger menu. Please check!");
    }
}
