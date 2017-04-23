package com.quikr.app.android.home;

import com.quikr.app.android.AppAndroidPageBase;
import com.quikr.app.android.Constants.MobileConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra singh on 12/8/15.
 */
public class Hompage extends AppAndroidPageBase {

    public Hompage(AppiumDriver driver) {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("ANDROID_HOME_DOM_FILE");

    /**
     * function to skip refer & earn pop-up
     * swatantra
     *
     * @throws Exception
     */
    public void ApplaunchPopup() {
        Mapper.waitForElementToBeVisible(domFile, "referPopUp");
        if (isElementPresent("referPopUp")) {
            Mapper.find(domFile, "referPopUp").click();
        }
    }

    /**
     * skip refer and earn Pop up
     *
     * @return
     */
    public void clickonPostAd() {
        if (isElementPresent("postAd")) {
            Mapper.find(domFile, "postAd").click();
            System.out.println("post ad With Variant- A");
        } else {
            Mapper.find(domFile, "PostAdVArientB").click();
            System.out.println("post ad With Variant- B");
        }
    }

    public String validateCityOnHomepage() {
        Mapper.waitForElementToBeVisible(domFile, "city", 10);
        String city = Mapper.find(domFile, "city").getText().toString();
        return city;
    }

    /**
     * function to extrxct subCategory for any particular catogery
     *
     * @return
     */
    public List extractSubcategory() {
        List<WebElement> subCatelements = Mapper.finds(domFile, "SubCatId");
        List<String> list = new ArrayList<String>();
        for (WebElement e : subCatelements) {
            list.add(e.getText());
        }

        for (String obj : list)
            System.out.println(obj);
        List<String> listNew = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String testValue = (list.get(i));
            listNew.add(testValue);

        }
        return listNew;
    }

    /**
     * validate Mobiles & tablets sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryMobiles(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.Mobilesandtablets.size(); j++) {
                if (list.get(i).equals(MobileConstants.Mobilesandtablets.get(j))) {
                    flag = 1;
                    //System.out.println(list.get(i) + MobileConstants.Mobilesandtablets.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Electronics And Appliances sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryElectronics(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.Electronicsandappliances.size(); j++) {
                if (list.get(i).equals(MobileConstants.Electronicsandappliances.get(j))) {
                    flag = 1;
                    // System.out.println(list.get(i) + MobileConstants.Electronicsandappliances.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Realestate sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryRealEstate(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.Realestate.size(); j++) {
                if (list.get(i).equals(MobileConstants.Realestate.get(j))) {
                    flag = 1;
                    // System.out.println(list.get(i) + MobileConstants.Realestate.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Home & Life style sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryHome(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.Homeandlifestyle.size(); j++) {
                if (list.get(i).equals(MobileConstants.Homeandlifestyle.get(j))) {
                    flag = 1;
                    // System.out.println(list.get(i) + MobileConstants.Homeandlifestyle.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Jobs sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryJobs(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.jobs.size(); j++) {
                if (list.get(i).equals(MobileConstants.jobs.get(j))) {
                    flag = 1;
                    // System.out.println(list.get(i) + MobileConstants.jobs.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validateServices sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryServices(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.services.size(); j++) {
                if (list.get(i).equals(MobileConstants.services.get(j))) {
                    flag = 1;
                    //System.out.println(list.get(i) + MobileConstants.services.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Entertainment sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryEntertainment(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.entertainment.size(); j++) {
                if (list.get(i).equals(MobileConstants.entertainment.get(j))) {
                    flag = 1;
                    //System.out.println(list.get(i) + MobileConstants.entertainment.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Education And Learning sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryEducation(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.education.size(); j++) {
                if (list.get(i).equals(MobileConstants.education.get(j))) {
                    flag = 1;
                    //System.out.println(list.get(i) + MobileConstants.education.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Pets And Care sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryPets(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.petsandcare.size(); j++) {
                if (list.get(i).equals(MobileConstants.petsandcare.get(j))) {
                    flag = 1;
                    //System.out.println(list.get(i) + MobileConstants.petsandcare.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Community sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryCommunity(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.communityandevents.size(); j++) {
                if (list.get(i).equals(MobileConstants.communityandevents.get(j))) {
                    flag = 1;
                    //System.out.println(list.get(i) + MobileConstants.communityandevents.get(j));
                    break;
                }
            }


        }
        //System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Matrimonial sub Category
     *
     * @param list
     * @return
     */
    public boolean validatesubCategoryMatrimonial(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.matriomonial.size(); j++) {
                if (list.get(i).equals(MobileConstants.matriomonial.get(j))) {
                    flag = 1;
                    //System.out.println(list.get(i) + MobileConstants.matriomonial.get(j));
                    break;
                }
            }


        }
        //////System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    /**
     * validate Cars sub Category
     *
     * @param list
     * @return
     */

    public boolean validatesubCategoryCars(List list) {
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < MobileConstants.cars.size(); j++) {
                if (list.get(i).equals(MobileConstants.cars.get(j))) {
                    flag = 1;
                    // System.out.println(list.get(i) + MobileConstants.cars.get(j));
                    break;
                }
            }


        }
        ////System.out.println(flag);
        if (flag == 1)
            return true;
        return false;
    }

    public void selectCity() {
        Mapper.waitForElementToBeVisible(domFile, "city");
        Mapper.find(domFile, "city").click();
    }

    /**
     * click on trending ads on honme page
     */
    public void openTrendingAds(int i) {
        Mapper.finds(domFile, "trendingAd").get(i).click();
    }

    public void selectCategory(String category) {
//        Mapper.waitForElementToBeVisible(domFile,category);
        Mapper.scroll(category);
        Mapper.waitForElementToBeVisible(domFile, category);
        Mapper.find(domFile, category).click();
        closeFullScreenInterstitialFromCategoryPage();
    }

    public void closeFullScreenInterstitialFromCategoryPage()
    {
        if(!isElementPresent("titlebar"))
            navigateBack();
    }

    public void closeFullScreenInterstitialFromVAP()
    {
        if(!isElementPresent("toolbar"))
            navigateBack();
    }

    public void ignoreUpdate() {
        while (isElementPresent("cancelButton"))
            Mapper.find(domFile, "cancelButton").click();
    }


    public void swipeVertically() {
        int Y1 = Mapper.find(domFile,"postAd").getLocation().getX();
        int   Y2 = Mapper.find(domFile,"carouselImage").getLocation().getX()+30;
        verticalSwipeWithCordinates(Y1,Y2);
    }


