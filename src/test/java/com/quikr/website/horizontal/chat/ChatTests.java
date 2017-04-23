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
 * Created by akhil.singla on 13/1/16.
 */
public class ChatTests extends ChatTestBase
{
    // test data
    private HashMap<String, String> testData = getTestData(getProperties().get("CHAT_TESTDATA_FILE"));

    @Test
    public void chat_buyerInitiatedOffer()
    {
        
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
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();
        Assert.assertTrue(chatPage1.checkMessageSent(),"Message Not Sent");
    }

    @Test
    public void chat_sellerReceivesOffer()
    {
        
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
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();

        ChatPage chatPage = new ChatPage(driver);
        chatPage.clickChatNotifTopRight();
        chatPage.clickMyChatUnderChatNotification();

        //Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        //Assert.assertTrue(chatPage.checkOfferReceivedBySeller(amountofOffer),"Offer Not Received By Seller Pls Check");
        Assert.assertTrue(chatPage.validateIfofferReceived(testData.get("name"),amountofOffer)," Offer was not recieved or something broke down at assertion. Please check!");
    }

    @Test
    public void chat_SellerAcceptsOffer()
    {
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
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();

        ChatPage chatPage = new ChatPage(driver);
        navigatethirdparty(driver, testData.get("city") + ".quikr.com/MyQuikr?action=mychats");
        chatPage.clickOnChatMessageinMyChats();

        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        Assert.assertTrue(chatPage.checkOfferReceivedBySeller(amountofOffer), "Offer Not Received By Seller Pls Check");
        chatPage.clickAcceptOffer();
        chatPage.SubmitSellerInfo(testData.get("name"),getRandomInteger(15) + "@yopmail.com","9" + getRandomInteger(9));
        Assert.assertTrue(chatPage.isSellerAcceptedOffer(), "Seller offer Not Accepted");
    }

    /*WEB-265:Buyer Screen after seller accepts offer
    */
    @Test
    public void chat_MakePayment()
    {
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
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();

        ChatPage chatPage = new ChatPage(driver);
        navigatethirdparty(driver, testData.get("city") + ".quikr.com/MyQuikr?action=mychats");
        chatPage.clickOnChatMessageinMyChats();

        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        Assert.assertTrue(chatPage.checkOfferReceivedBySeller(amountofOffer), "Offer Not Received By Seller Pls Check");
        chatPage.clickAcceptOffer();
        chatPage.SubmitSellerInfo(testData.get("name"), getRandomInteger(15) + "@yopmail.com", "9" + getRandomInteger(9));
        Assert.assertTrue(chatPage.isSellerAcceptedOffer(), "Seller offer Not Accepted");
        chatPage1.clickMakePaymentButton();
        Assert.assertTrue(chatPage1.isPaymentScreenLoaded(),"Payment Screen Not Loaded");

    }


