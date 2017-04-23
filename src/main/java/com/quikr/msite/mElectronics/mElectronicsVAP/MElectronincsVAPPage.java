package com.quikr.msite.mElectronics.mElectronicsVAP;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 20/11/15.
 */
public class MElectronincsVAPPage extends MPageBase {

    private static final String domFile = getProperties().get("mElectronicsVAP_DOM_FILE");
    public MElectronincsVAPPage(AppiumDriver driver){super(domFile,driver);}


    public boolean validateElectronicsVAPPage(){
        boolean flag;

        if(isElementPresent("AdDetails")){
            flag=true;
        }
        else {
            logger.info("AdDetails is Not present");
            flag=false;
        }

        if(isElementPresent("AdDescription")){
            flag=true;
        }
        else {
            logger.info("AdDescription is Not Present");
            flag=false;
        }
        return flag;
    }

    public void clickChatonVAP(){
        Mapper.waitForElementToBeVisible(domFile,"ChatOptionOnVAP");
        Mapper.waitForElementToBeClickable(domFile,"ChatOptionOnVAP");
        Mapper.find(domFile,"ChatOptionOnVAP").click();
    }

    public void setChatDetails(String chatmsg,String Email,String mobile, String Name){
        Mapper.find(domFile,"ChatName").click();
        Mapper.find(domFile,"ChatName").sendKeys(Name);
        Mapper.find(domFile,"ChatMessage").sendKeys(chatmsg);
        Mapper.find(domFile,"ChatEmail").sendKeys(Email);
        Mapper.find(domFile,"ChatPhone").click();
        Mapper.find(domFile,"ChatPhone").sendKeys(mobile);
        Mapper.find(domFile,"StartChatButton").click();

    }
}
