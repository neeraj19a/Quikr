package com.quikr.msite.mEscrow.mEscrowVap;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 5/2/16.
 */
public class MEscrowVapPage extends MPageBase {

    public static final String domFile = getProperties().get("mEscrow_VAP_DOM_FILE");

    public MEscrowVapPage(AppiumDriver driver) {
        super(domFile, driver);
    }


    public void buyNow() {
        Mapper.waitForElementToBeVisible(domFile, "buyNowButton");
        Mapper.find(domFile, "buyNowButton").click();
        Mapper.waitForElementToBeVisible(domFile, "butNowForm");
    }

    public void buyNowEmail(String email) {
        Mapper.waitForElementToBeClickable(domFile,"buyNowEmail");
        Mapper.find(domFile, "buyNowEmail").clear();
        Mapper.find(domFile, "buyNowEmail").sendKeys(email);
    }

    public void buyNowMobile(String mobile) {
        Mapper.find(domFile, "buyNowMobile").clear();
        Mapper.find(domFile, "buyNowMobile").sendKeys(mobile);
    }

    public void buyNowCity(String city) {
        Mapper.find(domFile, "buyNowCity").clear();
        Mapper.find(domFile, "buyNowCity").sendKeys(city);
        Mapper.waitForElementToBeClickable(domFile,"buyNowCityAutoSuggest");
        Mapper.find(domFile, "buyNowCityAutoSuggest").click();
    }

    public void submitBuyNow() {
        Mapper.waitForElementToBeVisible(domFile, "submitBuyNow");
        Mapper.find(domFile, "submitBuyNow").click();

    }

    public void mao() {
        Mapper.waitForElementToBeVisible(domFile, "maoNowButton");
        Mapper.find(domFile, "maoNowButton").click();
        Mapper.waitForElementToBeVisible(domFile, "maoForm");
    }

    public void maoPrice(String price)
    {
        Mapper.waitForElementToBeVisible(domFile, "maoPrice");
        Mapper.waitForElementToBeClickable(domFile, "maoPrice");
        Mapper.find(domFile, "maoPrice").sendKeys(price);
    }

    public void maoMail(String email)
    {
        Mapper.waitForElementToBeClickable(domFile, "maoEmail");
        Mapper.find(domFile, "maoEmail").clear();
        Mapper.find(domFile, "maoEmail").sendKeys(email);
    }

    public void maoMobile(String mobile)
    {
        Mapper.waitForElementToBeClickable(domFile, "maoMobile");
        Mapper.find(domFile, "maoMobile").clear();
        Mapper.find(domFile, "maoMobile").sendKeys(mobile);
    }

    public void maoCity(String city) {
        Mapper.waitForElementToBeClickable(domFile, "maoCity");
        Mapper.find(domFile, "maoCity").sendKeys(city);
        Mapper.waitForElementToBeClickable(domFile,"maoCityAutoSuggest");
        Mapper.find(domFile, "maoCityAutoSuggest").click();
    }

    public void submitOffer()
    {
        Mapper.waitForElementToBeVisible(domFile, "submitMao");
        Mapper.waitForElementToBeClickable(domFile,"submitMao");
        Mapper.find(domFile, "submitMao").click();
    }

    public boolean isOfferSent() {
        Mapper.waitForElementToBeVisible(domFile, "offerSent",10);
        if (Mapper.find(domFile, "offerSent").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void openAcceptOfferURL(String offerId,String sellerEncodeEmail) {
        navigateTo().to("http://bangalore.quikr.com/sellerresp?responseId=7&offerId="+offerId+"&allowmail=%20"+sellerEncodeEmail);
    }



    public void acceptOfferSeller(String offerId, String sellerName,String sellerEncodeEmail) {
        openAcceptOfferURL(offerId,sellerEncodeEmail);
        Mapper.waitForElementToBeClickable(domFile,"nameUserDetails");
        Mapper.find(domFile,"nameUserDetails").sendKeys(sellerName);
        Mapper.waitForElementToBeVisible(domFile, "submitUserDetails");
        Mapper.find(domFile, "submitUserDetails").click();
    }

    public boolean ifOfferAccepted()
    {
        Mapper.waitForElementToBeVisible(domFile,"acceptOfferThankYouMessage");
        if (Mapper.find(domFile,"acceptOfferThankYouMessage").getText().equalsIgnoreCase("Thank You!"))
        {
            return true;
        }
        else
            return false;
    }

    public void openCounterOfferURL(String offerId,String sellerEncodeEmail) {
        navigateTo().to("http://bangalore.quikr.com/sellercounterofferform?offerId="+offerId+"&allowmail="+sellerEncodeEmail);
    }

    public void inputCounterOfferPrice(String price) {
        Mapper.waitForElementToBeVisible(domFile, "hiUsernameHeader");
        Mapper.waitForElementToBeClickable(domFile, "hiUsernameHeader");
        Mapper.find(domFile, "counterOfferPrice").sendKeys(price);
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
        Mapper.find(domFile, "counterOfferSubmitButton").click();
    }

    public boolean isCounterOfferSent(){
        Mapper.waitForElementToBeVisible(domFile,"counterOfferThankYouMessage");
        if (Mapper.find(domFile,"counterOfferThankYouMessage").getText().equalsIgnoreCase("Thank You!"))
        {
            return true;
        }
        else
            return false;
    }

    public boolean isMAONotPresent() {
        try {
            Mapper.find(domFile, "maoNowButton");
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    public boolean isBuyNowPresent() {
        if (Mapper.find(domFile, "buyNowButton").isDisplayed()) {
            return true;
        } else return false;
    }


}
