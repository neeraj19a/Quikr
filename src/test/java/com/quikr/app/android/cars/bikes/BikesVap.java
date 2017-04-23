package com.quikr.app.android.cars.bikes;

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
 * Created by swatantra on 7/12/15.
 */
public class BikesVap extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_CARS_TESTDATA_FILE"));

    /**
     * Android :1067
     *Select location as Bangalore and verify the VAP page for Bikes which are inspected
     */
  @Test
    public void validateInspectionReportIsPresentOnVap()
  {
      CarsNewPage carsNewPage=new CarsNewPage(driver);
      SnbPage snbPage=new SnbPage(driver);
      Hompage hompage=new Hompage(driver);
      hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
      carsNewPage.clickOnBikesFromCHP();
      carsNewPage.clickOnSearchOnChp();
      carsNewPage.SubmitSearch();
      snbPage.hideOverlayLayout();
      carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
      carsNewPage.openAdFromSnb(0);
      snbPage.hideOverlayLayout();
      Assert.assertTrue(carsNewPage.verifyInspectionReportAppearOnVap(),"inspection report not visible on vap");
  }
    /**
     * Android 1076
     * Dont Select location as Bangalore and verify the VAP page for Bikes
     *
     */
    @Test
    public void validateInspectionReportIsPresentOnVapForSpecificCities()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectCity();
        hompage.selectelementWithoutScroll(testData.get("city"), QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnBikesFromCHP();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(!carsNewPage.verifyInspectionReportAppearOnVap(),"inspection report is present for cities other than bangalore"+testData.get("city") );
    }
    /**
     * Android :1074
     */
    @Test
    public void validateBikesFilterForSpecificCities() {
        CarsNewPage carsNewPage = new CarsNewPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        hompage.selectCity();
        hompage.selectelementWithoutScroll(testData.get("city"), QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnBikesFromCHP();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnFilterButton();
        Assert.assertTrue(!carsNewPage.inspectedFilterIsPresent(),"inspected filter is present for other cities");

    }
    /**
     * Android :1076
     * Verify the Inspection Report tab for bikes
     */
   @Test
    public void verifyInspectionReportText()
   {
       CarsNewPage carsNewPage=new CarsNewPage(driver);
       SnbPage snbPage=new SnbPage(driver);
       Hompage hompage=new Hompage(driver);
       hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
       carsNewPage.clickOnBikesFromCHP();
       carsNewPage.clickOnSearchOnChp();
       carsNewPage.SubmitSearch();
       snbPage.hideOverlayLayout();
       carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
       carsNewPage.openAdFromSnb(0);
       snbPage.hideOverlayLayout();
       Assert.assertTrue(testData.get("inspectionreport").equalsIgnoreCase(carsNewPage.inspectionReportText()),carsNewPage.inspectionReportText()+ "is present");

   }

    /**Android 1077
     * Verify whether there is an inspected only Filter in the S&B page for bikes
     */
    @Test
    public void InspectfilterIsFresentOnSNB()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnBikesFromCHP();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.validateInspectedtoggleButtonSnbMenu(),"inspection button not visible on vap");
    }
    /**
     * Android 1087
     * Verify the S&B page for bikes after selecting inspected only filter(new UI)
     */
    @Test
     public void validateInspectedResultOnSnb()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnBikesFromCHP();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        Assert.assertTrue(carsNewPage.validateInspetedLogo(),"inspected ads not displayed");
    }
    /**
     * Android 1091
     * Verify whether user is able to see the inspected label in the VAP screen
     */
    @Test
    public  void inspectedLogoIsPresentOnVap()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnBikesFromCHP();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(carsNewPage.inspectedLogoIsPresentOnVap(),"logo not present");
    }
/**
 * Android 1208
 * Verify the fetching of carnation report when Inspected ad is tapped from Search result of "All Categories"
 */
    //@Test
    public void validateInspectionReport()
    {
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.selectelementWithoutScroll(testData.get("Category"), QuikrAppEnums.Home_Categories);
        carsNewPage.clickOnBikesFromCHP();
        carsNewPage.clickOnSearchOnChp();
        carsNewPage.SubmitSearch();
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectedCheckBoxFromSnbMenu();
        carsNewPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        carsNewPage.clickOnInspectionReport();
        Assert.assertTrue(carsNewPage.carnationReport(),"crnation options not present");




    }


}
