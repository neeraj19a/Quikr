package com.quikr.app.ios.services.servicesHomePage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.dataProvider.Data;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.snb.SnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 2/26/16.
 */
public class ServicesHomePageTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_SERVICESPAGE_TESTDATA_FILE"));

    /**
     * S_002
     *
     * Verify Services category on launching the app when a city is selected
     */
    @Title("Verify Services category on launching the app when a city is selected")
    @Test(groups = "horizontal")
    public void verifyServiceCategoryWhenCityIsSelected()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);

      //  Assert.assertTrue(homePage.validateNoOfCityList());
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("servicesCategory"));
        Assert.assertTrue(servicesHomePage.validateServicesPage(),"services page can't be redirected");
    }

    /**
     * S_004
     *
     * Verify the services category menu
     */
    @Title("Verify the services category menu")
    @Test(groups = "horizontal")
    public void verifyServicesMaineSubCategory()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("servicesCategory"));
        Assert.assertTrue(servicesHomePage.validateMaineServicesSubCategoryName(),"services maine subcategory is not present");
    }

    /**
     * S_005
     *
     * Verify Quikr connect in the sevices category menu
     */
    @Title("Verify Quikr connect in the services category menu")
    @Test(groups = "horizontal")
    public void verifyQuikrConnectServicesForHomeServices()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("servicesCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("servicesSubcategoryName"));
        Assert.assertTrue(servicesHomePage.validateQuikrConnectPageOptions(),"all the quikr connect options can't match ");
    }

    /**
     * S_006
     *
     * quikr connect page is not display for  Plumber-Electrician and Carpenters-Furniture work
     */
    @Title("Verify Quikr connect in the services some category menu not display")
    @Test(dataProvider ="servicesSubCat" ,dataProviderClass = Data.class,groups = "horizontal")
    public void verifyQuikrConnectPageIsNotDisplayForSomeCategory(String subcategory)
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("servicesCategory"));
        switch (subcategory)
        {
            case "Plumbers" :
                homePage.selectCategoryOrSubcategory(testData.get("servicesSubcategoryNamePlumber"));
                if (homePage.checkDontAllowButton())
                {
                    realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
                }
                break;
            case "Carpenters - Furniture Work" :
                homePage.selectCategoryOrSubcategory(testData.get("servicesSubcategoryNameCarpenters"));
                if (homePage.checkDontAllowButton())
                {
                    realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
                }
                break;
        }
        Assert.assertTrue(!servicesHomePage.validateQuikrConnectPageOptions()," quikr connect page is redirected ");
    }

    /**
     * S_008
     *
     * Verify the Quikr connect page for skip and connect now button
     */
    @Title("Verify the Quikr connect page for skip and connect now button ")
    @Test(groups = "horizontal")
    public void verifySkipAndConnectNowButton()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("servicesCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("servicesSubcategoryName"));
        Assert.assertTrue(servicesHomePage.validateQuikrConnectPageOptions(),"all the quikr connect options can't match ");
        Assert.assertTrue(servicesHomePage.validateSkipAndConnectNowButtonOnQuikrConnectPage(),"missing skip or connect now button on quikr connect page");
    }

    /**
     * S_020  , S_021
     *
     * Verify when  city is selected and user searches a subcategory in Services
     */
    @Title("Verify when  city is selected and user searches a subcategory in Services")
    @Test(groups = "horizontal")
    public void verifyQuikrConnectPageRedirectedSearchFromHomePageServicesSubcategory()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);

        homePage.selectCityName();
        homePage.clickOnSearchBar();
        homePage.sendTextInSearchBar(testData.get("enterRequiredSearchText"));
        homePage.selectSearchSuggestionOptionName(1);
        Assert.assertTrue(servicesHomePage.validateQuikrConnectPageOptions(),"all the quikr connect options can't match ");
        Assert.assertTrue(servicesHomePage.validateSkipAndConnectNowButtonOnQuikrConnectPage(),"missing skip or connect now button on quikr connect page");
    }

    /**
     * S_022
     *
     * Verify on searching the result for services subcat where quikr connect is not applicable in services
     */
    @Title("Verify on searching the result for services subcat where quikr connect is not applicable in services")
    @Test(groups = "horizontal")
    public void verifyQuikrConnectPageNotRedirectedSearchFromHomePageServicesSubcategory()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
        SnbPage snbPage=new SnbPage(driver);

        homePage.selectCityName();
        homePage.clickOnSearchBar();
        homePage.sendTextInSearchBar(testData.get("enterRequiredSubcategoryForWhichQuikrConnectPageIsNotRedirected"));
        homePage.selectSearchSuggestionOptionName(1);
        if (homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        Assert.assertTrue(!servicesHomePage.validateQuikrConnectPageOptions()," quikr connect page is redirected ");
        Assert.assertTrue(snbPage.validateSnbPageForSortAndFilterButton(),"sort or filter button is missing or not redirected on snb page");
    }
}
