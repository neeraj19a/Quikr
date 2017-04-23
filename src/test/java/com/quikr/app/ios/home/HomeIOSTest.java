package com.quikr.app.ios.home;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.pap.PapPage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.snb.SnbPage;
import com.quikr.app.ios.vap.VapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 30/9/15.
 */
public class HomeIOSTest extends AppiOSTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_HOME_TESTDATA_FILE"));



    @Title(" validate city")
    @Test(groups = "horizontal")
    public void verifyCity()
    {
        HomePage homePage=new HomePage(driver);

        //homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
       // homePage.selectelementWithoutScroll(testData.get("cityName"), QuikrAppEnums.Hompage_SelectCity);
        homePage.selectCityName();
        Assert.assertEquals(homePage.validateCityName(),testData.get("expectedCityName"),"city can't be selected or diff city selected");

    }

    /**
     * iOS-16:User is able to perform search based on 'search suggestion'
     */
    @Title(" User is able to perform search based on 'search suggestion")
    @Test(groups = "horizontal")
    public void verifySearchSuggestionOptionAndSearchResult()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.clickOnSearchBar();
        homePage.sendTextInSearchBar(testData.get("enterRequiredSearchText"));
        Assert.assertTrue(homePage.validateSearchSuggestionOptions(testData.get("enterRequiredSearchText")));
        homePage.selectSearchSuggestionOptionName(2);
      //  Assert.assertTrue(snbPage.validateRequiredNameFromAdTitleOnSnbPage(testData.get("enterRequiredSearchText")));

        // incomp only for last line

    }

    /**
     * test for to change city
     */
    @Title("validate change city")
    @Test(groups = "horizontal")
    public void verifyChangeCity()
    {
        HomePage homePage =new HomePage(driver);
        PapPage papPage=new PapPage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.clickOnCurrentSelectedCity(testData.get("expectedCityName"));
        homePage.selectCrossButtonOnAllowLocationPopup();
       // homePage.changeCity(testData.get("changeCityName"));
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("changeCityName"));
        papPage.selectRequiredTextAsInSearchBox(1);
        Assert.assertTrue(homePage.validateChangeCity(testData.get("city")));
    }

    /**
     * verify option present on home page after city selection
     */
    @Title("verify option present on home page after city selection")
    @Test(groups = "horizontal")
    public void verifyQuikrXOptionPresentOnHomePageAfterPopularCitySelection()
    {
        HomePage homePage =new HomePage(driver);

       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        Assert.assertTrue(homePage.validateQuikrXOptionOnHomePageAfterCitySelection(),"quikrX option are not coming after city selection");
    }

    /**
     * H_005
     *
     * verify recently viewed ad status on home page before any ad view and after ad viewed
     */
    @Title("recently viewed ad status on home page before any ad view and after ad viewed")
    @Test(groups = "horizontal")
    public void verifyRecentlyViewedAd()
    {
        HomePage homePage =new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        VapPage vapPage=new VapPage(driver);
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
        snbPage.openAdOnSnbPageForGeneralCategory(1);
        vapPage.clickOnVapBackButton();
        homePage.selectHomeButton();
        Assert.assertTrue(homePage.validateRecentlyViewedAdText(),"recently view ad text is not appear on home page screen");
        Assert.assertTrue(homePage.validateSeeAllButton(),"seeAll button is not display for the recently viewed ad");
        homePage.selectRecentlyViewedAd(1);
        Assert.assertTrue(vapPage.validateShortlistButtonBeforeSelect(),"recently viewed ads can't open");
    }

    /**
     * verify the seeAll button is present on home screen for quikrX
     */
    @Title("seeAll button is present on home screen for quikrX")
    @Test(groups = "horizontal")
    public void verifySeeAllButtonForQuikrX()
    {
        HomePage homePage =new HomePage(driver);

        homePage.selectCityName();
        Assert.assertTrue(homePage.validateSeeAllButton(),"see all button is not display for the quikrX");
    }

    /**
     * H_148
     *
     * Check the More button in the categories screen
     */
    @Title("Check the More button in the categories screen")
    @Test(groups = "horizontal")
    public void verifyMoreButtonInHomeScreen()
    {
        HomePage homePage =new HomePage(driver);

        homePage.selectCityName();
        Assert.assertTrue(homePage.validateMoreButtonOnHomePage(),"more button is not display on home page");
    }

    /**
     * H_149
     *
     * Check the Less button in the categories screen
     */
    @Title("Check the Less button in the categories screen")
    @Test(groups = "horizontal")
    public void verifyLessButtonOnHomePage()
    {
        HomePage homePage =new HomePage(driver);

        homePage.selectCityName();
        homePage.clickOnMoreButtonInCategory();
        Assert.assertTrue(homePage.validateLessButtonOnHomePage(),"less button is not display");
    }

    /**
     * H_155
     *
     * Quikr X phones module
     */
    @Title("Quikr X phones module for quikrX certified")
    @Test(groups = "horizontal")
    public void  verifyQuikrXCertifiedAdCount()
    {
        HomePage homePage =new HomePage(driver);

        homePage.selectCityName();
        Assert.assertTrue(homePage.validateQuikrXAdOnHomePage(),"ad count are less than 10 or more than 10 in quikrXCertified section");
    }

    /**
     * H_155
     *
     * Quikr X phones module
     */
    @Title("Quikr X phones module for quikrX exchange for new")
    @Test(groups = "horizontal")
    public void  verifyQuikrXExchangeForNewAdCount()
    {
        HomePage homePage =new HomePage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        Assert.assertTrue(homePage.validateQuikrXAdOnHomePage(),"ad count are less than 10 or more than 10 in quikrX Exchange for new section");
    }

    /**
     * H_168
     *
     * The cancel button against the search text field in the search overlay
     */
    @Title("The cancel button against the search text field in the search overlay")
    @Test(groups = "horizontal")
    public void verifyInSearchBoxClearButtonFunctionality()
    {
        HomePage homePage=new HomePage(driver);

        homePage.selectCityName();
        homePage.clickOnSearchBar();
        homePage.sendTextInSearchBar(testData.get("enterRequiredSearchText"));
        homePage.clickOnClearButtonInSearchBox();
        Assert.assertTrue(!homePage.validateClearButton(),"clear button present");
    }

}
