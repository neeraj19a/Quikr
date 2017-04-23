package com.quikr.msite.mEscrow;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mEscrow.mEscrowPap.MEscrowPapPage;
import com.quikr.msite.mEscrow.mEscrowPayNow.MEscrowPayNowPage;
import com.quikr.msite.mEscrow.mEscrowSnb.MEscrowSnbPage;
import com.quikr.msite.mEscrow.mEscrowVap.MEscrowVapPage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by quikr on 9/2/16.
 */
public class MEscrowTestBase extends MTestBase {
    private final static Log LOGGER = LogFactory.getLog(MEscrowTestBase.class.getName());

    public void postAdMobileWOMinPrice(HashMap<String, String> testData,String sellerEmail,String sellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        //papPage.enterDescription(testData.get("description"));
        //papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryMobile"));
        papPage.selectSubCategory(testData.get("subCatMobile"));
        papPage.goNextFromPictures();
        papPage.enterPrice(testData.get("price"));
        papPage.selectPurchaseYear(testData.get("yop"));
        papPage.selectOriginalInvoice();
        papPage.selectBrand(testData.get("mobileBrand"));
        papPage.selectModel(testData.get("mobileModel"));
        papPage.selectOS(testData.get("OS"));
        papPage.selectSims(testData.get("sims"));
        papPage.selectIncludedItem(testData.get("includedItem"));
        papPage.selectPhysicalChecks(testData.get("functionalCheck"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterDescription(testData.get("description"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(sellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }
    public void postAdMobileWithMinPrice(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        //papPage.enterDescription(testData.get("description"));
        //papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryMobile"));
        papPage.selectSubCategory(testData.get("subCatMobile"));
        papPage.goNextFromPictures();
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(testData.get("minprice"));
        papPage.selectPurchaseYear(testData.get("yop"));
        papPage.selectOriginalInvoice();
        papPage.selectBrand(testData.get("mobileBrand"));
        papPage.selectModel(testData.get("mobileModel"));
        papPage.selectOS(testData.get("OS"));
        papPage.selectSims(testData.get("sims"));
        papPage.selectIncludedItem(testData.get("includedItem"));
        papPage.selectPhysicalChecks(testData.get("functionalCheck"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterDescription(testData.get("description"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }
    public void postAdMobileWithSameMinPrice(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        //papPage.enterDescription(testData.get("description"));
        //papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryMobile"));
        papPage.selectSubCategory(testData.get("subCatMobile"));
        papPage.goNextFromPictures();
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(testData.get("price"));
        papPage.selectPurchaseYear(testData.get("yop"));
        papPage.selectOriginalInvoice();
        papPage.selectBrand(testData.get("mobileBrand"));
        papPage.selectModel(testData.get("mobileModel"));
        papPage.selectOS(testData.get("OS"));
        papPage.selectSims(testData.get("sims"));
        papPage.selectIncludedItem(testData.get("includedItem"));
        papPage.selectPhysicalChecks(testData.get("functionalCheck"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterDescription(testData.get("description"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }

    public void postAdElectronicWOMinPrice(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
       // papPage.enterDescription(testData.get("description"));
        //papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryElectronic"));
        papPage.selectSubCategory(testData.get("subCatElectronic"));
        papPage.goNextFromPictures();
        papPage.enterPrice(testData.get("price"));
        papPage.selectProductType(testData.get("Laptop"));
        papPage.selectBrand(testData.get("computerBrand"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterDescription(testData.get("description"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }
    public void postAdHomeWOMinPrice(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        //papPage.enterDescription(testData.get("description"));
        //papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryHome"));
        papPage.selectSubCategory(testData.get("subCatHome"));
        papPage.goNextFromPictures();
        papPage.enterPrice(testData.get("price"));
        papPage.selectFurnitureType(testData.get("furnituretype"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterDescription(testData.get("description"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }

    public void postAdWarehouseElectronic(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        //papPage.enterDescription(testData.get("description"));
        //papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryElectronic"));
        papPage.selectSubCategory(testData.get("subCatElectronic"));
        papPage.goNextFromPictures();
        papPage.enterPrice(testData.get("price"));
        papPage.warehouseSelect();
        papPage.selectProductType(testData.get("Laptop"));
        papPage.selectBrand(testData.get("computerBrand"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterDescription(testData.get("description"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }
    public void postAdWarehouseHome(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        papPage.enterDescription(testData.get("description"));
        papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryHome"));
        papPage.selectSubCategory(testData.get("subCatHome"));
        papPage.enterPrice(testData.get("price"));
        papPage.warehouseSelect();
        papPage.selectFurnitureType(testData.get("furnituretype"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }

    public void postAdElectronicWithMinPrice(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        //papPage.enterDescription(testData.get("description"));
        papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryElectronic"));
        papPage.selectSubCategory(testData.get("subCatElectronic"));
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(testData.get("minprice"));
        papPage.selectProductType(testData.get("Laptop"));
        papPage.selectBrand(testData.get("computerBrand"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterDescription(testData.get("description"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }
    public void postAdHomeWithMinPrice(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        papPage.enterDescription(testData.get("description"));
        papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryHome"));
        papPage.selectSubCategory(testData.get("subCatHome"));
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(testData.get("minprice"));
        papPage.selectFurnitureType(testData.get("furnituretype"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }
    public void postAdElectronicWithSameMinPrice(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        papPage.enterDescription(testData.get("description"));
        papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryElectronic"));
        papPage.selectSubCategory(testData.get("subCatElectronic"));
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(testData.get("price"));
        papPage.selectProductType(testData.get("Laptop"));
        papPage.selectBrand(testData.get("computerBrand"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }
    public void postAdHomeWithSameMinPrice(HashMap<String, String> testData,String sellerEmail,String SellerMobile){
        MEscrowPapPage papPage = new MEscrowPapPage(driver);
        papPage.enterDescription(testData.get("description"));
        papPage.goNextFromPictures();
        papPage.selectCategory(testData.get("categoryHome"));
        papPage.selectSubCategory(testData.get("subCatHome"));
        papPage.enterPrice(testData.get("price"));
        papPage.enterMinPrice(testData.get("price"));
        papPage.selectFurnitureType(testData.get("furnituretype"));
        papPage.enterAddress(testData.get("sellerAddress"));
        papPage.selectLocality(testData.get("sellerLocality"));
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.goNextFromAttributes();
        papPage.enterTitle(testData.get("title"));
        papPage.enterSellerEmail(sellerEmail);
        papPage.enterSellerMobile(SellerMobile);
        papPage.selectMaintainPrivacy();
        papPage.postAd();
    }

    public void makeAnOffer(HashMap<String,String>testData)
    {
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);
        vapPage.mao();
        vapPage.maoPrice(testData.get("priceMAO"));
        vapPage.maoMobile(testData.get("buyerMobile"));
        vapPage.maoMail(testData.get("buyerMail"));
        vapPage.maoCity(testData.get("buyerCity"));
        vapPage.submitOffer();
    }

    public void makeAnOfferElectronic(HashMap<String,String>testData)
    {
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);
        vapPage.mao();
        vapPage.maoPrice(testData.get("priceMAO"));
        vapPage.maoMobile(testData.get("buyerMobile"));
        vapPage.maoMail(testData.get("buyerMail"));
        vapPage.submitOffer();
    }

    public void counterOfferSeller(String offerId,HashMap<String,String>testData,String sellerEncodeEmail,String SellerMobile) {
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);

        vapPage.openCounterOfferURL(offerId,sellerEncodeEmail);
        vapPage.inputCounterOfferPrice(testData.get("counterPrice"));
        vapPage.inputCounterOfferName(testData.get("sellerName"));
        vapPage.inputCounterOfferPincode(testData.get("sellerPincode"));
        vapPage.inputCounterOfferAddress(testData.get("sellerAddress"));
        vapPage.inputCounterOfferMobile(SellerMobile);
        vapPage.submitCounterOffer();
    }

    public void buyNow(HashMap<String,String>testData)
    {
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);
        vapPage.buyNow();
        vapPage.buyNowEmail(testData.get("buyerMail"));
        vapPage.buyNowMobile(testData.get("buyerMobile"));
        vapPage.buyNowCity(testData.get("buyerCity"));
        vapPage.submitBuyNow();
    }
    public void buyNowElectronic(HashMap<String,String>testData)
    {
        MEscrowVapPage vapPage = new MEscrowVapPage(driver);
        vapPage.buyNow();
        vapPage.buyNowEmail(testData.get("buyerMail"));
        vapPage.buyNowMobile(testData.get("buyerMobile"));
        vapPage.submitBuyNow();
    }

    public void makeAnOfferFromSnB(HashMap<String,String>testData)
    {
        MEscrowSnbPage snbPage = new MEscrowSnbPage(driver);
        snbPage.mao();
        snbPage.maoPrice(testData.get("priceMAO"));
        snbPage.maoMobile(testData.get("buyerMobile"));
        snbPage.maoMail(testData.get("buyerMail"));
        snbPage.maoCity(testData.get("buyerCity"));
        snbPage.submitOffer();
    }
    public void makeAnOfferFromSnBElectronic(HashMap<String,String>testData)
    {
        MEscrowSnbPage snbPage = new MEscrowSnbPage(driver);
        snbPage.mao();
        snbPage.maoPrice(testData.get("priceMAO"));
        snbPage.maoMobile(testData.get("buyerMobile"));
        snbPage.maoMail(testData.get("buyerMail"));
        snbPage.submitOffer();
    }

    public void payNowSkipWarranty(HashMap<String,String>testData)
    {
        MEscrowPayNowPage payNowPage = new MEscrowPayNowPage(driver);
        payNowPage.payNowContinueLogin();
        payNowPage.payNowBuyerName(testData.get("buyerName"));
        payNowPage.payNowBuyerPincode(testData.get("buyerPincode"));
        payNowPage.payNowBuyerAddress(testData.get("buyerAddress"));
        payNowPage.payNowBuyerMobile(testData.get("buyerMobile"));
        payNowPage.clickDelivery();
        //payNowPage.skipWarranty();
        payNowPage.selectcardType(testData.get("cardType"));
        payNowPage.inputCardNo(testData.get("cardNo"));
        payNowPage.inputNameOnCard(testData.get("buyerName"));
        payNowPage.inputExpiry(testData.get("expiryDate"));
        payNowPage.inputCvv(testData.get("cvv"));
        payNowPage.clickPayNow();
    }

    public void payNowWithSkipWarranty(HashMap<String,String>testData)
    {
        MEscrowPayNowPage payNowPage = new MEscrowPayNowPage(driver);
        payNowPage.payNowContinueLogin();
        payNowPage.payNowBuyerName(testData.get("buyerName"));
        payNowPage.payNowBuyerPincode(testData.get("buyerPincode"));
        payNowPage.payNowBuyerAddress(testData.get("buyerAddress"));
        payNowPage.payNowBuyerMobile(testData.get("buyerMobile"));
        payNowPage.clickDelivery();
        payNowPage.skipWarranty();
        payNowPage.selectcardType(testData.get("cardType"));
        payNowPage.inputCardNo(testData.get("cardNo"));
        payNowPage.inputNameOnCard(testData.get("buyerName"));
        payNowPage.inputExpiry(testData.get("expiryDate"));
        payNowPage.inputCvv(testData.get("cvv"));
        payNowPage.clickPayNow();
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
