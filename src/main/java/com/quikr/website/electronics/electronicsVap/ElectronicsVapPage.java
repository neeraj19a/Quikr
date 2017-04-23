package com.quikr.website.electronics.electronicsVap;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra singh on 29/7/15.
 */
public class ElectronicsVapPage extends PageBase {

    public ElectronicsVapPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("Electronics_VAP_DOM_FILE");


    /**
     * in recomended ad section similar ads should be displayed
     * Created by swatantra singh on 30/7/15.
     */
    public boolean verifyrecomenedenAd() {
        Mapper.waitForElementToBeVisible(domFile, "headrsubcat");
        String Vapheader = Mapper.finds(domFile, "headrsubcat").get(0).getText().toString();
        Mapper.waitForElementToBeVisible(domFile, "relatedHeader");
        String RecomendenAd = Mapper.find(domFile, "relatedHeader").getText().toString();
        String Category=Mapper.find(domFile,"Category").getText().toString();
        if (RecomendenAd.contains(Vapheader) || RecomendenAd.contains(Category.substring(0,3)))
        {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean verifyRecommendedAdsPresence()
    {
        try
        {
            if (Mapper.find(domFile, "RecomendedAd").isDisplayed()) {
                return true;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }



    public void clickReplybuttonforRecommendedAds(){

        List<WebElement> recommendedads_replybutton=Mapper.finds(domFile, "recommendedadswith_replybutton");
        recommendedads_replybutton.get(0).click();
    }
    /**
     * ads marked as favourite should be displayed in saved ads
     * Created by swatantra singh on 30/7/15.
     */
    public boolean MarkAdAsFavourite() {
        boolean flag=false;
        Mapper.waitForElementToBeVisible(domFile, "AddToFav");
        if(Mapper.waitForElementToBeClickable(domFile, "AddToFav")==true) {
            Mapper.find(domFile, "AddToFav").click();
            Mapper.waitForElementToBeVisible(domFile, "favoritebx");
            if (isElementPresent("favoritebx")) {
                flag= true;
            }
            else
                flag=false;
        }
        return flag;
    }

    /**
     * click on apply jobs
     *
     * @return
     */
    public ElectronicsVapPage clickOnApplyJobs() {
        Mapper.find(domFile, "applyJobs").click();
        Mapper.waitForElementToBeVisible(domFile, "jobsRole");
        return this;
    }

    /**
     * swatantra
     *
     * @param role
     * @return
     */
    public ElectronicsVapPage selectRole(String role) {

        Mapper.find(domFile, "jobsRole").click();
        Mapper.findChildElement(domFile, role, "jobsSuggestion", "a", true).click();
        return this;
    }

    /**
     * swatantra singh
     *
     * @param email
     * @return
     */
    public ElectronicsVapPage sendEmailId(String email) {
        Mapper.find(domFile, "jobsEmail").sendKeys(email);
        return this;
    }

    /**
     * swatantra singh
     *
     * @param number
     * @return
     */
    public ElectronicsVapPage sendNumber(String number) {
        Mapper.find(domFile, "jobsNumber").sendKeys(number);
        return this;
    }

    /**
     * submit reply
     * swatantra singh
     */
    public ElectronicsVapPage submitReply() {
        Mapper.find(domFile, "submitJobs").click();
        return this;
    }

    public boolean validateJobsApplied() {
        if (isElementPresent("validateJob"))
            return true;
        return false;
    }

    public ElectronicsVapPage openRecomendeAd() {
        Mapper.waitForElementToBeVisible(domFile, "openRecomendedAd");
        Mapper.find(domFile, "openRecomendedAd").click();
        return this;
    }

    public boolean isPrevandNextButtonvisible(){
        boolean flag=false;

        if(isElementPresent("vapPreviousButton")){
            flag=true;
        }
        else
            return false;

        if(isElementPresent("vapNextButton")){
            flag=true;
        }
        else
            return false;

        return flag;

    }

    /**
     * click on reply button
     */
    public void clickOnReplyButton() {

        //Mapper.waitForElementToBeClickable(domFile, "AddToFav");
                try {
                        Mapper.find(domFile, "vapReplyButton").click();

                } catch (Exception e) {

                            }

        try {
                      Mapper.find(domFile, "vapReplyButton1").click();
                   }
                catch (Exception e)
                 {

                               }

    }

    /**
     * enter message in reply dialog
     *
     * @param message
     */
    public void setMessageInReplyDialog(String message) {
        Mapper.find(domFile, "smscontent").sendKeys(message);
    }

    /**
     * enter email in reply dialog
     *
     * @param email
     */
    public void setEmailAddressInReplyDialog(String email) {
        Mapper.waitForElementToBeClickable(domFile, "smsemail");
        Mapper.find(domFile, "smsemail").sendKeys(email);
    }

    /**
     * click on send button present in reply dialog
     */
    public void clickSendButtonInReplyDialog() {
        Mapper.find(domFile, "smssend").click();
    }

    /**
     * validate reply success message is present
     */
    public boolean validateReplySuccess() {
        return isElementPresent("submitsms");
    }

    public void openRecomendedAdsFromVap() {
        Mapper.find(domFile, "RecomendenAdTitle").click();
    }

    /**
     * validate elements of application form
     * swatantra singh
     *
     * @return
     */
    public boolean validateApplyFormElements() {
        if (isElementPresent("jobsRole") && isElementPresent("jobsEmail") && isElementPresent("jobsNumber"))
            return true;
        return false;
    }

    /**
     * Function for validating Escrow details on VAP
     */
    public boolean validateEscrowDetailsOnVAP() {
        if (!(Mapper.find(domFile, "freeDeliveryOption")).isDisplayed() || !(Mapper.find(domFile, "codOption").isDisplayed() || !(Mapper.find(domFile, "cashbackOption").isDisplayed()))) {
            return false;
        }
        return true;
    }

    /**
     * Function for clicking Make An Offer
     */
    public void clickMakeAnOffer() {
        Mapper.find(domFile, "makeAnOfferButton").click();
    }

    /**
     * Function for entering details in Make An Offer prompt
     */
    public void enterMakeAnOfferDetails(String amt, String email, String phoneNo) {
        Mapper.find(domFile, "escrowPrice").sendKeys(amt);
        Mapper.find(domFile, "escrowEmail").sendKeys(email);
        Mapper.find(domFile, "escrowPhone").sendKeys(phoneNo);
        Mapper.find(domFile, "escrowMakeAnOfferButton").click();
    }

    /**
     * Function for validating Make An offer
     */
    public boolean validateMakeAnOffer() {
        if (!(Mapper.find(domFile, "makeAnOfferSuccessMessage")).isDisplayed()) {
            return false;
        }
        return true;
    }

    /**
     * validate locality
     *
     * @param locality
     * @return
     */
    public boolean validateLocality(String locality) {
        //String content=null, content2=null;
        Mapper.waitForElementToBeVisible(domFile, "localityOptionName");
        if (isElementPresent("localityOptionName")) {
            String content = Mapper.find(domFile, "localityOptionName").getAttribute("title");

            if (content == null || content.isEmpty()) {
                content = Mapper.find(domFile, "localityOptionName").getText();
            }
            System.out.println("Locality:" + content);
            return content.contains(locality);
        } else if (isElementPresent("localityOptionName2")) {
            String content2 = Mapper.find(domFile, "localityOptionName2").getText();
            System.out.println("Locality:" + content2);
            return content2.contains(locality);
        }
        return false;
    }

    /**
     * Function for entering details in Buy Now prompt
     */
    public void enterBuyNowDetails(String email, String phoneNo) {
        Mapper.find(domFile, "buyNowEmail").sendKeys(email);
        Mapper.find(domFile, "buyNowMobileNumber").sendKeys(phoneNo);
        Mapper.find(domFile, "escrowBuyNowOption").click();
    }

    /**
     * Function for validation payment method displayed
     */
    public boolean validatePaymentMethod() {
        Mapper.find(domFile, "paymentOptionMaximize").click();
        if (!(Mapper.find(domFile, "paymentOption")).isDisplayed()) {
            return false;
        }
        return true;
    }

    public void ClickChatButtonVap() {
        Mapper.find(domFile, "chatButton").click();
    }

    public String viewdetailstext() {
        String text = Mapper.find(domFile, "descriptionText").getText();
        return text;
    }

    /**
     * This method clicks on fb, twitter and hangout share buttons and verifies the respective urls.
     *
     * @return boolean value
     */
    public boolean verifySocialMediaButtons()
    {
        boolean finalVal = false;
        String baseWindowHandle = getWindowHandle();
        if (Mapper.waitForElementToBeClickable(domFile,"socialMediaShare")==true)
        {
                Mapper.find(domFile, "socialMediaShare").click();
                Mapper.waitForElementToBeVisible(domFile, "facebookShareLink");
                if (Mapper.find(domFile, "facebookShareLink")!=null)
                    {
                    Mapper.find(domFile, "facebookShareLink").click();
                        switchTo().window(baseWindowHandle);
                    }
            else
                return false;

                    if (Mapper.find(domFile, "twitterShareLink")!=null)
                    {
                            Mapper.find(domFile, "twitterShareLink").click();
                        switchTo().window(baseWindowHandle);
                        }
                         else
                        return false;


                    if (Mapper.find(domFile, "hangoutShareLink")!=null)
                    {
                            Mapper.find(domFile, "hangoutShareLink").click();
                        switchTo().window(baseWindowHandle);
                        }
            else
                        return false;
                    }
        else{
        logger.info("Pls Check Might be Social Media Icons are not present");
        return false;
        }


        Set<String> windowHandles = getWindowHandles();
        System.out.println("Number of window handles >>" + windowHandles.size());
        Iterator it = windowHandles.iterator();
        String[] urls = {"quikr.com", "facebook.com/login", "twitter.com", "google.com"};
        int i = 0;
        while (it.hasNext()) {
            String winHandle = it.next().toString();
            switchTo().window(winHandle);
            if (getCurrentLocation().contains(urls[i])) {
                finalVal = true;
                i += 1;
                switchTo().window(baseWindowHandle);
            }
        }
        return finalVal;
    }

    public void clickFirstAd()
    {
        try {
            Thread.sleep(5000);
            if (Mapper.waitForElementToBeVisible(domFile, "firstAdSnb") == true) {
                if (Mapper.find(domFile, "firstAdSnb").isDisplayed() == true) {
                    Mapper.find(domFile, "firstAdSnb").click();
                    if (Mapper.waitForElementToBeInvisible(domFile, "firstAdSnb") == true) {
                        logger.info("Ad title clicked at first attempt.");
                    } else {
                        Mapper.find(domFile, "firstAdSnb").click();
                        logger.info("Ad title clicked on second attempt.");
                    }
                } else {
                    logger.info("First ad in snb is not displayed.");
                }
            } else {
                logger.info("Either there are no ads on SNB or some other problem which you need to check!");
            }
        }catch (Exception e){}
    }

    public void addToFavMobiles()
    {
        if (Mapper.waitForElementToBeClickable(domFile, "starIconMobileVap")==true)
        {
            Mapper.find(domFile, "starIconMobileVap").click();
        }
    }

    public boolean verifyAdSaveMobileVap()
    {
        boolean retval = false;
        if (Mapper.waitForElementToBeVisible(domFile, "savedAdBoxMobileVap")==true)
        {
            if (Mapper.find(domFile, "savedAdBoxMobileVap").isDisplayed()==true)
            {
                retval= true;
            } else {
                retval= false;
            }
        }
        return retval;
    }

    public boolean verifySavedAdOnCookieDeletion()
    {
        boolean retVal = false;
        deleteAllCookies();
        navigateTo().refresh();
        if (Mapper.find(domFile, "savedAdBoxMobileVap").isDisplayed()==true)
        {
            retVal= false;
        } else {
            retVal= true;

        }
        return retVal;
    }

    public boolean checkFiveAdsToVerifyShowMoreShowLess() {
        boolean finalVal = false;
        List<WebElement> elm = Mapper.finds(domFile, "adHeadersSnb");
        for (int i = 0; i < 5; i++) {
            String locator = "adDescription" + "[" + i + 1 + "]";
            elm.get(i).click();
            Mapper.waitForElementToBeVisible(domFile, "adDescription");
            String Text = Mapper.find(domFile, "adDescription").getText();
            if (Mapper.find(domFile, "showmoreLink").isDisplayed()) {
                Mapper.find(domFile, "showmoreLink").click();
                String LongText = Mapper.find(domFile, "adDescription").getText();
                if (LongText.contains(Text)) {
                    finalVal = true;
                }
                Mapper.find(domFile, "showmoreLink").click();
                String LessText = Mapper.find(domFile, "adDescription").getText();
                if (LessText.equals(Text)) {
                    finalVal = true;
                }

            } else {
                navigateTo().back();
            }
        }
        return finalVal;
    }


    public boolean verifyShowMoreAndLessLink()
    {
        boolean finalVal = false;
        //commenting clickFirstAd() as now the function returnAdNumberhavingLongestTitle() will automatically search for ad havinbg show more and show less link
        //clickFirstAd();
        if (Mapper.waitForElementToBeVisible(domFile, "adDescription")==true)
        {
            String Text = Mapper.find(domFile, "adDescription").getText();
            if (Mapper.waitForElementToBeVisible(domFile, "showmoreLink")==true)
            {
                WebElement element=Mapper.find(domFile, "showmoreLink");
                if (element!=null)
                {
                    element.click();
                    if (Mapper.waitForElementToBeVisible(domFile, "adDescription")==true)
                    {
                        String LongText = Mapper.find(domFile, "adDescription").getText();
                        if (LongText.contains(Text)) {
                            finalVal = true;
                        }
                        else
                        {
                            return false;
                        }
                        Mapper.find(domFile, "showmoreLink").click();
                        String LessText = Mapper.find(domFile, "adDescription").getText();
                        if (LessText.equals(Text)) {
                            finalVal = true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
                else
                {
                    logger.info("The element is null. No show more/less links.");
                    return false;
                }

            }
            else
            {
                finalVal=true;
                logger.info("The length of the description is probably not long enough for show more/less links. Hence returning true.");
            }
        }
        else
        {
            logger.info("The ad description is itelf not visible. Please check!");
        }
        return finalVal;
    }

    public String viewadtext() {

        String text = Mapper.find(domFile, "productDetails").getText();
        return text;
    }

    public boolean verifyCategoryChange() {
        return getCurrentLocation().contains("Electronics-Appliances");
    }

    public boolean verifyUrl(String stringToLookInUrl) {
        waitForPageToLoad(stringToLookInUrl);
        return getCurrentLocation().toUpperCase().contains(stringToLookInUrl.toUpperCase());
    }

    public boolean verifyBreadcrumbs() {
        boolean flag=false;
        if (Mapper.waitForElementToBeVisible(domFile, "breadCrumbs") == true){
        if(Mapper.find(domFile, "breadCrumbs")!=null){
            flag= true;
        }
            else {
            flag= false;
        }
    }
        return flag;
    }

    public boolean deleteSavedAdWithCrossButton()
    {

            if (Mapper.find(domFile, "savedAddCrossIcon")!=null)
            {
                Mapper.find(domFile, "savedAddCrossIcon").click();
                //navigateTo().refresh();
            }
        else
                return false;

        try
        {
            Thread.sleep(3000);
        }catch (Exception e)
        {

        }
        if (Mapper.find(domFile, "savedAdBoxMobileVap")!=null){
            return false;
        }
        else
            return true;
    }

    public boolean deleteSavedAdWithRemoveAll()
    {
        if (Mapper.waitForElementToBeVisible(domFile, "savedAdBoxMobileVap")==true) {

            if (Mapper.waitForElementToBeVisible(domFile, "removeAllLink") == true) {
                Mapper.find(domFile, "removeAllLink").click();
                navigateTo().refresh();
            }
        }
        if (Mapper.find(domFile, "savedAdBoxMobileVap")!=null){
            return false;
        }
        else
            return true;
    }

    public boolean verifyReplybuttonInVapisEnabled() {
        //hack to handle pop up of Services invoked when clicked on Quikr Services Ad with Reply button

        if(Mapper.waitForElementToBeClickable(domFile, "crossButtonQuikrConnectPopUp")==true){
            Mapper.find(domFile,"crossButtonQuikrConnectPopUp").click();
        }
        else
        try {
            if (Mapper.find(domFile, "vapReplyButton").isDisplayed()) {
                return true;
            }
        }
        catch (Exception e){

        }
        try {
             if(Mapper.find(domFile,"vapReplyButton1").isDisplayed())
             {
                 return true;
             }
        }
        catch (Exception e) {

        }

        try{
            if(Mapper.find(domFile,"vapReplyButtonNewUi").isDisplayed());
        }
        catch (Exception e){

        }
        return false;
    }

    public void clickReplyButton()
    {
        if (Mapper.find(domFile,"vapReplyButton")!=null)
        {
            if (Mapper.find(domFile,"vapReplyButton").isDisplayed()==true)
            {
                Mapper.find(domFile,"vapReplyButton").click();
            }
            else
            {
                logger.info("Vap reply button is not displayed.");
            }
        }
        else if (Mapper.find(domFile,"vapReplyButton1")!=null)
        {
            if (Mapper.find(domFile,"vapReplyButton1").isDisplayed()==true)
            {
                Mapper.find(domFile,"vapReplyButton1").click();
            }
            else
            {
                logger.info("Vap reply button is not displayed.");
            }
        }
        else if (Mapper.find(domFile,"vapReplyButtonNewUi")!=null)
        {
            if (Mapper.find(domFile,"vapReplyButtonNewUi").isDisplayed()==true)
            {
                Mapper.find(domFile,"vapReplyButtonNewUi").click();
            }
            else
            {
                logger.info("Vap reply button is not displayed.");
            }
        }
        else
        {
            logger.info("Vap reply button is not present.");
        }
    }

    public void inputReply()
    {
        if (Mapper.find(domFile,"vapReplyMessageArea")!=null)
        {
            if (Mapper.waitForElementToBeVisible(domFile, "vapReplyMessageArea")==true)
            {
                Mapper.find(domFile, "vapReplyMessageArea").click();
                Mapper.find(domFile,"vapReplyMessageArea").sendKeys("This message is junk or it is not... you sure ;) ?");
            }
            else
            {
                logger.info("Reply text field is not visible.");
            }
        }else
        {
            logger.info("Reply message are is not present.");
        }
    }

    public void clickSendReplyBox()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"vapReplySendButton")==true)
        {
            Mapper.find(domFile, "vapReplySendButton").click();
        }
        else
        {
            logger.info("Vap reply send button is not visible.");
        }
    }

    public boolean verifyReplybutton()
    {
        boolean retVal = false;
        clickReplyButton();
        inputReply();
        clickSendReplyBox();
        if (Mapper.find(domFile, "vapReplySuccessMessage")!=null)
        {
            String successMsgCaptured = Mapper.find(domFile, "vapReplySuccessMessage").getText();

            if (successMsgCaptured.contains("Your reply has been successfully sent")) {
                retVal = true;
            }
        }
        else if (Mapper.find(domFile, "ReplyMsgAreaError").isDisplayed())
        {
            inputReply();
            clickSendReplyBox();
            String successMsgCapturedAgn = Mapper.find(domFile, "vapReplySuccessMessage").getText();
            if (successMsgCapturedAgn.contains("Your reply has been successfully sent"))
                retVal = true;
        } else
        {
            logger.info("Dude! am unable to find why the reply didn't get posted. Check please.");
            return false;
        }
        return retVal;
    }


    public boolean verifyVapReplySimilarAds() {
        if (Mapper.find(domFile, "vapReplySimilarAds").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public String gettextSimilarAdsVapPage(){
        return Mapper.find(domFile,"SimilarAdsTitleOldVAPPage").getText();
    }

    public boolean verifyReplybuttonSimilarAds() {
        List<WebElement> replybutton = Mapper.finds(domFile, "ReplybuttonSimilarAds");
        if (replybutton.size() > 0) {
            return true;
        } else
            return false;
    }

    public boolean verifyMoreAdsSectionisDisplayed() {
        if (Mapper.find(domFile, "MoreAdsSection").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void clickMoreAdsSection()
    {
        Mapper.find(domFile, "MoreAdsSection").click();
    }

    public String moreadstext(){
        String moreadstext= Mapper.find(domFile, "MoreAdsSection").getText();
        return moreadstext;
    }

    public boolean verifyDownloadAppPopUp(){
        if (Mapper.find(domFile,"downloadAppPopUP").isDisplayed()){
            return true;
        }
        else
            return false;
    }
}
