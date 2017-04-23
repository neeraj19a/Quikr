package com.quikr.msite.mJobs.mJobsVAP;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mJobs.mJobsHome.MJobsHomePage;
import com.quikr.msite.mJobs.mJobsSNB.MJobsSNBPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 30/11/15.
 */
public class MJobsVAPTests extends MTestBase {


    private HashMap<String, String> testData = getTestData(getProperties().get("mJOBS_TESTDATA_FILE"));

    /*
    MS-376:Click on Apply Button for any ad.
    */
    @Test
    public void validateApplyButtonVAP()
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
        Assert.assertTrue(mJobsVAPPage.isJobsVapPageloaded(), "Failed to Apply on Job");
        mJobsVAPPage.clickApplyButtonVAP();
        Assert.assertTrue(mJobsVAPPage.isApplyFormLoadedVAP(),"Failed to Load Apply Form VAP Page");

    }
}
