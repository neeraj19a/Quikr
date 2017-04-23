package com.quikr.website;

import com.quikr.utils.Mapper;
import com.quikr.website.horizontal.header.HeaderPage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by akhil.singla
 */
public class PageBase
{
    private String fileName;
    protected Log logger;
    private RemoteWebDriver driver;
    protected Mapper Mapper;

    public PageBase(String fileName, RemoteWebDriver driver)
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
        Mapper = new Mapper(this.driver);
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
        driver.executeScript("return document.readyState;").equals("complete");
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
    public WebDriver.TargetLocator switchTo()
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

    /**
     * zoom out browser
     * @param numberOfTimes
     */
    public  void zoomOut(int numberOfTimes)
    {
        try
        {
            for(int i=0; i<numberOfTimes; i++) {
            driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            }
            Thread.sleep(1000);
        }catch (Exception e)
        {}
    }

    /**
     * reset zoom
     */
    public void resetZoom()
    {
        try {
            driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
            Thread.sleep(1000);
        }catch (Exception e)
        {}
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

    public void closeChildWindowofPopUp(String childwindow) {
        driver.switchTo().window(childwindow).close();
    }
    /**
     * This function is used to return currentwindow
     *
     * @return
     */
    public String returnCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public boolean binarySearch(String searchString, String[] arrayToSearchFrom)
    {
        boolean retVal = false;
        Arrays.sort(arrayToSearchFrom);
        int index = Arrays.binarySearch(arrayToSearchFrom, searchString);
        if (index!=-1)
        {
            retVal=true;
        }
        else
        {
            logger.info("Search String not present in the given array. Please check!");
            return false;
        }
        return retVal;
    }

    public void selectCity(String city)
    {
        //HomePage homePage = new HomePage(driver);
        ////homePage.SelectCity(city);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.selectACity(city);
    }

    /**
     * wait for page to load having given URL
     *
     * @param pageURL
     */
    public boolean waitForPageToLoad(final String pageURL) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 45);
            return wait.until(new ExpectedCondition<Boolean>() {
                                  public Boolean apply(WebDriver d) {
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

    public void getElementintoView(WebElement element){
        Coordinates coordinates=((Locatable)element).getCoordinates();
        coordinates.inViewPort();
        sleep(3000);
    }

    public void sleep(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollVerticallWithCords(int startValue, int endValue) {
        try {
            ((JavascriptExecutor) driver).executeScript("scroll(" + startValue + "," + endValue + " );");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
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

    public void moveSlider(WebElement element){
        Actions actions= new Actions(driver);
        actions.moveToElement(element).click();
        actions.dragAndDropBy(element, 30, 0).build().perform();
        logger.info("Slider Moved");
    }

    /*
    Utility function to capture Image, Need to Revisit
     */
    public static File captureElementPicture(WebElement element)
            throws Exception {

        // get the WrapsDriver of the WebElement
        WrapsDriver wrapsDriver = (WrapsDriver) element;

        // get the entire screenshot from the driver of passed WebElement
        File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver())
                .getScreenshotAs(OutputType.FILE);

        // create an instance of buffered image from captured screenshot
        BufferedImage img = ImageIO.read(screen);

        // get the width and height of the WebElement using getSize()
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        // create a rectangle using width and height
        Rectangle rect = new Rectangle(width, height);

        // get the location of WebElement in a Point.
        // this will provide X & Y co-ordinates of the WebElement
        Point p = element.getLocation();

        // create image  for element using its location and size.
        // this will give image data specific to the WebElement
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,
                rect.height);

        // write back the image data for element in File object
        ImageIO.write(dest, "png", screen);

        // return the File object containing image data
        return screen;
    }


    /*
    Utlity Function to Read Captcha,Need to Revisit
     */
    public void readCaptcha(WebElement captchaimage){
        File imageFile = null;
        try {
            imageFile = captureElementPicture(captchaimage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    // get the Tesseract direct interace
        Tesseract instance = new Tesseract();

    // the doOCR method of Tesseract will retrive the text
    // from image captured by Selenium
        String result = null;
        try {
            result = instance.doOCR(imageFile);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        logger.info("Captcha Text is -->"+result);
    }

    public void closeTheYoutubeFrame(){
        try {
            driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
            driver.findElementByXPath("//a[@id='webklipper-publisher-widget-container-notification-close-div']").click();
            driver.switchTo().defaultContent();
        }catch (Exception e){e.printStackTrace();}
    }
}