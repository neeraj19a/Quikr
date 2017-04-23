package com.quikr.app.android.snb;

import com.quikr.app.android.AppAndroidPageBase;
import com.quikr.app.android.Constants.MobileConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 13/8/15.
 */
public class SnbPage extends AppAndroidPageBase {
    public SnbPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ANDROID_SNB_DOM_FILE");
    public WebElement filterScreen = null;

    public void openAdFromSnb(int i) {
        Mapper.waitForElementToBeVisible(domFile, "snbAdTitle",30);
        Mapper.finds(domFile, "snbAdTitle").get(i).click();
    }

    /**
     * clck on menu icon on snb page
     *
     * @return
     */
    public SnbPage selectMenuButtonAtSnbPage() {
        Mapper.waitForElementToBeVisible(domFile, "snbNavigation");
        Mapper.find(domFile, "snbNavigation").click();
        return this;
    }

    /**
     * function to select filter button on snb page
     */
    public void selectFilterButtonOnSnbPage() {
        Mapper.waitForElementToBeClickable(domFile, "filterButton");
        Mapper.find(domFile, "filterButton").click();
    }

    /**
     * function to click on  brand name in filter
     */
    public void selectBrandName() {
        Mapper.waitForElementToBeVisible(domFile, "applyFilter");
        if (filterScreen==null)
            filterScreen=Mapper.find(domFile,"filterScreen");
        WebElement filterBrand = loopScrollToElementByName(filterScreen,"filterBrand",true,1000);
        filterBrand.click();
        //Mapper.find(domFile, "filterBrand").click();
    }

    /**
     * function to select apply button in filter
     */
    public void selectApplyButton() {
        Mapper.find(domFile, "applyFilter").click();
        Mapper.waitForElementToBeInvisible(domFile, "applyFilter");
    }

