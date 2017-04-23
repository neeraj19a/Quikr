package com.quikr.website.quikrX.quikrXHome;

import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXLandingPage.QuikrXLandingPage;
import com.quikr.website.quikrX.quikrXNewListingPage.QuikrXNewListingPage;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

/**
 * Created by francis.s@quikr on 24/11/15.
 */
public class QuikrXNewListingPageTests extends QuikrXTestBase {

    private final static Log LOGGER = LogFactory.getLog(QuikrXNewListingPageTests.class.getName());
    String city = testData.get("newUiCity");

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {

        HomePage homePage = new HomePage(driver);
        QuikrXLandingPage quikrHome = new QuikrXLandingPage(driver);

        sleep(4000);
        homePage.selectCity(city);
        waitForPageToLoad(city);
        homePage.clickNewQuikrX();
        waitForPageToLoad("QuikrX");
        quikrHome.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished-Mobile-Phones");

    }


    @Test(groups ={"QuikrXHighPrio","prodTest"})
    public void listingPageUiValidation(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing listing Page Ui Validation tests");

        Assert.assertTrue(listingPage.firstListingCameraDetailisPresent(),"camera details not disaplyed in the listing");
        Assert.assertTrue(listingPage.firstListingMemoryisPresent(),"memory details not disaplyed in the listing");
        Assert.assertTrue(listingPage.firstListingRamisPresent(),"ram details not dispalyed in the listing");
        Assert.assertTrue(listingPage.firstListingTitleisPresent(),"item name not displayed in the listing");
        Assert.assertTrue(listingPage.verifyCallUs(),"call us icon missing");
        Assert.assertTrue(listingPage.verifyEmailUs(),"email us icon missing");

        LOGGER.info("executed listing Page Ui Validation tests");

    }

