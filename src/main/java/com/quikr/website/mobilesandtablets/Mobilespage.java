package com.quikr.website.mobilesandtablets;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra singh on 29/7/15.
 */
public class Mobilespage extends PageBase {

    public Mobilespage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("MOBILES_DOM_FILE");
    //click on mobilesPhones
    public String selectMobilePhones()
    {
        return Mapper.find(domFile,"mobilesPhones").getAttribute("href");
    }

    public Mobilespage selectTablets()
    {
        Mapper.waitForElementToBeVisible(domFile, "Tablets");
        Mapper.waitForElementToBeClickable(domFile, "Tablets");
        Mapper.find(domFile,"Tablets").click();
        Mapper.waitForElementToBeInvisible(domFile,"Tablets",15);
        if(Mapper.find(domFile,"Tablets").isDisplayed()){
            Mapper.waitForElementToBeVisible(domFile, "Tablets");
            Mapper.waitForElementToBeClickable(domFile, "Tablets");
            Mapper.find(domFile,"Tablets").click();

        }
        Mapper.waitForElementToBeInvisible(domFile,"Tablets",15);
        if(Mapper.find(domFile,"Tablets").isDisplayed()){
            Mapper.waitForElementToBeVisible(domFile, "Tablets");
            Mapper.waitForElementToBeClickable(domFile, "Tablets");
            Mapper.find(domFile,"Tablets").click();

        }
        return this;
    }

    //validate if filters are present
    public boolean isFiltersvisible()
    {
        return isElementPresent("filters");
    }

    /**
     * Function for selecting Quikr certified phones option
     */
    public void selectExchangePhoneOption()
    {
        Mapper.find(domFile, "exchangePhoneOption").click();
    }

    /**
     * Function for selecting Exchange phone option
     */
    public void selectQuikrCertifiedOption()
    {
        Mapper.find(domFile, "certifiedPhoneOption").click();
    }

    public void selectMobilewithBrandFilter(String brandName)
    {
        Mapper.waitForElementToBeVisible(domFile,"brandSearchTextbox");
        Mapper.find(domFile, "brandSearchTextbox").click();
        Mapper.find(domFile, "brandSearchTextbox").clear();
        Mapper.find(domFile, "brandSearchTextbox").sendKeys(brandName);
        Mapper.waitForElementToBeVisible(domFile,"firstOptionInBrandSearch");
        Mapper.find(domFile, "firstOptionInBrandSearch").click();
    }

    public void clickSeeAllUsedMobiles(){
        if (Mapper.waitForElementToBeVisible(domFile,"seeAllUsedMobiles")==true){
            Mapper.find(domFile,"seeAllUsedMobiles").click();
        }else{
            logger.info("See All mobile link not available.");
        }
    }
}
