package com.quikr.website.escrow.escrowPap;

import com.quikr.website.PageBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Date;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by gurinder.singh@quikr.com
 */
public class EscrowPapPage extends PageBase {
    public EscrowPapPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ESCROW_PAP_DOM_FILE");
    private final static Log LOGGER = LogFactory.getLog(EscrowPapPage.class.getName());

    public void selectAdType() {
        Mapper.find(domFile, "WantToSellRadioButton").click();
    }

    public void enterTitle(String title) {
        Date date =new Date();
        Mapper.find(domFile, "TitleTextfield").sendKeys(title+date);
    }

    public void selectCondition() {
        Mapper.find(domFile, "ConditionUsed").click();
    }

    public void warehouseSelect() {
        Mapper.waitForElementToBeVisible(domFile, "warehouseSelect");
        Mapper.waitForElementToBeClickable(domFile, "warehouseSelect");
        Mapper.find(domFile, "warehouseSelect").click();
    }

    public void enterPrice(String Price) {
        Mapper.find(domFile, "PriceTextfield").sendKeys(Price);
    }

    public void selectCategory(String categoryName) {
        Mapper.findAndReplace(domFile, "categoryName", new String[]{categoryName}).click();
    }

    public void subcategory(String subcategory) {
        Mapper.waitForElementToBeVisible(domFile, subcategory);
        Mapper.waitForElementToBeClickable(domFile, subcategory);
        Mapper.findChildElement(domFile, subcategory, "subcategory", "a", true).click();
    }

    public void selectCategories(String category, String subcategory) {
        selectCategory(category);
        subcategory(subcategory);
    }

    public void enterMinPrice(String MinPrice) {
        Mapper.doJavascriptOnElement("jQuery('#ReservePrice').val('" + MinPrice + "');");
        //Mapper.doJavascriptOnElement("jQuery('#Reserved_Price').val('4000');");
    }

    public void selectYearOfPurchase(String year) {
        Mapper.find(domFile, "YOPDropdown").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "YOPList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(year)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void furnitureType(String furnitureName) {
        Mapper.find(domFile, "furniture").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "furnitureList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(furnitureName)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void selectBrandName(String BrandName) {
        Mapper.find(domFile, "BrandNameDropdown").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "BrandNameList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(BrandName)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void selectRam(String ramsize) {
        Mapper.find(domFile, "ram").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "ramList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(ramsize)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void screenSize(String screensize) {
        Mapper.find(domFile, "screenSize").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "screenSizeList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(screensize)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void selectOS(String OSName) {
        Mapper.find(domFile, "OSDropdown").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "OSList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(OSName)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void selectNoOfSims(String sims) {
        Mapper.find(domFile, "SimsDropdown").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "SimsList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(sims)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void selectAlsoIncludes(String items) {
        Mapper.find(domFile, "AlsoIncludesDropdown").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "AlsoIncludesList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(items)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void selectFunctionalChecks(String checks) {
        Mapper.find(domFile, "FunctionalChecksDropdown").click();
        List<WebElement> allBrands = Mapper.finds(domFile, "FunctionalChecksList");
        for (int i = 0; i < allBrands.size(); i++)
            if (allBrands.get(i).getText().equalsIgnoreCase(checks)) {
                allBrands.get(i).click();
                break;
            }
    }

    public void enterDescription(String desc) {
        Mapper.find(domFile, "adDescription").click();
        Mapper.find(domFile, "adDescription").sendKeys(desc);
    }

    public void selectSellerType() {
        Mapper.find(domFile, "IndividualSellerType").click();
    }

    public void enterSellerName(String sellerName) {
        Mapper.find(domFile, "NameTextfield").sendKeys(sellerName);
    }

    public void enterSellerEmail(String sellerEmail) {
        WebElement email = Mapper.find(domFile, "EmailTextfield");
        if (!email.getAttribute("type").equals("hidden")) {
            Mapper.find(domFile, "EmailTextfield").sendKeys(sellerEmail);
        }
    }

    public void enterSellerMobile(String sellerMobile) {
        Mapper.find(domFile, "MobileTextfield").sendKeys(sellerMobile);
    }

    public void enterSellerAddress(String sellerAddress) {

        try {
            if (Mapper.find(domFile, "AddressTextfield") != null) {
                Mapper.find(domFile, "AddressTextfield").sendKeys(sellerAddress);
            }
        }
        catch (Exception e)
        {
            logger.info("Pls check it seems like address Field is not present");
        }
    }

    public void clickSkipLink(){
        if(Mapper.find(domFile,"skipLink")!=null){
            Mapper.find(domFile,"skipLink").click();
        }

        else {
            logger.info("Skip Link might be Not Present on Citrus Pay Page");
        }
    }

    public void selectCity(String sellerCity) {
        Mapper.waitForElementToBeClickable(domFile, "CityDropdown");
        Mapper.find(domFile, "CityDropdown").click();
        Mapper.find(domFile, "CitySearchField").sendKeys(sellerCity);
        Mapper.waitForElementToBeClickable(domFile, "CityDropdownValue");
        Mapper.find(domFile, "CityDropdownValue").click();
    }

    public void selectLocality(String sellerLocality) {
        Mapper.find(domFile, "LocalityDropdown").click();
        Mapper.find(domFile, "LocalitySearchField").sendKeys(sellerLocality);
        List<WebElement> filteredLocality = Mapper.finds(domFile, "LocalityDropdownList");
        for (int i = 0; i < filteredLocality.size(); i++)
            if (filteredLocality.get(i).getText().equalsIgnoreCase(sellerLocality)) {
                filteredLocality.get(i).click();
                break;
            }
    }

