package com.quikr.msite.mHorizontal.mVAP;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 19/2/16.
 */
public class MVAPPage extends MPageBase {
    private static final String domFile = getProperties().get("mVAP_DOM_FILE");

    public MVAPPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    public boolean verifyVapPage() {
        boolean retVal = false;
        if (Mapper.find(domFile, "callSmsWrapper")!=null) {
            retVal = true;
        } else {
            return false;
        }
        return retVal;
    }

    public void clickViewProfileButton() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }

        verticalSwipeWithCordinates(1350, 100);
        if (Mapper.waitForElementToBeVisible(domFile, "viewProfile") == true) {
            if (Mapper.waitForElementToBeClickable(domFile, "viewProfile") == true) {
                Mapper.find(domFile, "viewProfile").click();
            } else {
                logger.info("Element is not clickable at this point of time. Please check!!!");
            }
        } else {
            logger.info("View Profile button is either not present or Not visible. Please check!!");
        }
    }

    public boolean verifyUserProfilePage() {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "userProfilePageProfileImage") == true) {
            retVal = true;
        } else {
            logger.info("User Profile Image is not visible.Please check!");
            return false;
        }
        return retVal;
    }

    public boolean verifyBuyNowLabel() {
        if (Mapper.waitForElementToBeVisible(domFile, "buyNowLabel") == true) {
            String label = Mapper.find(domFile, "buyNowLabel").getText();
            if (label.equals("BUY NOW")) {
                return true;
            }
            else
                return false;
            }
        else
            return false;
    }

    public void clickBuyNow() {
        if(Mapper.waitForElementToBeClickable(domFile,"buyNowButton")==true) {
            Mapper.find(domFile, "buyNowButton").click();
        }
    }

    public void clickMakeAnOffer() {
        if (Mapper.waitForElementToBeVisible(domFile, "makeAnOfferButton") == true) {
            Mapper.find(domFile, "makeAnOfferButton").click();
        } else {
            logger.info("Make an offer button is not present. Please check!");
        }
    }

    public void enterOfferPrice(String offerPrice) {
        if (Mapper.waitForElementToBeVisible(domFile, "makeAnOfferPrice") == true) {
            Mapper.find(domFile, "makeAnOfferPrice").sendKeys(offerPrice);
        }
    }

    public void enterMail(String mail) {
        Mapper.find(domFile, "makeAnOfferEmail").sendKeys(mail);
    }

    public void enterPhNum(String num) {
        Mapper.find(domFile, "makeAnOfferMobNum").clear();
        Mapper.find(domFile, "makeAnOfferMobNum").sendKeys(num);
    }

    public boolean validateMakeAnOfferSuccess() {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "makeOfferSuccessMessage") == true) {
            String confText = Mapper.find(domFile, "makeOfferSuccessMessage").getText();
            if (confText.contains("We will update you")) {
                retVal = true;
            } else {
                return false;
            }
        }
        return retVal;
    }

    public void clickSendMyOfferButton() {
        Mapper.find(domFile, "sendMyOfferButton").click();
    }

    public String gettexttitleVapVernac() {
        return Mapper.find(domFile, "vapTitleVernac").getText();

    }
}
