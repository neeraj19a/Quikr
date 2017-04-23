package com.quikr.app.android.cars;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by sewatantra singh on 14/8/15.
 */
public class CarsPage extends AppAndroidPageBase {
    public CarsPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ANDROID_CARS_DOM_FILE");

    public String validateErrorMsgSelectModel() {
        String msg = Mapper.find(domFile, "modelError").getText().toString();
        return msg;
    }

    /**
     * function to click on More button
     */
    public void selectMoreButton() {
        Mapper.waitForElementToBeVisible(domFile, "clickOnMoreButton");
        Mapper.find(domFile, "clickOnMoreButton").click();

    }

    public void findRelatedSubCat(String subcat1) {
        List<WebElement> subcat = Mapper.finds(domFile, "subcatContainer");
        List<String> list = new ArrayList<String>();
        for (WebElement e : subcat) {
            list.add(e.getText());
        }
        System.out.println(list);

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).contains(subcat1)) {

                Mapper.finds(domFile, "ShowMoreAds").get(i).click();
            }

        }

    }

    /**
     * validate on Vap of Cras categories and its sub categories
     *
     * @return
     */

    public boolean validateDetailOnVap()
    {
        if ((isElementPresent("bikesDetailsVAP") ||isElementPresent("carDetails")))
            return true;
        else
            return false;
    }
    /**
     * validate description field on vap
     */
    public boolean validatedescriptionOnVap()
    {

        if ((isElementPresent("bikesDescriptionVap")||isElementPresent("carDescriptions")))
            return true;
        else
            return false;
    }

    /**
     * validate carnation text
     */
    public String CarnationTextVap()
    {
        String text=Mapper.find(domFile,"CarnationRating").getText().toString();
        return text;
    }
    /**
     * select no fikar report on Vap
     */
    public void selctNoFikarReport()
    {
        Mapper.waitForElementToBeVisible(domFile,"CarnationRating");
        Mapper.find(domFile,"CarnationRating").click();
    }

    /**
     * validate inspected option should appear as Filter on Cars Category
     * @return
     */
    public boolean validateInspectedFilter()
    {
        if (isElementPresent("inspectedText"))
            return true;
        else
            return false;
    }
    public void hideOverlayLayout()
    {
        if (isElementPresent("snbOverlay"))
            navigateBack();
    }
}


