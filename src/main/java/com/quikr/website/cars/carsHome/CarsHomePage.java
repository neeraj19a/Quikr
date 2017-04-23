package com.quikr.website.cars.carsHome;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Saurabh on 5/11/15.
 */
public class CarsHomePage extends CarsPageBase
{

    public CarsHomePage(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String domFile = getProperties().get("CARS_HOMEPAGE_DOM_FILE");

    public void clickNewCarsRadioButton(){
        if(Mapper.waitForElementToBeClickable(domFile,"newCarsRadioButton")==true){
            Mapper.find(domFile,"newCarsRadioButton").click();
        }
        else {
            logger.info("New cars radio button is not clickable");
        }
    }

    public void toggleCars()
    {
        Mapper.waitForElementToBeVisible(domFile, "carHomepageTab");
        Mapper.find(domFile, "carHomepageTab").click();
    }

    public String returnSearchBarHeading(){
        String text=null;

        if(Mapper.find(domFile,"autoSuggestavailable").isDisplayed()) {
            text=Mapper.find(domFile, "mostPopularCarsSearch").getText();
        }
        else
            logger.info("Looks like Auto Suggest is not available on Clicking Search Box");
        return text;
    }

    public int numberofAutoSuggestoptionsSearch(){
        return Mapper.finds(domFile,"autoSuggestions").size();
    }

    public boolean isBudgetHeadingAvailable(){

        if(Mapper.find(domFile, "rupeeSybmol")!=null) {
            return true;
        }
        else
            logger.info("Looks like Rupee Symbol is not available on Budget Box");
            return false;
    }

    public boolean validateBudgetSearch(){

        boolean flag=false;
        WebElement element=Mapper.find(domFile, "budgetBox");
        element.click();
        if(element.getAttribute("aria-expanded").equals("true")){
            flag=true;
            logger.info("Budget Search Box opened");
        }
        else{
        flag=false;
            logger.info("Budget Search Box Not opened");
        }

        List<WebElement> minBudgetOptions=Mapper.finds(domFile,"budgetMinBoxOptions");
        List<WebElement> minBudgetOptionsvisible=new ArrayList<>();
        for (WebElement element1:minBudgetOptions){
            if (element1.isDisplayed()) {
                minBudgetOptionsvisible.add(element1);
            }
        }

        if(minBudgetOptionsvisible.size()==9){
            flag=true;
            logger.info("Min Budget options are fine");
        }
        else{
        flag=false;
            logger.info("Min Budget options are not fine , Expected Size is 9 but size is "+minBudgetOptionsvisible.size());
        }

        //Clicking Min Options to check if Max options are visible
        minBudgetOptionsvisible.get(0).click();

        List<WebElement> maxBudgetOptions=Mapper.finds(domFile,"budgetMaxBoxOptions");
        List<WebElement> maxBudgetOptionsvisible=new ArrayList<>();
        for (WebElement element2:maxBudgetOptions){
            if (element2.isDisplayed()) {
                maxBudgetOptionsvisible.add(element2);
            }
        }

        if(maxBudgetOptionsvisible.size()==9){
            flag=true;
            logger.info("Max Budget options are fine");
        }
        else{
            flag=false;
        logger.info("Max Budget options are not fine , Expected Size is 9 but size is "+maxBudgetOptionsvisible.size());
        }

        return flag;
    }

    public String returnBudgettext(){
        logger.info("Budget Text is " + Mapper.find(domFile, "budgetText").getText());
        return Mapper.find(domFile,"budgetText").getText();
    }
    public void toggleBikes()
    {
        Mapper.waitForElementToBeClickable(domFile, "bikeHomepageTab");
        Mapper.find(domFile, "bikeHomepageTab").click();
    }

    public void toggleCommercial_Vehicles()
    {
        Mapper.waitForElementToBeVisible(domFile, "commercialHomepageTab");
        Mapper.find(domFile, "commercialHomepageTab").click();
    }

    public void toggleBicycles()
    {
        Mapper.waitForElementToBeVisible(domFile, "BicyclesHomepageTab");
        Mapper.find(domFile, "BicyclesHomepageTab").click();
    }

    public void togglesparePartsAccessoriesHomepageTab()
    {
        Mapper.waitForElementToBeVisible(domFile, "sparePartsAccessoriesHomepageTab");
        Mapper.find(domFile, "sparePartsAccessoriesHomepageTab").click();
    }
    public boolean validateCountText(String vehicleType)
    {
        boolean countTextStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "countLabel");
        if(Mapper.find(domFile, "countLabel").getText().toLowerCase().contains(vehicleType))
            countTextStatus = true;

        return countTextStatus;
    }

