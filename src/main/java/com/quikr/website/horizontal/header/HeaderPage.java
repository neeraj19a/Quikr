
package com.quikr.website.horizontal.header;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 28/7/15.
 */
public class HeaderPage extends PageBase {

    public HeaderPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("HEADER_DOM_FILE");


    public HeaderPage search(String query)
    {
        if(Mapper.waitForElementToBeVisible(domFile, "searchBox")==true) {
            Mapper.find(domFile, "searchBox").clear();
            Mapper.find(domFile, "searchBox").click();
            Mapper.find(domFile, "searchBox").sendKeys(query);
        }else{
            logger.info("SearchBox was not visible....");
        }
        WebElement element;
        if(Mapper.find(domFile, "search")!=null)
        {
            logger.info("Found the <search> button and clicking on it....");
            element = Mapper.find(domFile, "search");
        }
        else if(Mapper.find(domFile,"searchButtonNewUI")!=null)
        {
            logger.info("Found the <searchButtonNewUI> button and clicking on it....");
            element = Mapper.find(domFile,"searchButtonNewUI");
        }
        else
        {
            logger.info("Found the <searchOldButton> button and clicking on it....");
            element = Mapper.find(domFile,"searchOldButton");
        }

        element.click();
        return this;
    }

    public void clickSearchButton()
    {
        if(Mapper.waitForElementToBeClickable(domFile, "search")==true) {
            Mapper.find(domFile, "search").click();
        }
    }

    //Returns true if any of the populated search results have the searchTerm in it.
    public boolean checkSearchRelevance( String searchTerm)
    {
        int countOfMatches=0;
        boolean finalVal=false;
        search(searchTerm);
        List<WebElement> elms =  Mapper.finds(domFile, "snbSearchResultTitle");
        List strs = new ArrayList();

        for (int i=0; i<elms.size(); i++)
        {
            String titleAttr = elms.get(i).getAttribute("title").toString();
            strs.add(titleAttr);
        }
        for (int i=0;i<strs.size();i++)
        {
            if (strs.get(i).toString().contains(searchTerm))
            {
                finalVal=true;
            }
        }
        return finalVal;
    }

    //calculate Msp
    public  HeaderPage calculateMsp()
    {
        Mapper.waitForElementToBeClickable(domFile, "msp");
        Mapper.find(domFile,"msp").click();
        Mapper.find(domFile,"mspBuy").click();
        Select category=new Select(Mapper.find(domFile,"mspcategory"));
        category.selectByValue("60");
        Mapper.waitForElementToBeClickable(domFile, "mspSubCategory");
        Select subcategory=new Select(Mapper.find(domFile,"mspSubCategory"));
        subcategory.selectByVisibleText("Cars");
        Mapper.waitForElementToBeVisible(domFile, "mspBrand");
        Mapper.waitForElementToBeClickable(domFile, "mspBrand");
        Select brand=new Select(Mapper.find(domFile,"mspBrand"));
        brand.selectByVisibleText("Ford");
        Mapper.waitForElementToBeClickable(domFile, "mspModel");
        Select model=new Select(Mapper.find(domFile,"mspModel"));
        model.selectByVisibleText("Figo");
        Mapper.waitForElementToBeClickable(domFile, "mspYear");
        Select year=new Select(Mapper.find(domFile,"mspYear"));
        year.selectByVisibleText("2013");
        Mapper.waitForElementToBeClickable(domFile, "mspVariant");
        Select fuel =new Select(Mapper.find(domFile,"mspVariant"));
        fuel.selectByVisibleText("Diesel EXI");
        Mapper.find(domFile,"calculateMsp").click();

        return this;
    }


    public  HeaderPage setmspcategory(String category) {
        Mapper.waitForElementToBeClickable(domFile, "msp");
        Mapper.find(domFile, "msp").click();
        Mapper.find(domFile, "mspBuy").click();
        Select categoryselect=new Select(Mapper.find(domFile,"mspcategory"));
        categoryselect.selectByValue(category);
        return this;
    }

