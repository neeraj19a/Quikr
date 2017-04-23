package com.quikr.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by akhil.singla
 */
public class AppiumMapper
{
    private final int TIMEOUT = 45;
    private WebDriverWait wait;
    private HashMap<String, String> map;
    private AppiumDriver driver;
    private XMLReader xmlReader;

    public AppiumMapper(AppiumDriver driver)
    {
        this.driver = driver;
        xmlReader = new XMLReader();
    }

    /**
     * find and return WebElement corresponding to element
     * @param fileName
     * @param element
     * @return WebElement
     */
    public WebElement find(String fileName, String element)
    {
        WebElement webElement = null;
        map = xmlReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try
            {
                webElement = driver.findElement(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }

        return webElement;
    }

    /**
     * find and return WebElement corresponding to element
     * @param fileName
     * @param element
     * @param values
     * @return WebElement
     */
    public WebElement findAndReplace(String fileName, String element,String[] values)
    {
        WebElement webElement = null;
        map = xmlReader.getDOMElements(fileName, element);
        updateMap(values);

        while(!map.isEmpty())
        {
            try
            {
                webElement = driver.findElement(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }

        return webElement;
    }

    /**
     * find and return list of WebElement corresponding to element
     * @param fileName
     * @param element
     * @return list of WebElement
     */
    public List<WebElement> finds(String fileName, String element)
    {
        List<WebElement> webElements = null;
        map = xmlReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try {


                webElements = driver.findElements(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }

        return webElements;
    }

    /**
     * find and return list of WebElement corresponding to element
     * @param fileName
     * @param element
     * @param values
     * @return list of WebElement
     */
    public List<WebElement> findsAndReplace(String fileName, String element, String[] values)
    {
        List<WebElement> webElements = null;
        map = xmlReader.getDOMElements(fileName, element);
        updateMap(values);

        while(!map.isEmpty())
        {
            try {


                webElements = driver.findElements(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }

        return webElements;
    }

    /**
     * update map values
     * @param values
     */
    private void updateMap(String[] values)
    {
        Set<String> keys = map.keySet();

        for(String key : keys)
        {
            map.put(key, xmlReader.updateMapValue(map.get(key), values));
        }
    }

    /**
     * wait for element to be visible and enabled in order to be clickable
     * @param fileName
     * @param element
     * @return boolean
     */
    public boolean waitForElementToBeClickable(String fileName, String element)
    {
        try
        {
            wait  = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(find(fileName, element)));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * wait for element to be visible and enabled in order to be clickable
     * @param element
     * @return boolean
     */
    public boolean waitForElementToBeClickable(WebElement element)
    {
        try
        {
            wait  = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * wait for element to disappear from page
     * @param fileName
     * @param element
     * @return boolean
     */
    public boolean waitForElementToBeInvisible(String fileName, String element)
    {
        map = xmlReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try
            {
                wait  = new WebDriverWait(driver, TIMEOUT);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy()));
            }
            catch (NoSuchElementException e)
            {
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * wait for element to present and visible in page
     * @param fileName
     * @param element
     * @return boolean
     */
    public boolean waitForElementToBeVisible(String fileName, String element)
    {
        try
        {
            wait  = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(find(fileName, element)));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * wait for element to be visible in page
     * @param element
     * @return boolean
     */
    public boolean waitForElementToBeVisible(WebElement element)
    {
        try
        {
            wait  = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * wait for element to present and visible in page for given time
     * @param fileName
     * @param element
     * @return boolean
     */
    public boolean waitForElementToBeVisible(String fileName, String element, int timeOut)
    {
        try
        {
            wait  = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(find(fileName, element)));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * scroll element to bring in view, scroll down the page
     * @param fileName
     * @param element
     */
    public void scrollElementIntoView(String fileName, String element)
    {
        try
        {
            WebElement webElement = find(fileName, element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", webElement);
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * get child elements of given web element
     * @param element
     * @return list of child elements
     */
    public List<WebElement> findChilds(WebElement element)
    {
        String path = ".//*";
        return element.findElements(By.xpath(path));
    }

    /**
     * whether given element has child elements or not
     * @param element
     * @return boolean
     */
    public boolean hasChilds(WebElement element)
    {
        if(findChilds(element).size() != 0)
        {
            return true;
        }

        return false;
    }

    /**
     * return child element of parent element that matches passed parameters condition
     * it check for attribute value of given tag
     * @param domFile
     * @param value
     * @param parentElement
     * @param tagNameToSearch
     * @param attributeNameToSearch
     * @return WebElement
     */
    public WebElement findChildElement(String domFile, String value, String parentElement, String tagNameToSearch, String attributeNameToSearch)
    {
        waitForElementToBeVisible(domFile, parentElement);
        WebElement element = find(domFile, parentElement);
        List<WebElement> childElements = findChilds(element);

        for(int i=0; i<childElements.size(); i++)
        {
            if(childElements.get(i).getTagName().equalsIgnoreCase(tagNameToSearch))
            {
                String attribute = childElements.get(i).getAttribute(attributeNameToSearch).trim();
                if(attribute.equalsIgnoreCase(value))
                {
                    return childElements.get(i);
                }
            }
        }

        return null;
    }

    /**
     * return child element of parent element that matches passed parameters condition
     * it check for text content of given tag
     * @param domFile
     * @param value
     * @param parentElement
     * @param tagNameToSearch
     * @param exactMatch
     * @return
     */
    public WebElement findChildElement(String domFile, String value, String parentElement, String tagNameToSearch, boolean exactMatch)
    {
        waitForElementToBeVisible(domFile, parentElement);
        WebElement element = find(domFile, parentElement);
        List<WebElement> childElements = findChilds(element);

        for(int i=0; i<childElements.size(); i++)
        {
            if(childElements.get(i).getTagName().equalsIgnoreCase(tagNameToSearch)) {
                String textContent = childElements.get(i).getText().trim();
                if(exactMatch)
                {
                    if (textContent.equalsIgnoreCase(value))
                    {
                        return childElements.get(i);
                    }
                }
                else
                {
                    if (textContent.contains(value))
                    {
                        return childElements.get(i);
                    }
                }
            }
        }

        return null;
    }

    /**
     * get By
     * @return By
     */
    private By getBy()
    {
        By by = null;

        if (map.containsKey("id"))
        {
            by = By.id(map.get("id"));
            map.remove("id");
        }
        else if (map.containsKey("css"))
        {
            by = By.cssSelector(map.get("css"));
            map.remove("css");
        }
        else if (map.containsKey("name"))
        {
            by = By.name(map.get("name"));
            map.remove("name");
        }
        else if (map.containsKey("class"))
        {
            by = By.className(map.get("class"));
            map.remove("class");
        }
        else if (map.containsKey("tag"))
        {
            by = By.tagName(map.get("tag"));
            map.remove("tag");
        }
        else if (map.containsKey("linkText"))
        {
            by = By.linkText(map.get("linkText"));
            map.remove("linkText");
        }
        else if (map.containsKey("partialLinkText"))
        {
            by = By.partialLinkText(map.get("partialLinkText"));
            map.remove("partialLinkText");
        }
        else if (map.containsKey("xpath"))
        {
            by = By.xpath(map.get("xpath"));
            map.remove("xpath");
        }

        return by;
    }

    public void scroll(String text)
    {
        try
        {
            driver.scrollTo(text);
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * function to perform vertical swipe
     * swatantra singh
     * @param domfile
     * @param StartElement
     * @param EndElement
     */
    public void VerticalSwipe(String domfile, String StartElement ,String EndElement)
    {
        WebElement start1 = find( domfile, StartElement);
        WebElement end1 = find( domfile, EndElement);
        int start1x, start1y, end1x, end1y;
        start1x = start1.getLocation().getX();
        end1x = end1.getLocation().getX();
        start1y = start1.getLocation().getY();
        end1y = end1.getLocation().getY();
        TouchAction action1 = new TouchAction(driver);
        action1.press(350, end1y).waitAction(500).moveTo(350, start1y).release().perform();
    }

    public void horizontalSwipe(String domfile, String StartElement ,String EndElement)
    {
        WebElement start1 = find( domfile, StartElement);
        WebElement end1 = find( domfile, EndElement);
        int start1x, start1y, end1x, end1y;
        start1x = start1.getLocation().getX();
        end1x = end1.getLocation().getX();
        start1y = start1.getLocation().getY();
        end1y = end1.getLocation().getY();
        System.out.println(start1x);
        System.out.println(start1y);
        System.out.println(end1x);
        System.out.println(end1y);

        TouchAction action1 = new TouchAction(driver);
        action1.press(end1x,start1y+150).waitAction(600).moveTo(start1x,start1y+150).release().perform();
    }

    /**
     * function tap on element
     */
    public void tap(int i,int startElement,int endElement,int timeDuration)
    {
        driver.tap(i, startElement, endElement, timeDuration);
    }

    /**
     * swatantra singh
     * hidekeybord
     */
    public void hideKeyboard()
    {
        driver.hideKeyboard();
    }

    /**
     * return Action object
     * @return
     */
    public Actions getActionObject()
    {
        return new Actions(driver);
    }

    /**
     * function to perform touch action
     */
    public TouchAction getTouchActionObject()
    {
        return new TouchAction(driver);
    }
}
