package com.quikr.website.service.serviceVAP;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Adil on 22/9/15.
 */
public class ServiceVAPpage extends PageBase {

    private static final String domFile = getProperties().get("SERVICE_VAP_DOM_FILE");

    public ServiceVAPpage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    public String getAdTitleVap()
    {
        Mapper.waitForElementToBeVisible(domFile, "adTitleVap");
        return Mapper.find(domFile,"adTitleVap").getText();
    }


    public boolean verifyAdTitleVap(String adTitleFromSnb)
    {
        String adTitleFromVap = getAdTitleVap();
        if (adTitleFromVap.contains(adTitleFromSnb.substring(0,adTitleFromSnb.length()-10)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
