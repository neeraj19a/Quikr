package com.quikr.app.ios.menu;

import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 6/10/15.
 */
public class MenuPage  extends com.quikr.app.ios.AppiOSPageBase {
    public MenuPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_MENU_DOM_FILE");

    /**
     * function to validate app version
     */
    public boolean validateAppVersion()
    {
        Mapper.scroll("version");
        return isElementPresent("version");
    }

    /**
     * function to click on my account button
     */
    public void clickOnMyAccount()
    {
        Mapper.find(domFile,"myAccount").click();
    }

    /**
     * function to click on feedback
     */
    public void selectFeedbackButton()
    {
        Mapper.find(domFile,"feedbackButton").click();
    }

    /**
     * function to validate  feedback
     */
    public boolean validateFeedback()
    {
        return isElementPresent("feedbackMsg");
    }

    /**
     * function to select rate us
     */
    public void selectRateUs()
    {
        Mapper.find(domFile,"rateUsButton").click();
    }

    /**
     * function to click on yes button on rate us screen
     */
    public void selectYesButtonOnRateUsScreen()
    {
        Mapper.find(domFile,"rateUsOption").click();
    }

    /**
     * function ot click on rate us star
     */
    public void selectStarInRateUs()
    {
        System.out.println(Mapper.find(domFile,"rateUsStar").getAttribute("name"));
        Mapper.find(domFile,"rateUsStar").click();
      //  Mapper.waitForElementToBeVisible(domFile, "rateStarGold");
        if (isElementPresent("rateUsStar"))
        {
            while (Mapper.waitForElementToBeVisible(domFile, "rateUsStar"))
            {
                Mapper.find(domFile, "rateUsStar").click();
            }
        }
    }

    /**
     * function to validate rate us
     */
    public boolean validateRateUs()
    {

        Mapper.waitForElementToBeVisible(domFile,"validateMsgForRateUs");
        return isElementPresent("validateMsgForRateUs");

    }

    /**
     * function select share button
     */
    public void selectShareButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"shareButton");
        Mapper.find(domFile,"shareButton").click();
    }

    /**
     * function to click on mail button
     */
    public void selectMail()
    {
        Mapper.waitForElementToBeVisible(domFile,"mailButton");
        Mapper.find(domFile,"mailButton").click();
    }

    /**
     * function to validate share app
     */
    public boolean validateShareApp()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgBody");
        return isElementPresent("msgBody");
    }

    /**
     * function to select my shortlist
     */
    public void selectMyShortlist()
    {
        Mapper.find(domFile,"myShortlistButton").click();
    }

    /**
     * function to validate my shortlist
     */
    public boolean validateMyShortlist()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgForAdInShortlist");
        return isElementPresent("msgForAdInShortlist");
    }

    /**
     * function to select my ad
     */
    public void selectMyAds()
    {
        Mapper.waitForElementToBeVisible(domFile,"myAds");
        Mapper.find(domFile,"myAds").click();
    }

    /**
     * function to validate my ads page
     */
    public boolean validateMyAdsPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"myAdsMoreButton");
        return (isElementPresent("myAds") && isElementPresent("myAdsMoreButton"));
    }

    /**
     * function to validate my account all options
     */
    public boolean validateMyAccountOptions()
    {
        return (isElementPresent("signUpOrLogin") && isElementPresent("myShortlist") && isElementPresent("myAds") && isElementPresent("myAlert") && isElementPresent("myCart") && isElementPresent("myOrders"));
    }


    /**
     * function to click on legal and policy button
     */
    public void clickOnLegalAndPolicyButton()
    {
        Mapper.find(domFile,"clickOnLegalAndPolicyButton").click();
    }

    /**
     * function to validate legal and policy option
     */
    public boolean validateLegalAndPolicyOptions()
    {
        return (isElementPresent("termAndCondition") && isElementPresent("privacyAndPolicy"));
    }

    /**
     * function to click on ads nearby button
     */
    public void clickOnAdsNearBy()
    {
        Mapper.find(domFile,"adsNearByButton").click();
    }

    /**
     * function to validate ads nearby
     */
    public boolean validateAdsNearBy()
    {
        return isElementPresent("adsNearByText");
    }

    /**
     * function to click on popular in bangalore
     */
    public void clickOnPopularAdsInCityName()
    {
        Mapper.find(domFile,"popularInBangaloreCity").click();
    }

    /**
     * function to validate popular ads text
     */
    public boolean validatePopularAdsPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"popularAdsText");
        return isElementPresent("popularAdsText");
    }

    /**
     * function to click on contact us tab
     */
    public void clickOnContactUsTab()
    {
        Mapper.find(domFile,"contactUsTab").click();
    }

    /**
     * function to validate contact us
     */
    public boolean validateContactUs()
    {
        Mapper.waitForElementToBeVisible(domFile,"makeAnOfferTab");
        return (isElementPresent("makeAnOfferTab") && isElementPresent("quikrXTab") && isElementPresent("quikrCustomerCare"));
    }
}
