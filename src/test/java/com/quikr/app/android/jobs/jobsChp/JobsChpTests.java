package com.quikr.app.android.jobs.jobsChp;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.jobs.JobsPage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by rohanbajaj on 12/10/15.
 */
public class JobsChpTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_JOBS_TESTDATA_FILE"));
    /**
     * ANDROID-926:Verify the roles available in 'Role' drop down menu
     */
    @Test
    public void verifyRoleListOfJob()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.clickOnSelectRole();
        Assert.assertTrue(jobsPage.validateRoleListOfJob(),"role list is not display");
    }

//    /**
//     * ANDROID-945:Verify the default city selected in JHP
//     */
//    @Test
//    public void verifyCityOnJobPage()
//    {
//        Hompage hompage = new Hompage(driver);
//        JobsPage jobsPage = new JobsPage(driver);
//        hompage.selectcategoriesFromHomePageCategoryMenu();
//        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
//        Assert.assertEquals(testData.get("city"), jobsPage.validateCityOnJobPage());
//    }

//    /**
//     * ANDROID-907:Verify Jobs roles are displayed in list view for new UI
//     */
//    @Test
//    public void verifySelectRoleIsDisplayOnJobPage()
//    {
//        Hompage hompage = new Hompage(driver);
//         JobsPage jobsPage = new JobsPage(driver);
//        //hompage.ApplaunchPopup();
//        hompage.selectcategoriesFromHomePageCategoryMenu();
//        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
//        Assert.assertEquals(testData.get("selectRole"), jobsPage.validateSelectRoleIsDisplayOnJobPage());
//    }

    /**
     * ANDROID-925:Verify the Role can be selected from the 'Select Role' drop down menu
     */
//    @Test
//    public void verifyRoleOptionIsSelectable()
//    {
//        Hompage hompage = new Hompage(driver);
//        JobsPage jobsPage = new JobsPage(driver);
//        //hompage.ApplaunchPopup();
//        hompage.selectcategoriesFromHomePageCategoryMenu();
//        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
//        jobsPage.clickOnSelectRole();
//        jobsPage.searchRole(testData.get("selectRoleOptionName"));
//        jobsPage.selectelementWithoutScroll(testData.get("selectRoleOptionName"), QuikrAppEnums.JOBS_ROLE_LIST);
//        Assert.assertEquals(testData.get("selectRoleOptionName"),jobsPage.validateSelectRoleIsDisplayOnJobPage());
//    }
    //    /**
//     * ANDROID-933:Tap on 'Show More' and verify
//     */
//    @Test
    public void verifyShowMoreButtonAndFunctionality()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(jobsPage.checkShowMoreButton(), "showMore Button can't present");
        jobsPage.selectShowMoreButton();
        Assert.assertTrue(jobsPage.checkShowLessButton(), "More job role can't display");
    }

    /**
     * ANDROID-936:Tap on 'Show Less' and verify
     */
//    @Test
    public void verifyShowLessButtonAndFunctioning()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        jobsPage.checkShowMoreButton();
        jobsPage.selectShowMoreButton();
        Assert.assertTrue(jobsPage.checkShowLessButton(), "showLess button can't present");
        jobsPage.selectShowLessButton();
        Assert.assertTrue(jobsPage.checkShowMoreButton(),"less job can't display");
    }

    /**
     * ANDROID-920:Verify "Post Free Ad" button is available at the bottom right in new JHP
     */
    @Test
    public void verifyPostAdButtonOnJobPage()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(jobsPage.checkPostAdButtonOnJobPage(),"post ad button on job page can't present");
    }

//    /**
//     * ANDROID-927:Verify the city can be selected from the 'Select City' drop down menu
//     */
//    @Test
//    public void verifySelectCityFromDropDown()
//    {
//        Hompage hompage = new Hompage(driver);
//        JobsPage jobsPage = new JobsPage(driver);
//        //hompage.ApplaunchPopup();
//        hompage.selectcategoriesFromHomePageCategoryMenu();
//        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
//        jobsPage.selectCityOnJobPage();
//        hompage.selectelementWithoutScroll(testData.get("NewCity"), QuikrAppEnums.Hompage_SelectCity);
//        Assert.assertEquals(testData.get("NewCity"), jobsPage.validateCityOnJobPage(), "new city is not reflected");
//    }

    /**
     * ANDROID-760:More Button for Jobs Subcategory
     */
    @Test
    public void verifyMoreButtonOnJobPage()
    {
        Hompage hompage = new Hompage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        //hompage.ApplaunchPopup();
        hompage.selectelementWithoutScroll(testData.get("jobs"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(!jobsPage.validateMoreButton(), "more button is present on job page");
    }
    @Test
    public void verifyRoleFieldIsPresentWhileCreatingAlert()
    {

        MenuPage menuPage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        PapPage papPage=new PapPage(driver);
        JobsPage jobsPage = new JobsPage(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("MyAccount"), QuikrAppEnums.CATEGORY_MENU);
        authPage.clickOnMyAlerts();
        alertPage.clickOnCreateAlert();
        papPage.ClickonCategory();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.ClickonSubCategory();
        papPage.selectelementWithoutScroll(testData.get("fullTimeJobs"), QuikrAppEnums.CATEGORY_LOCATION);
        List<String>jobsAlerField=jobsPage.fetchElementsIntoList(testData.get("domElementPostAdTextBOx"));
        Assert.assertTrue(jobsAlerField.contains(testData.get("RoleField")),"Role field is not present while creating Alert"+jobsAlerField);

    }
}
