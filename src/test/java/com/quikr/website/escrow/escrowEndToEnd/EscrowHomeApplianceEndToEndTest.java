package com.quikr.website.escrow.escrowEndToEnd;

import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.api.escrow.EscrowEnumData;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Rajat Kachhara on 05-02-2016.
 */
public class EscrowHomeApplianceEndToEndTest extends EscrowTestBase {
    public static final HashMap<String, String> testData = getTestData(getProperties().get("ESCROW_E2E_TESTDATA_FILE"));
    public String AdId = null;
    public String awbNumber = null;
    protected String OfferId = null;
    public HomePage homePage = null;
    public HeaderPage headerPage = null;
    public EscrowPapPage papPage = null;
    public EscrowSnbPage snbPage = null;
    public EscrowVapPage vapPage = null;
    public EscrowApiBase apiBase = null;
    public EscrowPayNowPage payNowPage = null;
    public Database db = null;
    public int flagStatus[] = new int[10];
    String sellerEmail=null;
    String sellerMobilenum=null;
    String encodeSellerEmail=null;
    String pass=null;
    private final static Log LOGGER = LogFactory.getLog(EscrowHomeApplianceEndToEndTest.class.getName());
    private static final String ADID="275442564";
    private static final String ADIDWMIN ="275442930";
    private static final String ADIDSAMEMINPRICE ="275443145";

    @BeforeMethod(alwaysRun = true)
    public void init() {
        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        papPage = new EscrowPapPage(driver);
        snbPage = new EscrowSnbPage(driver);
        vapPage = new EscrowVapPage(driver);
        apiBase = new EscrowApiBase();
        payNowPage = new EscrowPayNowPage(driver);
        db = new Database();
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail=Base64Encoding(sellerEmail);
        pass=mD5(sellerEmail);
    }

