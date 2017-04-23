package com.quikr.app.android.horizontalTests.trendingPriceMeter;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.snb.SnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra singh  on 7/3/16.
 */
public class TrendingPriceMeter extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HOME_TESTDATA_FILE"));

    /**
     * verify trending  is present on homePage
     */
    @Features("Trending PriceMeter")
    @Test(groups = "horizontal")
    public  void verifyTrendingAdIsPresent()
    {
        Hompage hompage=new Hompage(driver);
        Assert.assertTrue(hompage.scrollAndVAlidateTrendingAdIsPresent());
    }

    /**
     * verify price meter is present Under Trending
     */
    @Features("Trending PriceMeter")
    @Test
    public void verifyPriceMeterIsPresentOnTrendindSection()
    {
        Hompage hompage=new Hompage(driver);
        Assert.assertTrue(hompage.scrollAndVAlidateTrendingAdIsPresent());
        Assert.assertTrue(hompage.priceMeterIsPresent(),"price meter is not present");
    }

    /**
     * on clicking viewAll button user is redirected to SNB
     */
    @Features("Trending PriceMeter")
    @Test
    public void validateOnClickingViewAllUserIsRediredtecToSnbPage()
    {
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        Assert.assertTrue(hompage.scrollAndVAlidateTrendingAdIsPresent());
        hompage.clickOnViewAllButton();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(hompage.onClickingViewAllUserIsRedirectedToSnb());
    }

    /**
     * on clicking ad counts use is redirected to snb
     */
    @Features("Trending PriceMeter")
    @Test
    public void validateOnClickingAdCountIsRediredtecToSnbPage()
    {
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        Assert.assertTrue(hompage.scrollAndVAlidateTrendingAdIsPresent());
        for(int i=0;i<3;i++)
        {
            int AdCount=hompage.adCount(i);
            System.out.println(AdCount);
            if (AdCount!=0)
            {
                hompage.clickOnAdCountToViewAllAds(i);
                snbPage.hideOverlayLayout();
                Assert.assertTrue(hompage.onClickingViewAllUserIsRedirectedToSnb());
                snbPage.navigateBack();

            }
        }
    }
    //@Test
    protected void validateSumOfAdsDisplayedEqualsSumInTitle()
    {
        Hompage hompage=new Hompage(driver);
        Assert.assertTrue(hompage.scrollAndVAlidateTrendingAdIsPresent());
        int totalCount=hompage.totalNumberOfAdUnderTrendingAd();
        System.out.println(totalCount);
        int sumOfCount=0;
        for(int i=0;i<3;i++)
        {
            int AdCount=hompage.adCount(i);
            System.out.println(AdCount);
            sumOfCount +=AdCount;
        }
        System.out.println(sumOfCount);
        Assert.assertEquals(sumOfCount,totalCount,"sum of ads doesn't match");
    }

}
