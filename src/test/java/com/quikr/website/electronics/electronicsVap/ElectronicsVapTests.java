package com.quikr.website.electronics.electronicsVap;

import com.quikr.website.TestBase;
import com.quikr.website.electronics.electronicsSnb.ElectronicsSnbPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.horizontal.myquikr.MyQuikrPage;
import com.quikr.website.horizontal.recommendsection.RecommendSectionPage;
import com.quikr.website.mobilesandtablets.Mobilespage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra singh on 29/7/15.
 */
public class ElectronicsVapTests extends TestBase
{
    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("ELECTRONICS_AND_APPLIANCES_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    /**
     * validate Chat ON VAP
     * Created by swatantra singh on 29/7/15.
     */
    @Test(groups = "horizontal")
    public void ChatOnVap()
    {
        
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);

        headerPage.selectCity(testData.get("city"));
        logger.info("Selected City : " + testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("queryForChatOption"));
        waitForPageToLoad(testData.get("searchResultUrl"));
        String title = horizontalSnbPage.clickOnAdHavingChatOption();
        //waitForPageToLoad(title.split(" ")[0]);
        //chatPage.validateChatOnVAp(testData.get("chatNumber"), testData.get("chatContent"), testData.get("chatbody"), testData.get("chatName"), testData.get("chatEmail"));
    }

    /** test in recomended ad section similar ads should be displayed
     * Created by swatantra singh on 29/7/15.
     */
    /*WEB-27:Similar/Recommended Ads
    */
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void validateRecomendedAds()
    {
        /*HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("query"));
        waitForPageToLoad(testData.get("searchResultUrl"));
        horizontalSnbPage.OpenAd();
        Assert.assertTrue(electronicsVapPage.verifyrecomenedenAd(), "Matching recommended ads not displayed!");
 */   }

