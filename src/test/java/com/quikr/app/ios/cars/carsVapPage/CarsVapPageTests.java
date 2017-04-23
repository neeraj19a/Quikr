package com.quikr.app.ios.cars.carsVapPage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.vap.VapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;


/**
 * Created by Manvendra Singh on 1/19/16.
 */
public class CarsVapPageTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_CARSPAGE_TESTDATA_FILE"));

    /**
     * C_001
     *
     * verify post ad for cars without optional details and also verify preview screen
     */
    @Title("post ad for cars without optional details and also verify preview screen")
    @Test
    public void verifyPostAdForCarsWithoutOptionalDetails()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
        VapPage vapPage=new VapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

       // homePage.clickOnSelectCity();
       // homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityName"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryNameCar"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
      //  papPage.selectLocalityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("localityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnNextButton();
        papPage.setAdDescription(testData.get("adDescription"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.scroll(testData.get("brandNameOptionFoCars"));
        papPage.selectBrandName(testData.get("brandNameOptionFoCars"));
       // papPage.selectApplyButton();
        papPage.clickOnYear();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("yearName"));
        papPage.selectRequiredTextAsInSearchBox(1);
      //  papPage.selectApplyButton();
        papPage.setKmsDriven(testData.get("kmsDriven"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.setMobileNo(testData.get("mobileNo"));
        papPage.selectPostAdButton();
        Assert.assertEquals(papPage.validatePostAd(),testData.get("successMsgAfterAdPost"),"some issue in posting ad");
        papPage.clickOnViewYourAdButton();
        Assert.assertTrue(vapPage.validatePreviewAdPost(),"preview ad post screen are not display");
    }

    /**
     * verify post ad for cars with optional details for cars
     */
    @Title("post ad for cars with optional details for cars")
    @Test
    public void verifyPostAdForCarsWithOptionalDetails()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
        VapPage vapPage=new VapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

      //  homePage.clickOnSelectCity();
       // homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityName"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryNameCar"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
           realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
       // papPage.selectLocalityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("localityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnNextButton();
        papPage.setAdDescription(testData.get("adDescription"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.scroll(testData.get("brandNameOptionFoCars"));
        papPage.selectBrandName(testData.get("brandNameOptionFoCars"));
       // papPage.selectApplyButton();
        papPage.clickOnYear();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("yearName"));
        papPage.selectRequiredTextAsInSearchBox(1);
      //  papPage.selectApplyButton();
      //  papPage.clickOnKmsDriven();
        papPage.setKmsDriven(testData.get("kmsDriven"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.clickOnOptionalDetailsForCars();
        papPage.clickOnModel();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("carModelName"));
        papPage.selectRequiredTextAsInSearchBox(1);
       // papPage.selectApplyButton();
        papPage.clickOnVariantOrVersion();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("carsVariant"));
        papPage.selectRequiredTextAsInSearchBox(1);
       // papPage.selectApplyButton();
        papPage.clickOnFuelType();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("carFuelType"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnColor();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("carColor"));
        papPage.selectRequiredTextAsInSearchBox(1);

      //  papPage.selectApplyButton();
        papPage.setPriceForCars(testData.get("priceForCar"));
        papPage.clickOnNoOfOwners();
        papPage.selectRequiredTextAsInSearchBox(1);
       // papPage.selectApplyButton();
      //  papPage.clickOnInsuranceValidTill();
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.setMobileNo(testData.get("mobileNo"));
        papPage.selectPostAdButton();
        Assert.assertEquals(papPage.validatePostAd(),testData.get("successMsgAfterAdPost"),"some issue in posting ad");
        papPage.clickOnViewYourAdButton();
        Assert.assertTrue(vapPage.validatePreviewAdPost(),"preview ad post screen are not display");
    }

    /**
     * verify post ad for bikes
     */
    @Title("post ad for bikes")
    @Test
    public void verifyPostAdForBikes()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
        VapPage vapPage=new VapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

     //   homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityName"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryNameBikes"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
       // papPage.selectLocalityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("localityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnNextButton();
        papPage.setAdDescription(testData.get("adDescriptionForBikes"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("brandOptionNameForBike"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnYear();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("yearName"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.setKmsDriven(testData.get("kmsDriven"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.clickOnOptionalDetailsForBikes();
        papPage.clickOnModel();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("modelOptionNameForBike"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.setPriceForBikes(testData.get("priceForBikes"));
        papPage.clickOnNoOfOwners();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("noOfOwnersForBike"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.setMobileNo(testData.get("mobileNo"));
        papPage.selectPostAdButton();
        Assert.assertEquals(papPage.validatePostAd(),testData.get("successMsgAfterAdPost"),"some issue in posting ad");
        papPage.clickOnViewYourAdButton();
        Assert.assertTrue(vapPage.validatePreviewAdPost(),"preview ad post screen are not display");
    }
}
