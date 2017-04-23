package com.quikr.website.cars.carsVAP;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Saurabh on 15/11/15.
 */
public class CarsVAPPage extends CarsPageBase
{

    public CarsVAPPage(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String domFile = getProperties().get("CARS_VAP_PAGE_DOM_FILE");

    public String adPrice()
    {
        WebElement element=null;
        if(Mapper.find(domFile,"adPriceNewVapPage")!=null){
            element=Mapper.find(domFile,"adPriceNewVapPage");
        }
        else if (Mapper.find(domFile, "adPrice")!=null){
            element=Mapper.find(domFile, "adPrice");
        }
        return element.getText();
    }

    public String adUserStatus()
    {
        Mapper.waitForElementToBeVisible(domFile, "adUserStatus");
        return Mapper.find(domFile, "adUserStatus").getText();
    }

    public HashMap<String, String> adBasicDetailsData(String vehicleClass)
    {
        HashMap<String, String> data = new HashMap<>();
        if (vehicleClass.equals("cars")) {
            Mapper.waitForElementToBeVisible(domFile, "adBasicDetails");
            List<WebElement> adBasicDetailsSection = Mapper.finds(domFile, "adBasicDetails");

            for (int i = 1; i <= adBasicDetailsSection.size(); i++) {

                String key = Mapper.findAndReplace(domFile, "adBasicDetailsLabel", new String[]{Integer.toString(i)}).getText().toUpperCase();
                String price = adPrice();
                data.put("PRICE", price);
                String value = Mapper.findAndReplace(domFile, "adBasicDetailsValue", new String[]{Integer.toString(i)}).getText();
                String title = heading();
                data.put(key, value);
                data.put("TITLE", title);
            }
            logger.info("Ad Basic Details Data" + data);

            data.remove("COLOR");
            data.remove("INSURANCE VALID TILL");
            data.remove("OWNER");
            logger.info("After removal" + data);

        }
        else {
            WebElement kmsDetails=Mapper.find(domFile, "adKmsDetails");
            if(kmsDetails!=null&& kmsDetails.getText().toLowerCase().contains("kms")) {
                data.put("KMS DRIVEN",kmsDetails.getText());
            }
            String price = adPrice();
            data.put("PRICE", price);
            String title = heading();
            data.put("TITLE", title);

            logger.info("Ad Basic Details Data" + data);
        }
        return data;
    }

    public boolean validateChatReplyButton(){
        List<WebElement> chatReplyButtons=Mapper.finds(domFile,"chatReplyButtonNewVapPage");
        List<WebElement> visibleChatReplyButtons=new ArrayList<>();
        for(WebElement element:chatReplyButtons){
            if(element.isDisplayed()){
                visibleChatReplyButtons.add(element);
            }
        }
        logger.info("Visible Chat or Reply Button-->" + visibleChatReplyButtons.size());
        visibleChatReplyButtons.get(0).click();
        boolean visible=Mapper.find(domFile,"chatReplyFormVisibility").isDisplayed();
        logger.info("Is Chat or Reply Window Visible-->"+visible);
        return  visible;
    }

    public boolean clickAdShortList(){

        boolean flag=false;

        if(Mapper.waitForElementToBeClickable(domFile,"adShortlistUnClickedState")==true){
            Mapper.find(domFile,"adShortlistUnClickedState").click();
            flag=true;
        }
        else {
            logger.info("Looks like Shortlist icon is not present on VAP Page ");
        }
        if(Mapper.find(domFile,"adShortlistClickedState")!=null){
            logger.info("ShortList Clicked");
            flag=true;
        }
        else {
            logger.info("ShortList Icon not Clicked");
        }
        return flag;
    }

    public boolean validateShareIconVAP(){
        boolean flag=false;
        if(Mapper.waitForElementToBeClickable(domFile,"shareIconVap")==true){
            flag=true;
            Mapper.find(domFile,"shareIconVap").click();
        }
        else {
            flag=false;
            logger.info("Share Icon is not visible");
        }

        System.out.println("Here is share Text-->"+Mapper.find(domFile,"shareModalHeading").getText());
        if(Mapper.find(domFile,"shareModalHeading").getText().trim().equals("Share on")){
            List<WebElement> elements=Mapper.finds(domFile,"shareOptions");
                if(elements.get(0).getAttribute("href").contains("https://www.facebook.com/sharer/sharer.php?")){
                    flag=true;
                    logger.info("Facebook Share is available");
                    if(elements.get(1).getAttribute("href").contains("https://twitter.com/intent/tweet/?")){
                        flag=true;
                        logger.info("Twitter Share is available");
                        if(elements.get(2).getAttribute("href").contains("https://plus.google.com/share?url")){
                            flag=true;
                            logger.info("Google Plus share is available");
                        }
                        else {
                            flag=false;
                            logger.info("Google Plus share is not available");
                        }
                    }
                    else {
                        flag=false;
                        logger.info("Twitter Share is not available");
                    }
                }
            else {
                    flag=false;
                    logger.info("Facebook Share is not available");
                }

        }
        else {
            flag=false;
            logger.info("Share Heading is not visible");
        }
    return flag;
    }

}