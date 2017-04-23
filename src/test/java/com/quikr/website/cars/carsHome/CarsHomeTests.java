package com.quikr.website.cars.carsHome;

import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.cars.carsHeader.CarsHeaderPage;
import com.quikr.website.cars.carsSNB.CarsSNBPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Saurabh on 22/10/15.
 */
public class CarsHomeTests extends CarsTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate toggling to Cars Tab from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void switchtoCarsPageFromQuikrHomePage() {
        navigateToCarsHomePage();
        Assert.assertTrue(validateToggling("car"), "Toggling to Cars Tab from HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate toggling to Bikes Tab from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void switchBikescategoryFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleBikes();
        waitForPageToLoad("bikes");
        Assert.assertTrue(validateToggling("bike"), "Toggling to Bikes Tab from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate toggling to Commercial Vehicles Tab from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void switchCommercial_VehiclesCategoryFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleCommercial_Vehicles();

        Assert.assertTrue(validateToggling("vehicle"), "Toggling to Commercial Vehicles Tab from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate toggling to Bicycles Tab from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void switchBicyclesCategoryFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleBicycles();

        Assert.assertTrue(validateToggling("bicycles"), "Toggling to Bicycles Tab from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate toggling to SparePartsAccessoriesTab from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void switchSparePartsAccessoriesCategoryHomepage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.togglesparePartsAccessoriesHomepageTab();

        Assert.assertTrue(validateToggling("spare parts"), "Toggling to Spare Parts Accessories Tab from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Inspected Cars Page from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateInspected_CarsFromCarsHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        if (carsHomePage.validateInspectedText()) {
            carsHomePage.clickInspectedCars();
            String carsInspectedUrl = getCurrentUrl();
            Assert.assertTrue(carsInspectedUrl.contains(testData.get("getItInspectedCarsURL")), "Navigation to Get It Inspected Page for Cars from Cars HomePage was unsuccessful");
        } else {
            logger.info("Looks like Inspected Cars section is not Available properly on Cars Home Page");
        }
    }

    /**
     * Testcase - Validate navigation to Check MSP Page for Cars from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateMSP_CarsFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        if (carsHomePage.validateCheckMSPText()) {
            carsHomePage.clickCheckMSP();
            String currentURL = getCurrentUrl();
            Assert.assertTrue(currentURL.contains("msp"), "Navigation to Check MSP Page for Cars from Cars HomePage was unsuccessful");
        } else {
            logger.info("Looks like Check MSP is not proper on Cars Home Page");
        }
    }

    /**
     * Testcase - Validate Most Popular Section from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void validateMostPopular_CarsFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        Assert.assertEquals(carsHomePage.getTextMostPopularAdsonQuikr().trim(), "Most Popular on Quikr");
        Assert.assertTrue(carsHomePage.numberofMostPopularAdsonQuikr("cars"), "Number of Cars are not 8 on Most Popular Ads");
        Assert.assertEquals(carsHomePage.returnModelsMostPopularonQuikr("cars"), carsHomePage.returnModelsNamefromImagesonMostPopularAdsonQuikr("cars"), "Looks like Images of the Most Poulars Cars are not matching with Models of the cars. Pls Check");
    }

    /**
     * Testcase - Validate Most Popular Section from Bikes HomePage
     */
    @Test(groups = "carsSanity")
    public void validateMostPopular_BikesFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleBikes();
        Assert.assertEquals(carsHomePage.getTextMostPopularAdsonQuikr().trim(), "Most Popular on Quikr");
        Assert.assertTrue(carsHomePage.numberofMostPopularAdsonQuikr("bikes"), "Number of bikes are not 8 on Most Popular Ads");
    }

    /**
     * Testcase - Validate Navigate to Most Popular Section from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateMostPopular_CarsFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        String carModeltext = carsHomePage.returnModelsMostPopularonQuikr("cars").get(0);
        carsHomePage.clickMostPopularCar(1);
        String snbSearchResultsHeadingText = carsSNBPage.returnSNBSearchResultsHeading();
        logger.info("carModeltext is " + carModeltext);
        logger.info("snbSearchResultsHeadingText is " + snbSearchResultsHeadingText);
        Assert.assertTrue(snbSearchResultsHeadingText.contains("Used " + carModeltext), "Looks like SNB Page on Clickinh Most Popular Models is not proper, Pls Check");
        waitForPageToLoad("http://www.quikr.com");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.validateSnbProductsName(carModeltext), "Looks Like SNB Search Result does not contain The Car Model" + carModeltext + "Search");
    }

    @Test(groups = "carsSanity")
    public void testNumberofMostPopularCars() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        navigateToCarsHomePage();
        Assert.assertTrue(carsHomePage.isNumberofMostPopularAdsproper("Cars"), "Looks like Number of Cars on Most popular Section are not in proper Number");
    }


    @Test(groups = "carsSanity")
    public void testNumberofMostPopularBikes() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        navigateToCarsHomePage();
        carsHomePage.toggleBikes();
        waitForPageToLoad("http://www.quikr.com/bikes");
        Assert.assertTrue(carsHomePage.isNumberofMostPopularAdsproper("Bikes"), "Looks like Number of Bikes on Most popular Section are not in proper Number");
    }

    /**
     * Testcase - Validate navigation from Most Popular Section for Bikes to SNB from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateMostPopular_BikesFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleBikes();

        Assert.assertTrue(validateMostPopularData("bikes"), "Navigation from Most Popular Section for Bikes to SNB from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation from Most Popular Section for Commercial Vehicles to SNB from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateMostPopular_VehiclesFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleCommercial_Vehicles();

        Assert.assertTrue(validateMostPopularData("vehicles"), "Navigation from Most Popular Section for Commercial Vehicles to SNB from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation from Most Popular Section for Cars to SNB from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateRecentlyPosted_CarsFromHomePage() {
        navigateToCarsHomePage();

        Assert.assertTrue(validateRecentlyPostedData("cars"), "Navigation from Most Popular Section for Cars to SNB from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation from Recently Posted Section for Commercial Vehicles to SNB from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateRecentlyPosted_BikesFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        waitForPageToLoad("http://www.quikr.com/");
        navigateToCarsHomePage();
        carsHomePage.toggleBikes();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(validateRecentlyPostedData("bikes"), "Navigation from Recently Posted Section for Commercial Vehicles to SNB from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Save Ad Page from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateSaveAdFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        HashMap<String, String> saveAd_AdId = new HashMap<>();
        navigateToCarsHomePage();

        if (!carsHomePage.clickShortlist().isEmpty())
            saveAd_AdId.put("Cars", carsHomePage.clickShortlist());

        carsHomePage.toggleBikes();
        carsPageBase.closeCityPopup();
        if (!carsHomePage.clickShortlist().isEmpty())
            saveAd_AdId.put("Bikes", carsHomePage.clickShortlist());

        carsHomePage.toggleCommercial_Vehicles();
        carsPageBase.closeCityPopup();
        if (!carsHomePage.clickShortlist().isEmpty())
            saveAd_AdId.put("Commercial Vehicles", carsHomePage.clickShortlist());

        Assert.assertTrue(validateSaveAdData(saveAd_AdId), "Navigation to Save Ad Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate Chat_Reply from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void validateChat_ReplyFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();

        if (carsHomePage.areChatButtonavailableonHomePage("cars")) {
            boolean chatStatus_Cars = carsHomePage.validateChat("cars");
            Assert.assertTrue(chatStatus_Cars, "Validation of Chat from Cars HomePage was unsuccessful");
        }
        if (carsHomePage.areReplyButtonavailableonHomePage("cars")) {
            boolean replyStatus_Cars = carsHomePage.validateReply("cars");
            Assert.assertTrue(replyStatus_Cars, "Validation of Reply from Cars HomePage was unsuccessful");
        }

        carsHomePage.toggleBikes();
        if (carsHomePage.areChatButtonavailableonHomePage("bikes")) {
            boolean chatStatus_Bikes = carsHomePage.validateChat("bikes");
            Assert.assertTrue(chatStatus_Bikes, "Validation of Chat from Bikes HomePage was unsuccessful");
        }
        if (carsHomePage.areReplyButtonavailableonHomePage("bikes")) {
            boolean replyStatus_Bikes = carsHomePage.validateReply("bikes");
            Assert.assertTrue(replyStatus_Bikes, "Validation of Reply from Bikes HomePage was unsuccessful");
        }

        carsHomePage.toggleCommercial_Vehicles();
        if (carsHomePage.areChatButtonavailableonHomePage("vehicles")) {
            boolean chatStatus_CommercialVehicles = carsHomePage.validateChat("vehicles");
            Assert.assertTrue(chatStatus_CommercialVehicles, "Validation of Chat from CommercialVehicles HomePage was unsuccessful");
        }
        if (carsHomePage.areReplyButtonavailableonHomePage("vehicles")) {
            boolean replyStatus_CommercialVehicles = carsHomePage.validateReply("vehicles");
            Assert.assertTrue(replyStatus_CommercialVehicles, "Validation of Reply from CommercialVehicles HomePage was unsuccessful");
        }
    }

    /**
     * Testcase - Validate navigation to Reviews Page from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateReviewsFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        String title = null;
        Assert.assertTrue(carsHomePage.validateReviewsSection("profile"), "Looks like Review Section is not visible");
        title = carsHomePage.clickReviews("profile");
        String pitStoppageTitletext = carsHomePage.returnPitStopPageTitleText();
        waitForPageToLoad("http://www.quikr.com/cars-bikes/pitstop");
        Assert.assertEquals(title, pitStoppageTitletext, "Looks like the PitStop Page or Title of the ad is not Proper, Pls Check");
    }

    /**
     * Testcase - Validate navigation to Just Launched Page from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateJustLaunchedFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        String justLaunched = null;
        Assert.assertTrue(carsHomePage.validateJustLaunchedSection(), "Looks like Just Launched Section is not fine");
        justLaunched = carsHomePage.clickJustLaunched("1");
        waitForPageToLoad("http://www.quikr.com/cars-bikes/pitstop");
        Assert.assertEquals(justLaunched, carsHomePage.returnPitStopPageTitleText(), "Looks like the PitStop Page or Title of the ad is not Proper, Pls Check");
    }

    /**
     * Testcase - Validate navigation to Auto News Page from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateAutoNewsFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        String adTitle = null;
        Assert.assertTrue(carsHomePage.validateAutoNewsSection(), "Looks like AutoNews Section is not available Pls Check");
        adTitle = carsHomePage.clickAutoNews("1");
        waitForPageToLoad("http://www.quikr.com/cars-bikes/pitstop");
        Assert.assertEquals(adTitle, carsHomePage.returnPitStopPageTitleText(), "Looks like the PitStop Page or Title of the ad is not Proper, Pls Check");
    }

    /**
     * Testcase - Validate navigation to Videos Page from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void navigateVideosFromHomePage() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        String videoTitle = null;
        Assert.assertTrue(carsHomePage.validateVideosSection(), "Looks like Video Section is not visible");
        videoTitle = carsHomePage.clickVideos("2");
        waitForPageToLoad("http://www.quikr.com/cars-bikes/pitstop");
        Assert.assertEquals(videoTitle, carsHomePage.returnPitStopPageTitleText(), "Looks like the PitStop Page or Title of the ad is not Proper, Pls Check");
    }

    /**
     * Testcase - Validate Cars Search from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void testSearchResults() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.clickSearchBar();
        Assert.assertEquals(carsHomePage.returnSearchBarHeading().trim(), "Most Popular Cars");
        Assert.assertEquals(carsHomePage.numberofAutoSuggestoptionsSearch(), 9, "Looks like Auto Suggest options are not proper");
    }

    /**
     * Testcase - Validate Budget Search from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void testBudgetSearch() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        Assert.assertTrue(carsHomePage.isBudgetHeadingAvailable(), "Looks like Rupee Symbol is not available");
        Assert.assertEquals(carsHomePage.returnBudgettext(), "Budget", "Look like Budget Text is not available in Search");
        Assert.assertTrue(carsHomePage.validateBudgetSearch(), "Looks like Budget Search Options are not proper");
    }

    /**
     * Testcase - Validate Search from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void testSearch() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        carsHomePage.clickSearchBar();
        carsHomePage.searchText(testData.get("searchItem"));
        carsHomePage.clickBudget();
        carsHomePage.selectMinBudget("cars", "0");
        carsHomePage.selectMaxBudget("cars", "50,000");
        carsHomePage.search();
        Assert.assertEquals(carsHomePage.heading().contains("Results for"), "Looks like Search Page is not coming pls Check");
    }

    /**
     * Testcase - Validate Footer of Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void testFooter() {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsHomePage();
        Assert.assertTrue(carsHomePage.isFatFooterProper(), "Looks like Fat Footer is not coming proper pls Check");
    }

    /**
     * Testcase - Validate Sell your Car button on Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void testPostAd() {

        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        navigateToCarsHomePage();
        carsHeaderPage.clickPostAd();
        Assert.assertTrue(waitForPageToLoad("http://quikr.com/post-classifieds-ads/?postadcategoryid"), "Looks like Cars PAP page is not opening");
    }
}