package com.quikr.msite.mEscrow.mEscrowSnb;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 5/2/16.
 */
public class MEscrowSnbPage extends MPageBase {

    private static final String domFile = getProperties().get("mEscrow_SNB_DOM_FILE");
    public MEscrowSnbPage(AppiumDriver driver){super(domFile,driver);}

    public void searchAd(String adID){
        deleteAllCookies();
        Mapper.waitForElementToBeVisible(domFile,"startAdSearch");
        Mapper.find(domFile,"startAdSearch").click();
        Mapper.waitForElementToBeVisible(domFile,"enterAdId");
        Mapper.find(domFile,"enterAdId").sendKeys(adID);
        Mapper.find(domFile,"searchAd").click();
    }

    public void selectAd() {
        Mapper.waitForElementToBeVisible(domFile, "selectAd");
        Mapper.find(domFile, "selectAd").click();
    }

    public boolean ifAdSelected(){
        Mapper.waitForElementToBeVisible(domFile,"isAdSelected");
        if(Mapper.find(domFile,"isAdSelected").isDisplayed())
            return true;
        else
            return false;
    }

    public void mao()
    {
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

    public boolean ifBuyNowPresent(){
        Mapper.waitForElementToBeVisible(domFile,"buyNowButton");
        if (Mapper.find(domFile,"buyNowButton").isDisplayed()){
            return true;
        }
        else
            return false;
    }

    public boolean isOfferSent() {
        Mapper.waitForElementToBeVisible(domFile, "offerSent",10);
        if (Mapper.find(domFile, "offerSent").isDisplayed()) {
            return true;
        } else
            return false;
    }

}
