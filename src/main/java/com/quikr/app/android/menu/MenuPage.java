package com.quikr.app.android.menu;

import com.quikr.app.android.AppAndroidPageBase;
import com.quikr.app.android.Constants.MobileConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra singh on 13/8/15.
 */
public class MenuPage extends AppAndroidPageBase {
    public MenuPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("ANDROID_MENU_DOM_FILE");
    /**
     * click on menu icon
     */
    public void clickOnMenuIcon() {
        closeFullScreenInterstitialFromCategoryPage();
        Mapper.waitForElementToBeVisible(domFile, "menuIcon");
        Mapper.find(domFile,"menuIcon").click();
    }

    public void clickOnQuikrLogo() {
        Mapper.find(domFile, "quikrlogo").click();
    }

    public boolean validateAppVersion() {
        if (isElementPresent("appVersion"))
            return true;
        return false;
    }

    /**
     * @return
     */
    public boolean checkMSP() {
        //Mapper.waitForElementToBeVisible(domFile,"mspValidate");

        if (isElementPresent("mspValidate"))
            return true;
        return false;
    }

    /**
     * extract title text of Ad in recently viewed ads
     *
     * @return
     */
    public String recentlyViewedAdtitle() {
        Mapper.waitForElementToBeVisible(domFile, "recentlyViewdAdtitle");
        String RecentlyViewedAdTItle = Mapper.find(domFile, "recentlyViewdAdtitle").getText().toString();
        return RecentlyViewedAdTItle;
    }

    /**
     * swatantra
     * check if Post
     *
     * @return
     */

    public boolean validateMenuOnAd() {
        if (isElementPresent("postAd"))
            return true;
        return false;
    }

    public void clickOnYesiconOnRateus() {
        Mapper.waitForElementToBeVisible(domFile, "YesOnRateUs");
        Mapper.find(domFile, "YesOnRateUs").click();
    }

    public String validateRateQuikr() {
        Mapper.waitForElementToBeVisible(domFile, "redirectToPlayStore");
        String PlaystoreAppText = Mapper.find(domFile, "redirectToPlayStore").getText().toString();
        return PlaystoreAppText;
    }

    /**
     * function to select My shortlist option from menu list
     */
    public void selectMyShortlist() {
        Mapper.find(domFile, "myShortlist").click();
    }

    /**
     * function to validate my shortList
     */
    public boolean validateMyShortList() {
        return (!isElementPresent("adTitle"));
    }

    /**
     * function to click on inactive button
     */
    public void selectInactiveButton() {
        Mapper.waitForElementToBeVisible(domFile, "inactiveButton");
        Mapper.find(domFile, "inactiveButton").click();
    }

