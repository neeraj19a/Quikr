package com.quikr.website.horizontal.alert;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akhil.singla on 31/5/16.
 */
public class ElectronicsandAppliancesAlertTests extends TestBase {

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithCamerasAccessories(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Camera Accessories");
        alertPage.selectadType(adType);
        alertPage.clickAccessoryType();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }


    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithCameraDigiCams(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Cameras - Digicams");
        alertPage.selectadType(adType);
        alertPage.clickProductType();
        alertPage.selectBrand();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithComputerPeripherals(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Computer Peripherals");
        alertPage.selectadType(adType);
        alertPage.clickPeripheralType();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithFaxEPABXOfficeEquipment(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Fax, EPABX, Office Equipment");
        alertPage.selectadType(adType);
        alertPage.clickProductType();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithHomeKitchenAppliances(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Home - Kitchen Appliances");
        alertPage.selectadType(adType);
        alertPage.clickApplianceType();
        alertPage.selectBrand();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithInvertersUPSGenerators(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Inverters, UPS & Generators");
        alertPage.selectadType(adType);
        alertPage.clickProductType();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithiPodsMP3Players(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("iPods, MP3 Players");
        alertPage.selectadType(adType);
        alertPage.selectBrand();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithLaptopsComputers(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Laptops - Computers");
        alertPage.selectadType(adType);
        alertPage.clickproductTypeOptionElectronicsandAppliances();
        alertPage.selectBrand();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithMusicSystemsHomeTheatre(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Music Systems - Home Theatre");
        alertPage.selectadType(adType);
        alertPage.selectBrand();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithOfficeSupplies(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Office Supplies");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithSecurityEquipmentProducts(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Security Equipment - Products");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithToolsMachineryIndustrial(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Tools - Machinery - Industrial");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithTVDVDMultimedia(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("TV - DVD - Multimedia");
        alertPage.selectadType(adType);
        alertPage.clickProductType();
        alertPage.selectBrand();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithVideoGamesConsoles(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Video Games - Consoles");
        alertPage.selectadType(adType);
        alertPage.selectBrand();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void ElectronicsandApplianceswithEverythingElse(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Electronics & Appliances");
        alertPage.selectSubCategory("Everything Else");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFieldsCameras(email,mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

}
