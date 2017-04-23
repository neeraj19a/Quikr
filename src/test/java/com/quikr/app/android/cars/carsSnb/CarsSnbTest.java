package com.quikr.app.android.cars.carsSnb;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 27/11/15.
 */
public class CarsSnbTest extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_CARS_TESTDATA_FILE"));

    /**
     * sanity 3399 :Verify Inspected Cars are displayed in SNB screen for Cars through toggle button.
     */
    @Test
    public void verifyInspectedToggleButtonOnSnb() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.vallidateElementsOnChpbeforeSwipe());
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.validateInspectedtoggleButtonSnbMenu());
    }

    /**
     * sanity 3400 Verify able to filter SNB screen via Inspected Cars
     */
    @Test
    public void ValidateResultsOnSelecteingInspectedCarsOnly() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.vallidateElementsOnChpbeforeSwipe());
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        Assert.assertTrue(carsNewPage.validateInspetedLogo());
    }

    /**
     * Android sanity:3488
     * User should be able to create Alert from in content alert
     */
//    @Test(groups = "horizontal")
    public void ValidateCreateAlertFromSNB() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        AlertPage alertPage = new AlertPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.vallidateElementsOnChpbeforeSwipe());
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnFilterButton();
        carsNewPage.selectBrandOnFilterPage();
        alertPage.selectelementWithoutScroll(testData.get("AlertBrand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        carsNewPage.clcikOnApplyFilters();
        carsNewPage.scrolToCreateAlert();
        carsNewPage.clickOnCreateAlert();
        carsNewPage.provideEmailForAleartCreation();
        carsNewPage.provideMobileNumberForAleartCreation();
        carsNewPage.submitAlertRequest();
        Assert.assertTrue(carsNewPage.validateAlertCreated(), "failed to create alert");

    }

    /**
     * Android sanity:3490
     * If user selects a particular brand or model, the same should be seen as main text(next to create alert button) and main heading(create alert page)
     */
    //@Test(groups = "horizontal")
    public void validateMainTextOFCreateAlertButton() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        AlertPage alertPage = new AlertPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.vallidateElementsOnChpbeforeSwipe());
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnFilterButton();
        carsNewPage.selectBrandOnFilterPage();
        alertPage.selectelementWithoutScroll(testData.get("AlertBrand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        carsNewPage.clcikOnApplyFilters();
        carsNewPage.scrolToCreateAlert();
        String Brand = carsNewPage.validateCreateLertMainText();
        Assert.assertTrue(Brand.contains(testData.get("AlertBrand")), "brand selected in Filter is not displayed in create Alert Main text");
    }

    /**
     * android sanity 3495
     * Once alert is created through incontent alert, it should reflect in My Alert
     */
//    @Test(groups = "horizontal")
    public void validateIncontentAlertIsDisplayedInMyAlert() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        MenuPage menuPage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.vallidateElementsOnChpbeforeSwipe());
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnFilterButton();
        carsNewPage.selectBrandOnFilterPage();
        alertPage.selectelementWithoutScroll(testData.get("AlertBrand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        carsNewPage.clcikOnApplyFilters();
        carsNewPage.scrolToCreateAlert();
        carsNewPage.clickOnCreateAlert();
        carsNewPage.provideEmailForAleartCreation();
        carsNewPage.provideMobileNumberForAleartCreation();
        carsNewPage.submitAlertRequest();
        Assert.assertTrue(carsNewPage.validateAlertCreated(), "failed to create alert");
        snbPage.selectMenuButtonAtSnbPage();
        menuPage.selectElements(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickOnMyAlerts();

    }

    /***
     * Android 200
     * Verify 'Inspected Cars Only' filter is displayed only for CAR subcat
     */
    @Test
    public void inspectedCarsOnlyFilterIsPresent() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnFilterButton();
        Assert.assertTrue(carsNewPage.inspectedFilterIsPresent(), "inspected Cars only filter is not present");
    }

    /**
     * Android 203.204,205
     * Verify user is able to toggle On/Off this filter
     * test when filter is on and off also included(Android205/206)
     */
    @Test
    public void verifyUserIsAbleToToggleOnOffInspectedFilter() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        Assert.assertTrue(carsNewPage.validateInspetedLogo(), "when inspected filte is On inspected Ad are not displayed");
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        Assert.assertTrue(!carsNewPage.validateInspetedLogo(), "when inspected filte is Off inspected Ad are displayed");
    }

    /**
     * Android 207
     * Verify Inspected Cars Only' filter is by default OFF.
     */
    @Test
    public void VeryInspectedOnlyFilterIsOffByDefault() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnFilterButton();
        String filter = carsNewPage.textOfFilterSwitch(0);
        Assert.assertTrue(filter.equalsIgnoreCase(testData.get("off")), "inspected only filter is not  set to OFF by Default");
    }

    /**
     * Android 208
     * Verify the filter,when user clicks on Clear Filter
     */
    @Test(groups = "horizontal")
    public void verifyResetFilterAction() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnFilterButton();
        carsNewPage.clickOnFilterSwitch(0);
        carsNewPage.clickOnFilterSwitch(1);
        carsNewPage.resetFilters();
        String filter = carsNewPage.textOfFilterSwitch(0);
        String filter2 = carsNewPage.textOfFilterSwitch(0);
        Assert.assertTrue(filter.equalsIgnoreCase(testData.get("off")), "filters not rest ");
        Assert.assertTrue(filter2.equalsIgnoreCase(testData.get("off")), "filters not rest ");

    }

    /**
     * Android 209
     * Verify "Inspected Cars Only" filter from Search screen
     */
    @Test
    public void validateInspectedFilterForSearchScreen() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.searchCarsFromChp(testData.get("brand"));
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        Assert.assertTrue(carsNewPage.validateInspetedLogo(), "when inspected filte is Off inspected Ad are displayed");

    }

    /**
     * Validates Filter options after tapping on filter icon in SnB
     */

    @Test
    public void validateFilterElements() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnNewCarsFilterIcon();
        Assert.assertEquals(carsNewPage.getNewCarsFilterElements(), 4, "All elements not present");
        Assert.assertTrue(carsNewPage.getNewCarsPriceSlider(), "could not find price slider");
    }

    /**
     * Validates the text of the selected brand
     */
    @Test
    public void validateTheTextOfSelectedFilter() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnNewCarsFilterIcon();
        carsNewPage.tapOnNewCarFilterBrand();
        carsNewPage.selectFilterOptionNewCars();
        carsNewPage.tapOnDoneButtonFilterOfNewCars();
        carsNewPage.clcikOnApplyFilters();
        Assert.assertTrue(carsNewPage.getAstonMartinText().contains(testData.get("AstonMartinTextContains")), "Aston Martin text not present");
    }

    /**
     * Validates the Sort options in New Cars
     */

    @Test
    public void validateThesortOptions() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSortButton();
        Assert.assertTrue(carsNewPage.getSortOptions(),"Sort options not present");
    }

    /**
     * Validates Lowest Sort options
     */
    @Test
    public void validateLowestSortOptions() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSortButton();
        carsNewPage.tapOnLowestSort();
        Assert.assertTrue(carsNewPage.validateAscendingSortOnSnbPageForNewCars(),"Not sorted by lowest price");
    }

    /**
     * Validates Highest Sort options
     */
    @Test
    public void validateHighestSortOptions() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSortButton();
        carsNewPage.tapOnHighestSort();
        Assert.assertTrue(carsNewPage.validateDescendingSortOnSnbPageForNewCars(),"Not sorted by Highest price");
    }

    /**
     *Verifies Snb after tapping on any brand in Most Popular Near you
     */

    @Title("Verifies Snb after tapping on any brand in Most Popular Near you")
    @Test
    public void validatesSnbAfterTappingOnMostPopularNearYou(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.swipeVertically("MspLink","carsAndBikes");
        carsNewPage.tapOnMoreMostPopularNearYou();
        carsNewPage.tapsOnMostPopularBrand();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.validateSnbMenu(),"Not navigated to snb");
    }

    /**
     * Validates the text of popular brands and the brand and model in filters
     */

    @Title("Validates the popular brands text and the filter options text(Brand name and model)")
    @Test
    public void validatesBrandNameAndModelInPopularAdsAndFilter(){
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.swipeVertically("MspLink","carsAndBikes");
        carsNewPage.tapOnMoreMostPopularNearYou();
        String popularCar = carsNewPage.getPopularAdText();
        carsNewPage.tapsOnMostPopularBrand();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnFilterButton();
        carsNewPage.swipeVertically("onlineUsersFilter","inspectedFilter");
        Assert.assertTrue(popularCar.contains(carsNewPage.getBrandNameTextInFilters(0)),"Filter option does not match with popular brand");
        Assert.assertTrue(popularCar.contains(carsNewPage.getBrandNameTextInFilters(1)),"Filter option does not match with popular brand");
    }

    /**
     * Validates whether user is navigated to snb after selecting brand in New Cars Search bar
     */

    @Title("Validates whether user is navigated to snb after selecting brand in New Cars Search bar")
    @Test
    public void validateSnbAfterSelectingBrandFromNewCarsSearchBar(){
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.tapOnPopularAds();
        Assert.assertTrue(carsNewPage.getNewCarsTitle(),"Not navigated to Snb");
    }

    /**
     * Validates the selected brand in snb
     */

    @Title("Validates whether selected brand is loaded in Snb")
    @Test
    public void validatesSelectedBrandInSnb(){
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        String popularAdText=carsNewPage.getMarutiSuzuki();
        carsNewPage.tapOnPopularAds();
        Assert.assertTrue(carsNewPage.getSnbText().contains(popularAdText), "did not match");
    }
}
