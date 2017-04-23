package com.quikr.app.ios.menu;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 6/10/15.
 */
public class MenuTests extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_MENU_TESTDATA_FILE"));



    /**
     * iOS-113:App version correctly reflected
     */
    @Title("App version correctly reflected")
    @Test(groups = "horizontal")
    public void verifyAppVersion()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

      //  homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
       // homePage.clickOnMenuIconButton();
        homePage.selectMoreButtonOnHomePage();
        Assert.assertTrue(menuPage.validateAppVersion(),"app version correctly can't reflected");
    }

    /**
     * iOS-114:App feedback
     */
    @Title("App feedback")
    @Test(groups = "horizontal")
    public void verifyFeedback()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

      //  homePage.selectDontAllowOption();
        homePage.selectCityName();
        homePage.selectMoreButtonOnHomePage();
       // homePage.clickOnMenuIconButton();
         menuPage.selectFeedbackButton();
        Assert.assertTrue(menuPage.validateFeedback(),"feedback page can't open");
    }

    /**
     * iOS-117:Rate quikr App
     */
    @Title("Rate quikr App")
    @Test(groups = "horizontal")
    public void verifyRateUs()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

        //homePage.selectDontAllowOption();
//        homePage.clickOnMenuIconButton();
        homePage.selectCityName();
        homePage.selectMoreButtonOnHomePage();
        menuPage.selectRateUs();
       // menuPage.selectYesButtonOnRateUsScreen();
        menuPage.selectStarInRateUs();
        Assert.assertTrue(menuPage.validateRateUs(),"rate us functionality is not working properly");
    }

    /**
     * iOS-132:Share App by mail
     */
    @Title("Share App by mail")
    @Test(groups = "horizontal")
    public void verifyShareApp()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

      //  homePage.selectDontAllowOption();
       // homePage.clickOnMenuIconButton();
        homePage.selectCityName();
        homePage.selectMoreButtonOnHomePage();
        menuPage.selectShareButton();
        menuPage.selectMail();
        Assert.assertTrue(menuPage.validateShareApp(),"ad can't share with mail");
    }

    /**
     * Check if  My account screen have the following options
     */
    @Title("My account screen have the following options")
    @Test(groups = "horizontal")
    public void verifyMyAccountScreenOptions()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

        homePage.selectCityName();
        homePage.selectAccountButton();
        Assert.assertTrue(menuPage.validateMyAccountOptions(),"all options are not present");
    }

    /**
     * H_012
     *
     * verify legal and policy options
     */
    @Title(" legal and policy options")
    @Test(groups = "horizontal")
    public void verifyLegalAndPolicyOptions()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

        homePage.selectCityName();
        homePage.selectMoreButtonOnHomePage();
        menuPage.clickOnLegalAndPolicyButton();
        Assert.assertTrue(menuPage.validateLegalAndPolicyOptions(),"required option are not present in legal and policy options");
    }

    /**
     * H_006
     *
     * verify ads nearby
     */
    @Title("ads nearby")
    @Test(groups = "horizontal")
    public void verifyAdsNearBy()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

        homePage.selectCityName();
        homePage.selectMoreButtonOnHomePage();
        menuPage.clickOnAdsNearBy();
        if (homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        Assert.assertTrue(menuPage.validateAdsNearBy(),"ads nearby page is not display");
    }

    /**
     * H_007
     *
     * verify poplar ads in bangalore
     */
    @Title("poplar ads in bangalore")
    @Test(groups = "horizontal")
    public void verifyPopularAds()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

        homePage.selectCityName();
        homePage.selectMoreButtonOnHomePage();
        menuPage.clickOnPopularAdsInCityName();
        Assert.assertTrue(menuPage.validatePopularAdsPage(),"popular ads page is not display");
    }

    /**
     * H_010
     *
     * verify contact us
     */
    @Title("contact us")
    @Test(groups = "horizontal")
    public void verifyContactUs()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

        homePage.selectCityName();
        homePage.selectMoreButtonOnHomePage();
        menuPage.clickOnContactUsTab();
        Assert.assertTrue(menuPage.validateContactUs(),"all the required options for contact us are not present");
    }

    /**
     * RC_039
     *
     * Check the "My Ads "screen
     */
    @Title("Check the \"My Ads \"screen")
    @Test(groups = "horizontal")
    public void verifyMyAdsScreen()
    {
        HomePage homePage=new HomePage(driver);
        MenuPage menuPage=new MenuPage(driver);

        homePage.selectCityName();
        homePage.selectAccountButton();
        menuPage.selectMyAds();
        Assert.assertTrue(menuPage.validateMyAdsPage(),"my ads page is not redirected or their are no ads on my ads page");

    }
}