    public boolean validateSearchBarIcon(String vehicleType)
    {
        boolean searchBarIconStatus = false;

        if(vehicleType.equalsIgnoreCase("bike")||vehicleType.equalsIgnoreCase("car")) {
            Mapper.waitForElementToBeVisible(domFile, "searchBarBikeIcon");
                searchBarIconStatus = true;
        }
        else{
            Mapper.waitForElementToBeVisible(domFile, "searchBarIcon");
                searchBarIconStatus = true;
        }

        return searchBarIconStatus;
    }

    public boolean validateSearchBarPlaceholderText(String vehicleType)
    {
        boolean searchBarPlaceholderTextStatus = false;
        Mapper.waitForElementToBeVisible(domFile, "searchBar");
        if(vehicleType.equalsIgnoreCase("Bike")||vehicleType.equalsIgnoreCase("car")) {
            if (Mapper.find(domFile, "searchBar").getAttribute("placeholder").toLowerCase().contains(vehicleType)) {
                searchBarPlaceholderTextStatus = true;
            }
            else {
                logger.info("Looks like searchBar does not contain proper PlaceHolder");
                searchBarPlaceholderTextStatus=false;
            }
        }
        else if (Mapper.find(domFile, "searchBar").getAttribute("placeholder").contains("Type your Search Here")) {
            searchBarPlaceholderTextStatus = true;
        }

        return searchBarPlaceholderTextStatus;
    }

    public void clickSearchBar()
    {
        Mapper.waitForElementToBeVisible(domFile, "searchBar");
        Mapper.find(domFile, "searchBar").click();
    }

    public void searchText(String searchText)
    {
        Mapper.waitForElementToBeVisible(domFile, "searchBar");
        Mapper.find(domFile, "searchBar").clear();

        Mapper.find(domFile, "searchBar").sendKeys(searchText);
    }

    public void searchFromSearchList(String searchText)
    {
        Mapper.waitForElementToBeVisible(domFile, "searchItemSelect", new String[]{searchText});
        Mapper.findAndReplace(domFile, "searchItemSelect", new String[]{searchText}).click();

        if(!Mapper.waitForElementToBeInvisible(domFile, "searchBarList"))
        {
            Mapper.find(domFile, "searchBar").clear();
            Mapper.find(domFile, "searchBar").sendKeys(searchText);

            Mapper.waitForElementToBeVisible(domFile, "searchItemSelect", new String[]{searchText});
            Mapper.findAndReplace(domFile, "searchItemSelect", new String[]{searchText}).click();
        }
    }

    public boolean validateMostPopularList()
    {
        boolean mostPopularListStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "mostPopularList");
        List<WebElement> mostPopularList = Mapper.finds(domFile, "mostPopularList");

        if (mostPopularList.size() > 0 && mostPopularList.size() <= 8)
            mostPopularListStatus = true;

