package com.quikr.website.cars.carsPAP;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.*;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Saurabh on 15/11/15.
 */
public class CarsPAPPage extends CarsPageBase {

    public CarsPAPPage(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String domFile = getProperties().get("CARS_PAP_PAGE_DOM_FILE");

    public boolean isElementPresent(String element,String text){
        if(Mapper.waitForElementToBeVisible(domFile,element)==true){
            logger.info("Text of Element is "+Mapper.find(domFile,element).getText().replaceAll(" ", ""));
            return Mapper.find(domFile,element).getText().replaceAll(" ", "").contains(text);
        }
        else
        {
            logger.info("Looks like signInPAP Link is not present");
            return false;
        }
    }

    public boolean isSignINPresent(){
        return isElementPresent("signInPAP", "SignIn/Register");
    }

    public boolean isHelpPresent(){
        return isElementPresent("helpPAP", "Help");
    }

    public boolean isLanguagePresent(){
        return isElementPresent("languagePAP","Languages");
    }

    public boolean validateTextBoxesonPAP() {

        boolean flag = false;
        List<WebElement> textBoxes = Mapper.finds(domFile, "textBoxesPAPText");
        Set<String> text = new HashSet<>();
        String[] expectedText = {"Selected Category", "Type of ad", "Photos for your ad", "Brand Name", "Fuel Type", "Year of Registration", "Kms Driven", "Price", "Number of Owners", "Insurance Valid Till", "Color", "Your City", "Description", "You Are", "Your Name", "Your Email", "Your Mobile No."};
        if (textBoxes.size() == 22) {
            logger.info("Number of Text Boxes on Cars PAP are 22 seems fine");
        } else {
            logger.info("Number of Text Boxes on Cars PAP are " + textBoxes.size() + " seems PAP is not fine it should be 22");
            flag = false;
        }

        for (WebElement element : textBoxes) {
            text.add(element.getText());
        }
        List<String> expected = Arrays.asList(expectedText);
        Collections.sort(expected);
        List<String> realtext=new ArrayList<>(text);
        //Removing gargbage element at 0th position
        realtext.remove(0);
        Collections.sort(realtext);
        logger.info(expected+"and size is "+expected.size());
        logger.info(realtext + "and size is " + realtext.size());

        for (int i = 0; i < expected.size(); i++) {
                    if (expected.get(i).replaceAll("[, ]", "").contains(realtext.get(i).replaceAll("[, ]", ""))) {
                        flag = true;
                    } else {
                        flag = false;
                        logger.info("Looks like text of the Text Boxes on PAP is not matching");
                    }
                }
        return flag;
    }

    public String PAPHeading()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "oldPAPHeading"))
            return Mapper.find(domFile, "oldPAPHeading").getText();

        else if(Mapper.waitForElementToBeVisible(domFile, "newPAPHeading"))
            return Mapper.find(domFile, "newPAPHeading").getText();

        else
            return "PAP not loaded properly";
    }


    public WebElement selectRandomElement(String element){
        List<WebElement> list=Mapper.finds(domFile,element);
        int randomOption=new Random().nextInt(list.size());
        return list.get(randomOption);
    }

    public void postCarAd(String adTitle,String name, String description){

        Mapper.findAndReplace(domFile, "typeOfAdPAP", new String[]{Integer.toString(1)}).click();
        WebElement title=Mapper.find(domFile, "titlePAP");
        //title.click();
        //title.sendKeys(adTitle);
        //Mapper.findAndReplace(domFile, "conditionPAP", new String[]{Integer.toString(1)}).click();
        Mapper.find(domFile, "brandNamePAP").click();
        selectRandomElement("brandNameRadioOptions").click();
        Mapper.find(domFile, "model_PAP").click();
        //To handle random model selection from visible Models
        List<WebElement> list=Mapper.finds(domFile,"modelOptions");
        List<WebElement> visibleOptions=new ArrayList<>();
        for (WebElement element:list){
            if(element.isDisplayed()){
                visibleOptions.add(element);
            }
        }
        int random=new Random().nextInt(visibleOptions.size());
        WebElement element=visibleOptions.get(random);
        Mapper.findChilds(element).get(0).click();

        Mapper.find(domFile, "fuelTypePAP").click();
        selectRandomElement("fuelTypeRadioOptions").click();
        Mapper.find(domFile, "yearofRegistrationPAP").click();
        selectRandomElement("yearofRegistrationOptions").click();
        Mapper.find(domFile, "Kms_DrivenPAP").sendKeys("3000");
        Mapper.find(domFile, "PricePAP").sendKeys("40000");
        Mapper.find(domFile, "No_of_owners_PAP").click();
        selectRandomElement("No_of_owners_Options").click();
        Mapper.find(domFile, "Color_PAP").click();
        selectRandomElement("Color_Options").click();
        Mapper.find(domFile, "cityPAP").click();
        String city="Delhi";
        Mapper.find(domFile,"searchCityPAP").click();
        Mapper.find(domFile, "searchCityPAP").sendKeys(city);
        sleep(2000);
        Mapper.findAndReplace(domFile, "city_Options", new String[]{city}).click();
        Mapper.waitForElementToBeClickable(domFile, "localityPAP");
        Mapper.find(domFile,"localityPAP").click();
        Mapper.find(domFile,"localityOptions").click();
        Mapper.find(domFile,"descriptionPAP").click();
        Mapper.find(domFile,"descriptionPAP").sendKeys(description);
        Mapper.findAndReplace(domFile, "sellerOptions", new String[]{Integer.toString(1)}).click();
        Mapper.find(domFile, "namePAP").click();
        Mapper.find(domFile,"namePAP").sendKeys(name);
        Mapper.find(domFile,"emailidPAP").click();
        Mapper.find(domFile,"emailidPAP").sendKeys(getRandomString(11) + "@gmail.com");
        Mapper.find(domFile,"mobilePAP").click();
        Mapper.find(domFile,"mobilePAP").sendKeys(9 + getRandomInteger(9));
        Mapper.find(domFile,"postButtonPAP").click();
    }

    public void skipPayment(){
        if(Mapper.waitForElementToBeClickable(domFile, "skipPayment")==true) {
            Mapper.find(domFile, "skipPayment").click();
        }
        else{
            logger.info("Looks like Skip Payment link is not available");
        }
    }

    public boolean isThanksMessageavailable(){
        if(Mapper.waitForElementToBeVisible(domFile,"ThanksMessage")==true) {
            if (Mapper.find(domFile, "ThanksMessage").getText().contains("Thanks for posting your ad on Quikr")) {
                return true;
            } else {
                logger.info("Looks like Thanks message is not proper");
                return false;
            }
        }
        else {
            logger.info("Looks like Thanks message is not available");
            return false;
        }
    }

    public String getAdId(){
        return (Mapper.find(domFile,"adId").getText().replaceAll("Ad Id","").replaceAll("[:() ]",""));
    }


}