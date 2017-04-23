package com.quikr.app.android.appstatics;

import com.quikr.app.android.AppStaticsTestBase;
import com.quikr.app.android.appStatics.AppStatics;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.quikrX.QuikrXPage;
import com.quikr.app.android.realEstate.realEstateHomePage.RealEstateHomePage;
import com.quikr.app.android.services.ServicesPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by quikr on 14/12/15.
 */
public class AndroidAppStatics extends AppStaticsTestBase {

    /**
     * calculate AppLaunch time
     */
    @Test
    public void CalculateAppLaunchTime() {
        AppStatics appStatics = new AppStatics(driver);
        long start = appStatics.startCounterAppLaunch();
        long end = appStatics.stopCounterInitiatorAppLaunch();
        System.out.println("start time="+ start + "end time="+ end);
        if (!(end == 0))
        {
            long diff = end - start;
            System.out.println((double)diff/1000 +"seconds");
        }
    }

    /**
     * calculate text load time for popular ads
     */
    @Test
    public void calculateTextLoadTime()
    {
        AppStatics appStatics = new AppStatics(driver);
        Hompage hompage=new Hompage(driver);
        hompage.skipBannerMsg();
        hompage.selectLanguageOnLaunch();
        appStatics.openAdsFronHomePage();
        appStatics.hideOverlayLayout();
        appStatics.navigateBack();
        long start=appStatics.startCounterTextLoad();
        long end=appStatics.stopCounterInitiatorTextLoad();
        System.out.println("start time="+ start + "end time="+ end);
        if (!(end == 0))
        {
            long diff = end - start;
            System.out.println((double)diff/1000 +"seconds");
        }
    }

    /**
     * calculate text load time for different categories
     * @param androidSataicsCategory
     */
    @Test(dataProvider = "androidSataicsCategory" ,dataProviderClass = Data.class)
    public void calculateTextLoadTimeForDifferentVerticals(String androidSataicsCategory)
    {
        AppStatics appStatics = new AppStatics(driver);
        Hompage hompage=new Hompage(driver);
        hompage.skipBannerMsg();
        hompage.selectLanguageOnLaunch();
        switch (androidSataicsCategory)
        {
            case "Cars":
                CarsNewPage carsNewPage=new CarsNewPage(driver);
                hompage.selectelementWithoutScroll(androidSataicsCategory, QuikrAppEnums.HOMEPAGE_CATEGORY);
                Assert.assertTrue(carsNewPage.vallidateElementsOnChpbeforeSwipe());
                carsNewPage.clickOnSearchOnChp();
                carsNewPage.SubmitSearch();
                appStatics.hideOverlayLayout();
                appStatics.navigateBack();
                long start=appStatics.startCounterTextLoadCarsSNB();
                long end= appStatics.stopCounterInitiatorTextLoadCarsSnb();

                System.out.println("start time="+ start + "end time="+ end);
                if (!(end == 0))
                {
                    long diff = end - start;
                    System.out.println("for cars SNB Text Load time ="+(double)diff/1000 +"seconds");
                }
                break;
            case "JOBS":
                hompage.selectcategoriesFromHomePageCategoryMenu();
                hompage.selectelementWithoutScroll(androidSataicsCategory, QuikrAppEnums.Home_Categories);
                long jobsStart=appStatics.startCounterTextLoadJobsSNB();
                long jobsEnd=appStatics.stopCounterInitiatorTextLoadJobsSnb();
                System.out.println("start time="+ jobsStart + "end time="+ jobsEnd);
                if (!(jobsEnd == 0))
                {
                    long diff = jobsEnd - jobsStart;
                    System.out.println("for Jobs Text SNB Load time ="+(double)diff/1000 +"seconds");
                }
                break;
            case "REAL":
                RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
                hompage.selectcategoriesFromHomePageCategoryMenu();
                hompage.selectelementWithoutScroll(androidSataicsCategory, QuikrAppEnums.Home_Categories);
                realEstateHomePage.clickOnSearch();
                long realEstateStart=appStatics.startCounterTextLoadRealEstateSNB();
                long realEstateEnd=appStatics.stopCounterInitiatorTextLoadRealEstateSnb();
                System.out.println("start time="+ realEstateStart + "end time="+ realEstateEnd);
                if (!(realEstateEnd == 0))
                {
                    long diff = realEstateEnd - realEstateStart;
                    System.out.println("for Real estate SNB Text Load time ="+(double)diff/1000 +"seconds");
                }
                break;
            case"MOBILES":
                QuikrXPage quikrXPage=new QuikrXPage(driver);
                hompage.selectelementWithoutScroll(androidSataicsCategory,QuikrAppEnums.HOMEPAGE_CATEGORY);
                quikrXPage.openQuikrXfromMobilesAndTabs();
                quikrXPage.clickonExchangePhone();
                appStatics.hideOverlayLayout();
                appStatics.navigateBack();
                long QuikrXStart=appStatics.startCounterTextLoadQuikrXSNB();
                long QuikrXEnd=appStatics.stopCounterInitiatorTextLoadQuikrXSnb();
                System.out.println("start time=" + QuikrXStart + "end time=" + QuikrXEnd);
                if (!(QuikrXEnd == 0))
                {
                    long diff = QuikrXEnd - QuikrXStart;
                    System.out.println("for QuikrX SNB Text Load time ="+(double)diff/1000 +"seconds");
                }
                break;
            case "SERVICES":
                ServicesPage servicesPage = new ServicesPage(driver);
                hompage.selectcategoriesFromHomePageCategoryMenu();
                hompage.selectelementWithoutScroll(androidSataicsCategory, QuikrAppEnums.Home_Categories);
                servicesPage.clickOnOtherServices();
                appStatics.clickOnServicesAutoSuggest();
                appStatics.hideOverlayLayout();
                appStatics.navigateBack();
                long servicesStart=appStatics.startCounterTextLoadServicesSNB();
                long servicesEnd=appStatics.stopCounterInitiatorTextLoadServicesSnb();
                System.out.println("start time=" + servicesEnd + "end time=" + servicesStart);
                if (!(servicesEnd == 0))
                {
                    long diff = servicesEnd - servicesStart;
                    System.out.println("for Services SNB Text Load time ="+(double)diff/1000 +"seconds");
                }


        }


    }
}
