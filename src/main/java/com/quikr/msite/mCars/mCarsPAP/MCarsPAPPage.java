package com.quikr.msite.mCars.mCarsPAP;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 12/11/15.
 */
public class MCarsPAPPage extends MPageBase {

    public MCarsPAPPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("mCars_PAP_DOM_FILE");

    public boolean isPAPPageavailable(){

        boolean flag=false;
        
        if(isElementPresent("addImage")){
            flag=true;
        }
        else {
            flag=false;
        }
        if(isElementPresent("NextButton")){
            flag=true;
        }
        else {
            flag=false;
        }

        return flag;
    }

    public void clickNextButton(){
        Mapper.waitForElementToBeClickable(domFile,"NextButton");
        Mapper.find(domFile,"NextButton").click();
    }

    public void clickNextButtonSetAttribute(){
        Mapper.waitForElementToBeClickable(domFile,"NextButtonSetAttribute");
        Mapper.find(domFile,"NextButtonSetAttribute").click();
    }

    public boolean validateSubCategory(String CarsandBikes, String MobilesandTablets, String ElectronicsandAppliances, String RealEstate, String HomeandLifestyle, String Jobs,String Services){
        boolean flag=false;
        List<WebElement> elements;
        Mapper.findAndReplace(domFile,"CategoryName",new  String[]{CarsandBikes}).click();
        elements=Mapper.findsAndReplace(domFile, "SubCategoriesofCategory",new String[]{CarsandBikes});
        if(elements.size()==6){
            flag=true;
        }
        else {
            flag=false;
        }
        Mapper.findAndReplace(domFile,"CategoryName",new  String[]{MobilesandTablets}).click();
        elements=Mapper.findsAndReplace(domFile, "SubCategoriesofCategory",new String[]{MobilesandTablets});
        if(elements.size()==3){
            flag=true;
        }
        else {
            flag=false;
        }
        Mapper.findAndReplace(domFile,"CategoryName",new  String[]{ElectronicsandAppliances}).click();
        elements=Mapper.findsAndReplace(domFile, "SubCategoriesofCategory",new String[]{ElectronicsandAppliances});
        if(elements.size()==15){
            flag=true;
        }
        else {
            flag=false;
        }

        Mapper.findAndReplace(domFile,"CategoryName",new  String[]{RealEstate}).click();
        elements=Mapper.findsAndReplace(domFile, "SubCategoriesofCategory",new String[]{RealEstate});
        if(elements.size()==13){
            flag=true;
        }
        else {
            flag=false;
        }
        Mapper.findAndReplace(domFile,"CategoryName",new  String[]{HomeandLifestyle}).click();
        elements=Mapper.findsAndReplace(domFile, "SubCategoriesofCategory",new String[]{HomeandLifestyle});
        if(elements.size()==24){
            flag=true;
        }
        else {
            flag=false;
        }

        Mapper.findAndReplace(domFile,"CategoryName",new  String[]{Jobs}).click();
        elements=Mapper.findsAndReplace(domFile, "SubCategoriesofCategory",new String[]{Jobs});
        if(elements.size()==4){
            flag=true;
        }
        else {
            flag=false;
        }

        Mapper.findAndReplace(domFile,"CategoryName",new  String[]{Services}).click();
        elements=Mapper.findsAndReplace(domFile, "SubCategoriesofCategory",new String[]{Services});
        if(elements.size()==41){
            flag=true;
        }
        else {
            flag=false;
        }
        return flag;
    }

    public ArrayList validationMsgSetAttributes(){
        ArrayList<String> messages=new ArrayList<>();
        List<WebElement> validationMessage;
        validationMessage=Mapper.finds(domFile,"ValidationMsgSetAttributes");
        for(int i=0;i<validationMessage.size();i++){
            messages.add(validationMessage.get(i).getText());
        }
        WebElement mycitymessage= Mapper.find(domFile,"ValidationMsgSetAttributesMyCity");
        messages.add(mycitymessage.getText());
        return messages;
    }



