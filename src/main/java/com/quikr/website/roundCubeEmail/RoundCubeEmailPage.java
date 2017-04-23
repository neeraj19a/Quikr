package com.quikr.website.roundCubeEmail;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by francis.s@quikr on 6/4/16.
 */
public class RoundCubeEmailPage extends PageBase {



    private static final String domFile = getProperties().get("ROUND_CUBE_DOM_FILE");


    public void openRoundCube() {
        navigateTo().to("http://192.168.124.69/");
    }

    public RoundCubeEmailPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public void enterEmail(String email) {
        Mapper.find(domFile, "emailField").sendKeys(email);
    }

    public void enterPassword(String password) {
        Mapper.find(domFile, "passwordField").sendKeys(password);
    }

    public void submitLogin() {
        Mapper.find(domFile, "submitButton").click();
    }

    public int getEmailCount() {
        List<WebElement> mails = Mapper.finds(domFile, "allEmailCount");
        return mails.size();
    }

    public void waitForPageLoad() {
        Mapper.waitForElementToBeVisible(domFile, "homeLink");
    }

    public void refreshInbox() {
        Mapper.find(domFile, "refreshLink").click();
    }

    public String getFirstEmailTitle() {
        return Mapper.find(domFile, "firstEmailTitle").getText().trim();
    }

    public String getFirstEmailFromName() {
        return Mapper.find(domFile, "emailFrom").getText().trim();
    }

    public String emailTitle() {
        return Mapper.find(domFile, "emailTitle").getText().trim();
    }

    public String getFirstEmailTime() {

        return Mapper.find(domFile, "firstEmailTime").getText().trim();
    }

    public boolean newMailArrived(String emailTitle)  {

        String[] dayTime = getFirstEmailTime().split(" ");
        Date d1 = null;
        Date d2 = null;

        DateFormat day = new SimpleDateFormat("E");
        DateFormat time = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String emailDay = "Today";
        try {
            d1 = time.parse(time.format(date));
            d2 = time.parse(dayTime[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long difference = d1.getTime() - d2.getTime();
        long diffMinutes = difference / (60 * 1000) % 60;

        if (diffMinutes < 15 && emailDay.equalsIgnoreCase("Today") && emailTitle.equalsIgnoreCase(getFirstEmailTitle()))
            return true;
        else
            return false;

    }

    /**
     * wait for email 1=10 sec wait
     *
     * @param time
     * @param emailTitle
     * @throws InterruptedException
     * @throws ParseException
     */
    public void waitForMail(int time, String emailTitle) {

        int counter = 0;
        do {
            try {
                refreshInbox();
                waitForPageLoad();
                System.out.println("waiting for new mail");
                if (newMailArrived(emailTitle))
                    break;
                Thread.sleep(10000l);
                counter++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assert.fail("No new mail arrived");
        } while (counter < time) ;

    }

    public void openFirstEmail(){
        String url = Mapper.find(domFile,"firstEmailTitleLink").getAttribute("href").trim();
        navigateTo().to(url);

    }

    public String quikrXTrsckOrderLink(){
      return Mapper.find(domFile,"trackOrder").getAttribute("href").trim();
    }
}


