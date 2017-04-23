package com.quikr.website.horizontal.home;

import com.quikr.website.TestBase;
import com.quikr.website.electronics.electronicsHome.ElectronicsHome;
import com.quikr.website.electronics.electronicsPap.ElectronicsPapPage;
import com.quikr.website.electronics.electronicsSnb.ElectronicsSnbPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.jobs.jobsHeader.JobsHeaderPage;
import com.quikr.website.jobs.jobsPAP.JobsPAPPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 19/1/16.
 */
public class HomeTests2 extends TestBase {


    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("HOME_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");


    @Title("WEB-42:Post free ad - with login")
    //@Test(groups = {"horizontal" ,"horizontalPRI0","dev"})
    public void postFreeAdWithLogin()
    {

            JobsPAPPage jobsPAPPage = new JobsPAPPage(driver);
            HomePage homePage = new HomePage(driver);
            HeaderPage headerPage = new HeaderPage(driver);
            JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);

            String adTitle = testData.get("adTitle");
            String role = testData.get("role");
            String desc = testData.get("description");

            headerPage.letsLogin("", (testData.get("city")),username, password);
            headerPage.clickOnQuikrLogo();
            homePage.selectJobsOptionFromHome();
            waitForPageToLoad(testData.get("NewJobsUrl"));
            jobsHeaderPage.clickPostAdbuttonpresent();
            waitForPageToLoad(testData.get("validURL"));

