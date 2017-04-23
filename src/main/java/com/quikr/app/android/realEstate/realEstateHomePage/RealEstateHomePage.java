package com.quikr.app.android.realEstate.realEstateHomePage;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 8/10/15.
 */
public class RealEstateHomePage extends AppAndroidPageBase {


    public RealEstateHomePage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_REALESTATEHOME_DOM_FILE");

    /**
     * validate categories present in realEstate UI
     */

    public boolean validateHomeCAtegories()
    {
        return (isElementPresent("residential")&&isElementPresent("commercial")&&isElementPresent("agriculture"));
    }
    /**
     * after selecting categories from homepage validate spinner sub-cat
     */
    public List<String> findRelatedSubCat() {
        List<WebElement> subcat = Mapper.finds(domFile, "subCatspinner");
        List<String> list = new ArrayList<String>();
        for (WebElement e : subcat) {
            list.add(e.getText());
        }
        System.out.println(list);
        System.out.println(list.size());
        return  list;
    }
    /**
     * select residential
     */
    public void clickOnResidential()
    {
        Mapper.waitForElementToBeVisible(domFile, "residential");
        Mapper.find(domFile,"residential").click();
    }
    /**
     * click on spinner to select subCategories
     */

    public void clickOnSpinner()
    {
        Mapper.waitForElementToBeVisible(domFile,"realEstateHomeSpinner");
        Mapper.find(domFile,"realEstateHomeSpinner").click();
    }
    /**
     *  select commercial
     */
    public void clickOnCommercial()
    {
        Mapper.waitForElementToBeVisible(domFile,"commercial");
        Mapper.find(domFile,"commercial").click();
    }
    /**
     *  select Agriculture
     */
    public void clickOnAgriculture()
    {
        Mapper.waitForElementToBeVisible(domFile,"agriculture");
        Mapper.find(domFile,"agriculture").click();
    }
    /**
     *  select search icon from homepage
     */
    public void clickOnSearch()
    {
        Mapper.waitForElementToBeVisible(domFile, "search");
        Mapper.find(domFile,"search").click();
    }
    /**
     *  provide search key
     */
    public void SendSearchText(String search)
    {
        Mapper.waitForElementToBeVisible(domFile, "searchPage");
        Mapper.find(domFile,"searchPage").sendKeys(search);
    }

    /**
     * extract auto suggested results and verify
     */
    public List<String> findAutogestedResults() {
        Mapper.waitForElementToBeVisible(domFile,"autoSuggest");
        List<WebElement> subcat = Mapper.finds(domFile, "autoSuggest");
        List<String> list = new ArrayList<String>();
        for (WebElement e : subcat) {
            list.add(e.getText());
        }
        System.out.println(list);
        System.out.println(list.size());
        return  list;
    }

    /**
     * Select OPtions from auto suggest
     */

    public void selecOptionsFromAutoSuggest(int i)
    {
        Mapper.waitForElementToBeVisible(domFile, "autoSuggestSubCAt", 15);
        Mapper.finds(domFile, "autoSuggestSubCAt").get(i).click();
    }

    /**
     * get recently selected locality on search page
     */

    public String recentlySelectedLocality()
    {
        Mapper.waitForElementToBeVisible(domFile,"autoSuggestSubCAt");
        String locality=Mapper.finds(domFile,"autoSuggestSubCAt").get(0).getText();
        return locality;
    }

    public boolean isChpBannerPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "chpBanner");
        return isElementPresent("chpBanner");
    }

    public List<String> getPopularSearchCategories()
    {
//        return Mapper.finds(domFile, "autoSuggestSubCAt").get(i).getText();
        Mapper.find(domFile, "search").click();
        Mapper.hideKeyboard();
        List<WebElement> subcat = Mapper.finds(domFile, "autoSuggestSubCAt");
        List<String> popularCategoriesList = new ArrayList<String>();
        for (WebElement e : subcat) {
            popularCategoriesList.add(e.getText());
        }
        return popularCategoriesList;
    }

    public boolean isClockAndSearchIconPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "search");
        return isElementPresent("searchIcon");
    }

    public boolean isLocalityTextPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "autoSuggestLocality");
        return isElementPresent("autoSuggestLocality");
    }

    public boolean isProjectTextPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "autoSuggestProject");
        return isElementPresent("autoSuggestProject");
    }
}
