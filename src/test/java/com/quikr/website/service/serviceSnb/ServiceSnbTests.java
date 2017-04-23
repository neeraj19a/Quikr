package com.quikr.website.service.serviceSnb;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.service.serviceHomepage.ServiceHomepage;
import com.quikr.website.service.serviceSNB.ServiceSNBpage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 12/10/15.
 */
public class ServiceSnbTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("SERVICES_TESTDATA_FILE"));

    /*
    This test is obsolete due to absence of the feature.
     */
    /*@Test
    public void validateChatOnSnb()
    {
        HomePage homePage = new HomePage(driver);
        ChatPage chatPage = new ChatPage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        serviceHomepage.searchOnServicesHomepage(testData.get("searchService"), testData.get("locality"));
        chatPage.validateChatOnSnb(testData.get("chatName"), testData.get("chatNumber"), testData.get("email"), testData.get("chatContent"));
        Assert.assertEquals(testData.get("chatContent"), chatPage.validatechat(), "Not able to chat");
    }
    */
    /*
    This test is obsolete due to absence of the feature.
     */
    /*@Test
    public void validateReplyOnSnb()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsSnbPage snbPage = new ElectronicsSnbPage(driver);
        ServicePage servicePage = new ServicePage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        servicePage.selectServicesSubCategory(testData.get("subCategory"));
        snbPage.hideQuikrAdPopUp();
        waitForPageToLoad(testData.get("subCategory"));
        snbPage.clickOnReplyButton();
        Assert.assertTrue(snbPage.replyContent());
        Assert.assertTrue(snbPage.replyEmail());
        Assert.assertTrue(snbPage.replyNumber());
        Assert.assertTrue(snbPage.replyCaptcha());
    }
    */

    /*
    fixed
     */
    @Test
    public void verifyServicesSubcategoryName()
    {
        HomePage homePage = new HomePage(driver);
        ServiceSNBpage serviceSNBpage = new ServiceSNBpage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        serviceHomepage.searchFromServiceHomepage(testData.get("searchService"), testData.get("searchServiceLocation"));
        waitForPageToLoad(testData.get("searchService"));
        Assert.assertTrue(serviceSNBpage.validateSnbResults(testData.get("searchService")), "Snb result not as expected.");
    }

    /*
     WEB-568:Search functionality on SnB Page
     Fixed
     */
    @Test()
    public void searchFunctionalitySnb()
    {
        HomePage homePage = new HomePage(driver);
        ServiceSNBpage serviceSNBpage = new ServiceSNBpage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        serviceHomepage.searchFromServiceHomepage(testData.get("searchService"), testData.get("searchServiceLocation"));
        waitForPageToLoad(testData.get("searchService"));
        serviceSNBpage.getTheAdCounts();
        serviceHomepage.closeQuikrConnectPopUp();
        Assert.assertTrue(serviceSNBpage.validateSnbResults(testData.get("searchService")), "Search result doesn't have even one-fourth of successful match.");
    }

    /*
    Fixed
     */
    @Test
    public void verifyPremiumAdAndNonPremiumAdOnSnbPage()
    {
        HomePage homePage = new HomePage(driver);
        ServiceSNBpage serviceSNBpage = new ServiceSNBpage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        serviceHomepage.searchFromServiceHomepage(testData.get("searchService"), testData.get("searchServiceLocation"));
        waitForPageToLoad(testData.get("searchService"));
        Assert.assertTrue(serviceSNBpage.validatePremiumAdsAndNonPremiumAds(), "Premium ads are not present!!!");
    }

    /*
    WEB-219:SNB Page - Filter : Locality
     */
    @Test
    public void verifyFilterLocality()
    {
        HomePage homePage = new HomePage(driver);
        ServiceSNBpage serviceSNBpage = new ServiceSNBpage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        serviceHomepage.searchFromServiceHomepage(testData.get("searchService"), testData.get("searchServiceLocation"));
        waitForPageToLoad(testData.get("searchService"));
        serviceSNBpage.getTheAdCounts();
        serviceHomepage.closeConnectNowOverlay();
        Assert.assertTrue(serviceSNBpage.validateSnbResults(testData.get("searchService")), "Search result doesn't have even one-fourth of successful match.");
        Assert.assertTrue(serviceSNBpage.checkLocalityInSearchRes(testData.get("searchServiceLocation")), "Search results have different location then the one searched for.");
    }

    /*
    WEB-553:Search Section on Home Page
     */
    @Test
    public void verifySearchSectionHomepage()
    {
        HomePage homePage = new HomePage(driver);
        ServiceSNBpage serviceSNBpage = new ServiceSNBpage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        serviceHomepage.searchFromServiceHomepage(testData.get("searchService"), testData.get("searchServiceLocation"));
        waitForPageToLoad(testData.get("searchService"));
        serviceSNBpage.getTheAdCounts();
        serviceHomepage.closeConnectNowOverlay();
        Assert.assertTrue(serviceSNBpage.validateSnbResults(testData.get("searchService")), "Search result doesn't have even one-fourth of successful match.");
        Assert.assertTrue(serviceSNBpage.checkLocalityInSearchRes(testData.get("searchServiceLocation")), "Location doesn't match.");
    }

    /*
     WEB-554:Search section without locality on Home Page
     */
    @Test
    public void verifySearchResultWithoutLocality()
    {
        HomePage homePage = new HomePage(driver);
        ServiceSNBpage serviceSNBpage = new ServiceSNBpage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        serviceHomepage.selectFirstOptionSearchDropdown(testData.get("searchService"));
        serviceHomepage.clickSubmitButton();
        waitForPageToLoad(testData.get("searchService"));
        serviceSNBpage.getTheAdCounts();
        serviceHomepage.closeConnectNowOverlay();
        Assert.assertTrue(serviceSNBpage.validateSnbResults(testData.get("searchService")), "Search result doesn't have even one-fourth of successful match.");
    }

    /*
    WEB-557:Home Services on service home page
    Incomplete. A test has been left out. Will be completed in due time.
     */
    @Test
    public void verifyHomeServices()
    {
        HomePage homePage = new HomePage(driver);
        ServiceSNBpage serviceSNBpage = new ServiceSNBpage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        Assert.assertTrue(serviceHomepage.checkCountHomeServicesCategories(), "All eleven categories are not displayed.");
    }

}
