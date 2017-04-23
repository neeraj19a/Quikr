package com.quikr.website.escrow.escrowEndToEnd;

import com.fasterxml.jackson.databind.JsonNode;
import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.utils.Database;
import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.escrow.EscrowTestBase;
import com.quikr.website.escrow.escrowPap.EscrowPapPage;
import com.quikr.website.escrow.escrowPayNow.EscrowPayNowPage;
import com.quikr.website.escrow.escrowSnb.EscrowSnbPage;
import com.quikr.website.escrow.escrowVap.EscrowVapPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by gurinder.singh@quikr.com on 24/12/15.
 */
public class EscrowMobileEndToEndTests extends EscrowTestBase {
    // test data
    public static final HashMap<String, String> testData = getTestData(getProperties().get("ESCROW_E2E_TESTDATA_FILE"));
    public String AdId = null;
    public String OfferId = null;
    private final static Log LOGGER = LogFactory.getLog(EscrowMobileEndToEndTests.class.getName());
    public String sellerEmail=null;
    public String encodeSellerEmail=null;
    public String sellerMobilenum=null;
    private static final String ADIDWOMIN="276713052"; //it has Min Price as 7000/-
    private static final String ADIDWMIN ="279998830";//""276712344";
    private static final String ADIDSAMEMIN="276712590";
    private static final String BIDADID="279999231";
    private static final String BUYNOW="Buy Now";
    private static final String MAO="Make an Offer";
    public String pass = null;

