package com.quikr.app.android.jobs.jobsApply;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.jobs.JobsPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by rohanbajaj on 12/10/15.
 */
public class JobsApplyTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_JOBS_TESTDATA_FILE"));

    /**
     * Android Sanity :2262,2265
     * ANDROID-616:Tap "Apply" button from VAP screen and verify
     * ANDROID-617:Verify UI of "Apply to this Job" screen
     */
    @Test
    public void verifyApplyPageOptionForJob() throws Exception
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        VapPage vapPage = new VapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        //snbPage.hideOverlayLayout();
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
        Assert.assertTrue(jobsPage.validateApplyJobPageOption(), "apply page all options can't display");
    }

    /**
     * Android Sanity 2267:Tap on 'APPLY' button without filling the fields in Apply to job step 1
     * ANDROID-618:Tap "Apply" button in Step 1 screen without filling in any fields and verify
     */
    @Test
    public void verifyTextAfterClickOnApplyButtonWithoutFillingOptions()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        //snbPage.hideOverlayLayout();
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
        jobsPage.selectApplyButtonOnApplyPage();
        Assert.assertTrue(jobsPage.validateTextAfterClickOnApplyButtonWithoutFillingOptionsOnApplyPage(), "required text is not display");
    }

    /**
     * ANDROID-619:Enter mobile number more than 10 digits in "Phone Number" field and tap "Apply" button
     */
    @Test
    public void verifyPhoneNoOnApplyPage()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        VapPage vapPage = new VapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
        jobsPage.setEmailOnApplyPage();
        // jobsPage.setNameOnApplyPage(testData.get("name"));
        jobsPage.hideKeyboard();
        jobsPage.setincorrectPhoneNoApplyPage();
        jobsPage.hideKeyboard();
        jobsPage.selectApplyButtonOnApplyPage();
        Assert.assertTrue(jobsPage.validateWrongPhoneNoOnApplyPage(),"enter mb no is correct");
    }

    /**
     * ANDROID-621:Enter invalid email format in "Email" field an tap "Apply" button
     */
    @Test
    public void verifyWrongEmail()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        //snbPage.hideOverlayLayout();
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
        jobsPage.setWrongEmailOnApplyPage(testData.get("wrongEmail"));
        jobsPage.hideKeyboard();
        jobsPage.setPhoneNoApplyPage();
        jobsPage.hideKeyboard();
        jobsPage.selectApplyButtonOnApplyPage();
        Assert.assertTrue(jobsPage.validateWrongEmail(),"enter email id is correct");
    }

    /**
     * Android Sanity 2272
     * ANDROID-622:Fill in all fields in Step 1 screen and tap "Apply" button
     *  ANDROID-623:Verify UI of Step 2 screen
     */
    @Test

    public void verifyApplyPageOptionOnSecondScreen()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        PapPage papPage = new PapPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        //snbPage.hideOverlayLayout();
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
//        String format=jobsPage.checkRole();
//        System.out.println(format);
//        switch (format)
//        {
//            case "Current Role":
//                jobsPage.selectCurrentRole();
//                jobsPage.selectCurrentRoleOptionsName();
//                jobsPage.setEmailOnApplyPageOldFormat();
//                jobsPage.setPhoneNoApplyPageOldFormat();
//                jobsPage.hideKeyboard();
//                jobsPage.selectApplyButtonOnApplyPage();
//                jobsPage.selectEnterName(testData.get("name"));
//                jobsPage.clickOnEducationalQualification();
//                jobsPage.selectEducationOptionName();
//                jobsPage.clickOnTotalExperience();
//                jobsPage.selectExperienceOptionName();
//                jobsPage.selectSalaryOnApplyPage(testData.get("salary"));
//                jobsPage.selectSubmitButtonOfApplyPage();
//                Assert.assertTrue(jobsPage.validateMsgAfterClickOnSubmitButtonOnApplyPageOldFormat(), "apply page 2nd screen all option can't present");
//
//                break;
//            case "Locality":
        jobsPage.setNameOnApplyPage(testData.get("name"));
        jobsPage.setLocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        jobsPage.setEmailOnApplyPage();
        jobsPage.setPhoneNoApplyPage();
        jobsPage.selectApplyButtonOnApplyPage();
        jobsPage.clickOnRoleExperiance();
        jobsPage.selectelementWithoutScroll(testData.get("RoleExp"), QuikrAppEnums.Jobs_Role_Exp);
        jobsPage.clickOnLanguageKnown();
        jobsPage.selectelementWithoutScroll(testData.get("languageKnown"), QuikrAppEnums.Jobs_New_language);
        alertPage.selectCheckBox();
        jobsPage.scroll("Industry");
        jobsPage.selectIndustryOnSecondScreenOfApplyPage();
        jobsPage.selectelementWithoutScroll(testData.get("inddustry"), QuikrAppEnums.Jobs_New_language);
        alertPage.selectCheckBox();
        jobsPage.selectSubmitButtonOfApplyPage();
        Assert.assertTrue(jobsPage.validateApplyPageSecondScreen(), "apply page 2nd screen all option can't present");



    }

    /**
     *  ANDROID-633:Fill in all fields in Step 2 and tap "Submit" button
     */
    @Test
    public void verifyActionOfSubmitButtonAfterFillingAllOptionOnApplyPage()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        PapPage papPage = new PapPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        //snbPage.hideOverlayLayout();
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
//        String format=jobsPage.checkRole();
//        System.out.println(format);
//        switch (format)
//        {
//            case "Current Role":
//                jobsPage.selectCurrentRole();
//                jobsPage.selectCurrentRoleOptionsName();
//                jobsPage.setEmailOnApplyPageOldFormat();
//                jobsPage.setPhoneNoApplyPageOldFormat();
////                jobsPage.hideKeyboard();
//                jobsPage.selectApplyButtonOnApplyPage();
//                jobsPage.selectEnterName(testData.get("name"));
//                jobsPage.clickOnEducationalQualification();
//                jobsPage.selectEducationOptionName();
//                jobsPage.clickOnTotalExperience();
//                jobsPage.selectExperienceOptionName();
//                jobsPage.selectSalaryOnApplyPage(testData.get("salary"));
//                jobsPage.selectSubmitButtonOfApplyPage();
//                Assert.assertTrue(jobsPage.validateMsgAfterClickOnSubmitButtonOnApplyPageOldFormat(),"Required msg can't display");
//                break;
//            case "Locality":
        jobsPage.setNameOnApplyPage(testData.get("name"));
        jobsPage.setLocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        jobsPage.setEmailOnApplyPage();
        jobsPage.setPhoneNoApplyPage();
        jobsPage.selectApplyButtonOnApplyPage();
        jobsPage.clickOnRoleExperiance();
        jobsPage.selectelementWithoutScroll(testData.get("RoleExp"), QuikrAppEnums.Jobs_Role_Exp);
        jobsPage.clickOnLanguageKnown();
        jobsPage.selectelementWithoutScroll(testData.get("languageKnown"), QuikrAppEnums.Jobs_New_language);
        alertPage.selectCheckBox();
        jobsPage.scroll("Industry");
        jobsPage.selectIndustryOnSecondScreenOfApplyPage();
        jobsPage.selectelementWithoutScroll(testData.get("inddustry"), QuikrAppEnums.Jobs_New_language);
        alertPage.selectCheckBox();
        jobsPage.selectSubmitButtonOfApplyPage();
        Assert.assertTrue(jobsPage.validateMsgAfterClickOnSubmitButtonOnApplyPage(), "Required msg can't display");




    }

    /**
     *  ANDROID-701:Open "Apply for more Jobs" Step 1 screen again after applying for a job
     */
    // @Test
    public void verifyPreFillEntryOnApplyPageScreen1()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        PapPage papPage = new PapPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
