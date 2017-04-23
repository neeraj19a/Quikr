package com.quikr.website.cars.carsSNB;

import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.cars.carsHome.CarsHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 26/7/16.
 */
public class CarsSNBTests extends CarsTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate Brand Selection from SNB Page
     */
    @Test(groups = "carsSanity") 
    public void testBrand() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedCars"), "Looks like Search Page is not coming pls Check");
        String brand = "Ford";
        carsSNBPage.selectBrand(brand);
        String brandName = carsSNBPage.brandsSelected();
        waitForPageToLoad("http://www.quikr.com/" + brand + "/Cars");
        carsPageBase.closeCityPopup();
        Assert.assertEquals(brand, brandName, "Looks like brand is not properly selected");
    }

    /**
     * Testcase - Validate MultiBrand Selection from SNB Page
     */
    @Test(groups = "carsSanity") 
    public void testMultiBrand() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedCars"), "Looks like Search Page is not coming pls Check");
        String brand = "Ford";
        carsSNBPage.selectBrand(brand);
        carsPageBase.closeCityPopup();
        String brand2 = "Honda";
        carsSNBPage.selectBrand(brand2);
        carsPageBase.closeCityPopup();
        String brandName = carsSNBPage.brandsSelected();
        String[] brandNames=brandName.split("-");
        Assert.assertTrue(waitForPageToLoad("http://www.quikr.com/" + brand + "_" + brand2 + "/Cars"), "Looks like in URL brands are not getting populated ,Current URL is" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(Arrays.asList(brandNames).contains(brand), "Looks like" + brand + " is not properly selected");
        Assert.assertTrue(Arrays.asList(brandNames).contains(brand2), "Looks like" + brand2 + " is not properly selected");
        Assert.assertTrue(carsSNBPage.validateSnbProductsName(brand, brand2), "Looks like Search results does not contain All brands selected");
    }

    /**
     * Testcase - Validate Clear MultiBrand Selection from SNB Page
     */
    @Test(groups = "carsSanity")
    public void testclearMultiBrandSelection() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedCars"), "Looks like Search Page is not coming pls Check");
        String brand = "Ford";
        carsSNBPage.selectBrand(brand);
        carsPageBase.closeCityPopup();
        System.out.println(carsSNBPage.getBreadFiltersCrumbText());
        Assert.assertTrue(carsSNBPage.getBreadFiltersCrumbText().contains(brand), "Looks like Brand is not reflected in BreadCrumb");
        waitForPageToLoad("quikr.com/"+brand+"/Cars");
        carsPageBase.closeCityPopup();
        String brand2 = "Honda";
        carsSNBPage.selectBrand(brand2);
        String brandName = carsSNBPage.brandsSelected();
        String[] brandNames=brandName.split("-");
        Assert.assertTrue(waitForPageToLoad("http://www.quikr.com/" + brand + "_" + brand2 + "/Cars"), "Looks like in URL brands are not getting populated ,Current URL is" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(Arrays.asList(brandNames).contains(brand), "Looks like" + brand + " is not properly selected");
        Assert.assertTrue(Arrays.asList(brandNames).contains(brand2), "Looks like" + brand2 + " is not properly selected");
        Assert.assertTrue(carsSNBPage.validateSnbProductsName(brand, brand2), "Looks like Search results does not contain All brands selected");
        carsSNBPage.clearAllFiltersfromBreadCrumb();
        Assert.assertTrue(carsSNBPage.validateFilterBreadCrumbnotPresent(), "Looks like BreadCrumb of Brand Filters are present");

    }

    /**
     * Testcase - Validate ShortList Icons from SNB Page
     */
    @Test(groups = "carsSanity") 
    public void testShortListIcons() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertEquals(carsSNBPage.numberofShortListIcon(), 30, "Number of icons are not proper-->" + carsSNBPage.numberofShortListIcon());
        Assert.assertTrue(carsSNBPage.validatePrimeFeatures(),"Prime Features are not coming properly on SNB Page");
    }

    /**
     * Testcase - Validate Ads have proper price on SNB Page
     */
    @Test(groups = "carsSanity") 
    public void testPriceOnSnbAds() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.isAllAdsHaveProperPrice(),"Pls check Seems Ads does not have proper price");
    }

    /**
     * Testcase - Validate Chat and Reply Buttons on SNB Page
     */
    @Test(groups = "carsSanity") 
    public void testChatandReplyButtonsOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.areChatandReplyButtonAvailable(),"Pls check Seems Ads does not have proper Chat or Reply Buttons");
    }

    /**
     * Testcase - Validate Posted time ago and Online or Offline Status on SNB Page
     */
    @Test(groups = "carsSanity") 
    public void testpostedAdAgoTimeandOnlineOfflineStatusOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        SoftAssert s_assert=new SoftAssert();

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        s_assert.assertTrue(carsSNBPage.isUserOnlineStatusproper(), "Pls check Seems Ads does not have proper online or offline Status");
        s_assert.assertTrue(carsSNBPage.isPostedStatusproper(), "Pls check Seems Ads does not have proper psoted Time Status");
        s_assert.assertAll();
    }

    /**
     * Testcase - Validate Inspected on SNB Page
     */
    @Test(groups = "carsSanity") 
    public void testInspectedOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        SoftAssert s_assert=new SoftAssert();

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        s_assert.assertEquals(carsSNBPage.isInspectedPresent(), "Inspected Only", "Looks like Inspected text is not coming");
        s_assert.assertTrue(carsSNBPage.validatetoggleInspectedOnSNB(), "Inspection Page not loaded succesfully");
        s_assert.assertAll();
    }

    /**
     * Testcase - Validate Sort by Least Driven on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testSortLeastDrivenOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.sortBy("Least");
        Assert.assertTrue(waitForPageToLoad("kms_Driven"), "Looks like the Page is not loaded properly as URL is--->" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.validateSortByLeastDriven(), "Looks like Kms are not sorted Properly");
    }

    /**
     * Testcase - Validate Sort by Highest Price on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testSortHighestPriceOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsSNBPage.selectCityFromSNB("Bangalore");
        carsPageBase.closeCityPopup();
        carsSNBPage.sortBy("Highest Price");
        Assert.assertTrue(waitForPageToLoad("priceDesc"), "Looks like the Page is not loaded properly as URL is--->" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.validateSortByPrice("Highest"),"Looks like Highest Price are not sorted Properly");
    }

    /**
     * Testcase - Validate Sort by Lowest Price on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testSortLowestPriceOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.sortBy("Lowest Price");
        Assert.assertTrue(waitForPageToLoad("priceAsc"), "Looks like the Page is not loaded properly as URL is--->" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.validateSortByPrice("Lowest"),"Looks like Lowest Price are not sorted Properly");
    }

    /**
     * Testcase - Validate Sort by Sort By Latest Model on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testSortByLatestModelOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.sortBy("Latest Model");
        Assert.assertTrue(waitForPageToLoad("order=year"), "Looks like the Page is not loaded properly as URL is--->" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.validateSortByLatestModel(),"Looks like Latest Models are not sorted Properly");
    }

    /**
     * Testcase - Validate Pagination on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testPaginationOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsPageBase.closeCityPopup();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedCarsinIndia"), "Looks like SNB Page is not loaded");
        Assert.assertTrue(carsSNBPage.validatePagination(),"Looks like Pagination is not proper");
    }

    /**
     * Testcase - Validate Google Ads on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testGoogleAdsOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.validateGoogleAds(),"Looks like Google Ads are not proper");
    }

    /**
     * Testcase - Validate Price Range Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testpriceRangeFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.priceRangeFilter("0-50000"), "Some Problem in Price Range Filter");
        Assert.assertTrue(carsHomePage.heading().contains("UsedCarsbetween"));
        Assert.assertEquals(carsSNBPage.getFilterBreadCrumbText().get(3), "0-50000", "Looks like price Range is not getting proper reflected on SNB");
    }

    /**
     * Testcase - Validate Mutiple Price Range Filter Close on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testpriceRangeFilterCloseOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.priceRangeFilter("0-50000"), "Some Problem in Price Range Filter");
        Assert.assertTrue(carsSNBPage.priceRangeFilter("50000-100000"), "Some Problem in Price Range Filter");
        Assert.assertTrue(carsHomePage.heading().contains("UsedCars"));
        Assert.assertEquals(carsSNBPage.getFilterBreadCrumbText().get(3), "0-50000", "Looks like price Range is not getting proper reflected on SNB");
        carsSNBPage.clearAllFiltersfromBreadCrumb();
        Assert.assertTrue(carsSNBPage.validateFilterBreadCrumbnotPresent(), "Looks like BreadCrumb of Price Filters are present");
        Assert.assertEquals(carsSNBPage.numberofShortListIcon(), 30, "Number of icons are not proper-->" + carsSNBPage.numberofShortListIcon());
    }

    /**
     * Testcase - Validate Fuel Type Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testFuelTypeFilterCloseOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.selectFuelType("CNG"), "Looks like Fuel Filter is not getting selected Pls Check");
        Assert.assertTrue(waitForPageToLoad("=Fuel_Type"), "Page not refreshing on selecting Fuel Type Filter");
        Assert.assertTrue(carsHomePage.heading().contains("UsedCNGCars"), "Looks like Heading for Search result for CNG Cars on SNB Page is not proper");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.validateSnbResultswithFuelType("CNG"), "Looks like SNB Search Results does not contain Fuel CNG");
    }

    /**
     * Testcase - Validate Multiple Fuel Type Filter Clear on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testMultipleFuelTypeFilterClearCloseOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.selectFuelType("CNG"), "Looks like Fuel Filter is not getting selected Pls Check");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.selectFuelType("Petrol"), "Looks like Fuel Filter is not getting selected Pls Check");
        Assert.assertTrue(waitForPageToLoad("=Fuel_Type"), "Page not refreshing on selecting Fuel Type Filter");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedCars"), "Looks like Heading for Search result for CNG Cars on SNB Page is not proper");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.getFilterBreadCrumbText().contains("CNG"), "Looks like Fuel Filter is not getting proper reflected on SNB");
        carsSNBPage.clearAllFiltersfromBreadCrumb();
        carsPageBase.closeCityPopup();
        waitForPageToLoad("quikr.com/Cars");
        Assert.assertFalse(carsSNBPage.getFilterBreadCrumbText().contains("CNG"), "Looks like BreadCrumb of Price Filters are present");
        carsPageBase.closeCityPopup();
        Assert.assertEquals(carsSNBPage.numberofShortListIcon(), 30, "Number of icons are not proper-->" + carsSNBPage.numberofShortListIcon());
    }

    /**
     * Testcase - Validate Premium Ads Selection from More Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testPremiumAdSectionfromMoreFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectFiltersFromMore("premium", "");
        waitForPageToLoad("showpremium");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.numberOfPremiumAds() > 20, "Looks like Premium Ads are not in proper Number-->" + carsSNBPage.numberOfPremiumAds());
    }

    /**
     * Testcase - Validate Reset Filters from More Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testResetFiltersfromMoreFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectFiltersFromMore("premium", "");
        waitForPageToLoad("showpremium");
        carsPageBase.closeCityPopup();
        carsSNBPage.selectFiltersFromMore("resetFilter", "");
        carsPageBase.closeCityPopup();
        //Assert.assertTrue(carsSNBPage.numberOfPremiumAds() == 3, "Looks like Premium Ads are not in proper Number after Reset Selection from More Section-->" + carsSNBPage.numberOfPremiumAds());
        Assert.assertFalse(carsSNBPage.getFilterBreadCrumbText().contains("Only Premium Ads"), "Looks like BreadCrumb of Filters are present");
    }

    /**
     * Testcase - Validate Posted By Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testPostedByFiltersfromMoreFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectFiltersFromMore("postedBy", "Individual");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.getFilterBreadCrumbText().contains("Individual"), "Looks like Filter Individual is not getting applied");
    }

    /**
     * Testcase - Validate Car Type Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testCarTypeFiltersfromMoreFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectFiltersFromMore("carType", "Hatchback");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedHatchbackCars"), "Looks like Search Page is not coming with Used Hatchback Cars pls Check");
        List<String> breadCrumbText=carsSNBPage.getFilterBreadCrumbText();
        Assert.assertEquals(carsSNBPage.getFilterBreadCrumbText().get(breadCrumbText.size()-1),"Hatchback","Looks like Breadcrumb for Hatchback is not coming");
    }

    /**
     * Testcase - Validate Transmission Type Filter on SNB Page
     */
    //@Test(groups = "carsSanity")
    public void testTransmissionTypeFiltersfromMoreFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectFiltersFromMore("transmission","Automatic");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedAutomaticCars"), "Looks like Search Page is not coming with Used Automatic Cars  pls Check");
        Assert.assertTrue(carsSNBPage.getBreadFiltersCrumbText().contains("Automatic"),"Looks like Breadcrumb for Automatic is not coming");
    }

    /**
     * Testcase - Validate Owner Type Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testOwnerFiltersfromMoreFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectFiltersFromMore("owner","One");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsHomePage.heading().contains("UsedCars"), "Looks like Search Page is not coming with Used Cars  pls Check");
        Assert.assertTrue(carsSNBPage.getFilterBreadCrumbText().contains("One"), "Looks like Breadcrumb for One Owner is not coming");
    }

    /**
     * Testcase - Validate Kms Driven Slider Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testKmsDrivenSliderfromMoreFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectFiltersFromMore("moveSlider", "");
        carsPageBase.closeCityPopup();
        List<String> breadCrumbText=carsSNBPage.getFilterBreadCrumbText();
        int breadCrumbtext=(Integer.parseInt(breadCrumbText.get(breadCrumbText.size()-1)));
        Assert.assertTrue(carsHomePage.heading().contains("UsedCarsbelow" + breadCrumbtext), "Looks like the Search Results heading is not proper after Kms selection-->" + carsHomePage.heading());
        Assert.assertTrue(carsSNBPage.validateKmsSnbResultsarebetweenSlider(breadCrumbtext));

    }

    /**
     * Testcase - Validate Year Of Make Filter on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testyearOfMakefromMoreFilterOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        String yearOfMake="2010";
        carsSNBPage.selectFiltersFromMore("yearOfMake", yearOfMake);
        carsPageBase.closeCityPopup();
        List<String> breadCrumbText=carsSNBPage.getFilterBreadCrumbText();
        String breadCrumbtext=breadCrumbText.get(breadCrumbText.size() - 1);
        Assert.assertTrue(carsHomePage.heading().contains("Used" + yearOfMake+"Cars"), "Looks like the Search Results heading is not proper after Kms selection-->" + carsHomePage.heading());
        Assert.assertTrue(breadCrumbtext.equals(yearOfMake),"Looks like BreadCrumb does not contain-->"+yearOfMake);

    }

    /**
     * Testcase - Validate Multiple Filter combinations on SNB Page
     */
    @Test(groups = "carsSanity")
    public void testMultipleFilterCombinationsOnSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        String yearOfMake="2010";
        carsSNBPage.selectFiltersFromMore("yearOfMake", yearOfMake);
        carsPageBase.closeCityPopup();
        List<String> breadCrumbText=carsSNBPage.getFilterBreadCrumbText();
        String breadCrumbtext=breadCrumbText.get(breadCrumbText.size() - 1);
        Assert.assertTrue(carsHomePage.heading().contains("Used" + yearOfMake+"Cars"), "Looks like the Search Results heading is not proper after Kms selection-->" + carsHomePage.heading());
        Assert.assertTrue(breadCrumbtext.equals(yearOfMake), "Looks like BreadCrumb does not contain-->" + yearOfMake);
        String brand = "Ford";
        carsSNBPage.selectBrand(brand);
        String brandName = carsSNBPage.brandsSelected();
        waitForPageToLoad("http://www.quikr.com/" + brand + "/Cars");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(brandName.contains(brand), "Looks like brand is not properly selected");
        carsSNBPage.sortBy("Least");
        Assert.assertTrue(waitForPageToLoad("kms_Driven"), "Looks like the Page is not loaded properly as URL is--->" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.validateSortByLeastDriven(), "Looks like Kms are not sorted Properly");

    }

    /**
     * Testcase - Validate Navigate to Quikr Home Page from SNB Page
     */
    @Test(groups = "carsSanity")
    public void testnavigatetoQuikrHomePagefromCarsSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.navigateToQuikrHomePagefromSNB();
        Assert.assertTrue(waitForPageToLoad("http://www.quikr.com/"),"Looks like Quikr Home Page is not getting loaded on Clicking Quikr Home Page Icon from Cars SNB Page");
    }

    /**
     * Testcase - Validate Navigate to Second Page from SNB Page
     */
    @Test(groups = "carsSanity")
    public void testSecondPagefromCarsSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.navigateToSecondPage();
        Assert.assertTrue(waitForPageToLoad("page=2"), "Looks like Second Page is not getting loaded on Clicking Pagination from Cars SNB Page");
        Assert.assertTrue(carsSNBPage.numberofShortListIcon()>0, "Number of icons are not proper-->" + carsSNBPage.numberofShortListIcon());
    }

    /**
     * Testcase - Validate Sign up for Quikr Alerts form
     */
    @Test(groups = "carsSanity")
    public void testSignUpfromCarsSNBAlerts() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectBrand("Hyundai");
        waitForPageToLoad("quikr.com/Hyundai/Cars/");
        carsPageBase.closeCityPopup();
        carsSNBPage.selectCityFromSNB("Bangalore");
        carsSNBPage.selectMinBudgetSignUpSection("50,000");
        carsSNBPage.selectMaxBudgetSignUpSection("1 lakh");
        carsSNBPage.setEmailinSignUpQuikrAlerts(getRandomString(10) + "@gmail.com");
        carsSNBPage.setMobileinSignUpQuikrAlerts("9" + getRandomInteger(9));
        carsSNBPage.clickCreateAlertButtonQuikrAlerts();
        Assert.assertEquals(carsSNBPage.getTextAfterAlertSubmission(), "Great! Your alert has been successfully created", "Looks like Alert is not getting created or Text is not proper");
    }

    /*
        verify "Look beyond secondhand Honda cars! Now find new cars on Quikr" section is getting displayed
     */
    @Test(groups = "carsSanity")
    public void validateLookBeyondText() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectBrand("Hyundai");
        waitForPageToLoad("quikr.com/Hyundai/Cars/");
        carsPageBase.closeCityPopup();
        carsSNBPage.selectCityFromSNB("Bangalore");
        Assert.assertEquals(carsSNBPage.getTextLookBeyondSection(), "Look Beyond Used Hyundai cars! Now find new cars on Quikr", "Looks like Look Beyond Section is not proper");
        Assert.assertTrue(carsSNBPage.validateLookBeyondSection("Hyundai"));
    }

    /*
        verify Find the best price for <model>  Cars form
     */
    @Test(groups = "carsSanity")
    public void validateBestPriceCarsForm() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        String brand="Hyundai";
        carsSNBPage.selectBrand(brand);
        waitForPageToLoad("quikr.com/Hyundai/Cars/");
        carsPageBase.closeCityPopup();
        carsSNBPage.selectCityFromSNB("Bangalore");
        Assert.assertTrue(carsSNBPage.validateFindBestPriceCars(brand));
    }

    /*
        validate change of city and result
     */
    @Test(groups = "carsSanity")
    public void validateCityChange() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        String city="Bangalore";
        carsSNBPage.selectCityFromSNB(city);
        waitForPageToLoad(city.toLowerCase() + "quikr.com");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.heading().contains("UsedCarsin" + city));
        String city1="Delhi";
        carsSNBPage.selectCityFromSNB(city1);
        waitForPageToLoad(city1.toLowerCase() + "quikr.com");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(carsSNBPage.heading().contains("UsedCarsin" + city1));
    }

    /*
        validate rply form submission for cars before login
     */
    @Test(groups = "carsSanity")
    public void validateReplySubmissionwithoutLoginSNB() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsSNBPage carsSNBPage = new CarsSNBPage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);

        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        String replyContent="Hi Test Message"+getRandomString(50);
        String replyMobile="9"+ getRandomInteger(9);
        String replyEmail=getRandomString(11)+"@gmail.com";
        Assert.assertTrue(carsSNBPage.validateReplyAdSNB(replyContent, replyMobile, replyEmail),"Looks like Reply Message was not sent properly");
    }

}