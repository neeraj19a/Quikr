package com.quikr.website.escrow;

import com.quikr.website.TestBase;
import com.quikr.website.escrow.escrowPap.EscrowPapPage;
import com.quikr.website.escrow.escrowPayNow.EscrowPayNowPage;
import com.quikr.website.escrow.escrowVap.EscrowVapPage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by quikr on 21/1/16.
 */
public class EscrowTestBase extends TestBase {

    protected HashMap<String, String> testData = getTestData(getProperties().get("ESCROW_E2E_TESTDATA_FILE"));

    protected String AdId = null;
    protected String OfferId = null;
    private final static Log LOGGER = LogFactory.getLog(EscrowTestBase.class.getName());
    private static final String MYOFFER_URL_SALT = "2B0@3aD7!f";



    public void postAdWOMinPrice(HashMap<String, String> testData,String sellerEmail,String mobile) {
        EscrowPapPage papPage = new EscrowPapPage(driver);
        papPage.selectAdType();
        papPage.enterTitle(testData.get("title"));
        papPage.selectCondition();
        papPage.enterPrice(testData.get("price"));
        papPage.selectBrandName(testData.get("mobileBrand"));
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        //papPage.enterSellerEmail(testData.get("sellerMail"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(mobile);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectCity(testData.get("sellerCity"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        //papPage.freeAd();
        papPage.submitAd();
    }