    public HeaderPage setmspSubCategory(String subcategory) {
        Mapper.waitForElementToBeClickable(domFile, "mspSubCategory");
        Select subcategoryselect = new Select(Mapper.find(domFile, "mspSubCategory"));
        subcategoryselect.selectByVisibleText(subcategory);
        return this;
    }
    public HeaderPage setmspBrand(String brandname) {
        Mapper.waitForElementToBeVisible(domFile, "mspBrand");
        Mapper.waitForElementToBeClickable(domFile, "mspBrand");
        Select brand = new Select(Mapper.find(domFile, "mspBrand"));
        brand.selectByVisibleText(brandname);
        return this;
    }
    public HeaderPage setmspModel(String model) {
        Mapper.waitForElementToBeClickable(domFile, "mspModel");
        Select modelselect = new Select(Mapper.find(domFile, "mspModel"));
        modelselect.selectByVisibleText(model);
        return this;
    }

    public HeaderPage setmspYear(String year) {
        Mapper.waitForElementToBeClickable(domFile, "mspYear");
        Select yearselect = new Select(Mapper.find(domFile, "mspYear"));
        yearselect.selectByVisibleText(year);
        return this;
    }


    public HeaderPage clickmspVariant(String variant)
    { Mapper.waitForElementToBeClickable(domFile, "mspVariant");
        Select variantselect =new Select(Mapper.find(domFile,"mspVariant"));
        variantselect.selectByVisibleText(variant);
        return this;
    }

    public HeaderPage setmspKms(String kms)
    { Mapper.waitForElementToBeClickable(domFile, "mspKms");
        Select variantselect =new Select(Mapper.find(domFile,"mspKms"));
        variantselect.selectByVisibleText(kms);
        Mapper.find(domFile,"calculateMsp").click();

        return this;
    }
    public boolean validatemsp() {
        Mapper.waitForElementToBeVisible(domFile, "mspbox");
        return isElementPresent("mspbox");

    }

    /**
     *  click on post free ad button
     */
    public void clickPostFreeAdButton()
    {
        Mapper.find(domFile, "postFreeAdButton").click();
    }

    //function to check autosuggest words
    public boolean Autosuggest(String query)
    {
        Mapper.find(domFile, "searchBox").sendKeys(query);
        Mapper.waitForElementToBeVisible(domFile, "AutoSuggestWord");
        List<WebElement> ListOfAutoSuggestWord = Mapper.finds(domFile,"AutoSuggestWord");
        List<String> AllElements=new ArrayList<>();
        for(WebElement e : ListOfAutoSuggestWord)
        {
            AllElements.add(e.getText());

        }

        for(int i=0;i<AllElements.size();i++)
        {
            if(!(AllElements.get(i).contains(query)))
            {
                System.out.println("Query:"+AllElements.get(i));
                return false;
            }
        }
        return true;
    }

    public void loginWindowResponsiveHP(String email, String password)
    {
        if(Mapper.find(domFile,"loginWindowResponsiveHP").isDisplayed()==false) {
            Mapper.find(domFile, "signIn").click();
        }

        else
        {
            Mapper.find(domFile, "emailId").sendKeys(email);
            Mapper.find(domFile, "password").click();
            Mapper.find(domFile, "password").sendKeys(password);
        }

        if (Mapper.waitForElementToBeVisible(domFile, "submitButtonNonEscrowCity", 10)==false)
        {
            Mapper.find(domFile,"submitButtonNewUI").click();
        }
        else
        {
            Mapper.find(domFile,"submitButtonNonEscrowCity").click();
        }

    }

