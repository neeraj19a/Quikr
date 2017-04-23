package com.quikr.app.ios.realEstate.realEstateVapPage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import com.quikr.app.ios.realEstate.realEstateHeaderPage.RealEstateHeaderPage;
import com.quikr.app.ios.realEstate.realEstateHomePage.RealEstateHomePage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.snb.SnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 27/11/15.
 */
public class RealEstateVapPageTests extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_REALESTATEVAPPAGE_TESTDATA_FILE"));

    /**
     * iOS-2430:Verify posting the ad without providing mobile No. ( Offering)
     *
     * preview post ad
     */
    @Title("posting the ad without providing mobile No. ( Offering) for realEstate")
    @Test
    public void verifyPostAdWithoutProvidingMobileNo()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
      //  VapPage vapPage=new VapPage(driver);

 //       homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("realEstateSubCategory"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        papPage.selectLocalityName();
        papPage.clickOnNextButton();
        papPage.setAdTitle(testData.get("adTitle"));
        papPage.setAdDescription(testData.get("adDescription"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.clickOnNoOfRooms();
        papPage.selectNoOfRooms();
       // papPage.selectApplyButton();
        papPage.clickOnAreaSqFt();
        papPage.selectAreaSqFt(testData.get("areaSqFt"));
     //   papPage.selectApplyButton();
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.selectPostAdButton();
        Assert.assertTrue(papPage.validatePopupMsgForPostAdWithoutMbNo(),"popup is not present or the msg is mismatch as required ");
//        Assert.assertEquals(papPage.validatePostAd(), testData.get("successMsgAfterAdPost"), "some issue in posting ad");
//        papPage.clickOnViewYourAdButton();
//        Assert.assertTrue(vapPage.validatePreviewAdPost(),"preview ad post screen are not display");
    }

    /**
     *  iOS-2430:Verify posting the ad without providing mobile No. (Wanted)
     *
     *  preview post ad
     */

    @Title("posting the ad without providing mobile No. (Wanted) for realEstate")
    @Test
    public void verifyPostAdWithoutProvidingMobileNoForWantAd()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
      //  VapPage vapPage=new VapPage(driver);

  //      homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("realEstateSubCategory"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        papPage.selectLocalityName();
        papPage.clickOnNextButton();
        papPage.setAdTitle(testData.get("adTitleForWantAd"));
        papPage.setAdDescription(testData.get("adDescriptionForWantAd"));
        papPage.selectAdTypeForRealEstate();
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.clickOnNoOfRooms();
        papPage.selectNoOfRooms();
       // papPage.selectApplyButton();
        papPage.clickOnAreaSqFt();
        papPage.selectAreaSqFt(testData.get("areaSqFt"));
      //  papPage.selectApplyButton();
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.selectPostAdButton();
        Assert.assertTrue(papPage.validatePopupMsgForPostAdWithoutMbNo(),"popup is not present or the msg is mismatch as required ");
//        Assert.assertEquals(papPage.validatePostAd(), testData.get("successMsgAfterAdPost"), "some issue in posting ad");
//        papPage.clickOnViewYourAdButton();
//        Assert.assertTrue(vapPage.validatePreviewAdPost(),"preview ad post screen are not display");
    }

    /**
     *  iOS-2431:Verify the error pop up when email id is not provided while posting ad
     */
    @Title("error pop up when email id is not provided while posting ad")
    @Test
    public void verifyPopUpMsgInPostAdIfNotProvideEmail()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);

     //   homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("realEstateSubCategory"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        papPage.selectLocalityName();
        papPage.clickOnNextButton();
        papPage.setAdTitle(testData.get("adTitle"));
        papPage.setAdDescription(testData.get("adDescription"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.clickOnNoOfRooms();
        papPage.selectNoOfRooms();
       // papPage.selectApplyButton();
        papPage.clickOnAreaSqFt();
        papPage.selectAreaSqFt(testData.get("areaSqFt"));
      //  papPage.selectApplyButton();
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.selectPostAdButton();
        Assert.assertTrue(papPage.validateEmailNotProvided(),"email id is provide in posting ad");
    }

    /**
     * iOS-2459:Tap on 'CALL' button in VAP and verify
     */
    @Title("Tap on 'CALL' button in VAP and verify")
    @Test
    public void verifyCallButtonOnVapPageForRealEstate()
    {
        HomePage homePage=new HomePage(driver);
        RealEstateHomePage realEstateHomePage=new RealEstateHomePage(driver);
        RealEstateHeaderPage realEstateHeaderPage=new RealEstateHeaderPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        RealEstateVapPage realEstateVapPage=new RealEstateVapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

        // homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"));
        realEstateHomePage.clickOnLocalityOrProjectsOrBuildersSearchBox();
        realEstateHeaderPage.setLocalityProjectsOrBuilders(testData.get("localityName"));
        realEstateHeaderPage.selectSearchButtonOfKeyboard();
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.openAdOnSnbPageForGeneralCategory(0);
        Assert.assertTrue(realEstateVapPage.validateCallButtonVapPage(),"callButton can't present");
        realEstateVapPage.selectCallButtonOnVapPage();
        Assert.assertTrue(realEstateVapPage.validateCallAndCancelButtonPopUp(),"call and cancel popup can't display");
    }
}
