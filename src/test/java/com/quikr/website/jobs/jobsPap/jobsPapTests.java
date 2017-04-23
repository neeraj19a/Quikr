package com.quikr.website.jobs.jobsPap;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.electronics.electronicsPap.ElectronicsPapPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.jobs.jobsHeader.JobsHeaderPage;
import com.quikr.website.jobs.jobsPAP.JobsPAPPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 30/10/15.
 */
public class jobsPapTests extends TestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("JOBS_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    @Test(groups = "JobsP0", dataProvider = "jobCategoriesForJobPap", dataProviderClass = Data.class)
    public void validateAdPosting(String jobCat)
    {
        JobsPAPPage jobsPAPPage = new JobsPAPPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);

        String adTitle = testData.get("adTitle");
        String role = testData.get("role");
        String desc = testData.get("description");

        /*headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("", testData.get("city"), username, password);
        headerPage.clickOnQuikrLogo();
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHeaderPage.clickPostAdbuttonpresent();
        waitForPageToLoad(testData.get("validURL"));

        jobsPAPPage.selectJobCategory(jobCat);
        jobsPAPPage.postAdTitle(adTitle);
        jobsPAPPage.selectRole(role);
        jobsPAPPage.setMinSalary(testData.get("minSalary"));
        jobsPAPPage.setMaxSalary(testData.get("maxSalary"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(desc);
        jobsPAPPage.clickSubmitBtnJobPostAd();
        jobsPAPPage.clickAddToJobBtnJobPostAd();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(jobsPAPPage.isJobPosted(), "Failed to Load Successful Job Post Page");
    }

    /*WEB-605:Post ad:In post ad page,user can select multiple locality in jobs category.
    */
    @Test()
    public void postAdWithMultipleLocalities()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        JobsPAPPage jobsPAPPage = new JobsPAPPage(driver);
        HomePage homePage = new HomePage(driver);
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);

        String adTitle = testData.get("adTitle");
        String role = testData.get("role");
        String edu = testData.get("education");
        String exp = testData.get("experience");
        String desc = testData.get("description");
        String mob = testData.get("number");

        /*headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("",(testData.get("city")),username, password);
        headerPage.clickOnQuikrLogo();
        homePage.selectJobsOptionFromHome();
        jobsHeaderPage.clickPostAdbuttonpresent();
        jobsPAPPage.postAdTitle(adTitle);
        jobsPAPPage.selectRole(role);
        jobsPAPPage.setMinSalary("1000");
        jobsPAPPage.setMaxSalary("2000");
        jobsPAPPage.selectmultiplelocality();
        jobsPAPPage.provideDescription(desc);
        jobsPAPPage.clickSubmitBtnJobPostAd();
        jobsPAPPage.clickAddToJobBtnJobPostAd();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(jobsPAPPage.isJobPosted(), "Failed to Load Successfull Job Post Page");
    }

    @Test()
    public void postADWithoutLoginAsWantType()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage pap=new ElectronicsPapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        JobsHeaderPage jobsHeaderPage = new JobsHeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        clickOnQuikrLogo();
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHeaderPage.clickPostAdbuttonpresent();
        waitForPageToLoad(testData.get("cityUrl"));

        pap.postAdAsWant();
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role1"));
        jobsPAPPage.selectEducation(testData.get("edu"));
        jobsPAPPage.selectExperience(testData.get("experiance"));
        jobsPAPPage.selectsalary(testData.get("salary"));
        pap.selectRelocation(testData.get("relocate"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        pap.sendName(testData.get("name"));
        jobsPAPPage.provideMobNum(testData.get("phoneNumber"));
        jobsPAPPage.clickPostAdButton();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(pap.validateAd(), "Failed to post ad");
    }

    @Test()
    public void postADWithoutLoginAsOfferType()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage pap=new ElectronicsPapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        JobsHeaderPage jobsHeaderPage = new JobsHeaderPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        clickOnQuikrLogo();
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHeaderPage.clickPostAdbuttonpresent();
        waitForPageToLoad(testData.get("cityUrl"));

        pap.postAdAsWant();
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role1"));
        jobsPAPPage.selectEducation(testData.get("edu"));
        jobsPAPPage.selectExperience(testData.get("experiance"));
        jobsPAPPage.selectsalary(testData.get("salary"));
        pap.selectRelocation(testData.get("relocate"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        pap.sendName(testData.get("name"));
        jobsPAPPage.provideMobNum(testData.get("phoneNumber"));
        jobsPAPPage.clickPostAdButton();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(pap.validateAd(), "Failed to post ad");
    }


    @Test()
    public void postADWithLoginAsWantType()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage pap=new ElectronicsPapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        JobsHeaderPage jobsHeaderPage = new JobsHeaderPage(driver);

        /*headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("",(testData.get("city")),username,password);
        clickOnQuikrLogo();
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHeaderPage.clickPostAdbuttonpresent();
        waitForPageToLoad(testData.get("cityUrl"));

        pap.postAdAsWant();
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role1"));
        jobsPAPPage.selectEducation(testData.get("edu"));
        jobsPAPPage.selectExperience(testData.get("experiance"));
        jobsPAPPage.selectsalary(testData.get("salary"));
        pap.selectRelocation(testData.get("relocate"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        pap.sendName(testData.get("name"));
        jobsPAPPage.clickPostAdButton();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(pap.validateAd(), "Failed to post ad");
    }

    @Test
    public void postADWithtLoginAsOfferType()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage pap=new ElectronicsPapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);

        /*headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        */headerPage.letsLogin("", (testData.get("city")),username,password);
        waitForPageToLoad("MyQuikr");
        pap.openPap();
        waitForPageToLoad(testData.get("pap"));
        pap.selectCategory(testData.get("category"));
        pap.selectSubCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("pap"));
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        pap.selectCompany(testData.get("company"));
        jobsPAPPage.selectRole(testData.get("role1"));
        jobsPAPPage.selectEducation(testData.get("edu"));
        jobsPAPPage.selectExperience(testData.get("experiance"));
        jobsPAPPage.selectCompensation(testData.get("salary"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        pap.sendName(testData.get("name"));
        jobsPAPPage.clickPostAdButton();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(pap.validateAd(), "Failed to post ad");
    }

}
