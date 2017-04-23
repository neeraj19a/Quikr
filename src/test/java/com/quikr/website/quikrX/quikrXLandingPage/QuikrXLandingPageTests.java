package com.quikr.website.quikrX.quikrXLandingPage;

import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXNewListingPage.QuikrXNewListingPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Map;

/**
 * Created by francis.s@quikr on 22/2/16.
 */
public class QuikrXLandingPageTests extends QuikrXTestBase{

    private final static Log LOGGER = LogFactory.getLog(QuikrXLandingPageTests.class.getName());
    String city = testData.get("newUiCity");

    @BeforeMethod(alwaysRun = true)
    public void setUp(){

        HomePage homePage = new HomePage(driver);

        homePage.selectCity(city);
        waitForPageToLoad(city);
        homePage.clickNewQuikrX();
        waitForPageToLoad("QuikrX");
        LOGGER.info("navigate to quikrX landing page");
    }


    @Title("Test to validate quikrX landing page")
    @Description("validate refurbished unboxed and exchange new quikrC landing page")
    @Test(dataProvider = "quikrXLandingPage", dataProviderClass = Data.class,groups = {"QuikrXHighPrio","prodTest"},priority=1)
    public void refurbishedUiValidation(String category,String ctaText,String saveText,String defText){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);

        LOGGER.info("validating UI for "+ category + " landing page");
        if(category.equalsIgnoreCase("refurbished")) {
            landingPage.clickRefurbishedTab();
            Assert.assertTrue(landingPage.refurbishedcheckListPresent(),"check list not displayed");
            Assert.assertEquals(landingPage.refurbishedSavetext(),saveText,"refurbished save text misatch");
            Assert.assertEquals(landingPage.refurbishedDeftext(),defText," refurbished description text mismatch");
        }
        else if (category.equalsIgnoreCase("unboxed")) {
            landingPage.clickUnboxedTab();
            Assert.assertTrue(landingPage.unboxedCheckListPresent(), "unboxed checklist missing");
            Assert.assertEquals(landingPage.unboxedSavetext(),saveText,"unboxed save text mimatch");
            Assert.assertEquals(landingPage.unboxedDeftext(),defText," unboxed description text mismatch");
        }
        else {
            landingPage.clickExchangeTab();
            Assert.assertTrue(landingPage.exchangeCheckListPresent(), "exchange checklist missing");
            Assert.assertEquals(landingPage.exchangeSavetext(),saveText,"exchange save text mimatch");
            Assert.assertEquals(landingPage.exchangeDeftext(),defText," exchange description text mismatch");
        }

