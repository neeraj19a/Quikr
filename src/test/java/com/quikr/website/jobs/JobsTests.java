package com.quikr.website.jobs;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.electronics.electronicsPap.ElectronicsPapPage;
import com.quikr.website.electronics.electronicsSnb.ElectronicsSnbPage;
import com.quikr.website.electronics.electronicsVap.ElectronicsVapPage;
import com.quikr.website.horizontal.alert.AlertPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.horizontal.myquikr.MyQuikrPage;
import com.quikr.website.jobs.jobsHeader.JobsHeaderPage;
import com.quikr.website.jobs.jobsPAP.JobsPAPPage;
import com.quikr.website.jobs.jobsSNB.JobsSNBPage;
import com.quikr.website.mobilesandtablets.Mobilespage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 31/7/15.
 */
public class JobsTests extends TestBase {
    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("JOBS_TESTDATA_FILE"));

    /**
     * Test case validation for job role
     */

    @Test
    public void ValidateJobRole()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        horizontalSnbPage.selectRoleOption(testData.get("specifiedOptionName"));
        waitForPageToLoad(testData.get("roleUrl"));
        Assert.assertTrue(horizontalSnbPage.validateSpecifiedOptionName(testData.get("specifiedOptionName")), "Can not filter according to role selected");

    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void validateSubcategories(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);

        homePage.selectJobsOptionFromHome();
//        waitForPageToLoad(testData.get("JobsUrl"));
        System.out.println("Category:" + categoriesForJobs);
        jobsPage.selectJobsCategory(categoriesForJobs);
        if (!(categoriesForJobs.equals("Placement - Recruitment Agencies")))
            Assert.assertEquals(categoriesForJobs, jobsPage.getTextOfJobCategory(), "Job categories do not match");
        else
            Assert.assertEquals("Placement - Recruitment Agenc...", jobsPage.getTextOfJobCategory(), "Job categories do not match");
    }

    /**
     * Click on apply buttons on Jobs SNB
     * swatantra singh
     */
    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void checkApplyButton(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        System.out.println("Category:" + categoriesForJobs);
        jobsPage.selectJobsCategory(categoriesForJobs);
        Assert.assertTrue(horizontalSnbPage.validateApplyButton());
    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void createAlertWantAd(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        alertPage.setCategory(testData.get("category"));
        alertPage.setSubcategory(categoriesForJobs);
        alertPage.setAdType(testData.get("wantAd"));
        alertPage.setRole(testData.get("role"));
        if(!((categoriesForJobs.equals("Placement - Recruitment Agencies")) ||(categoriesForJobs.equals("Non-Profit NGOs"))) )
        {
            alertPage.setEducation(testData.get("education"));
            alertPage.setExperience(testData.get("experience"));
        }
        alertPage.setLocality(testData.get("locality"));
        alertPage.setEmail(testData.get("emailId"));
        alertPage.setPhoneNumber(testData.get("phoneNumber"));
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateCreateAlert(), "Alert not created");

    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void createAlertOfferAd(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        alertPage.setCategory(testData.get("category"));
        alertPage.setSubcategory(categoriesForJobs);
        alertPage.setAdType(testData.get("offerAd"));
        alertPage.setRole(testData.get("role"));
        if(!((categoriesForJobs.equals("Placement - Recruitment Agencies")) ||(categoriesForJobs.equals("Non-Profit NGOs"))) )
        {
            alertPage.setEducation(testData.get("education"));
            alertPage.setExperience(testData.get("experience"));
        }
        alertPage.setLocality(testData.get("locality"));
        alertPage.setEmail(testData.get("emailId"));
        alertPage.setPhoneNumber(testData.get("phoneNumber"));
        alertPage.submitAlert();
        Assert.assertTrue(alertPage.validateCreateAlert(), "Alert not created");

    }

    @Test
    public void validateJobCompensation()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        horizontalSnbPage.selectCompensationOptions(testData.get("compensationOptionName"));
        waitForPageToLoad(testData.get("compensationUrl"));
        Assert.assertTrue(horizontalSnbPage.validateCompensationOption(testData.get("compensationOptionFullName")), "Can not filter according to compensation option selected");
    }

    @Test
    public void validateJobExperience()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        horizontalSnbPage.selectExperienceOptions(testData.get("experienceOptionName"));
        waitForPageToLoad(testData.get("experienceUrl"));
        Assert.assertTrue(horizontalSnbPage.validateExperienceOption(testData.get("experienceOptionName")), "Can not filter according to experience option selected");

    }

    @Test
    public void applyForJob()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        horizontalSnbPage.validateApplyButton();
        horizontalSnbPage.selectRole(testData.get("role"));
        horizontalSnbPage.replyemail(testData.get("emailId"));
        horizontalSnbPage.replyNumber(testData.get("number"));
        horizontalSnbPage.submitReply();
        Assert.assertTrue(horizontalSnbPage.validateReply());

    }

    @Test
    public void validateJobEducation()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        horizontalSnbPage.selectEducationOptions(testData.get("educationOptionName"));
        waitForPageToLoad(testData.get("educationUrl"));
        Assert.assertTrue(horizontalSnbPage.validateEducationOption(), "Can not filter according to experience option selected");
    }

    @Test
    public void validateJobLocality()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        horizontalSnbPage.selectLocalityOptions(testData.get("localityOptionName"));
        waitForPageToLoad(testData.get("localityUrl"));
        validateLocalityOption(3, testData.get("localityOptionName"));
    }

    @Test
    public void applyJobsWithoutLoginFromVap()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
