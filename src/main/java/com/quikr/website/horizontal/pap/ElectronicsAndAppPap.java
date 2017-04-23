package com.quikr.website.horizontal.pap;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 25/5/16.
 */
public class ElectronicsAndAppPap extends PageBase {

    private static final String domFile = getProperties().get("ELECTRONICS_AND_APPLIANCES_DOM_FILE");

    public ElectronicsAndAppPap(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    Random rand = new Random();

    public void SelectApplicanceType() {
        if (Mapper.waitForElementToBeVisible(domFile, "applianceType") == true) {
            Mapper.find(domFile,"applianceType").click();
            if (Mapper.waitForElementToBeVisible(domFile, "applianceTypeValues") == true) {
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"applianceTypeValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            } else {
                logger.info("Applicance type options were not populated as dropdown. Please check!");
            }
        } else {
            logger.info("Appliances were not visible....");
        }
    }

    public void selectComputerType() {
        int max = 2, min = 1, randomNum = rand.nextInt(max - min) + min;
        Select dropdown = new Select(Mapper.find(domFile, "computerType"));
        dropdown.selectByIndex(randomNum);
    }

    public void selectScreenSize() {
        if (Mapper.waitForElementToBeVisible(domFile, "screenSize") == true) {
            Mapper.find(domFile,"screenSize").click();
            if (Mapper.waitForElementToBeVisible(domFile, "screenSizeValues") == true) {
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"screenSizeValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            } else {
                logger.info("Screen size options were not populated as dropdown. Please check!");
            }
        } else {
            logger.info("Screensize field was not visible....");
        }
    }

    public void selectRam() {
        if (Mapper.waitForElementToBeVisible(domFile, "ram") == true) {
            Mapper.find(domFile,"ram").click();
            if (Mapper.waitForElementToBeVisible(domFile, "ramValues") == true) {
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"ramValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            } else {
                logger.info("ram value options were not populated as dropdown. Please check!");
            }
        } else {
            logger.info("Ram option was not visible.");
        }
    }

    public void selectPeripheralType(String buyOrSell) {

        if (Mapper.waitForElementToBeVisible(domFile, "peripheralType") == true) {
            Mapper.find(domFile, "peripheralType").click();
            if (buyOrSell.equalsIgnoreCase("buy")){
                if (Mapper.waitForElementToBeVisible(domFile, "peripheralTypeBuyValues") == true) {
                    logger.info("COnfirmed checkbox....");
                    List<WebElement> dropDownOptions = Mapper.finds(domFile, "peripheralTypeBuyValues");
                    int numberOfOptions = dropDownOptions.size();
                    logger.info("Count of options available in the dropdown are :: " + numberOfOptions);
                    int randomNum = new Random().nextInt(numberOfOptions);
                    dropDownOptions.get(randomNum).click();
                } else {
                    logger.info("peripheral value options were not populated as dropdown. Please check!");
                }
            }else if (buyOrSell.equalsIgnoreCase("sell")){
                if (Mapper.waitForElementToBeVisible(domFile, "peripheralTypeSellValues") == true) {
                    List<WebElement> dropDownOptions = Mapper.finds(domFile, "peripheralTypeSellValues");
                    int numberOfOptions = dropDownOptions.size();
                    logger.info("Count of options available in the dropdown are :: " + numberOfOptions);
                    int randomNum = new Random().nextInt(numberOfOptions);
                    dropDownOptions.get(randomNum).click();
                } else {
                    logger.info("peripheral value options were not populated as dropdown. Please check!");
                }
            }else{
                logger.info("Invalid argument passed....");
            }
        }
    }

    public void selectProductType(String sellOrBuy){
        if (Mapper.waitForElementToBeVisible(domFile, "productType") == true) {
            Mapper.find(domFile, "productType").click();
            logger.info("Clicked on product type.");
            if (sellOrBuy.equalsIgnoreCase("sell")){
                if (Mapper.waitForElementToBeVisible(domFile, "productTypeSellValues") == true) {
                    List<WebElement> dropDownOptions = Mapper.finds(domFile,"productTypeSellValues");
                    int numberOfOptions = dropDownOptions.size();
                    logger.info("Count of options available in the dropdown(sell) are :: "+numberOfOptions);
                    int randomNum = new Random().nextInt(numberOfOptions);
                    dropDownOptions.get(randomNum).click();
                } else {
                    logger.info("Product type options(sell) were not populated as dropdown. Please check!");
                }
            }else if (sellOrBuy.equals("buy")){
                if (Mapper.waitForElementToBeVisible(domFile, "productTypeBuyValues") == true) {
                    List<WebElement> dropDownOptions = Mapper.finds(domFile,"productTypeBuyValues");
                    int numberOfOptions = dropDownOptions.size();
                    logger.info("Count of options available in the dropdown(buy) are :: "+numberOfOptions);
                    int randomNum = new Random().nextInt(numberOfOptions);
                    dropDownOptions.get(randomNum).click();
                } else {
                    logger.info("Product type options(buy) were not populated as dropdown. Please check!");
                }
            }
        }else{
            logger.info("Product type option was not available....");
        }
    }

    public void selectAccessoryType(){
        if (Mapper.waitForElementToBeVisible(domFile, "accessoryType") == true) {
            Mapper.find(domFile,"accessoryType").click();
            if (Mapper.waitForElementToBeVisible(domFile, "accessoryTypeValues") == true) {
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"accessoryTypeValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            } else {
                logger.info("Accessory type options were not populated as dropdown. Please check!");
            }
        } else {
            logger.info("Accessory type was not visible.");
        }
    }

    public void selectBrandName() {
        if (Mapper.waitForElementToBeClickable(domFile, "brandNameDropDown") == true) {
            Mapper.find(domFile, "brandNameDropDown").click();
            List<WebElement> radioButtons = Mapper.finds(domFile, "brandNameValues");
            List<WebElement> checkboxes = Mapper.finds(domFile, "brandNameCheckboxValues");
            if (radioButtons.size() > 0) {
                if (Mapper.waitForElementToBeVisible(domFile, "brandNameValues") == true) {
                    List<WebElement> dropDownOptions = Mapper.finds(domFile, "brandNameValues");
                    int numberOfOptions = dropDownOptions.size();
                    logger.info("Count of options available in the dropdown are :: " + numberOfOptions);
                    int randomNum = new Random().nextInt(numberOfOptions);
                    dropDownOptions.get(randomNum).click();
                } else {
                    logger.info("Brand name values were not populated as dropdown. Please check!");
                }
            } else if (checkboxes.size() > 0) {
                if (Mapper.waitForElementToBeVisible(domFile, "brandNameCheckboxValues") == true) {
                    List<WebElement> dropDownOptions = Mapper.finds(domFile, "brandNameCheckboxValues");
                    int numberOfOptions = dropDownOptions.size();
                    logger.info("Count of options available in the dropdown are :: " + numberOfOptions);
                    int randomNum = new Random().nextInt(numberOfOptions);
                    dropDownOptions.get(randomNum).click();
                }
            } else {
                logger.info("Niether the checkboxes nor the radio buttons are present in brand name options. Please check!");
            }
        } else {
            logger.info("Brand name dropdown is not visible....");
        }
    }

    public boolean ifAdPostedSuccessfully(){
        boolean retVal = false;
        waitForPageToLoad("redesignCitrus");
        try{
        Mapper.find(domFile,"skipLinkAfterPostAd").click();
        }
        catch (Exception e){
            logger.info("Looks like Skip Option Not available after Posting Ad");
        }
        waitForPageToLoad("PostAd/?succeed");

        List<WebElement> adIdString = Mapper.finds(domFile,"adIdString");
        List<WebElement> anotherAdIdString = Mapper.finds(domFile, "anotherAdIdString");

        if (adIdString.size()>0){
            logger.info("adIdString :: <"+adIdString.get(0).getText()+">");
            if( getCurrentLocation().contains("PostAd/?succeed") && adIdString.get(0).getText().contains("Ad Id"))
                retVal = true;
            else
                return false;
        }else if (anotherAdIdString.size()>0){
            logger.info("anotherAdIdString :: <"+anotherAdIdString.get(0).getText()+">");
            if( getCurrentLocation().contains("PostAd/?succeed") && anotherAdIdString.get(0).getText().contains("Ad Id"))
                retVal = true;
            else
                return false;
        }else{
            logger.info("None of the ad id string messages expected are visible. Please check!");
        }
        return retVal;

    }


}

