package com.quikr.website.horizontal.alert;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akhil.singla on 31/5/16.
 */
public class MobilesandTabletsAlertTests extends TestBase {

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void MobilesandTabletswithAccessories(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Mobiles & Tablets");
        alertPage.selectSubCategory("Accessories");
        alertPage.selectadType(adType);
        alertPage.clickAccessoryType();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsMobile(4, email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }


    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void MobilesandTabletswithMobilePhones(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Mobiles & Tablets");
        alertPage.selectSubCategory("Mobile Phones");
        alertPage.selectadType(adType);
        alertPage.selectNumberofsims();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsMobile(2,email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void MobilesandTabletswithTablets(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Mobiles & Tablets");
        alertPage.selectSubCategory("Tablets");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsMobile(2,email, mobile);
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }


}
