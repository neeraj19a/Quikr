package com.quikr.app.android.jobs.jobsPap;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.Constants.MobileConstants;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by rohanbajaj on 12/10/15.
 */
public class JobsPapTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_JOBS_TESTDATA_FILE"));

    /**
     * Android Sanity:2248
     * Verify whethere role field is displayed In Post Ad page after selecting Jobs Category
     */
    @Test
    public void verifyRoleFieldIsDisplayForOfferAdInJobCategory()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("fullTimeJobs"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String cate=papPage.getPapCategory();
        System.out.println(cate);
//        papPage.selectIAmAnEmployer();
        papPage.scroll("Role");
        Assert.assertTrue(papPage.validateRoleFieldForJobCategory(), "Role field is not display for offer ad in job category");
    }
    /**
     * Android Sanity:2248
     * Verify whethere role field is displayed In Post Ad page after selecting Jobs Category
     */

    @Test(dataProvider ="jobsSubcat",dataProviderClass = Data.class)
    public void verifyRoleFieldIsDisplayForWantAdInJobCategory(String subcat)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(subcat, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectINeedAJob();
        Assert.assertTrue(papPage.validateRoleFieldForJobCategory(),"Role field is not display for want ad in  job Subcategory="+ subcat);
    }

    /**
     * Android Sanity:2251
     * Verify the roles available in role field
     *
     */
    @Test
    public void verifyRoleOptionListForPostAnAdForJob()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("fullTimeJobs"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectINeedAJob();
        papPage.selectRoleForJobPostAd();
        Assert.assertTrue(papPage.validateRoleOptionsList(),"role option list is not display while select role ");
    }

    /**
     * android Sanity:2253
     * Verify whether role field is displayed while we create alert in jobs subcat.
     */
    @Test
    public void verifyRoleFieldInCreateAlertForJobs()
    {
        PapPage papPage = new PapPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        //hompage.ApplaunchPopup();
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickOnMyAlerts();
        alertPage.clickOnCreateAlert();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("fullTimeJobs"), QuikrAppEnums.CATEGORY_LOCATION);
        Assert.assertTrue(papPage.validateRoleFieldForJobCategory(),"role option are not display in create alert for job");
    }

    /**Android Sanity2256:Verify vap from success screen after posting a job ad.
     * Android Sanity 2233 :Should be able to Post an Ad without email id for Wanted Jobs
     * ANDROID-922:Tap on Post Free Ad button and verify
     */
    @Test(dataProvider ="jobsSubcat",dataProviderClass = Data.class ,groups = "horizontal")
    public void verifyPostAnAdForJobs(String subcat)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(subcat, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Jobs";
        System.out.println(papPage.getPapCategory());
        papPage.selectINeedAJob();
        papPage.selectRoleForJobPostAd();
        papPage.selectelementWithoutScroll(testData.get("role"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectEducationButton();
        papPage.selectelementWithoutScroll(testData.get("educationOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectExperienceButton();
        papPage.selectelementWithoutScroll(testData.get("experienceOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectCurrentSalaryButton();
        papPage.selectelementWithoutScroll(testData.get("currentSalaryOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("adTitleForAdPost"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("adDescriptionForAdPost"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("nameOfPerson"));
//        Assert.assertTrue(papPage.validateAttributeMissingMessageForEmail(), "Attribute missing message not displayed for email");
        papPage.sendNumber(testData.get("mbNumber"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for jobs Subcat=" + subcat);
//        papPage.selectViewMyAdButton();
//        Assert.assertTrue(papPage.validateRoleAfterPostingAd(testData.get("role")),"value of role is not displayed on vap success page");

    }

    @Test
    public void verifyRoleFieldIsDisplayInEditAdTemplate()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("fullTimeJobs"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Jobs";
        System.out.println(papPage.getPapCategory());
        papPage.selectINeedAJob();
        papPage.selectRoleForJobPostAd();
        papPage.selectelementWithoutScroll(testData.get("role"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectEducationButton();
        papPage.selectelementWithoutScroll(testData.get("educationOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectExperienceButton();
        papPage.selectelementWithoutScroll(testData.get("experienceOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectCurrentSalaryButton();
        papPage.selectelementWithoutScroll(testData.get("currentSalaryOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.setAdTitle(testData.get("adTitleForAdPost"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("adDescriptionForAdPost"),category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("nameOfPerson"));
        papPage.sendNumber(testData.get("mbNumber"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd(), "some issue in posting an ad for job");
        papPage.selectViewMyAdButton();
        Assert.assertTrue(papPage.validatePreviewPostAd(), "can't able to see preview ad post");
        papPage.clickOnEditAd();
        papPage.scroll(testData.get("role"));
        Assert.assertTrue(papPage.validateRoleFieldInEditAd(),"role is not present");
    }

    /**
     * Android Sanity:2258
     * Verify whether Jobs subcats are displayed in list view for new UI while posting ad for jobs
     */

    @Test
    public void verifyJobsRespectiveSubCategoriesIsPresent()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        List<String>Jobssubcat=papPage.checkJobsSubCategories();
        for(int i=0;i<Jobssubcat.size();i++)
        {
            Assert.assertEquals(Jobssubcat.get(i),(MobileConstants.JobsPostAdSubcategories.get(i)),"all sub categories not present"+ MobileConstants.JobsPostAdSubcategories);
        }


    }
}
