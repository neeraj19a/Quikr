package com.quikr.website.horizontal.pap;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 31/5/16.
 */
public class MobileAndTabletsPapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("PAP_TESTDATA_FILE"));

    @Test
    public void PostAdBuyMobilePhones(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        MobileAndTabletsPap mpap = new MobileAndTabletsPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MOBILES_AND_TABLETS, QuikrEnums.MobileTabletSubCat.MOBILE_PHONES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        papPage.selectCondition();
        mpap.selectBrandName();
        mpap.selectModel();
        papPage.selectCondition();
        mpap.selectOperatingSys();
        papPage.selectCondition();
        mpap.selectSimCount();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        Assert.assertTrue(mpap.ifAdPostedSuccessfully(),"The ad was not posted successfully. Please check!");
    }

    @Test
    public void PostAdSellMobilePhones(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        MobileAndTabletsPap mpap = new MobileAndTabletsPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MOBILES_AND_TABLETS, QuikrEnums.MobileTabletSubCat.MOBILE_PHONES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        papPage.selectCondition();
        papPage.inputPrice("20000");
        mpap.selectYearOfPurchase();
        mpap.selectBrandName();
        mpap.selectModel();
        mpap.selectOperatingSys();
        mpap.selectSimCount();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560102");
        papPage.postTheAd();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.enterAddress();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        Assert.assertTrue(mpap.ifAdPostedSuccessfully(),"The ad was not posted successfully. Please check!");
    }

    @Test
    public void PostAdSellAccessories(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        MobileAndTabletsPap mpap = new MobileAndTabletsPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MOBILES_AND_TABLETS, QuikrEnums.MobileTabletSubCat.ACCESSORIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        mpap.selectAccessory();
        papPage.selectCondition();
        papPage.inputPrice("1000");
        mpap.selectBrandName();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.enterAddress();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        Assert.assertTrue(mpap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void PostAdBuyAccessories(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        MobileAndTabletsPap mpap = new MobileAndTabletsPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MOBILES_AND_TABLETS, QuikrEnums.MobileTabletSubCat.ACCESSORIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        mpap.selectAccessory();
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        papPage.selectCondition();
        mpap.selectBrandName();
        papPage.selectCondition();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        Assert.assertTrue(mpap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void PostAdSellTablets(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        MobileAndTabletsPap mpap = new MobileAndTabletsPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MOBILES_AND_TABLETS, QuikrEnums.MobileTabletSubCat.TABLETS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        papPage.selectCondition();
        papPage.inputPrice("2000");
        mpap.selectBrandName();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.enterAddress();
        papPage.postTheAd();
        Assert.assertTrue(mpap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void PostAdBuyTablets(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        MobileAndTabletsPap mpap = new MobileAndTabletsPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MOBILES_AND_TABLETS, QuikrEnums.MobileTabletSubCat.TABLETS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        papPage.selectCondition();
        mpap.selectBrandName();
        papPage.selectCondition();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.postTheAd();
        Assert.assertTrue(mpap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }
}
