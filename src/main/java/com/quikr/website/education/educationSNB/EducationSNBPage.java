package com.quikr.website.education.educationSNB;

import com.quikr.website.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 31/8/16.
 */
public class EducationSNBPage extends PageBase {

    private static final String domFile = getProperties().get("EDUCATION_SNB_DOM_FILE");

    public EducationSNBPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    public void clickColleges(){
        boolean val = Mapper.waitForElementToBeVisible(domFile, "CollegesSubCat");
        if (val=true)
            Mapper.find(domFile,"CollegesSubCat").click();
        else
            logger.info("Colleges sub category was not visible.");
    }

    public boolean verifyDisciplineTitles(){

        boolean val = false;
        String GivenDisciplines = "Engineering&TechnologyBusinessManagementMedicine&HealthcareDesignScienceMassComm&MediaArts&CommerceAnimationLawArchitectureComputerTeachingBanking&FinanceAccounting&FinanceHospitality&AviationFilms&EntertainmentRetailBeauty&FitnessLanguageTrainingExamCoaching";
        String extractedDisciplines = "";
        Mapper.waitForElementToBeVisible(domFile, "CollegeDisciplineTitles");
        List<WebElement> elm = Mapper.finds(domFile, "CollegeDisciplineTitles");
        for (int i=0;i<elm.size();i++){
            extractedDisciplines = extractedDisciplines+elm.get(i).getText().replaceAll("\\s+","");
        }
        if (GivenDisciplines.equals(extractedDisciplines)){
            val = true;
        }else{
            logger.info(">>"+extractedDisciplines+"<<");
            return false;
        }
        return val;
    }

    public void clickEngineeringDiscipline(){
        if (Mapper.waitForElementToBeVisible(domFile,"CollegeDisciplineTitles")==true){
            Mapper.find(domFile,"CollegeDisciplineTitles").click();
        }else{
            logger.info("College discipline labels were not visible.");
        }
    }

    public boolean verifyCollegeChicklets(){
        return isElementPresent("CollegeLocChicklets");
    }

    public boolean verifyCollegeDisciplineFilter(){
        String labelText ="";
        String chickletText = "";
        if (Mapper.waitForElementToBeVisible(domFile,"CollegeDisciplineFilters")==true){
            WebElement elm = Mapper.find(domFile,"CollegeDisciplineFilters");
            labelText = elm.getText();
            elm.click();
        }else{
            logger.info("College discipline filters were not visible.");
        }

        if (Mapper.waitForElementToBeVisible(domFile,"CollegeDisciplineChicklet")==true){
            WebElement elm = Mapper.find(domFile,"CollegeDisciplineChicklet");
            chickletText = elm.getText();
        }
        return labelText.equals(chickletText);
    }

    public String getTheCollegeTitle(){
        String collegeTitle = "";
        if (Mapper.waitForElementToBeVisible(domFile,"CollegeTitles")==true){
            collegeTitle = Mapper.find(domFile,"CollegeTitles").getText();
        }else{
            logger.info("College titles are not visible in the snb.");
        }
        return collegeTitle;
    }

    public void clickFirstCollegeTitle(){
        if (Mapper.waitForElementToBeVisible(domFile,"CollegeTitles")==true){
            Mapper.find(domFile,"CollegeTitles").click();
        }else{
            logger.info("College titles are not visible in the snb.");
        }
        sleep(3000);
    }
    public String getCollegeTitle(){
        return Mapper.find(domFile,"CollegeTitles").getText();
    }

    public boolean verifyInfiniteScroll(){
        int initialCount=0, finalCount = 0;
        if (Mapper.waitForElementToBeVisible(domFile, "CollegeTitles")==true){
            List<WebElement> elm = Mapper.finds(domFile,"CollegeTitles");
            initialCount = elm.size();
            elm.get(1).sendKeys(Keys.END);
            try{Thread.sleep(5000);}catch (Exception e){}

        }else{
            logger.info("College titles were not visible.");
        }
        waitForPageToLoad(".com");
        if (Mapper.waitForElementToBeVisible(domFile,"CollegeTitles")==true){
            List<WebElement> elm1 = Mapper.finds(domFile,"CollegeTitles");
            finalCount = elm1.size();
        }else{
            logger.info("College titles were not visible again..");
        }
        logger.info(initialCount+"<>"+finalCount);
        if (finalCount>initialCount)
            return true;
        else
            return false;
    }

    public void clickViewContactDetails(){
        if (Mapper.waitForElementToBeVisible(domFile,"CollegeViewContactDetails")==true){
            Mapper.find(domFile,"CollegeViewContactDetails").click();
        }else{
            logger.info("View Contact Details buttons are not visible.");
        }
    }

    public void clickVapShowDetails(){
        if (Mapper.waitForElementToBeVisible(domFile,"CollegeVapShowDetails")==true){
            Mapper.find(domFile,"CollegeVapShowDetails").click();
        }else{
            logger.info("Show details in vap page are not visible.");
        }
    }

    public boolean verifyBlurredContactNumber(){
        if (Mapper.waitForElementToBeVisible(domFile,"CollegeVapShowDetails")==true)
            return true;
        else
            return false;
    }

    public void clickSchoolTutions(){
        if (Mapper.waitForElementToBeVisible(domFile,"SchoolTutionsCat")==true){
            Mapper.find(domFile,"SchoolTutionsCat").click();
        }else{
            logger.info("School tutions  cat ws not visible.");
        }
    }

