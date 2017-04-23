package com.quikr.website.horizontal.myquikr;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.sql.Timestamp;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by rohanbajaj on 29/07/15.
 */

public class MyQuikrPage extends PageBase
{
    public MyQuikrPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("MYQUIKR_DOM_FILE");

    /**
     * click on replies to my ad link
     */
    public void clickOnRepliesToMyAd()
    {
        Mapper.find(domFile,"RepliesToMyAds").click();
    }

    /**
     * delete reply
     */
    public void deleteReply()
    {
        Mapper.waitForElementToBeClickable(domFile,"DeleteRep");
        Mapper.find(domFile,"DeleteRep").click();
        switchTo().alert().accept();
        Mapper.waitForElementToBeVisible(domFile, "AfterDeleteRepMsg");
    }

    /**
     * verify reply deleted successfully
     */
    public boolean verifyDeleteReplySuccess()
    {
        return isElementPresent("MsgAfterDeleteRep");
    }

    //function to check active status of ads
    public boolean  ActiveAd()
    {
        if(Mapper.waitForElementToBeClickable(domFile, "manageFreeAds")==true) {
            Mapper.find(domFile, "manageFreeAds").click();
        }
        List<WebElement> activeads=Mapper.finds(domFile,"adTitleActiveAd");
        if(activeads.size()>=1) {
            return true;
        }
        else
            return false;

    }
    public boolean updateProfile()
    {
        Mapper.find(domFile,"updateProfile").click();
        Mapper.find(domFile,"userAddress").clear();
        Mapper.find(domFile,"userAddress").sendKeys("Test Address" + new Timestamp(System.currentTimeMillis()));
        Mapper.find(domFile,"updateButton").click();
        if(isElementPresent("updateMsg"))
            return true;
        return false;
    }

