package com.quikr.msite.mEscrow.mEscrowPayNow;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 5/2/16.
 */
public class MEscrowPayNowPage extends MPageBase {

    public static final String domFile = getProperties().get("mEscrow_PAYNOW_DOM_FILE");

    public MEscrowPayNowPage(AppiumDriver driver) {
        super(domFile, driver);
    }


    public void openPayNowURL(String offerId) {
        navigateTo().to("http://bangalore.quikr.com/escrowBuyer?offerId=" + offerId);
    }

    public boolean priceCheck(String amount) {
        Mapper.waitForElementToBeVisible(domFile,"netPayableAmount",20);
        Actions action=Mapper.getActionObject();
        action.moveToElement(Mapper.find(domFile,"netPayableAmount"));
        System.out.println("Netpayable amount== " + Mapper.find(domFile, "netPayableAmount").getText().replaceAll("\\D+", ""));
        if (Mapper.find(domFile, "netPayableAmount").getText().replaceAll("\\D+", "").equals(amount)) {
            return true;
        } else return false;
    }

    public void payNowContinueLogin() {
        Mapper.find(domFile, "ContinueLoginButton").click();
    }

    public void payNowBuyerName(String buyerName) {
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
        Mapper.find(domFile, "PayNow").click();
    }

    public boolean isTransactionSuccessfull() {

        Mapper.waitForElementToBeVisible(domFile, "TransactionSuccessMsg");
        if (Mapper.find(domFile, "TransactionSuccessMsg").getText().equalsIgnoreCase("Your transaction is successful")) {
            return true;
        } else return false;
    }

    public String getTransactionId() {
        return Mapper.find(domFile, "TransactionId").getText();
    }

    public void skipWarranty() {
        Mapper.find(domFile, "SkipOnsite").click();
    }


    public void selectcardType(String cardType) {
        Mapper.scrollElementIntoView(domFile,"CardTypeDropdown");
        Mapper.waitForElementToBeVisible(domFile, "CardTypeDropdown",10);
        Select Year  =new Select(Mapper.find(domFile,"CardTypeDropdown"));
        Year.selectByValue("debit");
    }

    public void inputCardNo(String cardNo) {
        sendKeysSlow("cardNo", cardNo);
    }

    public void inputNameOnCard(String nameOnCard) {
        Mapper.waitForElementToBeClickable(domFile, "NameOnCard");
        Mapper.find(domFile, "NameOnCard").click();
        Mapper.find(domFile, "NameOnCard").sendKeys(nameOnCard);
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
