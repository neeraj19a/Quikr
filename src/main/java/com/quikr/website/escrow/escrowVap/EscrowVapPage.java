package com.quikr.website.escrow.escrowVap;

import com.quikr.website.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 30/12/15.
 */
public class EscrowVapPage extends PageBase {
    public EscrowVapPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ESCROW_VAP_DOM_FILE");

    public boolean ifAdSelected() {
        Mapper.waitForElementToBeVisible(domFile, "adTitle",25);
        if (Mapper.find(domFile, "adTitle").isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void selectMakeAnOffer() {
        try {
            Thread.sleep(5000);
            Mapper.waitForElementToBeVisible(domFile, "maoButton");
            Mapper.waitForElementToBeClickable(domFile, "maoButton");
            Actions action = Mapper.getActionObject();
            action.moveToElement(Mapper.find(domFile, "maoButton")).click();
            action.perform();
    }catch(Exception e){
        Mapper.find(domFile, "maoButton").click();
    }


    }

    public void selectBuyNow() {
        try{
        Thread.sleep(3000);
        Mapper.waitForElementToBeVisible(domFile, "buynowButton");
        Mapper.waitForElementToBeClickable(domFile, "buynowButton");
        Actions action = Mapper.getActionObject();
        action.moveToElement(Mapper.find(domFile, "buynowButton")).click();
        action.perform();
        }catch(Exception e){
            Mapper.find(domFile, "buynowButton").click();
        }

    }

    public List<WebElement> buttononVap() {
        Mapper.waitForElementToBeVisible(domFile, "buynowButton");
        List<WebElement> list = Mapper.finds(domFile, "buynowButton");
        return list;

    }

    public boolean isMAOOpen() {
        sleep(2000);
        Mapper.waitForElementToBeVisible(domFile, "maoPopUpHeader",20);
        if (Mapper.find(domFile, "maoPopUpHeader").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean isBuyNowOpen() {
        Mapper.waitForElementToBeVisible(domFile, "maoPopUpHeader",30);
        if (Mapper.find(domFile, "maoPopUpHeader").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean isBuyNowOpenSnB() {
        Mapper.waitForElementToBeVisible(domFile, "buyNowPopUpHeader",30);
        if (Mapper.find(domFile, "buyNowPopUpHeader").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void maoPrice(String price) {
        Mapper.waitForElementToBeVisible(domFile, "offerPriceTextfield");
        Mapper.find(domFile, "offerPriceTextfield").sendKeys(price);
    }

    public void maoMail(String email) {
        Mapper.find(domFile, "offerEmailTextfield").sendKeys(email);
    }

    public void maoMobile(String mobile) {
        Mapper.find(domFile, "offerMobileTextfield").sendKeys(mobile);
    }

    public void maoCity(String city) {
        try{
        if (Mapper.find(domFile, "offerCityTextfield").isDisplayed()) {
            Mapper.find(domFile, "offerCityTextfield").sendKeys(city);
            Mapper.find(domFile, "offerCitySuggestion").click();
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    }

    public boolean isMAOOpenSnB() {
        sleep(2000);
        Mapper.waitForElementToBeVisible(domFile, "makeAnOfferSnB",20);
        if (Mapper.find(domFile, "makeAnOfferSnB").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void maoPriceSnB(String price) {
        Mapper.waitForElementToBeVisible(domFile, "offerPriceTextfieldSnB");
        Mapper.find(domFile, "offerPriceTextfieldSnB").sendKeys(price);
    }

    public void maoMailSnB(String email) {
        Mapper.find(domFile, "offerEmailTextfieldSnB").sendKeys(email);
    }

    public void maoMobileSnB(String mobile) {
        Mapper.find(domFile, "offerMobileTextfieldSnB").sendKeys(mobile);
    }

    public void maoCitySnB(String city) {
        try{
            if (Mapper.find(domFile, "offerCityTextfieldSnB").isDisplayed()) {
                Mapper.find(domFile, "offerCityTextfieldSnB").sendKeys(city);
                Mapper.find(domFile, "offerCitySuggestion").click();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void submitOfferSnB() {
        Actions ele=Mapper.getActionObject();
        ele.moveToElement(Mapper.find(domFile, "sendOfferButtonBuyNow"));
        Mapper.find(domFile, "sendOfferButtonBuyNow").click();
    }



    public void submitOffer() {
       Actions ele=Mapper.getActionObject();
        ele.moveToElement(Mapper.find(domFile, "sendOfferButton"));
        Mapper.find(domFile, "sendOfferButton").click();
    }

    public boolean isOfferAutoAccepted() {
        Mapper.waitForElementToBeVisible(domFile, "offerAcceptedButton");
        if (Mapper.find(domFile, "offerAcceptedButton").isDisplayed()) {
            return true;
        } else return false;
    }

    public void selectBuyNowAutoAcceptButton() {
        Actions action = Mapper.getActionObject();
        action.moveToElement(Mapper.find(domFile, "offerAcceptedButton")).click();
        //Mapper.doJavascriptOnElement("jQuery('#buyNowOfferAutoAccept').click();");
       // Mapper.find(domFile, "offerAcceptedButton").click();
        action.perform();

    }

    public void closeDialouge(){
        Mapper.waitForElementToBeVisible(domFile, "buttonClose");
        Mapper.find(domFile, "buttonClose").click();
    }


    public void buyNowMail(String email) {
        Mapper.find(domFile, "offerEmailTextfield").clear();
        Mapper.find(domFile, "offerEmailTextfield").sendKeys(email);
    }

    public void buyNowMobile(String mobile) {
        Mapper.find(domFile, "offerMobileTextfield").clear();
        Mapper.find(domFile, "offerMobileTextfield").sendKeys(mobile);
    }

    public void buyNowCity(String city) {
        Mapper.find(domFile, "offerCityTextfield").clear();
        Mapper.find(domFile, "offerCityTextfield").sendKeys(city);
        Mapper.find(domFile, "offerCitySuggestion").click();
    }

    public void submitBuyNow() {
        Mapper.waitForElementToBeVisible(domFile, "sendOfferButton");
        Mapper.waitForElementToBeClickable(domFile, "sendOfferButton");
        Mapper.find(domFile, "sendOfferButton").click();
        //Mapper.waitForElementToBeVisible(domFile, "letscompleteorder",20);
    }

    public void buyNowMailSnB(String email) {
        Mapper.find(domFile, "offerEmailTextfieldSnB").clear();
        Mapper.find(domFile, "offerEmailTextfieldSnB").sendKeys(email);
    }

    public void buyNowMobileSnB(String mobile) {
        Mapper.find(domFile, "offerMobileTextfieldSnB").clear();
        Mapper.find(domFile, "offerMobileTextfieldSnB").sendKeys(mobile);
    }

    public void buyNowCitySnB(String city) {
        Mapper.find(domFile, "offerCityTextfieldSnB").clear();
        Mapper.find(domFile, "offerCityTextfieldSnB").sendKeys(city);
        Mapper.find(domFile, "offerCitySuggestion").click();
    }





    public boolean isOfferSent() {
        Mapper.waitForElementToBeVisible(domFile, "offerSentMessage",20);
        if (Mapper.find(domFile, "offerSentMessage").isDisplayed()) {
            Mapper.find(domFile, "closeOfferSuccessButton").click();
            return true;
        } else
            return false;
    }

    public boolean isOfferSentAccept() {
        Mapper.waitForElementToBeVisible(domFile, "offerSentMessageAccept",20);
        if (Mapper.find(domFile, "offerSentMessageAccept").isDisplayed()) {
            //Mapper.find(domFile, "closeOfferSuccessButton").click();
            return true;
        } else
            return false;
    }

    public boolean counterOfferLessThanOffer() {
        Mapper.waitForElementToBeVisible(domFile, "counterOfferLess");
        if (Mapper.find(domFile, "counterOfferLess").isDisplayed()) {
           return true;
        } else
            return false;
    }

    public boolean buyerSellerSame() {
        Mapper.waitForElementToBeVisible(domFile, "buyerSellerSame",20);
        if (Mapper.find(domFile, "buyerSellerSame").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean isOfferSentSnB() {
        Mapper.waitForElementToBeVisible(domFile, "offerSentMessageSnB",20);
        if (Mapper.find(domFile, "offerSentMessageSnB").isDisplayed()) {
            Mapper.find(domFile, "closeOfferSuccessButtonSnB").click();
            return true;
        } else
            return false;
    }

    public void closeOfferSuccessMsg() {
        Mapper.find(domFile, "closeOfferSuccessButton").click();
    }

    public boolean isAdSold() {
        return isElementPresent("adSold");
    }

    public boolean isMAONotPresent() {
        try {
            Mapper.find(domFile, "maoButton");
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public boolean isBuyNowPresent() {
        if (Mapper.find(domFile, "buynowButton").isDisplayed()) {
            return true;
        } else return false;
    }

    public void acceptOfferSeller(String offerId,String adid,String pass,String sellername,String encodeEmail) {

        openAcceptOfferURL(offerId,adid,pass,encodeEmail);
        Mapper.waitForElementToBeVisible(domFile,"offerAccepteName");
        Mapper.find(domFile,"offerAccepteName").click();
        Mapper.find(domFile,"offerAccepteName").clear();
        Mapper.find(domFile,"offerAccepteName").sendKeys(sellername);
        Mapper.waitForElementToBeVisible(domFile, "submitUserDetails");
        Mapper.find(domFile, "submitUserDetails").click();
        Mapper.waitForElementToBeVisible(domFile, "OfferAccepted",20);

    }

    public void acceptOfferSellerProd(String offerId,String adid,String sellername) {
        String []offerid=null;
       openAcceptOfferURLProd(offerId,adid);
       /* Mapper.waitForElementToBeVisible(domFile,"offerAcceptButton");
        Mapper.find(domFile,"offerAcceptButton").click();
        Mapper.waitForElementToBeVisible(domFile, "offerAcceptedMessage",20);*/
        Mapper.waitForElementToBeVisible(domFile,"offerAcceptedButtonMyOffer",new String[]{offerId});
        Mapper.findAndReplace(domFile,"offerAcceptedButtonMyOffer",new String[]{offerId}).click();
       /* Mapper.findAndReplace(domFile,"offerAccepteName",new String[]{offerId,offerId,offerId}).clear();
        Mapper.find(domFile,"offerAccepteName").sendKeys(sellername);
        Mapper.waitForElementToBeVisible(domFile, "submitUserDetails");
        Mapper.find(domFile, "submitUserDetails").click();*/
       // Mapper.waitForElementToBeVisible(domFile,"offerAcceptedMessage",new String[]{offerId});

    }

    public void inputCounterOfferPrice(String price) {
        Mapper.waitForElementToBeVisible(domFile, "counterOfferButton");
        Mapper.find(domFile, "counterOfferButton").click();
        Mapper.waitForElementToBeVisible(domFile, "counterOfferPrice");
        Mapper.find(domFile, "counterOfferPrice").sendKeys(price);
    }

    public void inputCounterOfferPriceMyOffer(String price,String offerId) {
        Mapper.waitForElementToBeVisible(domFile, "counterOfferButton",new String[]{offerId});
        Mapper.findAndReplace(domFile, "counterOfferButton",new String[]{offerId}).click();
        Mapper.waitForElementToBeVisible(domFile, "counterOfferPriceMyOffer",new String[]{offerId});
        Mapper.findAndReplace(domFile, "counterOfferPriceMyOffer",new String[]{offerId}).sendKeys(price);
        Mapper.waitForElementToBeVisible(domFile, "counterOfferButtonAfterPrice",new String[]{offerId});
        Mapper.findAndReplace(domFile, "counterOfferButtonAfterPrice",new String[]{offerId}).click();
        Mapper.waitForElementToBeVisible(domFile, "counterOfferButtonNegotiate",new String[]{offerId});
    }

    public void inputCounterOfferName(String name) {
        Mapper.find(domFile, "counterOfferName").clear();
        Mapper.find(domFile, "counterOfferName").sendKeys(name);
    }

    public void inputCounterOfferPincode(String pincode) {
        Mapper.find(domFile, "counterOfferPincode").clear();
        Mapper.find(domFile, "counterOfferPincode").sendKeys(pincode);
    }

    public void inputCounterOfferAddress(String address) {
        Mapper.find(domFile, "counterOfferAddress").clear();
        Mapper.find(domFile, "counterOfferAddress").sendKeys(address);
    }

    public void inputCounterOfferMobile(String mobile) {
        Mapper.find(domFile, "counterOfferMobile").clear();
        Mapper.find(domFile, "counterOfferMobile").sendKeys(mobile);
    }

    public void submitCounterOffer() {
        Mapper.waitForElementToBeVisible(domFile, "counterOfferSubmitButton");
        //Mapper.waitForElementToBeClickable(domFile, "counterOfferSubmitButton");
        Mapper.find(domFile, "counterOfferSubmitButton").click();
        Mapper.waitForElementToBeVisible(domFile,"counterOfferSubmitted",20);
    }

//    public void acceptOfferBuyer(String offerId){
//        openTab();
//        switchTab();
//        openAcceptOfferURL(offerId);
//        Mapper.find(domFile,"submitUserDetails").click();
//        Mapper.waitForElementToBeVisible(domFile,"");
//
//        closeTab();
//        switchTab();
//    }

    /*public void switchTab() {
        Set<String> handles = getWindowHandles();
        List<String> handlesList = new ArrayList<String>(handles);
        switchTo().window(handlesList.get(0));
    }*/

    public void openAcceptOfferURLProd(String offerId,String adid) {
        navigateTo().to("http://bangalore.quikr.com/Escrow/MyOffers/viewAllOffers?action_type=accept&adId="+adid+"&offerId="+offerId+"&email=cWtyc2VsbGVyQGdtYWlsLmNvbQ==&pass=3bc7ac44f9f22f3492d5f1b2b50cbc23&channel=EMAIL");
        System.out.println("http://bangalore.quikr.com/Escrow/MyOffers/actionOnOffer?action_type=accept&adId="+adid+"&offerId="+offerId+"&email=cWtyc2VsbGVyQGdtYWlsLmNvbQ==&pass=3bc7ac44f9f22f3492d5f1b2b50cbc23&channel=EMAIL");
    }

    public void openCounterOfferURLProd(String offerId,String adid) {
        navigateTo().to("http://bangalore.quikr.com/Escrow/MyOffers/viewAllOffers?action_type=counter&adId="+adid+"&offerId="+offerId+"&email=cWtyc2VsbGVyQGdtYWlsLmNvbQ==&pass=3bc7ac44f9f22f3492d5f1b2b50cbc23&channel=EMAIL");
    }

    public void openAcceptOfferURL(String offerId,String adid,String pass,String email) {
        navigateTo().to("http://bangalore.quikr.com/Escrow/MyOffers/actionOnOffer?action_type=accept&adId="+adid+"&offerId="+offerId+"&email="+email+"&pass="+pass+"&channel=EMAIL");
       System.out.println("http://bangalore.quikr.com/Escrow/MyOffers/actionOnOffer?action_type=accept&adId="+adid+"&offerId="+offerId+"&email="+email+"&pass="+pass+"&channel=EMAIL");
    }

    public void openCounterOfferURL(String offerId,String adid,String pass,String email) {
        navigateTo().to("http://bangalore.quikr.com/Escrow/MyOffers/actionOnOffer?action_type=counter&adId="+adid+"&offerId="+offerId+"&email="+email+"&pass="+pass+"&channel=EMAIL");
    }

   /* public void openTab() {
        Mapper.find(domFile, "body").sendKeys(Keys.CONTROL + "t");
    }

    public void closeTab() {
        Mapper.find(domFile, "body").sendKeys(Keys.CONTROL + "w");
    }*/


    public void userProfileLink(){
        //navigateTo().to("http://bangalore.quikr.com/userProfile?uId=VjVTMgU3WGMEMlZkBWYAZQdh");
        sleep(3000);
    Mapper.scrollElementIntoView(Mapper.find(domFile,"userProfileLink"));
    Mapper.waitForElementToBeVisible(domFile,"userProfileLink",20);
    Mapper.scrollVerticallWithCords(0,350);
    Mapper.find(domFile,"userProfileLink").click();
    }

    public boolean isUserProfileImg(){
        sleep(3000);
        Mapper.waitForElementToBeVisible(domFile,"userProfileimg");
        if (Mapper.find(domFile, "userProfileimg").isDisplayed()) {
            return true;
        } else return false;
    }

    public void userProfileButton(String value){
        Mapper.waitForElementToBeVisible(domFile,"userProfileButtons");
        List<WebElement> buttons=Mapper.finds(domFile,"userProfileButtons");
        sleep(3000);
        for(int i=1;i<buttons.size();i++){
            String btnVal=buttons.get(i).getAttribute("value");
            if(btnVal.contentEquals(value)){
                buttons.get(i).click();
                break;
            }
        }
    }


public int findOfferPrice(){
    Mapper.waitForElementToBeVisible(domFile, "userProfileButtons1");
    String price=Mapper.find(domFile, "userProfileButtons1").getText().replaceAll("\\D+", "").toString();
    int finalPrice=(Integer.parseInt(price)/2)+100;
    System.out.println(price + finalPrice);
    return finalPrice;
}

    public String iVRStatus(){
        WebDriver.Navigation e =navigateTo();
        e.to("http://etsdom.kapps.in/webapi/quikr/api/quikr_entp_4448_obd.py?auth_key=6bd85802-595f-11e5-a50e-06b74f9e7768&customer_number=+919030350824&msg_type=1&offer_amount=1900&sub_category=Home+-+Office+Furniture&counter_price=null&ad_id=270121947");
        return Mapper.find(domFile,"successIVR").getText();
    }

    public void sellerBuyerLogin(String email,String pwd){
        Mapper.waitForElementToBeVisible(domFile,"signInButtonResponsiveHP",20);
        Mapper.find(domFile, "signInButtonResponsiveHP").click();
        Mapper.waitForElementToBeVisible(domFile, "emailId");
        Mapper.find(domFile, "emailId").sendKeys(email);
        Mapper.find(domFile, "password").click();
        Mapper.find(domFile, "password").sendKeys(pwd);
        Mapper.find(domFile,"submitButtonNewUI").click();
        Mapper.waitForElementToBeVisible(domFile,"myOffer",25);
    }
    public void sellerMyOfferTab(){
        Actions a=null;
        Mapper.find(domFile,"myOffer").click();
        Mapper.waitForElementToBeVisible(domFile,"sellerTabMyOffer",25);
        try{
            Mapper.waitForElementToBeVisible(domFile,"viewOffer");
        }catch (Exception e){
            Mapper.find(domFile,"sellerTabMyOffer").click();
            Mapper.waitForElementToBeVisible(domFile,"viewOffer",20);
        }
        List<WebElement> adList= Mapper.finds(domFile,"totalAds");
        if(adList.size()>0) {
            a = Mapper.getActionObject();
            a.moveToElement(Mapper.find(domFile, "viewOffer")).perform();
            Mapper.find(domFile, "viewOffer").click();
        }
        Mapper.waitForElementToBeVisible(domFile,"acceptOffer",25);
            Mapper.waitForElementToBeClickable(domFile,"acceptOffer");
                Mapper.find(domFile,"acceptOffer").click();
        try{
            Mapper.find(domFile,"acceptOffer").click();
        }catch(Exception e){
            Mapper.waitForElementToBeVisible(domFile,"acceptOfferLanding",25);
        }
        Mapper.waitForElementToBeVisible(domFile,"acceptOfferLanding",25);
        WebDriver.Navigation e =navigateTo();
        e.back();
        Mapper.waitForElementToBeVisible(domFile,"counterOffer",25);
            Mapper.waitForElementToBeClickable(domFile,"counterOffer");

        Mapper.find(domFile,"counterOffer").click();
            //Mapper.find(domFile,"acceptOffer").click();
            try{
                Mapper.find(domFile,"counterOffer").click();
            }catch(Exception b){
                Mapper.waitForElementToBeVisible(domFile,"counterOfferLanding",25);

            }
        Mapper.waitForElementToBeVisible(domFile,"counterOfferLanding",25);
        e.back();
        Mapper.waitForElementToBeVisible(domFile,"counterOffer",25);

    }

    public void buyerMyOfferTab() {
        Actions a = null;
        Mapper.find(domFile, "myOffer").click();
        Mapper.waitForElementToBeVisible(domFile, "buyerTabMyOffer", 25);
        Mapper.find(domFile, "buyerTabMyOffer").click();
    }

    public boolean buyerAdDisplay() {
        Mapper.waitForElementToBeVisible(domFile, "buyerAds");
        if (Mapper.find(domFile, "buyerAds").isDisplayed()){
            return true;
           } else
            return false;
    }

    public boolean counterOfferText() {
        Mapper.waitForElementToBeVisible(domFile, "counterOfferTextBuyer");
        if (Mapper.find(domFile, "counterOfferTextBuyer").isDisplayed()){
            return true;
        } else
            return false;
    }

    public boolean offerAcceptedText() {
        Mapper.waitForElementToBeVisible(domFile, "acceptOfferTextBuyer");
        if (Mapper.find(domFile, "acceptOfferTextBuyer").isDisplayed()){
            return true;
        } else
            return false;
    }

      public void payNowBuyerOffer() {
          Mapper.waitForElementToBeVisible(domFile, "payNowButton",25);
          Mapper.find(domFile, "payNowButton").click();
      }

    public boolean payNowLandingPage() {
        Mapper.waitForElementToBeVisible(domFile, "payNowLandingPage",25);
        if (Mapper.find(domFile, "payNowLandingPage").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean landingBackToBuyTab() {
        WebDriver.Navigation e = navigateTo();
        //e.back();
        try {
            e.back();
            Mapper.waitForElementToBeVisible(domFile, "payNowButton");
        }catch(Exception b){
            e.back();
        }
        Mapper.waitForElementToBeVisible(domFile, "payNowButton");
        if (Mapper.find(domFile, "payNowButton").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void mobileVerification(String mobile) {
        Mapper.waitForElementToBeVisible(domFile, "MobileVerificationPayment");
        Mapper.find(domFile, "MobileVerificationPayment").click();
        Mapper.find(domFile, "MobileVerificationPayment").sendKeys(mobile);
        Mapper.find(domFile,"MobileVerificationPaymentSubmit").click();

    }

    public void chatWindow(String mobile){

        Mapper.waitForElementToBeVisible(domFile, "chatIcon");
        Actions a=Mapper.getActionObject();
        a.moveToElement(Mapper.find(domFile, "chatIcon"));
        Mapper.find(domFile, "chatIcon").click();
        try{
            Mapper.find(domFile, "chatIcon").click();
        }catch (Exception e){

        }
        Mapper.waitForElementToBeVisible(domFile,"charIconInputField");
        Mapper.find(domFile,"charIconInputField").click();
        Mapper.find(domFile,"charIconInputField").sendKeys("HelloBuyer");
        Mapper.find(domFile,"charIconInputField").sendKeys(Keys.ENTER);
        Mapper.waitForElementToBeClickable(domFile,"paymentFromChat");
        Mapper.find(domFile,"paymentFromChat").click();
        mobileVerification(mobile);
        payNowLandingPage();
        WebDriver.Navigation e = navigateTo();
        e.back();
        try {
            e.back();
            Mapper.waitForElementToBeVisible(domFile,"paymentFromChat");

        }catch(Exception b){
            e.back();
        }
       Mapper.waitForElementToBeVisible(domFile,"paymentFromChat");
    }


    public boolean placeBidLabel() {
        Mapper.waitForElementToBeVisible(domFile, "placeBidLabel");
        if (Mapper.find(domFile, "placeBidLabel").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean lastBidLabel() {
        Mapper.waitForElementToBeVisible(domFile, "lastBidLabel");
        if (Mapper.find(domFile, "lastBidLabel").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean auctionLabel() {
        Mapper.waitForElementToBeVisible(domFile, "auctionLabel");
        if (Mapper.find(domFile, "auctionLabel").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public String nextBidVal() {

        Mapper.waitForElementToBeVisible(domFile, "inputBid");
        return Mapper.find(domFile, "inputBid").getAttribute("value");

    }

    public boolean checkInvalidBid(String invalidBid){
        Mapper.waitForElementToBeVisible(domFile, "inputBid");
        Mapper.find(domFile, "inputBid").click();
        Mapper.find(domFile, "inputBid").clear();
        Mapper.find(domFile, "inputBid").sendKeys(invalidBid);
        Mapper.find(domFile, "bidButtonVap").click();
        Mapper.waitForElementToBeVisible(domFile,"invalidBidError",25);
        if (Mapper.find(domFile,"invalidBidError").isDisplayed()) {
            return true;
        } else
            return false;
    }


    public void bidNowClickVap(String nextBidVal){
        Mapper.find(domFile, "inputBid").click();
        Mapper.find(domFile, "inputBid").clear();
        Mapper.find(domFile, "inputBid").sendKeys(nextBidVal);
        Mapper.find(domFile, "bidButtonVap").click();

/*
        Mapper.waitForElementToBeVisible(domFile,"bidSuccessfull",25);
        if (Mapper.find(domFile,"bidSuccessfull").isDisplayed()) {
            return true;
        } else
            return false;*/
    }



    public void submitBid(String nextBidval,String email,String mobile){
        Mapper.waitForElementToBeVisible(domFile,"bidPriceTextfield");
        /*Mapper.find(domFile,"bidPriceTextfield").click();
        Mapper.find(domFile,"bidPriceTextfield").clear();
        Mapper.find(domFile,"bidPriceTextfield").sendKeys(nextBidval);*/
        Mapper.find(domFile,"bidderEmailTextfield").click();
        Mapper.find(domFile,"bidderEmailTextfield").clear();
        Mapper.find(domFile,"bidderEmailTextfield").sendKeys(email);
        Mapper.find(domFile,"bidderMobileTextfield").click();
        Mapper.find(domFile,"bidderMobileTextfield").clear();
        Mapper.find(domFile,"bidderMobileTextfield").sendKeys(mobile);
        Mapper.find(domFile,"bidderCityTextfield").click();
        Mapper.find(domFile,"bidderCityTextfield").clear();
        Mapper.find(domFile,"bidderCityTextfield").sendKeys("Pune");
        Mapper.find(domFile,"bidderCitySuggestion").click();
        Mapper.find(domFile,"placeBid").click();
        /*Mapper.waitForElementToBeVisible(domFile,"bidClose");
        Mapper.find(domFile,"bidClose").click()*/;
    }
public boolean bidSuccessfull(){
    Mapper.waitForElementToBeVisible(domFile,"bidSuccessfull",25);
    if (Mapper.find(domFile,"bidSuccessfull").isDisplayed()) {
        return true;
    } else
        return false;
}



}
