package com.quikr.msite.mEscrow.mEscrowEndToEnd;

import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.msite.mEscrow.MEscrowTestBase;
import com.quikr.msite.mEscrow.mEscrowPap.MEscrowPapPage;
import com.quikr.msite.mEscrow.mEscrowPayNow.MEscrowPayNowPage;
import com.quikr.msite.mEscrow.mEscrowSnb.MEscrowSnbPage;
import com.quikr.msite.mEscrow.mEscrowVap.MEscrowVapPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.utils.Database;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 5/2/16.
 */
public class MEscrowMobileEndToEndTests extends MEscrowTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("mESCROW_E2E_TESTDATA_FILE"));
    public String OfferId = null;
    private final static Log LOGGER = LogFactory.getLog(MEscrowElectronicEndToEndTests.class.getName());
    public String AdId = null;
    public String sellerEmail=null;
    public String sellerMobilenum=null;
    public String encodeSellerEmail=null;

    @Stories("TestFlow: Make An Offer from VAP")
    @Features("Mobile&Tablet")
    @Title("Title: Make An Offer from VAP")
    @Description("To test Make An Offer from VAP")
    @Test
    public void MAOFromVap() {
        MHomePage homePage = new MHomePage(driver);
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        MEscrowSnbPage snbPage = new MEscrowSnbPage(driver);
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);
        MEscrowPayNowPage payNowPage = new MEscrowPayNowPage(driver);

        EscrowApiBase apiBase = new EscrowApiBase();
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);

        homePage.selectCity("Bangalore");
        homePage.closeLanguagePopUp();
        homePage.clickPostAdButtonHomePage();
        postAdMobileWOMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        makeAnOffer(testData);
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        vapPage.acceptOfferSeller(OfferId, testData.get("sellerName"),encodeSellerEmail);
        Assert.assertTrue(vapPage.ifOfferAccepted(), "Offer was not Accepted");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("priceMAO")), "Incorrect Payable Amount");
        payNowWithSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Make an Offer from SnB")
    @Features("Mobile&Tablet")
    @Title("Make an Offer from SnB")
    @Description("To test Make an Offer from SnB")
    @Test
    public void MAOFromSnb() {
        MHomePage homePage = new MHomePage(driver);
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        MEscrowSnbPage snbPage = new MEscrowSnbPage(driver);
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);
        MEscrowPayNowPage payNowPage = new MEscrowPayNowPage(driver);

        EscrowApiBase apiBase = new EscrowApiBase();
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);

        homePage.selectCity("Bangalore");
        homePage.closeLanguagePopUp();
        homePage.clickPostAdButtonHomePage();
        postAdMobileWithMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        makeAnOfferFromSnB(testData);
        Assert.assertTrue(snbPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        vapPage.acceptOfferSeller(OfferId, testData.get("sellerName"),encodeSellerEmail);
        Assert.assertTrue(vapPage.ifOfferAccepted(), "Offer was not Accepted");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("priceMAO")), "Incorrect Payable Amount");
        payNowWithSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Counter offer")
    @Features("Mobile&Tablet")
    @Title("Counter Offer")
    @Description("To test Counter offer")
    @Test
    public void counterOffer() {
        MHomePage homePage = new MHomePage(driver);
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        MEscrowSnbPage snbPage = new MEscrowSnbPage(driver);
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);
        MEscrowPayNowPage payNowPage = new MEscrowPayNowPage(driver);

        EscrowApiBase apiBase = new EscrowApiBase();
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);

        homePage.selectCity("Bangalore");
        homePage.closeLanguagePopUp();
        homePage.clickPostAdButtonHomePage();
        postAdMobileWOMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        makeAnOffer(testData);
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        counterOfferSeller(OfferId, testData,encodeSellerEmail,sellerMobilenum);
        Assert.assertTrue(vapPage.isCounterOfferSent(), "Counter offer was NOT sent");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPrice")), "Payable Amount doesnot match counter price");
        payNowWithSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Buy Now Without Min Price")
    @Features("Mobile&Tablet")
    @Title("Buy Now Without Min Price")
    @Description("To test Buy Now Without Min Price")
    @Test
    public void BuyNowWithoutReservedPrice() {
        MHomePage homePage = new MHomePage(driver);
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        MEscrowSnbPage snbPage = new MEscrowSnbPage(driver);
        MEscrowPayNowPage payNowPage = new MEscrowPayNowPage(driver);

        EscrowApiBase apiBase = new EscrowApiBase();
        Database db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);

        homePage.selectCity("Bangalore");
        homePage.closeLanguagePopUp();
        homePage.clickPostAdButtonHomePage();
        postAdMobileWOMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        buyNow(testData);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("price")), "Incorrect Payable Amount");
        payNowWithSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Post Ad with Same Price and Reserved Price")
    @Features("Mobile&Tablet")
    @Title("Post Ad with Same Price and Reserved Price")
    @Description("To test if Only Buy Now Button available while Posting Ad with Same Price and Reserved Price")
    @Test
    public void SameListingAndReservedPrice() {
        MHomePage homePage = new MHomePage(driver);
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        MEscrowSnbPage snbPage = new MEscrowSnbPage(driver);
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);
        EscrowApiBase apiBase = new EscrowApiBase();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);

        homePage.selectCity("Bangalore");
        homePage.closeLanguagePopUp();
        homePage.clickPostAdButtonHomePage();
        postAdMobileWithSameMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        Assert.assertTrue(snbPage.ifBuyNowPresent(), "Buy now button not present on SNB");
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        Assert.assertTrue(vapPage.isBuyNowPresent(), "BuyNow not present on VAP");
    }

    @Stories("TestFlow:Post Ad with All Field selected")
    @Features("Mobile&Tablet")
    @Title("Post Ad with All Field selected")
    @Description("To test Post Ad with All Field selected")
    @Test
    public void postAdAllFieldCheck() {
        MHomePage homePage = new MHomePage(driver);
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));

        homePage.selectCity("Bangalore");
        homePage.closeLanguagePopUp();
        homePage.clickPostAdButtonHomePage();
        postAdMobileWithMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
    }

    @Stories("TestFlow:Post Ad with Mandatory Fields check")
    @Features("Mobile&Tablet")
    @Title("Post Ad with Mandatory Fields check")
    @Description("To test Post Ad with Mandatory Fields check")
    @Test
    public void postAdMandatoryFieldCheck() {
        MHomePage homePage = new MHomePage(driver);
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        homePage.selectCity("Bangalore");
        homePage.closeLanguagePopUp();
        homePage.clickPostAdButtonHomePage();

        papPage.goNextFromPictures();
        Assert.assertTrue(papPage.descriptionMandatoryError(), "Description is Not mandatory");
        papPage.enterDescription(testData.get("description"));
        papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryMobile"));
        papPage.selectSubCategory(testData.get("subCatMobile"));

        papPage.goNextFromAttributes();
        Assert.assertTrue(papPage.priceMandatoryError(), "Price is Not mandatory");
        Assert.assertTrue(papPage.brandNameMandatoryError(), "Brand Name is Not mandatory");
        Assert.assertTrue(papPage.addressMandatoryError(), "Address is Not mandatory");
        Assert.assertTrue(papPage.localityMandatoryError(), "Locality is Not mandatory");
        Assert.assertTrue(papPage.pincodeMandatoryError(), "Pincode is Not mandatory");
        papPage.enterPrice(testData.get("price"));
        papPage.selectBrand(testData.get("mobileBrand"));
        papPage.selectModel(testData.get("mobileModel"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.postAd();
        Assert.assertTrue(papPage.titleMandatoryError(), "Title is Not mandatory");
        Assert.assertTrue(papPage.emailMandatoryError(), "Email is Not mandatory");
        Assert.assertTrue(papPage.mobileMandatoryError(), "Mobile is Not mandatory");
    }

    @AfterMethod(alwaysRun = true)
    public void AfterMethod() {
        if (AdId != null)
            LOGGER.info("AdId==  " + AdId + "OfferId== " + OfferId);
    }
}

