package com.quikr.website.jobs.jobsPAP;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 5/10/15.
 */
public class JobsPAPPage extends PageBase {

    public JobsPAPPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("JOBS_PAP_DOM_FILE");

    public JobsPAPPage postAdTitle(String title)
    {
        Mapper.waitForElementToBeVisible(domFile, "title");
        Mapper.waitForElementToBeClickable(domFile, "title");
        Mapper.find(domFile, "title").click();
        Mapper.find(domFile, "title").sendKeys(title);
        return this;
    }

    public JobsPAPPage selectRole(String role)
    {
        if(Mapper.waitForElementToBeClickable(domFile,"jobsRole")==true) {
            Mapper.find(domFile, "jobsRole").click();
        }
        Mapper.findAndReplace(domFile,"jobRoleText",new String[]{role}).click();
        return this;
    }

    public JobsPAPPage selectEducation(String edu)
    {
        Select education=new Select(Mapper.find(domFile,"jobsEducation"));
        education.selectByVisibleText(edu);
        return this;
    }


    public JobsPAPPage selectExperience(String exp)
    {
        Select experiance=new Select(Mapper.find(domFile,"jobsExperience"));
        experiance.selectByVisibleText(exp);
        return this;
    }

    public JobsPAPPage selectsalary(String sal)
    {
        Select salary=new Select(Mapper.find(domFile,"jobsSalary"));
        salary.selectByVisibleText(sal);
        return this;
    }

    public JobsPAPPage selectCompensation(String comp)
    {
        Select salary=new Select(Mapper.find(domFile,"jobsCompensation"));
        salary.selectByVisibleText(comp);
        return this;
    }

    public void setSalaryJobs(String salaryMin, String salaryMax){
        Mapper.find(domFile,"jobsSalaryMin").click();
        Mapper.find(domFile,"jobsSalaryMin").sendKeys(salaryMin);
        Mapper.find(domFile,"jobsSalaryMax").click();
        Mapper.find(domFile,"jobsSalaryMax").sendKeys(salaryMax);
    }
    public JobsPAPPage selectlLocality()
    {
        Mapper.find(domFile,"locality").click();
        Mapper.find(domFile, "selectLocalityFirst").click();
        return this;
    }

    public JobsPAPPage selectmultiplelocality()
    {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            Select loc = new Select(Mapper.find(domFile,"locality"));
            loc.selectByVisibleText("80 Ft. Road");
            loc.selectByVisibleText("Achitnagar");
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
        catch (Exception e){}
        return this;
    }


    public JobsPAPPage provideDescription(String Desc) {
        if (Mapper.waitForElementToBeVisible(domFile, "adDescription") == true)
        {
            Mapper.find(domFile, "adDescription").sendKeys(Desc);
        }
        else
        {
            logger.info("Ad Description is not visible. Please check!");
        }
        return this;
    }

