package com.quikr.msite.mCars.mCarsVAP;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 17/11/15.
 */
public class MCarsVap extends MPageBase {

    public MCarsVap(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("mCARS_VAP_DOM_FILE");

    public boolean verifyVapContents()
    {
        boolean finalVal = false;
        if (Mapper.find(domFile,"postedOnLabel").getText().equals("Posted On"))
        {
            finalVal=true;
        }
        else
        {
            logger.info("Posted On label is not correct.");
        }

        if (Mapper.find(domFile,"locationLabel").getText().equals("Location"))
        {
            finalVal=true;
        }
        else
        {
            logger.info("location label is not correct.");
        }
        return finalVal;
    }

    public boolean validateFilteredResult()
    {
        boolean finalVal=false;
        String extractedOwnerLabel = Mapper.find(domFile,"ownerLabel").getText();
        if (extractedOwnerLabel.equals("Owner"))
        {
            finalVal=true;
        }
        else
        {
            logger.info("Owner label not as expected.");
            return false;
        }

        String extractedUsedLabel = Mapper.find(domFile,"usedLabel").getText();
        if (extractedUsedLabel.equals("Used"))
        {
            finalVal=true;
        }
        else
        {
            logger.info("used label not as expected.");
            return false;
        }

        String extractedProdLabel = Mapper.find(domFile,"desktopLabel").getText();
        if (extractedProdLabel.equals("Desktop"))
        {
            finalVal=true;
        }
        else
        {
            logger.info("prod label not as expected.");
            return false;
        }
        return finalVal;
    }
}
