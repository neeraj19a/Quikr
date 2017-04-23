package com.quikr.app.ios.chat;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by Manvendra Singh on 27/10/15.
 */
public class ChatPage extends com.quikr.app.ios.AppiOSPageBase {

    public ChatPage(AppiumDriver driver)
    {
        super(domFile,driver);
    }
    // const string
    private static final String domFile = getProperties().get("IOS_CHAT_DOM_FILE");

    /**
     * function to click on chat msj
     */
    public void clickOnChatBox()
    {
        Mapper.waitForElementToBeVisible(domFile,"chatBox");
        Mapper.find(domFile,"chatBox").click();
    }

    /**
     * function to send text in chat box
     */
    public void setTextInChatBox(String str)
    {
        Mapper.waitForElementToBeVisible(domFile,"chatBox");
        Mapper.find(domFile,"chatBox").sendKeys(str);
    }

    /**
     * function to select send button on chat page
     */
    public void selectSendButton()
    {
        Mapper.find(domFile,"sendButton").click();
    }

    /**
     * function to check send button present
     */
    public boolean checkPresenceOfSendButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"sendButton");
        return isElementPresent("sendButton");
    }

    /**
     * function to validate chat
     */
    public boolean validateChat()
    {
        Mapper.waitForElementToBeVisible(domFile,"validateChatText");
        List<WebElement> allElements = Mapper.finds(domFile, "validateChatText");
        for (int i=0;i<allElements.size();i++)
        {
            String text=Mapper.finds(domFile, "validateChatText").get(i).getText();
            if (text.contains("Sent"))
            {
                return true;
            }
        }

        return false;

    }

    /**
     * function to click on ad on chat and replies
     */
    public void selectAdFromChatAndReplies()
    {
        System.out.println(Mapper.find(domFile, "adToSelect").getText());
        Mapper.find(domFile, "adToSelect").click();
    }

    /**
     * function to click on chat attach button
     */
    public void clickOnChatAttachButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"chatAttachButton");
        Mapper.find(domFile,"chatAttachButton").click();
    }

    /**
     * function to select share my contact
     */
    public void selectShareMyContactButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"shareMyContactButton");
        Mapper.find(domFile,"shareMyContactButton").click();
    }

    /**
     * function to select share my location
     */
    public void selectShareMyLocationButton()
    {
        Mapper.find(domFile,"shareMyLocationButton").click();
    }

    /**
     * function to check allow button present
     */
    public boolean checkAllowButton()
    {
        return isElementPresent("allowButton");
    }

    /**
     * function to click on allow button
     */
    public void selectAllowButton()
    {
        Mapper.find(domFile,"allowButton").click();
    }

    /**
     * function to select location
     */
    public void selectShareLocation()
    {
        Mapper.waitForElementToBeVisible(domFile,"selectShareLocation");
        Mapper.find(domFile,"selectShareLocation").click();
    }

//    /**
//     * function to get text from set name
//     */
//    public String getTextFromSetNameOnChatAndRepliesPage()
//    {
//        String userName=Mapper.find(domFile, "userName").getText();
//        return userName;
//    }
//
//    /**
//     * function to get text from mobile no
//     */
//    public String getTextFromSetMobileNoOnChatAndRepliesPage()
//    {
//        String mobileNo=Mapper.find(domFile,"mobileNo").getText();
//        return mobileNo;
//    }
//
//    /**
//     * function to get text of email
//     */
//    public String getTextOfEmailOnChatAndRepliesPage()
//    {
//        String email= Mapper.find(domFile,"email").getText();
//        return email;
//    }

    /**
     * function to provide name
     */
    public void setNameOnChatAndRepliesPage(String name)
    {
        Mapper.waitForElementToBeVisible(domFile,"userName");
        Mapper.find(domFile,"userName").clear();
        Mapper.find(domFile,"userName").sendKeys(name);
    }

    /**
     * function to provide email id
     */
    public void setEmailOnChatAndRepliesPage(String emailName,int i)
    {
//        Mapper.find(domFile,"email").clear();
//        Mapper.find(domFile,"email").sendKeys(emailName);
        Mapper.findAndReplace(domFile, "email", new String[]{Integer.toString(i)}).clear();
        Mapper.findAndReplace(domFile, "email", new String[]{Integer.toString(i)}).sendKeys(emailName);
    }

    /**
     * function to provide mobile no
     */
    public void setMobileNoOnChatAndRepliesPage(String mbNo,int i)
    {
//        Mapper.find(domFile,"mobileNo").clear();
//        Mapper.find(domFile,"mobileNo").sendKeys(mbNo);
        Mapper.findAndReplace(domFile, "mobileNo", new String[]{Integer.toString(i)}).clear();
        Mapper.findAndReplace(domFile, "mobileNo", new String[]{Integer.toString(i)}).sendKeys(mbNo);
    }



    /**
     * function to click on share button
     */
    public void clickOnShareButton()
    {
        Mapper.find(domFile,"shareButton").click();
    }

    /**
     * function to validation for if all the mandatory fields are not fill on chat initiation screen
     */
    public boolean validateAllMandatoryFieldsNotFill()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgAfterPressSendButtonWithoutFillDetails");
        return isElementPresent("msgAfterPressSendButtonWithoutFillDetails");
    }

    /**
     * function to validate if name and email not write on chat initiation screen
     */
    public boolean validateNameAndEmailNotWriteOnChatInitiationScreen()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgAfterPressSendButtonWithoutFillingNameAndEmail");
        return isElementPresent("msgAfterPressSendButtonWithoutFillingNameAndEmail");
    }

    /**
     * function to validate if email is not write in chat initiation chat
     */
    public boolean validateEmailNotProvidedOnChatInitiationScreen()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgAfterPressSendButtonWithoutFillingEmail");
        return isElementPresent("msgAfterPressSendButtonWithoutFillingEmail");
    }

    /**
     * function to validate incorrect email id
     */
    public boolean validateIncorrectEmail()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgAfterEnterIncorrectEmail");
        return isElementPresent("msgAfterEnterIncorrectEmail");
    }

    /**
     * function to select cross sign on chat page
     */
    public void selectCrossButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"crossButton");
        Mapper.find(domFile,"crossButton").click();
    }

    /**
     * function to validate MCR screen
     */
    public boolean validateMCRScreen()
    {
        return (isElementPresent("allTab") && isElementPresent("byOtherTab") && isElementPresent("byMeTab"));
    }

    /**
     * function to select by other tab in MCR screen
     */
    public void selectByOtherTab()
    {
        Mapper.find(domFile,"byOtherTab").click();
    }

    /**
     * function to select by me tab in MCR screen
     */
    public void selectByMeTab()
    {
        Mapper.find(domFile,"byMeTab").click();
    }

    /**
     * function to click on ad title in MCR screen
     */
    public void clickOnAdTitleOnMCRScreen()
    {
        Mapper.find(domFile,"clickOnAdTitle").click();
    }

