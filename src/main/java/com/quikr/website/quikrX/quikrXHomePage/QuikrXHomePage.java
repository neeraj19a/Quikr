package com.quikr.website.quikrX.quikrXHomePage;

import com.quikr.website.PageBase;
import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Cliford D'souza (cdsouza@quikr.com) on 28/9/15.
 */
public class QuikrXHomePage extends PageBase {
    public QuikrXHomePage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("QUIKRXHOMEPAGE_DOM_FILE");


    /**
     * Select preowned/Quikr certified phones option
     */
    public void selectPreOwnedPhones()
    {
        Mapper.find(domFile, "preownedPhoneOption").click();
//      Mapper.waitForElementToBeVisible(domFile, "preownedPhoneName");
    }

    /**
     * Select Exchange phone option
     */
    public void selectExchangePhones()
    {
        Mapper.find(domFile, "exchangePhoneOption").click();
//        Mapper.waitForElementToBeVisible(domFile, "exchangePhoneName");
    }

    /**
     * validation of preowned/quikr certified phone details
     */
    public boolean validatePreOwnedPhoneDetails()
    {
        if(!(Mapper.find(domFile, "preownedPhoneName").isDisplayed()) || !(Mapper.find(domFile, "preownedPhoneImage").isDisplayed()) || !(Mapper.find(domFile, "preownedPhonePrice").isDisplayed()) || !(Mapper.find(domFile, "preownedPhoneDesc").isDisplayed()))
        {
            return false;
        }
        return true;
    }

    /**
     * validation of exchange phone details
     */
    public boolean validateExchangePhoneDetails()
    {
        if(!(Mapper.find(domFile, "exchangePhoneName").isDisplayed()) || !(Mapper.find(domFile, "exchangePhoneImage").isDisplayed()) || !(Mapper.find(domFile, "exchangePhonePrice").isDisplayed()) || !(Mapper.find(domFile, "exchangePhoneDesc").isDisplayed()))
        {
            return false;
        }
        return true;
    }

    /**
     * Open preowned phone details page
     */
    public void openPreOwnedPhoneDetails()
    {
        Mapper.find(domFile,"preownedPhoneName").click();
        Mapper.waitForElementToBeVisible(domFile,"viewCount");
    }

    /**
     * Open Exchange phone details page
     */
    public void openExchangePhoneDetails()
    {
        Mapper.find(domFile,"exchangePhoneName").click();
        Mapper.waitForElementToBeVisible(domFile, "viewCount");
    }

    /**
     * apply brandname filter
     */
    public void applyBrandNameFilter()
    {
        Mapper.waitForElementToBeClickable(domFile,"brandNameFilter");

        Mapper.find(domFile,"brandNameFilter").click();
    }

    /**
     * validation of brand name filter
     */
    public boolean validateBrnadNameFilter()
    {
        if(!(Mapper.find(domFile, "preownedPhoneName").getText().contains(Mapper.find(domFile, "brandName").getText())))
        {
            return false;
        }
        return true;
    }

    /**
     * apply price range filter
     */
    public void applyPriceRangeFilter()
    {
        Mapper.find(domFile,"priceRangeFilter").click();
    }

    /**
     * Validate Price Range Filter
     * @return
     */
    public boolean validatePriceRangeFilter()
    {
        if(!(Integer.parseInt(Mapper.find(domFile,"preownedPhonePrice").getText().replaceAll("\\D", ""))>= 10001 && Integer.parseInt(Mapper.find(domFile, "preownedPhonePrice").getText().replaceAll("\\D", "")) <= 20000))
        {
            return false;
        }
        return true;
    }

    /**
     * apply operating system filter
     */
    public void applyOperatingSystemFilter()
    {
        Mapper.find(domFile, "operatingSystemFilter").click();
    }

    /**
     * Validate Operating System filter
     */
    public boolean validateOperatingSystemFilter()
    {
        //Mapper.waitForElementToBeInvisible(domFile, "preownedPhoneName");
        if (!(getPageSource().contains(Mapper.find(domFile, "operatingSystem").getText())))
        {
            return false;
        }
        return true;
    }

    /**
     * Apply No. of Sims Filter
     */
    public void applyNoOfSimsFilter()
    {
        Mapper.find(domFile, "noOfSimFilter").click();
    }

