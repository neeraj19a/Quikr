package com.quikr.website.horizontal.alert;

import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by akhil.singla on 31/5/16.
 */
public class EducationandLearningAlertTests extends TestBase {

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationandLearningwithCareerCounseling(int adType ){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Career Counseling");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationandLearningwithSchoolTuitions(int adType ){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("School Tuitions");
        alertPage.selectadType(adType);
        alertPage.clickSchoolBoardDropDown();
        alertPage.clicksubjectsDropDown();
        alertPage.selectDeliveryMode();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    //@Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void EducationandLearningwithDanceMusicClasses(int adType ){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Dance - Music Classes");
        alertPage.selectadType(adType);
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationandLearningwithHobbyClasses(int adType ){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Hobby Classes");
        alertPage.selectadType(adType);
        alertPage.selectRandomhobby();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    //@Test(groups = "alerts",dataProvider="adType", dataProviderClass = Data.class)
    public void EducationandLearningwithProfessionalShortTermCourses(int adType ){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Professional & Short Term Courses");
        alertPage.selectadType(adType);
        alertPage.selectRandomCourse();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationandLearningwithStudyAbroad(int adType ){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Study Abroad");
        alertPage.selectadType(adType);
        alertPage.selectRandomCountry();
        alertPage.selectRandomservicesOffered();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingCertifications(int adType){

        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Certifications & Training");
        alertPage.selectadType(adType);
        alertPage.selectRandomCourseDomain();
        alertPage.selectRandomCourseCategories();
        alertPage.selectRandomCourses();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.selectDeliveryMode();
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingCompetitiveExams(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Competitive Exams Coaching");
        alertPage.selectadType(adType);
        alertPage.selectRandomCareerStream();
        alertPage.selectRandomSpecialization();
        alertPage.selectDeliveryMode();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingDistanceLearning(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400,2500);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Distance Learning Courses");
        alertPage.selectadType(adType);
        alertPage.selectRandomProgramType();
        alertPage.selectRandomCoursesDistanceLearning();
        alertPage.selectDeliveryMode();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingEntranceExamCoaching(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2800);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Entrance Exam Coaching");
        alertPage.selectadType(adType);
        alertPage.selectRandomCareerStream();
        alertPage.selectRandomSpecialization();
        alertPage.selectDeliveryMode();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingPlaySchools(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2800);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Play Schools - Creche");
        alertPage.selectadType(adType);
        alertPage.selectRandomProgramsOffered();
        alertPage.selectRandomEducationMethod();
        alertPage.selectDeliveryMode();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingStudyAbroadConsultants(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2800);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Study Abroad Consultants");
        alertPage.selectadType(adType);
        alertPage.selectRandomCountry();
        alertPage.selectRandomservicesOffered();
        alertPage.selectDeliveryMode();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingTextBooksStudyMaterials(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2800);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Study Abroad Consultants");
        alertPage.selectadType(adType);
        alertPage.selectRandomProductTypeTextBooks();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingVocationalSkill(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2800);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Vocational Skill Training");
        alertPage.selectadType(adType);
        alertPage.selectRandomIndustry();
        alertPage.selectRandomTrainingType();
        alertPage.selectRandomKeyFeatures();
        String email=getRandomString(9)+"@gmail.com";
        String mobile="9"+getRandomInteger(9);
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }

    @Test(groups = "alerts",dataProvider="adType1", dataProviderClass = Data.class)
    public void EducationAndTrainingWorkShops(int adType){
        AlertPage alertPage=new AlertPage(driver);

        scrollVerticallWithCords(400, 2800);
        alertPage.selectCategory("Education & Training");
        alertPage.selectSubCategory("Workshops");
        alertPage.selectadType(adType);
        String mobile="9"+getRandomInteger(9);
        String email=getRandomString(9)+"@gmail.com";
        alertPage.masterCommonFields(email, mobile);
        alertPage.selectRandomLocality();
        alertPage.clickAlertButton();
        Assert.assertTrue(waitForPageToLoad("recommended-section"), "Expected URL was quikr.com/recommended-section " + "But Found " + getCurrentUrl());
    }
}