    public void postAdWOMinPriceForHomeAppliance(HashMap<String, String> testData,String sellerEmail,String mobile) {
        EscrowPapPage papPage = new EscrowPapPage(driver);
        papPage.selectAdType();
        papPage.enterTitle(testData.get("title"));
        papPage.furnitureType(testData.get("furnituretype"));
        //papPage.selectCondition();
        papPage.enterPrice(testData.get("price"));
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(mobile);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectCity(testData.get("sellerCity"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        //papPage.freeAd();
        papPage.submitAd();
    }

    public void postAdWithMinPrice(HashMap<String, String> testData, String minprice,String sellerEmail,String mobile) {
        EscrowPapPage papPage = new EscrowPapPage(driver);
        papPage.selectAdType();
        papPage.enterTitle(testData.get("title"));
        papPage.selectCondition();
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(minprice);
        papPage.selectBrandName(testData.get("mobileBrand"));
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(mobile);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectCity(testData.get("sellerCity"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        //papPage.freeAd();
        papPage.submitAdWithMinPrice(minprice);
    }

    public void postAdWithMinPriceHomeAppliance(HashMap<String, String> testData, String minprice,String sellerEmail,String mobile) {
        EscrowPapPage papPage = new EscrowPapPage(driver);
        papPage.selectAdType();
        papPage.enterTitle(testData.get("title"));
        papPage.furnitureType(testData.get("furnituretype"));
        //papPage.selectCondition();
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(minprice);
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(mobile);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectCity(testData.get("sellerCity"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        //papPage.freeAd();
        papPage.submitAdWithMinPrice(minprice);
    }

    public void postAdWithWarehouse(HashMap<String, String> testData,String sellerEmail,String mobile) {
        EscrowPapPage papPage = new EscrowPapPage(driver);
        papPage.selectAdType();
        papPage.enterTitle(testData.get("title"));
        papPage.selectCondition();
        //papPage.warehouseSelect();
        papPage.enterPrice(testData.get("price"));
        papPage.selectBrandName(testData.get("mobileBrand"));
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(mobile);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectCity(testData.get("sellerCity"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        //papPage.freeAd();
        papPage.submitAdWareHouse();
    }

    public void postAdWithWarehouseForHomeAppliance(HashMap<String, String> testData,String sellerEmail,String mobile) {
        EscrowPapPage papPage = new EscrowPapPage(driver);
        papPage.selectAdType();
        papPage.enterTitle(testData.get("title"));
        papPage.furnitureType(testData.get("furnituretype"));
        //papPage.selectCondition();
        //papPage.warehouseSelect();
        papPage.enterPrice(testData.get("price"));
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(mobile);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectCity(testData.get("sellerCity"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        //papPage.freeAd();
        papPage.submitAdWareHouse();
    }

    public void postAdAllFields(HashMap<String, String> testData,String sellerEmail,String mobile) {
        EscrowPapPage papPage = new EscrowPapPage(driver);

        papPage.selectAdType();
        papPage.enterTitle(testData.get("title"));
        papPage.selectCondition();
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(testData.get("minprice"));
        papPage.selectBrandName(testData.get("mobileBrand"));
        papPage.selectYearOfPurchase(testData.get("year"));
        papPage.selectOS(testData.get("osName"));
        papPage.selectNoOfSims(testData.get("sims"));
        papPage.selectAlsoIncludes(testData.get("items"));
        papPage.selectFunctionalChecks(testData.get("checks"));
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(mobile);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectCity(testData.get("sellerCity"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        //papPage.freeAd();
        papPage.submitAd();
    }

    public void postAdAllFieldsElectronics(HashMap<String, String> testData,String sellerEmail,String mobile) {
        EscrowPapPage papPage = new EscrowPapPage(driver);

        papPage.selectAdType();
        papPage.enterTitle(testData.get("title"));
        papPage.selectCondition();
        //papPage.warehouseSelect();
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(testData.get("minprice"));
        papPage.selectBrandName(testData.get("mobileBrand"));
        papPage.screenSize(testData.get("screenSize"));
        papPage.selectRam(testData.get("ramsize"));
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(mobile);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectCity(testData.get("sellerCity"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        //papPage.freeAd();
        papPage.submitAd();
    }

    public void counterOfferSellerProd(String offerId, String price, String name, String pincode, String address, String mobile,String adid) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        /*vapPage.openTab();
        vapPage.switchTab();*/
        vapPage.openCounterOfferURLProd(offerId,adid);
        counterOfferDetails(price, name, pincode, address, mobile,offerId);
        /*vapPage.closeTab();
        vapPage.switchTab();*/
    }

    public void counterOfferSeller(String offerId, String price, String name, String pincode, String address, String mobile,String adid,String pass,String email) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        /*vapPage.openTab();
        vapPage.switchTab();*/
        vapPage.openCounterOfferURL(offerId,adid,pass,email);
        counterOfferDetails(price, name, pincode, address, mobile,offerId);
        /*vapPage.closeTab();
        vapPage.switchTab();*/
    }

    public void counterOfferDetails(String price, String name, String pincode, String address, String mobile,String offerId) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        /*vapPage.inputCounterOfferPrice(price);
        vapPage.inputCounterOfferName(name);
       *//* vapPage.inputCounterOfferPincode(pincode);
        vapPage.inputCounterOfferAddress(address);
        vapPage.inputCounterOfferMobile(mobile);*//*
        vapPage.submitCounterOffer();*/
        vapPage.inputCounterOfferPriceMyOffer(price,offerId);

    }

    public void payNowWithoutWarranty(String buyerName, String buyerAddress, String buyerPincode, String buyerMobile, String cardType, String cardNo, String expiryDate, String Cvv) {
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        //payNowPage.payNowContinueLogin();
        payNowPage.payNowBuyerName(buyerName);
        payNowPage.payNowBuyerAddress(buyerAddress);
        payNowPage.payNowBuyerPincode(buyerPincode);
        payNowPage.payNowBuyerMobile(buyerMobile);
        payNowPage.clickDelivery();
        payNowPage.skipWarranty();
        enterCardDetails(cardType, cardNo, buyerName, expiryDate, Cvv);
        payNowPage.clickPayNow();
    }

    public void payNowWithoutWarrantyElectronics(HashMap<String, String> testData) {
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        //payNowPage.payNowContinueLogin();
        payNowPage.payNowBuyerName(testData.get("buyerName"));
        payNowPage.payNowBuyerAddress(testData.get("buyerAddress"));
        payNowPage.payNowBuyerPincode(testData.get("buyerPincode"));
        payNowPage.payNowBuyerMobile(testData.get("buyerMobile"));
        payNowPage.clickDelivery();
        enterCardDetails(testData.get("cardType"), testData.get("cardNo"), testData.get("buyerName"), testData.get("expiryDate"), testData.get("cvv"));
        payNowPage.clickPayNow();
    }

    public void payNowWithoutWarrantyElectronicsProd(HashMap<String, String> testData) {
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        payNowPage.mobileVerification(testData.get("buyerMobileProd"));
        payNowPage.payNowBuyerName(testData.get("buyerNameProd"));
        payNowPage.payNowBuyerAddress(testData.get("buyerAddress"));
        payNowPage.payNowBuyerPincode(testData.get("buyerPincode"));
        payNowPage.payNowBuyerMobile(testData.get("buyerMobileProd"));
        //payNowPage.clickDelivery();
        enterCardDetails(testData.get("cardType"), testData.get("cardNo"), testData.get("buyerNameProd"), testData.get("expiryDate"), testData.get("cvv"));
    }
    public void payNowInProd(HashMap<String, String> testData) {
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);
        payNowPage.payNowBuyerName(testData.get("buyerNameProd"));
        payNowPage.payNowBuyerAddress(testData.get("buyerAddress"));
        payNowPage.payNowBuyerPincode(testData.get("buyerPincode"));
        payNowPage.payNowBuyerMobile(testData.get("buyerMobileProd"));
        //payNowPage.clickDelivery();
        enterCardDetails(testData.get("cardType"), testData.get("cardNo"), testData.get("buyerNameProd"), testData.get("expiryDate"), testData.get("cvv"));
        payNowPage.clickPayNow();
    }

    public void makeAnOffer(String price, String email, String mobile, String city) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.maoPrice(price);
        vapPage.maoMail(email);
        vapPage.maoMobile(mobile);
        vapPage.maoCity(city);
        vapPage.submitOffer();
    }
    public void makeAnOfferSnB(String price, String email, String mobile, String city) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.maoPriceSnB(price);
        vapPage.maoMailSnB(email);
        vapPage.maoMobileSnB(mobile);
        vapPage.maoCitySnB(city);
        vapPage.submitOfferSnB();
    }
    public void makeAnOfferUserProfile(String price, String email, String mobile, String city) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.maoPrice(price);
        vapPage.maoMail(email);
        vapPage.maoMobile(mobile);
        vapPage.maoCity(city);
        vapPage.submitOffer();
    }

    public void makeAnOfferForElectronics(String price, String email, String mobile) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.maoPrice(price);
        vapPage.maoMail(email);
        vapPage.maoMobile(mobile);
        vapPage.submitOffer();
    }

    public void makeAnOfferForElectronicsSnB(String price, String email, String mobile) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.maoPriceSnB(price);
        vapPage.maoMailSnB(email);
        vapPage.maoMobileSnB(mobile);
        vapPage.submitOfferSnB();
    }

    public void buyNow(String email, String mobile, String city) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.buyNowMail(email);
        vapPage.buyNowMobile(mobile);
        vapPage.buyNowCity(city);
        vapPage.submitBuyNow();
    }
    public void buyNowSnB(String email, String mobile, String city) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.buyNowMailSnB(email);
        vapPage.buyNowMobileSnB(mobile);
        vapPage.buyNowCitySnB(city);
        vapPage.submitOfferSnB();
    }


    public void buyNowElectronics(String email, String mobile) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.buyNowMail(email);
        vapPage.buyNowMobile(mobile);
        vapPage.submitBuyNow();
    }

    public void buyNowElectronicsSnB(String email, String mobile) {
        EscrowVapPage vapPage = new EscrowVapPage(driver);
        vapPage.buyNowMailSnB(email);
        vapPage.buyNowMobileSnB(mobile);
        vapPage.submitOfferSnB();
    }

    public void enterCardDetails(String cardType, String cardNo, String nameOnCard, String expiryDate, String Cvv) {
        EscrowPayNowPage payNowPage = new EscrowPayNowPage(driver);

        payNowPage.selectcardType(cardType);
        payNowPage.inputCardNo(cardNo);
        payNowPage.inputNameOnCard(nameOnCard);
        payNowPage.inputExpiry(expiryDate);
        payNowPage.inputCvv(Cvv);
    }

    public String randomSellerEmail() {
        DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        // get current date time with Date()
        Date date = new Date();
        String EmailDate = dateFormat.format(date);
        String email = "Escrow" + EmailDate + "@rediffmail.com";
        return email;
    }

    public String Base64Encoding(String string) {
        String encryptedString = null;
        try {
            byte[] encodeByte = Base64.encodeBase64(string.getBytes("utf-8"));
            encryptedString=new String(encodeByte);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Can't Encode String to base64: [" + encryptedString + "]", e);
        }
        return encryptedString;
    }

    public String mD5(String email) {
        String inputData = email + MYOFFER_URL_SALT;
        return DigestUtils.md5Hex(inputData);
    }

    public long generateRandomMobileNo(int length) {
        StringBuilder sb=new StringBuilder();
        Random random = new Random();
        sb.append('8');
        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return Long.parseLong(new String(sb.toString()));
    }





}
