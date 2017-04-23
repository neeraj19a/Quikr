package com.quikr.website.horizontal.chat;

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
public class ChatTests3 extends ChatTestBase
{
    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("CHAT_TESTDATA_FILE"));

    /*WEB-508:User should able to share his/her contact details
    */
    @Test
    public void chat_ShareContactDetailsBySeller() {
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
        chatPage.clickOnChatMessageinMyChats();
        Assert.assertTrue(chatPage1.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage1.clickAttachmentIcon();
        chatPage1.clickContactAttachment();
        chatPage1.setNameContactAttachment(testData.get("name"));
        chatPage1.setPhoneContactAttachment("9" + getRandomInteger(9));
        chatPage1.clickSendContactButton();
        Assert.assertTrue(chatPage1.isContactDetailsSent(), "Failed To send Contact Details by Seller");

    }

    /*WEB-509:Receiver should see sender's contact details
    */
    @Test
    public void chat_SharedContactDetailsReceivedbyBuyer() {
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
        chatPage1.clickContactAttachment();
        chatPage1.setNameContactAttachment(testData.get("name"));
        chatPage1.setPhoneContactAttachment("9" + getRandomInteger(9));
        chatPage1.clickSendContactButton();
        Assert.assertTrue(chatPage1.isContactDetailsSent(), "Failed To send Contact Details by Sender");
        Assert.assertTrue(chatPage.isContactDetailsSent(), "Failed To receive Contact Details by Buyer");

    }


    /*WEB-511:User should able to share his/her location through google map
    */
    //@Test
    public void chat_ShareLocationDetailsBySeller() {
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
        chatPage1.clickLocationAttachment();
        chatPage1.shareLocation();
        Assert.assertTrue(chatPage1.isLocationShown(), "Failed to Send Location by Seller");

    }

    /* WEB-512:Receiving user should able to open Location
*/
    //@Test
    public void chat_SharedLocationDetailsReceivedByBuyer() {
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
        chatPage1.clickLocationAttachment();
        chatPage1.shareLocation();
        Assert.assertTrue(chatPage1.isLocationShown(), "Failed to Send Location by Seller");
        Assert.assertTrue(chatPage.isLocationShown(),"Failed to Receive Location by Buyer");

    }

    /*WEB-853:Canned Responses For Buyer
    */
    @Test
    public void chat_CannedResponsesBuyer() {
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

        ChatPage chatPage1 = new ChatPage(newDriver);
        chatPage1.clickChatNowVapPage();
        chatPage1.setChatName(testData.get("name"));
        chatPage1.setContactNo("9" + getRandomInteger(9));
        chatPage1.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage1.setQuestion(testData.get("question"));
        chatPage1.clickChatNowChatDialog();


        ChatPage chatPage = new ChatPage(driver);
        chatPage.clickChatNotificationAtTopRight();
        System.out.println("Data is " + chatPage.getIncomingMessageFromChatDialog(1));
        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage1.clickCannedResponseChat();
        chatPage1.clickCannedResponseMessage(2);
        Assert.assertTrue(chatPage1.getOutgoingMessageFromChatDialog(1).equals(chatPage.getIncomingMessageFromChatDialog(1)), "Failed to Load exact Message at Buyer Side");
        chatPage1.clickCannedResponseChat();
        chatPage1.clickCannedResponseMessage(3);
        Assert.assertTrue(chatPage1.getOutgoingMessageFromChatDialog(2).equals(chatPage.getIncomingMessageFromChatDialog(2)), "Failed to Load exact Message at Buyer Side");
        chatPage1.clickCannedResponseChat();
        chatPage1.clickCannedResponseMessage(4);
        Assert.assertTrue(chatPage1.getOutgoingMessageFromChatDialog(3).equals(chatPage.getIncomingMessageFromChatDialog(3)), "Failed to Load exact Message at Buyer Side");
        chatPage1.clickCannedResponseChat();
        chatPage1.clickCannedResponseMessage(5);
        Assert.assertTrue(chatPage1.getOutgoingMessageFromChatDialog(4).equals(chatPage.getIncomingMessageFromChatDialog(4)), "Failed to Load exact Message at Buyer Side");

    }

    /*WEB-854:Canned Responses For Seller
    */
    @Test
    public void chat_CannedResponsesSeller() {
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

        ChatPage chatPage1 = new ChatPage(newDriver);
        chatPage1.clickChatNowVapPage();
        chatPage1.setChatName(testData.get("name"));
        chatPage1.setContactNo("9" + getRandomInteger(9));
        chatPage1.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage1.setQuestion(testData.get("question"));
        chatPage1.clickChatNowChatDialog();


        ChatPage chatPage = new ChatPage(driver);
        chatPage.clickChatNotificationAtTopRight();
        System.out.println("Data is " + chatPage.getIncomingMessageFromChatDialog(1));
        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        chatPage.clickCannedResponseChat();
        chatPage.clickCannedResponseMessage(2);
        Assert.assertTrue(chatPage.getOutgoingMessageFromChatDialog(1).equals(chatPage1.getIncomingMessageFromChatDialog(1)), "Failed to Load exact Message at Buyer Side");
        chatPage.clickCannedResponseChat();
        chatPage.clickCannedResponseMessage(3);
        Assert.assertTrue(chatPage.getOutgoingMessageFromChatDialog(2).equals(chatPage1.getIncomingMessageFromChatDialog(2)), "Failed to Load exact Message at Buyer Side");
        chatPage.clickCannedResponseChat();
        chatPage.clickCannedResponseMessage(4);
        Assert.assertTrue(chatPage.getOutgoingMessageFromChatDialog(3).equals(chatPage1.getIncomingMessageFromChatDialog(3)), "Failed to Load exact Message at Buyer Side");
        chatPage.clickCannedResponseChat();
        chatPage.clickCannedResponseMessage(5);
        Assert.assertTrue(chatPage.getOutgoingMessageFromChatDialog(4).equals(chatPage1.getIncomingMessageFromChatDialog(4)), "Failed to Load exact Message at Buyer Side");

    }



    /*
    WEB-76:Chat window login form
     */
    @Test
    public void validateChatLoginForm()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPage = new ChatPage(newDriver);

        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();
        chatPage.clickChatNowVapPage();

        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();
        Assert.assertEquals(false, chatPage.checkSentTag(), "Sent tag present.");
        chatPage.setChatName(testData.get("name"));
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();
        Assert.assertEquals(false, chatPage.checkSentTag(), "Sent tag present.");
        chatPage.setChatName(testData.get("name"));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();
        Assert.assertEquals(true, chatPage.checkSentTag(), "Sent tag absent.");
    }

    /*
    WEB-78:Check whether message is sent after chat initiated.
     */
    @Test
    public void validateChatCommunication()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPage = new ChatPage(newDriver);
        ChatPage chatPage1 = new ChatPage(driver);

        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();
        Assert.assertEquals(true, chatPage.checkSentTag(), "Sent tag absent.");

        chatPage1.clickChatNotificationAtTopRight();
        Assert.assertTrue(chatPage1.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
    }

    /*
    WEB-79:Chek whether Header notification is triggered after chat initiated.
     */
    @Test
    public void checkChatHeaderNotification()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPage = new ChatPage(newDriver);
        ChatPage chatPage1 = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);

        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();
        Assert.assertEquals(true, chatPage.checkSentTag(), "Sent tag absent.");

        Assert.assertTrue(chatPage1.checkIfChatNotificationUpdated(), "Notification didn't get triggered.");
    }

    /*
    WEB-80:Check whether chat window is getting opened after clicking on header notification.
     */
    @Test
    public void validateChatWindow()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPage = new ChatPage(newDriver);
        ChatPage chatPage1 = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);

        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();
        Assert.assertEquals(true, chatPage.checkSentTag(), "Sent tag absent.");

        chatPage1.clickChatNotificationAtTopRight();
        Assert.assertTrue(chatPage1.checkChatWindow(), "The message container didn't show up.");

    }

    /*
    WEB-81:Check whether Message status is changing "Sent" to "seen" after every message view.
     */
    @Test
    public void validateMessageStatus()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);
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
        String finalExtractedStatus = chatPageNewDriver.getChatStatus();
        Assert.assertEquals(finalExtractedStatus, "Seen", "The chat status is not correct. Please check!.");
    }

    /*
    WEB-179:Chat Init From VAP
     */
    @Test
    public void ValidateChatVap()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPage = new ChatPage(newDriver);
        ChatPage chatPage1 = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);

        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        newDriver.manage().window().maximize();
        chatPage.clickChatNowVapPage();
        chatPage.setChatName(testData.get("name"));
        chatPage.setEmailId(getRandomInteger(15) + "@yopmail.com");
        chatPage.setQuestion(testData.get("question"));
        chatPage.clickChatNowChatDialog();
        String initialExtractedStatus = chatPage.getChatStatus();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not displayed. Indicates that the chat window didn't appear. Please check!");
    }

    /*
    WEB-101:"Block this user" option is available in chat window & MCR.
     */
    @Test
    public void ValidateBlockUserOption()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);
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
        Assert.assertTrue(chatPageOldDriver.checkIfBlockIconPresent(), "Block icon not visible! ");
    }

    /*
    WEB-102:Buddy is blocked by Seller
     */
    @Test
    public void ValidateUserBlocking()
    {
        adCreation(testData.get("city"));
        String toBlockMsg = "toBlock";
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);
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
        chatPageNewDriver.enterChatMessage(toBlockMsg);


        Assert.assertFalse(chatPageOldDriver.checkTextInInlineMsgs(toBlockMsg), "The text message from blocked user is still being received!");
    }

    /*
    WEB-103:Blocked messages should have status "Sent" & should get dropped on page refreshment.
     */
    @Test
    public void ValidateBlockedMessageDisapperance()
    {
        adCreation(testData.get("city"));
        String toBlockMsg = "toBlock";
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        ChatPage chatPageOldDriver = new ChatPage(driver);

        loginAndOpenAd(adUserName, adId, driver);
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
        chatPageNewDriver.setQuestion(toBlockMsg);
        String newExtractedStatus = chatPageNewDriver.getChatStatus();
        refreshPage();
        Assert.assertEquals(newExtractedStatus, "", "The message from blocked status didn't dissapear!. Please check!");
    }
}
