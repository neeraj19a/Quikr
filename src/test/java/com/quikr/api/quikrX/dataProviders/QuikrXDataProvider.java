package com.quikr.api.quikrX.dataProviders;

import com.quikr.api.quikrX.QuikrXEnumData;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.DataProvider;

/**
 * Created by francis.s@quikr on 12/11/15.
 */
public class QuikrXDataProvider {



    @DataProvider(name = "endToendPaymentMethods")
    public static Object[][] endToendPaymentMethods()
    {
        return new Object[][]{
          {"1000","1125","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","certified", QuikrXEnumData.paymentMehtod.COD,""},
          {"1000","1118","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","exchange",QuikrXEnumData.paymentMehtod.CREDIT_CARD,"1068"},
          {"1000","1118","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","exchange",QuikrXEnumData.paymentMehtod.NETBANKING,"1068"},
        };
    }

    @DataProvider(name = "endToendFlows")
    public static Object[][] endToendFlows()
    {
        return new Object[][]{
                {"1000","1125","myorders.integ@test.com","certified",""},
                {"1000","1118","myorders.integ@test.com","exchange","1068"},
        };
    }


    @DataProvider(name = "oneRupeeScenario")
    public static Object[][] oneRupeeScenario()
    {
        return new Object[][]{
                {"1000","1118","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","exchange","1068"},
        };
    }

    @DataProvider(name = "iDontHaveaPhone")
    public static Object[][] iDontHaveaPhone()
    {
        return new Object[][]{
                {"1000","1118","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","exchange","1304"},
        };
    }

    @DataProvider(name = "replacement")
    public static Object[][] replacement()
    {
        return new Object[][]{
//                {"1000","1140","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","certified",""},
                {"1000","1218","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","exchange","1068"},
        };
    }

    @DataProvider(name = "skuDiscount")
    public static Object[][] skuDiscount(){

        return new Object[][]{
                {"1503","Xiaomi Mi 3 (16 GB) - Unboxed","Xiaomi","1000","50","1"},
                {"1140","Micromax Canvas 2","Micromax","1000","10","2"}
        };
    }

    @DataProvider(name = "B2CBrands")
    public static Object[][] B2CBrands(){
        return new Object[][]{
                {"Apple","iPhone%204%208%20GB","22",true},
                {"Apple","iPhone%205%2032%20GB","22",true},
//                {"IBM","5233","223",false}
        };
    }

    @DataProvider(name="exchangeAndroid")
    public static Object[][] exchangeAndroid(){

        return new Object[][]{
                {"","Please select a category"},
                {"65465","Invalid Category"}
        };
    }


    @DataProvider(name = "B2CBrandsWithScore")
    public static Object[][] B2CBrandsWithScore(){
        return new Object[][]{
//                {"Micromax","Canvas%20Nitro","22","70",5,"1140"},
                {"Apple","iPhone%204%208%20GB","22","83",10,"1088"},
                {"LG","Nexus%205%2032%20GB","22","93",15,"1260"},
                {"HTC","DESIRE%20816","22","100",20,"1166"},

        };
    }

    @DataProvider(name = "sellersFilter")
    public static Object[][] sellersFilter(){
        return new Object[][]{
                {"64","1006"},
                {"58","1009"}

        };
    }


    @DataProvider(name="skuDiscountTests")
    public static Object[][] skuDiscounts(){
        return new Object[][]{
                {"1503"},
                {"1530"},
                {"1455"},
        };
    }

    @DataProvider(name="onlineDiscountTests")
    public static Object[][] onlineDiscountTests() {
        return new Object[][]{
                {"1121", 20},
                {"1118", 5},
                {"1455", 10},
        };
    }

    @DataProvider(name = "cancelOrder")
    public static Object[][] cancelOrder()
    {
        return new Object[][]{
                {"1000","1125","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","certified",""},
                {"1000","1118","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com","exchange","1068"},
        };
    }

    @DataProvider(name = "gradeItems")
    public static Object[][] gradeItems()
    {
        return new Object[][]{
                {"1124",1,"Excellent"},
                {"3120",2,"Good"},
//                {"1152",3,"Fair"}
        };
    }

    @DataProvider(name = "gradeFilter")
    public static Object[][] gradeFilter()
    {
        return new Object[][]{
                {"1","Like New"},
                {"2","Good"},
                {"3","Fair"}
        };
    }


    @DataProvider(name = "b2cGrdation")
    public static Object[][] b2cGrdation(){
        return new Object[][]{
                {"Apple","iPhone%204%208%20GB","100","Excellent",0},
                {"Apple","iPhone%205%2016%20GB","80","Good",10},
                {"HTC","Desire%20X","70","Fair",15},
                {"HTC","Desire%20X","100","Bad",25},
                {"HTC","DESIRE%20816","100","Bad",25},

        };
    }

    @DataProvider(name = "exchangeFlow")
    public static Object[][] exchangeFlow()
    {
        return new Object[][]{
                {"1000","1118","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com"},
                {"1000","1118","testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com"}
        };
    }


}
