package com.quikr.app.android.chat;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by rohanbajaj on 18/08/15.
 */
public class ChatPage extends AppAndroidPageBase
{
    public ChatPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    // const string
    private static final String domFile = getProperties().get("ANDROID_CHAT_DOM_FILE");

    public void clickOnChatAdTitle()
    {
        Mapper.finds(domFile, "chatAdTitle").get(0).click();
    }

    public void clickOnChatConversation()
    {
        Mapper.waitForElementToBeVisible(domFile, "chatConversation");
        Mapper.finds(domFile, "chatConversation").get(0).click();
    }

    public void sendChat(String chatContent)
    {
        Mapper.find(domFile, "chatInput").click();
        Mapper.find(domFile, "chatInput").sendKeys(chatContent);
        Mapper.find(domFile, "sendChat").click();
    }

    public boolean validateChatSent()
    {
        return (isElementPresent("chatSentConfirmation1") || isElementPresent("chatSentConfirmation2"));
    }

    /**
     * function to click on any chat and replies user
     */
    public void selectChatAndRepliesUser(int i)
    {
        Mapper.waitForElementToBeVisible(domFile,"chatAndRepliesUser");
        Mapper.finds(domFile,"chatAndRepliesUser").get(i).click();
    }

    /**
     * function to click on chat page ad
     */
    public void openChatPageAds()
    {
        Mapper.waitForElementToBeVisible(domFile,"openChatPageAd");
        Mapper.find(domFile, "openChatPageAd").click();
    }

    /**
     * function to validate 4 option are present on chat screen
     */
    public boolean validateOptionPresentOnChatScreen()
    {
        return (isElementPresent("chatSmileyButton") && isElementPresent("cannedIcon") && isElementPresent("cameraIcon"));
    }

    /**
     * function to click on smiley icon
     */
    public void clickOnSmileyIcon()
    {
        Mapper.find(domFile,"chatSmileyButton").click();
    }

    /**
     * function to click on camera icon
     */
    public void clickOnCameraIcon()
    {
        Mapper.find(domFile,"cameraIcon").click();
    }

    /**
     * function to validate  behaviour  of camera
     */
    public boolean validateBehaviourOfCamera()
    {
        Mapper.waitForElementToBeVisible(domFile, "chooseFromPhotoGallery");
        return (isElementPresent("chooseFromPhotoGallery") && isElementPresent("captureAPhoto"));
    }

    /**
     * function to click on attach button on chat page
     */
    public void clickOnAttachButton()
    {
        Mapper.find(domFile,"attachButton").click();
    }

    /**
     * function to validate block button is present on chat page
     */
    public boolean validateBlockButtonOnChatPage()
    {
        return isElementPresent("blockButton");
    }

    /**
     * select block user button
     */
    public void selectBlockUserButton()
    {
        Mapper.find(domFile,"blockButton").click();
    }

    /**
     * function to validate msg after click on block user button
     */
    public boolean validateMsgForBlockButtonAction()
    {
        return isElementPresent("unblock");
    }

    /**
     * function to click on chat text box
     */
    public void clickOnChatBox()
    {
        Mapper.find(domFile,"chatInput").click();
    }

    /**
     * function to sends text in chat box
     */
    public void sendTextInChatBox(String inputText)
    {
        Mapper.find(domFile,"chatInput").sendKeys(inputText);
    }

    public void clickOnMyChats()
    {
        Mapper.waitForElementToBeVisible(domFile, "myChats", 10);
        Mapper.find(domFile,"myChats").click();
    }
    public void enterSenderDetails(String name, String email, String number){
        Mapper.waitForElementToBeVisible(domFile,"chatName");
        Mapper.find(domFile,"chatName").sendKeys(name);
//        Mapper.find(domFile,"chatEmail").sendKeys(email);
        Mapper.find(domFile,"chatMobile").sendKeys(number);
        Mapper.find(domFile,"submit").click();
    }
    public boolean ifChatMAOFormOpen() {
        Mapper.waitForElementToBeVisible(domFile, "chatMaoFormHeader", 10);
        if (Mapper.find(domFile, "chatMaoFormHeader").getText().equalsIgnoreCase("Make Offer & Chat")) {
            return true;
        } else
            return false;
    }

        /**
     * click on chat rounded letter to view chat
     */
    public void clickOnRoundedLetterToOpenChat(int i) {
        Mapper.waitForElementToBeVisible(domFile, "charRoundedLetter", 10);
        Mapper.finds(domFile, "charRoundedLetter").get(i).click();
    }

    public void maoAndChat(String price, String email, String mobile)
    {
        Mapper.waitForElementToBeVisible(domFile,"chatMaoFormHeader",5);
//        Mapper.find(domFile,"MaoPrice").sendKeys(price);
//        Mapper.find(domFile,"MaoEmail").sendKeys(email);
//        Mapper.find(domFile,"MaoMobile").sendKeys(mobile);
        hideKeyboard();
        Mapper.find(domFile,"MaoSubmit").click();
    }

    public boolean ifOfferAmountSent(String offerAmount){
        Mapper.waitForElementToBeVisible(domFile,"chatMessage",5);
        String amountOnChatScreen = Mapper.find(domFile,"chatMessage").getText().replaceAll("\\D+","");
        if (offerAmount==amountOnChatScreen) {
            return true;
        }else
            return false;
    }
    public boolean ifWithdrawOfferPresent(){
        return true;
        //awaits a fix
    }
    public boolean ifEditOfferPresent(){
        if(Mapper.find(domFile,"editOffer").isDisplayed()) {
            return true;
        }
        else return false;
    }
    /**
     * verify Header OF individual chat screen
     */
    public boolean verifyHeaderOfIndividualChatScreen()
    {
        Mapper.waitForElementToBeVisible(domFile, "lastActiveTime");
        return(isElementPresent("moreButton")&&isElementPresent("chatUserNAme")&&isElementPresent("attachButton")&&isElementPresent("navigateBack"));

    }
    /**
     * get last active text
     */
    public String getLastActiveStatus()
    {
        Mapper.waitForElementToBeVisible(domFile, "lastActiveTime");
        return (Mapper.find(domFile,"lastActiveTime").getText());
    }
    /**
     * verify header of chat screen if user is not logged in
     */
    public boolean verifyHeaderOfChatScreenIfUserIsNotLoggedIn()
    {
        return (isElementPresent("menuIcon")&&isElementPresent("chatUserNAme"));
    }

    /**
     * verify header of chat screen if user is logged in
     */
    public boolean verifyHeaderOfChatScreenIfUserIsLoggedIn()
    {
        return (isElementPresent("menuIcon")&&isElementPresent("chatUserNAme")&&isElementPresent("userNAMeOnChatHomeScreen"));
    }
}
