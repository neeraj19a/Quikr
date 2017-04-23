package com.quikr.website.horizontal.recommendsection;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;


/**
 * Created by quikr on 1/9/15.
 */
public class RecommendSectionPage extends PageBase
{
    public RecommendSectionPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("RECOMMEND_SECTION_DOM_FILE");

    public void clickRepliesToMyAds()
    {
        Mapper.find(domFile,"replytomyads").click();
    }

    public void clickViewAd()
    {
        Mapper.waitForElementToBeClickable(domFile,"viewad");
        Mapper.find(domFile,"viewad").click();

    }

    public void clickConvertToPremium()
    {
        Mapper.waitForElementToBeVisible(domFile,"converttopremium");
        Mapper.find(domFile,"converttopremium").click();
        Mapper.waitForElementToBeVisible(domFile, "converttopremiumheader");
    }

    public void clickEditAd()
    {
        Mapper.waitForElementToBeClickable(domFile,"editad");
        Mapper.find(domFile,"editad").click();
        Mapper.waitForElementToBeVisible(domFile, "editadheader");
    }


    public void clickDeleteAd()
    {
        Mapper.waitForElementToBeClickable(domFile,"deletead");
        Mapper.find(domFile,"deletead").click();
        Mapper.waitForElementToBeVisible(domFile, "deleteadheader");
    }

//    public String notificationbelltext()
    public String getRecommendedForYouText()
    {
        String text=Mapper.find(domFile,"recommended").getText();
        return text;
    }

    public boolean verifyShowMoreLink()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "showMoreLink")==true)
            return true;
        else
            return false;
    }


}
