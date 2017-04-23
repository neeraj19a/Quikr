package com.quikr.website.horizontal.alert;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akhil.singla on 31/5/16.
 */
public class EntertainmentAlertTests extends TestBase {

    @Test(groups = "alerts")
    public void EntertainmentwithActingModelingRoles(){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Entertainment");
        alertPage.selectSubCategory("Acting - Modeling Roles");
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Acting-Modeling-Roles"), "Expected URL was quikr.com/Acting-Modeling-Roles " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts")
    public void EntertainmentwithActingSchools(){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Entertainment");
        alertPage.selectSubCategory("Acting Schools");
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void EntertainmentwithArtDirectorsEditors(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Entertainment");
        alertPage.selectSubCategory("Art Directors - Editors");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section") || waitForPageToLoad("Art-Directors-Editors"), "Expected URL was quikr.com/recommended-section or quikr.com/Art-Directors-Editors" + "But Found " + getCurrentUrl());

    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void EntertainmentwithModelingAgencies(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Entertainment");
        alertPage.selectSubCategory("Modeling Agencies");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Modeling-Agencies") || waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/Modeling-Agencies or quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void EntertainmentwithPhotographersCameraman(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Entertainment");
        alertPage.selectSubCategory("Photographers - Cameraman");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Photographers-Cameraman") || waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/Photographers-Cameraman  or quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void EntertainmentwithStudiosLocationsforhire(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Entertainment");
        alertPage.selectSubCategory("Studios - Locations for hire");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void EntertainmentwithOtherEntertainment(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Entertainment");
        alertPage.selectSubCategory("Other Entertainment");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("Other-Entertainment") || waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/Other-Entertainment or quikr.com/recommended-section" + "But Found " + getCurrentUrl());
    }

}
