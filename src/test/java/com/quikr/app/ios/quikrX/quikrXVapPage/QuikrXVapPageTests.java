package com.quikr.app.ios.quikrX.quikrXVapPage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.auth.AuthPage;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import com.quikr.app.ios.quikrX.quikrXSnbPage.QuikrXSnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 16/10/15.
 */
public class QuikrXVapPageTests extends AppiOSTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_QUIKRXVAPPAGE_TESTDATA_FILE"));

    /**
     * iOS-283:Cart icon
     *
     *  iOS-295:Cart button in VAP of Buy certified used phone
     */
    @Title("Cart icon and Cart button in VAP of Buy certified used phone")
    @Test
    public void verifyCartIcon()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

//        homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("buyCertifiedUsedPhone"));
        Assert.assertTrue(quikrXSnbPage.validateBuyCertifiedUsedPhoneTextOnSnbPage(),"snb page is not display");
        quikrXSnbPage.openAdOnSnbPage(0);
//        quikrXVapPage.setPinCode(testData.get("pinCode"));
//        quikrXVapPage.clickOnVerifyButton();
        Assert.assertTrue(quikrXVapPage.validateCartOption(),"cart icon can't display");
    }

    /**
     *  iOS-290:Back button in the VAP screen of "Exchange old for new"
     */
    @Title("Back button in the VAP screen of \"Exchange old for new\"")
    @Test
    public void verifyFunctionalityOfBackButtonOnVapPage()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

  //      homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.clickOnBackButtonOnVapPage();
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"some issue with back button");
    }

    /**
     * QX_29
     *
     * QX_30 , QX_31 , QX_32
     *
     * iOS-292:Format of VAP for "Exchange old for New"
     */
    @Title("Format of VAP for \"Exchange old for New\"")
    @Test
    public void verifyAllDetailsForExchangeOldForNew()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);
        PapPage papPage=new PapPage(driver);


    //    homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.setPinCode(testData.get("pinCode"));
       // quikrXVapPage.clickOnVerifyButton();
        quikrXVapPage.clickOnBrandNameOnVapPage();
        quikrXVapPage.selectBrandNameOptionOnVapPage(testData.get("brandNameOptionOnVapPage"));
        papPage.selectApplyButton();
        quikrXVapPage.clickOnModelOnVapPage();
        quikrXVapPage.selectModelName(testData.get("modelNameOption"));
        papPage.selectApplyButton();
        Assert.assertTrue(quikrXVapPage.validateQuikrXVapPageOptions(),"all the details are not match");
    }

    /**
     * iOS-289:VAP of "Exchange old for new"
     */
    @Title("VAP of \"Exchange old for new\"")
    @Test
    public void verifyVapOfExchangeOldForNew()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

      //  homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        quikrXSnbPage.openAdOnSnbPage(0);
        Assert.assertTrue(quikrXVapPage.validateVerifyButtonOnVapPageOfQuikrX(),"not redirect on vap page ");

    }

    /**
     * iOS-293:VAP of "Certified used phones"
     */
    @Title("VAP of \"Certified used phones\"")
    @Test
    public void verifyVapOfCertifiedUsedPhones()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

        //homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("buyCertifiedUsedPhone"));
        quikrXSnbPage.openAdOnSnbPage(0);
        Assert.assertTrue(quikrXVapPage.validateVerifyButtonOnVapPageOfQuikrX(),"not redirect on vap page ");
    }

    /**
     *  iOS-294:Back button in the VAP screen of Buy certified used phones
     */
    @Title("Back button in the VAP screen of Buy certified used phones")
    @Test
    public void verifyBackButtonFunctionalityOnVapPageForBuyCertifiedUsedPhone()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

      //  homePage.selectDontAllowOption();
     //   homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("buyCertifiedUsedPhone"));
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.clickOnBackButtonOnVapPage();
        Assert.assertTrue(quikrXSnbPage.validateBuyCertifiedUsedPhoneTextOnSnbPage(),"some issue with back button");
    }

    /**
     * iOS-297:My Cart page
     *
     * iOS-298:Payment page
     *
     * iOS-299:Login Page
     *
     * iOS-300:Delivery page
     *
     * iOS-301:Success Page
     */
    @Title("verify my cart , payment , login, delivery and success page ")
    @Test
    public void verifyMyCartPage()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);


    //    homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("buyCertifiedUsedPhone"));
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.setPinCode(testData.get("pinCode"));
        quikrXVapPage.clickOnVerifyButton();
        quikrXVapPage.clickOnBuyNowButton();
        //quikrXVapPage.clickOnBackButtonOnVapPage();
        quikrXVapPage.clickOnBackButtonOncheckoutPage();
       // quikrXVapPage.clickOnCartButton();
       // quikrXVapPage.clickOnBackButtonOnVapPage();
        quikrXVapPage.clickOnCartButtonAfterAdditionOfItem();
        quikrXVapPage.selectContinueButtonOnMyCartPage();
        Assert.assertTrue(quikrXVapPage.validateCheckoutPage1(), "required options are not present");
        quikrXVapPage.setEmailOnCheckoutPage(testData.get("emailId"));
        quikrXVapPage.selectContinueButtonOnCheckoutPage();
        Assert.assertTrue(quikrXVapPage.validateCheckoutPage2(),"delivery details all options are not present");
        quikrXVapPage.setNameOnCheckoutPage2(testData.get("userName"));
        quikrXVapPage.setPinCodeOnCheckoutPage2(testData.get("pinCode"));
        quikrXVapPage.setPhoneNoOnCheckoutPage2(testData.get("mobileNo"));
        quikrXVapPage.setAddressOnCheckoutPage2(testData.get("address"));
        quikrXVapPage.selectContinueButtonOnPaymentPage();
        Assert.assertTrue(quikrXVapPage.validateMsgAfterPlacingAnOrder(),"order can't place successfully");
    }

    /**
     * QX_28
     *
     * Tap on 'Buy Now' button without selecting the brand and verify
     */
    @Title("Tap on 'Buy Now' button without selecting the brand and verify")
    @Test
    public void  verifyToastMsgForExchangeForNewWithoutSelectingBrandName()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.setPinCode(testData.get("pinCode"));
        quikrXVapPage.clickOnVerifyButton();
        quikrXVapPage.clickOnBuyNowButton();
        Assert.assertTrue(quikrXVapPage.validateToastMsgForBrandNameNotSelected(),"msg popup is not present for brand notification");
    }

    /**
     * QX_33
     *
     * Tap on 'Buy Now' button without selecting the model and verify
     */
    @Title("Tap on 'Buy Now' button without selecting the model and verify")
    @Test
    public void  verifyToastMsgForExchangeForNewWithoutSelectingModelName()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);
        PapPage papPage=new PapPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.setPinCode(testData.get("pinCode"));
        quikrXVapPage.clickOnVerifyButton();
        quikrXVapPage.clickOnBrandNameOnVapPage();
        quikrXVapPage.selectBrandNameOptionOnVapPage(testData.get("brandNameOptionOnVapPage"));
        papPage.selectApplyButton();
        quikrXVapPage.clickOnBuyNowButton();
        Assert.assertTrue(quikrXVapPage.validateToastMsgForModelNameNotSelected(),"msg popup is not present for model name  notification");
    }


    /**
     * QX_34
     *
     * Tap on Terms and Conditions and verify
     */
    @Title("Tap on Terms and Conditions and verify")
    @Test
    public void verifyTermAndConditionPage()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);
        PapPage papPage=new PapPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.setPinCode(testData.get("pinCode"));
        quikrXVapPage.clickOnVerifyButton();
        quikrXVapPage.clickOnBrandNameOnVapPage();
        quikrXVapPage.selectBrandNameOptionOnVapPage(testData.get("brandNameOptionOnVapPage"));
        papPage.selectApplyButton();
        quikrXVapPage.clickOnTermAndCondition();
        Assert.assertTrue(quikrXVapPage.validateTermAndConditionPage()," term and condition page aren't redirected");
    }

    /**
     * QX_41
     *
     * Verify the Specifications provided for exchange old for new
     */
    @Title("Verify the Specifications provided for exchange old for new")
    @Test
    public void verifySpecificationProvidedInQuikrXVapForExchangeOldForNew()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.openAdOnSnbPage(0);
        Assert.assertTrue(quikrXVapPage.validateSpecificationOptions(),"some specification options are missing");
    }

    /**
     * QX_41
     *
     * Verify the Specifications provided for exchange buy certified  used phone
     */
    @Title("Verify the Specifications provided for exchange buy certified  used phone")
    @Test
    public void verifySpecificationProvidedInQuikrXVapForBuyCertified()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

        homePage.selectCityName();
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateBuyCertifiedUsedPhoneTextOnSnbPage(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.openAdOnSnbPage(0);
        Assert.assertTrue(quikrXVapPage.validateSpecificationOptions(),"some specification options are missing");
    }

    /**
     * QX_45
     *
     * Enter valid pincode and verify(Select 'I do not have phone to exchange')
     */
    @Title("Enter valid pincode and verify Select 'I do not have phone to exchange'")
    @Test
    public void verifyClickOniDoNotHavePhoneToExchangeButtonCheckBuyNowButton()
    {
        HomePage homePage=new HomePage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("exchangeOldForNew"));
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateSnbPageForExchangeOldForNew(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.clickOniDoNotHavePhoneToExchange();
        quikrXVapPage.setPinCode(testData.get("pinCode"));
        quikrXVapPage.clickOnVerifyButton();
        Assert.assertTrue(quikrXVapPage.validateBuyNowButton(),"buy now button is not display");
        Assert.assertTrue(quikrXVapPage.validateAdToCartOption(),"ad to cart button can't present");
    }

    /**
     * QX_66
     *
     * Tap on 'Buy Now' button for a user and verify
     */
    @Title("Tap on 'Buy Now' button for a user  with login and for Buy Certified Used Phone in quikrX")
    @Test
    public void verifyBuyNowButtonFunctionalityWithLoginForBuyCertifiedUsedPhone()
    {
        HomePage homePage=new HomePage(driver);
        AuthPage authPage=new AuthPage(driver);
        QuikrXSnbPage quikrXSnbPage=new QuikrXSnbPage(driver);
        QuikrXVapPage quikrXVapPage=new QuikrXVapPage(driver);

        homePage.selectCityName();
        homePage.selectAccountButton();
        authPage.clickOnLoginOrSignUp();
        authPage.setEmailInLogin(testData.get("emailId"));
        authPage.setLoginPassword(testData.get("password"));
        authPage.selectLoginButton();
        authPage.clickOnOkButtonAfterLogin();
        authPage.navigateBack();
        homePage.selectHomeButton();
        homePage.clickOnSeeAllButton();
        Assert.assertTrue(quikrXSnbPage.validateBuyCertifiedUsedPhoneTextOnSnbPage(),"snb page is not display");
        Assert.assertTrue(quikrXSnbPage.validateSortOptions(),"missing sort option on snb page");
        quikrXSnbPage.openAdOnSnbPage(0);
        quikrXVapPage.setPinCode(testData.get("pinCode"));
        quikrXVapPage.clickOnVerifyButton();
        Assert.assertTrue(quikrXVapPage.validateBuyNowButton(),"buy now button is not display");
        Assert.assertTrue(quikrXVapPage.validateAdToCartOption(),"ad to cart button can't present");
        quikrXVapPage.clickOnBuyNowButton();
        Assert.assertTrue(quikrXVapPage.validateCheckoutPage2(),"delivery details all options are not present");
    }
}
