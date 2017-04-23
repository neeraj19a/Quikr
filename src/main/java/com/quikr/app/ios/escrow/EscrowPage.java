package com.quikr.app.ios.escrow;


import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 30/10/15.
 */
public class EscrowPage extends com.quikr.app.ios.AppiOSPageBase {

    public EscrowPage(AppiumDriver driver)
    {
        super(domFile,driver);
    }
    // const string
    private static final String domFile = getProperties().get("IOS_ESCROW_DOM_FILE");

    /**
     * function to validate make an offer button
     */
    public boolean validateMakeAnOfferButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"makeAnOfferButton");
        return isElementPresent("makeAnOfferButton");
    }
}
