package com.quikr.website.horizontal.alert;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akhil.singla on 31/5/16.
 */
public class ServicesAlertTests extends TestBase {


    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithArchitect(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Architect");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithArtsClasses(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Arts Classes");
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
    public void ServiceswithAstrologyNumerology(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Astrology - Numerology");
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
    public void ServiceswithBabySittersNanny(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Baby Sitters - Nanny");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.selectCityService();
        alertPage.setEmailAddress(email);
        alertPage.setMobileNumber(mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Services") || waitForPageToLoad("recommended-section"), "Expected URL was http://www.quikr.com/Services or http://www.quikr.com/recommended-section  " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithBankAcounts(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Bank Acounts");
        alertPage.selectadType(adType);
        alertPage.clicktypeServices();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.selectCityService();
        alertPage.setEmailAddress(email);
        alertPage.setMobileNumber(mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithBatteriesInvertors(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Batteries and Invertors");
        alertPage.selectadType(adType);
        alertPage.clicktypeServices();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.selectCityService();
        alertPage.setEmailAddress(email);
        alertPage.setMobileNumber(mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithBusinessOffers(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Business Offers");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsServices(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithCarpentersFurnitureWork(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Carpenters - Furniture Work");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.selectCityService();
        alertPage.setEmailAddress(email);
        alertPage.setMobileNumber(mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Services") || waitForPageToLoad("recommended-section"), "Expected URL was http://www.quikr.com/Services or quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithCateringTiffinServices(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Catering -Tiffin Services");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.selectCityService();
        alertPage.setEmailAddress(email);
        alertPage.setMobileNumber(mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Services") ||waitForPageToLoad("recommended-section"), "Expected URL was http://www.quikr.com/Services or quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    //Subcategory does not exist on Production
    //@Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ServiceswithCompetitiveExamCoaching(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Services");
        alertPage.selectSubCategory("Competitive Exam Coaching");
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
