package com.quikr.msite.mHorizontal.mHome;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 31/8/15.
 */
public class MHomePage extends MPageBase
{
    public MHomePage(AppiumDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("mHOMEPAGE_DOM_FILE");

    public void clickCloseMostTrustedBrandPopUp(){
        if(isElementPresent("CloseMostTrustedBrandPopUp")){
            try {
                Mapper.waitForElementToBeClickable(domFile,"CloseMostTrustedBrandPopUp");
                Mapper.find(domFile,"CloseMostTrustedBrandPopUp").click();
            }
            catch (Exception e){}
        }
    }

    public void closeLanguagePopUp(){
        Mapper.waitForElementToBeVisible(domFile,"languagePopUp",7);
        Mapper.waitForElementToBeVisible(domFile,"languagePopUpHidden",3);
    }

    public boolean isSelectCityavailable(){
        return isElementPresent("selectCity");
    }
    public void selectCity(String name)
    {
        Mapper.find(domFile,"selectCity").click();
        Mapper.findAndReplace(domFile, "selectCityName", new String[]{name}).click();
        waitForPageToLoad(name.toLowerCase());
//        try {
//            Mapper.find(domFile,"menuIcon").click();
//        }catch (Exception e){
//
//        }

    }

    public boolean isSearchavailable(){
        return isElementPresent("searchButton");
    }

    public void search(){
        Mapper.waitForElementToBeClickable(domFile, "searchButton");
        Mapper.find(domFile,"searchButton").click();
    }

    public boolean isPostAdButtonavailable(){
        return isElementPresent("PostAdButton");
    }

    public String postAdButtonText(){
        return Mapper.find(domFile,"PostAdButton").getText();
    }
    public boolean isQuikrLogoavailable(){
        return isElementPresent("QuikrLogo");
    }

    public boolean numberofCategories(){
        clickShowMoreCategory();
        List<WebElement> categories=Mapper.finds(domFile, "Categories");
        if(categories.size()==19){
            return true;
        }
        else {
            return false;
        }
    }
    public void clickPostAdButtonHomePage(){
        if(Mapper.waitForElementToBeVisible(domFile, "PostAdButton")==true) {
            Mapper.find(domFile, "PostAdButton").click();
        }
        else if(Mapper.waitForElementToBeVisible(domFile, "PostAdButtonNewCities")==true){
            Mapper.find(domFile,"PostAdButtonNewCities").click();
        }
    }

    public void clickCarsandBikes(){
        Mapper.waitForElementToBeClickable(domFile, "CarsandBikes");
        Mapper.find(domFile,"CarsandBikes").click();
    }

    public void clickMobilesandTablets(){
        Mapper.waitForElementToBeClickable(domFile, "MobilesandTablets");
        Mapper.find(domFile,"MobilesandTablets").click();
    }

    public void clickElectronicsandAppliances(){
        Mapper.waitForElementToBeClickable(domFile, "ElectronicsandAppliances");
        Mapper.find(domFile,"ElectronicsandAppliances").click();
    }

    public void clickRealEstate(){
        Mapper.waitForElementToBeClickable(domFile, "RealEstate");
        Mapper.find(domFile,"RealEstate").click();
    }

    public void clickHomeandLifeStyle(){
        Mapper.waitForElementToBeClickable(domFile, "HomeandLifeStyle");
        Mapper.find(domFile,"HomeandLifeStyle").click();
    }

    public void clickJobs(){
        Mapper.waitForElementToBeClickable(domFile, "Jobs");
        Mapper.find(domFile,"Jobs").click();
    }

    public void clickEntertainment(){
        Mapper.waitForElementToBeClickable(domFile, "Entertainment");
        Mapper.find(domFile,"Entertainment").click();
    }

    public void clickServices(){
        Mapper.waitForElementToBeClickable(domFile, "Services");
        Mapper.find(domFile,"Services").click();
    }

    public void clickPetsandPetCare(){
        Mapper.waitForElementToBeClickable(domFile, "PetsandPetCare");
        Mapper.find(domFile,"PetsandPetCare").click();
    }

    public boolean isShowMoreCategoryAvailable(){
        return isElementPresent("ShowMoreCategoryDropIcon");
    }
    public void clickShowMoreCategory(){
        Mapper.waitForElementToBeClickable(domFile,"ShowMoreCategoryDropIcon");
        Mapper.find(domFile, "ShowMoreCategoryDropIcon").click();
    }

    public void clickEducationandLearning(){
        Mapper.waitForElementToBeClickable(domFile, "EducationandLearning");
        Mapper.find(domFile,"EducationandLearning").click();
    }

    public void clickCommunityandEvents(){
        Mapper.waitForElementToBeClickable(domFile, "CommunityandEvents");
        Mapper.find(domFile,"CommunityandEvents").click();
    }

    public void clickMatrimonial(){
        Mapper.waitForElementToBeClickable(domFile, "Matrimonial");
        Mapper.find(domFile,"Matrimonial").click();
    }

    public boolean isMSPavailable(){
        clickPlusIcon();
        return isElementPresent("Msp");
    }

    public void clickHamburgerMenuResponsiveHP(){
        if(Mapper.waitForElementToBeClickable(domFile,"HamburgerMenuResponsiveHP")==true){
            Mapper.find(domFile,"HamburgerMenuResponsiveHP").click();
        }
    }
    public void clickMSP()
    {
        Mapper.waitForElementToBeVisible(domFile,"menuIcon");
        Mapper.waitForElementToBeClickable(domFile,"menuIcon");
        Mapper.find(domFile,"menuIcon").click();
        Mapper.waitForElementToBeClickable(domFile,"Msp");
        Mapper.find(domFile, "Msp").click();
    }

    public void navigataebackFromHomePage(){
        navigateTo().back();
    }

    public boolean isMyChatandRepliesMenuIconavilable(){
        Mapper.waitForElementToBeVisible(domFile, "menuIcon");
        Mapper.waitForElementToBeClickable(domFile, "menuIcon");
        Mapper.find(domFile, "menuIcon").click();
        return isElementPresent("MyChatandRepliesMenuIcon");
    }

    public void clickMyChatandRepliesMenuIcon(){
        Mapper.waitForElementToBeVisible(domFile,"menuIcon");
        Mapper.waitForElementToBeClickable(domFile,"menuIcon");
        Mapper.find(domFile,"menuIcon").click();
        Mapper.waitForElementToBeClickable(domFile,"MyChatandRepliesMenuIcon");
        Mapper.find(domFile, "MyChatandRepliesMenuIcon").click();
    }

    public boolean isCreateAlertavailable(){
        Mapper.waitForElementToBeVisible(domFile, "menuIcon");
        Mapper.waitForElementToBeClickable(domFile, "menuIcon");
        Mapper.find(domFile,"menuIcon").click();
        return isElementPresent("CreateAlert");
    }

    public void clickCreateAlert(){
        Mapper.waitForElementToBeClickable(domFile,"menuIcon");
        Mapper.find(domFile,"menuIcon").click();
        Mapper.waitForElementToBeClickable(domFile,"CreateAlert");
        Mapper.find(domFile, "CreateAlert").click();
    }

    public void clickQuikrX(){
        try {
            TimeUnit.SECONDS.sleep(5l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Mapper.waitForElementToBeClickable(domFile,"QuikrX");
        Mapper.find(domFile, "QuikrX").click();
    }

    public void clickDownloadAppLink(){
        Mapper.waitForElementToBeClickable(domFile, "DownloadAppLink");
        Mapper.find(domFile,"DownloadAppLink").click();
    }

    public void clickMyChatAndReplies(){
        Mapper.waitForElementToBeClickable(domFile, "MyChatAndReplies");
        Mapper.find(domFile,"MyChatAndReplies").click();
    }

    public boolean isGoogleAdsdisplayedHomePage(){
        Mapper.waitForElementToBeVisible(domFile, "GoogleAdsHomePage");
        return isElementPresent("GoogleAdsHomePage");
    }

    public boolean isSwiperHomePagedisplayed(){
        Mapper.waitForElementToBeVisible(domFile, "SwiperHomePage");
        return isElementPresent("SwiperHomePage");
    }

    public ArrayList<String> isAllLanguagesDisplayed(){
        ArrayList<String> languagelist=new ArrayList<>();
        for (int i=1;i<=8;i++) {
            WebElement languages=Mapper.findAndReplace(domFile, "Language", new String[]{Integer.toString(i)});
            languagelist.add(languages.getText());
        }
        return languagelist;
    }

    public void clickLanguage(int languagenumber){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement language = Mapper.findAndReplace(domFile, "Language", new String[]{Integer.toString(languagenumber)});
            language.click();

    }
    
    public boolean isEnglishLanguageHomePagedisplayed(){
        WebElement english=Mapper.findAndReplace(domFile, "Language", new String[]{Integer.toString(1)});
        System.out.println(english.getText());
            if(english.isDisplayed()){
                return true;
            }
        else
                return false;
    }

    public void clickMenu()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"menuIcon")==true)
        {
            Mapper.find(domFile,"menuIcon").click();
//            if (Mapper.waitForElementToBeVisible(domFile,"myAccountMenuLink")==true)
//            {
//                logger.info("Menu icon clicked successfully.");
//            }
//            else
//            {
//                logger.info("Attempting click on <menuIcon> second time.");
//                Mapper.find(domFile,"menuIcon").click();
//            }
        }else {
            logger.info("Menu icon was not clicked.");
        }
    }

