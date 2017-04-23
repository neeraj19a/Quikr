package com.quikr.app.ios.realEstate.realEstateHomePage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 20/11/15.
 */
public class RealEstateHomePageTests extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_REALESTATEHOMEPAGE_TESTDATA_FILE"));

    /**
     *   iOS-2433:Verify the categories present in Real Estate UI
     */
    @Title("categories present in Real Estate UI")
    @Test
    public void verifyCategoryPresentInRealEstateHomePage()
    {
        HomePage homePage=new HomePage(driver);
        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);

       // homePage.selectDontAllowOption();
        //homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        Assert.assertTrue(realEstateHomePage.validateRealEstateHomePageCategory(),"all category not present on realEstate home page");
    }

    /**
     * iOS-2434:Verify the subcategories present in 'Residential'
     */
    @Title("subcategories present in 'Residential'")
    @Test
    public void verifySubcategoryOfResidential()
    {
        HomePage homePage=new HomePage(driver);
        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
        Assert.assertTrue(realEstateHomePage.validateSubCategoryOfResidentialCategory(),"all subcategory of residential are not present");
    }

    /**
     *  iOS-2435:Verify the subcategories present in 'Commercial'
     */
    @Title("subcategories present in 'Commercial'")
    @Test
    public void verifySubcategoryOfCommercial()
    {
        HomePage homePage=new HomePage(driver);
        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);

       // homePage.selectDontAllowOption();
        //homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("realEstateCommercialCategory"));
        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
        Assert.assertTrue(realEstateHomePage.validateSubCategoryOfCommercialCategory(),"all subcategory of commercial category don't present");
    }

    /**
     * iOS-2436:Verify the subcategory present in 'Agriculture'
     */
    @Title("subcategory present in 'Agriculture'")
    @Test
    public void verifySubcategoryOfAgriculture()
    {
        HomePage homePage=new HomePage(driver);
        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);

     //   homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("realEstateAgricultureCategory"));
        realEstateHomePage.clickOnSubCategoryOfRealEstateCategory();
        Assert.assertTrue(realEstateHomePage.validateAgricultureSubCategory(),"subcategory of agriculture category are not display");
    }
}
