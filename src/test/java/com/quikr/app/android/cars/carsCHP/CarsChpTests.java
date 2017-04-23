package com.quikr.app.android.cars.carsCHP;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra on 10/11/15.
 */
public class CarsChpTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_CARS_TESTDATA_FILE"));

    /**
     * /**
     * Android Sanity:3478
     * Cars homepage should reflect new UI with toggle view
     */
    @Test
    public void validateChp()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.vallidateElementsOnChpbeforeSwipe(),"on cars CHp all links not present");
        carsNewPage.swipeCarsChp();
        Assert.assertTrue(carsNewPage.validateElementsOnCHPAfterScrolling(),"on cars CHp all links not present");
    }

    /**
     * Android 3481
     * User should be able to see MSP link and it should be tapable
     */

    @Test
    public void validateMspLinkIsTapable()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.validateMSPButtonOnCarsChp(), "Msp button not visible on CHP");
        carsNewPage.clickOnMspButtonOnChp();
        Assert.assertTrue(carsNewPage.validateCarsAndBikesOnMspHomePage());
    }

    /**
     * Android Sanity:3482
     * User should be able to see inspected link and it should be tapable
     */
    @Test
    public void validateGetispectedButtonIsTapable()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.validateMSPButtonOnCarsChp(), "Msp button not visible on CHP");
        carsNewPage.clickOnGetInspectedButtonOnChp();
        Assert.assertTrue(carsNewPage.validateGetInspectedButtonOnChpIsCliclable(),"on clicking insppected button user not redirected to Get free inspection page");

    }

    /**
     * Android sanity :3483
     * User should be able to see Read Reviews and it should be tapable
     */
    @Test
    public void validateReadReviewsIsClickable()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnReadReviewsButtonOnChp();
        Assert.assertTrue(carsNewPage.validateReadReviewsButtonIsClickable(),"on clcicking read reviews button user not redirected to review page");
    }
    /**
     * Android Sanity 3484
     * User should be able to see Read News and it should be tapable
     */

    @Test
    public void validateReadNewsIsClickable()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnReadNewsButtonOnChp();
        Assert.assertTrue(carsNewPage.validateReadNewsButtonIsClickable(),"on clcicking read News button user not redirected to review page");
    }

    /**
     * Android Sanity 3485
     * User should be able to see watch videos and it should be tapable
     */

    @Test
    public void validateWatchVideosIsClickable()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnWatchVideosButtonOnChp();
        Assert.assertTrue(carsNewPage.validateWatchVideosButtonIsClickable(),"on clcicking watch videos button user not redirected to review page");
    }

    /**
     * Validates whether user is in used cars tab or not
     */
    @Test
    public void validateUsedCarsTab(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        Assert.assertTrue(carsNewPage.getUsedCarsElement(),"Not in used cars tab");
    }

    /**
     * Validates whether user is able to switch from used to new cars tab
     */
    @Test
    public void validateNewCarsTab(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        Assert.assertEquals(carsNewPage.getNewCarsTabText(),testData.get("NewCarsText"), "Not able to switch to new cars tab");
    }
    /**
     * Validates the auto suggestion list
     */

    @Test
    public void validateAutoSuggestionListNewCars(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.writeLetterInSearchBar();
        Assert.assertTrue(carsNewPage.getAutoSuggestionElement(),"Auto suggestion list did not load");
    }

    /**
     * Validates whether user is able to see only popular Brands when tapped on search bar(user is using the app for the first time)
     */
      @Test
    public void validateUserIsUsingTheAppFirstTime(){
          CarsNewPage carsNewPage=new CarsNewPage(driver);
          Hompage hompage=new Hompage(driver);
          hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
          carsNewPage.clickOnSearchOnChp();
          carsNewPage.tapOnDismissCallout();
          carsNewPage.tapOnNewCarsTab();
          carsNewPage.tapOnNewCarsSearchBar();
//          Assert.assertTrue(!carsNewPage.getRecentSearchElement(),"User is not using the app for the first time and can see  Recent Searches");
          Assert.assertFalse(carsNewPage.getRecentSearchElement(),"User is not using the app for the first time and can see Recent Searches");
      }

    /**
     * Validates whether user is able to see recent searches or not
     */
    @Test
    public void validateRecentSearchForNewCar(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.tapOnPopularAd();
        carsNewPage.tapOnFindCars();
        carsNewPage.navigateBack();
        carsNewPage.navigateBack();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        Assert.assertTrue(carsNewPage.getRecentSearchElement(),"User is using the app for the first time");

      }

    /**
     * Validates whether user is able to select from Recent Searches
     */
    @Test
    public void validateNewCarTitle(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.tapOnPopularAd();
        carsNewPage.tapOnFindCars();
        carsNewPage.navigateBack();
        carsNewPage.navigateBack();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.tapOnPopularAd();
        carsNewPage.tapOnFindCars();
        Assert.assertTrue(carsNewPage.getNewCarsTitle(),"Could not search through Recent searches");
    }

    /**
     * Validates whether user is able to select from PopularF Searches
     */
    @Test
    public void validateNewCarTitleWhenSelectedFromPopularSearches(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.tapOnPopularAd();
        carsNewPage.tapOnFindCars();
        Assert.assertTrue(carsNewPage.getNewCarsTitle(),"Could not search through Recent searches");
    }

    /**
     * Validates whether recent searches disappear after tapping on clear button
     */
    @Test
    public void validateRecentSearchesAfterTappingOnClear(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.tapOnPopularAd();
        carsNewPage.tapOnFindCars();
        carsNewPage.navigateBack();
        carsNewPage.navigateBack();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.tapOnClearButton();
        Assert.assertTrue(!carsNewPage.getRecentSearchElement(),"Could not clear Recent Searches");

    }

    /**
     * Validates whether user is navigated to most popular ads near you page or not
     */
    @Title("Navigates to Most Popular Near You Page")
    @Test
    public void validateMostPopularAdsNearYouPage(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.swipeVertically("MspLink","carsAndBikes");
        carsNewPage.tapOnMoreMostPopularNearYou();
        Assert.assertTrue(carsNewPage.getMostPopularNearYou(),"Not navigated to Most popular near you page");
    }

    /**
     * Validates the icon of Brands in Most Popular Near You
     */

    @Title("Verifies the icon prefix to brands in Most popular Near You")
    @Test
    public void validateIconPrefixToBrands(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.swipeVertically("MspLink","carsAndBikes");
        carsNewPage.tapOnMoreMostPopularNearYou();
        Assert.assertTrue(carsNewPage.getIconElement(),"Icon not present");
    }

    /**
     * Validates the suffix of brands in Most popular near you
     */
    @Title("Verifies the number suffix to brands in Most popular Near You")
    @Test
    public void validateNumberSuffixToBrands(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.swipeVertically("MspLink","carsAndBikes");
        carsNewPage.tapOnMoreMostPopularNearYou();
        Assert.assertTrue(carsNewPage.getSuffixNumber(),"Suffix not present");
    }


}
