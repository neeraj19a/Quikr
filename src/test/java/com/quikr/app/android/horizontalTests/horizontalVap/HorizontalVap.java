package com.quikr.app.android.horizontalTests.horizontalVap;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.jobs.JobsPage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.realEstate.realEstateHomePage.RealEstateHomePage;
import com.quikr.app.android.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.android.realEstate.realEstateVapPage.RealEstateVapPage;
import com.quikr.app.android.services.ServicesPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Swatantra singh on 11/1/16.
 */
public class HorizontalVap extends AppAndroidTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HORIZONTAL_TESTDATA_FILE"));

    /**
     * Verify report ad option is available on VAP. Taping on it should prompt login screen if user is not logged in.
     * @param cat
     */
    @Stories("VAP")
    @Title(" Verify report ad option is available on VAP")
    @Test(dataProvider = "category", dataProviderClass = Data.class)
    public void validateReportAdISPresentOnVAp(String cat)
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

//        hompage.selectcategoriesFromHomePageCategoryMenu();
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
                try{
                    Thread.sleep(2000);
                }
                catch (Exception e)
                {}
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(!vapPage.reportAdLinkIsPresent(), "reoprt Ad link is present on cars vap");
                break;
            case "Mobiles":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMobilesSubcat();
                snbPage.hideOverlayLayout();
                escrowPage.selectCityAsCurrentCityOnVapEscrow();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Mobiles vap");
                break;
            case "ELECTRONICS":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectelementWithoutScroll("Home Appliances",QuikrAppEnums.ELECTRONICS_SUBCATEGORIES);
                escrowPage.selectelementWithoutScroll("Refrigerators",QuikrAppEnums.ELECTRONICS_SUBCATEGORIES);
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Mobiles vap");
                break;
            case"ENTERTAINMENT":

                hompage.selectelementWithoutScroll(testData.get("showMoreCategories"), QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                escrowPage.selectMoreButton();//needs improvement
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Mobiles vap");
                break;
            case "EDUCATION":

                hompage.selectelementWithoutScroll(testData.get("showMoreCategories"), QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Mobiles vap");
                break;
            // case"PETS":
            //case"MATRIMONIAL":
            case"Homes":

                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                realEstateHomePage.clickOnResidential();
                realEstateHomePage.clickOnSearch();
                realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
                snbPage.hideOverlayLayout();
                realEstateSnbPage.openAdFromSnb();
                Integer swipeCord[]=realEstateVapPage.CordinatesForVErticalSwipe("vapChat","headerActionBAr");
                realEstateVapPage.verticalSwipeWithCordinates(swipeCord[0],swipeCord[1]);
                realEstateVapPage.verticalSwipeWithCordinates(swipeCord[0],swipeCord[1]);
                Assert.assertTrue(!vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  present on RealEstate vap");
                break;

            case"JOBS":

                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                jobsPage.clickOnSelectRole();
                jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
                snbPage.hideOverlayLayout();
                jobsPage.openAdFromJobsSnb(0);
                carsNewPage.scroll("Ad Views");
                Assert.assertTrue(!vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  present on jobs vap");
                break;
            case"SERVICES":

                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                servicesPage.scroll(("Browse Ads"));
                servicesPage.clickOnBrowseAds();
                servicesPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                vapPage.scrollToReportAd();
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Services vap");


                break;
            case "COMMUNITY":

                hompage.selectelementWithoutScroll(testData.get("showMoreCategories"), QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(testData.get("showMoreCategories"),QuikrAppEnums.Home_Categories);
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(0);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on community vap");
                break;
            case"Lifestyle":

                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                snbPage.clickOnLifeStyleSubCAtOnCHP();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Home&LifeStyle vap");

        }
    }


    // @Test(dataProvider = "category", dataProviderClass = Data.class)
    public void validateUserIsAbleToReportAdfWithoutLogin(String cat)
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
        AuthPage authPage=new AuthPage(driver);
//        hompage.selectcategoriesFromHomePageCategoryMenu();
        System.out.println(cat);
        switch (cat)
        {
            case "Cars":
                hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                carsNewPage.clickOnSearchOnChp();
                carsNewPage.SubmitSearch();
                snbPage.hideOverlayLayout();
                carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
                carsNewPage.openAdFromSnb(0);
                snbPage.hideOverlayLayout();
                try{
                    Thread.sleep(2000);
                }
                catch (Exception e)
                {}
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(!vapPage.reportAdLinkIsPresent(), "reoprt Ad link is present on cars vap");
                break;
            case "Mobiles":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMobilesSubcat();
                snbPage.hideOverlayLayout();
                escrowPage.selectCityAsCurrentCityOnVapEscrow();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Mobiles vap");
                vapPage.clickOnReportAd();
                vapPage.loginWhenRedirectedToLoginPAge(testData.get("email"), testData.get("password"));
                vapPage.onReportAdFormSelectIncorrectInfo();
                vapPage.setReportAdComments(testData.get("reportAdComment"));
                vapPage.submitReportAd();
                Assert.assertTrue(testData.get("reportAdSuccessMsg").contains(vapPage.validateReportAdSuccessMsg()),"expected Pop up msg="+testData.get("reportAdSuccessMsg")+"Pop up msg ="+vapPage.validateReportAdSuccessMsg());
                break;
            case "ELECTRONICS":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                snbPage.clickOnLifeStyleSubCAtOnCHP();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Mobiles vap");
                vapPage.clickOnReportAd();
                vapPage.loginWhenRedirectedToLoginPAge(testData.get("email"), testData.get("password"));
                vapPage.onReportAdFormSelectIncorrectInfo();
                vapPage.setReportAdComments(testData.get("reportAdComment"));
                vapPage.submitReportAd();
                Assert.assertTrue(testData.get("reportAdSuccessMsg").contains(vapPage.validateReportAdSuccessMsg()),"expected Pop up msg="+testData.get("reportAdSuccessMsg")+"Pop up msg ="+vapPage.validateReportAdSuccessMsg());
                break;
            case"ENTERTAINMENT":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMoreButton();//needs improvement
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Mobiles vap");
                vapPage.clickOnReportAd();
                vapPage.loginWhenRedirectedToLoginPAge(testData.get("email"), testData.get("password"));
                vapPage.onReportAdFormSelectIncorrectInfo();
                vapPage.setReportAdComments(testData.get("reportAdComment"));
                vapPage.submitReportAd();
                Assert.assertTrue(testData.get("reportAdSuccessMsg").contains(vapPage.validateReportAdSuccessMsg()),"expected Pop up msg="+testData.get("reportAdSuccessMsg")+"Pop up msg ="+vapPage.validateReportAdSuccessMsg());
                break;
            case "EDUCATION":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Mobiles vap");
                vapPage.clickOnReportAd();
                vapPage.loginWhenRedirectedToLoginPAge(testData.get("email"), testData.get("password"));
                vapPage.onReportAdFormSelectIncorrectInfo();
                vapPage.setReportAdComments(testData.get("reportAdComment"));
                vapPage.submitReportAd();
                Assert.assertTrue(testData.get("reportAdSuccessMsg").contains(vapPage.validateReportAdSuccessMsg()),"expected Pop up msg="+testData.get("reportAdSuccessMsg")+"Pop up msg ="+vapPage.validateReportAdSuccessMsg());
                break;
            // case"PETS":
            //case"MATRIMONIAL":
            case"REAL ESTATE":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                realEstateHomePage.clickOnResidential();
                realEstateHomePage.clickOnSearch();
                realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
                snbPage.hideOverlayLayout();
                realEstateSnbPage.openAdFromSnb();
                carsNewPage.scroll("About the Owner");
                Assert.assertTrue(!vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  present on RealEstate vap");
                break;

            case"JOBS":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                jobsPage.clickOnSelectRole();
                jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
                snbPage.hideOverlayLayout();
                jobsPage.openAdFromJobsSnb(0);
                carsNewPage.scroll("Ad Views");
                Assert.assertTrue(!vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  present on jobs vap");
                break;
            case"SERVICES":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                servicesPage.clickOnOtherServices();
                servicesPage.selectElements(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(0);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is not present on services vap");
                vapPage.clickOnReportAd();
                vapPage.loginWhenRedirectedToLoginPAge(testData.get("email"), testData.get("password"));
                vapPage.onReportAdFormSelectIncorrectInfo();
                vapPage.setReportAdComments(testData.get("reportAdComment"));
                vapPage.submitReportAd();
                Assert.assertTrue(testData.get("reportAdSuccessMsg").contains(vapPage.validateReportAdSuccessMsg()), "expected Pop up msg=" + testData.get("reportAdSuccessMsg") + "Pop up msg =" + vapPage.validateReportAdSuccessMsg());
                break;
            case "COMMUNITY":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on community vap");
                vapPage.clickOnReportAd();
                vapPage.loginWhenRedirectedToLoginPAge(testData.get("email"), testData.get("password"));
                vapPage.onReportAdFormSelectIncorrectInfo();
                vapPage.setReportAdComments(testData.get("reportAdComment"));
                vapPage.submitReportAd();
                Assert.assertTrue(testData.get("reportAdSuccessMsg").contains(vapPage.validateReportAdSuccessMsg()),"expected Pop up msg="+testData.get("reportAdSuccessMsg")+"Pop up msg ="+vapPage.validateReportAdSuccessMsg());
                break;
            case"HOME":
                hompage.selectelementWithoutScroll(cat, QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMoreButton();
                snbPage.hideOverlayLayout();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                carsNewPage.scroll(testData.get("viewProfile"));
                Assert.assertTrue(vapPage.reportAdLinkIsPresent(), "reoprt Ad link is  not present on Home&LifeStyle vap");
                vapPage.clickOnReportAd();
                vapPage.loginWhenRedirectedToLoginPAge(testData.get("email"), testData.get("password"));
                vapPage.onReportAdFormSelectIncorrectInfo();
                vapPage.setReportAdComments(testData.get("reportAdComment"));
                vapPage.submitReportAd();
                Assert.assertTrue(testData.get("reportAdSuccessMsg").contains(vapPage.validateReportAdSuccessMsg()),"expected Pop up msg="+testData.get("reportAdSuccessMsg")+"Pop up msg ="+vapPage.validateReportAdSuccessMsg());

        }
    }
    /**
     *  check on vap user is able to swipr more tah 20 ads on vap horizontally
     */
//    @Test
    public void userIsAbleToSwipeMoreThasn20Ads()
    {

        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("escrowmobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        List<Integer> cordinates=escrowPage.swipeCordinatesForVAp();
        System.out.println(cordinates);
        int count =0;
        while( count<22)
        {
            System.out.println(count);
            String descriptionBeforeSwipe=escrowPage.escrowAdDescription();
            escrowPage.horizontalSwipeWithCordinates(cordinates.get(1), cordinates.get(0), cordinates.get(2));
            String descriptionAfterSwipe=escrowPage.escrowAdDescription();
            if(!(descriptionBeforeSwipe.equalsIgnoreCase(descriptionAfterSwipe)))
                count ++;
        }
        System.out.println(count);
        Assert.assertTrue(count > 20, "NOt able to swipe more than 20 ads on VAp" + " only" + count + "ads swiped");


    }
    /**
     * Verify for hyderabad city VAP, similar ads module is displayed with 5 ads and view all button
     */
    @Stories("VAP")
    @Title("Verify for hyderabad city VAP")
    @Description(" Verify for hyderabad city VAP, similar ads module is displayed with 5 ads and view all button")
//    @Test
    public void validateSimilarAdMOduleForHydrabadCity()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        VapPage vapPage=new VapPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        MenuPage menuPage=new MenuPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.changeCityFromMenuList();
        headerPage.searchCity(testData.get("hydrabad"));
        hompage.selectelementWithoutScroll(testData.get("hydrabad"), QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("escrowmobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        escrowPage.clickOnSortButtonOnSnb();
        snbPage.selectLowestPriceFirst();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        Integer[] cordinates=vapPage.cordinatesForVerticalSwipeOnVap();
        vapPage.verticalSwipeWithCordinates(cordinates[0]+100,cordinates[1]);
        vapPage.verticalSwipeWithCordinates(cordinates[0] + 100, cordinates[1]);
        Assert.assertTrue(vapPage.validateSimilarAdLinkOnVap(),"similar ads are present for hyderabad");

    }

    /**
     * verify header of vap
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void validateHeaderOfVp()
    {
        VapPage vapPage=new VapPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("escrowmobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(vapPage.verifyHeaderOfVAP());

    }
    /**
     * VAP should be opened after tapping an ad from ,
     - Popular Ads
     - Nearby Ads
     - Recommended Ads
     */
    @Stories("Homepage")
    @Test(dataProvider ="HomePageAdTypes",dataProviderClass = Data.class)
    public void validateVapIsDisplayedbAfterTappingAdsFromPopularNearByAndRecomendedAds(String homePageAdTypes)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        MenuPage menuPage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);
        VapPage vapPage = new VapPage(driver);
        switch (homePageAdTypes) {
            case "Popular Ads":
                hompage.swipeVertically();
                hompage.explicitWait(2);
                hompage.openAdFromPopularAds();
                Assert.assertTrue(vapPage.verifyHeaderOfVAP(), "VAP Page did not open");
                break;
            case "Nearby Ads":
                hompage.scroll("Nearby Ads");
                hompage.explicitWait(2);
                hompage.openAdFromNearbyAds();
                Assert.assertTrue(vapPage.verifyHeaderOfVAP(), "VAP Page did not open");
                break;
            case "Recommended Ads":
                hompage.scroll("Recommended Ads");
                hompage.explicitWait(2);
                hompage.openAdFromRecommendedForYou();
                Assert.assertTrue(vapPage.verifyHeaderOfVAP(), "VAP Page did not open");
                break;
        }
    }

}
