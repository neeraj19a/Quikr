package com.quikr.app.android.chat;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;


/**
 * Created by Manvendra Singh on 8/9/15.
 */
public class ChatTests extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_CHAT_TESTDATA_FILE"));



    /**
     * ANDROID-731:Four options for Attachment
     */
    @Stories("Chat")
    @Title("Verify Options Present On Chat Screen")
    @Test(groups = "horizontal")
    public void verifyOptionPresentOnChatScreen()
    {
        ChatPage chatPage=new ChatPage(driver);MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyChats"), QuikrAppEnums.CATEGORY_MENU);
        chatPage.selectChatAndRepliesUser(0);
        Assert.assertTrue(chatPage.validateOptionPresentOnChatScreen(),"all 4 options are not present on chat screen");
    }

    /**
     * ANDROID-739:Response for Camera icon
     */
    @Stories("Chat")
    @Title("Verify Response for Camera icon")
    @Test(groups = "horizontal")
    public void verifyCameraResponse()
    {

        ChatPage chatPage=new ChatPage(driver);MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyChats"), QuikrAppEnums.CATEGORY_MENU);
        chatPage.selectChatAndRepliesUser(1);
        chatPage.clickOnCameraIcon();
        Assert.assertTrue(chatPage.validateBehaviourOfCamera(),"camera's behaviour does not working as required behaviour ");
    }


    /**
     * ANDROID-782:Check whether Block User button appears whenever a new chat message is received by the seller.
     *
     * ANDROID-785:Verify the appearance of Block User button when Seller does not type anything to reply
     */
//    @Test
//    public void verifyBlockButtonForFirstRcvMsgToSeller()
//    {
//        hompage.ApplaunchPopup();
//        hompage.selectChatAndRepliesButton();
//        chatPage.selectChatAndRepliesUser(1);
//        Assert.assertTrue(chatPage.validateBlockButtonOnChatPage(),"block button can't present");
//    }

    /**
     * ANDROID-783:Verify the behaviour of 'Block User' button which appears below the Ad.
     */
//    @Test
//    public void verifyBehaviourOfBlockButtonAppearsBelowAd()
//    {
//        hompage.ApplaunchPopup();
//        hompage.selectChatAndRepliesButton();
//        chatPage.selectChatAndRepliesUser(1);
//        chatPage.selectBlockUserButton();
//        Assert.assertTrue(chatPage.validateMsgForBlockButtonAction(),"unblock button can't display");
//    }

    /**
     * ANDROID-786:Verify the appearance of Block User button when Seller sends the reply.
     */
    @Stories("Chat")
    @Title("Verify the appearance of Block User button  seller sends Reply")
    @Test(groups = "horizontal")
    public void verifyBlockButtonIsDisappearWhenSellerStartsChat()
    {

        ChatPage chatPage=new ChatPage(driver);MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyChats"), QuikrAppEnums.CATEGORY_MENU);
        chatPage.selectChatAndRepliesUser(0);
        Assert.assertTrue(!chatPage.validateMsgForBlockButtonAction(),"block button is present");
    }

    /**
     * ANDROID-784:Verify the appearance of Block User button when Seller starts typing on keypad for chat.
     */
    @Stories("Chat")
    @Title("Verify the appearance of Block User button when Seller starts typing on keypad for chat. ")
    @Test(groups = "horizontal")
    public void verifyBlockButtonIsDisappearWhenSellerStartsTypeOnKeyBoard()
    {

        ChatPage chatPage=new ChatPage(driver);
        MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyChats"), QuikrAppEnums.CATEGORY_MENU);
        chatPage.selectChatAndRepliesUser(1);
        chatPage.clickOnChatBox();
        chatPage.sendTextInChatBox(testData.get("inputText"));
        chatPage.navigateBack();
        Assert.assertTrue(!chatPage.validateMsgForBlockButtonAction(),"block button is present");
    }
    /**
     * Verify the header of individual chat screen
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfIndividualChatScreen()
    {
        ChatPage chatPage=new ChatPage(driver);
        MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyChats"), QuikrAppEnums.CATEGORY_MENU);
        chatPage.clickOnRoundedLetterToOpenChat(1);
        Assert.assertTrue(chatPage.verifyHeaderOfIndividualChatScreen());
        Assert.assertTrue(chatPage.getLastActiveStatus().contains("Last active"),"active status text"+chatPage.getLastActiveStatus());

    }

}
