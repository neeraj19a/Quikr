package com.quikr.website.electronics.electronicsPap;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by rohanbajaj on 28/07/15.
 */
public class ElectronicsPapPage extends PageBase {

    public ElectronicsPapPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("Electronics_PAP_DOM_FILE");

    public boolean isCategoryListVisible() {
        return isElementPresent("menuList");
    }

    /**
     * Click on post AD to open PAP
     * swattantra singh
     */
    public ElectronicsPapPage openPap() {
        if (isElementPresent("postAd"))
            Mapper.find(domFile, "postAd").click();
        else if(isElementPresent("PostAdIconLogin"))
           Mapper.find(domFile,"PostAdIconLogin").click();

        else if(isElementPresent("postAdHomePage"))
            Mapper.find(domFile,"postAdHomePage").click();
        //Mapper.find(domFile, "postAd").click();
        return this;
    }

    /**
     * @param category swatanatra singh
     */
    public ElectronicsPapPage selectCategory(String category) {
        Mapper.waitForElementToBeClickable(domFile, category);
        Mapper.findChildElement(domFile, category, "category", "div", true).click();
        return this;
    }


    /**
     * @param subcategory swatanatra singh
     */
    public ElectronicsPapPage selectSubCategory(String subcategory) {
        Mapper.waitForElementToBeVisible(domFile, subcategory);
        Mapper.waitForElementToBeClickable(domFile, subcategory);
        Mapper.findChildElement(domFile, subcategory, "subcategory", "a", true).click();
        return this;
    }

    /**
     * select ad type as want
     * click on post Ad button
     * swatanatra singh
     */

    public ElectronicsPapPage postAdAsWant() {
        Mapper.find(domFile, "adtypeWant").click();
        return this;

    }

    /**
     * provide ad title
     *
     * @param title swatanatra singh
     */
    public ElectronicsPapPage postAdTitle(String title) {
        Mapper.waitForElementToBeVisible(domFile, "title");
        Mapper.find(domFile, "title").sendKeys(title);
        return this;
    }


    public ElectronicsPapPage clickpostAdTitle() {
        Mapper.waitForElementToBeVisible(domFile, "title");
        Mapper.find(domFile, "title").click();
        return this;
    }

    public boolean ispostAdTitleEnabled() {
        WebElement element=Mapper.find(domFile, "title");
        String attribute=element.getAttribute("readonly");
        System.out.println("Here is attribute" + attribute);
        if(attribute.equals("true")) {
            return true;
        }

        else
            return false;

    }

    /**
     * provide Price
     *
     * @param price swatanatra singh
     */
    public ElectronicsPapPage PostAdPrice(String price) {
        Mapper.find(domFile, "Price").sendKeys(price);
        return this;
    }

    /**
     * Provide Ad descriptin
     *
     * @param Desc swatanatra singh
     */

    public ElectronicsPapPage AdDesceription(String Desc) {
        Mapper.find(domFile, "adDescription").sendKeys(Desc);
        return this;
    }

    public ElectronicsPapPage clickAdDesceription() {
        Mapper.find(domFile, "adDescription").click();
        return this;
    }

    public boolean isAdDesceriptionEnabled() {

        WebElement element=Mapper.find(domFile, "adDescription");
        String attribute=element.getAttribute("readonly");
        System.out.println("Here is attribute" + attribute);
        if(attribute.equals("true")) {
            return true;
        }

        else
            return false;


    }


    /**
     * Provide personel info
     *
     * @param name,email,number swatanatra singh
     */
    public ElectronicsPapPage personelinfo(String name, String email, String number) {
//        Mapper.find(domFile,"individual").click();
        Mapper.find(domFile, "name").sendKeys(name);
        Mapper.find(domFile, "email").sendKeys(email);
        Mapper.find(domFile, "number").sendKeys(number);
        return this;
    }

    public ElectronicsPapPage setArea(String area) {
        Mapper.find(domFile, "area").click();
        Mapper.find(domFile, "area").sendKeys(area);
        return this;
    }

    /**
     * submit ad button
     * swatanatra singh
     */
    public ElectronicsPapPage SubmitAd() {
        Mapper.find(domFile, "submitAd").click();
        return this;
    }


