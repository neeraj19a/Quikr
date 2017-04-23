package com.quikr.website.horizontal.pap;

import com.quikr.website.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 26/5/16.
 */
public class HomeAndLifeStylePap extends PageBase {

    private static final String domFile = getProperties().get("HOME_AND_LIFESTYLE_DOM_FILE");

    public HomeAndLifeStylePap(RemoteWebDriver driver) {
        super(domFile, driver);}


    public void selectRandomProductType(){
        WebElement selectBox=Mapper.find(domFile, "Product_Type");
        selectBox.click();
        boolean flag=false;
        List<WebElement> list=new LinkedList<>();
        list=Mapper.finds(domFile,"Product_Type_OptionsRadioButton");
        if(list.size()==0){
            list=Mapper.finds(domFile,"Product_Type_OptionsCheckBox");
            flag=true;
        }
        int randomOption=new Random().nextInt(list.size());
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        if(flag){
            element.sendKeys(Keys.ENTER);
        }
        String productType=list.get(randomOption).getText();
        logger.info("Clicking on Product Type---->"+productType);
    }

    public void selectRandomFurnishingType(){
        WebElement selectBox=Mapper.find(domFile, "Furnishing_Type");
        selectBox.click();
        boolean flag=false;
        List<WebElement> list=new LinkedList<>();
        list=Mapper.finds(domFile,"Furnishing_Type_OptionsRadioButton");
        if(list.size()==0){
            list=Mapper.finds(domFile,"Furnishing_Type_OptionsCheckbox");
            flag=true;
        }
        int randomOption=new Random().nextInt(list.size());
        if(randomOption<=0){
            randomOption++;
        }
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        if(flag){
            element.sendKeys(Keys.ENTER);
        }
        String furnishingType=list.get(randomOption).getText();
        logger.info("Clicking on Furnishing Type---->"+furnishingType);
    }

    public void selectRandomStyle(){
        WebElement selectBox=Mapper.find(domFile, "Style");
        selectBox.click();
        boolean flag=false;
        List<WebElement> list=new LinkedList<>();
        list=Mapper.finds(domFile,"Style_OptionsRadioButton");
        if(list.size()==0){
            list=Mapper.finds(domFile,"Style_OptionsCheckbox");
            flag=true;
        }
        int randomOption=new Random().nextInt(list.size());
        if(randomOption<=0){
            randomOption++;
        }
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        if(flag){
            element.sendKeys(Keys.ENTER);
        }
        String style=list.get(randomOption).getText();
        logger.info("Clicking on Style---->"+style);
    }

    public void selectRandomAccessoryType(){
        WebElement selectBox=Mapper.find(domFile, "Accessory_Type");
        selectBox.click();
        boolean flag=false;
        List<WebElement> list=new LinkedList<>();
        list=Mapper.finds(domFile,"Accessory_TypeOptionsRadioButton");
        if(list.size()==0){
            list=Mapper.finds(domFile,"Accessory_TypeCheckbox");
            flag=true;
        }
        int randomOption=new Random().nextInt(list.size());
        if(randomOption<=0){
            randomOption++;
        }
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        if(flag){
            element.sendKeys(Keys.ENTER);
        }
        String accessory_Type=list.get(randomOption).getText();
        logger.info("Clicking on Accessory_Type---->"+accessory_Type);
    }

    public void selectRandomJewelleryType(){
        WebElement selectBox=Mapper.find(domFile, "Jewellery_Type");
        selectBox.click();
        boolean flag=false;
        List<WebElement> list=new LinkedList<>();
        list=Mapper.finds(domFile,"Jewellery_Type_OptionsRadioButton");
        if(list.size()==0){
            list=Mapper.finds(domFile,"Jewellery_TypeCheckbox");
        }
        int randomOption=new Random().nextInt(list.size());
        if(randomOption<=0){
            randomOption++;
        }
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        if(flag){
            element.sendKeys(Keys.ENTER);
        }
        String Jewellery_Type=list.get(randomOption).getText();
        logger.info("Clicking on Jewellery---->"+Jewellery_Type);
    }

    public void selectRandomEquipment_Type(){
        WebElement selectBox=Mapper.find(domFile, "Equipment_Type");
        selectBox.click();
        boolean flag=false;
        List<WebElement> list=new LinkedList<>();
        list=Mapper.finds(domFile,"Equipment_Type_OptionsRadioButton");
        if(list.size()==0){
            list=Mapper.finds(domFile,"Equipment_TypeCheckbox");
            flag=true;
        }
        int randomOption=new Random().nextInt(list.size());
        if(randomOption<=0){
            randomOption++;
        }
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        if(flag){
            element.sendKeys(Keys.ENTER);
        }
        String Equipment_Type=list.get(randomOption).getText();
        logger.info("Clicking on Equipment_Type---->"+Equipment_Type);
    }

    public void selectRandomBrandname(){
        WebElement selectBox=Mapper.find(domFile, "Brand_name");
        selectBox.click();
        boolean flag=false;
        List<WebElement> list=new LinkedList<>();
        list=Mapper.finds(domFile,"Brand_name_OptionsRadioButton");
        System.out.println("Here"+list.size());
        if(list.size()==0){
            list=Mapper.finds(domFile,"Brand_nameCheckbox");
            System.out.println("Here now"+list.size());
            flag=true;
        }
        int randomOption=new Random().nextInt(list.size());
        if(randomOption<=0){
            randomOption++;
        }
        System.out.println("Here random"+randomOption);
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        if(flag){
            element.sendKeys(Keys.ENTER);
        }
        String Brand_name=list.get(randomOption).getText();
        logger.info("Clicking on Brand_name---->"+Brand_name);
    }

    public void selectGenre(){
        if (Mapper.waitForElementToBeVisible(domFile,"genre")==true){
            Mapper.find(domFile,"genre").click();
            if (Mapper.waitForElementToBeVisible(domFile,"genreValues")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"genreValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("Genre options were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("Genre dropdown is not visible....");
        }
    }

    public void selectMediaType(){
        if (Mapper.waitForElementToBeVisible(domFile,"Media")==true){
            Mapper.find(domFile,"Media").click();
            if (Mapper.waitForElementToBeVisible(domFile,"mediaValues")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"mediaValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("Media options were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("Media dropdown is not visible....");
        }
    }

    public void selectInstrumentType(){
        if (Mapper.waitForElementToBeVisible(domFile,"InstrumentType")==true){
            Mapper.find(domFile,"InstrumentType").click();
            if (Mapper.waitForElementToBeVisible(domFile,"InstrumentTypeValues")==true){
                List<WebElement> dropDownOptions = Mapper.finds(domFile,"InstrumentTypeValues");
                int numberOfOptions = dropDownOptions.size();
                logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                dropDownOptions.get(randomNum).click();
            }else{
                logger.info("Instrument type options were not populated as dropdown. Please check!");
            }
        }else{
            logger.info("Instrument dropdown is not visible....");
        }
    }
}
