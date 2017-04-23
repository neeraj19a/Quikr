package com.quikr.website.escrow.escrowFilterSearch;

import com.quikr.website.PageBase;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by CFLAP583 on 12-02-2016.
 */
public class EscrowSearchFilterPage extends PageBase {

    public EscrowSearchFilterPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ESCROW_SEARCH_FILTER_DOM_FILE");

    public boolean isMobileTabletPageOpen() {
        Mapper.waitForElementToBeVisible(domFile, "isMobileTabletHeading");
        if (Mapper.find(domFile, "isMobileTabletHeading").isDisplayed())
            return true;
        else
            return false;
    }

    public void selectBrand() {
        Mapper.waitForElementToBeVisible(domFile, "selectBrand");
        Actions action = Mapper.getActionObject();
        action.moveToElement(Mapper.find(domFile, "selectBrand"));
        /*Mapper.waitForElementToBeClickable(domFile, "selectBrand");
        Mapper.find(domFile, "selectBrand").click();*/
        Mapper.find(domFile, "selectBrand").click();
    }

    public boolean isBrandSelected(String brand) {
        Mapper.waitForElementToBeVisible(domFile, "isBrandSelected",20);
        if (Mapper.find(domFile, "isBrandSelected").getAttribute("label").equalsIgnoreCase(brand))
            return true;
        else
            return false;
    }

    public void selectAd() {
        Mapper.waitForElementToBeVisible(domFile, "selectAd",20);
        Mapper.waitForElementToBeClickable(domFile, "selectAd");
        Mapper.find(domFile, "selectAd").click();
    }

    public boolean isAdSelected() {
        Mapper.waitForElementToBeVisible(domFile, "isAdSelected",20);
        if (Mapper.find(domFile, "isAdSelected").isDisplayed())
            return true;
        else
            return false;
    }

}
