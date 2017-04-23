package com.quikr.app.ios.snb;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
* Created by Manvendra Singh on 5/10/15.
*/
public class SnbPage extends com.quikr.app.ios.AppiOSPageBase {
    public SnbPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_SNB_DOM_FILE");

    /**
     * function to click on filter button
     */
    public void clickOnFilterButtonOnSnbPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"filterButton");
        Mapper.find(domFile,"filterButton").click();
    }

    /**
     * function to click on Brand Name
     */
    public void clickOnBrandName()
    {
        Mapper.find(domFile,"brandName").click();
    }

    /**
     * function to select brand name
     */
    public void selectBrandNameOption()
    {
        Mapper.find(domFile,"brandNameOption").click();
    }

    /**
     * function to open ad on snb page for general category
     */
    public void openAdOnSnbPageForGeneralCategory(int i)
    {
        System.out.println(i);
        Mapper.waitForElementToBeVisible(domFile,"listOfAllUIATableCell");
        List<WebElement> allElements = Mapper.finds(domFile, "listOfAllUIATableCell");
        System.out.println(allElements.size());
        System.out.println(allElements);
        System.out.println(allElements.get(i).getText());
        System.out.println(allElements.get(i).findElements(By.className("UIAStaticText")).get(i).getAttribute("name"));
        allElements.get(i).findElements(By.className("UIAStaticText")).get(i).click();
    }



    /**
     * function to validate required name from title on snb page
     */
    public boolean validateRequiredNameFromAdTitleOnSnbPage(String str)
    {
        int count=0;
        for (int i = 4; i <= 6; i++)
        {
            if ((Mapper.findAndReplace(domFile, "titleNameOnSnbPage", new String[]{Integer.toString(i)}).getAttribute("name").contains(str)) )
            {
                System.out.println(Mapper.findAndReplace(domFile, "titleNameOnSnbPage", new String[]{Integer.toString(i)}).getAttribute("name"));
                count++ ;
                System.out.println(count);
                if(count==3)
                {
                    System.out.println("check");
                    System.out.println(count);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * function to validate filter on snb page
     */
    public boolean validateFilterOnSnbPage()
    {
        System.out.println(Mapper.find(domFile,"brandNameOption").getText());
       if(!isElementPresent("brandNameOption"))
       {
           Mapper.scroll("brandNameOption");
       }
        return (isElementPresent("brandNameOption"));
    }

    /**
     * function to click on online user ad
     */
    public void clickOnOnline()
    {
        Mapper.find(domFile,"online").click();
    }

    /**
     * function to click on price button o snb page
     */
    public void selectPriceButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"priceButton");
        Mapper.find(domFile,"priceButton").click();
        Mapper.waitForElementToBeVisible(domFile,"sortDownArrow");
    }

    /**
     * function to perform sorting in ascending order
     */
    public boolean validateAscendingSortOnSnbPage()
    {
        List<String> list = new ArrayList<String>();
        Mapper.waitForElementToBeVisible(domFile,"cellList");
        List<WebElement> allElement = Mapper.finds(domFile, "cellList");
        System.out.println(allElement);
        System.out.println(allElement.size());
        int NoOfCell = allElement.size();
        System.out.println(NoOfCell);
        for(int x=2;x<NoOfCell;x++)
        {
            List<WebElement> allElements = allElement.get(x).findElements(By.className("UIAStaticText"));

            System.out.println(allElements.size());
            for (WebElement e : allElements)
            {
                list.add(e.getAttribute("name"));
                System.out.println(e.getAttribute("name"));

            }
        }
        System.out.println(list.size());
        List<String> PriceArr=new ArrayList<String>();
        for(int y=0;y<list.size();y++)
        {
            if(list.get(y).trim().startsWith("₹"))
            {

                PriceArr.add(list.get(y).replaceAll("\\D",""));
            }
        }
        Iterator itr=PriceArr.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());

        }
        List<Integer>StorePriceOriginal=new ArrayList<Integer>();
        List<Integer>StorePriceNew=new ArrayList<Integer>();
        for(int i=0;i<PriceArr.size();i++)
        {
            int Value=Integer.parseInt(PriceArr.get(i));
            StorePriceOriginal.add(Value);
            StorePriceNew.add(Value);
        }

        System.out.println(StorePriceOriginal);
        Collections.sort(StorePriceNew);
        System.out.println(StorePriceNew);
        for(int i=0; i<StorePriceOriginal.size(); i++){
            if (!(StorePriceOriginal.get(i)).equals(StorePriceNew.get(i)))
            {
                return false;
               }
            }
        return true;
    }

    /**
     * function to click on ad type in filter option
     */
    public void clickOnAdTypeInFilterOption()
    {
        Mapper.waitForElementToBeVisible(domFile,"adType");
        Mapper.find(domFile,"adType").click();
    }

    /**
     * function to select want ad
     */
    public void selectWantButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"wantButton");
        Mapper.find(domFile, "wantButton").click();
    }

    /**
     * function to select check box of current selected city in multicity popup
     */
    public void selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup()
    {
        Mapper.waitForElementToBeVisible(domFile,"cancelButton");
        Mapper.find(domFile,"selectCheckboxForCurrentSelectedCity").click();
        Mapper.waitForElementToBeInvisible(domFile,"cancelButton");
    }

    /**
     * function to click on cancel in multicity popup
     */
    public void selectCancelButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"cancelButton");
        Mapper.find(domFile,"cancelButton").click();
    }

    /**
     * function  to click on chat button on snb page
     */
    public void clickOnChatButtonOnSnbPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"chatButtonOnSnbPage");
        Mapper.find(domFile,"chatButtonOnSnbPage").click();
    }

    /**
     * function to validate sort options for mobile category or electronics category
     */
    public boolean validateSortOptionsForMobileOrElectronicsCategory()
    {
        return (isElementPresent("recentlyPostedTab") && isElementPresent("nearestTab") && isElementPresent("lowestPriceTab") && isElementPresent("highestTab"));
    }

    /**
     * function to validate snb screen
     */
    public boolean validateSnbPageForSortAndFilterButton()
    {
        return (isElementPresent("sortButton") && isElementPresent("filterButton"));
    }
}