        return  mostPopularListStatus;
    }

    public String searchFromMostPopularList()
    {
        String itemSelected = "";

        Mapper.waitForElementToBeVisible(domFile, "mostPopularList");
        itemSelected = Mapper.find(domFile, "mostPopularList").getText();

        Mapper.find(domFile, "mostPopularList").click();

        return itemSelected;
    }

    public boolean validateHistoricSearchList()
    {
        boolean historicSearchListStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "historicSearchList");
        List<WebElement> historicSearchList = Mapper.finds(domFile, "historicSearchList");

        if (historicSearchList.size() > 0 && historicSearchList.size() <= 8)
            historicSearchListStatus = true;

        return  historicSearchListStatus;
    }

    public String searchFromHistoricSearchList()
    {
        String itemSelected = "";

        Mapper.waitForElementToBeVisible(domFile, "historicSearchList");
        itemSelected = Mapper.find(domFile, "historicSearchList").getText();

        Mapper.find(domFile, "historicSearchList").click();

        return itemSelected;
    }

    public void clickBudget()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "budgetDropDown")==true) {
            Mapper.find(domFile, "budgetDropDown").click();
        }
        else
        {
            logger.info("Looks like Budget is not visible");
        }
    }

    public boolean isFatFooterProper(){
        List<String> elements=new ArrayList<>();
        for (int i=1;i<=4;i++){
            WebElement element=Mapper.findAndReplace(domFile, "fatFooter", new String[]{Integer.toString(i)});
            String text=element.getText();
            elements.add(text.replaceAll(" ",""));
        }
        logger.info("Here are elements-->" + elements);
        List<String> expectedElements=new ArrayList<>();
        expectedElements.add(0,"USEDCARS");
        expectedElements.add(1,"NEWCARS");
        expectedElements.add(2,"USEDBIKES");
        expectedElements.add(3, "BYCITY");
        logger.info("Expected elements are " + expectedElements);
        if(elements.equals(expectedElements)){
            return true;
        }
        else{
            logger.info("Looks like Footer elements are not proper");
            return false;
        }
    }
    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public void selectMinBudget(String vehicleType, String budgetValue)
    {
        String budgetClass = "type-based for-"+vehicleType.toLowerCase().trim();

        Mapper.waitForElementToBeVisible(domFile, "minBudgetDropDown", new String[]{budgetClass, budgetValue});
        Mapper.findAndReplace(domFile, "minBudgetDropDown", new String[]{budgetClass, budgetValue}).click();
    }

    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public void selectMaxBudget(String vehicleType, String budgetValue)
    {
        String budgetClass = "type-based for-"+vehicleType.toLowerCase().trim();

        Mapper.waitForElementToBeVisible(domFile, "maxBudgetDropDown", new String[]{budgetClass, budgetValue});
        Mapper.findAndReplace(domFile, "maxBudgetDropDown", new String[]{budgetClass, budgetValue}).click();
    }

    public void toggleUsedCars(){
        if(Mapper.waitForElementToBeClickable(domFile, "toggleUsedCars")==true){
            Mapper.find(domFile,"toggleUsedCars").click();
        }
        else
            logger.info("Used Cars Button not Clickable");
    }

    public void search()
    {
        if (Mapper.waitForElementToBeClickable(domFile, "searchButton")==true) {
            WebElement searchButton=Mapper.find(domFile, "searchButton");
            getElementintoView(searchButton);
            searchButton.click();

        }
        else
            logger.info("Search Button not Clickable");
    }

    public void clickInspectedCars(){
        Mapper.waitForElementToBeClickable(domFile, "inspectedCars");
        Mapper.find(domFile,"inspectedCars").click();
    }

    public boolean validateInspectedText()
    {
        boolean inspectedTextStatus = false;

        if(Mapper.waitForElementToBeVisible(domFile, "inspectedText")==true){
            inspectedTextStatus=true;
        if(Mapper.find(domFile, "inspectedDescription").getText().contains("Choose from India's largest collection of Individual-owned Inspected Cars")) {
            inspectedTextStatus = true;

        }
            else {
            logger.info("Looks like inspectedDescription is not available");
        }
        }
        else {
            logger.info("Looks like inspectedText is not available");
        }
        return inspectedTextStatus;
    }

    public void clickCheckMSP()
    {
        Mapper.waitForElementToBeVisible(domFile, "checkMSPHomepage");
        Mapper.find(domFile, "checkMSPHomepage").click();
    }

    /**  vehicleType can take the values as :- bikes, cars  **/
    public boolean validateCheckMSPText()
    {
        boolean mspTextStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "mspText");
        if(Mapper.find(domFile, "mspText").getText().contains("Used Car Valuation")) {
            mspTextStatus = true;
        }
        else {
            logger.info("Looks like Msp Text is not Available");
        }
        if(Mapper.find(domFile, "mspDescription").getText().contains("Find the true value of any Car or Bike with India's best used vehicle pricing tool")) {
            mspTextStatus = true;
        }
        else {
            logger.info("Looks like MSP Description On Cars Page is not proper");
        }

        return mspTextStatus;
    }

    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public boolean validateMostPopularSection(String vehicleType)
    {
        String vehicleClass = "popular-carousel-"+vehicleType;

        boolean mostPopularSectionStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "mostPopularAds", new String[]{vehicleClass});
        List<WebElement> mostPopularSection = Mapper.findsAndReplace(domFile, "mostPopularAds", new String[]{vehicleClass});

        if (mostPopularSection.size()==8) {
            mostPopularSectionStatus = true;
        }
        else {
            logger.info("Most Popular Section is not proper , It has Size-->"+mostPopularSection.size());
        }

        return  mostPopularSectionStatus;
    }

    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public HashMap <String, String> mostPopularData(String vehicleType)
    {
        String vehicleClass = "popular-carousel-"+vehicleType;
        HashMap<String, String> data = new HashMap<>();

        Mapper.waitForElementToBeVisible(domFile, "mostPopularAds", new String[]{vehicleClass});

        if(!vehicleType.equals("vehicles"))
            data.put("LOGO", Mapper.findAndReplace(domFile, "mostPopularAdImage", new String[]{vehicleClass}).getAttribute("src"));

        String temp = Mapper.findAndReplace(domFile, "mostPopularAdImage", new String[]{vehicleClass}).getAttribute("src");
        String[] tempBrand = temp.split("/");
        data.put("BRAND", tempBrand[tempBrand.length - 1].replace(".jpg", "").replace("-", " "));

        data.put("MODEL", Mapper.findAndReplace(domFile, "mostPopularAdModel", new String[]{vehicleClass}).getText());

        logger.info("Here is data-->"+data);
        return data;
    }

    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public void clickMostPopularAd(String vehicleType)
    {
        String vehicleClass = "popular-carousel-"+vehicleType;

        Mapper.waitForElementToBeVisible(domFile, "mostPopularAds", new String[]{vehicleClass});
        Mapper.findAndReplace(domFile, "mostPopularAdLinkText", new String[]{vehicleClass}).click();
    }

    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public boolean validateRecentlyPostedSection(String vehicleType)
    {
        String vehicleClass = vehicleType+"-carousel";

        boolean recentlyPostedSectionStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "recentlyPostedAds");
        List<WebElement> recentlyPostedSection = Mapper.finds(domFile, "recentlyPostedAds");

        if (recentlyPostedSection.size() > 0 && recentlyPostedSection.size() <= 4)
            recentlyPostedSectionStatus = true;

        return recentlyPostedSectionStatus;
    }

    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public HashMap <String, String> recentlyPostedData(String vehicleType)
    {
        //Have to validate the same set of data on VAP Page
        String vehicleClass = vehicleType+"-carousel";
        HashMap<String, String> data = new HashMap<>();

        Mapper.waitForElementToBeVisible(domFile, "recentlyPostedAds");

        data.put("TITLE", Mapper.finds(domFile, "recentlyPostedAdTitle").get(0).getAttribute("title").replaceAll(" ",""));

        WebElement kms=Mapper.findAndReplace(domFile, "recentlyPostedAdData", new String[]{Integer.toString(1)});
            if(kms.getText().contains("kms"))
                data.put("KMS DRIVEN", kms.getText());

        WebElement fuel=Mapper.findAndReplace(domFile, "recentlyPostedAdData", new String[]{Integer.toString(2)});
        if(vehicleType=="cars"){
        if(fuel.getText().toLowerCase().contains("diesel") || fuel.getText().toLowerCase().contains("petrol"))
                data.put("FUEL TYPE", fuel.getText());
        }

        data.put("PRICE", Mapper.finds(domFile, "recentlyPostedAdPrice").get(0).getText());

        logger.info("Data is " + data);
        return data;
    }

    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public void clickRecentlyPostedAd(String vehicleType)
    {
        String vehicleClass = vehicleType+"-carousel";

        //Mapper.waitForElementToBeVisible(domFile, "recentlyPostedAds");
        Mapper.finds(domFile, "recentlyPostedAdLinkText").get(0).click();
    }


    public boolean areChatButtonavailableonHomePage(String vehicleType){

        String vehicleClass = vehicleType+"-carousel";
        ArrayList<String> adId=new ArrayList<>();
        Mapper.waitForElementToBeVisible(domFile, "recentlyPostedAds", new String[]{vehicleClass});

        if(Mapper.findAndReplace(domFile, "recentlyPostedAdUserState", new String[]{vehicleClass}).getAttribute("style").contains("rgb(92, 184, 92)")) {
            List<WebElement> chatButtons = Mapper.finds(domFile, "recentlyPostedAd_Chat");
            for (WebElement chatelement : chatButtons) {
                if (chatelement.isDisplayed()) {
                    chatelement.click();
                    adId.add(chatelement.getAttribute("data-adid"));
                    logger.info("Here are Ad Id for chat-->"+adId);
                }
            }
        }
        if (adId.size()==0){
            return false;
        }
        else
            return true;
    }


    public boolean areReplyButtonavailableonHomePage(String vehicleType){

        String vehicleClass = vehicleType+"-carousel";
        ArrayList<String> adId=new ArrayList<>();
        Mapper.waitForElementToBeVisible(domFile, "recentlyPostedAds", new String[]{vehicleClass});

        if(Mapper.findAndReplace(domFile, "recentlyPostedAdUserState", new String[]{vehicleClass}).getAttribute("style").contains("rgb(92, 184, 92)")) {
            List<WebElement> replyButtons = Mapper.finds(domFile, "recentlyPostedAd_Reply");
            for (WebElement replyelement : replyButtons ) {
                if (replyelement.isDisplayed()) {
                    replyelement.click();
                    adId.add(replyelement.getAttribute("data-adid"));
                    logger.info("Here are Ad Id for Reply-->"+adId);
                }
            }
        }
        if (adId.size()==0){
            return false;
        }
        else
            return true;
    }
    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public boolean validateChat(String vehicleType)
    {
        String vehicleClass = vehicleType+"-carousel";
        String adId=null;
        boolean chatStatus=false;

        Mapper.waitForElementToBeVisible(domFile, "recentlyPostedAds", new String[]{vehicleClass});

        if(Mapper.findAndReplace(domFile, "recentlyPostedAdUserState", new String[]{vehicleClass}).getAttribute("style").contains("rgb(92, 184, 92)")) {
            List<WebElement> chatButtons = Mapper.finds(domFile, "recentlyPostedAd_Chat");
            for (WebElement chatelement : chatButtons) {
                if (chatelement.isDisplayed()) {
                    chatelement.click();
                    adId = chatelement.getAttribute("data-adid");
                }
            }
        }
            chatStatus = chatBoxStatus(adId);

        return chatStatus;
    }


    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public boolean validateReply(String vehicleType)
    {
        String vehicleClass = vehicleType+"-carousel";
        String adId=null;
        boolean replyStatus=false;

        Mapper.waitForElementToBeVisible(domFile, "recentlyPostedAds", new String[]{vehicleClass});

        if(Mapper.findAndReplace(domFile, "recentlyPostedAdUserState", new String[]{vehicleClass}).getAttribute("style").contains("rgb(92, 184, 92)")) {

            List<WebElement> replyButtons=Mapper.finds(domFile,"recentlyPostedAd_Reply");
            for (WebElement element:replyButtons) {
                if(element.isDisplayed()){
                    element.click();
                    adId=element.getAttribute("data-adid");
                }
            }
            replyStatus = replyBoxStatus(adId);
        }

        return replyStatus;
    }
    /**  vehicleType can take the values as :- bikes, cars, vehicles  **/
    public String clickShortlist()
    {
        String adId;

        Mapper.waitForElementToBeVisible(domFile, "recentlyPostedAds");
        Mapper.waitForElementToBeClickable(domFile,"recentlyPostedAdShortlist");
            Mapper.find(domFile, "recentlyPostedAdShortlist").click();
            adId = Mapper.find(domFile, "recentlyPostedAdShortlist").getAttribute("id");
            logger.info("Here is Adid-->" + adId);
            return adId;
    }

    /**  tab can take the values as :- home, profile, messages  **/
    public boolean validateReviewsSection(String tab)
    {
        boolean reviewsSectionStatus = false ;

        Mapper.waitForElementToBeVisible(domFile, "reviewsToggle", new String[]{tab});
        Mapper.findAndReplace(domFile, "reviewsToggle", new String[]{tab}).click();
        Mapper.waitForElementToBeVisible(domFile, "reviewsBigImage", new String[]{tab});

        if(Mapper.findAndReplace(domFile, "reviewsBigImage", new String[]{tab}).getAttribute("src").contains("534x462.jpg") && Mapper.findAndReplace(domFile, "reviewsSmallImage", new String[]{tab}).getAttribute("src").contains("218x150.jpg")){
            if(Mapper.findAndReplace(domFile, "reviewsTitle", new String[]{tab}).getAttribute("href").replaceAll("534x462.jpg","").equals(Mapper.findAndReplace(domFile, "reviewsMore", new String[]{tab}).getAttribute("href"))) {
                reviewsSectionStatus = true;
            }
            else {
                logger.info("Looks like reviewsTitle are not proper");
            }
        }
        else {
            logger.info("Looks like reviewsBigImage are not proper");
        }

        return reviewsSectionStatus;
    }

    public String returnPitStopPageTitleText(){
        return Mapper.find(domFile,"pitStopPageTitle").getText();
    }

    /**  tab can take the values as :- home, profile, messages  **/
    public String clickReviews(String tab)
    {
        Mapper.waitForElementToBeVisible(domFile, "reviewsToggle", new String[]{tab});
        String title=Mapper.findAndReplace(domFile, "reviewsTitle", new String[]{tab}).getText();
        Mapper.findAndReplace(domFile, "reviewsToggle", new String[]{tab}).click();
        switchtoPopup(Mapper.findAndReplace(domFile, "reviewsTitle", new String[]{tab}));
        return  title;
    }

    public boolean validateJustLaunchedSection()
    {
        boolean justLaunchedSectionStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "justLaunched");
        List<WebElement> justLaunchedSection = Mapper.finds(domFile, "justLaunched");

        if(justLaunchedSection.size() > 0 && justLaunchedSection.size() <= 2) {
            for (int i = 0; i <= justLaunchedSection.size(); i++) {
                if (Mapper.findAndReplace(domFile, "justLaunchedImage", new String[]{String.valueOf(i)}).getAttribute("src").contains("218x150.jpg")) {
                    if (Mapper.findAndReplace(domFile, "justLaunchedTitle", new String[]{String.valueOf(i)}).getAttribute("href").equals(Mapper.findAndReplace(domFile, "justLaunchedMore", new String[]{String.valueOf(i)}).getAttribute("href"))) {
                        justLaunchedSectionStatus = true;
                    }
                    else {
                        logger.info("Just Launched Title are not proper ");
                    }
                }

                else {
                    logger.info("Just Launched Image are not proper ");
                }
            }
        }

        return justLaunchedSectionStatus;
    }

    public String clickJustLaunched(String newsNumber)
    {
        Mapper.waitForElementToBeClickable(domFile, "justLaunched");
        String justLaunchedTitle1=Mapper.findAndReplace(domFile, "justLaunchedTitle", new String[]{newsNumber}).getText();
        System.out.println("Here" + justLaunchedTitle1);
        switchtoPopup(Mapper.findAndReplace(domFile, "justLaunchedTitle", new String[]{newsNumber}));
        return  justLaunchedTitle1;
    }

    public boolean validateAutoNewsSection()
    {
        boolean autoNewsSectionStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "autoNews");
        List<WebElement> autoNewsSection = Mapper.finds(domFile, "autoNews");

        if(autoNewsSection.size() > 0 && autoNewsSection.size() <= 2) {
            for (int i = 0; i <= autoNewsSection.size(); i++) {
                if (Mapper.findAndReplace(domFile, "autoNewsTitle", new String[]{String.valueOf(i)}).getAttribute("href").equals(Mapper.findAndReplace(domFile, "autoNewsMore", new String[]{String.valueOf(i)}).getAttribute("href"))) {
                    autoNewsSectionStatus = true;
                }
                else {
                    logger.info("Looks like auto New title is not proper");
                }
            }
        }
        else {
            logger.info("Looks like auto New Section is not in proper size");
        }

        return autoNewsSectionStatus;
    }

    public String clickAutoNews(String newsNumber)
    {
        Mapper.waitForElementToBeVisible(domFile, "autoNews");
        String adtitle=Mapper.findAndReplace(domFile, "autoNewsTitle", new String[]{newsNumber}).getText();
        switchtoPopup(Mapper.findAndReplace(domFile, "autoNewsTitle", new String[]{newsNumber}));
        return adtitle;
    }

    public boolean validateVideosSection()
    {
        boolean videoSectionStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "video");
        List<WebElement> videoSection = Mapper.finds(domFile, "video");

        if(videoSection.size() > 0 && videoSection.size() <= 3) {
            for (int i = 1; i <= videoSection.size(); i++) {
                if (Mapper.findAndReplace(domFile, "videoImage", new String[]{String.valueOf(i)}).getAttribute("src").contains("324x235.jpg")) {
                    if (Mapper.findAndReplace(domFile, "videoTitle", new String[]{String.valueOf(i)}).getAttribute("href").equals(Mapper.findAndReplace(domFile, "videoMore", new String[]{String.valueOf(i)}).getAttribute("href"))) {
                        videoSectionStatus = true;
                    } else {
                        logger.info("Looks like Video Title are not Proper");
                    }
                }else {
                    logger.info("Looks like Video Image are not Proper");
                }
                }
            }
        return videoSectionStatus;
        }


    public String clickVideos(String videoNumber)
    {
        Mapper.waitForElementToBeVisible(domFile, "video");
        String videoTitle=Mapper.findAndReplace(domFile, "videoTitle", new String[]{videoNumber}).getText();
        switchtoPopup(Mapper.findAndReplace(domFile, "videoTitle", new String[]{videoNumber}));
        return  videoTitle;
    }

    public String getTextMostPopularAdsonQuikr(){
        return Mapper.find(domFile,"mostPopularAdsonQuikrText").getText();
    }

    public boolean numberofMostPopularAdsonQuikr(String vehicleClass){

        List<WebElement> mostpopularAdsonQuikr=Mapper.findsAndReplace(domFile,"mostPopularAdImage",new String[]{vehicleClass});

        boolean flag=false;
        if(mostpopularAdsonQuikr.size()==8){
            logger.info("Number of Most Popular Ads are 8");
            flag=true;
        }
        else {
            logger.info("Looks like Number of Most Popular Ads are not 8");
            flag=false;
        }
        return flag;
    }

    public List<String> returnModelsMostPopularonQuikr(String vehicleClass){
        List<WebElement> modelsonMostPopularAdsonQuikr=Mapper.findsAndReplace(domFile,"mostPopularAdModel",new String[]{vehicleClass});
        ArrayList<String> modelsName=new ArrayList<>();

        for(WebElement element:modelsonMostPopularAdsonQuikr){
            modelsName.add(element.getText().trim());
        }
        logger.info("modelsName are"+modelsName);
        return modelsName;
    }

    public List<String> returnModelsNamefromImagesonMostPopularAdsonQuikr(String vehicleClass){

        List<WebElement> mostpopularAdsonQuikr=Mapper.findsAndReplace(domFile,"mostPopularAdImage",new String[]{vehicleClass});
        ArrayList<String> modelsNamePopularAdsOnQuikr=new ArrayList<>();
        for(WebElement element:mostpopularAdsonQuikr){
            modelsNamePopularAdsOnQuikr.add(element.getAttribute("src").replaceAll("http://teja1.kuikr.com/images/QuikrCar/images/new_cars/"," ").replaceAll("/1_cropped.jpg","").replaceAll("[-_]", " ").trim());
        }
        logger.info("modelsNamePopularAdsOnQuikr are "+modelsNamePopularAdsOnQuikr);
        return  modelsNamePopularAdsOnQuikr;
    }

    public void clickMostPopularCar(int carNumber){
        List<WebElement> mostpopularCars=Mapper.finds(domFile,"mostPopularAds");
        mostpopularCars.get(carNumber-1).click();
    }

    public boolean isNumberofMostPopularAdsproper(String vehicleClass){
        boolean flag=false;
        List<WebElement> mostPopularCars=Mapper.finds(domFile,"mostPopularAdsNumber");
        List<Integer> mostPopularCarsNumber=new ArrayList<>();
        for(WebElement element:mostPopularCars){
            mostPopularCarsNumber.add(Integer.parseInt(element.getText().replaceAll(" ","").replaceAll(vehicleClass,"")));
        }

        for (int numbers:mostPopularCarsNumber){
            if(numbers<=0){
                flag=false;
                logger.info("Looks like Number of Cars for Most Popular Section are not proper Number returned are"+numbers);
            }
            else
                flag=true;
        }
        return flag;
    }
}