package com.quikr.app.ios.escrow;


import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.auth.AuthPage;
import com.quikr.app.ios.chat.ChatPage;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.pap.PapPage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.snb.SnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 30/10/15.
 */
public class EscrowTests extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_ESCROW_TESTDATA_FILE"));

    /**
     * iOS-148:Verify if ‘Make offer’ feature is currently active only in Mumbai and Bangalore
     *
     * iOS-149:Verify if ‘Make offer’ feature is available only for a selected categories
     *
     * For Electronics And Appliances Category
     */
    @Title("‘Make offer’ feature is available only for a selected categories")
    @Test(groups = "horizontal")
    public void verifyMakeAnOfferButtonForElectronicsAndAppliancesCategory()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
      //  VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
       // PapPage papPage=new PapPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

//        homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("electronicsAndApplianceCategory"),testData.get("electronicsAndApplianceCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("electronicsAndApplianceSubCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
        snbPage.clickOnChatButtonOnSnbPage();
        if(chatPage.checkPresenceOfSendButton())
        {
            chatPage.clickOnChatBox();
            chatPage.setTextInChatBox(testData.get("sendText"));
            chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
            chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"),2);
            chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"),3);
            chatPage.selectSendButton();
            homePage.selectCrossButtonOnAllowLocationPopup();
            Assert.assertTrue(chatPage.validateChat(), "some issue in chat");
        }
//        if(!escrowPage.validateMakeAnOfferButton())
//        {
//            chatPage.selectCrossButton();
//            vapPage.selectChatButton();
//        }
        if(!escrowPage.validateMakeAnOfferButton())
        {
           // chatPage.selectCrossButton();
           // vapPage.clickOnVapBackButton();
            chatPage.navigateBack();
            homePage.selectChatNowButton();
            homePage.selectCrossButtonOnAllowLocationPopup();
            chatPage.selectAdFromChatAndReplies();
        }

        Assert.assertTrue(escrowPage.validateMakeAnOfferButton(),"make an offer button can't present");
    }

    /**
     * iOS-148:Verify if ‘Make offer’ feature is currently active only in Mumbai and Bangalore
     *
     * iOS-149:Verify if ‘Make offer’ feature is available only for a selected categories
     *
     * for mobiles and tablets category
     */
    @Title("'Make offer’ feature is available only for a selected categories")
    @Test(groups = "horizontal")
    public void verifyMakeAnOfferButtonForMobilesAndTabletsCategory()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
      //  VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

//        homePage.selectDontAllowOption();
     //   homePage.clickOnSelectCity();
        homePage.selectCityName();
       // homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsCategory"),testData.get("mobilesAndTabletsCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsSubCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
        snbPage.clickOnChatButtonOnSnbPage();
        if(chatPage.checkPresenceOfSendButton())
        {
            chatPage.clickOnChatBox();
            chatPage.setTextInChatBox(testData.get("sendText"));
            chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
            chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"),2);
            chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"),3);
            chatPage.selectSendButton();
            homePage.selectCrossButtonOnAllowLocationPopup();
            Assert.assertTrue(chatPage.validateChat(), "some issue in chat");
        }