    /*
     * PostAd for Mobiles Category, MAO from , And Buy
     */
    @Stories("TestFlow:Make an offer from Vap")
    @Features("Mobile&Tablet")
    @Title("Make an offer from Vap")
    @Description("To test Make an offer from Vap")
    @Test(groups={"EscrowMobileAndTablet"})
    public void MAOFromVap() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);


        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);
        pass=mD5(sellerEmail);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWOMinPrice(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"), testData.get("buyerCity"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        vapPage.acceptOfferSeller(OfferId, AdId,pass,testData.get("sellerName"),encodeSellerEmail);
        payNowPage.openPayNowURL(OfferId,pass);
        payNowWithoutWarranty(testData.get("buyerName"), testData.get("buyerAddress"), testData.get("buyerPincode"), testData.get("buyerMobile"), testData.get("cardType"), testData.get("cardNo"), testData.get("expiryDate"), testData.get("cvv"));
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }



    @Stories("TestFlow:Make an offer from Snb")
    @Features("Mobile&Tablet")
    @Title("Make an offer from Snb")
    @Description("To test Make an offer from Snb")
    @Test(groups={"EscrowMobileAndTablet"})
    public void MAOFromSnb() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        encodeSellerEmail=Base64Encoding(sellerEmail);
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        pass=mD5(sellerEmail);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWOMinPrice(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectMakeAnOffer(AdId);
        Assert.assertTrue(snbPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"), testData.get("buyerCity"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        vapPage.acceptOfferSeller(OfferId, AdId,pass,testData.get("sellerName"),encodeSellerEmail);
        payNowPage.openPayNowURL(OfferId,pass);
        payNowWithoutWarranty(testData.get("buyerName"), testData.get("buyerAddress"), testData.get("buyerPincode"), testData.get("buyerMobile"), testData.get("cardType"), testData.get("cardNo"), testData.get("expiryDate"), testData.get("cvv"));
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }
    @Stories("TestFlow:Counter offer Functionality")
    @Features("Mobile&Tablet")
    @Title("Counter offer Functionality")
    @Description("Counter offer Functionality")
    @Test(groups={"EscrowMobileAndTablet"})
    public void counterOffer() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWOMinPrice(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"), testData.get("buyerCity"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        counterOfferSeller(OfferId, testData.get("counterPrice"), testData.get("sellerName"), testData.get("sellerPincode"), testData.get("sellerAddress"),sellerMobilenum, AdId,pass,encodeSellerEmail);
        payNowPage.openPayNowURL(OfferId,pass);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPrice"),testData.get("buyerMobileProd")), "Payable amount doesnot match counter price");
        payNowWithoutWarranty(testData.get("buyerName"), testData.get("buyerAddress"), testData.get("buyerPincode"), testData.get("buyerMobile"), testData.get("cardType"), testData.get("cardNo"), testData.get("expiryDate"), testData.get("cvv"));
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }
    @Stories("TestFlow:Buy Now with Minimum Price")
    @Features("Mobile&Tablet")
    @Title("Buy Now with Minimum Price")
    @Description("To test Buy Now with Minimum Price")
    @Test(groups={"EscrowMobileAndTablet"})
    public void BuyNowWithoutReservedPrice() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        encodeSellerEmail=Base64Encoding(sellerEmail);
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWOMinPrice(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpen(), "BuyNow Pop Up did not open");
        buyNow(testData.get("buyerMail"), testData.get("buyerMobile"), testData.get("buyerCity"));
        payNowWithoutWarranty(testData.get("buyerName"), testData.get("buyerAddress"), testData.get("buyerPincode"), testData.get("buyerMobile"), testData.get("cardType"), testData.get("cardNo"), testData.get("expiryDate"), testData.get("cvv"));
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }
    @Stories("TestFlow:Make an offer with Minimum Price")
    @Features("Mobile&Tablet")
    @Title("Make an offer with Minimum Price")
    @Description("To test Make an offer with Minimum Price")
    @Test(groups={"EscrowMobileAndTablet"})
    public void MAOWithMinPriceLimit() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWithMinPrice(testData, testData.get("minprice"),sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"), testData.get("buyerCity"));
        Assert.assertTrue(vapPage.isOfferAutoAccepted(), "Offer was not Auto Accepted");
        //vapPage.selectBuyNow();
        vapPage.selectBuyNowAutoAcceptButton();
        payNowWithoutWarranty(testData.get("buyerName"), testData.get("buyerAddress"), testData.get("buyerPincode"), testData.get("buyerMobile"), testData.get("cardType"), testData.get("cardNo"), testData.get("expiryDate"), testData.get("cvv"));
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());

    }
    @Stories("TestFlow:Buy Now with Same Listing and Minimum Price")
    @Features("Mobile&Tablet")
    @Title("Buy Now with Same Listing and Minimum Price ")
    @Description("To test Buy Now with Same Listing and Minimum Price")
    @Test(groups={"EscrowMobileAndTablet"})
    public void SameListingAndReservedPrice() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        Database db = new Database();
        sellerEmail = randomSellerEmail();

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWithMinPrice(testData, testData.get("minpriceandpricesame"),sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        Assert.assertTrue(snbPage.isBuyNowPresent(), "BuyNow button not present for on SnB page");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        java.util.List<WebElement> list = vapPage.buttononVap();
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).getAttribute("class").contains("Buy"));
    }
    @Stories("TestFlow:Post Ad with all field selected")
    @Features("Mobile&Tablet")
    @Title("Post Ad with all field selected")
    @Description("To Test Post Ad with all field selected")
    @Test(groups={"EscrowMobileAndTablet"})
    public void postAdAllFieldCheck() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdAllFields(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        System.out.println(db.getAdDetails(AdId));

    }
    @Stories("TestFlow:Post Ad with Mandatory fields selected")
    @Features("Mobile&Tablet")
    @Title("Post Ad with Mandatory fields selected")
    @Description("Post Ad with Mandatory fields selected")
    @Test(groups={"EscrowMobileAndTablet"})
    public void postAdMandatoryFieldCheck() {
        HomePage homePage = new HomePage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        papPage.submitAd();
        Assert.assertTrue(papPage.isTitleErrorThrown(), "Title doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isConditionErrorThrown(), "Condition doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isPriceErrorThrown(), "Price doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isBrandNameErrorThrown(), "Brand Name doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isDescriptionErrorThrown(), "Description doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isSellerTypeErrorThrown(), "Seller Type doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isAddressErrorThrown(), "Address doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isLocalityErrorThrown(), "Locality doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isPincodeErrorThrown(), "Pincode doesnot seem to be Mandatory");
    }


    /*
     * Delivery Feedback by Seller and Buyer with YES option
     */
    @Stories("TestFlow:Delivery Feedback by Seller and Buyer")
    @Features("Mobile&Tablet")
    @Title("Delivery Feedback by Seller and Buyer")
    @Description("To test Delivery Feedback given by Seller and Buyer with Yes option")
    @Test(groups={"EscrowMobileAndTablet"})
    public void deliverFeedbackSellerAndBuyerWIthYes() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);
        String[]dbOutpt=new String[5];

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWOMinPrice(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"), testData.get("buyerCity"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        payNowPage.deliveryFeedBackSellerURL(OfferId);
        Assert.assertTrue(payNowPage.isDoorFeedbackHeader());
        Assert.assertEquals(AdId,payNowPage.deliveryFeedBackAdId());
        payNowPage.itemPickedUpYes();
        payNowPage.itemPickedOnTimeYes();
        Assert.assertTrue(payNowPage.feedBackShared());
        dbOutpt=db.deliveryFeedbackDBCheck(AdId);
        System.out.println(dbOutpt[0] + dbOutpt[1] + dbOutpt[2]);
        Assert.assertEquals(dbOutpt[0],"Y");
        Assert.assertEquals(dbOutpt[1],"Y");
        Assert.assertEquals(dbOutpt[2],"N");
        payNowPage.deliveryFeedBackBuyerURL(OfferId);
        Assert.assertTrue(payNowPage.isDoorFeedbackHeader());
        Assert.assertEquals(AdId,payNowPage.deliveryFeedBackAdId());
        payNowPage.itemPickedUpYes();
        payNowPage.itemPickedOnTimeYes();
        Assert.assertTrue(payNowPage.feedBackShared());
        dbOutpt=db.deliveryFeedbackDBCheck(AdId);
        System.out.println(dbOutpt[0] + dbOutpt[1] + dbOutpt[2]);
        Assert.assertEquals(dbOutpt[0],"Y");
        Assert.assertEquals(dbOutpt[1],"Y");
        Assert.assertEquals(dbOutpt[2],"Y");
    }

    /*
     * Delivery Feedback by Seller and Buyer with YES option
     */
    @Stories("TestFlow:Delivery Feedback by Seller and Buyer")
    @Features("Mobile&Tablet")
    @Title("Delivery Feedback by Seller and Buyer")
    @Description("To test Delivery Feedback given by Seller and Buyer with Yes option")
    @Test(groups={"EscrowMobileAndTablet"})
    public void deliverFeedbackSellerAndBuyerWIthDeliverNotOnTime() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);
        String[]SellerOutpt=new String[5];
        String[]BuyerOutpt=new String[5];
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWOMinPrice(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"), testData.get("buyerCity"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        payNowPage.deliveryFeedBackSellerURL(OfferId);
        Assert.assertTrue(payNowPage.isDoorFeedbackHeader());
        Assert.assertEquals(AdId,payNowPage.deliveryFeedBackAdId());
        payNowPage.itemPickedUpYes();
        payNowPage.itemPickedOnTimeNo();
        payNowPage.feedBackComment();
        payNowPage.feedBackSubmit();
        Assert.assertEquals(payNowPage.feedBackSubmittedMessage(),"Sorry to hear that!");

        SellerOutpt=db.deliveryFeedbackDBCheck(AdId);
        System.out.println("Seller output==" + SellerOutpt[0] + SellerOutpt[1] + SellerOutpt[2]+SellerOutpt[3]);
        Assert.assertEquals(SellerOutpt[0],"Y");
        Assert.assertEquals(SellerOutpt[1],"N");
        Assert.assertEquals(SellerOutpt[2],"N");
        Assert.assertEquals(SellerOutpt[3],"Hello");

        payNowPage.deliveryFeedBackBuyerURL(OfferId);
        Assert.assertTrue(payNowPage.isDoorFeedbackHeader());
        Assert.assertEquals(AdId,payNowPage.deliveryFeedBackAdId());
        payNowPage.itemPickedUpYes();
        payNowPage.itemPickedOnTimeNo();
        payNowPage.feedBackComment();
        payNowPage.feedBackSubmit();
        Assert.assertEquals(payNowPage.feedBackSubmittedMessage(),"Sorry to hear that!");
        BuyerOutpt=db.deliveryFeedbackDBCheck(AdId);
        System.out.println("Buyer output=="+ BuyerOutpt[0] + BuyerOutpt[1] + BuyerOutpt[2]+BuyerOutpt[3]);
        Assert.assertEquals(BuyerOutpt[0],"Y");
        Assert.assertEquals(BuyerOutpt[1],"N");
        Assert.assertEquals(BuyerOutpt[2],"Y");
        Assert.assertEquals(BuyerOutpt[3],"Hello");
    }

    /*
     * Delivery Feedback by Seller and Buyer with YES option
     */
    @Stories("TestFlow:Delivery Feedback by Seller and Buyer")
    @Features("Mobile&Tablet")
    @Title("Delivery Feedback by Seller and Buyer")
    @Description("To test Delivery Feedback given by Seller and Buyer with Yes option")
    @Test(groups={"EscrowMobileAndTablet"})
    public void deliverFeedbackSellerAndBuyerWithItemNotPicked() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);
        String[]SellerOutpt=new String[5];
        String[]BuyerOutpt=new String[5];
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryMobile"), testData.get("subCatMobile"));
        postAdWOMinPrice(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsMobiles();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"), testData.get("buyerCity"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        payNowPage.deliveryFeedBackSellerURL(OfferId);
        Assert.assertTrue(payNowPage.isDoorFeedbackHeader());
        Assert.assertEquals(AdId,payNowPage.deliveryFeedBackAdId());
        payNowPage.itemPickedUpNo();
        payNowPage.feedBackComment();
        payNowPage.feedBackSubmit();
        Assert.assertEquals(payNowPage.feedBackSubmittedMessage(),"Sorry to hear that!");

        SellerOutpt=db.deliveryFeedbackDBCheck(AdId);
        System.out.println("Seller output==" + SellerOutpt[0] + SellerOutpt[1] + SellerOutpt[2]+SellerOutpt[3]);
        Assert.assertEquals(SellerOutpt[0],"N");
        Assert.assertEquals(SellerOutpt[1],"N");
        Assert.assertEquals(SellerOutpt[2],"N");
        Assert.assertEquals(SellerOutpt[3],"Hello");

        payNowPage.deliveryFeedBackBuyerURL(OfferId);
        Assert.assertTrue(payNowPage.isDoorFeedbackHeader());
        Assert.assertEquals(AdId,payNowPage.deliveryFeedBackAdId());
        payNowPage.itemPickedUpNo();
        payNowPage.feedBackComment();
        payNowPage.feedBackSubmit();
        Assert.assertEquals(payNowPage.feedBackSubmittedMessage(),"Sorry to hear that!");
        BuyerOutpt=db.deliveryFeedbackDBCheck(AdId);
        System.out.println("Buyer output=="+ BuyerOutpt[0] + BuyerOutpt[1] + BuyerOutpt[2]+BuyerOutpt[3]);
        Assert.assertEquals(BuyerOutpt[0],"N");
        Assert.assertEquals(BuyerOutpt[1],"N");
        Assert.assertEquals(BuyerOutpt[2],"Y");
        Assert.assertEquals(BuyerOutpt[3],"Hello");
    }

    //////EscrowProduction cases



    /*
 * PostAd for Mobiles Category, MAO from , And Buy
 */
    @Stories("TestFlow:Make an offer from Vap")
    @Features("Mobile&Tablet")
    @Title("Make an offer from Vap")
    @Description("To test Make an offer from Vap")
    @Test(groups={"EscrowProduction"})
    public void MAOFromVapProdMobile() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);


        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDWOMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDWOMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAOMobile"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"), testData.get("buyerCityProd"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADIDWOMIN);
        vapPage.acceptOfferSellerProd(OfferId,ADIDWOMIN,testData.get("sellerNameProd"));
        payNowPage.openPayNowURLProd(OfferId);
        payNowWithoutWarrantyElectronicsProd(testData);

    }
    @Stories("TestFlow:Make an offer from Snb")
    @Features("Mobile&Tablet")
    @Title("Make an offer from Snb")
    @Description("To test Make an offer from Snb")
    @Test(groups={"EscrowProduction"})
    public void MAOFromSnbProdMobile() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDWOMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDWOMIN), "Search Unsuccessfull");
        snbPage.selectMakeAnOffer(ADIDWOMIN);
        Assert.assertTrue(snbPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferSnB(testData.get("priceMAOMobile"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"), testData.get("buyerCityProd"));
        Assert.assertTrue(vapPage.isOfferSentSnB(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADIDWOMIN);
        vapPage.acceptOfferSellerProd(OfferId,ADIDWOMIN,testData.get("sellerNameProd"));
        payNowPage.openPayNowURLProd(OfferId);
        payNowWithoutWarrantyElectronicsProd(testData);
    }
    @Stories("TestFlow:Counter offer Functionality")
    @Features("Mobile&Tablet")
    @Title("Counter offer Functionality")
    @Description("Counter offer Functionality")
    @Test(groups={"EscrowProduction"})
    public void counterOfferEscrowProductionMobile() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);


        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDWOMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDWOMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAOMobile"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"), testData.get("buyerCityProd"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADIDWOMIN);
        counterOfferSellerProd(OfferId, testData.get("counterPriceMobile"), testData.get("sellerNameProd"), testData.get("sellerPincodeProd"), testData.get("sellerAddressProd"), testData.get("sellerMobileProd"), ADIDWOMIN);
        payNowPage.openPayNowURLProd(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPriceMobile"),testData.get("buyerMobileProd")), "Payable amount doesnot match counter price");
        payNowInProd(testData);

    }
    @Stories("TestFlow:Make an offer with Minimum Price")
    @Features("Mobile&Tablet")
    @Title("Make an offer with Minimum Price")
    @Description("To test Make an offer with Minimum Price")
    @Test(groups={"EscrowProduction"})
    public void MAOWithMinPriceLimitProdMobile() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);


        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDWMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDWMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAOMobile1"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"), testData.get("buyerCityProd"));
        Assert.assertTrue(vapPage.isOfferSentAccept(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADIDWMIN);
        //vapPage.acceptOfferSellerProd(OfferId, ADIDWMIN,testData.get("sellerNameProd"));
        payNowPage.openPayNowURLProd(OfferId);
        payNowWithoutWarrantyElectronicsProd(testData);



    }

    /*
            * Posted Ad with Price and Min Price same and Buy
            */
    @Stories("TestFlow:Buy Now with Same Price and Minimum Price")
    @Features("Mobile&Tablet")
    @Title("Buy Now with Same Price and Minimum Price")
    @Description("To Test Buy Now with Same Price and Minimum Price")
    @Test(groups = {"EscrowProduction"})
    public void samePriceAndMinPriceProdMobile() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDSAMEMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDSAMEMIN), "Search Unsuccessfull");
        Assert.assertTrue(snbPage.buyNowOnly());
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        List<WebElement> list = vapPage.buttononVap();
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).getAttribute("class").contains("buyBox"));
        vapPage.selectBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpen(), "BuyNow Pop Up did not open");
        buyNow(testData.get("buyerMailProd"), testData.get("buyerMobileProd"),testData.get("buyerCityProd"));
        payNowInProd(testData);

    }

    /*
       * Buy Now on SnB
       */
    @Stories("TestFlow:Buy Now from SnB")
    @Features("Mobile&Tablet")
    @Title("Buy Now from SnB")
    @Description("To Test Buy Now from SbB")
    @Test(groups = {"EscrowProduction"})
    public void buyNowFromSnBMobile() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDSAMEMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDSAMEMIN), "Search Unsuccessfull");
        Assert.assertTrue(snbPage.buyNowOnly());
        snbPage.clickBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpenSnB(), "BuyNow Pop Up did not open");
        buyNowSnB(testData.get("buyerMailProd"), testData.get("buyerMobileProd"),testData.get("buyerCityProd"));
        payNowInProd(testData);
        Assert.assertTrue(payNowPage.checkTransactionStatusInProd(), "Payment Unsuccessfull");
    }

    /*
       * User Profile Buy Now
       */
    @Stories("TestFlow:User Profile Buy Now")
    @Features("Mobile&Tablet")
    @Title("User Profile Buy Now")
    @Description("User Profile Buy Now")
    @Test(groups = {"EscrowProduction"})
    public void userProfileBuyNow() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDSAMEMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDSAMEMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.userProfileLink();
        Assert.assertTrue(vapPage.isUserProfileImg());
        vapPage.userProfileButton(BUYNOW);
        Assert.assertTrue(vapPage.isBuyNowOpenSnB(), "BuyNow Pop Up did not open");
        buyNowSnB(testData.get("buyerMailProd"), testData.get("buyerMobileProd"),testData.get("buyerCityProd"));
        payNowInProd(testData);

    }

    /*
       * User Profile Buy Now
       */
    @Stories("TestFlow:User Profile Make An Offer")
    @Features("Mobile&Tablet")
    @Title("User Profile Make an Offer")
    @Description("User Profile Buy Now")
    @Test(groups = {"EscrowProduction"})
    public void userProfilemakeAnOffer() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDSAMEMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDSAMEMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.userProfileLink();
        Assert.assertTrue(vapPage.isUserProfileImg());
        vapPage.userProfileButton(MAO);
        Assert.assertTrue(vapPage.isMAOOpenSnB(), "MAO Pop Up did not open");
        String price=String.valueOf(vapPage.findOfferPrice());
        makeAnOfferSnB(price, testData.get("buyerMailProd"), testData.get("buyerMobileProd"), testData.get("buyerCityProd"));
        vapPage.closeDialouge();
       // payNowWithoutWarrantyElectronicsProd(testData);
    }



    /*
 * PostAd for Mobiles Category, MAO from , And Buy
 */
    @Stories("TestFlow:Make an offer from Vap")
    @Features("Mobile&Tablet")
    @Title("Make an offer from Vap")
    @Description("To test Make an offer from Vap")
    @Test(groups={"EscrowProduction"})
    public void buyerSellerSameMaOProd() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);


        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDWOMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDWOMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAOMobile"), testData.get("sellerMailProd"), testData.get("sellerMobileProd"), testData.get("buyerCityProd"));
        Assert.assertTrue(vapPage.buyerSellerSame(), "Buyer And Seller are same");
       }

    @Stories("TestFlow:Counter offer Functionality")
    @Features("Mobile&Tablet")
    @Title("Counter offer Functionality")
    @Description("Counter offer Functionality")
    @Test(groups={"EscrowProduction"})
    public void counterOfferLessThanOfferProd() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        EscrowPapPage papPage = new EscrowPapPage(driver);
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);


        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        headerPage.search(ADIDWOMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDWOMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOffer(testData.get("priceMAOMobile"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"), testData.get("buyerCityProd"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADIDWOMIN);
        counterOfferSellerProd(OfferId, testData.get("counterPriceMobileLessThanOffer"), testData.get("sellerNameProd"), testData.get("sellerPincodeProd"), testData.get("sellerAddressProd"), testData.get("sellerMobileProd"), ADIDWOMIN);
        Assert.assertTrue(vapPage.counterOfferLessThanOffer(), "Offer was not less than offer");
    }

    @Stories("TestFlow:IVR")
    @Features("Mobile&Tablet")
    @Title("IVR Functionality")
    @Description("Check if IVR is working or not")
    @Test(groups={"EscrowProduction"})
    public void iVRCheckProd() {
        HeaderPage headerPage = new HeaderPage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        Assert.assertEquals(vapPage.iVRStatus(),"Success");
    }
    @Stories("My Offer Seller Tab")
    @Features("My Offer Seller Tab")
    @Title("My Offer Seller Tab Functionality")
    @Description("Check My Offer Seller Tab Functionality")
    @Test(groups={"EscrowProduction"})
    public void myOfferSellerTab() {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        vapPage.sellerBuyerLogin("qkrseller@gmail.com","Autom@tion");
        vapPage.sellerMyOfferTab();
    }

    @Stories("My Offer Buyer Tab")
    @Features("My Offer Buyer Tab")
    @Title("My Offer Buyer Tab")
    @Description("Check My Offer Buyer Tab Functionality")
    @Test(groups={"EscrowProduction"})
    public void myOfferBuyerTab() {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        vapPage.sellerBuyerLogin("qkrbuyer@gmail.com","Autom@tion");
        vapPage.buyerMyOfferTab();
        Assert.assertTrue(vapPage.buyerAdDisplay(),"No Ad displayed under Buyer tab");
        //Assert.assertTrue(vapPage.counterOfferText(),"Counter Offer Text dint display");
        Assert.assertTrue(vapPage.offerAcceptedText(),"Accept Offer Text dint display");
        vapPage.payNowBuyerOffer();
        Assert.assertTrue(vapPage.payNowLandingPage(),"Payment landing page dint display");
        Assert.assertTrue(vapPage.landingBackToBuyTab(),"Buyer Tab landing page dint display");
        vapPage.chatWindow(testData.get("buyerMobileProd"));
    }

    @Features("BidNowSnB")
    @Title("BidNow Test on SnB")
    @Description("Check Bid Now Functionality on SnB Page")
    @Test(groups={"EscrowProduction"})
    public void bidNowSnB() {
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        headerPage.selectCity("Pune");
        waitForPageToLoad("pune.quikr.com");

        System.out.println(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES);
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES);
        headerPage.search(BIDADID);
        Assert.assertTrue(snbPage.bidButtonOnSnB(),"No Ad displayed under Buyer tab");
        //Assert.assertTrue(snbPage.lastBidpricelabelSnB(),"No Ad displayed under Buyer tab");
        Assert.assertTrue(snbPage.auctionLabelSnB(),"No Ad displayed under Buyer tab");
        String nextBidVal=snbPage.nextBidVal();
        int intbidval=Integer.parseInt(nextBidVal);
        int lessbidval=intbidval-1;
        String finalbidVal=Integer.toString(lessbidval);
        String actualBidVal=Integer.toString(intbidval);
        snbPage.bidNowClick();
        Assert.assertTrue(snbPage.bidPopUpSnB(),"Bid Pop did not get open");
        Assert.assertTrue(snbPage.lessThanPreviousBidAmount(finalbidVal),"Less than Price did not work");
        snbPage.submitBid(actualBidVal,testData.get("sellerMailProd"), testData.get("sellerMobileProd"));
        Assert.assertTrue(snbPage.lastBidpricelabelSnB(),"last bid price label was not found correct");
    }


    @Features("BidNowVap")
    @Title("BidNow Test on Vap")
    @Description("Check Bid Now Functionality on Vap Page")
    @Test(groups={"EscrowProduction"})
    public void bidNowVap() {
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        headerPage.selectCity("Pune");
        waitForPageToLoad("pune.quikr.com");


        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES);
        headerPage.search(BIDADID);
        Assert.assertTrue(snbPage.isSearchSuccess(BIDADID), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        Assert.assertTrue(vapPage.placeBidLabel(),"No Ad displayed under Buyer tab");
        Assert.assertTrue(vapPage.lastBidLabel(),"No Ad displayed under Buyer tab");
        Assert.assertTrue(vapPage.auctionLabel(),"No Ad displayed under Buyer tab");
        String nextBidVal=vapPage.nextBidVal();
        int StrToIntBidVal=Integer.parseInt(nextBidVal);
        int lessBid=StrToIntBidVal-1;
        String intToStrLessBidVal= String.valueOf(lessBid);
        Assert.assertTrue(vapPage.checkInvalidBid(intToStrLessBidVal),"Error does not display");
        vapPage.bidNowClickVap(nextBidVal);
        Assert.assertTrue(snbPage.bidPopUpSnB(),"No Ad displayed under Buyer tab");
        vapPage.submitBid(nextBidVal,testData.get("sellerMailProd"), testData.get("sellerMobileProd"));
        Assert.assertTrue(vapPage.bidSuccessfull(),"No Ad displayed under Buyer tab");
    }

    @Features("Auction")
    @Title("Auction Flow")
    @Description("Check Auction Functionality")
    @Test(groups={"Stage"},enabled=false)
    public void auction(){
        EscrowSnbPage snbPage = new EscrowSnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        String AdId;
        JsonNode auction;
        String[]auctionID= new String[10];
        String [] bidInfo=new String[10];
        String auctionid;
        JsonNode getADById;
        EscrowApiBase apiBase = new EscrowApiBase();
        JsonNode bid[]=new JsonNode[8];
        Database db = new Database();
        List<String> bidList=new ArrayList<>();
        auction=apiBase.postAdEscrow("web13@mail.quikrtest.com","8090890982","23","15000","20865980");
        AdId = auction.get("Ad").get("id").toString();
        apiBase.publishAdWareHouse(AdId);
        System.out.println(AdId);
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("bangalore.quikr.com");
        apiBase.publishAdWareHouse(AdId);
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.MOBILES_AND_TABLETS);
        apiBase.publishAdWareHouse(AdId);
        headerPage.search(AdId);
        try {
            Assert.assertTrue(snbPage.bidButtonOnSnB(), "No Ad displayed under Buyer tab");
        }catch(Exception e){
            apiBase.publishAdWareHouse(AdId);
            headerPage.search(AdId);
            Assert.assertTrue(snbPage.bidButtonOnSnB(), "No Ad displayed under Buyer tab");
        }
        //Assert.assertTrue(snbPage.lastBidpricelabelSnB(),"No Ad displayed under Buyer tab");
        Assert.assertTrue(snbPage.auctionLabelSnB(),"No Ad displayed under Buyer tab");
        snbPage.bidNowClick();
        Assert.assertTrue(snbPage.bidPopUpSnB(),"Bid Pop did not get open");
        Assert.assertTrue(snbPage.lessThanPreviousBidAmount("7999"),"Less than Price did not work");
        snbPage.submitBid("8000","web7@mail.quikrtest.com","9165031630");
        refreshPage();
        waitForPageToLoad("bangalore.quikr.com");
       // Assert.assertTrue(snbPage.lastBidpricelabelSnB(),"last bid price label was not found correct");
        snbPage.bidNowClick();
        Assert.assertTrue(snbPage.bidPopUpSnB(),"Bid Pop did not get open");
        snbPage.submitBid("8400","web9@mail.quikrtest.com","9535712568");
        refreshPage();
        waitForPageToLoad("bangalore.quikr.com");
        snbPage.bidNowClick();
        Assert.assertTrue(snbPage.bidPopUpSnB(),"Bid Pop did not get open");
        snbPage.submitBid("8900","web10@mail.quikrtest.com","8654098765");
        refreshPage();
        waitForPageToLoad("bangalore.quikr.com");
        snbPage.bidNowClick();
        Assert.assertTrue(snbPage.bidPopUpSnB(),"Bid Pop did not get open");
        snbPage.submitBid("9500","web11@mail.quikrtest.com","7543278908");
        refreshPage();
        waitForPageToLoad("bangalore.quikr.com");
        snbPage.bidNowClick();
        Assert.assertTrue(snbPage.bidPopUpSnB(),"Bid Pop did not get open");
        snbPage.submitBid("10100","web12@mail.quikrtest.com","7878650942");
        refreshPage();
        waitForPageToLoad("bangalore.quikr.com");
        snbPage.bidNowClick();
        Assert.assertTrue(snbPage.bidPopUpSnB(),"Bid Pop did not get open");
        snbPage.submitBid("10900","web18@mail.quikrtest.com","8112329870");
        auctionID=db.auctionId(AdId);
        apiBase.publishAdWareHouse(AdId);
        auctionid=auctionID[0];
        auctionID=null;
        System.out.println("Auction ID === " + auctionid);
        apiBase.publishAdWareHouse(AdId);
        bidInfo=db.bidId(auctionid);


        getADById=apiBase.getCompleteAdById(AdId);
        String attribute=getADById.path("GetCompleteAdByIdResponse").path("data").path("ad").path("attributeString").toString();
        Assert.assertTrue(attribute.contains("auction_enabled:1"));
        //auctionID=db.auctionId(AdId);
        //Checking Max bid ID
        Assert.assertEquals(bidInfo[5],auctionID[5]);
        //Checking Live Auction's status
        Assert.assertEquals(auctionID[1],1);
        //Cheking total bids
        Assert.assertEquals(auctionID[5],6);

        String startDate=auctionID[2];
        String endDate=auctionID[3];
        String paymentEndDate=auctionID[4];
        //Checking date Difference between start and end date
        Assert.assertEquals(apiBase.dateDifference(startDate,endDate),7);
        //Checking date Difference between End and Payment End date
        Assert.assertEquals(apiBase.dateDifference(endDate,paymentEndDate),10);

    }



    @AfterMethod(alwaysRun = true)
    public void AfterMethod() {
        if (AdId != null)
            LOGGER.info("AdId==  " + AdId + "OfferId== " + OfferId);
    }
}

