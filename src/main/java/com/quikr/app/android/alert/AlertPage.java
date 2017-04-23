package com.quikr.app.android.alert;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 16/8/15.
 */
public class AlertPage extends AppAndroidPageBase {
    public AlertPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_ALERT_DOM_FILE");

    /**
     * swatantra singh
     * Click on create button
     */
    public void clickOnCreateAlert()
    {
        Mapper.waitForElementToBeVisible(domFile, "createAlert",10);
        Mapper.find(domFile,"createAlert").click();
    }

    /**swatantra
     * click on submit alert bu tton
     */
    public  void submitAlert()
    {
        if (!isElementPresent("submitAlert"))
            navigateBack();
        Mapper.find(domFile,"submitAlert").click();
    }

    /**
     * 'validate alert created
     * @return
     */
    public  boolean validateAlert()
    {
        Mapper.waitForElementToBeVisible(domFile, "alertPopup", 10);
        return  ((Mapper.find(domFile,"alertPopup").getText().toLowerCase().contains("Your alert will be live shortly".toLowerCase())));
    }

    /**
     * click on OK button after selection
     * checkbox appear with two different Locator ID ,Hence below function handles the same
     * @return
     */
    public AlertPage selectCheckBox()
    {
        if (isElementPresent("checkBox"))
        {
            Mapper.find(domFile, "checkBox").click();
        }
        else
            Mapper.find(domFile,"LocalityCheckbox").click();
//            Mapper.find(domFile,"doneButton").click();

        return  this;
    }

    public void enterPhoneNumber(String phoneNumber)
    {
        if(isElementPresent("phoneNumber"))
            Mapper.find(domFile, "phoneNumber").sendKeys(phoneNumber);
    }

    public String Text()
    {
        String text1 = Mapper.find(domFile, "dropdownOptions").getText();
        return text1;
    }
    /**
     * provide alert title
     */
    public  void createAlertTitle(String title)
    {
        Mapper.waitForElementToBeVisible(domFile,"createAlertTitle");
        Mapper.find(domFile,"createAlertTitle").sendKeys(title);
    }
    /**
     * provide alert Description
     */
    public  void createAlertDescription(String title)
    {
        Mapper.waitForElementToBeVisible(domFile,"createAlertDescription");
        Mapper.find(domFile,"createAlertDescription").sendKeys(title);
    }
    /**
     * 'validate alert created
     * @return
     */
    public  boolean validateAlertNewFormat()
    {
        return  (isElementPresent("validateAlertNewFormat"));

    }

    /**
     * click on real estate area
     */
    public void clickOnRealestaeSelectArea()
    {
        Mapper.find(domFile,"realestateArea").click();
    }

    /**
     * swipe to year in create alert with multiple values
     */

