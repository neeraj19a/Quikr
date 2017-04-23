package com.quikr.website.escrow.escrowEndToEnd;

import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.api.escrow.EscrowEnumData;
import com.quikr.utils.Database;
import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.escrow.EscrowTestBase;
import com.quikr.website.escrow.escrowFilterSearch.EscrowSearchFilterPage;
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
 * Created by Rajat Kachhara on 03/2/16.
 */
public class EscrowElectronicEndToEndTests extends EscrowTestBase {
    // test data
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
    public EscrowSearchFilterPage escrowsearch = null;
    public int flagStatus[] = new int[10];
    private final static Log LOGGER = LogFactory.getLog(EscrowElectronicEndToEndTests.class.getName());
    public String sellerEmail = null;
    public String sellerMobilenum = null;
    public String encodeSellerEmail = null;
    private static final String ADID="275441308";
    private static final String ADIDWOMIN="275441595";
    private static final String ADIDMINPRICSAME="275441972";
    public String pass = null;


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
        escrowsearch = new EscrowSearchFilterPage(driver);
        sellerEmail = randomSellerEmail();
        sellerMobilenum = String.valueOf(generateRandomMobileNo(10));
        encodeSellerEmail = Base64Encoding(sellerEmail);
        pass=mD5(sellerEmail);
    }

    /*
     * Make an Offer from VAP Page
     */
    @Stories("TestFlow: Make An Offer from VAP")
    @Features("Electronic&Appliance")
    @Title("Title: Make An Offer from VAP")
    @Description("To test Make An Offer from VAP")
    @Test(groups = {"EscrowElectronics"})
    public void MAOFromVap() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWOMinPrice(testData, sellerEmail, sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.selectElectronicsAndAppliances();
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
    @Stories("TestFlow:Counter Offer")
    @Features("Electronic&Appliance")
    @Title("Counter Offer")
    @Description("To test Counter Offer")
    @Test(groups = {"EscrowElectronics"})
    public void Counteroffer() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWOMinPrice(testData, sellerEmail, sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.selectElectronicsAndAppliances();
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
        /*payNowWithoutWarrantyElectronics(testData.get("buyerName"), testData.get("buyerAddress"), testData.get("buyerPincode"), testData.get("buyerMobile"), testData.get("cardType"), testData.get("cardNo"), testData.get("expiryDate"), testData.get("cvv"));*/
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
    @Features("Electronic&Appliance")
    @Title("Buy Now without Minimum Price")
    @Description("To test Buy Now without Minimum Price")
    @Test(groups = {"EscrowElectronics"})
    public void BuyNowWithoutMinPrice() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWOMinPrice(testData, sellerEmail, sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.selectElectronicsAndAppliances();
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
    @Features("Electronic&Appliance")
    @Title("Make an offer from Snb")
    @Description("To test Make an offer from Snb")
    @Test(groups = {"EscrowElectronics"})
    public void MAOFromSnb() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWOMinPrice(testData, sellerEmail, sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.selectElectronicsAndAppliances();
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
    @Stories("TestFlow:Make an offer with minimun price")
    @Features("Electronic&Appliance")
    @Title("Make an offer with minimun price")
    @Description("To test Make an offer with minimun price")
    @Test(groups = {"EscrowElectronics"})
    public void MAOWithinMinPriceLimit() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWithMinPrice(testData, testData.get("minprice"), sellerEmail, sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.selectElectronicsAndAppliances();
        headerPage.search(AdId);
        Assert.assertTrue(snbPage.isSearchSuccess(AdId), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAO"), testData.get("buyerMail"), testData.get("buyerMobile"));
        Assert.assertTrue(vapPage.isOfferAutoAccepted(), "Offer was not Auto Accepted");
        vapPage.selectBuyNowAutoAcceptButton();
        //vapPage.selectBuyNow();
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
    @Features("Electronic&Appliance")
    @Title("Buy Now with Minimum Price")
    @Description("To test Buy Now with Minimum Price")
    @Test(groups = {"EscrowElectronics"})
    public void BuyNowWithMinPrice() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWithMinPrice(testData, testData.get("minprice"), sellerEmail, sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.selectElectronicsAndAppliances();
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
    @Stories("TestFlow:Make an offer with WareHouse check")
    @Features("Electronic&Appliance")
    @Title("Make an offer with WareHouse check")
    @Description("To test Make an offer with WareHouse check")
    @Test(groups = {"EscrowElectronics"},enabled=false)
    public void wareHouseMao() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWithWarehouse(testData, sellerEmail, sellerMobilenum);
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
    @Stories("TestFlow:Counter offer with WareHouse check")
    @Features("Electronic&Appliance")
    @Title("Counter offer with WareHouse check")
    @Description("To test Counter offer with WareHouse check")
    @Test(groups = {"EscrowElectronics"},enabled=false)
    public void wareHouseMaoCounteroffer() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWithWarehouse(testData, sellerEmail, sellerMobilenum);
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
    @Stories("TestFlow:Buy Now with WareHouse check")
    @Features("Electronic&Appliance")
    @Title("Buy Now with WareHouse check")
    @Description("To test Buy Now with WareHouse check")
    @Test(groups = {"EscrowElectronics"},enabled=false)
    public void wareHouseBuy() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWithWarehouse(testData, sellerEmail, sellerMobilenum);
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
    @Stories("TestFlow:Buy Now with Same Price and Minimum Price")
    @Features("Electronic&Appliance")
    @Title("Buy Now with Same Price and Minimum Price")
    @Description("To Test Buy Now with Same Price and Minimum Price")
    @Test(groups = {"EscrowElectronics"})
    public void samePriceAndMinPrice() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdWithMinPrice(testData, testData.get("minpriceandpricesame"), sellerEmail, sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        papPage.navigateHome();
        homePage.categoryDropDown();
        homePage.selectElectronicsAndAppliances();
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
         * Post Ad with All field
         */
    @Stories("TestFlow:Post Ad with All field ")
    @Features("Electronic&Appliance")
    @Title("Post Ad with All field ")
    @Description("To Test Post Ad with All field entered.")
    @Test(groups = {"EscrowElectronics"})
    public void adWithAllFields() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
        postAdAllFieldsElectronics(testData, sellerEmail, sellerMobilenum);
        papPage.handleVerifyNoPopUp();
        Assert.assertTrue(papPage.isAdPostSuccess(), "Error with Post Ad");
        AdId = papPage.getAdId();
        apiBase.publishAd(AdId);
        System.out.println(db.getAdDetails(AdId));
    }

    /*
     * Mandatory Field check for an Ad.
     */
    @Stories("TestFlow:Post Ad with Mandatory field")
    @Features("Electronic&Appliance")
    @Title("Post Ad with Mandatory field ")
    @Description("To Test Post Ad with Mandatory field ")
    @Test(groups = {"EscrowElectronics"})
    public void AdMandatoryFieldCheck() {
        HomePage homePage = new HomePage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectCategories(testData.get("categoryElectronic"), testData.get("subCatElectronic"));
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

    @Stories("TestFlow:Filter functionality on Snb")
    @Features("Electronic&Appliance")
    @Title("Filter functionality on Snb ")
    @Description("To Test Filter functionality on Snb ")
    @Test(groups = {"EscrowElectronics"})
    public void filterCheck() {
        HomePage homePage = new HomePage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.SelectsMobiles();
        Assert.assertTrue(escrowsearch.isMobileTabletPageOpen(), "Mobile and Tablet page did not get open");
        escrowsearch.selectBrand();
        Assert.assertTrue(escrowsearch.isBrandSelected(testData.get("searchBrandName")), "Brand not selected");
        escrowsearch.selectAd();
        Assert.assertTrue(escrowsearch.isAdSelected(), "Ad not selected");

    }

    // EscrowProduction cases////////////////////////////////////////

    /*
     * Make an Offer from VAP Page
     */
    @Stories("TestFlow: Make An Offer from VAP")
    @Features("Electronic&Appliance")
    @Title("Title: Make An Offer from VAP")
    @Description("To test Make An Offer from VAP")
    @Test(groups = {"EscrowProduction"})
    public void MAOFromVapProd() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
       homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES);
       /* homePage.categoryDropDown();
        homePage.selectElectronicsAndAppliances()*/;
        headerPage.search(ADIDWOMIN);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDWOMIN), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAOElect"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADIDWOMIN);
        vapPage.acceptOfferSellerProd(OfferId, ADIDWOMIN,testData.get("sellerNameProd"));
        payNowPage.openPayNowURLProd(OfferId);
        payNowWithoutWarrantyElectronicsProd(testData);

    }

    /*test
     * Make an Offer-->Counter Offer from VAP Page
     */
    @Stories("TestFlow:Counter Offer")
    @Features("Electronic&Appliance")
    @Title("Counter Offer")
    @Description("To test Counter Offer")
    @Test(groups = {"EscrowProduction"})
    public void CounterofferProd() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES);
        headerPage.search(ADID);
        Assert.assertTrue(snbPage.isSearchSuccess(ADID), "Search Unsuccessfull");
        snbPage.selectAd();
        Assert.assertTrue(vapPage.ifAdSelected(), "Ad Not Selected");
        vapPage.selectMakeAnOffer();
        Assert.assertTrue(vapPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronics(testData.get("priceMAOElect1"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        Assert.assertTrue(vapPage.isOfferSent(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADID);
        counterOfferSellerProd(OfferId, testData.get("counterPriceElect"), testData.get("sellerNameProd"), testData.get("sellerPincodeProd"), testData.get("sellerAddressProd"), testData.get("sellerMobileProd"), ADID);
        payNowPage.openPayNowURLProd(OfferId);
        Assert.assertTrue(payNowPage.priceCheck(testData.get("counterPriceElect"),testData.get("buyerMobileProd")), "Payable amount doesnot match counter price");
        payNowInProd(testData);

    }

    /*
     * Make an Offer from SNB Page
     */
    @Stories("TestFlow:Make an offer from Snb")
    @Features("Electronic&Appliance")
    @Title("Make an offer from Snb")
    @Description("To test Make an offer from Snb")
    @Test(groups = {"EscrowProduction"})
    public void MAOFromSnbProd() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES);
        headerPage.search(ADID);
        Assert.assertTrue(snbPage.isSearchSuccess(ADID), "Search Unsuccessfull");
        snbPage.selectMakeAnOffer(ADID);
        Assert.assertTrue(snbPage.isMAOOpen(), "MAO Pop Up did not open");
        makeAnOfferForElectronicsSnB(testData.get("priceMAOElect1"), testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        Assert.assertTrue(vapPage.isOfferSentSnB(), "Offer was not sent");
        OfferId = apiBase.getOfferTokenId(ADID);
        vapPage.acceptOfferSellerProd(OfferId, ADID,testData.get("sellerNameProd"));
        payNowPage.openPayNowURLProd(OfferId);
        payNowWithoutWarrantyElectronicsProd(testData);

    }

    /*
        * Posted Ad with Price and Min Price same and Buy
        */
    @Stories("TestFlow:Buy Now with Same Price and Minimum Price")
    @Features("Electronic&Appliance")
    @Title("Buy Now with Same Price and Minimum Price")
    @Description("To Test Buy Now with Same Price and Minimum Price")
    @Test(groups = {"EscrowProduction"})
    public void samePriceAndMinPriceProd() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES);
        headerPage.search(ADIDMINPRICSAME);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDMINPRICSAME), "Search Unsuccessfull");
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
    public void buyNowFromSnBElect() {
        headerPage.selectCity("Bangalore");
        waitForPageToLoad("http://bangalore.quikr.com");
        homePage.selectCategoryFromDropDown(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES);
        headerPage.search(ADIDMINPRICSAME);
        Assert.assertTrue(snbPage.isSearchSuccess(ADIDMINPRICSAME), "Search Unsuccessfull");
        Assert.assertTrue(snbPage.buyNowOnly());
        snbPage.clickBuyNow();
        Assert.assertTrue(vapPage.isBuyNowOpenSnB(), "BuyNow Pop Up did not open");
        buyNowElectronicsSnB(testData.get("buyerMailProd"), testData.get("buyerMobileProd"));
        payNowInProd(testData);
        Assert.assertTrue(payNowPage.checkTransactionStatusInProd(), "Payment Unsuccessfull");
    }
    @AfterMethod(alwaysRun = true)
    public void AfterMethod() {
        if (AdId != null)
            LOGGER.info("AdId==" + AdId + " OfferId== " + OfferId);
    }
}
