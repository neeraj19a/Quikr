package com.quikr.app.android;

import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import com.quikr.utils.InitiateDriver;
import com.quikr.utils.XMLReader;
import io.appium.java_client.AppiumDriver;
import net.lightbody.bmp.BrowserMobProxy;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by akhil.singla
 */

public class AppAndroidTestBase {

    protected AppiumDriver driver;
    protected Log logger;
    protected String classname = this.getClass().getSimpleName();
    protected String Package = this.getClass().getPackage().getName();
    DateFormat dateFormatday = new SimpleDateFormat("dd_MMM_yyyy");
    DateFormat dateFormathour = new SimpleDateFormat("HH");
    //DateFormat dayFormattime = new SimpleDateFormat("hh-mm");
    DateFormat dayFormatseconds = new SimpleDateFormat("hh-mm-ss");
    protected String destDir;
    protected BrowserMobProxy proxyServer;
    public boolean flag=false;

    public AppAndroidTestBase()
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
    @BeforeClass(alwaysRun=true)
    public String mobileNumber()
    {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        String phNo = 99393+randomNumbers;
        return phNo;

    }
    @BeforeMethod(groups = "horizontal", alwaysRun = true)
    public void setUp()
    {

        try
        {
            logger = LogFactory.getLog(this.getClass().getName());
            InitiateDriver initiateDriver = new InitiateDriver();
            driver = initiateDriver.getAppiumDriver();
            // if (!classname.equalsIgnoreCase("HorizontalHomeTests")&&(flag==false))
            if (!classname.equalsIgnoreCase("HorizontalHomeTests"))
            {
                HeaderPage headerPage = new HeaderPage(driver);
                Hompage hompage = new Hompage(driver);
                hompage.skipBannerMsg();
                hompage.skipLoginOnLaunch();
                hompage.skipChatPopUp();
                if (Package.contains("postAdV2"))
                {
                    hompage.selectCity();
                    headerPage.searchCity("Mumbai");
                    hompage.selectelementWithoutScroll("Mumbai", QuikrAppEnums.Hompage_SelectCity);
                    headerPage.getNameOfCitySelected();

                }
                else
                    headerPage.checkCity();
            }

            proxyServer = initiateDriver.getServer();

            if (proxyServer != null) {
                proxyServer.newHar("quikrApp");
            }

        }

        catch (Exception e)
        {
            System.out.println("In catch Block Before Method");
            HeaderPage headerPage = new HeaderPage(driver);
            Hompage hompage = new Hompage(driver);
            if (hompage.validateImageTutorialLinkIsPresent())
            {
                if (!classname.equalsIgnoreCase("HorizontalHomeTests"))
                {
                    hompage.skipBannerMsg();
                    hompage.skipLoginOnLaunch();
                    hompage.skipChatPopUp();
                    if (Package.contains("postAdV2"))
                    {
                        hompage.selectCity();
                        headerPage.searchCity("Mumbai");
                        hompage.selectelementWithoutScroll("Mumbai", QuikrAppEnums.Hompage_SelectCity);
                        headerPage.getNameOfCitySelected();

                    }
                    else
                        headerPage.checkCity();
                }
            }
            e.printStackTrace();
        }

    }

    @Attachment
    public byte[]screenShot()
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod(groups = "horizontal", alwaysRun = true)
    public void tearDown(ITestResult result)
    {
        try
        {

//            if (result.getStatus() == ITestResult.SUCCESS&&Package.contains("postAdV2")) {
//                flag=true;
//                System.out.println("control in aftermethod"+"VAlue of flag"+flag);
//                Hompage hompage=new Hompage(driver);
//                hompage.navigateBack();
//                hompage.closeGoogleAds();
//            }
//            if ((result.getStatus() == ITestResult.FAILURE)||(result.getStatus() == ITestResult.SKIP))
//                {
//                    screenShot();
//                   // flag=false;
//                   // System.out.println("testFAiled value of flag" + flag);
//  driver.closeApp();
            //driver.quit();
//
//                }

            if ((result.getStatus() == ITestResult.FAILURE)||(result.getStatus() == ITestResult.SKIP))
            {
                // screenshooting
                screenShot();
            }

               driver.closeApp();
            driver.quit();
            //driver.resetApp();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (proxyServer != null) {
            proxyServer.stop();
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


    public void resetApp(){
        driver.resetApp();
    }

    public void executeCommand(String[] command)
    {
        Process p ;
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> command(final String cmdline,
                                            final String directory) {
        try {
            Process process =
                    new ProcessBuilder(new String[] {"bash", "-c", cmdline})
                            .redirectErrorStream(true)
                            .directory(new File(directory))
                            .start();

            ArrayList<String> output = new ArrayList<String>();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = null;
            while ( (line = br.readLine()) != null )
                output.add(line);

            if (0 != process.waitFor())
                return null;

            return output;

        } catch (Exception e) {
            return null;
        }
    }

}