    public boolean isSubmitAdEnabled() {
        WebElement element=Mapper.find(domFile, "submitAd");
        String attribute=element.getAttribute("disabled");
        System.out.println("Here is attribute" + attribute);
        if(attribute.equals("true")) {
            return true;
        }

        else
            return false;

    }


    /**
     * @param sal swatanatra singh
     */
    public ElectronicsPapPage selectsalary(String sal) {
        Select salary = new Select(Mapper.find(domFile, "jobsSalary"));
        salary.selectByVisibleText(sal);
        return this;
    }

    public ElectronicsPapPage selectlocality() {
        Mapper.find(domFile, "locality").click();
        Mapper.find(domFile, "selectlocality").click();
        return this;
    }

    /**
     * @param rel swatanatra singh
     */
    public ElectronicsPapPage selectRelocation(String rel) {
        Select relocate = new Select(Mapper.find(domFile, "jobsrelocate"));
        relocate.selectByVisibleText(rel);
        return this;
    }

    /**
     * validate ad
     * swatanatra singh
     */
    public boolean validateAd() {
        if (isElementPresent("validateAd"))
            return true;
        return false;
    }

    /**
     * @param comp swatanatra singh
     */
    public ElectronicsPapPage selectCompensation(String comp) {
        Select salary = new Select(Mapper.find(domFile, "jobsCompensation"));
        salary.selectByVisibleText(comp);
        return this;
    }

    /**
     * @param compny swatanatra singh
     */
    public ElectronicsPapPage selectCompany(String compny) {
        Mapper.find(domFile, "company").sendKeys(compny);
        return this;
    }

    public ElectronicsPapPage sendName(String name) {
        Mapper.find(domFile, "name").sendKeys(name);
        return this;
    }

    /**
     * set email
     *
     * @param email
     * @return
     */
    public ElectronicsPapPage setEmail(String email) {
        Mapper.find(domFile, "email").sendKeys(email);
        return this;
    }

    /**
     * click on covert to premium link
     *
     * @return
     */
    public ElectronicsPapPage clickConvertToPremiumLink() {
        Mapper.find(domFile, "convertToPremium").click();
        return this;
    }

    /**
     * select premium ad package
     *
     * @param adPackage
     * @return
     */
    public ElectronicsPapPage selectPremiumAdPackage(QuikrEnums adPackage) {
        switch (adPackage) {
            case PREMIUM_TOP_AD:
                Mapper.find(domFile, "premiumTopAd").click();
                break;
            case PREMIUM_URGENT_AD:
                Mapper.find(domFile, "premiumUrgentAd").click();
                break;
            case PREMIUM_BOTH_AD:
                Mapper.waitForElementToBeClickable(domFile, "premiumBothAd");
                Mapper.find(domFile, "premiumBothAd").click();
        }

        return this;
    }

    /**
     * select payment method for premium ad
     *
     * @return
     */
    public ElectronicsPapPage selectPaymentMethodForPremiumAd(QuikrEnums paymentMethod) {
        switch (paymentMethod) {
            case PREMIUM_PAYMENT_ONLINE:
                Mapper.findChildElement(domFile, "o", "paymentsMethod", "input", "value").click();
                break;
            case PREMIUM_PAYMENT_CHEQUE:
                Mapper.findChildElement(domFile, "c", "paymentsMethod", "input", "value").click();
                break;
            case PREMIUM_PAYMENT_ADCREDITS:
                Mapper.findChildElement(domFile, "v", "paymentsMethod", "input", "value").click();
                break;
            case PREMIUM_PAYMENT_QUIKR_POINT:
                Mapper.findChildElement(domFile, "rps", "paymentsMethod", "input", "value").click();
                break;
            case PREMIUM_PAYMENT_MOBILE:
                Mapper.findChildElement(domFile, "ipay", "paymentsMethod", "input", "value").click();
                break;
        }

        return this;
    }

    public boolean ispremiumAdRadioButtonavailable(){
        List<WebElement> premiumradioButton=Mapper.finds(domFile,"premiumAdRadioButtons");
        if(premiumradioButton.size()==2){
            return true;
        }
        else
            return false;
    }
    /**
     * click on post premium ad buttom
     */
    public ElectronicsPapPage clickPostPremiumAdButton() {

        Mapper.waitForElementToBeVisible(domFile, "postPremiumAdButton");
        if (Mapper.waitForElementToBeClickable(domFile, "postPremiumAdButton")==true) {
            Mapper.find(domFile, "postPremiumAdButton").click();
        }
        return this;
    }