        Assert.assertTrue(landingPage.isAndroidDisplayed(),"all android phones not disaplyed");
        Assert.assertTrue(landingPage.shopByBrandDisplayed(),"shop by brand not displayed");
        Assert.assertTrue(landingPage.seeAllTrendingPresent(),"see all trending not displayed");
        Assert.assertTrue(landingPage.isfilterScrollerDisplayed(),"new filter category missing");
        Assert.assertTrue(landingPage.isPrimaryCtaDisplayed(),"primay cta missing");
        Assert.assertEquals(landingPage.getPrimaryCtaText(),ctaText,"mismatch in primary cta text");
        LOGGER.info("ui validation of "+ category+" page done");

    }

    @Title("Test to validate shop by brand")
    @Description("click on shop by brand names , validate correct landing page")
    @Test(dataProvider = "quikrXLandingPage", dataProviderClass = Data.class)
    public void shopByBrand(String category,String ctaText,String saveText,String defText){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("Validate shop by brand urls for "+category);

        selectCategory(category);

        Map<String,String> map = landingPage.brandUrls();
        for(String key:map.keySet()){
            navigatethirdparty(driver,map.get(key));
            waitForPageToLoad(key);
           Assert.assertTrue(listingPage.filteresValuesContains(key));
        }

        LOGGER.info("Validation done for shop by brand urls for "+category);

    }


    @Title("Test phone by price")
    @Description("click on price filters and verify if filter is applied in listing page")
    @Test(dataProvider = "quikrXLandingPage", dataProviderClass = Data.class)
    public void filterByPrice(String category,String ctaText,String saveText,String defText){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("Validate shop by price category for  "+category);

        selectCategory(category);

        Map<String,String> map = landingPage.priceFilter(category);
        for(String key:map.keySet()){
            navigatethirdparty(driver,map.get(key));
            waitForPageToLoad(key);
            if(!category.equalsIgnoreCase("new"))
               Assert.assertTrue(listingPage.filteresValuesContains(category));

            Assert.assertTrue(listingPage.filteresValuesContains(key));
        }

        LOGGER.info("Validation done  shop by price category for "+category);

    }


    @Title("Test phone by different filters")
    @Description("click on  filters below Get the best prices and verify if filter is applied in listing page")
    @Test(dataProvider = "quikrXLandingPage", dataProviderClass = Data.class)
    public void sliderCategoryTest(String category,String ctaText,String saveText,String defText){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("Validate all slider filters for "+category);

        selectCategory(category);
        Map<String,String> map = landingPage.sliderCategoryUrls();
        for(String key:map.keySet()){
            navigatethirdparty(driver,map.get(key));

                if (!key.equalsIgnoreCase("Low to High")){
                    waitForPageToLoad(key);
                    Assert.assertTrue(listingPage.filteresValuesContains(key), key + " filter is broken");
            }
            else {
                    waitForPageToLoad("order=priceAsc");
                int firstListingprice = listingPage.getFirstListingDiscPrice();
                int secondListingprice = listingPage.getSecondListingDiscPrice();
                Assert.assertTrue(firstListingprice < secondListingprice, "saving phones filter is broken");
            }

        }

        LOGGER.info("Validation done all slider filters for "+category);

    }

    @Test(dataProvider = "quikrXLandingPage", dataProviderClass = Data.class)
    public void androidPhonesFilter(String category,String ctaText,String saveText,String defText){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("validate see all android filters for "+category);

        selectCategory(category);
        Assert.assertTrue(landingPage.isAndroidDisplayed());
        landingPage.clickSeeAllAndroids();
        if(!category.equalsIgnoreCase("new"))
            Assert.assertTrue(listingPage.filteresValuesContains(category));
        listingPage.filteresValuesContains("Android");

        LOGGER.info("validated see all android filters for "+category);

    }

    @Test(dataProvider = "quikrXLandingPage", dataProviderClass = Data.class)
    public void applePhonesFilter(String category,String ctaText,String saveText,String defText){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("validate see all android filters for "+category);

        selectCategory(category);
        landingPage.clickAllApplePhones();
        if(!category.equalsIgnoreCase("new"))
            Assert.assertTrue(listingPage.filteresValuesContains(category));
        listingPage.filteresValuesContains("Apple");

        LOGGER.info("validated see all android filters for "+category);

    }

    @Test(dataProvider = "quikrXLandingPage", dataProviderClass = Data.class)
    public void seeAllPrimaryCta(String category,String ctaText,String saveText,String defText){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("validate see all android filters for "+category);

        if(category.equalsIgnoreCase("refurbished")) {
            landingPage.clickRefurbishedTab();
            landingPage.clickSeeAllrefurbishedPhone();
            waitForPageToLoad(category);
            Assert.assertTrue(listingPage.filteresValuesContains(category));
        }
        else if (category.equalsIgnoreCase("unboxed")) {
            landingPage.clickUnboxedTab();
            landingPage.clickSeeAllUnboxedPhone();
            waitForPageToLoad(category);
            Assert.assertTrue(listingPage.filteresValuesContains(category));
        }
        else {
            landingPage.clickExchangeTab();
            landingPage.clickSeeAllExchangePhone();
            waitForPageToLoad(category);
            Assert.assertEquals(listingPage.isNewTabselected(),"QuikrX - Exchange Mobile Phones","new phones not displayed");
        }

    }


  private void selectCategory(String category){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);

        if(category.equalsIgnoreCase("refurbished"))
            landingPage.clickRefurbishedTab();
        else if (category.equalsIgnoreCase("unboxed"))
            landingPage.clickUnboxedTab();
        else
            landingPage.clickExchangeTab();
    }

}
