package com.quikr.website.service.serviceHomepage;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Adil on 1/10/15.
 */
public class ServiceHomepage extends PageBase {

    public ServiceHomepage(RemoteWebDriver driver) {
        super(domFile, driver);
    }


    private static final String domFile = getProperties().get("SERVICE_HOMEPAGE_DOM_FILE");

//    public void searchOnServicesHomepage(String searchService, String locality) {
//        Mapper.waitForElementToBeVisible(domFile, "ServicesSearchTextboxHomepage");
//        Mapper.find(domFile, "ServicesSearchTextboxHomepage").sendKeys(searchService);
//        Mapper.find(domFile, "ServicesLocalityTextboxHomepage").sendKeys(locality);
//        Mapper.find(domFile, "SearchButtonHomepage").click();
//    }

    public void createAlert() {
        try
        {
            Mapper.find(domFile, "CreateAlertCategory").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertCategoryService");
            Mapper.find(domFile, "CreateAlertCategoryService").click();
            Mapper.find(domFile, "CreateAlertSubCategory").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertFirstSubCategory");
            Mapper.find(domFile, "CreateAlertFirstSubCategory").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertSelectType");
            Mapper.find(domFile, "CreateAlertSelectType").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertSelectTypeAll");
            Mapper.find(domFile, "CreateAlertSelectTypeAll").click();
            Thread.sleep(2000);
            Mapper.find(domFile, "CreateAlertLocation").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertFirstLocation");
            Mapper.find(domFile, "CreateAlertFirstLocation").click();
            Mapper.find(domFile, "CreateAlertEmail").clear();
            Mapper.find(domFile, "CreateAlertEmail").sendKeys("automation.quikr@gmail.com");
            Mapper.find(domFile, "CreateAlertPhNo").clear();
            Mapper.find(domFile, "CreateAlertPhNo").sendKeys("9966998855");
            Mapper.find(domFile, "CreateAlertSubmit").click();
        } catch (Exception e) {}
    }

    public void clickSubmitButton()
    {
        Mapper.find(domFile, "SearchButton").click();

    }

    public void searchFromServiceHomepage(String Searchterm, String Locality)
    {
        selectFirstOptionSearchDropdown(Searchterm);
        selectFirstOptionLocalityDropdown(Locality);
//        Mapper.find(domFile, "ServiceHomepageSearchTextBox").sendKeys(Searchterm);
//        Mapper.find(domFile, "ServiceHomepageLocalityTextBox").sendKeys(Locality);
        clickSubmitButton();
    }

    public boolean checkUserProfileLink()
    {
        boolean finalVal = false;

        if (Mapper.find(domFile, "UserProfileLinkHomepage").isDisplayed())
        {
            Mapper.find(domFile, "UserProfileLinkHomepage").click();

            if (Mapper.find(domFile, "UserProfileDropdownBox").isDisplayed())
            {
                finalVal=true;
            }
            logger.info("User profile dropdown box was not displayed.");
        }
        logger.info("User profile link at homepage was not displayed.");
        return finalVal;
    }

    public boolean checkLanguageSelectLink()
    {
        boolean finalVal = false;

        if (Mapper.find(domFile, "LanguageLinkHomepage").isDisplayed())
        {
            Mapper.find(domFile, "LanguageLinkHomepage").click();

            if (Mapper.find(domFile, "LanguageDropdownBox").isDisplayed())
            {
                finalVal=true;
            }
            else{
                logger.info("Language dropdown box was not displayed.");
            }
        }else
        {
            logger.info("Language link in homepage was not displayed.");
        }
        return finalVal;
    }

    public boolean checkPostAdBtn()
    {
        if (Mapper.find(domFile, "postFreeAdButton").isDisplayed())
        {
            return true;
        }
        else
        {
            logger.info("Post ad button is not displayed.");
            return false;
        }
    }

