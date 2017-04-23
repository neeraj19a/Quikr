package com.quikr.msite.mHorizontal.mSearch;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 3/11/15.
 */
public class MSearchPage extends MPageBase {

    public MSearchPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("mSEARCHPAGE_DOM_FILE");

    public void searchKeyword(String term){
        Mapper.find(domFile,"inputSearchTerm").click();
        Mapper.find(domFile,"inputSearchTerm").sendKeys(term);
        Mapper.find(domFile,"SearchGo").click();

    }

}
