package com.quikr.app.ios.vap;

import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 13/10/15.
 */
public class VapPage extends com.quikr.app.ios.AppiOSPageBase {
    public VapPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_VAP_DOM_FILE");

    /**
     * function validate preview ad
     */
    public boolean validatePreviewAdPost()
    {
        Mapper.waitForElementToBeVisible(domFile,"adPreview");
        return isElementPresent("adPreview");
    }

    /**
     * function click on edit ad button
     */
    public void clickOnEditAdButtonOnVap()
    {
        Mapper.find(domFile,"editAdButton").click();
        Mapper.waitForElementToBeInvisible(domFile,"editAdButton");
    }

    /**
     * function to validate edit ad button
     */
    public boolean validateEditAdButton()
    {
        return isElementPresent("editAdButton");
    }

    /**
     * function to click on shortlist button on Vap page
     */
    public void clickOnShortlistButtonOnVap()
    {
        Mapper.waitForElementToBeVisible(domFile,"shortlistButton");
        Mapper.find(domFile,"shortlistButton").click();
    }

    /**
     * function to validate shortlist button on vap before select
     */
    public boolean validateShortlistButtonBeforeSelect()
    {
        Mapper.waitForElementToBeVisible(domFile,"shortlistButton");
        return isElementPresent("shortlistButton");
    }

    /**
     * function to click on shortlist press button (unStar button)
     */
    public void  selectShortlistPressButton()
    {
        Mapper.find(domFile,"shortlistPressButton").click();
    }

    /**
     * function to validate shortlist press button is showing in shortlist ad
     */
    public boolean validateShortlistPressButton()
    {
        return isElementPresent("shortlistPressButton");
    }

    /**
     * function to click on chat button on vap
     */
    public void selectChatButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"chatButton");
        Mapper.find(domFile,"chatButton").click();
    }

    /**
     * function to select vap share button
     */
    public void selectVapShareButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"vapShareButton");
        Mapper.find(domFile,"vapShareButton").click();
    }

    /**
     * function to validate vap share button
     */
    public boolean validateVapShareButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"vapShareButton");
        return isElementPresent("vapShareButton");
    }

    /**
     * function to click on vap page back button
     */
    public void clickOnVapBackButton()
    {
        Mapper.find(domFile,"vapBackButton").click();
    }
}
