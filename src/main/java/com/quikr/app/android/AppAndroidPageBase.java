package com.quikr.app.android;

import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.utils.AppiumMapper;
import com.quikr.utils.enums.app.QuikrAppEnums;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil.singla
 */
public class AppAndroidPageBase
{

    private String fileName;
    private AppiumDriver driver;
    protected AppiumMapper Mapper;

    public AppAndroidPageBase(String fileName, AppiumDriver driver)
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
    {
        Mapper.waitForElementToBeVisible(fileName,getMenuDOMElement(category),10);
        if (!isElementPresent(element))
        {
            Mapper.scroll(element);
        }
        List<WebElement> elements=Mapper.finds(fileName,getMenuDOMElement(category));
        List<String> list = new ArrayList<String>();
        for (WebElement e :elements)
        {
            list.add(e.getText());
        }
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).toLowerCase().trim().contains(element.toLowerCase().trim())||equals(element.toLowerCase().trim()))
            {
                int retval=i;
                Mapper.finds(fileName,getMenuDOMElement(category)).get(retval).click();
                break;
            }
        }
    }

    public void selectelementWithoutScroll(String element, QuikrAppEnums category)
    {
        Mapper.waitForElementToBeVisible(fileName,getMenuDOMElement(category),20);
        List<WebElement> elements=Mapper.finds(fileName,getMenuDOMElement(category));
        List<String> list = new ArrayList<String>();
        int i=0;
        for (WebElement e :elements)
        {
            //list.add(e.getText());
            String text = e.getText();
            if (text.toLowerCase().contains(element.toLowerCase().trim()) || text.toLowerCase().equals(element.toLowerCase().trim()))
            {

                int retval=i;
                explicitWait(2);
                Mapper.finds(fileName,getMenuDOMElement(category)).get(retval).click();
                break;
            }
            i++;
        }
//        int retval=list.indexOf(element);
        // Mapper.finds(fileName,getMenuDOMElement(category)).get().click();

    }

    public void sendInPutText(String element, QuikrAppEnums category,String Text)
    {
        explicitWait(2);
        Mapper.waitForElementToBeVisible(fileName, getMenuDOMElement(category), 10);
        List<WebElement> elements = Mapper.finds(fileName, getMenuDOMElement(category));
        List<String> list = new ArrayList<String>();
        for (WebElement e : elements) {
            list.add(e.getText());
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().contains(element.toLowerCase().trim()) || equals(element.toLowerCase().trim())) {
                int retval = i;
                System.out.println(retval);
                explicitWait(2);
                System.out.println(Text);
//                Mapper.finds(fileName, getMenuDOMElement(category)).get(retval).click();
                Mapper.finds(fileName, getMenuDOMElement(category)).get(retval).sendKeys(Text);
                //driver.getKeyboard().sendKeys(Text);
                break;
            }

        }
    }
    public void sendTextByKeybord(String element, QuikrAppEnums category, String Text)
    {
        explicitWait(2);
        Mapper.waitForElementToBeVisible(fileName, getMenuDOMElement(category), 10);
        List<WebElement> elements = Mapper.finds(fileName, getMenuDOMElement(category));
        List<String> list = new ArrayList<String>();
        for (WebElement e : elements) {
            list.add(e.getText());
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().contains(element.toLowerCase().trim()) || equals(element.toLowerCase().trim())) {
                int retval = i;
                System.out.println(retval);
                explicitWait(2);
                System.out.println(Text);
                Mapper.finds(fileName, getMenuDOMElement(category)).get(retval);
                driver.getKeyboard().sendKeys(Text);
                break;

            }

        }
    }
    /**
     * send text after scrolling to destination
     */
    public void sendInPutTextWithScroll(String element, QuikrAppEnums category,String Text)
    {
        Mapper.waitForElementToBeVisible(fileName, getMenuDOMElement(category), 10);
        Mapper.scroll(element);
        List<WebElement> elements=Mapper.finds(fileName,getMenuDOMElement(category));
        List<String> list = new ArrayList<String>();
        for (WebElement e :elements)
        {
            list.add(e.getText());
        }
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).toLowerCase().contains(element.toLowerCase().trim()) || equals(element.toLowerCase().trim()))
            {
                int retval=i;
                explicitWait(2);
//                Mapper.finds(fileName,getMenuDOMElement(category)).get(retval).click();
                Mapper.finds(fileName,getMenuDOMElement(category)).get(retval).sendKeys(Text);
                break;
            }

        }

