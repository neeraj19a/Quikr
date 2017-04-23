package com.quikr.website.mail;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 4/3/16.
 */
public class GmailPage extends PageBase {

    public GmailPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("GMAIL_DOM_FILE");

    public void loginIntoGmailAccount(String emailid, String password){
        if(Mapper.waitForElementToBeClickable(domFile,"gmailEmail")==true){
            Mapper.find(domFile,"gmailEmail").click();
            Mapper.find(domFile,"gmailEmail").sendKeys(emailid);
            Mapper.find(domFile,"gmailNext").click();
            Mapper.find(domFile,"gmailPassword").click();
            Mapper.find(domFile,"gmailPassword").sendKeys(password);
            Mapper.find(domFile,"gmailSignIn").click();
        }

    }

    public String clickonInboxMail( String adTitleSubstring){

        System.out.println("Path is "+Mapper.findAndReplace(domFile, "gmailInboxMailSubject", new String[]{adTitleSubstring}));
        WebElement email=Mapper.findAndReplace(domFile, "gmailInboxMailSubject", new String[]{adTitleSubstring});
        String emailtext=email.getText();
        email.click();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return emailtext ;
    }

    public boolean isEmailavailable(String adTitle){
        String emailHeading=null;
        if(Mapper.waitForElementToBeVisible(domFile,"gmailInboxMailHeading")==true){
            //Email Heading appears in the Form Your ad posted on Quikr is under review (“Ad Title...”)
            emailHeading=Mapper.find(domFile, "gmailInboxMailHeading").getText();
        }
        System.out.println("Email Heading is"+emailHeading);
        //Following line of code extracts the actual Ad Title from the Email Heading
        String emailHeadingSubString=emailHeading.substring(emailHeading.indexOf("(")+2,emailHeading.indexOf("."));
        /*System.out.println("AdTitle now here is "+adTitle);
        System.out.println("EmailHeadingSubString now here is "+emailHeadingSubString);
        System.out.println("COntains is"+adTitle.contains(emailHeadingSubString));
        */
        if(adTitle.contains(emailHeadingSubString)){
            return true;
        }
        else {
            logger.info("Mail with Proper Title of the Ad is not coming in the Mail");
            return false;
        }
    }
}
