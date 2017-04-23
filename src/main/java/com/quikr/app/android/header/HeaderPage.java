package com.quikr.app.android.header;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by Manvendra Singh on 17/8/15.
 */
public class HeaderPage extends AppAndroidPageBase {
    public HeaderPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_HEADER_DOM_FILE");

    /**
     * function to select header search icon
     */
    public void selectHeaderSearchIcon()
    {
        Mapper.waitForElementToBeVisible(domFile,"headerSearch");
        Mapper.find(domFile,"headerSearch").click();

    }

    /**
     * function to send text in search header
     */
    public void searchTextFromHomePage(String searchText)
    {
        Mapper.waitForElementToBeVisible(domFile, "inputText");
        Mapper.find(domFile,"inputText").sendKeys(searchText);


    }

    public void navigateToBackPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"navigateback");
        Mapper.find(domFile,"navigateback").click();

    }

    /**
     * function to click on search button after sends some text in search box
     */
    public void clickOnSearchSuggestionAfterInputSomeText()
    {
        Mapper.waitForElementToBeVisible(domFile, "searchSuggestionText");
        Mapper.find(domFile,"searchSuggestionText").click();
    }

    /**
     * check city is selected on launch
     */
    public  void checkCity()
    {
        if(!isElementPresent("titlebar"))
            navigateBack();
        Mapper.waitForElementToBeClickable(domFile, "city");
        String city=Mapper.find(domFile,"city").getText();
        System.out.println(city);
        if (!city.equalsIgnoreCase("Bangalore"))
        {
            Mapper.find(domFile,"city").click();
            Mapper.find(domFile,"cityName").click();
        }
    }

    /**
     * get subcat of autosuggest options
     */
    public String getAutoSuggestSuggestSubCat()
    {
        Mapper.waitForElementToBeVisible(domFile, "suggestionSubCAt");
        String subcat= Mapper.find(domFile,"suggestionSubCAt").getText();
        return subcat;
    }
    /**
     * search city
     */
    public void searchCity(String city)
    {
        Mapper.waitForElementToBeVisible(domFile,"searchCity",20);
        Mapper.find(domFile,"searchCity").sendKeys(city);
    }
    /**
     * Select City from List
     */
    public void selectNonEscrowCity()
    {
        Mapper.find(domFile,"noEscrowCityName").click();
    }

    /**
     * validate recent search
     */
    public boolean validateRecentSearch()
    {
        return isElementPresent("honda");
    }
    /**
     *validate snb search title
     */
    public String snbSearchTitle()
    {
        return (Mapper.find(domFile,"snbSearchTitle").getText());
    }
    /**
     * get name of city selected
     */
    public void getNameOfCitySelected()
    {
        Mapper.waitForElementToBeClickable(domFile, "city");
        String city=Mapper.find(domFile,"city").getText();
        System.out.println(city);
    }

    public void permissionRequestInstall(){
        Mapper.waitForElementToBeVisible(domFile, "install", 20);
        Mapper.find(domFile,"install").click();
    }

    public void gotoHomeFromSideMenuDrawer(){
        Mapper.waitForElementToBeVisible(domFile,"openSideMenuDrawer");
        Mapper.find(domFile,"openSideMenuDrawer").click();
        Mapper.waitForElementToBeVisible(domFile,"drawerList");
        Mapper.finds(domFile,"drawerList").get(1).click();
    }

    public void opensidemenudrawer(){
        Mapper.waitForElementToBeVisible(domFile,"openSideMenuDrawer");
        Mapper.find(domFile,"openSideMenuDrawer").click();
    }

    public void gotoMyAccountSideMenuDrawer(){
        opensidemenudrawer();
        Mapper.waitForElementToBeVisible(domFile,"drawerList");
        Mapper.finds(domFile,"drawerList").get(1).click();
    }
    public void gotoNotificationsSideMenuDrawer(){
        opensidemenudrawer();
        Mapper.waitForElementToBeVisible(domFile,"drawerList");
        Mapper.finds(domFile,"drawerList").get(6).click();
    }
    /**
     * verify header elements
     */
    public boolean verifyHeaderOfHomePage()
    {
        explicitWait(3);
        return (isElementPresent("openSideMenuDrawer")&&isElementPresent("city")&&isElementPresent("quikrLogoheader")&&isElementPresent("headerSearch")&&isElementPresent("chatButtonOnHeader"));
    }

    public void tapQuikrNext()
    {
        explicitWait(3);
        Mapper.find(domFile,"quikrNext").click();
    }

    public void tapMyOffers()
    {
        explicitWait(2);
        Mapper.find(domFile,"myOffersHeader").click();
    }
    /**
     * from auto suggest selct honda
     */
    public void selectSearchSuggestionFromAutoSuggest(String text)
    {
        Mapper.waitForElementToBeVisible(domFile, "searchSuggestionText", 10);
        Mapper.findAndReplace(domFile, "selectHondaFromAutoSearch", new String[]{text}).click();
    }
    /**
     * function to select header search icon on SNB
     */
    public void selectSnbHeaderSearchIcon()
    {
        Mapper.waitForElementToBeVisible(domFile,"snbHeaderSearch");
        Mapper.find(domFile, "snbHeaderSearch").click();

    }
    /**
     * validate recently searched item
     */
    public boolean validateRecentlySearchedItem(String searchText)
    {
        Mapper.waitForElementToBeVisible(domFile,"recentlySearchedItem");
        return (Mapper.find(domFile,"recentlySearchedItem").getText().equalsIgnoreCase(searchText));
    }

    public void clickOnQuikrNext(){
        Mapper.waitForElementToBeVisible(domFile,"chatButtonOnHeader");
        Mapper.find(domFile,"chatButtonOnHeader").click();
    }
}
