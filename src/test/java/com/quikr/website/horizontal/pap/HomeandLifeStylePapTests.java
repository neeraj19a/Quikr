package com.quikr.website.horizontal.pap;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 6/6/16.
 */
public class HomeandLifeStylePapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("PAP_TESTDATA_FILE"));

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithAntiquesHandicrafts(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.ANTIQUES_HANDICRAFTS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription="We sell ANTIQUES and HANDICRAFTS at reasonable price. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        papPage.inputPrice("20000");
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithPaintings(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.PAINTINGS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription="We sell Paintings at reasonable price. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        papPage.inputPrice("20000");
        papPage.selectCondition();
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithKitchenware(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.KITCHENWARE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription="We sell Kitchenware of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.inputPrice("20000");
        papPage.selectCondition();
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithHousehold(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.HOUSEHOLD);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription="We sell Household items of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.inputPrice("20000");
        papPage.selectCondition();
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithHomeDecorFurnishings(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.HOME_DECOR_FURNISHINGS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription="We sell HOME DECOR FURNISHINGS of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomFurnishingType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithHomeOfficeFurniture(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap = new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.HOME_OFFICE_FURNITURE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "We sell HOME OFFICE FURNITURE of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomFurnishingType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithBabyInfantProducts(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap = new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.BABY_INFANT_PRODUCTS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "We sell Baby - Infant Products of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithKidsLearning(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap = new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.KIDS_LEARNING);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "We sell Kids Learning Products of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithToysGames(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap = new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.TOYS_GAMES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "We sell Toys and Games of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithWholesaleBulk(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.WHOLESALE_BULK);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "We are wholesalers of Household Items and groceries of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithEverythingElse(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.EVERYTHING_ELSE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "We are wholesalers of Household Items and groceries of all major brands. Showrooms in Indira Nagar, Whitefield";
        papPage.inputAdTitle(inputDescription);
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithBagsLuggage(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.BAGS_LUGGAGE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "1 year old Red Samsonite Trolley Bag for sale in MG Rd";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }


    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithClothingGarments(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.CLOTHING_GARMENTS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "All Branded Clothing Garments available at reasonable price. Showrooms available in Indira Nagar, Kormangala ";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomStyle();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithFashionAccessories(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.FASHION_ACCESSORIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "All Fashion Accessories available at reasonable price. Showrooms available in Indira Nagar, Kormangala ";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomAccessoryType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithFootwear(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.FOOTWEAR);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "Biggest Outlet of Footwear All Brands under one Store. Showrooms available in Indira Nagar, Kormangala ";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithGiftsStationary(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.GIFTS_STATIONARY);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "All GIFTS and STATIONARY available of all major brands. Showrooms available in Indira Nagar, Kormangala ";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithHealthBeautyProducts(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.HEALTH_BEAUTY_PRODUCTS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "All Healthy and Beauty Products available of all major brands. Showrooms available in Indira Nagar, Kormangala ";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithJewellery(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.JEWELLERY);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "New Jewellery stores Opened in Indira Nagar, Kormangala , We have all major brands available";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomJewelleryType();
        papPage.inputPrice("20000");
        papPage.selectCondition();
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithSportFitnessEquipment(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.SPORT_FITNESS_EQUIPMENT);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "All Major brands of Sport - Fitness Equipment available in our Stores, Stores in WhiteField,IndiraNagar";
        papPage.inputAdTitle(inputDescription);
        homeAndLifeStylePap.selectRandomEquipment_Type();
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PostAdHomeandLifeStylewithWatches(int adType) {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.WATCHES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String inputDescription = "All Major brands of Watches available in our Stores, Stores in WhiteField,IndiraNagar";
        papPage.inputAdTitle(inputDescription);
        papPage.selectCondition();
        homeAndLifeStylePap.selectRandomBrandname();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.enterAddress();
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.selectCondition();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void HomeAndLifeStyleBooksMagzinesPap(int adType){
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.BOOKS_MAGAZINES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String titleDesc="Series of Harry porter books on sale. come grab it!!!!!!";
        papPage.inputAdTitle(titleDesc);
        homeAndLifeStylePap.selectGenre();
        papPage.selectCondition();
        papPage.inputPrice("1000");
        papPage.inputDescription(titleDesc);
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.inputDescription(titleDesc);
        Assert.assertTrue(papPage.adPostedSucessfully(),"The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void HomeAndLifeStyleCoinsStampsPap(int adType){
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.COINS_STAMPS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String titleDesc="Age old coins and stamps. COme and grab them!!!!!!";
        papPage.inputAdTitle(titleDesc);
        homeAndLifeStylePap.selectRandomProductType();
        papPage.selectCondition();
        papPage.inputPrice("1000");
        papPage.inputDescription(titleDesc);
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.inputDescription(titleDesc);
        Assert.assertTrue(papPage.adPostedSucessfully(),"The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void HomeAndLifeStyleCollectiblesPap(int adType){
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.COLLECTIBLES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String titleDesc="Age old collectibles on for sale. COme abd grab them!!!!";
        papPage.inputAdTitle(titleDesc);
        papPage.selectCondition();
        papPage.inputPrice("1000");
        papPage.inputDescription(titleDesc);
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.inputDescription(titleDesc);
        Assert.assertTrue(papPage.adPostedSucessfully(),"The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void HomeAndLifeStyleMusicMoviesPap(int adType){
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.MUSIC_MOVIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String titleDesc="Age old movie and music cds on for sale. COme abd grab them!!!!";
        papPage.inputAdTitle(titleDesc);
        homeAndLifeStylePap.selectMediaType();
        papPage.selectCondition();
        papPage.inputPrice("1000");
        papPage.inputDescription(titleDesc);
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.inputDescription(titleDesc);
        Assert.assertTrue(papPage.adPostedSucessfully(),"The ad was not posted successfully. Please check!");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void HomeAndLifeStyleMusicMusicalInstrumentsPap(int adType){
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        HomeAndLifeStylePap homeAndLifeStylePap=new HomeAndLifeStylePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE, QuikrEnums.HomeLifestyleSubCat.MUSICAL_INSTRUMENTS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(adType);
        String titleDesc="Age old music instruments on for sale. COme abd grab them!!!!";
        papPage.inputAdTitle(titleDesc);
        homeAndLifeStylePap.selectInstrumentType();
        papPage.selectCondition();
        papPage.inputPrice("1000");
        papPage.inputDescription(titleDesc);
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.selectSellerType();
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.inputDescription(titleDesc);
        Assert.assertTrue(papPage.adPostedSucessfully(),"The ad was not posted successfully. Please check!");
    }



}