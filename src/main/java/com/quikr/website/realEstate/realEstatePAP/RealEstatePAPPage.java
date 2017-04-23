package com.quikr.website.realEstate.realEstatePAP;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by quikr on 3/11/15.
 */
public class RealEstatePAPPage extends PageBase{

    private static final String domFile = getProperties().get("REAL_ESTATE_PAP_DOM_FILE");

    public RealEstatePAPPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }
    public void citySelectionLayer(String cityname) {
        Mapper.waitForElementToBeVisible(domFile,"citylayer",1000);
        Mapper.find(domFile,"citylayer").click();
        Mapper.find(domFile,"citylayer").sendKeys(cityname);
        Mapper.find(domFile,"selectedcity").click();
    }
    public void clickPostAdbutton()
    {
        Mapper.waitForElementToBeClickable(domFile, "postadbutton");
        Mapper.find(domFile,"postadbutton").click();
    }
    public boolean verifyPAPURL()
    {
        if(getCurrentLocation().contains("postad"))
            return true;
        else
            return false;
    }

    public void selectPropertyCategory()
    {
        Mapper.waitForElementToBeVisible(domFile, "category", 30);
        Mapper.find(domFile,"category").click();
    }
    public void selectAdType()
    {
        Mapper.find(domFile,"adtypedropdown").click();
        Mapper.find(domFile, "adtype").click();
    }
    public void selectProjectname(String projectname)
    {
        Mapper.find(domFile,"projectname").click();
        Mapper.find(domFile,"projectname").sendKeys(projectname);
        Mapper.waitForElementToBeVisible(domFile, "suggestedproject");
        Mapper.find(domFile,"suggestedproject").click();
    }
    public void selectUnittype()
    {

        Mapper.waitForElementToBeClickable(domFile, "unit");
        Mapper.find(domFile,"unit").click();
        try{Thread.sleep(2000);}
        catch (InterruptedException e){}
        Mapper.waitForElementToBeClickable(domFile, "unittype");
        System.out.println(Mapper.finds(domFile, "unittype").size());
        Mapper.find(domFile,"unittype").click();

    }
    public void selectPropertytype()
    {
        Mapper.waitForElementToBeVisible(domFile, "propertytype");
        Mapper.find(domFile,"propertytype").click();
    }
    public void inputPrice(String price)
    {
        Mapper.find(domFile,"price").sendKeys(price);
    }
    public void submitFirstForm()
    {
        Mapper.find(domFile,"submitfirstform").click();
    }
    public void secondFormTransition()
    {
        Mapper.find(domFile,"nextpage").click();
    }
    public void thirdformTransition()
    {
        Mapper.find(domFile,"finalpage").click();
    }
    public void inputAdTitle(String title)
    {
        Mapper.find(domFile,"adtitle").click();
        Mapper.find(domFile,"adtitle").sendKeys(title);
    }

    public void inputAdDescription(String description)
    {
        Mapper.find(domFile,"addescription").click();
        Mapper.find(domFile,"addescription").sendKeys(description);
    }
    public void submitPAPForm()
    {
        Mapper.find(domFile,"submitpapform").click();
    }
    public void submitUserForm(String radio,String email,String phone)
    {
        Mapper.waitForElementToBeVisible(domFile, "email");
        if(radio.equalsIgnoreCase("individual"))
            Mapper.find(domFile,"individualradiobutton").click();
        else
            Mapper.find(domFile,"brokerradiobutton").click();

        Mapper.find(domFile,"email").click();
        Mapper.find(domFile,"email").sendKeys(email);
        Mapper.find(domFile,"phone").click();
        Mapper.find(domFile,"phone").sendKeys(phone);
        Mapper.find(domFile,"submituserform").click();
    }

}
