package com.quikr.app.ios.quikrX.quikrXSnbPageTests;


import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.quikrX.quikrXSnbPage.QuikrXSnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
/**
 * Created by Manvendra Singh on 14/10/15.
 */
public class QuikrXSnbPageTests extends AppiOSTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_QUIKRXSNBPAGE_TESTDATA_FILE"));



    /**
     *  iOS-279:"Select a Brand new phone " banner
     *
     *  iOS-284:Sort option in SnB of "Exchange" or "Certified" screens
     */
    @Title("Brand new phone \" banner and Sort option in SnB of \"Exchange\" or \"Certified\" screens")
    @Test
    public void verifySnbScreenAfterClickOnExchangeOldForNew()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
    }

    /**
     *
     * QX_01
     *
     * Verify SnB of 'Exchange' when app city is supported city that is Bangalore
     */
    @Title("Verify SnB of 'Exchange' when app city is supported city that is Bangalore")
    @Test
    public void verifySnbScreenAfterClickOnExchangeOldForNewFromHomeScreen()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
    }

    /**
     *     iOS-281:Banner of "Certified" screen
     *
     *     iOS-284:Sort option in SnB of "Exchange" or "Certified" screens
     */
    @Title("Banner of \"Certified\" screen and Sort option in SnB of \"Exchange\" or \"Certified\" screens")
    @Test
    public void verifySnbScreenAfterClickOnBuyCertifiedUsedPhones()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);

       // homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("buyCertifiedUsedPhone"));
        Assert.assertTrue(quikrXSnbPage.validateBuyCertifiedUsedPhoneTextOnSnbPage(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
    }

    /**
     * QX_02
     *
     * Verify SnB of 'Certified' when app city is supported city that is Bangalore
     */
    @Title("Verify SnB of 'Certified' when app city is supported city that is Bangalore")
    @Test
    public void verifySnbScreenAfterClickOnBuyCertifiedUsedPhonesFromHomePage()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);

        homePage.selectCityName();
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateBuyCertifiedUsedPhoneTextOnSnbPage(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
    }

    /**
     * iOS-286:Options format of Filter in SnB of "Certified and Exchange"
     */
    @Title("Options format of Filter in SnB of \"Certified and Exchange\"")
    @Test
    public void verifyFilterOptionsForExchange()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);

        //homePage.selectDontAllowOption();
     //   homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.clickOnFilterButtonOnSnbPage();
        Assert.assertTrue(quikrXSnbPage.validateFilterOptions(),"all filter options are not present");

    }

    /**
     * iOS-286:Options format of Filter in SnB of "Certified and Exchange"
     */
    @Title("Options format of Filter in SnB of \"Certified and Exchange\"")
    @Test
    public void verifyFilterOptionsForCertified()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);

       // homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("buyCertifiedUsedPhone"));
        Assert.assertTrue(quikrXSnbPage.validateBuyCertifiedUsedPhoneTextOnSnbPage(), "snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.clickOnFilterButtonOnSnbPage();
        Assert.assertTrue(quikrXSnbPage.validateFilterOptions(),"all filter options are not present");
    }
}
