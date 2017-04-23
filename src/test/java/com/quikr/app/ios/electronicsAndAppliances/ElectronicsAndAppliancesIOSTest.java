package com.quikr.app.ios.electronicsAndAppliances;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.snb.SnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 30/9/15.
 */
public class ElectronicsAndAppliancesIOSTest extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_ELECTRONICSANDAPPLIANCES_TESTDATA_FILE"));


    @Title(" verify the post ad for electronics category")
    @Test(groups = "horizontal")
    public void verifyPostAd()
    {
        PapPage papPage=new PapPage(driver);
        HomePage homePage=new HomePage(driver);
       // ElectronicsAndAppliances electronicsAndAppliances=new ElectronicsAndAppliances(driver);


//        homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        //homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
       // homePage.selectElements(testData.get("electronicsCat"), QuikrAppEnums.CATEGORY_LIST_IN_POST_AD);
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryValue"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        //papPage.selectLocalityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("localityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnNextButton();
        papPage.setAdTitle(testData.get("adTitle"));
        papPage.setAdDescription(testData.get("adDescription"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.selectProductType(testData.get("productTypeName"));
        papPage.clickOnBrandName();
        papPage.selectBrandName(testData.get("brandNameOptionForElectronicsCategory"));
        papPage.setPriceForElectronicsInPostAd(testData.get("priceAmount"));
       // papPage.selectApplyButton();
//        papPage.clickOnOptionalDetails();
//        papPage.setPrice(testData.get("priceAmount"));
//        papPage.clickOnScreenSize();
//        papPage.selectScreenSize();
//        papPage.selectApplyButton();
//        papPage.clickOnAlsoIncludes();
//        papPage.selectAlsoIncludesOptionName(testData.get("alsoIncludesOptionNameForElectronicsCategory"));
//        papPage.selectApplyButton();
//       // papPage.clickOnNextButton();
        papPage.setPinCodeInPostAd(testData.get("pinCode"),9);
        papPage.setAddressInPostAd(testData.get("address"),10);
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.setMobileNo(testData.get("mobileNo"));
        papPage.selectPostAdButton();
       // Assert.assertTrue(papPage.validatePostAd(),"ad can't be posted successfully");
        Assert.assertEquals(papPage.validatePostAd(),testData.get("successMsgAfterAdPost"),"ad can't be posted successfully");
    }

    /**
     * iOS-133:Post ad with incomplete title
     *
     * iOS-134:Post ad with incomplete descriptions
     */
    @Title("Post ad with incomplete title and  descriptions")
    @Test(groups = "horizontal")
    public void verifyPostAdWithIncompleteTitle()
    {
        PapPage papPage=new PapPage(driver);
        HomePage homePage=new HomePage(driver);
     //   ElectronicsAndAppliances electronicsAndAppliances=new ElectronicsAndAppliances(driver);

  //      homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
      //  homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryValue"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
      //  papPage.selectLocalityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("localityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnNextButton();
        papPage.setAdTitle(testData.get("incompleteAdTitle"));
        papPage.setAdDescription(testData.get("incompleteAdDescription"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.selectProductType(testData.get("productTypeName"));
        papPage.clickOnBrandName();
        papPage.selectBrandName(testData.get("brandNameOptionForElectronicsCategory"));
        papPage.setPriceForElectronicsInPostAd(testData.get("priceAmount"));
       // papPage.selectApplyButton();
//        papPage.clickOnOptionalDetails();
//        papPage.setPrice(testData.get("priceAmount"));
//        papPage.clickOnScreenSize();
//        papPage.selectScreenSize();
//        papPage.selectApplyButton();
//        papPage.clickOnAlsoIncludes();
//        papPage.selectAlsoIncludesOptionName(testData.get("alsoIncludesOptionNameForElectronicsCategory"));
//        papPage.selectApplyButton();
        papPage.setPinCodeInPostAd(testData.get("pinCode"),9);
        papPage.setAddressInPostAd(testData.get("address"),10);
        papPage.selectNextButtonForcibly();
        Assert.assertTrue(papPage.validatePostAdWithIncompleteAdTitle(),"title provided as min words or more than ");
        Assert.assertTrue(papPage.validatePostAdWithIncompleteAdDescriptions(),"description is less than min words");
    }

    /**
     *  iOS-143:Sort by category Electronics and Appliances by Price
     */
    @Title("Sort by category Electronics and Appliances by Price")
    @Test(groups = "horizontal")
    public void verifyAscendingSortByPriceForElectronicsAndAppliances()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
        PapPage papPage=new PapPage(driver);

    //    homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
      //  homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if (homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        //snbPage.selectPriceButton();
        realEstateSnbPage.clickOnSortButtonInRealEstateSnbPage();
        realEstateSnbPage.selectLowestPrice();
        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage(),"some issue in sorting as needed");
    }

    /**
     * H_172
     *
     * verify sort options for the electronics category
     */
    @Title("sort options for the electronics category")
    @Test(groups = "horizontal")
    public void verifySortOptionsForElectronicsCategory()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
       // realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        realEstateSnbPage.clickOnSortButtonInRealEstateSnbPage();
        Assert.assertTrue(snbPage.validateSortOptionsForMobileOrElectronicsCategory(),"all the required options for electronics category are not present");
    }
}
