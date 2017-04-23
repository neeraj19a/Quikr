package com.quikr.app.ios.electronicsAndAppliances;

import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 30/9/15.
 */
public class ElectronicsAndAppliances extends com.quikr.app.ios.AppiOSPageBase {
    public ElectronicsAndAppliances(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_ELECTRONICSANDAPPLIANCES_DOM_FILE");


}
