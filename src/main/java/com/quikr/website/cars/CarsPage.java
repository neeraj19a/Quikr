package com.quikr.website.cars;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra on 28/7/15.
 */
public class CarsPage extends PageBase {

   // MyQuikrPage myQuikrPage = new MyQuikrPage();

    public CarsPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("CARS_DOM_FILE");

    public void NavigateToFirstAdPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "FirstAd");
        Mapper.find(domFile,"FirstAd").click();
    }

    public void SearchInCars(String searchItem)
    {
        Mapper.waitForElementToBeVisible(domFile, "searchTextField");
        Mapper.find(domFile, "searchTextField").clear();
        Mapper.find(domFile, "searchTextField").click();
        Mapper.find(domFile, "searchTextField").sendKeys(searchItem);
        Mapper.waitForElementToBeVisible(domFile, "searchButton");
        Mapper.find(domFile,"searchButton").click();
    }

    public void clickFirstHeartIcon()
    {
        Mapper.find(domFile,"firstHeartIcon").click();
    }

    public void clickSecondHeartIcon()
    {
        Mapper.find(domFile,"secondHeartIcon").click();
    }

    public void clickFirstCompareCheckBox()
    {
        Mapper.find(domFile, "compareCheckboxFirst").click();

    }

    public void clickSecondCompareCheckBox()
    {
        Mapper.find(domFile, "compareCheckboxSecond").click();

    }

    public void compare()
    {
        Mapper.find(domFile, "compareButton").click();
    }

    public boolean ValidateComparePage()
    {
        if (Mapper.find(domFile,"comparePageValidator").isDisplayed()) {
            return true;
        }
        else
            return false;
    }

    //Extracts the title of the ad being saved and asserts against the one found in My Shortlists
    public boolean ValidateAdSavingSNBPage()
    {
        String adTitle = Mapper.find(domFile, "titleFirstAd").getText();
        clickFirstHeartIcon();
        NavigateToMyShortlists();
        Mapper.waitForElementToBeVisible(domFile, "titleFirstAdShortlistPage");
        String adTitleShortListPage = Mapper.find(domFile, "titleFirstAdShortlistPage").getText();
        if (adTitle.equals(adTitleShortListPage))
        {
            return true;
        }else
        {
            return false;
        }

    }

    public boolean ValidateAdSavingVap()
    {

        return false;
    }

    public void SelectMoreFilters()
    {
        Mapper.find(domFile,"moreDropDown").click();
        Mapper.find(domFile,"kmDrivenDropDown").click();
        Mapper.waitForElementToBeVisible(domFile, "below5000Kms");
        Mapper.find(domFile,"below5000Kms").click();
        Mapper.find(domFile, "postedByIndividual");
        Mapper.find(domFile,"postedByIndividual").click();
        Mapper.find(domFile,"applyMoreFilters").click();
        Mapper.waitForElementToBeClickable(domFile, "titleFirstAd");
    }

    //3,400 kms
    public boolean ValidateMoreFilters()
    {
        Mapper.waitForElementToBeVisible(domFile, "kmsListed");
        List<WebElement> kmsString = Mapper.finds(domFile, "kmsListed");
        String[] kmsStr = new String[kmsString.size()];
        int[] kmsInt = new int[kmsString.size()];
        boolean retValFinal, retValOwnerType = false;
        boolean retValKms = false;

        for (int i=0;i<kmsString.size();i++)
        {
            kmsStr[i] = kmsString.get(i).getText();
            kmsInt[i] = Integer.parseInt(kmsStr[i].replace(",", "").replace(" kms", ""));
//            System.out.println(kmsInt[i]);
            if (kmsInt[i]<=5000)
            {
                retValKms = true;
            }
            else
            {
                retValKms = false;
            }
        }
        NavigateToFirstAdPage();
        Mapper.waitForElementToBeVisible(domFile, "OwnerType");
        String ownerType = Mapper.find(domFile, "OwnerType").getText();
        if (ownerType.equalsIgnoreCase("Individual"))
        {
            retValOwnerType = true;

        }
        else
        {
            retValOwnerType = false;
        }
        if (retValKms == true && retValOwnerType == true)
        {
            retValFinal =true;
        }
        else
        {
            retValFinal =false;
        }
        return retValFinal;

    }

    public boolean ValidateSorting()
    {
        Mapper.find(domFile, "sortingDropDown").click();
        Mapper.find(domFile, "sortByLowestPrice").click();


        List<WebElement> carPrices = Mapper.finds(domFile, "carPrices");
        String[] carPricesStr = new String[carPrices.size()];
        int[] carPricesInt = new int[carPrices.size()];
        boolean retVal1 = false;
        boolean retVal2 =false;
        boolean retFinal = false;
        for (int i =0; i<carPrices.size(); i++)
        {
            carPricesStr[i] = carPrices.get(i).getText().replace(",","");
            carPricesInt[i] = Integer.parseInt(carPricesStr[i]);
        }
        for (int i=0;i<carPricesInt.length-1;i++)
        {
            if (carPricesInt[i]<carPricesInt[i+1])
            {
                retVal1 = true;
            }
        }
// Commented out as the sort by highest price feature has been removed.
//        Mapper.find(domFile, "sortingDropDown").click();
//        Mapper.find(domFile, "sortByHighestPrice").click();
//        List<WebElement> carPricesAgain = Mapper.finds(domFile, "carPrices");
//        String[] carPricesStrAgain = new String[carPricesAgain.size()];
//        int[] carPricesIntAgain = new int[carPricesAgain.size()];
//
//        for (int i =0; i<carPrices.size(); i++)
//        {
//            carPricesStrAgain[i] = carPricesAgain.get(i).getText().replace(",","");
//            carPricesIntAgain[i] = Integer.parseInt(carPricesStrAgain[i]);
//        }
//        for (int i=0;i<carPricesIntAgain.length-1;i++)
//        {
//            if (carPricesIntAgain[i]<carPricesIntAgain[i+1])
//            {
//                retVal2 = true;
//            }
//        }

        if(retVal1 == true )
//        if (retVal1 == true && retVal2 == true)
        {
            retFinal = true;
        }
        return retFinal;
    }

    public void clickFirstHeartIconHomePage()
    {
        Mapper.waitForElementToBeVisible(domFile, "homePageFirstHeartIcon");
        List<WebElement> elementLsit = Mapper.finds(domFile, "homePageFirstHeartIcon");

        for(WebElement element : elementLsit)
        {
            if(element.isDisplayed())
            {
                element.click();
                break;
            }
        }
    }

    public String GetHomePageFirstAdTitle()
    {
        Mapper.waitForElementToBeVisible(domFile, "homePageFirstAdTitle");
        return Mapper.find(domFile, "homePageFirstAdTitle").getText();


    }

    public boolean ValidateAdSavingHomePage(String adTitle)
    {
        NavigateToMyShortlists();
        Mapper.waitForElementToBeVisible(domFile, "titleFirstAdShortlistPage");
        String adTitleShortListPage = Mapper.find(domFile, "titleFirstAdShortlistPage").getText();
        if (adTitle.equalsIgnoreCase(adTitleShortListPage))
        {
            return true;
        }else
        {
            return false;
        }

    }

    public void closeCitySelectionPopUp()
    {
        Mapper.find(domFile, "cityPopUpCloseBtn").click();
    }

    public void closeModalCityPopUp()
    {
        try
        {
            Mapper.find(domFile, "cityPopUpCloseBtnBeforeCarsHomePage").click();
            Thread.sleep(3000);
        } catch (Exception e)
        {

        }
    }

    public boolean ValidateRecentlyPostedAds()
    {
        List<WebElement> recentlyPostedAds = Mapper.finds(domFile, "recentlyPostedAds");
        if (recentlyPostedAds.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean ValidateMostPopularAds()
    {
        List<WebElement> mostPopularAds = Mapper.finds(domFile, "mostPopularAds");
        if (mostPopularAds.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void ClickAdForOnlineUserSnbPage()
    {
        Mapper.find(domFile,"firstAdWithOnlineUser").click();
        Mapper.waitForElementToBeVisible(domFile, "sellerLabelVap");
    }

    public void ClickChatRecommendedAds()
    {
        int i;
        List<WebElement> elements = Mapper.finds(domFile, "chatButtonRecommendedAds");
        for (i = 0; i < elements.size(); i++)
        {
            if (elements.get(i).isDisplayed())
            {
                elements.get(i).click();

                break;
            }
        }
    }

    public boolean ClickAndVerifyMSPLink()
    {
        Mapper.find(domFile, "mspLink").click();
        return  isElementPresent("mspHeaderText");
    }

    public void SelectPopularCityFromGeoLocationCarsPage(String cityToSelect)
    {
            Mapper.find(domFile, "geoLocationLink").click();
            Mapper.waitForElementToBeVisible(domFile, "citySearchTextBox");
            Mapper.find(domFile, "citySearchTextBox").sendKeys(cityToSelect);

            List<WebElement> elementList = Mapper.finds(domFile, "citySearchResultFromCitySelectDropdown");

            for (WebElement element : elementList)
            {
                if (element.getText().equalsIgnoreCase(cityToSelect)) {
                    element.click();
                    break;
                }
            }
    }

    public boolean ValidateQuikrInspected()
    {
        boolean retVal = false;
        Mapper.find(domFile, "quikrInspectedCheckbox").click();
        Mapper.waitForElementToBeVisible(domFile, "quikrInspectedTextsSnbPage");
        List<WebElement> inspectedTags = Mapper.finds(domFile, "quikrInspectedTextsSnbPage");
        for (int i=0;i<inspectedTags.size();i++)
        {
            if (inspectedTags.get(i).getText().equals("Inspected"))
            {
                retVal = true;
            }
            else
            {
                retVal = false;
            }
        }
        return retVal;
    }

    public boolean ValidateTrendingTagsSnbPage()
    {
        boolean retVal = false;
        List<WebElement> trendingTags = Mapper.finds(domFile, "trendingtagsSnbPage");
        if (trendingTags.size()>0)
        {
            retVal = true;
        }else
        {
            retVal = false;
        }
        return retVal;
    }

    public boolean ValidateAutoSuggestHomePage(String searchItem)
    {
        boolean retVal = false;
        try {
            Mapper.find(domFile, "searchTextField").clear();
            Mapper.find(domFile, "searchTextField").sendKeys(searchItem);
            Thread.sleep(5000);
            Mapper.waitForElementToBeVisible(domFile, "autoSuggestListHomePage");
            List<WebElement> autoSuggestedResults = Mapper.finds(domFile, "autoSuggestListHomePage");
            if (autoSuggestedResults.size() > 0) {
                retVal = true;
            } else {
                retVal = false;
            }
        }
        catch (Exception e)
        {
        }
        return retVal;
    }

    public boolean ValidateAutoSuggestSnbPage(String searchItem)
    {
        boolean retVal = false;
        try {
            Mapper.find(domFile, "searchTextFieldSnbPage").clear();
            Mapper.find(domFile, "searchTextFieldSnbPage").sendKeys(searchItem);
            Thread.sleep(5000);
            Mapper.waitForElementToBeVisible(domFile, "autoSuggestListSnbPage");
            List<WebElement> autoSuggestedResults = Mapper.finds(domFile, "autoSuggestListSnbPage");
            if (autoSuggestedResults.size() > 0) {
                retVal = true;
            } else {
                retVal = false;
            }
        }
        catch (Exception e)
        {
        }
        return retVal;

    }

    public void WaitForSearchBoxVisibility()
    {
        Mapper.waitForElementToBeClickable(domFile, "searchTextField");
    }

    public void ClickHeartIconVap()
    {
        List<WebElement> elementList = Mapper.finds(domFile, "homePageFirstHeartIcon");
        for (int i=0; i<elementList.size(); i++)
        {
            if (elementList.get(i).isDisplayed())
            {
                elementList.get(i).click();
                break;
            }
        }
    }

    public void ClickHeartIconRecommendedAds()
    {
        Mapper.find(domFile, "RecommendedAdsFirstHeartIcon").click();
    }

    public String GetAdTitleVap()
    {
        return Mapper.find(domFile, "AdTitleVap").getText();
    }

    public String GetFirstAdTitleRecommendedAds()
    {
        return Mapper.find(domFile, "FirstAdTitleRecommendedAds").getText();
    }

    public void deleteSavedCarsAds()
    {
        NavigateToMyShortlists();
        WebElement elm = Mapper.find(domFile, "SubtitleSavedAdtext");
        if (elm.getText().equals("There are no saved ads"))
        {
            navigateTo().back();

        }else if (elm.getText().contains("saved Cars"))
        {

            Mapper.waitForElementToBeVisible(domFile, "RemoveAllSaveCarLink");
            Mapper.find(domFile, "RemoveAllSaveCarLink").click();
            //Mapper.waitForElementToBeVisible(domFile, "ContinueButtonRemoveAllAds");
            Mapper.find(domFile, "ContinueButtonRemoveAllAds").click();
            navigateTo().back();
        }
    }

    public void clickUserIconCarsHomepage()
    {
        Mapper.waitForElementToBeVisible(domFile, "userIconCarsHomepage");
        Mapper.find(domFile, "userIconCarsHomepage").click();
    }

    public void clickLoginLinkCarsHomepage()
    {
        Mapper.waitForElementToBeVisible(domFile, "loginLinkCarsHomepage");
        Mapper.find(domFile, "loginLinkCarsHomepage").click();
    }

    public boolean loginFromCarsHomepage(String email, String password)
    {
        Mapper.find(domFile,"emailId").sendKeys(email);
        Mapper.find(domFile,"password").sendKeys(password);
        Mapper.find(domFile,"submitButton").click();
        if(isElementPresent("MyDashboard"))
            return true;
        return false;
    }

    public void NavigateToMyShortlists()
    {
        Mapper.find(domFile,"userIconHomepage").click();
        Mapper.waitForElementToBeVisible(domFile, "myShortLists");
        Mapper.find(domFile, "myShortLists").click();
    }

}