    public boolean checkSearchTextbox()
    {
        boolean finalVal = false;

        if (Mapper.find(domFile, "ServiceHomepageSearchTextBox").isDisplayed())
        {
            finalVal=true;
            logger.info("Service homepage search text box is displayed.");
        }
        else
        {
            logger.info("Service homepage search textbox is not displayed.");
        }

        if (Mapper.find(domFile,"ServiceHomepageLocalityTextBox").isDisplayed())
        {
            finalVal=true;
            logger.info("Service homepage locality text box is displayed.");
        }
        else
        {
            logger.info("Service homepage locality text box is not displayed.");
        }
        return finalVal;
    }

    public boolean checkHomeServices()
    {
        boolean finalVal=false;
        WebElement homeServices = Mapper.find(domFile, "homeServices");

        if (homeServices.isDisplayed() && homeServices.getText()=="Home Services")
        {
            finalVal=true;
        }
        else
        {
            logger.info("Home services is not displayed or the text is not as <Home Services>.");
        }

        if (Mapper.find(domFile, "homeServicesCategoryContainer").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            logger.info("Home services category container is not being displayed.");
        }

        List<WebElement> homeServicesCategories = Mapper.finds(domFile, "homeServicesCategories");

        for (int i=0;i<homeServicesCategories.size();i++)
        {
            if (homeServicesCategories.get(i).isDisplayed())
            {
                finalVal=true;
            }
            else
            {
                logger.info("Home services category at index "+i+" is not being displayed.");
            }
        }
        return finalVal;
    }

    public boolean checkOtherCategories()
    {
        boolean finalVal=false;

        if (Mapper.find(domFile,"otherCategories").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            logger.info("Other categories not being displayed.");
        }
        return finalVal;
    }

    public boolean checkConnectNowBtn()
    {
        boolean val=false;

        if (Mapper.find(domFile,"connectNowButton").isDisplayed())
        {
            val=true;
        }
        else
        {
            logger.info("Connect now button not displayed.");
        }
        return val;
    }

    public boolean verifyConnectNow()
    {
        boolean finalVal=false;
        Mapper.waitForElementToBeVisible(domFile, "connectNowButton");

        if (Mapper.find(domFile,"connectNowButton").getText().contains("Connect Now"))
        {
            finalVal=true;
        }

        Mapper.find(domFile,"connectNowButton").click();
        Mapper.waitForElementToBeVisible(domFile,"connectNowContainerTabTwo");

        if (Mapper.find(domFile, "connectNowContainerTabTwo").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            logger.info("The tab not being displayed that occurs after clicking <connect now>");
        }
        return finalVal;
    }

    public void selectFirstOptionSearchDropdown(String searchService)
    {
        Mapper.find(domFile, "ServiceHomepageSearchTextBox").sendKeys(searchService);
        Mapper.waitForElementToBeVisible(domFile, "firstOptionServiceSearchDropdown");
        Mapper.find(domFile,"firstOptionServiceSearchDropdown").click();
    }

    public void selectFirstOptionLocalityDropdown(String searchLocality)
    {
        Mapper.find(domFile, "ServicesLocalityTextboxHomepage").sendKeys(searchLocality);
        Mapper.waitForElementToBeVisible(domFile, "firstOptionLocalitySearchDropdown");
        Mapper.find(domFile,"firstOptionLocalitySearchDropdown").click();
    }

    public void closeConnectNowOverlay()
    {
        Mapper.waitForElementToBeVisible(domFile,"crossButtonConnectNowOverlay");
        Mapper.find(domFile,"crossButtonConnectNowOverlay").click();
    }

    public void closeQuikrConnectPopUp()
    {
        if(Mapper.waitForElementToBeVisible(domFile,"crossButtonQuikrConnectPopUp")==true)
        {
            Mapper.find(domFile, "crossButtonQuikrConnectPopUp").click();
        }
    }


    public boolean checkCountHomeServicesCategories()
    {
        boolean finalVal = false;
        Mapper.waitForElementToBeVisible(domFile,"homeServicesAllCategories");
        List<WebElement> elms = Mapper.finds(domFile,"homeServicesAllCategories");
        if (elms.size()==11)
        {
            finalVal=true;
        }
        return finalVal;
    }



}