    public void MPostJobsAdSell(String Price, String Brand, String Model, String Variant, String Year, String Kms, String Color, String FuelType, String No_of_owners, String Insurancedate) {
        Mapper.waitForElementToBeClickable(domFile, "Price");
        Mapper.find(domFile, "Price").click();
        Mapper.find(domFile,"Price").sendKeys(Price);
        Select brand=new Select(Mapper.find(domFile,"Brand"));
        brand.selectByValue(Brand);
        Select year=new Select(Mapper.find(domFile,"Year"));
        year.selectByValue(Year);
        Select model=new Select(Mapper.find(domFile,"Model"));
        model.selectByValue(Model);
        Select variant=new Select(Mapper.find(domFile,"Variant"));
        variant.selectByValue(Variant);
        Mapper.find(domFile,"Kms").sendKeys(Kms);
        Select color=new Select(Mapper.find(domFile,"Color"));
        color.selectByValue(Color);
        Select fuelType=new Select(Mapper.find(domFile,"FuelType"));
        fuelType.selectByValue(FuelType);
        Select no_of_owners=new Select(Mapper.find(domFile,"No_of_owners"));
        no_of_owners.selectByValue(No_of_owners);
        //Mapper.find(domFile,"Insurance").click();
        //selectdate(Integer.parseInt(Insurancedate));
        Mapper.find(domFile,"Locality").click();
        Mapper.find(domFile,"secondLocalitySelect").click();
        Mapper.find(domFile,"NextButtonSetAttribute").click();
    }



    public void MPostAdBuy(String Price, String Brand, String Model, String Variant, String Year, String Color, String FuelType, String No_of_owners, String Insurancedate) {
        Mapper.waitForElementToBeClickable(domFile, "Price");
        Mapper.find(domFile, "Price").click();
        Mapper.find(domFile,"Price").sendKeys(Price);
        Select brand=new Select(Mapper.find(domFile,"Brand"));
        brand.selectByValue(Brand);
        Select model=new Select(Mapper.find(domFile,"Model"));
        model.selectByValue(Model);
        Select variant=new Select(Mapper.find(domFile,"Variant"));
        variant.selectByValue(Variant);
        Select year=new Select(Mapper.find(domFile,"Year"));
        year.selectByValue(Year);
        Select color=new Select(Mapper.find(domFile,"Color"));
        color.selectByValue(Color);
        Select fuelType=new Select(Mapper.find(domFile,"FuelType"));
        fuelType.selectByValue(FuelType);
        Select no_of_owners=new Select(Mapper.find(domFile,"No_of_owners"));
        no_of_owners.selectByValue(No_of_owners);
        Mapper.find(domFile,"Locality").click();
        Mapper.find(domFile,"secondLocalitySelect").click();
        Mapper.find(domFile,"LocalityOkButton").click();
        if (Mapper.waitForElementToBeClickable(domFile,"NextButtonSetAttribute")==true) {
            Mapper.find(domFile, "NextButtonSetAttribute").click();
        }
    }

    public boolean isAdDescriptionavailable(){
        boolean flag=false;
        if(isElementPresent("AdDescription")){
            flag=true;
        }
        else {
            flag=false;
        }

        if(isElementPresent("AdEmail")){
            flag=true;
        }
        else {
            flag=false;
        }

        if(isElementPresent("AdMobile")){
            flag=true;
        }
        else {
            flag=false;
        }

        if(isElementPresent("Privacy")){
            flag=true;
        }
        else {
            flag=false;
        }

        if(isElementPresent("PostAdButtonDescription")){
            flag=true;
        }
        else {
            flag=false;
        }
        return flag;
    }

    public ArrayList validationMsgsAdDescription(){
        List<WebElement> msgs=Mapper.finds(domFile, "ValidationMsgsAdDescription");

        ArrayList<String> msgstext=new ArrayList<>();

        for(int i=0;i<msgs.size();i++){
            msgstext.add(msgs.get(i).getText());
        }

        return msgstext;
    }

    public void clickPostAdButtonDescription(){
        Mapper.find(domFile,"PostAdButtonDescription").click();
    }

