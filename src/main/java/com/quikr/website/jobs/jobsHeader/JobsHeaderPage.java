package com.quikr.website.jobs.jobsHeader;

import com.quikr.website.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 26/11/15.
 */
public class JobsHeaderPage extends PageBase{

    private static final String domFile = getProperties().get("JOBS_HEADER_DOM_FILE");

    public JobsHeaderPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    public boolean isJobsLogopresent(){
        return Mapper.find(domFile, "jobsLogo").isDisplayed();
    }

    public boolean isCityPrePopulated(String city){
        return  Mapper.findAndReplace(domFile, "citySearchBoxinputHeader", new String[]{city}).isDisplayed();
    }

    public boolean isHamMenupresent() {
        return Mapper.find(domFile, "hamMenu").isDisplayed();
    }

    public void clickHamMenu() {
        Mapper.find(domFile, "hamMenu").click();
    }

    public boolean isQuikrHomepresentHamMenu() {
        return Mapper.find(domFile, "QuikrHomeIconHamMenu").isDisplayed() ;
    }

    public void clickQuikrHomeHamMenu() {
        Mapper.find(domFile, "QuikrHomeIconHamMenu").click();
    }

    public boolean isQuikrHomeHamMenuopen(){
        try{
            if(Mapper.find(domFile,"QuikrHomeIconHamMenuPresent").isDisplayed()){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean isAllHamMenuOptionsavailable() {
        for (int i = 3; i <= 11; i++) {
            try {
                if(!Mapper.findAndReplace(domFile, "hamMenuOptions", new String[]{Integer.toString(i)}).isDisplayed()){
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }


    public void closeHemuMenu(){
        Mapper.find(domFile,"closeHamMenu").click();
        try {
            Thread.sleep(5000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickAllHamMenuOptions() {
        for (int i = 3; i <= 11; i++) {
            try {
                clickHamMenu();
                Mapper.findAndReplace(domFile, "hamMenuOptions", new String[]{Integer.toString(i)}).click();
                navigateTo().refresh();
                navigateTo().back();
            } catch (Exception e) {
            }
        }
    }


    public void clickHindiLanguageJobsHeader(){
        Mapper.waitForElementToBeClickable(domFile,"languagedropdown");
        Mapper.find(domFile,"languagedropdown").click();
        Mapper.waitForElementToBeClickable(domFile,"HeaderhindiLanguageOption");
        Mapper.find(domFile,"HeaderhindiLanguageOption").click();

    }

    public void clickTamilLanguageJobsHeader(){
        Mapper.waitForElementToBeClickable(domFile,"languagedropdown");
        Mapper.find(domFile,"languagedropdown").click();
        Mapper.waitForElementToBeClickable(domFile,"HeaderTamilLanguageOption");
        Mapper.find(domFile,"HeaderTamilLanguageOption").click();

    }

    public void clickTeluguLanguageJobsHeader(){
        Mapper.waitForElementToBeClickable(domFile,"languagedropdown");
        Mapper.find(domFile,"languagedropdown").click();
        Mapper.waitForElementToBeClickable(domFile,"HeaderTeluguLanguageOption");
        Mapper.find(domFile,"HeaderTeluguLanguageOption").click();

    }

    public void clickMalyalamLanguageJobsHeader(){
        Mapper.waitForElementToBeClickable(domFile,"languagedropdown");
        Mapper.find(domFile,"languagedropdown").click();
        Mapper.waitForElementToBeClickable(domFile,"HeaderMalyalamLanguageOption");
        Mapper.find(domFile,"HeaderMalyalamLanguageOption").click();

    }


    public boolean isPostAdbuttonpresent(){
        return Mapper.find(domFile,"postAdButton").isDisplayed();
    }


    public void clickPostAdbuttonpresent(){
        Mapper.waitForElementToBeVisible(domFile, "postAdButton");
        Mapper.waitForElementToBeClickable(domFile, "postAdButton");
        Mapper.find(domFile,"postAdButton").click();
    }

    public void enterJobRoleHeader(String JobRole)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"JobRoleHeader")==true) {
            Mapper.waitForElementToBeClickable(domFile, "JobRoleHeader");
            Mapper.find(domFile, "JobRoleHeader").click();
            Mapper.find(domFile, "JobRoleHeader").sendKeys(Keys.CONTROL, "a");
            Mapper.find(domFile, "JobRoleHeader").sendKeys(Keys.DELETE);
            Mapper.find(domFile, "JobRoleHeader").sendKeys(JobRole);
        }else
        {
            logger.info("Job Role header is not visible. Please check!");
        }
    }

    public void clickJobSearchIconHeader()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "JobSearchIconHeader")==true)
        {
            Mapper.find(domFile,"JobSearchIconHeader").click();
        }
        else
        {
            logger.info("Job Search icon not visible in header. Please check!");
        }
    }

}
