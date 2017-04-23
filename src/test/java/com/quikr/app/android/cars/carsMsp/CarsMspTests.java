package com.quikr.app.android.cars.carsMsp;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 23/11/15.
 */
public class CarsMspTests extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_CARS_TESTDATA_FILE"));

    /**
     * 3118,3121,3123,3481 sanity
     * User should be able to see MSP calculator in the sub menu bar next to inspected switch
     * MSP calculator should be present only for cars and bikes subcat
     */
    @Test
    public void validateMenuBarOnSnbAndMspHomePage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(carsNewPage.validateMSPButtonOnCarsChp());
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.validateMSPButtonOnCarsSNB());
        carsNewPage.selectMspFromSnb();
        Assert.assertTrue(carsNewPage.validateCarsAndBikesOnMspHomePage());

    }

    /**
     * 3124 Cars icon should be bold and user should be able to enter car related info in the form
     */

    @Test
    public void validateCarsMSPForm() {

        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.selectMspFromSnb();
        Assert.assertTrue(carsNewPage.validateCarsAndBikesOnMspHomePage());


    }

    /**
     * 3127 Bikes icon should be bold and user should be able to enter bike related info in the form
     */

    @Test
    public void validateBikesMSPForm() {

        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.selectMspFromSnb();
        carsNewPage.selectBikesOnMspPAge();
        Assert.assertTrue(carsNewPage.validateBikesFieldOnMSPCalculationPage());


    }

    /**
     * 3546 ,3547sanity
     * 3129 Cars icon should be bold and user should be able to enter car related info in the form
     */

    @Test
    public void calculateMSP() {

        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.selectMspFromSnb();
        carsNewPage.selectBrand();
        hompage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.selectModel();
        hompage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.selectYearOfPurchase();
        hompage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.selectVarient();
        hompage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.selectKmDriven();
        hompage.selectelementWithoutScroll(testData.get("kmDrivenMsp"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        carsNewPage.calculateMSP();
        Assert.assertTrue(carsNewPage.validateMsp());
        carsNewPage.calaulateMspAgain();
        Assert.assertTrue(carsNewPage.validateCarsAndBikesOnMspHomePage());


    }

    /**
     * Android Sanity:3545
     * Msp calculator should be present only for cars and bikes sub cat
     */
    @Test
    public void MspCalculatorIsNotPresent() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        snbPage.clickOnChangeSubCAtFromSNB();
        snbPage.selectSubCatFromSNB(2);
        Assert.assertTrue(!carsNewPage.validateMSPButtonOnCarsSNB(), "msp button is present for other categories ");
    }

    /**
     * Validates create Alert button in MSP result page
     */

    @Test
    public void validateCreateAlertButtonInMspResultPage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.tapOnMspInSnb();
        carsNewPage.tapOnMspBikes();
        carsNewPage.tapOnMspBrandNameBikes();
        carsNewPage.selectSuzukiBrand(testData.get("BrandForMsp"));
        carsNewPage.tapOnMspModelNameBikes();
        carsNewPage.selectBanditModel(testData.get("ModelForMSP"));
        carsNewPage.tapOnMspYearBikes();
        carsNewPage.selectYearMspBikes();
        carsNewPage.tapOnCheckMspButton();
        Assert.assertTrue(carsNewPage.getCreateAlertButtonMsp(), "Create Alert button not present");
    }

    /**
     * Validates Post Ad button in MSP result page
     */

    @Test
    public void validatePostAdButtonInMspResultPage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.tapOnMspInSnb();
        carsNewPage.tapOnMspBikes();
        carsNewPage.tapOnIAmSelling();
        carsNewPage.tapOnMspBrandNameBikes();
        carsNewPage.selectSuzukiBrand(testData.get("BrandForMsp"));
        carsNewPage.tapOnMspModelNameBikes();
        carsNewPage.selectBanditModel(testData.get("ModelForMSP"));
        carsNewPage.tapOnMspYearBikes();
        carsNewPage.selectYearMspBikes();
        carsNewPage.tapOnCheckMspButton();
        Assert.assertTrue(carsNewPage.validatePostFreeAdButtonInMspResultPage(), "Post free ad button not present");
    }

    /**
     * Validates Snb page after tapping on Find Bikes of MSP Result page
     */

    @Test
    public void validateSnbAfterTappingOnFindBikesInMspResult() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.tapOnMspInSnb();
        carsNewPage.tapOnMspBikes();
        carsNewPage.tapOnMspBrandNameBikes();
        carsNewPage.selectSuzukiBrand(testData.get("BrandForMsp"));
        carsNewPage.tapOnMspModelNameBikes();
        carsNewPage.selectFieroModel(testData.get("FieroModelMsp"));
        carsNewPage.tapOnMspYearBikes();
        carsNewPage.selectYearFiero();
        carsNewPage.tapOnCheckMspButton();
        carsNewPage.tapOnFindCarsBikes();
        Assert.assertTrue(carsNewPage.validateInspectedtoggleButtonSnbMenu(), "Not navigated to SnB");
    }

    /**
     * Validates Snb page after tapping on Find Cars of MSP Result page
     */

    @Test
    public void validateSnbAfterTappingOnFindCarsInMspResult() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.tapOnMspInSnb();
        carsNewPage.tapOnCarBrandName();
        carsNewPage.selectAudiFromBrandName();
        carsNewPage.tapOnModelCars();
        carsNewPage.selectA4Model();
        carsNewPage.tapOnYearCars();
        carsNewPage.selectYearFiero();
        carsNewPage.tapOnVariantCars();
        carsNewPage.selectVariantNameCars();
        carsNewPage.tapOnKmsDrivenCarsMsp();
        carsNewPage.selectKmsDrivenValue();
        carsNewPage.tapOnCheckMspButton();
        carsNewPage.tapOnFindCarsBikes();
        Assert.assertTrue(carsNewPage.validateInspectedtoggleButtonSnbMenu(), "Inspection toggle not present");
    }

    /**
     * Calculates Msp from MSP calculator
     */

    @Test
    public void validateMspResultPage() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.tapOnMspInSnb();
        carsNewPage.tapOnCarBrandName();
        carsNewPage.selectAudiFromBrandName();
        carsNewPage.tapOnModelCars();
        carsNewPage.selectA4Model();
        carsNewPage.tapOnYearCars();
        carsNewPage.selectYearFiero();
        carsNewPage.tapOnVariantCars();
        carsNewPage.selectVariantNameCars();
        carsNewPage.tapOnKmsDrivenCarsMsp();
        carsNewPage.selectKmsDrivenValue();
        carsNewPage.tapOnCheckMspButton();
        Assert.assertEquals(carsNewPage.getMspResult(),testData.get("getMspResultText"),"Not tapped on Find Cars/Bikes Button");
    }

    /**
     *  Validates the I am Buying button in MSP Evaluation page
     */

    @Test
    public void validateIamBuyingButton(){
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.tapOnMspInSnb();
        Assert.assertEquals(carsNewPage.getIamBuyingMsp(),testData.get("IamBuying")," Text not present");

    }

    /**
     *  Validates the I am Selling button in MSP Evaluation page
     */

    @Test
    public void validateIamSellingButton(){
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.tapOnMspInSnb();
        Assert.assertEquals(carsNewPage.getIamSellingMsp(),testData.get("IamSelling")," Text not present");

    }
}
