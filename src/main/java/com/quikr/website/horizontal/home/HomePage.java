package com.quikr.website.horizontal.home;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.PageBase;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.*;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by akhil.singla
 */
public class HomePage extends PageBase {
    public HomePage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("HOME_DOM_FILE");


    //If you need to use selectCity(), please  use it from HeadersPage. Any other instance of a similar method will be deleted soon.
    /*public HomePage SelectCity(String city) {
        logger.debug("Testing");
        try {
            Mapper.findChildElement(domFile, city, "citySelect", "a", "index").click();
            waitForPageToLoad(city);
            zoomOut(2);
        } catch (NullPointerException e) {
            searchCityByName(city);
            waitForPageToLoad(city);
            zoomOut(2);
        }
        return this;
    }*/

    public void navigateToNewUI(String newUIURL) {
        openUrl(newUIURL);
    }

    /**
     * click on Post free Ad button
     */
    public void clickPostFreeAdButton() {
        try {
            /*if (Mapper.find(domFile, "postFreeAdButton") != null) {
                Mapper.find(domFile, "postFreeAdButton").click();
                if (!Mapper.waitForElementToBeInvisible(domFile, "postFreeAdButton", 15)) {
                    Mapper.find(domFile, "postFreeAdButton").click();
                }
            } else if
            */
                    if (Mapper.find(domFile, "postFreeAdButtonNewUICities") != null) {
                Mapper.waitForElementToBeClickable(domFile, "postFreeAdButtonNewUICities");
                Mapper.find(domFile, "postFreeAdButtonNewUICities").click();
            }
        }catch (Exception e){e.printStackTrace();}
    }


    public void clickPostFreeAdMyQuikrPage() {
        Mapper.find(domFile, "postFreeAdButtonMyQuikrPage").click();
    }

    public void clickHamburgerMenu() {
        if (Mapper.waitForElementToBeVisible(domFile,"hamburger_menu")==true){
            Mapper.find(domFile, "hamburger_menu").click();
            if (Mapper.waitForElementToBeInvisible(domFile,"hamburger_menu")==true){
                logger.info("Hamburger menu was clicked on the first attempt.");
            }else{
                Mapper.find(domFile, "hamburger_menu").click();
            }
        }else{
            logger.info("Hamburger menu not visible.");
        }
    }

    public void clickQuikrHomeHamburgerMenu() {
        Mapper.find(domFile, "quikr_home").click();
    }


    public HomePage selectsCarsAndBikes() {
        Mapper.find(domFile, "cars").click();
        return this;
    }

    public HomePage SelectsMobiles() {
        Mapper.waitForElementToBeVisible(domFile, "mobile_ResponsiveHp");
        Mapper.waitForElementToBeClickable(domFile, "mobile_ResponsiveHp");
        Mapper.find(domFile, "mobile_ResponsiveHp").click();
       /* Mapper.waitForElementToBeInvisible(domFile, "mobile_ResponsiveHp", 10);
        WebElement element = Mapper.find(domFile, "mobile_ResponsiveHp");
        if (element != null && element.isDisplayed()) {
            element.click();
        }*/

        return this;
    }

    public HomePage SelectsHomeLifeStyle() {
        Mapper.waitForElementToBeVisible(domFile, "HomeAndLifestyle");
        Mapper.waitForElementToBeClickable(domFile, "HomeAndLifestyle");
        Mapper.find(domFile, "HomeAndLifestyle").click();
       /* Mapper.waitForElementToBeInvisible(domFile, "HomeAndLifestyle", 10);
        WebElement element = Mapper.find(domFile, "HomeAndLifestyle");
        if (element != null && element.isDisplayed()) {
            element.click();
        }
*/
        return this;
    }

    public void categoryDropDown() {
        Mapper.waitForElementToBeVisible(domFile, "selectCategoryDropDown");
        Mapper.find(domFile, "selectCategoryDropDown").click();
    }
    public String selectPopularCategory() {
        String title = Mapper.find(domFile, "popularCategoriesOnQuikr").getText();
        Mapper.find(domFile, "popularCategoriesOnQuikr").click();
        return title;
    }

    public boolean isPopularCategoriesVisible() {
        Mapper.waitForElementToBeClickable(domFile, "createAlertWindow");
        return isElementPresent("createAlertWindow");
    }

    public boolean isCreateAlertWindowVisible() {
        Mapper.waitForElementToBeVisible(domFile, "createAlertWindow");
        return isElementPresent("createAlertWindow");
    }

    public HomePage selectElectronicsAndAppliances() {
        Mapper.waitForElementToBeClickable(domFile, "ElectronicsAndAppliances");
        Mapper.find(domFile, "ElectronicsAndAppliances").click();
       /* if (Mapper.waitForElementToBeInvisible(domFile, "ElectronicsAndAppliances", 15) == false) {
            Mapper.waitForElementToBeClickable(domFile, "ElectronicsAndAppliances");
            Mapper.find(domFile, "ElectronicsAndAppliances").click();
        }*/
        return this;
    }

    /**
     * select quikrX option from Homepage
     */
    public void selectQuikrXOptionFromHome() {
//       Mapper.find(domFile,"body").click();
        Mapper.find(domFile, "quikrXOptionHomePage").click();
        Mapper.waitForElementToBeInvisible(domFile, "quikrXOptionHomePage");
    }

    /**
     * select jobs option from Homepage
     */
    public void selectJobsOptionFromHome() {
        if (isElementPresent("LanguageSelectionMessage")) {
            Mapper.find(domFile, "JobsOptionHomepage").click();
            Mapper.find(domFile, "JobsOptionHomepage").click();
        } else if (!isElementPresent("LanguageSelectionMessage")) {
            Mapper.waitForElementToBeVisible(domFile, "JobsOptionHomepage");
            Mapper.waitForElementToBeClickable(domFile, "JobsOptionHomepage");
            Mapper.find(domFile, "JobsOptionHomepage").click();
            if (isElementPresent("JobsOptionHomepage")) {
                Mapper.find(domFile, "JobsOptionHomepage").click();
            }

        }
    }

    public boolean validateFooter() {
        return isElementPresent("footer");
    }

    public boolean validateAboutUs() {
        Mapper.find(domFile, "aboutUs").click();
        return isElementPresent("aboutUsContainer");
    }

    /**
     * function to select services option from Homepage
     */
    public void selectServicesOptionFromHome() {
        Mapper.find(domFile, "servicesOptionHomePage").click();
        Mapper.waitForElementToBeInvisible(domFile, "servicesOptionHomepage");
    }

    //Commented since the ad is no longer displayed at homepage. Will be incorporating logic where this method executes only when ad is displayed.
    public void skipAd() {
        //Mapper.find(domFile, "skipAd").click();
    }

    /**
     * select mobile and tablets option from Homepage
     */
    public void selectMobilesAndTabletsOptionFromHome() {
        Mapper.waitForElementToBeVisible(domFile, "mobilesAndTabletsOption");
        if(Mapper.waitForElementToBeClickable(domFile, "mobilesAndTabletsOption")==true) {
            Mapper.find(domFile, "mobilesAndTabletsOption").click();
            if(Mapper.waitForElementToBeInvisible(domFile, "mobilesAndTabletsOption")==false) {
                Mapper.find(domFile, "mobilesAndTabletsOption").click();

            }

        }
    }

