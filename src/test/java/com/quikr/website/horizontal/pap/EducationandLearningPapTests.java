package com.quikr.website.horizontal.pap;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 30/5/16.
 */
public class EducationandLearningPapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("PAP_TESTDATA_FILE"));

    @Test()
    public void EducationAndLearningWithDistanceLearningCourses(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap elPap = new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.DISTANCE_LEARNING_COURSES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We are offering Distance Learning Courses for all major courses. "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        elPap.clickProgramType();
        elPap.selectRandomProgram();
        elPap.clickCourse();
        elPap.selectRandomCourse();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        elPap.inputAffiliatedUniversity("VTU");
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test()
    public void EducationandLearningwithCareerCounseling(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.CAREER_COUNSELLING);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        educationandLearningPap.clickEducationStream();
        educationandLearningPap.selectRandomEducationStream();
        String adTitle=getRandomString(5)+" We are conducting Career Counseling session near Bannerghatta "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        educationandLearningPap.setCounselling_Fees("2500");
        educationandLearningPap.setInstitute_Name(testData.get("instituteName"));
        educationandLearningPap.setInstitute_Website(testData.get("instituteWebsite"));
        educationandLearningPap.setInstitute_Address(testData.get("instituteAddress"));
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    //@Test() This sub-category doesn't exists anymore
    public void EducationandLearningwithCoachingTuitions(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.COACHING_AND_TUITIONS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide coaching and Tuitions near Bannerghatta "+getRandomString(10);
        //papPage.selectAdType();
        papPage.inputAdTitle(adTitle);
        educationandLearningPap.selectRandomCoaching_Tuitions();
        educationandLearningPap.selectRandomBoard_Entrance_Exams();
        educationandLearningPap.selectRandomSubjects();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test()
    public void EducationandLearningWithCompetitiveExamsCoaching(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.COMPETITIVE_EXAMS_COACHING);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide COMPETITIVE EXAMS COACHING near Bannerghatta "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        educationandLearningPap.selectRandomCareer_Stream();
        educationandLearningPap.selectRandomSpecialization();
        educationandLearningPap.setCourse_Duration("12");
        educationandLearningPap.setCoaching_Fees("60000");
        educationandLearningPap.selectRandomDelivery_Mode();
        educationandLearningPap.setInstitute_Name(testData.get("instituteName"));
        educationandLearningPap.setInstitute_Website(testData.get("instituteWebsite"));
        educationandLearningPap.setInstitute_Address(testData.get("instituteAddress"));
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }


    //@Test() This subcat doesn't exist anymore
    public void EducationandLearningWithDanceMusicClasses(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.DANCE_MUSIC_CLASSES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide Dance - Music Classes near WhiteField "+getRandomString(10);

        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void EducationAndLearningWithEntranceExamCoaching(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.ENTRANCE_EXAM_COACHING);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide Entrance Exam Coaching near WhiteField "+getRandomString(10);

        papPage.inputAdTitle(adTitle);
        educationandLearningPap.selectRandomCareer_Stream();
        educationandLearningPap.selectRandomSpecialization();
        educationandLearningPap.setCourse_Duration("12");
        educationandLearningPap.setCoaching_Fees("60000");
        educationandLearningPap.selectRandomDelivery_Mode();
        educationandLearningPap.setInstitute_Name(testData.get("instituteName"));
        educationandLearningPap.setInstitute_Website(testData.get("instituteWebsite"));
        educationandLearningPap.setInstitute_Address(testData.get("instituteAddress"));
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void EducationAndLearningWithHobbyClasses(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.HOBBY_CLASSES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide Hobby Classes in  WhiteField "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        educationandLearningPap.selectRandomHobby();
        educationandLearningPap.selectRandomEligbility();
        educationandLearningPap.selectRandomClasses_Held();
        educationandLearningPap.selectRandomClass_Timings();
        educationandLearningPap.setMonthly_Fees("4000");
        educationandLearningPap.setInstitute_Name(testData.get("instituteName"));
        educationandLearningPap.setInstitute_Website(testData.get("instituteWebsite"));
        educationandLearningPap.setInstitute_Address(testData.get("instituteAddress"));
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test()
    public void EducationAndLearningWithPlaySchoolsCreche(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap elpap = new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.PLAY_SCHOOLS_CRECHE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide Play Schools - Creche services in  WhiteField "+getRandomString(10);

        elpap.clickProgramsOffered();
        elpap.selectRandomProgramsOffered();
        elpap.selectAgeGroup();
        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    //@Test() This sub cat doesn't exists any more.
    public void EducationAndLearningWithProfessionalAndShortTermCourses(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.PROFESSIONAL_AND_SHORT_TERM_COURSES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide Professional & Short Term Courses in  WhiteField "+getRandomString(10);

        papPage.inputAdTitle(adTitle);
        educationandLearningPap.selectRandomCourseType();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void EducationAndLearningWithSchoolTuitions(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.SCHOOL_TUITIONS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide SCHOOL TUITIONS for all classes in  WhiteField "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        educationandLearningPap.selectRandomSchool_Board();
        educationandLearningPap.selectRandomSubjects();
        educationandLearningPap.selectRandomStandard();
        educationandLearningPap.selectRandomDelivery_Mode();
        educationandLearningPap.setMonthly_Tuition_Fees("4000");
        educationandLearningPap.setInstitute_Name(testData.get("instituteName"));
        educationandLearningPap.setInstitute_Website(testData.get("instituteWebsite"));
        educationandLearningPap.setInstitute_Address(testData.get("instituteAddress"));
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void EducationAndLearningWithStudyAbroad(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.STUDY_ABROAD);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" STUDY ABROAD with Job Assistance one of the top study abroad education consultancy " + getRandomString(10);
        papPage.inputAdTitle(adTitle);
        educationandLearningPap.selectRandomCountry();
        scrollVerticallWithCords(400, 1000);
        educationandLearningPap.selectRandomServices_Offered();
        educationandLearningPap.selectRandomEducationStream();
        educationandLearningPap.setInstitute_Name(testData.get("instituteName"));
        educationandLearningPap.setInstitute_Website(testData.get("instituteWebsite"));
        educationandLearningPap.setInstitute_Address(testData.get("instituteAddress"));
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test()
    public void EducationAndLearningWithTextbooksAndStudyMaterial(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap educationandLearningPap=new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.TEXT_BOOKS_AND_STUDY_MATERIAL);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide Text books & Study Material of all courses in Marathalli and Whitefield Area" + getRandomString(10);

        papPage.inputAdTitle(adTitle);
        educationandLearningPap.selectRandomProduct_Type();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test()
    public void EducationAndLearningWithWorkshops(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.WORKSHOPS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" Workshops and training programs in Bangalore, Career, Hobby, Photography and Art workshops, classes and seminar events in Bangalore." + getRandomString(10);

        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void verifyCertificationsAndTraining(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        EducationandLearningPap elPap = new EducationandLearningPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.EDUCATION_AND_TRAINING, QuikrEnums.EducationLearningSubCat.CERTIFICATIONS_AND_TRAINING);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");

        elPap.clickCourseDomain();
        elPap.verifyCourseDomainValues();
        //elPap.selectRandomCourseDomainValues();
        elPap.selectDomain("computer training");
        String selectedDomainText = elPap.getSelectedDomainsText();
        elPap.clickCourseCategory();
        elPap.verifyCourseCategories();
        elPap.selectAllCourseCategories();
        String adtitle = getRandomInteger(5)+" this is a test ad with following domain >>"+selectedDomainText;
        papPage.inputAdTitle(adtitle);
        String selectedCourseCats = elPap.getTheSelectedCourseCategories();
        elPap.clickCourses();
        elPap.clickAllCoursesAvailable();
        papPage.enterCity("Bangalore");
        String selectedCourses = elPap.getTheSelectedCourses();
        papPage.selectLocality();
        papPage.inputDescription("This is a sample test description. This is a sample test description. This is a sample test description.");
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
        papPage.clickEditThisAd();
        String extractedDomainText = elPap.getSelectedDomainsText();
        String extractedSelectedCourseCats = elPap.getTheSelectedCourseCategories();
        String extractedCourses = elPap.getTheSelectedCourses();
        logger.info("Initial >>"+selectedDomainText+" | "+selectedCourseCats+" | "+selectedCourses);
        logger.info("Final >>"+extractedDomainText+" | "+extractedSelectedCourseCats+" | "+extractedCourses);
        Assert.assertEquals(extractedDomainText,selectedDomainText,"Domain text are not the same!");
        Assert.assertEquals(extractedSelectedCourseCats,selectedCourseCats, "The course categoeries are not the same. Please check!");
        Assert.assertEquals(extractedCourses,selectedCourses,"The courses are not same.");
    }
}
