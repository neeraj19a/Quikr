package com.quikr.website.cars.carsSNB;

import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.cars.carsHome.CarsHomePage;
import com.quikr.website.cars.carsVAP.NewCarsVapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by quikr on 10/8/16.
 */
public class NewCarsSNBTests extends CarsTestBase {

    @Test(groups = "carsSanity")
    public void newCarsSNBPageLand(){

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        CarsSNBPage carsSNBPage=new CarsSNBPage(driver);
        NewCarsSNBPage newCarsSNBPage =new NewCarsSNBPage(driver);


        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        Assert.assertTrue(carsSNBPage.heading().contains(brand+"CarsinAll-India"),"Looks like Heading of the New Cars SNB is not proper");
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds>0,"Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.numberofProductsFromNewCarsSearchResult()>1,"Looks like Search Results for New Cars is not 10 instead it is-->"+newCarsSNBPage.numberofProductsFromNewCarsSearchResult());
    }

    @Test(groups = "carsSanity")
    public void validateNewCarsSNBResultsImageAttributes() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds>0,"Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.validateNewCarsImageOnSNB(), "Looks like Image attributes are not proper on New Cars SNB Page");
    }

    @Test(groups = "carsSanity")
    public void validatePriceforNewCarsSNBResults() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds>0,"Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.validateProperPriceforNewCarsSNBResults(),"Looks like Price for the Cars are not proper on New Cars SNB Page");
    }

    @Test(groups = "carsSanity")
    public void validateNewCarsAttributesSnbResults() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds>0,"Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.validateNewCarsAttributesfromSnbResults(),"Looks like Attributes for the New Cars are not proper on New Cars SNB Page");
    }

    @Test(groups = "carsSanity")
    public void validateNewCarsSnbResultAttributesValues() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds>0,"Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.validateNewCarsSnbResultAttributesValues(),"Looks like Attributes Values for the New Cars are not proper on New Cars SNB Page");
    }

    @Test(groups = "carsSanity")
    public void validateNumberofRoadPriceButtons() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds>0,"Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.numberofRoadPriceButtons()==numberOfSNBAds-1,"Looks like On Road Price Buttons for the New Cars are not proper on New Cars SNB Page Expected-->"+numberOfSNBAds+" but actual is"+newCarsSNBPage.numberofRoadPriceButtons());
    }

    @Test(groups = "carsSanity")
    public void validateMostPopularComparison() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);
        SoftAssert softAssert=new SoftAssert();

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds > 0, "Looks like SNB Ads for New Cars was not displayed");
        softAssert.assertTrue(newCarsSNBPage.validateMostPopularComparisonImages(), "Looks like Images for Most Popular Cars Comparsion for the New Cars are not proper on New Cars SNB Search Results");
        Assert.assertTrue(newCarsSNBPage.numberOfVSOnMostPopularCarsComparison(), "Looks like VS is not 2 ");
        softAssert.assertTrue(newCarsSNBPage.validateMostPopularCarsName(),"Looks like Text for Every Cars to be compared is not coming");
        softAssert.assertTrue(newCarsSNBPage.validateMostPopularCarsPrice(),"Looks like the price of New Cars Comparison is Not Proper");
        softAssert.assertAll();
    }

    @Test(groups = "carsSanity")
    public void validateGetOnRoadPriceNewCars() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds > 0, "Looks like SNB Ads for New Cars was not displayed");
        String nameOfLead=getRandomString(10);
        String mobileOfLead="9" + getRandomInteger(9);
        String emailOfLead=getRandomString(11) + "@gmail.com";
        newCarsSNBPage.validateGetOnRoadPrice(nameOfLead, mobileOfLead, emailOfLead);
        Assert.assertTrue(getCurrentUrl().contains("leadUName=" + nameOfLead + "&leadUMobile=" + mobileOfLead + "&leadUEmail=" + emailOfLead), "Looks like Url is not capturing parameters Name of Lead, Mobile of Lead, Email of Lead Pls Check");
        logger.info("Actual is" + getCurrentUrl());
        logger.info("Expected is-->" + "leadUName=" + nameOfLead + "&leadUMobile=" + mobileOfLead + "&leadUEmail=" + emailOfLead);
    }

    @Test(groups = "carsSanity")
    public void validateCheckAnotherCarPriceButtonNewCars() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds > 0, "Looks like SNB Ads for New Cars was not displayed");
        String nameOfLead=getRandomString(10);
        String mobileOfLead="9" + getRandomInteger(9);
        String emailOfLead=getRandomString(11) + "@gmail.com";
        newCarsSNBPage.validateGetOnRoadPrice(nameOfLead,mobileOfLead ,emailOfLead );
        Assert.assertTrue(getCurrentUrl().contains("leadUName=" + nameOfLead + "&leadUMobile=" + mobileOfLead + "&leadUEmail=" + emailOfLead), "Looks like Url is not capturing parameters Name of Lead, Mobile of Lead, Email of Lead Pls Check");
        logger.info("Actual is" + getCurrentUrl());
        logger.info("Expected is-->"+"leadUName=" + nameOfLead + "&leadUMobile=" + mobileOfLead + "&leadUEmail=" + emailOfLead);
        Assert.assertTrue(newCarsSNBPage.isCheckAnotherCarworking(), "Looks like On Road Price Calculator Page is not loaded");
    }

    @Test(groups = "carsSanity")
    public void validateNotifyUpcomingCarsNewCars() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        Assert.assertTrue(newCarsSNBPage.validateToggleShowUpcomingCars(), "Looks like Toggle for Upcoming Cars not working");
        String name=getRandomString(10);
        String email=getRandomString(15)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        String city="Delhi";
        Assert.assertTrue(newCarsSNBPage.validateNotifyMe(name, email, mobile, city),"Looks like Notify me not working as expected");
    }

    /*
        validate sort By Lowest Price for Upcoming Cars
     */
    @Test(groups = "carsSanity")
    public void validateSortByLowestPriceUpcomingCars() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds > 0, "Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.validateToggleShowUpcomingCars(), "Looks like Toggle for Upcoming Cars not working");
        waitForPageToLoad("quikr.com/cars/upcoming");
        carsPageBase.closeCityPopup();
        newCarsSNBPage.sortBy("Lowest Price");
        Assert.assertTrue(waitForPageToLoad("priceAsc"), "Looks like the Page is not loaded properly as URL is--->" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(newCarsSNBPage.isPriceSort("Lowest Price"),"Sort not proper pls check");
    }

    /*
        validate sort By Highest Price for Upcoming Cars
     */
    @Test(groups = "carsSanity")
    public void validateSortByHighestPrimceUpcomingCars() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        String brand = "Ford";
        newCarsSNBPage.selectBrandNewSNBPage(brand);
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds > 0, "Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.validateToggleShowUpcomingCars(), "Looks like Toggle for Upcoming Cars not working");
        waitForPageToLoad("quikr.com/cars/upcoming");
        carsPageBase.closeCityPopup();
        newCarsSNBPage.sortBy("Highest Price");
        Assert.assertTrue(waitForPageToLoad("priceDesc"), "Looks like the Page is not loaded properly as URL is--->" + getCurrentUrl());
        carsPageBase.closeCityPopup();
        Assert.assertTrue(newCarsSNBPage.isPriceSort("Highest Price"),"Sort not proper pls check");
    }

     /*
      validate navigation to model page from snb for Upcoming Cars
     */
    @Test(groups = "carsSanity")
    public void validateNavigationToModelPageforUpcomingCars() {

        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);
        NewCarsVapPage newCarsVapPage=new NewCarsVapPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        Assert.assertTrue(newCarsSNBPage.validateToggleShowUpcomingCars(), "Looks like Toggle for Upcoming Cars not working");
        waitForPageToLoad("quikr.com/cars/upcoming");
        carsPageBase.closeCityPopup();
        Assert.assertTrue(newCarsSNBPage.navigationToModelPageForUpcomingCars(newCarsVapPage),"Looks like Models page is not correct");
    }

}
