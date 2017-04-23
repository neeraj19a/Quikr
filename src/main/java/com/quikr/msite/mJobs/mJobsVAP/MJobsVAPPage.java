package com.quikr.msite.mJobs.mJobsVAP;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 30/11/15.
 */
public class MJobsVAPPage extends MPageBase {

    private static final String domFile = getProperties().get("mJobs_VAP_DOM_FILE");
    public MJobsVAPPage(AppiumDriver driver){
        super(domFile, driver);
    }

    public boolean isJobsVapPageloaded(){
        boolean flag;

        if(isElementPresent("JobDetailsVAP")){
            flag=true;
        }
        else
            flag=false;

        if(isElementPresent("JobDescriptionVAP")){
            flag=true;
        }
        else
            flag=false;

        if(isElementPresent("ApplyButtonVAP")){
            flag=true;
        }
        else
            flag=false;

        return flag;
    }

    public void clickApplyButtonVAP(){
        Mapper.waitForElementToBeClickable(domFile,"ApplyButtonVAP");
        Mapper.find(domFile,"ApplyButtonVAP").click();
    }

    public  boolean isApplyFormLoadedVAP(){
        boolean flag;

        if(isElementPresent("MobileNumberApplyFormVAP")){
            flag=true;
        }
        else
            flag=false;

        if(isElementPresent("LocalityApplyFormVAP")){
            flag=true;
        }
        else
            flag=false;

        if(isElementPresent("CaptchaApplyFormVAP")){
            flag=true;
        }
        else
            flag=false;

        if(isElementPresent("SubmitApplyFormVAP")){
            flag=true;
        }
        else
            flag=false;

        return flag;
    }
}
