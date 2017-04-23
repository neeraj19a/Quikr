package com.quikr.app.android.home;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.cars.CarsNewPage;
import com.quikr.app.android.chat.ChatPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra on 13/8/15.
 */
public class HomeAppAndroidTest extends AppAndroidTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HOME_TESTDATA_FILE"));

    @Stories("Homepage")
    @Title("Gps turned on Validate City As Bangalore")
    @Test(groups = "horizontal")
    public  void validateCityOnHompage()
    {
        Hompage hompage=new Hompage(driver);
//        hompage.ApplaunchPopup();
        Assert.assertEquals(testData.get("city"),hompage.validateCityOnHomepage());
    }

    //old UI
//    @Test(groups = "horizontal",dataProvider ="getCategories",dataProviderClass = Data.class)
//    public void ValidateSubcategory(String homePageCategories)
//    {
//        hompage.ApplaunchPopup();
//        hompage.selectElements(homePageCategories, QuikrAppEnums.HOMEPAGE_CATEGORY);
//        List subcat=hompage.extractSubcategory();
//        if (homePageCategories.equals(testData.get("cars")))
//            Assert.assertTrue(hompage.validatesubCategoryCars(subcat));
//        else if (homePageCategories.equals(testData.get("Mobiles")))
//            Assert.assertTrue(hompage.validatesubCategoryMobiles(subcat));
//        else if (homePageCategories.equals(testData.get("Electronics")))
//            Assert.assertTrue(hompage.validatesubCategoryElectronics(subcat));
//        else if (homePageCategories.equals(testData.get("Real Estate")))
//            Assert.assertTrue(hompage.validatesubCategoryRealEstate(subcat));
//        else if (homePageCategories.equals(testData.get("Home")))
//            Assert.assertTrue(hompage.validatesubCategoryHome(subcat));
//        else if (homePageCategories.equals(testData.get("Jobs")))
//            Assert.assertTrue(hompage.validatesubCategoryJobs(subcat));
//        else if (homePageCategories.equals(testData.get("Services")))
//            Assert.assertTrue(hompage.validatesubCategoryServices(subcat));
//        else if (homePageCategories.equals(testData.get("Entertainment")))
//            Assert.assertTrue(hompage.validatesubCategoryEntertainment(subcat));
//        else if (homePageCategories.equals(testData.get("Education")))
//            Assert.assertTrue(hompage.validatesubCategoryEducation(subcat));
//        else if (homePageCategories.equals(testData.get("Pets")))
//            Assert.assertTrue(hompage.validatesubCategoryPets(subcat));
//        else if (homePageCategories.equals(testData.get("Community")))
//            Assert.assertTrue(hompage.validatesubCategoryCommunity(subcat));
//        else if (homePageCategories.equals(testData.get("Matrimonial")))
//            Assert.assertTrue(hompage.validatesubCategoryMatrimonial(subcat));
//
//
//    }

    @Stories("Homepage")
    @Title("VAlidate Selected City NAme After Login")
    @Test(groups = "horizontal")
    public  void validateCityOnLogin()
    {
        Hompage hompage=new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        Assert.assertEquals(testData.get("city"),hompage.validateCityOnHomepage());
    }
    @Stories("Homepage")

    @Test(groups = "horizontal")
    public  void validateChangeOfCity()
    {
        Hompage hompage=new Hompage(driver);
//        hompage.ApplaunchPopup();
        hompage.selectCity();
        hompage.selectelementWithoutScroll(testData.get("NewCity"),QuikrAppEnums.Hompage_SelectCity);
        Assert.assertEquals(testData.get("NewCity"),hompage.validateCityOnHomepage());
    }

    /**
     * ANDROID-353:Verify recently viewed ads through “Popular Ads” under “Recently Viewed Ads'' tab
     */
    // @Test
    public void verifyRecentlyViedAdForPopularAds()
    {
        MenuPage menuePage=new MenuPage(driver);
        VapPage vapPage=new VapPage(driver);
        SnbPage snbPage=new SnbPage(driver);
//        hompage.ApplaunchPopup();
        snbPage.viewMoreAds(0);
        snbPage.hideOverlayLayout();
        snbPage.openAdFromSnb(1);
        vapPage.waitForTitleToBeVisibleAtVap();
        String VapTitle=vapPage.AdTitle();
        vapPage.selectBackButtonOnVap();
        menuePage.clickOnMenuIcon();
        menuePage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        String recentlyViewedAdTitle=menuePage.recentlyViewedAdtitle();
        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));
    }

    /**
     * ANDROID-355:Verify recently viewed ads through “Ads near you” under “Recently Viewed Ads'' tab
     */
    // @Test
    public void verifyRecentlyViewedAdForAdsNearYou()
    {
        SnbPage snbPage=new SnbPage(driver);
        MenuPage menuePage=new MenuPage(driver);
        VapPage vapPage=new VapPage(driver);
//        hompage.ApplaunchPopup();
        snbPage.viewMoreAds(1);
        snbPage.hideOverlayLayout();
        snbPage.openAdFromSnb(1);
        vapPage.waitForTitleToBeVisibleAtVap();
        String VapTitle=vapPage.AdTitle();
        vapPage.selectBackButtonOnVap();
        menuePage.clickOnMenuIcon();
        menuePage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        String recentlyViewedAdTitle=menuePage.recentlyViewedAdtitle();
        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));
    }

