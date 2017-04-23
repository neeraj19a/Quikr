package com.quikr.msite;

import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.utils.InitiateDriver;
import com.quikr.utils.XMLReader;
import io.appium.java_client.AppiumDriver;
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
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by akhil.singla
 */

public class MTestBase extends TestListenerAdapter
{
    protected AppiumDriver driver;
    protected Log logger;
    protected String classname = this.getClass().getSimpleName();
    DateFormat dateFormatday = new SimpleDateFormat("dd_MMM_yyyy");
    DateFormat dateFormathour = new SimpleDateFormat("HH");
    //DateFormat dayFormattime = new SimpleDateFormat("hh-mm");
    DateFormat dayFormatseconds = new SimpleDateFormat("hh-mm-ss");
    public static String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static int RANDOM_STRING_LENGTH = 10;
    protected String env = InitiateDriver.getEnv();
    protected HashMap<String, String> loginCredentials;


    protected String destDir;

    public MTestBase()
    {
        // set homepath for logger to use
        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
            System.setProperty("homePath", "/home/quikr");
        } else {
            System.setProperty("homePath", "/Users/apple");
        }
    }

    @BeforeMethod(groups = {"horizontal", "QuikrXHighPrio","UATHorizontal","ProdHorizontal"}, alwaysRun = true)
    public void setup()
    {
        logger = LogFactory.getLog(this.getClass().getName());
        driver = new InitiateDriver().getAppiumDriver();
        driver.get("http://www.quikr.com");
        waitForPageToLoad("www.quikr.com");
        //MHomePage mHomePage=new MHomePage(driver);
        //mHomePage.closeLemsPopUp();
        //driver.manage().addCookie(new Cookie("quikrlite","0",".quikr.com","/",null));
        //refreshPage();
    }


    /**
     * wait for page to load having given URL
     *
     * @param pageURL
     */
    public boolean waitForPageToLoad(final String pageURL)
    {

        WebDriverWait wait = new WebDriverWait(driver, 45);
        return wait.until(new ExpectedCondition<Boolean>() {
                              public Boolean apply(WebDriver d)
                              {
                                  return (driver.getCurrentUrl().contains(pageURL)) && driver.executeScript("return document.readyState;").equals("complete");
                              }
                          }
        );
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
     * Generic method to Switch to Pop Up, enter element as parameter by which clicking on it pop up opens
     *
     * @param popup
     */
    public String switchtoPopup(WebElement popup) {
        String mainWindowHandle = driver.getWindowHandle();
        popup.click();
        Set s = driver.getWindowHandles();
        Iterator ite = s.iterator();
        while (ite.hasNext()) {
            String popupHandle = ite.next().toString();
            if (!popupHandle.contains(mainWindowHandle)) {
                driver.switchTo().window(popupHandle);
            }
        }

        return mainWindowHandle;
    }

    /**
     * Switch to Parent window with parameter value of Parent window
     *
     * @param Parentwindow
     */
    public void switchtoParentfromPopUp(String Parentwindow) {
        driver.switchTo().window(Parentwindow);
    }

    /**
     * This function is used to return currentwindow
     *
     * @return
     */
    public String returnCurrentWindowHandle()
    {
        return driver.getWindowHandle();
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
        Cookie name = new Cookie(cookiename, cookievalue);
        driver.manage().addCookie(name);
    }

    /**This function is used to generate the String of random length
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

    /**This function is used to generate Integer of random length
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

    /**This function is used to navigate directly to a particular URL
     *
     * @param URL
     */
    public void navigatethirdparty(String URL)
    {
        driver.navigate().to(URL);
    }


    /**
     * Function for Validating breadcrumb on QuikrX home page
     */
    public String getTestDataWithReplace(String input, String replace){

        return input.replace("-s-", replace);

    }

    /**
     * Function for refreshing page
     */
    public void refreshPage()
    {
        driver.navigate().refresh();
    }


    /**
     * Parse http response to JSONObject
     * @param response
     * @return JSONObject
     * @throws IOException
     */
    public JSONObject parseResponse(HttpResponse response) throws IOException{


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
     * @param path
     * @param jsonData
     * @return HttpResponse
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResponse postMethod(String path, String jsonData) throws ClientProtocolException, IOException
    {
        HttpPost post = new HttpPost(path);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("X-Quikr-Client", "DesktopSite");
        post.setHeader("Accept", "application/json");
        post.setHeader("X-Stream" , "true");
        HttpResponse response=null;

        try {
            post.setEntity(new StringEntity(jsonData));
            HttpClient client = HttpClientBuilder.create().build();
            return client.execute(post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response;

    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver()
    {
        try
        {
            driver.quit();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * get login credential
     * @param name
     * @return login credentials
     */
    public HashMap<String, String> getLoginCredential(String name)
    {
        return XMLReader.getLogins(name);
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

    public void deleteAllCookies()
    {
        driver.manage().deleteAllCookies();
    }

    public void wait(int timeToSleepExecution)
    {
        try
        {
            Thread.sleep(timeToSleepExecution);
        }
        catch (Exception e){};
    }


}



