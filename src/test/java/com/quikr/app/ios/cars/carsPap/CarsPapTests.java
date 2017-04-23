package com.quikr.app.ios.cars.carsPap;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 1/19/16.
 */
public class CarsPapTests extends AppiOSTestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_CARSPAGE_TESTDATA_FILE"));

    /**
     * post ad for cars with less than 30 chars
     */
    @Title("post ad with incomplete title")
    @Test
    public void verifyPostAdWithInCompleteDescriptionForCar()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);

        homePage.selectCityName();
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryNameCar"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        papPage.selectLocalityName();
        papPage.clickOnNextButton();
        papPage.setAdDescription(testData.get("incompleteAdDescription"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.scroll(testData.get("brandNameOptionFoCars"));
        papPage.selectBrandName(testData.get("brandNameOptionFoCars"));
        papPage.clickOnYear();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("yearName"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.setKmsDriven(testData.get("kmsDriven"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.selectNextButtonForcibly();
        Assert.assertTrue(papPage.validatePostAdWithIncompleteAdDescriptions(), "description is less than min words");
    }
}