//        String format=jobsPage.checkRole();
//        System.out.println(format);
//        switch (format)
//        {
//            case "Current Role":
//                jobsPage.selectCurrentRole();
//                jobsPage.selectCurrentRoleOptionsName();
//                jobsPage.setEmailOnApplyPageOldFormat();
//                jobsPage.setPhoneNoApplyPageOldFormat();
//                jobsPage.hideKeyboard();
//                jobsPage.selectApplyButtonOnApplyPage();
//                jobsPage.selectNoThanks();
//                jobsPage.selectApplyToMoreJobsOnApplyPage();
//                break;
//            case "Locality":
        jobsPage.setNameOnApplyPage(testData.get("name"));
        jobsPage.setLocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        jobsPage.setEmailOnApplyPage();
        jobsPage.setPhoneNoApplyPage();
        jobsPage.hideKeyboard();
        jobsPage.selectApplyButtonOnApplyPage();


        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
        Assert.assertEquals(testData.get("email"), jobsPage.getEmail());
        Assert.assertEquals(testData.get("mbNumber"), jobsPage.getPhoneNo());
    }

    /**
     * ANDROID-704:Open "Apply for more Jobs" Step 2 screen again after submitting for a job
     */
    @Test
    public void verifyPreFillEntryOnApplyPageScreen2()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        PapPage papPage = new PapPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectElements(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
//        String format=jobsPage.checkRole();
//        System.out.println(format);
//        switch (format)
//        {
//            case "Current Role":
//                jobsPage.selectCurrentRole();
//                jobsPage.selectCurrentRoleOptionsName();
//                jobsPage.setEmailOnApplyPageOldFormat();
//                jobsPage.setPhoneNoApplyPageOldFormat();
//                jobsPage.hideKeyboard();
//                jobsPage.selectApplyButtonOnApplyPage();
//                jobsPage.selectEnterName(testData.get("name"));
//                jobsPage.clickOnEducationalQualification();
//                jobsPage.selectEducationOptionName();
//                jobsPage.clickOnTotalExperience();
//                jobsPage.selectExperienceOptionName();
//                jobsPage.selectSalaryOnApplyPage(testData.get("salary"));
//                jobsPage.selectSubmitButtonOfApplyPage();
//                jobsPage.selectApplyToMoreJobsOnApplyPage();
//
//                break;
//            case "Locality":
        jobsPage.setNameOnApplyPage(testData.get("name"));
        jobsPage.setLocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        jobsPage.setEmailOnApplyPage();
        jobsPage.setPhoneNoApplyPage();
        jobsPage.selectApplyButtonOnApplyPage();
        jobsPage.clickOnRoleExperiance();
        jobsPage.selectelementWithoutScroll(testData.get("RoleExp"), QuikrAppEnums.Jobs_Role_Exp);
        jobsPage.clickOnLanguageKnown();
        jobsPage.selectelementWithoutScroll(testData.get("languageKnown"), QuikrAppEnums.Jobs_New_language);
        alertPage.selectCheckBox();
        jobsPage.scroll("Industry");
        jobsPage.selectIndustryOnSecondScreenOfApplyPage();
        jobsPage.selectelementWithoutScroll(testData.get("inddustry"), QuikrAppEnums.Jobs_New_language);
        alertPage.selectCheckBox();
        jobsPage.selectSubmitButtonOfApplyPage();
        jobsPage.openAdFromJobsSnb(0);
        jobsPage.selectApplyButtonOnVapPage();
        Assert.assertEquals(testData.get("name"), jobsPage.getPrefilledData(0));
        Assert.assertNotNull(jobsPage.getPrefilledData(1), "Email not prefilled");
        Assert.assertNotNull(jobsPage.getPrefilledData(2), "Mobile not prefilled");
    }

    /**
     * Android Sanity:2273
     * Verify whether user is able to submit the application without filling any of the fields
     */