    /*WEB-268:Buyer Edit Offer
    */
    @Test
    public void chat_EditOffer()
    {
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
        chatPage1.setEmailId(getRandomInteger(15) + "@gmail.com");
        chatPage1.setQuestion(testData.get("question"));
        chatPage1.clickChatNowChatDialog();
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();
        chatPage1.clickEditOfferBUtton();
        String editamountofOffer="3999";
        chatPage1.setAmountForOffer(editamountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        Assert.assertTrue(chatPage1.isUpdatedOfferMessagePresent(),"Buyer is not able to Update Offer");
    }

    /*WEB-269:Seller receives offer and then give Counter Offer
    */
    @Test
    public void chat_CounterOfferSellerSide() {
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
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();

        ChatPage chatPage = new ChatPage(driver);
        chatPage.clickChatNotificationAtTopRight();

        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        Assert.assertTrue(chatPage.checkOfferReceivedBySeller(amountofOffer), "Offer Not Received By Seller Pls Check");
        chatPage.clickNegotiateOfferButton();
        String amountofNegotiateOffer="4000";
        chatPage.setAmountForOffer(amountofNegotiateOffer);
        Assert.assertFalse(chatPage.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        //chatPage.clickSubmitButton();
        Assert.assertTrue(chatPage.isCounterOfferMessageSellerSidePresent(), "Failed to appear Counter Offer message at Seller Side");
    }

    /*WEB-270:Buyer receives Counter Offer
    */
    @Test
    public void chat_CounterOfferAcceptBuyerSide()
    {
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
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();

        ChatPage chatPage = new ChatPage(driver);
        chatPage.clickChatNotificationAtTopRight();

        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        Assert.assertTrue(chatPage.checkOfferReceivedBySeller(amountofOffer), "Offer Not Received By Seller Pls Check");
        chatPage.clickNegotiateOfferButton();
        String amountofNegotiateOffer="4000";
        chatPage.setAmountForOffer(amountofNegotiateOffer);
        Assert.assertFalse(chatPage.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        //chatPage.clickSubmitButton();
        Assert.assertTrue(chatPage.isCounterOfferMessageSellerSidePresent(), "Failed to appear Counter Offer message at Seller Side");
        Assert.assertTrue(chatPage1.isCounterOfferBuyerSidePresent(), "Failed to Receive Counter Offer Update at Buyer Side");
        chatPage1.clickAcceptCounterOffer();
        Assert.assertTrue(chatPage1.isMakePaymentButtonpresent(),"Make Payment Button not visible");

    }

    /*WEB-272:Buyer withdraw Offer
    */

    @Test
    public void chat_BuyerWithdrawOffer()
    {
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
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();
        chatPage1.clickWithdrawOfferBUtton();
        Assert.assertTrue(chatPage1.isWithdrawMessagePresent(),"Withdraw Message Not Dsipalyed To Buyer");
    }


    /*WEB-274:Buyer receives counter offer but declines counter offer
    */
    @Test
    public void chat_CounterDeclineBuyerSide()
    {
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
        chatPage1.clickMakeanOfferButton();
        String amountofOffer="3000";
        chatPage1.setAmountForOffer(amountofOffer);
        Assert.assertFalse(chatPage1.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        chatPage1.clickSubmitButton();

        ChatPage chatPage = new ChatPage(driver);
        chatPage.clickChatNotificationAtTopRight();

        Assert.assertTrue(chatPage.getIncomingMessageFromChatDialog(1).equals(testData.get("question")), "Not able to deliver chat message to recipient (buyer initiated chat)");
        Assert.assertTrue(chatPage.checkOfferReceivedBySeller(amountofOffer), "Offer Not Received By Seller Pls Check");
        chatPage.clickNegotiateOfferButton();
        String amountofNegotiateOffer="4000";
        chatPage.setAmountForOffer(amountofNegotiateOffer);
        Assert.assertFalse(chatPage.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        Assert.assertTrue(chatPage.isCounterOfferMessageSellerSidePresent(), "Failed to appear Counter Offer message at Seller Side");
        Assert.assertTrue(chatPage1.isCounterOfferBuyerSidePresent(), "Failed to Receive Counter Offer Update at Buyer Side");
        chatPage1.clickDeclineOfferButton();
        Assert.assertTrue(chatPage1.isWithdrawMessagePresent(), "Withdraw Message Not Dsipalyed To Buyer");
    }

    /*WEB-1021:Low touch: Seller negotiate offer
    */
    @Test(dataProvider = "LowTouchCities",dataProviderClass = Data.class)
    public void chat_LowTouchSellerSideNegotiateOffer(String city) {

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
        chatPage.clickNegotiateOfferButton();
        String amountofNegotiateOffer = "4000";
        chatPage.setAmountForOffer(amountofNegotiateOffer);
        Assert.assertFalse(chatPage.confirmOfferBuyer(), "API is down Pls Try Again Message is coming");
        Assert.assertTrue(chatPage.isLowTouchPresent(), "Failed to Load Low Touch");
    }

    /*
    WEB-73:Check whether Ad presence(Online) is there on Snb/Vap/Mcr.
     */
    @Test
    public void ValidateAdPresence()
    {
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

        ChatPage chatPageOldDriver = new ChatPage(driver);
        ChatPage chatPageNewDriver = new ChatPage(newDriver);

        chatPageNewDriver.clickChatNowVapPage();
        chatPageNewDriver.setChatName(testData.get("name"));
        chatPageNewDriver.setEmailId(getRandomInteger(15) + "@gmail.com");
        chatPageNewDriver.setQuestion(testData.get("question"));
        chatPageNewDriver.clickChatNowChatDialog();
        String initialExtractedStatus = chatPageNewDriver.getChatStatus();
        Assert.assertEquals(initialExtractedStatus, "Sent", "The chat status is not correct. Please check!.");
        Assert.assertTrue(chatPageOldDriver.checkOnlinePresence(), "Online presence validation failed. Please check!");
    }

    /*
    WEB-83:Check whether Fresh user is getting logged into Tigase after posting Ad & replying ads.
     */
    @Test
    public void ValidateFreshUserLogIn()
    {
        adCreation(testData.get("city"));
        RemoteWebDriver newDriver = openNewWindow();
        ChatPage chatPageNewDriver = new ChatPage(newDriver);
        newDriver.manage().window().maximize();
        openAd(newDriver, adId);
        waitForPageToLoad("W0QQAdIdZ", newDriver);
        Assert.assertTrue(chatPageNewDriver.checkOnlinePresence(), "Online presence validation failed for fresh user in tigase. Please check!");
    }

    /*
    WEB-74:Check whether Ad presence(Offline) is there on Snb/Vap/Mcr.
     */
    @Test
    public void ValidateUserGettingOffline()
    {
        adCreation(testData.get("city"));
        ChatPage chatPageOldDriver = new ChatPage(driver);
        MyQuikrPage myQuikrPage = new MyQuikrPage(driver);
        String extractedUsername = myQuikrPage.getLoggedInEmail();
        openAd(driver, adId);
        waitForPageToLoad("W0QQAdIdZ", driver);
        driver.manage().window().maximize();
        String ExtractedUsernameAgain = myQuikrPage.getLoggedInEmail();
        Assert.assertEquals(chatPageOldDriver.validateLoggedInEmail(extractedUsername, ExtractedUsernameAgain), false, "User got logged in. Please check!");
    }

    /*
    WEB-178:Chat Init From SnB
     */
    @Test
    public void ValidateChatInitSnb()
    {
        adCreation(testData.get("city"));

        RemoteWebDriver newDriver = openNewWindow();
        HeaderPage headerPage = new HeaderPage(newDriver);
        HomePage homePage = new HomePage(newDriver);
        ChatPage chatPage = new ChatPage(newDriver);

        navigatethirdparty(newDriver,"http://www.quikr.com");
        //homePage.clickonCityCloseButton();
        headerPage.search(adId);
        Assert.assertTrue(chatPage.detectOnlineUserAndClickSnbChat(),"The ads didn't get listed in Snb. Please check!");
        Assert.assertTrue(chatPage.checkChatWindow(), "Chat didn't get initialised. Please check!");
    }
}
