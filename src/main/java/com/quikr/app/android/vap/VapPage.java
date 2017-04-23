package com.quikr.app.android.vap;

import com.quikr.app.android.AppAndroidPageBase;
import com.quikr.app.android.Constants.MobileConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 17/8/15.
 */
public class VapPage extends AppAndroidPageBase {
    public VapPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ANDROID_VAP_DOM_FILE");

    public String AdTitle()
    {
        String adTitle = Mapper.find(domFile, "VapAdTitle").getText().toString();
        return adTitle;
    }

    /**
     * function to select rePost ad in vap
     */
    public void clickOnRePostAdButtonAtVap() {
        Mapper.waitForElementToBeVisible(domFile, "rePostAdButtonAtVap");
        Mapper.find(domFile, "rePostAdButtonAtVap").click();
    }

    /**
     * function to select yes or cancel button in rePost ad popup
     */
    public void selectChoiceInRePostAdPopUp() {
        Mapper.waitForElementToBeVisible(domFile, "choiceOptionSelected");
        Mapper.find(domFile, "choiceOptionSelected").click();
    }

    /**
     * function for the validation of rePost ad
     */
    public boolean validationRePostAd() {
        return isElementPresent("Success msg pop of rePost ad");
    }

    /**
     * function to validate role for offer ad in jobs subcategory
     */
    public boolean validateJobRoleForOfferAd() {

        if (isElementPresent("roleName") && (isElementPresent("apply") || isElementPresent("chatNow"))) {
            return true;
        } else if (!isElementPresent("roleName")) {
            Mapper.scroll("roleName");
            return (isElementPresent("roleName") && (isElementPresent("apply") || isElementPresent("chatNow")));
        }

        return false;
    }

    /**
     * function to wait for element to be visible on vap
     */
    public void waitForTitleToBeVisibleAtVap()
    {
        if (!isElementPresent("vapAdTitle"))
        {
            navigateBack();
        }
        Mapper.waitForElementToBeVisible(domFile, "vapAdTitle");
    }

    /**
     * function to validate role for wanted ads in jobs subcategory
     */
    public boolean validateJobRoleForWantedAds() {
        //        if(isElementPresent("role")&&(isElementPresent("reply") || isElementPresent("chatNow")))
        //        {
        //            return true;
        //        }
        //        else if(!isElementPresent("role"))
        //        {
        //            Mapper.scroll("role");
        //     return ((isElementPresent("reply") || isElementPresent("chatNow")));
        //      }

        return ((isElementPresent("apply")));

    }

    /**
     * function validate apply button not  present for other category except job
     */
    public boolean validateApplyButtonForOtherCategory() {
        return (!(isElementPresent("apply")));
    }

    /**
     * function to click on apply button on Vap
     */
    public void selectApplyButtonOnVap()
    {
        Mapper.waitForElementToBeVisible(domFile,"jobsVapAdTitle");
        Mapper.scroll("Apply");
        Mapper.find(domFile, "jobsApply").click();
    }

    /**
     * function to validate vap screen after click on cancel button on apply page pop up
     */
    public boolean validateVapAfterClickOnCancelFromApplyPagePopUp() {
        return ((isElementPresent("jobsApply") || isElementPresent("chatNow")));
    }

    /**
     * function to check all vap page options
     *
     * @return
     */
    public boolean validateAllOptionsOnVapForJob()
    {
        Mapper.waitForElementToBeVisible(domFile,"jobsApply");
        Mapper.scroll("Apply");
        return (isElementPresent("jobsApply") && isElementPresent("JobsShortlist") && isElementPresent("shareButton") && isElementPresent("call"));
    }

    /**
     * function to click on share button on vap
     */
    public void selectShareButton() {
        Mapper.find(domFile, "shareButton").click();
    }

    /**
     * function to validate share button functionality
     */
    public boolean validateShareButtonFunctionality() {
        return((Mapper.find(domFile,"shateAdTitle").getText()).equalsIgnoreCase("Share App"));
    }

    /**
     * function to click on back button
     */
    public void selectBackButtonOnVap() {

        Mapper.waitForElementToBeVisible(domFile,"backButton");
        Mapper.find(domFile, "backButton").click();
    }


