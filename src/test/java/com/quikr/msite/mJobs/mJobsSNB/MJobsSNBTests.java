package com.quikr.msite.mJobs.mJobsSNB;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mJobs.mJobsHome.MJobsHomePage;
import com.quikr.msite.mJobs.mJobsVAP.MJobsVAPPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 30/11/15.
 */
public class MJobsSNBTests extends MTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("mJOBS_TESTDATA_FILE"));

    /*
   MS-358:Click on Apply Button of any ad.
    */
    @Test
    public void validateApplyButtonSNB()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MJobsHomePage mJobsHomePage=new MJobsHomePage(driver);
        MJobsSNBPage mJobsSNBPage=new MJobsSNBPage(driver);

        waitForPageToLoad(testData.get("quikrurl"));
        mHomePage.clickJobs();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsHomePage.setJobRole(testData.get("AccountantRole"));
        mJobsHomePage.search();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.clickApplyFirstJob();
        Assert.assertTrue(mJobsSNBPage.isApplyFormavailable(),"Failed to Apply on Job");
    }


    /*
   MS-360:Click on Ad Title of any Ad.
    */
    @Test
    public void validateClickAdSNB()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MJobsHomePage mJobsHomePage=new MJobsHomePage(driver);
        MJobsSNBPage mJobsSNBPage=new MJobsSNBPage(driver);
        MJobsVAPPage mJobsVAPPage=new MJobsVAPPage(driver);

        waitForPageToLoad(testData.get("quikrurl"));
        mHomePage.clickJobs();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsHomePage.setJobRole(testData.get("AccountantRole"));
        mJobsHomePage.search();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.clickFirstAd();
        waitForPageToLoad(testData.get("jobsurl"));
        Assert.assertTrue(mJobsVAPPage.isJobsVapPageloaded(),"Failed to Load VAP Page");
    }


    /*
    MS-387:Apply Sucessfully for an ad by providing form2 details
     */
    //@Test
    public void validateApplyingForJob()
    {
        
    }

    /*MS-380:SNB Page, Ad Filter : Education
   */
    @Test
    public void validateEducationFilter() {

        MHomePage mHomePage = new MHomePage(driver);
        MJobsHomePage mJobsHomePage=new MJobsHomePage(driver);
        MJobsSNBPage mJobsSNBPage=new MJobsSNBPage(driver);
        MJobsVAPPage mJobsVAPPage=new MJobsVAPPage(driver);

        waitForPageToLoad(testData.get("quikrurl"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickJobs();
        mJobsHomePage.JobsNewUI();
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsHomePage.setJobRole(testData.get("AccountantRole"));
        mJobsHomePage.search();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.clickFilterIconSNBJobs();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.selectFilterbyName(testData.get("FilterSelectEducation"));
        mJobsSNBPage.clickCheckBoxFilter(testData.get("FilterSelectEducation"), 5);
        mJobsSNBPage.clickApplyFilter();
        waitForPageToLoad(testData.get("jobsurl"));
        Assert.assertTrue(Integer.parseInt(mJobsSNBPage.getNumberofJobsfromSNBSearch())> 0, "Failed to Load Job Results , Number of Jobs returned in search Result are  "+mJobsSNBPage.getNumberofJobsfromSNBSearch());
    }



    /*MS-381:SNB Page, Ad Filter : Experience
   */
    @Test
    public void validateExperienceFilter() {

        MHomePage mHomePage = new MHomePage(driver);
        MJobsHomePage mJobsHomePage=new MJobsHomePage(driver);
        MJobsSNBPage mJobsSNBPage=new MJobsSNBPage(driver);
        MJobsVAPPage mJobsVAPPage=new MJobsVAPPage(driver);

        waitForPageToLoad(testData.get("quikrurl"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickJobs();
        mJobsHomePage.JobsNewUI();
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsHomePage.setJobRole(testData.get("AccountantRole"));
        mJobsHomePage.search();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.clickFilterIconSNBJobs();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.selectFilterbyName(testData.get("FilterSelectExperience"));
        mJobsSNBPage.clickCheckBoxFilter(testData.get("FilterSelectExperience"), 5);
        mJobsSNBPage.clickApplyFilter();
        waitForPageToLoad(testData.get("jobsurl"));
        Assert.assertTrue(Integer.parseInt(mJobsSNBPage.getNumberofJobsfromSNBSearch())> 0, "Failed to Load Job Results , Number of Jobs returned in search Result are  "+mJobsSNBPage.getNumberofJobsfromSNBSearch());
    }


    /* MS-383:SNB Page, Ad Filter : Compensation
   */
    @Test
    public void validateSalaryFilter() {

        MHomePage mHomePage = new MHomePage(driver);
        MJobsHomePage mJobsHomePage=new MJobsHomePage(driver);
        MJobsSNBPage mJobsSNBPage=new MJobsSNBPage(driver);
        MJobsVAPPage mJobsVAPPage=new MJobsVAPPage(driver);

        waitForPageToLoad(testData.get("quikrurl"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickJobs();
        mJobsHomePage.JobsNewUI();
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsHomePage.setJobRole(testData.get("AccountantRole"));
        mJobsHomePage.search();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.clickFilterIconSNBJobs();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.selectFilterbyName(testData.get("FilterSelectSalary"));
        mJobsSNBPage.clickCheckBoxFilter(testData.get("FilterSelectSalary"), 8);
        mJobsSNBPage.clickApplyFilter();
        waitForPageToLoad(testData.get("jobsurl"));
        Assert.assertTrue(Integer.parseInt(mJobsSNBPage.getNumberofJobsfromSNBSearch())> 0, "Failed to Load Job Results , Number of Jobs returned in search Result are  "+mJobsSNBPage.getNumberofJobsfromSNBSearch());
    }

    /*  MS-384:SNB Page, Ad Filter : Located In
    */
    @Test
    public void validateLocalityFilter() {

        MHomePage mHomePage = new MHomePage(driver);
        MJobsHomePage mJobsHomePage=new MJobsHomePage(driver);
        MJobsSNBPage mJobsSNBPage=new MJobsSNBPage(driver);
        MJobsVAPPage mJobsVAPPage=new MJobsVAPPage(driver);

        waitForPageToLoad(testData.get("quikrurl"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickJobs();
        mJobsHomePage.JobsNewUI();
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsHomePage.setJobRole(testData.get("AccountantRole"));
        mJobsHomePage.search();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.clickFilterIconSNBJobs();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.selectFilterbyName(testData.get("FilterSelectCity"));
        mJobsSNBPage.selectCityFilterByCityName(testData.get("FilterCityName"));
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.clickFilterIconSNBJobs();
        mJobsSNBPage.selectFilterbyName(testData.get("FilterSelectLocality"));
        mJobsSNBPage.clickCheckBoxFilter(testData.get("FilterSelectLocality"), 8);
        mJobsSNBPage.clickApplyFilter();
        waitForPageToLoad(testData.get("jobsurl"));
        Assert.assertTrue(Integer.parseInt(mJobsSNBPage.getNumberofJobsfromSNBSearch())> 0, "Failed to Load Job Results , Number of Jobs returned in search Result are  "+mJobsSNBPage.getNumberofJobsfromSNBSearch());
    }


    /*  MS-384:SNB Page, Ad Filter : Located In
    */
    @Test
    public void validateRecentSort() {

        MHomePage mHomePage = new MHomePage(driver);
        MJobsHomePage mJobsHomePage=new MJobsHomePage(driver);
        MJobsSNBPage mJobsSNBPage=new MJobsSNBPage(driver);
        MJobsVAPPage mJobsVAPPage=new MJobsVAPPage(driver);

        waitForPageToLoad(testData.get("quikrurl"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickJobs();
        mJobsHomePage.JobsNewUI();
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        //createorEditCookieValue(testData.get("cookiename"), testData.get("JobsNewUIcookie"));
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsHomePage.setCityfromCityPopUpWindow(testData.get("JobsCity"));
        mJobsHomePage.setJobRole(testData.get("AccountantRole"));
        mJobsHomePage.search();
        waitForPageToLoad(testData.get("jobsurl"));
        mJobsSNBPage.clickSortRecent();
        waitForPageToLoad(testData.get("jobsurl"));
        Assert.assertTrue(mJobsSNBPage.validateRecentSort(),"Failed to Sort");
    }



}