    public String[] clickClassroomCoachingFilters(){
        Random randObj = new Random();
        int randNum;
        String[] selectedFiltersLabel = new String[3];

        if (Mapper.waitForElementToBeVisible(domFile,"ClassroomCoachingBoardStandardSubjectFilters")==true){
            List<WebElement> elm = Mapper.finds(domFile, "ClassroomCoachingBoardStandardSubjectFilters");
            int numberOfFilters = elm.size();
            randNum=randObj.nextInt((5-1))+1;
            logger.info(">>"+randNum);
            selectedFiltersLabel[0]=elm.get(randNum).getText();
            elm.get(randNum).click();
            sleep(5000);
            randNum=randObj.nextInt((18-7))+7;
            logger.info(">>"+randNum);
            selectedFiltersLabel[1]=elm.get(randNum).getText();
            elm.get(randNum).click();
            sleep(5000);
            randNum=randObj.nextInt((36-18))+18;
            selectedFiltersLabel[2]=elm.get(randNum).getText();
            elm.get(randNum).click();
            logger.info(">>"+randNum);
        }else{
            logger.info("Classroom coaching filters were not visible.");
        }
        for (int i=0;i<selectedFiltersLabel.length;i++){
            System.out.println(selectedFiltersLabel[i]);
        }
        return selectedFiltersLabel;
    }
    public void clickSearchResults(){
        Mapper.find(domFile,"ClassroomSearchResults").click();
    }

    public String[] getClassroomResultantChicklets(){
        String[] arr=null;
        if (Mapper.waitForElementToBeVisible(domFile, "ClassroomFilterChicklets")==true){
            List<WebElement> elm = Mapper.finds(domFile,"ClassroomFilterChicklets");
            arr = new String[elm.size()];
            for (int i=0;i<elm.size();i++){
                arr[i]=elm.get(i).getText();
            }
        }
        return arr;
    }

    public boolean ClassroomCoachingFilterVerification(String[] initialArr, String[] newArr){
        boolean retVal = false;
        for (int i=0;i<newArr.length;i++){
            if (newArr[i].contains(initialArr[0])){
                retVal=true;
            }else{
                return false;
            }
            if (newArr[i].contains(initialArr[2])){
                retVal=true;
            }else{
                return false;
            }
        }
        return retVal;
    }

    public void clickPostAdBtn(){
        Mapper.find(domFile,"postAdBtn").click();
    }

    public boolean verifySkipShowResultsLink(){
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"skipShowResults")==true){
            Mapper.find(domFile,"skipShowResults").click();
            if (Mapper.waitForElementToBeInvisible(domFile,"collegeFinderResults")==true){
                retVal=true;
            }else{
                return false;
            }
        }else{
            logger.info("Skip show results link is not present.");
        }
        return retVal;
    }

    public String clickRandomCollegeDiscipline(){
        String disciplineSelected="";
        if (Mapper.waitForElementToBeVisible(domFile, "CollegeDisciplineTitles") == true) {
            List<WebElement> disciplines = Mapper.finds(domFile, "CollegeDisciplineTitles");
            int numberOfDisciplines = disciplines.size();
            logger.info("Count of disciplines available are :: " + numberOfDisciplines);
            int randomNum = new Random().nextInt(numberOfDisciplines);
            disciplineSelected=disciplines.get(0).getText().trim();
            //disciplineSelected=disciplines.get(randomNum).getText().trim();
            //disciplines.get(randomNum).click();
            disciplines.get(0).click();
            sleep(5000);

        } else {
            logger.info("Disciplines were not displayed. Please check!");
        }
        return disciplineSelected;
    }

    public String clickRandomCollegeFinderCity(){
        String citySelected="";
        if (Mapper.waitForElementToBeVisible(domFile, "collegeFinderCities") == true) {
            List<WebElement> cities = Mapper.finds(domFile, "collegeFinderCities");
            int numOfCities = cities.size();
            logger.info("Count of cities available are :: " + numOfCities);
            int randomNum = new Random().nextInt(numOfCities);
            citySelected=cities.get(randomNum).getText().trim();
            cities.get(randomNum).click();

        } else {
            logger.info("Cities were not displayed. Please check!");
        }
        clickCollegeFinderNext();
        return citySelected;
    }

    public void clickCollegeFinderNext(){
        Mapper.find(domFile,"collegeFinderNext").click();
        sleep(5000);
    }

    public String clickRandomCollegeFinderGrad(){
        String gradSelected="";
        if (Mapper.waitForElementToBeVisible(domFile, "collegeFinderGrads") == true) {
            List<WebElement> grads = Mapper.finds(domFile, "collegeFinderGrads");
            int numOfGrads = grads.size();
            logger.info("Count of grad options available are :: " + numOfGrads);
            int randomNum = new Random().nextInt(numOfGrads);
            gradSelected=grads.get(randomNum).getText().trim();
            grads.get(randomNum).click();

        } else {
            logger.info("Grads were not displayed. Please check!");
        }
        return gradSelected;
    }

    public boolean verifyFiltersApplied(String disc, String city, String cat){
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"appliedFilterText")==true){
            List<WebElement> elm = Mapper.finds(domFile,"appliedFilterText");
            List<String> elmTxt = new ArrayList<String>();
            for (int i=0;i<elm.size();i++){
                String label = elm.get(i).getText().trim();
                elmTxt.add(i,label);
                logger.info(">>"+label+"<<");
            }
            if (elmTxt.contains(disc)){
                if (elmTxt.contains(city)){
                    if (elmTxt.contains(cat)){
                        retVal=true;
                    }else {
                        logger.info("Category is not matching/available.");
                        return false;
                    }
                }else {
                    logger.info("City is not matching/available.");
                    return false;
                }
            }else{
                logger.info("Discipline is not matching/available.");
                return false;
            }
        }
        return retVal;
    }

    public boolean verifyHamburger(){
        if (isElementPresent("hamburgerLoginButton")==true)
            return true;
        else
            return false;
    }

}
