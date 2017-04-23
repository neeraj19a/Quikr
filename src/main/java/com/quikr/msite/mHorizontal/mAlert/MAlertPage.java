package com.quikr.msite.mHorizontal.mAlert;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 10/11/15.
 */
public class MAlertPage extends MPageBase {

    private static final String domFile = getProperties().get("mALERTPAGE_DOM_FILE");

    public MAlertPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    public boolean isMyAlertPageavailable() {

        boolean flag = false;
        if (isElementPresent("SelectCity")) {
            flag = true;
        } else {
            flag = false;
        }
        if (isElementPresent("Category")) {
            flag = true;
        } else {
            flag = false;
        }
        if (isElementPresent("SubCategory")) {
            flag = true;
        } else {
            flag = false;
        }
        if (isElementPresent("Locality")) {
            flag = true;
        } else {
            flag = false;
        }
        if (isElementPresent("Description")) {
            flag = true;
        } else {
            flag = false;
        }
        if (isElementPresent("Email")) {
            flag = true;
        } else {
            flag = false;
        }
        if (isElementPresent("Mobile")) {
            flag = true;
        } else {
            flag = false;
        }

        if (isElementPresent("Submit")) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public void clickMobiletextField()
    {
        Mapper.find(domFile,"Mobile").click();
    }

    public void clickSubmit()
    {
        Mapper.find(domFile,"Submit").click();
    }

    public void clickEditAlert()
    {
        List<WebElement> elms = Mapper.finds(domFile,"EditUnsubscribeContainer");
        List<String> elmsString = new ArrayList();

        for (int i=0;i<elms.size();i++)
        {
            elmsString.add(i,elms.get(i).getText());
        }

        for (int i=0;i<elmsString.size();i++)
        {
            if (elmsString.get(i).equals("Edit"))
            {
                elms.get(i).click();
                break;
            }
            else
            {
                logger.info("No edit link.");
                break;
            }
        }
    }

    public void selectCityAlertCreation(String city)
    {
        Mapper.find(domFile,"selectCityAlertCreation").click();
        Mapper.findAndReplace(domFile, "cityOption", new String[]{city}).click();
    }

    public void selectCat(String cat)
    {
        String[] catValues= {"60","269","247","20","40","93","179","123","246","194","1","161"};
        String[] correspondingCats = {"cars & bikes","mobiles & tablets","electronics & appliances","real estate","home & lifestyle","jobs","entertainment","services","pets & pet care","education & learning","community & events","matrimonial"};

        Select category = new Select(Mapper.find(domFile,"categoryAlertCreation"));

        for (int i=0;i<correspondingCats.length;i++)
        {
            if (correspondingCats[i].contains(cat.toLowerCase()))
            {
                category.selectByValue(catValues[i]);
            }
        }
    }

    public void selectSubCatAlertCreationCars()
    {
        Select subCat = new Select(Mapper.find(domFile,"subCatAlertCreation"));
        subCat.selectByValue("263"); //cars
    }

    public void selectSubCatAlertCreationJobs(String subcat)
    {
        String[] catValues ={"18233834","18230134","18236609","18231059","250","18232909","18234759","18231984"};
        String[] correspondingCats={"Freelancers","Full Time Jobs","Non-Profit NGOs","Part Time Jobs","Placement - Recruitment Agencies","Summer Trainees - Interns","Work From Home","Other Jobs"};

        //Mapper.find(domFile,"subCatAlertCreation").click();
        Select subCat = new Select(Mapper.find(domFile,"subCatAlertCreation"));
        for (int i=0;i<correspondingCats.length;i++)
        {
            if (correspondingCats[i].contains(subcat))
            {
                subCat.selectByValue(catValues[i]);
            }
        }
    }

    public void selectBrand()
    {
        Select brand = new Select(Mapper.find(domFile,"brandNameAlertCreation"));
        brand.selectByValue("Ford");
    }

    public void selectModel()
    {
        Select mod = new Select(Mapper.find(domFile,"modelAlertCreation"));
        mod.selectByValue("Ecosport");
    }

    public void selectLocalityAlertCreation()
    {
        WebElement elm = Mapper.find(domFile, "subCatAlertCreation");
        scrollToWebElement(elm);
        //Select loc = new Select(Mapper.find(domFile,"subCatAlertCreation"));
        //loc.selectByValue("Adugodi");
        //loc.selectByVisibleText("Adugodi");
        Mapper.find(domFile,"loct").click();
    }

    public void inputDescAlertCreation(String desc)
    {
        Mapper.find(domFile,"descAlertCreation").sendKeys(desc);
    }

    public void clickSubmitAlertCreation()
    {
        Mapper.find(domFile,"submitAlertCreation").click();
    }

    public void createAlertCars(String desc, String city, String category)
    {
        selectCityAlertCreation(city);
        selectCat(category);
        selectSubCatAlertCreationCars();
        selectBrand();
        selectModel();
        selectLocalityAlertCreation();
        inputDescAlertCreation(desc);
        clickSubmitAlertCreation();
    }

    public void selectEmployeeAlertCreation()
    {
        Mapper.waitForElementToBeVisible(domFile, "amAnEmployerAlertCreation");
        Mapper.find(domFile,"amAnEmployerAlertCreation").click();
    }

    public void selectJobSeekerAlertCreation()
    {
        Mapper.waitForElementToBeVisible(domFile,"amAJobSeekerAlertCreation");
        Mapper.find(domFile,"amAJobSeekerAlertCreation").click();
    }

    public void selectEmployerAlertCreation()
    {
        if (Mapper.find(domFile,"amAnEmployerAlertCreation")!=null)
        {
            Mapper.find(domFile,"amAnEmployerAlertCreation").click();
        }
        else
        {
            logger.info("Object not displayed/visible.");
        }
    }

    public void selectJobRoleAlertCreation(String ExactRoleString)
    {
        Mapper.waitForElementToBeVisible(domFile,"JobRoleAlertCreation");
        Select role = new Select(Mapper.find(domFile,"JobRoleAlertCreation"));
        role.selectByValue(ExactRoleString);
    }

    public void selectEducationAlertCreation(String ExactEduString)
    {
        Select role = new Select(Mapper.find(domFile,"educationAlertCreation"));
        role.selectByValue(ExactEduString);
    }

    public void selectExpAlertCreation(String ExactExpString)
    {
        Select role = new Select(Mapper.find(domFile,"expAlertCreation"));
        role.selectByValue(ExactExpString);
    }

    public void createAlertJobs(String city,String category,String subCategory,String exactJobRole,String edu,String exp)
    {
        selectCityAlertCreation(city);
        selectCat(category);
        selectSubCatAlertCreationJobs(subCategory);
        selectEmployerAlertCreation();
        selectJobRoleAlertCreation(exactJobRole);
        selectEducationAlertCreation(edu);
        selectExpAlertCreation(exp);
        selectLocalityAlertCreation();
        clickSubmitAlertCreation();
    }

}
