package com.quikr.msite.mHorizontal.mHorizontalPAP;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mCars.mCarsPAP.MCarsPAPPage;
import com.quikr.msite.mElectronics.mElectronicsSNB.MElectronicsSnBPage;
import com.quikr.msite.mHorizontal.mHeader.MHeaderPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mHorizontal.mLogin.MLoginpage;
import com.quikr.msite.mHorizontal.mMyAccount.MMyAccountPage;
import com.quikr.msite.mHorizontal.mPAP.MPAPPage;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 12/11/15.
 */
public class MHorizontalPAPTests extends MTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("mHORIZONTAL_TESTDATA_FILE"));

    /*MS-477:Verify Post Ad landing page
    */
    @Test(groups = "horizontal")
    public void validatePAPPage()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MPAPPage mpapPage = new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        mpapPage.clickCategoryPAP("Mobiles & Tablets");
        mpapPage.clickSubCategoryPAP("Mobile Phones");
        Assert.assertTrue(waitForPageToLoad("upload-image"),"Pap landing page didn't load. Please check!");

    }

    /*MS-480:PAP: Verify 'Select Category' page
    */
    @Test(groups = "horizontal")
    public void validateSelectCategory() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("selectcategoryURL"));
        Assert.assertTrue(mpapPage.validateSelectCategory(testData.get("CategoryCarsandBikes"), testData.get("CategoryMobilesandTablets"), testData.get("CategoryElectronics"), testData.get("CategoryRealEstate"), testData.get("CategoryHomeandLifeStyle"), testData.get("CategoryJobs"), testData.get("CategoryEntertainment")), "Failed to Load Category on PAP Page");
    }


    /*MS-481:PAP: Verify Sub-Categories lisitng on category page
    */
    @Test(groups = "horizontal")
    public void validateSelectSubCategory() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        //MCarsPAPPage.clickNextButton();
        waitForPageToLoad(testData.get("selectcategoryURL"));
        Assert.assertTrue(MCarsPAPPage.validateSubCategory(testData.get("CategoryCarsandBikes"), testData.get("CategoryMobilesandTablets"), testData.get("CategoryElectronics"), testData.get("CategoryRealEstate"), testData.get("CategoryHomeandLifeStyle"), testData.get("CategoryJobs"), testData.get("CategoryServices")), "Failed to Load Sub Category on PAP Page");
    }

    /* MS-482:PAP: Verify 'Select Attributes' page
     */
    @Test(groups = "horizontal")
    public void validateSelectAttribute() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        mpapPage.clickCategoryPAP(testData.get("CategoryMobilesandTablets"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryMobiles"));
        mpapPage.clickNextButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("SetAttributesURL")), "Fail to load URL : " + testData.get("SetAttributesURL") + " expected : " + MCarsPAPPage.getCurrentLocation());

    }


    /*MS-483:PAP: Verify field validation on 'Select Attributes' page
    */
    @Test(groups = "horizontal")
    public void validateValidationsAttribute() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        mpapPage.clickCategoryPAP(testData.get("CategoryMobilesandTablets"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryMobiles"));
        mpapPage.clickNextButton();
        waitForPageToLoad(testData.get("SetAttributesURL"));
        mpapPage.clickNextButtonPostAdPage();
        Assert.assertEquals(MCarsPAPPage.validationMsgSetAttributes().get(1), testData.get("ValidationMsgBrand"));
        Assert.assertEquals(MCarsPAPPage.validationMsgSetAttributes().get(2), testData.get("ValidationMsgCity"));

    }

    /*MS-484:PAP: Verify 'Ad Description' page
    */
    @Test(groups = "horizontal")
    public void validateAdDescription() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        Assert.assertTrue(MCarsPAPPage.isPAPPageavailable(), "Failed to Load PAP Page");
        waitForPageToLoad(testData.get("selectcategoryURL"));
        mpapPage.clickCategoryPAP(testData.get("CategoryCarsandBikes"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryCars"));
        mpapPage.clickNextButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("SetAttributesURL")), "Fail to load URL : " + testData.get("SetAttributesURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.clickNextButtonSetAttribute();
        MCarsPAPPage.MPostJobsAdSell(testData.get("Price"), testData.get("Brand"), testData.get("Model"), testData.get("Variant"), testData.get("Year"), testData.get("Kms"), testData.get("Color"), testData.get("Fuel"), testData.get("NoOfOwners"), testData.get("Insurancedate"));
        waitForPageToLoad(testData.get("PostAdURL"));
        Assert.assertTrue(MCarsPAPPage.isAdDescriptionavailable(), "Failed to Load Ad Description Page");
    }


    /*MS-485:PAP: Verify field validation on 'Ad Description' page
    */
    @Test(groups = "horizontal")
    public void validateValidationsAdDescription() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage = new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        Assert.assertTrue(MCarsPAPPage.isPAPPageavailable(), "Failed to Load PAP Page");
        waitForPageToLoad(testData.get("selectcategoryURL"));
        mpapPage.clickCategoryPAP(testData.get("CategoryCarsandBikes"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryCars"));
        MCarsPAPPage.clickNextButton();
        MCarsPAPPage.MPostJobsAdSell(testData.get("Price"), testData.get("Brand"), testData.get("Model"), testData.get("Variant"), testData.get("Year"), testData.get("Kms"), testData.get("Color"), testData.get("Fuel"), testData.get("NoOfOwners"), testData.get("Insurancedate"));
        waitForPageToLoad(testData.get("PostAdURL"));
        Assert.assertTrue(MCarsPAPPage.isAdDescriptionavailable(), "Failed to Load Ad Description Page");
        MCarsPAPPage.clickPostAdButtonDescription();
        Assert.assertEquals(MCarsPAPPage.validationMsgsAdDescription().get(0), testData.get("ValidationMsgAdDescription"), "Failed to Load Validations Message Fo Description");
        Assert.assertEquals(MCarsPAPPage.validationMsgsAdDescription().get(1), testData.get("ValidationMsgAdDescription1"), "Failed to Load Validations Message for Email");

    }


    /*MS-486:PAP: Verify Post Ad success
    */
    @Test(groups = "horizontal")
    public void validatePostAdSucess() {

        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("NewUICity"));
        mHomePage.closeLemsPopUp();
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        mpapPage.clickCategoryPAP(testData.get("CategoryMobilesandTablets"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryMobiles"));
        mpapPage.clickNextButton();
        waitForPageToLoad(testData.get("SetAttributesURL"));
        String adtitle="Selling Samsung Mobile Great Condition "+getRandomString(10);
        String mobnumber="98"+getRandomInteger(8);
        String description=testData.get("MobilesAdDescription");
        String emaild=getRandomString(8)+"@gmail.com";
        mpapPage.MPostMobileAdSell(testData.get("Price"), testData.get("mobileBrand"), testData.get("mobileModel"), testData.get("OperatingSystem"), testData.get("Address"), testData.get("pincode"), adtitle, mobnumber, description,emaild);
        waitForPageToLoad(testData.get("PostAdSuccessfulURL"));
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
    }

    /*MS-487:PAP:Verify 'PAP Success' page
     MS-661:Ensure post Ad is working for Buy & Sell options
    */
    @Test(groups = "horizontal")
    public void validatePAPSellSucess() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("NewUICity"));
        mHomePage.closeLemsPopUp();
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        mpapPage.clickCategoryPAP(testData.get("CategoryMobilesandTablets"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryMobiles"));
        mpapPage.clickNextButton();
        waitForPageToLoad(testData.get("SetAttributesURL"));
        String adtitle="Selling Samsung Mobile Great Condition "+getRandomString(10);
        String mobnumber="98"+getRandomInteger(8);
        String description=testData.get("MobilesAdDescription");
        String emaild=getRandomString(8)+"@gmail.com";
        mpapPage.MPostMobileAdSell(testData.get("Price"), testData.get("mobileBrand"), testData.get("mobileModel"), testData.get("OperatingSystem"), testData.get("Address"), testData.get("pincode"), adtitle, mobnumber, description, emaild);
        waitForPageToLoad(testData.get("PostAdSuccessfulURL"));
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
    }

    /* MS-661:Ensure post Ad is working for Buy & Sell options
    */
    @Test(groups = "horizontal")
    public void validatePAPBuySucess() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage = new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("NewUICity"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        mpapPage.clickCategoryPAP(testData.get("CategoryMobilesandTablets"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryMobiles"));
        mpapPage.clickNextButton();
        waitForPageToLoad(testData.get("SetAttributesURL"));
        String adtitle="Selling Samsung Mobile Great Condition "+getRandomString(10);
        String mobnumber="98"+getRandomInteger(8);
        String description=testData.get("MobilesAdDescription");
        String emaild=getRandomString(8)+"@gmail.com";
        mpapPage.MPostMobileAdBuy(testData.get("mobileBrand"), testData.get("mobileModel"), testData.get("OperatingSystem"), testData.get("Address"), testData.get("pincode"), adtitle, mobnumber, description, emaild);
        waitForPageToLoad(testData.get("PostAdSuccessfulURL"));
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
        Assert.assertTrue(MCarsPAPPage.isUpdateToPremiumAdAvailable(), "Failed to Load Update To Premium Ad Page");
    }

    /*MS-488:PAP:Verify all actions on 'PAP Success' page
    */
    @Test(groups = "horizontal")
    public void validateAllActionsPAPSuccessPage() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage = new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("NewUICity"));
        waitForPageToLoad(testData.get("NewUICity").toLowerCase());
        mHomePage.closeLemsPopUp();
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        mpapPage.clickCategoryPAP(testData.get("CategoryMobilesandTablets"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryMobiles"));
        mpapPage.clickNextButton();
        waitForPageToLoad(testData.get("SetAttributesURL"));
        String adtitle="Selling Samsung Mobile Great Condition "+getRandomString(10);
        String mobnumber="98"+getRandomInteger(8);
        String description=testData.get("MobilesAdDescription");
        String emaild=getRandomString(8)+"@gmail.com";
        mpapPage.MPostMobileAdBuy( testData.get("mobileBrand"), testData.get("mobileModel"), testData.get("OperatingSystem"), testData.get("Address"), testData.get("pincode"), adtitle, mobnumber, description, emaild);
        waitForPageToLoad(testData.get("PostAdSuccessfulURL"));
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
        MCarsPAPPage.validateViewAdLinkPostAd();
        Assert.assertTrue(waitForPageToLoad("AdId"), "Failed to Load Posted Ad, View Ad Link Not Working ");
    }


    /*MS-680:Change_1: Verify Post Ad changes in selected cities
    */
    @Test(groups = "horizontal", dataProvider = "postadCitiesMsite", dataProviderClass = Data.class)
    public void validateNewPostAdCities(String city) {
        MHomePage mHomePage = new MHomePage(driver);
        MPAPPage mpapPage = new MPAPPage(driver);
        MCarsPAPPage mCarsPAPPage = new MCarsPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(city);
        waitForPageToLoad(city.toLowerCase());
        mHomePage.closeLemsPopUp();
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        Assert.assertTrue(mpapPage.isNewPostAdScreenavailable(), "Failed to Load PAP Page with Upload Images and Description");
        Assert.assertTrue(mCarsPAPPage.isPAPPageavailable(), "Failed to Load PAP Page");
    }

    /*MS-505:Premium Ad: Verify the Premium Ad plan details
    */
    @Test(groups = "horizontal")
    public void validatePremiumAdsDetails() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        waitForPageToLoad(testData.get("selectcategoryURL"));
        mpapPage.clickCategoryPAP(testData.get("CategoryCarsandBikes"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryCars"));
        //mpapPage.clickNextButton();
        MCarsPAPPage.clickNextButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("SetAttributesURL")), "Fail to load URL : " + testData.get("SetAttributesURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.clickNextButtonSetAttribute();
        MCarsPAPPage.MPostJobsAdSell(testData.get("Price"), testData.get("Brand"), testData.get("Model"), testData.get("Variant"), testData.get("Year"), testData.get("Kms"), testData.get("Color"), testData.get("Fuel"), testData.get("NoOfOwners"), testData.get("Insurancedate"));
        waitForPageToLoad(testData.get("PostAdURL"));
        Assert.assertTrue(MCarsPAPPage.isAdDescriptionavailable(), "Failed to Load Ad Description Page");
        String mobilenumber="9"+getRandomInteger(9);
        String emailId = getRandomInteger(15) + "@yopmail.com";
        MCarsPAPPage.enterAdDescriptionCars(testData.get("AdDescription"), emailId, mobilenumber);
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
        Assert.assertTrue(MCarsPAPPage.isUpdateToPremiumAdAvailable(), "Failed to Load Update To Premium Ad Page");
        Assert.assertEquals(MCarsPAPPage.adPlanPrices().get(0), testData.get("255PremiumPlan"), "Failed to Load Premium ad Plan 225");
        Assert.assertEquals(MCarsPAPPage.adPlanPrices().get(1), testData.get("300PremiumPlan"), "Failed to Load Premium ad Plan 300");
        Assert.assertEquals(MCarsPAPPage.adPlanPrices().get(2), testData.get("450PremiumPlan"), "Failed to Load Premium ad Plan 450");
        Assert.assertEquals(MCarsPAPPage.adPlanDurations().get(0), testData.get("15daysPremiumPlan"), "Failed to Load 15 days i.e. duration of the plan");
        Assert.assertEquals(MCarsPAPPage.adPlanDurations().get(1), testData.get("1monthPremiumPlan"), "Failed to Load 1 month i.e. duration of the plan");
        Assert.assertEquals(MCarsPAPPage.adPlanDurations().get(2), testData.get("60daysPremiumPlan"), "Failed to Load 60 days i.e. duration of the plan");
        MCarsPAPPage.clickAdPlan();
        MCarsPAPPage.clickPaymentButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("PaymentGatewayURL")), "Fail to load URL : " + testData.get("PaymentGatewayURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
    }

    /*MS-506:Premium Ad: Verify the 'Mode of Payment' page
     */
    @Test(groups = "horizontal")
    public void validatePaymentModePage() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        mHomePage.closeLemsPopUp();
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        Assert.assertTrue(MCarsPAPPage.isPAPPageavailable(), "Failed to Load PAP Page");
        waitForPageToLoad(testData.get("selectcategoryURL"));
        mpapPage.clickCategoryPAP(testData.get("CategoryCarsandBikes"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryCars"));
        mpapPage.clickNextButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("SetAttributesURL")), "Fail to load URL : " + testData.get("SetAttributesURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.clickNextButtonSetAttribute();
        MCarsPAPPage.MPostJobsAdSell(testData.get("Price"), testData.get("Brand"), testData.get("Model"), testData.get("Variant"), testData.get("Year"), testData.get("Kms"), testData.get("Color"), testData.get("Fuel"), testData.get("NoOfOwners"), testData.get("Insurancedate"));
        waitForPageToLoad(testData.get("PostAdURL"));
        Assert.assertTrue(MCarsPAPPage.isAdDescriptionavailable(), "Failed to Load Ad Description Page");
        String mobilenumber="9"+getRandomInteger(9);
        String emailID=getRandomString(10)+"@gmail.com";
        MCarsPAPPage.enterAdDescriptionCars(testData.get("AdDescription"), emailID,mobilenumber);
        waitForPageToLoad(testData.get("PostAdSuccessfulURL"));
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
        Assert.assertTrue(MCarsPAPPage.isUpdateToPremiumAdAvailable(), "Failed to Load Update To Premium Ad Page");
        MCarsPAPPage.clickAdPlan();
        MCarsPAPPage.clickPaymentButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("PaymentGatewayURL")), "Fail to load URL : " + testData.get("PaymentGatewayURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        Assert.assertEquals(MCarsPAPPage.paymentModes().get(0), testData.get("PaymentMode1"), "Failed to Load Payment Mode");
        Assert.assertEquals(MCarsPAPPage.paymentModes().get(1), testData.get("PaymentMode2"), "Failed to Load Payment Mode");
        Assert.assertEquals(MCarsPAPPage.paymentModes().get(2), testData.get("PaymentMode3"), "Failed to Load Payment Mode");

    }

    /*MS-507:Premium Ad: Verify payment through Mobile Billing
    */
    @Test(groups = "horizontal")
    public void validateMobileBilling() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        waitForPageToLoad(testData.get("selectedCity").toLowerCase());
        mHomePage.closeLemsPopUp();
        waitForPageToLoad(testData.get("selectedCity").toLowerCase());
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        Assert.assertTrue(MCarsPAPPage.isPAPPageavailable(), "Failed to Load PAP Page");
        mpapPage.clickCategoryPAP(testData.get("CategoryCarsandBikes"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryCars"));
        MCarsPAPPage.clickNextButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("SetAttributesURL")), "Fail to load URL : " + testData.get("SetAttributesURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.clickNextButtonSetAttribute();
        MCarsPAPPage.MPostJobsAdSell(testData.get("Price"), testData.get("Brand"), testData.get("Model"), testData.get("Variant"), testData.get("Year"), testData.get("Kms"), testData.get("Color"), testData.get("Fuel"), testData.get("NoOfOwners"), testData.get("Insurancedate"));
        waitForPageToLoad(testData.get("PostAdURL"));
        Assert.assertTrue(MCarsPAPPage.isAdDescriptionavailable(), "Failed to Load Ad Description Page");
        String mobilenumber="9"+getRandomInteger(9);
        String emailID=getRandomString(10)+"@gmail.com";
        MCarsPAPPage.enterAdDescriptionCars(testData.get("AdDescription"), emailID,mobilenumber);
        waitForPageToLoad(testData.get("PostAdSuccessfulURL"));
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
        Assert.assertTrue(MCarsPAPPage.isUpdateToPremiumAdAvailable(), "Failed to Load Update To Premium Ad Page");
        MCarsPAPPage.clickAdPlan();
        MCarsPAPPage.clickPaymentButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("PaymentGatewayURL")), "Fail to load URL : " + testData.get("PaymentGatewayURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.clickMobileBilling();
        Assert.assertTrue(MCarsPAPPage.isPayMobileBillavailable(), "Failed to Load Mobile Billing Mobile Billing Text or Pay Now button");
        MCarsPAPPage.clickPayNowMobileBilling();
        Assert.assertTrue(MCarsPAPPage.isMobilePaymentavailable(), "Failed to Load Mobile Payment Page");
        MCarsPAPPage.setMobileNumberandOperator(testData.get("profilePhNum"));
        MCarsPAPPage.clickContinueButtonMobilePayment();
        Assert.assertTrue(MCarsPAPPage.isPaymentSuccessful(), "Failed to to Make Payment");
    }

    /*MS-508:Premium Ad: Verify payment through Credit & Debit card
    */
    @Test(groups = "horizontal")
    public void validateCreditandDebitBilling() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        waitForPageToLoad(testData.get("selectedCity").toLowerCase());
        mHomePage.closeLemsPopUp();
        waitForPageToLoad(testData.get("selectedCity").toLowerCase());
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        Assert.assertTrue(MCarsPAPPage.isPAPPageavailable(), "Failed to Load PAP Page");
        mpapPage.clickCategoryPAP(testData.get("CategoryCarsandBikes"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryCars"));
        MCarsPAPPage.clickNextButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("SetAttributesURL")), "Fail to load URL : " + testData.get("SetAttributesURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.MPostJobsAdSell(testData.get("Price"), testData.get("Brand"), testData.get("Model"), testData.get("Variant"), testData.get("Year"), testData.get("Kms"), testData.get("Color"), testData.get("Fuel"), testData.get("NoOfOwners"), testData.get("Insurancedate"));
        waitForPageToLoad(testData.get("PostAdURL"));
        Assert.assertTrue(MCarsPAPPage.isAdDescriptionavailable(), "Failed to Load Ad Description Page");
        String mobilenumber="9"+getRandomInteger(9);
        String emailID=getRandomString(10)+"@gmail.com";
        MCarsPAPPage.enterAdDescriptionCars(testData.get("AdDescription"), emailID,mobilenumber);
        waitForPageToLoad(testData.get("PostAdSuccessfulURL"));
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
        Assert.assertTrue(MCarsPAPPage.isUpdateToPremiumAdAvailable(), "Failed to Load Update To Premium Ad Page");
        MCarsPAPPage.clickAdPlan();
        MCarsPAPPage.clickPaymentButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("PaymentGatewayURL")), "Fail to load URL : " + testData.get("PaymentGatewayURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.clickPaymentModeCreditCard();
        Assert.assertTrue(MCarsPAPPage.isCreditandDebitOptionsavailable(), "Failed to Load Credit and Debit Card Payment Options");
        MCarsPAPPage.selectCardType(testData.get("CardTypeCredit"));
        MCarsPAPPage.setCardDetails(testData.get("CardNumberCreditAmericanExpress"), testData.get("NameofHolder"), testData.get("ExpiryDate"), testData.get("CVV"));
        MCarsPAPPage.clickPayNowCreditDebitCard();
    }

    /*MS-509:Premium Ad: Verify payment through Netbanking
    */
    @Test(groups = "horizontal")
    public void validateNetBankingBilling() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage MCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        waitForPageToLoad(testData.get("selectedCity").toLowerCase());
        mHomePage.closeLemsPopUp();
        waitForPageToLoad(testData.get("selectedCity").toLowerCase());
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        Assert.assertTrue(MCarsPAPPage.isPAPPageavailable(), "Failed to Load PAP Page");
        mpapPage.clickCategoryPAP(testData.get("CategoryCarsandBikes"));
        mpapPage.clickSubCategoryPAP(testData.get("SubCategoryCars"));
        MCarsPAPPage.clickNextButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("SetAttributesURL")), "Fail to load URL : " + testData.get("SetAttributesURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.clickNextButtonSetAttribute();
        MCarsPAPPage.MPostJobsAdSell(testData.get("Price"), testData.get("Brand"), testData.get("Model"), testData.get("Variant"), testData.get("Year"), testData.get("Kms"), testData.get("Color"), testData.get("Fuel"), testData.get("NoOfOwners"), testData.get("Insurancedate"));
        waitForPageToLoad(testData.get("PostAdURL"));
        Assert.assertTrue(MCarsPAPPage.isAdDescriptionavailable(), "Failed to Load Ad Description Page");
        String mobilenumber="9"+getRandomInteger(9);
        String emailID=getRandomString(10)+"@gmail.com";
        MCarsPAPPage.enterAdDescriptionCars(testData.get("AdDescription"), emailID,mobilenumber);
        waitForPageToLoad(testData.get("PostAdSuccessfulURL"));
        Assert.assertTrue(MCarsPAPPage.isPostAdSuccessful(), "Failed to Post Ad");
        Assert.assertTrue(MCarsPAPPage.isUpdateToPremiumAdAvailable(), "Failed to Load Update To Premium Ad Page");
        MCarsPAPPage.clickAdPlan();
        MCarsPAPPage.clickPaymentButton();
        Assert.assertTrue(waitForPageToLoad(testData.get("PaymentGatewayURL")), "Fail to load URL : " + testData.get("PaymentGatewayURL") + " expected : " + MCarsPAPPage.getCurrentLocation());
        MCarsPAPPage.clickPaymentModeNetBanking();
        MCarsPAPPage.selectBank(testData.get("BankName"));
        MCarsPAPPage.clickPayNowNetBanking();
        waitForPageToLoad(testData.get("ICICIBankPaymentPage"));
        Assert.assertTrue(MCarsPAPPage.isNetBakingPaymentPageavailable(), "Failed to Load NetBanking Payment Page");
    }


    /*MS-511:Verify premium Ad functionality for Old Ad
    */
    @Test(groups = "horizontal")
    public void validatePremiumAd() {
        MHomePage mHomePage = new MHomePage(driver);
        MMyAccountPage mMyAccountPage = new MMyAccountPage(driver);
        MLoginpage mLoginpage = new MLoginpage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity(testData.get("selectedCity"));
        mHomePage.clickMenu();
        mMyAccountPage.clickMyAccount();
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        mMyAccountPage.clickConvertToPremiumMyAds();
        Assert.assertTrue(waitForPageToLoad("PostAd"), "Premium ads link is not working.");
    }

    /*MS-591:PAP: Verify Post Ad Flow in Vernacular Languages
  */
    @Test(groups = "horizontal")
    public void validatePostAdVernac() {
        MHomePage mHomePage = new MHomePage(driver);
        MPAPPage mpapPage = new MPAPPage(driver);
        MHeaderPage mHeaderPage = new MHeaderPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickLanguage(2);
        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertTrue(mpapPage.isPostAdPageloaded());
        mHeaderPage.goToQuikrHome();
        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickLanguage(3);
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertTrue(mpapPage.isPostAdPageloaded());
        mHeaderPage.goToQuikrHome();
        mHomePage.clickLanguage(4);
        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertTrue(mpapPage.isPostAdPageloaded());
        mHeaderPage.goToQuikrHome();
        mHomePage.clickLanguage(5);
        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertTrue(mpapPage.isPostAdPageloaded());

    }

    /*MS-661:Ensure post Ad is working for Buy & Sell options
    */
    @Test(groups = "horizontal")
    public void PostAdBuyAndSell() {
        MHomePage mHomePage = new MHomePage(driver);
        MPAPPage mpapPage = new MPAPPage(driver);
        MCarsPAPPage mCarsPAPPage = new MCarsPAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("homepage"));
        Assert.assertTrue(mpapPage.isPostAdPageloaded());
        mpapPage.clickCategoryPAP("Cars");
        mpapPage.clickSubCategoryPAP("Cars");
        mpapPage.clickNextButton();


    }

    /*MS-684:Change_2: Verify Post Ad changes in selected cities
    */
    @Test(groups = "horizontal")
    public void validatePostAdCities() {
        MHomePage mHomePage = new MHomePage(driver);
        MCarsPAPPage mCarsPAPPage = new MCarsPAPPage(driver);
        MPAPPage mpapPage=new MPAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage=new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();
        //mHomePage.handleLemsQuikrLitePopUp();

        mHomePage.selectCity(testData.get("NewUICity"));
        waitForPageToLoad(testData.get("NewUICity").toLowerCase());
        mHomePage.clickPostAdButtonHomePage();
        waitForPageToLoad(testData.get("papPage"));
        Assert.assertTrue(mpapPage.validateSelectCategory(testData.get("CategoryCarsandBikes"), testData.get("CategoryMobilesandTablets"), testData.get("CategoryElectronics"), testData.get("CategoryRealEstate"), testData.get("CategoryHomeandLifeStyle"), testData.get("CategoryJobs"), testData.get("CategoryEntertainment")), "Failed to Load Category on PAP Page");


    }

}