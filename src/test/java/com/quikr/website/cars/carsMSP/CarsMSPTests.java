package com.quikr.website.cars.carsMSP;

import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

;
/**
 * Created by Ankita Anshu on 29/11/15.
 */
public class CarsMSPTests extends CarsTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate navigation to Check Cars MSP for I m Buying from Cars HomePage
     */


    @Test(dataProvider = "checkCarsMsp", dataProviderClass = Data.class)
    public void CarMSPForImBuying(String brand, String model, String year, String variant, String kmsDriven) {

        String validationString = brand + model + variant;
        CarsMSPPage checkMSP = new CarsMSPPage(driver);
        navigateToPage("Check_MSP_HomePage", "CarsHomePage", false);
        calculateMsp("Cars","Buy",brand,model,year,variant,kmsDriven);
        Assert.assertTrue(checkMSP.validateMSPHeadingText(validationString),"Heading mismatch");
        Assert.assertTrue(checkMSP.mspStatus(), "Range mismatch");
    }

    /**
     * Testcase - Validate navigation to Check Cars MSP for I m Selling from Cars HomePage
     */

    @Test(dataProvider = "checkCarsMsp", dataProviderClass = Data.class)
    public void CarMSPForImSelling(String brand, String model, String year, String variant, String kmsDriven) {

        String validationString = brand + model + variant;
        CarsMSPPage checkMSP = new CarsMSPPage(driver);
        navigateToPage("Check_MSP_HomePage", "CarsHomePage", false);

        checkMSP.clickCars();
        checkMSP.closeCityPopup();
        checkMSP.clickImSelling();
        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(year);
        checkMSP.selectVariant(variant);
        checkMSP.clickKmsDriven(kmsDriven);
        checkMSP.carsSubmit();
        Assert.assertTrue(checkMSP.validateMSPHeadingText(validationString),"Heading mismatch");
        Assert.assertTrue(checkMSP.mspStatus(), "Range mismatch");
    }

    /**
     * Testcase - Validate navigation to Check Cars MSP for I m Buying from Cars CityPage
     */

    @Test(dataProvider = "checkCarsMsp", dataProviderClass = Data.class)
    public void CarMSPForImBuyingCity (String brand, String model, String year, String variant, String kmsDriven)
    {
        String validationString = brand + model + variant;
        CarsMSPPage checkMSP = new CarsMSPPage(driver);
        navigateToPage("Check_MSP_HomePage", "CarsCityPage", false);

        checkMSP.clickCars();
        checkMSP.clickImBuying();
        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(year);
        checkMSP.selectVariant(variant);
        checkMSP.clickKmsDriven(kmsDriven);
        checkMSP.carsSubmit();
        Assert.assertTrue(checkMSP.validateMSPHeadingText(validationString),"Heading mismatch");
        Assert.assertTrue(checkMSP.mspStatus(), "Range mismatch");
    }

    /**
     * Testcase - Validate navigation to Check Cars MSP for I m Selling from Cars CityPage
     */

   //    @Test(dataProvider = "checkCarsMsp", dataProviderClass = Data.class)
    public void CarMSPForImSellingCity (String brand, String model, String year, String variant, String kmsDriven)
    {
        String validationString = brand + model + variant;
        CarsMSPPage checkMSP = new CarsMSPPage(driver);
        navigateToPage("Check_MSP_HomePage", "CarsCityPage", false);

        checkMSP.clickCars();
        checkMSP.clickImBuying();
        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(year);
        checkMSP.selectVariant(variant);
        checkMSP.clickKmsDriven(kmsDriven);
        checkMSP.carsSubmit();
        Assert.assertTrue(checkMSP.validateMSPHeadingText(validationString),"Heading mismatch");
        Assert.assertTrue(checkMSP.mspStatus(), "Range mismatch");
    }

    /**
     * Testcase - Validate navigation to Check Bikes MSP for I m Buying from Cars HomePage
     */

    @Test(dataProvider = "checkBikesMsp", dataProviderClass = Data.class)
    public void bikesMSPForImBuying (String brand, String model, String yearOfMake)
    {
        String validationString = brand + model;
        CarsMSPPage checkMSP = new CarsMSPPage(driver);
        navigateToPage("Check_MSP_HomePage", "CarsHomePage", false);

        checkMSP.closeCityPopup();
        checkMSP.clickBikes();
        checkMSP.clickImBuying();
        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(yearOfMake);
        checkMSP.bikesSubmit();
        Assert.assertTrue(checkMSP.validateMSPHeadingText(validationString),"Heading mismatch");
        Assert.assertTrue(checkMSP.mspStatus(), "Range mismatch");
    }

    /**
     * Testcase - Validate navigation to Check Bikes MSP for I m Selling from Cars HomePage
     */

    @Test(dataProvider = "checkBikesMsp", dataProviderClass = Data.class)
    public void bikesMSPForImSelling (String brand, String model, String yearOfMake)
    {
        String validationString = brand + model + yearOfMake;
        CarsMSPPage checkMSP = new CarsMSPPage(driver);
        navigateToPage("Check_MSP_HomePage", "CarsHomePage", false);

        checkMSP.clickBikes();
        checkMSP.closeCityPopup();
        checkMSP.clickImSelling();
        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(yearOfMake);
        checkMSP.bikesSubmit();
        Assert.assertTrue(checkMSP.validateMSPHeadingText(validationString),"Heading mismatch");
        Assert.assertTrue(checkMSP.mspStatus(), "Range mismatch");
    }

    /**
     * Testcase - Validate navigation to Check Bikes MSP for I m Buying from Cars CityPage
     */

    @Test(dataProvider = "checkBikesMsp", dataProviderClass = Data.class)
    public void bikesMSPForImBuyingCity (String brand, String model, String yearOfMake)
    {
        String validationString = brand + model + yearOfMake;
        CarsMSPPage checkMSP = new CarsMSPPage(driver);
        navigateToPage("Check_MSP_HomePage", "CarsCityPage", false);

        checkMSP.clickBikes();
        checkMSP.clickImSelling();
        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(yearOfMake);
        checkMSP.bikesSubmit();
        Assert.assertTrue(checkMSP.validateMSPHeadingText(validationString),"Heading mismatch");
        Assert.assertTrue(checkMSP.mspStatus(), "Range mismatch");
    }

    /**
     * Testcase - Validate navigation to Check Bikes MSP for I m Selling from Cars CityPage
     */

    @Test(dataProvider = "checkBikesMsp", dataProviderClass = Data.class)
    public void bikesMSPForImSellingCity (String brand, String model, String yearOfMake)
    {
        String validationString = brand + model + yearOfMake;
        CarsMSPPage checkMSP = new CarsMSPPage(driver);
        navigateToPage("Check_MSP_HomePage", "CarsCityPage", false);

        checkMSP.clickBikes();
        checkMSP.clickImSelling();
        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(yearOfMake);
        checkMSP.bikesSubmit();
        Assert.assertTrue(checkMSP.validateMSPHeadingText(validationString),"Heading mismatch");
        Assert.assertTrue(checkMSP.mspStatus(), "Range mismatch");

    }
    /**
     * Testcase - Validate navigation to Check Alert on MSP Page
     */

    @Test(dataProvider = "checkCarsMsp", dataProviderClass = Data.class)
    public void createAlert (String brand, String model, String year, String variant, String kmsDriven)
    {
        CarsMSPPage checkMSP = new  CarsMSPPage (driver);
        navigateToPage("Check_MSP_HomePage", "CarsCityPage", false);

        checkMSP.clickCars();
        checkMSP.clickImBuying();
        checkMSP.selectBrand(brand);
        checkMSP.selectModel(model);
        checkMSP.clickYearOfMake(year);
        checkMSP.selectVariant(variant);
        checkMSP.clickKmsDriven(kmsDriven);
        checkMSP.carsSubmit();

        if(checkMSP.adCount().equals("0"))
        {
            //String emailid = getRandomString(6)+"@abcd.in";
            //String mobileNum = "9"+getRandomInteger(9);
            CarsMSPPage carsMSPPage = new CarsMSPPage(driver);
            carsMSPPage.setEmailId(testData.get("emailId"));
            carsMSPPage.setMobileNumber(testData.get("chatNumber"));
            carsMSPPage.submitAlert();

            String message = carsMSPPage.getAlertMessage().trim();
            Assert.assertTrue(message.equals(testData.get("alertMessage")), "Alert message mismatched, getting "+ message);
        }
    }
}