    /**
     * function to validate filter apply by brand name
     */
    public boolean validateFilterOnSnbPage() {
        int count = 0;
        for (int j = 0; j < 3; j++) {
            String str = Mapper.finds(domFile, "adTitle").get(j).getText();
            for (int i = 0; i < MobileConstants.adTitlesContainsRequiredData.size(); i++) {
                if (str.contains(MobileConstants.adTitlesContainsRequiredData.get(i))) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * function to select sort button and
     */
    public void selectSortButton() {
        Mapper.find(domFile, "sortButton").click();
        Mapper.waitForElementToBeVisible(domFile, "sortOption");

    }

    /**
     * function to perform sorting in ascending order
     */
    public boolean validateAscendingSortOnSnbPage() {
        if(isElementPresent("price"))
        {
            List<WebElement> priceList = Mapper.finds(domFile, "price");
            List<String> list = new ArrayList<String>();
            for (WebElement e : priceList) {
                list.add(e.getText().replaceAll("\\D", ""));  // Will Keep only digits, rest all will be removed
            }
            List<Integer> listOriginal = new ArrayList<Integer>();
            List<Integer> listNew = new ArrayList<Integer>();
            for (int i = 0; i < list.size(); i++) {
                int testValue = Integer.parseInt(list.get(i));
                listNew.add(testValue);
                listOriginal.add(testValue);

            }
            Collections.sort(listNew);
            for (int i = 1; i < list.size(); i++) {
                if ((listOriginal.get(i)).equals(listNew.get(i))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * function to select favourite button
     */
    public void selectFavouriteButton() {
        Mapper.waitForElementToBeVisible(domFile, "favouriteButton");
        Mapper.find(domFile, "favouriteButton").click();
    }

    /**
     * function to perform sorting in descending order
     */
    public boolean validateDescendingSortOnSnbPage() {
        List<WebElement> priceList = Mapper.finds(domFile, "price");
        List<String> list = new ArrayList<String>();
        for (WebElement e : priceList) {
            list.add(e.getText().replaceAll("\\D", ""));  // Will Keep only digits, rest all will be removed
        }
        List<Integer> listOriginal = new ArrayList<Integer>();
        List<Integer> listNew = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            int testValue = Integer.parseInt(list.get(i));
            listNew.add(testValue);
            listOriginal.add(testValue);

        }
        Collections.sort(listNew);
        int j = list.size() - 1;
        for (int i = 1; i < list.size(); i++) {
            if ((listOriginal.get(i)).equals(listNew.get(j))) {
                return true;
            }
            j--;
        }
        return false;
    }

    /**
     * select highest price first option
     */
    public void selectSortHighestPriceButton() {
        Mapper.find(domFile, "sortButton").click();
        Mapper.waitForElementToBeVisible(domFile, "sortOption");
        Mapper.find(domFile, "sorhighestPrice").click();
        Mapper.waitForElementToBeInvisible(domFile, "sortOption");
    }

    /**
     * function to click on show more filter button in filter
     */
    public void selectShowMoreFiltersButton() {
        Mapper.waitForElementToBeVisible(domFile, "showMoreFilters");
        Mapper.find(domFile, "showMoreFilters").click();
    }

    /**
     * function to select wanted ads button in filter
     */
    public void selectWantedAdsButton() {
        Mapper.scroll("wantedAds");
        Mapper.find(domFile, "wantedAds").click();
    }

    /**
     * function to select mode option button at snb page
     */
    public void selectModeOptionButton() {
        Mapper.waitForElementToBeVisible(domFile, "modeOptionButton");
        Mapper.find(domFile, "modeOptionButton").click();
    }

    /**
     * function to select save search as alert from mode options
     */
    public void selectSaveSearchAsAlertButton() {
        Mapper.waitForElementToBeVisible(domFile, "saveSearchAsAlert");
        Mapper.find(domFile, "saveSearchAsAlert").click();
    }

    /**
     * on few verticals on clicking subCategory  rather than nredirecting to SNB (view ads options occur )
     */
    public void ViewAdsOnSnb() {
        Mapper.waitForElementToBeVisible(domFile, "viewAds");
        Mapper.find(domFile, "viewAds").click();
    }

    /**
     * click on inspected filter on SNB
     */
    public void selectFilterInspected() {
        Mapper.waitForElementToBeVisible(domFile, "inspectedFilter");
        Mapper.find(domFile, "inspectedFilter").click();
    }


    /**
     * function to validate after click on search button in job page reach at snb page
     */
    public boolean validationForJobPageToSwitchSnbPage() {
        Mapper.waitForElementToBeVisible(domFile, "filterButton");
        return isElementPresent("filterButton");
    }

    /**
     * function to click on gallery view
     */
    public void selectGalleryViewButton() {
        Mapper.waitForElementToBeVisible(domFile, "galleryButton");
        Mapper.finds(domFile, "galleryButton").get(2).click();
    }

    public boolean validateNavigationToSnb() {
        Mapper.waitForElementToBeVisible(domFile, "snbAd");
        return isElementPresent("snbAd");
    }


    public boolean validateSortButtonOnSnb() {
        Mapper.waitForElementToBeVisible(domFile, "sortButton");
        return isElementPresent("sortButton");
    }


    public void viewAdListing() {
        if (isElementPresent("viewAdListing"))
            Mapper.find(domFile, "viewAdListing").click();
        if (isElementPresent("skip"))
            Mapper.find(domFile, "skip").click();
    }

    public int getAdCount() {
        Mapper.waitForElementToBeVisible(domFile, "adImage");
        return Mapper.finds(domFile, "adImage").size();
    }

    /**
     * function to validate sort button is present on snb page
     */
    public boolean validateSortButtonOnSnbPage() {
        return isElementPresent("sortButton");
    }

    /**
     * function to validate create alert button on snb page
     */
    public boolean validateCreateAlertButton() {
        Mapper.waitForElementToBeVisible(domFile, "createAlertButtonOnSnbPage");
        return isElementPresent("createAlertButtonOnSnbPage");
    }


    /**
     * Click on More button on CHP to view more Ads corresponding to that category
     */
    public void viewMoreAds(int i) {
        Mapper.waitForElementToBeVisible(domFile, "ShowMoreAds");
        Mapper.finds(domFile, "ShowMoreAds").get(i).click();
    }


    public boolean isQuikrConnectOptionPresent() {
        return isElementPresent("quikrConnectOption");
    }

    public void connectNow() {
        Mapper.waitForElementToBeVisible(domFile, "quikrConnectOption");
        if (isElementPresent("quikrConnectOption"))
            Mapper.find(domFile, "quikrConnectOption").click();
    }


    /**
     * function to click on user online ad on snb page
     */
    public void selectOnlineUserAd() {
        Mapper.find(domFile, "onlineUserAd").click();
    }

    /**
     * <<<<<<< Updated upstream
     * function to validate all filter option present for job
     */
    public boolean validateAllFilterOptionsPresentForJob() {
        return (isElementPresent("onlineUsersOnly") && isElementPresent("localities") && isElementPresent("wantedAds") && isElementPresent("offeringAds"));
    }

    /**
     * function to click on locality button in filter
     */
    public void selectLocality() {
        Mapper.waitForElementToBeVisible(domFile, "JobsFilterLocality");
        Mapper.find(domFile, "JobsFilterLocality").click();
    }

    /**
     * function to validate locality list is open
     */
    public boolean validateLocalityList() {
        return (isElementPresent("localityOptions") && isElementPresent("OkButton"));
    }

    /**
     * function to validate localities option name in filter
     */
    public boolean validateLocalityOptionName() {
//        System.out.println(name);
        return isElementPresent("locality");
    }

    /**
     * change subcategory from SNB
     */
    public void clickOnChangeSubCAtFromSNB() {
        Mapper.waitForElementToBeVisible(domFile, "changecategorySNB");
        Mapper.find(domFile, "changecategorySNB").click();
    }

    /**
     * selectSubCAtFromSnb
     */
    public void selectSubCatFromSNB(int i) {
        Mapper.waitForElementToBeVisible(domFile, "carsSubcat");
        Mapper.finds(domFile, "carsSubcat").get(i).click();
    }


    /**
     * when is inspected filter is selected Ad with inspected logo should only be displayed
     */
    public boolean validateInspectedLogoWhenInspectedFilterIsOn() {
        if (isElementPresent("inspectedImage"))
            return true;
        else
            return false;
    }

    /**
     * clear selected filter On Filtescreen
     */
    public void clearFilter() {
        Mapper.waitForElementToBeVisible(domFile, "cleaFilter");
        Mapper.find(domFile, "cleaFilter").click();
    }

    /**
     * validate inspected filter is off by default
     */
    public boolean validateInspectedFilterIsOffByDefault() {
        if (isElementPresent("cleaFilter"))
            return true;
        else
            return false;
    }

    /**
     * Count number of Ads Displayed
     */

    public int CountNumberofAds() {
        Mapper.waitForElementToBeVisible(domFile, "adTitle");
        List<WebElement> count = Mapper.finds(domFile, "adTitle");
        List<String> list = new ArrayList<String>();
        for (WebElement e : count) {
            list.add(e.getText());
        }
        int Adcount = list.size();
        System.out.println(Adcount);
        return Adcount;
    }

    /**
     * function to  click on create alert button on snb page
     */
    public void selectCreateAlertButtonOnSnbPage() {
        Mapper.find(domFile, "createAlertButtonOnSnbPage").click();
    }

    public void hideOverlayLayout() {
        if (isElementPresent("snbOverlay") || isElementPresent("vapLAyOver"))
            navigateBack();
    }

    public void clickOnQuikrConnectOption() {
        Mapper.waitForElementToBeVisible(domFile, "quikrConnectOption");
        Mapper.find(domFile, "quikrConnectOption").click();
    }

    public void clickOnInstaConnectCategory(String category) {
        Mapper.find(domFile, category).click();
    }

    public void clickOnInstaConnectSubcategory(String subcategory) {
        Mapper.waitForElementToBeVisible(domFile, subcategory);
        Mapper.find(domFile, subcategory).click();
    }

    public boolean isInstaconnectNowOptionPresent() {
        return isElementPresent("instaConnectNowOption");
    }

    public void clickOnInstaconnectNowOption() {
        Mapper.waitForElementToBeVisible(domFile, "instaConnectNowOption");
        Mapper.find(domFile, "instaConnectNowOption").click();
    }

    public boolean isInstaconnectNamePresent() {
        return isElementPresent("instaConnectName");
    }

    public boolean isInstaconnectMobileNoPresent() {
        return isElementPresent("instaConnectMobileNo");
    }

    public boolean validatePopularAdsSortOptions()
    {
        List<WebElement>options= Mapper.finds(domFile,"sortDropDownOPtions");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options)
        {
            list.add(e.getText());
        }

        return (list.contains("Most Recent (default)")&&list.contains("Nearest"));
    }

    /**
     * click on sort buttion on snb
     */
    public void clickOnSortButtonOnSNB() {
        Mapper.waitForElementToBeVisible(domFile, "sortButton");
        Mapper.find(domFile, "sortButton").click();
    }

    /**
     * validate category on Snb
     */
    public String snbCategory() {
        Mapper.waitForElementToBeVisible(domFile, "changecategorySNB");
        String snbCat = Mapper.find(domFile, "changecategorySNB").getText();
        return snbCat;

    }

    /**
     * open ads from Snb on Mobiles and tab page
     */
    public void openAdFromSNBOnMobilesPage() {
        Mapper.waitForElementToBeVisible(domFile, "mobilesSnbAdTitle");
        Mapper.find(domFile, "mobilesSnbAdTitle").click();
    }

    /**
     * validate Escrow cashback and Free delivery message should be displayed in SNB
     */
    public boolean escrowCashBackAndFreeDeliveryMsgIsPresentOnSnb() {
        return isElementPresent("escrowSnbAndCashbackMsg");
    }

    /**
     * function to send text in search header
     */
    public void clickOnSearchTextFromSnbHeadre() {
        Mapper.waitForElementToBeVisible(domFile, "SnbMenuSearch");
        Mapper.find(domFile, "SnbMenuSearch").click();
    }

    public int getPremiumAdCount() {
        Mapper.waitForElementToBeVisible(domFile, "premiumAdTag");
        return Mapper.finds(domFile, "premiumAdTag").size();
    }

    /**
     * fetch snb locality for all ads
     */
    public List snbLocality() {
        List<WebElement> locality = Mapper.finds(domFile, "snblocalityMobiles");
        List<String> listLocality = new ArrayList<String>();
        for (WebElement e : locality) {
            listLocality.add(e.getText());
        }
        return listLocality;

    }
    /**
     * fetch snb locality for all ads
     */
    public List snbTitle() {
        List<WebElement> title = Mapper.finds(domFile, "snbAdTitle");
        List<String> listTitle = new ArrayList<String>();
        for (WebElement e : title) {
            listTitle.add(e.getText());
        }
        return listTitle;

    }
    /**
     * select Lowest sortOption
     */
    public void selectLowestPriceFirst()
    {
        Mapper.waitForElementToBeVisible(domFile,"sortOption",10);
        Mapper.find(domFile,"sortOption").click();
    }
    /**
     * get text of sort button
     */
    public String sortButtonText()
    {
        Mapper.waitForElementToBeVisible(domFile, "galleryButton");
        return Mapper.finds(domFile,"galleryButton").get(0).getText();
    }
    /**
     * fetch snb ad title
     */
    public String snbAdTitle()
    {
        Mapper.waitForElementToBeVisible(domFile,"snbAdTitle",20);
        return Mapper.finds(domFile,"snbAdTitle").get(1).getText();
    }
    /**
     * validate if chat button is present on snb
     */
    public boolean chatButtonIsPresentOnSnb()
    {
        return isElementPresent("snbChat");
    }
    /**
     * if nearby user is present
     */
    public boolean nearbyUSerIsPresentOnSnb()
    {
        return isElementPresent("snbNearbyUser");
    }
    /**
     * click on nearby user
     */
    public void clickOnNearByUser()
    {
        Mapper.waitForElementToBeVisible(domFile,"snbNearbyUser",10);
        Mapper.find(domFile,"snbNearbyUser").click();
    }
    /**
     * get distance of nearby user
     */
    public float nearbyUSerDistance()
    {
        Mapper.waitForElementToBeVisible(domFile, "nearbyUSerDistance", 10);
        String distance=Mapper.find(domFile,"nearbyUSerDistance").getText().replaceAll("[a-zA-Z]","");
        System.out.println(distance);

            float newdistance=Float.parseFloat(distance);
        System.out.println(newdistance);

        return newdistance;

    }

    public void verifyBuyNowButton(){
        Mapper.find(domFile,"BuyNowButton").click();
        Mapper.waitForElementToBeVisible(domFile,"buynowscreentitle");
    }

    public boolean ifBuyNowPresent(){
        hideKeyboard();
        Mapper.waitForElementToBeVisible(domFile, "BuyNowBlue", 10);
        if (Mapper.find(domFile,"BuyNowBlue").getText().equalsIgnoreCase("Buy Now"))
        {

            return true;
        }else
            return false;
    }
    public boolean ifMAOPresent(){
        Mapper.waitForElementToBeVisible(domFile,"MAOBlue",10);
        if (Mapper.find(domFile,"MAOBlue").getText().equalsIgnoreCase("Make an Offer"))
        {
            return true;
        }else
            return false;
    }

    public boolean ifSnbOverlayDisplayed()
    {
        Mapper.waitForElementToBeVisible(domFile,"SNBOverlay",7);
        if (Mapper.find(domFile, "snbCityOverlay").isDisplayed()){
            return true;
        }else
            return false;
    }
    public boolean ifNationwideOverlay(String nationWideText)
    {
        Mapper.waitForElementToBeVisible(domFile,"snbCityOverlay");
        if (Mapper.find(domFile,"nationwideOverlayMessage").isDisplayed())
        {
            return true;
        }
        else return false;
    }
    public boolean ifDoorstepOverlay(){
        Mapper.waitForElementToBeVisible(domFile,"snbCityOverlay");
        if (Mapper.find(domFile,"doorstepImage").isDisplayed()){
            return true;
        }else return false;
    }

    public boolean ifSnbOverlayCurrentCitySelected(String city)
    {
        Mapper.waitForElementToBeVisible(domFile,"SNBOverlayCurrentCity",5);
        if (Mapper.find(domFile,"SNBOverlayCurrentCity").getAttribute("checked").equalsIgnoreCase("true") && Mapper.find(domFile,"SNBOverlayCurrentCity").getText().equalsIgnoreCase(city)){
            return true;
        }else
            return false;
    }

    public boolean ifSnbOverlayAllCitiesSelected()
    {
        Mapper.waitForElementToBeVisible(domFile,"SNBOverlayAllCities",5);
        if (Mapper.find(domFile,"SNBOverlayAllCities").getAttribute("checked").equalsIgnoreCase("true"))
        {
            return true;
        }else
            return false;
    }

    public boolean ifCitySelectedInFilters(String city)
    {
        Mapper.waitForElementToBeVisible(domFile,"radioButton",10);
        if (Mapper.finds(domFile,"radioButton").get(1).getText().equalsIgnoreCase(city) && Mapper.finds(domFile,"radioButton").get(1).getAttribute("checked").equalsIgnoreCase("true")){
            return true;
        }else
            return false;
    }
    /**
     *
     */
    public boolean validateMatchingAdsDisplayedAfterClickingmatchingtab()
    {
        return isElementPresent("matchingAdTitle");
    }

    public void selectChatMaoButton(){
        Mapper.waitForElementToBeVisible(domFile,"snbChat");
        Mapper.finds(domFile,"snbChat").get(1).click();
    }

    public void handleOverlay() {
        Mapper.waitForElementToBeVisible(domFile, "overlay", 5);
        if (Mapper.find(domFile, "overlay").isDisplayed()) ;
        {
            navigateBack();
        }
    }

    public void selectMAOAd() {
        try {
            for (int i = 0; i < 4; i++) {
                Mapper.finds(domFile, "snbAdTitle").get(i).click();
                handleOverlay();
                Mapper.waitForElementToBeVisible(domFile, "MAOChatCity", 4);
                if (Mapper.waitForElementToBeVisible(domFile, "MAOChatCity", 2)) {
                    break;
                } else {
                    navigateBack();
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
    /**
     * verify header elements
     */
    public boolean verifyHeaderOfPopularAdSnb()
    {
        explicitWait(3);
        return (isElementPresent("snbNavigation")&&isElementPresent("changecategorySNB")&&isElementPresent("SnbMenuSearch")&&isElementPresent("chatButtonOnSnb"));
    }
    /**
     * verify header elements
     */
    public boolean verifyHeaderOfNearByAdSnb()
    {
        explicitWait(3);
        return (isElementPresent("snbNavigation")&&isElementPresent("chatButtonOnSnb")&&isElementPresent("SnbMenuSearch"));
    }

    /**
     * verify filter header
     * @return
     */
    public boolean verifyHeaderOfFilterScreen()
    {
        explicitWait(3);
        return (isElementPresent("navigateUP")&&isElementPresent("resetFilter"));
    }
    /**
     * verify filter header
     * @return
     */
    public boolean verifyHeaderOfCarsSnb()
    {
        explicitWait(3);
        return (isElementPresent("chatButtonOnSnb")&&isElementPresent("SnbMenuSearch")&&isElementPresent("changecategorySNB")&&isElementPresent("snbNavigation"));
    }
    /**
     * verify buttons below snb
     */
    public boolean verifyButtonsBelowSnb()
    {
        String button1=Mapper.finds(domFile,"buttonsBelowSnb").get(0).getText();
        String button2=Mapper.finds(domFile,"buttonsBelowSnb").get(1).getText();
        System.out.println(button1 + "&" + button2);
        return (button1.equalsIgnoreCase("Sort")&&button2.equalsIgnoreCase("Create Alert"));

    }
    /**
     * get text of headre
     */
    public String getHeaderText()
    {
        Mapper.waitForElementToBeVisible(domFile, "textView");
        return Mapper.find(domFile,"textView").getText();
    }
    /**
     * verify real; estate Snb Headre
     */
    public boolean verifyRealEstateHeader()
    {
        return (isElementPresent("chatButtonOnSnb")&&isElementPresent("SnbMenuSearch")&&isElementPresent("snbNavigation"));
    }
    public boolean verifyHeaderOfServicesSnb()
    {
        explicitWait(3);
        return (isElementPresent("chatButtonOnSnb")&&isElementPresent("SnbMenuSearch")&&isElementPresent("changecategorySNB")&&isElementPresent("snbNavigation"));
    }
    public List getAdTitlesOnSNB()
    {
        List<WebElement>title=Mapper.finds(domFile, "realEstateSnbAdTitle");
        List<String>AdTtle=new ArrayList<>();
        for (WebElement e: title)
        {
            AdTtle.add(e.getText());
        }
        return AdTtle;
    }
    public void selectFirstPopularProduct() {
        Mapper.waitForElementToBeVisible(domFile, "popularProducts");
        Mapper.finds(domFile, "popularProducts").get(1).click();
    }
    /**
     * click on life style subcat on chp
     */
    public void clickOnLifeStyleSubCAtOnCHP()
    {
        Mapper.waitForElementToBeClickable(domFile,"lifeStyleSubCAtImgOnSNB");
        Mapper.find(domFile,"lifeStyleSubCAtImgOnSNB").click();
    }

    public void scrollToBuyNowScreen()
    {
        WebElement scrollView = Mapper.find(domFile,"snbscrollview");
        loopScrollToElementByName(scrollView,"BuyNowButton",true,1000);
    }

    public void scrollToBidNowAd()
    {
        try{
            while(Mapper.find(domFile,"BidNowButton").isDisplayed()==false)
            {
                Mapper.scroll("Bid Now");
            }
        }
        catch (NullPointerException npe){
            Mapper.scroll("Bid Now");
            npe.printStackTrace();
        }
    }

    public void selectAuctionAd() {
        try {
            for (int i = 0; i < 4; i++) {
                Mapper.finds(domFile, "snbAdTitle").get(i).click();
                handleOverlay();
                Mapper.waitForElementToBeVisible(domFile, "BidNowButton", 4);
                if (Mapper.waitForElementToBeVisible(domFile, "BidNowButton", 2)) {
                    break;
                } else {
                    navigateBack();
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void tapBidNow(){
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        Mapper.find(domFile,"BidNowButton").click();
    }
    public boolean ifAmountPopulates(){
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        if(Mapper.find(domFile,"PopulatedAmountTextField").getText()!=null){
            return true;
        }
        else
            return false;
    }
    public boolean ifBidAmountIncreasesOnEveryBid(int LastBid, int NewBid){
        //According to 2% logic
        if(LastBid+(LastBid*0.02)==NewBid){
            return true;
        }
        else
            return false;
    }

    public int getLastBidAmount(){
        return Integer.parseInt(Mapper.find(domFile,"LastBidAmount").getText().replaceAll("\\D+",""));
    }
    public int getPopulatedAmount(){
        return Integer.parseInt(Mapper.find(domFile,"PopulatedAmountTextField").getText().replaceAll("\\D+",""));
    }

    public boolean ifBidNowFormOpen(){
        hideKeyboard();
//        navigateBack();
        return (isElementPresent("BidNowButton")&&isElementPresent("BidTimeLeft"));
    }
    public void enterBidMobileNumber(String mobile){
        Mapper.waitForElementToBeVisible(domFile,"BidMobile");
        Mapper.find(domFile,"BidMobile").sendKeys(mobile);}
}