//    public static void scrollElementIntoView(String element)
//    {
//        try
//        {
//            WebElement webElement = Mapper.find(domFile, element);
//            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", webElement);
//            Thread.sleep(1000);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    /**
     * function to click on popular ads
     */
    public void selectPopularAd() {

        Mapper.find(domFile, "popularAd").click();
        closeFullScreenInterstitialFromVAP();
    }

    /**
     * function to select ad from ads near you
     */
    public void selectAdsNearYouAd() {

        Mapper.find(domFile, "adNearU").click();
        closeFullScreenInterstitialFromVAP();
    }

    /**
     * function to select ad from recommended for you
     */
    public void openAdFromRecommendedForYou() {
        closeFullScreenInterstitialFromCategoryPage();
        Mapper.waitForElementToBeVisible(domFile, "recommendedAd");
        Mapper.find(domFile, "recommendedAd").click();
        closeFullScreenInterstitialFromVAP();
    }

    /**
     * function to click on chat and replies button on home page
     */
    public void selectChatAndRepliesButton() {
        Mapper.find(domFile, "chatAndRepliesButton").click();
    }

    /**
     * swipte category
     */
    public void swipeHompageCategory() {
        Mapper.waitForElementToBeVisible(domFile, "search");
        Mapper.horizontalSwipe(domFile, "home", "search");
    }

    public void swipeToQuikrX() {
        Mapper.waitForElementToBeVisible(domFile, "nearbyAds");
        Mapper.VerticalSwipe(domFile, "headerSearch", "moreButton");
    }

    public void swipeToRecomendedAds() {
        Mapper.waitForElementToBeVisible(domFile, "nearbyAds");
        Mapper.VerticalSwipe(domFile, "headerSearch", "adsNearYouArea");
    }

    /**
     * select categories from home page category icon
     */
    public void selectcategoriesFromHomePageCategoryMenu() {
        Mapper.waitForElementToBeVisible(domFile, "categories");
        Mapper.finds(domFile, "categories").get(1).click();
        Mapper.waitForElementToBeVisible(domFile, "waitForAdToSkip");
    }

    /**
     * dissmiss alert on launch
     */

    public void dismisNotificationAlert() {
        if (isElementPresent("alerNotification")) {
            Mapper.find(domFile, "alerNotification").click();
        }
    }

    /**
     * skip banner msg on Launch
     */

    public void skipBannerMsg() {
        Mapper.waitForElementToBeVisible(domFile, "skipBanner");
        if (isElementPresent("skipBanner"))
            Mapper.find(domFile, "skipBanner").click();
    }

    public boolean isPostAdButtonPresent() {
        return isElementPresent("postAd");
    }

    /**
     * on launch submit english as selectes language
     */
    public void selectLanguageOnLaunch() {
        Mapper.waitForElementToBeVisible(domFile, "submitSelectedLanguageS");
        if (isElementPresent("submitSelectedLanguageS"))
            Mapper.find(domFile, "submitSelectedLanguageS").click();
    }

    /**
     * click on chat pop up on homePage
     */
    public void clickOnChatPopUp() {
        Mapper.find(domFile, "startChatting").click();
    }

    /**
     * validate live on quikr is displayed
     */
    public String liveOnQuikr() {
        scroll("Live on Quikr");
        return (Mapper.find(domFile, "liveOnQuikr").getText());
    }

    /**
     * validate live on quikr fieldes
     */
    public boolean validateLiveOnQuikr() {
        return (isElementPresent("numberOfOnlineUSer") && isElementPresent("numberOfAdsPosted") && isElementPresent("OngoingChatConversation"));
    }

    /**
     * skip login page on launch of app
     */
    public void skipLoginOnLaunch() {

        if (isElementPresent("skipLoginOnLaunch"))
            Mapper.find(domFile, "skipLoginOnLaunch").click();

    }

    /**
     * perform login page on launch of app
     */
    public void loginOnLaunch() {
        if (isElementPresent("loginOnLaunch"))
            Mapper.find(domFile, "loginOnLaunch").click();
    }

    /**
     * function to skip chat assistant pop up
     */
    public void skipChatPopUp() {
        if (isElementPresent("alerNotification")) {
            Mapper.find(domFile, "alerNotification").click();
        } else if (isElementPresent("startChatting")) {
            Mapper.find(domFile, "skipChatAssistant").click();
        }
    }

    /**
     * validate tutorial image are present
     */
    public boolean validateImageTutorialLinkIsPresent() {
        Mapper.waitForElementToBeVisible(domFile,"skipBanner");
        return isElementPresent("skipBanner");
    }

    /**
     * validate tutorial image text
     */
    public List ImageTutorialText() {
        Mapper.waitForElementToBeVisible(domFile, "homePageCategory");
        String text1 = Mapper.find(domFile, "homePageCategory").getText();
        Mapper.find(domFile, "skipLoginOnLaunch").click();
        Mapper.waitForElementToBeVisible(domFile, "homePageCategory");
        String text2 = Mapper.find(domFile, "homePageCategory").getText();
        Mapper.find(domFile, "skipLoginOnLaunch").click();
        Mapper.waitForElementToBeVisible(domFile, "homePageCategory");
        String text3 = Mapper.find(domFile, "homePageCategory").getText();
        Mapper.find(domFile, "skipLoginOnLaunch").click();
        Mapper.waitForElementToBeVisible(domFile, "homePageCategory");
        String text4 = Mapper.find(domFile, "homePageCategory").getText();
        List<String> text = new ArrayList<>();
        text.add(text1);
        text.add(text2);
        text.add(text3);
        text.add(text4);
        return text;
    }

    /**
     * fetch language option displayed on launch of app
     */
    public List languageOptions() {
        List<WebElement> language = Mapper.finds(domFile, "homePageCategory");
        List<String> languageOptions = new ArrayList<String>();
        for (WebElement e : language) {
            languageOptions.add(e.getText());
        }
        return languageOptions;
    }

    /**
     * check if english is selected by default
     */
    public boolean englishLanguageIsSelectedByDefault() {
        Mapper.waitForElementToBeVisible(domFile, "homePageCategory", 10);
        String attribute = Mapper.finds(domFile, "homePageCategory").get(0).getAttribute("checked");
        System.out.println(attribute);
        return ("true".equalsIgnoreCase(attribute));
    }

    /**
     * swipe to near By ads on home page
     */
    public void swipeToNearByAdsOnHomePage() throws Exception {
        swipeVertically("moreButtonOnHomePAge", "bannerImage");
    }

    /**
     * swipe to Recomenden Ads
     */
    public void swipeToRecomendedAdsOnHomePage() throws Exception {
        swipeVertically("moreButtonOnHomePAge", "searchbutton");
    }

    /**
     * validate popular ,nearBy And Recomended Ads are reflected
     */
    public String adTypesOnHomePAge(int i) {
        /**
         * i=0 for popular ads
         * i=1 for near byAds
         * i=1 for Recomended Ads (After swipe)
         */
        Mapper.waitForElementToBeVisible(domFile, "heardeTextPopularNearByAds");
        return Mapper.finds(domFile, "heardeTextPopularNearByAds").get(i).getText();
    }

    /**
     * open ads from HomePage
     */
    public void clickOnMoreButtonToOpenAdsFromHOmePage(int i) {
        Mapper.waitForElementToBeVisible(domFile, "moreButtonOnHomePAge");
        Mapper.finds(domFile, "moreButtonOnHomePAge").get(i).click();
        closeFullScreenInterstitialFromVAP();
    }

    /**
     * fetch categories
     */
    public List Categories() {
        List<WebElement> categories = Mapper.finds(domFile, "homePageCategory");
        List<String> homePageCategories = new ArrayList<>();
        for (WebElement e : categories) {
            homePageCategories.add(e.getText());
        }
        return homePageCategories;

    }

    /**
     * validate homePage is selected by Default
     */
    public boolean homePageIsSelected() {
        return (isElementPresent("carouselImage"));
    }

    /**
     * scroll to Trending Ad and verify if it is present or not
     */
    public boolean scrollAndVAlidateTrendingAdIsPresent() {
        Mapper.waitForElementToBeVisible(domFile, "postAd", 15);
//        int Y2Cordinates=Mapper.find(domFile,"carouselImage").getLocation().getY();
//        int Y1Cordinates=Mapper.find(domFile,"postAd").getLocation().getY();
//        Mapper.scroll("Trending in Bangalore");
//        verticalSwipeWithCordinates(Y1Cordinates+20,Y2Cordinates);
        WebElement homePageScrollView = Mapper.find(domFile, "HomepageScrollView");
        loopScrollToElementByName(homePageScrollView,"TrendingTitle",true,1000);
        System.out.println("After finding Trending ads");
        loopScrollToElementByNameUntilInvisible(homePageScrollView,"RecommendedForYouTitle",true,1000);
        return isElementPresent("priceMeter");
    }

    /**
     * Price Meter is present on Trending Section
     */
    public boolean priceMeterIsPresent() {
        return (isElementPresent("priceMeter"));
    }

    /**
     * click on view button to view ads
     */
    public void clickOnViewAllButton() {
        Mapper.waitForElementToBeVisible(domFile, "viewAllUnderTrending");
        Mapper.find(domFile, "viewAllUnderTrending").click();
        closeFullScreenInterstitialFromCategoryPage();
    }

    /**
     * validate On Clicking view all button user is redirecred to snb
     */
    public boolean onClickingViewAllUserIsRedirectedToSnb() {
        closeFullScreenInterstitialFromCategoryPage();
        return isElementPresent("snbMenu");
    }

    /**
     * validate on clicking on price metre ad user is redirected to sng
     */
    public void clickOnAdCountToViewAllAds(int i) {
        Mapper.finds(domFile, "numberOfAdsInPriceMeter").get(i).click();
        closeFullScreenInterstitialFromCategoryPage();
    }

    /**
     *
     */
    public int adCount(int i) {
        int count = Integer.parseInt(Mapper.finds(domFile, "numberOfAdsInPriceMeter").get(i).getText().replaceAll("\\D", ""));
        return count;
    }

    /**
     * total number of ads under trending Ads
     */
    public int totalNumberOfAdUnderTrendingAd() {
        Mapper.waitForElementToBeVisible(domFile, "trendingAdTitle");
        return (Integer.parseInt(Mapper.find(domFile, "trendingAdTitle").getText().replaceAll("\\D", "")));
    }

    /**
     * fetch ad types on homepage
     *
     * @return
     */
    public List AdTypesOnHomePage() {
        //Storing allMenu elements  in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "heardeTextPopularNearByAds");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options) {
            list.add(e.getText());
        }
        explicitWait(1);
        scroll("Nearby Ads");
        List<WebElement> options1 = Mapper.finds(domFile, "heardeTextPopularNearByAds");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : options1) {
            list1.add(e.getText());
        }
        System.out.println(list1);
