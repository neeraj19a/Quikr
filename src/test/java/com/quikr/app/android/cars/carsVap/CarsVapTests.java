package com.quikr.app.android.cars.carsVap;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.home.Hompage;
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
public class CarsVapTests extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_CARS_TESTDATA_FILE"));

    /**
     * sanity 3401: Verify Inspection Report is displayed in VAP screen
     */
//    @Test
    public void ValidateIspectionReportOnVApOnSelectingInspectedFilter() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.vallidateElementsOnChpbeforeSwipe());
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.verifyInspectionReportAppearOnVap());

    }

    /**
     * Validates whether Get Quote button is present or not
     */
    @Test

    public void validateGetQuoteOnVap() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.validateGetQuote(), "Get quote button not present");
    }

    /**
     * Taps on Get Quotes button and navigates to policy bazaar
     */
    @Test

    public void tapOnGetQoutes() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 10) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }
        carsNewPage.clickOnGetQoute();
        carsNewPage.fillDialogBoxName(testData.get("name"));
        carsNewPage.fillDialogBoxPhone(testData.get("number"));
        carsNewPage.fillDialogBoxemail(testData.get("email"));
        carsNewPage.tapGetQuotesButton();
    }

    /**
     * Navigates to policy bazaar page and validates policy Bazaar text
     */
    @Test

    public void navigateToPolicyBazaarPage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 10) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }
        carsNewPage.clickOnGetQoute();
        carsNewPage.fillDialogBoxName(testData.get("name"));
        carsNewPage.fillDialogBoxPhone(testData.get("number"));
        carsNewPage.fillDialogBoxemail(testData.get("email"));
        carsNewPage.tapGetQuotesButton();
        Assert.assertTrue(carsNewPage.validatePolicyBazaarPage(), "Policy Bazaar Page not found");
    }

    /**
     * Validates error message all when mandatory fields are empty and taps on getQuotes Button
     */
    @Test
    public void validatePolicyBazaarErrorMessageForAllFields() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 10) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }
        carsNewPage.clickOnGetQoute();
        carsNewPage.tapGetQuotesButton();
        Assert.assertTrue(carsNewPage.validateErrorWhenAllFieldsEmpty(), "Form submitted even though fields are empty");
    }

    /**
     * Validates error message when 2 mandatory fields are empty and taps on getQuotes Button
     */
    @Test
    public void validatePolicyBazaarErrorMessageForTwoFields() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 10) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }
        carsNewPage.clickOnGetQoute();
        carsNewPage.tapGetQuotesButton();
        Assert.assertTrue((carsNewPage.validateErrorWhenEmailEmpty() && carsNewPage.validateErrorWhenNameEmpty()), "Form submitted even though fields are empty");
    }

    /**
     * Returns the text of invalid phone number  Policy bazar form
     */
    @Test
    public void validateInvalidPhoneNumberText() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 10) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }

        carsNewPage.clickOnGetQoute();
        carsNewPage.fillDialogBoxName(testData.get("name"));
        carsNewPage.fillDialogBoxPhone(testData.get("invalidNumber"));
        carsNewPage.fillDialogBoxemail(testData.get("email"));
        carsNewPage.tapGetQuotesButton();
        Assert.assertEquals(testData.get("invalidNumberMessage"), carsNewPage.validateInvalidPhoneNumber());
    }

    /**
     * Returns the text of invalid email address  Policy bazar form
     */
    @Test
    public void validateInvalidEmailAddressText() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 10) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }

        carsNewPage.clickOnGetQoute();
        carsNewPage.fillDialogBoxName(testData.get("name"));
        carsNewPage.fillDialogBoxPhone(testData.get("number"));
        carsNewPage.fillDialogBoxemail(testData.get("invalidEmail"));
        carsNewPage.tapGetQuotesButton();
        Assert.assertEquals(carsNewPage.validateInvalidEmailAddress(), testData.get("invalidEmailMessage"));
    }

    /**
     * Validates whether the prefilled data matches the logged in details in Get Quote button Dialog box
     */
    @Test
    public void validatePrefilledDataGetQuoteDialogBox() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        AuthPage authPage = new AuthPage(driver);
        carsNewPage.login(testData.get("loginPassword"), testData.get("loginEmail"));
        authPage.clickOnBackButtonOnLoginPage();
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 10) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }

        carsNewPage.clickOnGetQoute();
        Assert.assertEquals(carsNewPage.getPrefilledEmailGetQuote(), testData.get("loginEmail"), "prefilled email did not match");

    }

    /**
     * Swipes images in VAP horizontally and quits if it goes to next Ad
     */
    @Test
    public void swipeImagesVapHorizontally() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        AuthPage authPage = new AuthPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        Assert.assertFalse(carsNewPage.swipeTillVapChanges(), "Could not swipe to next ad");
    }

    /**
     * Validates the string "By insurance for just" in get quote page
     */
    @Test
    public void validateGetQuotePrefixString() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 5) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }
        Assert.assertTrue(carsNewPage.prefixTextGetQuote().contains(testData.get("GetQuotePrefixText")), "String does not match" + carsNewPage.prefixTextGetQuote());
    }

    /**
     * Validates the prefix element of Get Quote button
     */
    @Test
    public void validatePrefixElementGetQuote() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validateGetQuote() && count < 10) {
            count++;
            carsNewPage.swipeCarsVapHorizontally();
        }
        Assert.assertTrue(carsNewPage.prefixElementGetQuote(), "Element getQuote not present");
    }

    /**
     * Validates the element "Call" in VAP for Cars
     */
    @Test
    public void validateViewNumberVap() {

        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.getViewNumberVap(), "Call button does not exist");
    }

    /**
     * Taps on View Number and Validates Contact details element  of View Number Button
     */
    @Test
    public void validateContactDetailsPage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        carsNewPage.tapViewNumberVap();
        Assert.assertTrue(carsNewPage.verifyContactDetailsElement(), "Element not found");
    }

    /**
     * Validates Car details text on VAP
     */
    @Test
    public void validateCarDetailsText() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        carsNewPage.tapBatteryTab();
        carsNewPage.tapTransmissionTab();
        carsNewPage.tapAccessoriesTab();
        carsNewPage.tapDocsTab();
        Assert.assertTrue(carsNewPage.validateCarsDetailsTab(), "Cars Details tab not present");
    }

    /**
     * Types any two letters(Free text search) and taps on the auto suggestion and navigates to SnB
     */
    @Test
    public void freeTextSearchValidate() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.clickOnChooseSearchBar();
        carsNewPage.freeTextinCarsSearchBar(testData.get("freeText"));
        carsNewPage.tapOnAutoSuggest();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.validateSnb(), "Dint navigate to SnB");
    }

    /**
     * Validates when Price Range button not present
     */

    @Test(dataProvider = "getCarsSubcats", dataProviderClass = Data.class)
    public void validatePriceMeter(String carsSubCat) {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.selectSubCat(carsSubCat);
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (carsNewPage.validatePriceTrendsIcon() && count < 3) {
            count++;
            carsNewPage.swipeNonInspectedCarsVapHorizontally();
        }
        Assert.assertTrue(!carsNewPage.validatePriceTrendsIcon(), "Price range button present");

    }

    /**
     * Validtes when price trends bucket is not zero
     */
    @Test(dataProvider = "getPriceTrendsCarsSubcats", dataProviderClass = Data.class)
    public void validatePriceBucketNotZero(String pricetrendsSubcatCars) {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
//        carsNewPage.selectPriceTrendsSubcat();
        carsNewPage.selectSubCat(pricetrendsSubcatCars);
//        hompage.selectelementWithoutScroll(pricetrendsSubcatCars,QuikrAppEnums.SNB_Subcat);
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;

        while (carsNewPage.validatePriceTrendsIcon() && count < 3) {
            carsNewPage.tapPriceTrendsIcon();
            if (!carsNewPage.priceTrendsZeroAds()) {
                count++;
                carsNewPage.swipePriceTrendsHorizontally();
            } else {
                Assert.assertTrue(carsNewPage.priceTrendsZeroAds(), "No Price Trends with 0 Ads or No price trends icon present");
                break;
            }
        }

//        Assert.assertTrue(carsNewPage.scrollAndCheckIfPriceRangeButtonPresent(), "Price range button not present");
//        Assert.assertTrue(carsNewPage.priceTrendsZeroAds(),"No Price Trends with 0 Ads");


    }

    /**
     * Validates the Snb page after the user taps on price trends button
     */
    @Test
    public void validateSnbAfterTappingPriceRangeButton() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (count < 3) {
            if (carsNewPage.validatePriceTrendsIcon()) {
                carsNewPage.tapPriceTrendsIcon();
                if (!carsNewPage.priceTrendsZeroAds1()) {
                    count++;
                    carsNewPage.tapOnPriceTrendsButton("priceRangeButton1");
                    Assert.assertTrue(carsNewPage.isInspectedTogglePresent(), "Not navigated to SnB");
                    return;
                } else if (!carsNewPage.priceTrendsZeroAds2()) {
                    count++;
                    carsNewPage.tapOnPriceTrendsButton("priceRangeButton2");
                    Assert.assertTrue(carsNewPage.isInspectedTogglePresent(), "Not navigated to SnB");
                    return;
                } else if (!carsNewPage.priceTrendsZeroAds3()) {
                    count++;
                    carsNewPage.tapOnPriceTrendsButton("priceRangeButton3");
                    Assert.assertTrue(carsNewPage.isInspectedTogglePresent(), "Not navigated to SnB");
                    return;
                } else {
                    carsNewPage.swipePriceTrendsHorizontally();
                    count++;
                }
            } else {
                count++;
                carsNewPage.swipeFromBottomVapHorizontally();
            }
            Assert.assertTrue(carsNewPage.validateSnbMenu(), "Not Navigated to snb");
        }
    }

    /**
     * Validates when low, medium and high PriceTrends button are present
     */
    @Test
    public void validatePriceTrendsButton() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validatePriceTrendsIcon() && count < 3) {
            count++;
            carsNewPage.swipeNonInspectedCarsVapHorizontally();
        }
        carsNewPage.tapPriceTrendsIcon();
        Assert.assertTrue(carsNewPage.isPriceRangeButtonPresent(), "Price range button not present");
    }

    /**
     * Validates static post Ad page
     */
    @Test
    public void validateStaticPostAdPage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.tapOnInspectionLinkInHomeScreen();
        carsNewPage.scroll("POST AD NOW");
        carsNewPage.tapOnStaticPostAdButton();
        Assert.assertTrue(carsNewPage.getElementFromPostFreeAdPage(), "Post free ad button not present");
    }

    /**
     * Validates whether user is navigated to newCars VAP
     */
    @Test
    public void validateNewCarsVap() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        Assert.assertTrue(carsNewPage.getVapTableInfoElement(), "Not navigated to VAP");
    }

    /**
     * Validate VAP after selecting model from search bar for new cars
     */
    @Test
    public void validateVapAfterSelectingModelFromNewCarsSearchBar() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnNewCarsSearchBar();
        carsNewPage.writeLetterInSearchBar1();
        carsNewPage.selectAutoSuggestedOptions();
        Assert.assertTrue(carsNewPage.getVapTableInfoElement(), "Not navigated to VAP");
    }

    /**
     * Validates the detailed Vap
     */

    @Test
    public void validateDetailedVapElement() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        carsNewPage.tapOnVariant();
        Assert.assertTrue(carsNewPage.getDetailedVapElement(), "Not navigated to detailed Vap");
    }

    /**
     * Validates whether user is able to switch different tabs
     */

    @Test
    public void validateSwitchOfTabs() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        carsNewPage.tapOnVariant();
        carsNewPage.getDetailedVapElement();
        carsNewPage.tapOnSpecificationsTab();
        carsNewPage.tapOnfeaturesTab();
        Assert.assertTrue(carsNewPage.getComfortText(), "Not tapped on features tab");
    }

    /**
     * Validates Get on road price when city and variant is known
     */

    @Test
    public void validateGetOnRoadPricePage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        carsNewPage.tapOnVariant();
        carsNewPage.tapOnGetOnRoadPrice();
        Assert.assertTrue(carsNewPage.getExShowroompriceElement(), "Not navigated to On road price page");
    }

    /**
     * Validates Get on road price when city and model is known
     */

    @Test
    public void validateGetOnRoadPricePageWhenCityAndModelKnown() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        carsNewPage.tapOnGetOnRoadPrice();
        Assert.assertTrue(!carsNewPage.getExShowroompriceElement(), "Not navigated to correct page");
    }

    /**
     * Validates Get Onroad price elements after tapping Get on road price of another car
     */

    @Test
    public void validateGetOnRoadPricePageAfterTappingGetOnRoadPriceOfAnotherCar() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        carsNewPage.tapOnVariant();
        carsNewPage.tapOnGetOnRoadPrice();
        carsNewPage.tapOnGetOnroadPriceOfAnotherCar();
        Assert.assertTrue(carsNewPage.isBrandModelVariantCityPresent(), "Element field or fields are missing");
    }

    /**
     * Verifies the price trends icon in VAP for used Cars
     */

    @Title("Verifies the price trends icon in VAP for used Cars")
    @Test(dataProvider = "exceptCarsSubcats", dataProviderClass = Data.class)
    public void validatePriceTrendsIconInVapForUsedCars(String subcat) {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        snbPage.clickOnChangeSubCAtFromSNB();
        carsNewPage.selectelementWithoutScroll(subcat, QuikrAppEnums.Quikr_Cars_SnbSubcat);
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validatePriceTrendsIcon() && count < 3) {
            count++;
            carsNewPage.swipeNonInspectedCarsVapHorizontally();
        }
        if (subcat.equalsIgnoreCase("cars") || subcat.equalsIgnoreCase("Bikes & Scooters") || subcat.equalsIgnoreCase("Commercial Vehicles"))

            Assert.assertTrue(carsNewPage.validatePriceTrendsIcon(), "PriceTrends Icon not present");
        else
            Assert.assertTrue(!carsNewPage.validatePriceTrendsIcon(), "PriceTrends Icon present");
    }


    /**
     * Verifies the price trends icon in VAP for New Cars
     */

    @Title("Verifies the price trends icon in VAP for New Cars")
    @Test
    public void validatePriceTrendsIconInVapForNewCars() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        Assert.assertFalse(carsNewPage.validatePriceTrendsIcon(), "PriceTrends Icon  present");
