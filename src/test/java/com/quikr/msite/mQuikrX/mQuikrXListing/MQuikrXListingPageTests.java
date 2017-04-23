package com.quikr.msite.mQuikrX.mQuikrXListing;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mQuikrX.mQuikrListingPage.MQuikrXListingPage;
import com.quikr.website.quikrX.quikrXLandingPage.QuikrXLandingPage;
import com.quikr.website.quikrX.quikrXNewListingPage.QuikrXNewListingPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by francis.s@quikr on 9/11/15.
 */
public class MQuikrXListingPageTests extends MTestBase {


    private final static Log LOGGER = LogFactory.getLog(MQuikrXListingPageTests.class.getName());

    @BeforeMethod
    public void preSetUp() {

        MHomePage homePage = new MHomePage(driver);
        homePage.selectCity("Bangalore");
        homePage.clickQuikrX();
        waitForPageToLoad("QuikrX");

    }

    @Title("Validate UI")
    @Description("verify all Ui elements are present")
    @Test
    public void listingPageUiValidation(){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        landingPage.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        listingPage.clickClearFilters();

        for (int count = 0; count <= 1; count++) {
            if (count==1) {
                listingPage.clickExchangeTab();
                waitForPageToLoad("Exchange-Mobile-Phones");
            }else
                waitForPageToLoad("Refurbished-Mobile-Phones");
            Assert.assertTrue(listingPage.firstListingTitleisPresent(), "item name not displayed in the listing");
            Assert.assertTrue(listingPage.verifyCallUs(), "call us icon missing");
            Assert.assertTrue(listingPage.verifyEmailUs(), "email us icon missing");
        }

    }
    @Title("Validate price filters")
    @Description("click on  filters and verify if filters are applies correctly")
    @Test
    public void priceFilterTest(){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        MQuikrXListingPage mListingPage = new MQuikrXListingPage(driver);


        landingPage.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        listingPage.clickClearFilters();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        mListingPage.clickFilter();
        mListingPage.clickpriceFilter();
        mListingPage.selectPriceFilter();
        mListingPage.applyFilter();
        int priceAfterFilter = listingPage.getFirstListingDiscPrice();
        Assert.assertTrue(priceAfterFilter>2001&&priceAfterFilter<5000,"price filter not working");
        LOGGER.info("executed price filter Validation tests");

    }


    @Title("Validate brand filters")
    @Description("click on brand  filters and verify if filters are applies correctly")
    @Test
    public void brandFilterTest(){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        MQuikrXListingPage mListingPage = new MQuikrXListingPage(driver);

        landingPage.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        listingPage.clickClearFilters();
        waitForPageToLoad("Refurbished-Mobile-Phones");

        LOGGER.info("executing brand filter test");
        mListingPage.clickFilter();
        mListingPage.clickbrandFilter();
        mListingPage.selectbrandFilter("Apple");
        mListingPage.applyFilter();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        Assert.assertTrue(listingPage.getFirstListingTitle().contains("Apple"),"brand filter not working");
        LOGGER.info("executed brand filter test");

    }


    @Title("Validate Os filters")
    @Description("click on os filters and verify if filters are applies correctly")
    @Test
    public void osFilterTest(){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        MQuikrXListingPage mListingPage = new MQuikrXListingPage(driver);

        landingPage.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        listingPage.clickClearFilters();
        waitForPageToLoad("Refurbished-Mobile-Phones");

        LOGGER.info("executing os filter test");
        mListingPage.clickFilter();
        mListingPage.clickOsFilter();
        mListingPage.selectOsFilter("Android");
        mListingPage.applyFilter();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        Assert.assertTrue(listingPage.filteresValuesContains("Android"));
        LOGGER.info("executed os filter test");

    }

    @Title("Validate screen size filters")
    @Description("click on screen size filters and verify if filters are applies correctly")
    @Test
    public void screenSizeFilter(){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        MQuikrXListingPage mListingPage = new MQuikrXListingPage(driver);

        landingPage.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        listingPage.clickClearFilters();
        waitForPageToLoad("Refurbished-Mobile-Phones");

        LOGGER.info("executing screen size filter test");
        mListingPage.clickFilter();
        mListingPage.clickdisaplayFilter();
        mListingPage.selectDisaplayFilter();
        mListingPage.applyFilter();
        Assert.assertTrue(listingPage.filteresValuesContains("4-Inches"));
        LOGGER.info("executed screen size filter test");

    }


    @Title("Validate camera filters")
    @Description("click on camera filters and verify if filters are applies correctly")
    @Test
    public void cameraSizeFilter(){

        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        MQuikrXListingPage mListingPage = new MQuikrXListingPage(driver);

        landingPage.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        listingPage.clickClearFilters();
        waitForPageToLoad("Refurbished-Mobile-Phones");


        LOGGER.info("executing camera filter test");
        mListingPage.clickFilter();
        mListingPage.clickcameraFilter();
        mListingPage.selectCameraFilter();
        mListingPage.applyFilter();
        Assert.assertTrue(listingPage.filteresValuesContains("16-MP"));
        LOGGER.info("executed camera filter test");

    }


}