    /**
     * function to select app to share option
     */
    public void selectAppToShare()
    {
        Mapper.scroll("appToShareOption");
        Mapper.waitForElementToBeVisible(domFile, "appToShareOption");
        Mapper.find(domFile, "appToShareOption").click();
    }

    /**
     * function to click on vap chat now button
     */
    public void selectChatNowButton() {
        Mapper.find(domFile, "chatNow").click();
    }



    /**
     * function for chat input
     */
    public void sendChatInput(String inputText) {
        Mapper.find(domFile, "chatInputText").sendKeys(inputText);
    }

    /**
     * function to click on chat send button
     */
    public void selectChatSendButton() {
        Mapper.find(domFile, "chatSendButton").click();
    }

    /**
     * function to validate chat
     */
    public boolean validateChat() {
        return isElementPresent("sent");
    }

    /**
     * validate inspected Ads On Vap
     */
    public boolean validateInspectedOnVAP() {
        if (isElementPresent("InspectedOnVAp")& isElementPresent("noFikarReport"))
            return true;
        else
            return false;
    }

    /**
     * click on nofikar report on VAp
     */
    public void clickOnNoFikarReport()
    {
        Mapper.waitForElementToBeVisible(domFile, "noFikarReport");
        Mapper.find(domFile, "noFikarReport").click();
    }

    /**
     * ckeck all the fields of carnation report
     */
    public boolean validateCarnationField(String NofikarOptions) throws  Exception
    {
        //Storing all Elements on NO fikar options in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "cartantiOptions");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options)
        {
            list.add(e.getText());
        }
        Thread.sleep(2000);
        Mapper.scroll(NofikarOptions);
        List<WebElement> options1 = Mapper.finds(domFile, "cartantiOptions");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : options1)
        {
            list1.add(e.getText());
        }
        //        for(String obj1:list1)
        //            System.out.println(obj1);
        //        System.out.println("final Concatenated string");

        //Concatenating two lists

        List<String> finallist = new ArrayList<String>(list);
        finallist.addAll(list1);
        //        for(String obj2:finallist)
        //            System.out.println(obj2);
        //        System.out.println("final Concatenated string with no dups");
        //Removing Duplicates from strings
        HashSet hs = new HashSet();
        hs.addAll(finallist);
        finallist.clear();
        finallist.addAll(hs);
        for (String obj3 : finallist)
            System.out.println(obj3);
        //check if all options are present