    /**
     * function to validate Deleted Ad Viewed And Status Reflected
     */
    public boolean validateDeletedAdViewedAndStatusReflected() {
        if (isElementPresent("adTitle")) {
            if (isElementPresent("rePost")) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    /**
     * function to select rePost button in inactive ad
     */
    public void selectRePostButton() {
        Mapper.waitForElementToBeVisible(domFile, "submitAd");
        Mapper.find(domFile, "rePostButton").click();
    }

    /**
     * function to validate rePost ad button visible
     */
    public boolean validateRePostAdButton() {
        return isElementPresent("rePostButton");
    }

    /**
     * check premium ad is  not displayed on my shortlist
     *
     * @return
     */
    public boolean checkNonPremium() {
        if (!isElementPresent("premium"))
            return true;
        else
            return false;
    }

    /**
     * check premium ad is displayed on my shortlist
     *
     * @return
     */
    public boolean checkPremium() {
        if (isElementPresent("premium"))
            return true;
        else
            return false;
    }

    public boolean checkInspected() {
        if (isElementPresent("inspected"))
            return true;
        else
            return false;
    }

    /**
     * function to click on ad for recommended for you
     */
    public void openAdInRecommendedForYou() {
        Mapper.waitForElementToBeVisible(domFile, "matchingAdsMyAlert");
        Mapper.finds(domFile, "matchingAdsMyAlert").get(1).click();
        Mapper.waitForElementToBeVisible(domFile, "openAdOfRecommendedForU");
        Mapper.find(domFile, "openAdOfRecommendedForU").click();
    }

    /**
     * click on make premium form MyADS
     */
    public void MakePremiumFromMyAds() {
        Mapper.waitForElementToBeVisible(domFile, "MakePreimum");
        Mapper.find(domFile, "MakePreimum").click();
    }


    /**
     * function to validate after login than back button action
     */
    public boolean validateBackButtonActionAfterLogin() {
        return isElementPresent("menuIcon");
    }

    /**
     * check if compare checkBox is present on sgortlisting ads from cars CAtegory
     */
    public boolean verifyCheckboxOnMyShortlist() {
        if (isElementPresent("compareCheckBox"))
            return true;
        else
            return false;
    }

    /**
     * check if compare  button appears after selecting compare checkBox on my shortlist ads from cars CAtegory
     */
    public boolean verifyCompareButtonMyShortlist() {
        if (isElementPresent("compare"))
            return true;
        else
            return false;
    }

    /**
     * remove shortlisted Ads from my Shortlist
     */
    public void removeShortlistedAd() {
        Mapper.find(domFile, "removeShortlistedAd").click();
    }

    /**
     * validate my shortlisted ad after removing any particular ad
     */
    public String verifyRemovedAdOnMyShortlist(int i) {
        Mapper.find(domFile, "removeShortlistedAd").click();
        String Adtitle = Mapper.find(domFile, "recentlyViewdAdtitle").getText().toString();
        return Adtitle;
    }

    /**
     * on my shortlist page select Mobiles category
     */
    public void selectMobilesOnMyShortlist() {
        Mapper.waitForElementToBeVisible(domFile, "Mobile");
        Mapper.find(domFile, "Mobile").click();
    }

    /**
     * on my shortlist page select cars category
     */
    public void selectCarsOnMyShortlist() {
        Mapper.waitForElementToBeVisible(domFile, "cras");
        Mapper.find(domFile, "cras").click();
    }

    /**
     * validate on clicking compare button user is re directed to Compare Page
     */
    public boolean validateComparePage() {
        if (isElementPresent("comparePage"))
            return true;
        else
            return false;
    }

    /**
     * select checkBox to compare Ads
     */
    public void selectCompareCheckBox(int i) {
        Mapper.finds(domFile, "compareCheckBox").get(i).click();
    }

    /**
     * clcik on compare button
     */
    public void clickOnCompareButton() {
        Mapper.find(domFile, "compare").click();
    }

    /**
     * count number of Ads in MyShortlist
     */
    public int CountNumberofAdsOnMyShortlist() {
        Mapper.waitForElementToBeVisible(domFile, "compareCheckBox");
        List<WebElement> count = Mapper.finds(domFile, "compareCheckBox");
        List<String> list = new ArrayList<String>();
        for (WebElement e : count) {
            list.add(e.getText());
        }
        int Adcount = list.size();
        System.out.println(Adcount);
        return Adcount;
    }

    public boolean validateCompareCarsAttributes(String scroll) throws Exception {
        //Storing all Elements on NO fikar options in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "compareAttributes");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options) {
            list.add(e.getText());
        }
        Thread.sleep(2000);
        Mapper.scroll(scroll);
        List<WebElement> options1 = Mapper.finds(domFile, "compareAttributes");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : options1) {
            list1.add(e.getText());
        }
        //concatenating two lists and removing duplicates
        List<String> finallist = new ArrayList<String>(list);
        finallist.addAll(list1);
        HashSet hs = new HashSet();
        hs.addAll(finallist);
        finallist.clear();
        finallist.addAll(hs);
        for (String obj3 : finallist)
            System.out.println(obj3);

        //check if all options are present

        int flag = 0;
        for (int i = 0; i < finallist.size(); i++) {

            for (int j = 0; j < MobileConstants.compareCarsAttributes.size(); j++) {
                if (finallist.get(i).equals(MobileConstants.compareCarsAttributes.get(j))) {
                    flag = 1;
                    //
                    //
                    //
                    // System.out.println(finallist.get(i) + MobileConstants.compareCarsAttributes.get(j));
                    break;
                }
            }
        }
        System.out.println(flag);
        if (flag == 1)
            return true;
        else return false;
    }

