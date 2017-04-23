package com.quikr.app.android.jobs.jobsVap;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.jobs.JobsPage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by rohanbajaj on 12/10/15.
 */
public class JobsVapTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_JOBS_TESTDATA_FILE"));
    /**
     *  ANDROID-610:View a Offering Ad for categories other than Jobs and verify
     */
    @Test
    public void verifyApplyButtonForOtherCategoryExceptJobForOfferAds()
    {
        Hompage hompage = new Hompage(driver);
        VapPage vapPage = new VapPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        hompage.selectelementWithoutScroll(testData.get("carsAndBikes"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        for(int i=1;i<2;i++)
        {
            carsNewPage.openAdFromSnb(i);
            snbPage.hideOverlayLayout();
            Assert.assertTrue( vapPage.validateApplyButtonForOtherCategory()," apply button is display for other category for offer ads");
            vapPage.navigateBack();
        }
    }

    /**
     * ANDROID-959:Verify the fields present in the VAP screen of the Ad
     */
    @Test
    public void verifyAllOptionOnVapForJobAd()
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
        Assert.assertTrue(vapPage.validateAllOptionsOnVapForJob(), "all option on vap can't present");
    }

    /**
     * ANDROID-963:Tap on 'Share' button and verify
     */
    @Test(groups = "horizontal")
    public void verifyShareButtonAction()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectShareButton();
        Assert.assertTrue(vapPage.validateShareButtonFunctionality(), "share button does not working properly");
    }

    /**
     * ANDROID-964:Tap on shortlist icon and verify
     */
    @Test(groups = "horizontal")
    public void verifyShortlistButtonActionForJobAd()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        jobsPage.openAdFromJobsSnb(0);
        jobsPage.shortlistJobsAds();
        snbPage.navigateBack();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuPage.selectMyShortlist();
        snbPage.openAdFromSnb(0);
        snbPage.selectFavouriteButton();
        menuPage.navigateBack();
        Assert.assertTrue(menuPage.validateMyShortList(),"shortlistButton can't function properly");
    }

    /**
     * ANDROID-350:Verify recently viewed ads through home for different category or sub category.
     *                                  for job category
     */
    @Test(groups = "horizontal")
    public void verifyRecentlyViedAdForJobCategory()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);;
        jobsPage.openAdFromJobsSnb(0);
        hompage.dismisNotificationAlert();
        String VapTitle=jobsPage.vapAdTitle();
        vapPage.navigateBack();
        vapPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuPage.selectelementWithoutScroll(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        String recentlyViewedAdTitle=menuPage.recentlyViewedAdtitle();
        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));
    }




}
