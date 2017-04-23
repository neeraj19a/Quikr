package com.quikr.website.horizontal.alert;

/**
 * Created by rohanbajaj on 29/07/15.
 */

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.*;

import static com.quikr.utils.PropertyReader.getProperties;

public class AlertPage extends PageBase {

    public AlertPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ALERT_DOM_FILE");

    public boolean createAlertFromHeaderPage(String email, String phoneNumber)
    {
        Mapper.waitForElementToBeVisible(domFile, "selectCategoryForAlert");
        Mapper.find(domFile,"selectCategoryForAlert").click();
        Mapper.find(domFile,"CarsAndBikesCategory").click();
        Mapper.find(domFile,"selectSubcategoryForAlert").click();
        Mapper.find(domFile,"BicyclesSubcategory").click();
        Mapper.find(domFile,"selectCity").click();
        Mapper.find(domFile,"Bangalore").click();
        Mapper.find(domFile,"selectLocality").click();
        Mapper.find(domFile,"Locality").click();
        Mapper.find(domFile,"selectEmail").click();
        Mapper.find(domFile,"selectEmail").clear();
        Mapper.find(domFile,"selectEmail").sendKeys(email);
        Mapper.find(domFile,"enterPhoneNumber").click();
        Mapper.find(domFile,"enterPhoneNumber").clear();
        Mapper.find(domFile,"enterPhoneNumber").sendKeys(phoneNumber);
        Mapper.find(domFile,"submitButton").click();
        Mapper.waitForElementToBeVisible(domFile,"recommendedAlertsPage");
        if(isElementPresent("recommendedAlertsPage"))
            return true;
        return false;
    }
    //Creaye alert from Snb Page
    // created by swatantra isngh
    public boolean createAlertFromSnbPage(String email, String phoneNumber)
    {

//        Mapper.waitForElementToBeVisible(domFile,"NavigateToSnb");
//        Mapper.find(domFile,"NavigateToSnb").click();
        Mapper.waitForElementToBeVisible(domFile, "snbAlert");
        Mapper.find(domFile,"snbAlert").click();
        Mapper.waitForElementToBeVisible(domFile, "selectCategoryForAlert");
        Mapper.find(domFile,"selectCategoryForAlert").click();
        Mapper.find(domFile,"CarsAndBikesCategory").click();
        Mapper.find(domFile,"selectSubcategoryForAlert").click();
        Mapper.find(domFile,"BicyclesSubcategory").click();
        Mapper.find(domFile,"price").sendKeys("5000");
        Mapper.find(domFile,"snblocality").click();
        Mapper.find(domFile,"Locality").click();
        Mapper.find(domFile,"selectEmail").click();
        Mapper.find(domFile,"selectEmail").clear();
        Mapper.find(domFile,"selectEmail").sendKeys(email);
        Mapper.find(domFile,"enterPhoneNumber").click();
        Mapper.find(domFile,"enterPhoneNumber").clear();
        Mapper.find(domFile,"enterPhoneNumber").sendKeys(phoneNumber);
        Mapper.find(domFile,"SnbAlertSubmit").click();
        Mapper.waitForElementToBeVisible(domFile,"recommendedAlertsPage");
        if(isElementPresent("recommendedAlertsPage"))
            return true;
        return false;
    }

    public void setCategory(String category)
    {
        //Mapper.waitForElementToBeVisible(domFile,"selectCategoryForAlert");
        //Mapper.waitForElementToBeClickable(domFile,"selectCategoryForAlert");
        Mapper.find(domFile,"selectCategoryForAlert").click();
        Mapper.find(domFile,"selectCategoryForAlert").click();
        Mapper.waitForElementToBeVisible(domFile, "categoryList");
        Mapper.findChildElement(domFile, category, "categoryList", "a", true).click();
    }

    public void setSubcategory(String subcategory)
    {
        Mapper.find(domFile,"selectSubcategoryForAlert").click();
        Mapper.waitForElementToBeVisible(domFile, "subcategoryList");
        Mapper.findChildElement(domFile, subcategory, "subcategoryList", "a", true).click();
    }

    public void setAdType(String ad)
    {
        Mapper.waitForElementToBeClickable(domFile, "adType");
        Mapper.find(domFile, "adType").click();

        //check if drop-down appear or not, if not click on ad type once again
        for(int i=1; i<=10; i++)
        {
            if (!Mapper.find(domFile, "adTypeList").isDisplayed())
            {
                Mapper.find(domFile, "adType").click();
            }
            else
            {
                break;
            }
        }

        Mapper.findChildElement(domFile, ad, "adTypeList", "a", false).click();
    }

    public void setRole(String role)
    {
        Mapper.find(domFile,"role").click();
        Mapper.waitForElementToBeVisible(domFile, "roleList");
        Mapper.findChildElement(domFile, role, "roleList", "a", false).click();
    }

    public void setEducation(String education)
    {
        Mapper.find(domFile, "education").click();
        Mapper.waitForElementToBeVisible(domFile, "educationList");
        Mapper.findChildElement(domFile, education, "educationList", "a", false).click();
    }

