package com.quikr.app.ios;

import com.quikr.utils.AppiumMapper;
import com.quikr.utils.enums.app.QuikrAppEnums;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manvendra Singh on 30/9/15.
 */
public class AppiOSPageBase
{
    private String fileName;
    private AppiumDriver driver;
    protected AppiumMapper Mapper;

    public AppiOSPageBase(String fileName, AppiumDriver driver)
    {
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
            if(Mapper.find(fileName, element) == null)
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

    /**
     * get page source
     * @return
     */
    public String getPageSource()
    {
        return driver.getPageSource();
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


    /**function to click on elements
     * swatantra singh
     * @param element
     * @param category
     */
    public void selectElements(String element, QuikrAppEnums category)
    {    if (!isElementPresent(element))
    {
        Mapper.scroll(element);
    }
        List<WebElement> elements=Mapper.finds(fileName,getMenuDOMElement(category));
        List<String> list = new ArrayList<String>();
        System.out.println(elements);
        for (WebElement e :elements)
        {
            list.add(e.getText());
        }
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).contains(element)||equals(element))
            {
                int retval=i;
                Mapper.finds(fileName,getMenuDOMElement(category)).get(retval).click();
                break;
            }
        }
    }

    public void selectelementWithoutScroll(String element, QuikrAppEnums category)
    {
        //Mapper.scroll(element);
        List<WebElement> elements=Mapper.finds(fileName,getMenuDOMElement(category));
        List<String> list = new ArrayList<String>();
        for (WebElement e :elements)
        {
            list.add(e.getText());
        }
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).contains(element)||equals(element))
            {
                int retval=i;
                Mapper.finds(fileName,getMenuDOMElement(category)).get(retval).click();
                break;
            }

        }

//        int retval=list.indexOf(element);
        // Mapper.finds(fileName,getMenuDOMElement(category)).get().click();

    }

    private String getMenuDOMElement(QuikrAppEnums category)
    {
        switch (category)
        {
            case CATEGORY_MENU:
                return "menuList";
            case CATEGORY_CARS:
                return "";
            case  CATEGORY_MSP:
                return "mspCategory";
            case CATEGORY_LOCATION:
                return "localityOptions";
            case CATEGORY_ALERT:
                return "dropdownOptions";
            case HOMEPAGE_CATEGORY :
                return "homePageCategory";
            case CHP_SUBCATEGORY:
                return "jobsSubcategory";
            case Hompage_SelectCity:
                return "selectCityFromList";
            case PAP_NEW_LOCALITY:
                return "locationOptionNewFormat";
            case JOBS_ROLE_LIST :
                return "roleListOptions";
            case CATEGORY_SERVICES:
                return "servicesSubCategory";
            case New_UI_Subcat:
                return "NewUiSubCat";
            case CATEGORY_LIST_IN_POST_AD:
                return  "categoryListInPostAd";
        }

        return null;
    }
    
    public  void  scroll(String text)
    {
        Mapper.scroll(text);
    }

    /**
     * function to navigate back
     */
    public void navigateBack()
    {
        driver.navigate().back();
    }
}
