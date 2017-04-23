package com.quikr.msite.mHorizontal.mLogin;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 4/11/15.
 */
public class MLoginpage extends MPageBase

{
    public MLoginpage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("mLOGINPAGE_DOM_FILE");

    public boolean checkLoginPage()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "EmailTextField")==true)
        {
            retVal=true;
        }
        else
        {
            logger.info("<EmailTextField> is not visible. Please check!");
            return false;
        }
        return retVal;
    }

    public void enterUserId(String email)
    {
        if(Mapper.waitForElementToBeClickable(domFile,"EmailTextField")==true) {
            Mapper.find(domFile, "EmailTextField");
            Mapper.find(domFile, "EmailTextField").sendKeys(email);
        }
//        if (Mapper.waitForElementToBeVisible(domFile,"emailTextField")==true)
//        {
//        Mapper.find(domFile,"emailTextField").sendKeys(email);
//        }
//        else
//        {
//            logger.info("EMail text field was not visible.");
//        }
    }

    public void enterpassword(String password)
    {
        Mapper.find(domFile,"PasswordTextField").click();
        Mapper.find(domFile,"PasswordTextField").sendKeys(password);
    }

    public boolean clickSign()
    {
        boolean flag=false;
       if(Mapper.waitForElementToBeClickable(domFile, "LoginButton")==true) {
           Mapper.find(domFile, "LoginButton").click();
           if(waitForPageToLoad("MyQuikr",11)==true){
               logger.info("Login Successfull");
               flag= true;
           }
           else {
               logger.info("Login Not Successfull");
               flag=false;
           }
       }
        return flag;

    }

    public void signIn(String email, String password) {
        enterUserId(email);
        enterpassword(password);
        try {
                boolean flag=clickSign();
            if (!flag) {
                logger.info("Trying to Again Login as Login was not successful");
                navigateTo().refresh();
                enterUserId(email);
                enterpassword(password);
                clickSign();
            }
        }
        catch (Exception e){
        }
    }


}
