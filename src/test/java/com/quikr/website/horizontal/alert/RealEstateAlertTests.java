package com.quikr.website.horizontal.alert;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akhil.singla on 31/5/16.
 */
public class RealEstateAlertTests extends TestBase {

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void RealEstatewithCommercialPropertyforRent(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Commercial Property for Rent");
        alertPage.selectadType(adType);
        alertPage.clickAreaSqFt();
        alertPage.clickFurnished();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void RealEstatewithCommercialPropertyforSale(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Commercial Property for Sale");
        alertPage.selectadType(adType);
        alertPage.clickAreaSqFt();
        alertPage.clickFurnished();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts")
    public void RealEstatewithFlatmates(){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Flatmates");
        alertPage.clickLookingForRealEstateOptions(1);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void RealEstatewithHousesApartmentsforRent(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Houses - Apartments for Rent");
        alertPage.selectadType(adType);
        alertPage.clickYouareRealEstateOptions();
        alertPage.enterPrice(30000);
        alertPage.selectNoOfRooms();
        alertPage.clickAreaSqFt();
        alertPage.clickFurnished();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void RealEstatewithHousesApartmentsforSale(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Houses - Apartments for Sale");
        alertPage.selectadType(adType);
        alertPage.clickYouareRealEstateOptions();
        alertPage.enterPrice(30000);
        alertPage.selectNoOfRooms();
        alertPage.clickAreaSqFt();
        alertPage.clickFurnished();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void RealEstatewithLandPlotForSale(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Land - Plot For Sale");
        alertPage.selectadType(adType);
        alertPage.clicktypeOfLand();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void RealEstatewithPayingGuestHostel(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Paying Guest - Hostel");
        alertPage.selectadType(adType);
        alertPage.clickAccommodationFor(2);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts")
    public void RealEstatewithResidentialBuilderfloorsForRent(){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Residential - Builder floors For Rent");
        scrollVerticallWithCords(2800,2500);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Residential-Builder-floors-For-Rent") || waitForPageToLoad("recommended-section") || waitForPageToLoad("homes"), "Expected URL was Residential-Builder-floors-For-Rent or recommended-section or homes" + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts")
    public void RealEstatewithResidentialBuilderfloorsForSale(){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Residential - Builder floors For Sale");
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Residential-Builder-floors-For-Sale") || waitForPageToLoad("recommended-section") || waitForPageToLoad("homes"), "Expected URL was quikr.com/recommended-section or quikr.com/residential-builderfloors" + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void RealEstatewithServiceApartments(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Service Apartments");
        alertPage.selectadType(adType);
        alertPage.selectNoOfRooms();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void RealEstatewithVacationRentalsTimeshare(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Vacation Rentals - Timeshare");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void RealEstatewithVillasBungalowsforRent(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Villas/Bungalows for Rent");
        alertPage.selectadType(adType);
        alertPage.clickYouareRealEstateOptions();
        alertPage.enterPrice(30000);
        scrollVerticallWithCords(2900,2500);
        alertPage.selectNoOfRooms();
        alertPage.clickFurnished();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void RealEstatewithVillasBungalowsforSale(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Real Estate");
        alertPage.selectSubCategory("Villas/Bungalows for Sale");
        alertPage.selectadType(adType);
        alertPage.clickYouareRealEstateOptions();
        alertPage.enterPrice(30000);
        alertPage.selectNoOfRooms();
        alertPage.clickFurnished();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsRealEstate(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

}
