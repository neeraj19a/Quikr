package com.quikr.app.android;

import com.quikr.utils.InitiateDriver;
import com.quikr.utils.XMLReader;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

/**
 * Created by quikr on 14/12/15.
 */
public class AppStaticsTestBase {
    protected AppiumDriver driver;
    protected Log logger;

    public AppStaticsTestBase()
    {
        // set homepath for logger to use
        if(System.getProperty("os.name").equalsIgnoreCase("Linux"))
        {
            System.setProperty("homePath","/home/quikr");
        }
        else
        {
            System.setProperty("homePath","/Users/apple");

        }
    }

    @BeforeMethod(groups = "horizontal", alwaysRun = true)
    public void setUp()
    {


        try
        {
            logger = LogFactory.getLog(this.getClass().getName());
            driver= new InitiateDriver().getAppiumDriver();

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        //  headerPage.checkCity();


    }

    @AfterMethod(groups = "horizontal", alwaysRun = true)
    public void teardown()
    {
        try {
            driver.closeApp();
            driver.quit();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * get login credential
     * @param name
     * @return login credentials
     */
    public HashMap<String, String> getLoginCredential(String name)
    {
        return XMLReader.getLogins(name);
    }
}
