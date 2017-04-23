package com.quikr.website.escrow.escrowPayNow;

import com.quikr.website.PageBase;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 7/1/16.
 */
public class EscrowPayNowPage extends PageBase {
    public EscrowPayNowPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ESCROW_PAYNOW_DOM_FILE");

    public void openPayNowURLProd(String offerId) {
        //navigateTo().to("http://bangalore.quikr.com/escrowBuyer?offerId=" + offerId);
        navigateTo().to("http://secure.quikr.com/escrowBuyer?offerId="+offerId+"&pass=61d8d0e39138245dfe3703822277fd80&channel=EMAIL&noredir=true");

    }

    public void openPayNowURL(String offerId,String pass) {
        //navigateTo().to("http://bangalore.quikr.com/escrowBuyer?offerId=" + offerId);
        navigateTo().to("http://secure.quikr.com/escrowBuyer?offerId="+offerId+"&pass="+pass+"&channel=EMAIL&noredir=true");

    }

    public void deliveryFeedBackSellerURL(String offerId) {
        navigateTo().to("http://bangalore.quikr.com/Escrow/DeliveryFeedback?user=seller&offerId=" + offerId);
    }

    public void deliveryFeedBackBuyerURL(String offerId) {
        navigateTo().to("http://bangalore.quikr.com/Escrow/DeliveryFeedback?user=buyer&offerId=" + offerId);
    }

    public boolean isDoorFeedbackHeader() {
        Mapper.waitForElementToBeVisible(domFile, "doorFeedBackHeader");
        return Mapper.find(domFile, "doorFeedBackHeader").isDisplayed();
    }

    public String deliveryFeedBackAdId() {
        Mapper.waitForElementToBeVisible(domFile, "verifyAdId");

        return Mapper.find(domFile, "verifyAdId").getText().replaceAll("[\\D]","");
    }
    public void itemPickedUpYes() {
        Mapper.waitForElementToBeVisible(domFile, "itemPickedYes");
        Mapper.find(domFile, "itemPickedYes").click();
    }

    public void itemPickedUpNo() {
        Mapper.waitForElementToBeVisible(domFile, "itemPickedNo");
        Mapper.find(domFile, "itemPickedNo").click();
    }

    public void itemPickedOnTimeYes() {
        Mapper.waitForElementToBeVisible(domFile, "itemPickedOnTimeYes");
        Mapper.find(domFile, "itemPickedOnTimeYes").click();
    }

    public void itemPickedOnTimeNo() {
        Mapper.waitForElementToBeVisible(domFile, "itemPickedOnTimeNo");
        Mapper.find(domFile, "itemPickedOnTimeNo").click();
    }

    public boolean feedBackShared() {
        Mapper.waitForElementToBeVisible(domFile, "feedBackSuccessful");
        return Mapper.find(domFile, "feedBackSuccessful").isDisplayed();
    }

    public void feedBackComment() {
        Mapper.waitForElementToBeVisible(domFile, "feedBackComment");
        Mapper.find(domFile, "feedBackComment").sendKeys("Hello");
    }

    public void feedBackSubmit() {
        Mapper.waitForElementToBeVisible(domFile, "feedBackSubmit");
        Mapper.find(domFile, "feedBackSubmit").click();
    }

    public String feedBackSubmittedMessage() {
        Mapper.waitForElementToBeVisible(domFile, "feedBackSubmitMessage");
        return Mapper.find(domFile, "feedBackSubmitMessage").getText();
    }


    public boolean priceCheck(String amount,String mobile) {
        mobileVerification(mobile);
        System.out.println("Netpayable amount== " + Mapper.find(domFile, "netPayableAmount").getText().replaceAll("\\D+", ""));
        if (Mapper.find(domFile, "netPayableAmount").getText().replaceAll("\\D+", "").equals(amount)) {
            return true;
        } else return false;
    }

    public void payNowContinueLogin() {
        Mapper.waitForElementToBeVisible(domFile, "ContinueLoginButton", 20);
        Mapper.waitForElementToBeClickable(domFile, "ContinueLoginButton");
        Mapper.find(domFile, "ContinueLoginButton").click();
    }

