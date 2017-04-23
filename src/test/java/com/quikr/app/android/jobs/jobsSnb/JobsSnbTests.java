package com.quikr.app.android.jobs.jobsSnb;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.jobs.JobsPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
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
public class JobsSnbTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_JOBS_TESTDATA_FILE"));
    /**
     * ANDROID-928:"Tap on 'Search' button after selecting the Role and City "
     */
    @Test
    public void verifySearchButtonFunctioningAfterSelectingRoleAndLocation()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        //  hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        //hompage.selectElements(testData.get("jobs"),QuikrAppEnums.HOMEPAGE_CATEGORY);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.validationForJobPageToSwitchSnbPage(), "can  not reach at snb page");
    }

    /**
     * Android Sanity:2239 Verify Sort option is not displayed in SNB page of Jobs
     *  ANDROID-763:Sort button on S&B screen
     */
    @Test(groups = "horizontal")
    public void verifySortOptionAndCreateAlertButtonOnSnbPageForJob()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(!(snbPage.validateSortButtonOnSnbPage()), "on snb page sort button is present");

    }

    /**
     * ANDROID-965:Tap on 'Back' button in th VAP screen and verify
     */

    @Test(groups = "horizontal")
    public void verifyBackButtonAction()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        snbPage.hideOverlayLayout();
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectBackButtonOnVap();
        Assert.assertTrue(snbPage.validationForJobPageToSwitchSnbPage(),"back button can't work properly");
    }

    /**
     *  ANDROID-1160:Tap on locality and verify
     */
    @Test(groups = "horizontal")
    public void verifyLocalityList()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        // hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        snbPage.hideOverlayLayout();
        snbPage.selectFilterButtonOnSnbPage();
        snbPage.selectLocality();
        Assert.assertTrue(jobsPage.validateJobsLocalityList(),"list is not present");
    }

    /**
     *ANDROID-1161:Select the locality from the list and verify
     */
    @Test(groups = "horizontal")
    public void verifyLocalityAfterSet()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        snbPage.selectFilterButtonOnSnbPage();
        snbPage.selectLocality();
        snbPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.CATEGORY_LOCATION);
        alertPage.selectCheckBox();
        snbPage.selectApplyButton();
        String jobsSnbLocality=jobsPage.jobsSnbLoaclity();
        System.out.println(jobsSnbLocality);
        Assert.assertTrue(jobsSnbLocality.contains(testData.get("locality")),"Actual  ="+jobsSnbLocality +"  Expected ="+testData.get("locality"));

    }
/**
 *sort option is not present on snb
 */
    @Test
    public void verifySortOptionIsNotPresentOnSnb()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        Assert.assertTrue(jobsPage.sortOptionIsNotPresentOnSnb(),"Newest First is not present on Snb");

    }

    /**
     *Verify the 'Role' and Number of results is displayed in title bar along with 'Search' and 'Chat' options in the SNB screen
     */
    @Test
    public void verifySnbFields()
    {

        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnFullTimeOnChp();
        List<String >jobsKeySNB=jobsPage.fetchElementsIntoList(testData.get("jobKeyDomElemnt"));
        Assert.assertTrue(jobsKeySNB.contains(testData.get("RoleField")),"role not displayed on snb");
    }
    /**
     * Verify the SNB header contains 'Newest First' and 'Create Free Profile' tabs
     */
    @Test
    public void verifySnbJObsHeader()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnFullTimeOnChp();
        List<String >jobsSNBHeader=jobsPage.fetchElementsIntoList(testData.get("jobsSnbHeaderDomElemnt"));
        Assert.assertTrue(jobsSNBHeader.get(0).equalsIgnoreCase((testData.get("newestFirst")))&&jobsSNBHeader.get(1).equalsIgnoreCase(testData.get("createProfile")));
    }
    /**
     * Verify user can apply to the jobs by tapping 'APPLY' button in the SNB
     */
    @Test
    public void userIsAvbleToApplyFromSnb()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnFullTimeOnChp();
        jobsPage.applyjobFromSnb();
        Assert.assertTrue(jobsPage.userIsInApplyPage(),"on clicking apply button user is not navigated to apply form page");

    }
    /**
     * Verify Filter can be accessed in SNB screen
     */
    @Test
    public void filterCanBeAccessedFromSnb()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AuthPage authPage=new AuthPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnFullTimeOnChp();
        snbPage.selectFilterButtonOnSnbPage();
        Assert.assertTrue(authPage.getHeaderText().equalsIgnoreCase(testData.get("filters")));

    }


}
