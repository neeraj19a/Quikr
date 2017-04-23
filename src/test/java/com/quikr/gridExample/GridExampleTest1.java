package com.quikr.gridExample;

/**
 * Created by NEERAJ on 4/23/2017.
 */

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridExampleTest1 {

    public RemoteWebDriver driver;
    public static String appURL = "http://www.facebook.com";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.manage().window().maximize();
    }

    @Test
    public void testGooglePageTitleInIEBrowser() {
        System.out.println("*** One ***");
        driver.navigate().to(appURL);
        String strPageTitle = driver.getTitle();
        System.out.println("***  One Verifying page title ***");
        Assert.assertTrue(strPageTitle.equalsIgnoreCase("Facebook"), "Page title doesn't match");
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}