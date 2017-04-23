package com.quikr.website.cars;

import com.quikr.website.TestBase;
import com.quikr.website.cars.carsFooter.CarsFooterPage;
import com.quikr.website.cars.carsHeader.CarsHeaderPage;
import com.quikr.website.cars.carsHome.CarsHomePage;
import com.quikr.website.cars.carsMSP.CarsMSPPage;
import com.quikr.website.cars.carsSNB.CarsSNBPage;
import com.quikr.website.cars.carsSaveAd.CarsSaveAdPage;
import com.quikr.website.cars.carsVAP.CarsVAPPage;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Saurabh on 23/10/15.
 */
public class CarsTestBase extends TestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    public void navigateToPage(String navigateTo, String from, boolean clickHamburger)
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);
        CarsFooterPage carsFooterPage = new CarsFooterPage(driver);
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        if(from.equals("CarsHomePage"))
            navigateToCarsHomePage();
        else if(from.equals("CarsCityPage"))
            navigateToCarsCityPage();

        if(clickHamburger)
            carsHeaderPage.clickHamburger();

        switch (navigateTo)
        {
            /*** Hamburger cases ***/
            case "QuikrHome" : carsHeaderPage.clickQuikrHome();
                break;

            case "Cars" : carsHeaderPage.clickQuikrLogo();
                break;

            case "Mobiles_Tablets" : carsHeaderPage.clickMobiles_Tablets();
                break;

            case "Electronics_Appliances" : carsHeaderPage.clickElectronics_Appliances();
                break;

            case "RealEstate" : carsHeaderPage.clickRealEstate();
                break;

            case "Home_Lifestyle" : carsHeaderPage.clickHome_Lifestyle();
                break;

            case "Jobs" : carsHeaderPage.clickJobs();
                break;

            case "Services" : carsHeaderPage.clickServices();
                break;

            case "Pets_Petcare" : carsHeaderPage.clickPets_Petcare();
                break;

            case "Education_Learning" : carsHeaderPage.clickEducation_Learning();
                break;

            case "NewCars" : carsHeaderPage.clickNewCars();
                break;

            case "Check_MSP_Hamburger" : carsHeaderPage.clickCheckMSPHamburger();
                break;

            /*** Footer cases ***/
            case "Footer_Cars" : carsFooterPage.clickCars();
                break;

            case "Footer_Bikes_Scooter" : carsFooterPage.clickBikes_Scooters();
                break;

            case "Footer_Commercial_Vehicles" : carsFooterPage.clickCommercial_Vehicles();
                break;

            case "Footer_Bicycles" : carsFooterPage.clickBicycles();
                break;

            case "Footer_Spare_Parts" : carsFooterPage.clickSpare_Parts();
                break;

            case "Footer_Others" : carsFooterPage.clickOther_Vehicles();
                break;

            case "AboutUs" : carsFooterPage.clickAboutUs();
                break;

            case "ContactUs" : carsFooterPage.clickContactUs();
                break;

            case "Careers" : carsFooterPage.clickCareers();
                break;

            case "QuikrVideos" : carsFooterPage.clickQuikrVideos();
                break;

            case "Advertise" : carsFooterPage.clickAdvertise();
                break;

            case "Blog" : carsFooterPage.clickBlog();
                break;

            case "Help" : carsFooterPage.clickHelp();
                break;

            case "Listing_Policy" : carsFooterPage.clickListing_Policy();
                break;

            case "TermsOfUse" : carsFooterPage.clickTermsOfUse();
                break;

            case "Privacy_Policy" : carsFooterPage.clickPrivacy_Policy();
                break;

            case "QuikrX_Policy" : carsFooterPage.clickQuikrX_Policy();
                break;

            /*** HomePage cases ***/
            case "Inspected_Cars" : carsHomePage.clickInspectedCars();
                break;

            case "Check_MSP_HomePage" : carsHomePage.clickCheckMSP();
                break;
        }
    }

    public void navigateToCarsHomePage()
    {
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        
        carsPageBase.selectCars();
        waitForPageToLoad("quikr.com/Cars-Bikes/");

        if(carsPageBase.validatePageURL("www.quikr.com/"))
        {
            carsPageBase.closeCityPopup();
            waitForPageToLoad("quikr.com/Cars-Bikes/");
        }
    }

    public void navigateToCarsCityPage()
    {
        String cityToSelect = testData.get("city");
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        carsPageBase.selectCars();
        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsPageBase.closeCityPopup();
        carsPageBase.selectCityFromPopUp(cityToSelect.toLowerCase());
        waitForPageToLoad(cityToSelect.toLowerCase());
    }

    public boolean validateToggling(String vehicleType)
    {
        boolean togglingStatus = false;
        CarsHomePage carsHomePage = new CarsHomePage(driver);

        if(carsHomePage.validateSearchBarIcon(vehicleType) && carsHomePage.validateSearchBarPlaceholderText(vehicleType))
            togglingStatus = true;

        return togglingStatus;
    }

    public boolean validateMostPopularData(String vehicleType)
    {
        boolean mostPopularDataStatus = false;
        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);

        if (vehicleType.equals("cars")|| vehicleType.equals("bikes")) {
            if (carsHomePage.validateMostPopularSection(vehicleType)) {
                HashMap<String, String> mostPopularData = carsHomePage.mostPopularData(vehicleType);
                carsHomePage.clickMostPopularAd(vehicleType);
                //waitForPageToLoad(mostPopularData.get("MODEL").replace(" ", "/"));

                //if(carsSNBPage.validatePageURL(mostPopularData.get("MODEL").replace(" ", "/")))
                if (carsSNBPage.heading().contains("Used") && carsSNBPage.heading().contains(mostPopularData.get("MODEL").replaceAll(" ","")) && carsSNBPage.validateTupleSection()) {
                    mostPopularDataStatus = true;
                }
                else {
                    mostPopularDataStatus = false;
                    logger.info("Looks like mostPopularDataStatus is " + mostPopularDataStatus + "Might be landing SNB is not proper");
                }
            }
        }
        else {
            if (carsSNBPage.heading().contains("Used")){
                mostPopularDataStatus = true;
            }
            else {
                mostPopularDataStatus = false;
                logger.info("Looks like SNB Page for"+vehicleType+"is not proper");
            }
        }

        return mostPopularDataStatus;
    }

    public boolean validateRecentlyPostedData(String vehicleType)
    {
        boolean recentlyPostedDataStatus = false;
        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsVAPPage carsVAPPage = new CarsVAPPage(driver);

        if(carsHomePage.validateRecentlyPostedSection(vehicleType)) {
            HashMap<String, String> recentlyPostedData = carsHomePage.recentlyPostedData(vehicleType);
            System.out.println(recentlyPostedData);
            carsHomePage.clickRecentlyPostedAd(vehicleType);
            waitForPageToLoad("http://www.quikr.com");
            HashMap<String, String> adBasicDetailsData = carsVAPPage.adBasicDetailsData(vehicleType);
            if(carsVAPPage.heading().replaceAll(" ","").contains(recentlyPostedData.get("TITLE").replaceAll(" ","")) && recentlyPostedData.get("PRICE").equals(carsVAPPage.adPrice()))
            {
                for(String key : recentlyPostedData.keySet())
                {
                    recentlyPostedDataStatus = recentlyPostedData.get(key).equals(adBasicDetailsData.get(key));
                }

            }
            else {
                logger.info("Looks like Cars Vap Page is not displaying proper information Pls Check ");
            }
        }

        return recentlyPostedDataStatus;
    }

    public boolean validateSaveAdData(HashMap<String, String> adId)
    {
        boolean saveAdDataStatus = false;
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);
        CarsSaveAdPage carsSaveAdPage = new CarsSaveAdPage(driver);

        carsHeaderPage.clickUserIcon();
        carsHeaderPage.clickMySavedAds();
        waitForPageToLoad("action=mysavedads");

        for(String key : adId.keySet())
        {
            if(carsSaveAdPage.checkAdPresence(key, adId.get(key))) {
                saveAdDataStatus=true;
            }
            else {
                logger.info("Looks like All Save ads are not matching");
                logger.info("Tab is "+key);
                logger.info("Ad Id is "+adId.get(key));
                saveAdDataStatus=false;
            }
        }

        return saveAdDataStatus;
    }

    public void calculateMsp(String vehicleType, String adType, String brand, String model, String year, String variant, String kmsDriven)
    {
        CarsMSPPage checkMSP = new CarsMSPPage(driver);

        checkMSP.closeCityPopup();

        if(vehicleType.equals("Cars"))
            checkMSP.clickCars();
        else if(vehicleType.equals("Bikes"))
            checkMSP.clickBikes();

        if(adType.equals("Buy"))
            checkMSP.clickImBuying();
        else
            checkMSP.clickImSelling();

        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(year);

        if(vehicleType.equals("Cars"))
            checkMSP.selectVariant(variant);

        checkMSP.clickKmsDriven(kmsDriven);

        if(vehicleType.equals("Cars"))
            checkMSP.carsSubmit();
        else
            checkMSP.bikesSubmit();
    }

    public void loginUser(String username, String password)
    {
        CarsHeaderPage carsHeaderPage = new CarsHeaderPage(driver);

        waitForPageToLoad("quikr.com/Cars-Bikes/");
        carsHeaderPage.loginUser(username, password);
        waitForPageToLoad("quikr.com/MyQuikr");
    }

    public String covertToCityURL(String URL)
    {
        return URL.replace("www",testData.get("city").toLowerCase());
    }
}
