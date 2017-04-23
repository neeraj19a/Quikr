package com.quikr.website.education.educationVAP;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 9/9/16.
 */
public class EducationVAPPage extends PageBase {

    private static final String domFile = getProperties().get("EDUCATION_VAP_DOM_FILE");

    public EducationVAPPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    public boolean isFacilitiesPresent(){
        return isElementPresent("facilities");
    }

    public boolean isCoursesPresent(){
        return isElementPresent("courses");
    }

    public boolean isLocalityPresent(){
        return isElementPresent("locality");
    }

    public boolean isContactPresent(){
        return isElementPresent("contact");
    }

    public String getCollegeTitle(){
        return Mapper.find(domFile,"collgeTitle").getText();
    }

    public void clickPostAdBtn(){
        Mapper.find(domFile,"postAdBtn").click();
    }

    public boolean verifyRespectiveCollege(String intialCollegeText, String newCollegeName){
        if (newCollegeName.contains(intialCollegeText))
            return true;
        else
            return false;
    }

}
