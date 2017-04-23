package com.quikr.website.horizontal.alert;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akhil.singla on 31/5/16.
 */
public class CarsandBikesAlertTests extends TestBase {

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void CarsandBikeswithBikesAndScooters(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Cars & Bikes");
        alertPage.selectSubCategory("Bikes & Scooters");
        alertPage.selectadType(adType);
        alertPage.selectBrand();
        alertPage.selectYear();
        alertPage.selectRandomModel();
        alertPage.enterPrice(8000);
        alertPage.clickOwner();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(8000,email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void CarsandBikeswithBicycles(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Cars & Bikes");
        alertPage.selectSubCategory("Bicycles");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(8000, email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void CarsandBikeswithCars(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Cars & Bikes");
        alertPage.selectSubCategory("Cars");
        alertPage.selectadType(adType);
        alertPage.selectBrand();
        alertPage.selectRandomModel();
        alertPage.selectYear();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(8000, email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void CarsandBikeswithCommercialVehicles(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Cars & Bikes");
        alertPage.selectSubCategory("Commercial Vehicles");
        alertPage.selectadType(adType);
        alertPage.clickVehicleType();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(8000, email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void CarsandBikeswithSpareParts(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Cars & Bikes");
        alertPage.selectSubCategory("Spare Parts - Accessories");
        alertPage.selectadType(adType);
        alertPage.clickProductType();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(8000, email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void CarsandBikeswithOtherVehicles(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Cars & Bikes");
        alertPage.selectSubCategory("Other Vehicles");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(8000, email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

}
