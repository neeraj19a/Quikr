package com.quikr.website.education.educationHome;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by archana.kumari on 6/23/16.
 */
public class EducationHomeTests extends TestBase {

    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("EDUCATION_HOME_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    @BeforeMethod(alwaysRun = true)
    public void EditCookieAdRand(){
        waitForPageToLoad(testData.get("validUrl"));
        createorEditCookieValue(testData.get("cookieName"),testData.get("cookieValue"));
    }

    @Test
    public void verifyEducationHomePageRedirect()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationHomePage educationHomePage = new EducationHomePage(driver);

        headerPage.selectACity(testData.get("changecity"));
        waitForPageToLoad(testData.get("changecity"));
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");

        educationHomePage.clickCarouselBanner(0);
        Assert.assertTrue(waitForPageToLoad("study-abroad+"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickCarouselBanner(1);
        Assert.assertTrue(waitForPageToLoad("entrance-exam"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickCarouselBanner(2);
        Assert.assertTrue(waitForPageToLoad("administrative-services"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickCarouselBanner(3);
        Assert.assertTrue(waitForPageToLoad("computer-training"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickCarouselBanner(4);
        Assert.assertTrue(waitForPageToLoad("distance-learning"), "Navigated to wrong url : " + getCurrentUrl());
    }

    @Test
    public void verifySubCatRedirects(){

        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationHomePage educationHomePage = new EducationHomePage(driver);

        headerPage.selectACity(testData.get("changecity"));
        waitForPageToLoad(testData.get("changecity"));
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");

        educationHomePage.clickEducationLearningSubCat(0);
        Assert.assertTrue(waitForPageToLoad("play-schools-creche"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(1);
        Assert.assertTrue(waitForPageToLoad("school-tuitions-classes"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(2);
        Assert.assertTrue(waitForPageToLoad("exam-coaching-classes"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(3);
        Assert.assertTrue(waitForPageToLoad("study-abroad"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(4);
        Assert.assertTrue(waitForPageToLoad("distance-learning-courses"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(5);
        Assert.assertTrue(waitForPageToLoad("text-books-study-material"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(6);
        Assert.assertTrue(waitForPageToLoad("colleges"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(7);
        Assert.assertTrue(waitForPageToLoad("professional-certifications-training"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(8);
        Assert.assertTrue(waitForPageToLoad("vocational-skill-training"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(9);
        Assert.assertTrue(waitForPageToLoad("hobby-classes"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(10);
        Assert.assertTrue(waitForPageToLoad("workshops"), "Navigated to wrong url : " + getCurrentUrl());
        navigateBack();
        educationHomePage.clickEducationLearningSubCat(11);
        Assert.assertTrue(waitForPageToLoad("career-counseling"),"Navigated to wrong url : " + getCurrentUrl() );
    }

    @Test
    public void verifyArticlesRedirection(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationHomePage educationHomePage = new EducationHomePage(driver);

        headerPage.selectACity(testData.get("changecity"));
        waitForPageToLoad(testData.get("changecity"));
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");

        educationHomePage.clickOnArticles(1);
        switchToWindow();
        Assert.assertTrue(waitForPageToLoad("blog.quikr"), "Didn't redirect to blog.quikr.com. Please check!");
    }

    @Test
    public void loginFromEduHome(){

        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectACity(testData.get("changecity"));
        waitForPageToLoad(testData.get("changecity"));
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        headerPage.letsLogin("randomPageLoginResponsive", "", username, password);
        Assert.assertTrue(waitForPageToLoad("http://www.quikr.com/education-training"),"Didn't login properly. Please check!");
    }

    //@Test Since search is not implemented as expected
    public void validateSearchFromEduHome(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectACity(testData.get("changecity"));
        waitForPageToLoad(testData.get("changecity"));
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        headerPage.search("coaching");
        Assert.assertTrue(headerPage.checkSearchRelevance("coaching"), "Relevant search results were not found. Please check!");
    }

    @Test
    public void validateCityChangeHome(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectACity("lucknow");
        waitForPageToLoad("lucknow");
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        headerPage.selectACity("All india");
        Assert.assertEquals(waitForPageToLoad("system-error"), false, "On switching to All india it is giving system-error page.");
        headerPage.selectACity("Bangalore");
        Assert.assertTrue(waitForPageToLoad("http://www.quikr.com/education-training"), "Proper city page is not loaded. Please check!");
    }

    @Test
    public void validatePostAdButton(){
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EducationHomePage educationHomePage = new EducationHomePage(driver);

        headerPage.selectACity(testData.get("changecity"));
        waitForPageToLoad(testData.get("changecity"));
        homePage.selectCategoryFromHomePage(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING);
        waitForPageToLoad("education-training");
        educationHomePage.clickPostAd();
        Assert.assertTrue(waitForPageToLoad("/post-classifieds-ads"),"Post ad page was not loaded. Please check!");

    }
}