    public void setExperience(String experience)
    {
        Mapper.find(domFile,"experience").click();
        Mapper.waitForElementToBeVisible(domFile, "experienceList");
        Mapper.findChildElement(domFile, experience, "experienceList", "a", false).click();
    }

    public void setLocality(String locality)
    {
        //akhil will modify this function for the time being using Thread
        try{
            Thread.sleep(2000);
        }
        catch (Exception e){}
        Mapper.find(domFile, "selectLocality").click();
        WebElement webElement;

        if(Mapper.find(domFile, "localityListCheckbox").isDisplayed())
        {
            webElement = Mapper.findChildElement(domFile, locality, "localityListCheckbox", "label", true);
        }
        else
        {
            webElement = Mapper.findChildElement(domFile, locality, "localityListLabel", "a", true);
        }

        webElement.click();
    }

    public void setEmail(String email)
    {
        Mapper.find(domFile,"selectEmail").click();
        Mapper.find(domFile,"selectEmail").clear();
        Mapper.find(domFile,"selectEmail").click();
        Mapper.find(domFile, "selectEmail").sendKeys(email);
    }

    public void setPhoneNumber(String phoneNumber)
    {
        Mapper.find(domFile,"enterPhoneNumber").click();
        Mapper.find(domFile,"enterPhoneNumber").clear();
        Mapper.find(domFile, "enterPhoneNumber").sendKeys(phoneNumber);
    }

    public void submitAlert()
    {
        Mapper.find(domFile, "submitButton").click();
    }

    public boolean validateCreateAlert()
    {
        Mapper.waitForElementToBeVisible(domFile, "recommendedAlertsPage");
        if(isElementPresent("recommendedAlertsPage"))
            return true;
        return false;
    }

    public boolean unsubscribeAlertNotHappy(String password)
    {
        if(isElementPresent("unsubscribeAlert"))
        {
            Mapper.find(domFile, "unsubscribeAlert").click();
//            Mapper.find(domFile, "unsubscribePassword").sendKeys(password);
//            Mapper.find(domFile, "unsubscribeLogin").click();
            Mapper.find(domFile, "notHappy").click();
            Mapper.find(domFile, "unsubscribeSubmitButton").click();
            if (isElementPresent("unsubscribeSuccessMessage"))
                return true;
            return false;
        }
        else
            return true;
    }

    public boolean unsubscribeAlertFoundJob(String password)
    {
        if(isElementPresent("unsubscribeAlert"))
        {
            Mapper.find(domFile, "unsubscribeAlert").click();
//            Mapper.find(domFile, "unsubscribePassword").sendKeys(password);
//            Mapper.find(domFile, "unsubscribeLogin").click();
            Mapper.find(domFile, "foundJob").click();
            Mapper.find(domFile, "unsubscribeSubmitButton").click();
            if (isElementPresent("unsubscribeSuccessMessage"))
                return true;
            return false;
        }
        else
            return true;
    }

    public boolean unsubscribeAlertOtherReason(String password)
    {
        if(isElementPresent("unsubscribeAlert"))
        {
            Mapper.find(domFile, "unsubscribeAlert").click();
//            Mapper.find(domFile, "unsubscribePassword").sendKeys(password);
//            Mapper.find(domFile, "unsubscribeLogin").click();
            Mapper.find(domFile, "otherReason").click();
            Mapper.find(domFile,"unsubscribeReason").sendKeys("not satisfied");
            Mapper.find(domFile, "unsubscribeSubmitButton").click();
            if (isElementPresent("unsubscribeSuccessMessage"))
                return true;
            return false;
        }
        else
            return true;
    }

    public void setLocalityInAlertPopUp(String changeLocality)
    {
        Mapper.find(domFile,"setLocalityInAlertPopUp").click();
        //Mapper.find(domFile, "Locality").click();

        WebElement webElement;

        if(Mapper.find(domFile, "localityListCheckboxInAletPopup").isDisplayed())
        {
            webElement = Mapper.findChildElement(domFile, changeLocality, "localityListCheckboxInAletPopup", "label", true);
        }
        else
        {
            webElement = Mapper.findChildElement(domFile, changeLocality, "localityListLabelInAlertPopup", "a", true);
        }

        webElement.click();
    }
    //function to set submit button in alert popup
    public void setSubmitButtonInAlertPopUp()

    {
        Mapper.find(domFile, "submitButtonInInAlertButton").click();
    }



    //Responsive Page Alert Section

