package com.quikr.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by akhil.singla
 */

public class InitiateDriver
{
    private RemoteWebDriver driver;
    private AppiumDriver appiumDriver;
    private BrowserMobProxy server = null;
    private Proxy proxy;

    /**
     * initiate driver depend upon BROWSER and URL value in config.properties file
     */
    public InitiateDriver()
    {
        try
        {
            String runOn = System.getProperty("runOn") == null ? getProperties().get("RUN_ON") : System.getProperty("runOn");
            String platform = System.getProperty("platform") == null ? getProperties().get("PLATFORM") : System.getProperty("platform");
            String url = System.getProperty("url") == null ? getProperties().get("URL") : System.getProperty("url");
            String mUrl =System.getProperty("mUrl") == null ? getProperties().get("mURL") : System.getProperty("mUrl");
            String browser = null;
            String appUrl=System.getProperty("androidAppUrl") == null ? getProperties().get("AndroidAppURL") : System.getProperty("androidAppUrl");
            String needProxy = System.getProperty("proxy") == null ? getProperties().get("PROXY") : System.getProperty("proxy");
            if(needProxy.equalsIgnoreCase("true"))
            {
                proxy = startProxyServer();
            }

            if(platform.equalsIgnoreCase("WINDOWS"))
            {
                if (runOn.equalsIgnoreCase("WEBSITE"))
                {
                    browser = System.getProperty("browser") == null ? getProperties().get("BROWSER") : System.getProperty("browser");
                    driver = new RemoteWebDriver(new URL(url), getBrowserCapabilities(browser, runOn, needProxy));
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                }
            }
            else if(platform.equalsIgnoreCase("ANDROID"))
            {
                if (runOn.equalsIgnoreCase("ANDROID_SITE"))
                {
                    browser = System.getProperty("mBrowser") == null ? getProperties().get("MOBILE_BROSWER") : System.getProperty("mBrowser");
                    appiumDriver = new AndroidDriver(new URL(mUrl), getBrowserCapabilities(browser, runOn, needProxy));
                    appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                }
                else if (runOn.equalsIgnoreCase("ANDROID_APP")) {
                    appiumDriver = new AndroidDriver(new URL(appUrl), getBrowserCapabilities(browser, runOn, needProxy));
                    appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                }

            }
            else if(platform.equalsIgnoreCase("IOS"))
            {
                if (runOn.equalsIgnoreCase("IOS_SITE"))
                {
                    browser = System.getProperty("mBrowser") == null ? getProperties().get("MOBILE_BROSWER") : System.getProperty("mBrowser");
                    appiumDriver = new IOSDriver(new URL(mUrl), getBrowserCapabilities(browser, runOn, needProxy));
                    appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                }
                else if (runOn.equalsIgnoreCase("IOS_APP")) {
                    appiumDriver = new IOSDriver(new URL(getProperties().get("iOSAppURL")), getBrowserCapabilities(browser, runOn, needProxy));
                    appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * get driver based upon BROWSER value in config.properties file, at present supporting IE, Chrome, Firefox
     * @return RemoteWebDriver
     */
    public RemoteWebDriver getDriver()
    {
        if (driver == null)
            throw new RuntimeException("We have not instantiated the driver.");
        return driver;
    }

    /**
     * return env on which automation is running
     * @return
     */
    public static synchronized String getEnv()
    {
        return System.getProperty("env") == null ? getProperties().get("ENV") : System.getProperty("env");
    }


    /**
     * get appium driver
     * @return
     */
    public AppiumDriver getAppiumDriver()
    {
        if (appiumDriver == null)
            throw new RuntimeException("We have not instantiated the driver.");
        return appiumDriver;
    }

    /**
     * get desired capabilities of browser
     * @param browser
     * @param runOn
     * @param needProxy
     * @return DesiredCapabilities
     */
    private DesiredCapabilities getBrowserCapabilities(String browser, String runOn, String needProxy)
    {
        DesiredCapabilities capabilities = null;
        String appName = System.getProperty("appName") == null ? getProperties().get("AppName") : System.getProperty("appName");
        String appPath =System.getProperty("appPath") == null ? getProperties().get("AppPath") : System.getProperty("appPath");

        try
        {
            if (runOn.equalsIgnoreCase("WEBSITE") && browser.equalsIgnoreCase("Firefox"))
            {
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName("firefox");
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setPlatform(Platform.ANY);
            }
            else if (runOn.equalsIgnoreCase("WEBSITE") && browser.equalsIgnoreCase("IE"))
            {
                //InternetExplorerDriverManager.getInstance().setup();
                //System.setProperty("webdriver.ie.driver", "../../../../resources/IEDriverServer.exe");
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setBrowserName("internet explorer");
                capabilities.setPlatform(Platform.WINDOWS);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setCapability("ie.ensureCleanSession", true);
            }
            else if (runOn.equalsIgnoreCase("WEBSITE") && browser.equalsIgnoreCase("Chrome"))
            {
                //ChromeDriverManager.getInstance().setup();
                if(System.getProperty("os.name").equalsIgnoreCase("Linux"))
                {
                    System.setProperty("webdriver.chrome.driver", "/home/" + System.getProperty("user.name") + "/Documents/chromedriver");
                }
                else
                {
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\" + System.getProperty("user.name") + "/Documents/chromedriver");
                }
                capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName("chrome");
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setPlatform(Platform.ANY);
            }
            else if (runOn.equalsIgnoreCase("ANDROID_SITE"))
            {
                capabilities = DesiredCapabilities.android();
                //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
            }
            else if (runOn.equalsIgnoreCase("IOS_SITE"))
            {
                capabilities = DesiredCapabilities.iphone();
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "");
            }
            else if (runOn.equalsIgnoreCase("ANDROID_APP"))
            {

                File appDir = new File(appPath);
                File app = new File(appDir, appName);
                capabilities=new DesiredCapabilities();
                capabilities.setCapability("deviceName", "");
                capabilities.setCapability("platformName", "Android");
              //  capabilities.setCapability("appWaitActivity", "com.quikr.homepage.helper.HomePageActivity_new");
                capabilities.setCapability("app", app.getAbsolutePath());
                capabilities.setCapability("appPackage", "com.quikr");
                capabilities.setCapability("appActivity", "com.quikr.old.SplashActivity");
            }
            else if (runOn.equalsIgnoreCase("IOS_APP"))
            {
                File appDir = new File("/Users/apple/automation/ios/app");
              //  File appDir = new File("/Users/Quikr/Downloads");
                File app = new File(appDir, "Quikr.app");
                capabilities=new DesiredCapabilities();

              //  capabilities.setCapability("autoAcceptAlerts", true);
                capabilities.setCapability("platformVersion", "9.1");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("deviceName", "iPhone");
                capabilities.setCapability("udid", "517ea6a9384dedd07fe34f3c9f6b7d70e5c57ff6");
                capabilities.setCapability("app", app.getPath());


            }
            else
            {
                // default is firefox
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName("firefox");
                capabilities.setPlatform(Platform.ANY);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(needProxy.equalsIgnoreCase("true"))
        {
            capabilities.setCapability(CapabilityType.PROXY, proxy);
        }

        return capabilities;
    }

    /**
     * return browser mob proxy server
     * @return
     */
    public BrowserMobProxy getServer()
    {
        return server;
    }

    /**
     * start proxy server
     * @return
     */
    private Proxy  startProxyServer()
    {
        if(server == null || !server.isStarted())
        {
            server = new BrowserMobProxyServer();
            server.start();
            proxy = ClientUtil.createSeleniumProxy(server);
        }

        return proxy;
    }
}
