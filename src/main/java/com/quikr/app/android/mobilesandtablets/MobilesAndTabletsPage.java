package com.quikr.app.android.mobilesandtablets;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by Manvendra Singh on 17/8/15.
 */
public class MobilesAndTabletsPage extends AppAndroidPageBase {
    public MobilesAndTabletsPage(AppiumDriver  driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_MOBILESANDTABLETS_DOM_FILE");
}
