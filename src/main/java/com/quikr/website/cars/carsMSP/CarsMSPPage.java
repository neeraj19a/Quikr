package com.quikr.website.cars.carsMSP;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 29/11/15.
 */
public class CarsMSPPage extends CarsPageBase {

    public CarsMSPPage(RemoteWebDriver driver)
    {
        super(driver);
    }

    private static final String domFile = getProperties().get("CARS_MSP_DOM_FILE");

    public void clickCars()
    {
        Mapper.waitForElementToBeVisible(domFile,"clickCars");
        Mapper.find(domFile,"clickCars").click();
    }

    public void clickBikes()
    {
        Mapper.waitForElementToBeVisible(domFile,"clickBikes");
        Mapper.find(domFile,"clickBikes").click();
    }

    public void clickImBuying()
    {
        Mapper.waitForElementToBeVisible(domFile,"clickBuying");
        Mapper.find(domFile,"clickBuying").click();
    }

    public void clickImSelling()
    {
        Mapper.waitForElementToBeVisible(domFile,"clickSelling");
        Mapper.find(domFile,"clickSelling").click();
    }

    public void clickBrand()
    {
        Mapper.waitForElementToBeClickable(domFile,"clickBrand");
        Mapper.find(domFile,"clickBrand").click();
    }

    public void selectBrand(String brandToSelect)
    {
        clickBrand();

        Mapper.waitForElementToBeVisible(domFile,"selectBrand", new String[]{brandToSelect});
        Mapper.findAndReplace(domFile,"selectBrand", new String[]{brandToSelect}).click();
    }

    public void clickModel()
    {
        Mapper.waitForElementToBeClickable(domFile,"clickModel");
        Mapper.find(domFile,"clickModel").click();
    }


    public void selectModel(String modelToSelect)
    {
        clickModel();

        Mapper.waitForElementToBeVisible(domFile,"selectModel", new String[]{modelToSelect});
        Mapper.findAndReplace(domFile,"selectModel", new String[]{modelToSelect}).click();
    }


    public void clickYearOfMake(String yearToSelect)
    {
        Mapper.waitForElementToBeVisible(domFile,"selectYear", new String[]{yearToSelect});
        Mapper.findAndReplace(domFile,"selectYear", new String[]{yearToSelect}).click();
    }

    public void clickVariant()
    {
        Mapper.waitForElementToBeVisible(domFile,"clickVariant");
        Mapper.find(domFile,"clickVariant").click();
    }

    public void selectVariant(String variantToSelect)
    {
        clickVariant();

        Mapper.waitForElementToBeVisible(domFile,"selectVariant", new String[]{variantToSelect});
        Mapper.findAndReplace(domFile,"selectVariant", new String[]{variantToSelect}).click();
    }

    public void clickKmsDriven(String kmsDrivenToSelect)
    {
        Mapper.waitForElementToBeVisible(domFile,"selectKmsDriven", new String[]{kmsDrivenToSelect});
        Mapper.findAndReplace(domFile,"selectKmsDriven", new String[]{kmsDrivenToSelect}).click();
    }

    public void carsSubmit()
    {
        Mapper.waitForElementToBeVisible(domFile,"carSubmit");
        Mapper.find(domFile,"carSubmit").click();
    }

    public void bikesSubmit()
    {
        Mapper.waitForElementToBeVisible(domFile,"bikeSubmit");
        Mapper.find(domFile,"bikeSubmit").click();
    }

    public boolean validateMSPHeadingText(String headingText)
    {
        boolean mspHeadingStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "mspHeading");
        if(Mapper.find(domFile, "mspHeading").getText().equals(headingText))
            mspHeadingStatus = true;

        return mspHeadingStatus;
    }

    public String mspMinPrice()
    {
        Mapper.waitForElementToBeVisible(domFile,"mspMinValue");
        return Mapper.find(domFile,"mspMinValue").getText();
    }

    public String mspMaxPrice()
    {
        Mapper.waitForElementToBeVisible(domFile,"mspMaxValue");
        return Mapper.find(domFile,"mspMaxValue").getText();
    }

    public boolean mspRangeElement()
    {
        return Mapper.waitForElementToBeVisible(domFile,"mspRangeElement");
    }

    public boolean mspStatus()
    {
        return Mapper.waitForElementToBeVisible(domFile,"mspMinValue") && Mapper.waitForElementToBeVisible(domFile,"mspMaxValue") && Mapper.waitForElementToBeVisible(domFile,"mspRangeElement");
    }

    public String adCount() {
        Mapper.waitForElementToBeVisible(domFile,"adCount");
        return Mapper.find(domFile,"adCount").getText();
    }

    public void setMobileNumber(String number)
    {
        Mapper.waitForElementToBeVisible(domFile,"mobileNumber");
        Mapper.find(domFile,"mobileNumber").sendKeys(number);
    }

    public void setEmailId(String emailId)
    {
        Mapper.waitForElementToBeVisible(domFile,"emailId");
        Mapper.find(domFile,"emailId").sendKeys(emailId);
    }

    public void submitAlert(){

        Mapper.waitForElementToBeVisible(domFile,"createAlert");
        Mapper.find(domFile,"createAlert").click();
    }

    public String getAlertMessage()
    {
        Mapper.waitForElementToBeVisible(domFile, "alertMessage");
        return Mapper.find(domFile, "alertMessage").getText();
    }
}
