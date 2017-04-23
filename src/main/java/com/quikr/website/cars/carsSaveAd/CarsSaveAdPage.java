package com.quikr.website.cars.carsSaveAd;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Saurabh on 15/11/15.
 */
public class CarsSaveAdPage extends CarsPageBase
{

    public CarsSaveAdPage(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String domFile = getProperties().get("CARS_SAVE_AD_PAGE_DOM_FILE");

    public void clickTab(String tabName)
    {
        Mapper.waitForElementToBeVisible(domFile, "adTab", new String[]{tabName});
        Mapper.findAndReplace(domFile, "adTab", new String[]{tabName}).click();
    }

    public boolean checkAdPresence(String tabName, String adId)
    {

        boolean adStatus = false;
        clickTab(tabName);
        Mapper.waitForElementToBeVisible(domFile, "adPresence", new String[]{adId});
        List<WebElement> ad = Mapper.findsAndReplace(domFile, "adPresence", new String[]{adId});

        if (ad.size()>0)
            adStatus = true;

        return adStatus;
    }
}