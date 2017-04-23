package com.quikr.website.horizontal.alert;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by quikr on 31/5/16.
 */
public class ServicesAlertTests1 extends TestBase {



    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithAdvertisingDesign(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Advertising - Design");
        alertPage.selectadType(adType);
        alertPage.clicktypeServices();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithAirlineTrainBusTickets(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Airline - Train - Bus Tickets");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithComputerRepairandService(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Computer Repair and Service");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.selectCityService();
        alertPage.setEmailAddress(email);
        alertPage.setMobileNumber(mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Services") || waitForPageToLoad("recommended-section"), "Expected URL was http://www.quikr.com/Services or quikr.com/recommended-section" + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithCooks(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Cooks");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.selectCityService();
        alertPage.setEmailAddress(email);
        alertPage.setMobileNumber(mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Services") || waitForPageToLoad("recommended-section"), "Expected URL was http://www.quikr.com/Services or quikr.com/recommended-section" + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithCourierServices(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Courier Services");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithCreditCards(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Credit Cards");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithCreditScoreManagement(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Credit Score Management");
        alertPage.selectadType(adType);
        alertPage.clicktypeServices();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithDanceClasses(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Dance Classes");
        alertPage.selectadType(adType);
        alertPage.clicktypeServices();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithDetectiveServices(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Detective Services");
        alertPage.selectadType(adType);
        alertPage.clicktypeServices();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithDJs(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("DJs");
        alertPage.selectadType(adType);
        alertPage.clicktypeServices();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithDoctors(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Doctors");
        alertPage.selectadType(adType);
        alertPage.clicktypeServices();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

}
