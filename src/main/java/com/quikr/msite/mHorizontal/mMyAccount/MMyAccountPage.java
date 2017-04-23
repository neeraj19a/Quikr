package com.quikr.msite.mHorizontal.mMyAccount;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 4/11/15.
 */
public class MMyAccountPage extends MPageBase
{
    public MMyAccountPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("mMYACCOUNT_DOM_FILE");


    public void clickMyAccount()
    {
        if(Mapper.waitForElementToBeVisible(domFile,"myAccountLinkOnMenu")==true)
        {
        Mapper.find(domFile, "myAccountLinkOnMenu").click();
        }
        else
        {
            logger.info("My account link on menu was not visible.");
        }
    }

    public void navigateHomepageThroughMenu()
    {
        Mapper.waitForElementToBeVisible(domFile,"homePageLinkOnMenu");
        Mapper.find(domFile,"homePageLinkOnMenu").click();
    }

    public boolean checkSuccessfulLogin()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"viewPublicProfileLink")==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return  retVal;
    }

    public boolean isMyChatsAndRepliesPresent()
    {
        boolean retVal = false;
        verticalSwipeWithCordinates(50, 90);
        if (Mapper.waitForElementToBeVisible(domFile, "myChatAndRepliesLink")==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return  retVal;
    }

    public boolean isMyAdsPresent()
    {
        boolean retVal = false;
        verticalSwipeWithCordinates(50,100);
        if (Mapper.waitForElementToBeVisible(domFile, "manageMyAdsLink")==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return  retVal;

    }

    public boolean isMyOffersPresent()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "myOffersLink")==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return  retVal;
    }

    public boolean isMyAlertsPresent()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile, "myAlertsLink")==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return  retVal;
    }

    public boolean validateMyAccountPage()
    {
        boolean finalVal = false;

        if (isElementPresent("viewProfileBtn")==true)
        {
            finalVal=true;
        }
        else
        {
            return false;
        }

        if (isElementPresent("editProfileBtn")==true)
        {
            finalVal=true;
        }
        else
        {
            return false;
        }


        if (isElementPresent("manageFreeAds")==true)
        {
            finalVal=true;
        }
        else
        {
            return false;
        }
        return finalVal;
    }

    public boolean validateMyProfilePage(String email, String name, String phonNum)
    {
        boolean finalVal = false;
        WebElement element=Mapper.find(domFile,"emailMyProfilePage");
        scrollToWebElement(element);
        if (isElementPresent("emailMyProfilePage")==true)
        {
            String em = Mapper.find(domFile,"emailMyProfilePage").getText();
            if (em.contains(email))
            {
                finalVal = true;
            }
            else
            {
                logger.info("Email is not same.");
                return false;
            }
        }
        else
        {
            return false;
        }

        WebElement element2=Mapper.find(domFile,"emailMyProfilePage");
        scrollToWebElement(element2);
        if (isElementPresent("phoneNumMyProfilePage")==true)
        {
            String ph = Mapper.find(domFile,"phoneNumMyProfilePage").getText();
            if (ph.contains(phonNum))
            {
                finalVal = true;
            }
            else
            {
                logger.info("Phone number is not the same.");
                return false;
            }
        }
        else
        {
            return false;
        }

        return finalVal;
    }

    public void clickEditMyProfile()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "editProfileBtn")==true)
        {
            Mapper.find(domFile,"editProfileBtn").click();
        }
        else
        {
            logger.info("My profile link was not clicked properly. Please check!");
        }
    }

    public void clickEditProfile()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"editMyProfile")==true) {
            logger.info("Seems <editMyprofile> is visible. Lets try to click on it....");
            if (Mapper.waitForElementToBeClickable(domFile,"editMyProfile")==true){
                logger.info("<editMyProfile> is clickable, Clicking....");
                try{
                    Thread.sleep(5000);
                }catch(Exception e){
                }
                    //swipeVertically("viewPublicProfileLink", "myAccountLabel");
                    verticalSwipeWithCordinates(200,900);
                    Mapper.find(domFile, "editMyProfile").click();
                    logger.info("Clicked....");
            }else{
                logger.info("Element was not clickable....");
            }
        }else {
            logger.info("<editMyProfile> seems is not visible. Lets try to bring it on view");
            logger.info("Swiping vertically....");
            swipeVertically("viewPublicProfileLink", "myAccountLabel");
            logger.info("Swiped....");
        }



    }

    public boolean clickPhNumProfilePage()
    {
        boolean flag=false;
        if(Mapper.waitForElementToBeVisible(domFile,"phoneNumMyProfilePage")==true)
         {
        WebElement a=Mapper.find(domFile, "phoneNumMyProfilePage");
        WebElement b = Mapper.find(domFile, "emailMyProfilePage");

        if (a!=null && b!=null) {
            flag= true;
        } else {
            flag= false;

        }

    }
        return flag;
    }

    public void clickSaveProfilePage()
    {
        WebElement element=Mapper.find(domFile,"profileUpdateButton");
        scrollToWebElement(element);
        element.click();
    }

    public void editProfile()
    {

        clickEditProfile();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(clickPhNumProfilePage(), "Check phone and email fields");
        clickSaveProfilePage();
        
    }

    public void clickRecommendedAds()
    {
        Mapper.find(domFile,"recommendedAdsLink").click();
    }

    public boolean verifyRecommAdsPageContent()
    {
        boolean finalVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"addAlertRecommAds")==true)
        {
            finalVal = true;
        }
        else
        {
            return false;
        }

        if (Mapper.waitForElementToBeVisible(domFile,"manageRecommAds")==true)
        {
            finalVal=true;
        }
        else
        {
            return false;
        }
        return finalVal;
    }

    public void clickRepliesToMyAds()
    {
        Mapper.find(domFile,"repliesToMyAdsLink").click();
    }

    public void clickOnAReplyToAnAd()
    {
        Mapper.find(domFile,"firstReplyToAd").click();
    }

    public void clickManageRecommAds()
    {
        Mapper.find(domFile,"manageRecommAds").click();
    }

    public void clickMyAlerts()
    {
        Mapper.find(domFile,"myAlertsLink").click();
    }

    /*
    Need to add check for visibility of edit button too.
     */
    public boolean verifyMyAlertsPageContents()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"unsubscribeMyAlertsPage")==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }

        if (Mapper.waitForElementToBeVisible(domFile,"editAlertsMyAlertsPage")==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public void clickAlertUnsubscribe()
    {
        Mapper.finds(domFile,"unsubscribeMyAlertsPage").get(0).click();
    }

    public void clickMyNeedIsMet()
    {
        Mapper.find(domFile, "myNeedIsMet").click();
    }

    public void submitUnsubscribeAlert()
    {
        Mapper.find(domFile,"submitButtonUnsubscribeForm").click();
    }

    public void clickMyAds()
    {
        Mapper.find(domFile,"manageMyAdsLink").click();
        Mapper.find(domFile,"activAdsLink").click();
    }

    public boolean checkMyAdsPageContents()
    {
        boolean finalVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"myAdsPageContentContainer")==true)
        {
            finalVal=true;
        }
        else
        {
            return false;
        }

        List<WebElement> adsPageContents = Mapper.finds(domFile,"myAdsPageContents");

        List<String> adsPageContentsString = new ArrayList();
        for (int i=0;i<adsPageContents.size();i++)
        {
            adsPageContentsString.add(i, adsPageContents.get(i).getText());
        }

        String content=adsPageContentsString.toString().replaceAll("\\|", " ");

        System.out.println("Content is Here" + content);
        if (content.contains("View")&&content.contains("Edit")&&content.contains("Delete"))
        {
            finalVal=true;
        }else
        {
            return false;
        }

        return finalVal;
    }

    public void clickFirstViewAdLinkAdsPage()
    {

        if(Mapper.waitForElementToBeClickable(domFile,"firstAdInMyAdsPage")==true){
            Mapper.find(domFile,"firstAdInMyAdsPage").click();
        }
    }

    public void clickFirstEditLinkAdsPage()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"myAdsPageEditLink")==true)
        {
            Mapper.find(domFile,"myAdsPageEditLink").click();
        }
        else
        {
            logger.info("Edit link not present or corresponsing xpath changed.");
        }
    }

    public boolean verifyAdEditPage()
    {
        boolean retVal=false;
        Mapper.find(domFile,"editAds").click();
        if (isElementPresent("editAdHeaders")==true)
        {
            retVal=true;
        }
        else
        {
            return false;
        }

        if (Mapper.find(domFile,"editAdHeaders").getText().contains("Edit"))
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public boolean verifyEditAdPageContent()
    {
        boolean finalVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"editAdHeaders")==true)
        {
            if (isElementPresent("adTitleTextFieldEditAd")==true)
            {
                finalVal=true;
            }
            else
            {
                logger.info("Ad title text field not present.");
                return false;
            }

            if (isElementPresent("adDescTextFieldEditAd")==true)
            {
                finalVal=true;
            }
            else
            {
                logger.info("Ad desc text field not present.");
                return false;
            }

            WebElement updateButton = Mapper.find(domFile,"updateAdButtonEditAd");
            scrollToWebElement(updateButton);

            if (isElementPresent("updateAdButtonEditAd")==true)
            {
                finalVal=true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            logger.info("Edit ad headers not present!!!");
        }

        return finalVal;
    }

    public void clickTitleTextFieldEditAd()
    {
        Mapper.find(domFile,"adTitleTextFieldEditAd").click();
    }

    public void clickUpdateAdEditAd()
    {
        Mapper.find(domFile,"updateAdButtonEditAd").click();
    }

    public void clickLoadMoreDetailsEditAd()
    {
        Mapper.find(domFile,"loadMoreAdDetailEditAd").click();
    }

    public boolean verifyLoadMoreAdDetailEditAd()
    {
        boolean finalVal = true;
        if (Mapper.waitForElementToBeVisible(domFile,"EditAdLoadMoreDetailContainer")==true)
        {
            if (Mapper.find(domFile,"EditAdLoadMoreDetailContainer").getAttribute("style").equals("display: block;"))
            {
                finalVal=true;
            }
            else
            {
                logger.info("The style attribute of load more ad detail container is not as expected.");
                return false;
            }
        }
        else
        {
            logger.info("load more ad details container is not visible.");
            return false;
        }
        return finalVal;
    }


    public void clickAddAlertRecommAds() {
        Mapper.find(domFile, "addAlertRecommAds").click();
    }

    public void clickConvertToPremiumMyAds(){
        Mapper.waitForElementToBeClickable(domFile,"manageMyAdsLink");
        Mapper.find(domFile,"manageMyAdsLink").click();
        Mapper.waitForElementToBeVisible(domFile, "activeAdManagement");
        Mapper.find(domFile, "activeAdManagement").click();
        Mapper.waitForElementToBeVisible(domFile,"myAdsConvertToPremium");
        Mapper.find(domFile, "myAdsConvertToPremium").click();
    }

    public void clickmyChatAndRepliesLinkOnMyAccount(){
        if(Mapper.waitForElementToBeClickable(domFile,"myChatAndRepliesLinkOnMenu")==true){
            Mapper.find(domFile,"myChatAndRepliesLinkOnMenu").click();
        }
    }

    public void clickSigninSignupBtn(){
        if (Mapper.waitForElementToBeVisible(domFile, "signinSignupButton")==true){
            Mapper.find(domFile,"signinSignupButton").click();
        }else{
            logger.info("Signin signup button was not displayed.");
        }
    }

    public void clickMenuFromAccountPage(){
        if (Mapper.waitForElementToBeVisible(domFile, "menutBtnAccountPage")==true){
            Mapper.find(domFile,"menutBtnAccountPage").click();
        }else{
            logger.info("Menu btn in account page was not visible.");
        }
    }

    public void clickManageAlertsFromAccountPage(){
        if (Mapper.waitForElementToBeVisible(domFile,"manageAlertsAccountPageMenu")==true){
            Mapper.find(domFile,"manageAlertsAccountPageMenu").click();
        }else{
            logger.info("MAnage alerts link in account page menu was not visible.");
        }
    }

    public void clickManageFreeAds(){
        if (Mapper.waitForElementToBeVisible(domFile,"manageFreeAds")==true){
            Mapper.find(domFile,"manageFreeAds").click();
        }else{
            logger.info("Manage free ads button was not available. Please check!");
        }
    }

    public void clickEditAlert(){
        Mapper.find(domFile,"editAlertsMyAlertsPage").click();
    }
}
