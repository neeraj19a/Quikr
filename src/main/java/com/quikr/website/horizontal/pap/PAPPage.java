package com.quikr.website.horizontal.pap;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.Random;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 12/5/16.
 */
public class PAPPage extends PageBase
{
    // const string
    private static final String domFile = getProperties().get("HORIZONTAL_PAP_DOM_FILE");

    public PAPPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    /**
     * click on category and then on subsequent sub-category
     * @param categoryName
     * @param subCategory
     */
    public void selectSubCategory(QuikrEnums.CategoryName categoryName, Enum subCategory)
    {
        boolean cond =  categoryName == QuikrEnums.CategoryName.ENTERTAINMENT ||
                categoryName == QuikrEnums.CategoryName.PETS_AND_PET_CARE ||
                categoryName == QuikrEnums.CategoryName.EDUCATION_AND_TRAINING ||
                categoryName == QuikrEnums.CategoryName.COMMUNITY ||
                categoryName == QuikrEnums.CategoryName.EVENTS ||
                categoryName == QuikrEnums.CategoryName.MATRIMONIAL;

        if(cond)
        {
            try {
                Mapper.findAndReplace(domFile, "categoryName", new String[]{QuikrEnums.CategoryName.OTHERS.toString()}).click();
            }catch (Exception e){}
        }

        Mapper.findAndReplace(domFile, "categoryName", new String[]{categoryName.toString()}).click();
        Mapper.findAndReplace(domFile, "subCategoryName", new String[]{categoryName.toString(), subCategory.toString()}).click();
    }

    /**
     *Provide adType Options as 1 or 2 to select radio Button
     * @param adTypeOption
     */