    /**
     * Validate No. of sims filter
     */
    public boolean validateNoOfSimsFilter()
    {
        if (!(getPageSource().contains(Mapper.find(domFile, "noOfSims").getText())))
        {
            return false;
        }
        return true;
    }

    /**
     * Apply Screen size filter
     */
    public void applyScreenSizeFilter()
    {
        Mapper.find(domFile, "screenSizeFilter").click();
    }

    /**
     * Validate Screen Size filter
     */
    public boolean validateScreenSizeFilter()
    {
        if (!(getPageSource().contains(Mapper.find(domFile, "screenSize").getText())))
        {
            return false;
        }
        return true;
    }

    /**
     * Apply Primary Camera filter
     */
    public void applyPrimaryCameraFilter()
    {
        Mapper.find(domFile, "primaryCameraFilter").click();
    }

    /**
     * Validate Primary Camera filter
     */
    public boolean validatePrimaryCameraFilter()
    {
        if (!(getPageSource().contains(Mapper.find(domFile, "primaryCamera").getText())))
        {
            return false;
        }
        return true;
    }

    /**
     * Apply Ram filter
     */
    public void applyRamFilter()
    {
        Mapper.find(domFile, "ramFilter").click();
    }

    /**
     * Validate Ram filter
     */
    public boolean validateRamFilter()
    {
        if (!(getPageSource().contains(Mapper.find(domFile, "ram").getText())))
        {
            return false;
        }
        return true;
    }

    /**
     * Apply Processor filter
     */
    public void applyProcessorFilter()
    {
        Mapper.find(domFile, "processorFilter").click();
    }

    /**
     * Validate Processor filter
     */
    public boolean validateProcessorFilter()
    {
        if (!(getPageSource().contains(Mapper.find(domFile, "processorFilter").getText())))
        {
            return false;
        }
        return true;
    }

    /**
     * Apply brand name filter by entering brand name in search box
     */
    public void applyBrandNameFilterWithSearch(String brandName)
    {
        Mapper.find(domFile, "brandNameSearchBox").click();
        Mapper.find(domFile, "brandNameSearchBox").clear();
        Mapper.find(domFile, "brandNameSearchBox").sendKeys(brandName);
        applyBrandNameFilter();
    }

    /**
     * Apply operating system filter by entering operating system in search box
     */
    public void applyOperatingSystemFilterWithSearch(String operatingSystem)
    {
        Mapper.find(domFile, "operatingSystemSearchBox").click();
        Mapper.find(domFile, "operatingSystemSearchBox").clear();
        Mapper.find(domFile, "operatingSystemSearchBox").sendKeys(operatingSystem);
        applyOperatingSystemFilter();
    }

    /**
     * Apply no. of sims filter by Sims value in search box
     */
    public void applyNoOfSimsFilterWithSearch(String noOfSims)
    {
        Mapper.find(domFile, "noOfSimsSearchBox").click();
        Mapper.find(domFile, "noOfSimsSearchBox").clear();
        Mapper.find(domFile, "noOfSimsSearchBox").sendKeys(noOfSims);
        applyNoOfSimsFilter();
    }

    /**
     * Apply screen size filter by screen size in search box
     */
    public void applyScreenSizeFilterWithSearch(String screenSize)
    {
        Mapper.find(domFile, "screenSizeSearchBox").click();
        Mapper.find(domFile, "screenSizeSearchBox").clear();
        Mapper.find(domFile, "screenSizeSearchBox").sendKeys(screenSize);
        applyScreenSizeFilter();
    }

    /**
     * Apply primary camera filter by primary camera in search box
     */
    public void applyPrimaryCameraFilterWithSearch(String primaryCamera)
    {
        Mapper.find(domFile, "primaryCameraSearchBox").click();
        Mapper.find(domFile, "primaryCameraSearchBox").clear();
        Mapper.find(domFile, "primaryCameraSearchBox").sendKeys(primaryCamera);
        applyPrimaryCameraFilter();
    }

    /**
     * Apply Ram filter by entering Ram value in search box
     */
    public void applyRamFilterWithSearch(String ramValue)
    {
        Mapper.find(domFile, "ramSearchBox").click();
        Mapper.find(domFile, "ramSearchBox").clear();
        Mapper.find(domFile, "ramSearchBox").sendKeys(ramValue);
        applyRamFilter();
    }

