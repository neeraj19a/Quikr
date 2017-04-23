package com.quikr.app.android.services.servicesVap;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.services.ServicesPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by rohanbajaj on 12/10/15.
 */
public class ServicesVapTests extends AppAndroidTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_SERVICES_TESTDATA_FILE"));

    /**
     * ANDROID-350:Verify recently viewed ads through home for different category or sub category.
     *                              for services
     */

//    @Test
    public void verifyRecentlyViedAdForServicesCategory()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        VapPage vapPage = new VapPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.clickOnOtherServices();
        servicesPage.selectElements(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
        snbPage.hideOverlayLayout();
        snbPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
        String VapTitle=vapPage.AdTitle();
        vapPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuPage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        String recentlyViewedAdTitle=menuPage.recentlyViewedAdtitle();
        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));
    }

    // Verify shortlisting of ad on any ad on Services Category SNB screen for first time.
    @Test
    public void verifyShorlistingAdForServices() throws Exception
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        VapPage vapPage = new VapPage(driver);
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        servicesPage.swipeVertically(testData.get("showAllOption"), testData.get("servicesSearchBar"));
        servicesPage.clickOnBrowseAds();
        servicesPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
        snbPage.hideOverlayLayout();
        snbPage.openAdFromSnb(0);
        snbPage.hideOverlayLayout();
//        String VapTitle=vapPage.AdTitle();
        String vapAdDesciption = vapPage.getAdDescription();
        snbPage.selectFavouriteButton();
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuPage.selectMyShortlist();
        snbPage.openAdFromSnb(0);
        String shortlistedAdDescription=vapPage.getAdDescription();
        Assert.assertTrue(vapAdDesciption.contains(shortlistedAdDescription));
    }








}