    /**
     * function to check social media icons
     */
    public boolean checkicons() {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "linkedin_img")==true){
            retVal=true;
        }else{
            return false;
        }
        if(Mapper.waitForElementToBeVisible(domFile, "facebook_img")==true){
            retVal=true;
        }else{
            return false;
        }
        if(Mapper.waitForElementToBeVisible(domFile, "twitter_img")==true){
            retVal=true;
        }else{
            return false;
        }
        if (Mapper.waitForElementToBeVisible(domFile, "youtube_img")==true){
            retVal=true;
        }else{
            return false;
        }
        if(Mapper.waitForElementToBeVisible(domFile, "google_img")==true){
            retVal=true;
        }else{
            return false;
        }
        return retVal;
    }

    /**
     * function to click notification bell Home page
     */

    public void selectnotificationbell() {
        Mapper.find(domFile, "notification_bell").click();
    }

    /*function to click on view details for the add
    */

    public void clickviewdetails() {
        Mapper.waitForElementToBeClickable(domFile, "view_details");
        Mapper.find(domFile, "view_details").click();
    }

    /*function to click on Real Estate option
    */
    public void selectRealEstateOptionFromHome() {
        Mapper.find(domFile, "RealEstateOptionHomepage").click();
        Mapper.waitForElementToBeInvisible(domFile, "RealEstateOptionHomepage");
    }

    /*function to select Bikes & Scooters option
  */
    public void selectCarsandBikes() {
        Mapper.waitForElementToBeClickable(domFile, "CarsandBikes");
        Mapper.find(domFile, "CarsandBikes").click();
        Mapper.waitForElementToBeInvisible(domFile, "CarsandBikes");
    }

    /*function to Select Houses - Apartments for Rent option
  */
    public void selectHousesApartmentsRent() {
        Mapper.find(domFile, "Houses-Apartments-for-Rent").click();
        Mapper.waitForElementToBeInvisible(domFile, "Houses-Apartments-for-Rent");
    }

    /*function to Select Houses - Apartments for Sale
    */
    public void selectHousesApartmentsSale() {
        Mapper.find(domFile, "Houses-Apartments-for-Sale").click();
        Mapper.waitForElementToBeInvisible(domFile, "Houses-Apartments-for-Sale");
    }

    /*function to Select Laptops Computers
    */
    public void selectLaptopsComputers() {
        Mapper.find(domFile, "Laptops-Computers").click();
        Mapper.waitForElementToBeInvisible(domFile, "Laptops-Computers");
    }

    /**
     * function to Select Home Furniture
     */
    public void selectHomeFurniture() {
        Mapper.find(domFile, "Home-Furniture").click();
        Mapper.waitForElementToBeInvisible(domFile, "Home-Furniture");
    }

    /*function to Select Kitchen Appliances
    */
    public void selectKitchenAppliances() {
        Mapper.find(domFile, "Kitchen-Appliances").click();
        Mapper.waitForElementToBeInvisible(domFile, "Kitchen-Appliances");
    }

    /*function to SelectPopular Cities from footer section
    */
    public boolean selectpopularCity(String city) {

        boolean flag = false;
        Mapper.findChildElement(domFile, city, "popularcitySelect", "a", true).click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getCurrentLocation().contains(city.toLowerCase())) {
            flag = true;

        } else if (!getCurrentLocation().contains(city.toLowerCase())) {
            {
                Mapper.findChildElement(domFile, city, "popularcitySelect", "a", true).click();
                if (getCurrentLocation().equalsIgnoreCase(city)) {
                    flag = true;
                } else
                    flag = false;
            }

        }
        return flag;
    }


    public void selectCityDropDown() {
        Mapper.find(domFile, "cityselect_dropdown").click();
    }

    public void selectMoreCities() {
        Mapper.find(domFile, "more_cities").click();
    }

    public void clickonStateforDownloadApp() {
        Mapper.waitForElementToBeVisible(domFile, "Tamil_Dadu_download_app_state");
        Mapper.waitForElementToBeClickable(domFile, "Tamil_Dadu_download_app_state");
        Mapper.find(domFile, "Tamil_Dadu_download_app_state").click();
        Mapper.find(domFile, "aarani_download_app_city").click();
    }

    public void clickonCityCloseButton() {

        try {
            if (Mapper.find(domFile, "closecity_popup") != null) {
                Mapper.waitForElementToBeVisible(domFile, "closecity_popup");
                Mapper.waitForElementToBeClickable(domFile, "closecity_popup");
                Mapper.find(domFile, "closecity_popup").click();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void navigateback() {
        navigateTo().back();
    }

    /**
     * Generic function to Select City from the State or Union Territories from the City POP UP
     *
     * @param city
     */
    public void selectStateorUnionTerritories(String city){

        Mapper.waitForElementToBeVisible(domFile, "SelectCityDiv");
        Mapper.findAndReplace(domFile, "selectStateorUnionTerritory", new String[]{city}).click();

    }

    public boolean checkLanguageTranslationHomepage(String HeaderText, String CarsBikesText, String JobText) {
        boolean FinalVal = false;
        String HeaderAboveCategoriesText = Mapper.find(domFile, "HeaderTextAboveCategories").getText();
        String CarsAndBikesText = Mapper.find(domFile, "CarsText").getText();
        String JobsText = Mapper.find(domFile, "JobsText").getText();

        if (HeaderAboveCategoriesText.equals(HeaderText)) {
            FinalVal = true;
        }
        if (CarsAndBikesText.equals(CarsBikesText)) {
            FinalVal = true;
        }
        if (JobsText.equals(JobText)) {
            FinalVal = true;
        }

        return FinalVal;
    }

    public boolean checkKeyboardPresence() {
        boolean FinalVal = false;
        Mapper.find(domFile, "SearchTextArea").click();
        Mapper.find(domFile, "SearchTextArea").click();
        if (isElementPresent("KeyboardContainer")) {
            FinalVal = true;
        }
        return FinalVal;
    }

    public void setMobileSearchKeywordVernacKeyboard() {
        Mapper.find(domFile, "SearchTextArea").click();
        Mapper.waitForElementToBeVisible(domFile, "KeyboardContainer");
        Mapper.waitForElementToBeVisible(domFile, "m_in_Hindi");
        Mapper.waitForElementToBeClickable(domFile, "m_in_Hindi");
        Mapper.waitForElementToBeVisible(domFile, "m_in_Hindi");
        Mapper.waitForElementToBeClickable(domFile, "m_in_Hindi");
        Mapper.find(domFile, "m_in_Hindi").click();
        Mapper.find(domFile, "O_in_Hindi").click();
        Mapper.find(domFile, "B_in_Hindi").click();
        Mapper.find(domFile, "A_in_Hindi").click();
        Mapper.waitForElementToBeVisible(domFile, "A_in_Hindi");
        Mapper.waitForElementToBeClickable(domFile, "A_in_Hindi");
        Mapper.find(domFile, "ShiftVernacKeyboard").click();
        if(Mapper.waitForElementToBeVisible(domFile, "I_in_Hindi")==true) {
            Mapper.waitForElementToBeClickable(domFile, "I_in_Hindi");
            Mapper.find(domFile, "I_in_Hindi").click();
        }
        Mapper.find(domFile, "ShiftVernacKeyboard").click();
        Mapper.find(domFile, "ShiftVernacKeyboard").click();
        if(Mapper.waitForElementToBeClickable(domFile, "L_in_Hindi")==true) {
            Mapper.find(domFile, "L_in_Hindi").click();
        }
    }

    public void clickLanguageLink() {
        try {
            if (Mapper.find(domFile, "LanguageLink") !=null)

            {
                Mapper.find(domFile, "LanguageLink").click();
                //to check if Language drop is open or not
                Mapper.waitForElementToBeVisible(domFile, "LanguageHindi", 6);
                WebElement element = Mapper.find(domFile, "LanguageHindi");
                if (!element.isDisplayed()) {
                    //Mapper.waitForElementToBeClickable(domFile, "LanguageLink");
                    Mapper.find(domFile, "LanguageLink").click();
                }
            }

            else if(Mapper.find(domFile,"languageDropDownResponsiveHP")!=null){
                Mapper.find(domFile,"languageDropDownResponsiveHP").click();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectHindiLanguage() {
        Mapper.waitForElementToBeVisible(domFile, "LanguageHindi");
        if(Mapper.find(domFile, "LanguageHindi")!=null) {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Mapper.find(domFile, "LanguageHindi").click();

            if(Mapper.find(domFile, "LanguageHindi").isDisplayed()==true){
                Mapper.find(domFile, "LanguageHindi").click();
            }
        }
        else if(Mapper.find(domFile,"languageDropDownResponsiveHP")!=null){
            Mapper.findAndReplace(domFile, "languageDropDownOptionsResponsiveHP", new String[]{Integer.toString(2)}).click();
        }
    }

    public void selectTamilLanguage() {
        Mapper.waitForElementToBeVisible(domFile, "LanguageTamil");
        if(Mapper.find(domFile,"LanguageTamil")!=null) {
            Mapper.find(domFile, "LanguageTamil").click();
        }
        else if(Mapper.find(domFile,"languageDropDownResponsiveHP")!=null){
            Mapper.findAndReplace(domFile, "languageDropDownOptionsResponsiveHP", new String[]{Integer.toString(3)}).click();
        }
    }

    public void selectTeluguLanguage() {
        Mapper.waitForElementToBeVisible(domFile, "LanguageTelugu");
        Mapper.find(domFile, "LanguageTelugu").click();
    }

    public void selectMalayalamLanguage() {
        Mapper.waitForElementToBeVisible(domFile, "LanguageMalayalam");
        Mapper.find(domFile, "LanguageMalayalam").click();
    }


    public void selectEnglishLanguage() {
            /*Mapper.waitForElementToBeVisible(domFile, "LanguageLink");
            Mapper.find(domFile, "LanguageLink").click();
            Mapper.find(domFile, "LanguageLink").sendKeys(Keys.ESCAPE);
            Mapper.find(domFile, "LanguageLink").click();
            */
        Mapper.waitForElementToBeVisible(domFile, "LanguageEnglish");
        Mapper.find(domFile, "LanguageEnglish").click();
        //Thread.sleep(4000);

    }

    public void searchCityByName(String city) {
        clickonCityCloseButton();
        Mapper.find(domFile, "cityselect_dropdown").click();
        Mapper.find(domFile, "CityNametextField").sendKeys(city);
        if (city.equalsIgnoreCase("vasaivirar"))
            city = "VasaiVirar";
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Mapper.waitForElementToBeVisible(domFile, "selectedCityName", new String[]{city});
        Mapper.findAndReplace(domFile, "selectedCityName", new String[]{city}).click();
        waitForPageToLoad(city);
    }

    public void searchCityByNameCityPopUpClosed(String city) {

        //clickonCityCloseButton();
        Mapper.find(domFile, "cityselect_dropdown").click();
        Mapper.find(domFile, "CityNametextField").sendKeys(city);
        Mapper.waitForElementToBeVisible(domFile, "selectedCityName", new String[]{city});
        Mapper.findAndReplace(domFile, "selectedCityName", new String[]{city}).click();
        waitForPageToLoad(city);
    }

    public boolean verifyMainContainerHomePage() {
        if (Mapper.find(domFile, "MainContainer").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean checkTrendingAds() {
        boolean retVal;
        if (Mapper.find(domFile, "TrendingAds") != null) {
            retVal = true;
        } else {
            return false;
        }

        return retVal;
    }

    public boolean checkTrendingAdsNewUICities() {

        return isElementPresent("TrendingAdsNewUICities");

    }

    public boolean verifyAppearanceOrderforTrendingRecommendedAds() {
        boolean finalVal = false;
        Mapper.waitForElementToBeVisible(domFile, "RecommendedAdsContainer");
        if (Mapper.find(domFile, "RecommendedAdsContainer").isDisplayed()) {
            if (Mapper.find(domFile, "TrendingAdContainer").isDisplayed()) {
                finalVal = true;
            } else
                logger.info("Trending ad container not displayed.");
        } else
            logger.info("Recommended ad container not displayed");

        return finalVal;
    }

    public String gettextRecommenededAdsHomePage() {
        Mapper.waitForElementToBeVisible(domFile, "RecommendedAdsTitleHomePage");
        return Mapper.find(domFile, "RecommendedAdsTitleHomePage").getText();
    }

    public boolean verifyTrendingAdsLocalityChicklet() {
        boolean finalVal = false;
        int count = 0;
            List<WebElement> elms = Mapper.finds(domFile, "TrendingAdsLocalityChicklet");
            for (int i = 0; i < elms.size(); i++) {
                if (elms.get(i).isDisplayed()) {
                    count+=1;
                }
            }
        if (count==4){
            finalVal=true;
        }
        else{
            logger.info("Doesn't seem that required number of locality chicklets were displayed. The count of visible chicklets is :: "+count);
        }
        return finalVal;
    }


    public boolean verifyTrendingAdsLocalityChickletNewUICities() {
        boolean finalVal = false;
        List<WebElement> elms = null;
        Mapper.waitForElementToBeVisible(domFile, "TrendingAdsLocalityChickletNewUICities");
        elms = Mapper.finds(domFile, "TrendingAdsLocalityChickletNewUICities");

        if (elms.size() > 0) {
            finalVal = true;
        } else
            logger.info("Locality chicklets in trending ads are not being displayed properly.");

        return finalVal;
    }


    public boolean verifyTrendingAdsTimeChickletNewUICities() {
        boolean finalVal = false;
        List<WebElement> elms = Mapper.finds(domFile, "TrendingAdsTimeChickletNewUICities");
        for (int i = 0; i < elms.size(); i++) {
            if (elms.get(i).isDisplayed()) {
                finalVal = true;
            } else
                logger.info("Time chicklets in trending ads are not being displayed properly.");
        }
        return finalVal;
    }

    public boolean verifyTrendingAdsTimeChicklet() {
        boolean finalVal = false;
        int count=0;
        List<WebElement> elms = Mapper.finds(domFile, "TrendingAdsTimeChicklet");
        for (int i = 0; i < elms.size(); i++) {
            if (elms.get(i).isDisplayed()==true) {
                count+=1;
            } else
                logger.info("Time chicklets in trending ads are not being displayed properly.");
        }
        if (count==4){
            finalVal=true;
        }else
        {
            logger.info("Required number of time chicklets were not visible. Count of visible time chicklets = "+count);
        }
        return finalVal;
    }

    public boolean verifyRecommAdLocalityTimeChicklets() {
        boolean finalVal = false;
        try {
            if (Mapper.waitForElementToBeVisible(domFile, "RecommAdsLocalityChicklet") == true) {
                List<WebElement> loc = Mapper.finds(domFile, "RecommAdsLocalityChicklet");

                for (int i = 0; i < loc.size() - 1; i++) {
                    if (loc.get(i).isDisplayed() == true) {
                        finalVal = true;
                    } else {
                        logger.info("Size of locality chicklet list :: " + loc.size());
                        logger.info("Locality chicklets not displayed.");
                        //return false;
                    }
                }
                List<WebElement> time = Mapper.finds(domFile, "RecommAdsTimeChicklet");

                for (int i = 0; i < time.size() - 1; i++) {
                    if (time.get(i).isDisplayed() == true) {
                        finalVal = true;
                    } else {
                        logger.info("Size of time chicklet list :: " + time.size());
                        logger.info("Time chicklets not displayed.");
                        //return false;
                    }
                }
                return finalVal;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return finalVal;
    }

    public boolean verifyCreateAlertLabels() {
        boolean finalVal = false;
        List<WebElement> labels = Mapper.finds(domFile, "CreateAlertLabels");
        List<String> labelsStr = new ArrayList<>();
        for (int i = 0; i < labels.size(); i++) {
            labelsStr.add(labels.get(i).getText().toString());
            logger.info(">>" + labelsStr.get(i) + "<<");
        }
        String[] PresentLabelArr = {"Category", "Sub Category", "Type", "Location", "Email Address", "Mobile Number"};

        for (int i = 0; i < PresentLabelArr.length; i++) {
            if (labelsStr.contains(PresentLabelArr[i])) {
                finalVal = true;
            }
        }
        return finalVal;
    }


    public boolean verifyCreateAlertLabelsNewUiCities(String City) {
        boolean finalVal = false;
        List<WebElement> labels = Mapper.finds(domFile, "CreateAlertLabelsNewUICities");
        List<String> labelsStr = new ArrayList<>();
        for (int i = 0; i < labels.size(); i++) {
            labelsStr.add(labels.get(i).getText().toString());
            logger.info(">>" + labelsStr.get(i) + "<<");
        }
        String[] PresentLabelArr = {"Select Category", "Select Sub Category", "I want to buy", City, "Any Locality", "Email Address", "Mobile Number"};

        for (int i = 0; i < PresentLabelArr.length; i++) {
            if (labelsStr.contains(PresentLabelArr[i])) {
                finalVal = true;
            }
        }
        return finalVal;
    }

    public void clickNewQuikrX() {
        navigateToNewUI(getCurrentLocation() + "QuikrX");
    }

    public void selectCategoryAlertCreation(String category) {
        Mapper.find(domFile, "selectCategoryBoxAlertCreation").click();
        List<WebElement> elm = Mapper.finds(domFile, "selectCategoryAlertCreation");
        List<String> elmStrings = new ArrayList();
        String text;

        for (int i = 0; i < elm.size(); i++) {
            text = elm.get(i).getText();
            elmStrings.add(i, text);
        }

        for (int i = 0; i < elmStrings.size(); i++) {
            if (elmStrings.get(i).equals(category)) {
                elm.get(i).click();
                break;
            }
        }
    }

    public void selectSubCategoryAlertCreation(String subCat) {
        Mapper.find(domFile, "selectSubCategoryBoxAlertCreation").click();
        List<WebElement> elm = Mapper.finds(domFile, "selectSubCategoryAlertCreation");
        List<String> elmStrings = new ArrayList();
        String text;

        for (int i = 0; i < elm.size(); i++) {
            text = elm.get(i).getText();
            elmStrings.add(i, text);
        }

        for (int i = 0; i < elmStrings.size(); i++) {
            if (elmStrings.get(i).equals(subCat)) {
                elm.get(i).click();
                break;
            }
        }
    }

    public boolean validateIWantACook() {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "IWantACook") == true) {
            if (Mapper.find(domFile, "IWantACook") != null) {
                if (Mapper.find(domFile, "IWantACook").getText().equals("I want a cook")) ;
                {
                    retVal = true;
                }
            }
        }
        return retVal;
    }

    public void selectLocalityAlertCreation(String loc) {
        Mapper.find(domFile, "selectLocalityBoxAlertCreation").click();
        List<WebElement> elm = Mapper.finds(domFile, "selectLocalityAlertCreation");
        List<String> elmStrings = new ArrayList();
        String text;

        for (int i = 0; i < elm.size(); i++) {
            text = elm.get(i).getText();
            elmStrings.add(i, text);
        }

        for (int i = 0; i < elmStrings.size(); i++) {
            if (elmStrings.get(i).equals(loc)) {
                elm.get(i).click();
            } else {
                logger.info("The mentioned locality doesn't exists.");
            }
        }
    }

    public void clickSubmitAlertCreation() {
        Mapper.find(domFile, "submitButtonAlertCreation").click();
    }

    public void searchCityInCitySearchBox(String searchCityString) {
        if (Mapper.waitForElementToBeVisible(domFile, "citySearchTextField") == true) {
            Mapper.find(domFile, "citySearchTextField").click();
            Mapper.find(domFile, "citySearchTextField").sendKeys(searchCityString);
        }
    }

    public boolean checkCityAutoSuggest(String searchCityString) {
        boolean retVal = true;
        if (Mapper.waitForElementToBeVisible(domFile, "cityAutoSuggest") == true) {
            String str = (Mapper.find(domFile, "cityAutoSuggest").getText()).toLowerCase();
            if (str.contains(searchCityString) == true) {
                retVal = true;
            } else {
                return false;
            }
        }
        return retVal;
    }

    public boolean checkNewUI() {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "newHomePagePlusIcon") == true) {
            retVal = true;
        } else {
            return false;
        }
        return retVal;
    }

    public String checkCityPresence() {
        String retVal = "";
        String[] cities = {"Ahmedabad", "Bangalore", "Chandigarh", "Chennai", "Coimbatore", "Delhi", "Gurgaon", "Hyderabad", "Jaipur", "Kochi", "Kolkata", "Lucknow", "Mumbai", "NaviMumbai", "Noida", "Pune", "Trivandrum","Panaji"};
        if (Mapper.waitForElementToBeVisible(domFile, "snbFirstAdTitle") == true) {
            String textFromAdTitle = Mapper.find(domFile, "snbFirstAdTitle").getText();
            for (int i = 0; i < cities.length; i++) {
                if (textFromAdTitle.contains(cities[i])) {
                    retVal = cities[i];
                }
            }
            Mapper.find(domFile, "snbFirstAdTitle").click();
        }
        return retVal;
    }

    public boolean checkUrlForString(String stringToCheck) {
        boolean retVal;
        String url = getCurrentLocation().toString();
        if (url.contains(stringToCheck)) {
            retVal = true;
        } else {
            return false;
        }
        return retVal;
    }

    public boolean checkLocInChicklet(String loc) {
        boolean retVal = false;
        int Tflag = 0;
        int Fflag = 0;
        List<WebElement> chickletContents = Mapper.finds(domFile, "snbLocChicklets");

        for (int i = 0; i < chickletContents.size(); i++) {
            if (chickletContents.get(i).getAttribute("title").equals(loc)) {
                Tflag += 1;
            } else {
                Fflag += 1;
            }
        }
        if (Tflag >= Fflag) {
            retVal = true;
        } else {
            return false;
        }
        return retVal;
    }

    public boolean checkSpecificCityAdTitle(String city) {
        boolean retVal = false;
        String textFromAdTitle = Mapper.find(domFile, "snbFirstAdTitle").getText();
        if (textFromAdTitle.contains(city)) {
            retVal = true;
            logger.info(city+" found in the ad title as ::  "+textFromAdTitle);
        } else {
            return false;
        }
        return retVal;
    }

    public boolean isAppDownloadLinkWorking() {
        boolean flag = false;
        String maiwindowhandle = getWindowHandle();
        if (isElementPresent("androidAppLink") == true) {
            WebElement androidAppLink = Mapper.find(domFile, "androidAppLink");
            androidAppLink.click();
        }

        if (isElementPresent("IOSAppLink") == true) {
            WebElement IOSAppLink = Mapper.find(domFile, "IOSAppLink");
            Mapper.waitForElementToBeClickable(domFile, "IOSAppLink");
            IOSAppLink.click();
        }

        if (isElementPresent("WindowsAppLink") == true) {
            WebElement windowsAppLink = Mapper.find(domFile, "WindowsAppLink");
            Mapper.waitForElementToBeClickable(domFile, "WindowsAppLink");
            windowsAppLink.click();
        }

        String urls[]={"quikr.com","play.google.com/store","itunes.apple.com","microsoft.com"};
        Set<String> s=getWindowHandles();

        Iterator<String> ite=s.iterator();
        int i=0;
            while (ite.hasNext()) {
                String check = ite.next().toString();
                switchTo().window(check);
                logger.info("url extracted :: "+getCurrentLocation());
                if (getCurrentLocation().contains(urls[i])) {
                    flag = true;
                    switchTo().window(maiwindowhandle);
                    logger.info(urls[i]+"has been matched with above url");
                } else {
                    flag = false;
                    logger.info("url didn't match.");
                }
                i++;
            }

        logger.info("flag value is :: "+flag);
        return flag;
    }


    public boolean isFacebookLinkworking() {
        boolean flag = false;
        if (isElementPresent("facebook_img") == true) {
            flag = true;
            WebElement facebookLink = Mapper.find(domFile, "facebook_img");
            String mainwindow=switchtoPopup(facebookLink);
            if (getCurrentLocation().contains("facebook.com")) {
                flag = true;
                switchtoParentfromPopUp(mainwindow);
            } else {
                logger.info("Facebook link not redirecting to Facebook");
                flag = false;
            }
        } else {
            logger.info("Facebook Link not available");
            flag = false;
        }

        return flag;
    }


    public boolean isLinkedInLinkworking() {
        boolean flag = false;
        if (isElementPresent("linkedin_img") == true) {
            flag = true;
            WebElement linkedInLink = Mapper.find(domFile, "linkedin_img");
            String mainwindow=switchtoPopup(linkedInLink);
            if (getCurrentLocation().contains("linkedin.com/company")) {
                flag = true;
                switchtoParentfromPopUp(mainwindow);
            } else {
                logger.info("LinkedIn link not redirecting to LinkedIn");
                flag = false;
            }
        } else {
            logger.info("LinkedIn Link not available");
            flag = false;
        }
        return flag;
    }


    public boolean isTwitterLinkworking() {
        boolean flag = false;
        if (isElementPresent("twitter_img") == true) {
            flag = true;
            WebElement twitterLink = Mapper.find(domFile, "twitter_img");
            String mainwindow=switchtoPopup(twitterLink);
            if (getCurrentLocation().contains("twitter.com/quikr")) {
                flag = true;
                switchtoParentfromPopUp(mainwindow);
            } else {
                logger.info("Twitter link not redirecting to Twitter");
                flag = false;
            }
        } else {
            logger.info("Twitter Link not available");
            flag = false;
        }
        return flag;
    }

    public boolean isYouTubeLinkworking() {
        boolean flag = false;
        if (isElementPresent("youtube_img") == true) {
            flag = true;
            WebElement youtubeLink = Mapper.find(domFile, "youtube_img");
            String mainwindow=switchtoPopup(youtubeLink);
            if (getCurrentLocation().contains("youtube.com/channel")) {
                flag = true;
                switchtoParentfromPopUp(mainwindow);
            } else {
                logger.info("YouTube link not redirecting to YouTube");
                flag = false;
            }
        } else {
            logger.info("YouTube Link not available");
            flag = false;
        }
        return flag;
    }

    public boolean isGooglePlusLinkworking() {
        boolean flag = false;
        if (Mapper.waitForElementToBeVisible(domFile, "google_img") == true) {
            flag = true;
            WebElement googlePlusLink = Mapper.find(domFile, "google_img");
            String mainwindow=switchtoPopup(googlePlusLink);
            if (getCurrentLocation().contains("plus.google.com/+quikr")) {
                flag = true;
                switchtoParentfromPopUp(mainwindow);
            } else {
                logger.info("GooglePlus link not redirecting to GooglePlus");
                flag = false;
            }
        } else {
            logger.info("GooglePlus Link not available");
            flag = false;
        }
        return flag;
    }

    public boolean isHamburgerMenuPresentResponsiveHP() {
        return isElementPresent("HamburgerMenuResponsiveHP");
    }

    public boolean validateHamburgerMenuResponsiveHP() {
        boolean flag = false;

        if (isElementPresent("checkMSP_HamburgerMenuResponsiveHP") == true) {
            flag = true;
            logger.info("<checkMSP_HamburgerMenuResponsiveHP> is present....");
        } else{
            logger.info("<checkMSP_HamburgerMenuResponsiveHP> is not present....");
            return false;
        }

        if (isElementPresent("signINbutton_HamburgerMenuResponsiveHP") == true) {
            flag = true;
            logger.info("<signINbutton_HamburgerMenuResponsiveHP> is present....");
        } else {
            logger.info("<signINbutton_HamburgerMenuResponsiveHP> is not present....");
            return false;
        }

        Mapper.find(domFile, "HamburgerMenuResponsiveHP").click();
        Mapper.find(domFile, "moreLink_HamburgerMenuResponsiveHP").click();
        logger.info("<moreLink_HamburgerMenuResponsiveHP> is present and so clicked upon....");
        if(Mapper.waitForElementToBeVisible(domFile, "categories_HamburgerMenuResponsiveHP")==true) {
            List<WebElement> categories = Mapper.finds(domFile, "categories_HamburgerMenuResponsiveHP");
            logger.info("<Size of categories <categories_HamburgerMenuResponsiveHP> is >"+categories.size());

            Mapper.find(domFile, "cars").click();

            if (getCurrentLocation().contains("Cars-Bikes") == true) {
                flag = true;
                navigateback();
                Mapper.waitForElementToBeClickable(domFile, "HamburgerMenuResponsiveHP");
                Mapper.find(domFile, "HamburgerMenuResponsiveHP").click();
            } else
                return false;
        }
        return flag;
    }


    public void selectMobiles_ResponsiveHP()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "mobile_ResponsiveHp")==true)
        {
            Mapper.find(domFile,"mobile_ResponsiveHp").click();
            logger.info("Mobiles selected from homepage....");
        }
        else {
            logger.info("Mobile and tablets icon was not present in homepage");
        }
        waitForPageToLoad("Mobiles-Tablets");
    }


    public boolean closeHamburgerSectionResponsiveHP() {
        Mapper.find(domFile, "close_HamburgerMenuResponsiveHP").click();
        return isElementPresent("openedHamburgerMenuResponsiveHP");
    }

    public boolean isPostAdbuttonpresentFooterResponsiveHP() {
        boolean flag = false;
        if (isElementPresent("postAdButtonFooterResponsiveHP") == true) {
            flag = true;
        } else {
            logger.info("Post Ad button not present in footer");
            return false;
        }

        Mapper.find(domFile, "postAdButtonFooterResponsiveHP").click();
        if (getCurrentLocation().contains("post-classifieds-ads/select-category")) {
            flag = true;
        } else {
            logger.info("Unable to Open Post Ad page");
            return false;
        }
        return flag;
    }

    public boolean isPostAdbuttonpresentHeaderResponsiveHP() {
        boolean flag = false;
        if (Mapper.find(domFile, "postAdButtonTopResponsiveHP")!=null) {
            flag = true;
        } else {
            logger.info("Post Ad button not present in Header");
            return false;
        }

        Mapper.find(domFile, "postAdButtonTopResponsiveHP").click();
        if (getCurrentLocation().contains("post-classifieds-ads/select-category")) {
            flag = true;
        } else {
            logger.info("Unable to Open Post Ad page");
            return false;
        }
        return flag;
    }


    public boolean validatemobileNumberDownloadQuikrAppResponsiveHP(String number) {

        boolean flag=false;
        if (Mapper.waitForElementToBeVisible(domFile, "mobileNumberDownloadQuikrAppResponsiveHP") == true) {
            if (Mapper.waitForElementToBeClickable(domFile, "mobileNumberDownloadQuikrAppResponsiveHP") == true) {
                Mapper.find(domFile, "mobileNumberDownloadQuikrAppResponsiveHP").click();
                //Mapper.find(domFile, "mobileNumberDownloadQuikrAppResponsiveHP").click();
                Mapper.find(domFile, "mobileNumberDownloadQuikrAppResponsiveHP").clear();
                Mapper.find(domFile, "mobileNumberDownloadQuikrAppResponsiveHP").sendKeys(number);
            }
        }
        if (Mapper.waitForElementToBeVisible(domFile, "submitButtonDownloadQuikrAppResponsiveHP") == true) {
            Mapper.waitForElementToBeClickable(domFile, "submitButtonDownloadQuikrAppResponsiveHP");
            Mapper.find(domFile, "submitButtonDownloadQuikrAppResponsiveHP").click();
                Alert alert= switchTo().alert();
                String text=alert.getText();
                alert.accept();
                if (text.equals("Please enter a valid phone number")) {
                    flag= true;
                } else
                    return false;
            }
            return flag;
    }

    public void openFabIcon(){
        Mapper.find(domFile,"fabIconResponsiveHP").click();
    }

    public void closeFabIcon(){
        if(Mapper.waitForElementToBeClickable(domFile, "fabIconOpenedResponsiveHP")==true) {
            Mapper.find(domFile, "fabIconOpenedResponsiveHP").click();
        }
    }

    public boolean isFabIconDisplayed(){
        boolean flag=false;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Mapper.find(domFile,"createAlertFabIconResponsiveHP").isDisplayed()==true){
            flag=true;
        }
        else
            return false;

        if(Mapper.find(domFile,"postAdFabIconResponsiveHP").isDisplayed()==true){
            flag=true;
        }
        else
            return false;

        if(Mapper.find(domFile,"checkMSPFabIconResponsiveHP").isDisplayed()==true){
            flag=true;
        }
        else
            return false;

        return flag;
    }

    public boolean isGoogleAdsHeaderdisplayed(){
        List<WebElement> googleAds=Mapper.finds(domFile,"googleAdsHeaderResponsiveHP");
        if(googleAds.size()==1){
            return true;
        }
        else
            return false;
    }

    public boolean isGoogleAdsDisplayedTrendingandRecommendedAds(){
        boolean flag=false;
        if(Mapper.find(domFile,"googleAdsRightBannerResponsiveHP")!=null){
            flag=true;
        }
        else
            return false;
        if(Mapper.find(domFile, "googleAdsBottomBannerResponsiveHP")!=null){
            flag=true;
        }
        else{
            return false;
        }
        return flag;
    }

    public boolean isCarsBelowTrendingAds(){
        WebElement element=Mapper.find(domFile,"carsBelowTrendingResponsiveHP");
        if(element!=null){
            return true;
        }
        else
            return false;
    }


    public boolean ishomeBelowCarsAds(){
        WebElement element=Mapper.find(domFile,"homeBelowCarsResponsiveHP");
        if(element!=null){
            return true;
        }
        else
            return false;
    }

    public void clickQNXTIcon(){
        if(Mapper.waitForElementToBeClickable(domFile, "qnxtIconResponsiveHP")==true) {
            Mapper.find(domFile, "qnxtIconResponsiveHP").click();
        }
    }

    public void clickViewAllchat(){
        if(Mapper.waitForElementToBeClickable(domFile, "viewAllChatsQnxtIconResponsiveHP")==true) {
            Mapper.find(domFile, "viewAllChatsQnxtIconResponsiveHP").click();
        }
    }

    public boolean validateDownloadAppTopLeftResponsiveHP(){
        boolean flag=false;
        if(Mapper.waitForElementToBeVisible(domFile, "DownloadAppTopLeftResponsiveHP")==true){
            flag=true;
            Mapper.find(domFile,"DownloadAppTopLeftResponsiveHP").click();{
                if (getCurrentLocation().contains("quikr.com/app")){
                    flag=true;
                }
                else
                    return false;
            }
        }
        else
            return false;

        return flag;
    }

    public boolean isExploreOptionworking(Integer optionnumber){
        Mapper.findAndReplace(domFile, "ExploreOptions", new String[]{Integer.toString(optionnumber)}).click();
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentlocation=getCurrentLocation();
        String[] urls={"Cars-Bikes","","Services","jobs","Mobiles-Tablets","Electronics-Appliances","Home-Lifestyle"};

        if(currentlocation.contains(urls[optionnumber-1])){
            return true;
        }
        else
            return false;
    }

    public boolean validateMSPworkingResponsiveHP(){
        boolean flag=false;
        if(Mapper.waitForElementToBeClickable(domFile, "mspIconTopResponsiveHP")==true){
            logger.info("<mspIconTopResponsiveHP> found clickable....");
            flag=true;
            Mapper.find(domFile,"mspIconTopResponsiveHP").click();
            logger.info("<mspIconTopResponsiveHP> clicked....");
            Mapper.waitForElementToBeInvisible(domFile, "mspIconTopResponsiveHP",15);
            if(Mapper.find(domFile,"mspIconTopResponsiveHP")!=null){
                logger.info("<mspIconTopResponsiveHP> still found in webpage....");
                Mapper.find(domFile,"mspIconTopResponsiveHP").click();
                logger.info("<mspIconTopResponsiveHP> clicked again....");
            }
            if(getCurrentLocation().contains("msp")){
                logger.info("Url contains <msp>, flag true....");
                flag=true;
            }
            else
                return false;
        }
        else
            return false;

        return flag;
    }


    public boolean checkQuikrLogoAtFooter()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "quikrLogoFooterResponsiveHP")==true) {
            retVal= true;
        }
        else
        {
            logger.info("Quikr at footer is missing or some other problem. PLease check!");
            retVal= false;
        }
        return retVal;
    }

    public boolean validatePopularCitiesAtFooter()
    {
        boolean retVal=false;
        String[] footerPopularCities = {"Ahmedabad","Bangalore","Chandigarh","Chennai","Coimbatore","Delhi","Gurgaon","Hyderabad","Jaipur","Kochi","Kolkata","Lucknow","Mumbai","Pune","Trivandrum","All Cities"};

        List<WebElement> elmCities = Mapper.finds(domFile,"popularCitiesFooterResponsiveHP");
        for (int i=0;i<elmCities.size();i++)
        {
            retVal=binarySearch(elmCities.get(i).getText(),footerPopularCities);
        }
        logger.info("retval value :: " + retVal);
        return retVal;
    }

    public void clickAboutUs()
    {
        Mapper.find(domFile,"AboutUsStaticLinkResponsiveHP").click();
    }

    public void clickContactUs()
    {
        Mapper.find(domFile,"ContactUsStaticLinkResponsiveHP").click();
    }

    public void clickCareers()
    {
        Mapper.find(domFile,"CareersStaticLinkResponsiveHP").click();
    }

    public void clickQuikrVideos()
    {
        Mapper.find(domFile,"QuikrVideosStaticLinkResponsiveHP").click();
    }

    public void clickAdvertiseWithUs()
    {
        Mapper.find(domFile,"AdvertiseWithUSStaticLinkResponsiveHP").click();
    }

    public void clickHelp()
    {
        Mapper.find(domFile,"HelpStaticLinkResponsiveHP").click();
    }

    public void clickPremiumAds()
    {
        Mapper.find(domFile,"PremiumAdsStaticLinkResponsiveHP").click();
    }

    public void clickListingPolicy()
    {
        Mapper.find(domFile,"ListingPolicyStaticLinkResponsiveHP").click();
    }

    public void clickTermsOfUse()
    {
        Mapper.find(domFile,"TermsStaticLinkResponsiveHP").click();
    }

    public void clickPrivacyPolicy()
    {
        Mapper.find(domFile,"PrivacyPolicyResponsiveHP").click();
    }

    public void clickQuikrXPolicy()
    {
        Mapper.find(domFile,"QuikrXPolicyResponsiveHP").click();
    }

    public void clickSitemap()
    {
        Mapper.find(domFile,"SitemapResponsiveHP").click();
    }

    public void clickNews()
    {
        Mapper.find(domFile,"NewsResponsiveHP").click();
    }

    public boolean validateCreateAlertSection()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"CreateAlertLabelResponsiveHP")==true)
        {
            String labelText = Mapper.find(domFile,"CreateAlertLabelResponsiveHP").getText();
            if (labelText.equals("I Want To Receive Alerts")==true)
            {
                retVal = true;
            }
            else
            {
                return false;
            }
        }
        return retVal;
    }

    public void clickOnExploreHomes()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"ExploreHomeLinkResponsiveHP")==true)
        {
            Mapper.find(domFile,"ExploreHomeLinkResponsiveHP").click();
        }
        else
        {
            logger.info("Explore home link absent or invisible.");
        }
    }

    public void clickFirstSubCatHome()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"FirstSubCatHomeResponsiveHP")==true)
        {
            Mapper.find(domFile,"FirstSubCatHomeResponsiveHP").click();
        }
        else
        {
            logger.info("Said Subcat is not visible.");
        }
    }

    public boolean isLanguageDropDownworking(){
        boolean flag=false;
        if(Mapper.waitForElementToBeVisible(domFile, "languageDropDownResponsiveHP")==true){
            logger.info("<languageDropDownResponsiveHP> found visible....");
            flag=true;
            Mapper.find(domFile,"languageDropDownResponsiveHP").click();
            logger.info("<languageDropDownResponsiveHP> clicked....");
            if(Mapper.find(domFile,"languageDropDown")!=null){
                logger.info("<languageDropDownResponsiveHP> still present on the page....");
                Mapper.find(domFile,"languageDropDownResponsiveHP").click();
                logger.info("<languageDropDownResponsiveHP> clicked again....");
            }
            for(int i=1;i<=8;i++) {
                if (Mapper.findAndReplace(domFile, "languageDropDownOptionsResponsiveHP", new String[]{Integer.toString(i)}).isDisplayed()) {
                    flag = true;
                    logger.info("<languageDropDownOptionsResponsiveHP> being displayed. Displayed i="+i);
                }
                else{
                 logger.info("Language Option Number "+i+" is not visible");
                    return false;
                }
            }

        }
        else {
            return false;
        }
        return  flag;
    }

    public boolean isCartOptionworking(){
        boolean flag=false;
        if(Mapper.waitForElementToBeVisible(domFile, "cartResponsiveHP")==true){
            flag=true;
            Mapper.find(domFile,"cartResponsiveHP").click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(getCurrentLocation().contains("QuikrX/Cart")){
                flag=true;
            }
            else{
                return false;
            }
        }
        else
        {
            return false;
        }
        return flag;
    }

    public boolean isSignInbuttonHeaderworking(){
        boolean flag=false;
        if(Mapper.waitForElementToBeClickable(domFile, "signInButtonResponsiveHP")==true){
            Mapper.find(domFile,"signInButtonResponsiveHP").click();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Mapper.find(domFile,"loginWindowResponsiveHP").isDisplayed()){
                    flag=true;
                }
                else {
                    return false;
                }
        }


        return flag;
    }


    public void clickSignOutbuttonHeader(){
        boolean flag=false;
        if(Mapper.waitForElementToBeClickable(domFile, "dropDownIconLoggedInAccountResponsiveHP")==true){
            Mapper.find(domFile,"dropDownIconLoggedInAccountResponsiveHP").click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement signinDropDown=Mapper.find(domFile,"signInDropDown");
            String attribute=signinDropDown.getAttribute("aria-expanded");
            System.out.println("Here " + attribute);
            if(attribute.equals("true")){
            Mapper.find(domFile,"SignOutLink").click();
            }

        }

    }
    public boolean validateAllCategoriesvisibleResponsiveHP(){
        List<WebElement> categories=Mapper.finds(domFile,"categoriesResponsiveHPBelowCarousel");
        if(categories.size()==14){
            return true;
        }
        else
            return false;
    }

    public boolean validateMyAccountHamburgerMenu(){
        clickHamburgerMenu();
        Mapper.find(domFile,"myaccountHamburger_ResponsiveHp").click();
        if(getCurrentLocation().contains("MyQuikr")){
            return true;
        }
        else
            return false;
    }

    public void clickSignInSignUpHamburgerMenu(){
        clickHamburgerMenu();
        Mapper.find(domFile,"signINbutton_HamburgerMenuResponsiveHP").click();
    }

    public boolean isSignInbuttonHamburgerworking()
    {
        boolean retVal = false;
        clickHamburgerMenu();
        if (Mapper.waitForElementToBeVisible(domFile, "signINbutton_HamburgerMenuResponsiveHP") == true) {
            Mapper.find(domFile, "signINbutton_HamburgerMenuResponsiveHP").click();
            if (Mapper.waitForElementToBeVisible(domFile, "loginWindowResponsiveHP") == true) {
                retVal= true;
            } else {
                retVal= false;
            }
        }
        return retVal;
    }

    public boolean isNewLoginwindowDisplayed(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Mapper.waitForElementToBeVisible(domFile, "loginWindowResponsiveHP");
        WebElement element = Mapper.find(domFile, "loginWindowResponsiveHP");
            return element.isDisplayed();

    }

    public boolean selectCityResponsiveHP(String city){
        boolean flag=false;
        if(Mapper.waitForElementToBeClickable(domFile,"cityDropDownIconResponsiveHP")==true) {
            Mapper.find(domFile, "cityDropDownIconResponsiveHP").click();
            List<WebElement> citiesonDropDown = Mapper.finds(domFile, "popularCitiesDropDownIconResponsiveHP");
            if (citiesonDropDown.size() == 17) {
                flag = true;
            } else {
                return false;
            }
        }
            else{
            logger.info("Unable to click on City Drop Down Icon on Responsive HP");
            return false;
        }
        if (Mapper.waitForElementToBeClickable(domFile, "inputCityDropDownIconResponsiveHP")==true) {
            Mapper.find(domFile, "inputCityDropDownIconResponsiveHP").click();
            Mapper.find(domFile, "inputCityDropDownIconResponsiveHP").click();
            Mapper.find(domFile, "inputCityDropDownIconResponsiveHP").sendKeys(city);
            Mapper.find(domFile, "inputCityDropDownIconResponsiveHP").clear();
            Mapper.find(domFile, "inputCityDropDownIconResponsiveHP").sendKeys(city);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Mapper.findAndReplace(domFile, "selectCityDropDownIconResponsiveHP", new String[]{city}).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getCurrentLocation().contains(city.toLowerCase())) {
                zoomOut(2);
                flag = true;
            } else {
                return false;
            }

        }
        else
        { logger.info("Unable to Click on City Input Drop Down box");
            return false;
        }
        return flag;
    }

    public void clickFacebookLoginResponsiveHP(){
        if(isSignInbuttonHeaderworking()==true) {
            WebElement facebook=Mapper.find(domFile, "facebookLoginResponsiveHP");
            switchtoPopup(facebook);
        }else if (Mapper.waitForElementToBeInvisible(domFile,"facebookLoginResponsiveHP")==true){
            logger.info("Facebook login button was clicked at first attempt I guess....");
        }
        else if (Mapper.waitForElementToBeInvisible(domFile,"facebookLoginResponsiveHP")==false){
            logger.info("Facebook login button was not clicked on first attempt. Attempting click the second time....");
            WebElement facebook=Mapper.find(domFile, "facebookLoginResponsiveHP");
            switchtoPopup(facebook);
        }
        else
        {
            logger.info("Sign In Button Not Working So unable to Open Facebook Login Window");
        }
    }

    public void clickCategoryResponsiveHP(int i)
    {
        if (Mapper.waitForElementToBeClickable(domFile, "categoryDropDownIconResponsiveHP")==true)
        {
            Mapper.find(domFile, "categoryDropDownIconResponsiveHP").click();

            if (Mapper.waitForElementToBeVisible(domFile, "categoryDropDownResponsiveHP") == true) {
                String text = Mapper.findAndReplace(domFile, "categoriesResponsiveHP", new String[]{Integer.toString(i)}).getText();
                Mapper.findAndReplace(domFile, "categoriesResponsiveHP", new String[]{Integer.toString(i)}).click();
            }
            else {
                Mapper.find(domFile, "categoryDropDownIconResponsiveHP").click();
                if (Mapper.waitForElementToBeVisible(domFile, "categoryDropDownResponsiveHP") == true) {
                    String text = Mapper.findAndReplace(domFile, "categoriesResponsiveHP", new String[]{Integer.toString(i)}).getText();
                    Mapper.findAndReplace(domFile, "categoriesResponsiveHP", new String[]{Integer.toString(i)}).click();
                }
                else {
                    logger.info("Required category from drop down is not present. Please check!");
                 }
            }
        }
        else {
            logger.info("Categories dropdown in homepage didn't get clicked or is not visible. Please check!");
        }
    }

    public boolean validateAutoSuggestSearch(String searchKeyword){
        boolean flag=false;
        Mapper.find(domFile,"searchKeywordResponsiveHP").click();
        Mapper.find(domFile,"searchKeywordResponsiveHP").sendKeys(searchKeyword);
        if(Mapper.waitForElementToBeVisible(domFile,"searchResultsResponsiveHP")==true) {
            Mapper.waitForElementToBeVisible(domFile, "autoSuggestKeywordsResponsiveHP");
            List<WebElement> autosuggestelements = Mapper.finds(domFile, "autoSuggestKeywordsResponsiveHP");
            Set autosuggestKeywords = new HashSet();
            for (int i = 0; i < autosuggestelements.size(); i++) {
                autosuggestKeywords.add(autosuggestelements.get(i).getText());
            }

            Iterator iterator = autosuggestKeywords.iterator();
            while (iterator.hasNext()) {
                String element = (String) iterator.next();
                if (StringUtils.containsIgnoreCase(element, searchKeyword)) {
                    flag = true;
                } else {
                    return false;
                }

            }
        }
        return flag;
    }

    public boolean validateScrolltoTop(){
        Mapper.find(domFile, "gotopResponsiveHP").click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Mapper.find(domFile,"gotopResponsiveHP").isDisplayed()){
            return false;
        }
        else
            return true;

    }

    public void skipLoginWindow(){
//        if(isNewLoginwindowDisplayed()==true){
//            Mapper.find(domFile,"skipLoginWindowResponsiveHP").click();
//        }
    }

    public boolean isskipLoginWindowAvailable(){
            return isNewLoginwindowDisplayed();

    }

    public boolean iscategoryPresentbelowAnotherCategory(String aboveoptionnumber, String aboveoptiontext, String belowoptionnumber, String belowoptiontext){
     if(Mapper.findAndReplace(domFile, "CategoryPresentBelowOtherCategoryResponsiveHP", new String[]{aboveoptionnumber, aboveoptiontext, belowoptionnumber, belowoptiontext})!=null){
         return true;
     }
        else
         return false;
    }

    public boolean isCarspresentBelowTrendingAds(){
    if (Mapper.find(domFile, "CarsPresentBelowTrendingAdsCategoryResponsiveHP")!=null){
        return true;
        }

        else
        return false;
    }


    /**
     * select category from drop-down
     * @param categoryName
     */
    public void selectCategoryFromDropDown(QuikrEnums.CategoryName categoryName)
    {
        Mapper.find(domFile,"selectCategoryDropDown").click();

        Mapper.findAndReplace(domFile, "categoryNameDropDown", new String[]{categoryName.toString()}).click();

    }

    /**
     * select category from home page
     * @param categoryName
     */
    public void selectCategoryFromHomePage(QuikrEnums.CategoryName categoryName)
    {
        if (categoryName.toString().contains("Education")){
            scrollVerticallWithCords(0,200);
        }
        sleep(3000);
        Mapper.findAndReplace(domFile, "categoryNameHomePage", new String[]{categoryName.toString()}).click();
    }
}