    public boolean validatePaymentPageavailable(){
        if(Mapper.waitForElementToBeClickable(domFile,"paymentFormPremiumAd")==true){
        return true;
        }

        else
            return false;
    }
    /**
     * validate premium ad success when submitted with cheque payment
     *
     * @return
     */
    public boolean validatePremiumAdWithChequeSuccess() {

        boolean success=isElementPresent("premiumAdSuccessWithCheque");
        boolean sorrymessage=isElementPresent("SorryMessage");
        if(success ||sorrymessage){
            return true;
        }
        else
        return false;
    }

    /**
     * function to select non-default ad type
     */
    public void selectNonDefaultAdType() {
        Mapper.waitForElementToBeVisible(domFile, "adTypes");
        List<WebElement> webElementList = Mapper.finds(domFile, "adTypes");

        if (webElementList.get(0).isSelected()) {
            webElementList.get(1).click();
        } else {
            webElementList.get(0).click();
        }
    }

    /**
     * Furniture type for Home - Office Furniture subcategory
     */
    public ElectronicsPapPage selectFurnitureType() {
        Mapper.find(domFile, "furnitureTypeOption").click();
        Mapper.find(domFile, "furnitureType").click();
        return this;
    }

    /**
     * Product condition
     */
    public ElectronicsPapPage selectProductCondition() {
        Mapper.find(domFile, "productCondition").click();
        return this;
    }

    /**
     * You are selection
     */
    public ElectronicsPapPage selectYouAre() {
        Mapper.find(domFile, "youAre").click();
        return this;
    }

    public ElectronicsPapPage setAddress(String address) {
        Mapper.find(domFile, "address").click();
        Mapper.find(domFile, "address").sendKeys(address);
        return this;
    }

