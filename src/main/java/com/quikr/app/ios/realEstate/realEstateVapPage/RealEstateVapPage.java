package com.quikr.app.ios.realEstate.realEstateVapPage;

import com.quikr.app.ios.AppiOSPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 27/11/15.
 */
public class RealEstateVapPage extends AppiOSPageBase{

    public RealEstateVapPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_REALESTATEVAPPAGE_DOM_FILE");

    /**
     * function to validate locality on vap page
     */
    public boolean validateLocalityOnVapPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"localityName");
        return  isElementPresent("localityName");
    }

    /**
     * function to validate call button on realestate
     */
    public boolean validateCallButtonVapPage()
    {
        return isElementPresent("callButton");
    }

    /**
     * function to click on call button
     */
    public void selectCallButtonOnVapPage()
    {
        Mapper.find(domFile,"callButton").click();
    }

    /**
     * function to validate call and cancel button popup
     */
    public boolean validateCallAndCancelButtonPopUp()
    {
        return (isElementPresent("callButtonOnPopUp") && isElementPresent("cancelButtonOnPopUp"));
    }
}
