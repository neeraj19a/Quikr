package com.quikr.website.cars.carsVAP;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 23/8/16.
 */
public  class NewCarsVapPage extends CarsPageBase {


    public NewCarsVapPage(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String domFile = getProperties().get("NEWCARS_VAP_PAGE_DOM_FILE");


    public boolean validateUpcomingCarsFeatures() {

        int countFalse=0;

        List<WebElement> upcomingCarFeatures = Mapper.finds(domFile, "upcomingCarsFeatures");
        List<String> featuresList = new ArrayList<>();
        for (WebElement element : upcomingCarFeatures) {
            featuresList.add(element.getText());
        }

            if(featuresList.contains("EXPECTED PRICE")){
                if(featuresList.contains("ENGINE")){
                    if(featuresList.contains("MILEAGE")){
                    }
                    else {
                        countFalse++;
                        logger.info("looks like MILEAGE is not present");
                    }
                }
                else {
                    countFalse++;
                    logger.info("looks like ENGINE is not present");
                }
            }
        else {
                countFalse++;
                logger.info("Looks like EXPECTED PRICE is not present");
            }
        return countFalse==0;

    }

    public boolean validateButtonsOnUcomingCarsVapPage(){
        WebElement upcomingCarPrice = Mapper.find(domFile, "upcomingCarsVapPrice");
        WebElement upcomingCarNotifyMe = Mapper.find(domFile, "upcomingCarsVapNotifyMeButton");
        WebElement upcomingCarContactDealer = Mapper.find(domFile, "upcomingCarsVapContactDealerButton");

        if(upcomingCarPrice!=null && upcomingCarNotifyMe!=null && upcomingCarContactDealer!=null){
            return true;
        }
        else{
            logger.info("Looks like some button are not present-->upcomingCarPrice "+upcomingCarPrice != null);
            logger.info("Looks like some button are not present-->upcomingCarNotifyMe"+upcomingCarNotifyMe!=null);
            logger.info("Looks like some button are not present-->upcomingCarContactDealer"+upcomingCarContactDealer!=null);

            return false;
        }
    }

    public boolean clickContactDealersButton(String name,String mobile,String email){
        
        if(Mapper.waitForElementToBeClickable(domFile,"ContactDealersButton")==true){
            List<WebElement> contactDealerButtons=Mapper.finds(domFile, "ContactDealersButton");
            if(contactDealerButtons.size()>0){
                contactDealerButtons.get(0).click();
                sleep(3000);
                if(Mapper.find(domFile,"dealerForm").isDisplayed()){
                    Mapper.find(domFile,"dealerFormName").click();
                    Mapper.find(domFile,"dealerFormName").sendKeys(name);
                    Mapper.find(domFile,"dealerFormMobile").sendKeys(mobile);
                    Mapper.find(domFile,"dealerFormEmail").sendKeys(email);
                    Mapper.find(domFile, "dealerFormModelInterested").click();
                    Mapper.finds(domFile,"dealerFormModelInterestedOption").get(0).click();
                    Mapper.find(domFile,"dealerFormModelViewDealerDetails").click();
                    if(Mapper.find(domFile,"dealerForm").isDisplayed()){
                       return false;
                    }
                }

            }
            else {
                logger.info("Looks like Contact Dealer is not present");
                return  false;
            }
        }
        else {
            logger.info("Looks likeLooks like Contact DealerButtons are not present");
            return false;
        }
        return true;
    }

    public boolean validateImagesonUpcomingCarsVAP(String modelName){
        int countfalse=0;
        String image=Mapper.find(domFile,"upcomingCarsImage").getAttribute("src");
        if(!image.contains(modelName)){
            countfalse++;
            logger.info("looks like Images scr is not proper--->"+image+" but brand is-->"+modelName);
        }

        return countfalse==0;
    }

    public String getHeadingfromNewCarsVapPage(){
        String heading=null;
        if(Mapper.waitForElementToBeVisible(domFile,"heading")==true){
            heading=Mapper.find(domFile,"heading").getText();
        }
        return heading;
    }

    public  boolean clickContactDealer(String city){
        int countFalse=0;
        if(Mapper.waitForElementToBeVisible(domFile,"upcomingCarsVapContactDealerButton")==true){
            Mapper.find(domFile,"upcomingCarsVapContactDealerButton").click();
            waitForPageToLoad("quikr.com/cars/dealer-locater");
            if(getHeadingfromNewCarsVapPage().matches("\\w*[Showing]+\\s?\\d?\\s?\\w+\\s?\\w+\\s?Showrooms in "+city+"")||getHeadingfromNewCarsVapPage().matches("\\w*[Showing]+\\s?\\d?\\s?\\w+\\s?\\w+\\s?Showroom in "+city+"")){

            }
            else {
                countFalse++;
                logger.info("Looks like heading is not proper for Dealers");
            }
        }
        else {
            countFalse++;
            logger.info("Looks like Contact Dealer button is not visible");
        }
        logger.info("Heading for New Cars VAP is -->"+getHeadingfromNewCarsVapPage());
        logger.info("CountFalse is-->"+countFalse);
        return countFalse==0;
    }

    public void clickFindDealers(){
        if(Mapper.waitForElementToBeClickable(domFile, "findDealersButton")==true){
            Mapper.find(domFile,"findDealersButton").click();
        }
    }

    public boolean checkContactDealersButtonPreFilled() {
        if(Mapper.waitForElementToBeClickable(domFile,"ContactDealersButton")==true) {
            List<WebElement> contactDealerButtons = Mapper.finds(domFile, "ContactDealersButton");
            if (contactDealerButtons.size() > 0) {
                contactDealerButtons.get(0).click();
            }
        }

        sleep(3000);
        if (Mapper.find(domFile, "dealerForm").isDisplayed()) {
            if(!Mapper.find(domFile, "dealerFormName").getText().isEmpty()
            && !Mapper.find(domFile, "dealerFormMobile").getText().isEmpty()
            && !Mapper.find(domFile, "dealerFormEmail").getText().isEmpty()){
                logger.info("Values for Name ,Mobile and Email are present");
            }
            else {
                logger.info("Values for Name ,Mobile and Email are not present");
            }
        }
        else {
            logger.info("Looks like Dealer form is not visible");
            return false;
        }

        return true;
    }

}