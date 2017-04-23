package com.quikr.msite.mElectronics.mElectronicsHome;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 6/11/15.
 */
public class MElectronicsHomePage extends MPageBase {

    private static final String domFile = getProperties().get("mElectronicsHome_DOM_FILE");
    public MElectronicsHomePage(AppiumDriver driver){super(domFile,driver);}


    public void clickELectronicsandAppliacesCategory(String categoryname){
        Mapper.findAndReplace(domFile,"ElectronicsCategory",new String[]{categoryname}).click();
    }

    public void clickLaptopAndComputers()
    {
        Mapper.find(domFile,"LaptopAndComps").click();
    }

}
