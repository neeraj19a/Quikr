package com.quikr.app.ios.mobilesAndTablets;

import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
* Created by Manvendra Singh on 5/10/15.
*/
public class MobilesAndTabletsPage extends com.quikr.app.ios.AppiOSPageBase {
    public MobilesAndTabletsPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_MOBILESANDTABLETS_DOM_FILE");


    /**
     * function to validate quikrX subcategory option in mobile and tablets subcategory
     */
    public boolean validateQuikrXSubcategoryName()
    {
        return (isElementPresent("exchangeOldForNew") && isElementPresent("buyCertifiedUsedPhone"));
    }

}
