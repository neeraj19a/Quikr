package com.quikr.website.cars.carsPAP;

import com.quikr.app.ios.cars.carsHomePage.CarsHomePage;
import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.CarsTestBase;
import com.quikr.website.cars.carsHeader.CarsHeaderPage;
import com.quikr.website.cars.carsSNB.CarsSNBPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 25/7/16.
 */
public class CarsPAPTests  extends CarsTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

    /**
     * Testcase - Validate Post Ad from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void testPostAd(){

        CarsHeaderPage carsHeaderPage=new CarsHeaderPage(driver);
        CarsPAPPage carsPAPPage=new CarsPAPPage(driver);
        SoftAssert softAssert=new SoftAssert();

        navigateToCarsHomePage();
        carsHeaderPage.clickPostAd();
        String title="2007 Honda City For Sale in MG Road. Driven 45,000 kms";
        String name=getRandomString(10);
        String description=title+ "in a great condition, New Tyres";
        carsPAPPage.postCarAd(title, name, description);
        Assert.assertTrue(waitForPageToLoad("https://secure.quikr.com/securepaymentpage?id="), "Looks like Payment Page is not loaded on Postinbg Ad");
        carsPAPPage.skipPayment();
        waitForPageToLoad("http://quikr.com");
        softAssert.assertTrue(carsPAPPage.isThanksMessageavailable(), "Looks like Thanks Message not coming after posting Ad, Pls Check");
        softAssert.assertTrue(getCurrentUrl().contains(carsPAPPage.getAdId()), "Looks like Ad Id is not proper");
    }

    /**
     * Testcase - Validate Post Ad Page from Cars HomePage
     */
    @Test(groups = "carsSanity")
    public void validatePAPPage(){

        CarsHeaderPage carsHeaderPage=new CarsHeaderPage(driver);
        CarsPAPPage carsPAPPage=new CarsPAPPage(driver);
        SoftAssert softAssert=new SoftAssert();

        navigateToCarsHomePage();
        waitForPageToLoad("quikr.com/Cars-Bikes");
        carsHeaderPage.clickPostAd();
        softAssert.assertTrue(carsPAPPage.isSignINPresent(), "Sign In Button not Present on Cars PAP");
        softAssert.assertTrue(carsPAPPage.isHelpPresent(), "Help Link not Present on Cars PAP");
        softAssert.assertTrue(carsPAPPage.isLanguagePresent(), "Language Link not Present on Cars PAP");
        softAssert.assertTrue(carsPAPPage.validateTextBoxesonPAP(),"Text Boxes on PAP Page are not proper");
        softAssert.assertAll();
    }

    /**
     * Testcase - Validate Post Ad Page from Cars HomePage for Delhi
     */
    @Test(groups = "carsSanity")
    public void validatePAPPageForDelhi(){

        CarsHeaderPage carsHeaderPage=new CarsHeaderPage(driver);
        SoftAssert softAssert=new SoftAssert();
        CarsPageBase carsPageBase=new CarsPageBase(driver);
        com.quikr.website.cars.carsHome.CarsHomePage carsHomePage=new com.quikr.website.cars.carsHome.CarsHomePage(driver);
        CarsSNBPage carsSNBPage=new CarsSNBPage(driver);


        navigateToCarsHomePage();
        carsHomePage.toggleUsedCars();
        carsHomePage.search();
        carsPageBase.closeCityPopup();
        carsSNBPage.selectBrand("Hyundai");
        waitForPageToLoad("quikr.com/Hyundai/Cars/");
        carsPageBase.closeCityPopup();
        carsSNBPage.selectCityFromSNB("Delhi");
        createorEditCookieValue("abRand", "45");
        carsPageBase.closeCityPopup();
        carsHeaderPage.clickPostAd();

    }


}