    public void selectAdType(int adTypeOption)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"typeOfAdLabel1")==true){
            logger.info("Ad type field became visible.");
            if (adTypeOption==1)
            {
                String adOption1label=Mapper.find(domFile,"typeOfAdLabel1").getText();
                logger.info("Going to select adtype Option 1 -->"+adOption1label);
                Mapper.findAndReplace(domFile, "typeOfAd", new String[]{Integer.toString(adTypeOption)}).click();
            }
            else if (adTypeOption==2)
            {
                String adOption2label=Mapper.find(domFile,"typeOfAdLabel2").getText();
                logger.info("Going to select adType Option 2 -->"+adOption2label);
                Mapper.findAndReplace(domFile, "typeOfAd", new String[]{Integer.toString(adTypeOption)}).click();
            }
            else
            {
                logger.info("Invalid argument provided. you need to give argument as 1 or 2");
            }
        }
        else
        {
            logger.info("Ad type field is not visible.");
        }
    }

    public void inputAdTitle(String adTitle){
        if (Mapper.waitForElementToBeVisible(domFile, "adTitle",15)==true){
            logger.info("<adTitle> field is visible.");
            WebElement elm = Mapper.find(domFile, "adTitle");
            elm.click();
            elm.clear();
            elm.sendKeys(adTitle);
        }else{
            logger.info("<adTitle> field was not visible.");
        }
    }

    public void selectCondition(){

        try {
            if (Mapper.waitForElementToBeVisible(domFile, "condition") == true) {
                logger.info("condition field became visible.");
                List<WebElement> radioButtonOptions = Mapper.finds(domFile, "condition");
                int numberOfOptions = radioButtonOptions.size();
                logger.info("Count of options available for condition field are are :: " + numberOfOptions);
                int randomNum = new Random().nextInt(numberOfOptions);
                logger.info("random number for selecting condition field is :: " + randomNum);
                radioButtonOptions.get(randomNum).click();
            }
        }
        catch (Exception e){
            logger.info("Condition field is not visible.");
        }
    }

    public void inputPrice(String price){
        try {
            if (Mapper.waitForElementToBeVisible(domFile, "price",15) == true) {
                Mapper.find(domFile, "price").click();
                Mapper.find(domFile, "price").sendKeys(price);
            }
        }
        catch (Exception e){   logger.info("Text field to input price is not visible....");
        }
    }

    public void inputDescription(String description){
        if (Mapper.waitForElementToBeVisible(domFile, "description")==true){
            Mapper.find(domFile,"description").click();
            Mapper.find(domFile, "description").sendKeys(description);
        }else{
            logger.info("Description field was not visible....");
        }
    }

    public void selectSellerType(){
        if (Mapper.waitForElementToBeVisible(domFile, "sellerType")==true){
            logger.info("sellerType field became visible.");
            List<WebElement> radioButtonOptions = Mapper.finds(domFile,"sellerType");
            int numberOfOptions = radioButtonOptions.size();
            logger.info("Count of options available in the dropdown are :: " + numberOfOptions);
            int randomNum = new Random().nextInt(numberOfOptions);
            radioButtonOptions.get(randomNum).click();
        }else{
            logger.info("Seller type field is not visible.");
        }
    }

    public void  enterEmail(String email){
        if (Mapper.waitForElementToBeVisible(domFile, "emailId")==true){
            Mapper.find(domFile, "emailId").click();
            Mapper.find(domFile, "emailId").sendKeys(email);
        }else{
            logger.info("Email id text field was not visible.");
        }
    }

    public void enterCity(String cityName){
        if (Mapper.waitForElementToBeVisible(domFile, "cityName")==true){
            Mapper.find(domFile,"cityName").click();
            Mapper.find(domFile,"enterCityName").click();
            Mapper.find(domFile,"enterCityName").sendKeys(cityName);
            sleep(2000);
            Mapper.findAndReplace(domFile, "cityNameAutocomplete", new String[]{cityName}).click();

        }else{
            logger.info("City input text field was not visible.");
        }
    }

    public void selectLocality(){
        try {
            if (Mapper.waitForElementToBeVisible(domFile, "localityTextBox",15) == true) {
                Mapper.find(domFile, "localityTextBox").click();
                if (Mapper.waitForElementToBeVisible(domFile, "resultingAutoSuggestedLocality") == true) {
                    List<WebElement> options = Mapper.finds(domFile, "resultingAutoSuggestedLocality");
                    int randomOption = new Random().nextInt(options.size());
                    options.get(randomOption).click();
                    logger.info("Clicked at Locality -->" + options.get(randomOption).getAttribute("locname"));
                } else {
                    logger.info("No auto suggest option was displayed.");
                }
            }
        }
        catch (Exception e){
            logger.info("Locality text box was not visible.");
        }
    }

    public void enterPincode(String pincode){
        try {
            Mapper.find(domFile, "pincode").click();
            Mapper.find(domFile, "pincode").sendKeys(pincode);
        }
        catch (Exception e){
            logger.info("Pin code Field is not available");
        }
    }

    public void postTheAd(){
        if (Mapper.waitForElementToBeVisible(domFile, "postAd")==true){
            Mapper.find(domFile,"postAd").click();
        }else{
            logger.info("Post ad button ws not visible.");
        }
    }

    public void shareAdWithNeighbour() {
        Random rand = new Random();
        int max = 5, min = 1, randomNum = rand.nextInt(max - min) + min;
        if (Mapper.waitForElementToBeVisible(domFile, "shareWithNeighbour") == true) {
            Mapper.find(domFile, "shareWithNeighbour").click();
            Mapper.find(domFile, "shareWithNeighbour").sendKeys("ap");
            if (Mapper.waitForElementToBeVisible(domFile, "shareWithNeighbourvalues") == true) {
                Mapper.findAndReplace(domFile, "shareWithNeighbourvalues", new String[]{Integer.toString(randomNum)}).click();
            } else {
                logger.info("Neighbour values were not populated as dropdown. Please check!");
            }
        } else {
            logger.info("Share with neighbour field is not visible....");
        }
    }

    public boolean adPostedSucessfully(){
        waitForPageToLoad("redesignCitrus");
        Mapper.find(domFile,"skipLinkAfterPostAd").click();
        waitForPageToLoad("PostAd/?succeed");
        boolean a=false;
        boolean b=false;
        try {
        a=Mapper.find(domFile,"adIdPostAd").getText().contains("Ad Id");
        } catch (Exception e){
            logger.info("Looks like a different Post Ad Success page is coming");
        }
        try{
        b=Mapper.find(domFile,"adIdPostAdNew").getText().contains("Ad Id");
        } catch (Exception e){
            logger.info("Looks like a different Post Ad Success page is coming");
        }

        if( getCurrentLocation().contains("PostAd/?succeed") && (a||b ))
            return true;
        else
            return false;
    }

    public void enterMobileNumber(){
        String num = "9"+getRandomInteger(9);
        logger.info("Number ::" + num);
        Mapper.find(domFile,"mobileNumber").click();
        Mapper.find(domFile,"mobileNumber").sendKeys(num);
    }

    public void enterAddress(){
        try {
            Mapper.find(domFile, "address").click();
            Mapper.find(domFile, "address").sendKeys("#196, third cross, tavarerekere");
        }
        catch (Exception e){
            logger.info("Address field is not visible");
        }
    }

    public void clickEditThisAd(){
        if (Mapper.waitForElementToBeVisible(domFile,"editThisAd")==true){
            Mapper.find(domFile,"editThisAd").click();
        }else{
            logger.info("EditThisAd button was not visible....");
        }
    }
}
