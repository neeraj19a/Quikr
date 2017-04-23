package com.quikr.website.service.servicePAP;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Adil on 23/9/15.
 */
public class ServicePAP extends PageBase {

    public ServicePAP(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("SERVICE_PAP_DOM_FILE");

    public ServicePAP openPap(){
        if (isElementPresent("postAd"))
            Mapper.find(domFile, "postAd").click();
        else
            Mapper.find(domFile,"PostAdIconLogin").click();
        return this;
    }

    public ServicePAP navigateToServices()
    {
        Mapper.find(domFile, "servicesCategory").click();
        Mapper.find(domFile, "servicesSubCategoryAdvertising").click();
        return this;
    }

    public void selectAdCity(String cityName)
    {
        Mapper.find(domFile, "selectACity").click();
        Mapper.waitForElementToBeVisible(domFile, "citySearchTextBox");
        Mapper.find(domFile, "citySearchTextBox").sendKeys(cityName);
        Mapper.waitForElementToBeVisible(domFile, "city");
        Mapper.find(domFile, "city").click();
    }

    public void selectAdLocality()
    {
        Mapper.find(domFile, "selectALocality").click();
        Mapper.find(domFile, "locality").click();
    }

    public void selectAdAsCustomer()
    {
        Mapper.find(domFile,"PostAdAsCustomer").click();

    }

    public void giveAdTitle(String adTitle)
    {
        Mapper.find(domFile, "adTitle").sendKeys(adTitle);
    }

    public void selectAdType()
    {
        Mapper.find(domFile, "type").click();
        Mapper.waitForElementToBeVisible(domFile, "selectTypeAdvertising");
        Mapper.find(domFile, "selectTypeAdvertising").click();
    }

    public void giveAdDescription(String description)
    {
        Mapper.find(domFile, "adDescription").sendKeys(description);
    }

    public void YourEmail(String email)
    {
        Mapper.find(domFile, "YourEmailTextbox").sendKeys(email);
    }

    public void postAd()
    {
        try {
            Thread.sleep(3000);
            logger.info("Post ad button cointainer size" + Mapper.finds(domFile, "postTheAd").size());
            Mapper.waitForElementToBeVisible(domFile, "postTheAd");
            Mapper.waitForElementToBeClickable(domFile, "postTheAd");
            Mapper.find(domFile, "postTheAd").click();
            Mapper.find(domFile, "postTheAd").click();
            logger.info("Post ad button clicked....");
        }
    catch (Exception e){}}

    public void postAdWithoutPhotos()
    {
        Mapper.waitForElementToBeVisible(domFile, "postWithoutPhotosButton");
        Mapper.find(domFile, "postWithoutPhotosButton").click();
        //Mapper.find(domFile, "postWithoutPhotosButton").click();
    }

    public boolean checkSuccessMsg()
    {
        Mapper.waitForElementToBeVisible(domFile, "adPostSuccessConfirmationText");
        String txt = Mapper.find(domFile, "adPostSuccessConfirmationText").getText();
        logger.info("Success message captured as - "+txt);
        if (txt.contains("Thanks for posting"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean postAnAdServices(String cityName, String adTitle, String description)
    {
        selectAdCity(cityName);
        selectAdLocality();
        giveAdTitle(adTitle);
        selectAdType();
        giveAdDescription(description);
        postAd();
        return checkSuccessMsg();
    }




}