            jobsPAPPage.selectJobCategory("Part time");
            jobsPAPPage.postAdTitle(adTitle);
            jobsPAPPage.selectRole(role);
            jobsPAPPage.setMinSalary(testData.get("minSalary"));
            jobsPAPPage.setMaxSalary(testData.get("maxSalary"));
            jobsPAPPage.selectlLocality();
            jobsPAPPage.provideDescription(desc);
            jobsPAPPage.clickSubmitBtnJobPostAd();
            jobsPAPPage.clickAddToJobBtnJobPostAd();
            waitForPageToLoad(testData.get("validURL"));
            Assert.assertTrue(jobsPAPPage.isJobPosted(), "Failed to Load Successful Job Post Page");
        }


    @Title("WEB-43:Post premium ad - without login")
    //@Test(groups = {"horizontal" ,"horizontalPRI0","dev"})
    public void postPremiumAdWithoutLogin()
    {

        JobsPAPPage jobsPAPPage = new JobsPAPPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);

        String adTitle = testData.get("adTitle");
        String role = testData.get("role");
        String desc = testData.get("description");
        String email=getRandomString(8)+"@gmail.com";
        String randomNumber="99"+getRandomInteger(8);

        headerPage.selectCity(testData.get("city"));
        headerPage.clickOnQuikrLogo();
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("NewJobsUrl"));
        jobsHeaderPage.clickPostAdbuttonpresent();
        waitForPageToLoad(testData.get("validURL"));

        jobsPAPPage.selectJobCategory("Part time");
        jobsPAPPage.postAdTitle(adTitle);
        jobsPAPPage.selectRole(role);
        jobsPAPPage.setMinSalary(testData.get("minSalary"));
        jobsPAPPage.setMaxSalary(testData.get("maxSalary"));
        jobsPAPPage.selectlLocality();
        jobsPAPPage.setEmail(email);
        jobsPAPPage.setRandomMobNumber(randomNumber);
        jobsPAPPage.provideDescription(desc);
        jobsPAPPage.clickSubmitBtnJobPostAd();
        jobsPAPPage.clickAddToJobBtnJobPostAd();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(jobsPAPPage.isJobPosted(), "Failed to Load Successful Job Post Page");
    }
    /*
    WEB-12:Post premium ad - with login and online payment
    Cannot be automated as it involves interaction with payment gateway.
     */
    /*
    WEB-45:Post premium ad - with login and cheque payment
    */
    //Commenting as Login Cheque is no longer exist
    //@Test(groups = {"horizontal" ,"horizontalPRI0"})
    public void postpremiumAdWithLoginCheque()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        JobsPAPPage jobsPAPPage = new JobsPAPPage(driver);
        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        headerPage.selectCity(testData.get("CitySelect"));
        String adTitle = testData.get("adTitle");
        String role = testData.get("role");
        String edu = testData.get("education");
        String exp = testData.get("experience");
        String desc = testData.get("description");
        String email = testData.get("emailId");
        String num = testData.get("phoneNumber");
        String city = testData.get("city");
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("CitySelect"));
        homePage.clickPostFreeAdMyQuikrPage();
        waitForPageToLoad(testData.get("CitySelect"));
        electronicsPapPage.selectCategory(testData.get("category"));
        //electronicsPapPage.selectSubCategory(testData.get("subcategory"));
        waitForPageToLoad(testData.get("CitySelect"));
        jobsPAPPage.postAdTitle(adTitle);
        jobsPAPPage.selectRole(role);
        jobsPAPPage.selectEducation(edu);
        jobsPAPPage.selectExperience(exp);
        jobsPAPPage.setCity(city);
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(desc);
        //jobsPAPPage.setDummyEmail(email);
        jobsPAPPage.setRandomMobNumber(num);
        jobsPAPPage.selectPremiumAd();
        jobsPAPPage.clickPostAdButton();
        waitForPageToLoad(testData.get("CitySelect"));
        Assert.assertTrue(electronicsPapPage.validatePaymentPageavailable(), "Premium ad not posted as expected. Please check!");
    }
    /*
    WEB-15:Post ad with banned words
     */
    //@Test(groups = {"horizontal" ,"horizontalPRI0","dev"})
    public void postAdWithBannedWords()
    {
        JobsPAPPage jobsPAPPage = new JobsPAPPage(driver);
        HomePage homePage = new HomePage(driver);
        JobsHeaderPage jobsHeaderPage=new JobsHeaderPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        String adTitle = testData.get("adTitle");
        String bannedWords = testData.get("bannedWords");
        String role = testData.get("role");
        String edu = testData.get("education");
        String exp = testData.get("experience");
        String desc = testData.get("description");
        String mob = testData.get("number");
        headerPage.selectCity(testData.get("city"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.selectJobsOptionFromHome();
        waitForPageToLoad(testData.get("validURL"));
        jobsHeaderPage.clickPostAdbuttonpresent();
        waitForPageToLoad(testData.get("validURL"));
        jobsPAPPage.postAdTitle(adTitle + " " + bannedWords);
        jobsPAPPage.selectRole(role);
        jobsPAPPage.selectEducation(edu);
        jobsPAPPage.selectExperience(exp);
        jobsPAPPage.selectlLocality();
        jobsPAPPage.provideDescription(desc + " " + bannedWords);
        jobsPAPPage.setRandomMobNumber(mob);
        jobsPAPPage.clickPostAdButton();
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertEquals(jobsPAPPage.isJobPosted(), false, "Able to Post Ad with Banned Words");
    }


    @Title("WEB-732:Display of only Online Users Ad.")
    //Commenting as discussed Online Users toggle does not create any difference on SNB
    //@Test(groups = "ProdHorizontal")
    public void validateDisplayOnlineUsersOnly()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ElectronicsHome electronicsHome =new ElectronicsHome(driver);
        ElectronicsSnbPage electronicsSnbPage = new ElectronicsSnbPage(driver);

        headerPage.letsLogin("", (testData.get("city")), username, password);
        clickOnQuikrLogo();
        waitForPageToLoad(testData.get("city").toLowerCase());
        homePage.selectElectronicsAndAppliances();
        electronicsHome.clickLaptopsComputeers();
        electronicsSnbPage.toggleOnlineUsers();
        waitForPageToLoad("?online=1");
        Assert.assertTrue(electronicsSnbPage.validateAllOnlineUsers(), "It still has offline users. Please check!");
    }

    @Title("WEB-500:Able to see new home page design on launching www.quikr.com")
    @Test(groups = "horizontal")
    public void validateNewHomepageUI()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        homePage.navigateToNewUI(testData.get("newUIUrl"));
        waitForPageToLoad(testData.get("newUICityUrl"));
        Assert.assertTrue(homePage.checkNewUI(),"New UI hasn't been loaded. Please check!");
    }
    /*
    WEB-871:Search Query – “2bhk for rent”: No city/locality in the query
     */
    @Test(groups = "horizontal")
    public void cityAgnosticSearchNoCity()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);

        //homePage.clickonCityCloseButton();
        headerPage.search(testData.get("cityAgnosticSearchQuery"));
        String capturedCity = homePage.checkCityPresence();
        Assert.assertTrue(waitForPageToLoad(capturedCity.toLowerCase()), "City from the ad title is not in the URL. Please check!");
    }

    /*
    WEB-872:search query – “2bhk for rent in Bangalore”: City in the query
     */
    @Test(groups = "horizontal")
    public void cityAgnosticSearchWithCity()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        headerPage.selectCity(testData.get("city"));
        headerPage.search(testData.get("cityAgnosticSearchQuery")+testData.get("cityAgnosticSearchQueryCityName"));
        homePage.checkCityPresence();
        Assert.assertTrue(waitForPageToLoad(testData.get("cityUrl").toLowerCase()), "City from the ad title is not in the URL. Please check!");
    }
    /*
    WEB-873:search query – “2bhk for rent in Indiranagar”: Only Locality in the query
     */
    @Test(groups = "horizontal")
    public void cityAgnosticSearchWithLocality()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);

        //homePage.clickonCityCloseButton();
        headerPage.search(testData.get("cityAgnosticSearchQuery") + testData.get("cityAgnosticSearchQueryLocalityName"));
        homePage.checkCityPresence();
        Assert.assertTrue(waitForPageToLoad(testData.get("cityPanajiCorrseponsingToLocality").toLowerCase()), "City from the ad title is not in the URL. Please check!");
    }
    /*
    WEB-875:search query – “2bhk for rent in Indiranagar”: Locality in the query
     */
    @Test(groups="horizontal")
    public void cityAgnosticSearchWithLocalityCitySelected()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);

        String loc = testData.get("cityAgnosticSearchQueryLocalityName");
        headerPage.selectCity(testData.get("city"));
        headerPage.search(testData.get("cityAgnosticSearchQuery") + loc);
        waitForPageToLoad(testData.get("cityUrl"));
        Assert.assertTrue(homePage.checkLocInChicklet(loc),"Relevant locality not being shown. Please check!");
    }
    /*
    WEB-876:search query – “2bhk for rent in Delhi”: different City in the query
     */
    @Test(groups = "horizontal")
    public void cityAgnosticSearchWithDifferentCity()
    {
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);
        headerPage.selectCity(testData.get("city"));
        headerPage.search(testData.get("cityAgnosticSearchQuery") + testData.get("cityAgnosticSearchQueryAnotherCityName"));
        Assert.assertTrue(homePage.checkSpecificCityAdTitle(testData.get("cityAgnosticSearchQueryAnotherCityName")),"Proper city not present in Ad title. Please check!");
    }
    /*
    WEB-877:when the user comes from a city agnostic page (www.quikr.com)
     */
    @Test(groups = "horizontal")
    public void CityAgnosticSearchWEB877()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        headerPage.search(testData.get("cityAgnosticSearchQuery") + testData.get("cityAgnosticSearchQueryAnotherLocalityName"));
        homePage.checkSpecificCityAdTitle(testData.get("cityAgnosticSearchQueryAnotherLocalityName"));
        clickOnQuikrLogo();
        headerPage.search(testData.get("cityAgnosticSearchQuery") + testData.get("cityAgnosticSearchQueryAnotherLocalityName") + " " + testData.get("cityAgnosticSearchQueryYetAnotherCityName"));
        Assert.assertTrue(homePage.checkSpecificCityAdTitle(testData.get("cityAgnosticSearchQueryYetAnotherCityName")), "Proper city not shown in display.");
    }
    /*
    WEB-878:When the user comes from a city page (bangalore.quikr.com)
     */
    @Test(groups = "horizontal")
    public void CityAgnosticSearchWEB878()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("validURL"));
        headerPage.selectCity(testData.get("city"));
        clickOnQuikrLogo();
        headerPage.search(testData.get("cityAgnosticSearchQuery") + testData.get("cityAgnosticSearchQueryAnotherLocalityName"));
        homePage.checkSpecificCityAdTitle(testData.get("cityAgnosticSearchQueryAnotherLocalityName"));
        clickOnQuikrLogo();
        headerPage.search(testData.get("cityAgnosticSearchQuery") + testData.get("cityAgnosticSearchQueryAnotherLocalityName") + " " + testData.get("cityAgnosticSearchQueryYetAnotherCityName"));
        Assert.assertTrue(homePage.checkSpecificCityAdTitle(testData.get("cityAgnosticSearchQueryYetAnotherCityName")), "Proper city not shown in display.");
    }
    /*WEB-502:Verify, Download App link on new home page header
    **/
    @Test(groups = "horizontal")
    public void validateDownloadAppLinkCities() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(headerPage.checkDownloadAppLink());
    }
    /* WEB-490:Edit ad:Homepage Ensure below Vernac languages are seen on Home page(English,Hindi,Tamil,Telugu, Malayalam,Kannada,Marathi,Gujarati)
   */
    //@Test(groups = "horizontal")
    public void validatelanguagelist() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        //homePage.clickonCityCloseButton();
        ArrayList<String> languages = headerPage.checklanguage();
        Assert.assertEquals(languages.get(0), testData.get("English"), "Failed to load English Language");
        Assert.assertEquals(languages.get(1), testData.get("Hindi"), "Failed to load हिन्दी  Language");
        Assert.assertEquals(languages.get(2), testData.get("Tamil"), "Failed to load தமிழ  Language");
        Assert.assertEquals(languages.get(3), testData.get("Telugu"), "Failed to load తెలుగు  Language");
        Assert.assertEquals(languages.get(4), testData.get("Malayalam"), "Failed to load ಕನ್ನಡ  Language");
        Assert.assertEquals(languages.get(5), testData.get("Kannada"), "Failed to load മലയാളം  Language");
        Assert.assertEquals(languages.get(6), testData.get("Marathi"), "Failed to load मराठी Language");
        Assert.assertEquals(languages.get(7), testData.get("Gujarati"), "Failed to load ગુજરાતી Language");
    }

    /**
     * WEB-23:Banners on Home/Snb/Vap
     */
    @Test(groups = {"horizontal","horizontalPRI0"})
    public void verifyBannerAds() {
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.letsLogin("", (testData.get("city")), username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("city").toLowerCase());
        Assert.assertTrue(verifyGoogleAds(testData.get("homepageBannerIframeId")), "Probably banner is not being displayed in homepage. Please check!");
    }
    /*WEB-505:Trending Ads should appear with images below Recommended section
    */
    //Recommended Section No More
    @Test(groups="dev")
    public void checkAppearanceOrderTrendingRecommended()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage= new HeaderPage(driver);

        headerPage.letsLogin("randomPageLoginNonResponsive", (testData.get("city")), username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad("quikr");
        Assert.assertTrue(homePage.verifyAppearanceOrderforTrendingRecommendedAds(), "Probably the order of appearance is not as expected.");
    }
    /*
   WEB-510:Verify Locality, Time/Date Chicklets for Trending ads
    */
    @Test(groups = "horizontal")
    public void checkLocalityDateTimeForTrending()
    {
        HomePage homePage = new HomePage(driver);

        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(homePage.verifyTrendingAdsLocalityChicklet(), "Locality chicklets not being displayed properly.");
        Assert.assertTrue(homePage.verifyTrendingAdsTimeChicklet(), "Time chicklets not being displayed properly.");
    }
    /*
    WEB-510:Verify Locality, Time/Date Chicklets for Trending ads
    */
    @Test(groups = "horizontal")
    public void checkLocalityDateTimeForTrendingNewUICities()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        headerPage.letsLogin("", (testData.get("city")),username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad("quikr");
        headerPage.selectCity(testData.get("responsiveHPCity"));
        //homePage.skipLoginWindow();
        Assert.assertTrue(homePage.verifyTrendingAdsLocalityChickletNewUICities(), "Locality chicklets not being displayed properly.");
        Assert.assertTrue(homePage.verifyTrendingAdsTimeChickletNewUICities(), "Time chicklets not being displayed properly.");
    }
    /*
 WEB-517:Verify Locality, Time/Date Chicklets for Recommended ads
  */
    //Recommended Section No More
    @Test(groups = "dev")
    public void checkLocalityDateTimeForRecommended()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage= new HeaderPage(driver);

        //homePage.clickonCityCloseButton();
        headerPage.letsLogin("randomPageLoginNonResponsive", "",username, password);
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad("quikr");
        Assert.assertTrue(homePage.verifyRecommAdLocalityTimeChicklets(), "Chicklets not being displayed properly.");
    }


}