//        Assert.assertTrue(!carsNewPage.validatePriceTrendsIcon(),"PriceTrends Icon  present");
    }

    /**
     * verifies the price trends element after tapping on price trends icon
     */

    @Title("verifies the price trends element after tapping on price trends icon")
    @Test
    public void validatePriceTrendsElementAfterTappingPriceTrendsIcon() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        int count = 0;
        while (!carsNewPage.validatePriceTrendsIcon() && count < 3) {
            count++;
            carsNewPage.swipeNonInspectedCarsVapHorizontally();
        }
        carsNewPage.tapPriceTrendsIcon();
        Assert.assertTrue(carsNewPage.isPriceRangeButtonPresent(), "Price range button not present");
    }

    /**
     * Validates if Calculate EMI button is present in new cars VAP
     */

    @Title("Validates if Calculate EMI button is present in new cars VAP")
    @Test
    public void validateCalculateEmiButtonForNewCars() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        carsNewPage.tapOnVariant();
        Assert.assertTrue(carsNewPage.isEmiButtonPresent(), "Emi button not present or not tapped on Variant");

    }

    /**
     * Validates whether app is navigated to EMI Calculator page
     */

    @Title("Validates whether app is navigated to EMI Calculator page")
    @Test
    public void validateEmiCalculatorPage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        carsNewPage.tapOnVariant();
        carsNewPage.tapOnCalculateEmiButton();
        Assert.assertTrue(carsNewPage.isEmiCalculatorPresent(),"Not tapped on Calculate EMI button");
    }

    /**
     * Validate Calculate Emi Button in model page
     */

    @Title("Validate Calculate Emi Button in model page ")
    @Test
    public void validateEmiButtonInModelPage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.tapOnDismissCallout();
        carsNewPage.tapOnNewCarsTab();
        carsNewPage.tapOnFindCars();
        carsNewPage.tapOnSnbAd();
        Assert.assertTrue(!carsNewPage.isEmiCalculatorPresent(),"Calculate EMI Button");
    }
}


