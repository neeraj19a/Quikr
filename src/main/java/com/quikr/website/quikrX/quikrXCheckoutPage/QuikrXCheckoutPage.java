package com.quikr.website.quikrX.quikrXCheckoutPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Cliford D'souza (cdsouza@quikr.com) on 29/9/15.
 */
public class QuikrXCheckoutPage extends PageBase
{
    public QuikrXCheckoutPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("QUIKRXCHECKOUTPAGE_DOM_FILE");

    /**
     * Function for validation of delivery details
     */
    public boolean validateDeliveryDetails()
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        if (!(Mapper.find(domFile, "fullName").isDisplayed()) || !(Mapper.find(domFile, "pinCode").isDisplayed()) || !(Mapper.find(domFile, "address").isDisplayed()) || !(Mapper.find(domFile, "phoneNumber").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of delivery details
     */
    public boolean validateDeliveryFields()
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        Mapper.find(domFile, "continueButtonDeliveryDetails").click();
        if (!(Mapper.find(domFile, "errorOnfullName").isDisplayed()) || !(Mapper.find(domFile, "errorOnpinCode").isDisplayed()) || !(Mapper.find(domFile, "errorOnaddress").isDisplayed()) || !(Mapper.find(domFile, "errorOnphoneNumber").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of phone and pincode in delivery details
     */
    public boolean validatePhoneAndPincodeInDeliveryForm(String pinCode,String phoneNumber)
    {
        Mapper.find(domFile, "pinCode").sendKeys(pinCode);
        Mapper.find(domFile, "phoneNumber").sendKeys(phoneNumber);
        Mapper.find(domFile, "continueButtonDeliveryDetails").click();
        if (!(Mapper.find(domFile, "errorOnfullName").isDisplayed()) || !(Mapper.find(domFile, "errorOnpinCode").isDisplayed()) || !(Mapper.find(domFile, "errorOnaddress").isDisplayed()) || !(Mapper.find(domFile, "errorOnphoneNumber").isDisplayed()))
            return false;
        return true;

    }

    /**
     * Function for validation of order summary
     */
    public boolean validateOrderSummary()
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        if (!(Mapper.find(domFile, "orderSummaryDetails").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of Continue as Guest
     */
    public boolean validateContinueAsGuest(String userEmail)
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        Mapper.find(domFile, "emailAddress").sendKeys(userEmail);
        Mapper.find(domFile, "continueButton").click();
        Mapper.waitForElementToBeInvisible(domFile, "continueButton");
        if (!(Mapper.find(domFile, "continueAsGuest").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for Validation of SignIn and Continue as Guest option
     */
    public boolean validateContinueAsGuestAndSignIn(String userEmail)
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        Mapper.find(domFile, "emailAddress").sendKeys(userEmail);
        Mapper.find(domFile, "continueButton").click();
        if (!(Mapper.find(domFile, "continueAsGuest").isDisplayed()) || !(Mapper.find(domFile, "signInOption").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of SignIn on Checkout Page
     */
    public boolean validateSignInOnCheckoutPage(String userEmail, String userPassword)
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        Mapper.find(domFile, "emailAddress").sendKeys(userEmail);
        Mapper.find(domFile, "continueButton").click();
        Mapper.find(domFile, "passwordOption").sendKeys(userPassword);
        Mapper.find(domFile, "signInOption").click();
        Mapper.waitForElementToBeInvisible(domFile, "signInOption");
        if (!(Mapper.find(domFile, "fullName").isDisplayed()) || !(Mapper.find(domFile, "pinCode").isDisplayed()) || !(Mapper.find(domFile, "address").isDisplayed()) || !(Mapper.find(domFile, "phoneNumber").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for placing order via COD
     */
    public void placeOrderViaCod(String fullName, String pinCode, String address, String phoneNumber)
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        Mapper.find(domFile, "fullName").clear();
        Mapper.find(domFile, "address").clear();
        Mapper.find(domFile, "phoneNumber").clear();
        Mapper.find(domFile, "fullName").sendKeys(fullName);
        Mapper.waitForElementToBeVisible(domFile,"address");
        Mapper.find(domFile, "address").sendKeys(address);
        Mapper.find(domFile, "phoneNumber").sendKeys(phoneNumber);
        Mapper.waitForElementToBeVisible(domFile,"continueButtonDeliveryDetails");
        Mapper.find(domFile, "continueButtonDeliveryDetails").click();
        Mapper.waitForElementToBeClickable(domFile, "codOption");
        sleep(3000);
        selectCod();
        sleep(2000);
        clickOrderConfirmation();
        Mapper.waitForElementToBeInvisible(domFile, "confirmOrderOption");
    }

    /**
     * Function for validation of order placed
     */
    public boolean validatePlaceOrder()
    {
        Mapper.waitForElementToBeVisible(domFile, "thankYouMessage");
        if (!(Mapper.find(domFile, "thankYouMessage").isDisplayed()) || !(Mapper.find(domFile, "orderPlacedMessage").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for verifying netbanking form in checkout page
     */
    public boolean placeOrderViaNetBanking(String fullName, String pinCode, String address, String phoneNumber)
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        Mapper.find(domFile, "fullName").clear();
        Mapper.find(domFile, "pinCode").clear();
        Mapper.find(domFile, "address").clear();
        Mapper.find(domFile, "phoneNumber").clear();
        Mapper.find(domFile, "fullName").sendKeys(fullName);
        Mapper.find(domFile, "pinCode").sendKeys(pinCode);
        Mapper.find(domFile, "address").sendKeys(address);
        Mapper.find(domFile, "phoneNumber").sendKeys(phoneNumber);
        Mapper.find(domFile, "continueButtonDeliveryDetails").click();
        Mapper.find(domFile, "netbankingoption").click();
        if (!(Mapper.find(domFile, "chooseBanktitle").isDisplayed()) || !(Mapper.find(domFile, "chooseBankListing").isDisplayed()) || !(Mapper.find(domFile, "netBankingPayNowBtn").isDisplayed()) || !(Mapper.find(domFile, "chooseBankListDropdown").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for verifying Debit/creditcard form in checkout page
     */
    public boolean placeOrderViaDebitCreditcard(String fullName, String pinCode, String address, String phoneNumber)
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        Mapper.find(domFile, "fullName").clear();
        Mapper.find(domFile, "pinCode").clear();
        Mapper.find(domFile, "address").clear();
        Mapper.find(domFile, "phoneNumber").clear();
        Mapper.find(domFile, "fullName").sendKeys(fullName);
        Mapper.find(domFile, "pinCode").sendKeys(pinCode);
        Mapper.find(domFile, "address").sendKeys(address);
        Mapper.find(domFile, "phoneNumber").sendKeys(phoneNumber);
        Mapper.find(domFile, "continueButtonDeliveryDetails").click();
        Mapper.find(domFile, "cardoption").click();
        if (!(Mapper.find(domFile, "cardTypeDrodpown").isDisplayed()) || !(Mapper.find(domFile, "cardHolderName").isDisplayed())
                || !(Mapper.find(domFile, "cardNumber").isDisplayed())
                || !(Mapper.find(domFile, "cardExpiryDate").isDisplayed()) || !(Mapper.find(domFile, "cardCvv").isDisplayed())
                || !(Mapper.find(domFile, "cardCvvIcon").isDisplayed()) || !(Mapper.find(domFile, "cardPayButton").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function buying product as guest user
     */
    public void placeOrderAsGuestUser(String userEmail, String userPassword, String userName,String userPin, String userAddress, String userPhone)
    {
        Mapper.find(domFile, "emailAddress").sendKeys(userEmail);
        Mapper.find(domFile, "continueButton").click();
        Mapper.find(domFile, "passwordOption").sendKeys(userPassword);
        Mapper.find(domFile, "signInOption").click();
        Mapper.waitForElementToBeVisible(domFile, "fullName");
        Mapper.find(domFile, "fullName").clear();
        Mapper.find(domFile, "pinCode").clear();
        Mapper.find(domFile, "address").clear();
        Mapper.find(domFile, "phoneNumber").clear();
        Mapper.find(domFile, "fullName").sendKeys(userName);
        Mapper.find(domFile, "pinCode").sendKeys(userPin);
        Mapper.find(domFile, "address").sendKeys(userAddress);
        Mapper.find(domFile, "phoneNumber").sendKeys(userPhone);
        Mapper.find(domFile, "continueButtonDeliveryDetails").click();
        Mapper.find(domFile, "codOption").click();
        Mapper.waitForElementToBeClickable(domFile, "confirmOrderOption");
        Mapper.find(domFile, "confirmOrderOption").click();
        Mapper.find(domFile, "confirmOrderOption").click();

    }

    /**
     * get Out of stock notification text
     */
    public String getOosChangeText(){

        return Mapper.find(domFile,"outOfStock").getText() +" "+ Mapper.find(domFile,"moreSellersLink").getText();
    }

    /**
     * get Out of stock notification text
     */
    public String getPriceChangeText(){

        return Mapper.find(domFile,"pricechange").getText();
    }

    /**
     * fill out checkout page for cod
     */
    public void orderViaCod(String fullName, String pinCode, String address, String phoneNumber)
    {
        Mapper.waitForElementToBeInvisible(domFile, "placeOrder");
        Mapper.find(domFile, "fullName").clear();
        Mapper.find(domFile, "pinCode").clear();
        Mapper.find(domFile, "address").clear();
        Mapper.find(domFile, "phoneNumber").clear();
        Mapper.find(domFile, "fullName").sendKeys(fullName);
        Mapper.find(domFile, "pinCode").sendKeys(pinCode);
        Mapper.find(domFile, "address").sendKeys(address);
        Mapper.find(domFile, "phoneNumber").sendKeys(phoneNumber);
        Mapper.find(domFile, "continueButtonDeliveryDetails").click();
        Mapper.find(domFile, "codOption").click();
        Mapper.find(domFile, "confirmOrderOption").click();
    }

    public String getOrderId(){
       return Mapper.find(domFile,"orderId").getText().trim();
    }

    public String getTotalPrice(){
        return Mapper.find(domFile,"totalPrice").getText().replace("₹","").replace(",","").trim();
    }

    public String getItemPrice(){
        return Mapper.find(domFile,"itemPrice").getText().split("₹")[1].trim().replaceAll(",","");
    }

    public String getExchangeDiscount(){
        return Mapper.find(domFile,"exchangeDiscount").getText().split("₹")[1].trim().replaceAll(",","");
    }

    public WebElement body(){
        return Mapper.find(domFile,"body");
    }


    public String getExchangeprice(){
       String price = Mapper.find(domFile,"exchangePrice").getText().split("₹")[1].trim();
       if (price.contains(",")){
           return price.replace(",","");
       }else
           return price;

    }

    public String getOnlineDiscount(){
        return Mapper.find(domFile,"discountAmount").getText().split("₹")[1].replace(".00","").trim();
    }

    public int getOnlineTotal(){
        return Integer.parseInt(Mapper.find(domFile,"onlineFinalPrice").getText().split("₹")[1].trim());
    }

    public boolean isOnlineDiscountDisplayed(){
        try {
          return Mapper.find(domFile,"onlineDicsountDiv").isDisplayed();
        }catch (Exception e)
        {
            return false;
        }

    }

    public boolean isOnlineDiscountEnabled(){
        try {
            return Mapper.find(domFile,"onlineDiscountEnabled").isDisplayed();
        }catch (Exception e)
        {
            return false;
        }

    }

    public void fillCheckOutDetails(String userEmail,String fullName, String pinCode, String address, String phoneNumber){

        Mapper.find(domFile, "emailAddress").sendKeys(userEmail);
        Mapper.find(domFile, "continueButton").click();
        sleep(5000);
        try{
        if (Mapper.find(domFile, "pinError").isDisplayed()) {
            Mapper.find(domFile,"pinCode").sendKeys(pinCode);
        }}catch (Exception e){

        }
        Mapper.find(domFile, "fullName").clear();
        Mapper.find(domFile, "address").clear();
        Mapper.find(domFile, "phoneNumber").clear();
        Mapper.find(domFile, "fullName").sendKeys(fullName);
        Mapper.find(domFile, "address").sendKeys(address);
        Mapper.find(domFile, "phoneNumber").sendKeys(phoneNumber);
        Mapper.find(domFile, "continueButtonDeliveryDetails").click();
        sleep(2000);
        Mapper.waitForElementToBeInvisible(domFile, "continueButtonDeliveryDetails");

    }

    public void selectCod(){
        Mapper.waitForElementToBeClickable(domFile, "codOption");
        Mapper.find(domFile, "codOption").click();
        sleep(5);
    }

    public void selectNetBanking(){
        Mapper.find(domFile, "netbankingoption").click();
    }

    public void seclectCard(){
        Mapper.find(domFile, "cardoption").click();
    }

    public void clickOrderConfirmation(){

        Mapper.waitForElementToBeClickable(domFile, "confirmOrderOption");
        Mapper.find(domFile, "confirmOrderOption").click();
        sleep(5);
    }

    public boolean validateCardFields(){
        Mapper.find(domFile, "cardoption").click();
        if (!(Mapper.find(domFile, "cardTypeDrodpown").isDisplayed()) ||
                 !(Mapper.find(domFile, "cardHolderName").isDisplayed()) ||
                    !(Mapper.find(domFile, "cardNumber").isDisplayed()) ||
                      !(Mapper.find(domFile, "cardExpiryDate").isDisplayed()) ||
                         !(Mapper.find(domFile, "cardCvv").isDisplayed()) ||
                             !(Mapper.find(domFile, "cardCvvIcon").isDisplayed()) ||
                               !(Mapper.find(domFile, "cardPayButton").isDisplayed())){
            return false;
        }else{
            return true;
        }

    }

    public boolean validateNetBankingFields(){
        if (!(Mapper.find(domFile, "chooseBanktitle").isDisplayed()) ||
                !(Mapper.find(domFile, "chooseBankListing").isDisplayed()) ||
                  !(Mapper.find(domFile, "netBankingPayNowBtn").isDisplayed()) ||
                    !(Mapper.find(domFile, "chooseBankListDropdown").isDisplayed()))
            return false;
        return true;
    }


}