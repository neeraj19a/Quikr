package com.quikr.website.horizontal.home;

import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.myquikr.MyQuikrPage;
import com.quikr.website.mail.YopMailPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/*
 * Created by quikr on 10/2/16.
 */
public class HomeTests4 extends TestBase {

    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("HOME_TESTDATA_FILE"));
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    @Title ("WEB-1245:User should redirect to respective SnB page on clicking any sub category")

    @Test(groups="horizontal")
    public void validateRespectiveSnbRedirection() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);


        headerPage.selectCity(testData.get("EscrowCity"));
        homePage.clickFirstSubCatHome();
        Assert.assertTrue(waitForPageToLoad("Cars"), "Respective Snb page didn't load. Please check!");
    }

    @Title ("WEB-1239:User should redirect to 'Mobiles&Tablets' home page on clicking 'Explore Mobiles' link")
    @Test(groups="horizontal")
    public void verifyMobileandTabletsLink()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("validURL"));
        Assert.assertTrue(homePage.isExploreOptionworking(5), "Explore Mobile and Tablets is not working");

    }


    @Title("WEB-1241:User should redirect to 'Electronics&Appliances' home page on clicking 'Explore Electronics' link")
    @Test(groups="horizontal")
    public void verifyElectronicsandAppliancesLink()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        refreshPage();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0, 900);
        Assert.assertTrue(homePage.isExploreOptionworking(6), "Explore Electronics&Appliances is not working");
    }

    @Title ("WEB-1243:User should redirect to 'Home & Lifestyle' home page on clicking 'Explore Home & Lifestyle' link")
    @Test(groups="horizontal")
    public void verifyHomeandLifestyleLink()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0, 900);
        Assert.assertTrue(homePage.isExploreOptionworking(7), "Explore Home & Lifestyle is not working");
    }

    @Title("WEB-1072:Verify, Languages drop down, MSP, Cart & Sign In/Sign Up links on header")
    //@Test(groups="horizontal")
    public void verifyLanguageMSPSignInCart()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        Assert.assertTrue(homePage.validateMSPworkingResponsiveHP(), "Failed to Load MSP Page");
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        homePage.navigateback();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.isLanguageDropDownworking(), "Language Drop Down not working");
        Assert.assertTrue(homePage.isCartOptionworking(), "Cart Option is not working");
        Assert.assertTrue(homePage.isSignInbuttonHeaderworking(),"Sign In or Sign Up Button not working``");
    }

    @Title ("WEB-1069:Able to see responsive home page design on launching www.quikr.com")
    @Test(groups="horizontal")
    public void verifyResponsiveHomePage()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(homePage.validateMSPworkingResponsiveHP(), "Failed to Load MSP Page");
        homePage.navigateback();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.validateDownloadAppTopLeftResponsiveHP(), "Android App Link present on Home Page Top is not working");
        homePage.navigateback();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.isExploreOptionworking(6), "Explore Electronics and Appliances is not working");
        homePage.navigateback();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        refreshPage();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.isSignInbuttonHeaderworking(), "Sign In or Sign Up Button not working``");
    }

    @Title ("WEB-1073:Verify, New Login/Register popup for responsive home page")
    @Test(groups="horizontal")
    public void verifyLoginNewUI() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        String email=getRandomString(9)+"@gmail.com";
        String password=getRandomString(9);
        String number="98"+getRandomInteger(8);
        String name=getRandomString(5);
        Assert.assertTrue(headerPage.isRegisterbuttonWorking(name,email, password, number));
        homePage.navigateback();
        Assert.assertTrue(headerPage.letsLogin("",testData.get("responsiveHPCity"),testData.get("facebookemail"), testData.get("facebookpassword")), "Failed To Login");
    }

    @Title ("WEB-1075:Verify, My Account Menu on header")
    @Test(groups="horizontal")
    public void verifyMyAccount() {
        HeaderPage headerPage=new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        Assert.assertTrue(headerPage.letsLogin("", testData.get("responsiveHPCity"), testData.get("facebookemail"), testData.get("facebookpassword")), "Failed To Login");
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(headerPage.validateMyAccountOptions(),"Failed to Load All My Account Options");
    }


    @Title ("WEB-1144:Meta Categories should be displayed below carousel banner")
    @Stories("WEB-1144:Meta Categories should be displayed below carousel banner")
    @Features("Allignment Test")
    @Test(groups="horizontal")
    public void verifyCategoriesBelowCarouselBanner() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        Assert.assertTrue(homePage.validateAllCategoriesvisibleResponsiveHP(),"Failed to Load All Categories on Responsive Home Page");
    }

    @Title ("WEB-1137:Login/Register popup will be displayed on clicking Sign In/Sign Up button from Hamburger Menu")
    @Test(groups="horizontal")
    public void verifyDashboardRedirectionMyAccountclick() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(homePage.isSignInbuttonHamburgerworking(),"Failed to Load SignIN or SignUp Div when clicked on Sign In Button on Hamburger Menu");
    }

    @Title ("WEB-1084:Verify, City drop down on header")
    @Test(groups="horizontal")
    public void verifyCityDropDownResponsiveHP() {
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(waitForPageToLoad(testData.get("responsiveHPCity")),"City homepage didn't load. Please check!");
    }


    @Title ("WEB-1083:Verify, Facebook login on Sign In popup")
    @Test(groups="horizontal")
    public void verifyFacebookLoginResponsiveHP() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        headerPage.selectCity(testData.get("responsiveHPCity"));
        String parentWindowHandle = headerPage.returnCurrentWindowHandle();
        homePage.clickFacebookLoginResponsiveHP();
        headerPage.enterfacebookdetails(testData.get("facebookemail"), testData.get("facebookpassword"));
        headerPage.clickfacebookloginbutton();
        headerPage.switchtoParentfromPopUp(parentWindowHandle);
        waitForPageToLoad(testData.get("DashboardURL"));
        Assert.assertTrue(getCurrentUrl().contains(testData.get("DashboardURL")),"Failed To Login through Facebook, Not Redirecting To Dashobard");

    }

    @Title ("WEB-1070:Able to see responsive home page design on launching <city>.quikr.com")
    @Test(groups="horizontal")
    public void verifyResponsiveHomePageonChangeCity()
    {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(homePage.validateMSPworkingResponsiveHP(), "Failed to Load MSP Page");
        homePage.navigateback();
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        Assert.assertTrue(homePage.validateDownloadAppTopLeftResponsiveHP(), "Android App Link present on Home Page Top is not working");
        homePage.navigateback();
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        Assert.assertTrue(homePage.isExploreOptionworking(6), "Explore Electronics and Appliances is not working");
        homePage.navigateback();
        homePage.navigateback();
        refreshPage();
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        Assert.assertTrue(homePage.isPostAdbuttonpresentHeaderResponsiveHP(), "Failed to Load Post Ad Button on Header of Responsive HP");
        homePage.navigateback();
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        homePage.navigateback();
        waitForPageToLoad(testData.get("EscrowCity").toLowerCase());
        Assert.assertTrue(homePage.isSignInbuttonHeaderworking(), "Sign In or Sign Up Button not working``");
    }

    @Title ("WEB-1085:Able to change Categories from Category drop down")
    @Test(groups="horizontal")
    public void verifyChangeCategoriesDropDownResponsiveHP() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.clickCategoryResponsiveHP(2);
        Assert.assertTrue(homePage.validateAutoSuggestSearch("alto"), "Failed to Load results from Category ");
        homePage.clickCategoryResponsiveHP(3);
        Assert.assertTrue(homePage.validateAutoSuggestSearch("samsung"), "Failed to Load results from Category ");

    }

    @Title ("WEB-1086:User is not allowed to search any another sub category")
    @Test(groups="horizontal")
    public void verifyCategoriesSearchResponsiveHP() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.clickCategoryResponsiveHP(2);
        Assert.assertFalse(homePage.validateAutoSuggestSearch("samsung"), "Able to Load Results from other Categories ");
    }

    @Title("WEB-1294:Page should scroll to top on clicking 'Move to top' icon")
    @Test(groups="horizontal")
    public void verifyScrolltoTopResponsiveHP() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        scrollVerticallWithCords(0, 18000);
        Assert.assertTrue(homePage.validateScrolltoTop(), "Scroll To Top Icon is present");

    }

    @Title("WEB-1095:Post Free Ad, Check MSP, Create Free Alert links should be invisible upon clicking (X) button")
    @Stories("WEB-1095:Post Free Ad, Check MSP, Create Free Alert links should be invisible upon clicking (X) button")
    @Features("Fab Icon Working")
    @Test(groups="horizontal")
    public void verifyFabIcon() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        scrollVerticallWithCords(0, 18000);
        homePage.openFabIcon();
        Assert.assertTrue(homePage.isFabIconDisplayed(), "Fab Icon is not Displaying Options to Post Ad, MSP, Alert");
        homePage.closeFabIcon();
        Assert.assertFalse(homePage.isFabIconDisplayed(), "Fab Icons are still getting shown even after Closing Fab Icon");
    }

    @Title("1227:Ensure, Google Ad is displayed above header" )
    @Stories("1227:Ensure, Google Ad is displayed above header")
    @Features("Google Ads on Responsive HP")
    @Test(groups="horizontal")
    public void verifyGoogleAdsHeaderResponsiveHP() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        refreshPage();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertTrue(homePage.isGoogleAdsHeaderdisplayed(), "Failed to load Google Ads on Header Responsive Home Page");
    }

    @Title("WEB-1228:Google Ad should be displayed beside Trending / Recommended Ads")
    @Stories("WEB-1228:Google Ad should be displayed beside Trending / Recommended Ads")
    @Features("Trending Ads and Recommend Section on Home Page")
    @Test(groups="horizontal")
    public void verifyGoogleAdsTrendingandRecommendedResponsiveHP() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(homePage.isGoogleAdsDisplayedTrendingandRecommendedAds(),"Failed to load Google Ads on Trending and Recommend Section on Responsive Home Page");
    }


    @Title("WEB-1091:'View All Chats' link should get opened on clicking QNXT icon")
    @Stories("WEB-1091:'View All Chats' link should get opened on clicking QNXT icon")
    @Features("View All Chats on QNXT Icon")
    @Test(groups="horizontal")
    public void verifyViewAllChatsQNXTIcon() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.clickQNXTIcon();
        homePage.clickViewAllchat();
        Assert.assertTrue(homePage.isNewLoginwindowDisplayed(), "Failed to Load Login Window");
        headerPage.loginWindowResponsiveHP(testData.get("facebookemail"), testData.get("facebookpassword"));
        Assert.assertTrue(waitForPageToLoad(testData.get("myQuikrChats")), "Failed to Load MCR Page");
    }

    @Title("WEB-1334:Verify Skippable login popup.")
    @Stories("WEB-1334:Verify Skippable login popup.")
    @Features("Skip Login Responsive HP")
    //@Test(groups="horizontal")
    public void verifySkippableLogin() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        Assert.assertFalse(homePage.isskipLoginWindowAvailable(), "Skippable Login Window Pop Up is appearing for Non Responsive City as well");
        headerPage.selectCity(testData.get("responsiveHPCity"));
        Assert.assertTrue(homePage.isskipLoginWindowAvailable(), "Failed to Load  Skippable Login Window Pop Up");

    }

    @Title("WEB-1335:Check all the validations for Login/Register")
    @Stories("WEB-1335:Check all the validations for Login/Register")
    @Features("Validation Msg on Login and Register")
    //@Test(groups="horizontal")
    public void verifySkippableLoginredirecttoHomePage() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        if (homePage.isNewLoginwindowDisplayed()==false){
            homePage.isSignInbuttonHeaderworking();
        }
        Assert.assertTrue(homePage.isskipLoginWindowAvailable(), "Failed to Load  Skippable Login Window Pop Up");
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        Assert.assertFalse(homePage.isskipLoginWindowAvailable(), "Skippable Login Window Pop Up is appearing even after Skipping Login Window Pop Up");
    }

    @Title("WEB-1242:Able to see Home & Lifestyle (Design Your Lifestyle) category layout below Electronics&Appliances")
    @Stories("WEB-1242:Able to see Home & Lifestyle (Design Your Lifestyle) category layout below Electronics&Appliances")
    @Features("Allignment Test")
    @Test(groups="horizontal")
    public void verifyHomeandLifestylebelowElectronics() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0, 18000);
        Assert.assertTrue(homePage.iscategoryPresentbelowAnotherCategory("6", "Elec", "7", "Home"), "Failed to Load Home below Electronics");
    }

    @Title("WEB-1240:Able to see Electronics&Appliances (Electronics You’ll Love) category layout below Mobiles&Tablets")
    @Stories("WEB-1240:Able to see Electronics&Appliances (Electronics You’ll Love) category layout below Mobiles&Tablets")
    @Features("Allignment Test")
    @Test(groups="horizontal")
    public void verifyElectronicsBelowMobile() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0,18000);
        Assert.assertTrue(homePage.iscategoryPresentbelowAnotherCategory("5", "Mobil", "6", "Elec"),"Failed to Load Electronics below Mobile");
    }

    @Title("WEB-1238:Able to see Mobiles&Tablets (Get Your Dream Gadgets) category layout below Jobs layout")
    @Stories("WEB-1238:Able to see Mobiles&Tablets (Get Your Dream Gadgets) category layout below Jobs layout")
    @Features("Allignment Test")
    @Test(groups="horizontal")
    public void verifyMobileBelowJobs() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0,18000);
        Assert.assertTrue(homePage.iscategoryPresentbelowAnotherCategory("4", "Job", "5", "Mobile"),"Failed to Load Mobiles below Jobs");
    }

    @Title("WEB-1235:Able to see Jobs (Find A Job - Simple & Easy) category layout below Services ")
    @Stories("WEB-1235:Able to see Jobs (Find A Job - Simple & Easy) category layout below Services ")
    @Features("Allignment Test")
    @Test(groups="horizontal")
    public void verifyJobsBelowServices() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0,18000);
        Assert.assertTrue(homePage.iscategoryPresentbelowAnotherCategory("3", "Service", "4", "Job"),"Failed to Load Jobs below Services");
    }

    @Title("WEB-1234:Able to see Services (Verified Household Service Experts) category layout below Homes layout")
    @Stories("WEB-1234:Able to see Services (Verified Household Service Experts) category layout below Homes layout")
    @Features("Allignment Test")
    @Test(groups="horizontal")
    public void verifyServicesBelowHome() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0,18000);
        Assert.assertTrue(homePage.iscategoryPresentbelowAnotherCategory("2", "Home", "3", "Service"),"Failed to Load Services below Home");
    }

    @Title("WEB-1231:Able to see Homes category(Your Ideal Home) layout below Cars layout with sub categories")
    @Stories("WEB-1231:Able to see Homes category(Your Ideal Home) layout below Cars layout with sub categories")
    @Features("Allignment Test")
    @Test(groups="horizontal")
    public void verifyHomesBelowCars() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0,18000);
        Assert.assertTrue(homePage.iscategoryPresentbelowAnotherCategory("1", "Car", "2", "Home"),"Failed to Load Home below Cars");
    }


    @Title(" WEB-1229:Able to see Cars category layout below Trending & Google ad sections with sub categories")
    @Stories(" WEB-1229:Able to see Cars category layout below Trending & Google ad sections with sub categories")
    @Features("Allignment Test")
    @Test(groups="horizontal")
    public void verifyCarsBelowmTrendingAds() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        homePage.skipLoginWindow();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        scrollVerticallWithCords(0, 900);
        Assert.assertTrue(homePage.isCarspresentBelowTrendingAds(),"Failed To Load Cars below Trending Ads Section On Resonsive Home Page");
    }

    @Title("WEB-1339:Verify New User Registration with both Mobile and Email Verification")
    @Test(groups="horizontal")
    public void verifyRegistrationMobileAndEmail() {

        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        YopMailPage yopMailPage=new YopMailPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        if (homePage.isNewLoginwindowDisplayed()==false){
            homePage.isSignInbuttonHeaderworking();
        }
        Assert.assertTrue(homePage.isNewLoginwindowDisplayed(), "Failed to Load Login Window");
        String email=getRandomString(9)+"@yopmail.com";
        String password = getRandomString(9);
        String number = "9" + getRandomInteger(9);
        String name= getRandomString(9);
        Assert.assertTrue(headerPage.isRegisterbuttonWorking(name, email, password, number), "Unable To Register User");
        headerPage.closeResponsiveLoginPopUp();
        Assert.assertFalse(headerPage.letsLogin("randomPageLoginResponsive", "", email, password), "User is able to Login without Verifying via Email");
        String windowhandle=headerPage.returnCurrentWindowHandle();
        navigatethirdparty(driver, "http://yopmail.com");
        waitForPageToLoad("yopmail.com");
        yopMailPage.logintoYopMailInbox(email.replace("@yopmail.com", ""));
        waitForPageToLoad("yopmail.com1");
        yopMailPage.switchToIFrameMailContent();
        yopMailPage.clickVerifyEmail();
        navigatethirdparty(driver, "http://quikr.com");
        homePage.switchTo().window(windowhandle);
        Assert.assertTrue(headerPage.letsLogin("randomPageLoginResponsive","",email, password), "Failed To Login");
        Assert.assertTrue(myQuikrPage.isEmailVerified(), "Email has been verified but on My Quikr Page it does not show Email as Verified");
        String newEmail=getRandomString(10) + "@yopmail.com";
        myQuikrPage.addMoreEmail(newEmail);
        myQuikrPage.clickAddandVerifyButton();
        String windowhandle1=myQuikrPage.returnCurrentWindowHandle();
        navigatethirdparty(driver, "http://yopmail.com");
        waitForPageToLoad("yopmail.com");
        yopMailPage.logintoYopMailInbox(newEmail.replace("@yopmail.com", ""));
        waitForPageToLoad("yopmail.com");
        yopMailPage.switchToIFrameMailContent();
        yopMailPage.clickVerifyEmail();
        navigatethirdparty(driver, "http://quikr.com");
        homePage.switchTo().window(windowhandle1);
        homePage.clickSignOutbuttonHeader();
        Assert.assertTrue(headerPage.letsLogin("","",newEmail, password), "Failed To Login");

    }

    @Title("WEB-1341:Verify new user Registration with Email only")
    @Test(groups="horizontal")
    public void verifyRegistrationEmailOnly() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        YopMailPage yopMailPage=new YopMailPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        if (homePage.isNewLoginwindowDisplayed()==false){
            homePage.isSignInbuttonHeaderworking();
        }
        Assert.assertTrue(homePage.isNewLoginwindowDisplayed(), "Failed to Load Login Window");
        String email=getRandomString(9)+"@yopmail.com";
        String password = getRandomString(9);
        String number = "9" + getRandomInteger(9);
        String name= getRandomString(9);
        Assert.assertTrue(headerPage.isRegisterbuttonWorking(name, email, password, number), "Unable To Register User");
        headerPage.closeResponsiveLoginPopUp();
        Assert.assertFalse(headerPage.letsLogin("randomPageLoginResponsive","",email, password), "User is able to Login without Verifying via Email");
        navigatethirdparty(driver, "http://yopmail.com");
        waitForPageToLoad("yopmail.com");
        yopMailPage.logintoYopMailInbox(email.replace("@yopmail.com", ""));
        waitForPageToLoad("yopmail.com");
        yopMailPage.switchToIFrameMailInbox();
        Assert.assertTrue(yopMailPage.isMailPresentinYopMailInbox(),"No Mail Recieved Pls Check");


    }

    @Title("WEB-1345:If the user email/Mobile is already registered, verified and a new user tries to register with that")
    @Test(groups="horizontal")
    public void verifyRegistrationwithExistingEmail() {
        HomePage homePage = new HomePage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        YopMailPage yopMailPage=new YopMailPage(driver);
        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);

        waitForPageToLoad(testData.get("HomeURL"));
        headerPage.selectCity(testData.get("responsiveHPCity"));
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        if (homePage.isNewLoginwindowDisplayed()==false){
            homePage.isSignInbuttonHeaderworking();
        }
        Assert.assertTrue(homePage.isNewLoginwindowDisplayed(), "Failed to Load Login Window");
        String email=getRandomString(9)+"@yopmail.com";
        String password = getRandomString(9);
        String number = "9" + getRandomInteger(9);
        String name= getRandomString(9);
        Assert.assertTrue(headerPage.isRegisterbuttonWorking(name, email, password, number), "Unable To Register User");
        headerPage.closeResponsiveLoginPopUp();
        Assert.assertFalse(headerPage.letsLogin("randomPageLoginResponsive","",email, password), "User is able to Login without Verifying via Email");
        navigatethirdparty(driver, "http://yopmail.com");
        waitForPageToLoad("yopmail.com");
        yopMailPage.logintoYopMailInbox(email.replace("@yopmail.com", ""));
        waitForPageToLoad("yopmail.com");
        yopMailPage.switchToIFrameMailContent();
        yopMailPage.clickVerifyEmail();
        navigatethirdparty(driver, "http://quikr.com");
        Assert.assertTrue(headerPage.letsLogin("randomPageLoginResponsive","",email, password), "Failed To Login");
        Assert.assertTrue(myQuikrPage.isEmailVerified(), "Email has been verified but on My Quikr Page it does not show Email as Verified");
        String newEmail=getRandomString(10) + "@yopmail.com";
        myQuikrPage.addMoreEmail(newEmail);
        myQuikrPage.clickAddandVerifyButton();
        navigatethirdparty(driver, "http://yopmail.com");
        waitForPageToLoad("yopmail.com");
        yopMailPage.logintoYopMailInbox(newEmail.replace("@yopmail.com", ""));
        waitForPageToLoad("yopmail.com");
        yopMailPage.switchToIFrameMailContent();
        yopMailPage.clickVerifyEmail();
        navigatethirdparty(driver, "http://quikr.com");
        waitForPageToLoad("pune.quikr.com");
        homePage.clickSignOutbuttonHeader();
        Assert.assertTrue(headerPage.letsLogin("randomPageLoginResponsive","",newEmail, password), "Failed To Login");
        headerPage.clickOnQuikrLogo();
        waitForPageToLoad(testData.get("responsiveHPCity").toLowerCase());
        homePage.clickSignOutbuttonHeader();
        Assert.assertFalse(headerPage.isRegisterbuttonWorking(name, email, password, number), "User is able to Register with Email Id that is already verified");
        Assert.assertTrue(headerPage.validateSignUpErrorResponsiveHP(),"Failed To Load Sign Up Error Message when User tries to Register with Existing Mail Id");
    }
}
