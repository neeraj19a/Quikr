package com.quikr.msite.mJobs.mJobsHome;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Keys;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 30/11/15.
 */
public class MJobsHomePage extends MPageBase {

    private static final String domFile = getProperties().get("mJobs_HOME_DOM_FILE");
    public MJobsHomePage(AppiumDriver driver){
        super(domFile, driver);
    }

    public boolean isQuikrJobsLogoavailable(){
        return (isElementPresent("QuikrJobsLogo"));
    }

    public void setJobRole(String role){
        Mapper.waitForElementToBeClickable(domFile, "InputJobRole");
        Mapper.find(domFile,"InputJobRole").click();
        Mapper.find(domFile, "InputJobRole").sendKeys(role);
    }

    public void setCity(String city){
        Mapper.waitForElementToBeClickable(domFile, "SelectCityDropIcon");
        Mapper.find(domFile, "SelectCityDropIcon").click();
        Mapper.find(domFile,"InputCity").click();
        Mapper.find(domFile,"InputCity").sendKeys(city);
        Mapper.findAndReplace(domFile, "SelectCity", new String[]{city}).click();
    }

    public void search(){
        Mapper.find(domFile, "InputJobRole").sendKeys(Keys.ENTER);
    }


    public void setCityfromCityPopUpWindow(String city){
        Mapper.waitForElementToBeVisible(domFile,"InputCity");
        Mapper.waitForElementToBeClickable(domFile,"InputCity");
        Mapper.find(domFile,"InputCity").sendKeys(city);
        Mapper.findAndReplace(domFile, "SelectCity", new String[]{city}).click();
    }

}
