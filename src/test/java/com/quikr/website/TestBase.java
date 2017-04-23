package com.quikr.website;

import com.quikr.utils.InitiateDriver;
import com.quikr.utils.XMLReader;
import com.quikr.website.electronics.electronicsPap.ElectronicsPapPage;
import com.quikr.website.electronics.electronicsSnb.ElectronicsSnbPage;
import com.quikr.website.electronics.electronicsVap.ElectronicsVapPage;
import com.quikr.website.escrow.escrowPap.EscrowPapPage;
import com.quikr.website.escrow.escrowVap.EscrowVapPage;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import net.lightbody.bmp.BrowserMobProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by akhil.singla
 */

public class TestBase extends TestListenerAdapter {
    protected RemoteWebDriver driver;
    protected Log logger;
    protected String classname = this.getClass().getSimpleName();
    DateFormat dateFormatday = new SimpleDateFormat("dd_MMM_yyyy");
    DateFormat dateFormathour = new SimpleDateFormat("HH");
    //DateFormat dayFormattime = new SimpleDateFormat("hh-mm");
    DateFormat dayFormatseconds = new SimpleDateFormat("hh-mm-ss");
    public static String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static int RANDOM_STRING_LENGTH = 10;
    protected BrowserMobProxy proxyServer;
    protected String env = InitiateDriver.getEnv();
    protected HashMap<String, String> loginCredentials;
    private List<RemoteWebDriver> newDriversList;
    public String browser = null;
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");
    int testCaseCount=0;


    protected String destDir;
    private HashMap<String, String> testData = getTestData(getProperties().get("TESTBASE_TESTDATA_FILE"));

