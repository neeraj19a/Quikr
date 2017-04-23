package com.quikr.app.android.jobs;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.RandomStringUtils;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by Manvendra Singh on 17/8/15.
 */
public class JobsPage  extends AppAndroidPageBase {
    public JobsPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_JOBS_DOM_FILE");

    /**
     * function to click on select role
     */
    public void clickOnSelectRole()
    {
        Mapper.waitForElementToBeVisible(domFile,"searchJobs");
        Mapper.find(domFile,"searchJobs").click();
    }

    /**
     * function to validate select role option is display on job page
     */
    public String validateSelectRoleIsDisplayOnJobPage()
    {
        String clickOnSelectRole =Mapper.find(domFile,"clickOnSelectRole").getText().toString();
        return clickOnSelectRole;
    }

    /**
     * function to validate role list is display or not
     */
    public boolean validateRoleListOfJob()
    {
        return Mapper.waitForElementToBeVisible(domFile,"roleTitles");
    }

    /**
     * function to click on city
     */
    public void selectCityOnJobPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "changeCity");
        Mapper.find(domFile,"changeCity").click();
    }

    /**
     * function to validate city on job page
     */
    public String validateCityOnJobPage()
    {
        String city =Mapper.find(domFile,"city").getText().toString();
        return city;
    }

    /**
     * function to click on search button
     */
    public void selectSearchButton()
    {
        Mapper.waitForElementToBeClickable(domFile, "searchButton");
        Mapper.find(domFile,"searchButton").click();

    }

    /**
     * function to check Show more option is present on job page
     */
    public boolean checkShowMoreButton()
    {
        Mapper.VerticalSwipe(domFile, "chatHeader", "postAdButton");
        Mapper.VerticalSwipe(domFile, "chatHeader", "postAdButton");
        return isElementPresent("showMoreButton");
    }

    /**
     * function to click on show more button
     */
    public void selectShowMoreButton()
    {
        Mapper.find(domFile,"showMoreButton").click();
    }

    /**
     * function to check show less button is present
     */
    public boolean checkShowLessButton()
    {

        Mapper.VerticalSwipe(domFile,"chatHeader","postAdButton");
        Mapper.VerticalSwipe(domFile, "chatHeader", "postAdButton");
        Mapper.waitForElementToBeVisible(domFile, "showLessButton");
        return isElementPresent("showLessButton");
    }

    /**
     * function to select show less button
     */
    public void selectShowLessButton()
    {
        // Mapper.scroll("showLessButton");
        Mapper.waitForElementToBeVisible(domFile, "showLessButton");
        Mapper.find(domFile,"showLessButton").click();
    }

    /**
     * function to validate post ad button present on ad job page
     */
    public boolean checkPostAdButtonOnJobPage()
    {
        return Mapper.waitForElementToBeVisible(domFile,"postAdButton");
    }

    /**
     * function to validate apply page option for job
     */
    public boolean validateApplyJobPageOption()
    {
        if(!isElementPresent("submitButtonOfApplyPage"))
            hideKeyboard();
        return (isElementPresent("enterEmailformat1")&& (isElementPresent("phoneNoformat1")) && isElementPresent("submitButtonOfApplyPage"));
    }

    /**
     * function to click on apply button on apply page in job
     */
    public void selectApplyButtonOnApplyPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"jobsSectionTitle");
        Mapper.scroll("Apply");
        Mapper.find(domFile,"submitButtonOfApplyPage").click();

    }

    /**
     * function to validate text on clicking on apply button on apply page without filling any option
     * text appear with '-' or without '-'
     */
    public boolean validateTextAfterClickOnApplyButtonWithoutFillingOptionsOnApplyPage() {

//        if (isElementPresent("currentRole"))
//        {
//            return (isElementPresent("pleaseEnterValidEmailAddressFormat1") | isElementPresent("-pleaseEnterValidEmailAddressFormat1") && isElementPresent("pleaseEnterValidMobileFormat1") | isElementPresent("-pleaseEnterValidMobileFormat1"));
//        }
//        else
            return (isElementPresent("pleaseEnterValidEmailAddressFormat2")&isElementPresent("pleaseEnterValidMobileFormat2"));
    }

    /**
     * function to select current role on apply page
     */
    public void selectCurrentRole()
    {
        Mapper.waitForElementToBeVisible(domFile,"currentRole");
        Mapper.find(domFile,"currentRole").click();
    }

    /**
     * function to get current role name
     */
    public String getCurrentRoleName()
    {
        String str =Mapper.find(domFile,"currentRole").getText().toString();
        return str;
    }

    /**
     * function to send email id on apply page
     */
    public void setEmailOnApplyPage()
    {
        String emailId= "swat_" +(int)(10000000* Math.random()) + "@gmail.com";
        System.out.println("Random email generated:"+emailId);
        Mapper.find(domFile,"enterEmailformat1").sendKeys(emailId);
    }

    /**
     * function to get text of email
     */
    public String getEmail()
    {
        String email=Mapper.find(domFile,"enterEmailformat1").getText().toString();
        return email;
    }
    /**
     * send Phone no on apply page
     */

    public void setPhoneNoApplyPage()
    {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        String phNo = 99393+randomNumbers;
        Mapper.find(domFile,"phoneNoformat1").sendKeys(phNo);

    }

    /**
     * function to get phone no
     */
    public String getPhoneNo()
    {
        Mapper.scroll("phoneNo");
        String str=Mapper.find(domFile,"phoneNo").getText().toString();
        return str;
    }

    /**
     * function to select current role option name on apply page
     */
    public void selectCurrentRoleOptionsName()
    {
        Mapper.waitForElementToBeVisible(domFile, "currentRoleOptionNameOnApplyPage");
        if(!isElementPresent("currentRoleOptionNameOnApplyPage"))
        {
            Mapper.scroll("currentRoleOptionNameOnApplyPage");
        }
        Mapper.find(domFile, "currentRoleOptionNameOnApplyPage").click();
    }

    /**
     * function to validation for wrong phone no on apply page
     */
    public boolean validateWrongPhoneNoOnApplyPage()
    {
//        if(isElementPresent("pleaseEnterValidMobileFormat1")|isElementPresent("-pleaseEnterValidMobileFormat1"))
//            return (isElementPresent("pleaseEnterValidMobileFormat1")|isElementPresent("-pleaseEnterValidMobileFormat1"));
//        else
            return (isElementPresent("pleaseEnterValidMobileFormat2"));
    }

    /**
     * function to validate wrong email id on apply page
     */
    public boolean validateWrongEmail()
    {
//        if(isElementPresent("pleaseEnterValidEmailAddressFormat1")|isElementPresent("-pleaseEnterValidEmailAddressFormat1"))
//            return (isElementPresent("pleaseEnterValidEmailAddressFormat1")|isElementPresent("-pleaseEnterValidEmailAddressFormat1"));
//        else
            return (isElementPresent("pleaseEnterValidEmailAddressFormat2"));
    }

    /**
     * function to validate apply page screen 2 options present
     */
    public boolean validateApplyPageSecondScreen()
    {

        return (isElementPresent("jobssnbAdTitle"));
    }

    /**
     * function to select no thanks button on apply page 2nd screen
     */
    public void selectNoThanks()
    {
        if (!isElementPresent("noThanksButton"))
            Mapper.scroll("No,Thanks!");
        Mapper.find(domFile,"noThanksButton").click();

    }

    /**
     * select skip on second step of jobs apply page
     */
    public void selectSkip()
    {
        if (!isElementPresent("skip"))
            Mapper.scroll("Skip");
        Mapper.find(domFile, "skip").click();

    }



    /**
     * function to validation for after click on no thanks button show msg
     */
    public boolean validateMsgAfterClickOnNoThanksButton()
    {
        return( isElementPresent("msgAfterClickOnNoThanksButton")|isElementPresent("skip"));
    }

    /**
     * function to click on cancel button after click on no thanks button show msg pop on apply page
     */
    public void selectCancelButtonOnPopUpOnApplyPage()
    {

        Mapper.waitForElementToBeVisible(domFile, "cancelButtonOnPopUp");
        Mapper.find(domFile, "cancelButtonOnPopUp").click();
    }

    /**
     * function to click on Apply for more jobs button on apply page pop up
     */
    public  void selectApplyToMoreJobsOnApplyPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "applyForMoreButtonJobsButton");
        while (isElementPresent("applyForMoreButtonJobsButton"))
            Mapper.find(domFile,"applyForMoreButtonJobsButton").click();
    }

    /**
     * function to click on submit button on apply page
     */
    public void selectSubmitButtonOfApplyPage()
    {
        if(!isElementPresent("submitButtonOfApplyPage"))
            Mapper.hideKeyboard();
        Mapper.find(domFile,"submitButtonOfApplyPage").click();
    }

    /**
     * function for validation for after click on submit button on apply page without filling any options
     */
    public boolean validationForSubmitButtonWithoutFillingAnyOptions()
    {
        return (isElementPresent("applyToThisJob"));
    }

    /**
     * function to click on enter name on apply page
     */
    public void selectEnterName(String name)
    {
        Mapper.find(domFile,"enterName").sendKeys(name);
    }

    /**
     * function to get name
     */
    public String getPrefilledData(int i)
    {
        return Mapper.finds(domFile,"preFilledData").get(i).getText();
    }

    /**
     * function to click on educationalQualification on apply page
     */
    public void clickOnEducationalQualification()
    {
        Mapper.find(domFile,"educationalQualification").click();
    }

    /**
     * function to select education's option name
     */
    public void selectEducationOptionName()
    {
        Mapper.waitForElementToBeVisible(domFile, "educationOptionsName");
        Mapper.find(domFile,"educationOptionsName").click();
    }

    /**
     * function to click on totalExperience button
     */
    public void clickOnTotalExperience()
    {
        Mapper.scroll("totalExperience");
        Mapper.find(domFile,"totalExperience").click();
    }
    /**
     * function to select experience option
     */
    public void selectExperienceOptionName()
    {
        Mapper.find(domFile,"totalExperienceOptionName").click();
    }

    /**
     * function to select salary
     */
    public void selectSalaryOnApplyPage(String salary)
    {
        if (isElementPresent("currentSalary"))
            Mapper.find(domFile,"currentSalary").sendKeys(salary);
        else
            Mapper.find(domFile,"salaryNew").sendKeys(salary);

    }

    /**
     * function to get salary
     */
    public String getSalaryOnApplyPage()
    {
        Mapper.scroll("submitButtonOfApplyPage");
        String str=Mapper.find(domFile,"currentSalary").getText().toString();
        return str;
    }

    /**
     * function to validate msg after filling all option on apply page and click on submit button
     */
    public boolean validateMsgAfterClickOnSubmitButtonOnApplyPage()
    {
        return( isElementPresent("jobsSnbAdTitle")|isElementPresent("JobDetails"));
    }

    /**
     * function to validate More button on job page
     */
    public boolean validateMoreButton()
    {
        return isElementPresent("more");
    }
    /**
     * change city from jobs page
     */
    public void chageCityFromJobsPage()
    {
        Mapper.find(domFile,"changeCity").click();  }

    public String checkRole()
    {
        if (isElementPresent("location"))
        {
            String role=Mapper.find(domFile,"location").getText();
            return role;
        }
        else
        {
            String newformat=Mapper.find(domFile,"currentRole").getText();
            return newformat;
        }
    }
    /**
     * set name on apply page
     */

    public void setNameOnApplyPage(String name)
    {
        Mapper.waitForElementToBeVisible(domFile,"name");
        Mapper.find(domFile, "name").sendKeys(name);
    }
    /**
     * select location from apply page
     */
    public void setLocation()
    {
        if (!isElementPresent("location"))
            Mapper.hideKeyboard();
        Mapper.find(domFile,"location").click();
    }

    /**
     * shortlist ads from jobs page
     */
    public void shortlistJobsAds()
    {
        Mapper.waitForElementToBeVisible(domFile,"JobsShortlist");
        Mapper.find(domFile,"JobsShortlist").click();
    }
    /**
     * select role experiance on new ui
     */

    public void clickOnRoleExperiance()
    {
        Mapper.waitForElementToBeVisible(domFile, "ExperianceType");
        if (!isElementPresent("jobsRoleNew"))
            Mapper.hideKeyboard();
        Mapper.find(domFile,"jobsRoleNew").click();
    }
    public void clickOnLanguageKnown()
    {
        Mapper.waitForElementToBeVisible(domFile,"languageKnown");
        Mapper.find(domFile,"languageKnown").click();
    }
    public void clickOnEducationNewUI()
    {
        Mapper.waitForElementToBeVisible(domFile,"education");
        Mapper.find(domFile,"education").click();
    }
    /**
     * set wrong email
     */
    public void setWrongEmailOnApplyPage(String emailId)
    {

        Mapper.waitForElementToBeVisible(domFile,"enterEmailformat1");
        Mapper.find(domFile, "enterEmailformat1").sendKeys(emailId);
//        else
//            Mapper.find(domFile,"enterEmailFormat2").sendKeys(emailId);
    }
    /**
     * open ads from jobs page
     */
    public void openAdFromJobsSnb(int i)
    {
        Mapper.waitForElementToBeVisible(domFile, "jobsSnbAdTitle");
        System.out.println("before clicking title");
        Mapper.finds(domFile, "jobsSnbAdTitle").get(i).click();
        System.out.println("After clicking title");

    }
    /**
     * search respective role
     */

    public void searchRole(String role)
    {
        Mapper.waitForElementToBeVisible(domFile, "searchRole");
        Mapper.find(domFile, "searchRole").sendKeys(role);
    }

    /**
     * function to validate locality list is open
     */
    public boolean validateJobsLocalityList()
    {
        return (isElementPresent("localityOptions") && isElementPresent("OkButton"));
    }

    /**
     * send  incorrect Phone no on apply page
     */

    public void setincorrectPhoneNoApplyPage()
    {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        String phNo = 993923+randomNumbers;
        Mapper.waitForElementToBeVisible(domFile,"phoneNoformat1");
        Mapper.find(domFile,"phoneNoformat1").sendKeys(phNo);
//        else
//            Mapper.find(domFile,"phoneformat2").sendKeys(phNo);

    }
    /**
     * swipe to role Architect
     */
    public void swipeToRoleArchitect()
    {
        Mapper.VerticalSwipe(domFile, "postAd", "search");
    }
    /**
     * select role Architect from jobs CHP
     */

    public void selctRoleArchitectFromJobsCHP()
    {
        Mapper.find(domFile,"Architect").click();
    }

    /**
     * jobs VAP ad title
     * @return
     */
    public String vapAdTitle()
    {
        String adTitle = Mapper.find(domFile, "jobsVapAdTitle").getText().toString();
        return adTitle;
    }

    /**
     * function to validate msg after filling all option on apply page and click on submit button
     */
    public boolean validateMsgAfterClickOnSubmitButtonOnApplyPageOldFormat()
    {
        return( isElementPresent("validateSubmitOldFormat"));
    }

    /**
     * function to send email id on apply page
     */
    public void setEmailOnApplyPageOldFormat()
    {
        String emailId= "swat_" +(int)(10000000* Math.random()) + "@gmail.com";
        Mapper.find(domFile,"enterEmailFormat2").sendKeys(emailId);
    }

    /**
     * set phone number old format
     */
    public void setPhoneNoApplyPageOldFormat() {
        String randomNumbers = RandomStringUtils.randomNumeric(5);
        String phNo = 99393 + randomNumbers;
        Mapper.find(domFile, "phoneformat2").sendKeys(phNo);
    }
    /**
     * select experiance on 2nd screen of apply page
     */
    public void selectExperianceOnSecondScreenOfApplyPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"ExperianceType");
        Mapper.find(domFile,"ExperianceType").click();
    }
    /**
     * select experiance on 2nd screen of apply page
     */
    public void selectIndustryOnSecondScreenOfApplyPage()
    {
        Mapper.find(domFile,"industry").click();
    }
    /**
     * select experiance on 2nd screen of apply page
     */
    public void selectRoleOnSecondScreenOfApplyPage()
    {
        Mapper.find(domFile,"roleExp").click();
    }

    /**
     * select dropdown checkBox
     */
    public void selectCheckBox()
    {

        Mapper.find(domFile, "checkBox").click();
    }
    /**
     * verify create free profile button is present on snab
     */

    public boolean verifyCreateFreeProfileButtonIsPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "jobsCreateProfile");
        String createProfile= Mapper.finds(domFile,"jobsCreateProfile").get(1).getText();
        return (createProfile.equalsIgnoreCase("Create Free Profile"));
    }

