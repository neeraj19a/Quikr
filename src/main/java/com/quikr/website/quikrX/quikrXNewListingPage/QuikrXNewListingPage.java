package com.quikr.website.quikrX.quikrXNewListingPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by francis.s@quikr on 20/11/15.
 */
public class QuikrXNewListingPage extends PageBase {

    private static final String domFile = getProperties().get("QUIKRX_NEW_LISTING_DOM_FILE");

    public QuikrXNewListingPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public String isNewTabselected(){
        return Mapper.find(domFile,"exchangeBread").getText().trim();
    }

    public void clickCertifiedTab() {
        Mapper.find(domFile, "certfifiedTab").click();
    }

    public void clickExchangeTab() {
        Mapper.find(domFile, "exchangePhoneTab").click();
    }

    public void clickcreateAlertLink() {
        Mapper.find(domFile, "createAlert").click();
    }

    public void openFirstitem(){
        Mapper.find(domFile,"firstItem").click();
    }

    public void entercreateAlertBrand(String brand) {
        clickBrandDrop();
        Mapper.findAndReplace(domFile, "createAlertBrand", new String[]{brand}).click();
    }

    public void entercreateAlertModel(String model) {
        clickModelDrop();
        Mapper.findAndReplace(domFile, "createAlertModel", new String[]{model}).click();
    }

    public void entercreateAlertCity(String city) {
        clickCityDrop();
        Mapper.findAndReplace(domFile, "createAlertCity", new String[]{city}).click();
    }

    public void clickCloseClreateAlert(){
        Mapper.find(domFile,"createAlertClose").click();
    }

    public void entercreateAlertEmail(String email) {

        Mapper.find(domFile, "createAlertEmail").sendKeys(email);
    }

    public void entercreateAlertPhone(String phoneNo) {
        Mapper.find(domFile, "createAlertPhone").sendKeys(phoneNo);
    }

    public void clickcreateAlertSubmit() {
        Mapper.find(domFile, "createAlertSubmit").click();
    }


    public void clickPriceRangeFilter() {
        Mapper.find(domFile, "priceRangeFilter").click();
    }


    public void clickClearFilters() {
        Mapper.find(domFile, "clearFilter").click();
    }


    public boolean filteresValuesContains(String value) {

        List<WebElement> elements = Mapper.finds(domFile, "filteredValues");
        boolean flag=false;
        for (WebElement ele : elements) {
            System.out.println(ele.getAttribute("val"));
            if (ele.getAttribute("val").equalsIgnoreCase(value)) {
                flag=true;
                break;
            } else
                flag=false;

        }
        return flag;
    }

    public String getFirstListingCameraDetails(){
        return Mapper.find(domFile,"firstListingCameraDetail").getText().replace("MP","").trim();
    }


    public String getFirstListingScreenSize(){

        return Mapper.find(domFile,"firstListingSizeDetails").getText().replace("\"","");
    }


    public String getFirstListingTitle() {
        return Mapper.find(domFile, "firstListingTitle").getText();
    }

    public void clickApplyFilterButton() {
        Mapper.find(domFile, "applyFilterButton").click();
    }

    public void clickPriceRangeFIlter() {
            Mapper.find(domFile, "priceRangeFilter").click();
        }

    public void clickPriceRangeFIlterValue() {
        Mapper.find(domFile, "PriceRangeFirstValue").click();
    }

    public void clickBrandFilter() {
        Mapper.find(domFile, "brandFilter").click();
    }

    public void selectBrandFilter(String value) {
        Mapper.findAndReplace(domFile, "brandFilterValue", new String[]{value}).click();
    }

    public void clickoperatingSystemFilter() {
        Mapper.find(domFile, "operatingSystemFilter").click();
    }

    public void selectopertatingSystemFilterValue(String value) {
        Mapper.findAndReplace(domFile, "opertatingSystemFilterValue", new String[]{value}).click();
    }

    public void categoryFilter() {
        Mapper.find(domFile, "categoryFilter").click();
    }

    public void selectcategoryFilterValue(String value) {
        Mapper.findAndReplace(domFile, "categoryFilterValue", new String[]{value}).click();
    }

    public void clickMoreFileter() {
        Mapper.find(domFile, "moreFilters").click();
    }

    public void clickdualSimFilterr() {
        Mapper.find(domFile, "dualSimFilter").click();
    }

    public void selectsingleSimFilter() {
        Mapper.find(domFile, "singleSimFilter").click();
    }

    public void selectScreenSizeFilter(String value) {
        Mapper.findAndReplace(domFile, "screenSizeFilterValue", new String[]{value}).click();
    }

    public void selectcameraFilterValue(String value) {
        Mapper.findAndReplace(domFile, "cameraFilterValue", new String[]{value}).click();
    }

    public void selectramFilterValue(String value) {
        Mapper.findAndReplace(domFile, "ramFilterValue", new String[]{value}).click();
    }

    public void selectprocessorFilterValue(String value) {
        Mapper.findAndReplace(domFile, "processorFilterValue", new String[]{value}).click();
    }

    public List<String> categoryRibbon(String condition) {

        List<String> returnList = new LinkedList<>();
        List<WebElement> list =  Mapper.findsAndReplace(domFile, "categoryRibbon",new String[]{condition});
        for (WebElement ele : list) {
            returnList.add(ele.getAttribute("class"));

        }

        return returnList;

    }

    public boolean firstListingTitleisPresent() {
        return Mapper.find(domFile, "firstListingTitle").isDisplayed();
    }