//    /**
//     * ANDROID-352:Verify recently viewed ads through “Recommended For You” under “Recently Viewed Ads'' tab
//     */
//    @Test
//    public void verifyRecentlyViewedAdForRecommendedForYou()
//    {
//        SnbPage snbPage=new SnbPage(driver);
//        MenuPage menuePage=new MenuPage(driver);
//        Hompage hompage=new Hompage(driver);
//        VapPage vapPage=new VapPage(driver);
////        hompage.ApplaunchPopup();
//        hompage.swipeToRecomendedAds();
//        hompage.clickOnMoreToOpenRecomendedAds();
////        hompage.openAdFromRecommendedForYou();
////        vapPage.waitForTitleToBeVisibleAtVap();
////        String VapTitle=vapPage.AdTitle();



////        hompage.navigateBack();
////        hompage.navigateBack();
////        menuePage.clickOnMenuIcon();
////        menuePage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
////        String recentlyViewedAdTitle=menuePage.recentlyViewedAdtitle();
////        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));
//    }

    /**
     * ANDROID-356:My Chats & Replies : Verify recently viewed ads through “My Chats & Replies” under “Recently Viewed
     */
    // @Test
    public void verifyRecentlyViewedAdForChatAndReplies()
    {
        MenuPage menuePage=new MenuPage(driver);
        Hompage hompage=new Hompage(driver);
        VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
//        hompage.ApplaunchPopup();
        hompage.selectChatAndRepliesButton();
        chatPage.selectChatAndRepliesUser(2);
        chatPage.openChatPageAds();
        vapPage.waitForTitleToBeVisibleAtVap();
        String VapTitle=vapPage.AdTitle();
        vapPage.selectBackButtonOnVap();
        chatPage.navigateBack();
        chatPage.navigateBack();
        menuePage.clickOnMenuIcon();
        menuePage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);
        String recentlyViewedAdTitle=menuePage.recentlyViewedAdtitle();
        Assert.assertTrue(VapTitle.contains(recentlyViewedAdTitle));
    }

    /**Android Snity:2263
     * Verify if the sorting is applied correctly on Popular ads, near by ads page
     */
    @Stories("SNB")
    @Test(groups = "horizontal")
    public void validateSortOnPopularAds()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage =new Hompage(driver);
        hompage.swipeVertically();
        snbPage.viewMoreAds(0);
        snbPage.hideOverlayLayout();
        snbPage.clickOnSortButtonOnSNB();
        Assert.assertTrue(snbPage.validatePopularAdsSortOptions(),"sort options not present on snb");

    }
    /**
     * Android Sanity:2278
     * Verify if user is able to do smart search and is able to change the Category after performing smart search
     */
    @Stories("SNB")
    @Title("User is Able To Change Category After Search")
    @Test(groups = "horizontal" ,dataProvider = "smartSearchItems",dataProviderClass = Data.class)
    public void userIsAbleToPerFormSmartSearch(String searchItem)
    {
        HeaderPage headerPage=new HeaderPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        CarsNewPage carsNewPage=new CarsNewPage(driver);
        headerPage.selectHeaderSearchIcon();
        headerPage.searchTextFromHomePage(searchItem);
        headerPage.selectSearchSuggestionFromAutoSuggest(searchItem);
        //headerPage.clickOnSearchSuggestionAfterInputSomeText()
        snbPage.hideOverlayLayout();
        if (searchItem.equals(testData.get("samsung")))
            snbPage.hideOverlayLayout();
            escrowPage.selectCityAsCurrentCityOnVapEscrow();
        String snbTitle=new String();
        if (searchItem.equals(testData.get("search")))
            snbTitle=carsNewPage.snbAdTitle().toUpperCase();
        else
            snbTitle=snbPage.snbAdTitle().toUpperCase();
        System.out.println(snbTitle);
        Assert.assertTrue(snbTitle.contains(searchItem.toUpperCase()));
        String Snbsubcat=snbPage.snbCategory();
        snbPage.clickOnChangeSubCAtFromSNB();
        if (Snbsubcat.equals("Bikes & Scooters"))
            snbPage.selectSubCatFromSNB(2);
        else
            snbPage.selectSubCatFromSNB(1);
        String Snbsubcat1=snbPage.snbCategory();
        Assert.assertFalse(Snbsubcat.equals(Snbsubcat1),"User is not  able to change CAtegory after performing search");
        //Assert.assertTrue(subcat.contains(snbPage.snbCategory()),"category MisMatch on snb" + subcat +" "+ snbPage.snbCategory() +"   item searched"+searchItem);

    }
    /**
     * validate live on quikr
     */

    @Stories("Homepage")
    @Test
    public void validateLiveOnQuikr()
    {
        Hompage hompage=new Hompage(driver);
        String liveOnQuikr=hompage.liveOnQuikr();
        Assert.assertTrue(liveOnQuikr.equalsIgnoreCase(testData.get("LiveOnQuikr")));
        Assert.assertTrue(hompage.validateLiveOnQuikr(),"All fields are not reflected on Live on Quikr");
    }
    /**
     * verify user is able to perform search
     */
    @Stories("Homepage")
    @Test
    public void userIsAbleToPerformSearch()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        headerPage.selectHeaderSearchIcon();
        headerPage.searchTextFromHomePage(testData.get("searchKeyword"));
        headerPage.selectSearchSuggestionFromAutoSuggest(testData.get("searchKeyword"));
        snbPage.hideOverlayLayout();
        Assert.assertTrue(testData.get("searchKeyword").equalsIgnoreCase(headerPage.snbSearchTitle()),"Expected="+testData.get("searchKeyword")+">>>>"+"Actual="+headerPage.snbSearchTitle());
    }
    /**
     * validate recent search is reflected in search page
     */
    @Stories("Homepage")

    @Test
    public void validateRecentlySearchItemIsReflectedInSearchPage()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        headerPage.selectHeaderSearchIcon();
        SnbPage snbPage=new SnbPage(driver);
        headerPage.searchTextFromHomePage(testData.get("searchKeyword"));
        headerPage.selectSearchSuggestionFromAutoSuggest(testData.get("searchKeyword"));
        snbPage.hideOverlayLayout();
        headerPage.selectSnbHeaderSearchIcon();
        Assert.assertTrue(headerPage.validateRecentlySearchedItem(testData.get("searchKeyword")), "recently searched item is not reflected in Search Page ");

    }

    @Stories("Categories")
    @Test(groups="Home")
       /**
     * Verify the respective categories landing page
     */
    public  void selectAllCategories(){
        Hompage hompage = new Hompage(driver);
        MenuPage menuPage = new MenuPage(driver);

         String[] categories = {"cars", "mobiles", "electronics","lifestyle","bikes","homes",
                              "services","jobs","education","entertainment", "nationwide",
                               "pets","events","matrimonial"};
        for(String s: categories){
            switch (s){
                case "cars":
                    hompage.selectelementWithoutScroll(testData.get("cars"),QuikrAppEnums.Home_Categories);

                    Assert.assertTrue(hompage.isUserCarIsPresent(), "Not in Cars Category");
                    driver.navigate().back();
                   // menuPage.clickOnMenuIcon();
                   // menuPage.selectHomeIcon();
                    break;
                case "mobiles":
                    hompage.selectelementWithoutScroll(testData.get("Mobiles"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isMobileIsPresent(),"Not in Mobiles & Tablets category");
                    driver.navigate().back();
                    break;
                case "electronics":
                    hompage.selectelementWithoutScroll(testData.get("Electronics"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isElectronicsIsPresent(),"Not in Electronics Category");
                    driver.navigate().back();
                    break;
                case "lifestyle":
                    hompage.selectelementWithoutScroll(testData.get("Lifestyle"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isLifestyleIsPresent(),"Not in Lifestyle category");
                    driver.navigate().back();
                    break;
                case "bikes":
                    hompage.selectelementWithoutScroll(testData.get("Bikes"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isBikeIsPresent(),"Not in Bikes category");
                    driver.navigate().back();
                    break;
                case "homes":
                    hompage.selectelementWithoutScroll(testData.get("Home"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isHomeIsPresent(),"Not in real estate category");
                    driver.navigate().back();
                    break;
                case "services":
                    hompage.selectelementWithoutScroll(testData.get("Services"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isServicesIsPresent(),"Not in Services category");
                    driver.navigate().back();
                    break;
                case "jobs":
                    hompage.selectelementWithoutScroll(testData.get("Jobs"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isJobsIsPresent(),"Not in Jobs category");
                    driver.navigate().back();
                    break;
                case "education":
                    hompage.selectelementWithoutScroll(testData.get("Education"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isEducationIsPresent(),"Not in Education category");
                    driver.navigate().back();
                    break;
                case "entertainment":
                    hompage.selectelementWithoutScroll(testData.get("Entertainment"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isEntertainmentIsPresent(),"Not in Entertainment category");
                    driver.navigate().back();
                    break;
                case "nationwide":
                    hompage.selectelementWithoutScroll(testData.get("Nationwide"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isNationwideIsPresent(),"Not in Nationwide category");
                    //hompage.pressBackButton();
                    driver.navigate().back();
                    break;
                case "pets":
                    hompage.selectelementWithoutScroll(testData.get("more"),QuikrAppEnums.Home_Categories);
                    hompage.expandMore();
                    hompage.selectelementWithoutScroll(testData.get("Pets"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isPetsIsPresent(),"Not in Pets category");
                    driver.navigate().back();
                    break;
                case "events":
                    hompage.selectelementWithoutScroll(testData.get("more"),QuikrAppEnums.Home_Categories);
                    hompage.expandMore();
                    hompage.selectelementWithoutScroll(testData.get("Events"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isEventsIsPresent(),"Not in Events category");
                    driver.navigate().back();
                    break;
                case "matrimonial":
                    hompage.selectelementWithoutScroll(testData.get("more"),QuikrAppEnums.Home_Categories);
                    hompage.expandMore();
                    hompage.selectelementWithoutScroll(testData.get("Matrimonial"),QuikrAppEnums.Home_Categories);
                    Assert.assertTrue(hompage.isMatrimonialIsPresent(),"Not in Matrimonial category");
                    driver.navigate().back();
                    break;

                default:
                    System.out.println("No categories are available");



            }

        }

    }
    @Stories("HEADER UI")
    @Test(groups = "Header")
    /**
     * Verify the header of the homepage
     */
    public void verifyHeaderOfHomePage()
    {
        HeaderPage headerPage=new HeaderPage(driver);
        Assert.assertTrue( headerPage.verifyHeaderOfHomePage());
    }
    /**
     * Verify the header of the popular ads SNB screen
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfPopularAdsSnb()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.swipeVertically();
        hompage.clickOnMoreButtonToOpenAdsFromHOmePage(0);
        snbPage.hideOverlayLayout();
        Assert.assertTrue( snbPage.verifyHeaderOfPopularAdSnb());
    }
    /**
     * Verify the header of the nearby ads SNB screen
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfNearByAds()
    {
        Hompage hompage=new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.scroll("Nearby Ads");
        hompage.clickOnMoreButtonToOpenAdsFromHOmePage(0);
        snbPage.hideOverlayLayout();
        Assert.assertTrue(snbPage.verifyHeaderOfNearByAdSnb());
    }
    /**
     * Verify the header of the PAP
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfPAP()
    {
        Hompage hompage=new Hompage(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        hompage.clickonPostAd();
        Assert.assertTrue(postAdV2Page.verifyPostAdHeader());


    }
    /**
     * Verify by tapping on More button for Recommended ads in the homepage
     */
    @Stories("Homepage")
    @Test
    public void moreButtonOfRecommendedAds()
    {
        Hompage hompage=new Hompage(driver);
        hompage.scroll("Recommended Ads");
        hompage.clickOnMoreButtonToOpenAdsFromHOmePage(0);
        Assert.assertTrue(hompage.isMatchingAdsPresent(), "Matching Ads is present");

    }
    /**
     * Verify by tapping on MAO CTA in matching ads page
     */
    @Stories("Homepage")
    @Test
    public void tapOnMaoButtonOfMatchingAds()
    {
        Hompage hompage=new Hompage(driver);
        hompage.scroll("Recommended Ads");
        hompage.clickOnMoreButtonToOpenAdsFromHOmePage(0);
        Assert.assertTrue(hompage.isMatchingAdsPresent(), "Matching Ads is present");
        hompage.tapOnMaoButtonInMatchingAds();
        Assert.assertTrue(hompage.isHeaderMakeAnOffer(), "Make an offer page");

    }


}