    /**
     * Apply Processor filter by entering Processor value in search box
     */
    public void applyProcessorFilterWithSearch(String processorValue)
    {
        Mapper.find(domFile, "processorSearchBox").click();
        Mapper.find(domFile, "processorSearchBox").clear();
        Mapper.find(domFile, "processorSearchBox").sendKeys(processorValue);
        applyProcessorFilter();
    }

    /**
     * Function for sorting quikrX page
     */
    public void sortQuikrXItems(String sortOrder)
    {
        Select sortDropDown = new Select(Mapper.find(domFile, "sortOption"));
        sortDropDown.selectByVisibleText(sortOrder);
    }

    /**
     * Function for validation of exchange lower price phone with higher price
     */
    public boolean validateExchangeHigherPriceWithLowerPrice()
    {
        if (!(Mapper.find(domFile, "cartValueTotal").getText().contains("Rs. 1")) || !(Mapper.find(domFile, "grandTotal").getText().contains("Rs.1")))
            return false;
        return true;
    }

    /**
     * Function for validation of sorting by lower price
     */
    public boolean validatePreOwnedPhonesSortByLowerPrice()
    {
        String firstPhonePrice = Mapper.find(domFile, "preOwnedFirstPhonePrice").getText().replaceAll("₹", "").trim();
        String secondPhonePrice = Mapper.find(domFile, "preOwnedSecondPhonePrice").getText().replaceAll("₹", "").trim();
        if ((int)Math.round(Double.parseDouble(firstPhonePrice)) > (int)Math.round(Double.parseDouble(secondPhonePrice)))
            return false;
        return true;
    }

    /**
     * Function for validation of sorting by higher price
     */
    public boolean validatePreOwnedPhonesSortByHigherPrice()
    {
        String firstPhonePrice = Mapper.find(domFile, "preOwnedFirstPhonePrice").getText().replaceAll("₹", "").replaceAll(",","").trim();
        String secondPhonePrice = Mapper.find(domFile, "preOwnedSecondPhonePrice").getText().replaceAll("₹", "").replaceAll(",","").trim();
        if ((int)Math.round(Double.parseDouble(firstPhonePrice)) < (int)Math.round(Double.parseDouble(secondPhonePrice)))
            return false;
        return true;
    }

    /**
     * Function for validation of sorting by lower price
     */
    public boolean validateExchangePhonesSortByLowerPrice()
    {

        String firstPhonePrice = Mapper.find(domFile, "exchangeFirstPhonePrice").getText().replaceAll("₹", "").trim();
        String secondPhonePrice = Mapper.find(domFile, "exchangeSecondPhonePrice").getText().replaceAll("₹", "").trim();
        if ((int)Math.round(Double.parseDouble(firstPhonePrice)) > (int)Math.round(Double.parseDouble(secondPhonePrice)))
            return false;
        return true;

    }

    /**
     * Function for validation of sorting by higher price
     */
    public boolean validateExchangePhonesSortByHigherPrice()
    {
        String firstPhonePrice = Mapper.find(domFile, "exchangeFirstPhonePrice").getText().replaceAll("₹", "").replaceAll(",","").trim();
        String secondPhonePrice = Mapper.find(domFile, "exchangeSecondPhonePrice").getText().replaceAll("₹", "").replaceAll(",","").trim();
        if ((int)Math.round(Double.parseDouble(firstPhonePrice)) < (int)Math.round(Double.parseDouble(secondPhonePrice)))
            return false;
        return true;
    }


    /**
     * Function to click on CreateAlert
     */
    public void clickOnCreateAlert() {
        Mapper.find(domFile, "createAlert").click();
        Mapper.waitForElementToBeVisible(domFile, "cartHeader");
    }

    /**
     * Function to click on CreateAlert
     */
    public void clickOnCreateAlertSubmit() {
        executeScript("scroll(0, -250);");
        Mapper.find(domFile, "alertSubmitBtn").click();
        Mapper.waitForElementToBeVisible(domFile, "alertSubmitBtn");
      }

