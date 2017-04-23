package com.quikr.app.ios.services.servicesHomePage;

import com.quikr.app.ios.AppiOSPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 2/26/16.
 */
public class ServicesHomePage extends AppiOSPageBase{

    public ServicesHomePage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_SERVICESHOMEPAGE_DOM_FILE");

    /**
     * function to validate services page text
     */
    public boolean validateServicesPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"servicesText");
        return isElementPresent("servicesText");
    }

    /**
     * function to validate maine subcategory name for services
     */
    public boolean validateMaineServicesSubCategoryName()
    {
        return isElementPresent("homeServices");
    }

    /**
     * function to validate quikr connect page
     */
    public boolean validateQuikrConnectPageOptions()
    {
        return (isElementPresent("submitUrContactDetailsText") && isElementPresent("weCallYouToUnderstandYourRequirementsText")
           && isElementPresent("connectYouWithRelevantServicesProviderText") && isElementPresent("relaxAndGetYourJobDoneQuikrText"));
    }

    /**
     * function to validate skip and connect now button on quikr connect page
     */
    public boolean validateSkipAndConnectNowButtonOnQuikrConnectPage()
    {
        return (isElementPresent("skipButton") && isElementPresent("connectNowButton"));
    }

    /**
     * function to click on skip button
     */
    public void clickOnSkipButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"skipButton");
        Mapper.find(domFile,"skipButton").click();
    }

    /**
     * function to select connect now button
     */
    public void selectConnectNowButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"connectNowButton");
        Mapper.find(domFile,"connectNowButton").click();
    }

    /**
     * function to validate quikr connect form
     */
    public boolean validateSubmitButtonOnQuikrConnectForm()
    {
        Mapper.scroll("Submit");
        return (isElementPresent("submitButton"));
    }

    /**
     * function to select submit button on services quikr connect page
     */
    public void selectSubmitButtonOnQuikrConnectPage()
    {
        Mapper.scroll("Submit");
        Mapper.find(domFile,"submitButton").click();
    }

    /**
     * function to set name on quikr services page
     */
    public void setNameOnServicesQuikrConnectPage(String name)
    {
        Mapper.waitForElementToBeVisible(domFile,"userName");
        Mapper.find(domFile,"userName").clear();
        Mapper.find(domFile,"userName").sendKeys(name);
    }

    /**
     * function to set mobile no
     */
    public void setMobileNoOnServicesQuikrConnectPage(String mobile)
    {
        Mapper.waitForElementToBeVisible(domFile,"mobileNo");
        Mapper.find(domFile,"mobileNo").clear();
        Mapper.find(domFile,"mobileNo").sendKeys(mobile);
    }

    /**
     * function to validate validation code error msg
     */
    public boolean validateValidationCodeErrorMsg()
    {
        return isElementPresent("validationCodeErrorMsg");
    }

    /**
     * function to validate msg after not entering mobile no in quikr connect form
     */
    public boolean validateMsgNotProvidingMbNoInQuikrConnectFormPage()
    {
        return isElementPresent("msgAfterNotProvidingMbNo");
    }
}
