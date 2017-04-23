package com.quikr.website.service.serviceHome;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.service.serviceHomepage.ServiceHomepage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 12/10/15.
 */
public class ServiceHomepageTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("SERVICES_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    @Test
    public void validateSearch()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.search(testData.get("query"));
        Assert.assertTrue(waitForPageToLoad(testData.get("query")));
    }

    @Test
    public void CreateAlert()
    {
        HomePage homePage = new HomePage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        serviceHomepage.createAlert();
        Assert.assertTrue(waitForPageToLoad("recommended-section"));
    }

    /*
    Seems obsolete. No alert creation feature in SNB.
     */
    /*@Test
    public void validateCreateAlertFromSnbForServices()
    {
        HomePage homePage = new HomePage(driver);
        ElectronicsSnbPage snbPage = new ElectronicsSnbPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        ServicePage servicePage = new ServicePage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        servicePage.selectServicesCategory(testData.get("doctors"));
        waitForPageToLoad(testData.get("doctorsUrl"));
        snbPage.selectCreateAlertButtonOnSnbPage();
        snbPage.setTypeOnSnBPage(testData.get("drType"));
        snbPage.setLocalityInAlertOnSnbPage(testData.get("locality"));
        alertPage.setEmail(testData.get("email"));
        alertPage.setPhoneNumber(testData.get("number"));
        snbPage.selectSubmitButtonOfCreateAlertOnSnbPage();
        waitForPageToLoad(testData.get("recommendationsUrl"));
        Assert.assertTrue(alertPage.validateCreateAlert(), "Alert can not be created successfully");
    }
    */

    /*
    WEB-552:Service Home Page Header as a LoggedIn User
    Fixed
     */
    @Test()
    public void verifyServiceHomepageHeader()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);
        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        //headerPage.login(username,password);
        clickOnQuikrLogo();
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        Assert.assertTrue(serviceHomepage.checkUserProfileLink(), "There is some problem with the user profile link. Please check!");
        Assert.assertTrue(serviceHomepage.checkPostAdBtn(), "Post ad button is not being displayed. Please check!");
        Assert.assertTrue(serviceHomepage.checkLanguageSelectLink(), "There is some problem with the language link. Please check!");
    }

    /*
    WEB-262:Services Home Page
     */
    @Test()
    public void verifyServiceHomepage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);
        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        //headerPage.login(username,password);
        clickOnQuikrLogo();
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        Assert.assertTrue(serviceHomepage.checkUserProfileLink(), "There is some problem with the user profile link. Please check!");
        Assert.assertTrue(serviceHomepage.checkPostAdBtn(), "Post ad button is not being displayed. Please check!");
        Assert.assertTrue(serviceHomepage.checkLanguageSelectLink(), "There is some problem with the language link. Please check!");
        Assert.assertTrue(serviceHomepage.checkSearchTextbox(), "Search textboxes not displayed. Please check!");
        Assert.assertTrue(serviceHomepage.checkHomeServices(), "HomeServices header not being displayed. Please check!");
        Assert.assertTrue(serviceHomepage.checkOtherCategories(), "Other catogeries are not being shown. Please check!");
    }

    /*
    WEB-556:Connect Now on Home page.
     */
    @Test()
    public void checkConnectNowButton()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);
        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        //headerPage.login(username,password);
        clickOnQuikrLogo();
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        Assert.assertTrue(serviceHomepage.checkConnectNowBtn(),"Connect now button is not displayed. Please check!");
    }

    /*
    WEB-555:Redirection of Connect Now on Home page.
     */
    @Test()
    public void verifyConnectNow()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);
        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        //headerPage.login(username,password);
        clickOnQuikrLogo();
        homePage.selectServicesOptionFromHome();
        waitForPageToLoad(testData.get("servicesUrl"));
        Assert.assertTrue(serviceHomepage.verifyConnectNow(),"Connect now button is not working as expected. Please check!");
    }

    /*
    WEB-572:Create Alert for top cities and top category listed
     */
    @Test()
    public void createAlertTopCities()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("city"));
        waitForPageToLoad(testData.get("hompage"));
        //headerPage.login(username,password);
        clickOnQuikrLogo();
        homePage.selectCategoryAlertCreation("Services");
        homePage.selectSubCategoryAlertCreation("Cooks");
        Assert.assertTrue(homePage.validateIWantACook(), "The text is not as expected. Please check!");
        homePage.selectLocalityAlertCreation("80 Ft. Road");
        homePage.clickSubmitAlertCreation();
        acceptAlert();
        Assert.assertTrue(waitForPageToLoad("Services"),"Services homepage was not loaded. Please check!");
    }

    /*
    WEB-573:Create Alert for Non Top Cities
     */
    @Test()
    public void createAlertNonTopCities()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ServiceHomepage serviceHomepage = new ServiceHomepage(driver);

        //homePage.SelectCity(testData.get("nonTopCity"));
        waitForPageToLoad(testData.get("nonTopCity"));
        //headerPage.login(username,password);
        clickOnQuikrLogo();
        homePage.selectCategoryAlertCreation("Services");
        homePage.selectSubCategoryAlertCreation("Cooks");
        Assert.assertTrue(homePage.validateIWantACook(), "The text is not as expected. Please check!");
        homePage.selectLocalityAlertCreation("Anna Nagar");
        homePage.clickSubmitAlertCreation();
        acceptAlert();
        Assert.assertTrue(waitForPageToLoad("Services"),"Services homepage was not loaded. Please check!");
    }
}
