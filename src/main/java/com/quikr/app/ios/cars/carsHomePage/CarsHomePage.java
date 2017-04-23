package com.quikr.app.ios.cars.carsHomePage;

import com.quikr.app.ios.AppiOSPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 1/19/16.
 */
public class CarsHomePage extends AppiOSPageBase{

    public CarsHomePage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_CARSHOMEPAGE_DOM_FILE");

    /**
     * function to validate subcategory name present in cars and bike sub category
     */
    public boolean validateSubCategoryOfCarsAndBikes()
    {
        return (isElementPresent("bikes") && isElementPresent("cars") && isElementPresent("commercial"));
    }


    /**
     * function to click on bike tab on car home page
     */
    public void clickOnBikeTabOnCarsHomePage()
    {
        Mapper.waitForElementToBeVisible(domFile,"bikes");
        Mapper.find(domFile,"bikes").click();
    }

    /**
     * function to validate check msp tab is present on car home page
     */
    public boolean validateMspTabPresentOrNot()
    {
        return isElementPresent("checkMspButton");
    }

    /**
     * function to click on msp tab
     */
    public void clickOnMspTab()
    {
        Mapper.find(domFile,"checkMspButton").click();
    }

    /**
     * function to click on find box on cars home page
     */
    public void clickOnFindBox()
    {
        Mapper.waitForElementToBeVisible(domFile,"clickOnFindBox");
        Mapper.find(domFile,"clickOnFindBox").click();
    }

    /**
     * function to click on RequiredTextFindBox
     */
    public void clickOnRequiredTextFindBox()
    {
        Mapper.find(domFile,"clickFindTextBox").click();
    }

    /**
     * find to set the required text
     */
    public void setRequiredTextInFindBox(String str)
    {
        System.out.println(str);
        Mapper.find(domFile,"setTextForFind").sendKeys(str);
    }

    /**
     * function to select find cars button
     */
    public void selectFindCarsButton()
    {
        Mapper.find(domFile,"findCarsButton").click();
    }

    /**
     * function to select find bikes button
     */
    public void selectFindBikeButton()
    {
        Mapper.find(domFile,"findBikesButton").click();
    }

}
