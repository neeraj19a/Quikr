package com.quikr.website.cars;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Saurabh on 22/10/15.
 */
public class CarsPageBase extends PageBase
{
    public CarsPageBase(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("CARS_PAGEBASE_DOM_FILE");

    public String heading()
    {
        String heading=null;
        sleep(3000);
        try {
            heading=Mapper.find(domFile,"adHeadingNewVapPage").getText();
        }
        catch (Exception e){
            heading=Mapper.find(domFile, "heading").getText();
        }
        logger.info("Heading is " + heading);
        return heading.replaceAll(" ","");
    }

    public void selectCars(){
        if(Mapper.waitForElementToBeClickable(domFile,"Cars")==true){
            Mapper.find(domFile,"Cars").click();
        }
    }

    public void closeCityPopup()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "cityPopUpCloseBtnCars",20)==true) {
            Mapper.find(domFile, "cityPopUpCloseBtnCars").click();
        }
        else {
            logger.info("Looks like City pop Up is not visible");
        }

        if(!Mapper.waitForElementToBeInvisible(domFile, "cityPopUpCloseBtnCars",20))
            Mapper.find(domFile, "cityPopUpCloseBtnCars").click();
    }

    public String citySelected()
    {
        String city=null;
        if(Mapper.waitForElementToBeVisible(domFile, "citySelected")==true) {
            city= Mapper.find(domFile, "citySelected").getText();
        }
        return city;
    }

    public void selectCityFromPopUp(String cityToSelect)
    {
        Mapper.waitForElementToBeVisible(domFile, "changeCityLink",10);
        Mapper.find(domFile, "changeCityLink").click();

        Mapper.waitForElementToBeVisible(domFile, "citySearchTextBox",10);
        Mapper.find(domFile, "citySearchTextBox").click();
        Mapper.find(domFile, "citySearchTextBox").sendKeys(cityToSelect);

        if(Mapper.waitForElementToBeVisible(domFile, "citySelect", new String[] {cityToSelect})==true) {
            Mapper.findAndReplace(domFile, "citySelect", new String[]{cityToSelect}).click();
        }

        else if(!Mapper.waitForElementToBeInvisible(domFile, "cityPopUpCloseBtnCars",10))
        {
            Mapper.find(domFile, "citySearchTextBox").clear();
            Mapper.find(domFile, "citySearchTextBox").sendKeys(cityToSelect);

            Mapper.waitForElementToBeVisible(domFile, "citySelect", new String[] {cityToSelect});
            Mapper.findAndReplace(domFile, "citySelect", new String[] {cityToSelect}).click();
        }
    }

    public boolean replyBoxStatus(String adId)
    {
        boolean replyBoxStatus = false;

        //Checking of Reply Box is visible
        if (Mapper.waitForElementToBeVisible(domFile, "replyBox", new String[]{adId})==true) {
            //Checking if Close Button for that Reply Box is visible
            if(Mapper.waitForElementToBeVisible(domFile, "replyCloseBtn", new String[]{adId})==true) {
                Mapper.findAndReplace(domFile, "replyCloseBtn", new String[]{adId}).click();
            }
            else {
                logger.info("Looks like  replyclose button for "+adId+"is not visible");
            }
        }
        else {
            logger.info("Looks like replyBox for "+adId+"is not visible");
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!Mapper.waitForElementToBeVisible(domFile, "replyBox", new String[]{adId})) {
            replyBoxStatus = true;
        }
        else {
            logger.info("Looks like replyBoxStatus is "+replyBoxStatus);
        }

        return replyBoxStatus;
    }

    public boolean chatBoxStatus(String adId)
    {
        boolean chatBoxStatus = false;

        //Checking of Chat Box is visible
        if (Mapper.waitForElementToBeVisible(domFile, "chatBox", new String[]{adId})==true) {
            //Checking if Close Button for that Chat Box is visible
            if(Mapper.waitForElementToBeVisible(domFile, "chatCloseBtn", new String[]{adId})==true) {
                Mapper.findAndReplace(domFile, "chatCloseBtn", new String[]{adId}).click();
            }
            else {
                logger.info("Looks like "+Mapper.findAndReplace(domFile, "chatCloseBtn", new String[]{adId})+"is not visible");
            }
        }
        else {
            logger.info("Looks like "+Mapper.findAndReplace(domFile, "chatBox", new String[]{adId})+"is not visible");
        }


        if(!Mapper.waitForElementToBeVisible(domFile, "chatBox", new String[]{adId}))
            chatBoxStatus = true;

        return chatBoxStatus;
    }

    public boolean validatePageURL(String expectedURL)
    {
        return (getCurrentLocation().contains(expectedURL));
    }

    public void navigateToURL(String URL)
    {
        navigateTo().to(URL);
    }

    public String toCamelCase(String input)
    {
        String[] parts = input.split(" ");
        String output = "";

        for(String part : parts)
        {
            output = output + toProperCase(part) + " ";
        }

        return output.trim();
    }

    public String toProperCase(String input)
    {
        return input.substring(0,1).toUpperCase() + input.substring(1);
    }
}
