package com.quikr.msite.mJobs.mJobsPAP;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.Select;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 1/12/15.
 */
public class MJobsPAP extends MPageBase
{
    private static final String domFile = getProperties().get("mJobs_PAP_DOM_FILE");
    public MJobsPAP(AppiumDriver driver)
    {
        super(domFile, driver);
    }

    public void clickPostFreeAdBtn()
    {
        if (Mapper.find(domFile,"postFreeAdBtn")==null)
        {
            Mapper.find(domFile,"postFreeAdBtn").click();
        }else
        {
            logger.info("Post free ad button not visible/enabled. Please check!");
        }
    }

    public void clickNext()
    {
        if (Mapper.find(domFile,"AddPhotoPageNextBtn")!=null)
        {
            Mapper.find(domFile,"AddPhotoPageNextBtn").click();
        }
        else if (Mapper.find(domFile,"SelectAttrPageNextBtn")!=null)
        {
            Mapper.find(domFile,"SelectAttrPageNextBtn").click();
        }
        else
        {
            logger.info("No NEXT button present. Please check!");
        }
    }

    public void selectJobs()
    {
        Mapper.find(domFile,"selectJobsForPostingAd").click();
    }

    public void selectEmployer()
    {
        if (Mapper.find(domFile,"selectIamAnEmployer")==null)
        {
            Mapper.find(domFile,"selectIamAnEmployer").click();
        }else
        {
            logger.info("Select employer button not visible/enabled. Please check!");
        }
    }

    public void selectJobSeeker()
    {
        if (Mapper.find(domFile,"selectIamAJobSeeker")==null)
        {
            Mapper.find(domFile,"selectIamAJobSeeker").click();
        }else
        {
            logger.info("Job Seeker button not visible/enabled. Please check!");
        }
    }

    public void selectJobRole(String Role)
    {
        Select roles = new Select(Mapper.find(domFile,"OptionRole"));
        roles.selectByValue(Role);
    }

    public void selectEducation(String edu)
    {
        Select roles = new Select(Mapper.find(domFile,"OptionEdu"));
        roles.selectByValue(edu);
    }

    public void selectExperience(String exp)
    {
        Select roles = new Select(Mapper.find(domFile,"OptionExp"));
        roles.selectByValue(exp);
    }

    public void selectCity(String city)
    {
        Select roles = new Select(Mapper.find(domFile,"OptionCity"));
        roles.selectByValue(city);
    }

    public void selectLocality(String loc)
    {
        Select roles = new Select(Mapper.find(domFile,"OptionLocality"));
        roles.selectByValue(loc);
    }

    public void inputAdTitle(String adTitle)
    {
        if (Mapper.find(domFile,"PostAdTitle")!=null)
        {
            Mapper.find(domFile,"PostAdTitle").sendKeys(adTitle);
        }else
        {
            logger.info("Ad title input box not visible. Please check!");
        }
    }

    public void inputAdDesc(String adDesc)
    {
        Mapper.find(domFile,"PostAdDescription").sendKeys(adDesc);
    }

    public void postAd()
    {
        Mapper.find(domFile,"PostAdBtn").click();
    }

    public boolean validateAdPostSuccess()
    {
        boolean retVal;
        if (Mapper.find(domFile,"PostAdSuccessCongrats")!=null)
        {
            retVal = true;
        }
        else
        {
            logger.info("Ad not posted successfully. <Congratulations> missing");
            return false;
        }
        return retVal;
    }


}
