package com.quikr.app.android.horizontalTests.horizontalPostAd;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.cars.CarsPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Swatantra singh on 11/2/16.
 */
public class HoriZontalPostAdForCars extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HORIZONTAL_TESTDATA_FILE"));



    @Stories("PostAd")
    @Title("User is Able To Post Ad")
    @Test(groups = "horizontal")
    public void postAd()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd());
    }

    @Stories("PostAd")
    @Title("Post Ad With Incomplete Title")
    @Test(groups = "horizontal")
    public void PostAdWithIncompleteTitle()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("incompleteAdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.scroll("Ad Title");
        Assert.assertTrue(papPage.validateIncompleteTitle());
    }
    @Stories("PostAd")
    @Title("Post Ad with incomplete Description")
    @Test(groups = "horizontal")
    public void PostAdWithIncompleteDescription()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectelementWithoutScroll("Model", QuikrAppEnums.PAP_Radio_elements);
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("incompleteDesc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.scroll("10 more");
        Assert.assertTrue(papPage.validateIncompleteDescription());
    }
    @Stories("PostAd")
    @Title("PostAd AS Start Fresh")
    @Test(groups = "horizontal")
    public void PostAdAsStartFresh() throws Exception
    {

        Hompage hompage = new Hompage(driver);
        PapPage papPage=new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.explicitWait(5);
        papPage.navigateBack();
        papPage.clickOnPositiveOkButton();
        hompage.clickonPostAd();
        papPage.postAdAsStartFresh();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectelementWithoutScroll("Model", QuikrAppEnums.PAP_Radio_elements);
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), testData.get("cars"));
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), testData.get("cars"));
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd());
    }
    @Stories("PostAd")
    @Title("Post Ad As Continue")
    @Test(groups = "horizontal")
    public void PostAdAsContinue()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.navigateBack();
        papPage.clickOnPositiveOkButton();
        hompage.clickonPostAd();
        papPage.postAdAsContine();
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd());
    }

    @Stories("PostAd")
    @Title("Preview Post Ad")
    @Test(groups = "horizontal")
    public void verifyPreviewPostAd()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd());
        papPage.selectViewMyAdButton();
        Assert.assertTrue(papPage.validatePreviewPostAd(), "can't able to see preview ad post");
        papPage.selectDoneButtonAtVap();
        Assert.assertTrue(papPage.validatePostAd(), "After preview post ad done the msg of congratulation can't reflected");
    }


    /**
     *  android 572 ,278
     * Validate server side error for posting add without model
     *
     */
    @Stories("PostAd")
    @Test(dataProvider = "CrasSubCat",dataProviderClass = Data.class ,groups = "horizontal")
    public void   postAdWithoutModelAsOfferType(String subcat)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        CarsPage carsPage=new CarsPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(subcat, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        List<Integer> cordinates=alertPage.swipeCordinatesFprCreateAlert();
        System.out.println(cordinates);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        switch (subcat)
        {
            case "Cars":
                papPage.selectBrand();
                papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.selectYear();
                papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.scroll("Enter Kms Driven");
                papPage.selectDistanceDriven(testData.get("distance"));
                papPage.selectColor();
                papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.selectFuelType();
                papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.selectOwners();
                papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.setAdTitle(testData.get("AdTitle"), category);
                papPage.navigateBack();
                papPage.setAdDescription(testData.get("desc"), category);
                papPage.navigateBack();
                papPage.clickonAdlocation();
                papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
                papPage.provideName(testData.get("name"));
                papPage.submitAd();
                String msgs = carsPage.validateErrorMsgSelectModel();
                System.out.println(msgs);
                Assert.assertTrue(msgs.contains(testData.get("modelAssertionString")),"" +testData.get("modelAssertionString") + "->" +msgs);

                break;
            case "Bikes & Scooters":
                papPage.selectBrand();
                papPage.selectelementWithoutScroll(testData.get("bikeBrand"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.selectYear();
                papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.selectDistanceDriven(testData.get("distance"));
                papPage.verticalSwipeWithCordinates(cordinates.get(0), cordinates.get(1));
                papPage.selectOwners();
                papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.setAdTitle(testData.get("AdTitle"), category);
                papPage.navigateBack();
                papPage.setAdDescription(testData.get("desc"), category);
                papPage.navigateBack();
                papPage.clickonAdlocation();
                papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
                papPage.provideName(testData.get("name"));
                papPage.navigateBack();
                papPage.verticalSwipeWithCordinates(cordinates.get(0), cordinates.get(1));
                papPage.submitAd();
                String msgs1 = carsPage.validateErrorMsgSelectModel();
                System.out.println(msgs1);
                Assert.assertTrue(msgs1.contains(testData.get("modelAssertionString")),"" +testData.get("modelAssertionString") + "->" +msgs1);
        }

    }



    /**
     * android 587,
     * Validate server side error for posting add without model
     *
     */
    @Stories("PostAd")
    @Title("Post Ad Without Model as Want Type For CarsSubCat")
    @Test(groups = "horizontal")
    public void postAdWithoutModelAsWantTypeCarsSubcategory()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        AlertPage alertPage =new AlertPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.postAdAsWantType();
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectElements("Brand", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Year", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Color", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("color"),QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Fuel Type", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("fuel"),QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectElements("Number of", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd(),"User is not able to post Ad as want type with specifying Model");


    }
    /**
     * android 591
     */
    @Stories("PostAd")
    @Title("Post Ad ad Offer Type for subCat Commercial Vehicle ")

    @Test(groups = "horizontal")
    public void postAdOfferTypeCommercialVehicle()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("commercialVehicle"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.selectelementWithoutScroll(testData.get("vehicleType"), QuikrAppEnums.PAP_Radio_elements);
        papPage.selectelementWithoutScroll(testData.get("Boat"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setPrice(testData.get("price"));
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd(),"User is not able to post Ad as Offer type with specifying Model");

    }

    /**
     *Android 599
     */
    @Stories("PostAd")
    @Title("Post Ad ad Want Type for subCat Commercial Vehicle ")

    @Test(groups = "horizontal")
    public void postAdAsWantTypeCommercialVehicle()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("commercialVehicle"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.postAdAsWantType();
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.selectelementWithoutScroll(testData.get("vehicleType"), QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("Boat"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.setPrice(testData.get("price"));
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd(),"User is not able to post Ad as Offer type with specifying Model");


    }

    /**
     *Android 599
     */
    @Stories("PostAd")
    @Title("Post Ad ad Want Type for subCat Others Vehicle ")

    @Test
    public void postAdAsWantTypeOtherVechile()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("otherVehicle"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.postAdAsWantType();
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd(),"User is not able to post Ad as Offer type with specifying Model");


    }

    /**
     * android 592
     */
    @Stories("PostAd")
    @Title("Post Ad ad Offer Type for subCat Others Vehicle ")
    @Test
    public void postAdAsOfferTypeOtherVehicle()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("otherVehicle"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd(),"User is not able to post Ad as Offer type with specifying Model");


    }
    @Stories("PostAd")
    @Title("Post Ad without Model As Want type for Bikes subCategory")
    @Test
    public void postAdWithoutModelAsWantTypeBikesSubCategory()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage =new PapPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        CarsPage carsPage=new CarsPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("bikes"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.postAdAsWantType();
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectelementWithoutScroll("Brand", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("bikeBrand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Year", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Number of", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd(),"User is not able to post Ad as want type with specifying Model");


    }

    /**
     * validate eXchange Of cars after post ad
     */
    @Stories("PostAd")
    @Title("validate eXchange Of cars after post ad")
    @Test
    public void validateExchangeCarsAfterPostAd()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.clickOnExchangeCarsAfterPostAd();
        papPage.skipAds();
        papPage.clickOnusedcarsForExchangeAfterPostAd();
        papPage.selectExchangeBrand();
        hompage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        papPage.clickOnDoneBUttonforExchangeCArs();
        Assert.assertTrue(papPage.validateOnOnselectingUsedCarsUserIsRedirectedToUSedCarsPAge(), "on clicking Done button on Exchange page user is not redirected to exchange cars page");
        papPage.clickonexploreButton();
        snbPage.hideOverlayLayout();
        String title=carsNewPage.snbAdTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains(testData.get("brand")),"brand selected during Exchange is not reflected  on Snb");
    }

}
