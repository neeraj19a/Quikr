package com.quikr.app.ios.realEstate.realEstateHomePage;

import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 20/11/15.
 */
public class RealEstateHomePage extends com.quikr.app.ios.AppiOSPageBase {
    public RealEstateHomePage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_REALESTATEHOMEPAGE_DOM_FILE");

    /**
     * function to validate category present on realEstate home page
     */
    public boolean validateRealEstateHomePageCategory()
    {
        return (isElementPresent("residential") && isElementPresent("commercial") && isElementPresent("agriculture"));
    }

    /**
     * function to click on subcategory of realEstate category
     */
    public void clickOnSubCategoryOfRealEstateCategory()
    {
        Mapper.find(domFile,"subCategoryOfRealEstateCategory").click();
    }

    /**
     * function to validate subcategory of realEstate category
     */
    public boolean validateSubCategoryOfResidentialCategory()
    {
        return (isElementPresent("rent") && isElementPresent("buy") && isElementPresent("pgOrFlat"));
    }

    /**
     * function to select buy button
     */
    public void selectBuyButton()
    {
        Mapper.find(domFile,"buy").click();
    }

    /**
     * function to select pg or flat in residential category
     */
    public void selectPgOrFlat()
    {
        Mapper.find(domFile,"pgOrFlat").click();
    }
    /**
     * function to validate subcategory of commercial category
     */
    public boolean validateSubCategoryOfCommercialCategory()
    {
        return (isElementPresent("rent") && isElementPresent("buy"));
    }

    /**
     * function to select rent
     */
    public void selectRentButton()
    {
        Mapper.find(domFile,"rent").click();
    }

    /**
     * function to validate subcategory of agriculture category
     */
    public boolean validateAgricultureSubCategory()
    {
        return isElementPresent("buy");
    }

    /**
     * function to click on locality,projects or builders search box
     */
    public void clickOnLocalityOrProjectsOrBuildersSearchBox()
    {
        Mapper.find(domFile,"clickOnLocalityOrProjectsOrBuildersSearchBox").click();
    }
}