    /**
     * Function for validation of CreateAlert
     */
    public boolean verificationOfCreateAlertFields() {
        if (!(Mapper.find(domFile, "selectBrand").isDisplayed()) || !(Mapper.find(domFile, "selectModel").isDisplayed()) || !(Mapper.find(domFile, "selectCity").isDisplayed()) ||
                !(Mapper.find(domFile, "emailInAlert").isDisplayed()) || !(Mapper.find(domFile, "mobileInAlert").isDisplayed()) || !(Mapper.find(domFile, "alertSubmitBtn").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of CreateAlert
     */
    public boolean validationOfCreateAlert() {
        if (!(Mapper.find(domFile, "selectBrand").isDisplayed()) || !(Mapper.find(domFile, "selectModel").isDisplayed()) || !(Mapper.find(domFile, "selectCity").isDisplayed()) ||
                !(Mapper.find(domFile, "emailInAlert").isDisplayed()) || !(Mapper.find(domFile, "mobileInAlert").isDisplayed()) || !(Mapper.find(domFile, "alertSubmitBtn").isDisplayed()) || !(Mapper.find(domFile, "alertSubmitBtn").isDisplayed()))
            return false;
        return true;
    }


    /**
     * Function for validation of Error in CreateAlert
     */
    public boolean validationOfErrorInCreateAlert() {
        if ((Mapper.find(domFile, "selectBrandFieldError").isDisplayed()) ||
                (Mapper.find(domFile, "selectModelFieldError").isDisplayed()) ||
                !(Mapper.find(domFile, "selectCityFieldError").isDisplayed()) ||
                (Mapper.find(domFile, "emailInAlertError").isDisplayed()) ||
                (Mapper.find(domFile, "mobileInAlertError").isDisplayed()))
            return true;
        return false;
    }

    /**
     * Function for selectBrand in CreateAlert
     */
    public void selectBrandinCreateAlert(String Option) {
        Select select = new Select(Mapper.find(domFile, "selectBrand"));
        select.selectByVisibleText(Option);
    }

    /**
     * Function for selectModel in CreateAlert
     */
    public void selectModelinCreateAlert(String Option) {
        Select select = new Select(Mapper.find(domFile, "selectModel"));
        select.selectByVisibleText(Option);
    }

    /**
     * Function for selecting City  in CreateAlert
     */
    public void selectCityinCreateAlert(String Option) {
        Select select = new Select(Mapper.find(domFile, "selectCity"));
        select.selectByVisibleText(WordUtils.capitalize(Option));
    }

    /**
     * Function for getting preselected City in CreateAlert
     */
    public String getPreselectedCityAlert() {
        Select select = new Select(Mapper.find(domFile, "selectCity"));
        String city = select.getFirstSelectedOption().getText();
        System.out.println(city);
        return city;
    }

    /**
     * Function to enterEmail in CreateAlert
     */
    public void enterEmailinCreateAlert(String EmailId) {
        Mapper.find(domFile, "emailInAlert").sendKeys(EmailId);
    }


    /**
     * Function to enterMobile in CreateAlert
     */
    public void enterMobileInCreateAlert(String Mobile) {
        Mapper.find(domFile, "mobileInAlert").sendKeys(Mobile);
    }


    /**
     * Function to verify create alert is success
     */
    public boolean createAlertSuccess() {
        Mapper.waitForElementToBeVisible(domFile, "saveAlertMsg");
        if (!(Mapper.find(domFile, "saveAlertMsg").isDisplayed()) || !(Mapper.find(domFile, "createNewAlert").isDisplayed()))
             return false;
        return true;
    }

    /**
     * Function to Click on Create New Alertlink
     */
    public void clickonCreateNewAlertLink() {
        Mapper.find(domFile, "createNewAlert").click();
        Mapper.waitForElementToBeVisible(domFile, "emailInAlert");
    }

    /**
     * Open preowned phone details page with multiple sellers
     */
    public void openPreOwnedPhoneDetailsWithMultipleSellers()
    {
        Mapper.find(domFile,"productWithMultipleSeller").click();
        Mapper.waitForElementToBeVisible(domFile,"viewCount");
    }

    /**
     * Open Exchange phone details page with Multiple sellers
     */
    public void openExchangePhoneDetailsWithMultipleSellers()
    {
        Mapper.find(domFile,"productWithMultipleSeller").click();
        Mapper.waitForElementToBeVisible(domFile,"viewCount");
    }

    /**
     * Function for Validating breadcrumb on QuikrX home page
     */
    public boolean validateBreadCrumbOnQuikrXPage()
    {
        if (!(Mapper.find(domFile, "bradCrumbOnHomePage").isDisplayed()))
            return false;
        return true;
    }

    public boolean isExchangeDisplayed(){

        return Mapper.find(domFile,"exchangeTab").isDisplayed();
    }

    public boolean isCertifiedDisplayed(){

        return Mapper.find(domFile,"certifiedTab").isDisplayed();
    }



}