    public boolean isRegisterbuttonWorking(String name,String email,String password, String mobilenumber){
        boolean flag=false;

        if(Mapper.find(domFile,"loginWindowResponsiveHP").isDisplayed()==false) {
            Mapper.find(domFile, "signInButtonResponsiveHP").click();
        }

        Mapper.waitForElementToBeClickable(domFile, "register");
        Mapper.find(domFile, "register").click();
        Mapper.find(domFile, "name_signup").click();
        Mapper.find(domFile, "name_signup").sendKeys(name);
        Mapper.find(domFile, "emailpap_signup").click();
        Mapper.find(domFile, "emailpap_signup").sendKeys(email);
        Mapper.find(domFile, "passwordpap_signup").click();
        Mapper.find(domFile, "passwordpap_signup").sendKeys(password);
        Mapper.find(domFile, "confirmPassword").sendKeys(password);
        //Mapper.find(domFile, "Phone").sendKeys(mobilenumber);
        Mapper.find(domFile, "ageCheckbox").click();
        if (Mapper.waitForElementToBeClickable(domFile, "CreateAccountButtonNonEscrowCity") == true) {
            Mapper.find(domFile, "CreateAccountButtonNonEscrowCity").click();
        }
        try {
            Thread.sleep(7000);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            if(Mapper.find(domFile, "mobileVerificationPopUponRegistrationResponsiveHP").isDisplayed()){
                flag=true;
            }
            else
            {
                return false;
            }


        return flag;
    }
    public boolean forgotPassword(String email)
    {
        boolean retVal = false;
        clickSigninLinkResponsive();
        Mapper.waitForElementToBeVisible(domFile, "forgotPassword");
        Mapper.find(domFile,"forgotPassword").click();
        Mapper.find(domFile,"enterEmail").sendKeys(email);
        Mapper.find(domFile,"sendEmail").click();
        switchTo().alert().accept();
        if (waitForPageToLoad("quikr.com")){
            retVal=true;
        }
        else{
            retVal=false;
        }
        return retVal;
    }

    public void forgotPasswordPAP(String email)
    {
        Mapper.find(domFile,"forgotPassword").click();
        Mapper.find(domFile, "enterEmail").sendKeys(email);
        Mapper.find(domFile, "sendEmail").click();

    }

    public void closeFreeDeliveryMsg(){
        Mapper.waitForElementToBeVisible(domFile, "FreeDeliveryMessageClose");
        Mapper.waitForElementToBeClickable(domFile, "FreeDeliveryMessageClose");
        Mapper.find(domFile, "FreeDeliveryMessageClose").click();
    }

    //function to select after login myquikr  options
    public void selectMyQuikrOption(String option)
    {
        Mapper.find(domFile,"signIn").click();
        Mapper.findChildElement(domFile, option, "myLoginDropDownOptions", "a", true).click();
    }

    public boolean verifyQuikrLogo()
    {
        Mapper.waitForElementToBeClickable(domFile, "quikrLogoHomePage");
        if (Mapper.find(domFile, "quikrLogoHomePage").isDisplayed())
        {
            return true;
        }
        else
            return false;
    }

    /**
     * click on quikr logo
     */
    public void clickOnQuikrLogo()
    {

        List<WebElement> valHomepage = Mapper.finds(domFile,"quikrLogoHomePage");
        List<WebElement> valMyQuikr = Mapper.finds(domFile,"quikrLogoMyQuikr");
        List<WebElement> valPapPage = Mapper.finds(domFile,"quikrLogoPapPage");
        List<WebElement> valNewUIPage = Mapper.finds(domFile,"quikrLogoNewUI");
        List<WebElement> valResponsiveUIPage = Mapper.finds(domFile,"responsiveNewUI");

        if (valHomepage.size()>0)
        {
            valHomepage.get(0).click();
            return;
        }
        else if (valMyQuikr.size()>0)
        {
            valMyQuikr.get(0).click();
            return;
        }
        else if (valPapPage.size()>0)
        {
            valPapPage.get(0).click();
            return;
        }

        else if (valNewUIPage.size()>0)
        {
            valNewUIPage.get(0).click();
            return;
        }
        else if (valResponsiveUIPage.size()>0)
        {
            valResponsiveUIPage.get(0).click();
            sleep(3000);
            return;
        }
        else
        {
            logger.info("Proper quikr logo not present to be clicked upon. Please check!");
        }

    }

    /**
     * Function for Logout from Quikr Account
     */
    public void logout()
    {
        Mapper.find(domFile, "signInOption").click();
        Mapper.find(domFile, "sigoutOption").click();
    }

    /**
     * Function for My Ads section
     */
    public void clickMyAds()
    {
        Mapper.waitForElementToBeVisible(domFile, "signInOption");
        Mapper.waitForElementToBeClickable(domFile, "signInOption");
        WebElement signIn=Mapper.find(domFile, "signInOption");
        signIn.click();
        WebElement myadsOption=Mapper.find(domFile, "myAdsOption");
        Mapper.waitForElementToBeVisible(domFile, "myAdsOption", 15);
        if(myadsOption!=null && myadsOption.isDisplayed())
        {
            myadsOption.click();
        }
        Mapper.waitForElementToBeInvisible(domFile, "myAdsOption", 15);

        /*if(myadsOption!=null)
        {
            myadsOption.click();
        }

        else if (!Mapper.find(domFile, "myAdsOption").isDisplayed()){
            signIn.click();
            myadsOption.click();

        }
*/
    }

    /**
     * Function for Clicking on My Cart
     */
    public void clickMyCart()
    {
        Mapper.find(domFile, "myCartOption").click();
    }

    public void clickQuikrCarsLogo()
    {
        Mapper.find(domFile,"QuikrCars").click();
    }

    public boolean checkDownloadAppLink()
    {
        if (Mapper.find(domFile,"download_app_link").isDisplayed())
        {
            return true;
        }
        else
            return false;
    }
    /*function to click on LanguageDropDown
    */
    public ArrayList<String> checklanguage()

    {
        ArrayList<String> list = new ArrayList<>();
        WebElement languagelist=Mapper.find(domFile, "language_selection");
        languagelist.click();
        for (int i=1;i<=8;i++)
        {
            String text=Mapper.findAndReplace(domFile, "language_options", new String[]{Integer.toString(i)}).getText();
            list.add(text);
        }
        return list;
    }

    public void clickSignInPAP()
    {
        Mapper.waitForElementToBeClickable(domFile, "signinPAP");
        Mapper.find(domFile,"signinPAP").click();
    }

    public void closeLoginPAP()
    {
        Mapper.find(domFile,"closeloginPAP").click();
    }

    public WebElement clickFacebooklogin(){
        WebElement facebook=Mapper.find(domFile,"facebooklogin");
        Mapper.waitForElementToBeClickable(domFile, "facebooklogin");
        return facebook;
    }

    public void enterfacebookdetails(String Email, String Password){
        Mapper.waitForElementToBeVisible(domFile, "facebookemail");
        WebElement email=Mapper.find(domFile,"facebookemail");
        email.click();
        email.sendKeys(Email);
        Mapper.waitForElementToBeVisible(domFile, "facebookpassword");
        WebElement password=Mapper.find(domFile,"facebookpassword");
        password.click();
        password.sendKeys(Password);

    }

    public void clickfacebookloginbutton()
    {
        WebElement fbloginbutton=Mapper.find(domFile,"facebookloginbutton");
        Mapper.waitForElementToBeClickable(domFile, "facebookloginbutton");
        fbloginbutton.click();
    }

    public void clicksignup(){
        Mapper.waitForElementToBeClickable(domFile, "signupfrompap");
        WebElement signup=Mapper.find(domFile,"signupfrompap");
        signup.click();
    }

    public void clickSignupLoginWindow()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"signupLinkLoginWindow")==true)
        {
            Mapper.find(domFile,"signupLinkLoginWindow").click();
        }
        else {
            logger.info("Signup link in login window was not visible. Please check!");
        }
    }

    public void enterNameSignupPAP(String name){
        Mapper.waitForElementToBeVisible(domFile, "name_signup");
        WebElement elm = Mapper.find(domFile, "name_signup");
        elm.sendKeys(name);
    }

    public void enterEmailSignUpPAP(String SignUpEmailid){
        Mapper.waitForElementToBeClickable(domFile, "emailpap_signup");
        WebElement emailsignup=Mapper.find(domFile,"emailpap_signup");
        emailsignup.click();
        emailsignup.sendKeys(SignUpEmailid);
    }

    public void enterPasswordSignUpPAP(String SignUpPassword){
        Mapper.waitForElementToBeClickable(domFile,"passwordpap_signup");
        WebElement passwordsignup=Mapper.find(domFile,"passwordpap_signup");
        passwordsignup.click();
        passwordsignup.sendKeys(SignUpPassword);
        Mapper.find(domFile,"confirmPasswordpap_signup").click();
        Mapper.find(domFile,"confirmPasswordpap_signup").sendKeys(SignUpPassword);
    }

    public void enterPhonenumberSignUpPAP(String SignUpPhone){
        Mapper.waitForElementToBeClickable(domFile,"phonepap_signup");
        WebElement phonesignup=Mapper.find(domFile,"phonepap_signup");
        phonesignup.click();
        phonesignup.sendKeys(SignUpPhone);
    }

    public void selectcitySignUpPAP( String cityname){
        Mapper.waitForElementToBeClickable(domFile, "citypap_signup");
        Select city=new Select(Mapper.find(domFile,"citypap_signup"));
        city.selectByValue(cityname);
    }
    public void clickSignUpAgeCheckBox(){
        Mapper.waitForElementToBeClickable(domFile, "ageconfirm_pap_signup");
        Mapper.find(domFile, "ageconfirm_pap_signup").click();
    }

    public void clickCreateAccount(){
        Mapper.waitForElementToBeClickable(domFile, "createaccount");
        List<WebElement> elm = Mapper.finds(domFile, "createaccount");
        elm.get(1).click();
    }

    public String verifyRegistrationText(){
        Mapper.waitForElementToBeClickable(domFile,"registration_text");
        String text=Mapper.find(domFile,"registration_text").getText();
        return text;
    }

    public void verifyredirectiontoPapOnLogin(String Email, String Password ){
        Mapper.waitForElementToBeClickable(domFile,"registration_header_close");
        Mapper.find(domFile,"registration_header_close").click();
        Mapper.waitForElementToBeClickable(domFile, "Email");
        Mapper.find(domFile,"Email").click();
        Mapper.find(domFile,"Email").sendKeys(Email);
        Mapper.find(domFile,"Password").click();
        Mapper.find(domFile,"Password").sendKeys(Password);
        Mapper.find(domFile, "SignIn").click();
    }

    public void clickMyQuikrX() {

       navigateTo().to("http://www.quikr.com/QuikrX/myorders?city=www");
    }

    public void clickMyDoorStepOffer(){

        Mapper.waitForElementToBeClickable(domFile, "emailIdDropDown");
        Mapper.find(domFile, "emailIdDropDown").click();
        Mapper.find(domFile,"myDoorStepLink").click();
    }

    public void clickTrackOrder(){
       Mapper.find(domFile,"trackOrder").click();
    }

    public String languagetext(){
         String text= Mapper.find(domFile,"searchquery_error").getAttribute("placeholder");
        return text;
    }

    public void clickSignIn(String city){
        navigateTo().to(getCurrentLocation() + "SignIn");
    }

    public void clickCart(){
        Mapper.find(domFile,"quikrXCart").click();
    }

    public String getCartCount(){
       return Mapper.find(domFile,"quirXCartCount").getText().trim();
    }


    public boolean validateMyAccountOptions() {
        boolean flag = false;
        List<WebElement> myaccountoptions = Mapper.finds(domFile, "myAccountOptionsResponsiveHP");
        if (myaccountoptions.size() == 8) {
            flag = true;
        } else {
            logger.info("All Options are not present Pls Check , Size of options returned is -->" + myaccountoptions.size());
            return false;
        }
        String urls[] = {"MyQuikr?action=activeads", "/Escrow/MyOffers/getSellersOfferForm", "MyQuikr?action=mychats", "MyQuikr?action=alertsdetails", "myorders?", "MyQuikr", "recommended-section"};
        for (int i = 1; i <= 7; i++) {
            logger.info("Getting into the loop....");
            if (Mapper.waitForElementToBeVisible(domFile,"myAccountDropDownResponsiveHP")==true){
                Mapper.find(domFile, "myAccountDropDownResponsiveHP").click();
                logger.info("Clicked on <myAccountDropDownResponsiveHP>....");
                WebElement elm = Mapper.findAndReplace(domFile, "myAccountOptionNumberResponsiveHP", new String[]{Integer.toString(i)});
                elm.click();
                logger.info("Clicked on :: "+i+"th link....");
                if (Mapper.waitForElementToBeVisible(domFile, "quikrLogoMyQuikr") == true) {
                    if (getCurrentLocation().contains(urls[i - 1])) {
                        navigateTo().back();
                        flag = true;
                    } else {
                        logger.info("Unable to load option number -->" + i);
                        return false;
                    }
                }
            }else {
                logger.info("<myAccountDropDownResponsiveHP> is not visible.....");
            }
        }
        return flag;
    }

    public boolean isMobileVerificationPopUpAppearonRegisteration(){
        boolean flag=false;
        if(Mapper.waitForElementToBeVisible(domFile, "mobileVerificationPopUponRegistrationResponsiveHP")==true) {
            if(Mapper.find(domFile, "mobileVerificationPopUponRegistrationResponsiveHP").isDisplayed()==true){
                flag =true;
            }
            else {
                flag=false;
            }
        }
        return flag;
    }

    public void closeResponsiveLoginPopUp(){
        try {
            if (Mapper.waitForElementToBeClickable(domFile, "closeLoginPopUpResponsiveHP") == true) {
                Mapper.find(domFile, "closeLoginPopUpResponsiveHP").click();
            }
        }
        catch (Exception e){
            logger.info("Might be Login Pop Up Was not displayed");
        }
    }

    public boolean validateSignUpErrorResponsiveHP(){
        return Mapper.find(domFile,"signUpError").getText().contains("User already exist with mail id");
    }


    public void clickSigninLinkResponsive()
    {
        if (Mapper.waitForElementToBeClickable(domFile, "SigninLinkResponsive")==true)
        {
            Mapper.find(domFile,"SigninLinkResponsive").click();
            if(!Mapper.find(domFile,"responsiveLoginButton").isDisplayed()){
                Mapper.find(domFile,"SigninLinkResponsive").click();
            }else{
                logger.info("Sign in link was clicked in the first attempt itself.");
            }

        } else {
            logger.info("Probably the responsive signin link is not available. Please check!");
        }
    }

    public void clickSigninLinkNonResponsive()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"SigninLinkNonResponsive")==true)
        {
            Mapper.find(domFile,"SigninLinkNonResponsive").click();
        }
        else
        {
            logger.info("Probably the non-responsive signin link is not available. Please check!");
        }
    }


    public void nonResponsiveLogin(String email, String password)
    {
        Mapper.find(domFile,"non-ResponsiveEmailField").sendKeys(email);
        Mapper.find(domFile,"non-ResponsivePasswordField").sendKeys(password);
        Mapper.waitForElementToBeClickable(domFile,"non-ResponsiveLoginButton");
        Mapper.find(domFile,"non-ResponsiveLoginButton").click();
        Mapper.waitForElementToBeVisible(domFile, "non-ResponsiveLoginButton");
    }

    public void closeCityPopUp()
    {
        Mapper.find(domFile,"closeCityPopUp").click();
    }

    public boolean ifResponsivePage()
    {
        boolean retVal = false;
        if (Mapper.find(domFile, "responsiveHeader")!=null)
        {
            retVal = true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public void responsiveLogin(String email, String password)
    {
        Mapper.find(domFile,"responsiveEmailField").sendKeys(email);
        Mapper.find(domFile,"responsivePasswordField").sendKeys(password);
        Mapper.find(domFile,"responsiveLoginButton").click();
    }

    /*
    This method handles usual non-responsive login scenario(with or without city selection).
    @param : In case of login is required with city, then param = "<city>",
             if no city selection is required then param = "noCityRequired"
             if its required to login from the any page other than the landing page then param = "randomPageLoginResponsive"/"randomPageLoginNonResponsive"
     */
    public boolean letsLogin(String LoginParameter,String city, String email, String password)
    {

        boolean isResponsivePage = false;
        boolean retVal = false;
        /*
        For case where login is required for any responsive page in quikr.com
         */
        if (LoginParameter.equals("randomPageLoginResponsive")==true)
        {
            logger.info("Opting for Random responsive page login.");
            clickSigninLinkResponsive();
            responsiveLogin(email, password);
        }
        /*
        For case where login is required for any non-responsive page in quikr.com
         */
        else if (LoginParameter.equals("randomPageLoginNonResponsive")==true)
        {
            logger.info("Opting for Random non-responsive page login.");
            clickSigninLinkNonResponsive();
            nonResponsiveLogin(email, password);
        }
        /*
        For case where login is required without selecting any city
         */
        else if (LoginParameter.equals("noCityRequired")==true)
        {
            logger.info("Opting for login with no city selection.");
            //closeCityPopUp();
            clickSigninLinkNonResponsive();
            nonResponsiveLogin(email, password);
        }
        /*
        For cases where city given and then login is required based on whether its responsive or non-responsive
         */
        else
        {
            logger.info("Opting for login with city selection. Next checking if responsive page or not.");
            selectCity(city);
            isResponsivePage=ifResponsivePage();
            if (isResponsivePage==true)
            {
                logger.info("Opting for responsive page login, <isResponsivePage> = "+isResponsivePage);
                clickSigninLinkResponsive();
                responsiveLogin(email, password);
            }
            else
            {
                logger.info("Opting for non-responsive page login, <isResponsivePage> = "+isResponsivePage);
                clickSigninLinkNonResponsive();
                nonResponsiveLogin(email, password);
            }
        }

       if(waitForPageToLoad("MyQuikr")==true)
        {
            retVal= true;
        }
        else
        {
            retVal= false;
        }
        logger.info("Retval has the value as :: "+retVal);
        return retVal;
    }

    public void clickCitySelectDropDownBox(){
        if (Mapper.waitForElementToBeVisible(domFile,"citySelectDropDownBox")==true) {
            Mapper.find(domFile, "citySelectDropDownBox").click();
        }else {
            logger.info("City selection dropdown is not visible. Please check!");
        }
        sleep(3000);
    }

    public WebElement isCityPresentInDropDownList(String city) {
        sleep(3000);
        WebElement elmRequired = null;
        if (Mapper.find(domFile, "cityLinksFromDropdown") != null) {
            List<WebElement> elms = Mapper.finds(domFile, "cityLinksFromDropdown");
            for (int i = 0; i < elms.size(); i++) {
                String s = elms.get(i).getText();
                logger.info(s);
                if (s.toLowerCase().equals(city.toLowerCase())){
                    logger.info("Required city found in the dropdown....");
                    elmRequired = elms.get(i);
                    return elmRequired;
                } else {
                    logger.info("Required city not found in dropdown yet....");
                    elmRequired =  null;
                }
            }
        }else{
            logger.info("<cityLinksFromDropdown> is null....");
        }
        return elmRequired;
    }

    public void inputCity(String city){
        if (Mapper.find(domFile,"citySelectionInputText")!=null){
            Mapper.find(domFile,"citySelectionInputText").click();
            Mapper.find(domFile,"citySelectionInputText").sendKeys(city);
            try{
                Thread.sleep(4000);
            }catch(Exception e){}
            Mapper.find(domFile,"citySuggested").click();
        }else{
            logger.info("Textarea for city is not visible....");
        }
        waitForPageToLoad(city);
        zoomOut(2);
    }

    public void selectACity(String city){
        clickCitySelectDropDownBox();
        String allIndia = "All India";
        if (city.toLowerCase().equals(allIndia.toLowerCase())) {
            Mapper.find(domFile, "AlIndiaLinkFromCityDropdown").click();
        }else {
            WebElement elm = isCityPresentInDropDownList(city);
            if (elm != null) {
                //logger.info("City required was already present in the dropdown list. Clicking on it....");
                sleep(3000);
                elm.click();
                waitForPageToLoad(city);
                zoomOut(2);
            } else {
                logger.info("Putting in the city name as value in the text area....");
                sleep(3000);
                inputCity(city);
            }
        }
    }

    public boolean checkMobileAndVerificationPage(){
        boolean retval = false;
        Mapper.waitForElementToBeVisible(domFile,"mobileAndEmailVerificationLabel");
        if (Mapper.find(domFile,"mobileAndEmailVerificationLabel").getText().contains("Verification")){
            retval=true;
        }else {
            return false;
        }
        return retval;
    }

}