//    @Test

    public void VerifyUserIsAbletoSubmitFormWithoutFilling()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        VapPage vapPage = new VapPage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
//        String format=jobsPage.checkRole();
//        System.out.println(format);
//        switch (format)
//        {
//            case "Current Role":
//                jobsPage.selectCurrentRole();
//                jobsPage.selectCurrentRoleOptionsName();
//                jobsPage.setEmailOnApplyPageOldFormat();
//                jobsPage.setPhoneNoApplyPageOldFormat();
//                jobsPage.hideKeyboard();
//                jobsPage.selectApplyButtonOnApplyPage();
//                jobsPage.selectSubmitButtonOfApplyPage();
//                Assert.assertTrue(jobsPage.validateMsgAfterClickOnSubmitButtonOnApplyPageOldFormat(), "test not valid for old apply flow");
//
//                break;
//            case "Locality":
        jobsPage.setNameOnApplyPage(testData.get("name"));
        jobsPage.setLocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        jobsPage.setEmailOnApplyPage();
        jobsPage.setPhoneNoApplyPage();
        jobsPage.selectApplyButtonOnApplyPage();
        jobsPage.selectSubmitButtonOfApplyPage();
        Assert.assertTrue(jobsPage.validateApplyPageSecondScreen(), "apply page 2nd screen all option not  present");


    }

    /**Android Sanity 2275
     * Verify user can skip the application process by tapping on 'Skip' in apply to jobs step 2
     */

    @Test
    public void VerifyUserIsAbletoSkipsecondPage()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        VapPage vapPage = new VapPage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        //snbPage.hideOverlayLayout();
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
//        String format=jobsPage.checkRole();
//        System.out.println(format);
//        switch (format)
//        {
//            case "Current Role":
//                jobsPage.selectCurrentRole();
//                jobsPage.selectCurrentRoleOptionsName();
//                jobsPage.setEmailOnApplyPageOldFormat();
//                jobsPage.setPhoneNoApplyPageOldFormat();
//                jobsPage.hideKeyboard();
//                jobsPage.selectApplyButtonOnApplyPage();
//                jobsPage.selectNoThanks();
//                Assert.assertTrue(jobsPage.validateMsgAfterClickOnSubmitButtonOnApplyPageOldFormat(), "user not able to skip 2nd step in apply page");
//
//                break;
//            case "Locality":
        jobsPage.setNameOnApplyPage(testData.get("name"));
        jobsPage.setLocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        jobsPage.setEmailOnApplyPage();
        jobsPage.setPhoneNoApplyPage();
        jobsPage.selectApplyButtonOnApplyPage();
        jobsPage.selectSkip();
        Assert.assertTrue(jobsPage.validateApplyPageSecondScreen(), "user not able to skip 2nd step in apply page");
    }
    /**
     * Verify user can select a option in 'Locality' field
     */
    @Test
    public void userIsableToSelectLocality()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        VapPage vapPage = new VapPage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        jobsPage.selectelementWithoutScroll(testData.get("newRole"), QuikrAppEnums.JOBS_ROLE_LIST);
        jobsPage.openAdFromJobsSnb(0);
        vapPage.selectApplyButtonOnVap();
        jobsPage.setLocation();
        papPage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.PAP_NEW_LOCALITY);
        Assert.assertTrue(jobsPage.validateUSerIsAbleToSelectLocality().equalsIgnoreCase(testData.get("locality")),"selected "+testData.get("locality")+"Actual="+jobsPage.validateUSerIsAbleToSelectLocality());

    }

}

