package com.quikr.website.horizontal.pap;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 24/5/16.
 */
public class CommunityandEventsPap extends PageBase {

    // const string
    private static final String domFile = getProperties().get("COMMUNITYANDEVENTS_PAP_DOM_FILE");

    public CommunityandEventsPap(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public String selectRandomEventCategory(){
            WebElement selectBox=Mapper.find(domFile, "eventCategory");
            selectBox.click();
            List<WebElement> list=Mapper.finds(domFile,"eventCategory_RadioButton");
            int randomOption=new Random().nextInt(list.size());
            WebElement element=list.get(randomOption);
            getElementintoView(element);
            element.click();
            String eventname=list.get(randomOption).getText();
        return eventname;
    }


    public String selectRandomEventType(){
        WebElement selectBox=Mapper.find(domFile, "eventType");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile,"eventType_RadioButton");
        int randomOption=new Random().nextInt(list.size());
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        String eventname=list.get(randomOption).getText();
        return eventname;
    }

    public void selectRandomEventStartDateandStartTime(){

        String eventname= selectRandomEventCategory();
        if(!eventname.trim().equalsIgnoreCase("Festivals")) {
            WebElement dateIcon = Mapper.find(domFile, "eventStartDate");
            dateIcon.click();
            Calendar now = Calendar.getInstance();
            now.add(Calendar.DATE, 10);
            int newdate = now.get(Calendar.DATE);

            WebElement date = Mapper.findAndReplace(domFile, "eventStartDatePicker", new String[]{Integer.toString(newdate)});
            if (newdate <= now.DATE) {
                WebElement element = Mapper.find(domFile, "eventStartDatePickerNextMonth");
                getElementintoView(element);
                element.click();
                date.click();
            } else {
                date.click();
            }
        }
        else {
            logger.info("Event Selected is Festivals so Date is not required field omiting Date Field");
        }

        if(!eventname.trim().equalsIgnoreCase("Festivals")) {
            if(Mapper.waitForElementToBeClickable(domFile,"eventEndTime")==true){
                Mapper.find(domFile,"eventEndTime").click();
                Mapper.find(domFile,"eventEndTime").sendKeys("15:30");
            }
            else {
                logger.info("Event Start Time Box is not visible");
            }

        }
        else {
            logger.info("Event Selected is Festivals so Time is not required field omiting Time Field");
        }
    }

}
