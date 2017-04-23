package com.quikr.website.horizontal.pap;

import com.quikr.website.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 24/5/16.
 */
public class EducationandLearningPap extends PageBase {

    // const string
    private static final String domFile = getProperties().get("EDUCATIONANDLEARNING_PAP_DOM_FILE");

    public EducationandLearningPap(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public void selectRandomCoaching_Tuitions(){
        WebElement selectBox=Mapper.find(domFile, "Coaching_Tuitions");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Coaching_TuitionsOptions");
        int randomOption=new Random().nextInt(list.size());
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Coaching_Tuitions Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomBoard_Entrance_Exams(){
        WebElement selectBox=Mapper.find(domFile, "Board_Entrance_Exams");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Board_Entrance_Exams_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Board_Entrance_Exams Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomSubjects(){
        WebElement selectBox=Mapper.find(domFile, "Subjects");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Subjects_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Subjects Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomCourseType(){
        WebElement selectBox=Mapper.find(domFile, "Course_Type");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Course_TypeOptions");
        int randomOption=new Random().nextInt(list.size());
        WebElement element =list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Course_Type Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomHobby(){
        WebElement selectBox=Mapper.find(domFile, "Hobby");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Hobby_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element =list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Hobby Option -->" + element.getAttribute("lblval"));
    }


    public void selectRandomEligbility(){
        WebElement selectBox=Mapper.find(domFile, "Eligbility");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        WebElement element =list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Eligbility Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomSchool_Board(){
        WebElement selectBox=Mapper.find(domFile, "School_Board");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "School_Board_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the School_Board Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomStandard(){
        WebElement selectBox=Mapper.find(domFile, "Standard");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Standard_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element=list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Standard Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomClasses_Held(){
        WebElement selectBox=Mapper.find(domFile, "Classes_Held");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Classes_Held_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element =list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Classes_Held Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomClass_Timings(){
        WebElement selectBox=Mapper.find(domFile, "Class_Timings");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Classes_Timings_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element =list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Class_Timings Option -->" + list.get(randomOption).getAttribute("lblval"));
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomCareer_Stream(){
        WebElement selectBox=Mapper.find(domFile, "Career_Stream");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Career_Stream_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element =list.get(randomOption);
        getElementintoView(element);
        element.click();
        logger.info("Clicked on the Career_Stream Option -->" + list.get(randomOption).getAttribute("lblval"));
    }

    //Need to revisit
    public void selectRandomSpecialization(){
        WebElement selectBox=Mapper.find(domFile, "Specialization");
        getElementintoView(selectBox);
        selectBox.click();
        List<WebElement> list=Mapper.finds(domFile, "Specialization_Options");
        List<WebElement> visibleoptions=new LinkedList<>();
        for(int i=0;i<list.size();i++) {
            if (list.get(i).isDisplayed()){
                visibleoptions.add(list.get(i));
            }
        }
        int randomOption=new Random().nextInt(visibleoptions.size());
        WebElement element =visibleoptions.get(randomOption);
        getElementintoView(element);
        Mapper.findChilds(element).get(0).click();
        element.sendKeys(Keys.ENTER);
    }

    public void selectRandomEducationStream(){
        WebElement element=Mapper.find(domFile, "Education_Stream");
        getElementintoView(element);
        element.click();
        List<WebElement> list=Mapper.finds(domFile, "Education_Stream_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element1 =list.get(randomOption);
        getElementintoView(element1);
        element1.click();
        logger.info("Clicked on the Education_Stream Option -->" + list.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomCountry(){
        WebElement element=Mapper.find(domFile, "Country");
        getElementintoView(element);
        element.click();
        List<WebElement> list=Mapper.finds(domFile, "Country_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element1 =list.get(randomOption);
        getElementintoView(element1);
        element1.click();
        logger.info("Clicked on the Country Option -->" + element1.getAttribute("lblval"));
        element1.sendKeys(Keys.ENTER);
    }

    public void selectRandomServices_Offered(){
        WebElement element=Mapper.find(domFile, "Services_Offered");
        getElementintoView(element);
        element.click();
        List<WebElement> list=Mapper.finds(domFile, "Services_Offered_Options");
        int randomOption=new Random().nextInt(list.size());
        WebElement element1 =list.get(randomOption);
        getElementintoView(element1);
        element1.click();
        logger.info("Clicked on the Services Option -->" + element1.getAttribute("lblval"));
        element1.sendKeys(Keys.ENTER);
    }

    public void setCounselling_Fees(String fees){
        Mapper.find(domFile, "Counselling_Fees").click();
        Mapper.find(domFile,"Counselling_Fees").sendKeys(fees);
    }


    public void setInstitute_Name(String institute_name){
        Mapper.find(domFile,"Institute_Name").click();
        Mapper.find(domFile,"Institute_Name").sendKeys(institute_name);
    }

    public void setInstitute_Address(String institute_address){
        Mapper.find(domFile,"Institute_Address").click();
        Mapper.find(domFile,"Institute_Address").sendKeys(institute_address);
    }

    public void setInstitute_Website(String institute_website){
        Mapper.find(domFile,"Institute_Website").click();
        Mapper.find(domFile,"Institute_Website").sendKeys(institute_website);
    }

    public void setCourse_Duration(String course_duration){
        Mapper.find(domFile,"Course_Duration").click();
        Mapper.find(domFile,"Course_Duration").sendKeys(course_duration);
    }

    public void setCoaching_Fees(String coaching_fees){
        Mapper.find(domFile,"Coaching_Fees").click();
        Mapper.find(domFile,"Coaching_Fees").sendKeys(coaching_fees);
    }

    public void setMonthly_Fees(String monthly_fees){
        Mapper.find(domFile,"Monthly_Fees").click();
        Mapper.find(domFile,"Monthly_Fees").sendKeys(monthly_fees);
    }

    public void setMonthly_Tuition_Fees(String monthly_fees){
        Mapper.find(domFile,"Monthly_Tuition_Fees").click();
        Mapper.find(domFile,"Monthly_Tuition_Fees").sendKeys(monthly_fees);
    }

    public void selectRandomDelivery_Mode(){
        WebElement selectBox=Mapper.find(domFile, "Delivery_Mode");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        WebElement element =list.get(randomOption);
        element.click();
        logger.info("Clicked on the Delivery_Mode Option -->" + list.get(randomOption).getAttribute("lblval"));
    }

    public void selectRandomProduct_Type(){
        WebElement selectBox=Mapper.find(domFile, "Product_Type");
        Select selectoptions=new Select(selectBox);
        List<WebElement> list=selectoptions.getOptions();
        int randomOption=new Random().nextInt(list.size());
        WebElement element =list.get(randomOption);
        element.click();
        logger.info("Clicked on the Product_Type Option -->" + list.get(randomOption).getAttribute("lblval"));
    }

    public void clickCourseDomain(){
        if (Mapper.waitForElementToBeVisible(domFile,"courseDomain")==true){
            WebElement elm = Mapper.find(domFile,"courseDomain");
            elm.click();
        }else {
            logger.info("course domain was not visible/displayed. Please check!");
        }
    }

    public boolean verifyCourseDomainValues(){
        int tru, fals = 0;
        String givenCourseDomainValues[] = {"Business Analytics Courses","Computer Certification Courses","Computer Training Courses","Design Courses","Hardware Training Courses","Management Courses","Soft-Skill Courses"};
        List<WebElement> fromWeb = Mapper.finds(domFile, "courseDomainValueTexts");
        Set<String> setFromWeb = new HashSet<String>();
        for (int i=0;i<fromWeb.size();i++){
            setFromWeb.add(fromWeb.get(i).getText());
        }

        for (int j=0;j<givenCourseDomainValues.length;j++){
            if (setFromWeb.contains(givenCourseDomainValues[j])==true){
                tru=0;
            }else{
                fals+=1;
                logger.info(givenCourseDomainValues[j]+" Is not present. Please check!");
            }
        }
        if (fals>0)
            return false;
        else{
            logger.info("No title is missing. Cheer up!");
            return true;
        }
    }

    public void selectRandomCourseDomainValues(){
        if (Mapper.waitForElementToBeVisible(domFile, "courseDomainValueRadioButtons") == true) {
            List<WebElement> dropDownOptions = Mapper.finds(domFile,"courseDomainValueRadioButtons");
            int numberOfOptions = dropDownOptions.size();
            logger.info("Count of options available in the dropdown are :: "+numberOfOptions);
            int randomNum = new Random().nextInt(numberOfOptions);
            dropDownOptions.get(randomNum).click();

        } else {
            logger.info("courseDomain Values were not populated as dropdown. Please check!");
        }
    }

    public void selectDomain(String domain){
        if (domain.contains("buiness")){
            if (Mapper.waitForElementToBeVisible(domain,"BusinessAnalyticsCourse")){
                Mapper.find(domain,"BusinessAnalyticsCourse").click();
            }else
            {
                logger.info("Business analytics was not visible....");
            }
        }else if(domain.contains("computer certification")){
            if (Mapper.waitForElementToBeVisible(domain,"CompCertCourses")){
                Mapper.find(domain,"CompCertCourses").click();
            }else
            {
                logger.info("Computer certification was not visible....");
            }
        }else if(domain.contains("computer training")){
            if (Mapper.waitForElementToBeVisible(domain,"CompTrainingCourses")){
                Mapper.find(domain,"CompTrainingCourses").click();
            }else
            {
                logger.info("Computer training was not visible....");
            }
        }else if(domain.contains("design")){
            if (Mapper.waitForElementToBeVisible(domain,"DesignCourses")){
                Mapper.find(domain,"DesignCourses").click();
            }else
            {
                logger.info("DesignCourses was not visible....");
            }
        }else if(domain.contains("hardware")){
            if (Mapper.waitForElementToBeVisible(domain,"HardwareTrainingCourses")){
                Mapper.find(domain,"HardwareTrainingCourses").click();
            }else
            {
                logger.info("HardwareTrainingCourses was not visible....");
            }
        }else if(domain.contains("management")){
            if (Mapper.waitForElementToBeVisible(domain,"ManagementCourses")){
                Mapper.find(domain,"ManagementCourses").click();
            }else
            {
                logger.info("ManagementCourses was not visible....");
            }
        }else if(domain.contains("soft-skill")){
            if (Mapper.waitForElementToBeVisible(domain,"SoftskillCourses")){
                Mapper.find(domain,"SoftskillCourses").click();
            }else
            {
                logger.info("SoftskillCourses was not visible....");
            }
        }else{
            logger.info("Invalid parameter given :: "+domain);
        }
    }

    public String getSelectedDomainsText(){
        Mapper.waitForElementToBeVisible(domFile,"selectedCourseDomain");
        String selectedDomainText = Mapper.find(domFile,"selectedCourseDomain").getText();
        logger.info("Domain selected is :: "+selectedDomainText);
        return selectedDomainText;
    }

    public void clickCourseCategory(){
        if (Mapper.waitForElementToBeVisible(domFile,"courseCat")==true){
            Mapper.find(domFile,"courseCat").click();
        }else{
            logger.info("Course category option was not visible. Please check!");
        }
    }

    public boolean verifyLabels(String[] arrStr, List<WebElement> listWeb){
        int tru, fals = 0;
        Set<String> setFromWeb = new HashSet<String>();
        for (int i=0;i<listWeb.size();i++){
            setFromWeb.add(listWeb.get(i).getText());
        }

        for (int j=0;j<arrStr.length;j++){
            if (setFromWeb.contains(arrStr[j])==true){
                tru=0;
            }else{
                fals+=1;
                logger.info(arrStr[j]+" Is not present. Please check!");
            }
        }
        if (fals>0)
            return false;
        else{
            logger.info("No title is missing. Cheer up!");
            return true;
        }
    }

    public boolean verifyCourseCategories(){
        boolean val=false;
        String BusinessAnalytics[] = {" Business Intelligence Training"," Database Training"};
        String ComputerCertifications[] ={"Cisco Certification","Citrix Certifications","CompTIA Certifications","DOEACC Certifications","EC-Council Certifications","IBM Certifications","(ISC)2 Certifications","ITIL Certifications","Linux Certifications","Microsoft Certifications","Oracle Certifications","VMware Certifications","GIAC Certifications","CWNP Certifications","SAP Certifications","Red Hat Certifications","ISACA Certifications"};
        String ComputerTrainingCourses[] ={"Administration Training","Computer Networking Training","Content Management Software Training","Database Training","Enterprise Training","Ethical Hacking Training","MainFrame Training","Middleware Training","Mobile Development","Operating System Training","Programming Languages Training","Software Architecture Training","Software Tools Training","Software Testing Training","Web Technologies Training","Other Software Courses"};
        String Design[] ={"Engineering Design Training","Multimedia & Design Training"};
        String HardwareTraining[]= {"Embedded Systems Training","Robotics Training"};
        String Management[] ={"Accounting & Finance Courses","Project Management Certification Courses","Operation Management Courses","Marketing Courses"};
        String SoftSkill[] ={"Spoken English Training","Foreign Languages","Personality Development"};

        String selectedDomain = getSelectedDomainsText();

        if (selectedDomain.contains("Business")){
            List<WebElement> busins = Mapper.finds(domFile,"courseCatValueTexts");
            val = verifyLabels(BusinessAnalytics,busins);
        }else if (selectedDomain.contains("Computer Certification")){
            List<WebElement> compCert = Mapper.finds(domFile,"courseCatValueTexts");
            val = verifyLabels(ComputerCertifications,compCert);
        }else if (selectedDomain.contains("Computer Training")){
            List<WebElement> compTrain = Mapper.finds(domFile,"courseCatValueTexts");
            val = verifyLabels(ComputerTrainingCourses,compTrain);
        } else if (selectedDomain.contains("Design")){
            List<WebElement> desgn = Mapper.finds(domFile,"courseCatValueTexts");
            val = verifyLabels(Design,desgn);
        }else if (selectedDomain.contains("Hardware")){
            List<WebElement> hard = Mapper.finds(domFile,"courseCatValueTexts");
            val = verifyLabels(HardwareTraining,hard);
        }else if (selectedDomain.contains("Management")){
            List<WebElement> manage = Mapper.finds(domFile,"courseCatValueTexts");
            val = verifyLabels(Management,manage);
        }else if(selectedDomain.contains("Soft")){
            List<WebElement> sof = Mapper.finds(domFile,"courseCatValueTexts");
            val = verifyLabels(SoftSkill,sof);
        }
        return val;
    }

    public int selectAllCourseCategories(){
        List<WebElement> checkBoxes = new ArrayList<WebElement>();
        if (Mapper.waitForElementToBeVisible(domFile,"courseCatCheckboxes")==true)
        {
            checkBoxes = Mapper.finds(domFile,"courseCatCheckboxes");
            for (int i=0;i<checkBoxes.size();i++){
                checkBoxes.get(i).click();
            }
            logger.info("All the checkboxes were clicked....");
        }else{
            logger.info("COurse categories were not visible probably.");
        }
        return checkBoxes.size();
    }

    public void clickCourses(){
        if (Mapper.waitForElementToBeVisible(domFile,"courses")==true){
            Mapper.find(domFile,"courses").click();
        }else
        {
            logger.info("Courses were not available....");
        }
    }


    public void clickAllCoursesAvailable(){
        if (Mapper.waitForElementToBeVisible(domFile,"coursesCheckboxes")==true)
        {
            List<WebElement> checkBoxes = Mapper.finds(domFile,"coursesCheckboxes");
            for (int i=0;i<checkBoxes.size();i++){
                checkBoxes.get(i).click();
            }
            logger.info("All the checkboxes were clicked....");
        }else{
            logger.info("Courses were not visible probably.");
        }

    }

    public String getTheSelectedCourseCategories(){
     return Mapper.find(domFile,"selectedCourseCategories").getText();
    }

    public String getTheSelectedCourses(){
        return Mapper.find(domFile,"selectedCourses").getText();
    }

    public void clickProgramType(){
        Mapper.find(domFile,"programType").click();
    }

    public void selectRandomProgram(){
        if (Mapper.waitForElementToBeVisible(domFile, "programTypeValues") == true) {
            List<WebElement> dropDownOptions = Mapper.finds(domFile,"programTypeValues");
            int numberOfOptions = dropDownOptions.size();
            logger.info("Count of options available for program type in the dropdown are :: "+numberOfOptions);
            int randomNum = new Random().nextInt(numberOfOptions);
            dropDownOptions.get(randomNum).click();

        } else {
            logger.info("program type Values were not populated as dropdown. Please check!");
        }
    }

    public void clickCourse(){
        Mapper.find(domFile,"course").click();
    }

    public void selectRandomCourse(){
        if (Mapper.waitForElementToBeVisible(domFile, "courseValues") == true) {
            List<WebElement> dropDownOptions = Mapper.finds(domFile,"courseValues");
            int numberOfOptions = dropDownOptions.size();
            logger.info("Count of options available for courses in the dropdown are :: "+numberOfOptions);
            int randomNum = new Random().nextInt(numberOfOptions);
            dropDownOptions.get(randomNum).click();

        } else {
            logger.info("course Values were not populated as dropdown. Please check!");
        }
    }

    public void inputAffiliatedUniversity(String univ){
        Mapper.find(domFile,"affiliateUniversity").sendKeys(univ);
    }

    public void clickEducationStream(){
        Mapper.find(domFile,"educationStream").click();
    }

    public void selectRandomEduStream(){
        if (Mapper.waitForElementToBeVisible(domFile, "educationStreamValues") == true) {
            List<WebElement> dropDownOptions = Mapper.finds(domFile,"educationStreamValues");
            int numberOfOptions = dropDownOptions.size();
            logger.info("Count of options available for education stream in the dropdown are :: "+numberOfOptions);
            int randomNum = new Random().nextInt(numberOfOptions);
            dropDownOptions.get(randomNum).click();

        } else {
            logger.info("Education stream were not populated as dropdown. Please check!");
        }
    }

    public void clickProgramsOffered(){
        Mapper.find(domFile,"programsOffered").click();
    }

    public void selectRandomProgramsOffered(){
        if (Mapper.waitForElementToBeVisible(domFile, "programsOfferedValues") == true) {
            List<WebElement> dropDownOptions = Mapper.finds(domFile,"programsOfferedValues");
            int numberOfOptions = dropDownOptions.size();
            logger.info("Count of options available for programs offered in the dropdown are :: "+numberOfOptions);
            int randomNum = new Random().nextInt(numberOfOptions);
            dropDownOptions.get(randomNum).click();

        } else {
            logger.info("Programs offered were not populated as dropdown. Please check!");
        }
    }

    public void selectAgeGroup(){
        WebElement fromMonths = Mapper.find(domFile,"minMonths");
        WebElement toMonths = Mapper.find(domFile,"maxMonths");

        Select fromDropDown = new Select(fromMonths);
        fromDropDown.selectByValue("6");

        Select toDropDown = new Select(toMonths);
        toDropDown.selectByValue("20");
    }


}