    public void selectCategory(String option)
    {
        if(Mapper.waitForElementToBeClickable(domFile, "selectCategory")==true) {
            WebElement element1=Mapper.find(domFile, "selectCategory");
            element1.click();

            //Handling to Click on Jobs Category
            if(option.equals("Jobs"))
            {
                option = "Home & Lifestyle";
                WebElement element=Mapper.findAndReplace(domFile, "selectCategoryOption", new String[]{option});
                getElementintoView(element);
                element.click();
                sleep(1000);
                element1.click();
                option = "Jobs";
                WebElement element2=Mapper.findAndReplace(domFile, "selectCategoryOption", new String[]{option});
                getElementintoView(element2);
                scrollVerticallWithCords(2800,2500);
                element2.click();
            }

            WebElement element=Mapper.findAndReplace(domFile, "selectCategoryOption", new String[]{option});
            getElementintoView(element);
            logger.info("Trying to Click on Category-->" +element.getText());
            element.click();
            sleep(3000);
            scrollVerticallWithCords(2800, 2500);
        }
    }
    //Responsive Page Alert Section

    public void selectCategory(int optionNumber)
    {
        if(Mapper.waitForElementToBeClickable(domFile, "selectCategory")==true) {
            WebElement element1=Mapper.find(domFile, "selectCategory");
            element1.click();

            //Handling to Click on Jobs Category
            if(optionNumber == 6)
            {
                optionNumber = 7;
                WebElement element=Mapper.findAndReplace(domFile, "selectCategoryOption", new String[]{Integer.toString(optionNumber)});
                getElementintoView(element);
                element.click();
                sleep(1000);
                optionNumber = 6;
                scrollVerticallWithCords(2800,2500);
                element1.click();
            }

            WebElement element=Mapper.findAndReplace(domFile, "selectCategoryOption", new String[]{Integer.toString(optionNumber)});
            //getElementintoView(element);
            logger.info("Trying to Click on Category-->" +element.getText());
            element.click();
            sleep(3000);
            scrollVerticallWithCords(2800, 2500);
        }
        }


    public void selectSubCategory(String option) {
        sleep(3000);
        closeTheYoutubeFrame();
        Mapper.find(domFile, "selectSubCategory").click();
        WebElement element=Mapper.findAndReplace(domFile, "selectSubCategoryOption", new String[]{option});
        getElementintoView(element);
        logger.info("Trying to Click on SubCategory-->" + element.getText());
        element.click();
        if(Mapper.find(domFile,"selectSubCategoryDisplayed").isDisplayed()==true){
            getElementintoView(element);
            logger.info("Again Trying to Click on SubCategory-->" + element.getText());
            element.click();
        }
        else {
            logger.info("Clicked on SubCategory-->" + element.getText());
        }
        scrollVerticallWithCords(2800,2500);
    }

    public void selectSubCategory(int optionNumber) {

            sleep(3000);
            Mapper.find(domFile, "selectSubCategory").click();
            WebElement element=Mapper.findAndReplace(domFile, "selectSubCategoryOption", new String[]{Integer.toString(optionNumber)});
            getElementintoView(element);
            logger.info("Trying to Click on SubCategory-->" + element.getText());
            element.click();
            if(Mapper.find(domFile,"selectSubCategoryDisplayed").isDisplayed()==true){
                getElementintoView(element);
                logger.info("Again Trying to Click on SubCategory-->" + element.getText());
                element.click();
            }
            else {
                logger.info("Clicked on SubCategory-->" + element.getText());
            }
            scrollVerticallWithCords(2800,2500);
        }


    public void selectadType(int optionNumber){

        WebElement element=null;
        if (Mapper.waitForElementToBeClickable(domFile, "adType") == true) {
            element=Mapper.find(domFile, "adType");
            scrollVerticallWithCords(2800,2500);
            element.click();
            }
            sleep(3000);
            //To handle Ad Type Drop Down options are displayed or Not
        WebElement option=null;
        if (Mapper.find(domFile, "adTypeOptionsDisplayed").isDisplayed() == true) {
                option=Mapper.findAndReplace(domFile, "adTypeOption", new String[]{Integer.toString(optionNumber)});
                getElementintoView(option);
                option.click();
            } else {
                element=Mapper.find(domFile, "adType");
                element.click();
                sleep(2000);
                option=Mapper.findAndReplace(domFile, "adTypeOption", new String[]{Integer.toString(optionNumber)});
                getElementintoView(option);
                option.click();
                logger.info("Clicked on Ad Type -->"+option.getText());
            }

        scrollVerticallWithCords(2800,2500);
        }



    public void selectBrand(){
        if(Mapper.waitForElementToBeClickable(domFile, "brandName")==true) {
            Mapper.find(domFile, "brandName").click();
        }
        List<WebElement> selectBrandOptionOptions=Mapper.finds(domFile, "selectBrandOption");
        int selectBrandOptionOptionsSize=selectBrandOptionOptions.size();
        int randomOption=new Random().nextInt(selectBrandOptionOptionsSize);
        WebElement element=selectBrandOptionOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Brand-->" + selectBrandOptionOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900,2800);
    }


    public void clickRoleJobs(){
        Mapper.find(domFile,"roleJobs").click();
        List<WebElement> roleJobsOptions=Mapper.finds(domFile, "roleJobsOptions");
        int roleJobsOptionsSize=roleJobsOptions.size();
        int randomOption=new Random().nextInt(roleJobsOptionsSize);
        WebElement element=roleJobsOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        scrollVerticallWithCords(2900,2800);
    }

