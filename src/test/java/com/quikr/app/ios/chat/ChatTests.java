package com.quikr.app.ios.chat;


import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.auth.AuthPage;
import com.quikr.app.ios.home.HomePage;
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
 * Created by Manvendra Singh on 27/10/15.
 */
public class ChatTests extends AppiOSTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_CHAT_TESTDATA_FILE"));


    /**
     * iOS-35:User can chat without signing in
     */
    @Title("User can chat without signing in")
    @Test(groups = "horizontal")
    public void verifyChatForWithoutLogin()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        //VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

//        homePage.selectDontAllowOption();
      //  homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
//        chatPage.clickOnChatBox();
        snbPage.clickOnChatButtonOnSnbPage();
        chatPage.setTextInChatBox(testData.get("sendText"));
        chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
        chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"),2);
        chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"),3);
        chatPage.selectSendButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        Assert.assertTrue(chatPage.validateChat(),"some issue in chat");
    }

    /**
     *  iOS-34:User can chat after signing in valid credentials
     */
    @Title("User can chat after signing in valid credentials")
    @Test(groups = "horizontal")
    public void verifyChatWithLoginUser()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
      //  VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
//        MenuPage menuPage=new MenuPage(driver);
        AuthPage authPage=new AuthPage(driver);

  //      homePage.selectDontAllowOption();
        //homePage.clickOnSelectCity();
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
        homePage.selectHomeButton();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
//        chatPage.clickOnChatBox();
        snbPage.clickOnChatButtonOnSnbPage();
        chatPage.setTextInChatBox(testData.get("sendText"));
