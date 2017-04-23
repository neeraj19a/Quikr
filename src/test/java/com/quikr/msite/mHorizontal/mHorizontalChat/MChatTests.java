package com.quikr.msite.mHorizontal.mHorizontalChat;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mElectronics.mElectronicsHome.MElectronicsHomePage;
import com.quikr.msite.mElectronics.mElectronicsSNB.MElectronicsSnBPage;
import com.quikr.msite.mElectronics.mElectronicsVAP.MElectronincsVAPPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mHorizontal.mLogin.MLoginpage;
import com.quikr.msite.mHorizontal.mMyAccount.MMyAccountPage;
import com.quikr.msite.mHorizontal.mMyChatandReplies.MMyChatandReply;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 20/11/15.
 */
public class MChatTests extends MTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("mHORIZONTAL_TESTDATA_FILE"));

    /*MS-519:Verify On SNB presence is or showing or not (Online users)
    */
    @Test(groups = "horizontal")
    public void validateChatPresence(){
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
       
        mHomePage.clickElectronicsandAppliances();
        waitForPageToLoad(testData.get("validpage"));
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page");
        Assert.assertTrue(mElectronicsSnBPage.validateChatIconsavailable(),"Failed Chat Icons not available");
    }

    /*MS-520:Verify user click on presence ad user should redirect on VAP page with chat option
    */
    @Test(groups = "horizontal")
    public void validateVapRedirection(){
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);
        MElectronincsVAPPage mElectronincsVAPPage=new MElectronincsVAPPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickElectronicsandAppliances();
        waitForPageToLoad(testData.get("validpage"));
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        mElectronicsSnBPage.clickAdNumber(2);

        Assert.assertTrue(mElectronincsVAPPage.validateElectronicsVAPPage(),"Failed to Load Electronics VAP Page");
    }

    /*MS-530:Verify On MCR page chat message are displayed
    */
    @Test(groups = "horizontal")
    public void validateMCRChats(){
        MHomePage mHomePage = new MHomePage(driver);
        MMyChatandReply myChatandReply=new MMyChatandReply(driver);
        MLoginpage mLoginpage=new MLoginpage(driver);
        MMyAccountPage myAccountPage=new MMyAccountPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickMyChatandRepliesMenuIcon();
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        myAccountPage.clickmyChatAndRepliesLinkOnMyAccount();
        Assert.assertTrue(myChatandReply.isChatandReplycontainsMsgs(),"Failed to Load My Chat messages");
    }

    /*MS-537:Verify Delete Chat threads is working fine
    */
    @Test(groups = "horizontal")
    public void validateMCRChatsClickable()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MMyChatandReply myChatandReply=new MMyChatandReply(driver);
        MLoginpage mLoginpage=new MLoginpage(driver);
        MMyAccountPage myAccountPage=new MMyAccountPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickMyChatandRepliesMenuIcon();
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        myAccountPage.clickmyChatAndRepliesLinkOnMyAccount();
        Assert.assertTrue(myChatandReply.isChatandReplycontainsMsgs(), "Failed to Load My Chat messages");
        myChatandReply.clickMessagesinChat();

        try {
            Thread.sleep(6000);
            myChatandReply.chatAction("delete");
            Thread.sleep(6000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        myChatandReply.deleteChatConfirmation();
        Assert.assertTrue(myChatandReply.isMessageDeleted(), "Failed to Delete Message Pls Check");
    }

    /*MS-538:Verify v-card is connected
    */
    @Test(groups = "horizontal")
    public void validateVCard()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MMyChatandReply myChatandReply=new MMyChatandReply(driver);
        MLoginpage mLoginpage=new MLoginpage(driver);
        MMyAccountPage myAccountPage=new MMyAccountPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickMyChatandRepliesMenuIcon();
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        myAccountPage.clickmyChatAndRepliesLinkOnMyAccount();
        Assert.assertTrue(myChatandReply.isChatandReplycontainsMsgs(), "Failed to Load My Chat messages");
        myChatandReply.clickMessagesinChat();
        myChatandReply.clickShareIcon();
        myChatandReply.clickShareContact();
        Assert.assertTrue(myChatandReply.isContactShared(),"Failed to Load V Card");
    }

    /*MS-543:Verify timing is showing or not
    */
    @Test(groups = "horizontal")
    public void validateTimingofChats()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MMyChatandReply myChatandReply=new MMyChatandReply(driver);
        MLoginpage mLoginpage=new MLoginpage(driver);
        MMyAccountPage myAccountPage=new MMyAccountPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickMyChatandRepliesMenuIcon();
        mLoginpage.signIn(testData.get("emailId"), testData.get("password"));
        myAccountPage.clickmyChatAndRepliesLinkOnMyAccount();
        Assert.assertTrue(myChatandReply.isChatandReplycontainsMsgs(), "Failed to Load My Chat messages");
        myChatandReply.clickMessagesinChat();
        Assert.assertTrue(myChatandReply.chatTimings().replaceAll("[a-z,A-Z,:]","").replaceAll(" ","").matches("[0-9]+"),"Failed To Load Timings");
    }

    /*MS-544:Verify half user can chat
    */
    @Test(groups = "horizontal")
    public void validateHalfUserChats()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);
        MElectronincsVAPPage mElectronincsVAPPage=new MElectronincsVAPPage(driver);
        MMyChatandReply myChatandReply=new MMyChatandReply(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.selectCity("Bangalore");
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        waitForPageToLoad(testData.get("validpage"));
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));
        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page");
        Assert.assertTrue(mElectronicsSnBPage.validateChatIconsavailable(), "Failed Chat Icons not available");
        mElectronicsSnBPage.clickAdswithChatOption();

        mElectronincsVAPPage.clickChatonVAP();
        waitForPageToLoad(testData.get("validpage"));

        mElectronincsVAPPage.setChatDetails(testData.get("ChatMessage"), testData.get("emailId"), testData.get("profilePhNum"),testData.get("chatName"));
        waitForPageToLoad(testData.get("validpage"));

        Assert.assertTrue(myChatandReply.isShareButtonavailable(),"Failed to Load Chat Window");

    }


}