    public void clickEducationJobs(){
        Mapper.find(domFile,"education").click();
        List<WebElement> educationOptions=Mapper.finds(domFile, "educationOptions");
        int educationSize=educationOptions.size();
        int randomOption=new Random().nextInt(educationSize);
        logger.info("Clicking on Option"+educationOptions.get(randomOption).getText());
        WebElement element=educationOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        scrollVerticallWithCords(2900,2800);
    }

    public void clickExperienceJobs(){
        Mapper.find(domFile,"experience").click();
        List<WebElement> experienceOptions=Mapper.finds(domFile, "experienceOptions");
        int experienceSize=experienceOptions.size();
        int randomOption=new Random().nextInt(experienceSize);
        WebElement element=experienceOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        scrollVerticallWithCords(2900, 2800);
    }


    public void clickVehicleType(){
        Mapper.find(domFile,"customVehicleType").click();
        List<WebElement> customVehicleTypeOptions=Mapper.finds(domFile, "customVehicleTypeOption");
        int customVehicleTypeSize=customVehicleTypeOptions.size();
        int randomOption=new Random().nextInt(customVehicleTypeSize);
        WebElement element=customVehicleTypeOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on VehicleTypeOption-->" + customVehicleTypeOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clickProductType(){
        Mapper.find(domFile,"productType").click();
        int randomOption=0;
        List<WebElement> productTypeOptions = Mapper.finds(domFile, "productTypeOption1");
        for(int i=0;i<productTypeOptions.size();i++) {
            if (productTypeOptions.get(i).isDisplayed()) {
                int elementListSize = productTypeOptions.size();
                randomOption = new Random().nextInt(elementListSize);
            }
        }
        WebElement element=productTypeOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on ProductType -->" + productTypeOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);

    }

    public void clickPetType(){
        Mapper.find(domFile,"petTypeDropDown").click();
        List<WebElement> petTypeOptions=Mapper.finds(domFile, "petTypeOption");
        int petTypeOptionsSize=petTypeOptions.size();
        int randomOption=new Random().nextInt(petTypeOptionsSize);
        logger.info("Clicking on Option-->" + petTypeOptions.get(randomOption).getAttribute("lblval"));
        WebElement element=petTypeOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        scrollVerticallWithCords(2900, 2800);

    }

    public void selectRandomhobby(){
        Mapper.waitForElementToBeClickable(domFile, "hobbyDropDown");
        Mapper.find(domFile,"hobbyDropDown").click();
        sleep(5000);
        List<WebElement> Options=Mapper.finds(domFile, "hobbyOption");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        //getElementintoView(element);
        element.click();
        sleep(2000);
        element.click();
        sleep(2000);
        element.click();
        //scrollVerticallWithCords(2900,2500);

    }

    public void selectRandomCourse(){
        Mapper.find(domFile,"courseDropDown").click();
        List<WebElement> Options=Mapper.finds(domFile, "courseOption");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        scrollVerticallWithCords(2900, 2800);

    }

    public void selectRandomCountry(){
        Mapper.find(domFile,"countryDropDown").click();
        List<WebElement> Options = Mapper.finds(domFile, "countryOptions");
        int OptionsSize = Options.size();
        int randomOption = new Random().nextInt(OptionsSize);
        WebElement element = Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Country Option -->" + element.getAttribute("lblval"));
        //scrollVerticallWithCords(2900, 2800);

    }

