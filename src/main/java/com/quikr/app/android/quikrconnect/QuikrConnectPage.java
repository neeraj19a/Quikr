package com.quikr.app.android.quikrconnect;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by rohanbajaj on 01/09/15.
 */
public class QuikrConnectPage extends AppAndroidPageBase
{
    public QuikrConnectPage(AppiumDriver driver)
    {
        super(domFile,driver);
    }
    private static final String domFile = getProperties().get("ANDROID_QUIKRCONNECT_DOM_FILE");

    public boolean isQuikrConnectTextPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "quikrConnectText");
        return isElementPresent("quikrConnectText");
    }
}
