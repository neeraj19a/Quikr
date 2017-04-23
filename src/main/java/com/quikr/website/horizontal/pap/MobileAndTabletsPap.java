package com.quikr.website.horizontal.pap;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.Random;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 20/5/16.
 */
public class MobileAndTabletsPap extends PageBase {

    private static final String domFile = getProperties().get("MOBILE_AND_TAB_DOM_FILE");

    public MobileAndTabletsPap(RemoteWebDriver driver){
        super(domFile, driver);
    }

    Random rand = new Random();

    public void selectYearOfPurchase(){
        if (Mapper.waitForElementToBeVisible(domFile,"purchaseYearDropdown")==true){
            Mapper.find(domFile,"purchaseYearDropdown").click();
            if (Mapper.waitForElementToBeVisible(domFile,"purchaseYearValues")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"purchaseYearValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("Purchase year values were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("purchase year dropdown is not visible....");
        }
    }

    public void selectBrandName(){
        if (Mapper.waitForElementToBeVisible(domFile,"brandNameDropDown")==true){
            Mapper.find(domFile,"brandNameDropDown").click();
            if (Mapper.waitForElementToBeVisible(domFile,"brandNameValues")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"brandNameValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("Brand name values were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("Brand name dropdown is not visible....");
        }
    }

    public void selectModel(){
        if (Mapper.waitForElementToBeVisible(domFile,"modelType")==true){
            Mapper.find(domFile,"modelType").click();
            if (Mapper.waitForElementToBeVisible(domFile,"modelTypeValues")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"modelTypeValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("Model values were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("Model dropdown is not visible....");
        }
    }

    public void selectOperatingSys(){
        if (Mapper.waitForElementToBeVisible(domFile,"operatingSystemDropDown")==true){
            Mapper.find(domFile,"operatingSystemDropDown").click();
            if (Mapper.waitForElementToBeVisible(domFile,"operatingSystemValues")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"operatingSystemValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("OS values were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("OS dropdown is not visible....");
        }
    }

    public void selectSimCount(){
        if (Mapper.waitForElementToBeVisible(domFile,"numberOfSims")==true){
            Mapper.find(domFile,"numberOfSims").click();
            if (Mapper.waitForElementToBeVisible(domFile,"simCount")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"simCount");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("Sim values were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("sim dropdown is not visible....");
        }
    }

    public void selectAccessory(){
        if (Mapper.waitForElementToBeVisible(domFile,"accessoryType")==true){
            Mapper.find(domFile,"accessoryType").click();
            if (Mapper.waitForElementToBeVisible(domFile,"accessoryTypeValues")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"accessoryTypeValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("Accessories options were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("Accessory dropdown is not visible....");
        }
    }

    public boolean ifAdPostedSuccessfully(){
        waitForPageToLoad("redesignCitrus");
        Mapper.find(domFile,"skipLinkAfterPostAd").click();
        waitForPageToLoad("PostAd/?succeed");
        logger.info("adIdString :: <"+Mapper.find(domFile,"adIdString").getText()+">");
        if( getCurrentLocation().contains("PostAd/?succeed") && Mapper.find(domFile,"adIdString").getText().contains("Ad Id"))
            return true;
        else
            return false;
    }
}
