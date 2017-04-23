package com.quikr.app.ios.mobilesAndTabletsPostAd;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.auth.AuthPage;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.menu.MenuPage;
import com.quikr.app.ios.mobilesAndTablets.MobilesAndTabletsPage;
import com.quikr.app.ios.pap.PapPage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.snb.SnbPage;
import com.quikr.app.ios.vap.VapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 9/10/15.
 */
public class MobilesAndTabletsPostAdTests extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_MOBILEANDTABLETSPOSTAD_TESTDATA_FILE"));




    /**
     *  test  post ad for mobiles
     *
     *  iOS-23:User should be able to Preview posted Ad
     */
    @Title("post ad for mobiles")
    @Test(groups = "horizontal")
    public void verifyPostAdForMobileCategory()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
        VapPage vapPage=new VapPage(driver);

 //       homePage.selectDontAllowOption();
     //   homePage.clickOnSelectCity();
      //  homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryValue"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
      //  papPage.selectLocalityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("localityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnNextButton();
        papPage.setAdTitle(testData.get("adTitle"));
        papPage.setAdDescription(testData.get("adDescription"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.selectBrandName(testData.get("brandNameOptionForMobilesCategory"));
        papPage.setPriceForMobileCategory(testData.get("priceAmount"));

      //  papPage.selectApplyButton();
//        papPage.clickOnModel();
//        papPage.selectModelOptionName();
//        papPage.selectApplyButton();
       // papPage.clickOnOptionalDetails();
//        papPage.clickOnMobilesOptionalDetails();
//     //   papPage.setPriceForMobileCategory(testData.get("priceAmount"));
//        papPage.clickOnModel();
//        papPage.selectModelOptionName();
//        papPage.selectApplyButton();
//        papPage.clickOnOperatingSystem();
//        papPage.selectOperatingOptionName();
//        papPage.selectApplyButton();
//        papPage.clickOnNoOfSims();
//        papPage.selectNoOfSimsOption();
//        papPage.selectApplyButton();
//        papPage.clickOnAlsoIncludes();
//        papPage.selectAlsoIncludesOptionName("alsoIncludesOptionNameForMobilesCategory");
//        papPage.selectApplyButton();
        papPage.setPinCodeInPostAd(testData.get("pinCode"),8);
        papPage.setAddressInPostAd(testData.get("address"),9);
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.setMobileNo(testData.get("mobileNo"));
        papPage.selectPostAdButton();
        //Assert.assertTrue(papPage.validatePostAd(),"ad can't post for mobile category");
        Assert.assertEquals(papPage.validatePostAd(),testData.get("successMsgAfterAdPost"),"some issue in posting ad");
        papPage.clickOnViewYourAdButton();
        Assert.assertTrue(vapPage.validatePreviewAdPost(),"preview ad post screen are not display");
    }

    /**
     * iOS-24:User should be able to edit Ad from Preview screen
     */
    @Title("User should be able to edit Ad from Preview screen")
    @Test(groups = "horizontal")
    public void verifyEditAdFromPreviewScreen()
    { HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
        VapPage vapPage=new VapPage(driver);

      //  homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
       // homePage.selectCityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("cityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryValue"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        papPage.clickOnLocalityInAdPost();
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
       // papPage.selectLocalityName();
        papPage.setRequiredTextInSearchBoxAtAdPost(testData.get("localityNameForPostAd"));
        papPage.selectRequiredTextAsInSearchBox(1);
        papPage.clickOnNextButton();
        papPage.setAdTitle(testData.get("adTitle"));
        papPage.setAdDescription(testData.get("adDescription"));
        papPage.selectYouAre(testData.get("youRTypeName"));
        papPage.selectCondition(testData.get("conditionTypeName"));
        papPage.clickOnBrandName();
        papPage.selectBrandName(testData.get("brandNameOptionForMobilesCategory"));
        papPage.setPriceForMobileCategory(testData.get("priceAmount"));
       // papPage.selectApplyButton();
//        papPage.clickOnModel();
//        papPage.selectModelOptionName();
//        papPage.selectApplyButton();
//        papPage.clickOnMobilesOptionalDetails();
//       // papPage.setPriceForMobileCategory(testData.get("priceAmount"));
//        papPage.clickOnModel();
//        papPage.selectModelOptionName();
//        papPage.selectApplyButton();
//        papPage.clickOnOperatingSystem();
//        papPage.selectOperatingOptionName();
//        papPage.selectApplyButton();
//        papPage.clickOnNoOfSims();
//        papPage.selectNoOfSimsOption();
//        papPage.selectApplyButton();
//        papPage.clickOnAlsoIncludes();
//        papPage.selectAlsoIncludesOptionName("alsoIncludesOptionNameForMobilesCategory");
//        papPage.selectApplyButton();
        papPage.setPinCodeInPostAd(testData.get("pinCode"),8);
        papPage.setAddressInPostAd(testData.get("address"),9);
        papPage.selectNextButtonForcibly();
        papPage.setName(testData.get("name"));
        papPage.setEmail(testData.get("emailId"));
        papPage.setMobileNo(testData.get("mobileNo"));
        papPage.selectPostAdButton();
        Assert.assertEquals(papPage.validatePostAd(),testData.get("successMsgAfterAdPost"),"some issue in posting ad");
        papPage.clickOnViewYourAdButton();
        Assert.assertTrue(vapPage.validatePreviewAdPost(),"preview ad post screen are not display");
        vapPage.clickOnEditAdButtonOnVap();
        papPage.setAdDescription(testData.get("adDescriptionInEditAd"));
        papPage.selectSaveChangesButton();
        Assert.assertTrue(papPage.validateEditAdFromPreviewScreen(),"some issue in edit ad from preview screen");
    }

    /**
     * iOS-135:Post ad without Locality
     */
    @Title("Post ad without Locality")
    @Test(groups = "horizontal")
    public void verifyPostAdWithOutLocality()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);


     //   homePage.selectDontAllowOption();
        //homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.clickOnPostAdButton();
        papPage.clickOnNextButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryValue"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        papPage.clickOnNextButton();
        Assert.assertTrue(papPage.validatePostAdWithOutLocality(),"locality is selected or warning popup is not present");
    }

    /**
     *  iOS-288:Sub Cat page of Mobiles and Tablets
     */
    @Title("Sub Cat page of Mobiles and Tablets")
    @Test(groups = "horizontal")
    public void verifyQuikrXSubcategoryNameOnMobilePage()
    {
        HomePage homePage=new HomePage(driver);
        MobilesAndTabletsPage mobilesAndTabletsPage=new MobilesAndTabletsPage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        Assert.assertTrue(mobilesAndTabletsPage.validateQuikrXSubcategoryName(),"QuikrX subcategory are not present");

    }

    /**
     * iOS-14:User can mark the Ads as Starred and Unstarred
     */
    @Title("User can mark the Ads as Starred and Unstarred")
    @Test(groups = "horizontal")
    public void verifyStarAndUnStared()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
     //   QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);
        MenuPage menuPage=new MenuPage(driver);
        VapPage vapPage=new VapPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

       // homePage.selectDontAllowOption();
    //    homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if (homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
        snbPage.openAdOnSnbPageForGeneralCategory(0);
        vapPage.clickOnShortlistButtonOnVap();
       // quikrXVapPage.clickOnBackButtonOnVapPage();
        vapPage.clickOnVapBackButton();
        //snbPage.navigateBack();
        homePage.selectHomeButton();
      //  homePage.clickOnMenuIconButton();
        homePage.selectAccountButton();
        menuPage.selectMyShortlist();
        snbPage.openAdOnSnbPageForGeneralCategory(0);
        Assert.assertTrue(vapPage.validateShortlistPressButton(),"shortlist button can't mark");
        vapPage.selectShortlistPressButton();
      //  quikrXVapPage.clickOnBackButtonOnVapPage();
        vapPage.clickOnVapBackButton();
        Assert.assertTrue(menuPage.validateMyShortlist(),"ad can be present");
    }

    /**
     * iOS-131:Share Ad by mail
     */
    @Title("Share Ad by mail")
    @Test(groups = "horizontal")
    public void verifyShareAdByMail()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        VapPage vapPage=new VapPage(driver);
        MenuPage menuPage=new MenuPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

       // homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if (homePage.checkDontAllowButton())
        {
           realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
        snbPage.openAdOnSnbPageForGeneralCategory(0);
        vapPage.selectVapShareButton();
        menuPage.selectMail();
        Assert.assertTrue(menuPage.validateShareApp(),"app can't share with mail");
    }

    /**
     *  test for delete an ad without login
     */
    @Title("delete an ad without login")
    @Test(groups = "horizontal")
    public void verifyDeleteAdWithoutLogin()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
        MenuPage menuPage=new MenuPage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
      //  homePage.clickOnMenuIconButton();
        homePage.selectAccountButton();
        menuPage.selectMyAds();
        papPage.clickOnMoreButtonInMyAds(1);
        papPage.selectDeleteAdButton();
        papPage.selectDeleteButton();
        Assert.assertTrue(papPage.validateDeletedAd(),"ad can't be deleted");
    }

    /**
     * iOS-17:User can delete Ads mapped to the Logged in user
     */
    @Title("User can delete Ads mapped to the Logged in use")
    @Test(groups = "horizontal")
    public void verifyDeleteAdWihLogin()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
        MenuPage menuPage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);

       // homePage.selectDontAllowOption();
     //   homePage.clickOnSelectCity();
        homePage.selectCityName();
       // homePage.clickOnMenuIconButton();
        homePage.selectAccountButton();
        //menuPage.clickOnMyAccount();
        authPage.clickOnLoginOrSignUp();
        authPage.setEmailInLogin(testData.get("emailId"));
        authPage.setLoginPassword(testData.get("password"));
        authPage.selectLoginButton();
        authPage.clickOnOkButtonAfterLogin();
        authPage.navigateBack();
       // homePage.clickOnMenuIconButton();
        menuPage.selectMyAds();
        papPage.clickOnMoreButtonInMyAds(2);
        papPage.selectDeleteAdButton();
        papPage.selectDeleteButton();
        Assert.assertTrue(papPage.validateDeletedAd(),"ad can't be deleted");
    }

    /**
     * iOS-27:Active Ad status is correctly reflected
     */
    @Title("Active Ad status is correctly reflected")
    @Test(groups = "horizontal")
    public void verifyActiveAdStatusCorrectlyReflected()
    {
        HomePage homePage=new HomePage(driver);
        PapPage papPage=new PapPage(driver);
        MenuPage menuPage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);

       // homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        //homePage.clickOnMenuIconButton();
        homePage.selectAccountButton();
        //menuPage.clickOnMyAccount();
        authPage.clickOnLoginOrSignUp();
        authPage.setEmailInLogin(testData.get("emailId"));
        authPage.setLoginPassword(testData.get("password"));
        authPage.selectLoginButton();
        authPage.clickOnOkButtonAfterLogin();
        authPage.navigateBack();
       // homePage.clickOnMenuIconButton();
        menuPage.selectMyAds();
        Assert.assertTrue(papPage.validateActiveAdStatusCorrectlyReflected(),"ad status does not reflected");
    }
}
