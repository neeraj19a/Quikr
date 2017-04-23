package com.quikr.website.horizontal.pap;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 30/5/16.
 */
public class CommunityandEventsPapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("PAP_TESTDATA_FILE"));

    @Test
    public void PostAdCommunityAndEventsWithAnnouncements(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.COMMUNITY, QuikrEnums.CommunitySubCat.ANNOUNCEMENTS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        String adTitle=getRandomString(5)+" This is to announce that we are organizing HALLOWEEN PARTY this October "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void PostAdCommunityandEventswithCarPoolBikeRide(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.COMMUNITY, QuikrEnums.CommunitySubCat.CAR_POOL_BIKE_RIDE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        String adTitle=getRandomString(5)+" I am offering CAR POOL Ride looking for one person to Join in POLO Car "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void PostAdCommunityandEventswithCharityDonateNGO(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.COMMUNITY, QuikrEnums.CommunitySubCat.CHARITY_DONATE_NGO);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        String adTitle=getRandomString(5)+" NGO(Save the Children) is organizing Charity - Donate Camp in Bannerghatta "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void PostAdCommunityandEventswithEvents(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        CommunityandEventsPap communityandEventsPap=new CommunityandEventsPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EVENTS, QuikrEnums.EventsSubCat.LIVE_EVENTS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        String adTitle=getRandomString(5)+" We are organizing a Technical Meetup in Salarpuria, Pls register soon"+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        communityandEventsPap.selectRandomEventType();
        communityandEventsPap.selectRandomEventStartDateandStartTime();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void PostAdCommunityandEventswithTenderNotices(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.COMMUNITY, QuikrEnums.CommunitySubCat.TENDER_NOTICES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        String adTitle=getRandomString(5)+" Annual sectional Contract for Zone A to G Solapur Division Supply of Building materials "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void PostAdCommunityandEventswithLostFound(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.COMMUNITY, QuikrEnums.CommunitySubCat.LOST_FOUND);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        String adTitle=getRandomString(5)+" LOST my 6 years old Pit Bull in Whitefield near kundhanhalli signal. "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }
}