//    /**
//     * function to validate delete icon and block icon on mcr screen
//     */
//    public boolean validateDeleteAndBlockIconOnMCRScreen()
//    {
//        return (isElementPresent("deleteButton") && isElementPresent("blockButtonOnMCRScreen"));
//    }
//
//    /**
//     * function to slide ad
//     */
//    public void slideAnAd()
//    {
//        MobileElement cell = (MobileElement) Mapper.find(domFile,"myAdUIATableList");
//        cell.swipe(SwipeElementDirection.LEFT, 1000);
//        Point location = cell.getLocation();
//        Dimension size = cell.getSize();
//        Point deleteBtnLocation = new Point (location.getX() + size.getWidth()-10, location.getY() + size.getHeight()/2);
//        Mapper.tap(1, deleteBtnLocation.getX(), deleteBtnLocation.getY(), 1000);
//    }
//    /**
//     * function to select delete button on mcr screen
//     */
//    public void selectDeleteButtonOnMCRScreen()
//    {
//        Mapper.find(domFile,"deleteButton").click();
//    }

    /**
     * function to validate all button on chat page through mcr screen
     */
    public boolean validateAllOptionsOnChatThroughMCRScreen()
    {
        return (isElementPresent("chatAttachButton") && isElementPresent("cannedButton") && isElementPresent("cameraButton") && isElementPresent("audioRecordingButton") );
    }

    /**
     * function to click on canned button
     */
    public void clickOnCannedButton()
    {
        Mapper.find(domFile,"cannedButton").click();
    }

    /**
     * function to validate canned msg
     */
    public boolean validateCannedMsg()
    {
        Mapper.waitForElementToBeVisible(domFile,"cannedMsg");
        List<WebElement> allElement=Mapper.find(domFile, "cannedMsg").findElements(By.className("UIATableCell"));
        int totalCellCount=allElement.size();
        if(totalCellCount==5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * function to click on canned msg
     */
    public void selectCannedMsgFromCannedMsgList()
    {
        Mapper.find(domFile,"selectCannedMsgFromCannedMsgList").click();
    }

    /**
     * function to validate canned msg sent status
     */
    public boolean validateCannedMsgStatus()
    {
        return isElementPresent("cannedMsgStatus");
    }
    /**
     * function to click on text msg send button
     */
    public void selectTextMsgSendButton()
    {
        Mapper.find(domFile,"textMsgSendButton").click();
    }

    /**
     * function to validate invalid or blank share contact details
     */
    public boolean validateInvalidShareContactDetails()
    {
        return isElementPresent("msgAfterClickOnShareButtonWithoutFillingAnyDetails");
    }

    /**
     * function to validate delete and block icon in mcr screen
     * @return
     */
    public  boolean validateDeleteAndBlockButtonInMcrScreen()
    {
        MobileElement cell = (MobileElement)(Mapper.find(domFile, "uiaTableViewForMcrScreen").findElements(By.className("UIATableCell")).get(1));

        cell.swipe(SwipeElementDirection.LEFT, 1000);
        Mapper.waitForElementToBeVisible(domFile,"deleteIconInMcrScreen");
        Point location = cell.getLocation();
        int x=location.getX();
        int y=location.getY();
        TouchAction touchAction=Mapper.getTouchActionObject();
        touchAction.press(x, y).waitAction(1000).moveTo(x + 120, y).release();
        return (isElementPresent("deleteIconInMcrScreen") && isElementPresent("blockIconInMcrScreen"));
    }
}
