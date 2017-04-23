package com.quikr.app.ios.cars.carsSnbPage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.cars.carsHomePage.CarsHomePage;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.realEstate.realEstateHeaderPage.RealEstateHeaderPage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.snb.SnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 1/25/16.
 */
public class CarsSnbPageTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_CARSPAGE_TESTDATA_FILE"));

    /**
     * verify search results for cars reflected on snb page of cars
     */
    @Title("search results for cars reflected on snb page of cars")
    @Test
    public void verifySearchResultsReflectionOnSnbPageOfCars()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);
        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
        CarsSnbPage carsSnbPage=new CarsSnbPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

        // homePage.clickOnSelectCity();
       // homePage.selectCrossButtonOnAllowLocationPopup();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        carsHomePage.clickOnFindBox();
        carsHomePage.clickOnFindBox();
        carsHomePage.clickOnRequiredTextFindBox();
        carsHomePage.setRequiredTextInFindBox(testData.get("carModelName"));
        realEstateHeaderPage.selectSearchButtonOfKeyboard();
        carsHomePage.selectFindCarsButton();
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        Assert.assertTrue(carsSnbPage.validateCarsOrBikesSearchResultOnCarsSnbPage(),"required search  name for cars is missing in title");
    }

    /**
     * verify search results for bikes & reflected on snb page
     */
    @Title("search results for bikes & reflected on snb page")
    @Test
    public void verifySearchResultsForBikesOnSnbPage()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);
        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
        CarsSnbPage carsSnbPage=new CarsSnbPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        carsHomePage.clickOnBikeTabOnCarsHomePage();
        carsHomePage.clickOnFindBox();
        carsHomePage.clickOnFindBox();
        carsHomePage.clickOnRequiredTextFindBox();
        carsHomePage.setRequiredTextInFindBox(testData.get("brandOptionNameForBike"));
        realEstateHeaderPage.selectSearchButtonOfKeyboard();
        carsHomePage.selectFindBikeButton();
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        Assert.assertTrue(carsSnbPage.validateCarsOrBikesSearchResultOnCarsSnbPage(),"required search  name for bike  is missing in title ");
    }


    /**
     * verify the options present in sort options
     *
     * verify ascending sort for cars
     */
    @Title("options present in sort options and verify ascending sort for cars")
    @Test
    public void verifyOptionPresentInSortAndVerifyAscendingSortForCar()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);
        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
        CarsSnbPage carsSnbPage=new CarsSnbPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
        SnbPage snbPage=new SnbPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        carsHomePage.clickOnFindBox();
        carsHomePage.clickOnFindBox();
        carsHomePage.clickOnRequiredTextFindBox();
        carsHomePage.setRequiredTextInFindBox(testData.get("carModelName"));
        realEstateHeaderPage.selectSearchButtonOfKeyboard();
        carsHomePage.selectFindCarsButton();
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        Assert.assertTrue(carsSnbPage.validateCarsOrBikesSearchResultOnCarsSnbPage(),"required search  name for cars is missing in title");
        realEstateSnbPage.clickOnSortButtonInRealEstateSnbPage();
        Assert.assertTrue(carsSnbPage.validateCarsSortOptions(),"all the required options are  not present in sort ");
        realEstateSnbPage.selectLowestPrice();
        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage(),"some issue in sorting as needed");
    }

    /**
     * verify the options present in sort options
     *
     * verify ascending sort for bikes
     */
    @Title("options present in sort options and verify ascending sort for bikes ")
    @Test
    public void verifyOptionPresentInSortAndVerifyAscendingSortForBikes()
    {
        HomePage homePage=new HomePage(driver);
        CarsHomePage carsHomePage=new CarsHomePage(driver);
        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
        CarsSnbPage carsSnbPage=new CarsSnbPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
        SnbPage snbPage=new SnbPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        carsHomePage.clickOnBikeTabOnCarsHomePage();
        carsHomePage.clickOnFindBox();
        carsHomePage.clickOnFindBox();
        carsHomePage.clickOnRequiredTextFindBox();
        carsHomePage.setRequiredTextInFindBox(testData.get("brandOptionNameForBike"));
        realEstateHeaderPage.selectSearchButtonOfKeyboard();
        carsHomePage.selectFindBikeButton();
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        Assert.assertTrue(carsSnbPage.validateCarsOrBikesSearchResultOnCarsSnbPage(),"required search  name for bike  is missing in title ");
        realEstateSnbPage.clickOnSortButtonInRealEstateSnbPage();
        Assert.assertTrue(carsSnbPage.validateCarsSortOptions(),"all the required options are  not present in sort  for bikes ");
        realEstateSnbPage.selectLowestPrice();
        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage()," ascending sort data aren't coming");
    }
}