    public void selectRandomservicesOffered(){
        Mapper.find(domFile,"servicesOfferedDropDown").click();
        List<WebElement> Options=Mapper.finds(domFile, "servicesOfferedDropDownOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on ServiceOffered Option -->" + Options.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clickCoachingDropDown(){
        Mapper.find(domFile,"coachingDropDown").click();
        List<WebElement> coachingOptions=Mapper.finds(domFile, "coachingDropDownOptions");
        int coachingOptionsSize=coachingOptions.size();
        int randomOption=new Random().nextInt(coachingOptionsSize);
        WebElement element=coachingOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Coaching Drop Down Option -->" + coachingOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clickboardEntranceDropDown(){
        Mapper.find(domFile,"boardEntranceDropDown").click();
        List<WebElement> Options=Mapper.finds(domFile, "boardEntranceDropDownOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Board Entrance Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clickSchoolBoardDropDown(){
        Mapper.find(domFile,"schoolBoardDropDown").click();
        List<WebElement> Options=Mapper.finds(domFile, "schoolBoardDropDownOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on School Board Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void clicksubjectsDropDown(){
        Mapper.find(domFile,"subjectsDropDown").click();
        List<WebElement> Options=Mapper.finds(domFile, "subjectsDropDownOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Subject Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
        //scrollVerticallWithCords(2900,2800);
    }

    public void clickGenre(){
        Mapper.find(domFile,"genre").click();
        List<WebElement> genreOptions=Mapper.finds(domFile, "genreOptions");
        int genreSize=genreOptions.size();
        int randomOption=new Random().nextInt(genreSize);
        WebElement element=genreOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Genre Drop Down Option -->" + genreOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clickStyle(){
        Mapper.find(domFile,"Style").click();
        List<WebElement> StyleOptions=Mapper.finds(domFile, "styleOptions");
        int StyleSize=StyleOptions.size();
        int randomOption=new Random().nextInt(StyleSize);
        WebElement element=StyleOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Style Drop Down Option -->" + StyleOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900,2800);
    }

    public void clickinstrument(){
        Mapper.find(domFile,"instrument").click();
        List<WebElement> instrumentOptions=Mapper.finds(domFile, "instrumentTypeOptions");
        int instrumentSize=instrumentOptions.size();
        int randomOption=new Random().nextInt(instrumentSize);
        WebElement element=instrumentOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Instrument Drop Down Option -->" + instrumentOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900,2800);
    }

    public void clickEquipment(){
        Mapper.find(domFile,"equipment").click();
        List<WebElement> equipmentOptions=Mapper.finds(domFile, "equipmentTypeOptions");
        int equipmentSize=equipmentOptions.size();
        int randomOption=new Random().nextInt(equipmentSize);
        WebElement element=equipmentOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Equipment Drop Down Option -->" + equipmentOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clickAreaSqFt(){
        Mapper.find(domFile,"areaSqFt").click();
        List<WebElement> AreaSqFtOptions=Mapper.finds(domFile, "areaSqFtOption");
        int AreaSqFtOptionsSize=AreaSqFtOptions.size();
        int randomOption=new Random().nextInt(AreaSqFtOptionsSize);
        WebElement element=AreaSqFtOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on AreaSqFtOptions Drop Down Option -->" + AreaSqFtOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clicktypeServices(){
        Mapper.find(domFile, "typeServicesDropDown").click();
        List<WebElement> typeServicesOptions=Mapper.finds(domFile, "typeServicesDropDownOptions");
        int typeServicesOptionsSize=typeServicesOptions.size();
        int randomOption=new Random().nextInt(typeServicesOptionsSize);
        WebElement element=typeServicesOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on typeServicesOptions Drop Down Option -->" + typeServicesOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clicktypeOfLand(){
        Mapper.find(domFile, "typeOfLand").click();
        List<WebElement> typeOfLandOptions=Mapper.finds(domFile, "typeOfLandOption");
        int typeOfLandOptionsSize=typeOfLandOptions.size();
        int randomOption=new Random().nextInt(typeOfLandOptionsSize);
        WebElement element=typeOfLandOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on typeOfLandOptions Drop Down Option -->" + typeOfLandOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2800);
    }

    public void clickAccommodationFor(int optionNumber){
        Mapper.waitForElementToBeClickable(domFile, "accommodationFor");
        Mapper.find(domFile, "accommodationFor").click();
        Mapper.findAndReplace(domFile, "accommodationForOption", new String[]{Integer.toString(optionNumber)}).click();
    }


    public void clickFurnished(){
        WebElement furnished=Mapper.find(domFile, "furnished");
        getElementintoView(furnished);
        furnished.click();
        List<WebElement> furnishedOptions=Mapper.finds(domFile,"furnishedOption");
        int furnishedOptionSize=furnishedOptions.size();
        int randomOption=new Random().nextInt(furnishedOptionSize);
        WebElement element=furnishedOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on furnishedOptions Drop Down Option -->" + furnishedOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900, 2500);
    }

    public void clickproductTypeOptionElectronicsandAppliances(){
        Mapper.find(domFile,"productTypeElectronicsandAppliances").click();
        List<WebElement> productTypeElectronicsandAppliancesOptions=Mapper.finds(domFile,"productTypeOptionElectronicsandAppliances");
        int productTypeElectronicsandAppliancesSize=productTypeElectronicsandAppliancesOptions.size();
        int randomOption=new Random().nextInt(productTypeElectronicsandAppliancesSize);
        WebElement element=productTypeElectronicsandAppliancesOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on productTypeElectronicsandAppliancesOptions Drop Down Option -->" + productTypeElectronicsandAppliancesOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900,2800);
    }

    public void clickLookingForRealEstateOptions(int optionNumber){
        Mapper.find(domFile, "lookingForRealEstate").click();
        Mapper.findAndReplace(domFile, "lookingForRealEstateOptions", new String[]{Integer.toString(optionNumber)}).click();
        logger.info("Clicked on "+Mapper.findAndReplace(domFile, "lookingForRealEstateOptions", new String[]{Integer.toString(optionNumber)}).getText());
        scrollVerticallWithCords(2900,2800);
    }

    public void clickYouareRealEstateOptions(){
        Mapper.find(domFile, "YouareRealEstate").click();
        List<WebElement> youareOptions=Mapper.finds(domFile,"YouareRealEstateOptions");
        int youareOptionsSize=youareOptions.size();
        int randomOption=new Random().nextInt(youareOptionsSize);
        WebElement element=youareOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on YouareRealEstateOptions Drop Down Option -->" + youareOptions.get(randomOption).getText());
        scrollVerticallWithCords(2900,2800);
    }

    public void clickPeripheralType(){
        Mapper.find(domFile,"peripheralType").click();
        List<WebElement> peripheralTypeOptions=Mapper.finds(domFile,"peripheralTypeOption");
        int peripheralTypeOptionsSize=peripheralTypeOptions.size();
        int randomOption=new Random().nextInt(peripheralTypeOptionsSize);
        WebElement element=peripheralTypeOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on peripheralTypeOptions Drop Down Option -->" + peripheralTypeOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900,2800);
    }

    public void clickApplianceType(){
        Mapper.find(domFile,"applianceType").click();
        List<WebElement> applianceTypeOptions=Mapper.finds(domFile,"applianceTypeOption");
        int applianceTypeOptionsSize=applianceTypeOptions.size();
        int randomOption=new Random().nextInt(applianceTypeOptionsSize);
        WebElement element=applianceTypeOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on applianceTypeOptions Drop Down Option -->" + applianceTypeOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900,2500);
    }

    public void clickAccessoryType(){
        Mapper.find(domFile, "accessoryType").click();
        List<WebElement> accessoryTypeOptions=Mapper.finds(domFile,"accessoryTypeOptions");
        int accessoryTypeOptionsSize=accessoryTypeOptions.size();
        int randomOption=new Random().nextInt(accessoryTypeOptionsSize);
        WebElement element=accessoryTypeOptions.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on accessoryTypeOptions Drop Down Option -->" + accessoryTypeOptions.get(randomOption).getAttribute("lblval"));
        scrollVerticallWithCords(2900,2500);
    }

    public void selectYear() {
        if (Mapper.waitForElementToBeClickable(domFile, "selectYear") == true) {
            Mapper.find(domFile, "selectYear").click();
            List<WebElement> selectYearOption = Mapper.finds(domFile, "selectYearOption");
            int selectYearOptionSize = selectYearOption.size();
            int randomOption = new Random().nextInt(selectYearOptionSize);
            WebElement element=selectYearOption.get(randomOption);
            getElementintoView(element);
            element.click();
            logger.info("Selected Year -->" + selectYearOption.get(randomOption).getAttribute("lblval"));
        }
        scrollVerticallWithCords(2900,2500);
    }


    public void selectNoOfRooms(){
        scrollVerticallWithCords(2900,2800);
        Mapper.waitForElementToBeClickable(domFile, "noOfRooms");
        Mapper.find(domFile, "noOfRooms").click();
        List<WebElement> rooms=Mapper.finds(domFile,"noOfRoomsOption");
        int totalRoomsOptions=rooms.size();
        int randomoption=new Random().nextInt(totalRoomsOptions);
        WebElement element=rooms.get(randomoption);
        getElementintoView(element);
        logger.info("Trying to Click rooms -->" + element.getAttribute("lblval"));
        element.click();
       /* if(Mapper.find(domFile,"noOfRoomsDropDownisDisplayed").isDisplayed()==true){
            element=rooms.get(randomoption);
            getElementintoView(element);
            element.click();
            logger.info("Clicked rooms -->" + element.getAttribute("lblval"));

        }
        else {*/
            logger.info("Clicked rooms -->" + element.getAttribute("lblval"));

        scrollVerticallWithCords(2900,2500);

    }

    public void selectNumberofsims(){

        try{
                Mapper.find(domFile, "selectNumberOfSims").click();
                List<WebElement> elements=Mapper.finds(domFile,"selectNumberOfSims_Options");
                int randomOption=new Random().nextInt(elements.size());
                WebElement element=elements.get(randomOption);
                element.click();
                logger.info("Clicked on Sim Option -->"+element.getAttribute("lblval"));
        }
        catch (Exception e){
            logger.info("Might be Number of Sims is not available Option");
        }

        scrollVerticallWithCords(2900, 2800);

    }
    public void enterPrice(int price){
        if(Mapper.waitForElementToBeClickable(domFile,"price")==true) {
            Mapper.find(domFile, "price").click();
        }
        Mapper.find(domFile,"price").sendKeys(Integer.toString(price));
    }

    public void clickOwner(){
        scrollVerticallWithCords(2900, 2500);
        if(Mapper.waitForElementToBeClickable(domFile,"selectOwner")==true) {
            WebElement element=Mapper.find(domFile, "selectOwner");
            getElementintoView(element);
            element.click();
        }
        if(Mapper.waitForElementToBeClickable(domFile,"ownerOne")==true) {
            WebElement element=Mapper.find(domFile, "ownerOne");
            getElementintoView(element);
            element.click();
        }
    }

    public void selectCity(){

        scrollVerticallWithCords(2900, 2600);
        WebElement element=null;
        if(Mapper.waitForElementToBeClickable(domFile,"cityName")==true) {
            element=Mapper.find(domFile, "cityName");
            getElementintoView(element);
            element.click();
        }
        if(Mapper.waitForElementToBeClickable(domFile,"selectcityName")==true) {
            WebElement element1=Mapper.find(domFile, "selectcityName");
            getElementintoView(element1);
            element1.click();
        }
        if(Mapper.waitForElementToBeClickable(domFile,"cityName")==true) {
            element=Mapper.find(domFile, "cityName");
            getElementintoView(element);
            element.click();
        }
        if(Mapper.waitForElementToBeClickable(domFile,"selectcityName")==true) {
            WebElement element1=Mapper.find(domFile, "selectcityName");
            getElementintoView(element1);
            element1.click();
        }
    }

    public void selectCityService(){

        scrollVerticallWithCords(2900, 2700);
        WebElement element=null;
        if(Mapper.waitForElementToBeClickable(domFile,"cityName")==true) {
            element=Mapper.find(domFile, "cityName");
            getElementintoView(element);
            element.click();
        }
        if(Mapper.waitForElementToBeClickable(domFile,"AhmedabadCity")==true) {
            WebElement element1=Mapper.find(domFile, "AhmedabadCity");
            getElementintoView(element1);
            element1.click();
        }
    }


    public void setEmailAddress(String email){
        Mapper.find(domFile,"emailAddress").click();
        Mapper.find(domFile,"emailAddress").sendKeys(email);
        logger.info("Setting Email Address " + email);
    }

    public void setMobileNumber(String mobileNumber){
        scrollVerticallWithCords(2900, 2400);
        if(Mapper.waitForElementToBeClickable(domFile,"mobileNumber")==true){
            WebElement mobileNum=Mapper.find(domFile,"mobileNumber");
            getElementintoView(mobileNum);
            scrollVerticallWithCords(2900, 2700);
            mobileNum.click();
            Mapper.find(domFile,"mobileNumber").sendKeys(mobileNumber);
            logger.info("Setting Mobile Number" + mobileNumber);
        }

    }

    public void clickAlertButton(){
        if(Mapper.waitForElementToBeClickable(domFile,"createAlertButton")==true) {
            WebElement element=Mapper.find(domFile, "createAlertButton");
            getElementintoView(element);
            sleep(3000);
            element.click();
            logger.info("Clicking on Create Alert Button");
        }
    }

    public void masterCommonFields(int price, String email,String mobileNumber){

        enterPrice(price);
        selectCity();
        setEmailAddress(email);
        setMobileNumber(mobileNumber);
    }


    public void masterCommonFields(String email,String mobileNumber){

        selectCity();
        setEmailAddress(email);
        setMobileNumber(mobileNumber);
    }

    public void masterCommonFieldsMobile(int optionNumber,String email,String mobileNumber){

        selectBrand();
        selectCity();
        setEmailAddress(email);
        setMobileNumber(mobileNumber);
    }

    public void masterCommonFieldsCameras(String email,String mobileNumber){

        selectCity();
        setEmailAddress(email);
        setMobileNumber(mobileNumber);
    }

    public void masterCommonFieldsRealEstate(String email,String mobileNumber){

        selectCity();
        setEmailAddress(email);
        setMobileNumber(mobileNumber);
    }

    public void masterCommonFieldsServices(String email,String mobileNumber){

        selectCity();
        setEmailAddress(email);
        setMobileNumber(mobileNumber);
    }

    public void masterCommonFieldsJobs(String email,String mobileNumber){
        clickRoleJobs();
        clickEducationJobs();
        clickExperienceJobs();
        selectCity();
        setEmailAddress(email);
        setMobileNumber(mobileNumber);
    }



    public void selectRandomModel()
    {
        if(Mapper.waitForElementToBeClickable(domFile,"model")==true) {
            Mapper.find(domFile, "model").click();
        }
        List<WebElement> elementList = Mapper.finds(domFile,"modelDropDown");
        int visibleElementsCount = 0;
        List<WebElement> visibleElementsList = new LinkedList<WebElement>();

        for(int i=0; i<elementList.size(); i++)
        {
            if(elementList.get(i).isDisplayed())
            {
                visibleElementsList.add(visibleElementsCount, elementList.get(i));
                visibleElementsCount++;
            }
        }

        logger.info(visibleElementsCount);
        int randomOption = new Random().nextInt(visibleElementsCount);

        /*if(randomOption ==0)
        {
            randomOption++;
        }
        */logger.info(randomOption);
        WebElement element=visibleElementsList.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on Model -->" + visibleElementsList.get(randomOption).getAttribute("lblval"));
    }


    public void selectRandomLocality() {
        Mapper.find(domFile, "locality").click();
        int randomOption=0;
        List<WebElement> elementList = new LinkedList<WebElement>();
        boolean a = Mapper.find(domFile, "localityDropDownOptions").isDisplayed();
        boolean b = Mapper.find(domFile, "localityDropDownOptions1").isDisplayed();

        if (a == true) {
            elementList = Mapper.finds(domFile, "localityDropDown");
            int elementListSize = elementList.size();
            randomOption=elementListSize-(elementListSize-1);
            elementList.get(randomOption).click();
            logger.info("Clicked on Random Option -->"+elementList.get(randomOption).getAttribute("value"));

        }

        else if(b==true)
        {
            elementList = Mapper.finds(domFile, "localityDropDown1");
            int elementListSize = elementList.size();
            randomOption=elementListSize-(elementListSize-1);
            elementList.get(randomOption).click();
            logger.info("Clicked on Random Option -->"+elementList.get(randomOption).getText());

        }
    }

    public boolean isSNBPageLoaded(){
        Mapper.waitForElementToBeVisible(domFile, "createFreeAlertSNBPage");
        if(Mapper.find(domFile,"createFreeAlertSNBPage")!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public void selectDeliveryMode(){
        Mapper.find(domFile,"deliveryDropDown").click();
        List<WebElement> Options=Mapper.finds(domFile, "deliveryDropDownOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on delivery mode Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomCourseDomain(){
        Mapper.find(domFile,"courseDomainDropdown").click();
        sleep(3000);
        List<WebElement> Options=Mapper.finds(domFile, "courseDomainOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on course domain Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomCourseCategories(){
        Mapper.find(domFile,"courseCategories").click();
        sleep(3000);

        List<WebElement> options = Mapper.finds(domFile, "CourseCategoryOptions");
        ArrayList<WebElement> visibleElements = new ArrayList<WebElement>();
        for(WebElement element : options)
        {
            if(element.isDisplayed())
            {
                visibleElements.add(element);
            }
        }
        logger.info(visibleElements);
        visibleElements.get(0).click();
    }

    public void selectRandomCourses(){

        Mapper.find(domFile,"courses").click();
        sleep(3000);

        List<WebElement> options = Mapper.finds(domFile, "CoursesOption");
        ArrayList<WebElement> visibleElements = new ArrayList<WebElement>();
        for(WebElement element : options)
        {
            if(element.isDisplayed())
            {
                visibleElements.add(element);
            }
        }
        logger.info(visibleElements);
        visibleElements.get(0).click();
    }

    public void selectRandomCareerStream(){
        Mapper.find(domFile,"careerStream").click();
        sleep(3000);
        List<WebElement> Options=Mapper.finds(domFile, "careerStreamOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on course domain Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomSpecialization(){
        Mapper.find(domFile,"specialization").click();
        sleep(3000);

        List<WebElement> options = Mapper.finds(domFile, "specializationOptions");
        ArrayList<WebElement> visibleElements = new ArrayList<WebElement>();
        for(WebElement element : options)
        {
            if(element.isDisplayed())
            {
                visibleElements.add(element);
            }
        }
        logger.info(visibleElements);
        visibleElements.get(0).click();
    }

    public void selectRandomProgramType(){
        Mapper.find(domFile,"program").click();
        sleep(3000);
        List<WebElement> Options=Mapper.finds(domFile, "programOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on program type Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomCoursesDistanceLearning(){
        Mapper.find(domFile,"courseDistanceLearning").click();
        sleep(3000);
        List<WebElement> Options=Mapper.finds(domFile, "courseDistanceLearningOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on course from distance learning course Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomProgramsOffered(){
        Mapper.find(domFile,"programsOffered").click();
        sleep(3000);
        List<WebElement> Options=Mapper.finds(domFile, "programsOfferedOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on programs offered Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomEducationMethod(){
        Mapper.find(domFile,"educationMethod").click();
        sleep(3000);
        List<WebElement> Options=Mapper.finds(domFile, "educationMethodOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on education method Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomProductTypeTextBooks(){
        Mapper.find(domFile,"productTypeTextBooks").click();
        sleep(3000);
        List<WebElement> Options=Mapper.finds(domFile, "productTypeOptionsTextBooks");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on product type Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomIndustry(){
        Mapper.find(domFile,"industry").click();
        sleep(1000);
        List<WebElement> Options=Mapper.finds(domFile, "industryOptions");
        int OptionsSize=Options.size();
        int randomOption=new Random().nextInt(OptionsSize);
        WebElement element=Options.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on industry Drop Down Option -->" + Options.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomTrainingType(){
        Mapper.find(domFile,"trainingType").click();
        sleep(1000);

        List<WebElement> options = Mapper.finds(domFile, "trainingTypeOptions");
        ArrayList<WebElement> visibleElements = new ArrayList<WebElement>();
        for(WebElement element : options)
        {
            if(element.isDisplayed())
            {
                visibleElements.add(element);
            }
        }
        logger.info(visibleElements);
        visibleElements.get(0).click();
    }

    public void selectRandomKeyFeatures(){
        Mapper.find(domFile,"keyFeatures").click();
        sleep(1000);

        List<WebElement> options = Mapper.finds(domFile, "keyFeatureOptions");
        ArrayList<WebElement> visibleElements = new ArrayList<WebElement>();
        for(WebElement element : options)
        {
            if(element.isDisplayed())
            {
                visibleElements.add(element);
            }
        }
        logger.info(visibleElements);
        visibleElements.get(0).click();
    }

}

