package com.quikr.app.ios.realEstate.realEstateHeaderPage;

import com.quikr.app.ios.AppiOSPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 30/11/15.
 */
public class RealEstateHeaderPage extends AppiOSPageBase {

    public RealEstateHeaderPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_REALESTATEHEADERPAGE_DOM_FILE");

    /**
     * function to set localityOrProjectsOrBuilders
     */
    public void setLocalityProjectsOrBuilders(String str)
    {
        Mapper.find(domFile,"setLocalityProjectsOrBuilders").sendKeys(str);
    }

    /**
     * function to click on search button of keyboard
     */
    public void selectSearchButtonOfKeyboard()
    {
        Mapper.find(domFile,"searchButton").click();
    }

    /**
     * function to validate header text at snb page
     */
    public boolean validateHeaderTextAtSnbPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"headerText");
        return isElementPresent("headerText");
    }
}