    public void mobileVerification(String mobile) {
        Mapper.waitForElementToBeVisible(domFile, "MobileVerificationPayment");
        Mapper.find(domFile, "MobileVerificationPayment").click();
         Mapper.find(domFile, "MobileVerificationPayment").sendKeys(mobile);
        Mapper.find(domFile,"MobileVerificationPaymentSubmit").click();

    }

    public void payNowBuyerName(String buyerName) {
            //sleep(3000);
            Mapper.waitForElementToBeVisible(domFile, "RecieverName",25);
            Mapper.find(domFile, "RecieverName").clear();
            Mapper.find(domFile, "RecieverName").sendKeys(buyerName);


    }

    public void payNowBuyerAddress(String buyerAddress) {
        Mapper.find(domFile, "Address").clear();
        Mapper.find(domFile, "Address").sendKeys(buyerAddress);
    }

    public void payNowBuyerPincode(String buyerPincode) {
        Mapper.find(domFile, "Pincode").clear();
        Mapper.find(domFile, "Pincode").sendKeys(buyerPincode);
    }

    public void payNowBuyerMobile(String buyerMobile) {
        Mapper.find(domFile, "MobileNo").click();
        Mapper.find(domFile, "MobileNo").clear();
        Mapper.find(domFile, "MobileNo").sendKeys(buyerMobile);
    }

    public void clickDelivery() {
        Mapper.find(domFile, "DeliverButton").click();
    }

    public void clickPayNow() {

        //Mapper.waitForElementToBeClickable(domFile, "PayNow");
        Mapper.find(domFile, "PayNow").click();
        try {
            Actions a = Mapper.getActionObject();
            a.moveToElement(Mapper.find(domFile, "PayNow")).click().perform();
            }catch(Exception e){
            //Mapper.find(domFile, "PayNow").click();
        }
    }

    public boolean isTransactionSuccessfull() {
        Mapper.waitForElementToBeVisible(domFile, "TransactionSuccessMsg",25);
        if (Mapper.find(domFile, "TransactionSuccessMsg").getText().equalsIgnoreCase("Your transaction is successful")) {
            return true;
        } else return false;
    }

    public boolean checkTransactionStatusInProd() {
        Mapper.waitForElementToBeVisible(domFile, "TransactionSuccessMsg",30);
        if (Mapper.find(domFile, "TransactionSuccessMsg").getText().equalsIgnoreCase("Payment failed")) {
            return true;
        } else return false;
    }

    public String getTransactionId() {
        //this wait is not for below Transaction ID instead putting an extra time wait to get Sold tag on SnB page.
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }

        return Mapper.find(domFile, "TransactionId").getText();
    }

    public void skipWarranty() {
        Mapper.find(domFile, "SkipOnsite").click();
    }


    public void selectcardType(String cardType) {
                //sleep(1000);
                Mapper.waitForElementToBeVisible(domFile, "CardTypeDropdown");
        Actions a = Mapper.getActionObject();
        a.moveToElement(Mapper.find(domFile, "PayNow")).click().perform();
                new Select(Mapper.find(domFile, "CardTypeDropdown")).selectByValue(cardType);
    }

    public void inputCardNo(String cardNo) {
        sendKeysSlow("cardNo", cardNo);
    }

    public void inputNameOnCard(String nameOnCard) {
        Mapper.waitForElementToBeClickable(domFile, "NameOnCard");
        Mapper.find(domFile, "NameOnCard").click();
        Mapper.find(domFile, "NameOnCard").sendKeys(nameOnCard);
        Mapper.find(domFile, "NameOnCard").click();
    }

    public void inputExpiry(String expiryDate) {
        sendKeysSlow("ExpiryDate", expiryDate);
    }

    public void inputCvv(String Cvv) {
        sendKeysSlow("Cvv", Cvv);
    }

    public void sendKeysSlow(String element, String Keys) {
        for (char c : Keys.toCharArray()) {
            String ch = "" + c;
            Mapper.find(domFile, element).sendKeys(ch);

        }
    }
}
