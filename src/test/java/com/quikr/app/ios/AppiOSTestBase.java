package com.quikr.app.ios;

/**
 * Created by quikr on 30/9/15.
 */

import com.quikr.app.ios.home.HomePage;
import com.quikr.utils.InitiateDriver;
import com.quikr.utils.XMLReader;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.util.HashMap;

/**
 * Created by akhil.singla
 */

public class AppiOSTestBase {

    protected AppiumDriver driver;
    protected Log logger;

    public AppiOSTestBase()
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

    @BeforeMethod(groups = "horizontal")
    public void setUp()
    {
        try
        {
            logger = LogFactory.getLog(this.getClass().getName());
            driver= new InitiateDriver().getAppiumDriver();

            HomePage homePage=new HomePage(driver);
            homePage.selectCrossButtonOnAllowLocationPopup();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Attachment
    public byte[]screenShot()
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }


    @AfterMethod(groups = "horizontal", alwaysRun = true)
    public void teardown(ITestResult result)
    {
        try {
// taking screenshot on Failures
            if (result.getStatus() == ITestResult.FAILURE) {
                screenShot();
            }
        }
        catch (Exception e){}
        try
        {

            driver.closeApp();
            driver.quit();
        }
        catch (Exception e)
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
