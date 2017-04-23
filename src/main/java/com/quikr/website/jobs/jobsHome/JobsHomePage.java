package com.quikr.website.jobs.jobsHome;

import com.quikr.website.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by quikr on 5/10/15.
 */
public class JobsHomePage extends PageBase {

    public JobsHomePage(RemoteWebDriver driver) {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("JOBS_HOME_DOM_FILE");

    public void clickSearchJobBox() {
        Mapper.find(domFile, "inputjobsSearchBox").click();
    }

    public void selectJobRole(String jobrole) {
        Mapper.find(domFile, "inputjobsSearchBox").click();
        Mapper.findAndReplace(domFile, "jobRole", new String[]{jobrole}).click();
        Mapper.find(domFile, "jobSearch").click();
    }

    public String gettextSearchJobs() {
        WebElement searchjobs = Mapper.find(domFile, "inputjobsSearchBox");
        return searchjobs.getAttribute("placeholder").toString();
    }

    public void searchCity(String city) {
        getCityfromSuggestion(city);
        Mapper.findAndReplace(domFile, "citySearchSuggestion", new String[]{city}).click();
        Mapper.waitForElementToBeClickable(domFile, "jobSearchOldUI");
        Mapper.find(domFile, "jobSearchOldUI").click();
    }

    public boolean isSearchJobsBoxPresent() {
        return (Mapper.find(domFile, "inputjobsSearchBox").isDisplayed());
    }

    public boolean isTopRolesBoxpresent(){
        return isElementPresent("TopRolesBox");
    }

    public boolean isexactnumberofTopRolespresent() {
        boolean flag=false;
        for (int i = 2; i <= 13; i++) {
            if (Mapper.findAndReplace(domFile, "NumberofTopRoles", new String[]{Integer.toString(i)}).isDisplayed()) {
                flag=true;
            } else
                flag=false;
        }
        return flag;
    }

    public boolean isAllRolesPresent()
    {
        List<WebElement> allroles=Mapper.finds(domFile, "AllRoles");

        if (allroles.get(2).isDisplayed()){
        //if(allroles.size()==15){
            return true;
        }
        else
            return false;
    }

    public void clickAllRole(String rolename){
        Mapper.findAndReplace(domFile,"SelectRole_AllRoles",new String[]{rolename}).click();

    }

    public void clickTopRole(String rolename){
        Mapper.findAndReplace(domFile,"SelectRole_TopRoles",new String[]{rolename}).click();

    }

    public boolean isSelectCityPresent() {
        return (Mapper.find(domFile, "SelectCity").isDisplayed());
    }

    public String getJobfromSuggestion(String jobrole) {
        Mapper.find(domFile, "inputjobsSearchBox").click();
        Mapper.find(domFile, "inputjobsSearchBox").sendKeys(jobrole);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Mapper.findAndReplace(domFile, "jobsSearchBoxinput", new String[]{jobrole}).getText();
    }

    public boolean returnJobSearchdropDownOptionsCount() {
        List<WebElement> dropdown = Mapper.finds(domFile, "dropDownOptionsJobSearch");
        int numberofjobs = dropdown.size();
        if (numberofjobs >= 31) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkAdsonJobsHomePage()
    {
        List<WebElement> ads=Mapper.finds(domFile, "googleads");
        if(ads.size()>0)
        {
            return true;
        }
        else
            return false;
    }

    public boolean isJobSearchdropDownOptionsavailable() {
        List<WebElement> dropdown = Mapper.finds(domFile, "dropDownOptionsJobSearch");
        if (dropdown.size() > 0) {
            return true;
        } else
            return false;
    }

    public boolean isCitySearchdropDownOptionsavailable() {
        List<WebElement> dropdown = Mapper.finds(domFile, "dropDownOptionsCitySearch");
        if (dropdown.size() > 0) {
            return true;
        } else
            return false;
    }

    public void setCity(String cityname) {
        Mapper.find(domFile, "citySelectionIcon").click();
        Mapper.find(domFile, "citySelectionContainer").isDisplayed();
        Mapper.find(domFile, "inputcitySearchBox").sendKeys(cityname);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Mapper.findAndReplace(domFile, "citySearchSuggestion", new String[]{cityname}).click();
    }

    public String getCityfromSuggestion(String cityname) {
        Mapper.find(domFile, "citySelectionIcon").click();
        Mapper.find(domFile, "citySelectionContainer").isDisplayed();
        Mapper.find(domFile, "inputcitySearchBox").sendKeys(cityname);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Mapper.findAndReplace(domFile, "citySearchSuggestion", new String[]{cityname}).getText();
    }

    public boolean colorofSearchBox(String invalidcityname)
    {
        Mapper.find(domFile, "inputcitySearchBox").click();
        Mapper.find(domFile, "inputcitySearchBox").clear();
        Mapper.find(domFile, "inputcitySearchBox").sendKeys(invalidcityname);
        WebElement searchbox = Mapper.find(domFile, "jobSearchOldUI");
        String beforeclick = searchbox.getCssValue("background-color");
        logger.info("#OLD"+beforeclick+"$");
        searchbox.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String afterclick = searchbox.getCssValue("background-color");
        logger.info("#NEW" + afterclick + "$");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (beforeclick.contentEquals(afterclick)) {
            return false;
        } else
            return true;
    }

    public void clickSearchButton() {
        Mapper.waitForElementToBeClickable(domFile, "jobSearchOldUI");
        Mapper.find(domFile, "jobSearchOldUI").click();
    }

    public boolean isAllLanguageAvailable(){
        List<WebElement> languages1=Mapper.finds(domFile, "Languages1");
        List<WebElement> languages2=Mapper.finds(domFile,"Languages2");
        if(languages1.size()+languages2.size()==7){
            return true;
        }
        return false;
    }

    public void clickHindiLanguage(){
        Mapper.find(domFile,"hindiLanguageOption").click();
    }

    public void clickTamilLanguage(){
        Mapper.find(domFile,"TamilLanguageOption").click();
    }

    public void clickTeluguLanguage(){
        Mapper.find(domFile, "TeluguLanguageOption").click();
    }

    public void clickKannadaLanguage(){
        Mapper.find(domFile,"KannadaLanguageOption").click();
    }

    public String returnCenterTextJobsPage(){
        return Mapper.find(domFile, "centerTextJobsPage").getText();
    }

    public String CityPrePopulated(){

        return Mapper.find(domFile,"CityPrePopulated").getText();
    }

    public void setJobRole(String jobRole)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"inputjobsSearchBox")==true)
        {
            Mapper.find(domFile,"inputjobsSearchBox").sendKeys(jobRole);
        }
        else
        {
            logger.info("The search text field is not visible. Please check!");
        }
    }


    public void validatesetJobRoleAutocomplete(String jobRole)
    {
        Mapper.find(domFile,"inputjobsSearchBox").sendKeys(jobRole);
        Mapper.waitForElementToBeVisible(domFile, "jobRoleSearchFirstOption");
        Mapper.find(domFile,"jobRoleSearchFirstOption").click();
        clickSearchButton();
    }

    public boolean isJobTypesavailable(){
        List<WebElement> jobtypes=Mapper.finds(domFile,"JobTypes");
        if(jobtypes.size()==4){
            return true;
        }
        else
            return false;
    }

    public void enterFreeTextJobRole(String jobRole)
    {
        Mapper.find(domFile,"inputjobsSearchBox").sendKeys(jobRole);
    }

    public void enterJobCity(String jobCity)
    {
        WebElement elm = Mapper.find(domFile,"citySearchBox");
        elm.click();
        elm.clear();
        elm.sendKeys(jobCity);
    }

    public void searchJobsWithoutSelectingCity(String jobRole)
    {
        setJobRole(jobRole);
        clickSearchButton();
    }

    public boolean verifyJobTypeLabels()
    {
        boolean retVal = false;
        List<WebElement> elmJobTyp = Mapper.finds(domFile,"JobTypes");
        List<String> strJobTypes = new ArrayList();

        for (int i=0;i<elmJobTyp.size();i++)
        {
            strJobTypes.add(i,elmJobTyp.get(i).getText());
        }

        String[] expectedJobTypes = {"Work from home","Part time jobs","Full time jobs","Internships"};
        for (int i=0;i<expectedJobTypes.length;i++)
        {
            if (strJobTypes.contains(expectedJobTypes[i])==true)
            {
                retVal=true;
            }
            else
            {
                return false;
            }
        }
        return retVal;
    }

    public void clickFindJobs()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"jobSearch")==true)
        {
            Mapper.find(domFile,"jobSearch").click();
        }
        else
        {
            logger.info("Find jobs button not visible");
        }
    }

    public String getTextColor(WebElement elm)
    {
        return elm.getCssValue("background-color");
    }

    public boolean validateEmptySearch()
    {
        String oldTextColor = getTextColor(Mapper.find(domFile, "jobSearch"));
        String newTextColor = null;
        boolean retval = false;
        logger.info("OLD#"+oldTextColor+"#");
        clickFindJobs();
        try
        {
            Thread.sleep(4000);
            WebElement elm = Mapper.find(domFile, "jobSearch");
            newTextColor = getTextColor(elm);
            logger.info("NEW#"+newTextColor+"#");
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        if (oldTextColor.equals(newTextColor))
        {
            retval = false;
        }
        else
        {
            retval = true;
        }
        return retval;
    }

    public boolean verifyTopJobRoles()
    {
        boolean retVal = false;
        List<WebElement> elm = Mapper.finds(domFile,"topJobRolesContainers");
        if (elm.size()==12)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public void clickATopRole()
    {
        List<WebElement> elm = Mapper.finds(domFile,"topJobRolesContainers");
        elm.get(1).click();

    }

    public boolean verifyLanguagesPresent()
    {
        boolean retVal = false;
        List<WebElement> lang = Mapper.finds(domFile,"languageLabels");
        List<String> langStr = new ArrayList();

        for (int i=0;i<lang.size();i++)
        {
            String language = lang.get(i).getText();
            langStr.add(i,language);
        }

        String[] listedLangs = {"हिन्दी","தமிழ","తెలుగు","ಕನ್ನಡ","മലയാളം","मराठी","ગુજરાતી"};

        for (int i=0;i<listedLangs.length;i++)
        {
            if (langStr.contains(listedLangs[i]))
            {
                retVal = true;
            }
            else
            {
                logger.info("Some language label is missing!!!");
                return false;
            }
        }
        return retVal;
    }

    public String returnTopRoLeLabelText()
    {
        return Mapper.find(domFile,"topRoleLabel").getText();
    }

    public void clickCitySelection()
    {
        Mapper.find(domFile,"citySelectionIcon").click();
    }

    public boolean isCitySelectionScreenPresent()
    {
        Mapper.waitForElementToBeClickable(domFile, "citySelectionContainer");
        return Mapper.find(domFile,"citySelectionContainer").isDisplayed();
    }

    public void inputCity(String cityString)
    {
        WebElement elm = Mapper.find(domFile, "citySelectionInputTextField");
        elm.clear();
        elm.sendKeys(cityString);
    }

    public boolean validateCitySelectionScreenFunctionality(String expectedString)
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"citySelectionAutoSuggestion")==true)
        {
            if (Mapper.find(domFile,"citySelectionAutoSuggestion").getText().equalsIgnoreCase(expectedString)==true)
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

    public boolean isEmployerZonepresent(){
        return (isElementPresent("employerZone"));
    }

    public void clickEmployerZone(){
        Mapper.find(domFile,"employerZone").click();
    }

    public WebElement EmployerZone(){
        return Mapper.find(domFile,"employerZone");
    }

    public boolean isTatkalJobIconDisplayed(){
        if(isElementPresent("TatkalJobIcon")){
            return true;
        }
        else
            return false;
    }

    public void clickTatkalJob(){
        Mapper.waitForElementToBeClickable(domFile, "TatkalJobIcon");
        Mapper.find(domFile,"TatkalJobIcon").click();
    }


    public void clickTatkalJobCloseIcon(){
        Mapper.waitForElementToBeClickable(domFile,"TatkalJobPageCloseIcon");
        Mapper.find(domFile,"TatkalJobPageCloseIcon").click();
    }

    public boolean isTatkalPageDisplayed(){

        boolean flag=false;

        if(isElementPresent("TatkalJobPageHeading")){
            flag=true;
        }
        else
            return false;

        if(isElementPresent("TatkalJobPageName")){
            flag=true;
        }
        else
        return false;

        if(isElementPresent("TatkalJobPageEmail")){
            flag=true;
        }
        else
            return false;

        if(isElementPresent("TatkalJobPagePhone")){
            flag=true;
        }
        else
            return false;

        if(isElementPresent("TatkalJobPageRole")){
            flag=true;
        }
        else
            return false;

        if(isElementPresent("TatkalJobPageCity")){
            flag=true;
        }
        else
            return false;

        if(isElementPresent("TatkalJobPageLocality")){
            flag=true;
        }
        else
            return false;

        if(isElementPresent("TatkalJobPagePayButton")){
            flag=true;
        }
        else
            return false;

        return flag;
    }

    public boolean validateRoleCards()
    {
        boolean retVal = false;
        WebElement roleCard = Mapper.find(domFile,"roleCard");
        String hrefValue = roleCard.getAttribute("href");

        roleCard.click();
        if (getCurrentLocation().equals(hrefValue))
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public boolean validateDefaultCitySelection()
    {
        boolean retVal = false;
        String str = Mapper.find(domFile,"cityLabelGeolocation").getText();
        if (str.contains("All India"))
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return  retVal;
    }

    public void scrollToPageEnd()
    {
        Mapper.find(domFile,"cityLabelGeolocation").sendKeys(Keys.END);
    }

    public boolean validateScrollToTopBtn()
    {
        boolean retVal = false;
        if (Mapper.find(domFile,"scrollToTopButton")!=null)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public boolean validateCitiesJobsHomePage()
    {
        boolean retVal = false;
        String[] cities = {"Ahmedabad","Bangalore","Chandigarh","Chennai","Noida","Coimbatore","Delhi","Hyderabad","Kochi","Kolkata","Mumbai","Pune","Gurgaon","Jaipur","Lucknow","NaviMumbai","Trivandrum"};
        List<WebElement> citiesFromUI = Mapper.finds(domFile,"citiesFromJobsHomePage");
        List<String> citiesFromUIStrings = new ArrayList();

        for (int i=0;i<cities.length;i++)
        {
            String str = citiesFromUI.get(i).getText();
            citiesFromUIStrings.add(i,str);
        }

        for (int i=0;i<citiesFromUIStrings.size();i++)
        {
            String toSearch = citiesFromUIStrings.get(i);
            if (binarySearch(toSearch,cities)==true)
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

    public boolean checkCityLabelsAcrossHP(String city)
    {
        boolean retVal = false;
        String str;
        if (Mapper.waitForElementToBeVisible(domFile,"cityLabelAcrossHP")==true)
        {
            List<WebElement> cityLabels = Mapper.finds(domFile,"cityLabelAcrossHP");
            for (int i=0;i<cityLabels.size();i++)
            {
                str= cityLabels.get(i).getText();
                if (str.contains(city)==true)
                {
                    retVal = true;
                }
                else
                {
                    return false;
                }
            }
        }
        return retVal;
    }

    public void inputNameTJ(String name)
    {
        if (Mapper.find(domFile, "TatkalJobPageName")!=null)
        {
            if (Mapper.find(domFile,"TatkalJobPageName").isDisplayed()==true)
            {
                Mapper.find(domFile,"TatkalJobPageName").sendKeys(name);
            }
            else
            {
                logger.info("Name text field is not displayed!");
            }
        }else
        {
            logger.info("Name text field is not present!");
        }
    }

    public void inputEmailTJ(String email)
    {
        if (Mapper.find(domFile,"TatkalJobPageEmail")!=null)
        {
            if (Mapper.find(domFile,"TatkalJobPageEmail").isDisplayed()==true)
            {
                Mapper.find(domFile,"TatkalJobPageEmail").sendKeys(email);
            }
            else
            {
                logger.info("Email text field is not displayed!");
            }
        }else
        {
            logger.info("Email text field is not present!");
        }
    }

    public void inputMobNumTJ(String num)
    {
        if (Mapper.find(domFile,"TatkalJobPagePhone")!=null)
        {
            if (Mapper.find(domFile,"TatkalJobPagePhone").isDisplayed()==true)
            {
                Mapper.find(domFile,"TatkalJobPagePhone").sendKeys(num);
            }
            else
            {
                logger.info("Phone number text field is not displayed!");
            }
        }else
        {
            logger.info("Phone number text field is not present!");
        }
    }

    public void selectRoleTJ(String role)
    {
        if (Mapper.find(domFile,"TatkalJobPageRole")!=null)
        {
            Select elm = new Select(Mapper.find(domFile, "TatkalJobPageRole"));
            elm.selectByValue(role);
        }
    }

    public void selectCityTJ(String city)
    {
        if (Mapper.find(domFile,"TatkalJobPageCity")!=null)
        {
            Select elm = new Select(Mapper.find(domFile, "TatkalJobPageCity"));
            elm.selectByValue(city);
        }
    }

    public void selectLocalityTJ(String loc)
    {
        if (Mapper.find(domFile,"TatkalJobPageLocality")!=null)
        {
            Select elm = new Select(Mapper.find(domFile, "TatkalJobPageLocality"));
            elm.selectByValue(loc);
        }
    }

    public void clickPayButtonTJ()
    {
        if (Mapper.find(domFile,"TatkalJobPagePayButton").isDisplayed()==true)
        {
            Mapper.find(domFile,"TatkalJobPagePayButton").click();
        }
        else
        {
            logger.info("Pay button is not visible!");
        }
    }

    public void inputCreditCardNumTJ(String ccNum)
    {
        if (Mapper.find(domFile,"TatkalJobPageCCNum").isDisplayed()==true)
        {
            Mapper.find(domFile,"TatkalJobPageCCNum").click();
            Mapper.find(domFile,"TatkalJobPageCCNum").sendKeys("4111");
            Mapper.find(domFile,"TatkalJobPageCCNum").click();
            Mapper.find(domFile,"TatkalJobPageCCNum").sendKeys("1111");
            Mapper.find(domFile,"TatkalJobPageCCNum").click();
            Mapper.find(domFile,"TatkalJobPageCCNum").sendKeys("1111");
            Mapper.find(domFile,"TatkalJobPageCCNum").click();
            Mapper.find(domFile,"TatkalJobPageCCNum").sendKeys("1111");

        }
    }

    public void enterExpiryMonthYearCVVTJ()
    {
        Select elm = new Select(Mapper.find(domFile,"TatkalJobPageCCMonth"));
        elm.selectByValue("01");

        Select elm2 = new Select(Mapper.find(domFile,"TatkalJobPageCCYear"));
        elm2.selectByValue("20");

        Mapper.find(domFile,"TatkalJobPageCVV").sendKeys("123");
    }

    public void enterCardHolderName()
    {
        Mapper.find(domFile,"TatkalJobPageCCHolderName").sendKeys("test name");
    }

    public void clickPayCitrus()
    {
        Mapper.find(domFile,"TatkalJobPagePayAtCitrus").click();
        Mapper.find(domFile,"TatkalJobPageSubmitPayRequest").click();
        switchTo().alert().accept();
    }

    public void selectSubCategoryJobAlertCreation(int i)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"subcategoryList")==true)
        {
            Mapper.find(domFile, "subcategoryList").click();
            Mapper.findAndReplace(domFile, "jobsCreateAlertSubCategories", new String[]{Integer.toString(i)}).click();
        }else
        {
            logger.info("The sub categories list is not populating properly. Please check!");
        }
    }
}
