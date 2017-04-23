package com.quikr.msite.mHorizontal.mHeader;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 9/11/15.
 */
public class MHeaderPage extends MPageBase
{
    private static final String domFile = getProperties().get("mHEADER_DOM_FILE");
    public MHeaderPage(AppiumDriver driver){
        super(domFile, driver);
    }

    public void peformsearch(String keyword){
        Mapper.waitForElementToBeClickable(domFile,"inputSearchTerm");
        Mapper.find(domFile,"inputSearchTerm").click();
        Mapper.find(domFile,"inputSearchTerm").clear();
        Mapper.find(domFile,"inputSearchTerm").sendKeys(keyword);
        Mapper.waitForElementToBeClickable(domFile, "SearchButton");
        Mapper.find(domFile,"SearchButton").click();
    }

    public void goToQuikrHome(){
        Mapper.waitForElementToBeClickable(domFile, "menuIcon");
        Mapper.find(domFile,"menuIcon").click();
        Mapper.waitForElementToBeClickable(domFile, "menuIconHomePage");
        Mapper.find(domFile,"menuIconHomePage").click();
    }

    public void enterEmailLogin(String email)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"emailTextFieldLogin")==true)
        {
            Mapper.find(domFile,"emailTextFieldLogin").sendKeys(email);
        }
        else
        {
            logger.info("Email text field not visible. Please check!");
        }
    }

    public void enterPasswordLogin(String pass)
    {
        Mapper.find(domFile,"passwordTextFieldLogin").sendKeys(pass);
    }

    public void clickSubmitLogin()
    {
        Mapper.find(domFile,"submitButtonLogin").click();
    }

    public void login(String email, String pass)
    {
        enterEmailLogin(email);
        enterPasswordLogin(pass);
        clickSubmitLogin();
    }

}