    public void enterAdDescriptionCars(String Description, String Email, String Mobile){
        Mapper.waitForElementToBeClickable(domFile, "AdDescription");
        Mapper.find(domFile,"AdDescription").click();
        Mapper.find(domFile,"AdDescription").sendKeys(Description);
        Mapper.find(domFile,"AdEmail").sendKeys(Email);
        try {
            if (Mapper.waitForElementToBeClickable(domFile, "Privacy") == true) {
                Mapper.find(domFile, "Privacy").click();
            }
        }
        catch (Exception e){
            logger.info("Might be Privacy CheckBox is not present.");
        }
        Mapper.find(domFile,"AdMobile").click();
        Mapper.find(domFile,"AdMobile").sendKeys(Mobile);
        Mapper.find(domFile, "PostAdButtonDescription").click();

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
            Mapper.find(domFile,"PostAdButtonDescription").click();
        }
    }


    public boolean isPostAdSuccessful(){

        boolean flag=false;
        if (isElementPresent("CongratulationsMsg")){
            flag=true;
        }
        else {
            flag=false;
        }

        if (isElementPresent("ThankYouMsg")){
            flag=true;
        }
        else {
            flag=false;
        }

        if (isElementPresent("ViewAdLink")){
            flag=true;
        }
        else {
            flag=false;
        }

        if (isElementPresent("ContactNumber")){
            flag=true;
        }
        else {
            flag=false;
        }

        if (isElementPresent("PremiumAdsMsg")){
            flag=true;
        }
        else {
            flag=false;
        }

        if (isElementPresent("SubmitButtonPremiumAd")){
            flag=true;
        }
        else {
            flag=false;
        }

        return flag;
    }

    public void validateViewAdLinkPostAd(){
        Mapper.find(domFile,"ViewAdLink").click();

    }

    public void clickDialerIconPostAdSuccessPage(){
        Mapper.find(domFile,"callNumberVerification").click();
    }

    public void clickBuyAd(){
        Mapper.find(domFile,"buyAdRadioButton").click();
    }

    public boolean isalertPresent(){
        return isAlertPresent();
    }
    public boolean isUpdateToPremiumAdAvailable(){

        boolean flag=false;
        Mapper.waitForElementToBeClickable(domFile, "SubmitButtonPremiumAd");
        Mapper.find(domFile,"SubmitButtonPremiumAd").click();
        if(isElementPresent("ChoosePremiumPlanText")){
            flag=true;
        }
        else
        {
            flag=false;
        }
        return  flag;
    }

    public ArrayList adPlanPrices(){
        ArrayList<String> adplanprices=new ArrayList<>();
        for(int i=1;i<=3;i++){
            WebElement adplans=Mapper.findAndReplace(domFile, "AdPlanPrices", new String[]{Integer.toString(i)});
            adplanprices.add(adplans.getText());
        }
        return adplanprices;
    }


    public ArrayList adPlanDurations(){
        ArrayList<String> adplansduration=new ArrayList<>();
        for(int i=1;i<=3;i++){
            WebElement adplans=Mapper.findAndReplace(domFile, "AdPlanDurations", new String[]{Integer.toString(i)});
            adplansduration.add(adplans.getText());
        }
        return adplansduration;
    }

    public void clickAdPlan(){
        Mapper.waitForElementToBeClickable(domFile,"499AdPlan");
        Mapper.find(domFile,"499AdPlan").click();
    }
    public void clickPaymentButton(){
        Mapper.waitForElementToBeClickable(domFile,"PaymentButton");
        Mapper.find(domFile,"PaymentButton").click();
    }

    public ArrayList paymentModes(){
        List<WebElement> paymentmodes;
        ArrayList<String> paymentmodesname=new ArrayList<>();

        paymentmodes=Mapper.finds(domFile,"PaymentModesText");
        for(int i=0;i<paymentmodes.size();i++)
        {
        paymentmodesname.add(paymentmodes.get(i).getText());
        }
        return paymentmodesname;
    }

    public void clickMobileBilling(){
        Mapper.waitForElementToBeClickable(domFile, "PaymentModeMobileBilling");
        Mapper.find(domFile,"PaymentModeMobileBilling").click();
    }


    public boolean isPayMobileBillavailable(){
        boolean flag=false;

         if(isElementPresent("PayViaMobileText")){
             flag=true;
         }
        else
             flag=false;
        if(isElementPresent("MobilePayNow")){
            flag=true;
        }
        else {
            flag=false;
        }
        return flag;
    }




    public void clickPaymentModeCreditCard(){
        Mapper.waitForElementToBeClickable(domFile, "PaymentModeCreditCard");
        Mapper.find(domFile,"PaymentModeCreditCard").click();
    }

    public void clickPaymentModeNetBanking(){
        Mapper.waitForElementToBeClickable(domFile,"PaymentModeNetBanking");
        Mapper.find(domFile,"PaymentModeNetBanking").click();
    }

    public void selectBank(String BankName){
        Select bank=new Select(Mapper.find(domFile, "NetBankingChooseBank"));
        bank.selectByVisibleText(BankName);
    }


    public boolean isCreditandDebitOptionsavailable(){
        boolean flag=false;

        if(isElementPresent("SelectCardType")){
            flag=true;
        }
        else
            flag=false;

        if(isElementPresent("NameofHolder")){
            flag=true;
        }
        else {
            flag=false;
        }

        if(isElementPresent("ExpiryDate")){
            flag=true;
        }
        else {
            flag=false;
        }

        if(isElementPresent("CVV")){
            flag=true;
        }
        else {
            flag=false;
        }

        if(isElementPresent("creditDebitPayNow")){
            flag=true;
        }
        else {
            flag=false;
        }

        return flag;
    }


    public void selectCardType(String CardType){
        Select cartype=new Select(Mapper.find(domFile,"SelectCardType"));
        cartype.selectByValue(CardType);
    }


    public void setCardDetails(String CardNumber,String HolderName,String ExpiryDate, String CVV){
        Mapper.waitForElementToBeClickable(domFile, "CardNumber");
        Mapper.find(domFile,"CardNumber").click();
        Mapper.find(domFile,"CardNumber").sendKeys(CardNumber);
        Mapper.waitForElementToBeClickable(domFile, "NameofHolder");
        Mapper.find(domFile,"NameofHolder").click();
        Mapper.find(domFile,"NameofHolder").sendKeys(HolderName);
        Mapper.waitForElementToBeClickable(domFile, "ExpiryDate");
        Mapper.find(domFile,"ExpiryDate").click();
        Mapper.find(domFile,"ExpiryDate").sendKeys(ExpiryDate);
        Mapper.waitForElementToBeClickable(domFile, "CVV");
        Mapper.find(domFile,"CVV").click();
        Mapper.find(domFile,"CVV").sendKeys(CVV);

    }

    public void clickPayNowMobileBilling(){
        Mapper.waitForElementToBeClickable(domFile, "MobilePayNow");
        Mapper.find(domFile,"MobilePayNow").click();
    }

    public void clickPayNowCreditDebitCard(){
        Mapper.waitForElementToBeClickable(domFile,"creditDebitPayNow");
        Mapper.find(domFile,"creditDebitPayNow").click();
    }


    public void clickPayNowNetBanking(){
        Mapper.waitForElementToBeClickable(domFile,"NetBankingPayNow");
        Mapper.find(domFile,"NetBankingPayNow").click();
    }


    public void clickContinueButtonMobilePayment(){
        Mapper.waitForElementToBeClickable(domFile,"ContinueButtonMobilePayment");
        Mapper.find(domFile,"ContinueButtonMobilePayment").click();
    }


    public boolean isMobilePaymentavailable(){
        return isElementPresent("Header");
    }


    public boolean isNetBakingPaymentPageavailable(){
        return isElementPresent("NetBankingICICIPaymentHeaderPage");
    }


    public void setMobileNumberandOperator(String number){
        Mapper.find(domFile,"MobileNumerTextBox").sendKeys(number);
        Mapper.find(domFile,"AirtelOperator").click();
    }

    public boolean isPaymentSuccessful(){
        boolean flag;

        if(isElementPresent("PaymentConfirmationPremiumAd")){
            flag=true;
        }
        else {
            flag=false;
        }

        if (isElementPresent("ConfirmPaymentButton")) {
        flag=true;
        }
        else {
            flag=false;
        }
        return flag;
        }

}
