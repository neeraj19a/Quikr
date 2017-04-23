package com.quikr.msite.mHorizontal.mMyChatandReplies;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 10/11/15.
 */
public class MMyChatandReply extends MPageBase {

    private static final String domFile = getProperties().get("mMyChatandReply_DOM_FILE");
    public MMyChatandReply(AppiumDriver driver){
        super(domFile,driver);
    }

    public boolean isValidMyChatandRepliesPageavailble(){

        boolean flag=false;
        if(isElementPresent("All")==true){
            flag=true;
        }
        else {
            flag=false;
        }
        if(isElementPresent("ByOthers")==true){
            flag=true;
        }
        else {
            flag=false;
        }
        if(isElementPresent("ByMe")==true){
            flag=true;
        }
        else {
            flag=false;
        }
        return flag;
    }

    public boolean isChatandReplycontainsMsgs(){

        List<WebElement> msgs=Mapper.finds(domFile,"TitleOfMsgs");
        if(msgs.size()>0){
            return true;
        }
        else
            return false;
    }

    public void clickMessagesinChat(){
        if(Mapper.waitForElementToBeClickable(domFile, "MessagesinChatandReply")==true) {
            Mapper.find(domFile, "MessagesinChatandReply").click();
            while(Mapper.find(domFile,"dismissNotification")==null)
            {
                Mapper.find(domFile, "MessagesinChatandReply").click();
            }
        }
    }

    public void clickShareIcon(){
        Mapper.waitForElementToBeClickable(domFile,"ShareIcon");
        Mapper.find(domFile,"ShareIcon").click();
    }

    public void clickShareContact(){
        Mapper.waitForElementToBeClickable(domFile,"ShareContact");
        Mapper.find(domFile,"ShareContact").click();
    }


    public boolean isContactShared(){

        try{
            if(Mapper.find(domFile,"nameShareContact")!=null && Mapper.find(domFile,"nameShareContact").getText().isEmpty()){
                Mapper.find(domFile,"nameShareContact").click();
                Mapper.find(domFile,"nameShareContact").sendKeys("Test User");
                Mapper.find(domFile,"ContactShareButton").click();
            }
        }
        catch (Exception e){
            Mapper.find(domFile,"ContactShareButton").click();

        }
        return isElementPresent("SharedContact");
    }
    public void clickShareButton(){
        Mapper.waitForElementToBeClickable(domFile,"ShareButton");
        Mapper.find(domFile,"ShareButton").click();
    }

    public boolean isShareButtonavailable(){
        return isElementPresent("ShareIcon");
    }

    public void chatAction(String action){

        if(Mapper.waitForElementToBeClickable(domFile,"dismissNotification")==true) {
            Mapper.find(domFile, "dismissNotification").click();
        }
        if(Mapper.waitForElementToBeClickable(domFile,"chatAction")==true) {
            Mapper.find(domFile, "chatAction").click();
        }
        //Select chat=new Select(Mapper.find(domFile,"chatAction"));
        Mapper.find(domFile,"DeleteChat").click();
        //chat.selectByValue(action);
    }

    public void deleteChatConfirmation(){
        Mapper.find(domFile,"YesButtonDeleteChat").click();
    }

    public boolean isMessageDeleted(){
        return Mapper.find(domFile,"MessageDeleteChat").isDisplayed();
    }

    public String chatTimings() {
        Mapper.waitForElementToBeVisible(domFile, "ChatTimings");
        return Mapper.find(domFile, "ChatTimings").getText();

    }
}