//merging tow lists in oreder and removing duplicates
        for (int j = 0; j < list1.size(); j++) {
            if (!(list.contains(list1.get(j))))
                list.add(list1.get(j));
        }
        System.out.println(list);
        return list;
    }

    /**
     * open ads from popular ads
     */
    public void openAdFromPopularAds() {

        closeFullScreenInterstitialFromCategoryPage();
        Mapper.find(domFile, "openPopularAd").click();
        closeFullScreenInterstitialFromVAP();
    }

    /**
     * Open ads from Nearby Ads
     */

    public void openAdFromNearbyAds() {
        closeFullScreenInterstitialFromCategoryPage();
        Mapper.find(domFile, "openNearbyAd").click();
        closeFullScreenInterstitialFromVAP();
    }

    public void selectCategoryFromHomePage(String text) {
        Mapper.waitForElementToBeVisible(domFile, "categoryName");
        Mapper.findAndReplace(domFile, "selectCategory", new String[]{text}).click();
    }


    public boolean onHomePage() {
        Mapper.waitForElementToBeVisible(domFile, "onHomePage", 5);
        return isElementPresent("onHomePage");
    }



    public boolean isUserCarIsPresent() {
        return isElementPresent("Used Cars");
    }

    public boolean isMobileIsPresent() {
        return isElementPresent("Mobiles");
    }


    public boolean isElectronicsIsPresent() {
        Mapper.waitForElementToBeVisible(domFile, "ElectronicsAppliances");
        return isElementPresent("ElectronicsAppliances");
    }

    public void pressBackButton() {
        Mapper.find(domFile, "backButton").click();
    }

    public boolean isLifestyleIsPresent() {
        return isElementPresent("Lifestyle");
    }

    public boolean isBikeIsPresent() {
        return isElementPresent("Bikes");
    }

    public boolean isHomeIsPresent() {
        return isElementPresent("Homes");
    }

    public boolean isServicesIsPresent() {
        return isElementPresent("Services");
    }

    public boolean isJobsIsPresent() {
        return isElementPresent("Jobs homepage");
    }

    public boolean isEducationIsPresent() {
        return isElementPresent("Education");
    }

    public boolean isEntertainmentIsPresent() {
        return isElementPresent("Entertainment");

    }

    public boolean isNationwideIsPresent() {
        return isElementPresent("Nationwide");
    }

    public void expandMore() {
        if (isElementPresent("More")) {
            Mapper.find(domFile, "More").click();

        }
    }

    public boolean isMoreIsPresent() {
        return isElementPresent("More");
    }

    public boolean isPetsIsPresent() {
        return isElementPresent("Pets");
    }

    public boolean isEventsIsPresent() {
        return isElementPresent("Events");
    }

    public boolean isMatrimonialIsPresent() {
        return isElementPresent("Matrimonial");
    }

    /**
     * Verify the Matching ads screen
     */
    public boolean isMatchingAdsPresent() {
        return isElementPresent("Matching Ads");

    }

    /**
     * Verify tapping on MAO CTA in matching ads
     **/

    public void tapOnMaoButtonInMatchingAds() {
        // Mapper.waitForElementToBeInvisible(domFile, "MaoCtaInMatching Ads");
        if (!isElementPresent("MakeAnOfferInHeader")) {
            Mapper.scroll("MakeAnOfferInHeader");
            Mapper.find(domFile, "MaoCtaInMatching Ads").click();
        } else {
            Mapper.waitForElementToBeInvisible(domFile, "MaoCtaInMatching Ads");
            Mapper.find(domFile, "MaoCtaInMatching Ads").click();
        }

    }

    /**
     * Verify if header is Make an offer
     **/
    public boolean isHeaderMakeAnOffer() {
        return isElementPresent("MakeAnOfferInHeader");

    }

    public void closeGoogleAds() {
        Mapper.waitForElementToBeVisible(domFile, "googleAds", 10);
        if (isElementPresent("googleAds")) {
            Mapper.find(domFile, "googleAds").click();
        }
    }
}


