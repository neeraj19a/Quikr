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
 * Created by quikr on 24/5/16.
 */
public class PetsandPetCarePap extends PageBase {

    // const string
    private static final String domFile = getProperties().get("PETSANDPETCARE_PAP_DOM_FILE");

    public PetsandPetCarePap(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public void selectRandomPetType(int adType){

        WebElement selectBox=Mapper.find(domFile, "petType");
        selectBox.click();
        List<WebElement> list=new LinkedList<>();
        if(adType==1) {
            list= Mapper.finds(domFile, "petTypeRadioOptions");
        }
        else {
            list= Mapper.finds(domFile, "petTypeCheckboxOptions");
        }

        int randomOption = new Random().nextInt(list.size());
        if (randomOption<=0)
        {
        randomOption++;
        }
        WebElement element=list.get(randomOption);
        element.click();
        logger.info("Clicked on the petType Option -->" + element.getAttribute("lblval"));
        if(adType==2){
            element.sendKeys(Keys.ENTER);
        }
    }
}