    public TestBase() {
        // set homepath for logger to use
        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
            System.setProperty("homePath", "/home/quikr");
        } else {
            System.setProperty("homePath", "/Users/apple");
        }
    }

    @BeforeMethod(groups = {"horizontal", "QuikrXHighPrio", "UATHorizontal", "ProdHorizontal", "horizontalPRI0","EscrowMobileAndTablet","EscrowElectronics","EscrowHomeLifeStyle","EscrowProduction"}, alwaysRun = true)
    public void setup()
    {
        logger = LogFactory.getLog(this.getClass().getName());
        logger.info(1);
        InitiateDriver initiateDriver = new InitiateDriver();
        driver = initiateDriver.getDriver();
        newDriversList = new ArrayList<RemoteWebDriver>();
        proxyServer = initiateDriver.getServer();
        logger.info("Executing class : "+ this.getClass().getCanonicalName() + ", test case number : " + ++testCaseCount);

        if (proxyServer != null) {
            proxyServer.newHar("quikr.com");
        }

        try {
            if (env.equalsIgnoreCase("quikrhomes")) {
                driver.get("http://uat.quikr.com/homes");
            } else {
                driver.get("http://www.quikr.com");
            }
       } catch (Exception e) {
            driver.get("http://www.quikr.com");
        }

        new HomePage(driver).zoomOut(2);
    }

    /**
     * get login credentials
     *
     * @return
     */
    public HashMap getLoginCredentials() {
        if (env.equals("integration")) {
            loginCredentials = getLoginCredential("quikrIntegrationLogin");
        } else if (env.equals("uat")) {
            loginCredentials = getLoginCredential("quikrUatLogin");
        } else if (env.equals("dev")) {
            loginCredentials = getLoginCredential("quikrDevStageLogin");
        } else {
            loginCredentials = getLoginCredential("quikrGeneralLogin");
        }
        return loginCredentials;
    }

    @AfterMethod(groups = {"horizontal", "QuikrXHighPrio", "UATHorizontal", "ProdHorizontal", "horizontalPRI0","EscrowMobileAndTablet","EscrowElectronics","EscrowHomeLifeStyle"}, alwaysRun = true)
    public void tearDownandTakeScreenshot(ITestResult testResult) {
//        try {
//
           if (testResult.getStatus() == ITestResult.FAILURE)// Here ITestResult is an interface
               screenShot();
//            {
//                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                if (System.getProperty("os.name").equalsIgnoreCase("Apple")) {
//                    destDir = "/Users/apple/Documents/WebSiteAutomation/Screenshots/" + classname + "/" + dateFormatday.format(new Date()) + "/" + dateFormathour.format(new Date()) + " " + "hour" + "/";
//                } else if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
//                    destDir = "/home/quikr/Desktop/WebSiteAutomation/Screenshots/" + classname + "/" + dateFormatday.format(new Date()) + "/" + dateFormathour.format(new Date()) + " " + "hour" + "/";
//
//                }
//                if (!new File(destDir).exists())
//                {
//                    new File(destDir).mkdirs();
//                }
//                String destFile = dayFormatseconds.format(new Date()) + " " + testResult.getName() + ".jpg";
//                try {
//                    FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        } catch (Exception e) {
//
//        }

        if (proxyServer != null) {
            proxyServer.stop();
        }

        //driver.quit();


        if (newDriversList.size() != 0) {
            for (RemoteWebDriver webDriver : newDriversList) {
                webDriver.quit();
            }
        }

    }

    public void scrollVerticallWithCords(int startValue, int endValue) {
        try {
            ((JavascriptExecutor) driver).executeScript("scroll(" + startValue + "," + endValue + " );");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * wait for page to load having given URL
     *
     * @param pageURL
     */
    public boolean waitForPageToLoad(final String pageURL) {
        final ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        try {
            WebDriverWait wait = new WebDriverWait(driver, 45);
            return wait.until(new ExpectedCondition<Boolean>() {
                                  public Boolean apply(WebDriver d) {

                                      //hack for handling alert while posting ad in integration env
                                      if (horizontalSnbPage.getCurrentLocation().contains("/PostAd/?succeed")) {
                                          try {
                                              horizontalSnbPage.switchTo().alert().accept();
                                          } catch (NoAlertPresentException Ex) {
                                          }
                                      }
                                      return (driver.getCurrentUrl().toLowerCase().contains(pageURL.toLowerCase())) && driver.executeScript("return document.readyState;").equals("complete");
                                  }
                              }
            );
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * wait for page to load having given URL
     *
     * @param pageURL
     */
    public boolean waitForPageToLoad(final String pageURL, final RemoteWebDriver webDriver) {
        final ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(webDriver);

        WebDriverWait wait = new WebDriverWait(webDriver, 45);
        return wait.until(new ExpectedCondition<Boolean>() {
                              public Boolean apply(WebDriver d) {

                                  //hack for handling alert while posting ad in integration env
                                  if (horizontalSnbPage.getCurrentLocation().contains("/PostAd/?succeed")) {
                                      try {
                                          horizontalSnbPage.switchTo().alert().accept();
                                      } catch (NoAlertPresentException Ex) {
                                      }
                                  }

                                  return (webDriver.getCurrentUrl().toLowerCase().contains(pageURL.toLowerCase())) && webDriver.executeScript("return document.readyState;").equals("complete");
                              }
                          }
        );
    }

    /**
     * validate locality option by clicking on link present on snb page and verification on vap page
     *
     * @param numberOfLinksToCheck : number of links to check for locality
     */
    public void validateLocalityOption(int numberOfLinksToCheck, String locality) {
        ElectronicsSnbPage horizontalSnbPage = new ElectronicsSnbPage(driver);
        ElectronicsVapPage electronicsVapPage = new ElectronicsVapPage(driver);

        for (int i = 1; i <= numberOfLinksToCheck; i++) {
            horizontalSnbPage.clickOnAd(i);
            Assert.assertTrue(electronicsVapPage.validateLocality(locality), "Not getting locality " + locality + " at vap page");
            horizontalSnbPage.navigateTo().back();
        }
    }

    /*
    Extracts the current url and checks if it contains the PartialUrlString passed as parameter.
    @param PartialUrlString : the string that Url should contain
    */
    public boolean VerifyUrl(String PartialUrlString) {
        String urlExtracted = driver.getCurrentUrl();
        return urlExtracted.contains(PartialUrlString);
    }

    /**
     * Generic method to post an ad for mobile phones.
     *
     * @param adTitle
     * @param price
     * @param reservePrice
     * @param brandName
     * @param description
     */

    public void postAnAdMobiles(String adTitle, String price, String reservePrice, String brandName, String description, String address, String pincode, String number) {

        ElectronicsPapPage electronicsPapPage = new ElectronicsPapPage(driver);

        electronicsPapPage.postAdTitle(adTitle);
        electronicsPapPage.selectProductCondition();
        electronicsPapPage.PostAdPrice(price);
        electronicsPapPage.PostAdReversePrice(reservePrice);
        electronicsPapPage.selectBrandMobile(brandName);
        electronicsPapPage.selectlocality();
        electronicsPapPage.AdDesceription(description);
        electronicsPapPage.selectYouAre();
        electronicsPapPage.setAddress(address);
        electronicsPapPage.setMobileNumber(number);
        electronicsPapPage.setPincode(pincode);
        electronicsPapPage.clickPostAdButton();
    }

    /**
     * Generic method to verify if google ad iframe is present or not.
     *
     * @param iFrameId
     * @return
     */

    public boolean verifyGoogleAds(String iFrameId) {
        scrollVerticallWithCords(0, 500);
        return driver.getPageSource().contains(iFrameId);
    }


    /**
     * This function is used to edit the cookie by cookiename and value of that cookie
     *
     * @param cookiename
     * @param cookievalue
     */
    public void createorEditCookieValue(String cookiename, String cookievalue) {
        Cookie cookie = driver.manage().getCookieNamed(cookiename);
        driver.manage().deleteCookie(cookie);
        logger.info("First attempt to delete cookie " + cookiename);
        Cookie cookieAgain = driver.manage().getCookieNamed(cookiename);
        logger.info("Checking the cookie presence after its deletion");
        if (cookieAgain!=null){
            logger.info("Cookie has been successfully deleted.");
            Cookie name = new Cookie(cookiename, cookievalue,"/");
            driver.manage().addCookie(name);
            driver.navigate().refresh();
        }else{
            driver.manage().deleteCookie(cookie);
            logger.info("Cookie deletion tried for second time.");
            Cookie name = new Cookie(cookiename, cookievalue,"/");
            driver.manage().addCookie(name);
            driver.navigate().refresh();
        }
    }

    /**
     * This function is used to generate the String of random length
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        final String charset = "abcdefghijklmnopqrstuvwxyz";
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            sb.append(charset.charAt(pos));
        }
        return sb.toString();
    }

    /**
     * This function is used to generate Integer of random length
     *
     * @param length
     * @return
     */
    public static String getRandomInteger(int length) {
        final String charset = "12345678910111213";
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            sb.append(charset.charAt(pos));
        }
        return sb.toString();
    }

    /**
     * This function is used to navigate directly to a particular URL
     *
     * @param URL
     */
    public void navigatethirdparty(WebDriver driver, String URL) {
        driver.navigate().to(URL);
    }

    /**
     * Function for Validating breadcrumb on QuikrX home page
     */
    public String getTestDataWithReplace(String input, String replace) {

        return input.replace("-s-", replace);

    }

    /**
     * Function for refreshing page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickOnQuikrLogo() {
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.clickOnQuikrLogo();
    }

    /**
     * Parse http response to JSONObject
     *
     * @param response
     * @return JSONObject
     * @throws IOException
     */
    public JSONObject parseResponse(HttpResponse response) throws IOException {


        BufferedReader rd = null;
        try {
            rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        JSONObject jsonObject = (JSONObject) JSONValue.parse(result.toString());
        return jsonObject;

    }

    /**
     * past method with end point and Body as jsonData
     *
     * @param path
     * @param jsonData
     * @return HttpResponse
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResponse postMethod(String path, String jsonData) throws ClientProtocolException, IOException {
        HttpPost post = new HttpPost(path);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("X-Quikr-Client", "DesktopSite");
        post.setHeader("Accept", "application/json");
        post.setHeader("X-Stream", "true");
        HttpResponse response = null;

        try {
            post.setEntity(new StringEntity(jsonData));
            HttpClient client = HttpClientBuilder.create().build();
            return client.execute(post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response;

    }

    public String getToolTipTextEditAd(WebElement Hover) {

        Actions ActionChains = new Actions(driver);
        ActionChains.moveToElement(Hover).build().perform();
        String Tooltip = Hover.getAttribute("title");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Tooltip;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        browser = System.getProperty("browser") == null ? getProperties().get("BROWSER") : System.getProperty("browser");
        System.out.println("Browser Name== " + browser);
        if (browser.equalsIgnoreCase("Chrome")) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get login credential
     *
     * @param name
     * @return login credentials
     */
    public HashMap<String, String> getLoginCredential(String name) {
        return XMLReader.getLogins(name);
    }

    /**
     * open new browser window
     *
     * @return driver for that window
     */
    public RemoteWebDriver openNewWindow() {
        RemoteWebDriver newDriver = new InitiateDriver().getDriver();
        newDriversList.add(newDriver);
        return newDriver;
    }

    /**
     * create dummy escrow ad
     *
     * @param webDriver
     * @param adId
     * @param userName
     * @return ad id and username used to create ad
     */
    public String createTestEscrowAd(RemoteWebDriver webDriver, String adId, String userName, String city) {
        EscrowVapPage vapPage = new EscrowVapPage(webDriver);
        boolean adSold = false;

        if (adId != null) {
            openAd(webDriver, adId);
            waitForPageToLoad(adId, webDriver);
            adSold = vapPage.isAdSold();
        }

        if (adSold == true || adId == null) {
            HomePage homePage = new HomePage(webDriver);
            EscrowPapPage papPage = new EscrowPapPage(webDriver);
            HeaderPage headerPage = new HeaderPage(webDriver);
            webDriver.get("http://www.quikr.com");
            waitForPageToLoad(testData.get("basicUrl"), webDriver);
            homePage.selectpopularCity(city);
            waitForPageToLoad(city.toLowerCase(), webDriver);
            homePage.clickPostFreeAdButton();
            waitForPageToLoad(testData.get("papPageUrl"));
            papPage.selectCategory(testData.get("categoryMobile"));
            papPage.subcategory(testData.get("subCatMobile"));
            if (city.equals("Lucknow")) {
                System.out.println("Username is " + loginCredentials.get("username") + " Password is " + loginCredentials.get("password"));
                headerPage.letsLogin("randomPageLoginResponsive", "", loginCredentials.get("username"), loginCredentials.get("password"));
            }
            papPage.selectAdType();
            papPage.enterTitle(testData.get("title"));
            papPage.selectCondition();
            papPage.enterPrice(testData.get("price"));
            papPage.selectBrandName(testData.get("mobileBrand"));
            papPage.selectSellerType();
            papPage.enterSellerName(testData.get("sellerName"));
            userName = getRandomString(15);
            papPage.enterSellerEmail(userName + "@yopmail.com");
            papPage.enterSellerMobile("9" + getRandomInteger(9));
            papPage.enterSellerAddress(testData.get("sellerAddress"));
            headerPage.selectCity(city);
            papPage.selectLocality(0);
            papPage.enterPincode(testData.get("sellerPincode"));
            papPage.maintainPrivacy();
            papPage.enterDescription(testData.get("description"));
            if (city.equals("Chennai") || city.equals("Hyderabad")) {
                papPage.freeAd();
            } else {
                papPage.submitAd();
                papPage.closePostAdPopUpNewUICities(city);
                waitForPageToLoad(testData.get("adSuccessfulPostedUrl"), webDriver);
                adId = getCurrentUrl().split("adId=")[1].split("&mox")[0];
                //openAd(webDriver, adId); Commenting it here as it creates a blocker in other required tests. If you need to open the ad after creation. please use OpenAd(adId) in test
                //waitForPageToLoad(adId, webDriver);
                return adId + " " + userName + "@yopmail.com";
            }

        }

        return adId + " " + userName;
    }

    /**
     * open ad
     *
     * @param webDriver
     * @param adId
     */
    public void openAd(RemoteWebDriver webDriver, String adId) {
        webDriver.get("http://www.quikr.com/aa-W0QQAdIdZ" + adId);
    }

    /**
     * open ad after login with given adId
     *
     * @param adUserName
     * @param adId
     * @param webDriver
     */
    public void loginAndOpenAd(String adUserName, String adId, RemoteWebDriver webDriver) {
        HeaderPage headerPage = new HeaderPage(webDriver);

        clickOnQuikrLogo();
        headerPage.letsLogin("randomPageLoginNonResponsive","",adUserName, getLoginCredential("defaultPassword").get("password"));
        openAd(driver, adId);
        waitForPageToLoad("W0QQAdIdZ", webDriver);
    }

    public String PostAd(String city)
    {
        HomePage homePage = new HomePage(driver);
        EscrowPapPage papPage = new EscrowPapPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        homePage.clickPostFreeAdButton();
        waitForPageToLoad(testData.get("papPageUrl"));
        papPage.selectCategory(testData.get("categoryMobile"));
        papPage.subcategory(testData.get("subCatMobile"));
        papPage.selectAdType();
        String adTitle=getRandomString(9)+" "+testData.get("adTitle")+" "+getRandomString(9);
        papPage.enterTitle(adTitle);
        papPage.selectCondition();
        papPage.enterPrice(testData.get("price"));
        papPage.selectBrandName(testData.get("mobileBrand"));
        papPage.selectSellerType();
        papPage.enterSellerName(testData.get("sellerName"));
        //userName = getRandomString(15);
        //papPage.enterSellerEmail(userName + "@gmail.com");
        //papPage.enterSellerMobile("9" + getRandomInteger(9));
        headerPage.selectCity(city);
        papPage.enterSellerAddress(testData.get("sellerAddress"));
        papPage.selectLocality(0);
        papPage.enterPincode(testData.get("sellerPincode"));
        papPage.maintainPrivacy();
        papPage.enterDescription(testData.get("description"));
        /*if (city.equals("Chennai") || city.equals("Hyderabad")) {
            papPage.freeAd();
        } else {
        */    papPage.submitAd();
        waitForPageToLoad("redesignCitrus");
        papPage.clickSkipLink();

        papPage.closePostAdPopUpNewUICities(city);
            waitForPageToLoad(testData.get("adSuccessfulPostedUrl"), driver);
            return adTitle;
    }

    /**
     * scroll to the bottom of a dynamic page with 3 seconds wait
     */
    public void scrollToEndOfPage() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        Object currentHeight = jse.executeScript("return document.body.scrollHeight");
        while (true){
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         Object   newHeight =  jse.executeScript("return document.body.scrollHeight");
        if (newHeight.equals(currentHeight)) {
            break;
        }
        currentHeight=newHeight;
     }

    }

    public void sleep(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Attachment
    public byte[]screenShot()
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }


    public void navigateBack(){
        driver.navigate().back();
    }

    public String getParentWindow(){
        return driver.getWindowHandle();
    }

    public void switchToParentWindow(){
        driver.switchTo().window(getParentWindow());
    }

    public void switchToWindow(){
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

}