package com.quikr.app.ios.quikrX.quikrXSnbPage;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 14/10/15.
 */
public class QuikrXSnbPage extends com.quikr.app.ios.AppiOSPageBase {

    public QuikrXSnbPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_QUIKRXSNBPAGE_DOM_FILE");

    /**
     * function to validate  exchange old for new text  on snb page
     */
    public boolean validateSnbPageForExchangeOldForNew()
    {
        Mapper.waitForElementToBeVisible(domFile,"exchangeOldForNewTextDisplayOnSnbPage");
        return isElementPresent("exchangeOldForNewTextDisplayOnSnbPage");
    }

    /**
     * function to validate buy certified used phone text  on snb page
     */
    public boolean validateBuyCertifiedUsedPhoneTextOnSnbPage()
    {
        return isElementPresent("buyCertifiedPhoneTextOnSnbPage");
    }

    /**
     * function to open any ad on snb page
     */
    public void openAdOnSnbPage(int i)
    {
        List<WebElement> allElements = Mapper.finds(domFile, "listOfAllUIATablecell");
        allElements.get(i).findElements(By.className("UIAStaticText")).get(0).click();
        Mapper.waitForElementToBeVisible(domFile,"verify");
    }

    /**
     * function to validate sort option on snb page
     */
    public boolean validateSortOptions()
    {
        Mapper.waitForElementToBeVisible(domFile,"highToLow");
        return (isElementPresent("popularity") || isElementPresent("highToLow") || isElementPresent("lowToHigh"));
    }

    /**
     * function to click on filter button
     */
    public void clickOnFilterButtonOnSnbPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"filter");
        Mapper.find(domFile,"filter").click();
        Mapper.waitForElementToBeInvisible(domFile,"filter");
    }

    /**
     * function to validate filter options
     */
    public boolean validateFilterOptions()
    {
        return (isElementPresent("brandName") && isElementPresent("price"));
    }
}