    public boolean isMyAccountavailable(){
        Mapper.waitForElementToBeClickable(domFile, "menuIcon");
        Mapper.find(domFile,"menuIcon").click();
        return isElementPresent("myAccountMenuLink");
    }

    public  void clickMyAccount()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"myAccountMenuLink")==true)
        {
            Mapper.find(domFile,"myAccountMenuLink").click();
        }
        else
        {
            logger.info("My Account link is not visible in menu.");
        }
    }


    public boolean isHomePagevalid(){
        boolean flag=false;
        if(isQuikrLogoavailable()==true){
            flag=true;
        }
        else {
            return false;
        }
        if(isShowMoreCategoryAvailable() ==true){
            flag=true;
        }
        else {
            return false;
        }
        if(isPostAdButtonavailable() ==true){
            flag=true;
        }
        else {
            return  false;
        }
        if(isSearchavailable() ==true){
            flag=true;
        }
        else {
            return false;
        }
        if(isSelectCityavailable()==true){
            flag=true;
        }
        else
        {
            return false;
        }
        return flag;
    }

    public void clickHindiLink()
    {
        if (Mapper.waitForElementToBeClickable(domFile, "hindiLink")==true)
        {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Mapper.findAndReplace(domFile, "Language",new String[]{"2"}).click();

        }
        else
        {
            logger.info("The Hindi language link at the bottom of the screen is not visible. Please check!");
        }
    }

    public void clickTelguLink()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "telguLink")==true)
        {
            Mapper.find(domFile, "telguLink").click();
        }
        else
        {
            logger.info("The Telgu language link at the bottom of the screen is not visible. Please check!");
        }
    }

    public boolean checkCorrectLanguageTranslation(String langSelected)
    {
        boolean retVal = false;
        if (langSelected.equalsIgnoreCase("hindi")==true)
        {
            clickHindiLink();
            if (Mapper.waitForElementToBeVisible(domFile,"menuIcon")==true)
            {
                Mapper.find(domFile,"menuIcon").click();
                Mapper.find(domFile, "myChatsMenuLink");
                String myChatText = Mapper.find(domFile,"myChatsMenuLink").getText();
                if (myChatText.contains("मेरी चैट एवं उत्तर")==true)
                {
                    Mapper.find(domFile,"menuIcon").click();
                    retVal = true;
                }
                else
                {
                    Mapper.find(domFile,"menuIcon").click();
                    logger.info("Language translation not as expected. My chats text didn't get converted to respective Hindi translation. Please check!");
                    return false;
                }
            }else
            {
                logger.info("My chat link menu is not visible to check translation. Please check!");
                return false;
            }
        }
        else if (langSelected.equalsIgnoreCase("telgu")==true)
        {
            clickTelguLink();
            Mapper.find(domFile,"menuIcon");
            if (Mapper.waitForElementToBeVisible(domFile,"menuIcon")==true)
            {
                Mapper.find(domFile,"menuIcon").click();
                Mapper.find(domFile, "myChatsMenuLink");
                String myChatText = Mapper.find(domFile,"myChatsMenuLink").getText();
                System.out.println(">>"+myChatText+"<<");
                if (myChatText.contains("నా చాట్‌లు మరియు రిప్లైలు")==true)
                {
                    Mapper.find(domFile,"menuIcon").click();
                    retVal = true;
                }
                else
                {
                    Mapper.find(domFile,"menuIcon").click();
                    logger.info("Language translation not as expected. My chat text didn't get converted to respective Telgu translation. Please check!");
                    return false;
                }
            }else
            {
                logger.info("My chats in menu is not visible to check translation. Please check!");
                return false;
            }
        }
        return retVal;
    }

    public boolean verifySuccessfulLogin()
    {

        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "myProfileMenuLink")==true)
        {
            retVal = true;
        }
        else
        {
            return false;
        }
        return retVal;
    }


    public void closeLemsPopUp()
    {
        try {
            if (Mapper.find(domFile, "lemsPopUp") != null && Mapper.waitForElementToBeClickable(domFile, "lemsPopUpClose") == true) {
                Mapper.find(domFile, "lemsPopUpClose").click();
            }
        }
        catch(Exception e){
            logger.info("LEMS pop up was not displayed.");
        }
    }

    public void selectWebFromQuikrLems(){

        List<WebElement> list = Mapper.finds(domFile,"quikrWebLemsPopUp");
        if (list.size()>0){
            list.get(0).click();
            waitForPageToLoad(".com");
        } else{
            logger.info("Quikr lems pop up was not displayed.");
        }
    }

    public void clickPlusIcon(){
        if (Mapper.waitForElementToBeVisible(domFile,"plusIcon")==true)
            Mapper.find(domFile,"plusIcon").click();
        else
            logger.info("Plus icon button was not visible.");
    }
}
