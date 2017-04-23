package com.quikr.msite.mJobs;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 19/11/15.
 */
public class MJobs extends MPageBase {

    private static final String domFile = getProperties().get("mJobs_DOM_FILE");

    public MJobs(AppiumDriver driver) {
        super(domFile, driver);
    }


}
