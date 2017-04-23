package com.quikr.app.android.horizontalTests.similarAdsOnVap;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.jobs.JobsPage;
import com.quikr.app.android.realEstate.realEstateHomePage.RealEstateHomePage;
import com.quikr.app.android.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.android.realEstate.realEstateVapPage.RealEstateVapPage;
import com.quikr.app.android.services.ServicesPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 4/5/16.
 */
public class SimilarAdsOnVApTests extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HORIZONTAL_TESTDATA_FILE"));

    @Stories("Similar Ads")
    @Test(dataProvider = "category", dataProviderClass = Data.class)
    public void validateSimilarAdsArePresentOnVApAcrossVerticals(String cat)
    {
        Hompage hompage=new Hompage(driver);
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        VapPage vapPage=new VapPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateVapPage realEstateVapPage = new RealEstateVapPage(driver);
        System.out.println(cat);
        switch (cat)
        {
            case "Cars":

                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                carsNewPage.SubmitSearch();
                snbPage.hideOverlayLayout();
                carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
                carsNewPage.openAdFromSnb(0);
                snbPage.hideOverlayLayout();
                break;
            case "Mobiles":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMobilesSubcat();
                snbPage.hideOverlayLayout();
                escrowPage.selectCityAsCurrentCityOnVapEscrow();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                break;
            case "ELECTRONICS":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                snbPage.clickOnLifeStyleSubCAtOnCHP();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                break;
            case"ENTERTAINMENT":

                hompage.selectelementWithoutScroll(testData.get("showMoreCategories"), QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMoreButton();//needs improvement
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                break;
            case "EDUCATION":
                hompage.selectelementWithoutScroll(testData.get("showMoreCategories"), QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                break;

            case"Homes":

                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                realEstateHomePage.clickOnResidential();
                realEstateHomePage.clickOnSearch();
                realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
                snbPage.hideOverlayLayout();
                realEstateSnbPage.openAdFromSnb();
                realEstateVapPage.dismissShareAdPopUpOnVAp();
                break;

            case"JOBS":

                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                jobsPage.clickOnSelectRole();
                jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
                snbPage.hideOverlayLayout();
                jobsPage.openAdFromJobsSnb(0);
                realEstateVapPage.dismissShareAdPopUpOnVAp();
                vapPage.scroll(testData.get("similarAds"));
                Assert.assertTrue(vapPage.similarAdsText().equalsIgnoreCase(testData.get("similarAds")),"similar Ads not displayed for"+cat);
                break;
            case"SERVICES":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                servicesPage.scroll((testData.get("browseAdsOption")));
                servicesPage.clickOnBrowseAds();
                servicesPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                break;
            case "COMMUNITY":

                hompage.selectelementWithoutScroll(testData.get("showMoreCategories"), QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(testData.get("showMoreCategories"),QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(0);
                snbPage.hideOverlayLayout();

                break;
            case"Lifestyle":

                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                snbPage.clickOnLifeStyleSubCAtOnCHP();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();


        }
        vapPage.scroll(testData.get("similarAds"));
        if (vapPage.validateSimilarAdLinkOnVap())
            Assert.assertTrue(vapPage.similarAdsText().equalsIgnoreCase(testData.get("similarAds")),"similar Ads not displayed for"+cat);
        else
            System.out.println("similar Ads not displayed for"+cat);

    }

    /**
     * Verify UI, when less than 5 or 5 similar ads are displayed
     */
    @Stories("Similar Ads")
   // @Test
    public void verifyViewAllAsIsNOtDisplayedIfAdCountIsLessThan5()
    {   Hompage hompage=new Hompage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        VapPage vapPage=new VapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        vapPage.scroll(testData.get("similarAds"));
        if (vapPage.getSimilarAdCountOnVap()==5)
            Assert.assertTrue(vapPage.validateViewAllAdIsPresent(),"view all ad button is not present"+vapPage.getSimilarAdCountOnVap());
        else
            System.out.println("Less tahn five ads are present"+vapPage.getSimilarAdCountOnVap());


    }
}