        int flag = 0;
        for (int i = 0; i < finallist.size(); i++)
        {

            for (int j = 0; j < MobileConstants.NoFikarOptions.size(); j++)
            {
                if (finallist.get(i).equals(MobileConstants.NoFikarOptions.get(j)))
                {
                    flag = 1;
                    System.out.println(finallist.get(i) + MobileConstants.NoFikarOptions.get(j));
                    break;
                }
            }
        }
        System.out.println(flag);
        if (flag==1)
            return true;
        else return false;
    }
    /**
     * validate reply now for offline users and Chat now for ONline uers appear on vap
     */
    public boolean validateReplyOrChatButtonOnVAP() {
        return  (isElementPresent("reply") || isElementPresent("chatNow"));
    }
    /**
     * click on user profile on vap
     */
    public void clickOnViewProfileOnVap()
    {
        Mapper.waitForElementToBeVisible(domFile,"vapMobilesAndTAbsPrice");
//        Mapper.VerticalSwipe(domFile,"starButton","vapMakeAnOffer");
//        Mapper.waitForElementToBeVisible(domFile,"userProfile");
        Mapper.scroll("View Profile");
        Mapper.find(domFile,"userProfile").click();
    }
    /**
     * validate user Profile
     */
    public boolean validateUserProfile()
    {
        return isElementPresent("validateuserProfile");
    }

    /**
     * validate VAP should display Cashback, Free delivery message in Callout above CTA buttons
     */
    public boolean EscrowCashBackAndFreeDeliveryMsgIsPresentOnVap()
    {
        return (isElementPresent("escrowFreeDeliveryMsgOnVap")&&isElementPresent("escrowVapcashBack"));
    }
    /**
     * validate report link is present on vap
     */
    public boolean reportAdLinkIsPresent()
    {
        return isElementPresent("ReportAd");
    }

    public void scrollToReportAd()
    {
        //WebElement vapScrollView = Mapper.find(domFile,"vapscrollview");
        //loopScrollToElementByName(vapScrollView,"ReportAd",true,1000);
        Mapper.scroll("Report");
    }
    /**
     * click on report Ad
     */
    public void clickOnReportAd()
    {
        Mapper.waitForElementToBeVisible(domFile,"ReportAd");
        Mapper.find(domFile,"ReportAd").click();
    }
    /**
     * on report ad select incorrect info
     */
    public void onReportAdFormSelectIncorrectInfo()
    {
        Mapper.waitForElementToBeVisible(domFile, "ReportAdIncorrectInfo");
        Mapper.find(domFile,"ReportAdIncorrectInfo").click();
    }
    /**
     * on report ad provide comment
     */
    public void setReportAdComments(String comment)
    {
        Mapper.waitForElementToBeVisible(domFile,"reportAdComment");
        Mapper.find(domFile,"reportAdComment").click();
        explicitWait(5);
        Mapper.find(domFile,"reportAdComment").sendKeys(comment);
    }
    /**
     * clcik on submit reportAd
     */
    public void submitReportAd()
    {
        if (!isElementPresent("submitReportAd"))
            navigateBack();
        Mapper.find(domFile,"submitReportAd").click();
    }
    /**
     * validate report submission
     */
    public String validateReportAdSuccessMsg()
    {
        return (Mapper.find(domFile,"reportAdSuccessMsg").getText());
    }

    public String getAdDescription()
    {
        return Mapper.find(domFile, "adDescription").getText();
    }
    /**
     * shortlist Ad
     */

    public void shortListAdsOnVAp()
    {
        Mapper.waitForElementToBeVisible(domFile,"starButton");
        Mapper.find(domFile,"starButton").click();
    }
    /**
     * get text of chat button
     */
    public String chatButtonText()
    {
        Mapper.waitForElementToBeVisible(domFile, "chatNow");
        return Mapper.find(domFile, "chatNow").getText();
    }
    /**
     * get cordinates for swaping on vap
     */
    /**
     * get X&Y cordinates for swaping on post Ad
     */
    public Integer[] cordinatesForVerticalSwipeOnVap()
    {
        Mapper.waitForElementToBeVisible(domFile,"adDescription",20);
        int y1=Mapper.find(domFile,"adDescription").getLocation().getY();
        int y2=Mapper.find(domFile,"vapMobilesAndTAbsPrice").getLocation().getY();
        Integer[]cordinate={y1,y2};
        return cordinate;

    }
    /**
     * validate similar ads
     */
    public boolean validateSimilarAdLinkOnVap()
    {
        return (isElementPresent("similarAds"));
    }
    /**
     * validate user is on vap
     */
    public boolean validateNavigationToVap()
    {
        return isElementPresent("starButton");
    }

    public String getAdId(){
        Mapper.scroll("Ad Id:");
        Mapper.scrollElementIntoView(domFile, "AdId");
        String AdId= Mapper.find(domFile,"AdId").getText().replaceAll("\\D+","");
        return AdId;
    }

    public void clickEditAd(){
        Mapper.waitForElementToBeVisible(domFile,"EditAd");
        Mapper.find(domFile,"EditAd").click();
    }

    public boolean ifBuyNowPresent(){
        Mapper.waitForElementToBeVisible(domFile,"BuyNow",5);
        if (Mapper.find(domFile,"BuyNow").isDisplayed())
        {
            return true;
        } else
            return false;
    }
    public boolean ifCallPresent(){
        Mapper.waitForElementToBeVisible(domFile,"Call",5);
        if (Mapper.find(domFile,"Call").isDisplayed())
        {
            return true;
        } else
            return false;
    }
    public boolean ifChatPresent(){
        Mapper.waitForElementToBeVisible(domFile, "Chat", 5);
        if (Mapper.find(domFile,"Chat").isDisplayed())
        {
            return true;
        } else
            return false;
    }
    public void clickMakeAnOffer()
    {
        Mapper.waitForElementToBeVisible(domFile,"vapMakeAnOffer");
        Mapper.find(domFile,"vapMakeAnOffer").click();
    }
    public void enterOfferPrice(String price)
    {
        Mapper.waitForElementToBeVisible(domFile,"maoPrice");
        Mapper.find(domFile,"maoPrice").sendKeys(price);
    }
    public void enterOfferEmail(String email)
    {
        Mapper.waitForElementToBeVisible(domFile,"maoEmail");
        Mapper.find(domFile,"maoEmail").sendKeys(email);
    }
    public void enterOfferMobile(String mobile)
    {
        Mapper.waitForElementToBeVisible(domFile,"maoMobile");
        Mapper.find(domFile,"maoMobile").sendKeys(mobile);
    }
    public void submitOffer()
    {
        Mapper.waitForElementToBeVisible(domFile,"maoSubmit");
        Mapper.find(domFile,"maoSubmit").click();
    }
    public void hideKeyboardIfMAOnotVisibile()
    {
        if(!isElementPresent("maoSubmit"))
            navigateBack();
    }
    public boolean ifOfferAutoAcceptance(){
        if (Mapper.find(domFile,"buyNowSubmit").isDisplayed()){
            return true;
        }
        else return false;
    }
    public boolean ifInspectionScorePresent(){
        Mapper.waitForElementToBeVisible(domFile,"inspectionScoreProgressBar",7);
        if(Mapper.find(domFile,"inspectionScoreProgressBar").isDisplayed()){
            return true;
        }else
            return false;
    }
    public String getScannerScoreFromVap(){
        String scoreText = Mapper.find(domFile,"inspectionScoreText").getText();
        String a[] = scoreText.split(" ");
        String b[] = a[1].split("/");
        return b[0];
    }
    public String getScannerScoreFromReport(){
        String scoreText = Mapper.find(domFile,"reportScoreText").getText();
        String a[] = scoreText.split("/");
        return a[0];
    }
    public void viewScannerReport(){
        Mapper.find(domFile,"viewReportInspection").click();
    }

    public void viewIMEIinfo(){
        Mapper.scroll("Check the IMEI Number before accepting delivery");
        Mapper.find(domFile,"imeiInfoIcon").click();
    }

    public boolean ifImeiInfoDisplayed(){
        Mapper.waitForElementToBeVisible(domFile, "checkImeiDescription");
        if (Mapper.find(domFile,"checkImeiDescription").getText().equalsIgnoreCase("Open the dialer or keypad and type *#06#")){
            return true;
        }else
            return false;
    }

    public void selectMaoAndChatButton() {
        Mapper.waitForElementToBeVisible(domFile, "MAOChatCity");
        Mapper.find(domFile, "MAOChatCity").click();
    }
    /**
     * verify Vap header elements
     */
    /**
     * verify header elements
     */
    public boolean verifyHeaderOfVAP()
    {
        explicitWait(3);
        return (isElementPresent("backButton")&&isElementPresent("shareButton")&&isElementPresent("starButton"));
    }
    /**
     * get similar ads test
     */
    public String similarAdsText()
    {
        Mapper.waitForElementToBeVisible(domFile, "similarAds");
        return (Mapper.find(domFile,"similarAds").getText());
    }
    /**
     * count number of similar ads
     */
    public int getSimilarAdCountOnVap()
    {
        List<WebElement>adTitle=Mapper.finds(domFile, "similarAdsTitle");
        List<String>adCount=new ArrayList<>();
        for (WebElement e: adTitle)
        {
            adCount.add(e.getText());
        }
      //  System.out.println( adCount);
        Mapper.scroll("View All");
        List<WebElement>adTitle1=Mapper.finds(domFile, "similarAdsTitle");
        List<String>adCount1=new ArrayList<>();
        for (WebElement e1: adTitle1)
        {
            adCount1.add(e1.getText());
        }
        List<String>FinaladCount=new ArrayList<>(adCount);
        FinaladCount.addAll(adCount1);

        //System.out.println( adCount1);
        HashSet allAds=new HashSet();
        allAds.addAll(FinaladCount);
        FinaladCount.clear();
        FinaladCount.addAll(allAds);
        System.out.println( FinaladCount.size());
        return (FinaladCount.size());
    }

    public void hideOverlayLayout() {
        if (isElementPresent("dialogueOverlay"))
            navigateBack();
    }
/**
 * validate view All ad button on vap
 */
    public boolean validateViewAllAdIsPresent()
    {
        return(isElementPresent("viewAllAds"));
    }

}
