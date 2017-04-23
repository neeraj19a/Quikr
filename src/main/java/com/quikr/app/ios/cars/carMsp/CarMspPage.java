package com.quikr.app.ios.cars.carMsp;

import com.quikr.app.ios.AppiOSPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by Manvendra Singh on 1/21/16.
 */
public class CarMspPage extends AppiOSPageBase{

    public CarMspPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_CARSMSPPAGE_DOM_FILE");

    /**
     * function to validate tab on msp page
     */
    public boolean validateAllTabOnMspPage()
    {
        return (isElementPresent("calculateMsp") && isElementPresent("carsTab") && isElementPresent("bikesTab") && isElementPresent("imaTab") && isElementPresent("brandNameTab") && isElementPresent("modelNameTab") && isElementPresent("yearOfMakeTab") && isElementPresent("VariantTab") && isElementPresent("kmsDrivenTab") && isElementPresent("checkMspTab"));
    }

    /**
     * function to validate bike msp page all option's name
     */
    public boolean validateAllOptionNameForBikeInMspPage()
    {
        return (isElementPresent("imaTab") && isElementPresent("brandNameTab") && isElementPresent("modelNameTab") && isElementPresent("yearOfMakeTab") && isElementPresent("checkMspTab"));
    }

    /**
     * function to click on Bike Tab
     */
    public void selectBikeTab()
    {
        Mapper.find(domFile,"bikesTab").click();
    }

    /**
     * function to click on I'ma
     */
    public void clickOnIma()
    {
        Mapper.find(domFile,"imaTab").click();
    }

    /**
     * function to select Name as required search in search box in car's msp page
     */
    public void selectNameAsRequiredSearch(int i)
    {
        Mapper.findAndReplace(domFile, "clickOnRequiredSearchOptionName", new String[]{Integer.toString(i)}).click();
    }

    /**
     * function to set text as required search in car msp page
     */
    public void setTextInSearchBoxForCarMsp(String str)
    {
        Mapper.find(domFile,"setTextInSearchBox").sendKeys(str);
    }

    /**
     * function to click om model name in msp page
     */
    public void clickOnModelNameOnMspPage()
    {
        Mapper.find(domFile,"clickOnModelNameOnMspPage").click();
    }

    /**
     * function to click on year of make
     */
    public void clickOnYearOfMake()
    {
        Mapper.find(domFile,"yearOfMakeTab").click();
    }

    /**
     * function to click On variant on msp page
     */
    public void clickOnVariantTabOnMsp()
    {
        Mapper.find(domFile,"VariantTab").click();
    }

    /**
     * function to click on km's driven
     */
    public void clickOnKmsDriven()
    {
        Mapper.find(domFile,"kmsDrivenTab").click();
    }

    /**
     * function to validate msp calculate page is present or not
     */
    public boolean validateMsp()
    {
        return (isElementPresent("mspResult") && isElementPresent("calculatingMspAgain"));
    }
    /**
     * function to click on check msp button for bike
     */
    public void clickOnCheckMspButtonForBike()
    {
        Mapper.find(domFile,"mspButtonForBike").click();
    }

    /**
     * function to validate post button on car msp page
     */
    public boolean validatePostAdFreeButtonMspPage()
    {
        return isElementPresent("postAdFreeButton");
    }
}
