package com.quikr.website.monetization;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

public class Monetizationpage extends PageBase {
    private static final String domFile = getProperties().get("MONETIZATION_DOM_FILE");

    public Monetizationpage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public String getAdIdMobileAd() {
        String adIdString = null;
        try {
            Mapper.waitForElementToBeVisible(domFile, "replyToThisAdButton");
            Thread.sleep(3000);
            adIdString = Mapper.find(domFile, "adIdMobileAd").getText();
//            System.out.println("%%%%>" + adIdString.substring(adIdString.length() - 11, adIdString.length() - 1));
//            System.out.println(">>" + adIdString.substring(adIdString.length() - 11, adIdString.length() - 1) + "<<");

        } catch (Exception e) {

        }
        return adIdString.substring(adIdString.length() - 11, adIdString.length() - 1);

    }

    public void closeMobVerifyWindow() {
        Mapper.waitForElementToBeVisible(domFile, "mobVerifyWindow");
        Mapper.find(domFile, "mobVerifyWindow").click();
    }

    public boolean VerifyPostedAdWithSearch(String titleAdPosted) {
        String postedAdTitleSnb = Mapper.find(domFile, "postedAdsnb").getText();
        if (postedAdTitleSnb.equals(titleAdPosted)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean CheckCodOptions() {
        boolean val = false;
        Mapper.find(domFile, "postedAdsnb").click();
        Mapper.waitForElementToBeVisible(domFile, "freeDeliveryTextVap");
        if (Mapper.find(domFile, "freeDeliveryTextVap").isDisplayed()) {
            val = true;
        }
        if (Mapper.find(domFile, "codTextVap").isDisplayed()) {
            val = true;
        }
        if (Mapper.find(domFile, "cashbackTextVap").isDisplayed()) {
            val = true;
        }
        return val;
    }

    public void closeCitySelectionPopUp() {
        Mapper.find(domFile, "cityPopUpCloseBtn").click();
    }

    public void closeModalCityPopUp() {
        try {
            Mapper.find(domFile, "cityPopUpCloseBtnBeforeCarsHomePage").click();
            Thread.sleep(3000);
        } catch (Exception e) {

        }
    }

    public void SelectsMobileAndTablets() {
        Mapper.waitForElementToBeVisible(domFile, "mobile");
        Mapper.waitForElementToBeClickable(domFile, "mobile");
        Mapper.find(domFile, "mobile").click();
    }

    /*public void selectSubCategoryMobile()
    {
        Mapper.waitForElementToBeVisible(domFile, "mobileSubCategory");
        Mapper.waitForElementToBeClickable(domFile, "mobileSubCategory");
        Mapper.find(domFile, "mobileSubCategory").click();
        Mapper.waitForElementToBeInvisible(domFile, "mobileSubCategory");

        if(isElementPresent("mobileSubCategory"))
        {
            Mapper.waitForElementToBeVisible(domFile, "mobileSubCategory");
            Mapper.waitForElementToBeClickable(domFile, "mobileSubCategory");
            Mapper.find(domFile, "mobileSubCategory").click();

        }
    }
    */

    public boolean ClickFirstMakeAnOffer() {
        boolean flag=false;
        if (Mapper.waitForElementToBeClickable(domFile, "MakeAnOfferButton") == true) {


                Mapper.find(domFile, "MakeAnOfferButton").click();
                /*if (Mapper.waitForElementToBeVisible(domFile, "MakeAnOfferButton")) {
                    Mapper.find(domFile, "MakeAnOfferButton").click();
                }

*/

                if(Mapper.waitForElementToBeVisible(domFile, "MakeAnOfferPopUp")==true) {
                    flag= Mapper.find(domFile, "MakeAnOfferPopUp").isDisplayed();
                }
            }

        else if(Mapper.waitForElementToBeVisible(domFile, "MakeAnOfferPopUp")==false){
                logger.info("Trying to click Make Offer Button in Second Attempt");
                if(Mapper.waitForElementToBeClickable(domFile, "MakeAnOfferButton")==true) {
                    Mapper.find(domFile, "MakeAnOfferButton").click();
                }

            if(Mapper.waitForElementToBeVisible(domFile, "MakeAnOfferPopUp")==true) {
                flag= Mapper.find(domFile, "MakeAnOfferPopUp").isDisplayed();
            }

        }
        return flag;
        }

    public boolean CheckMakeAnOfferPopUp() {
        Mapper.waitForElementToBeVisible(domFile, "MakeAnOfferPopUp");
        return Mapper.find(domFile, "MakeAnOfferPopUp").isDisplayed();
    }

    public void clickMakeAnOfferPopUpClose() {

        try {
            Mapper.waitForElementToBeVisible(domFile, "MakeAnOfferPopUpClose");
            Mapper.waitForElementToBeClickable(domFile, "MakeAnOfferPopUpClose");
            WebElement makeofferpopup = Mapper.find(domFile, "MakeAnOfferPopUpClose");
            if (makeofferpopup.isDisplayed()) {
                Mapper.getActionObject().moveToElement(makeofferpopup).click(makeofferpopup).build().perform();
            }

        } catch (Exception e) {
        }
    }

    public boolean checkExternalAdsMobileHomescreen() {
        boolean FinalVal = false;
        Mapper.waitForElementToBeVisible(domFile, "MobileHomescreenExtrnlAdOne");
        Mapper.waitForElementToBeVisible(domFile, "MobileHomescreenExtrnlAdTwo");
        WebElement adOne = Mapper.find(domFile, "MobileHomescreenExtrnlAdOne");
        WebElement adTwo = Mapper.find(domFile, "MobileHomescreenExtrnlAdTwo");
        if (adOne.isDisplayed() && adTwo.isDisplayed()) {
            FinalVal = true;
        }
        return FinalVal;
    }

    public boolean checkExternalAdsMobileSnb() {
        boolean FinalVal = false;
        Mapper.waitForElementToBeVisible(domFile, "MobileSnbExtrnlAdOne");
        List<WebElement> adOne = Mapper.finds(domFile, "MobileSnbExtrnlAdOne");
        System.out.println("Size is "+adOne.size());
        if (adOne.size()==3) {
            FinalVal = true;
        }
        return FinalVal;
    }

    public boolean checkExternalAdsMobileVap() {
        boolean FinalVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "MobileVapExtrnlAdOne") == true) {
            if (Mapper.find(domFile, "MobileVapExtrnlAdOne").isDisplayed() == true) {
                FinalVal = true;
            } else {
                logger.info("First external ad not visible.");
                return false;
            }
        }
        if (Mapper.waitForElementToBeVisible(domFile, "MobileVapExtrnlAdTwo") == true) {
            if (Mapper.find(domFile, "MobileVapExtrnlAdTwo").isDisplayed() == true) {
                FinalVal = true;
            } else {
                logger.info("Second external ad not visible.");
                return false;
            }
        }
        return FinalVal;
    }

