package com.quikr.app.ios.cars.carsSnbPage;

import com.quikr.app.ios.AppiOSPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by Manvendra Singh on 1/25/16.
 */
public class CarsSnbPage extends AppiOSPageBase{
    public CarsSnbPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_CARSSNBPAGE_DOM_FILE");

    /**
     * function to validate cars or bikes  search results on snb page
     */
    public boolean validateCarsOrBikesSearchResultOnCarsSnbPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"chatButton");
        int count=0;
        for(int i=1;i<8;i++)
        {
           String titleName= Mapper.findAndReplace(domFile, "carsSnbPageTitle", new String[]{Integer.toString(i)}).getText();String titleNameInLowerCase=titleName.toLowerCase();
           System.out.println(titleNameInLowerCase);
            if(titleNameInLowerCase.contains("swift") || titleNameInLowerCase.contains("hero"))
            {
                count++;
                System.out.println(count);
            }
        }
        if(count==7)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * function to validate sort options for cars
     */
    public boolean validateCarsSortOptions()
    {
        Mapper.waitForElementToBeVisible(domFile,"mostRelevant");
        return (isElementPresent("mostRelevant") && isElementPresent("recentlyPosted") && isElementPresent("lowestPrice") && isElementPresent("highestRated") && isElementPresent("latestModel") && isElementPresent("leastDriven"));
    }
}
