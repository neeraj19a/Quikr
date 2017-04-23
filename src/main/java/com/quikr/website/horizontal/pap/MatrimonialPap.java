package com.quikr.website.horizontal.pap;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 12/5/16.
 */
public class MatrimonialPap extends PageBase
{
    // const string
    private static final String domFile = getProperties().get("MATRIMONIAL_PAP_DOM_FILE");

    public MatrimonialPap(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public void selectRandomAge(){
        WebElement selectBox=Mapper.find(domFile, "age");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        list.get(randomOption).click();
    }

    public void selectRandomHeight(){
        WebElement selectBox=Mapper.find(domFile, "height");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        list.get(randomOption).click();
    }

    public void selectRandomMaritalStatus(){
        WebElement selectBox=Mapper.find(domFile, "marital_Status");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        list.get(randomOption).click();
    }

    public void selectRandomReligion(){
        WebElement selectBox=Mapper.find(domFile, "religion");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        list.get(randomOption).click();
    }

    public void selectRandomMother_Tongue(){
        WebElement mother_tongue=Mapper.find(domFile, "mother");
        mother_tongue.click();
        List<String> motherTongue=new ArrayList<>();
        motherTongue.add("Hindi");
        motherTongue.add("Gujarati");
        motherTongue.add("Kashmiri");
        motherTongue.add("Bengali");
        motherTongue.add("Himachal");
        int random=new Random().nextInt(motherTongue.size());
        String randomMontherTongue=motherTongue.get(random);
        mother_tongue.sendKeys(randomMontherTongue);
    }

    public void selectRandomEducationQualification(){
        WebElement selectBox=Mapper.find(domFile, "education_Qualification");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        list.get(randomOption).click();
    }

    public void selectRandomCurrent_Occupation(){
        WebElement selectBox=Mapper.find(domFile, "current_Occupation");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        list.get(randomOption).click();
    }

    public void selectRandomSun_Sign(){
        WebElement selectBox=Mapper.find(domFile, "sun_Sign");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        list.get(randomOption).click();
    }


    public void selectRandomNakshatra(){
        WebElement selectBox=Mapper.find(domFile, "nakshatra");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        list.get(randomOption).click();
    }

    public void selectCommonMatrimonialOptions(){
        selectRandomAge();
        selectRandomHeight();
        selectRandomMaritalStatus();
        selectRandomReligion();
        selectRandomMother_Tongue();
        selectRandomEducationQualification();
        selectRandomCurrent_Occupation();
        selectRandomSun_Sign();
        selectRandomNakshatra();

    }

}