    /** test ads marked as favourite is saved
     * Created by swatantra singh on 29/7/15.
     */
    /*WEB-7:Add to Favourite - On click of "Save this Ad", Ad is saved in session
    */
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void validateMarkAsFavourite()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("query"));
        waitForPageToLoad(testData.get("searchResultUrl"));
        horizontalSnbPage.OpenAd();
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertTrue(electronicsVapPage.MarkAdAsFavourite(), "Ad saved when marked as favourite");
    }

    /*
    WEB-56:Reply from VAP
     */
    @Test(groups={"horizontal" ,"horizontalPRI0","dev"})
    public void replyFromVapPage()
    {
//        HeaderPage headerPage = new HeaderPage(driver);
//        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
//        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);
//
//        headerPage.search(testData.get("queryForReplyOption"));
//        waitForPageToLoad(testData.get("quikrURL"));
//        horizontalSnbPage.clickOnAdHavingReplyOption();
//        electronicsVapPage.clickReplyButton();
//        electronicsVapPage.setEmailAddressInReplyDialog(testData.get("chatEmail"));
//        electronicsVapPage.setMessageInReplyDialog(testData.get("chatContent"));
//        electronicsVapPage.clickSendButtonInReplyDialog();
//        waitForPageToLoad(testData.get("replySuccessUrl"));
//        Assert.assertTrue(electronicsVapPage.validateReplySuccess(), "Unable to successfuly reply to the ad");
    }

    /*
    WEB-58:Post Reply with Banned words (FnD)
     */
    @Test(groups={"ProdHorizontal","horizontalPRI0","UATHorizontal"})
    public void replyFromVapPageWithBannedWords()
    {
//        HomePage homePage = new HomePage(driver);
//        HeaderPage headerPage = new HeaderPage(driver);
//        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
//        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);
//
//        homePage.clickonCityCloseButton();
//        headerPage.search(testData.get("queryForReplyOption"));
//        waitForPageToLoad(testData.get("searchResultUrl"));
//        horizontalSnbPage.clickOnAdHavingReplyOption();
//        electronicsVapPage.clickOnReplyButton();
//        electronicsVapPage.setEmailAddressInReplyDialog(testData.get("chatEmail"));
//        electronicsVapPage.setMessageInReplyDialog(testData.get("messageContainingBannedWords"));
//        electronicsVapPage.clickSendButtonInReplyDialog();
//        waitForPageToLoad(testData.get("replySuccessUrl"));
//        Assert.assertTrue(electronicsVapPage.validateReplySuccess(), "Unable to successfuly reply to the ad");
    }


    /*WEB-452:Previous Ads & Next ads Links From VAP
    */
    @Test(groups={"horizontal" ,"horizontalPRI0"})
    public void previousAndNextAdsVAP()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("queryForReplyOption"));
        waitForPageToLoad(testData.get("searchResultUrl"));
        electronicsSnbPage.clickOnAd(20);
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertTrue(electronicsVapPage.isPrevandNextButtonvisible(), "Failed to Load Previous and Next Button on Electronics VAP Page");
    }

    @Title("WEB-453:Share to Facebook, Twitter & Hangout from VAP")
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void socialMediaShare()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        headerPage.letsLogin("randomPageLoginResponsive", "", username, password);
        headerPage.clickOnQuikrLogo();
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("quikrURL"));
        horizontalSnbPage.SelectMobileCategory();
        waitForPageToLoad(testData.get("mobile"));
        horizontalSnbPage.clickOnAd(6);
        waitForPageToLoad(testData.get("quikrURL"));
        Assert.assertTrue(electronicsVapPage.verifySocialMediaButtons(), "Probably the social media sharing is not working. Please check!");
    }

    /**
     * WEB-454:Save ads on cookie delete should get remove.
     */
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void verifyAdRemovalOnCookieDeletion() throws  Exception
    {
//        HomePage homePage = new HomePage(driver);
//        HeaderPage headerPage = new HeaderPage(driver);
//        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
//        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);
//
//        headerPage.selectCity(testData.get("city"));
//        waitForPageToLoad(testData.get("cityUrl"));
//        headerPage.loginWithCity(username,password);
//        headerPage.clickOnQuikrLogo();
//        homePage.SelectsMobiles();
//        horizontalSnbPage.SelectMobileCategory();
//        electronicsVapPage.clickFirstAd();
//        electronicsVapPage.addToFavMobiles();
//        Assert.assertTrue(electronicsVapPage.verifyAdSaveMobileVap(), "Ad is not saved at first place. So next assertion was ignored.");
//        Assert.assertTrue((electronicsVapPage.verifySavedAdOnCookieDeletion()), "Ad not deleted even after cookies were deleted. Please check");
    }
    /** Fixed added function to find Ad having Show More and Show Less Link
     * WEB-456:For larger descriptions ads , Show more & Show less
     *
     */
    //@Test(groups = {"horizontal","horizontalPRI0"})
    public void verifyShowMoreShowLess()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        //headerPage.letsLogin("randomPageLoginNonResponsive", (testData.get("city")), username, password);
        headerPage.letsLogin("",(testData.get("city")),username,password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("validURL"));
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("validURL"));
        horizontalSnbPage.SelectMobileCategory();
        waitForPageToLoad(testData.get("validURL"));
        horizontalSnbPage.clickOnAd(horizontalSnbPage.returnAdNumberhavingLongestTitle() + 1);
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(electronicsVapPage.verifyShowMoreAndLessLink(), "Probably the ad doesn't have a show more/less link. Please check!");
    }

    /**
     * WEB-459:To Verify Ads By Google section on VAP
     */
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void verifyGoogleAds()
    {
        /*HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.loginWithCity(username,password);
        headerPage.clickOnQuikrLogo();
        homePage.SelectsMobiles();
        horizontalSnbPage.SelectMobileCategory();
        electronicsVapPage.clickFirstAd();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(verifyGoogleAds(testData.get("vapGoogleAdIFrameId")), "Probably google ads are not being displayed. Please check!");
 */   }

    /**
     * WEB-473:Change Category from, Category Based Home Page
     */
    @Test(groups = "horizontal")
    public void verifyCategorychange()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        //headerPage.letsLogin("randomPageLoginNonResponsive", (testData.get("city")), username, password);
        headerPage.letsLogin("",(testData.get("city")),username,password);
        headerPage.clickOnQuikrLogo();
        homePage.SelectsMobiles();
        headerPage.clickOnQuikrLogo();
        homePage.selectElectronicsAndAppliances();
        Assert.assertTrue(electronicsVapPage.verifyCategoryChange(), "Category not changed, not reflected in url.");
    }

    /**
     * WEB-472:Browse by attribute from, Category Based Home Page
     */
    @Test(groups = {"horizontal","dev"})
    public void verifyBrowseAttribute()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        Mobilespage mobilespage = new Mobilespage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        headerPage.letsLogin("", (testData.get("bhopalCity")), username, password);
        headerPage.clickOnQuikrLogo();
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("bhopalCity").toLowerCase());
        horizontalSnbPage.SelectMobileCategory();
        waitForPageToLoad(testData.get("mobile"));

        String brandToSearch = testData.get("brandSearch");
        mobilespage.selectMobilewithBrandFilter(brandToSearch);
        waitForPageToLoad(testData.get("mobile"));
        Assert.assertTrue(electronicsVapPage.verifyUrl(brandToSearch), "The url doesn't reflect the brand searched. Please check!");
    }

    /**
     * WEB-471:Browse Subcategory from, Category Based Home Page
     */
    @Test(groups = {"horizontal","dev"})
    public void verifyBrowseSubCategory()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        headerPage.letsLogin("",(testData.get("bhopalCity")),username,password);
        headerPage.clickOnQuikrLogo();
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        horizontalSnbPage.SelectMobileCategory();
        Assert.assertTrue(electronicsVapPage.verifyUrl("Mobile-Phones"), "The url doesn't reflect the subcategory browsed. Please check!");

    }

    /**
     * WEB-470:Go to Quikr Home Page link from Recommended-section
     */
    @Test(groups = "horizontal")
    public void verifyNavigationToHomepage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        //headerPage.letsLogin("randomPageLoginNonResponsive", "", username, password);
        headerPage.letsLogin("randomPageLoginResponsive","",username,password);
        headerPage.clickOnQuikrLogo();
        homePage.SelectsMobiles();
        horizontalSnbPage.SelectMobileCategory();
        electronicsVapPage.clickFirstAd();
        Assert.assertTrue(electronicsVapPage.verifyUrl("quikr.com"), "Probably its not the homepage.");
    }

    /**
     * WEB-457:Bread crums navigation from VAP,
     */
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void verifyBreadcrumbsNavigation()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);

        headerPage.letsLogin("",(testData.get("bhopalCity")),username,password);
        headerPage.clickOnQuikrLogo();
        homePage.SelectsMobiles();
        waitForPageToLoad(testData.get("bhopalcityUrl"));
        horizontalSnbPage.SelectMobileCategory();
        Assert.assertTrue(electronicsVapPage.verifyBreadcrumbs(), "Breadcrumbs not visible.");
    }

    /**
     * WEB-455:Multiple save ads , Remove one ad from list (x) & from remove all.
     */
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void verifyRemovalOfSavedAds()
    {
//        HomePage homePage = new HomePage(driver);
//        HeaderPage headerPage = new HeaderPage(driver);
//        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
//        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);
//
//        headerPage.selectCity(testData.get("city"));
//        waitForPageToLoad(testData.get("cityUrl"));
//        headerPage.loginWithCity(username,password);
//        headerPage.clickOnQuikrLogo();
//        waitForPageToLoad(testData.get("cityUrl"));
//        homePage.SelectsMobiles();
//        horizontalSnbPage.SelectMobileCategory();
//        electronicsVapPage.clickFirstAd();
//        electronicsVapPage.addToFavMobiles();
//        Assert.assertTrue(electronicsVapPage.deleteSavedAdWithCrossButton(), "Ad not deleted with cross button.");
//        electronicsVapPage.addToFavMobiles();
//        Assert.assertTrue(electronicsVapPage.deleteSavedAdWithRemoveAll(), "Ad not deleted with remove All link button.");
    }

    /**
     * WEB-469:Show More link (Below listed ads), From Ads Matching my Alerts section.
     */
    @Test(groups = "horizontal")
    public void verifyMorelLinkRecommendedSection()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);
        RecommendSectionPage rp = new RecommendSectionPage(driver);

        headerPage.letsLogin("randomPageLoginNonResponsive", (testData.get("city")), username, password);
        headerPage.clickOnQuikrLogo();
        myQuikrPage.navigateToRecommendedAdForMe();
        waitForPageToLoad(testData.get("cityUrl"));
        scrollVerticallWithCords(0, 10000);
        Assert.assertTrue(rp.verifyShowMoreLink(), "Show more link is not visible. Please check!");
    }

    /*WEB-458:VAP Reply to premium ads
    */
    @Test(groups = "horizontal")
    public void validateReplyToPremiumAds()
    {
//        HomePage homePage = new HomePage(driver);
//        HeaderPage headerPage = new HeaderPage(driver);
//        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
//        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);
//
//        headerPage.selectCity(testData.get("city"));
//        waitForPageToLoad(testData.get("cityUrl"));
//        headerPage.loginWithCity(username,password);
//        headerPage.clickOnQuikrLogo();
//        waitForPageToLoad(testData.get("cityUrl"));
//        headerPage.search(testData.get("queryforpremium"));
//        Assert.assertTrue(horizontalSnbPage.clickPremiumAdswithReplySNB(), "Failed to Load premium Ads with Reply Option");
//        Assert.assertTrue(electronicsVapPage.verifyReplybutton(),"Probably the message was not sent successfully. Please check!");
    }

    /*WEB-460:VAP reply landing page, - to the Similar ads section, make a inline reply to ad.
    */
    /*WEB-10:Similar Ads -> Inline Reply: Reply is sent to Ad poster through email
    */
    @Test(groups = {"ProdHorizontal","UATHorizontal"})
    public void validateSimilarAdsSectionReplyToPremiumAds()
    {
//        HomePage homePage = new HomePage(driver);
//        HeaderPage headerPage = new HeaderPage(driver);
//        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
//        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);
//
//        waitForPageToLoad(testData.get("homeUrl"));
//        headerPage.loginWithCity(username,password);
//        headerPage.search(testData.get("query"));
//        Assert.assertTrue(horizontalSnbPage.checkadswithReplySNB(), "Failed to Load Ads with Reply Option");
//        horizontalSnbPage.clickAdswithReplySNB();
//        Assert.assertTrue(electronicsVapPage.verifyReplybuttonInVapisEnabled(), "Reply Button is not there");
//        Assert.assertTrue(electronicsVapPage.verifyReplybutton(), "Probably the message was not sent successfully. Please check!");
//        Assert.assertTrue(electronicsVapPage.verifyVapReplySimilarAds(), "Similar Ads Section not loaded after Replying on the Ad");
//        Assert.assertTrue(electronicsVapPage.verifyReplybuttonSimilarAds(), "Reply option for Similar Ads is not appearing so unable to make an Inline Reply");
//        Assert.assertTrue(horizontalSnbPage.checkadswithReplySNB(), "Failed to Load Ads with Reply Option on Recommended Ads or Similar Ads Section");
//        horizontalSnbPage.clickAdswithReplySNB();
//        Assert.assertTrue(electronicsVapPage.verifyReplybuttonInVapisEnabled(), "Reply Button is not there");
//        Assert.assertTrue(electronicsVapPage.verifyReplybutton(), "Probably the message was not sent successfully. Please check!");
    }

    /*WEB-461:VAP reply landing page, Sub category links redirection.
    */
    @Test(groups = {"ProdHorizontal","UATHorizontal","horizontalPRI0",})
    public void validateMoreAdsSectionReplyToPremiumAds()
    {
//        HomePage homePage = new HomePage(driver);
//        HeaderPage headerPage = new HeaderPage(driver);
//        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
//        ElectronicsVapPage electronicsVapPage =new ElectronicsVapPage(driver);
//
//        headerPage.selectCity(testData.get("city"));
//        waitForPageToLoad(testData.get("cityUrl"));
//        headerPage.loginWithCity(username,password);
//        headerPage.search(testData.get("query"));
//        Assert.assertTrue(horizontalSnbPage.checkadswithReplySNB(), "Failed to Load Ads with Reply Option");
//        horizontalSnbPage.clickAdswithReplySNB();
//        Assert.assertTrue(electronicsVapPage.verifyReplybuttonInVapisEnabled(), "Reply Button is not there");
//        Assert.assertTrue(electronicsVapPage.verifyReplybutton(), "Probably the message was not sent successfully. Please check!");
//        Assert.assertTrue(electronicsVapPage.verifyMoreAdsSectionisDisplayed(), "More Ads Section is not loaded after replying on the ads");
//        String moreadstext= electronicsVapPage.moreadstext();
//        electronicsVapPage.clickMoreAdsSection();
//        String categorytext= horizontalSnbPage.searchcategorytext();
//        Assert.assertEquals(moreadstext.substring(0, 4), categorytext.substring(0, 4), "Different Category Opened on Clicking More Ads ");
    }

    /**
     *  WEB-458:VAP Reply to premium ads
     */
    /*@Test(groups = "horizontal")
    public void verifyReplyToPremiumAds()
    {
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.loginWithCity(username,password);
        headerPage.clickOnQuikrLogo();
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        servicePage.selectServicesSubCategory(testData.get("subCategoryServices"));
        vapPage.clickFirstAd();
        Assert.assertTrue(vapPage.verifyReplybutton(), "Probably the message was not sent successfully. Please check!");
    }
*/

}
