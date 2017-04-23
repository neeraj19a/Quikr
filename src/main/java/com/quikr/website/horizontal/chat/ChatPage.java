package com.quikr.website.horizontal.chat;

import com.quikr.website.PageBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra singhon 29/7/15.
 */
public class ChatPage extends PageBase {

    public ChatPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("CHAT_DOM_FILE");

    /**
     * function to send chat name
     */
    public void setChatName(String chatName)
    {
        Mapper.waitForElementToBeClickable(domFile,"chatName");
        Mapper.find(domFile,"chatName").sendKeys(chatName);
    }

    /**
     * function to send contact number
     */
    public void setContactNo(String chatNumber)
    {
        Mapper.waitForElementToBeClickable(domFile,"chatNumber");
        Mapper.find(domFile,"chatNumber").sendKeys(chatNumber);
    }

    /**
     * function to send email id
     */
    public void setEmailId(String chatEmail)
    {
        Mapper.find(domFile,"chatEmail").sendKeys(chatEmail);
    }

    /**
     * function to send question
     */
    public  void setQuestion(String chatQuestion)
    {
        Mapper.waitForElementToBeVisible(domFile,"chatContent");
        Mapper.waitForElementToBeClickable(domFile, "chatContent");
        Mapper.find(domFile,"chatContent").sendKeys(chatQuestion);
    }

    /**
     * click on chat now button present in chat dialog
     */
    public void clickChatNowChatDialog() {
        Mapper.waitForElementToBeClickable(domFile, "chatNowChatDialog");
        Mapper.find(domFile, "chatNowChatDialog").click();
            if(Mapper.waitForElementToBeInvisible(domFile, "chatNowChatDialog", 45)==false) {
                WebElement chatbutton = Mapper.find(domFile, "chatNowChatDialog");
                if (chatbutton != null) {
                    chatbutton.click();
                }
            }
        }

    public void setanddeleteText(){
        Mapper.waitForElementToBeVisible(domFile, "enterChatMessage", 15);
        Mapper.find(domFile,"enterChatMessage").click();
        Mapper.find(domFile,"enterChatMessage").sendKeys("TestMessage TestMessage TestMessage");
        Mapper.find(domFile,"enterChatMessage").sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
    }

    /**
     * click on "Chat Now" button on Vap page
     */
    public void clickChatNowVapPage()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "vapChatNow")==true)
        {
            Mapper.find(domFile,"vapChatNow").click();
        }
        else
        {
            logger.info("Vap chat now button is invisible.");
        }

        if (!Mapper.waitForElementToBeVisible(domFile, "chatDialog"))
        {
            navigateTo().refresh();
            if (Mapper.waitForElementToBeVisible(domFile, "vapChatNow")==true)
            {
                Mapper.find(domFile,"vapChatNow").click();
            }
            else
            {
                logger.info("Vap chat now button is still invisible.");
            }
        }

