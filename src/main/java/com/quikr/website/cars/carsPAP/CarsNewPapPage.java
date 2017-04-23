package com.quikr.website.cars.carsPAP;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 19/10/16.
 */
public class CarsNewPapPage extends CarsPageBase {

    public CarsNewPapPage(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String domFile = getProperties().get("CARS_NEW_PAP_PAGE_DOM_FILE");

}
