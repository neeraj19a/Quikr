package com.quikr.msite;

import com.quikr.utils.AppiumMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.Set;

/**
 * Created by akhil.singla
 */
public class MPageBase
{
    private String fileName;
    protected Log logger;
    private AppiumDriver driver;
    public AppiumMapper Mapper;
    
    public MPageBase(String fileName, AppiumDriver driver)
    {
        // set homepath for logger to use
        if(System.getProperty("os.name").equalsIgnoreCase("Linux"))
        {
            System.setProperty("homePath","/home/quikr");
        }
        else
        {
            System.setProperty("homePath","/Users/apple");
        }

        logger = LogFactory.getLog(this.getClass().getName());
        this.fileName =  fileName;
        this.driver = driver;
        Mapper = new AppiumMapper(this.driver);
    }

    /*
        verify if element is present or not
     */
    protected boolean isElementPresent(String element)
    {
        try
        {
            if(Mapper.find(fileName,element) == null)
            {
                return false;
            };

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * get current location of the browser
     * @return
     */
    public String getCurrentLocation()
    {
        return driver.getCurrentUrl();
    }

    /**
     * execute java script
     * @param script
     * @return
     */
    public Object executeScript(String script)
    {
        return driver.executeScript(script);
    }

    public Object executeScript(String script,WebElement element)
    {
        return  ((JavascriptExecutor) driver).executeScript(script, element);

    }

    /**
     * get page source
     * @return
     */
    public String getPageSource()
    {
        return driver.getPageSource();
    }

    /**
     * return current window handles
     */
    protected Set<String> getWindowHandles()
    {
        return driver.getWindowHandles();
    }


    /**
     * return current window handle
     */
    protected String getWindowHandle()
    {
        return driver.getWindowHandle();
    }

    /**
     * return targert locator
     * @return
     */
    protected WebDriver.TargetLocator switchTo()
    {
        return driver.switchTo();
    }

    /**
     * navigate to new page
     * @return
     */
    protected WebDriver.Navigation navigateTo()
    {
        return driver.navigate();
    }

    /**
     * get title of page
     * @return
     */
    public String getTitle()
    {
        return driver.getTitle();
    }
    
   /**
    * opens passed Url
    * @param url
    */
    public void openUrl(String url){
    	driver.get(url);
    }

    /**
     * delete all cookies
     */
    public void deleteAllCookies()
    {
        driver.manage().deleteAllCookies();
    }

    public void hideKeyboard()
    {
        driver.hideKeyboard();
    }

    /**This function is used to select the date from the calendar with parameter days , here days will be added to current date i.e. date will be future date
     *
     * @param days
     */
    public void selectdate(int days){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, days);
        int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
        driver.findElement(By.xpath("//a[starts-with(.,\"" + dayOfMonth + "\")]")).click();
    }

    /*
    This method makes the driver scroll to the webelement passed as param.
     */
    public void scrollToWebElement(WebElement elmToScrollTo)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elmToScrollTo);
    }

    public void JobsNewUI(){
        driver.get("http://www.quikr.com/jobs");
    }

    public void navigateBack(){
        driver.navigate().back();
    }


    public boolean isAlertPresent()
    {
        try
        {
            Alert alert=driver.switchTo().alert();
            alert.accept();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   // catch
    }

    public void swipeVertically(String downElement, String upElement)
    {
        String originalContext = driver.getContext();
        driver.context("NATIVE_APP");
        Mapper.waitForElementToBeVisible(fileName, downElement, 15);
        int Y1Cordinates=Mapper.find(fileName,downElement).getLocation().getY();
        int Y2Cordinates=Mapper.find(fileName,upElement).getLocation().getY();
        logger.info(Y1Cordinates+ "    "+Y2Cordinates);
        TouchAction action1 = new TouchAction(driver);
        action1.press(250, Y1Cordinates ).waitAction(1000).moveTo(250, Y2Cordinates+30).release().perform();
        driver.context(originalContext);
    }

    public  void verticalSwipeWithCordinates(int Y1,int Y2)
    {
        String originalContext = driver.getContext();
        System.out.println("Context>>"+originalContext);
        driver.context("NATIVE_APP");
        TouchAction action1 = new TouchAction(driver);
        action1.press(350, Y1 ).waitAction(600).moveTo(350, Y2).release().perform();
        driver.context(originalContext);
    }

    /**
     * wait for page to load having given URL
     *
     * @param pageURL
     */
    public boolean waitForPageToLoad(final String pageURL, int timeout)
    {

        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(new ExpectedCondition<Boolean>() {
                              public Boolean apply(WebDriver d)
                              {
                                  return (driver.getCurrentUrl().contains(pageURL)) && driver.executeScript("return document.readyState;").equals("complete");
                              }
                          }
        );
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

}