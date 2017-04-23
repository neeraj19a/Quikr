package com.quikr.app.ios.quikrX.quikrXHomePage;


import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 14/10/15.
 */
public class QuikrXHomePage extends com.quikr.app.ios.AppiOSPageBase {
    public QuikrXHomePage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_QUIKRXHOMEPAGE_DOM_FILE");

}
