package com.quikr.website.horizontal.email;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.mail.GmailPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 4/3/16.
 */
public class EmailTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("EMAIL_TESTDATA_FILE"));
    @Test(groups = "horizontal")
    public void checkLoginGmail() {
        GmailPage gmailPage=new GmailPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        String username=getLoginCredential("quikrGeneralLogin").get("username");
        String password=getLoginCredential("quikrGeneralLogin").get("password");

        headerPage.letsLogin("",testData.get("city"),username,password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("cityUrl"));
        String adtitle=PostAd(testData.get("city"));
        //System.out.println("Here is the title"+ adtitle);
        navigatethirdparty(driver,"http://gmail.com/");
        waitForPageToLoad("accounts.google.com");
        gmailPage.loginIntoGmailAccount(username, password);
        if (waitForPageToLoad("mail.google.com")==false){
            refreshPage();
            waitForPageToLoad("mail.google.com");
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String adTitlesubstring=adtitle.substring(0, 20);
        //System.out.println("SubString"+adTitlesubstring);
        String emailtext=gmailPage.clickonInboxMail(adTitlesubstring);
        //System.out.println("Here is Email Text" + emailtext);
        Assert.assertTrue(gmailPage.isEmailavailable(adtitle),"Failed to Load Mail with Proper Title of the Ad ");
    }


}
