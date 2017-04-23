package com.quikr.app.ios.cars.carsMspPage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.cars.carMsp.CarMspPage;
import com.quikr.app.ios.cars.carsHomePage.CarsHomePage;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by Manvendra Singh on 1/21/16.
 */
public class CarsMspTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_CARSPAGE_TESTDATA_FILE"));

    /**
     * check the functionality of check msp button on car home page
     *
     * verify all  the options present on msp page
     */
    @Title("verify all  the options present on msp page")
    @Test
    public void verifyAllOptionPresentOnMspPage()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);
        CarMspPage carMspPage=new CarMspPage(driver);

       // homePage.clickOnSelectCity();
       // homePage.selectCrossButtonOnAllowLocationPopup();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        carsHomePage.clickOnMspTab();
        Assert.assertTrue(carMspPage.validateAllTabOnMspPage(),"all option on msp page are not present");
    }

    /**
     * check the functionality of  msp
     *
     * verify post ad button is display on msp result page
     */
    @Title("check the functionality of  msp")
    @Test
    public void verifyMsp()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);
        CarMspPage carMspPage=new CarMspPage(driver);
        PapPage papPage=new PapPage(driver);

        // homePage.clickOnSelectCity();
       // homePage.selectCrossButtonOnAllowLocationPopup();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        carsHomePage.clickOnMspTab();
        Assert.assertTrue(carMspPage.validateAllTabOnMspPage(),"all option on msp page are not present");
        carMspPage.clickOnIma();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("youAreTypeInCarOrBikeMspPage"));
        carMspPage.selectNameAsRequiredSearch(2);
        papPage.clickOnBrandName();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("brandOptionName"));
        carMspPage.selectNameAsRequiredSearch(2);
        carMspPage.clickOnModelNameOnMspPage();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("carModelName"));
        carMspPage.selectNameAsRequiredSearch(2);
        carMspPage.clickOnYearOfMake();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("yearOfMakeOptionName"));
        carMspPage.selectNameAsRequiredSearch(2);
        carMspPage.clickOnVariantTabOnMsp();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("variantOptionName"));
        carMspPage.selectNameAsRequiredSearch(2);
        carMspPage.clickOnKmsDriven();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("kmsDrivenOptionName"));
        carMspPage.selectNameAsRequiredSearch(2);
        carsHomePage.clickOnMspTab();
        Assert.assertTrue(carMspPage.validateMsp(),"msp result page is not present for car");
        Assert.assertTrue(carMspPage.validatePostAdFreeButtonMspPage(),"post free ad button can't display");
    }

    /**
     * verify  msp option's name  in msp page for bike
     *
     * verify the msp calculation for BIKE
     *
     * verify post ad button is display on msp result page
     */
    @Title("msp calculation for BIKE")
    @Test
    public void verifyMspForBike()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);
        CarMspPage carMspPage=new CarMspPage(driver);
        PapPage papPage=new PapPage(driver);

        // homePage.clickOnSelectCity();
       // homePage.selectCrossButtonOnAllowLocationPopup();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        carsHomePage.clickOnMspTab();
        carMspPage.selectBikeTab();
        Assert.assertTrue(carMspPage.validateAllOptionNameForBikeInMspPage(),"all options in msp page for bike are not present");
        carMspPage.clickOnIma();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("youAreTypeInCarOrBikeMspPage"));
        carMspPage.selectNameAsRequiredSearch(2);
        papPage.clickOnBrandName();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("brandOptionNameForBike"));
        carMspPage.selectNameAsRequiredSearch(2);
        carMspPage.clickOnModelNameOnMspPage();
        carMspPage.selectNameAsRequiredSearch(2);
        carMspPage.clickOnModelNameOnMspPage();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("modelOptionNameForBike"));
        carMspPage.selectNameAsRequiredSearch(2);
        carMspPage.clickOnYearOfMake();
        carMspPage.setTextInSearchBoxForCarMsp(testData.get("yearOfMakeOptionName"));
        carMspPage.selectNameAsRequiredSearch(2);
        carMspPage.clickOnCheckMspButtonForBike();
        Assert.assertTrue(carMspPage.validateMsp(),"msp result page is not display for bike");
        Assert.assertTrue(carMspPage.validatePostAdFreeButtonMspPage(),"post free ad button can't display");
    }
}
