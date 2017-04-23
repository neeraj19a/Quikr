package com.quikr.website.cars.carsFooter;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Saurabh on 15/10/15.
 */
public class CarsFooterPage extends CarsPageBase {

    public CarsFooterPage(RemoteWebDriver driver) {
        super(driver);
    }
    private static final String domFile = getProperties().get("CARS_FOOTER_DOM_FILE");

    public boolean checkFooter()
    {
        return Mapper.waitForElementToBeVisible(domFile, "footer");
    }

    public void clickCars()
    {
        Mapper.waitForElementToBeVisible(domFile, "browseCars");
        Mapper.find(domFile, "browseCars").click();
    }

    public void clickBikes_Scooters()
    {
        Mapper.waitForElementToBeVisible(domFile, "browseBikes_Scooters");
        Mapper.find(domFile, "browseBikes_Scooters").click();
    }

    public void clickCommercial_Vehicles()
    {
        Mapper.waitForElementToBeVisible(domFile, "browseCommercial_Vehicles");
        Mapper.find(domFile, "browseCommercial_Vehicles").click();
    }

    public void clickBicycles()
    {
        Mapper.waitForElementToBeVisible(domFile, "browseBicycles");
        Mapper.find(domFile, "browseBicycles").click();
    }

    public void clickSpare_Parts()
    {
        Mapper.waitForElementToBeVisible(domFile, "browseSpare_Parts");
        Mapper.find(domFile, "browseSpare_Parts").click();
    }

    public void clickOther_Vehicles()
    {
        Mapper.waitForElementToBeVisible(domFile, "browseOther");
        Mapper.find(domFile, "browseOther").click();
    }

    public void clickAboutUs()
    {
        Mapper.waitForElementToBeVisible(domFile, "aboutUs");
        Mapper.find(domFile,"aboutUs").click();
    }

    public void clickContactUs()
    {
        Mapper.waitForElementToBeVisible(domFile, "contactUs");
        Mapper.find(domFile,"contactUs").click();
    }

    public void clickCareers()
    {
        Mapper.waitForElementToBeVisible(domFile, "careers");
        Mapper.find(domFile,"careers").click();
    }

    public void clickQuikrVideos()
    {
        Mapper.waitForElementToBeVisible(domFile, "quikrVideos");
        Mapper.find(domFile,"quikrVideos").click();
    }

    public void clickAdvertise()
    {
        Mapper.waitForElementToBeVisible(domFile, "advertise");
        Mapper.find(domFile,"advertise").click();
    }

    public void clickBlog()
    {
        Mapper.waitForElementToBeVisible(domFile, "blog");
        Mapper.find(domFile,"blog").click();
    }

    public void clickHelp()
    {
        Mapper.waitForElementToBeVisible(domFile, "help");
        Mapper.find(domFile,"help").click();
    }

    public void clickIOSApp()
    {
        Mapper.waitForElementToBeVisible(domFile, "iosApp");
        switchtoPopup(Mapper.find(domFile, "iosApp"));
    }

    public void clickAndroidApp()
    {
        Mapper.waitForElementToBeVisible(domFile, "androidApp");
        switchtoPopup(Mapper.find(domFile, "androidApp"));
    }

    public void clickWindowsApp()
    {
        Mapper.waitForElementToBeVisible(domFile, "windowsApp");
        switchtoPopup(Mapper.find(domFile, "windowsApp"));
    }

    public void clickListing_Policy()
    {
        Mapper.waitForElementToBeVisible(domFile, "listingPolicy");
        Mapper.find(domFile,"listingPolicy").click();
    }

    public void clickTermsOfUse()
    {
        Mapper.waitForElementToBeVisible(domFile, "terms");
        Mapper.find(domFile,"terms").click();
    }

    public void clickPrivacy_Policy()
    {
        Mapper.waitForElementToBeVisible(domFile, "privacyPolicy");
        Mapper.find(domFile,"privacyPolicy").click();
    }

    public void clickQuikrX_Policy()
    {
        Mapper.waitForElementToBeVisible(domFile, "quikrXPolicy");
        Mapper.find(domFile,"quikrXPolicy").click();
    }

    public void clickFooterCity(String cityToSelect)
    {
        Mapper.waitForElementToBeVisible(domFile, "footerCity", new String[] {cityToSelect});
        Mapper.findAndReplace(domFile, "footerCity", new String[]{cityToSelect}).click();
    }
}
