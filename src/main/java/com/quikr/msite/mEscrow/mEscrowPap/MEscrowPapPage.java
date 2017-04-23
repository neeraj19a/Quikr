package com.quikr.msite.mEscrow.mEscrowPap;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 5/2/16.
 */
public class MEscrowPapPage extends MPageBase {

    private static final String domFile = getProperties().get("mEscrow_PAP_DOM_FILE");
    public MEscrowPapPage(AppiumDriver driver){super(domFile,driver);}

    public void enterDescription(String Desc)
    {
        Mapper.waitForElementToBeVisible(domFile,"Description");
        Mapper.find(domFile,"Description").sendKeys(Desc);
    }

    public void goNextFromPictures(){
        Mapper.waitForElementToBeVisible(domFile,"nextAddPictures");
        Mapper.find(domFile,"nextAddPictures").click();
    }

    public void selectCategory(String category)
    {
        Mapper.waitForElementToBeVisible(domFile,"categoryList");
        Mapper.findAndReplace(domFile, "categoryList", new String[]{category}).click();
    }

    public void selectSubCategory(String subCategory)
    {
        Mapper.waitForElementToBeVisible(domFile,"subCategoryList");
        Mapper.findAndReplace(domFile, "subCategoryList", new String[]{subCategory}).click();
    }

    public void enterPrice(String price)
    {
        Mapper.waitForElementToBeVisible(domFile,"price");
        Mapper.find(domFile,"price").sendKeys(price);
        navigateBack();
    }

    public void selectProductType(String productType)
    {
        Mapper.waitForElementToBeVisible(domFile,"productType");
//        Mapper.findAndReplace(domFile,"productType",new String[]{productType}).click();
    }

    public void selectFurnitureType(String type){
        Select Year  =new Select(Mapper.find(domFile,"furnitureType"));
        Year.selectByValue(type+"s");
    }


    public void enterMinPrice(String minPrice)
    {
        Mapper.find(domFile,"minimumPrice").sendKeys(minPrice);
    }

    public void selectPurchaseYear(String year)
    {
        Select Year  =new Select(Mapper.find(domFile,"purchaseYearDropdown"));
        Year.selectByValue(year);
    }

    public void selectOriginalInvoice()
    {
        Mapper.find(domFile,"originalInvoice").click();
    }

    public void selectBrand(String brand)
    {
        Select Brand  =new Select(Mapper.find(domFile,"brandNameDropdown"));
        Brand.selectByValue(brand);
    }

    public void selectModel(String model)
    {
        Select Model  =new Select(Mapper.find(domFile,"modelNameDropdown"));
        Model.selectByValue(model);
    }

    public void selectOS(String OS)
    {
        Select OpSys  =new Select(Mapper.find(domFile,"OSNameDropdown"));
        OpSys.selectByValue(OS);
    }

    public void selectSims(String sims)
    {
        Select Sims  =new Select(Mapper.find(domFile,"NoOfSimsDropdown"));
        Sims.selectByValue(sims);
    }

    public void selectIncludedItem(String includedItem)
    {
        Select Items  =new Select(Mapper.find(domFile,"IncludesDropdown"));
        Items.selectByValue(includedItem);
    }

    public void selectPhysicalChecks(String checks)
    {
        Select Checks  =new Select(Mapper.find(domFile,"ChecksDropdown"));
        Checks.selectByValue(checks);
    }

    public void enterAddress(String sellerAddress)
    {
        Mapper.find(domFile,"address").click();
        Mapper.find(domFile,"address").sendKeys(sellerAddress);
//        navigateBack();
    }

    public void selectCity(String city)
    {
        Mapper.find(domFile,"cityDropdown").click();
        Mapper.waitForElementToBeVisible(domFile,"cityList");
        Mapper.findAndReplace(domFile,"cityList", new String[]{city}).click();
    }

    public void selectLocality(String locality)
    {
        Mapper.waitForElementToBeClickable(domFile,"localityDropdown");
        Mapper.find(domFile,"localityDropdown").click();
        Mapper.waitForElementToBeVisible(domFile,"localityList");
        Mapper.findAndReplace(domFile,"localityList", new String[]{locality}).click();
    }

    public void enterPincode(String pincode)
    {
        Mapper.waitForElementToBeVisible(domFile,"pincode");
        Mapper.find(domFile,"pincode").sendKeys(pincode);
    }