    /**
     * function to open ad from  compare page
     *
     * @return
     */
    public void OpenAd1FromComparePAde() {
        Mapper.waitForElementToBeVisible(domFile, "compareAdImage1");
        Mapper.find(domFile, "compareAdImage1").click();


    }

    public void OpenAd2FromComparePAde() {
        Mapper.waitForElementToBeVisible(domFile, "compareAdImage2");
        Mapper.find(domFile, "compareAdImage2").click();
    }

    /**
     * validate compare formet
     */
    public boolean validateComparePageFormat() {
        return (isElementPresent("compareAdImage1") & isElementPresent("compareAdImage2"));
    }

    /**
     * validate Compare error msg
     */
    public boolean ComparisonError() {

        return (isElementPresent("CompareErrorMsg"));
    }

    /**
     * function to select My Ads option from menu list
     */
    public void selectMyAds() {
        Mapper.find(domFile, "MyAds").click();
    }

    /**
     * click on About Quikr
     */

    public void clickOnAboutQuikr() {
        Mapper.find(domFile, "appversion").click();
    }

    /**
     * validate terms And Conditions Appear in AboutQuikr
     */
    public boolean validateTermsAndConditionLink() {
        return isElementPresent("termsAndConditions");
    }

    /**
     * privacy [policy link is present
     */
    public boolean validatePrivacyPolicyLink() {
        return isElementPresent("privacyPolicy");
    }

    public void clickOnMsp() {
        Mapper.find(domFile, "msp").click();
    }

    /**
     * validate user is rdirected to mp page afer clicking msp on menu
     *
     * @return
     */
    public boolean validateUserIsRedirectedToMspPage() {
        return isElementPresent("mspPage");
    }

    /**
     * on clicking my ads user is redirected To my ads page
     */
    public boolean validateUSerIsRedirectedToMyAdsPage() {
        return (isElementPresent("activeAds") && isElementPresent("inactiveAds"));
    }

    /**
     * on clicking shortlist ads user is redirected To shortlist page
     */
    public boolean validateUSerIsRedirectedToShortlistPage() {
        return (isElementPresent("Shortlistedpage"));
    }

    /**
     * skip chat notification handler
     */
    public boolean skipChatNotificationHandler() {
        return (isElementPresent("menuChatBox"));
    }

    /**
     * open ad from recentlyViewd Ads
     */
    public void openAdFromRecentlyViewedAds() {
        Mapper.waitForElementToBeVisible(domFile, "recentlyViewdAdtitle");
        Mapper.find(domFile, "recentlyViewdAdtitle").click();
    }

    /**
     * storing menu dropDown elements in list
     * @return
     */
    public List menuListElements() {
        //Storing allMenu elements  in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "menuList");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options) {
            list.add(e.getText());
        }
        explicitWait(3);
        int downElemet = Mapper.find(domFile, "menuList").getLocation().getY();
        int upElement = Mapper.find(domFile, "menuListCahngeCity").getLocation().getY();
        List<String> tempList = new ArrayList<String>();
        List<String> list1 = new ArrayList<String>();
        list1.addAll(list);
        while (!tempList.equals(list1))
        {
            tempList.clear();
            tempList.addAll(list1);
            verticalSwipeWithCordinates(downElemet + 20, upElement);
            verticalSwipeWithCordinates(downElemet + 20, upElement);
            verticalSwipeWithCordinates(downElemet + 50, upElement);
            explicitWait(3);
            List<WebElement> options1 = Mapper.finds(domFile, "menuList");
            list1.clear();
            for (WebElement e : options1) {
                list1.add(e.getText());
            }
            System.out.println("List1: "+list1);
            list.removeAll(list1); //removing duplicates present in list1
            list.addAll(list1); //Merging list 1 to list
            System.out.println("List: "+list);
            System.out.println("tempList: "+tempList);
        }