/**
 * validate locality on jobs snb
 */
    public String  jobsSnbLoaclity()
    {
        String locality=Mapper.finds(domFile,"jobsSnbLocality").get(1).getText();
        return locality;

    }

    public void selectApplyButtonOnVapPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"jobsSectionTitle");
        Mapper.scroll("Apply");
        Mapper.find(domFile,"applyButtonVap").click();

    }
    /**
     * sort option is not present on snb of JObs
     */
    public boolean sortOptionIsNotPresentOnSnb()
    {
        Mapper.waitForElementToBeVisible(domFile, "newAdFirst");
        return (Mapper.find(domFile,"newAdFirst").getText().equalsIgnoreCase("Newest First"));
    }
    /**
     *Verify user can select a option in 'Locality' field
     */
    public String validateUSerIsAbleToSelectLocality()
    {
        Mapper.waitForElementToBeVisible(domFile,"location");
        return (Mapper.find(domFile,"location").getText().trim());
    }
    /**
     * fetch number of results displayed on snb
     */
    public String noOfResultsOnSnb()
    {
        return (Mapper.finds(domFile, "textView").get(1).getText());
    }
    /**
     * view fulltime jobs Ad from jobs chp
     */
    public void clickOnFullTimeOnChp()
    {
        Mapper.waitForElementToBeVisible(domFile,"fulltimeJobCHP");
        Mapper.find(domFile,"fulltimeJobCHP").click();
    }
    /**
     * apply to job from Snb
     */
    public void applyjobFromSnb()
    {
        Mapper.waitForElementToBeVisible(domFile,"applyButtonVap");
        Mapper.find(domFile,"applyButtonVap").click();
    }
    /**
     * verify user is on Apply page
     */
    public boolean userIsInApplyPage()
    {
        return isElementPresent("jobsSectionTitle");
    }

}


