package com.quikr.website.realEstate.realEstateHome;

import com.quikr.utils.HarAnalyzer;
import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import net.lightbody.bmp.core.har.Har;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 31/8/15.
 */
public class RealEstateHomeTests extends TestBase{

    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("REAL_ESTATE_TESTDATA_FILE"));

    /* WEB-577:City selection popup
    */
    //@Test(groups = {"ProdHorizontal"})
    public void verifyCitySelectionPopup()
    {
        HomePage homePage = new HomePage(driver);
        RealEstateHomePage realEstateHomePage =new RealEstateHomePage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("quikrhomeNewUIcookie"));
        homePage.selectRealEstateOptionFromHome();
        realEstateHomePage.selectCity(testData.get("city"));
        realEstateHomePage.clickloginQuikrHomes();
        Assert.assertEquals(realEstateHomePage.verifyLogintext(), "Recommended. And we will never post anything without your permission.", "Failed to load Pop Up");
    }

    /*WEB-579:Login on Home page
    */
    //@Test(groups = {"ProdHorizontal"})
    public void verifyLoginPopup()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        RealEstateHomePage realEstateHomePage =new RealEstateHomePage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("quikrhomeNewUIcookie"));
        homePage.selectRealEstateOptionFromHome();
        String parentWindowHandle = headerPage.returnCurrentWindowHandle();
        realEstateHomePage.selectCity(testData.get("city"));
        realEstateHomePage.clickloginQuikrHomes();
        Assert.assertEquals(realEstateHomePage.verifyLogintext(), "Recommended. And we will never post anything without your permission.", "Failed to load Pop Up");
        headerPage.switchtoPopup(headerPage.clickFacebooklogin());
        headerPage.enterfacebookdetails(testData.get("facebookemail"), testData.get("facebookpassword"));
        headerPage.clickfacebookloginbutton();
        headerPage.switchtoParentfromPopUp(parentWindowHandle);
        Assert.assertTrue(realEstateHomePage.verifyAfterLoginIcon(), "Failed to Login");
    }

    /*WEB-580:Post free ad
    */
    //@Test(groups = {"ProdHorizontal"})
    public void verifyPostFreeAdQuikrHomes()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        RealEstateHomePage realEstateHomePage =new RealEstateHomePage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("quikrhomeNewUIcookie"));
        homePage.selectRealEstateOptionFromHome();
        String parentWindowHandle = homePage.returnCurrentWindowHandle();
        realEstateHomePage.selectCity(testData.get("city"));
        realEstateHomePage.clickloginQuikrHomes();
        Assert.assertEquals(realEstateHomePage.verifyLogintext(), "Recommended. And we will never post anything without your permission.", "Failed to load Pop Up");
        homePage.switchtoPopup(headerPage.clickFacebooklogin());
        headerPage.enterfacebookdetails(testData.get("facebookemail"), testData.get("facebookpassword"));
        headerPage.clickfacebookloginbutton();
        homePage.switchtoParentfromPopUp(parentWindowHandle);
        Assert.assertTrue(realEstateHomePage.verifyAfterLoginIcon(), "Failed to Login");
        realEstateHomePage.clickPostFreeAd();
        realEstateHomePage.postadBasicInformation(testData.get("city"), testData.get("ProjectName"), testData.get("area"), testData.get("price"), testData.get("rate"), testData.get("locality"), testData.get("basicinformation_streetaddress"));
        realEstateHomePage.postadPropertyDetails(testData.get("propertydetails_floorNumber"), testData.get("propertydetails_buildingname"), testData.get("propertydetails_flatnumber"));
        realEstateHomePage.clickavailablefrom();
        realEstateHomePage.clickContinueButtonPropertyDetails();
        realEstateHomePage.postadPhotoandMedia(testData.get("photomedia_adtitle"), testData.get("photomedia_addesc"));
        realEstateHomePage.clickContinueButtonPhotomedia();
        Assert.assertEquals(realEstateHomePage.verifyPostAdMessage(), "Thank you for posting your Ad on Quikr Homes", "Failed to Post Ad");

    }

    /* WEB-581:Auto suggestions on homepage
    */
    //@Test(groups = {"ProdHorizontal"})
    public void verifyAutoSuggestion()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        RealEstateHomePage realEstateHomePage =new RealEstateHomePage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("quikrhomeNewUIcookie"));
        homePage.selectRealEstateOptionFromHome();
        String parentWindowHandle = homePage.returnCurrentWindowHandle();
        realEstateHomePage.selectCity(testData.get("city"));
        realEstateHomePage.clickloginQuikrHomes();
        Assert.assertEquals(realEstateHomePage.verifyLogintext(), "Recommended. And we will never post anything without your permission.", "Failed to load Pop Up");
        homePage.switchtoPopup(headerPage.clickFacebooklogin());
        headerPage.enterfacebookdetails(testData.get("facebookemail"), testData.get("facebookpassword"));
        headerPage.clickfacebookloginbutton();
        homePage.switchtoParentfromPopUp(parentWindowHandle);
        Assert.assertTrue(realEstateHomePage.verifyAfterLoginIcon(), "Failed to Login");
        Assert.assertTrue(realEstateHomePage.verifyAutoSuggestionQuikrHomes(testData.get("searchquery")), "Failed to load matching suggestion ");
        realEstateHomePage.clickAutoSuggestionQuikrHomes(testData.get("searchquery"));
        Assert.assertTrue(realEstateHomePage.verifySNBPageloaded(), "Failed to load SNB page");
    }

   /* WEB-582:SNB page
   */
    //@Test(groups = {"ProdHorizontal"})
    public void verifySNBPage()
    {
        HomePage homePage = new HomePage(driver);
        RealEstateHomePage realEstateHomePage =new RealEstateHomePage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("quikrhomeNewUIcookie"));
        homePage.selectRealEstateOptionFromHome();
        realEstateHomePage.selectCity(testData.get("city"));
        Assert.assertTrue(realEstateHomePage.verifyAutoSuggestionQuikrHomes(testData.get("searchquery")), "Failed to load matching suggestion ");
        realEstateHomePage.clickAutoSuggestionQuikrHomes(testData.get("searchquery"));
        Assert.assertTrue(realEstateHomePage.verifySNBPageloaded(), "Failed to load SNB page");
        Assert.assertTrue(realEstateHomePage.verifySNBPageFlitersandMaps(),"Failed to Load filters or Maps");
    }
    /* WEB-576:Home page Image carousal
    */
    //@Test(groups = {"ProdHorizontal"})
    public void verifytimeloaded()
    {
        HomePage homePage = new HomePage(driver);
        RealEstateHomePage realEstateHomePage =new RealEstateHomePage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("quikrhomeNewUIcookie"));
        homePage.selectRealEstateOptionFromHome();
        realEstateHomePage.selectCity(testData.get("city"));
        Assert.assertTrue(realEstateHomePage.timefind(),"Images carousal not moving");
    }

    /*WEB-598:Reply : SNB page
    */
    //@Test(groups = {"ProdHorizontal"})
    public void replySNBPage()
    {
        HomePage homePage = new HomePage(driver);
        RealEstateHomePage realEstateHomePage =new RealEstateHomePage(driver);

        createorEditCookieValue(testData.get("cookiename"), testData.get("quikrhomeNewUIcookie"));
        homePage.selectRealEstateOptionFromHome();
        realEstateHomePage.selectCity(testData.get("Bangalorecity"));
        Assert.assertTrue(realEstateHomePage.verifyAutoSuggestionQuikrHomes(testData.get("search_Kormangla")), "Failed to load matching suggestion ");
        realEstateHomePage.clickAutoSuggestionQuikrHomes(testData.get("search_Kormangla"));
        Assert.assertTrue(realEstateHomePage.verifySNBPageloaded(), "Failed to load SNB page");
        Assert.assertTrue(realEstateHomePage.verifyReplyads(),"Ads with Reply option not available");

    }

    @Test
    public void checkUtmpHomePage()
    {
        navigatethirdparty(driver, testData.get("homesHomePageUrl"));
        waitForPageToLoad(testData.get("homesHomePageUrl"));
        HarAnalyzer harAnalyzer = new HarAnalyzer(proxyServer);
        Har har = harAnalyzer.getHar();
        List<String> urls = harAnalyzer.getRequestUrlsFull(har, testData.get("analyticsUrl"));
        boolean cond = false;

        for (String url : urls)
        {
            if(url.contains(testData.get("utmpValue")))
            {
                cond = true;
                break;
            }
        }

        Assert.assertTrue(cond, "Not able to find utmp value : " + testData.get("utmpValue"));
    }
}
