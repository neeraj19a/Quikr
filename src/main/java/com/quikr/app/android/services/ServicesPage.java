package com.quikr.app.android.services;

import com.quikr.app.android.AppAndroidPageBase;
import com.quikr.app.android.Constants.MobileConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by rohanbajaj on 27/08/15.
 */
public class ServicesPage extends AppAndroidPageBase
{
    public ServicesPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_SERVICES_DOM_FILE");
    int flag;

    public boolean validateSubcategories()
    {
        List<WebElement> subcategory = Mapper.finds(domFile, "servicesSubCategory");
        List<String> list = new ArrayList<String>();
        for (WebElement e : subcategory)
        {
            list.add(e.getText());
            System.out.println("Subcategory:" + e.getText());
        }
        for (int i = 0; i < list.size(); i++)
        {

            for (int j = 0; j < MobileConstants.homeServices.size(); j++)
            {
                if (list.get(i).equals(MobileConstants.homeServices.get(j)))
                {
                    flag = 1;
                    System.out.println(list.get(i) + MobileConstants.homeServices.get(j));
                    break;
                }

            }
            if (flag == 1)
            {
                flag = 0;
                continue;
            }
            else
            {
                return false;
            }
        }
        return  true;
    }

    public boolean isMoreButtonDisplayed()
    {
        return isElementPresent("moreButton");
    }

    public boolean isPopularAdsDisplayed()
    {
        return isElementPresent("popularAds");
    }

    public void clickOnHomeServices()
    {
        Mapper.waitForElementToBeVisible(domFile, "homeServicesCategories");
        Mapper.find(domFile, "homeServicesCategories").click();
    }

    public void clickOnOtherServices()
    {
        Mapper.waitForElementToBeVisible(domFile, "otherServicesCategories");
        Mapper.find(domFile, "otherServicesCategories").click();
    }

    public boolean validateOtherSubcategories()
    {
        List<WebElement> subcategory = Mapper.finds(domFile, "servicesSubCategory");
        List<String> list = new ArrayList<String>();
        for (WebElement e : subcategory)
        {
            list.add(e.getText());
            System.out.println("Subcategory:" + e.getText());
        }
        for (int i = 0; i < list.size(); i++)
        {

            for (int j = 0; j < MobileConstants.otherServices.size(); j++)
            {
                if (list.get(i).equals(MobileConstants.otherServices.get(j)))
                {
                    flag = 1;
                    System.out.println(list.get(i) + MobileConstants.otherServices.get(j));
                    break;
                }

            }
            if (flag == 1)
            {
                flag = 0;
                continue;
            }
            else
            {
                return false;
            }
        }
        return  true;
    }

    public boolean isSearchBarPresent()
    {
        Mapper.waitForElementToBeVisible(domFile,"servicesSearchBar");
        return isElementPresent("servicesSearchBar");
    }

    public boolean isHomeServicesPresent()
    {
        return isElementPresent("homeServicesCategories");
    }

    public boolean isOtherServicesPresent()
    {
        return isElementPresent("otherServicesCategories");
    }

    public void clickOnSearchBar()
    {
        Mapper.find(domFile,"servicesSearchBar").click();
    }

    public boolean isServicesLocalityDropdownPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "servicesLocalityDropdown");
        return isElementPresent("servicesLocalityDropdown");
    }

    public boolean isServicesCategorySearchBarPresent()
    {
        return isElementPresent("servicesCategorySearchBar");
    }

    public boolean validateInstaconnectSubcategories()
    {
        Mapper.waitForElementToBeVisible(domFile, "servicesInstaconnectSubcategories");
        List<WebElement> subcategory = Mapper.finds(domFile, "servicesInstaconnectSubcategories");
        List<String> list = new ArrayList<String>();
        for (WebElement e : subcategory)
        {
            list.add(e.getText());
            System.out.println("Subcategory:" + e.getText());
        }
        for (int i = 0; i < list.size(); i++)
        {

            for (int j = 0; j < MobileConstants.ServicesInstaconnectSubcategories.size(); j++)
            {
                if (list.get(i).equals(MobileConstants.ServicesInstaconnectSubcategories.get(j)))
                {
                    flag = 1;
                    System.out.println(list.get(i) + MobileConstants.ServicesInstaconnectSubcategories.get(j));
                    break;
                }

            }
            if (flag == 1)
            {
                flag = 0;
                continue;
            }
            else
            {
                return false;
            }
        }
        return  true;
    }

    public boolean isInstaconnectInterstitialPresent()
    {
        return isElementPresent("instaConnectInterstitial");
    }
    /**
     * for instaconnect select user is not a service provider
     */
    public void selectNotAServiceProvider()
    {
        Mapper.waitForElementToBeVisible(domFile,"notAserviceProvider");
        Mapper.find(domFile,"notAserviceProvider").click();
    }

    public boolean isSkipOptionPresent()
    {
        return isElementPresent("skipOption");
    }

    public boolean isConnectNowButtonPresent()
    {
        return isElementPresent("connectNowButton");
    }

    public boolean verifyTappingOnBookNow()
    {
        Mapper.waitForElementToBeVisible(domFile, "connectNowButton");
        Mapper.find(domFile, "connectNowButton").click();
        Mapper.waitForElementToBeVisible(domFile, "quikrHelprLogo");
        return isElementPresent("quikrHelprLogo");
    }

    public boolean verifyTappingOnConnectNow()
    {
        Mapper.waitForElementToBeVisible(domFile, "connectNowButton");
        Mapper.find(domFile, "connectNowButton").click();
        Mapper.waitForElementToBeVisible(domFile, "quikrServicesWebview");
        return isElementPresent("quikrServicesWebview");
    }

    public void clickOnTalkNow()
    {
        Mapper.waitForElementToBeVisible(domFile, "connectNowButton");
        Mapper.find(domFile, "connectNowButton").click();
        if(isElementPresent("noButton"))
            Mapper.find(domFile, "noButton").click();
    }

    public boolean verifyInstaconnectAttributesPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "instaconnectAttributes");
        return isElementPresent("instaconnectAttributes");
    }

    public boolean verifyInstaconnectCallingScreen()
    {
        Mapper.find(domFile, "instaconnectNowButton").click();
        Mapper.waitForElementToBeVisible(domFile, "verifyByCallButton");
        return isElementPresent("verifyByCallButton");
    }

    public boolean isBookNowCardPresent()
    {
        Mapper.waitForElementToBeVisible(domFile,"bookNowCard");
        return isElementPresent("bookNowCard");
    }

    public boolean isEvaluateAndChooseCardPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "evaluateAndChooseCard");
        return isElementPresent("evaluateAndChooseCard");
    }

    public boolean isBrowseAdsCardPresent()
    {
        Mapper.scroll("Browse Ads");
        return isElementPresent("browseAdsCard");
    }

    public boolean validateBookNowCategory(String category)
    {
        Mapper.waitForElementToBeVisible(domFile, category);
        if(!isElementPresent(category))
        {
            Mapper.waitForElementToBeVisible(domFile, "showAllOption");
            if (isElementPresent("showAllOption"))
                Mapper.find(domFile, "showAllOption").click();
        }
        Mapper.find(domFile, category).click();
        Mapper.waitForElementToBeVisible(domFile, "continueButton");
        Mapper.find(domFile, "continueButton").click();
//        if(!category.equalsIgnoreCase("carCare"))
//        {
//            Mapper.waitForElementToBeVisible(domFile, "quikrHelprLogo");
//            return isElementPresent("quikrHelprLogo");
//        }
//        Mapper.waitForElementToBeVisible(domFile, "quikrHelprLogo");
//        return (isElementPresent("quikrHelprLogo"));
        Mapper.waitForElementToBeVisible(domFile, "quikrServicesWebview");
        return isElementPresent("quikrServicesWebview");
    }

    public ArrayList<String> getBookNowCategories()
    {
        ArrayList<String> bookNowCategories= new ArrayList<String>();
        int categoryCount = Mapper.finds(domFile, "bookNowCategories").size();
        System.out.println("Size:" + categoryCount);
        for(int i=0;i<categoryCount;i++)
            bookNowCategories.add(i, Mapper.finds(domFile, "bookNowCategories").get(i).getText());
//        for(String category : bookNowCategories)
//        {
//            System.out.println(category);
//        }
        return bookNowCategories;
    }

    public boolean validateEvaluateAndChooseCategory(String category)
    {
        Mapper.waitForElementToBeVisible(domFile, category);
        if(!isElementPresent(category)) {
            Mapper.waitForElementToBeVisible(domFile, "showAllOption");
            if (isElementPresent("showAllOption"))
                Mapper.find(domFile, "showAllOption").click();
        }
        Mapper.find(domFile, category).click();
        Mapper.waitForElementToBeVisible(domFile, "continueButton");
        Mapper.find(domFile, "continueButton").click();
        if(!category.equalsIgnoreCase("carCare")) {
            Mapper.waitForElementToBeVisible(domFile, "quikrHelprLogo");
            return isElementPresent("quikrHelprLogo");
        }
        Mapper.waitForElementToBeVisible(domFile, "bookNowButton");
        return isElementPresent("bookNowButton");
    }

    public void clickOnContinueButton()
    {
        Mapper.waitForElementToBeVisible(domFile, "continueButton");
        Mapper.find(domFile, "continueButton").click();
    }

    public void clickOnBrowseAds()
    {
        Mapper.waitForElementToBeVisible(domFile, "showAllOption");
        Mapper.find(domFile, "showAllOption").click();
    }

    public void searchFromServicesChp()
    {
        Mapper.waitForElementToBeVisible(domFile, "servicesSearchBar");
        Mapper.find(domFile, "servicesSearchBar").click();
        Mapper.find(domFile, "servicesCategorySearchBar").sendKeys("Cook");
        Mapper.find(domFile, "submitSearch").click();
    }
    public void searchAdsOnServicesPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "servicesCategorySearchBar");
        Mapper.find(domFile, "servicesCategorySearchBar").sendKeys("Cook");
        Mapper.find(domFile, "submitSearch").click();
    }
    public void selectSubcatAfterSearch()
    {
        Mapper.waitForElementToBeVisible(domFile, "servicesLocalityDropdown");
        Mapper.find(domFile,"servicesLocalityDropdown").click();
    }
    public void clickOnViewAds()
    {
        Mapper.waitForElementToBeVisible(domFile,"viewAdsButton");
        Mapper.find(domFile,"viewAdsButton").click();
    }

    public void clickOnBookNowCategories()
    {
        Mapper.waitForElementToBeVisible(domFile,"bookNowCategories");
        Mapper.find(domFile,"bookNowCategories").click();
    }







}