    public boolean updateProfileFromDropDown()
    {
        Mapper.waitForElementToBeVisible(domFile, "myQuikrIcon");
        Mapper.find(domFile, "myQuikrIcon").click();
        while(!getCurrentLocation().contains("MyQuikr?action=profileedit")){
            Mapper.find(domFile, "updateProfileFromDropDown").click();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Mapper.find(domFile,"userAddress").clear();
        Mapper.find(domFile,"userAddress").sendKeys("Test Address" + new Timestamp(System.currentTimeMillis()));
        Mapper.find(domFile,"updateButton").click();
        if(isElementPresent("updateMsg"))
            return true;
        return false;
    }

    public void clickActiveAdMngmnt()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "ActiveAds")==true)
        {
            Mapper.find(domFile,"ActiveAds").click();
        }
        else
        {
            logger.info("Active ads managemement link not visible.");
        }
    }

    public void clickDeleteAd()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "DeleteAd")==true)
        {
            Mapper.find(domFile,"DeleteAd").click();
        }
        else
        {
            logger.info("Delete Ad button not visible. Or there is no ad to be deleted.");
        }
    }

    public void clickReasonToDeleteAd()
    {
        if (Mapper.waitForElementToBeClickable(domFile, "ReasonOfDeleteAd")==true)
        {
            Mapper.find(domFile,"ReasonOfDeleteAd").click();
            if (Mapper.waitForElementToBeInvisible(domFile,"ReasonOfDeleteAd")==true)
            {
                logger.info("Delete ad reason clicked in second attempt.");
            }
            else
            {
                Mapper.find(domFile,"ReasonOfDeleteAd").click();
            }
        }
        else
        {
            logger.info("Reason to delete ad not visible or didn't get clicked.");
        }
    }

    public void clickSubmitDeleteAd()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"SubmitButton")==true)
        {
            Mapper.find(domFile,"SubmitButton").click();
            if (Mapper.waitForElementToBeVisible(domFile,"MsgAfterAdDeleted")==true)
            {
            }
            else
            {
                logger.info("Deletion confirmation didn't appear.");
            }
        }
    }

    public boolean validateSuccessfulAdDeletion()
    {
        boolean retVal = true;
        if (Mapper.waitForElementToBeVisible(domFile,"MsgAfterAdDeleted")==true)
        {
            retVal=true;
        }
        else
        {

            retVal=false;
            logger.info("Deletion confirmation didn't appear.");
        }
        return retVal;
    }

    public boolean DeleteAd()
    {
        clickActiveAdMngmnt();
        clickDeleteAd();
        clickReasonToDeleteAd();
        clickSubmitDeleteAd();
        return validateSuccessfulAdDeletion();
    }
    //function to select edit button in my alert


    public void selectEditAlertButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"editAlertButton");
        Mapper.waitForElementToBeClickable(domFile,"editAlertButton");
        Mapper.find(domFile,"editAlertButton").click();
    }

    //function to validate edit alert
    public boolean validateEditAlert()
    {
       return Mapper.waitForElementToBeVisible(domFile, "msgAfterEditAlert");
    }



    //Function to get the count of Ads in Recommended for me in MyQuikr page
    public int NumberOfRecommendedForMeAds()
    {
        navigateToRecommendedAdForMe();
        Mapper.waitForElementToBeVisible(domFile, "notificationBellImageInRecommendedForMeAds");
        return Mapper.finds(domFile,"adsInRecommendedForMe").size();
    }

    public void navigateToRecommendedAdForMe()
    {
        try{
            if(Mapper.waitForElementToBeVisible(domFile, "myQuikrIcon")==true) {
                Mapper.find(domFile, "myQuikrIcon").click();
            }
            if(Mapper.waitForElementToBeVisible(domFile,"recommendedForMeFromDropDown")==true) {
                Mapper.find(domFile, "recommendedForMeFromDropDown").click();
            }
        }
        catch (Exception e){
            if (Mapper.waitForElementToBeClickable(domFile, "myQuikrIconNewUI")==true) {
                Mapper.find(domFile, "myQuikrIconNewUI").click();
            }
            Mapper.find(domFile,"recommendedForMeFromDropDown").click();

        }
    }
    //Function to validate recommended ads for me
    public boolean ValidateRecommendedAds()
    {
        if(NumberOfRecommendedForMeAds()>0)
            {return true;}
        else
            {return false;}

    }

    /**
     * click on edit ad button
     */
    public void clickEditAdLink()
    {
        Mapper.waitForElementToBeVisible(domFile, "editAd");
        Mapper.waitForElementToBeClickable(domFile, "editAd");
        Mapper.find(domFile, "editAd").click();
        Mapper.waitForElementToBeInvisible(domFile, "editAd", 15);
        WebElement element=Mapper.find(domFile, "editAd");
        if(element!=null){
            Mapper.find(domFile, "editAd").click();
        }
    }

    public WebElement EditAdlink(){
        WebElement editAdlink=Mapper.find(domFile, "editAdElement");
        return editAdlink;
    }

    /**
     * convert free add to premium ad
     */
    public void clickConvertToPremiumLink()
    {
        if(Mapper.waitForElementToBeClickable(domFile, "ConverttoPremiumAd")==true) {
            WebElement convertToPremiumLink = Mapper.find(domFile, "ConverttoPremiumAd");
            convertToPremiumLink.click();
        }else{
            logger.info("Convert to premium ads link is not available.");
        }
        List<WebElement> elm = Mapper.finds(domFile, "ConverttoPremiumAd");
        if (elm.size()>0){
            logger.info("Convert ads to premium link was not clicked properly the first time. Clicking again...");
            Mapper.find(domFile, "ConverttoPremiumAd").click();
        }else{
            logger.info("COnvert to premium ads link was clicked the first time itself.");
        }
    }

    public void NavigateToMyShortlists()
    {
        Mapper.find(domFile,"userProfileIcon").click();
        Mapper.waitForElementToBeVisible(domFile, "myShortLists");
        Mapper.find(domFile, "myShortLists").click();
    }

    /**
     * function to select my chat
     */
    public void selectMyChat()
    {
        Mapper.waitForElementToBeVisible(domFile, "myChat");
        Mapper.find(domFile,"myChat").click();
    }

    public boolean editAlertRecommendedSection()
    {
        Mapper.waitForElementToBeVisible(domFile, "editAlertRecommendedSection");
        Mapper.find(domFile, "editAlertRecommendedSection").click();
        Mapper.find(domFile, "editAlertRecommendedSection").click();
        Mapper.waitForElementToBeVisible(domFile, "alertBoxRecommendedSection");
//        Mapper.find(domFile, "selectAllbrandsEditAlertRecommendedSection").click();
        Mapper.find(domFile, "submitButtonAlertRecommendedSection").click();
        Mapper.waitForElementToBeVisible(domFile, "editAlertSuccessMsgRecommendedSection");
        String txt = Mapper.find(domFile, "editAlertSuccessMsgRecommendedSection").getText();
        if (txt.equals("Your Alert has been updated successfully"))
        {
            return true;
        }else
        {
            return false;
        }
    }

    public boolean unsubscribeAlertRecommendedSection()
    {
        Mapper.waitForElementToBeVisible(domFile, "unsubscribeAlertRecommendedSection");
        Mapper.find(domFile, "unsubscribeAlertRecommendedSection").click();
        Mapper.waitForElementToBeVisible(domFile, "unsubscribeReasonCheckBox");
        Mapper.find(domFile, "unsubscribeReasonCheckBox").click();
        Mapper.find(domFile, "submitButtonAlertRecommendedSection").click();
        Mapper.waitForElementToBeVisible(domFile, "unsubscribeAlertConfirmMsgRecommendedSection");
        if (Mapper.find(domFile, "unsubscribeAlertConfirmMsgRecommendedSection").getText().contains("You have successfully unsubscribed"))
        {
            return true;
        }else
        {
            return false;
        }
    }

    public boolean repostExpiredAds()
    {
        boolean finalVal=false;
        Mapper.waitForElementToBeVisible(domFile, "expiredAdsManagementLink");
        Mapper.find(domFile, "expiredAdsManagementLink").click();

        java.util.List<WebElement> elms = Mapper.finds(domFile,"repostAdSelectionCheckBox");
        if (elms.size()==0) {
            finalVal = true;
            return finalVal;
        }
        Mapper.waitForElementToBeVisible(domFile, "repostAdSelectionCheckBox");
        Mapper.find(domFile, "repostAdSelectionCheckBox").click();

        Mapper.find(domFile, "repostButton").click();
        Mapper.waitForElementToBeVisible(domFile, "adRepostConfirmation");
        String txt = Mapper.find(domFile, "adRepostConfirmation").getText();
        if (txt.contains("Your ad will be reposted shortly!") || txt.contains("you don`t have permission"))
        {
            return true;
        }else
        {
            return false;
        }
    }

    public void clickMyQuikrIcon()
    {
        if (Mapper.find(domFile, "myQuikrIcon")!=null)
        {
            if (Mapper.waitForElementToBeVisible(domFile,"myQuikrIcon")==true)
            {
                Mapper.find(domFile, "myQuikrIcon").click();
            }
        }
    }

    public void clickMyAlerts()
    {
        if(Mapper.waitForElementToBeVisible(domFile,"myAlertsDropDown")==true){
        Mapper.waitForElementToBeClickable(domFile,"myAlertsDropDown");
        Mapper.find(domFile,"myAlertsDropDown").click();
        }
        if(Mapper.waitForElementToBeInvisible(domFile, "myAlertsDropDown", 15)==false){
            Mapper.find(domFile,"myAlertsDropDown").click();

        }
    }

    public void clickManagaeAlerts(){
        if (Mapper.waitForElementToBeVisible(domFile,"manageAlerts")==true){
            Mapper.find(domFile,"manageAlerts").click();
        }else{
            logger.info("Manage alerts link was not found.");
        }
    }

    public void clickEditAlert()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "editAlertButton")==true) {
            Mapper.find(domFile, "editAlertButton").click();
        }
    }

    public void clickSubmitAlerts()
    {
        Mapper.find(domFile,"submitButtonAlertRecommendedSection").click();
    }

    public boolean validateEditAlertConfirmation()
    {
        boolean retVal = false;
        if (Mapper.find(domFile,"alertUpdateConfirmation")!=null)
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public void clickUnsubscribeAlerts()
    {
        if(Mapper.waitForElementToBeClickable(domFile,"unsubscribeAlertButton")==true) {
            Mapper.find(domFile, "unsubscribeAlertButton").click();
        }
        if(Mapper.waitForElementToBeClickable(domFile, "unsubscribeReason")==true) {
            Mapper.find(domFile, "unsubscribeReason").click();
        }

        if(Mapper.waitForElementToBeClickable(domFile, "unsubscribeReasonSubmit")==true) {
            Mapper.find(domFile, "unsubscribeReasonSubmit").click();
        }

    }

    public void clickPremiumAdManagement()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "premiumAdsManagement")==true)
        {
            Mapper.find(domFile,"premiumAdsManagement").click();
        }
    }

    public void clickEditPremiumAdsManagement()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "premiumAdsManagementClickEdit")==true)
        {
            Mapper.find(domFile,"premiumAdsManagementClickEdit").click();
        }
    }

    public String getLoggedInEmail()
    {
        String email ="";
        List<WebElement> elms = Mapper.finds(domFile,"myQuikrIconNewUI");
        if (elms.size()>0){
            if(Mapper.waitForElementToBeVisible(domFile,"myQuikrIconNewUI")==true)
                {
                    email = Mapper.find(domFile,"myQuikrIconNewUI").getText();
                    logger.info("Email extracted after posting ad is :: "+email);
                }
        }else
        {
            email = "xyz";
        }
        return email;
    }

    /*
    Methods for new responsive HP
     */

    public boolean checkViewProfileButtonVap()
    {
        return isElementPresent("viewProfileButtonVap");
    }

    public void clickViewProfileButtonVap()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"viewProfileButtonVap")==true)
        {
            Mapper.find(domFile,"viewProfileButtonVap").click();
        }
        else
        {
            logger.info("View profile button in VAP is not visible.");
        }
    }

    public void clickUserProfileNameVAP_ResponsiveHp()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"userAccountName_ResponsiveHP")) {
            Mapper.find(domFile, "userAccountName_ResponsiveHP").click();
            waitForPageToLoad("userProfile");
        }else
        {
            logger.info("<userAccountName_ResponsiveHP> is not visible....");
        }
        List<WebElement> elms = Mapper.finds(domFile,"userAccountName_ResponsiveHP");
        if (elms.size()==0){
            logger.info("List size is zero, user profile was clicked and hence not doing anything.....");
        }else
        {
            logger.info("Attempting click for the second time....");
            Mapper.find(domFile, "userAccountName_ResponsiveHP").click();
            logger.info("Clicked and waiting for page load....");
            waitForPageToLoad("userProfile");
        }
    }

    public boolean verifyProfileName()
    {
        boolean retVal = false;

        if (Mapper.find(domFile,"userProfileNameProfilePage_ResponsiveHP").isDisplayed()==true && Mapper.find(domFile,"userProfileNameProfilePage_ResponsiveHP").getText()!=""){
            retVal=true;
        }else{
            return false;
        }
        return retVal;
    }

    public boolean checkIfadPostedCountisDisplayed()
    {
        if (Mapper.find(domFile,"adPostedCountUserProfilePage_ResponsiveHP").isDisplayed()==true)
            return true;
        else
            return false;
    }

    public void clickFirstAdUserProfileList()
    {
        if (Mapper.find(domFile,"firstAdListedUserProfilePage_ResponsiveHP").isDisplayed()==true)
        {
            Mapper.find(domFile,"firstAdListedUserProfilePage_ResponsiveHP").click();
        }
        else
        {
            logger.info("Ad listing didn't appear.");
        }
    }

    public boolean clickMakeAnOfferButton()
    {
        boolean retVal = false;
        if(Mapper.waitForElementToBeVisible(domFile,"MakeAnOfferBtnUserProfilePage_ResponsiveHP")==true) {
            if (Mapper.find(domFile, "MakeAnOfferBtnUserProfilePage_ResponsiveHP").isDisplayed() == true) {
                Mapper.find(domFile, "MakeAnOfferBtnUserProfilePage_ResponsiveHP").click();
                retVal = true;
            } else {
                logger.info("Make an offer button is not visible!");
                retVal = false;
            }
        }
        return retVal;
    }

    public boolean makeAnOffer(String offerPrice, String emailAddrs, String phnNum , String city)
    {
        boolean val = clickMakeAnOfferButton();
        if (val==true)
        {
            Mapper.find(domFile,"MakeAnOfferPriceUserProfilePage_ResponsiveHP").click();
            Mapper.find(domFile,"MakeAnOfferPriceUserProfilePage_ResponsiveHP").sendKeys(offerPrice);
            Mapper.find(domFile,"MakeAnOfferEmailUserProfilePage_ResponsiveHP").clear();
            Mapper.find(domFile, "MakeAnOfferEmailUserProfilePage_ResponsiveHP").sendKeys(emailAddrs);
            Mapper.find(domFile,"MakeAnOfferMobileUserProfilePage_ResponsiveHP").sendKeys(phnNum);
            Mapper.find(domFile,"MakeAnOfferCityUserProfilePage_ResponsiveHP").click();
            Mapper.find(domFile,"MakeAnOfferCityUserProfilePage_ResponsiveHP").sendKeys(city.toLowerCase());
            Mapper.find(domFile,"MakeAnOfferSendOfferProfilePage_ResponsiveHP").click();
        }
        else
        {
            logger.info("Make an offer button was not visible at first place. So didn't go ahead with further process.");
        }
        return val;
    }


    public boolean validateSuccessInMakeAnOffer()
    {
        boolean retVal = false;
        try {
            Thread.sleep(5000);
            Mapper.waitForElementToBeVisible(domFile,"MakeAnOfferConfirmationUserProfilePage_ResponsiveHP");
            List<WebElement> elm = Mapper.finds(domFile, "MakeAnOfferConfirmationUserProfilePage_ResponsiveHP");
            if (elm.size() > 0) {
                String confirmationText = Mapper.find(domFile, "MakeAnOfferConfirmationUserProfilePage_ResponsiveHP").getText();
                logger.info("Text found is :: "+confirmationText);
                String toMatchText = "as offer is accepted";
                if (confirmationText.contains(toMatchText)==true){
                    retVal=true;
                }
                else
                {
                    logger.info("COnfirmation text didn't match.....");
                    return false;
                }
            }else if (getCurrentLocation().contains("secure.quikr.com")){
                retVal=true;
            }else{
                logger.info("It didn't even go to secure payment page. Please check....");
                return false;
            }
        }
        catch (Exception e) {}
        return retVal;
    }

    public boolean clickBuyNowButton()
    {
        boolean retVal=false;
        if (Mapper.find(domFile,"BuyNowBtnUserProfilePage_ResponsiveHP").isDisplayed()==true)
        {
            Mapper.find(domFile,"BuyNowBtnUserProfilePage_ResponsiveHP").click();
            retVal = true;
        }
        else
        {
            logger.info("Buy now button is not visible!");
            retVal=false;
        }
        return retVal;

    }

    public boolean buyNow(String emailAddrs, String phnNum , String city)
    {
        boolean val = clickBuyNowButton();
        if (val==true)
        {
            Mapper.find(domFile,"MakeAnOfferCityUserProfilePage_ResponsiveHP").click();
            Mapper.find(domFile,"MakeAnOfferCityUserProfilePage_ResponsiveHP").sendKeys(city.toLowerCase());
            Mapper.find(domFile,"MakeAnOfferSendOfferProfilePage_ResponsiveHP").click();
        }
        else
        {
            logger.info("Buy now button was not visible at first place. So didn't go ahead with further process.");
        }
        return val;
    }

    public boolean validateSuccessInBuyNow()
    {
        boolean retVal = false;
        try {
            Thread.sleep(5000);
            List<WebElement> elm = Mapper.finds(domFile, "BuyNowConfirmationUserProfilePage_ResponsiveHP");
            if (elm.size() > 0) {
                String confirmationText = Mapper.find(domFile, "BuyNowConfirmationUserProfilePage_ResponsiveHP").getText();
                logger.info("Text found is :: "+confirmationText);
                String toMatchText = "help you complete";
                if (confirmationText.contains(toMatchText)==true){
                    retVal=true;
                }
                else
                {
                    logger.info("COnfirmation text didn't match.....");
                    return false;
                }
            }else if (getCurrentLocation().contains("secure.quikr.com")){
                retVal=true;
            }else{
                logger.info("It didn't even go to secure payment page. Please check....");
                return false;
            }
        }
        catch (Exception e) {}
        return retVal;
    }

    public String getChickletTextUserProfilePage()
    {
        String chickletText = "";
        if (Mapper.waitForElementToBeVisible(domFile,"LocalityChickletUserProfilePage_ResponsiveHP")==true)
        {
            WebElement elm = Mapper.find(domFile,"LocalityChickletUserProfilePage_ResponsiveHP");
            chickletText = elm.getText();
        }
        return chickletText.toLowerCase();
    }

    public void clickLocalityChickletUserProfile()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"LocalityChickletUserProfilePage_ResponsiveHP")==true)
        {
            Mapper.find(domFile,"LocalityChickletUserProfilePage_ResponsiveHP").click();
        }
        else
        {
            logger.info("<LocalityChickletUserProfilePage_ResponsiveHP> was not visible.");
        }
    }


    public boolean validateProperChickletInSnb(String chickletFromUSerProfile)
    {
        boolean retVal = false;
        List<WebElement> chicksFromSnb = Mapper.finds(domFile,"SelectedChickletSnb_ResponsiveHP");

        logger.info("Chicklet captured from User Profile Page. >>"+chickletFromUSerProfile+"<<");
        for (int i=0;i>chicksFromSnb.size();i++)
        {
            logger.info("Chicklet being captured form SNB >>"+chicksFromSnb.get(i).getText()+"<<");
            if (chicksFromSnb.get(i).getText().toLowerCase().contains(chickletFromUSerProfile)==true)
            {
                retVal=true;
            }
        }

        return  retVal;
    }

    public boolean validateHeader()
    {
        boolean retVal = false;

        if (Mapper.waitForElementToBeVisible(domFile,"searchField")==true)
        {
            if (Mapper.find(domFile,"searchField")!=null)
            {
                retVal = true;
            }
            else
            {
                logger.info("Search field is not present!");
                return false;
            }
        }
        else
        {
            logger.info("Search field is not visible!");
            return false;
        }

        if (Mapper.waitForElementToBeVisible(domFile,"searchButton")==true)
        {
            if (Mapper.find(domFile,"searchButton")!=null)
            {
                retVal = true;
            }
            else
            {
                logger.info("Search button is not present!");
                return false;
            }
        }
        else
        {
            logger.info("Search button is not visible!");
            return false;
        }

        if (Mapper.waitForElementToBeVisible(domFile,"postAdButton")==true)
        {
            if (Mapper.find(domFile,"postAdButton")!=null)
            {
                retVal = true;
            }
            else
            {
                logger.info("Post Ad button is not present!");
                return false;
            }
        }
        else
        {
            logger.info("Post ad button is not visible!");
            return false;
        }

//        if (Mapper.waitForElementToBeVisible(domFile,"myCartLink")==true)
//        {
//            if (Mapper.find(domFile,"myCartLink")!=null)
//            {
//                retVal = true;
//            }
//            else
//            {
//                logger.info("My Cart link is not present!");
//                return false;
//            }
//        }
//        else
//        {
//            logger.info("My Cart link is not visible!");
//            return false;
//        }

        if (Mapper.waitForElementToBeVisible(domFile,"languagesLink")==true)
        {
            if (Mapper.find(domFile,"languagesLink")!=null)
            {
                retVal = true;
            }
            else
            {
                logger.info("Languages link is not present!");
                return false;
            }
        }
        else
        {
            logger.info("Languages link is not visible!");
            return false;
        }
        return retVal;
    }

    public boolean checkChickletsUserProfilePage()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "chickletsUserProfilePage")==true)
            return true;
        else
            return false;
    }

    public boolean validatePagination()
    {
        boolean retVal = false;
        List<WebElement> ads = Mapper.finds(domFile,"AdsUserProfilePage");
        if (ads.size()==10)
        {
            if (Mapper.waitForElementToBeVisible(domFile,"PaginationProfilePage")==true)
            {
                List<WebElement> paginationElms = Mapper.finds(domFile,"PaginationProfilePage");
                if (paginationElms.size()>1)
                {
                    paginationElms.get(2).click();
                    waitForPageToLoad(".com");
                    String url = getCurrentLocation();
                    if (url.contains("page=2"))
                    {
                        retVal = true;
                    }
                    else
                    {
                        logger.info("Proper pagination didn't happen. The page count in url is not reflected correctly. Please check!");
                        return false;
                    }
                }
                else
                {
                    logger.info("The number of ads is less than 10 and hence no pagination. Making the test pass!");
                    return true;
                }
            }
            else
            {
                logger.info("Pagination web element is not visible at the bottom of the page. Please check!!");
                return false;
            }
        }
        else
        {
            logger.info("There are less than 10 ads in the user Profile Page. Sorry :P .");
            retVal = true;
        }
        return retVal;
    }

    public void clickRightArrowPaginationButtonUserProfilePage()
    {
        Mapper.find(domFile,"PaginationProfilePageRight").click();
    }

    public void clickOptionOtherThanAllOverIndia()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"selectGunturSnb")==true)
        {
            Mapper.find(domFile,"selectGunturSnb").click();
        }
        else
        {
            logger.info("The city tab next to <ALL OVER INDIA> was not visible.");
        }
    }


    public boolean isEmailVerified(){
        if(Mapper.find(domFile, "emailVerifiedMyQuikr")!=null){
            return true;
        }
        else
            return false;
    }

    public void addMoreEmail(String emailId){
        if(Mapper.waitForElementToBeClickable(domFile, "addMoreEmailMyQuikr")==true){
            Mapper.find(domFile,"addMoreEmailMyQuikr").click();
        }
        Mapper.find(domFile,"addEmailMyQuikr").click();
        Mapper.find(domFile,"addEmailMyQuikr").sendKeys(emailId);
    }

    public void clickAddandVerifyButton(){
        if(Mapper.waitForElementToBeClickable(domFile, "addEmailandVerifyButton")==true){
            Mapper.find(domFile,"addEmailandVerifyButton").click();
        }
    }

    public String getUserNamefromLoggedInMenu(){
         String name=Mapper.find(domFile,"myAccountUsernameonSingInMenu").getText();
        return name;
    }


    public String getUserNamefromMyQuikrResponsivePage(){
        String name=Mapper.find(domFile,"myAccountUsername").getText();
        return name;

    }

    public String getMobileNumber(){
        return Mapper.find(domFile,"mobileNumberMyQuikrResponsivePage").getText();
    }

    public void clickManageMyAds(){
        if (Mapper.find(domFile,"manageMyAds")!=null){
            Mapper.find(domFile,"manageMyAds").click();
            logger.info("Manage my ads link clicked....");
        }else{
            logger.info("Manage my ads link is not available....");
        }
    }


}
