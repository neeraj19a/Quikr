package com.quikr.app.android.quikrDoorstep;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 23/6/16.
 */
public class QuikrDoorstepPage  extends AppAndroidPageBase {
    public QuikrDoorstepPage(AppiumDriver driver){super(domFile, driver);}
    private static final String domFile = getProperties().get("ANDROID_QUIKRDOORSTEP_DOM_FILE");

    public void selectViewOffers(){
        Mapper.waitForElementToBeVisible(domFile,"viewOffers");
        Mapper.find(domFile,"viewOffers").click();
    }

    public boolean ifLatestOfferPresent(){
        boolean flag = false;
        Mapper.waitForElementToBeVisible(domFile,"latestOffer",10);
        try {
            if (Mapper.find(domFile, "latestOffer").isDisplayed()) {
                flag= true;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return flag;
    }
    public void acceptOffer(){
        Mapper.waitForElementToBeVisible(domFile,"acceptOffer");
        Mapper.find(domFile,"acceptOffer").click();
    }
    public void acceptOfferNotifications(){
        Mapper.waitForElementToBeVisible(domFile,"acceptOfferNotifications");
        Mapper.find(domFile,"acceptOfferNotifications").click();
        Mapper.waitForElementToBeVisible(domFile,"confirmAcceptOfferNotifications");
        Mapper.find(domFile,"confirmAcceptOfferNotifications").click();
    }
}
