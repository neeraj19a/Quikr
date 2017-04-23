package com.quikr.website.quikrX.quikrXStqPages;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by francis.s@quikr on 6/7/16.
 */
public class QuikrGuaranteedPurchasePage extends PageBase{

    private static final String domFile = getProperties().get("QUIKRX_GUARANTEED_DOM_FILE");

    public QuikrGuaranteedPurchasePage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public void selectBrand(String brand){

        Mapper.find(domFile,"brand").click();
        Mapper.findAndReplace(domFile,"selectBrand",new String[]{brand}).click();

    }


    public void selectModel(String model){

        Mapper.find(domFile,"model").click();
        Mapper.findAndReplace(domFile,"selectModel",new String[]{model}).click();
    }

    public void selectCity(String city){

        Mapper.find(domFile,"city").click();
        Mapper.findAndReplace(domFile,"selectCity",new String[]{city}).click();
    }

    public void clickGetPrice(){

        Mapper.find(domFile,"checkPrice").click();

    }

    public void selectCondition(String condition){

        Mapper.find(domFile,"condition").click();
        Mapper.findAndReplace(domFile,"selectCondition",new String[]{condition}).click();
    }

    public String getPrice(){
        return Mapper.find(domFile,"price").getText();
    }

    public void enterName(){
        Mapper.find(domFile,"nameField").sendKeys();
    }

    public void enterEmail(){
        Mapper.find(domFile,"emailId").sendKeys();
    }

    public void enterPhoneNumber(){
        Mapper.find(domFile,"phoneNumber").sendKeys();
    }

    public void clickConfirm(){
        Mapper.find(domFile,"confirm").click();
    }

    public void getAppUrl(){
        Mapper.find(domFile,"primaryCta").getText();
    }

    public boolean descriptionDisaplyed(){
        return Mapper.find(domFile,"howDoesItWork").isDisplayed();
    }

}