    /**
     * select locality based upon index
     *
     * @param index
     */
    public void selectLocality(int index) {
        Mapper.find(domFile, "LocalityDropdown").click();
        Mapper.waitForElementToBeVisible(domFile, "LocalityDropdownList");
        Mapper.finds(domFile, "LocalityDropdownList").get(index).click();
    }

    public void enterPincode(String pincode) {
        try {
            if (Mapper.find(domFile, "PincodeTextfield") != null) {
                Mapper.find(domFile, "PincodeTextfield").sendKeys(pincode);
            }
        }
        catch (Exception e){
        logger.info("Pls check it seems like Pin code field is not present");
        }
    }

    public void maintainPrivacy() {
        Mapper.find(domFile, "PrivacyCheckbox").click();
    }

    public void freeAd() {
        Mapper.find(domFile, "PayWithCashTab").click();
        Mapper.find(domFile, "FreeAdRadiobutton").click();
    }

    public void submitAd() {
            Mapper.waitForElementToBeVisible(domFile, "PostAdButton");
            Mapper.find(domFile, "PostAdButton").click();
          }

    public void submitAdWareHouse() {
        Mapper.doJavascriptOnElement("jQuery('#WareHouse_Enabled').val('1');");
        Mapper.waitForElementToBeVisible(domFile, "PostAdButton");
        Mapper.find(domFile, "PostAdButton").click();
    }

    public void submitAdWithMinPrice(String minPrice) {
        Mapper.doJavascriptOnElement("jQuery('#Reserved_Price').val('" + minPrice + "');");
        Mapper.waitForElementToBeVisible(domFile, "PostAdButton");
        Mapper.find(domFile, "PostAdButton").click();
        }

    public boolean isAdPostSuccess() {
        Mapper.waitForElementToBeVisible(domFile, "AdPostSuccessBanner",20);
        if (Mapper.find(domFile, "AdPostSuccessBanner").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public String getAdId() {
        Mapper.waitForElementToBeVisible(domFile, "AdIdText");
        String AdId = Mapper.find(domFile, "AdIdText").getText().replaceAll("[\\D]", "");
        LOGGER.info("AD ID == " + AdId);
        return AdId;
    }

    public void handleVerifyNoPopUp() {
        try {
            Mapper.waitForElementToBeVisible(domFile, "closeVerifyMob");
            if (Mapper.find(domFile, "closeVerifyMob").isDisplayed()) {
                Mapper.find(domFile, "closeVerifyMob").click();
            }
        } catch(Exception e){
                LOGGER.info("Pop up not found");
            }
    }

    public boolean isTitleErrorThrown() {
        if (Mapper.find(domFile, "titleErrorMsg").getText().equalsIgnoreCase("Please enter Title")) {
            return true;
        } else return false;
    }

    public boolean isConditionErrorThrown() {
        if (Mapper.find(domFile, "conditionErrorMsg").getText().equalsIgnoreCase("Please make a selection")) {
            return true;
        } else return false;
    }

    public boolean isPriceErrorThrown() {
        if (Mapper.find(domFile, "priceErrorMsg").getText().equalsIgnoreCase("Please enter price")) {
            return true;
        } else return false;
    }

    public boolean isBrandNameErrorThrown() {
        if (Mapper.find(domFile, "brandErrorMsg").getText().equalsIgnoreCase("Please select Brand Name")) {
            return true;
        } else return false;
    }

    public boolean isFurnitureTypeErrorThrown() {
        if (Mapper.find(domFile, "furnitureErrorMsg").getText().equalsIgnoreCase("Please select Furniture Type")) {
            return true;
        } else return false;
    }

    public boolean isDescriptionErrorThrown() {
        if (Mapper.find(domFile, "descriptionErrorMsg").getText().equalsIgnoreCase("Please enter description")) {
            return true;
        } else return false;
    }

    public boolean isSellerTypeErrorThrown() {
        if (Mapper.find(domFile, "sellerTypeErrorMsg").getText().equalsIgnoreCase("Please make a selection")) {
            return true;
        } else return false;
    }

    public boolean isAddressErrorThrown() {
        if (Mapper.find(domFile, "addressErrorMsg").getText().equalsIgnoreCase("Please enter address")) {
            return true;
        } else return false;
    }

    public boolean isLocalityErrorThrown() {
        if (Mapper.find(domFile, "localityErrorMsg").getText().equalsIgnoreCase("Please select Locality")) {
            return true;
        } else return false;
    }

    public boolean isPincodeErrorThrown() {
        if (Mapper.find(domFile, "pincodeErrorMsg").getText().equalsIgnoreCase("Please enter pincode")) {
            return true;
        } else return false;
    }

    public void navigateHome() {
        Mapper.waitForElementToBeVisible(domFile, "quikrLogo",20);
        Mapper.find(domFile, "quikrLogo").click();
    }

    public void closePostAdPopUpNewUICities(String city) {
        if (city == "Ahmedabad") {
            WebElement popup = Mapper.find(domFile, "PostAdPopUpNewUICities");
            if (popup != null) {
                Mapper.waitForElementToBeVisible(domFile, "postwithoutPhotosPopUpNewUICities", 15);
                Mapper.find(domFile, "postwithoutPhotosPopUpNewUICities").click();
            }
        }

    }

}
