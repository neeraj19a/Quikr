package com.quikr.app.android.appStatics;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 14/12/15.
 */
public class AppStatics extends AppAndroidPageBase {
    public AppStatics(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    // const string
    private static final String domFile = getProperties().get("Android_APP_STATICS_DOM_FILE");

    /**
     *start time for app launch
     * @return
     */
    public long startCounterAppLaunch()
    {
        long startTime = System.currentTimeMillis();
        return startTime;
    }

    /**
     *end time for app launch
     * @return
     */
    public long stopCounter()
    {
        long stopCounter = System.currentTimeMillis() ;
        return stopCounter;
    }

    /**
     *end time for app launch tome calculation
     * @return
     */
    public long stopCounterInitiatorAppLaunch()
    {  long StopTime=0;
        if (isElementPresent("skipBanner"))
        {
            StopTime = System.currentTimeMillis();
            return StopTime;
        }
        return StopTime;
    }


    /**
     *start time for text load from haome paoular ads
     * @return
     */
    public long startCounterTextLoad()
    {
        Mapper.waitForElementToBeVisible(domFile,"homeAdTitle");
        Mapper.find(domFile,"homeAdTitle").click();
        long start=System.currentTimeMillis();
        return  start;
    }
    /**end time for text load from home popular ads to snb
     *
     */
    public long stopCounterInitiatorTextLoad()
    {  long StopTime=0;
        if (isElementPresent("snbTitle"))
        {
            StopTime = System.currentTimeMillis();
            return StopTime;
        }
        return StopTime;
    }

    /**
     * open ads
     */
    public void openAdsFronHomePage()
    {
        Mapper.waitForElementToBeVisible(domFile, "homeAdTitle");
        Mapper.find(domFile,"homeAdTitle").click();
    }
    /**
     *skip layout
     */
    public void hideOverlayLayout()
    {
        if (isElementPresent("snbOverlay"))
            navigateBack();
    }
    /**
     *start time for text load for cars on find cars
     */
    public long startCounterTextLoadCarsSNB()
    {
        Mapper.waitForElementToBeVisible(domFile,"findCars");
        Mapper.find(domFile,"findCars").click();
        long start=System.currentTimeMillis();
        return  start;
    }
    /**
     *end time time for text to load from find cars to cars snb
     */
    public long stopCounterInitiatorTextLoadCarsSnb()
    {  long StopTime=0;
        if (isElementPresent("carsSnbTitle"))
        {
            StopTime = System.currentTimeMillis();
            return StopTime;
        }
        return StopTime;
    }
    /**
     *start time for real estate when clicked on search from popular searchs
     */
    public long startCounterTextLoadRealEstateSNB()
    {
        Mapper.waitForElementToBeVisible(domFile,"realestateAutoSuggest");
        Mapper.find(domFile,"realestateAutoSuggest").click();
        long start=System.currentTimeMillis();
        System.out.println(start);
        return  start;
    }
    /**
     * end time for real estate text to load on snb
     */
    public long stopCounterInitiatorTextLoadRealEstateSnb()
    {  long StopTime=0;
        if (isElementPresent("realEstateSnbTitle"))
        {
            StopTime = System.currentTimeMillis();
            return StopTime;
        }
        return StopTime;
    }
    /**
     *
     * start time for text to load from clicking on subCat on jobs home Page
     */
    public long startCounterTextLoadJobsSNB()
    {
        Mapper.waitForElementToBeVisible(domFile, "jobsworkFromHome");
        Mapper.find(domFile,"jobsworkFromHome").click();
        long start=System.currentTimeMillis();
        return  start;
    }
    /**
     * end time for text to load on Jobs Snb
     */
    public long stopCounterInitiatorTextLoadJobsSnb()
    {  long StopTime=0;
        if (isElementPresent("jobsSnbTitle"))
        {
            StopTime = System.currentTimeMillis();
            return StopTime;
        }
        return StopTime;
    }
    /**
     *
     * start time for text to load from clicking on subCat on QuikrX home Page
     */
    public long startCounterTextLoadQuikrXSNB()
    {
        Mapper.waitForElementToBeVisible(domFile,"EXCHANGE");
        Mapper.find(domFile,"EXCHANGE").click();
        long start=System.currentTimeMillis();
        return  start;
    }
    /**
     * end time for text to load on Quikrx Snb
     */
    public long stopCounterInitiatorTextLoadQuikrXSnb()
    {  long StopTime=0;
        if (isElementPresent("snbTitle"))
        {
            StopTime = System.currentTimeMillis();
            return StopTime;
        }
        return StopTime;
    }
    /**
     *
     * start time for text to load from clicking on subCat on QuikrX home Page
     */
    public long startCounterTextLoadServicesSNB()
    {
        Mapper.waitForElementToBeVisible(domFile,"servicesSubCAt");
        Mapper.find(domFile,"servicesSubCAt").click();
        long start=System.currentTimeMillis();
        return  start;
    }
    /**
     * end time for text to load on Quikrx Snb
     */
    public long stopCounterInitiatorTextLoadServicesSnb()
    {  long StopTime=0;
        if (isElementPresent("snbTitle"))
        {
            StopTime = System.currentTimeMillis();
            return StopTime;
        }
        return StopTime;
    }
    /**
     * click on services auto suggest
     */
    public void clickOnServicesAutoSuggest()
    {
        Mapper.waitForElementToBeVisible(domFile,"servicesSubCAt");
        Mapper.find(domFile,"servicesSubCAt").click();
    }

}
