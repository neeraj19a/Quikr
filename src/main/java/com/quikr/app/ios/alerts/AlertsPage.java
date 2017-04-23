package com.quikr.app.ios.alerts;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by Manvendra Singh on 12/10/15.
 */
public class AlertsPage extends com.quikr.app.ios.AppiOSPageBase {
    public AlertsPage(AppiumDriver driver)
    {
        super(domFile,driver);
    }
    // const string
    private static final String domFile = getProperties().get("IOS_ALERTS_DOM_FILE");

    /**
     * function to click on addAlertsCreate button
     */
    public void clickOnAddAlertsCreateButton()
    {
        Mapper.find(domFile,"addAlertsCreateButton").click();
    }

    /**
     * function to validate + AlertsCreateButton
     */
    public boolean validateAddAlertsCreateButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"addAlertsCreateButton");
        return isElementPresent("addAlertsCreateButton");
    }


    /**
     * function to click on select category on alert page
     */
    public void clickOnSelectCategory()
    {
        Mapper.find(domFile,"clickOnSelectCategory").click();
    }

    /**
     * function to select locality on alert page
     */
    public void clickOnLocalityOnAlerts()
    {
        //Mapper.scroll("locality");
        Mapper.waitForElementToBeVisible(domFile,"locality");
        Mapper.find(domFile,"locality").click();
    }

    /**
     * function to select locality option name
     */
    public void selectLocalityName()
    {
        Mapper.find(domFile,"localityOptionName").click();
    }

    /**
     * function to click on optional detail in create alert
     */
    public void clickOnOptionalDetailsForMobileCategoryInCreateAlerts()
    {
        Mapper.scroll("optionalDetailsButton");
        Mapper.find(domFile,"optionalDetailsButton").click();
    }

    /**
     * function select create alert button
     */
    public void selectCreateAlertButton()
    {
        Mapper.scroll("Create Alert");
//        Mapper.find(domFile,"createAlertButton").click();
//        Mapper.waitForElementToBeInvisible(domFile,"createAlertButton");

        List<WebElement> buttons = Mapper.finds(domFile, "cellList");
        WebElement createAlertButton = null;
        for (int i=0;i<buttons.size();i++)
        {
            if (buttons.get(i).getText().equalsIgnoreCase("Create Alert"))
            {
                createAlertButton = buttons.get(i);
                System.out.println(createAlertButton.getText());
                break;
            }
        }
        createAlertButton.click();
    }

    /**
     * function to validate alert
     */
    public boolean validateCreateAlert()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgAfterCreateAlerts");
        return isElementPresent("msgAfterCreateAlerts");
    }

}