    public void createAlert() {
        try {
            Mapper.find(domFile, "CreateAlertCategory").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertCategoryService");
            Mapper.find(domFile, "CreateAlertCategoryService").click();
            Mapper.find(domFile, "CreateAlertSubCategory").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertFirstSubCategory");
            Mapper.find(domFile, "CreateAlertFirstSubCategory").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertSelectType");
            Mapper.find(domFile, "CreateAlertSelectType").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertSelectTypeAll");
            Mapper.find(domFile, "CreateAlertSelectTypeAll").click();
            Thread.sleep(2000);
            Mapper.find(domFile, "CreateAlertLocation").click();
            Mapper.waitForElementToBeVisible(domFile, "CreateAlertFirstLocation");
            Mapper.find(domFile, "CreateAlertFirstLocation").click();
            Mapper.find(domFile, "CreateAlertEmail").clear();
            Mapper.find(domFile, "CreateAlertEmail").sendKeys("automation.quikr@gmail.com");
            Mapper.find(domFile, "CreateAlertPhNo").clear();
            Mapper.find(domFile, "CreateAlertPhNo").sendKeys("9966998855");
            Mapper.find(domFile, "CreateAlertSubmit");
            Mapper.find(domFile, "CreateAlertSubmit").click();
        } catch (Exception e) {
        }
    }