//        if(!escrowPage.validateMakeAnOfferButton())
//        {
//            chatPage.selectCrossButton();
//            vapPage.selectChatButton();
//        }

        if(!escrowPage.validateMakeAnOfferButton())
        {
          //  chatPage.selectCrossButton();
           // vapPage.clickOnVapBackButton();
            chatPage.navigateBack();
            homePage.selectChatNowButton();
            homePage.selectCrossButtonOnAllowLocationPopup();
            chatPage.selectAdFromChatAndReplies();
        }
        Assert.assertTrue(escrowPage.validateMakeAnOfferButton(),"make an offer button can't present");
    }

    /**
     * iOS-148:Verify if ‘Make offer’ feature is currently active only in Mumbai and Bangalore
     *
     * iOS-149:Verify if ‘Make offer’ feature is available only for a selected categories
     *
     * for Home and lifestyles for mumbai city
     */
    @Title("‘Make offer’ feature is available only for a selected categories for Home and lifestyles for mumbai city")
    @Test(groups = "horizontal")
    public void verifyMakeAnOfferButtonForHomeAndLifeStyle()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
     //   VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

  //      homePage.selectDontAllowOption();
     //   homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.clickOnCurrentSelectedCity(testData.get("expectedCityName"));
        homePage.selectCrossButtonOnAllowLocationPopup();
        homePage.changeCity(testData.get("changeCityName"));
        homePage.selectCategoryOrSubcategory(testData.get("homeAndLifeStyleCategory"),testData.get("homeAndLifeStyleCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("homeAndLifeStyleSubcategoryName"));
        if(homePage.checkDontAllowButton())
        {
           realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
        snbPage.clickOnChatButtonOnSnbPage();
        if(chatPage.checkPresenceOfSendButton())
        {
            chatPage.clickOnChatBox();
            chatPage.setTextInChatBox(testData.get("sendText"));
            chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
            chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"),2);
            chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"),3);
            chatPage.selectSendButton();
            homePage.selectCrossButtonOnAllowLocationPopup();
            Assert.assertTrue(chatPage.validateChat(), "some issue in chat");
        }
//        if(!escrowPage.validateMakeAnOfferButton())
//        {
//            chatPage.selectCrossButton();
//            vapPage.selectChatButton();
//        }
        if(!escrowPage.validateMakeAnOfferButton())
        {
            //chatPage.selectCrossButton();
           // vapPage.clickOnVapBackButton();
            chatPage.navigateBack();
            homePage.selectChatNowButton();
            homePage.selectCrossButtonOnAllowLocationPopup();
            chatPage.selectAdFromChatAndReplies();
        }
        Assert.assertTrue(escrowPage.validateMakeAnOfferButton(),"make an offer button can't present");
    }

    /**
     * test make an offer for other city accept bangalore and mumbai
     */
    @Test
    public void verifyMakeAnOfferButtonOtherCityExceptBangaloreAndMumbai()
    {
//        HomePage homePage=new HomePage(driver);
//        SnbPage snbPage=new SnbPage(driver);
//        VapPage vapPage=new VapPage(driver);
//        ChatPage chatPage=new ChatPage(driver);
//        //PapPage papPage=new PapPage(driver);
//        EscrowPage escrowPage=new EscrowPage(driver);
//
//    //    homePage.selectDontAllowOption();
//        homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.clickOnCurrentSelectedCity();
//        homePage.changeCity(testData.get("selectNameForChangeCity"));
//        homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsCategory"));
//        homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsSubCategoryName"));
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
//        if(chatPage.checkPresenceOfSendButton())
//        {
//            chatPage.clickOnChatBox();
//            chatPage.setTextInChatBox(testData.get("sendText"));
//            chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
//            chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"),2);
//            chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"),3);
//            chatPage.selectSendButton();
//            Assert.assertTrue(chatPage.validateChat(), "some issue in chat");
//        }
////        if(!escrowPage.validateMakeAnOfferButton())
////        {
////            chatPage.selectCrossButton();
////            vapPage.selectChatButton();
////        }
//        if(!escrowPage.validateMakeAnOfferButton())
//        {
//            chatPage.selectCrossButton();
//            vapPage.clickOnVapBackButton();
//            homePage.selectChatNowButton();
//            chatPage.selectAdFromChatAndReplies();
//        }
//        Assert.assertTrue(!escrowPage.validateMakeAnOfferButton(),"make an offer button is present");
    }

    /**
     * test make an offer button with login
     */
    @Title("make an offer button with login")
    @Test(groups = "horizontal")
    public void verifyMakeAnOfferButtonWithLogin()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
       // VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);
        EscrowPage escrowPage=new EscrowPage(driver);
       // MenuPage menuPage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

      //  homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
//        homePage.clickOnMenuIconButton();
//        menuPage.clickOnMyAccount();
//        authPage.setEmailInLogin(testData.get("emailId"));
//        authPage.setLoginPassword(testData.get("password"));
//        authPage.selectLoginButton();
//        authPage.clickOnOkButtonAfterLogin();
        homePage.selectAccountButton();
        authPage.clickOnLoginOrSignUp();
        authPage.setEmailInLogin(testData.get("emailId"));
        authPage.setLoginPassword(testData.get("password"));
        authPage.selectLoginButton();
        authPage.clickOnOkButtonAfterLogin();
        authPage.navigateBack();
        homePage.selectHomeButton();
        homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsCategory"),testData.get("mobilesAndTabletsCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsSubCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
        snbPage.clickOnChatButtonOnSnbPage();
        if(chatPage.checkPresenceOfSendButton())
        {
            chatPage.clickOnChatBox();
            chatPage.setTextInChatBox(testData.get("sendText"));
            chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
            chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"),2);
            chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"),3);
            chatPage.selectSendButton();
            homePage.selectCrossButtonOnAllowLocationPopup();
            Assert.assertTrue(chatPage.validateChat(), "some issue in chat");
        }
//        if(!escrowPage.validateMakeAnOfferButton())
//        {
//            chatPage.selectCrossButton();
//            vapPage.selectChatButton();
//        }

        if(!escrowPage.validateMakeAnOfferButton())
        {
            //chatPage.selectCrossButton();
           // vapPage.clickOnVapBackButton();
            chatPage.navigateBack();
            homePage.selectChatNowButton();
            homePage.selectCrossButtonOnAllowLocationPopup();
            chatPage.selectAdFromChatAndReplies();
        }

        Assert.assertTrue(escrowPage.validateMakeAnOfferButton(),"make an offer button can't present");
    }

    /**
     * iOS-147:Verify if the "make an offer" button is shown for wanted Ads
     */
    @Test
    public void verifyMakeAnOfferButtonForWantAd()
    {
        //Related to filter

//        HomePage homePage=new HomePage(driver);
//        SnbPage snbPage=new SnbPage(driver);
//        VapPage vapPage=new VapPage(driver);
//        ChatPage chatPage=new ChatPage(driver);
//        PapPage papPage=new PapPage(driver);
//        EscrowPage escrowPage=new EscrowPage(driver);
//
//      //  homePage.selectDontAllowOption();
//      //  homePage.clickOnSelectCity();
//        homePage.selectCityName();
//        homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsCategory"),testData.get("mobilesAndTabletsCategory"));
//        homePage.selectCategoryOrSubcategory(testData.get("mobilesAndTabletsSubCategoryName"));
//        snbPage.clickOnFilterButtonOnSnbPage();
//        snbPage.clickOnAdTypeInFilterOption();
//        snbPage.selectWantButton();
//        papPage.selectApplyButton();
//        papPage.selectApplyButton();
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
//        if(chatPage.checkPresenceOfSendButton())
//        {
//            chatPage.clickOnChatBox();
//            chatPage.setTextInChatBox(testData.get("sendText"));
//            chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
//            chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"),2);
//            chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"),3);
//            chatPage.selectSendButton();
//            Assert.assertTrue(chatPage.validateChat(), "some issue in chat");
//        }
////        if(!escrowPage.validateMakeAnOfferButton())
////        {
////            chatPage.selectCrossButton();
////            vapPage.selectChatButton();
////        }
//        if(!escrowPage.validateMakeAnOfferButton())
//        {
//            chatPage.selectCrossButton();
//            vapPage.clickOnVapBackButton();
//            homePage.selectChatNowButton();
//            chatPage.selectAdFromChatAndReplies();
//        }
//
//        Assert.assertTrue(!escrowPage.validateMakeAnOfferButton(),"make an offer button is present");
    }
}