    public void goNextFromAttributes()
    {
        Mapper.waitForElementToBeVisible(domFile,"NextSelectAttribute",3);
        Mapper.waitForElementToBeClickable(domFile,"NextSelectAttribute");
        Mapper.find(domFile,"NextSelectAttribute").click();
    }

    public void enterTitle(String title)
    {
        Date date=new Date();
        Mapper.waitForElementToBeVisible(domFile,"title");
        Mapper.find(domFile,"title").sendKeys(title+date);
    }

    public void enterSellerEmail(String sellerEmail)
    {
        Mapper.waitForElementToBeClickable(domFile,"sellerMail");
        Mapper.find(domFile,"sellerMail").sendKeys(sellerEmail);
    }

    public void enterSellerMobile(String sellerMobile)
    {
        Mapper.waitForElementToBeClickable(domFile,"sellerMobile");
        Mapper.find(domFile,"sellerMobile").sendKeys(sellerMobile);
    }

    public void selectMaintainPrivacy()
    {
        Mapper.waitForElementToBeClickable(domFile,"maintainPrivacy");
        Mapper.find(domFile,"maintainPrivacy").click();
    }

    public void postAd()
    {
        Mapper.waitForElementToBeClickable(domFile,"postAd");
        Mapper.find(domFile,"postAd").click();
    }

    public boolean ifAdPostSuccess(){
        Mapper.waitForElementToBeVisible(domFile,"postAdSuccessMessage");
        if (Mapper.find(domFile,"postAdSuccessMessage").getText().equalsIgnoreCase("Congratulations!")){
            return true;
        }
        else return false;
    }

    public String getAdId(){
        Mapper.waitForElementToBeVisible(domFile,"viewAdButton");
        String AdLink = Mapper.find(domFile,"viewAdButton").getAttribute("href").toString();
        String AdId = AdLink.substring(AdLink.indexOf("AdIdZ")+5,AdLink.indexOf("?flow"));
        System.out.println("Ad Id : "+AdId);
        return AdId;
    }

    public void navigateHome(){
        Mapper.waitForElementToBeVisible(domFile,"logoToHome",5);
        Mapper.find(domFile,"logoToHome").click();
        Mapper.waitForElementToBeVisible(domFile,"navigationBarHomePage",5);
        Mapper.find(domFile,"navigationBarHomePage").click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean descriptionMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"descriptionError");
        if(Mapper.find(domFile,"descriptionError").getText().equalsIgnoreCase("Description Should be minimum 8 words")){
            return true;
        }
        else
            return false;
    }

    public boolean priceMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"priceError");
        if(Mapper.find(domFile,"priceError").getText().equalsIgnoreCase("Enter Price")){
            return true;
        }
        else
            return false;
    }

    public boolean brandNameMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"brandNameError");
        if(Mapper.find(domFile,"brandNameError").getText().equalsIgnoreCase("Select Brand Name")){
            return true;
        }
        else
            return false;
    }

    public boolean addressMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"addressError");
        if(Mapper.find(domFile,"addressError").getText().equalsIgnoreCase("Please enter the address")){
            return true;
        }
        else
            return false;
    }

    public boolean localityMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"localityError");
        if(Mapper.find(domFile,"localityError").getText().equalsIgnoreCase("Please select your locality")){
            return true;
        }
        else
            return false;
    }

    public boolean pincodeMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"pincodeError");
        if(Mapper.find(domFile,"pincodeError").getText().equalsIgnoreCase("Please enter the pincode")){
            return true;
        }
        else
            return false;
    }

    public boolean titleMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"titleError");
        if(Mapper.find(domFile,"titleError").getText().equalsIgnoreCase("Title Should be minimum 4 words")){
            return true;
        }
        else
            return false;
    }

    public boolean emailMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"emailError");
        if(Mapper.find(domFile,"emailError").getText().equalsIgnoreCase("Invalid Email")){
            return true;
        }
        else
            return false;
    }

    public boolean mobileMandatoryError(){
        Mapper.waitForElementToBeVisible(domFile,"mobileError");
        if(Mapper.find(domFile,"mobileError").getText().equalsIgnoreCase("Please enter mobile number")){
            return true;
        }
        else
            return false;
    }

    public void warehouseSelect() {
        Mapper.find(domFile, "warehouseYes").click();
    }
}