    public void swipeToYearInCreateAlertWithMultipleValues()
    {
        int Y1Cordinates=Mapper.find(domFile,"subCatregory").getLocation().getY();
        Mapper.VerticalSwipe(domFile, "describeNeed", "subCatregory");
    }
    /**
     * swipe cordinates for swaping vertically on Alert page
     */
    public List swipeCordinatesFprCreateAlert()
    {
        Mapper.waitForElementToBeVisible(domFile,"category");
        int Y1Cordinates=Mapper.find(domFile,"subCatregory").getLocation().getY();
        int Y2Cordinates=Mapper.find(domFile,"category").getLocation().getY();
        List cordinates=new ArrayList<>();
        cordinates.add(Y1Cordinates);
        cordinates.add(Y2Cordinates);
        return cordinates;
    }
    /**
     * click on sort button on alert page
     */
    public void clickOnSortButtOnAlertPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "sortOnAlertPage");
        Mapper.find(domFile,"sortOnAlertPage").click();
    }

    public void clickOnMatchingAds()
    {
        Mapper.waitForElementToBeVisible(domFile, "matchingAds");
        Mapper.finds(domFile, "matchingAds").get(1).click();
    }

    public void clickOnLowestPriceOnTopOption()
    {
        Mapper.waitForElementToBeVisible(domFile, "lowestPriceOnTopOption");
        Mapper.find(domFile, "lowestPriceOnTopOption").click();
    }

    public void  clickOnMatchingAdsTab()
    {
        Mapper.waitForElementToBeVisible(domFile, "matchingAdsTab");
        Mapper.find(domFile, "matchingAdsTab").click();
    }

    public void clickOnMenuIcon()
    {
        Mapper.waitForElementToBeVisible(domFile, "menuIconOnAlertPage");
        Mapper.find(domFile, "menuIconOnAlertPage").click();
    }

    public void deleteAlert()
    {
        Mapper.waitForElementToBeVisible(domFile, "deleteAlertButton");
        Mapper.find(domFile, "deleteAlertButton").click();
    }

    public void confirmDeleteAlert()
    {
        Mapper.waitForElementToBeVisible(domFile, "confirmDeleteAlert");
        Mapper.find(domFile, "confirmDeleteAlert").click();
    }

    public boolean validateAlertDeletion()
    {
        Mapper.waitForElementToBeVisible(domFile, "validateAlertDeletion");
        return isElementPresent("validateAlertDeletion");
    }

    public boolean validateViewAllAlerts()
    {
        Mapper.waitForElementToBeVisible(domFile, "alertTitle");
        int size = Mapper.finds(domFile, "alertTitle").size();
        if(size > 0) return true;
        return false;
    }
    /*
    swipe on alert page
     */
    public Integer[] getCordinates()
    {
        // for long swipe from botton to up use "Y2-Y1"
        //for small swipe use "y3-Y2"
        Mapper.waitForElementToBeVisible(domFile,"category");
        int Y1=Mapper.find(domFile,"subCatregory").getLocation().getY();
        int Y2=Mapper.find(domFile,"category").getLocation().getY();
        Integer cordinates[]={Y1,Y2,};
        return cordinates;

    }
    /**
     * usingh rand function to create unique email id for every run
     * swatantra singh
     * @return
     */
    public void alertEmail()
    {
        String emailId= "swat_" +(int)(10000000* Math.random()) + "@gmail.com";
        Mapper.waitForElementToBeVisible(domFile, "alertEmail");
        Mapper.find(domFile,"alertEmail").sendKeys(emailId);

    }
    /**
     * click on matching ad tab for alerts
     */
    public void clickOnMAtchingAdTabsForGivenAlert()
    {
        Mapper.waitForElementToBeVisible(domFile,"matchingAdsNew");
        Mapper.find(domFile,"matchingAdsNew").click();
    }
    /**
     *
     */
    public boolean validateMatchingAdsDisplayedAfterClickingmatchingtab()
    {
        return isElementPresent("matchingAdTitle");
    }

    public boolean validateEditAlert()
    {
        Mapper.waitForElementToBeVisible(domFile, "editAlertMsg");
        return isElementPresent("editAlertMsg");
    }

    public void selectLocality(String locality)
    {
        Mapper.find(domFile, locality).click();
    }

    /**
     * verify header elements
     */
    public boolean verifyHeaderElements()
    {
        String button1=Mapper.finds(domFile,"matchingAds").get(0).getText();
        String button2=Mapper.finds(domFile,"matchingAds").get(1).getText();
        System.out.println(button1 + "&" + button2);
        return (button1.equalsIgnoreCase("Alerts")&&button2.equalsIgnoreCase("Matching Ads"));
    }
    public String getAlertCategory()
    {
        Mapper.waitForElementToBeVisible(domFile, "category");
        return(Mapper.find(domFile,"category").getText());
    }
    public String getAlertSubCategory()
    {
        Mapper.waitForElementToBeVisible(domFile, "subCatregory");
        return(Mapper.find(domFile,"subCatregory").getText());
    }
    public void hideKeyBoardAfterClickingEditAlert()
    {
        if (!isElementPresent("submitAlert"))
            navigateBack();
    }
}