    @Test
    public void priceFilterTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing price filter Validation tests");
        listingPage.clickPriceRangeFilter();
        listingPage.clickPriceRangeFIlterValue();
        waitForPageToLoad("Price-condition");
        sleep(5000);
        int priceAfterFilter = listingPage.getFirstListingDiscPrice();
        Assert.assertTrue(priceAfterFilter>2001&&priceAfterFilter<5000,"price filter not working");
        LOGGER.info("executed price filter Validation tests");

    }


    @Test
    public void brandFilterTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing brand filter test");
        listingPage.clickBrandFilter();
        listingPage.selectBrandFilter("Apple");
        Assert.assertTrue(listingPage.getFirstListingTitle().contains("Apple"),"brand filter not working");
        LOGGER.info("executed brand filter test");

    }


    @Test
    public void osFilterTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing os filter test");
        listingPage.clickoperatingSystemFilter();
        listingPage.selectopertatingSystemFilterValue("Android");
        Assert.assertTrue(listingPage.filteresValuesContains("Android"));
        LOGGER.info("executed os filter test");

    }


    @Test
    public void screenSizeFilter(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing screen size filter test");
        listingPage.clickMoreFileter();
        listingPage.selectScreenSizeFilter("3.5");
        listingPage.clickMoreFileter();
        listingPage.clickApplyFilterButton();
        String actual_val = listingPage.getFirstListingScreenSize();
        Assert.assertEquals(actual_val,"3.5", "screen size not working");
        LOGGER.info("executed screen size filter test");

    }

    @Test
    public void cameraSizeFilter(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing camera filter test");
        listingPage.clickMoreFileter();
        listingPage.selectcameraFilterValue("8");
        listingPage.clickMoreFileter();
        listingPage.clickApplyFilterButton();
        String actual_val = listingPage.getFirstListingCameraDetails();
        Assert.assertEquals(actual_val,"8", "screen size not working");
        LOGGER.info("executed camera filter test");

    }

    @Test
    public void createAlertTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing create alert test");

        listingPage.clickcreateAlertLink();
        listingPage.entercreateAlertPhone(testData.get("phoneNumber"));
        listingPage.entercreateAlertBrand(testData.get("phoneBrandName"));
        listingPage.entercreateAlertCity("Bangalore");
        listingPage.entercreateAlertEmail(RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com");
        listingPage.entercreateAlertModel(testData.get("phoneModelName2"));

        listingPage.clickcreateAlertSubmit();
        String message = listingPage.getAlertSuccessMessage();
        Assert.assertEquals(message,testData.get("alertSuccessMessage"),"create alert message not same");
        LOGGER.info("executed create alert test");

    }

    @Test
    public void sortListingTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing sorting test");
        listingPage.clickSortOptions("Low to High");
        int firstListingprice= listingPage.getFirstListingDiscPrice();
        int secondListingprice= listingPage.getSecondListingDiscPrice();
        Assert.assertTrue(firstListingprice<secondListingprice,"sort low to high not working");
        listingPage.clickSortOptions("High to Low");
        firstListingprice= listingPage.getFirstListingDiscPrice();
        secondListingprice= listingPage.getSecondListingDiscPrice();
        Assert.assertTrue(firstListingprice>secondListingprice,"sort high to low not working");

    }


    @Test
    public void ramFilterTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing ram filter test");
        listingPage.clickMoreFileter();
        listingPage.selectramFilterValue("1");
        listingPage.clickMoreFileter();
        listingPage.clickApplyFilterButton();
        String actual_val = listingPage.getFirstListingRamValue();
        Assert.assertEquals(actual_val,"1", "ram filter not working");
        LOGGER.info("executed ram filter test");
    }

    @Test
    public void countVsListTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing displayed count vs actual list");

        int displayedCount = listingPage.getDisplayedPhoneCount();
        scrollToEndOfPage();
        int actualPhoneCount = listingPage.getAllPhoneCount();
        Assert.assertEquals(actualPhoneCount,displayedCount,"mismatch in displayed and actual count");
        LOGGER.info("executed displayed count vs actual list");

    }


    @Title("Test to validate condition filters")
    @Description("validate Good Excellent Fair filters")
    @Test(groups = {"prodTest"})
    public void conditionFilterTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        String[] ribbon = {"like_new","fair","good"};

        LOGGER.info("executing condition filter test");
        List<String> conditions = listingPage.getConditionFilterValues();
        for(int i =0;i<conditions.size();i++) {
            listingPage.clickConditionFilter();
            listingPage.selectCondtionFilter(conditions.get(i));
            waitForPageToLoad("condition-grade");
            List<String> ribbons = listingPage.categoryRibbon(ribbon[i]);
            for (String condition : ribbons) {
                Assert.assertTrue(condition.contains(ribbon[i]), "condition filter test failed");
            }
        }
    }


    @Title("Test to filter by brand and model")
    @Description("Select brand and Model veridy only approp models disaplyed")
    @Test
    public void modelTestFilter(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing model filter test");

        listingPage.clickBrandFilter();
        listingPage.selectBrandFilter("Apple");
        listingPage.clickModelFilter();
        listingPage.selectModels();
         waitForPageToLoad("model-condition");
        System.out.println(listingPage.getFirstListingTitle());
        Assert.assertTrue(listingPage.getFirstListingTitle().contains("iPhone"),"other models found in listing");
    }

    @Title("Test to filter by brand and model")
    @Description("Select brand and Model veridy only approp models disaplyed")
    @Test
    public void modelFilterValuesTest(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing model filter test");

        listingPage.clickBrandFilter();
        listingPage.selectBrandFilter("Apple");
        listingPage.clickModelFilter();
        Assert.assertTrue(listingPage.validateModel("IPhone"),"other models found in dropdown");
    }

    @Title("Accessories Type Filter")
    @Description("Verify accessory type filter")
    @Test
    public void accessoriesTypeFilter(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing accessories Type filter test");

        listingPage.clickAccessoryTab();
        waitForPageToLoad("Accessory");
        listingPage.applyAccessoryTypeFilter("Power Banks");
        waitForPageToLoad("product_type");
        Assert.assertTrue(listingPage.filteresValuesContains("powerbanks"));
    }


    @Title("Accessories model Filter")
    @Description("Verify accessory model filter")
    @Test
    public void accessoriesModelFilter(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing accessories Type filter test");

        listingPage.clickAccessoryTab();
        waitForPageToLoad("Accessory");
        String model = listingPage.applyAccessoryModelFilter();
        waitForPageToLoad("model");
        Assert.assertTrue(listingPage.filteresValuesContains(model));
    }

    @Title("Accessories model Filter")
    @Description("Verify accessory model filter")
    @Test
    public void accessoriesBrandFilter(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        LOGGER.info("executing accessories Type filter test");

        listingPage.clickAccessoryTab();
        waitForPageToLoad("Accessory");
        String model = listingPage.applyAccessoryModelFilter();
        waitForPageToLoad("model");
        Assert.assertTrue(listingPage.filteresValuesContains(model));
    }


}
