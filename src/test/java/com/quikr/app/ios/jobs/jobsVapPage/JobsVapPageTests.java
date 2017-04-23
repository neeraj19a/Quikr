package com.quikr.app.ios.jobs.jobsVapPage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.dataProvider.Data;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 2/4/16.
 */
public class JobsVapPageTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_JOBSPAGE_TESTDATA_FILE"));

    /**
     * verify  post ad for jobs subcategory
     */
    @Title("seeAll button is present on home screen for quikrX")
    @Test(dataProvider ="jobsSubCat" ,dataProviderClass = Data.class,groups = "horizontal")
    public void  verifyPostAdForJobsSubcategory(String subcategory)
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);

       // homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        switch (subcategory)
        {
            case "Full Time Jobs" :
                homePage.selectCategoryOrSubcategory(testData.get("fullTimeJobs"));
                break;
            case "Work From Home" :
                homePage.selectCategoryOrSubcategory(testData.get("jobsSubCategoryWorkFromHome"));
                break;
            case "Part Time Jobs" :
                homePage.selectCategoryOrSubcategory(testData.get("partTimeJobs"));
                break;
            case "Internships" :
                homePage.selectCategoryOrSubcategory(testData.get("jobsSubCategoryInternship"));
                break;
        }
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
       // papPage.selectLocalityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("localityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnNextButton();
        switch (subcategory)
        {
            case "Full Time Jobs" :
                papPage.setAdTitle(testData.get("titleForFullTimeJobs"));
                break;
            case "Work From Home" :
                papPage.setAdTitle(testData.get("titleForWorkFromHome"));
                break;
            case "Part Time Jobs" :
                papPage.setAdTitle(testData.get("titleForPartTimeJobs"));
                break;
            case "Internships" :
                papPage.setAdTitle(testData.get("titleForInternships"));
                break;
        }
        switch (subcategory)
        {
            case "Full Time Jobs" :
                papPage.setAdDescription(testData.get("descriptionForFullTimeJobs"));
                break;
            case "Work From Home" :
                papPage.setAdDescription(testData.get("descriptionForWorkFromHome"));
                break;
            case "Part Time Jobs" :
                papPage.setAdDescription(testData.get("descriptionForPartTimeJobs"));
                break;
            case "Internships" :
                papPage.setAdDescription(testData.get("descriptionForInternships"));
                break;
        }
        papPage.clickOnRoleInJob();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("jobRoleName"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnEducationInJobs();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("educationType"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnExperience();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("experience"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.setMobileNo(testData.get("mobileNo"));
        papPage.selectPostAdButton();
        Assert.assertEquals(papPage.validatePostAd(), testData.get("successMsgAfterAdPost"), "some issue in posting ad");
    }
}
