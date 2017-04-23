package com.quikr.app.android.realEstate.realEstateSnbPage;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 8/10/15.
 */
public class RealEstateSnbPage extends AppAndroidPageBase {


    public RealEstateSnbPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_REALESTATESNB_DOM_FILE");

    /**
     * validate if result count is displayed on SNB header
     */
    public String ValidateResultCountOnSnbHeader()
    {
        Mapper.waitForElementToBeVisible(domFile,"SnbResultCount");
        String results=Mapper.find(domFile,"SnbResultCount").getText();
        System.out.println(results);
        return results;
    }

    /**
     * validate SnbHeader
     */
    public boolean validateSnbHeader()
    {
        return (isElementPresent("sort")&&isElementPresent("alert"));
    }
/**
 * click on create alert
 */

    public void clickOnCreateAlert()
    {
        Mapper.waitForElementToBeVisible(domFile,"alert");
        Mapper.find(domFile,"alert").click();
    }
    /**
     * click on filter icon
     */

    public void ClickFilterIcon()
    {
        Mapper.waitForElementToBeVisible(domFile,"filter");
        Mapper.find(domFile,"filter").click();
    }
    /**
     * validate is user is on fulter page
     */

    public  boolean validateFilterPageResidential()
    {
        return (isElementPresent("filterFurnishing")&&isElementPresent("filterAmenities")&&isElementPresent("filterProperty"));
    }

    public  boolean validateFilterPageCommercial()
    {
        return( isElementPresent("filterAmenities")&&isElementPresent("filterProperty"));
    }
    public  boolean validateFilterPageAgrriculture()
    {
        return (isElementPresent("filterProperty"));
    }

    /**
     * select filters on snb page
     */

    public void SelectFilterCheckBox(int i)
    {
        Mapper.waitForElementToBeVisible(domFile,"filterCheckbox");
        Mapper.finds(domFile,"filterCheckbox").get(i).click();
    }

    /**
     * validate on selecting reset .all filters are Reset
     */

    public boolean validateResetFilterButtonAction()
    {
        return  ( !Mapper.find(domFile, "filterCheckbox").isSelected() );

    }

    /**
     * reset all selected filters by clilcking reset button
     */
    public void clickOnResetFilterButton()
    {
        Mapper.find(domFile,"FilterReset").click();
    }

    /**
     *  validate Locality on snb
     */

    public String AdLocalityOnSnb()
    {
        String locality=Mapper.find(domFile, "AdLocality").getText();
        return locality;
    }
    /**
     * open ad from snb page
     */
    public void openAdFromSnb()
    {
        Mapper.waitForElementToBeVisible(domFile,"snbADTitle");
        Mapper.find(domFile,"snbADTitle").click();
    }

    /**
     * validate no of rooms on snb
     */
    public String snbRooms()
    {
        Mapper.waitForElementToBeVisible(domFile,"snbNoOfRooms");
        String snbRooms=Mapper.find(domFile, "snbNoOfRooms").getText();
        String [] rooms=snbRooms.split("|");
        String rooms1=rooms[0];
        return rooms1;
    }
    /**
     * validate if seller name is present on snb
     */
    public boolean validateSellerNAmeOnsnb()
    {
        Mapper.waitForElementToBeVisible(domFile,"snbADTitle");
        return (isElementPresent("snbSellerName"));
    }
    /**
     * validate user type On snb
     */
    public boolean validateSellerTypeOnSnb()
    {
        return isElementPresent("snbSellerType");
    }

    /**
     * fetch price
     */

    public String validatePriceFormat()
    {
        String Price=Mapper.find(domFile,"price").getText();
        System.out.println(Price);
        return Price;
    }

    /**
     * click on locality on filter to select locality
     */
    public void clickOnLocality()
    {
        Mapper.waitForElementToBeVisible(domFile,"filterLocality");
        Mapper.find(domFile,"filterLocality").click();
    }
/**
 * click on apply filter after selecting desired filters
 */

    public  void clickOnApplyFilter()
    {
        Mapper.find(domFile,"ApplyFilter").click();
    }
    /**
     * get localities from snb
     */
    public List snbLaocality()
    {
        //Storing localities in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "AdLocality");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options)
        {
            list.add(e.getText());
        }
        Mapper.VerticalSwipe(domFile,"snbMenuHeader","filter");
        List<WebElement> options1 = Mapper.finds(domFile, "AdLocality");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : options1)
        {
            list1.add(e.getText());
        }
        List<String> finallist = new ArrayList<String>(list);
        finallist.addAll(list1);
        Mapper.VerticalSwipe(domFile,"snbMenuHeader","filter");
        List<WebElement> options2 = Mapper.finds(domFile, "AdLocality");
        List<String> list2 = new ArrayList<String>();
        for (WebElement e : options2)
        {
            list2.add(e.getText());
        }
        finallist.addAll(list2);
        Mapper.VerticalSwipe(domFile,"snbMenuHeader","filter");
        List<WebElement> options3 = Mapper.finds(domFile, "AdLocality");
        List<String> list3 = new ArrayList<String>();
        for (WebElement e : options3)
        {
            list3.add(e.getText());
        }
        finallist.addAll(list3);
        return finallist;

    }

}