    /*
     * Make an Offer from VAP Page
     */
    @Stories("TestFlow:Make an offer from Vap")
    @Features("Home&LifeStyle")
    @Title("Make an offer from Vap")
    @Description("To Test Make an offer from Vap")
    @Test(groups = {"EscrowHomeLifeStyle"})
    public void MAOFromVap() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWOMinPriceForHomeAppliance(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsHomeLifeStyle();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        vapPage.acceptOfferSeller(OfferId, AdId,pass,testData.get("sellerName"),encodeSellerEmail);
        payNowPage.openPayNowURL(OfferId,pass);
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
     * Make an Offer-->Counter Offer from VAP Page
     */
    @Stories("TestFlow:Counter offer Functionality")
    @Features("Home&LifeStyle")
    @Title("Counter offer Functionality")
    @Description("To Test Counter offer Functionality")
    @Test(groups = {"EscrowHomeLifeStyle"})
    public void MaoCounteroffer() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWOMinPriceForHomeAppliance(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsHomeLifeStyle();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        counterOfferSeller(OfferId, testData.get("counterPrice"), testData.get("sellerName"), testData.get("sellerPincode"), testData.get("sellerAddress"),sellerMobilenum,AdId,pass,encodeSellerEmail);
        payNowPage.openPayNowURL(OfferId,pass);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPrice"),testData.get("buyerMobileProd")), "Payable amount doesnot match counter price");
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
         * Buy Now for Posted Ad [without Min Price]
         */
    @Stories("TestFlow:Buy Now without Minimum Price")
    @Features("Home&LifeStyle")
    @Title("Buy Now without Minimum Price")
    @Description("To Test Buy Now without Minimum Price")
    @Test(groups = {"EscrowHomeLifeStyle"})
    public void BuyNowWithoutMinPrice() {

        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWOMinPriceForHomeAppliance(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsHomeLifeStyle();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpen(), "BuyNow Pop Up did not open");
        buyNowElectronics(testData.get("buyerMail"), testData.get("buyerMobile"));
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
     * Make an Offer from SNB Page
     */
    @Stories("TestFlow:Make an offer from Snb")
    @Features("Home&LifeStyle")
    @Title("Make an offer from Snb")
    @Description("To Test Make an offer from Snb")
    @Test(groups = {"EscrowHomeLifeStyle"})
    public void MAOFromSnb() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWOMinPriceForHomeAppliance(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsHomeLifeStyle();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectMakeAnOffer(AdId);
        Assert.assertTrue(snbPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        vapPage.acceptOfferSeller(OfferId, AdId,pass,testData.get("sellerName"),encodeSellerEmail);
        payNowPage.openPayNowURL(OfferId,pass);
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
     * Make an Offer for an Ad with mini Price
     */
    @Stories("TestFlow:Make an offer with Minimum Price")
    @Features("Home&LifeStyle")
    @Title("Make an offer with Minimum Price")
    @Description("To Test Make an offer with Minimum Price")
    @Test(groups = {"EscrowHomeLifeStyle"})
    public void MAOWithMinPriceLimit() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWithMinPriceHomeAppliance(testData, testData.get("minprice"),sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsHomeLifeStyle();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"));
        Assert.assertTrue(vapPage.isOfferAutoAccepted(), "Offer was not Auto Accepted");
        vapPage.selectBuyNowAutoAcceptButton();
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
     * Buy from VAP page.
     */
    @Stories("TestFlow:Buy Now with Minimum Price")
    @Features("Home&LifeStyle")
    @Title("Buy Now with Minimum Price")
    @Description("To Test Buy Now with Minimum Price")
    @Test(groups = {"EscrowHomeLifeStyle"})
    public void BuyNowWithMinPrice() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWithMinPriceHomeAppliance(testData, testData.get("minprice"),sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsHomeLifeStyle();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpen(), "BuyNow Pop Up did not open");
        buyNowElectronics(testData.get("buyerMail"), testData.get("buyerMobile"));
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
     * Make an Offer with wareHouse option Check
     */
    @Stories("TestFlow:Make an offer with WareHouse Check")
    @Features("Home&LifeStyle")
    @Title("Make an offer with WareHouse Check")
    @Description("To Test Make an offer with WareHouse Check")
    @Test(groups = {"EscrowHomeLifeStyle"},enabled=false)
    public void wareHouseMao() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWithWarehouseForHomeAppliance(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        Assert.assertTrue(db.checkWareHouseStatus(AdId));
        flagStatus = apiBase.postAdWareHouseBeforeDeliver(AdId, 0, 0);
        Assert.assertEquals(db.wareHouseDetail(AdId), "50");
        // adstatus==50 and warehouse>0
        papPage.navigateHome();
        //TODO call content/publishAd with adId, attribute,flaReason, Status with 0, 0
        awbNumber = db.awbWareHouse(AdId);
        apiBase.updateOrderStatusPostShip(AdId, "", "", EscrowEnumData.overall_status.DELIVERED, "", EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED, EscrowEnumData.item_direction.To_Buyer, awbNumber);
        //TODO call content/publishAd with adId, attribute,flaReason, Status
        apiBase.postAdWareHouseBeforeDeliver(AdId, flagStatus[0], flagStatus[1]);
        apiBase.publishAd(AdId);
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        vapPage.acceptOfferSeller(OfferId, AdId,pass,testData.get("sellerName"),encodeSellerEmail);
        payNowPage.openPayNowURL(OfferId,pass);
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
     * Make an Offer-->Counter offer with wareHouse option Check
     */
    @Stories("TestFlow:Counter offer with WareHouse Check")
    @Features("Home&LifeStyle")
    @Title("Counter offer with WareHouse Check")
    @Description("To Test Counter offer with WareHouse Check")
    @Test(groups = {"EscrowHomeLifeStyle"},enabled=false)
    public void wareHouseMaoCounteroffer() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWithWarehouseForHomeAppliance(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
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
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = db.getOfferId(AdId);
        counterOfferSeller(OfferId, testData.get("counterPrice"), testData.get("sellerName"), testData.get("sellerPincode"), testData.get("sellerAddress"), sellerMobilenum, AdId,pass,encodeSellerEmail);
        payNowPage.openPayNowURL(OfferId,pass);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPrice"),testData.get("buyerMobileProd")), "Payable amount doesnot match counter price");
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
     * Buy from VAP Page for an Ad with WareHouse option check.
     */
    @Stories("TestFlow:Buy Now with WareHouse Check")
    @Features("Home&LifeStyle")
    @Title("Buy Now with WareHouse Check")
    @Description("To test Buy Now with WareHouse Check")
    @Test(groups = {"EscrowHomeLifeStyle"},enabled=false)
    public void wareHouseBuy() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWithWarehouseForHomeAppliance(testData,sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
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
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpen(), "BuyNow Pop Up did not open");
        buyNowElectronics(testData.get("buyerMail"), testData.get("buyerMobile"));
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
         * Posted Ad with Price and Min Price same and Buy
         */
    @Stories("TestFlow:Buy Now with same Price and Minimum Price")
    @Features("Home&LifeStyle")
    @Title("Buy Now with same Price and Minimum Price")
    @Description("To test Buy Now with same Price and Minimum Price")
    @Test(groups = {"EscrowHomeLifeStyle"})
    public void samePriceAndMinPrice() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        postAdWithMinPriceHomeAppliance(testData, testData.get("minpriceandpricesame"),sellerEmail,sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.SelectsHomeLifeStyle();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        Assert.assertTrue(snbPage.buyNowOnly());
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        List<WebElement> list = vapPage.buttononVap();
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).getAttribute("class").contains("Buy"));
        vapPage.selectBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpen(), "BuyNow Pop Up did not open");
        buyNowElectronics(testData.get("buyerMail"), testData.get("buyerMobile"));
        payNowWithoutWarrantyElectronics(testData);
        acceptAlert();
        Assert.assertTrue(payNowPage.isTransactionSuccessfull(), "Payment Unsuccessfull");
        payNowPage.getTransactionId();
        Assert.assertTrue(db.ifSold(AdId), "Sold Tag NOT applied to AdId : " + AdId);
        homePage.navigateToNewUI("http://bangalore.quikr.com");
        waitForPageToLoad("http://bangalore.quikr.com");
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.adSoldTag());
    }

    /*
     * Mandatory Field check for an Ad.
     */
    @Stories("TestFlow:Post Ad with mandatory Field Check")
    @Features("Home&LifeStyle")
    @Title("Post Ad with mandatory Field Check")
    @Description("To test Post Ad with mandatory Field Check")
    @Test(groups = {"EscrowHomeLifeStyle"})
    public void AdMandatoryFieldCheck() {
        HomePage homePage = new HomePage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryHome"), testData.get("subCatHome"));
        papPage.submitAd();
        Assert.assertTrue(papPage.isTitleErrorThrown(), "Title doesnot seem to be Mandatory");
        //Assert.assertTrue(papPage.isConditionErrorThrown(), "Condition doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isPriceErrorThrown(), "Price doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isFurnitureTypeErrorThrown(), "Furniture Type doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isDescriptionErrorThrown(), "Description doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isSellerTypeErrorThrown(), "Seller Type doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isAddressErrorThrown(), "Address doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isLocalityErrorThrown(), "Locality doesnot seem to be Mandatory");
        Assert.assertTrue(papPage.isPincodeErrorThrown(), "Pincode doesnot seem to be Mandatory");
    }

    //EscrowProduction Cases/////////////////////////////////////

    /*
     * Make an Offer from VAP Page
     */
    @Stories("TestFlow:Make an offer from Vap")
    @Features("Home&LifeStyle")
    @Title("Make an offer from Vap")
    @Description("To Test Make an offer from Vap")
    @Test(groups = {"EscrowProduction"})
    public void MAOFromVapProdHome() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE);
        headerPage.search(ADID);
        Assert.assertTrue(snbPage.isSearchSuccess(ADID), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAOHome"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADID);
        vapPage.acceptOfferSellerProd(OfferId, ADID,testData.get("sellerNameProd"));
        payNowPage.openPayNowURLProd(OfferId);
        payNowWithoutWarrantyElectronicsProd(testData);

    }

    /*
     * Make an Offer-->Counter Offer from VAP Page
     */
    @Stories("TestFlow:Counter offer Functionality")
    @Features("Home&LifeStyle")
    @Title("Counter offer Functionality")
    @Description("To Test Counter offer Functionality")
    @Test(groups = {"EscrowProduction"})
    public void MaoCounterofferProdHome() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE);
        headerPage.search(ADID);
        Assert.assertTrue(snbPage.isSearchSuccess(ADID), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAOHome"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADID);
        counterOfferSellerProd(OfferId, testData.get("counterPriceHome"), testData.get("sellerNameProd"), testData.get("sellerPincodeProd"), testData.get("sellerAddressProd"), testData.get("sellerMobileProd"), ADID);
        payNowPage.openPayNowURLProd(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPriceHome"),testData.get("buyerMobileProd")), "Payable amount doesnot match counter price");
        payNowInProd(testData);

    }


    /*
     * Make an Offer from SNB Page
     */
    @Stories("TestFlow:Make an offer from Snb")
    @Features("Home&LifeStyle")
    @Title("Make an offer from Snb")
    @Description("To Test Make an offer from Snb")
    @Test(groups = {"EscrowProduction"})
    public void MAOFromSnbProdHome() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE);
        headerPage.search(ADID);
        Assert.assertTrue(snbPage.isSearchSuccess(ADID), "Search Unsuccessfull");
        snbPage.selectMakeAnOffer(ADID);
        Assert.assertTrue(snbPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronicsSnB(testData.get("priceMAOHome"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        Assert.assertTrue(vapPage.isOfferSentSnB(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADID);
        vapPage.acceptOfferSellerProd(OfferId, ADID,testData.get("sellerNameProd"));
        payNowPage.openPayNowURLProd(OfferId);
        payNowWithoutWarrantyElectronicsProd(testData);

    }

    /*
     * Make an Offer for an Ad with mini Price
     */
    @Stories("TestFlow:Make an offer with Minimum Price")
    @Features("Home&LifeStyle")
    @Title("Make an offer with Minimum Price")
    @Description("To Test Make an offer with Minimum Price")
    @Test(groups = {"EscrowProduction"})
    public void MAOWithMinPriceLimitProdHome() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE);
        headerPage.search(ADIDWMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDWMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAOHome1"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
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
    @Features("Home&LifeStyle")
    @Title("Buy Now with Same Price and Minimum Price")
    @Description("To Test Buy Now with Same Price and Minimum Price")
    @Test(groups = {"EscrowProduction"})
    public void samePriceAndMinPriceProdHome() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE);
        headerPage.search(ADIDSAMEMINPRICE);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDSAMEMINPRICE), "Search Unsuccessfull");
        Assert.assertTrue(snbPage.buyNowOnly());
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        List<WebElement> list = vapPage.buttononVap();
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).getAttribute("class").contains("buyBox"));
        vapPage.selectBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpen(), "BuyNow Pop Up did not open");
        buyNowElectronics(testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        payNowInProd(testData);

    }


    /*
       * Buy Now on SnB
       */
    @Stories("TestFlow:Buy Now from SnB")
    @Features("Electronic&Appliance")
    @Title("Buy Now from SnB")
    @Description("To Test Buy Now from SbB")
    @Test(groups = {"EscrowProduction"})
    public void buyNowFromSnBHome() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE);
        headerPage.search(ADIDSAMEMINPRICE);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDSAMEMINPRICE), "Search Unsuccessfull");
        Assert.assertTrue(snbPage.buyNowOnly());
        snbPage.clickBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpenSnB(), "BuyNow Pop Up did not open");
        buyNowElectronicsSnB(testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        payNowInProd(testData);
        Assert.assertTrue(payNowPage.checkTransactionStatusInProd(), "Payment Unsuccessfull");

    }
    /*
           * Posted Ad with Price and Min Price same and Buy
           */
    @Stories("TestFlow:Buy Now with Same Price and Minimum Price")
    @Features("Home&LifeStyle")
    @Title("Buy Now with Same Price and Minimum Price")
    @Description("To Test Buy Now with Same Price and Minimum Price")
    @Test(groups = {"EscrowProduction"})
    public void userProfileBuyProdHome() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.HOME_AND_LIFESTYLE);
        headerPage.search(ADIDSAMEMINPRICE);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDSAMEMINPRICE), "Search Unsuccessfull");
        Assert.assertTrue(snbPage.buyNowOnly());
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        List<WebElement> list = vapPage.buttononVap();
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).getAttribute("class").contains("buyBox"));
        vapPage.selectBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpen(), "BuyNow Pop Up did not open");
        buyNowElectronics(testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        payNowInProd(testData);

    }



    @AfterMethod(alwaysRun = true)
    public void AfterMethod() {
        if (AdId != null)
            LOGGER.info("AdId==  " + AdId + "OfferId== " + OfferId);
    }
}