    public JobsPAPPage provideMobNum(String mob)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"mobField")==true)
        {
            Mapper.find(domFile, "mobField").click();
            Mapper.find(domFile, "mobField").sendKeys(mob);
        }
        else {
            logger.info("Mobile text field not visible.");
        }
        return this;

    }

    public boolean validateMobileNumMandatoryPAP(){
        WebElement mandatory= Mapper.find(domFile, "MandatoryFieldNumber");
        if(mandatory.isDisplayed()){
            return true;
        }
        else {
            return false;
        }
    }

    public String validateErrorMsgMobNumber(){
        return (Mapper.find(domFile,"MandatoryNumberErrorMessage").getText());
    }

    public String validateErrorMsgEmailandMobNum(){
        String errormsg=Mapper.find(domFile,"ErrorMessageEmailandMobNum").getText();
        return errormsg;
    }

    public void clickUpgradeButton(){
        Mapper.find(domFile,"upgradeAdButton").click();
    }

    public boolean validateEmailMandatory(){
        return (Mapper.find(domFile,"emailMandatoryField").isDisplayed());
    }

    public String getDummyEmailId(){
        String emailid=Mapper.find(domFile,"email").getAttribute("value");
        return emailid;
    }

    public void setEmail(String email){
        if(Mapper.waitForElementToBeClickable(domFile,"jobPostAdEmail")==true) {
            Mapper.find(domFile, "jobPostAdEmail").click();
            Mapper.find(domFile, "jobPostAdEmail").sendKeys(email);
        }

    }
    public void setDummyEmail(String email)
    {
        Mapper.find(domFile,"email").sendKeys(email);
    }

    public void setRandomMobNumber(String number){
        Mapper.find(domFile,"mobNumber").sendKeys(number);
    }

    public void setCompanyName(String companyName){
        Mapper.find(domFile,"postAdCompanyName").click();
        Mapper.find(domFile,"postAdCompanyName").sendKeys(companyName);
    }
    public void setCity(String city)
    {
        Mapper.find(domFile,"selectCityBox").click();
        Mapper.find(domFile,"selectCityBoxtextField").sendKeys(city);
        Mapper.waitForElementToBeVisible(domFile, "citySuggestion");
        Mapper.waitForElementToBeClickable(domFile, "citySuggestion");
        Mapper.find(domFile,"citySuggestion").click();

    }

    public void clickEditAd(){
        Mapper.find(domFile,"EditAd").click();
    }

    public void selectPremiumAd()
    {
        if (Mapper.find(domFile,"urgentAd")!=null)
        {
            if (Mapper.waitForElementToBeVisible(domFile, "urgentAd")==true)
            {
                Mapper.find(domFile, "urgentAd").click();
                if (Mapper.find(domFile,"urgentAd")!=null)
                {
                    Mapper.find(domFile, "urgentAd").click();
                }
            }
            else
            {
                logger.info("Urgent ad button not properly visible or some problem exists. Please check!");
            }

            if (Mapper.find(domFile,"chequePaymentOption")!=null)
            {
                if (Mapper.waitForElementToBeVisible(domFile,"chequePaymentOption")==true)
                {
                    Mapper.find(domFile,"chequePaymentOption").click();
                }
            }
            else
            {
                logger.info("Cheque Payment option is not visible.");
            }
        }
        else
        {
            logger.info("Urgent ad button is not present. Please check!");
        }
    }

    public JobsPAPPage clickPostAdButton()
    {
        Mapper.waitForElementToBeVisible(domFile, "postAdButton");
        Mapper.waitForElementToBeClickable(domFile, "postAdButton");
        Mapper.find(domFile, "postAdButton").click();
        return this;
    }

    public void clickPostAdStepOne(){
        if(Mapper.waitForElementToBeClickable(domFile,"postAdButtonStepOne")==true){
            Mapper.find(domFile,"postAdButtonStepOne").click();
        }
    }


    public void clickPostAdStepTwo(){
        if(Mapper.waitForElementToBeClickable(domFile,"postAdButtonStepTwo")==true){
            Mapper.find(domFile,"postAdButtonStepTwo").click();
        }
    }

    public String getAdId()
    {
        String adIdString = Mapper.find(domFile,"AdIdString").getText();
        String adId = adIdString.substring(adIdString.length() - 10, adIdString.length()-1);
        return adId;
    }

    public boolean isAdIdVisible(){

        return (isElementPresent("AdIdString"));
    }
    public boolean confirmPremiumAdPostSuccess()
    {
        boolean retVal = false;
        if (Mapper.find(domFile,"premiumAdConfirmationElements")!=null)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public boolean isJobPosted(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(getCurrentLocation().contains("PostAd/?succeed")){
            return true;
        }
        else if (getCurrentLocation().contains("postadsuccess"))
        {
            return true;
        }
        else if(getCurrentLocation().contains("postad_adid")){
            return true;
        }
        else if(getCurrentLocation().contains("postadsuccess_source")){
            return true;
        }
        else
            return false;
    }

    public void selectJobCategory(String argumnt)
    {
        if (argumnt.toLowerCase().contains("home"))
        {
            if (Mapper.waitForElementToBeVisible(domFile,"jobCategoryWorkFromHome")==true)
            {
                Mapper.find(domFile,"jobCategoryWorkFromHome").click();
            }
            else
            {
                logger.info("Work from Home category is not visible. Please check!");
            }
        }else if (argumnt.toLowerCase().contains("part"))
        {
            if (Mapper.waitForElementToBeVisible(domFile,"jobCategoryPartTime")==true)
            {
                Mapper.find(domFile,"jobCategoryPartTime").click();
            }
            else
            {
                logger.info("Part time category is not visible. Please check!");
            }
        }else if (argumnt.toLowerCase().contains("full"))
        {
            if (Mapper.waitForElementToBeVisible(domFile,"jobCategoryWorkFullTime")==true)
            {
                Mapper.find(domFile,"jobCategoryWorkFullTime").click();
            }
            else
            {
                logger.info("Full time category is not visible. Please check!");
            }
        }else if (argumnt.toLowerCase().contains("internship"))
        {
            if (Mapper.waitForElementToBeVisible(domFile,"jobCategoryInternship")==true)
            {
                Mapper.find(domFile,"jobCategoryInternship").click();
            }
            else
            {
                logger.info("Full time category is not visible. Please check!");
            }
        }else
        {
            logger.info("Proper argument not passed to click on the job categories.");
        }
    }

    public void setMinSalary(String minSalary)
    {
        Mapper.find(domFile,"jobPostAdMinSalary").clear();
        Mapper.find(domFile,"jobPostAdMinSalary").sendKeys(minSalary);
    }

    public void setMaxSalary(String maxSalary)
    {
        Mapper.find(domFile,"jobPostAdMaxSalary").clear();
        Mapper.find(domFile,"jobPostAdMaxSalary").sendKeys(maxSalary);
    }

    public void clickSubmitBtnJobPostAd()
    {
        Mapper.waitForElementToBeVisible(domFile, "jobPostSubmit");
        Mapper.find(domFile,"jobPostSubmit").click();
    }

    public void clickAddToJobBtnJobPostAd()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "jobPostAddToJobBtn")==true) {
            Mapper.find(domFile, "jobPostAddToJobBtn").click();

        }
    }
}