    public ElectronicsPapPage setMobileNumber(String number) {
        WebElement mobileNumber=Mapper.find(domFile, "number");
        try {


            if (mobileNumber.getAttribute("value").isEmpty()) ;
            {
                Mapper.find(domFile, "number").click();
                Mapper.find(domFile, "number").sendKeys(number);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public ElectronicsPapPage setPincode(String pincode) {
        Mapper.find(domFile, "pincode").click();
        Mapper.find(domFile, "pincode").sendKeys(pincode);
        return this;
    }

    /**
     * Post Ad button click operation
     */
    public ElectronicsPapPage clickPostAdButton() {
        Mapper.waitForElementToBeVisible(domFile, "postAdButton");
        Mapper.waitForElementToBeClickable(domFile, "postAdButton");
        WebElement postbutton=Mapper.find(domFile, "postAdButton");
        postbutton.click();
        return this;
    }

    /**
     * Reverse Price
     */
    public ElectronicsPapPage PostAdReversePrice(String reverseprice) {
        Mapper.find(domFile, "reversePrice").sendKeys(reverseprice);
        return this;
    }

    public ElectronicsPapPage selectBrandMobile(String brandName) {
        Mapper.find(domFile, "brandNameDropdownMobile").click();
        Mapper.waitForElementToBeVisible(domFile, "brandNameMobileDropdownValueOne");
        Mapper.findChildElement(domFile, brandName, "brandNameMobileDropdownValueOne", "span", false).click();
        return this;
    }

    public ElectronicsPapPage selectSubCategoryMobile() {
        Mapper.waitForElementToBeVisible(domFile, "mobileSubCategory");
        Mapper.find(domFile, "mobileSubCategory").click();
        return this;
    }

    public boolean CheckIfAdPosted() {
       // Mapper.waitForElementToBeVisible(domFile, "adPostConfirmationText");
        boolean a=isElementPresent("adPostConfirmationText");
        boolean b=getCurrentLocation().contains("citruspayAd");
        boolean c=getCurrentLocation().contains("PostAd/?succeed");
        if(a||b||c){
        return true;
        }
        else
            return false;
    }


    public String converttopremiumtext() {
        Mapper.waitForElementToBeVisible(domFile, "converttopremiumheader");
        String text = Mapper.find(domFile, "converttopremiumheader").getText();
        return text;
    }

    public String editadtext() {
        Mapper.waitForElementToBeVisible(domFile, "editadheader");
        String text = Mapper.find(domFile, "editadheader").getText();
        return text;
    }

    public String deleteadtext() {

        String text = Mapper.find(domFile, "deleteadheader").getText();
        return text;
    }

    public String gettextPostAd() {

        Mapper.waitForElementToBeVisible(domFile, "PostAdheading");
        String text = Mapper.find(domFile, "PostAdheading").getText();
        return text;
    }

    public boolean checkLanguageTranslationPap(String HeaderText, String SubHeaderText, String TitleForAdtext) {
        boolean FinalVal = false;
        String PostAdHeadingText = Mapper.find(domFile, "PostAdheading").getText();
        String PostAdSubHeadingText = Mapper.find(domFile, "PostAdSubHeading").getText();
        //String TitleForYourAdText = Mapper.find(domFile, "titleForYourAdText").getText();

        if (PostAdHeadingText.equals(HeaderText) && PostAdSubHeadingText.equals(SubHeaderText)) {
        //if (PostAdHeadingText.equals(HeaderText) && PostAdSubHeadingText.equals(SubHeaderText) && TitleForYourAdText.equals(TitleForAdtext)) {
            FinalVal = true;
        } else {
            FinalVal = false;
        }
        return FinalVal;
    }

    public void clickLanguageLinkPap() {
        Mapper.waitForElementToBeVisible(domFile, "LanguageLink");
        Mapper.find(domFile, "LanguageLink").click();
    }

    public boolean verifyDownloadAppPopUp() {
        if (Mapper.find(domFile, "downloadAppPopUP").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean validatInvalidNumberErrorMsgonAppPopUp(String invalidnumber) {
        Mapper.find(domFile, "mobilenumber_appPopUp").sendKeys(invalidnumber);
        Mapper.find(domFile, "appPopUp_sendSMS").click();
        if (Mapper.find(domFile, "invalidmobilenumber_errormsg_appPopUp").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void closeDownloadAppPopUp() {
        Mapper.waitForElementToBeClickable(domFile, "downloadAppPopUpClose");
        Mapper.find(domFile, "downloadAppPopUpClose").click();
    }

    public boolean isVirtualKeyboardPapavailable() {
        Mapper.waitForElementToBeVisible(domFile, "title");
        Mapper.waitForElementToBeClickable(domFile, "title");
        WebElement title = Mapper.find(domFile, "title");
        title.click();
        if (Mapper.find(domFile,"virtualVernacKeyboard").isDisplayed()==true) {
            return true;
        } else
            navigateTo().refresh();
        title.click();
        
        return Mapper.waitForElementToBeVisible(domFile, "virtualVernacKeyboard", 15);
    }


    public boolean istitleBlank() {
        WebElement title=Mapper.find(domFile, "title");
        if(title.getText().length()==0){
            return true;
        }
        else
        return false;
    }

    public boolean isAdDescriptionBlank() {
        WebElement title=Mapper.find(domFile, "adDescription");
        if(title.getText().length()==0){
            return true;
        }
        else
            return false;
    }
    public void setMobileKeywordinHindiPAPVernacKeyboard()
    {
        Mapper.find(domFile,"m_in_Hindi").click();
        Mapper.find(domFile,"O_in_Hindi").click();
        Mapper.find(domFile,"B_in_Hindi").click();
        Mapper.find(domFile,"A_in_Hindi").click();
        Mapper.find(domFile,"ShiftVernacKeyboard").click();
        Mapper.find(domFile,"I_in_Hindi").click();
        Mapper.find(domFile,"ShiftVernacKeyboard").click();
        Mapper.find(domFile,"ShiftVernacKeyboard").click();
        Mapper.waitForElementToBeClickable(domFile,"L_in_Hindi");
        Mapper.find(domFile,"L_in_Hindi").click();
    }


    public void setMobileKeywordRegionalLanguagePAPVernacKeyboard()
    {
        Mapper.find(domFile,"RegionalKeyboardInput1").click();
        Mapper.find(domFile,"RegionalKeyboardInput2").click();
        Mapper.find(domFile,"RegionalKeyboardInput3").click();
        Mapper.find(domFile,"RegionalKeyboardInput4").click();
        Mapper.find(domFile,"RegionalKeyboardInput5").click();
    }

}

