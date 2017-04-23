package com.quikr.msite.mEscrow.mEscrowEndToEnd;

import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.api.escrow.EscrowEnumData;
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
 * Created by quikr on 19/2/16.
 */
public class MEscrowHomeApplianceEndToEndTests extends MEscrowTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("mESCROW_E2E_TESTDATA_FILE"));
    public int flagStatus[] = new int[10];
    public String awbNumber = null;
    public String OfferId = null;
    private final static Log LOGGER = LogFactory.getLog(MEscrowElectronicEndToEndTests.class.getName());
    public String AdId = null;
    public String sellerEmail=null;
    public String sellerMobilenum=null;
    public String encodeSellerEmail=null;

    @Stories("TestFlow: Make An Offer from VAP")
    @Features("Home&LifeStyle")
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
        postAdHomeWOMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        makeAnOfferElectronic(testData);
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        vapPage.acceptOfferSeller(OfferId, testData.get("sellerName"),encodeSellerEmail);
        Assert.assertTrue(vapPage.ifOfferAccepted(), "Offer was not Accepted");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("priceMAO")), "Incorrect Payable Amount");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Make an Offer with Min Price")
    @Features("Home&LifeStyle")
    @Title("Make an Offer with Min Price")
    @Description("To test Make an Offer with Min Price")
    @Test
    public void MAOWithMinPrice() {
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
        postAdHomeWithMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        makeAnOfferElectronic(testData);
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        vapPage.acceptOfferSeller(OfferId, testData.get("sellerName"),encodeSellerEmail);
        Assert.assertTrue(vapPage.ifOfferAccepted(), "Offer was not Accepted");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("priceMAO")), "Incorrect Payable Amount");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Make an Offer with WareHouse Check on VAP")
    @Features("Home&LifeStyle")
    @Title("Make an Offer with WareHouse")
    @Description("To test Make an Offer with WareHouse Check on VAP")
    @Test
    public void MAOFromVapWarehouse() {
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
        postAdWarehouseHome(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        Assert.assertTrue(db.checkWareHouseStatus(AdId));
        flagStatus = apiBase.postAdWareHouseBeforeDeliver(AdId, 0, 0);
        Assert.assertEquals(db.wareHouseDetail(AdId), "50");
        papPage.navigateHome();
        //TODO call content/publishAd with adId, attribute,flaReason, Status with 0, 0
        awbNumber = db.awbWareHouse(AdId);
        apiBase.updateOrderStatusPostShip(AdId, "", "", EscrowEnumData.overall_status.DELIVERED, "", EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED, EscrowEnumData.item_direction.To_Buyer, awbNumber);
        //TODO call content/publishAd with adId, attribute,flaReason, Status
        apiBase.postAdWareHouseBeforeDeliver(AdId, flagStatus[0], flagStatus[1]);
        apiBase.publishAd(AdId);
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        makeAnOfferElectronic(testData);
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        vapPage.acceptOfferSeller(OfferId, testData.get("sellerName"),encodeSellerEmail);
        Assert.assertTrue(vapPage.ifOfferAccepted(), "Offer was not Accepted");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("priceMAO")), "Incorrect Payable Amount");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Counter offer")
    @Features("Home&LifeStyle")
    @Title("Counter Offer")
    @Description("To test Counter offer")
    @Test
    public void Counteroffer() {
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
        postAdHomeWOMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        makeAnOfferElectronic(testData);
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        counterOfferSeller(OfferId, testData,encodeSellerEmail,sellerMobilenum);
        Assert.assertTrue(vapPage.isCounterOfferSent(), "Counter offer was NOT sent");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPrice")), "Payable Amount doesnot match counter price");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Counter offer with Min Price")
    @Features("Home&LifeStyle")
    @Title("Counter offer with Min Price")
    @Description("To test Counter offer with Min Price")
    @Test
    public void CounterofferWithMinPrice() {
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
        postAdHomeWithMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        makeAnOfferElectronic(testData);
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        counterOfferSeller(OfferId, testData,encodeSellerEmail,sellerMobilenum);
        Assert.assertTrue(vapPage.isCounterOfferSent(), "Counter offer was NOT sent");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPrice")), "Payable Amount doesnot match counter price");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Counter offer with WareHouse Check")
    @Features("Home&LifeStyle")
    @Title("Counter offer with WareHouse Check")
    @Description("To test Counter offer with WareHouse Check")
    @Test
    public void CounterofferWarehouse() {
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
        postAdWarehouseHome(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        Assert.assertTrue(db.checkWareHouseStatus(AdId));
        flagStatus = apiBase.postAdWareHouseBeforeDeliver(AdId, 0, 0);
        Assert.assertEquals(db.wareHouseDetail(AdId), "50");
        papPage.navigateHome();
        //TODO call content/publishAd with adId, attribute,flaReason, Status with 0, 0
        awbNumber = db.awbWareHouse(AdId);
        apiBase.updateOrderStatusPostShip(AdId, "", "", EscrowEnumData.overall_status.DELIVERED, "", EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED, EscrowEnumData.item_direction.To_Buyer, awbNumber);
        //TODO call content/publishAd with adId, attribute,flaReason, Status
        apiBase.postAdWareHouseBeforeDeliver(AdId, flagStatus[0], flagStatus[1]);
        apiBase.publishAd(AdId);
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        makeAnOfferElectronic(testData);
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        counterOfferSeller(OfferId, testData,encodeSellerEmail,sellerMobilenum);
        Assert.assertTrue(vapPage.isCounterOfferSent(), "Counter offer was NOT sent");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPrice")), "Payable Amount doesnot match counter price");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Buy Now Without Min Price")
    @Features("Home&LifeStyle")
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
        postAdHomeWOMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        buyNowElectronic(testData);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("price")), "Incorrect Payable Amount");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Buy Now With WareHouse Check")
    @Features("Home&LifeStyle")
    @Title("Buy Now With WareHouse")
    @Description("To test Buy Now With WareHouse")
    @Test
    public void BuyNowWarehouse() {
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
        postAdWarehouseHome(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        Assert.assertTrue(db.checkWareHouseStatus(AdId));
        flagStatus = apiBase.postAdWareHouseBeforeDeliver(AdId, 0, 0);
        Assert.assertEquals(db.wareHouseDetail(AdId), "50");
        papPage.navigateHome();
        //TODO call content/publishAd with adId, attribute,flaReason, Status with 0, 0
        awbNumber = db.awbWareHouse(AdId);
        apiBase.updateOrderStatusPostShip(AdId, "", "", EscrowEnumData.overall_status.DELIVERED, "", EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED, EscrowEnumData.item_direction.To_Buyer, awbNumber);
        //TODO call content/publishAd with adId, attribute,flaReason, Status
        apiBase.postAdWareHouseBeforeDeliver(AdId, flagStatus[0], flagStatus[1]);
        apiBase.publishAd(AdId);
        snbPage.searchAd(AdId);
        snbPage.selectAd();
        Assert.assertTrue(snbPage.ifAdSelected(), "Ad was not selected");
        buyNowElectronic(testData);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("price")), "Incorrect Payable Amount");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Make An Offer From SnB")
    @Features("Home&LifeStyle")
    @Title("Make An Offer From SnB")
    @Description("To test Make An Offer From SnB")
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
        postAdHomeWOMinPrice(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        snbPage.searchAd(AdId);
        makeAnOfferFromSnBElectronic(testData);
        Assert.assertTrue(snbPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        vapPage.acceptOfferSeller(OfferId, testData.get("sellerName"),encodeSellerEmail);
        Assert.assertTrue(vapPage.ifOfferAccepted(), "Offer was not Accepted");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("priceMAO")), "Incorrect Payable Amount");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Make An Offer From SnB with WareHouse Check")
    @Features("Home&LifeStyle")
    @Title("Make An Offer From SnB with WareHouse")
    @Description("To test Make An Offer From SnB with WareHouse Check")
    @Test
    public void MAOFromSnbWarehouse() {
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
        postAdWarehouseHome(testData,sellerEmail,sellerMobilenum);
        Assert.assertTrue(papPage.ifAdPostSuccess(), "Ad was not posted");
        AdId = papPage.getAdId();
        Assert.assertTrue(db.checkWareHouseStatus(AdId));
        flagStatus = apiBase.postAdWareHouseBeforeDeliver(AdId, 0, 0);
        Assert.assertEquals(db.wareHouseDetail(AdId), "50");
        papPage.navigateHome();
        //TODO call content/publishAd with adId, attribute,flaReason, Status with 0, 0
        awbNumber = db.awbWareHouse(AdId);
        apiBase.updateOrderStatusPostShip(AdId, "", "", EscrowEnumData.overall_status.DELIVERED, "", EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED, EscrowEnumData.item_direction.To_Buyer, awbNumber);
        //TODO call content/publishAd with adId, attribute,flaReason, Status
        apiBase.postAdWareHouseBeforeDeliver(AdId, flagStatus[0], flagStatus[1]);
        apiBase.publishAd(AdId);
        snbPage.searchAd(AdId);
        makeAnOfferFromSnBElectronic(testData);
        Assert.assertTrue(snbPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        System.out.println(OfferId);
        vapPage.acceptOfferSeller(OfferId, testData.get("sellerName"),encodeSellerEmail);
        Assert.assertTrue(vapPage.ifOfferAccepted(), "Offer was not Accepted");
        payNowPage.openPayNowURL(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("priceMAO")), "Incorrect Payable Amount");
        payNowSkipWarranty(testData);
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Failure!");
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
    }

    @Stories("TestFlow:Post Ad with Same Price and Reserved Price")
    @Features("Home&LifeStyle")
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
        postAdHomeWithSameMinPrice(testData,sellerEmail,sellerMobilenum);
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

    @AfterMethod(alwaysRun = true)
    public void AfterMethod() {
        if (AdId != null)
            LOGGER.info("AdId==  " + AdId + "OfferId== " + OfferId);
    }
}