//        int retval=list.indexOf(element);
        // Mapper.finds(fileName,getMenuDOMElement(category)).get().click();

    }

    /**
     * get index of  element on Ui
     * @param element
     * @param category
     * @return
     */
    public int getIndexOfElement(String element, QuikrAppEnums category)
    {
        Mapper.waitForElementToBeVisible(fileName,getMenuDOMElement(category),10);
        Mapper.scroll(element);
        List<WebElement> elements=Mapper.finds(fileName,getMenuDOMElement(category));
        List<String> list = new ArrayList<String>();
        for (WebElement e :elements)
        {
            list.add(e.getText());
        }
        int retval=0;
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).toLowerCase().contains(element.toLowerCase().trim()) || equals(element.toLowerCase().trim()))
            {
                retval = i;
            }
        }
        return retval;

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
                return "selectCity";
            case PAP_NEW_LOCALITY:
                return "locationOptionNewFormat";
            case JOBS_ROLE_LIST :
                return "roleTitles";
            case CATEGORY_SERVICES:
                return "servicesSubCategory";
            case New_UI_Subcat:
                return "NewUiSubCat";
            case Jobs_Role_Exp:
                return "roleExperiance";
            case Jobs_New_language:
                return "languageOption";
            case Home_Categories:
                return "categoriesTitle";
            case Services_HomeSubcategories:
                return "homeSubcategories";
            case Services_OtherSubcategories:
                return "bookNowCategories";
            case InstaConnectCategories:
                return "instaConnectCategories";
            case  CATEGORY_JOBS_CHP_ROLES:
                return "chpRoles";
            case Alert_Form:
                return "AlertFields";
            case RealEstate_Locality:
                return "autoSuggest";
            case SERVICES_INSTACONNECT_CATEGORY:
                return "servicesInstaconnectSubcategories";
            case SERVICES_INSTACONNECT_SUBCAT:
                return "servicesInstaConnectSubCAt";
            case RealEstate_Search_Autosuggest:
                return "autoSuggestSubCAt";
            case Mobiles_SNB_MAKEOFFERORCHAT:
                return "makeAnOfferORChat";
            case Quikr_PAP_CAT_SUBCAT_NEW:
                return "papNewCat";
            case PAP_Radio_elements:
                return "radoiElement";
            case Alert_Widget_Elements:
                return "AlertFields";
            case SNB_Subcat:
                return "SubCatId";
            case QUIKRX_EXCHANGE_BRAND:
                return "exchangeBrandText";
            case Quikr_Cars_SnbSubcat:
                return "carsSnbSubCat";
            case PostAd_SelectFromDropDown:
                return "postAdDropdowns";
            case PostAD_InputText:
                return "postAdTestBox";
            case PostAd_RadioButton:
                return "radioButton";
            case PostAd_Checkbox:
                return "radioButton";
            case PostAd_category:
                return "selectcategoryOnPAP";
            case PostAd_categorylist:
                return "papCatTitle";
            case PostAd_EscrowExtraText:
                return "papEscrowExtraText";
            case PostAd_Warehouse:
                return "checkbox";
            case PostAd_Privacy:
                return "maintainPrivacy";
            case SNB_Chat:
                return "snbChat";
            case SNB_SORT_OPTIONS:
                return "sortDropDownOPtions";
            case ELECTRONICS_SUBCATEGORIES:
                return "electronicsCategoryNAme";
        }

        return null;
    }

    /**
     * function to navigate back
     */
    public void navigateBack()
    {
        driver.navigate().back();
    }

    /**
     * scroll to string
     * @param text
     */
    public void scroll(String text)
    {
        Mapper.scroll(text);
    }

    /**
     * hide keyboard
     */
    public void hideKeyboard()
    {
        try{
            Mapper.hideKeyboard();
        }
        catch (org.openqa.selenium.WebDriverException e)
        {
            System.out.println("Keyboard already closed");
        }

    }

    /**
     * login to quikr
     * @param password
     * @param email
     */
    public void login(String password,String email)
    {
        MenuPage menuePage = new MenuPage(driver);
        AuthPage authPage = new AuthPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll("My Account", QuikrAppEnums.CATEGORY_MENU);
        authPage.clickONLOginSignUPIcon();
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(email);
        authPage.setSignInPassword(password);
        authPage.submitsignin();
    }

    /**
     * login when user is redirected to login page
     */
    public void loginWhenRedirectedToLoginPAge(String email,String password)
    {
        AuthPage authPage = new AuthPage(driver);
        authPage.clickONLoginWithQuikr();
        authPage.setSignInEmail(email);
        authPage.setSignInPassword(password);
        authPage.submitsignin();
    }

    /**
     * swipe hprizontally with given cordinates
     * @param start1x
     * @param start1y
     * @param end1x
     */
    public  void horizontalSwipeWithCordinates(int start1x, int start1y,int end1x)
    {
        TouchAction action1 = new TouchAction(driver);
        action1.press(end1x, start1y ).waitAction(1000).moveTo(start1x, start1y).release().perform();
    }

    /**
     * swipe vertically with given Cordinates
     */
    public  void verticalSwipeWithCordinates(int Y1,int Y2)
    {
        explicitWait(3);
        TouchAction action1 = new TouchAction(driver);
        action1.press(350, Y1 ).waitAction(1000).moveTo(350, Y2).release().perform();
    }
    /**
     *
     */
    public void scrollToTextIfElementNotVisible(String Domelement,String ScrollToText)
    {
        if (!isElementPresent(Domelement))
            Mapper.scroll(ScrollToText);
    }

    public void swipeVertically(String downElement, String upElement)
    {
        Mapper.waitForElementToBeVisible(fileName, downElement, 15);
        int Y1Cordinates=Mapper.find(fileName,downElement).getLocation().getY();
        int Y2Cordinates=Mapper.find(fileName,upElement).getLocation().getY();
        TouchAction action1 = new TouchAction(driver);
        action1.press(250, Y1Cordinates ).waitAction(1000).moveTo(250, Y2Cordinates+30).release().perform();
    }
    /**
     * sleep time
     */
    public void explicitWait(int sec)
    {
        try {
            Thread.sleep(1000*sec);
        }
        catch (Exception e)
        {}
    }
    /**
     * fetch elements into list for validation
     */
    public List fetchElementsIntoList(String DomElement)
    {
        //Storing allMenu elements  in ARRAy LISt

        Mapper.waitForElementToBeVisible(fileName,DomElement,10);
        List<WebElement> options = Mapper.finds(fileName, DomElement);
        List<String> list = new ArrayList<String>();
        for (WebElement e : options)
        {
            list.add(e.getText().trim());
        }

        return list;
    }

    /**
     * send text by keyboard
     * @param text
     */
    public void setTextFromKeyBoard(String text)

    {
        driver.getKeyboard().sendKeys(text);
    }

    /**
     * swipe on postAd Page
     */
    public Integer[] CordinatesForVErticalSwipe(String downelement,String upElement)
    {
        // for long swipe from botton to up use "Y1-Y2"
        Mapper.waitForElementToBeVisible(fileName,downelement);
        int Y2=Mapper.find(fileName,upElement).getLocation().getY();
        int Y1=Mapper.find(fileName,downelement).getLocation().getY();
        Integer cordinates[]={Y1,Y2,};
        return cordinates;

    }

    /**
     * method for Mobile login
     * @param Username
     * @param Password
     */
    public void mobileLogin(String Username,String Password)
    {
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        MenuPage menuePage = new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll("My Account", QuikrAppEnums.CATEGORY_MENU);
        authPageMobileLogin.clickOnLogin();
        authPageMobileLogin.enterLoginEmailNumber(Username);
        authPageMobileLogin.enterLoginpassword(Password);
        authPageMobileLogin.submitLoginCredentials();
    }
    /**
     * swipe vertically with given DomElements
     */
    public  void verticalSwipe(String Element1,String Element2)
    {
        int y1=Mapper.find(fileName,Element1).getLocation().getY();
        int y2=Mapper.find(fileName,Element2).getLocation().getY();
        explicitWait(1);
        TouchAction action1 = new TouchAction(driver);
        action1.press(350, y1 ).waitAction(1000).moveTo(350, y2).release().perform();
    }

    /**
     * Helper method to scroll up or down to an element by name, with a particular step size.
     *
     * @param name The name of the element.
     * @param up True if movement of 'finger' is upwards.
     * @param stepSize Distance from top and bottom of screen where the gesture will begin/end (percentage of screen)
     * @return True if found.
     */
    public  WebElement loopScrollToElementByName(WebElement scrollObjectName,String name, boolean up, int stepSize) {
        int counter = 0;
        Dimension size = scrollObjectName.getSize();
        System.out.println(size);
        //int step = (int) (stepSize*0.35);
        //int topY = step;
        //int bottomY = 1-step;
        //int x = (int)(1*0.5);
        //int y = (int) (1*0.5);
        int starty = (int) (size.height*0.80);
        int endy = (int) (size.height*0.20);
        int startx = size.width/2;
        String pageSource = "";
        WebElement element = null;
        while (counter < 50) {
            try {
                //System.out.println("Outside: true");
                element = Mapper.find(fileName,name);
                if(element!= null)
                {
                    return element;
                }
                else
                {
                    System.out.println("Element Not Found while scrolling");
                }
                //int startY = up ? topY : bottomY;
                //int endY = up ? bottomY : topY;

                if (up)
                {
                    driver.swipe(startx, starty, startx, endy, stepSize);
                }
                else
                {
                    driver.swipe(startx, endy, startx, starty, stepSize);
                }

                if (pageSource.equals(driver.getPageSource())) {
                    return element;
                }
                pageSource = driver.getPageSource();
            } catch (Exception e) {
                System.out.println(counter + " time trying to scroll " + (up ? "up" : "down") + " to " + name);
            }
            counter++;
        }

        return element;
    }

    /**
     * Helper method to scroll up or down to an element by name, with a particular step size.
     *
     * @param name The name of the element.
     * @param up True if movement of 'finger' is upwards.
     * @param stepSize Distance from top and bottom of screen where the gesture will begin/end (percentage of screen)
     * @return True if found.
     */
    public  boolean loopScrollToElementByNameUntilInvisible(WebElement scrollObjectName,String name, boolean up, int stepSize) {
        int counter = 0;
        Dimension size = scrollObjectName.getSize();
        System.out.println(size);
        //int step = (int) (stepSize*0.35);
        //int topY = step;
        //int bottomY = 1-step;
        //int x = (int)(1*0.5);
        //int y = (int) (1*0.5);
        int starty = (int) (size.height*0.80);
        int endy = (int) (size.height*0.20);
        int startx = size.width/2;
        String pageSource = "";
        WebElement element = null;
        while (counter < 50) {
            try {
                //System.out.println("Outside: true");
                element = Mapper.find(fileName,name);
                if(element== null)
                {
                    return true;
                }
                //int startY = up ? topY : bottomY;
                //int endY = up ? bottomY : topY;

                if (up)
                {
                    driver.swipe(startx, starty, startx, endy, stepSize);
                }
                else
                {
                    driver.swipe(startx, endy, startx, starty, stepSize);
                }

                if (pageSource.equals(driver.getPageSource())) {
                    return false;
                }
                pageSource = driver.getPageSource();
            } catch (Exception e) {
                System.out.println(counter + " time trying to scroll " + (up ? "up" : "down") + " to " + name);
            }
            counter++;
        }

        return false;
    }

}