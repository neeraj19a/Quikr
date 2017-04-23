package com.quikr.app.android.cars.carsPap;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.cars.CarsPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra singh on 14/8/15.
 */
public class CarsPapTests extends AppAndroidTestBase
{


    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_CARS_TESTDATA_FILE"));

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
        papPage.selectelementWithoutScroll("Model",QuikrAppEnums.PAP_Radio_elements);
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


   // @Test(groups = "horizontal")
    public void PostAdAsStartFresh()
    {

        Hompage hompage = new Hompage(driver);
        PapPage papPage=new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.navigateBack();
        papPage.clickOnPositiveOkButton();
        hompage.clickonPostAd();
        papPage.postAdAsStartFresh();
        papPage.getPapCategory();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();

    }

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

   // @Test(groups = "horizontal")
    public void validateEditAd()
    {
        MenuPage menuePage = new MenuPage(driver);
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        SnbPage snbPage = new SnbPage(driver);

        int i = 0;
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAds"), QuikrAppEnums.CATEGORY_MENU);
        snbPage.openAdFromSnb(i);
        papPage.clickOnEditAd();
        papPage.editAdTitle();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll(testData.get("distance"));
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.scroll(testData.get("save"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd());
    }



    /**
     *  android 572 ,278
     * Validate server side error for posting add without model
     *
     */
    @Test(dataProvider = "CrasSubCat",dataProviderClass = Data.class ,groups = "horizontal")
    public void postAdWithoutModelAsOfferType(String subcat)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        CarsPage carsPage=new CarsPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(subcat, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
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

                break;
            case "Bikes & Scooters":
                papPage.selectBrand();
                papPage.selectelementWithoutScroll(testData.get("bikeBrand"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.selectYear();
                papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
                papPage.scroll("Enter Kms Driven");
                papPage.selectDistanceDriven(testData.get("distance"));
        }
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
    }



    /**
     * android 587,
     * Validate server side error for posting add without model
     *
     */
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

    @Test(groups = "horizontal")
    public void postAdWithoutModelAsOfferTypeCommercialVechile()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("commercialVehicle"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.postAdAsWantType();
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
    @Test(groups = "horizontal")
    public void postAdAsWantTypeCommercialVechile()
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




    /**
     *Android :222 validate nofikar report does not occur wehen inspected filter is Off
     */
//    @Test
//    public void verifyInspectedLabelWhenFilterIsOff()
//    {
//
//        //hompage.ApplaunchPopup();
//        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.HOMEPAGE_CATEGORY);
//        snbPage.viewMoreAds(0);
//        int count = snbPage.CountNumberofAds();
//        for (int i = 0; i < count; i++) {
//            snbPage.openAdFromSnb(i);
//            vapPage.waitForTitleToBeVisibleAtVap();
//            Assert.assertTrue(!vapPage.validateInspectedOnVAP());
//            carsPage.navigateBack();
//        }
//    }
    /**
     * Validate server side error for posting add without model
     * Android 589
     */
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
        papPage.selectElements("Brand", QuikrAppEnums.PAP_Radio_elements);
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
     * Android Sanity 2204
     * User can Post an Ad with 8 images and same is shown correctly on VAP (Add images via Camera & Gallery)
     */
   // @Test(groups = "horizontal")
    public void userIsAbleToPostAdWithEightImages()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage =new PapPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.clickonPostAd();
        papPage.clickOnUploadImage();
        papPage.selectgallery();
        for (int i=0;i<8;i++)
        {
            papPage.selectImageFromGalery(i);
            Assert.assertTrue(papPage.checkIfImageSelected(i).equalsIgnoreCase("true"));
        }
        papPage.uploadImageFromGallery();
        //   papPage.setAdTitle(testData.get("AdTitle"));
        //  papPage.setAdDescription(testData.get("desc"));
        papPage.navigateBack();
        papPage.scroll("Next");
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.navigateToNextPage();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Year");
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll(testData.get("KiloMeter"));
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd());

    }
    /**
     * user is not able to select more than 8 image
     */
    //@Test(groups = "horizontal")
    public void userIsNotAbleToPostAdWithMOreThanEightImage()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage =new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.clickOnUploadImage();
        papPage.selectgallery();
        for (int i=0;i<9;i++)
        {
            papPage.selectImageFromGalery(i);
            if (i==8)
                Assert.assertTrue(!(papPage.checkIfImageSelected(i).equalsIgnoreCase("true")));
        }

    }
}



