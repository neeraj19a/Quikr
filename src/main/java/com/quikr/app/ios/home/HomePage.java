package com.quikr.app.ios.home;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 29/9/15.
 */
public class HomePage extends com.quikr.app.ios.AppiOSPageBase {
    public HomePage(AppiumDriver driver)
    {
        super(domFile,driver);
    }
    // const string
    private static final String domFile = getProperties().get("IOS_HOME_DOM_FILE");

    /**
     * function to click on select city
     */
    public void clickOnSelectCity()
    {
        Mapper.find(domFile,"selectCity").click();
       // Mapper.waitForElementToBeVisible(domFile,"currentLocation");
    }

    /**
     * function to select city
     */
    public void selectCityName()
    {
       // Mapper.waitForElementToBeClickable(domFile,"cityName");
        Mapper.find(domFile,"cityName").click();
    }

    /**
     * function to validate city
     */
    public String validateCityName()
    {
        String text=Mapper.find(domFile,"selectCity").getText();
        System.out.println(text);
        return text;
    }

    /**
     * function to click on post ad button
     */
    public void clickOnPostAdButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"postAdButton");
        Mapper.find(domFile,"postAdButton").click();
    }

    /**
     * function to select category or subCategory
     * @param category
     */
    public void selectCategoryOrSubcategory(String category, String value)
    {
        System.out.println(category);
        if(!isElementPresent(category))
        {
            Mapper.scroll(category);
        }
        Mapper.waitForElementToBeVisible(domFile, category);
        Mapper.findAndReplace(domFile, category, new String[] {value}).click();
    }

    /**
     * function to select category or subCategory
     * @param category
     */
    public void selectCategoryOrSubcategory(String category)
    {
        System.out.println(category);
        if(!isElementPresent(category))
        {
            Mapper.scroll(category);
        }
        Mapper.waitForElementToBeVisible(domFile, category);
        Mapper.find(domFile, category).click();
    }

    /**
     * function to click on menu icon button on home page
     */
    public void clickOnMenuIconButton()
    {
        Mapper.finds(domFile,"menuIconButton").get(0).click();
    }


    /**
     * function click on Alert button
     */
    public void clickOnAlertButton()
    {
        Mapper.find(domFile,"alertButton").click();
    }

    /**
     * function to click on search bar
     */
    public void clickOnSearchBar()
    {
        Mapper.waitForElementToBeVisible(domFile,"searchBar");
        Mapper.find(domFile,"searchBar").click();
    }

    /**
     * function to sends text in search bar
     */
    public void sendTextInSearchBar(String str)
    {
        Mapper.find(domFile,"enterTextInSearchBar").sendKeys(str);
    }

    /**
     *   function to validate search suggestion options
     */

    public boolean validateSearchSuggestionOptions(String str)
    {
        for (int i = 1; i <= 4; i++)
        {

            if ((Mapper.findAndReplace(domFile, "searchSuggestionOptionName", new String[]{Integer.toString(i)}).getAttribute("name").contains(str)) ) {
                return true;
            }
        }

        return false;
    }

    /**
     * function to select  search suggestion option
     */
    public void selectSearchSuggestionOptionName(int i)
    {
       // Mapper.finds(domFile,"searchSuggestionOptionName").get(0).click();
        Mapper.findAndReplace(domFile, "searchSuggestionOptionName", new String[]{Integer.toString(i)}).click();
    }

    /**
     * function to click on home icon
     */
    public void selectHomeButton()
    {
        Mapper.find(domFile,"homeButton").click();
    }

    /**
     * function to click on chat now button
     */
    public void selectChatNowButton()
    {
        Mapper.find(domFile,"chatNowButton").click();
    }

    /**
     * function to click on current selected city
     */
    public void clickOnCurrentSelectedCity(String city)
    {
        List<WebElement> allElement=Mapper.find(domFile, "UIANavigationBarList").findElements(By.className("UIAButton"));
        System.out.println(allElement.size());
        System.out.println(city);
        int x,y;
        for(int i=0;i<allElement.size();i++)
        {
            if(allElement.get(i).getAttribute("name").equals(city))
            {
                System.out.println(allElement.get(i).getAttribute("name"));
                x= allElement.get(i).getLocation().getX();
                y=allElement.get(i).getLocation().getY();
                System.out.println(x);
                System.out.println(y);
                Mapper.tap(1,x,y,3000);
               // allElement.get(i).click();
            }
        }


      //  Mapper.tap(1,44,20,3000);

//        Mapper.finds(domFile,"currentSelectedCity").get(1).click();
//        Mapper.waitForElementToBeVisible(domFile, "cityName");
//        Mapper.find(domFile,"cityName").click();
    }

    /**
     * function to change the city
     */
    public void changeCity(String selectNameForChangeCity)
    {
        //Mapper.scroll(selectNameForChangeCity);
        Mapper.waitForElementToBeVisible(domFile,selectNameForChangeCity);
        Mapper.find(domFile,selectNameForChangeCity).click();
     //   Mapper.waitForElementToBeVisible(domFile,"homeButton");

    }

    /**
     * function to validate change city
     */
    public boolean validateChangeCity(String city)
    {
        boolean found=false;

        Mapper.waitForElementToBeVisible(domFile,"currentSelectedCity");
        String str=  Mapper.find(domFile,"currentSelectedCity").getAttribute("name");
        if(str.contains(city))
        {
            found=true;
        }

        return found;
    }

    /**
     * function to select don't allow option when app is install
     */
    public void selectDontAllowOption()
    {
        Mapper.waitForElementToBeVisible(domFile,"don'tAllowButton");
        Mapper.find(domFile,"don'tAllowButton").click();
    }

    /**
     * function to check don'tAllow pop is showing or not
     */
    public boolean checkDontAllowButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"don'tAllowButton");
        return isElementPresent("don'tAllowButton");
    }

    /**
     * function to click on more button
     */

    public void selectMoreButtonOnHomePage()
    {
        Mapper.find(domFile,"moreButton").click();
    }

    /**
     * function to click on more button in category
     */
    public void clickOnMoreButtonInCategory()
    {
        Mapper.find(domFile,"moreButtonInCategory").click();
    }

    /**
     * function to validate more button on home page
     */
    public boolean validateMoreButtonOnHomePage()
    {
        return isElementPresent("moreButton");
    }

    /**
     * function to validate less button on home page
     */
    public boolean validateLessButtonOnHomePage()
    {
        return isElementPresent("lessButton");
    }

    /**
     * function to click on account button
     */
    public void selectAccountButton()
    {
        Mapper.find(domFile,"accountButton").click();
    }

    /**
     * function to validate home page option after city selection
     */
    public boolean validateQuikrXOptionOnHomePageAfterCitySelection()
    {
        Mapper.waitForElementToBeVisible(domFile,"quikrXPhones");
        return (isElementPresent("quikrXPhones") && isElementPresent("quikrCertifiedPhones") && isElementPresent("exchangeOldForNew"));
    }

    /**
     * function to click on cross button on allow location popup
     */
    public void selectCrossButtonOnAllowLocationPopup()
    {
        Mapper.find(domFile,"crossButton").click();
    }

    /**
     * function to validate recently ad is present on home page
     */
    public boolean validateRecentlyViewedAdText()
    {
        return isElementPresent("recentlyViewedAdText");
    }

    /**
     * function to select recently viewed ad
     */
    public void selectRecentlyViewedAd(int i)
    {
        Mapper.findAndReplace(domFile, "clickOnRecentlyViewedAd", new String[]{Integer.toString(i)}).click();
    }

    /**
     * function to click on seeAll button
     */
    public void clickOnSeeAllButton()
    {
        Mapper.find(domFile,"seeAllButton").click();
    }

    /**
     * function to validate see all button
     */
    public boolean validateSeeAllButton()
    {
        return isElementPresent("seeAllButton");
    }

    /**
     * function to select quikrX ads
     */
    public void selectQuikrXAd(int i)
    {
        Mapper.findAndReplace(domFile, "clickOnQuikrXAd", new String[]{Integer.toString(i)}).click();
    }

    /**
     * function to count the no of ad coming in quikrX and validate
     */
    public boolean validateQuikrXAdOnHomePage()
    {
        List<WebElement> allElement=Mapper.find(domFile, "uiaTableListForQuikrXAd").findElements(By.className("UIACollectionCell"));
        System.out.println(allElement);
        int count=allElement.size();
        System.out.println(count);
        if(count==10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * function to select dismiss button in no city selected popup
     */
    public void selectDismissButtonInNoCitySelectedPopup()
    {
        Mapper.waitForElementToBeVisible(domFile,"dismissButton");
        Mapper.find(domFile,"dismissButton").click();
    }

    /**
     * function to count the no of city
     */
    public boolean validateNoOfCityList()
    {
        List<WebElement> allElement=Mapper.find(domFile, "uiaTableViewListForCity").findElements(By.className("UIATableCell"));
        System.out.println(allElement);
        int count=allElement.size();
        System.out.println(count);
        if(count==939)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * function to click on clear button in search box
     */
    public void clickOnClearButtonInSearchBox()
    {
        Mapper.find(domFile,"clearButton").click();
    }

    /**
     * function to validate clear button presence
     */
    public boolean validateClearButton()
    {
        return isElementPresent("clearButton");
    }
}
