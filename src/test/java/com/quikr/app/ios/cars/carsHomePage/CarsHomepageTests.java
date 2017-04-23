package com.quikr.app.ios.cars.carsHomePage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 1/19/16.
 */
public class CarsHomepageTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_CARSPAGE_TESTDATA_FILE"));

    /**
     * verify the subcategory present in cars and bike category
     */
    @Title("subcategory present in cars and bike category")
    @Test
    public void verifySubcategoryInCarsAndBikes()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);

        //homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        Assert.assertTrue(carsHomePage.validateSubCategoryOfCarsAndBikes(),"all the subcategory of cars and bikes are not present");
    }

    /**
     * verify msp tab present on car's home page
     */
    @Title("verify msp tab present on car's home page")
    @Test
    public void verifyMspTab()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);

       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        Assert.assertTrue(carsHomePage.validateMspTabPresentOrNot(),"msp tab button not present on car's home page");
    }
}