    public boolean firstLisitngActualPricePresent() {
        return Mapper.find(domFile, "firstLisitngActualPrice").isDisplayed();
    }

    public int getfirstLisitngActualPrice() {
        return Integer.parseInt(Mapper.find(domFile, "firstLisitngActualPrice").getText().replace("₹", "").replace(",", "").trim());
    }

    public String getFirstListingRamValue(){
        return Mapper.find(domFile,"firstListingRam").getText().replace("GB","").trim();
    }


    public int getFirstListingDiscPrice() {
        try {
            return Integer.parseInt(Mapper.find(domFile, "firstListingPrice").getText().replace("₹", "").replace(",", "").trim());
        }catch (NullPointerException e){
            return Integer.parseInt(Mapper.find(domFile, "firstListingPrice").getText().replace("₹", "").trim());
        }
    }

    public int getSecondListingDiscPrice(){
        return Integer.parseInt(Mapper.find(domFile, "secondListingPrice").getText().replace("₹", "").replace(",", "").trim());
    }

    public boolean firstListingDiscountisPresent() {
        return Mapper.find(domFile, "firstListingDiscount").isDisplayed();
    }

    public boolean firstListingCameraDetailisPresent() {
        return Mapper.find(domFile, "firstListingCameraDetail").isDisplayed();
    }

    public boolean firstListingRamisPresent() {
        return Mapper.find(domFile, "firstListingRam").isDisplayed();
    }

    public boolean firstListingMemoryisPresent() {
        return Mapper.find(domFile, "firstListingMemory").isDisplayed();
    }

    public void sortDropDown() {
        Mapper.find(domFile, "sortDropDown").click();
    }

    public void clickSortOptions(String sort){
        sortDropDown();
        Mapper.findAndReplace(domFile,"sortByValue",new String[]{sort}).click();

    }

    public void clickSellerListLink() {
        Mapper.find(domFile, "firstListingsellerCountLink").click();
    }

    public boolean verifyCallUs() {
        return Mapper.find(domFile, "callUs").isDisplayed();
    }

    public boolean verifyEmailUs() {
        return Mapper.find(domFile, "emailUs").isDisplayed();
    }

    public String getAlertSuccessMessage(){
        return Mapper.find(domFile,"alertSuccessMessage").getText().trim();
    }

    public void clickBrandDrop(){
        Mapper.find(domFile,"brandDropDown").click();
    }

    public void clickModelDrop(){
        Mapper.find(domFile,"modelDropDown").click();
    }

    public void clickCityDrop(){
        Mapper.find(domFile,"cityDropDown").click();
    }

    public void openItemWithMoreSeller(){
        List<WebElement> value = Mapper.finds(domFile,"moreSellers");
        navigateTo().to(value.get(0).getAttribute("href"));
    }

    public void getDicountItem(){
        List<WebElement> value = Mapper.finds(domFile,"discountAvailable");
        value.get(0).click();
    }

    public int getDisplayedPhoneCount(){
        String string = Mapper.find(domFile,"itemCount").getText();
        return Integer.parseInt(string.split(" ")[0].trim());
    }

    public int getAllPhoneCount(){
        List<WebElement> value = Mapper.finds(domFile,"allPhonesCount");
        return value.size();
    }



    public void clickConditionFilter() {
        Mapper.find(domFile, "conditionFilter").click();
    }

    public List<String> getConditionFilterValues(){

        List<String> conditionValues = new LinkedList<>();
        List<WebElement> eles = Mapper.finds(domFile,"getConditionFilterValues");
        for (WebElement values:eles){

            conditionValues.add(values.getText());
        }

        return conditionValues;
    }



    public void selectCondtionFilter(String value) {
        Mapper.findAndReplace(domFile, "conditionFilterValue", new String[]{value}).click();
    }

    public void clickModelFilter(){
        Mapper.find(domFile,"modelName").click();
    }

    public void selectModels(){
        Mapper.find(domFile,"selectModel").click();
    }

    public boolean validateModel(String model){
        System.out.println(Mapper.find(domFile,"availableModelName").getText());
        List<WebElement> list = Mapper.finds(domFile,"availableModelName");
        System.out.println(list.get(0).getText());

        boolean flag=false;

        for (WebElement element: list){
            System.out.println(element.getText());
            if(!element.getText().contains(model)){
                return flag;
            }else
            {
                flag=true;
            }
        }
        return flag;
    }


    public void clickAccessoryTab(){
        Mapper.find(domFile,"accessoriedTab").click();
    }

    public void applyAccessoryPriceFilter(){

        Mapper.find(domFile,"accessoryPriceFilter").click();
        Mapper.find(domFile,"accessoryPriceFilterValue").click();

    }

    public void applyAccessoryTypeFilter(String type){

        Mapper.find(domFile,"accessoryTypeFilter").click();
        Mapper.findAndReplace(domFile,"accessoryTypeValue",new String[]{type}).click();
    }

    public void applyAccessoryBrandFilter(String brand){

        Mapper.find(domFile,"accessoryBrandFilter").click();
        Mapper.findAndReplace(domFile,"accessoriesBrandFilterValue",new String[]{brand}).click();
    }


    public String applyAccessoryModelFilter(){

        Mapper.find(domFile,"accessoryModelFilter").click();
        String model = Mapper.find(domFile,"getAccessModelName").getText().trim();
        Mapper.find(domFile,"accessoryModelFilterValue").click();
        return model;
    }



}