//        Mapper.waitForElementToBeVisible(domFile, "vapChatNow");
//        Mapper.waitForElementToBeClickable(domFile, "vapChatNow");
//        Mapper.find(domFile,"vapChatNow").click();
//
//        if(!Mapper.waitForElementToBeVisible(domFile, "chatDialog"))
//        {
//            navigateTo().refresh();
//            Mapper.waitForElementToBeVisible(domFile, "vapChatNow");
//            Mapper.waitForElementToBeClickable(domFile, "vapChatNow");
//            Mapper.find(domFile,"vapChatNow").click();
//        }

    }

    /**
     * get incoming message from chat dialog
     * @param messageNumber
     */
    public String getIncomingMessageFromChatDialog(int messageNumber)
    {

        return Mapper.findAndReplace(domFile, "chatMessageAppearInDialog", new String[]{Integer.toString(messageNumber)}).getText();
    }

    public String getOutgoingMessageFromChatDialog(int messageNumber)
    {
        return Mapper.findAndReplace(domFile, "chatMessageAppearOutDialog", new String[]{Integer.toString(messageNumber)}).getText();
    }


    /**
     * enter chat message
     * @param message
     */
    public void enterChatMessage(String message)
    {
        Mapper.find(domFile, "enterChatMessage").sendKeys(message);
        Mapper.find(domFile, "enterChatMessage").sendKeys(Keys.ENTER);
    }

    /**
     * click on chat notification at bottom right of page
     */
    public void clickChatNotificationAtTopRight()
    {
        Mapper.waitForElementToBeVisible(domFile,"ChatMenu");
        Mapper.waitForElementToBeClickable(domFile, "ChatMenu");
        Mapper.find(domFile,"ChatMenu").click();
        Mapper.waitForElementToBeVisible(domFile, "ChatMessage");
        Mapper.waitForElementToBeClickable(domFile, "ChatMessage");
        Mapper.find(domFile, "ChatMessage").click();

    }

    public void clickImageShareNotificationAtTopRight()
    {
        Mapper.waitForElementToBeClickable(domFile, "ChatMenu");
        Mapper.find(domFile,"ChatMenu").click();
        Mapper.waitForElementToBeClickable(domFile, "imageShareTopNotification");
        Mapper.find(domFile, "imageShareTopNotification").click();

    }

    public void clickCannedResponseChat(){
        if(Mapper.find(domFile,"cannedResponseChatIcon").isDisplayed()==true){
            Mapper.find(domFile,"cannedResponseChatIcon").click();
        }
    }

    public void clickCannedResponseMessage(int messagenumber) {
        Mapper.findAndReplace(domFile, "cannedResponseMessage",new String[]{Integer.toString(messagenumber)}).click();
    }

    public boolean checkCannedMsgPresent(String message){
        String actualmessage=Mapper.findAndReplace(domFile,"cannedResponseMessagePresentText",new String []{message}).getText();
        if(actualmessage.equals(message)){
                return true;
            }
        else {
                return false;
            }
    }

    public void clickChatNotificationAtBottomRight()
    {
        Mapper.waitForElementToBeVisible(domFile, "chatNotificationAtPageBottom");
        Mapper.find(domFile, "chatNotificationAtPageBottom").click();
    }

    public void clickMakeanOfferButton(){
        Mapper.waitForElementToBeVisible(domFile, "MakeanOfferButton");
        Mapper.waitForElementToBeClickable(domFile, "MakeanOfferButton");
        Mapper.find(domFile,"MakeanOfferButton").click();
    }

    public void setAmountForOffer(String amountForOffer){
        Mapper.waitForElementToBeVisible(domFile,"OfferAmount");
        Mapper.waitForElementToBeClickable(domFile, "OfferAmount");
        Mapper.find(domFile, "OfferAmount").click();
        Mapper.find(domFile, "OfferAmount").clear();
        Mapper.find(domFile, "OfferAmount").sendKeys(amountForOffer);
    }

    public void clickSubmitButton(){
        WebElement submitButton=Mapper.find(domFile, "SubmitButton");
        if(submitButton!=null){
            submitButton.click();
        }

    }

    public boolean checkMessageSent(){
        return isElementPresent("MessageSent");
    }

    public boolean confirmOfferBuyer(){
        Mapper.waitForElementToBeVisible(domFile, "OfferAmountConfirmButton");
        Mapper.waitForElementToBeClickable(domFile, "OfferAmountConfirmButton");
        Mapper.find(domFile, "OfferAmountConfirmButton").click();
        boolean a=Mapper.find(domFile,"PlsTryAgainMsg").isDisplayed();
        boolean b=Mapper.find(domFile,"PlsTryAgainMsg").isDisplayed();
        if(a==true||b==true) {
            return true;
        }
        else
            return false;
    }

    public void cancelOfferBuyer(){
        Mapper.waitForElementToBeVisible(domFile, "OfferAmountCancelButton");
        Mapper.waitForElementToBeClickable(domFile, "OfferAmountCancelButton");
        Mapper.find(domFile, "OfferAmountCancelButton").click();
    }

    public boolean checkOfferSenttoSeller(){
        Mapper.waitForElementToBeVisible(domFile,"OfferMessage");
        boolean message = isElementPresent("OfferMessage");
        //boolean msgpresent=Mapper.find(domFile,"OfferMessage").getText().contains("Your offer has been sent to the seller.");
        if(message){
            return true;
        }
        else
            return false;
    }

    public boolean checkOfferReceivedBySeller(String amountofOffer){
        Mapper.waitForElementToBeVisible(domFile, "OfferMessage");
        String amount = Mapper.find(domFile, "OfferAmountAtSellerSide").getText();

        if(amount.contains(amountofOffer)){
            return true;
        }
        else
            return false;
    }


    /*
    Check for confirmation on message being sent
     */
    public boolean checkSentTag()
    {
        boolean retVal = false;
        Mapper.waitForElementToBeVisible(domFile,"ChatSentText");

        if (Mapper.find(domFile, "ChatSentText")!=null)
        {
            if (Mapper.find(domFile,"ChatSentText").getText().equals("Sent"))
            {
                retVal = true;
            }
            else
            {
                retVal=false;
            }
        }
        else
        {
            retVal=false;
        }
        return retVal;
    }

    public String getChatStatus()
    {
        String chatStatus = "";
        if (Mapper.find(domFile, "ChatSentText")!=null)
        {
            if (Mapper.waitForElementToBeVisible(domFile,"ChatSentText")==true)
            {
                chatStatus = Mapper.find(domFile, "ChatSentText").getText();
            }
        }
        else if (Mapper.find(domFile,"ChatSeenText")!=null)
        {
            if (Mapper.waitForElementToBeVisible(domFile,"ChatSeenText")==true)
            {
                chatStatus = Mapper.find(domFile, "ChatSeenText").getText();
            }
        }
        else
        {
            logger.info("Niether Sent nor Seen status are displayed!!!!!!! Please check!");
        }
        return chatStatus;
    }

    public boolean checkIfChatNotificationUpdated()
    {
        boolean retVal = false;
        if (Mapper.find(domFile,"ChatNotificationNumbers").isDisplayed()==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return  retVal;
    }

    public boolean checkChatWindow() {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "chatWindowDetailsArea") == true) {
            if (Mapper.find(domFile, "chatWindowDetailsArea") != null) {
                retVal = true;
            }
        } else {
            return false;
        }
        return retVal;
    }
    public void clickAcceptOffer(){
        Mapper.waitForElementToBeVisible(domFile, "AcceptOfferButton");
        Mapper.waitForElementToBeClickable(domFile, "AcceptOfferButton");
        logger.info("Clicking in Accept offer button....");
        Mapper.find(domFile, "AcceptOfferButton").click();
        logger.info("Clicked ....");
        Alert myalert=switchTo().alert();
        myalert.accept();
       /* if(Mapper.waitForElementToBeInvisible(domFile, "AcceptOfferButton",8)==false) {
            WebElement acceptbutton=Mapper.find(domFile, "AcceptOfferButton");
            if(acceptbutton!=null) {
                acceptbutton.click();
                myalert.accept();
            }
        }

        if(Mapper.waitForElementToBeInvisible(domFile, "AcceptOfferButton",8)==false) {
            WebElement acceptbutton=Mapper.find(domFile, "AcceptOfferButton");
            if(acceptbutton!=null) {
                acceptbutton.click();
                myalert.accept();
            }
        }*/
    }

    public void clickAcceptCounterOffer(){
        Mapper.waitForElementToBeVisible(domFile, "AcceptCounterOfferButton");
        Mapper.waitForElementToBeClickable(domFile, "AcceptCounterOfferButton");
        Mapper.find(domFile, "AcceptCounterOfferButton").click();
        Alert myalert=switchTo().alert();
        myalert.accept();

    }
    public void clickWithdrawOfferBUtton(){
        Mapper.waitForElementToBeVisible(domFile, "WithdrawOfferButton");
        Mapper.waitForElementToBeClickable(domFile, "WithdrawOfferButton");
        Mapper.find(domFile,"WithdrawOfferButton").click();

    }

    public void closeChatWindow(){
        if(Mapper.find(domFile,"closeChatWindow").isDisplayed()==true){
            Mapper.find(domFile,"closeChatWindow").click();
        }
    }
    public boolean isWithdrawMessagePresent(){
        return isElementPresent("WithdrawOfferMessage");
    }

    public void clickNegotiateOfferButton(){
        Mapper.waitForElementToBeVisible(domFile, "NegotiateOfferButton", 15);
        Mapper.find(domFile,"NegotiateOfferButton").click();
    }

    public boolean isLowTouchPresent(){
        return isElementPresent("LowTouchSellerSide");
    }
    public boolean isSellerAcceptedOffer(){
        return isElementPresent("OfferAcceptedMessageSellerSide");

    }


    public boolean isTypingSeenwhileChatting(){
        return isElementPresent("typing");
    }
    public void clickSettingsChatWindow()
    {
        if (Mapper.find(domFile,"chatWindowSettingsIcon").isDisplayed()==true)
        {
            Mapper.find(domFile,"chatWindowSettingsIcon").click();
        }
        else
        {
            logger.info("Setting icon in chat window is not present. Please check!");
        }
    }

    public boolean isChatCleared(){
        WebElement clearchat=Mapper.find(domFile,"clearConversationChatWindow");
        if(clearchat!=null){
            clearchat.click();
            Mapper.waitForElementToBeVisible(domFile,"clearConversationAlertYesButton",15);
            Mapper.find(domFile,"clearConversationAlertYesButton").click();
        }
        if(Mapper.find(domFile, "clearConversationMessage")!=null){
            return true;
        }
        else
            return false;

    }

    public boolean checkIfBlockIconPresent()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "chatWindowBlockUserIcon")==true)
        {
            retVal = true;
        }
        else
        {
            retVal = false;
        }
        return retVal;
    }

    public void clickBlockButton()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"chatWindowBlockUserIcon")==true)
        {
            Mapper.find(domFile,"chatWindowBlockUserIcon").click();
        }
        else {
            logger.info("Block button not present. Please check!");
        }
    }

    public boolean checkTextInInlineMsgs(String textToSearch) {
        boolean retVal = false;
        List<WebElement> elm = Mapper.finds(domFile, "chatWindowInlineMessages");
        for (int i = 0; i < elm.size(); i++) {
            String text = elm.get(i).getText();
            if (text.contains(textToSearch)) {
                retVal = true;
            } else {
                retVal = false;
            }
        }
        return retVal;
    }


    public void clickBlockConfirmationBtn() {
        Mapper.find(domFile, "chatWindowBlockConfirmationBtn").click();
    }
    public boolean isBuyerOfferAccepted(){
        return isElementPresent("OfferAcceptedMessageBuyerSide");

    }

    public void clickMakePaymentButton()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"MakePaymentButton")==true)
        {
            if (Mapper.waitForElementToBeClickable(domFile,"MakePaymentButton")==true)
            {
                Mapper.find(domFile,"MakePaymentButton").click();
            }else
            {
                logger.info("Make payment button is not clickable.");
            }
        }else
        {
            logger.info("Make payment button is not visible.");
        }
    }

    public boolean isMakePaymentButtonpresent(){
        return isElementPresent("MakePaymentButton");
    }
    public boolean isPaymentScreenLoaded(){
        Mapper.waitForElementToBeVisible(domFile, "PaymentScreen");
        return isElementPresent("PaymentScreen");
    }

    public void clickEditOfferBUtton(){
        Mapper.waitForElementToBeVisible(domFile,"EditOfferButton");
        Mapper.waitForElementToBeClickable(domFile, "EditOfferButton");
        Mapper.find(domFile,"EditOfferButton").click();
    }

    public boolean isUpdatedOfferMessagePresent(){
        return isElementPresent("UpdatedOfferMessage");
    }

    public boolean isCounterOfferMessageSellerSidePresent(){
        return isElementPresent("CounterOfferMessageSellerSide");
    }

    public void clickDeclineOfferButton(){
        Mapper.waitForElementToBeVisible(domFile,"DeclineOfferButton");
        Mapper.waitForElementToBeClickable(domFile, "DeclineOfferButton");
        Mapper.find(domFile,"DeclineOfferButton").click();
    }

    public boolean isCounterOfferBuyerSidePresent(){
        boolean message=isElementPresent("CounterOfferMessageBuyerSide");
        boolean declinebutton=isElementPresent("DeclineOfferButton");
        boolean acceptbutton=isElementPresent("AcceptCounterOfferButton");

        boolean flag=false;
        if(declinebutton==true){
            flag=true;
        }
        else
        return false;

        if(message==true){
            flag=true;
        }
        else
        return false;

        if(acceptbutton==true){
            flag=true;
        }
        else
        return false;

            return flag;
    }

    public void sendMessageChatWindow(String message)
    {
        if (Mapper.find(domFile,"chatWindowNewMessage").isDisplayed()==true)
        {
            Mapper.find(domFile,"chatWindowNewMessage").click();
            Mapper.find(domFile,"chatWindowNewMessage").sendKeys(message);
            Mapper.find(domFile,"chatWindowNewMessage").sendKeys(Keys.ENTER);
        }
    }

    public void clickUnblock()
    {
        if (Mapper.find(domFile,"chatWindowUnblockButton").isDisplayed()==true)
        {
            Mapper.find(domFile,"chatWindowUnblockButton").click();
            if (Mapper.find(domFile,"chatWindowUnblockConfirmation").isDisplayed()==true)
            {
                Mapper.find(domFile,"chatWindowUnblockConfirmation").click();
            }
        }
    }

    public int getChatCount()
    {
        int chatCount = 0;
        String strChatCount = "";
        if (Mapper.waitForElementToBeVisible(domFile, "chatCount")==true)
        {
            strChatCount=Mapper.find(domFile,"chatCount").getText();
            chatCount = Integer.parseInt(strChatCount);
            logger.info("CHAT COUNT = "+chatCount);
        }
        else
        {
            logger.info("Chat count still not visible.");
        }
        return chatCount;
    }

    public int getChatCountFromChatTab()
    {
        int chatCount = 0;
        String strChatCount = "";
        if (Mapper.waitForElementToBeVisible(domFile, "chatCountChatTab")==true)
        {
            strChatCount=Mapper.find(domFile,"chatCountChatTab").getText();
            chatCount = Integer.parseInt(strChatCount);
            logger.info("CHAT COUNT = "+chatCount);
        }
        else
        {
            logger.info("Chat count still not visible.");
        }
        return chatCount;
    }

    public boolean isChatCountPresent()
    {
        boolean retVal = false;
        if (Mapper.find(domFile, "chatCount")!=null)
        {
            retVal = true;
        }
        else
        {
            retVal = false;
        }
        return retVal;
    }

    public void clickAttachmentIcon(){
        Mapper.waitForElementToBeVisible(domFile,"chatAttachmentIcon");
        if(Mapper.find(domFile,"chatAttachmentIcon").isDisplayed()==true){
            Mapper.find(domFile,"chatAttachmentIcon").click();
        }
    }

    public void clickImageAttachment(){
        if(Mapper.find(domFile,"imageAttachment").isDisplayed()==true){
            Mapper.find(domFile,"imageAttachment").click();
        }

    }

    public boolean isFileSizeValidationMsgPresent(){
        return isElementPresent("fileSizeValidationMessage");
    }

    public String getFlyerBottomChatMessage(){
        WebElement flyermessage=Mapper.find(domFile,"FlyerBottomChatMessage");
        return flyermessage.getText();
    }

    public void clickVideoAttachment(){
        if(Mapper.find(domFile,"videoAttachment").isDisplayed()==true){
            Mapper.find(domFile,"videoAttachment").click();
        }

    }

    public void clickContactAttachment(){
        if(Mapper.find(domFile,"contactAttachment").isDisplayed()==true){
            Mapper.find(domFile,"contactAttachment").click();
        }

    }

    public void setNameContactAttachment(String name){
        if(Mapper.find(domFile,"inputNameContactAttachment").isDisplayed()==true){
            Mapper.find(domFile,"inputNameContactAttachment").click();
            Mapper.find(domFile,"inputNameContactAttachment").sendKeys(name);
        }

    }

    public void setPhoneContactAttachment(String phone){
        if(Mapper.find(domFile,"inputPhoneContactAttachment").isDisplayed()==true){
            Mapper.find(domFile,"inputPhoneContactAttachment").click();
            Mapper.find(domFile,"inputPhoneContactAttachment").sendKeys(phone);
        }

    }

    public boolean isContactDetailsSent(){
        return isElementPresent("contactDetailsSent");
    }

    public void clickSendContactButton(){
        if(Mapper.find(domFile,"sendContactAttachment").isDisplayed()==true){
            Mapper.find(domFile,"sendContactAttachment").click();
        }
    }

    public void clickLocationAttachment(){
        Mapper.waitForElementToBeVisible(domFile,"locationAttachment");
        if(Mapper.find(domFile,"locationAttachment").isDisplayed()==true){
            Mapper.find(domFile,"locationAttachment").click();
        }
    }

    public void shareLocation(){
        Mapper.waitForElementToBeVisible(domFile,"shareLocationWindow");
        if(Mapper.find(domFile,"shareLocationWindow").isDisplayed()==true){
            Mapper.find(domFile,"clickshareLocationButtonInMap").click();
        }
    }
    public boolean isLocationShown(){
        return isElementPresent("locationSharedbySeller");
    }
    public boolean isImageThumbnailShown(){
        return isElementPresent("imageThumbnail");
    }

    public void clickDownloadImageAttachment(){
        if(Mapper.find(domFile,"downloadImageAttachmentIcon").isDisplayed()==true){
            Mapper.find(domFile,"downloadImageAttachmentIcon").click();
        }
    }

    public boolean isImageShareBottomNotificationcoming(){
        return isElementPresent("imageShareBottomNotification");
    }

    public boolean checkOnlinePresence()
    {
        boolean retVal = false;
        try
        {
            Thread.sleep(5000);
            if (Mapper.find(domFile,"onlineChatVap")!=null)
            {
                retVal = true;
            }else if (Mapper.find(domFile,"offlineChatVap")!=null)
            {
                retVal=false;
            }else
            {
                logger.info("Niether online nor offline status is being reflected. Its wierd. Please check!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return retVal;
    }

    public boolean validateLoggedInEmail(String ExtractedEmail, String ExpectedEmail)
    {
        boolean retVal=false;
        if (ExtractedEmail.contains(ExpectedEmail)==true)
        {
            retVal = true;
        }
        else
        {
            retVal = false;
        }
        return retVal;
    }

    public boolean detectOnlineUserAndClickSnbChat()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "onlineChatSnb")==true)
        {
            Mapper.find(domFile,"onlineChatSnb").click();
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public void clickChatNotifTopRight()
    {
        try {
            logger.info(" I am now going to sleep for 7 seconds and then try to click on the chat notification icon");
            Thread.sleep(7000);
            logger.info("Woke up from my sleep. Clicking...");
            if (Mapper.waitForElementToBeVisible(domFile, "chatNotificationTopRight") == true) {
                logger.info("Seems <chatNotificationTopRight> is visible....");
                Mapper.find(domFile, "chatNotificationTopRight").click();
                logger.info("Clicked <chatNotificationTopRight> , first attempt....");
                if (Mapper.waitForElementToBeInvisible(domFile,"chatNotificationTopRight")==true)
                {
                    logger.info("It was clicked in the first attempt itself and hence doing nothing....");
                }
                else
                {
                    Mapper.find(domFile, "chatNotificationTopRight").click();
                    logger.info("Clicked <chatNotificationTopRight> , second attempt....");
                }
            } else {
                logger.info("Chat notification at top right corner was invisible. Please check!");
                logger.info("<chatNotificationTopRight> was not visible.");
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void clickMyChatUnderChatNotification()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "myChatUnderChatNotification")==true){
            logger.info("Clicking My Chat link....");
            Mapper.find(domFile,"myChatUnderChatNotification").click();
            logger.info("Clicked, first attempt....");
            if (Mapper.waitForElementToBeInvisible(domFile, "myChatUnderChatNotification")==true)
            {
                logger.info("Seems <myChatUnderChatNotification> was clicked in first attempt itself. Doing nothing....");
            }
            else
            {
                logger.info("<myChatUnderChatNotification> is still visible. Being clicked for the second time...");
            }
            waitForPageToLoad("mychats");
        }
        else {
            logger.info("Chat notification at top right corner was invisible. Please check!");
        }
    }

    public boolean validateIfofferReceived(String buyerName, String offerMade)
    {
        boolean retVal = false;
        if (waitForPageToLoad("mychats")==true)
        {
            String extractedBuyerName = Mapper.find(domFile,"buyerNameFromSellerMyChatPage").getText();
            String extractedOfferPrice = Mapper.find(domFile,"offerPriceFromSellerMyChatPage").getText();

            if (extractedBuyerName.contains(buyerName) && extractedOfferPrice.contains(offerMade))
            {
                retVal = true;
            }

        }
        else
        {
            logger.info("Offer made is not visible in my chats page of the seller. Please check!");
        }
        return retVal;
    }

    public void clickOnChatMessageinMyChats()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"chatMessagePlaceHolder"))
        {
            logger.info("Clicking on <chatMessagePlaceHolder> ....");
            Mapper.find(domFile,"chatMessagePlaceHolder").click();
            logger.info("Clicked on <chatMessagePlaceHolder> ....");
            if (Mapper.waitForElementToBeInvisible(domFile, "chatMessagePlaceHolder")==true)
            {
                logger.info("<chatMessagePlaceHolder> is invisible. Doing nothing....");
            }
            else
            {
                logger.info("Clicking on <chatMessagePlaceHolder> again ....");
                Mapper.find(domFile,"chatMessagePlaceHolder").click();
            }
        }
        else
        {
            logger.info("Chat message was not visible.");
        }
    }

    public void SubmitSellerInfo(String name, String Email, String phNo)
    {
        Mapper.find(domFile,"SellerName").sendKeys(name);
        Mapper.find(domFile,"SellerEmail").sendKeys(Email);
        Mapper.find(domFile,"SellerPincode").sendKeys("560029");
        Mapper.find(domFile,"SellerAddress").sendKeys("test address, this is my address, Houne number -456");
        Mapper.find(domFile,"SellerMobileNum").sendKeys(phNo);
        Mapper.find(domFile,"SubmitForSellerInfo").click();
    }



}