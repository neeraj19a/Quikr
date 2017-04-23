package com.quikr.app.ios.snb;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
* Created by Manvendra Singh on 5/10/15.
*/
public class SnbTests extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_SNBFORGENERAL_TESTDATA_FILE"));


    /**
     * iOS-6:Filter is applied correctly on S&B page
     */
    @Title("Filter is applied correctly on S&B paged")
    @Test(groups = "horizontal")
    public void verifyFilterOnSnbPage()
    {

        // related to filter

        
//        HomePage homePage=new HomePage(driver);
//        SnbPage snbPage=new SnbPage(driver);
//        PapPage papPage=new PapPage(driver);
//        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);
//        VapPage vapPage=new VapPage(driver);
//
//     //   homePage.selectDontAllowOption();
//        //homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
//        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
//        if(homePage.checkDontAllowButton())
//        {
//            homePage.selectDontAllowOption();
//        }
//        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
//        papPage.selectApplyButton();
//        snbPage.clickOnFilterButtonOnSnbPage();
//        snbPage.clickOnBrandName();
//        snbPage.selectBrandNameOption();
//        papPage.selectApplyButton();
//        papPage.selectApplyButton();
//        for (int i=2;i<4;i++)
//        {
//            //quikrXSnbPage.openAdOnSnbPage(i);
//            snbPage.openAdOnSnbPageForGeneralCategory(i);
//            Assert.assertTrue(snbPage.validateFilterOnSnbPage(),"Can't filter according to apply filter");
//           // quikrXVapPage.clickOnBackButtonOnVapPage();
//            vapPage.clickOnVapBackButton();
//        }
    }

    /**
     * test for sorting in mobile and tablets category
     */
    @Title("sorting in mobile and tablets category")
    @Test(groups = "horizontal")
    public void verifyAscendingSortByPriceForMobileAndTablets()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

     //   homePage.selectDontAllowOption();
        //homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
       //snbPage.selectPriceButton();
        realEstateSnbPage.clickOnSortButtonInRealEstateSnbPage();
        realEstateSnbPage.selectLowestPrice();
        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage(),"some issue in sorting as needed");
    }

    /**
     * H_171
     *
     * verify the sort options
     */
    @Title("sort options in mobile and tablets category")
    @Test(groups = "horizontal")
    public void verifySortOptionsForMobileCategory()
    {


        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
        realEstateSnbPage.clickOnSortButtonInRealEstateSnbPage();
        Assert.assertTrue(snbPage.validateSortOptionsForMobileOrElectronicsCategory(),"all the required options in sort are not present ");
    }
}
