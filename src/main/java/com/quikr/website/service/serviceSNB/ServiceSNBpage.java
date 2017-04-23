package com.quikr.website.service.serviceSNB;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Adil on 22/9/15.
 */
public class ServiceSNBpage extends PageBase {

    private static final String domFile = getProperties().get("SERVICE_SNB_DOM_FILE");

    public ServiceSNBpage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }


    public void navigateToFirstAd()
    {
        Mapper.find(domFile, "SnbAdTitles").click();
    }

    public String getFirstAdTitleSnb()
    {
        Mapper.waitForElementToBeClickable(domFile, "SnbAdTitles");
        return Mapper.find(domFile, "SnbAdTitles").getText();
    }

    public  boolean validateSnbResults(String searchKey)
    {
        int countTrue=0;
        int countFalse=0;
        boolean finalVal=false;

        Mapper.waitForElementToBeVisible(domFile,"locationFilter");
        Mapper.waitForElementToBeVisible(domFile, "totalAdCount");
        Mapper.find(domFile,"totalAdCount");
        List<WebElement> adTitles = Mapper.finds(domFile, "SnbAdTitles");

        for (int i=0;i<adTitles.size();i++)
        {
//            System.out.println(adTitles.get(i).getText());
            if (adTitles.get(i).getText().contains(searchKey))
            {
                countTrue=countTrue+1;
            }else
            {
                countFalse=countFalse+1;
            }
        }
//        System.out.println(countTrue + "T#F" + countFalse);
        if (countTrue>adTitles.size()/4)
        {
            finalVal=true;
        }
        return finalVal;
    }

    public boolean validatePremiumAdsAndNonPremiumAds()
    {
        boolean finalVal = false;
        List<WebElement> adTitles = Mapper.finds(domFile, "SnbAdTitles");
        List<WebElement> premiumAdImages = Mapper.finds(domFile, "SnbPremiumAdImages");

        if (premiumAdImages.size()>0)
        {
            finalVal=true;
        }
        return finalVal;
    }

    public String getTheAdCounts()
    {
        if(Mapper.find(domFile,"CloseIconofQuikrConnect")!=null){

            Mapper.find(domFile,"CloseIconofQuikrConnect").click();
        }
        Mapper.waitForElementToBeVisible(domFile,"totalAdCount");
        return Mapper.find(domFile,"totalAdCount").getText();
    }

    public boolean checkLocalityInSearchRes(String location)
    {
        boolean finalVal=false;
        Mapper.waitForElementToBeVisible(domFile,"adLocation");
        List<WebElement> elm = Mapper.finds(domFile,"adLocation");

        for (int i=0;i<elm.size();i++)
        {
            if (elm.get(i).getText().contains(location))
            {
                finalVal=true;
            }
        }
        return finalVal;
    }

}
