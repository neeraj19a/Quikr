package com.quikr.app.ios.realEstate.realEstateHeaderPage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.realEstate.realEstateHomePage.RealEstateHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 30/11/15.
 */
public class RealEstateHeaderPageTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_REALESTATEHEADERPAGE_TESTDATA_FILE"));

    /**
     * iOS-2440:Verify the options present in the SNB header
     */
    @Title("options present in the SNB header")
    @Test
    public void verifyOptionPresentInSnbHeader()
    {
        HomePage homePage=new HomePage(driver);
        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);

       // homePage.selectDontAllowOption();
        //homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
        realEstateHeaderPage.selectSearchButtonOfKeyboard();
        Assert.assertTrue(realEstateHeaderPage.validateHeaderTextAtSnbPage());
    }

}
