package com.quikr.website.mail;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 8/3/16.
 */
public class YopMailPage extends PageBase {
    public YopMailPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("YOPMAIL_DOM_FILE");

    public void logintoYopMailInbox(String LoginEmailId){
        Mapper.find(domFile,"emailInboxYopMail").click();
        Mapper.find(domFile,"emailInboxYopMail").clear();
        Mapper.find(domFile,"emailInboxYopMail").sendKeys(LoginEmailId);
        Mapper.find(domFile,"GoButtonYopMail").click();
    }

    public void clickEmail(){
        if(Mapper.waitForElementToBeClickable(domFile,"emailSubjectonYopMail")==true) {
            Mapper.find(domFile, "emailSubjectonYopMail");
        }
    }

    public void clickVerifyEmail(){
        if(Mapper.waitForElementToBeClickable(domFile,"verifyEmailQuikr")==true){
            //click is not working because of iframe issue in yopmail
            //Mapper.find(domFile,"verifyEmailQuikr").click();
            System.out.println("Here");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            openUrl(Mapper.find(domFile, "verifyEmailQuikr").getAttribute("href"));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void switchToIFrameMailInbox(){
        switchTo().frame("ifinbox");
    }


    public void switchToIFrameMailContent(){
        switchTo().frame("ifmail");
    }

    public void switchToIFrameInbox(){
        switchTo().frame("ifinbox");
    }


    public boolean isMailPresentinYopMailInbox(){
         if(Mapper.finds(domFile,"numberofMailsinYopMail").size()>0){
             return true;
        }
        else
             return false;
    }

    public void clickVerifyEmailSubject()
    {
        Mapper.find(domFile,"verifyEmailSubjectLine").click();
    }

    public void switchOutOfAllIFrames()
    {
        switchTo().defaultContent();
    }

    public void verifyMailYopMail(String loginUsername)
    {
        waitForPageToLoad("yopmail.com");
        logintoYopMailInbox(loginUsername);
        waitForPageToLoad("yopmail.com1");
        switchToIFrameInbox();
        clickVerifyEmailSubject();
        switchOutOfAllIFrames();
        switchToIFrameMailContent();
        clickVerifyEmail();
    }


}
