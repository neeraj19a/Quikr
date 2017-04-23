package com.quikr.app.android.escrowTests;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 1/6/16.
 */
public class EscrowSNBTests   extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_ESCROW_TESTDATA_FILE"));
    String AdId=null;
    @Title("SNB Overlay : SNB overlay should come on tapping Mobiles subcategories.")
    @Description("SNB overlay should come on tapping Mobiles subcategories.")
    @Test(groups = {"Stage","Prod"})
    public void verifySNBOverlayDisplayed()
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);

//        hompage.selectCategoryFromHomePage(testData.get("mobilesAndTAbs"));
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.ifSnbOverlayDisplayed(),"SNB select cities Overlay NOT displayed");
    }

    @Title("SNB Overlay : Verify by default selected is Bangalore on SNB overlay.")
    @Description("Verify by default selected is Bangalore on SNB overlay.")
    @Test(groups = {"Stage","Prod"})
    public void verifySNBOverlayCurrentCitySelected()
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);

        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.ifSnbOverlayDisplayed(),"SNB select cities Overlay NOT displayed");
        Assert.assertTrue(snbPage.ifSnbOverlayCurrentCitySelected("Bangalore"),"SNB current city NOT selected");
    }

    @Title("SNB Overlay : Verify by default selected is Bangalore on SNB overlay.")
    @Description("Verify by default selected is Bangalore on SNB overlay.")
    @Test(groups = {"Stage","Prod"},dataProvider = "ClusterCitiesOverlay",dataProviderClass = Data.class)
    public void verifySNBOverlayClusterCities(String clusterCities, String category)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        hompage.selectCity();
        headerPage.searchCity(clusterCities);
        hompage.selectelementWithoutScroll(clusterCities, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get(category), QuikrAppEnums.Home_Categories);
        escrowPage.selectFirstPopularProduct();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.ifSnbOverlayDisplayed(),"SNB select cities Overlay NOT displayed");
        Assert.assertTrue(snbPage.ifNationwideOverlay(testData.get("nationwideOverlayMsg")),"SNB Overlay is NOT Nationwide");
        Assert.assertTrue(snbPage.ifSnbOverlayAllCitiesSelected(),"SNB All cities NOT selected");
//        escrowPage.selectCityAsCurrentCityOnVapEscrow();
//        headerPage.gotoHomeFromSideMenuDrawer();
//        hompage.selectelementWithoutScroll(testData.get("HomesAndLifestyle"), QuikrAppEnums.Home_Categories);
//        escrowPage.selectMoreButton();
//        Assert.assertTrue(snbPage.ifSnbOverlayDisplayed(),"SNB select cities Overlay NOT displayed");
//        Assert.assertTrue(snbPage.ifSnbOverlayAllCitiesSelected(),"SNB All cities NOT selected");
    }

    @Title("SNB Overlay : Electronics : Verify by default selected is Bangalore on SNB overlay.")
    @Description("Verify by default selected is Bangalore on SNB overlay.")
    @Test(groups = {"Stage","Prod"},dataProvider = "ClusterCities",dataProviderClass = Data.class)
    public void verifyClusterCitySelectedInFiltersForElectronicsCategories(String clusterCities) {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        hompage.selectCity();
        headerPage.searchCity(clusterCities);
        hompage.selectelementWithoutScroll(clusterCities, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("ElectronicsAndAppliances"), QuikrAppEnums.Home_Categories);
        escrowPage.selectFirstPopularProduct();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        Assert.assertTrue(snbPage.ifCitySelectedInFilters(clusterCities), "City NOT selcted in Filters");
    }

    @Title("SNB Overlay : Electronics : Verify by default selected is Bangalore on SNB overlay.")
    @Description("Verify by default selected is Bangalore on SNB overlay.")
    @Test(groups = {"Stage","Prod"},dataProvider = "ClusterCities",dataProviderClass = Data.class)
    public void verifyClusterCitySelectedInFiltersForLifestyleCategories(String clusterCities) {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        hompage.selectCity();
        headerPage.searchCity(clusterCities);
        hompage.selectelementWithoutScroll(clusterCities, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("HomesAndLifestyle"), QuikrAppEnums.Home_Categories);
        escrowPage.selectFirstPopularProduct();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        Assert.assertTrue(snbPage.ifCitySelectedInFilters(clusterCities), "City NOT selcted in Filters");
    }

}
