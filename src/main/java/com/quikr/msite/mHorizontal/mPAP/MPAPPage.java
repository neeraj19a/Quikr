package com.quikr.msite.mHorizontal.mPAP;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.Select;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by quikr on 4/11/15.
 */
    public class MPAPPage extends  MPageBase{
    public MPAPPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("mMPAP_DOM_FILE");

    public void setAdDescription(String description){
        if(Mapper.waitForElementToBeClickable(domFile, "PostAdDescription")==true) {
            Mapper.find(domFile, "PostAdDescription").click();
            Mapper.find(domFile, "PostAdDescription").click();
            Mapper.find(domFile, "PostAdDescription").sendKeys(description);
        }
    }

    public boolean isNewPostAdScreenavailable(){
        boolean flag=false;
        if(Mapper.find(domFile,"UploadImagePostAd")!=null){
            flag=true;
        }
        else {
            return false;
        }

        if(Mapper.find(domFile, "PostAdDescription")!=null){
            flag=true;
        }
        else {
            return false;
        }
        return flag;
    }

    public boolean isPostAdPageloaded(){
        return getCurrentLocation().contains("post-classifieds-ads");
    }


    public void clickCategoryPAP(String CategoryName){
        Mapper.findAndReplace(domFile,"CategoryName",new  String[]{CategoryName}).click();
    }

    public void clickSubCategoryPAP(String SubCategoryName){
        Mapper.findAndReplace(domFile, "SubCategoryName", new String[]{SubCategoryName}).click();
    }

    public void clickNextButton(){
        Mapper.waitForElementToBeClickable(domFile,"NextButton");
        Mapper.find(domFile, "NextButton").click();
    }


    public void clickNextButtonPostAdPage(){
        if(Mapper.waitForElementToBeClickable(domFile,"NextButtonPostAdPage")==true) {
            Mapper.find(domFile, "NextButtonPostAdPage").click();
        }
    }

    public boolean validateSelectCategory(String Categoryone, String Categorytwo, String Categorythree, String Categoryfour, String Categoryfive, String Categorysix,String Categoryseven){

        boolean flag=false;
        if(Mapper.findAndReplace(domFile,"CategoryName", new String []{Categoryone}).isDisplayed()){
            flag=true;
        }
        else {
            flag=false;
        }
        if(Mapper.findAndReplace(domFile,"CategoryName", new String []{Categorytwo}).isDisplayed()){
            flag=true;
        }
        else {
            flag=false;
        }
        if(Mapper.findAndReplace(domFile,"CategoryName", new String []{Categorythree}).isDisplayed()){
            flag=true;
        }
        else {
            flag=false;
        }
        if(Mapper.findAndReplace(domFile,"CategoryName", new String []{Categoryfour}).isDisplayed()){
            flag=true;
        }
        else {
            flag=false;
        }
        if(Mapper.findAndReplace(domFile,"CategoryName", new String []{Categoryfive}).isDisplayed()){
            flag=true;
        }
        else {
            flag=false;
        }
        if(Mapper.findAndReplace(domFile,"CategoryName", new String []{Categorysix}).isDisplayed()){
            flag=true;
        }
        else {
            flag=false;
        }
        if(Mapper.findAndReplace(domFile,"CategoryName", new String []{Categoryseven}).isDisplayed()){
            flag=true;
        }
        else {
            flag=false;
        }

        return flag;
    }

    public void MPostMobileAdSell(String Price, String Brand, String Model, String OS, String Address, String Pincode, String adTitle,String mobilenumber,String description, String emailid) {
        Mapper.waitForElementToBeClickable(domFile, "Price");
        Mapper.find(domFile, "Price").click();
        Mapper.find(domFile,"Price").sendKeys(Price);
        Select brand=new Select(Mapper.find(domFile,"Brand"));
        brand.selectByValue(Brand);
        Select model=new Select(Mapper.find(domFile,"Model"));
        model.selectByValue(Model);
        Select os=new Select(Mapper.find(domFile,"OperatingSystem"));
        os.selectByValue(OS);
        Mapper.find(domFile,"Locality").click();
        if(Mapper.waitForElementToBeClickable(domFile,"secondLocalitySelect")==true) {
            Mapper.find(domFile, "secondLocalitySelect").click();
        }
        if(Mapper.waitForElementToBeClickable(domFile,"Address")==true) {
            Mapper.find(domFile, "Address").click();
        }
        Mapper.find(domFile,"Address").sendKeys(Address);
        Mapper.find(domFile,"pincode").click();
        Mapper.find(domFile,"pincode").sendKeys(Pincode);
        Mapper.find(domFile,"NextButtonPostAdPage").click();
        if(Mapper.waitForElementToBeClickable(domFile,"adTitle")==true) {
            Mapper.find(domFile, "adTitle").click();

            Mapper.find(domFile, "adTitle").sendKeys(adTitle);
        }
        enterAdDescription(description);
        setEmaildId(emailid);
        setMobileNumber(mobilenumber);
        Mapper.find(domFile,"PostAdButtonMobile").click();
        if(Mapper.waitForElementToBeClickable(domFile,"continueButtonPostAd")==true) {
            Mapper.find(domFile, "continueButtonPostAd").click();
        }
    }

    public void MPostMobileAdBuy( String Brand, String Model, String OS, String Address, String Pincode, String adTitle,String mobilenumber,String description, String emailid) {
        clickBuyAd();
        Select brand=new Select(Mapper.find(domFile,"Brand"));
        brand.selectByValue(Brand);
        Select model=new Select(Mapper.find(domFile,"Model"));
        model.selectByValue(Model);
        Select os=new Select(Mapper.find(domFile,"OperatingSystem"));
        os.selectByValue(OS);
        /*Mapper.find(domFile,"Locality").click();
        if(Mapper.waitForElementToBeClickable(domFile,"secondLocalitySelect")==true) {
            Mapper.find(domFile, "secondLocalitySelect").click();
        }
        if(Mapper.waitForElementToBeClickable(domFile,"LocalityOkButton")==true) {
            Mapper.find(domFile, "LocalityOkButton").click();
        }

        if(Mapper.waitForElementToBeClickable(domFile,"Address")==true) {
            Mapper.find(domFile, "Address").click();
        }
        Mapper.find(domFile,"Address").sendKeys(Address);
        */
        /*Mapper.find(domFile,"pincode").click();
        Mapper.find(domFile,"pincode").sendKeys(Pincode);
        */
        Mapper.find(domFile,"NextButtonPostAdPage").click();
        if(Mapper.waitForElementToBeClickable(domFile,"adTitle")==true) {
            Mapper.find(domFile, "adTitle").click();
        }
        Mapper.find(domFile,"adTitle").sendKeys(adTitle);
        enterAdDescription(description);
        setEmaildId(emailid);
        setMobileNumber(mobilenumber);
        Mapper.find(domFile,"PostAdButtonMobile").click();
        if(Mapper.waitForElementToBeClickable(domFile,"continueButtonPostAd")==true) {
            Mapper.find(domFile, "continueButtonPostAd").click();
        }
    }
    public void clickBuyAd(){
        Mapper.find(domFile,"buyAdRadioButton").click();
    }

    public void enterAdDescription(String Description){
        Mapper.waitForElementToBeClickable(domFile,"PostAdDescription");
        Mapper.find(domFile,"PostAdDescription").click();
        Mapper.find(domFile,"PostAdDescription").sendKeys(Description);
    }

    public void setEmaildId(String emaild){
        if(Mapper.waitForElementToBeClickable(domFile,"AdEmail")==true) {
            Mapper.find(domFile, "AdEmail").click();
            Mapper.find(domFile, "AdEmail").sendKeys(emaild);
        }
    }

    public void setMobileNumber(String mobileNumber){
        if(Mapper.waitForElementToBeClickable(domFile,"AdMobile")==true) {
            Mapper.find(domFile, "AdMobile").click();
            Mapper.find(domFile, "AdMobile").sendKeys(mobileNumber);
        }
    }
}