package com.quikr.website.jobs.jobsVAP;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 15/10/15.
 */
public class JobsVAPPage extends PageBase {

    public JobsVAPPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("JOBS_VAP_DOM_FILE");

    public boolean isVAPPageDisplayed(){

        boolean retVal = false;
        WebElement jobsSearchBox=Mapper.find(domFile,"JobsVAPSearchBox");
        WebElement jobViews=Mapper.find(domFile,"JobsVAPViews");
        WebElement jobRole=Mapper.find(domFile,"JobsVAPRole");
        WebElement jobLocation=Mapper.find(domFile,"JobsVAPLocation");
        WebElement jobPostedDate=Mapper.find(domFile,"JobsVAPPostedDate");
        WebElement jobDetails=Mapper.find(domFile,"JobsDetails");
        WebElement jobDescription=Mapper.find(domFile,"JobsDescription");
        WebElement jobsImage=Mapper.find(domFile,"JobsImage");
        WebElement googleAds=Mapper.find(domFile,"googleAds");
        WebElement jobsBreadCrumb=Mapper.find(domFile,"jobsBreadCrumb");

        if (jobsSearchBox.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Searchbox not displayed");
        }

        if (jobViews.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Job views not displayed");
        }

        if(jobRole.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Job role not displayed");
        }

        if (jobLocation.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Job location not displayed");
        }

        if (jobPostedDate.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Job posted date not displayed.");
        }

        if (jobDetails.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Job details not displayed.");
        }

        if (jobDescription.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Job description not displayed");
        }

        /*if (googleAds.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Google ads not displayed");
        }
*/
        if (jobsBreadCrumb.isDisplayed())
        {
            retVal=true;
        }
        else
        {
            logger.info("Breadcrumbs not displayed");
        }
        return retVal;
    }


    public String jobsViewCount(){
        WebElement jobViews=Mapper.find(domFile, "JobsVAPViews");
        String views=jobViews.getText();

        return views;
    }

    public boolean isGoogleAdsDisplayedonJobsVAP(){
        List<WebElement> googleads=Mapper.finds(domFile, "googleadsOnJobsVap");
        if(googleads.size()>=3){
            return true;
        }
        else
            return false;
    }


    public void navigatefromVAPtoSNBHomes(){
        navigateTo().back();
    }

    public ArrayList isBreadCrumbDisplayedwithCity(){
        ArrayList<String> breadCRumb=new ArrayList<>();
        for(int i=2;i<=4;i++) {
            String text = Mapper.findAndReplace(domFile, "JobsBreadCrumbwithCity", new String[]{Integer.toString(i)}).getText();
            breadCRumb.add(text);
        }
        return breadCRumb;
        }


    public boolean validateAdDetails(){
        boolean flag=false;
        if(Mapper.findAndReplace(domFile, "add_detail" ,new String[]{Integer.toString(1)}).isDisplayed())
        {
            flag=true;
        }
       if (Mapper.findAndReplace(domFile, "add_detail" ,new String[]{Integer.toString(2)}).isDisplayed()){
            flag=true;
        }

        if (Mapper.findAndReplace(domFile, "add_detail" ,new String[]{Integer.toString(3)}).isDisplayed()){
            flag=true;
        }

        if (Mapper.findAndReplace(domFile, "add_detail" ,new String[]{Integer.toString(4)}).isDisplayed()){
            flag=true;
        }

        if(Mapper.find(domFile,"applybuttonVAP").isDisplayed()){
            flag=true;
        }

        return flag;
    }

    public String isPremiumTagavailable()
    {
    return Mapper.find(domFile,"PremiumVAP").getText();
    }

    public boolean jobImageVAP(){
        return isElementPresent("jobImageVAP");
    }

    public void clickSimilarAds(){
        List<WebElement> similarads=Mapper.finds(domFile, "FirstsimilarJobsVAP");
        similarads.get(0).click();
    }

    public boolean isScrollUpIconworking(){

        Mapper.scrollVerticallWithCords(10, 3400);
        Mapper.waitForElementToBeVisible(domFile, "ScrollUpIconVAPPage");
        if(Mapper.find(domFile,"ScrollUpIconVAPPage")!=null){
            Mapper.find(domFile,"ScrollUpIconVAPPage").click();
        }
            Mapper.waitForElementToBeInvisible(domFile,"ScrollUpIconVAPPageVisibleState");
        if(Mapper.find(domFile,"ScrollUpIconVAPPageVisibleState")==null){
           return true;
        }
        else
            return false;

    }

    public String SimilarJobsTitle(){
        return Mapper.find(domFile,"SimilarJobsTitle").getText();
    }

    public boolean ifAdPostedSuccessfully(String getAdIdFrompap, String adTitleExtracted)
    {
        boolean retVal = false;
        String dummyUrl = "http://bangalore.quikr.com/Silver-2007-Maruti-Suzuki-Swift-VXI-1-14-000-kms-driven-W0QQAdIdZ240428043";
        openUrl((dummyUrl.substring(0,dummyUrl.length()-9))+getAdIdFrompap);
        String adTitleFromVap = Mapper.find(domFile,"AdTitlePostedAd").getText();

        if (adTitleFromVap.contains(adTitleExtracted))
        {
            retVal = true;
        }
        else
        {
            logger.info("adTitle from Vap >>"+adTitleFromVap+"<<");
            logger.info("adTitle extracted >>"+adTitleExtracted+"<<");
            return false;
        }
        return retVal;
    }
}
