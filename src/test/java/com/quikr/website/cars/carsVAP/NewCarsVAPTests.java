package com.quikr.website.cars.carsVAP;

import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.cars.carsHome.CarsHomePage;
import com.quikr.website.cars.carsSNB.CarsSNBPage;
import com.quikr.website.cars.carsSNB.NewCarsSNBPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 24/8/16.
 */
public class NewCarsVAPTests extends CarsTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /*
    validate locate a dealer button
    */
    @Test
    public void testContactDealer(){
        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);
        NewCarsVapPage newCarsVapPage=new NewCarsVapPage(driver);
        CarsSNBPage carsSNBPage=new CarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds > 0, "Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.validateToggleShowUpcomingCars(), "Looks like Toggle for Upcoming Cars not working");
        waitForPageToLoad("quikr.com/cars/upcoming");
        carsSNBPage.selectCityFromSNB("Bangalore");
        carsPageBase.closeCityPopup();
        newCarsSNBPage.navigatetoCarsVAPPage();
        Assert.assertTrue(newCarsVapPage.clickContactDealer("Bangalore"), "Looks like Contact Dealer page is not coming");
        String name=getRandomString(10);
        String mobile="9"+getRandomInteger(9);
        String email=getRandomString(10)+"@gmail.com";
        Assert.assertTrue(newCarsVapPage.clickContactDealersButton(name,mobile,email),"Looks like Dealer Form is not working properly");
    }

    /*
    validate locate a dealer from another car button
    */
    @Test
    public void testContactDealerForAnotherCar(){
        CarsHomePage carsHomePage = new CarsHomePage(driver);
        CarsPageBase carsPageBase = new CarsPageBase(driver);
        NewCarsSNBPage newCarsSNBPage = new NewCarsSNBPage(driver);
        NewCarsVapPage newCarsVapPage=new NewCarsVapPage(driver);
        CarsSNBPage carsSNBPage=new CarsSNBPage(driver);

        navigateToCarsHomePage();
        carsPageBase.closeCityPopup();
        carsHomePage.clickNewCarsRadioButton();
        carsHomePage.search();
        Assert.assertTrue(waitForPageToLoad("cars/new"), "Looks like New Cars SNB page is not loading pls check");
        int numberOfSNBAds=newCarsSNBPage.numberofNewCarsAdsSNBSearchResults();
        Assert.assertTrue(numberOfSNBAds > 0, "Looks like SNB Ads for New Cars was not displayed");
        Assert.assertTrue(newCarsSNBPage.validateToggleShowUpcomingCars(), "Looks like Toggle for Upcoming Cars not working");
        waitForPageToLoad("quikr.com/cars/upcoming");
        carsSNBPage.selectCityFromSNB("Bangalore");
        carsPageBase.closeCityPopup();
        newCarsSNBPage.navigatetoCarsVAPPage();
        Assert.assertTrue(newCarsVapPage.clickContactDealer("Bangalore"), "Looks like Contact Dealer page is not coming");
        String name=getRandomString(10);
        String mobile="9"+getRandomInteger(9);
        String email=getRandomString(10)+"@gmail.com";
        Assert.assertTrue(newCarsVapPage.clickContactDealersButton(name, mobile, email), "Looks like Dealer Form is not working properly");
        newCarsVapPage.clickFindDealers();
        Assert.assertTrue(newCarsVapPage.checkContactDealersButtonPreFilled(), "Contact Form seems to be not filled");
    }

}