//        waitForPageToLoad(testData.get("fulltimejobsurl"));
        horizontalSnbPage.OpenAd();
        electronicsVapPage.clickOnApplyJobs();
        electronicsVapPage.selectRole(testData.get("role1"));
        electronicsVapPage.sendEmailId(testData.get("emailId"));
        electronicsVapPage.sendNumber(testData.get("number"));
        electronicsVapPage.submitReply();
        Assert.assertTrue(electronicsVapPage.validateJobsApplied());
    }

    @Test
    public void applyJobsWithLoginFromVAP()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        */headerPage.letsLogin("",(testData.get("city")),testData.get("emailId"), testData.get("password"));
        waitForPageToLoad(testData.get("myQuikrUrl"));
        headerPage.clickOnQuikrLogo();
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        horizontalSnbPage.OpenAd();
        electronicsVapPage.clickOnApplyJobs();
        electronicsVapPage.selectRole(testData.get("role1"));
        electronicsVapPage.submitReply();
        Assert.assertTrue(electronicsVapPage.validateJobsApplied());
    }

    @Test()
    public void fetchFullTimeJobsFromRightNavFilters()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        AlertPage alertPage = new AlertPage(driver);


        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsOldUIcookie"));
        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        //waitForPageToLoad(testData.get("JobsUrl"));
        alertPage.setRole(testData.get("role"));
        alertPage.setEducation(testData.get("education"));
        alertPage.setExperience(testData.get("experience"));
        jobsPage.findJobsFromRightNavFilters();
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        Assert.assertEquals(jobsPage.getTextOfJobCategory(), testData.get("fullTimeJobs"), "Category not matching");
        Assert.assertTrue(horizontalSnbPage.verifyCheckedOptionInSidebar(QuikrEnums.ROLE, testData.get("role")), "Roles not matching");
        Assert.assertTrue(horizontalSnbPage.verifyCheckedOptionInSidebar(QuikrEnums.EDUCATION, testData.get("education")), "Education not matching");
        Assert.assertTrue(horizontalSnbPage.verifyCheckedOptionInSidebar(QuikrEnums.EXPERIENCE, testData.get("experience")), "Experience not matching");
    }

    @Test
    public void fetchPartTimeJobsFromRightNavFilters()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        AlertPage alertPage = new AlertPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectPartTimeJobsOption();
        alertPage.setRole(testData.get("role"));
        alertPage.setEducation(testData.get("education"));
        alertPage.setExperience(testData.get("experience"));
        jobsPage.findJobsFromRightNavFilters();
        waitForPageToLoad(testData.get("partTimeJobsUrl"));
        Assert.assertEquals(jobsPage.getTextOfJobCategory(), testData.get("partTimeJobs"), "Category not matching");
        Assert.assertTrue(horizontalSnbPage.verifyCheckedOptionInSidebar(QuikrEnums.ROLE, testData.get("role")), "Roles not matching");
        Assert.assertTrue(horizontalSnbPage.verifyCheckedOptionInSidebar(QuikrEnums.EDUCATION, testData.get("education")), "Education not matching");
        Assert.assertTrue(horizontalSnbPage.verifyCheckedOptionInSidebar(QuikrEnums.EXPERIENCE, testData.get("experience")), "Experience not matching");
    }


    @Test()
    public void validateEditAlertCreatedOnHomePage()
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("", (testData.get("city")),testData.get("emailId"), testData.get("password"));
        waitForPageToLoad(testData.get("myQuikrUrl"));
        headerPage.selectMyQuikrOption(testData.get("myQuikrOption"));
        waitForPageToLoad(testData.get("myQuikrAlertUrl"));
        myQuikrPage.selectEditAlertButton();
        alertPage.setLocalityInAlertPopUp(testData.get("changeLocality"));
        alertPage.setSubmitButtonInAlertPopUp();
        Assert.assertTrue(myQuikrPage.validateEditAlert(),"Your Alert has been updated successfully msg is not display");
    }

    @Test
    public void validateJobSubCategory()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        Assert.assertTrue(horizontalSnbPage.verifyJobSubCategory(),"Can not be filter as selected job subcategory");
    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void unsubscribeAlertNotHappy(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */
        headerPage.letsLogin("",(testData.get("city")),testData.get("emailId"), testData.get("password"));
        waitForPageToLoad(testData.get("myQuikrUrl"));
        headerPage.clickOnQuikrLogo();
        alertPage.setCategory(testData.get("category"));
        alertPage.setSubcategory(categoriesForJobs);

        alertPage.setAdType(testData.get("wantAd"));
        alertPage.setRole(testData.get("role"));

        if(!((categoriesForJobs.equals("Placement - Recruitment Agencies")) ||(categoriesForJobs.equals("Non-Profit NGOs"))) )
        {
            alertPage.setEducation(testData.get("education"));
            alertPage.setExperience(testData.get("experience"));
        }

        alertPage.submitAlert();
        waitForPageToLoad(testData.get("recommendationsUrl"));
        Assert.assertTrue(alertPage.unsubscribeAlertNotHappy(testData.get("loginpassword")),"Alert not unsubscribed");
    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void unsubscribeAlertFoundJob(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("", (testData.get("city")),testData.get("emailId"), testData.get("password"));
        waitForPageToLoad(testData.get("myQuikrUrl"));
        headerPage.clickOnQuikrLogo();
        alertPage.setCategory(testData.get("category"));
        alertPage.setSubcategory(categoriesForJobs);
        alertPage.setAdType(testData.get("wantAd"));
        alertPage.setRole(testData.get("role"));
        if(!((categoriesForJobs.equals("Placement - Recruitment Agencies")) ||(categoriesForJobs.equals("Non-Profit NGOs"))) )
        {
            alertPage.setEducation(testData.get("education"));
            alertPage.setExperience(testData.get("experience"));
        }

        alertPage.submitAlert();
        waitForPageToLoad(testData.get("recommendationsUrl"));
        Assert.assertTrue(alertPage.unsubscribeAlertFoundJob(testData.get("loginpassword")),"Alert not unsubscribed");
    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void unsubscribeAlertOtherReason(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("", (testData.get("city")),testData.get("emailId"), testData.get("password"));
        waitForPageToLoad(testData.get("myQuikrUrl"));
        headerPage.clickOnQuikrLogo();
        alertPage.setCategory(testData.get("category"));
        alertPage.setSubcategory(categoriesForJobs);
        alertPage.setAdType(testData.get("wantAd"));
        alertPage.setRole(testData.get("role"));

        if(!((categoriesForJobs.equals("Placement - Recruitment Agencies")) ||(categoriesForJobs.equals("Non-Profit NGOs"))) )
        {
            alertPage.setEducation(testData.get("education"));
            alertPage.setExperience(testData.get("experience"));
        }

        alertPage.submitAlert();
        waitForPageToLoad(testData.get("recommendationsUrl"));
        Assert.assertTrue(alertPage.unsubscribeAlertOtherReason(testData.get("loginpassword")),"Alert not unsubscribed");
    }

    @Test
    public void validateOfferAd()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        Assert.assertTrue(horizontalSnbPage.verifyOfferAd(), "Can not  be present an offer ad ");
    }

    @Test
    public void validateFilterJobsRole()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        horizontalSnbPage.selectRoleOption(testData.get("role"));
        Assert.assertTrue(horizontalSnbPage.validateSpecifiedOptionName(testData.get("role")));

    }

    @Test
    public void changeCategoryFromSnb()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        Mobilespage mobilespage =new Mobilespage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("mobilesUrl"));
        mobilespage.selectMobilePhones();
        waitForPageToLoad(testData.get("Mobilepage"));
        horizontalSnbPage.clickOncategories();
        waitForPageToLoad(testData.get("allcategory"));
        horizontalSnbPage.selectcategories(testData.get("category"));
        Assert.assertTrue(horizontalSnbPage.validateCategory(testData.get("JobsUrl")));
    }

    //Test failing since the new UI doesn't have any convert to premium link in PAP confirmation page.
    @Test()
    public void postPremiumADWithoutLogin()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage pap=new ElectronicsPapPage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        JobsHeaderPage jobsHeaderPage = new JobsHeaderPage(driver);


        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectJobsOptionFromHome();
        jobsHeaderPage.clickPostAdbuttonpresent();
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role1"));
        jobsPAPPage.setMinSalary(testData.get("minSalary"));
        jobsPAPPage.setMaxSalary(testData.get("maxSalary"));
        String email=getRandomString(9)+"@gmail.com";
        jobsPAPPage.setEmail(email);
        String mobnumber="98"+getRandomInteger(8);
        jobsPAPPage.setRandomMobNumber(mobnumber);
        String companyName="ABC company"+getRandomString(7);
        jobsPAPPage.setCompanyName(companyName);
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        jobsPAPPage.clickPostAdStepOne();
        jobsPAPPage.clickPostAdStepTwo();
        waitForPageToLoad(testData.get("adSuccessPage"));
        Assert.assertTrue(pap.validateAd(), "Failed to post ad");
        pap.clickConvertToPremiumLink();
        waitForPageToLoad(testData.get("upgradeAdToPremiumUrl"));
        pap.selectPremiumAdPackage(QuikrEnums.valueOf(testData.get("premiumAdPackageBoth")));
        pap.selectPaymentMethodForPremiumAd(QuikrEnums.valueOf(testData.get("premiumAdPaymentByCheque")));
        pap.clickPostPremiumAdButton();
        waitForPageToLoad(testData.get("paymentByChequeConfirmUrl"));
        Assert.assertTrue(pap.validatePremiumAdWithChequeSuccess(), "Not able to submit premium ad with cheque");
    }


    @Test()
    public void postPremiumADWithLoginPaymentAsCheque()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage pap=new ElectronicsPapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        JobsHeaderPage jobsHeaderPage = new JobsHeaderPage(driver);

        //homePage.SelectCity(testData.get("city"));
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
        pap.clickConvertToPremiumLink();
        waitForPageToLoad(testData.get("upgradeAdToPremiumUrl"));
        pap.selectPremiumAdPackage(QuikrEnums.valueOf(testData.get("premiumAdPackageBoth")));
        pap.selectPaymentMethodForPremiumAd(QuikrEnums.valueOf(testData.get("premiumAdPaymentByCheque")));
        pap.clickPostPremiumAdButton();
        waitForPageToLoad(testData.get("paymentByChequeConfirmUrl"));
        Assert.assertTrue(pap.validatePremiumAdWithChequeSuccess(), "Not able to submit premium ad with cheque");
    }

    @Test()
    public void postPremiumADWithLoginPaymentAsOnline()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage pap=new ElectronicsPapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        JobsHeaderPage jobsHeaderPage = new JobsHeaderPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */
        headerPage.letsLogin("",(testData.get("city")),testData.get("emailId"), testData.get("loginpassword"));
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
        pap.clickConvertToPremiumLink();
        waitForPageToLoad(testData.get("upgradeAdToPremiumUrl"));
        pap.selectPremiumAdPackage(QuikrEnums.valueOf(testData.get("premiumAdPackageBoth")));
        pap.selectPaymentMethodForPremiumAd(QuikrEnums.valueOf(testData.get("premiumAdPaymentByOnline")));
        pap.clickPostPremiumAdButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("paymentByOnlineConfirmUrl")),
                "Not able to submit premium ad with online payment, getting url : " + pap.getCurrentLocation());
    }

    @Test()
    public void postAdWithBannedWords()
    {
        JobsPAPPage jobsPAPPage = new JobsPAPPage(driver);
        HomePage homePage = new HomePage(driver);
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);

        String adTitle = testData.get("adTitle");
        String bannedWords = testData.get("bannedWords");
        String role = testData.get("role");
        String edu = testData.get("education");
        String exp = testData.get("experience");
        String desc = testData.get("description");
        String mob = testData.get("number");

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHeaderPage.clickPostAdbuttonpresent();
        waitForPageToLoad(testData.get("cityUrl"));
        jobsPAPPage.postAdTitle(adTitle+" "+bannedWords);
        jobsPAPPage.selectRole(role);
        jobsPAPPage.selectEducation(edu);
        jobsPAPPage.selectExperience(exp);
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(desc+ " "+bannedWords);
        jobsPAPPage.setRandomMobNumber(mob);
        jobsPAPPage.clickPostAdButton();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertEquals(jobsPAPPage.isJobPosted(), false ,"Failed to Load Successfull Job Post Page");
    }

    @Test()
    public void postADWithDuplicateContent()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsPapPage pap=new ElectronicsPapPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        JobsHeaderPage jobsHeaderPage = new JobsHeaderPage(driver);

        //homePage.SelectCity(testData.get("city"));

        for(int i=0; i<=1; i++)
        {
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
            headerPage.clickOnQuikrLogo();
        }
    }

    @Test
    public void validateApplyformElementsOfRecomendedAds()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("JobsUrl"));
        jobsPage.selectJobsCategory(testData.get("fullTimeJobs"));
        waitForPageToLoad(testData.get("fulltimejobsurl"));
        String title = horizontalSnbPage.OpenAd();
        waitForPageToLoad(title.split(" ")[0]);
        electronicsVapPage.openRecomendedAdsFromVap();
        electronicsVapPage.clickOnApplyJobs();
        Assert.assertTrue(electronicsVapPage.validateApplyFormElements(), "validating apply job form failed");
    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void unsubscribeOfferAdAlertNotHappy(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("", (testData.get("city")),testData.get("emailId"), testData.get("password"));
        waitForPageToLoad(testData.get("myQuikrUrl"));
        headerPage.clickOnQuikrLogo();
        alertPage.setCategory(testData.get("category"));
        alertPage.setSubcategory(categoriesForJobs);
        alertPage.setAdType(testData.get("offerAd"));
        alertPage.setRole(testData.get("role"));
        if(!((categoriesForJobs.equals("Placement - Recruitment Agencies")) ||(categoriesForJobs.equals("Non-Profit NGOs"))) )
        {
            alertPage.setEducation(testData.get("education"));
            alertPage.setExperience(testData.get("experience"));
        }
//        alertPage.setLocality(testData.get("locality"));
//        alertPage.setEmail(testData.get("emailId"));
//        alertPage.setPhoneNumber(testData.get("phoneNumber"));
        alertPage.submitAlert();
        waitForPageToLoad(testData.get("recommendationsUrl"));
        Assert.assertTrue(alertPage.unsubscribeAlertNotHappy(testData.get("loginpassword")), "Alert not unsubscribed");
    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void unsubscribeOfferAdAlertFoundJob(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */headerPage.letsLogin("", (testData.get("city")),testData.get("emailId"), testData.get("password"));
        waitForPageToLoad(testData.get("myQuikrUrl"));
        headerPage.clickOnQuikrLogo();
        alertPage.setCategory(testData.get("category"));
        alertPage.setSubcategory(categoriesForJobs);
        alertPage.setAdType(testData.get("offerAd"));
        alertPage.setRole(testData.get("role"));

        if(!((categoriesForJobs.equals("Placement - Recruitment Agencies")) ||(categoriesForJobs.equals("Non-Profit NGOs"))) )
        {
            alertPage.setEducation(testData.get("education"));
            alertPage.setExperience(testData.get("experience"));
        }

        alertPage.submitAlert();
        waitForPageToLoad(testData.get("recommendationsUrl"));
        Assert.assertTrue(alertPage.unsubscribeAlertFoundJob(testData.get("loginpassword")), "Alert not unsubscribed");
    }

    @Test(dataProvider = "getCategoriesForJobs", dataProviderClass = Data.class)
    public void unsubscribeOfferAdAlertOtherReason(String categoriesForJobs)
    {
        HomePage homePage = new HomePage(driver);
        AlertPage alertPage = new AlertPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        /*//homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        */
        headerPage.letsLogin("", (testData.get("city")),testData.get("emailId"), testData.get("password"));
        waitForPageToLoad(testData.get("myQuikrUrl"));
        headerPage.clickOnQuikrLogo();
        alertPage.setCategory(testData.get("category"));
        alertPage.setSubcategory(categoriesForJobs);
        alertPage.setAdType(testData.get("offerAd"));
        alertPage.setRole(testData.get("role"));

        if(!((categoriesForJobs.equals("Placement - Recruitment Agencies")) ||(categoriesForJobs.equals("Non-Profit NGOs"))) )
        {
            alertPage.setEducation(testData.get("education"));
            alertPage.setExperience(testData.get("experience"));
        }

        alertPage.submitAlert();
        waitForPageToLoad(testData.get("recommendationsUrl"));
        Assert.assertTrue(alertPage.unsubscribeAlertOtherReason(testData.get("loginpassword")), "Alert not unsubscribed");

    }

    //Runs on UAT, WEB-641:MUID - Delhi -PAP
    @Test()
    public void verifyMobileNumMandatoryPAP()
    {
        HomePage homePage = new HomePage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        //homePage.SelectCity(testData.get("delhicity"));
        homePage.clickPostFreeAdButton();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role"));
        jobsPAPPage.selectEducation(testData.get("education"));
        jobsPAPPage.selectExperience(testData.get("experience"));
        electronicsPapPage.selectCompensation(testData.get("compensationOptionName"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        electronicsPapPage.clickPostAdButton();
        Assert.assertTrue(jobsPAPPage.validateMobileNumMandatoryPAP(), "Mobile Number is not mandatory for Delhi PAP Page");
        Assert.assertEquals(jobsPAPPage.validateErrorMsgMobNumber(), "Please enter Mobile No");
    }

    //WEB-630:Desktop : MUID - CHAT on S&B
   // @Test()
    public void verifyValidationMessageChatSNB()
    {
        HomePage homePage = new HomePage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        JobsSNBPage jobsSNBPage=new JobsSNBPage(driver);

        //homePage.SelectCity(testData.get("delhicity"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        homePage.selectJobsOptionFromHome();
        jobsPage.searchJobs(testData.get("jobRole"));
        jobsSNBPage.clickChatButtonSNBAds();
        jobsSNBPage.setChatNameJobsSNB(testData.get("nameChatSNB"));
        jobsSNBPage.clickChatNowbutton();
        Assert.assertEquals(jobsSNBPage.chatErrorMessage(), testData.get("chatErrorMsg"));

    }
    //WEB-625:Desktop : PAP-MUID Validation Verification
    @Test()
    public void verifyValidationMsgJobsPAP()
    {
        HomePage homePage = new HomePage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        //homePage.SelectCity(testData.get("city"));
        homePage.clickPostFreeAdButton();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role"));
        jobsPAPPage.selectEducation(testData.get("education"));
        jobsPAPPage.selectExperience(testData.get("experience"));
        electronicsPapPage.selectCompensation(testData.get("compensationOptionName"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        jobsPAPPage.clickPostAdButton();
        Assert.assertEquals(jobsPAPPage.validateErrorMsgEmailandMobNum(), testData.get("mandatoryValidationMsgJob"), "Error message for Mandatory fields is not present");
    }

    //WEB-628:Desktop : PAP-MUID- Premuim ads
    @Test()
    public void verifyMandatoryFieldPremiumAdsJobsPAP()
    {
        HomePage homePage = new HomePage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        //homePage.SelectCity(testData.get("city"));
        homePage.clickPostFreeAdButton();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role"));
        jobsPAPPage.selectEducation(testData.get("education"));
        jobsPAPPage.selectExperience(testData.get("experience"));
        electronicsPapPage.selectCompensation(testData.get("compensationOptionName"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        jobsPAPPage.clickPostAdButton();
        jobsPAPPage.clickUpgradeButton();
        Assert.assertTrue(jobsPAPPage.validateEmailMandatory(), "Email id is not mandatory for Premium Ads");
    }

    /*WEB-629:Desktop : MUID - Edit Ad
    */@Test()
    public void validateDummyEmail()
    {
        HomePage homePage = new HomePage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        //homePage.SelectCity(testData.get("city"));
        homePage.clickPostFreeAdButton();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role"));
        jobsPAPPage.selectEducation(testData.get("education"));
        jobsPAPPage.selectExperience(testData.get("experience"));
        jobsPAPPage.selectCompensation(testData.get("compensationOptionName"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        jobsPAPPage.setRandomMobNumber(("9" + getRandomInteger(9)));
        jobsPAPPage.clickPostAdButton();
        jobsPAPPage.clickEditAd();
        Assert.assertTrue(jobsPAPPage.getDummyEmailId().contains("quikrAnonLA"), "Random Email is not getting generated");
    }


    /*WEB-626:Desktop : PAP-MUID-Post ad with only mobile number
    */
    @Test()
    public void validateMobileNumMandatoryPostAd()
    {
        HomePage homePage = new HomePage(driver);
        JobsPAPPage jobsPAPPage=new JobsPAPPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        //homePage.SelectCity(testData.get("delhicity"));
        homePage.clickPostFreeAdButton();
        electronicsPapPage.selectCategory(testData.get("category"));
        electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        jobsPAPPage.postAdTitle(testData.get("adTitle"));
        jobsPAPPage.selectRole(testData.get("role"));
        jobsPAPPage.selectEducation(testData.get("education"));
        jobsPAPPage.selectExperience(testData.get("experience"));
        electronicsPapPage.selectCompensation(testData.get("compensationOptionName"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(testData.get("description"));
        electronicsPapPage.setEmail(testData.get("emailId"));
        jobsPAPPage.clickPostAdButton();
        Assert.assertTrue(jobsPAPPage.validateMobileNumMandatoryPAP(), "Mobile Number is not mandatory for Delhi PAP Page");
        Assert.assertEquals(jobsPAPPage.validateErrorMsgMobNumber(), "Please enter Mobile No");
        jobsPAPPage.setRandomMobNumber(("9" + getRandomInteger(9)));
        jobsPAPPage.clickPostAdButton();
    }


}