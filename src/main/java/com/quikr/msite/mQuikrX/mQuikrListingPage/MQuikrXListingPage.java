package com.quikr.msite.mQuikrX.mQuikrListingPage;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 9/11/15.
 */
public class MQuikrXListingPage extends MPageBase {

    private static final String domFile = getProperties().get("mQuikrListing_DOM_FILE");

    public MQuikrXListingPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    public void clickFilter(){
        Mapper.find(domFile,"openFilter").click();
    }

    public void clickQuikrXphones() {
        Mapper.find(domFile, "quikXPhoneLink").click();
    }

    public void selectExchangePhone() {
        Mapper.find(domFile, "newPhonesWithExchange").click();
    }

    /**
     * Select Exchange phone option
     */
    public void selectCertified() {
        Mapper.find(domFile, "CertifiedPhoneOption").click();
    }

    public boolean isQuikrXPageavailable() {

        boolean flag = false;
        if (isElementPresent("refurbishedPhones")) {
            flag = true;
        } else {
            flag = false;
        }

        if (isElementPresent("unBoxedPhones")) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    public void clickpriceFilter() {
        Mapper.waitForElementToBeClickable(domFile,"applyFilter");
        Mapper.find(domFile, "priceFilter").click();
    }

    public void clickbrandFilter() {
        Mapper.waitForElementToBeClickable(domFile,"applyFilter");
        Mapper.find(domFile, "brandFilter").click();
    }

    public void clickOsFilter() {
        Mapper.waitForElementToBeClickable(domFile,"applyFilter");
        Mapper.find(domFile, "OsFilter").click();
    }

    public void clickcategoryFilter() {
        Mapper.waitForElementToBeClickable(domFile,"applyFilter");
        Mapper.find(domFile, "categoryFilter").click();
    }

    public void clicksimFilter() {
        Mapper.waitForElementToBeClickable(domFile,"applyFilter");
        Mapper.find(domFile, "simFilter").click();
    }

    public void clickdisaplayFilter() {
        Mapper.waitForElementToBeClickable(domFile,"applyFilter");
        Mapper.find(domFile, "disaplayFilter").click();
    }

    public void clickcameraFilter() {
        Mapper.waitForElementToBeClickable(domFile,"applyFilter");
        Mapper.find(domFile, "cameraFilter").click();
    }

    public void clickramFilter() {
        Mapper.waitForElementToBeClickable(domFile,"applyFilter");
        Mapper.find(domFile, "ramFilter").click();
    }

    public void selectPriceFilter() {
        Mapper.find(domFile, "firstPrice").click();
    }

    public void selectbrandFilter(String item) {
        Mapper.findAndReplace(domFile, "selectBrand",new String[]{item}).click();
    }

    public void selectOsFilter(String item) {
        Mapper.waitForElementToBeClickable(domFile,"osTextField");
        Mapper.findAndReplace(domFile, "osSelect",new String[]{item}).click();
    }

    public void selectCategoryFilter(String item) {
        Mapper.findAndReplace(domFile, "categorySelect",new String[]{item}).click();
    }

    public void selectSimFilter(String item) {
        Mapper.findAndReplace(domFile, "simSelect",new String[]{item}).click();
    }

    public void selectDisaplayFilter() {
        Mapper.find(domFile, "screenSizeSelect").click();
    }

    public void selectCameraFilter() {Mapper.find(domFile, "cameraSelect").click();
    }

    public void selectRamFilter(String item) {
        Mapper.findAndReplace(domFile, "ramSize",new String[]{item}).click();
    }

    public void applyFilter(){
        Mapper.find(domFile,"applyFilter").click();
    }

}