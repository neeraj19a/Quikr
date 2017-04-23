package com.quikr.website.horizontal.chat;

import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.horizontal.myquikr.MyQuikrPage;
import com.quikr.website.mail.YopMailPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by akhil.singla on 28/1/16.
 */
public class ChatTests2 extends ChatTestBase
{
    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("CHAT_TESTDATA_FILE"));

    /*WEB-1022:Low touch: Seller accepts offer
    */
    @Test(dataProvider = "LowTouchCities",dataProviderClass = Data.class)
    public void chat_LowTouchSellerSideAcceptOffer(String city) {

        adId=null;
        adCreation(city);
        openAd(driver, adId);
        if(!city.equals("Lucknow")) {
            loginAndOpenAd(adUserName, adId, driver);
        }
        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage1 = new ChatPage(newDriver);
        chatPage1.clickChatNowVapPage();
        chatPage1.setChatName(testData.get("name"));
        chatPage1.setContactNo("9" + getRandomInteger(9));
        chatPage1.setEmailId(getRandomInteger(15) + "@gmail.com");
        chatPage1.setQuestion(testData.get("question"));
        chatPage1.clickChatNowChatDialog();
        chatPage1.clickMakeanOfferButton();
        String amountofOffer = "3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();

        ChatPage chatPage = new ChatPage(driver);
        chatPage.clickChatNotificationAtTopRight();

        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        Assert.assertTrue(chatPage.checkOfferReceivedBySeller(amountofOffer), "Offer Not Received By Seller Pls Check");
        chatPage.clickAcceptOffer();
        Assert.assertTrue(chatPage.isLowTouchPresent(), "Failed to Load Low Touch");
    }

    /*WEB-93:if buddy typing some messages, Typing being display in owner's Chat window.
    */
    @Test
    public void chat_isTypingShown() {

        adCreation(testData.get("city"));
        openAd(driver, adId);

        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);

        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage1 = new ChatPage(newDriver);
        chatPage1.clickChatNowVapPage();
        chatPage1.setChatName(testData.get("name"));
        chatPage1.setContactNo("9" + getRandomInteger(9));
        chatPage1.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage1.setQuestion(testData.get("question"));
        chatPage1.clickChatNowChatDialog();


        ChatPage chatPage = new ChatPage(driver);
        navigatethirdparty(driver, testData.get("city") + ".quikr.com/MyQuikr?action=mychats");
        chatPage.clickOnChatMessageinMyChats();

        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage.setanddeleteText();
        Assert.assertTrue(chatPage1.isTypingSeenwhileChatting(), "Typing is not seen while Chatting");
        chatPage1.setanddeleteText();
        Assert.assertTrue(chatPage.isTypingSeenwhileChatting(), "Typing is not seen while Chatting");
    }

    /*WEB-94:All delivered messages are marked as "Sent".
    */
    @Test
    public void chat_isSentShown() {

        adId=null;
        adCreation(testData.get("city"));
        openAd(driver, adId);

        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);

        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage1 = new ChatPage(newDriver);
        chatPage1.clickChatNowVapPage();
        chatPage1.setChatName(testData.get("name"));
        chatPage1.setContactNo("9" + getRandomInteger(9));
        chatPage1.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage1.setQuestion(testData.get("question"));
        chatPage1.clickChatNowChatDialog();
        Assert.assertEquals(true, chatPage1.checkSentTag(), "Sent tag absent.");

        ChatPage chatPage = new ChatPage(driver);
        navigatethirdparty(driver, testData.get("city") + ".quikr.com/MyQuikr?action=mychats");
        chatPage.clickOnChatMessageinMyChats();
        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
    }

    /*WEB-97:Deleted/Expired Ads is blocked for both Owner & buddy.
    */
    @Test
    public void chat_DeletedAdChatBlocked() {

        MyQuikrPage myQuikrPage=new MyQuikrPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        //adId=null;
        adCreation(testData.get("city"));
        //openAd(driver, adId);

        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);

        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage1 = new ChatPage(newDriver);
        chatPage1.clickChatNowVapPage();
        chatPage1.setChatName(testData.get("name"));
        chatPage1.setContactNo("9" + getRandomInteger(9));
        chatPage1.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage1.setQuestion(testData.get("question"));
        chatPage1.clickChatNowChatDialog();

        ChatPage chatPage = new ChatPage(driver);
        navigatethirdparty(driver, testData.get("city") + ".quikr.com/MyQuikr?action=mychats");
        chatPage.clickOnChatMessageinMyChats();
        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        headerPage.clickMyAds();
        Assert.assertTrue(myQuikrPage.DeleteAd(), "Issue in an deleting an Ad");
        chatPage1.setQuestion(testData.get("question"));

    }


    /*WEB-98:"Delete Chat Coversation" option is available in Chat window & MCR.
    */
    @Test
    public void chat_DeleteConversation() {

        adCreation(testData.get("city"));
        //openAd(driver, adId);
        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);
        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage1 = new ChatPage(newDriver);
        chatPage1.clickChatNowVapPage();
        chatPage1.setChatName(testData.get("name"));
        chatPage1.setContactNo("9" + getRandomInteger(9));
        chatPage1.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage1.setQuestion(testData.get("question"));
        chatPage1.clickChatNowChatDialog();

        ChatPage chatPage = new ChatPage(driver);
        navigatethirdparty(driver, testData.get("city") + ".quikr.com/MyQuikr?action=mychats");
        chatPage.clickOnChatMessageinMyChats();
        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage1.clickSettingsChatWindow();
        Assert.assertTrue(chatPage1.isChatCleared(), "Unable to Clear Chat");

        chatPage.clickSettingsChatWindow();
        Assert.assertTrue(chatPage.isChatCleared(), "Unable to Clear Chat");

    }

    /*WEB-107:Check whether Replies are getting indexed into poster & seeker's chat history.
    */
    @Test
    public void chat_repliesIndex() {

        adId=null;
        adCreation(testData.get("city"));
        //openAd(driver, adId);
        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);
        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage = new ChatPage(newDriver);
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setContactNo("9" + getRandomInteger(9));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();

        ChatPage chatPage1 = new ChatPage(driver);
        loginAndOpenAd(adUserName, adId, driver);
        navigatethirdparty(driver, testData.get("city") + ".quikr.com/MyQuikr?action=mychats");
        Assert.assertTrue(chatPage1.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
    }

    /*WEB-90:User able to share Images.
    */
    //@Test
    public void chat_ShareImages() {
        adId=null;
        adCreation(testData.get("city"));
        //openAd(driver, adId);
        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);
        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage = new ChatPage(newDriver);
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setContactNo("9" + getRandomInteger(9));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();

        ChatPage chatPage1 = new ChatPage(driver);
        loginAndOpenAd(adUserName, adId, driver);
        chatPage1.clickChatNotificationAtTopRight();
        Assert.assertTrue(chatPage1.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage1.clickAttachmentIcon();
        chatPage1.clickImageAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(chatPage.isImageThumbnailShown(), "Failed To Load Thumbnail of the Image");

    }

    /*WEB-91:User able to download shared images.
    */

    //@Test
    public void chat_DownloadSharedImages() {

        adId=null;
        adCreation(testData.get("city"));
        //openAd(driver, adId);
        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);
        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage = new ChatPage(newDriver);
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setContactNo("9" + getRandomInteger(9));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();

        ChatPage chatPage1 = new ChatPage(driver);
        loginAndOpenAd(adUserName, adId, driver);
        chatPage1.clickChatNotificationAtTopRight();
        Assert.assertTrue(chatPage1.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage1.clickAttachmentIcon();
        chatPage1.clickImageAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(chatPage.isImageThumbnailShown(), "Failed To Load Thumbnail of the Image");
        chatPage.clickDownloadImageAttachment();

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        chatPage1.clickAttachmentIcon();
        chatPage1.clickImageAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chatPage.closeChatWindow();
        chatPage1.clickAttachmentIcon();
        chatPage1.clickImageAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(chatPage.isImageShareBottomNotificationcoming(),"Failed to Load Bottom Image Share Notification");
        //Assert.assertTrue(chatPage.isImageShareBottomNotificationcoming(),"Failed to Load Bottom Image Share Notification");
        chatPage.clickImageShareNotificationAtTopRight();
        Assert.assertTrue(chatPage.checkChatWindow(),"Failed to Open Chat window by Clicking Header Top Notification");

    }

    /*WEB-506:User should able to share videos
    */
    //@Test
    public void chat_ShareVideos() {
        adId=null;
        adCreation(testData.get("city"));
        //openAd(driver, adId);
        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);
        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage = new ChatPage(newDriver);
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setContactNo("9" + getRandomInteger(9));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();

        ChatPage chatPage1 = new ChatPage(driver);
        loginAndOpenAd(adUserName, adId, driver);
        chatPage1.clickChatNotificationAtTopRight();
        Assert.assertTrue(chatPage1.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage1.clickAttachmentIcon();
        chatPage1.clickVideoAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(chatPage.isImageThumbnailShown(), "Failed To Load Thumbnail of the Video");

        /*Below code is for checkinf File Size of 5 Mb validation Message
         */
        chatPage1.clickAttachmentIcon();
        chatPage1.clickVideoAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(chatPage1.isFileSizeValidationMsgPresent(), "Failed to Load Validation Message when user tries to Upload file size of 5 Mb");

    }


    /*WEB-507:User should able to download videos and play
    */
    //@Test
    public void chat_DownloadSharedVideos() {
        adId=null;
        adCreation(testData.get("city"));
        //openAd(driver, adId);
        RemoteWebDriver driverForYopMail = openNewWindow();
        YopMailPage yopMailPage = new YopMailPage(driverForYopMail);
        navigatethirdparty(driverForYopMail, "http://yopmail.com");
        yopMailPage.verifyMailYopMail(adUserName);
        loginAndOpenAd(adUserName, adId, driver);

        RemoteWebDriver newDriver = openNewWindow();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        ChatPage chatPage = new ChatPage(newDriver);
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setContactNo("9" + getRandomInteger(9));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();

        ChatPage chatPage1 = new ChatPage(driver);
        loginAndOpenAd(adUserName, adId, driver);
        chatPage1.clickChatNotificationAtTopRight();
        Assert.assertTrue(chatPage1.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage1.clickAttachmentIcon();
        chatPage1.clickVideoAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(chatPage.isImageThumbnailShown(), "Failed To Load Thumbnail of the Video");
        chatPage.clickDownloadImageAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        chatPage1.clickAttachmentIcon();
        chatPage1.clickVideoAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chatPage.closeChatWindow();
        chatPage1.clickAttachmentIcon();
        chatPage1.clickVideoAttachment();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(chatPage.getFlyerBottomChatMessage(), "You have received a video message", "Failed to Load Bottom Image Share Notification");
        //Assert.assertTrue(chatPage.isImageShareBottomNotificationcoming(),"Failed to Load Bottom Image Share Notification");
        chatPage.clickImageShareNotificationAtTopRight();
        Assert.assertTrue(chatPage.checkChatWindow(),"Failed to Open Chat window by Clicking Header Top Notification");
    }

    /*
    WEB-104:Owner is blocked by Buyer
     */
    @Test
    public void ValidateBuyerBlocking()
    {
        adCreation(testData.get("city"));
        String toBlockMsg = "toBlock";
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);

        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        chatPageNewDriver.clickChatNowVapPage();
        chatPageNewDriver.setChatName(testData.get("name"));
        chatPageNewDriver.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPageNewDriver.setQuestion(testData.get("question"));
        chatPageNewDriver.clickChatNowChatDialog();
        String initialExtractedStatus = chatPageNewDriver.getChatStatus();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not correct. Please check!.");

        chatPageOldDriver.clickChatNotificationAtTopRight();
        chatPageOldDriver.clickSettingsChatWindow();
        chatPageOldDriver.clickBlockButton();
        chatPageOldDriver.clickBlockConfirmationBtn();
        chatPageNewDriver.sendMessageChatWindow(toBlockMsg);

        Assert.assertEquals(chatPageOldDriver.checkTextInInlineMsgs(toBlockMsg), false, "Buyer is probably not blocked. Please check!");
    }

    /*
    WEB-105:Owner/Buddy unblocked
     */
    @Test
    public void ValidateBuddyUnblocking()
    {
        adCreation(testData.get("city"));
        String toBlockMsg = "toBlock";
        String toUnblockMsg = "toUnBlock";
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);

        //loginAndOpenAd(adUserName, adId, driver);
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        chatPageNewDriver.clickChatNowVapPage();
        chatPageNewDriver.setChatName(testData.get("name"));
        chatPageNewDriver.setEmailId(getRandomInteger(15) + "@gmail.com");
        chatPageNewDriver.setQuestion(testData.get("question"));
        chatPageNewDriver.clickChatNowChatDialog();
        String initialExtractedStatus = chatPageNewDriver.getChatStatus();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not correct. Please check!.");

        chatPageOldDriver.clickChatNotificationAtTopRight();
        chatPageOldDriver.clickSettingsChatWindow();
        chatPageOldDriver.clickBlockButton();
        chatPageOldDriver.clickBlockConfirmationBtn();

        chatPageNewDriver.sendMessageChatWindow(toBlockMsg);
        Assert.assertEquals(chatPageOldDriver.checkTextInInlineMsgs(toBlockMsg), false, "Buyer is probably not blocked. Please check!");
        chatPageOldDriver.clickUnblock();

        chatPageNewDriver.sendMessageChatWindow(toUnblockMsg);
        Assert.assertEquals(chatPageOldDriver.checkTextInInlineMsgs(toUnblockMsg), true, "Buyer is probably not unblocked. Please check!");
    }

    /*
    WEB-111:Unread chat thread count on MCR
     */
    @Test
    public void validateChatCount()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        int initialChatCount, newerChatCount = 0;

        chatPageNewDriver.clickChatNowVapPage();
        chatPageNewDriver.setChatName(testData.get("name"));
        chatPageNewDriver.setEmailId(getRandomInteger(15) + "@gmail.com");
        chatPageNewDriver.setQuestion(testData.get("question"));
        chatPageNewDriver.clickChatNowChatDialog();
        String initialExtractedStatus = chatPageNewDriver.getChatStatus();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not correct. Please check!.");
        initialChatCount = chatPageOldDriver.getChatCount();

        RemoteWebDriver chatPageOtherNewDriver = openNewWindow();
        ChatPage anotherNewChatPage = new ChatPage(chatPageOtherNewDriver);
        openAd(chatPageOtherNewDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", chatPageOtherNewDriver);
        chatPageOtherNewDriver.manage().window().maximize();
        anotherNewChatPage.clickChatNowVapPage();
        anotherNewChatPage.setChatName(testData.get("name"));
        anotherNewChatPage.setEmailId(getRandomInteger(15) + "@gmail.com");
        anotherNewChatPage.setQuestion(testData.get("question"));
        anotherNewChatPage.clickChatNowChatDialog();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not correct. Please check!.");

        newerChatCount = chatPageOldDriver.getChatCount();

        Assert.assertEquals(newerChatCount, initialChatCount + 1, "The count didn't increase as expected. Please check!");
    }

    /*
    WEB-112:Total Unread count should display in header notification & My chat tab
     */
    @Test
    public void ValidateUnreadCountChatTabAndHeader()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);

        RemoteWebDriver driverOne = openNewWindow();
        HomePage homePage = new HomePage(driverOne);
        HomePage homePageNewDriver = new HomePage(newDriver);
        navigatethirdparty(driverOne, "http://www.quikr.com");
        //homePage.clickonCityCloseButton();

        //loginAndOpenAd(adUserName, adId, driverOne);
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        int initialChatCount, newerChatCount, chatTabChatCount = 0;

        chatPageNewDriver.clickChatNowVapPage();
        chatPageNewDriver.setChatName(testData.get("name"));
        chatPageNewDriver.setEmailId(getRandomInteger(15) + "@gmail.com");
        chatPageNewDriver.setQuestion(testData.get("question"));
        chatPageNewDriver.clickChatNowChatDialog();
        String initialExtractedStatus = chatPageNewDriver.getChatStatus();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not correct. Please check!.");
        initialChatCount = chatPageOldDriver.getChatCount();

        RemoteWebDriver chatPageOtherNewDriver = openNewWindow();
        ChatPage anotherNewChatPage = new ChatPage(chatPageOtherNewDriver);
        openAd(chatPageOtherNewDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", chatPageOtherNewDriver);
        chatPageOtherNewDriver.manage().window().maximize();
        anotherNewChatPage.clickChatNowVapPage();
        anotherNewChatPage.setChatName(testData.get("name"));
        anotherNewChatPage.setEmailId(getRandomInteger(15) + "@gmail.com");
        anotherNewChatPage.setQuestion(testData.get("question"));
        anotherNewChatPage.clickChatNowChatDialog();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not correct. Please check!.");

        newerChatCount = chatPageOldDriver.getChatCount();
        myQuikrPage.clickMyQuikrIcon();
        myQuikrPage.clickMyAlerts();

        chatTabChatCount = chatPageOldDriver.getChatCountFromChatTab();
        Assert.assertEquals(chatTabChatCount, newerChatCount, "The chat count doesn't match. Please check!");

    }

    /*
    WEB-113:MCR: Header Notif: Once thread read, Unread count will update
     */
    @Test
    public void validateChatCountUpdate()
    {
        adId=null;
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();

        int initialChatCount, newerChatCount, chatTabChatCount = 0;

        chatPageNewDriver.clickChatNowVapPage();
        chatPageNewDriver.setChatName(testData.get("name"));
        chatPageNewDriver.setEmailId(getRandomInteger(15) + "@gmail.com");
        chatPageNewDriver.setQuestion(testData.get("question"));
        chatPageNewDriver.clickChatNowChatDialog();
        String initialExtractedStatus = chatPageNewDriver.getChatStatus();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not correct. Please check!.");
        initialChatCount = chatPageOldDriver.getChatCount();
        Assert.assertEquals(initialChatCount, 1, "Chat count didn't get updated. Please check!");

        chatPageOldDriver.clickChatNotificationAtTopRight();
        Assert.assertEquals(chatPageOldDriver.isChatCountPresent(), false, "Chat count didn't get reduced. Please check!");
    }
}