        return list;
    }
    /**
     * chat button is present
     */
    public boolean chatButtonIsPresent()
    {
        return isElementPresent("chatButton");
    }
    /**
     * tap on customer care number
     */
    public void tapOnCustomerCareNumber()
    {
        Mapper.waitForElementToBeVisible(domFile, "customerCareNumber");
        Mapper.find(domFile, "customerCareNumber").click();
    }
    /**
     * validate customer care number is present
     */
    public boolean customerCareNumberIsPresent()
    {
        return (isElementPresent("customerCareNumber"));
    }
    /**
     * Tapping on Customercare number , the phone has to be populated with the number and user should be able to call
     */
    public boolean validateUserIsAbleToMakeCall()
    {
        return (!isElementPresent("customerCareNumber"));
    }

    public void clickOnFeedback()
    {
        Mapper.waitForElementToBeVisible(domFile, "feedback");
        Mapper.find(domFile, "feedback").click();
    }

    public void clickOnGmailOption()
    {
        Mapper.waitForElementToBeVisible(domFile, "shareViaGmail");
        Mapper.find(domFile, "shareViaGmail").click();
//        Mapper.waitForElementToBeVisible(domFile, "shareViaGmail");
//        Mapper.find(domFile, "justOnceButton").click();
    }

    public boolean isGmailTextPresent()
    {
        Mapper.waitForElementToBeVisible(domFile, "gmailText");
        return isElementPresent("gmailText");
    }
    /**
     * change city from MenuList
     */
    public void changeCityFromMenuList()
    {
        Mapper.waitForElementToBeVisible(domFile,"menuListCahngeCity");
        Mapper.find(domFile,"menuListCahngeCity").click();
    }

    /**
     * fetch ad types on homepage
     * @return
     */
    public List AdTypesOnHomePage() {
        //Storing allMenu elements  in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "heardeTextPopularNearByAds");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options) {
            list.add(e.getText());
        }
        explicitWait(3);
        List<WebElement> options1 = Mapper.finds(domFile, "heardeTextPopularNearByAds");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : options1) {
            list1.add(e.getText());
        }
        System.out.println(list1);
//merging tow lists in oreder and removing duplicates
        for (int j = 0; j < list1.size(); j++) {
            if (!(list.contains(list1.get(j))))
                list.add(list1.get(j));
        }
        System.out.println(list);
        return list;
    }
    /**
     * get text of headre
     */
    public String getHeaderText()
    {
        Mapper.waitForElementToBeVisible(domFile, "textView");
        return Mapper.find(domFile,"textView").getText();
    }
    /**
     * verify myADS header
     */
    public boolean verifyMyAdsHeader()
    {
        return (isElementPresent("activeAds")&&isElementPresent("inactiveAds"));
    }
    /**
     * validate make an offer is present for escrow cat
     */
    public boolean validateMakeAnOfferIsPresent()
    {
        return (isElementPresent("chatButton")||isElementPresent("makeAnOffer"));
    }
    /**
     * click on Get more responses
     */
    public void clickOnGetMoreResponses() {
        Mapper.find(domFile, "getMoreResponses").click();

    }
    /**
     * click on menu icon in my active ads
     */
    public void clickOnMenuIconInActiveAds(int i) {
        Mapper.finds(domFile, "menuIconActiveAds").get(i).click();

    }
    public void clickOnAnnounceChatAvailability(){
        Mapper.find(domFile, "announceChatAvailability").click();

    }

    public boolean verifyIfDone(){
        Mapper.waitForElementToBeInvisible(domFile, "Done");
        if (isElementPresent("Done"))
            return true;
        return false;
    }
    public void clickMoveToTop(){
        Mapper.find(domFile,"moveToTop").click();
    }

    public void clickActiveAdsMenuIcon(int i){
        Mapper.finds(domFile,"menuIconActiveAds").get(i).click();
    }

    /**
     * Verify congratulations msg on move to top
     */
    public void verifySuccessMsg(){
        Mapper.find(domFile,"moveToTopSuccessMsg");
        Mapper.find(domFile,"moveToTopOkButton").click();
    }

    /**
     * Getting the active ads title
     *
     */
    public String verifyAdsTitle(int i) {
        String activeAdTitle = Mapper.finds(domFile,"adsTitle").get(i).getText().toString();
        return activeAdTitle;

    }

    public void closeFullScreenInterstitialFromCategoryPage()
    {
        if(!isElementPresent("titlebar"))
            navigateBack();
    }


    /*public void scrollToRefresh(){
       int size =

    }*/


}