//        chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
//        chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"), 2);
//        chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"), 3);
//        chatPage.selectSendButton();
        chatPage.selectTextMsgSendButton();
       // homePage.selectCrossButtonOnAllowLocationPopup();
        Assert.assertTrue(chatPage.validateChat(),"some issue in chat");

    }

    /**
     *  iOS-140:Share contact detail with Chat
     */
    @Title("Share contact detail with Chat")
    @Test(groups = "horizontal")
    public void verifyShareContactDetails()
    {
        HomePage homePage=new HomePage(driver);
        ChatPage chatPage=new ChatPage(driver);

    //    homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectChatNowButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        chatPage.selectAdFromChatAndReplies();
        chatPage.clickOnChatAttachButton();
        chatPage.selectShareMyContactButton();
//        Assert.assertEquals(chatPage.getTextFromSetNameOnChatAndRepliesPage(),testData.get("name"));
//        Assert.assertEquals(chatPage.getTextFromSetMobileNoOnChatAndRepliesPage(),testData.get("mobileNo"));
//        Assert.assertEquals(chatPage.getTextOfEmailOnChatAndRepliesPage(),testData.get("emailId"));
        chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
        chatPage.setMobileNoOnChatAndRepliesPage(testData.get("mobileNo"),2);
        chatPage.setEmailOnChatAndRepliesPage(testData.get("emailId"),3);
        chatPage.clickOnShareButton();
        Assert.assertTrue(chatPage.validateChat(),"contact detail can't share");
    }

    /**
     * iOS-141:Share Location with Chat
     */
    @Title("Share Location with Chat")
    @Test(groups = "horizontal")
    public void verifyShareLocation()
    {
        HomePage homePage=new HomePage(driver);
        ChatPage chatPage=new ChatPage(driver);

      //  homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectChatNowButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        chatPage.selectAdFromChatAndReplies();
        chatPage.clickOnChatAttachButton();
        chatPage.selectShareMyLocationButton();
        if(chatPage.checkAllowButton())
        {
            chatPage.selectAllowButton();
        }
        chatPage.selectShareLocation();
        Assert.assertTrue(chatPage.validateChat(),"location can't share");
    }

    /**
     * test if all the mandatory fields are not fill on chat initiation screen
     */
    @Title("all the mandatory fields are not fill on chat initiation screen")
    @Test(groups = "horizontal")
    public void verifyMandatoryFieldsNotFill()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
      //  VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

     //   homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
        snbPage.clickOnChatButtonOnSnbPage();
        chatPage.selectSendButton();
        Assert.assertTrue(chatPage.validateAllMandatoryFieldsNotFill(),"mandatory fields are filled");
    }

    /**
     * test if name and email not write in chat initiation chat
     */
    @Title("name and email not write in chat initiation chat")
    @Test(groups = "horizontal")
    public void verifyNameAndEmailNotWriteOnChatInitiationScreen()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
        VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);

      //  homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            homePage.selectDontAllowOption();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
        snbPage.clickOnChatButtonOnSnbPage();
        chatPage.setTextInChatBox(testData.get("sendText"));
        chatPage.selectSendButton();
        Assert.assertTrue(chatPage.validateNameAndEmailNotWriteOnChatInitiationScreen(),"name and email are provided at the time of cht initiation");
    }

    /**
     * test if email id is not provided at the chat initiation screen
     */
    @Title("email id is not provided at the chat initiation screen")
    @Test(groups = "horizontal")
    public void verifyEmailNotProvidedAtChatInitiation()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
      //  VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);


     //   homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
        snbPage.clickOnChatButtonOnSnbPage();
        chatPage.setTextInChatBox(testData.get("sendText"));
        chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
        chatPage.selectSendButton();
        Assert.assertTrue(chatPage.validateEmailNotProvidedOnChatInitiationScreen(),"email should be provided at the chat initiation");
    }

    /**
     * test if email id is provided wrong
     */
    @Title("email id is provided wrong")
    @Test(groups = "horizontal")
    public void verifyWrongEmailAtChatInitiationScreen()
    {
        HomePage homePage=new HomePage(driver);
        SnbPage snbPage=new SnbPage(driver);
      //  VapPage vapPage=new VapPage(driver);
        ChatPage chatPage=new ChatPage(driver);
        PapPage papPage=new PapPage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);

       // homePage.selectDontAllowOption();
       // homePage.clickOnSelectCity();
        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("categoryName"),testData.get("categoryName"));
        homePage.selectCategoryOrSubcategory(testData.get("subCategoryName"));
        if(homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        snbPage.selectCheckboxForCurrentSelectedCityOnSnbPageInMultiCityPopup();
        papPage.selectApplyButton();
//        snbPage.clickOnOnline();
//        vapPage.selectChatButton();
//        chatPage.clickOnChatBox();
        snbPage.clickOnChatButtonOnSnbPage();
        chatPage.setTextInChatBox(testData.get("sendText"));
        chatPage.setNameOnChatAndRepliesPage(testData.get("name"));
        chatPage.setEmailOnChatAndRepliesPage(testData.get("wrongEmailId"),2);
        chatPage.selectSendButton();
        Assert.assertTrue(chatPage.validateIncorrectEmail(),"right email is provided");
    }

    /**
     * Check the MCR screen
     */
    @Title("Check the MCR screen")
    @Test(groups = "horizontal")
    public void checkMCRScreen()
    {
        HomePage homePage=new HomePage(driver);
        ChatPage chatPage=new ChatPage(driver);

        homePage.selectCityName();
        homePage.selectChatNowButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        Assert.assertTrue(chatPage.validateMCRScreen(),"all the option can't present on MCR screen");
    }

    /**
     * Click on By Others button
     */
    @Title("Click on By Others button")
    @Test(groups = "horizontal")
    public void verifyByOthersAdOnMCRScreen()
    {
        HomePage homePage=new HomePage(driver);
        ChatPage chatPage=new ChatPage(driver);
        VapPage vapPage=new VapPage(driver);

        homePage.selectCityName();
        homePage.selectChatNowButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        chatPage.selectByOtherTab();
        chatPage.clickOnAdTitleOnMCRScreen();
        Assert.assertTrue(vapPage.validateEditAdButton(),"this ad can't be byOthers ad");
    }

    /**
     * Click on By Me button
     */
    @Title(" Click on By Me button")
    @Test(groups = "horizontal")
    public void verifyByMeAdOnMCRScreen()
    {
        HomePage homePage=new HomePage(driver);
        ChatPage chatPage=new ChatPage(driver);
        VapPage vapPage=new VapPage(driver);

        homePage.selectCityName();
        homePage.selectChatNowButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        chatPage.selectByMeTab();
        chatPage.clickOnAdTitleOnMCRScreen();
        Assert.assertTrue(vapPage.validateShortlistButtonBeforeSelect(),"shortlist button can't present ");
        Assert.assertTrue(vapPage.validateVapShareButton(),"this ad can't be post by me ");
    }

    /**
     * Slide left on any chat thread
     */
    @Test
    public void verifyDeleteAndBlockButtonOnMCRScreen()
    {
//        HomePage homePage=new HomePage(driver);
//        ChatPage chatPage=new ChatPage(driver);
//
//        homePage.selectChatNowButton();
//        chatPage.slideAnAd();

        //incomp
    }

    /**
     * all the button are present on chat page through MCR screen
     */
    @Title(" all the button are present on chat page through MCR screen")
    @Test(groups = "horizontal")
    public void verifyAllButtonOnChatPageThroughMCRScreen()
    {
        HomePage homePage=new HomePage(driver);
        ChatPage chatPage=new ChatPage(driver);
        homePage.selectCityName();
        homePage.selectChatNowButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        chatPage.selectAdFromChatAndReplies();
        Assert.assertTrue(chatPage.validateAllOptionsOnChatThroughMCRScreen(),"all required button are not present");
    }

    /**
     * H_041
     *
     * verify the invalid contact detail at the time of chat
     */
    @Title(" verify the invalid contact detail at the time of chat")
    @Test(groups = "horizontal")
    public void verifyInvalidShareContactDetails()
    {
        HomePage homePage=new HomePage(driver);
        ChatPage chatPage=new ChatPage(driver);

        homePage.selectCityName();
        homePage.selectChatNowButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        chatPage.selectAdFromChatAndReplies();
        chatPage.clickOnChatAttachButton();
        chatPage.selectShareMyContactButton();
        chatPage.clickOnShareButton();
        Assert.assertTrue(chatPage.validateInvalidShareContactDetails(),"contact details popup is not present or providing email at the time of sharing contact details");
    }

    /**
     * H_050
     *
     * From the chat screen  ,user should be able to see and send appropriate canned messages
     */
    @Title("From the chat screen  ,user should be able to see and send appropriate canned messages ")
    @Test(groups = "horizontal")
    public void verifyCannedMsgSent()
    {
        HomePage homePage=new HomePage(driver);
        ChatPage chatPage=new ChatPage(driver);

        homePage.selectCityName();
        homePage.selectChatNowButton();
        homePage.selectCrossButtonOnAllowLocationPopup();
        chatPage.selectAdFromChatAndReplies();
        chatPage.clickOnCannedButton();
        Assert.assertTrue(chatPage.validateCannedMsg(),"canned msg count is less then 5 or more then 5");
        chatPage.selectCannedMsgFromCannedMsgList();
        chatPage.selectTextMsgSendButton();
        Assert.assertTrue(chatPage.validateCannedMsgStatus(),"canned msg can't sent");
    }

}
