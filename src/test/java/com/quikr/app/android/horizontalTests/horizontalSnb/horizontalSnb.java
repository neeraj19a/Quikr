package com.quikr.app.android.horizontalTests.horizontalSnb;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.Constants.MobileConstants;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.services.ServicesPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Swatantra singh on 1/2/16.
 */
public class horizontalSnb extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HORIZONTAL_TESTDATA_FILE"));

    /**
     * Check for following Sort options
     */
    @Stories("SNB")
    @Title("Verify Sorting option ")
    @Test(dataProvider = "sortingOptions",dataProviderClass = Data.class)
    public void checkSortingOptions(String options[])
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        escrowPage.clickOnSortButtonOnSnb();
        List<String>sortingOptions=escrowPage.sortOPtions();
        System.out.println(sortingOptions);
        for (int i=0;i<options.length;i++)
        {
            Assert.assertTrue(options[i].equalsIgnoreCase(sortingOptions.get(i)),"all Sorting options not available or Oder MissMAtch"+">>Expected="+options[i]+">>Actual="+sortingOptions.get(i));
        }

    }
    /**
     * Verify appropriate search results are displayed after applying filters for Brand and Model in "Mobiles & Tablets"
     * Filter is applied correctly on SNB page
     */
    @Title("Filter is applied correctly on SNB page")
    @Stories({"Filters","SNB"})
    @Test
    public void filterIsAppiliedCorrectlyOnSnb()
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        escrowPage.clickOnSelectLocalityFilter();
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        escrowPage.selectFiltersOnFilterPage(testData.get("selectBrand"));
        alertPage.selectelementWithoutScroll(testData.get("escrowbrand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        snbPage.selectApplyButton();
        String locality=escrowPage.SnbLocality();
        System.out.println(locality);
        Assert.assertTrue(locality.equalsIgnoreCase(testData.get("location")));

    }

    /**
     * Sorting is applied correctly on Popular Ads , Near by Ads page
     */
    @Stories("SNB")
    @Title("Sorting is applied correctly on Popular Ads , Near by Ads page")
    @Test(dataProvider ="HomePageAdTypes",dataProviderClass = Data.class)
    public void SortingOptionsForAdTypesOnHomePage(String homePageAdTypes)throws Exception
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        MenuPage menuPage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);
        switch (homePageAdTypes)
        {
            case "Popular Ads":
                hompage.swipeVertically();
                hompage.explicitWait(2);
                hompage.clickOnMoreButtonToOpenAdsFromHOmePage(0);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                snbPage.hideOverlayLayout();
                escrowPage.clickOnSortButtonOnSnb();
                List<String>sortingOptions=escrowPage.sortOPtions();
                System.out.println(sortingOptions);
                Assert.assertTrue(testData.get("Mostrecrent").equalsIgnoreCase(sortingOptions.get(0)));
                Assert.assertTrue(testData.get("nearest").equalsIgnoreCase(sortingOptions.get(1)));
                break;
            case  "Nearby Ads":
                hompage.scroll("Nearby Ads");
                hompage.clickOnMoreButtonToOpenAdsFromHOmePage(0);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                snbPage.hideOverlayLayout();
                escrowPage.clickOnSortButtonOnSnb();
                List<String>sortingOptions1=escrowPage.sortOPtions();
                System.out.println(sortingOptions1);
                Assert.assertTrue(testData.get("Mostrecrent").equalsIgnoreCase(sortingOptions1.get(0)));
                Assert.assertTrue(testData.get("nearest").equalsIgnoreCase(sortingOptions1.get(1)));

                break;
            case "Recommended Ads":
                menuPage.clickOnMenuIcon();
                menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
                hompage.closeFullScreenInterstitialFromVAP();
                alertPage.clickOnMatchingAds();
                alertPage.clickOnSortButtOnAlertPage();
                List<String>sortingOptions2=escrowPage.sortOPtions();
                System.out.println(sortingOptions2);
                System.out.println(MobileConstants.sortOptionForRecomendedAds);
                for (int i=0;i< MobileConstants.sortOptionForRecomendedAds.size();i++)
                {
                    Assert.assertTrue(MobileConstants.sortOptionForRecomendedAds.get(i).trim().equalsIgnoreCase(sortingOptions2.get(i).trim()));
                }

        }
    }

    /**
     * "Able to apply Filter and then sort on the Filtered results
     */
    @Stories("Filters")
    @Title("User is Able to apply Filter and then sort on the Filtered results")
    @Test
    public void userIsAbleToSortFilteredReults()
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectFilterButtonOnSnbPage();
        escrowPage.clickOnSelectLocalityFilter();
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        escrowPage.selectFiltersOnFilterPage(testData.get("selectBrand"));
        alertPage.selectelementWithoutScroll(testData.get("escrowbrand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        snbPage.selectApplyButton();
        escrowPage.clickOnSortButtonOnSnb();
        snbPage.selectelementWithoutScroll(testData.get("lowestPriceFirstSOrt"),QuikrAppEnums.SNB_SORT_OPTIONS);
        List<String>snbLocation=snbPage.snbLocality();
        System.out.println(snbLocation);
        List<String>snbTitle=snbPage.snbTitle();
        System.out.println(snbTitle);
        for (int i=1;i<snbLocation.size();i++)
        {
            Assert.assertTrue(snbLocation.get(i).contains(testData.get("location")));
        }
        for (int i=1;i<snbLocation.size();i++)
        {
            Assert.assertTrue(snbTitle.get(i).toUpperCase().contains(testData.get("escrowbrand").toUpperCase())||snbTitle.get(i).toUpperCase().contains(testData.get("escrowbrand1").toUpperCase()));
        }
        Assert.assertTrue(snbPage.validateAscendingSortOnSnbPage(),"unAble to apply sort on Filtered results");

    }
    /**
     * Verify Chat button and the chat presence is displayed in the following S&B pages
     a)Recommended For You / Matching Ads
     b) My Shortlist Ads
     c) Recently Viewed Ads
     */@Stories("SNB")
    @Title("Verify Chat button and the chat presence")
    @Test(dataProvider = "chatPresenceRecomendedShortlistAndRecently",dataProviderClass = Data.class)
    public void vlidateChatButtonOnSnbForRecomendedShortlistAndRecentlyViewedAds(String chatPresence)throws Exception
    {
        EscrowPage escrowPage=new EscrowPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        VapPage vapPage=new VapPage(driver);
        MenuPage menuPage=new MenuPage(driver);
        switch (chatPresence)
        {
            case "Shortlisted Ads":
                hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
                hompage.closeFullScreenInterstitialFromCategoryPage();
                escrowPage.selectMobilesSubcat();
                snbPage.hideOverlayLayout();
                escrowPage.selectCityAsCurrentCityOnVapEscrow();
                snbPage.openAdFromSnb(1);
                snbPage.hideOverlayLayout();
                vapPage.shortListAdsOnVAp();
                snbPage.navigateBack();
                hompage.closeFullScreenInterstitialFromCategoryPage();
                snbPage.selectMenuButtonAtSnbPage();
                menuPage.selectElements(chatPresence, QuikrAppEnums.CATEGORY_MENU);
                Assert.assertTrue(menuPage.chatButtonIsPresent(), "chat button is not present for " + chatPresence);
                break;
//            case "Recently Viewed Ads":
//                hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
//                escrowPage.selectMobilesSubcat();
//                snbPage.hideOverlayLayout();
//                escrowPage.selectCityAsCurrentCityOnVapEscrow();
//                snbPage.openAdFromSnb(1);
//                snbPage.hideOverlayLayout();
//                snbPage.navigateBack();
//                snbPage.selectMenuButtonAtSnbPage();
//                menuPage.selectElements(chatPresence, QuikrAppEnums.CATEGORY_MENU);
//                Assert.assertTrue(menuPage.chatButtonIsPresent(), "chat button is not present for " + chatPresence);
//                break;
            case"Recommended Ads":
                AlertPage alertPage = new AlertPage(driver);
                menuPage.clickOnMenuIcon();
                menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
                hompage.closeFullScreenInterstitialFromVAP();
                alertPage.clickOnMatchingAds();
                Assert.assertTrue(menuPage.validateMakeAnOfferIsPresent(), "chat button is not present for " + chatPresence);

        }


    }
    /**
     *- Check translated text is displayed in SNB and VAP
     */
    @Stories("Vernac")
    @Title("Check translated text is displayed in SNB and VAP")
   // @Test(dataProvider = "language",dataProviderClass = Data.class)
    public  void verifyTranslatedLanguageIsDisplayedOnVapAnDSnb(String lang,String options[])
    {
        MenuPage menuePage = new MenuPage(driver);
        VapPage vapPage=new VapPage(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.selectMenuButtonAtSnbPage();
        menuePage.selectelementWithoutScroll(testData.get("language"), QuikrAppEnums.CATEGORY_MENU);
        hompage.selectelementWithoutScroll(lang, QuikrAppEnums.HOMEPAGE_CATEGORY);
        hompage.selectLanguageOnLaunch();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        String sort=snbPage.sortButtonText();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        String chat=vapPage.chatButtonText();
        Assert.assertTrue(sort.equals(options[0]) && chat.equals(options[1]), "Translated text not displayed for Language" + lang);


    }
    /**
     * Users Nearby: Tapping "Users Nearby" callout should display SNB screen and ads displayed should be within the required range (5-10kms)
     */
    // @Test
    public void validateOnTappingNearbyUserAdsAreDisplayedWithinRange()
    {
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
    }
    /**
     * Tapping "Chat" from SNB should display chat screen and shouild be able to intitaite a Chat
     */
    @Stories("SNB")
    @Title("Tapping \"Chat\" from SNB should display chat screen")
    @Test(dataProvider = "horizontalCategories",dataProviderClass = Data.class)
    public void verifyChatScreenIsInitiatedAfterTapingChatButton(String categories)
    {
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("showMoreCategories"), QuikrAppEnums.Home_Categories);
        hompage.selectelementWithoutScroll(categories, QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMoreButton();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.chatButtonIsPresentOnSnb(), "chat button is not present on snb of " + categories);
    }
    /**
     * "Chat" button should not be displayed in SNB of Services
     */
    @Stories("SNB")
    @Title("Chat button should not be displayed in Services Snb")
    @Test
    public void validateChatButtonIsNotPresentOnServicesSnb()
    {
        Hompage homePage = new Hompage(driver);
        ServicesPage servicesPage = new ServicesPage(driver);
        SnbPage snbPage = new SnbPage(driver);
//        homePage.selectcategoriesFromHomePageCategoryMenu();
        homePage.selectelementWithoutScroll(testData.get("SERVICES"), QuikrAppEnums.Home_Categories);
        homePage.closeFullScreenInterstitialFromCategoryPage();
        servicesPage.scroll((testData.get("browseAdsOption")));
        servicesPage.clickOnBrowseAds();
        servicesPage.selectelementWithoutScroll(testData.get("subcategory"), QuikrAppEnums.Services_OtherSubcategories);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(!snbPage.chatButtonIsPresentOnSnb(), "chat button is not present on snb of  Services");

    }
    /**
     * Users Nearby: Tapping "Users Nearby" callout should display SNB screen and ads displayed should be within the required range (5-10kms)
     */
    @Stories("SNB")
    @Title("Nearby user is within the required range (5-10kms)")
    @Test
    public void verifyNearByUserIdWithinGivenDistance()
    {
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage = new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        if (snbPage.nearbyUSerIsPresentOnSnb()) {
            snbPage.clickOnNearByUser();

            float fwl=snbPage.nearbyUSerDistance();
            Assert.assertTrue(fwl<=5,"distance of nearby user is more than 5km");
        }
        else
            System.out.println("nearBy USer not found");

    }
    /**
     * Users Nearby: : Able to navigate to VAP from Get Users Nearby SNB screen
     */
    @Stories("SNB")
    @Title(" Able to navigate to VAP from Get Users Nearby SNB screen")
    @Test
    public void navigateToVapFromNearbyUser()
    {
        Hompage hompage = new Hompage(driver);
        VapPage vapPage=new VapPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        hompage.closeFullScreenInterstitialFromCategoryPage();
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        if (snbPage.nearbyUSerIsPresentOnSnb())
        {
            snbPage.clickOnNearByUser();
            snbPage.openAdFromSnb(0);
            snbPage.hideOverlayLayout();
            Assert.assertTrue(vapPage.validateNavigationToVap(), "on clicking ad from get nearby user , not navigated to vap");
        }
        else
            System.out.println("nearBy USer not found");


    }


}

