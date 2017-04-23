package com.quikr.website.cars.carsHome;

import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.CarsTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Saurabh on 22/10/15.
 */
public class CarsHomeCityTests extends CarsTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate toggling to Cars Tab from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void toggleCarsFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        carsHomePage.toggleBikes();
        waitForPageToLoad("http://www.quikr.com");
        carsHomePage.toggleCars();

        Assert.assertTrue(validateToggling("car"), "Toggling to Cars Tab from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate toggling to Bikes Tab from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void toggleBikesFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        waitForPageToLoad("http://www.quikr.com");
        carsHomePage.toggleBikes();

        Assert.assertTrue(validateToggling("bike"), "Toggling to Bikes Tab from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate toggling to Cars Tab from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void toggleCommercial_VehiclesFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        waitForPageToLoad("http://www.quikr.com");
        carsHomePage.toggleCommercial_Vehicles();

        Assert.assertTrue(validateToggling("vehicle"), "Toggling to Commercial Vehicles Tab from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Get It Inspected Page for Cars from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateGetItInspected_CarsFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        if(carsHomePage.validateInspectedText())
            carsHomePage.clickInspectedCars();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("getItInspectedCarsURL"))), "Navigation to Get It Inspected Page for Cars from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHomePage.heading().contains("UsedCars"), "Something wrong with the Inspection Page");
    }

    /**
     * Testcase - Validate navigation to Get It Inspected Page for Bikes from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateGetItInspected_BikesFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        carsHomePage.toggleBikes();
        if(carsHomePage.validateInspectedText())
            carsHomePage.clickInspectedCars();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("getItInspectedBikesURL"))), "Navigation to Get It Inspected Page for Bikes from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHomePage.heading().contains("UsedBikes"), "Something wrong with the Inspection Page");
    }

    /**
     * Testcase - Validate navigation to Check MSP Page for Cars from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateMSP_CarsFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        if(carsHomePage.validateCheckMSPText())
            carsHomePage.clickCheckMSP();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("checkMSPPageURL"))), "Navigation to Check MSP Page for Cars from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHomePage.heading().equalsIgnoreCase("UsedVehicleValuation"), "Something wrong with MSP Page");
    }

    /**
     * Testcase - Validate navigation to Check MSP Page for Bikes from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateMSP_BikesFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        if(carsHomePage.validateCheckMSPText())
            carsHomePage.clickCheckMSP();

        Assert.assertTrue(waitForPageToLoad(covertToCityURL(testData.get("checkMSPPageURL"))), "Navigation to Check MSP Page for Bikes from Cars CityPage was unsuccessful");
        Assert.assertTrue(carsHomePage.heading().equalsIgnoreCase("UsedVehicleValuation"), "Something wrong with MSP Page");
    }

    /**
     * Testcase - Validate navigation from Most Popular Section for Cars to SNB from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateMostPopular_CarsFromCityPage()
    {
        navigateToCarsCityPage();

        Assert.assertTrue(validateMostPopularData("cars"), "Navigation from Most Popular Section for Cars to SNB from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation from Most Popular Section for Bikes to SNB from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateMostPopular_BikesFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        carsHomePage.toggleBikes();

        Assert.assertTrue(validateMostPopularData("bikes"), "Navigation from Most Popular Section for Bikes to SNB from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation from Most Popular Section for Commercial Vehicles to SNB from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateMostPopular_VehiclesFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        carsHomePage.toggleCommercial_Vehicles();

        Assert.assertTrue(validateMostPopularData("vehicles"), "Navigation from Most Popular Section for Commercial Vehicles to SNB from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation from Recently Posted Section for Cars to SNB from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateRecentlyPosted_CarsFromCityPage()
    {
        navigateToCarsCityPage();

        Assert.assertTrue(validateRecentlyPostedData("cars"), "Navigation from Recently Posted Section for Cars to SNB from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation from Recently Posted Section for Bikes to SNB from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateRecentlyPosted_BikesFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        carsHomePage.toggleBikes();

        Assert.assertTrue(validateRecentlyPostedData("bikes"), "Navigation from Recently Posted Section for Bikes to SNB from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation from Recently Posted Section for Commercial Vehicles to SNB from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateRecentlyPosted_VehiclesFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        carsHomePage.toggleCommercial_Vehicles();

        Assert.assertTrue(validateRecentlyPostedData("vehicles"), "Navigation from Recently Posted Section for Commercial Vehicles to SNB from Cars CityPage was unsuccessful");
    }

    /**
     * Testcase - Validate navigation to Save Ad Page from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateSaveAdFromCityPage()
    {
        HashMap<String, String> saveAd_AdId = new HashMap<>();
        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase=new CarsPageBase(driver);

        navigateToCarsCityPage();
        waitForPageToLoad("http://www.quikr.com");

        if(!carsHomePage.clickShortlist().isEmpty())
            saveAd_AdId.put("Cars", carsHomePage.clickShortlist());

        carsHomePage.toggleBikes();
        carsPageBase.closeCityPopup();
        if(!carsHomePage.clickShortlist().isEmpty())
            saveAd_AdId.put("Bikes", carsHomePage.clickShortlist());

        carsHomePage.toggleCommercial_Vehicles();
        carsPageBase.closeCityPopup();
        if(!carsHomePage.clickShortlist().isEmpty())
            saveAd_AdId.put("Commercial Vehicles",carsHomePage.clickShortlist());

        Assert.assertTrue(validateSaveAdData(saveAd_AdId), "Navigation to Save Ad Page from Cars HomePage was unsuccessful");
    }

    /**
     * Testcase - Validate Chat_Reply from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void validateChat_ReplyFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        boolean chatStatus_Cars = carsHomePage.validateChat("cars");
        Assert.assertTrue(chatStatus_Cars, "Validation of Chat from Cars CityPage was unsuccessful");
        boolean replyStatus_Cars=carsHomePage.validateReply("cars");
        Assert.assertTrue(replyStatus_Cars, "Validation of Reply from Cars CityPage was unsuccessful");

        carsHomePage.toggleBikes();
        boolean chatStatus_Bikes = carsHomePage.validateChat("bikes");
        Assert.assertTrue(chatStatus_Bikes, "Validation of Chat from Bikes CityPage was unsuccessful");
        boolean replyStatus_Bikes = carsHomePage.validateReply("bikes");
        Assert.assertTrue(replyStatus_Bikes , "Validation of Reply from Bikes CityPage was unsuccessful");

        carsHomePage.toggleCommercial_Vehicles();
        boolean chatStatus_CommercialVehicles = carsHomePage.validateChat("vehicles");
        Assert.assertTrue(chatStatus_CommercialVehicles, "Validation of Chat from CommercialVehicles CityPage was unsuccessful");
        boolean replyStatus_CommercialVehicles = carsHomePage.validateReply("vehicles");
        Assert.assertTrue(replyStatus_CommercialVehicles , "Validation of Reply from CommercialVehicles CityPage was unsuccessful");

    }

    /**
     * Testcase - Validate navigation to Reviews Page from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateReviewsFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        String title=null;
        Assert.assertTrue(carsHomePage.validateReviewsSection("profile"), "Looks like Review Section is not visible");
        title=carsHomePage.clickReviews("profile");
        String pitStoppageTitletext=carsHomePage.returnPitStopPageTitleText();
        waitForPageToLoad("http://www.quikr.com/cars-bikes/pitstop");
        Assert.assertEquals(title, pitStoppageTitletext, "Looks like the PitStop Page or Title of the ad is not Proper, Pls Check");
    }

    /**
     * Testcase - Validate navigation to Just Launched Page from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateJustLaunchedFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        String justLaunched=null;
        Assert.assertTrue(carsHomePage.validateJustLaunchedSection(),"Looks like Just Launched Section is not fine");
        justLaunched = carsHomePage.clickJustLaunched("1");
        waitForPageToLoad("http://www.quikr.com/cars-bikes/pitstop");
        Assert.assertEquals(justLaunched, carsHomePage.returnPitStopPageTitleText(), "Looks like the PitStop Page or Title of the ad is not Proper, Pls Check");
    }

    /**
     * Testcase - Validate navigation to Auto News Page from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateAutoNewsFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        String adTitle=null;
        Assert.assertTrue(carsHomePage.validateAutoNewsSection(), "Looks like AutoNews Section is not available Pls Check");
        adTitle = carsHomePage.clickAutoNews("1");
        waitForPageToLoad("http://www.quikr.com/cars-bikes/pitstop");
        Assert.assertEquals(adTitle, carsHomePage.returnPitStopPageTitleText(), "Looks like the PitStop Page or Title of the ad is not Proper, Pls Check");
    }

    /**
     * Testcase - Validate navigation to Videos Page from Cars CityPage
     */
    @Test(groups = "carsSanity")
    public void navigateVideosFromCityPage()
    {
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        navigateToCarsCityPage();
        String videoTitle=null;
        Assert.assertTrue(carsHomePage.validateVideosSection(), "Looks like Video Section is not visible");
        videoTitle=carsHomePage.clickVideos("2");
        waitForPageToLoad("http://www.quikr.com/cars-bikes/pitstop");
        Assert.assertEquals(videoTitle, carsHomePage.returnPitStopPageTitleText(), "Looks like the PitStop Page or Title of the ad is not Proper, Pls Check");
    }
}