    public void clickAlertCategory()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertCategory")==true)
        {
            Mapper.find(domFile,"CreateAlertCategory").click();
            logger.info("Clicked on the alert category, create alert.");
        }
        else
        {
            logger.info("Didn't click on create alert category.");
        }
    }

    public void clickMobileAndTabletsCatCreateAlert()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertCategoryMobileandTablets")==true)
        {
            Mapper.find(domFile,"CreateAlertCategoryMobileandTablets").click();
        }
        else
        {
            logger.info("Didn't click on mobile and tablets category.");
        }
    }

    public void clickCreateAlertSubCat()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertSubCategory")==true)
        {
            Mapper.find(domFile,"CreateAlertSubCategory").click();
        }
        else
        {
            logger.info("Didn't click on sub category.");
        }
    }

    public void clickFirstSubCatCreateAlert()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertFirstSubCategory")==true)
        {
            Mapper.find(domFile,"CreateAlertFirstSubCategory").click();
            logger.info("Clicked on the create alert first sub cat.");
        }
        else
        {
            logger.info("Didn't click on the first sub cat.");
        }
    }

    public void clickCreateAlertType()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertSelectType")==true)
        {
            Mapper.find(domFile,"CreateAlertSelectType").click();
            logger.info("Clicked on the type of alert.");
        }
        else
        {
            logger.info("Alert type couldn't be selected.");
        }
    }

    public void clickAlertSelectTypeBuy()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertSelectTypeBuy")==true)
        {
            Mapper.find(domFile,"CreateAlertSelectTypeBuy").click();
            logger.info("Clicked on the type buy of alert.");
        }
        else
        {
            logger.info("Alert type buy couldn't be selected.");
        }
    }

    public void clickCreateAlertCities()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertCities")==true)
        {
            Mapper.find(domFile,"CreateAlertCities").click();
            logger.info("Clicked on required city.");
        }
        else
        {
            logger.info("City couldn't be selected.");
        }
    }

    public void clickCreateAlertFirstCity()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertFirstCity")==true)
        {
            Mapper.find(domFile,"CreateAlertFirstCity").click();
            logger.info("Clicked on required city.");
        }
        else
        {
            logger.info("City couldn't be selected.");
        }
    }

    public void clickCreateAlertLocation()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertLocation")==true)
        {
            Mapper.find(domFile,"CreateAlertLocation").click();
            logger.info("Clicked on required location.");
        }
        else
        {
            logger.info("Location couldn't be selected.");
        }
    }

    public void clickCreateAlertFirstLocation()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertFirstLocation")==true)
        {
            Mapper.find(domFile,"CreateAlertFirstLocation").click();
            logger.info("Clicked on first location.");
        }
        else
        {
            logger.info("First location couldn't be selected.");
        }
    }

    public void clickCreateAlertAccessory()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertSelectAccessoryType")==true) {
            Mapper.find(domFile,"CreateAlertSelectAccessoryType").click();
            logger.info("Clicked on select accessory type..");
        }
        else {
            logger.info("Accessory couldn't be clicked.");
        }
    }

    public void clickCreateAlertSelectAllAccessory()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertSelectAccessoryTypeAll")==true) {
            Mapper.find(domFile,"CreateAlertSelectAccessoryTypeAll").click();
            logger.info("Clicked on select all accessory type..");
        }
        else {
            logger.info("All accessory couldn't be selected.");
        }
    }

    public void clickCreateAlertSelectBrand()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertSelectBrandName")== true) {
            Mapper.find(domFile,"CreateAlertSelectBrandName").click();
            logger.info("Clicked on select brand");
        }
        else {
            logger.info("Brand couldn't be clicked.");
        }
    }

    public void clickCreateAlertSelectAllBrand()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertSelectBrandNameAll")== true) {
            Mapper.find(domFile,"CreateAlertSelectBrandNameAll").click();
            logger.info("Clicked on select all brand");
        }
        else {
            logger.info("All Brand couldn't be slected.");
        }
    }

    public void createAlertSubmit()
    {
        Mapper.find(domFile, "CreateAlertSubmit");
        Mapper.find(domFile, "CreateAlertSubmit").click();
    }


    public void createAlertWithUserLoggedIn() {
        clickAlertCategory();
        clickMobileAndTabletsCatCreateAlert();
        clickCreateAlertSubCat();
        clickFirstSubCatCreateAlert();
        clickCreateAlertType();
        Mapper.waitForElementToBeVisible(domFile, "CreateAlertSelectTypeBuy");
        Mapper.waitForElementToBeClickable(domFile, "CreateAlertSelectTypeBuy");
        clickCreateAlertCities();
        clickCreateAlertFirstCity();
        clickCreateAlertLocation();
        clickCreateAlertFirstLocation();
        clickCreateAlertAccessory();
        clickCreateAlertSelectAllAccessory();
        clickCreateAlertSelectBrand();
        clickCreateAlertSelectAllBrand();
        createAlertSubmit();
        }
    }

