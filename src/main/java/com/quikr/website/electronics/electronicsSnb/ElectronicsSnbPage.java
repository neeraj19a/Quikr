package com.quikr.website.electronics.electronicsSnb;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by rohanbajaj on 27/07/15.
 */
public class ElectronicsSnbPage extends PageBase {
    public ElectronicsSnbPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("Electronics_SNB_DOM_FILE");


    public boolean isAdsOnSnbPageVisible() {
        return (isElementPresent("adsOnSnbPage") || isElementPresent("adsOnNewSnbPage"));
    }


    // function to perform sorting in ascending  order on SnBPage
    public boolean SnbPageAsndSort()
    {
        Mapper.waitForElementToBeVisible(domFile, "price");
        Mapper.waitForElementToBeClickable(domFile, "price");
        Mapper.find(domFile, "price").click();
        List<WebElement> priceList = Mapper.finds(domFile, "priceTag");
        List<String> list = new ArrayList<String>();

        for (int i=0;i<priceList.size()-1;i++) //-1 as one of the last string was coming as null, thus problem in parsing as int.
        {
            list.add(i,priceList.get(i).getText().replaceAll("\\D", ""));  // Will Keep only digits, rest all will be removed
        }

        List<Integer> listOriginal = new ArrayList<Integer>();
        List<Integer> listNew = new ArrayList<Integer>();

        //for (int i =0;i<list.size();i++)
        //{
          //  System.out.println("$"+list.get(i)+"$");
        //}


        for (int i = 0; i < list.size(); i++) {

            int testValue = Integer.parseInt(list.get(i));
            listNew.add(testValue);
            listOriginal.add(testValue);
        }

        Collections.sort(listNew);

        for (int i = 0; i < list.size(); i++) {

            if ((int) listOriginal.get(i) != (int) listNew.get(i)) {

                System.out.println(listOriginal.get(i) + " " + listNew.get(i));
                return false;
            }
        }

        return true;
    }


    // function to perform sorting in descending order on SnBPage
    public boolean SnbPageDsndSort()
    {
        Mapper.waitForElementToBeVisible(domFile, "price");
        Mapper.find(domFile, "price").click();
        Mapper.find(domFile, "price").click();
        List<WebElement> priceList = Mapper.finds(domFile, "priceTag");
        List<String> list = new ArrayList<String>();

        //for (WebElement e : priceList)
        for (int i=0;i<priceList.size();i++)
        {
            list.add(i, priceList.get(i).getText().replaceAll("\\D", ""));
        }

        List<Integer> listOriginal = new ArrayList<Integer>();
        List<Integer> listNew = new ArrayList<Integer>();

        //for (int i=0;i<list.size();i++)
        //{
          //  System.out.println("$"+list.get(i)+"$");
        //}

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).equals("")||list.get(i)=="")
            {
                list.remove(i);
            }
            else {
                int testValue = Integer.parseInt(list.get(i));
                listNew.add(testValue);
                listOriginal.add(testValue);
            }
        }

        Collections.sort(listNew);
        Collections.reverse(listNew);

        for (int i = 0; i < listNew.size(); i++) {
            if ((int) listOriginal.get(i) != (int) listNew.get(i))
                return false;
        }
        return true;
    }

    public String OpenAd()
    {
        String title = Mapper.find(domFile, "adTitle").getText();
        if (Mapper.find(domFile, "adTitle").isDisplayed()==true)
        {
            Mapper.find(domFile, "adTitle").click();
        }
        return title;
    }


    /**
     * select  Role option
     */
    public void selectRoleOption(String role) {
        Mapper.findChildElement(domFile, role, "roleElement", "a", "label").click();
    }

    /**
     * validation on SnB page in full time Jobs sub-category filter by role name
     */
    public boolean validateSpecifiedOptionName(String specifiedOption) {

        Mapper.waitForElementToBeVisible(domFile, "specifiedOptionName");
        List<WebElement> roleNameStore = Mapper.finds(domFile, "specifiedOptionName");
        List<String> allElements = new ArrayList<>();

        for (WebElement e : roleNameStore) {
            allElements.add(e.getText());
        }

        for (int i = 0; i < allElements.size(); i++) {
            if (!allElements.get(i).equalsIgnoreCase(specifiedOption)) {
                return false;
            }
        }

        return true;
    }

    /**
     * select Compensation options
     */
    public void selectCompensationOptions(String compensation) {
        Mapper.findChildElement(domFile, compensation, "compensationElement", "a", "label").click();
    }

    /**
     * validation on SnB page in  Jobs sub-category filter by Compensation option name
     */
    public boolean validateCompensationOption(String compensation) {
        Mapper.waitForElementToBeVisible(domFile, "compensationName1");
        Mapper.waitForElementToBeVisible(domFile, "compensationName2");
        List<WebElement> compensationName1Store = Mapper.finds(domFile, "compensationName1");
        List<WebElement> compensationName2Store = Mapper.finds(domFile, "compensationName2");
        List<String> allElements = new ArrayList<>();
        for (WebElement e : compensationName1Store) {
            if (e.getText().equals(compensation)) {
                allElements.add(e.getText());
            }
        }

        for (WebElement e : compensationName2Store) {
            if (e.getText().equals(compensation)) {
                allElements.add(e.getText());
            }
        }

        for (int i = 0; i < allElements.size(); i++) {
            if (!allElements.get(i).equalsIgnoreCase(compensation)) {
                return false;
            }
        }


        for (int i = 0; i < allElements.size(); i++) {
            if (!allElements.get(i).equalsIgnoreCase(compensation)) {
                return false;
            }
        }

        return true;
    }

    /**
     * select Experience options
     */
    public void selectExperienceOptions(String experience) {
        Mapper.findChildElement(domFile, experience, "experienceElement", "a", "label").click();
    }

    /**
     * validation on SnB page in  Jobs sub-category filter by Experience option name
     */
    public boolean validateExperienceOption(String experience) {
        Mapper.waitForElementToBeVisible(domFile, "experienceOptionName1");
        List<WebElement> experienceOptionName1Store = Mapper.finds(domFile, "experienceOptionName1");
        List<WebElement> experienceOptionName2Store = Mapper.finds(domFile, "experienceOptionName2");
        List<String> allElements = new ArrayList<>();

        for (WebElement e : experienceOptionName1Store) {
            if (e.getText().equals(experience)) {
                allElements.add(e.getText());
            }
        }

        for (WebElement e : experienceOptionName2Store) {
            if (e.getText().equals(experience)) {
                allElements.add(e.getText());
            }
        }

        for (int i = 0; i < allElements.size(); i++) {
            if (!allElements.get(i).equalsIgnoreCase(experience)) {
                return false;
            }
        }

        return true;
    }

    /**
     * click apply button on Snb
     * swatantra singh
     */
    public boolean validateApplyButton() {
        Mapper.waitForElementToBeClickable(domFile, "apply");
        Mapper.find(domFile, "apply").click();
        Mapper.waitForElementToBeVisible(domFile, "applybox");
        if (isElementPresent("applybox"))
            return true;
        return false;
    }


    /* select Education options
    */
    public void selectEducationOptions(String educationText) {
        Mapper.findChildElement(domFile, educationText, "educationElement", "a", "label").click();
    }

    /**
     * validation on SnB page in  Jobs sub-category filter by Education option name
     */
    public boolean validateEducationOption() {
        int count = 0;
        List<WebElement> snbPageAdStore = Mapper.finds(domFile, "snbPageAd");
        snbPageAdStore.get(2).click();

        if (Mapper.waitForElementToBeVisible(domFile, "educationOptionName")) {
            count++;
        }

        navigateTo().back();
        Mapper.waitForElementToBeClickable(domFile, "roleElement");
        List<WebElement> snbPageAdStore1 = Mapper.finds(domFile, "snbPageAd");
        snbPageAdStore1.get(3).click();

        if (Mapper.waitForElementToBeVisible(domFile, "educationOptionName")) {
            count++;
        }

        navigateTo().back();
        Mapper.waitForElementToBeClickable(domFile, "compensationElement");
        List<WebElement> snbPageAdStore2 = Mapper.finds(domFile, "snbPageAd");
        snbPageAdStore2.get(4).click();

        if (Mapper.waitForElementToBeVisible(domFile, "educationOptionName")) {
            count++;
        }

        navigateTo().back();
        Mapper.waitForElementToBeClickable(domFile, "compensationElement");

        if (count == 3) {
            return true;
        }

        return false;
    }

    /**
     * select Locality options
     */
    public void selectLocalityOptions(String localityText) {
        Mapper.findChildElement(domFile, localityText, "localityElement", "a", "label").click();
    }

    /**
     * click on particular ad present on snb page
     *
     * @param linkNumber : ad to be clicked
     * @return
     */
    public void clickOnAd(int linkNumber) {
        Mapper.waitForElementToBeVisible(domFile, "LHSCategory", 15);
        Mapper.waitForElementToBeClickable(domFile, "LHSCategory");
        WebElement snbPageAdStore=Mapper.findAndReplace(domFile,"snbPageAd",new String[]{Integer.toString(linkNumber)});
        snbPageAdStore.click();
        Mapper.waitForElementToBeInvisible(domFile, "LHSCategory", 15);
        WebElement element=Mapper.find(domFile, "LHSCategory");

        if(element!=null){
            snbPageAdStore.click();
        }
    }

    /**
     * @param role swatantra singh
     */
    public void selectRole(String role) {
        Mapper.find(domFile, "role").click();
        Mapper.findChildElement(domFile, role, "role", "a", true).click();

    }

    /**
     * @param email swatantra singh
     */
    public void replyemail(String email) {
        Mapper.find(domFile, "replyemail").sendKeys(email);
    }

    /**
     * @param number swatantra singh
     */
    public void replyNumber(String number) {
        Mapper.find(domFile, "replynumber").sendKeys(number);
    }

    /**
     * swatantra singh
     */
    public void submitReply() {
        Mapper.waitForElementToBeVisible(domFile, "jobApply");
        Mapper.find(domFile, "jobApply").click();
    }


    /**
     * validate reply
     */
    public boolean validateReply() {
        Mapper.waitForElementToBeVisible(domFile, "validateReply");
        if (isElementPresent("validateReply") || isElementPresent("formError"))
            return true;
        return false;
    }

    public boolean validateOfferTypeAds(String apply) {

        Mapper.waitForElementToBeVisible(domFile, "apply");
        List<WebElement> roleNameStore = Mapper.finds(domFile, "apply");
        List<String> allElements = new ArrayList<>();

        for (WebElement e : roleNameStore) {
            allElements.add(e.getText());
        }

        for (int i = 0; i < allElements.size(); i++) {
            if (!allElements.get(i).equalsIgnoreCase(apply)) {
                return false;
            }
        }

        return true;
    }

    /**
     * function to change category form SNB page
     * swatantra singh
     *
     * @param category
     * @return
     */
    public ElectronicsSnbPage selectcategories(String category) {
        Mapper.findChildElement(domFile, category, "selectCategory", "a", true).click();
        return this;
    }

    public ElectronicsSnbPage clickOncategories() {
        Mapper.find(domFile, "clickOnCategory").click();
        return this;
    }

    public String getCategoriestext()
    {
        if(Mapper.waitForElementToBeVisible(domFile, "LHSCategory")==true) {
            System.out.println("Here is category" + Mapper.find(domFile, "LHSCategory").getText());
        }
        else
        {
            logger.info("lhs category is itself not visible. Please check!");
        }
        return Mapper.find(domFile,"LHSCategory").getText();
    }
    /**
     * verify checked option in snb page, currently only checking Role, Education, Experiences
     *
     * @param optionToCheck
     * @param value
     * @return
     */
    public boolean verifyCheckedOptionInSidebar(QuikrEnums optionToCheck, String value) {
        List<WebElement> webElements = Mapper.finds(domFile, "checkedOptionsSidebar");

        switch (optionToCheck) {
            case ROLE:
                return webElements.get(0).getText().trim().equals(value);
            case EDUCATION:
                return webElements.get(1).getText().trim().equals(value);
            case EXPERIENCE:
                return webElements.get(2).getText().trim().equals(value);
        }

        return false;
    }

    /**
     * function to verify job sub-category is selected
     */
    public boolean verifyJobSubCategory() {
        return Mapper.waitForElementToBeVisible(domFile, "jobSubCategory");
    }

    public boolean validateCategory(String category) {
        String url = getCurrentLocation();
        if (url.contains(category)) {
            return true;
        }
        else if (url.toLowerCase().contains(category)){
            return true;
        }
        return false;
    }

    /**
     * function to verify Offer ad
     */
    public boolean verifyOfferAd() {
        Mapper.waitForElementToBeVisible(domFile, "apply");
        List<WebElement> applyNameStore = Mapper.finds(domFile, "apply");

        if (applyNameStore.size() == 28) {
            return true;
        }
        return false;
    }

    //Sort date asc
    public void SortDateAsc() {
        if(Mapper.waitForElementToBeClickable(domFile, "dateSortAsc")==true) {
            Mapper.find(domFile, "dateSortAsc").click();
        }
        else
        {
            logger.info("The date sort button was not clicked. Please check!");
        }
    }

    //Sort date desc, On first click
    public void SortDateDesc() {
        Mapper.find(domFile, "dateSortDesc").click();
    }


    //Returns all the date tags from snb page
    public void getDates() {
        List<WebElement> dates = Mapper.finds(domFile, "dateTags");
        List strDates = null;
        for (int i = 0; i < dates.size(); i++) {
            strDates.add(dates.get(i).getText());
        }
    }

    public void SelectMobileCategory()
    {
        List<WebElement> elms = Mapper.finds(domFile,"mobileCategory");
        if (elms.size()==0)
        {
            logger.info("Mobile phone category is not available in the page.");
        }
        else
        {
            logger.info("List shows a web element with mobile phones locators. Trying to click now <first attempt> ....");
            Mapper.find(domFile,"mobileCategory").click();
        }

        List<WebElement> elmsAgain = Mapper.finds(domFile,"mobileCategory");
        if (elmsAgain.size()==0)
        {
            logger.info("I guess it was clicked on the previous attempt itself.");

        }
        else
        {
            logger.info("List still shows a web element with mobile phones locators. Trying to click again <second attempt> ....");
            Mapper.find(domFile,"mobileCategory").click();
        }
        waitForPageToLoad("Mobile-Phones");
    }


    //Returns true if all the time elapsed is appearing in ascending order
    public boolean CheckIfTimeSortedAsc() {
        List<WebElement> timeELapsed = Mapper.finds(domFile, "dateTags");
        int timeElapsedInt[] = new int[timeELapsed.size()];
        Integer tempVar = null;

        for (int i = 0; i < timeELapsed.size(); i++) {
            if (timeELapsed.get(i).getText().contains("second ago")) {
                tempVar = Integer.parseInt(timeELapsed.get(i).getText().substring(0, timeELapsed.get(i).getText().length() - 11));
                timeElapsedInt[i] = tempVar;
            } else if (timeELapsed.get(i).getText().contains("seconds ago")) {
                tempVar = Integer.parseInt(timeELapsed.get(i).getText().substring(0, timeELapsed.get(i).getText().length() - 12));
                timeElapsedInt[i] = tempVar;
            } else if (timeELapsed.get(i).getText().contains("minute ago")) {
                tempVar = Integer.parseInt(timeELapsed.get(i).getText().substring(0, timeELapsed.get(i).getText().length() - 11));
                timeElapsedInt[i] = tempVar * 60;
            } else if (timeELapsed.get(i).getText().contains("minutes ago")) {
                tempVar = Integer.parseInt(timeELapsed.get(i).getText().substring(0, timeELapsed.get(i).getText().length() - 12));
                timeElapsedInt[i] = tempVar * 60;
            } else if (timeELapsed.get(i).getText().contains("hour ago")) {
                tempVar = Integer.parseInt(timeELapsed.get(i).getText().substring(0, timeELapsed.get(i).getText().length() - 9));
                timeElapsedInt[i] = tempVar * 60 * 60;
            } else if (timeELapsed.get(i).getText().contains("hours ago")) {
                tempVar = Integer.parseInt(timeELapsed.get(i).getText().substring(0, timeELapsed.get(i).getText().length() - 10));
                timeElapsedInt[i] = tempVar * 60 * 60;
            } else {
                System.out.println("Now this is a rare case, Look into the timeStamps again....");
            }
        }
        Boolean val = null;
        for (int i = 0; i < timeElapsedInt.length - 1; i++) {
            if (timeElapsedInt[i] < timeElapsedInt[i + 1]) {
                val = true;
            }
        }

        return val;
    }

    //Returns true if all the time elapsed is appearing in descending order.
    public Boolean CheckIfTimeSortedDesc() {
        List<WebElement> timeELapsed = Mapper.finds(domFile, "dateTags");
        String[] timeElapsedStr = new String[timeELapsed.size()];
        int timeElapsedInt[] = new int[timeELapsed.size()];

        for (int i = 0; i < timeELapsed.size(); i++) {
            if (timeELapsed.get(i).getText().contains("")) ;
        }

        return false;
    }


    //Validating chicklets
    public boolean ValidateChickletsPresent() {
        List<WebElement> chicklets = Mapper.finds(domFile, "chicklets");

        if (chicklets.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * click on ad having reply option
     */
    public String clickOnAdHavingReplyOption()
    {
        try {
            String title = Mapper.find(domFile, "adHavingReplyOption").getText();
            Thread.sleep(5000);
            Mapper.find(domFile, "adHavingReplyOption").click();
            return title;
        }
        catch (Exception e)
        {

        }

        return null;
    }

    /**
     * click on ad having chat option
     */
    public String clickOnAdHavingChatOption() {
        String title = Mapper.find(domFile, "adHavingChatOption").getText();
        Mapper.find(domFile, "adHavingChatOption").click();
        return title;
    }

    /**
     * Validate Buy Now Option on Snb
     */
    public boolean validateBuyNowOption() {
        if (!(Mapper.find(domFile, "buyNowOption").isDisplayed())) {
            return false;
        }

        return true;
    }

    /**
     * Validate Sold Status on Snb
     */
    public boolean validateSoldStatusOnSnb() {
        if (!(Mapper.find(domFile, "soldStatusSnB")).isDisplayed()) {
            return false;
        }

        return true;
    }

    /*  * select  Dr type option
      */
    public void selectDrTypeOption(String drType) {
        Mapper.findChildElement(domFile, drType, "drElement", "a", "label").click();
    }

    /**
     * validation on SnB page Services sub-category filter by Dr type name
     */
    public boolean verifyDoctorTypeOptions(String drType) {
        Mapper.waitForElementToBeVisible(domFile, "drTypeName");
        List<WebElement> drTypeNameStore = Mapper.finds(domFile, "drTypeName");
        List<String> allElements = new ArrayList<>();

        for (WebElement e : drTypeNameStore) {
            allElements.add(e.getText());
        }

        for (int i = 0; i < allElements.size(); i++) {
            if (!allElements.get(i).equalsIgnoreCase(drType)) {
                return false;
            }
        }

        return true;
    }


    /**
     * validate Make an Offer status on Snb
     */
    public boolean validateMakeAnOfferStatusOnSnb() {
        if (!(Mapper.find(domFile, "makeAnOfferSnB")).isDisplayed()) {
            return false;
        }

        return true;
    }

    /**
     * Function for clicking on ESCROW ad
     */
    public void clickOnEscrowAd() {
        Mapper.find(domFile, "escrowAdTitle").click();
    }


    /**
     * swatantra
     * hide services pop-up form thet is generated on clicking any subcategory
     */
    public void hideQuikrAdPopUp() {
        Mapper.find(domFile, "servicesPopUP").click();
    }

    /**
     * click on reply button on snb
     * swatantra
     */
    public void clickOnReplyButton() {
        Mapper.find(domFile, "clickonReply").click();
    }

    /**
     * validate reply form elements
     * swatantra
     */
    public boolean replyContent() {
        if (isElementPresent("replyContent"))
            return true;
        return false;
    }

    /**
     * validate reply form elements
     * swatantra
     */
    public boolean replyEmail() {
        if (isElementPresent("replyEmail"))
            return true;

        return false;

    }

    /**
     * validate reply form elements
     * swatantra
     */
    public boolean replyNumber() {
        if (isElementPresent("replyNumber"))
            return true;

        return false;
    }

    /**
     * validate reply form elements
     * swatantra
     */
    public boolean replyCaptcha() {
        if (isElementPresent("replycaptcha"))
            return true;

        return false;
    }


    public boolean validateInlineReply(){
        boolean flag=false;

        if(replyContent()){
            flag=true;
        }
        else {
            return false;
        }

        if(replyEmail()){
            flag=true;
        }
        else {
            return false;
        }
        if(replyNumber()){
            flag=true;
        }
        else {
            return false;
        }
        if(replyCaptcha()){
            flag=true;
        }
        else {
            return false;
        }
        return flag;

    }
    /**
     * function to select create alert button on Snb page
     */
    public void selectCreateAlertButtonOnSnbPage() {
        Mapper.find(domFile, "createAlertButton").click();
        Mapper.waitForElementToBeVisible(domFile, "submitButton");
    }

    /**
     * select  type on snb page for create alert
     *
     * @param
     */
    public void setTypeOnSnBPage(String type) {
//        Mapper.waitForElementToBeVisible(domFile,"adType");
        Mapper.find(domFile, "typeName").click();
//        Mapper.findChildElement(domFile, ad, "adTypeList", "a", false).click();
        WebElement webElement;

        if (Mapper.find(domFile, "typeNameListCheckboxInAlert").isDisplayed()) {
            webElement = Mapper.findChildElement(domFile, type, "typeNameListCheckboxInAlert", "label", true);
        } else {
            webElement = Mapper.findChildElement(domFile, type, "typeNameListLabelInAlert", "a", true);
        }

        webElement.click();
    }

    /**
     * function to select locality on Snb page for create alert
     *
     * @param
     */
    public void setLocalityInAlertOnSnbPage(String setLocality) {
        Mapper.find(domFile, "setLocalityOnSnbPageForAlert").click();
        //Mapper.find(domFile, "Locality").click();

        WebElement webElement;

        if (Mapper.find(domFile, "localityListCheckboxInAlertOnSnbPage").isDisplayed()) {
            webElement = Mapper.findChildElement(domFile, setLocality, "localityListCheckboxInAlertOnSnbPage", "label", true);
        } else {
            webElement = Mapper.findChildElement(domFile, setLocality, "localityListLabelInAlertPopup", "a", true);
        }

        webElement.click();
    }

    /**
     * function to select submit button of create alert on snb page
     */
    public void selectSubmitButtonOfCreateAlertOnSnbPage() {
        Mapper.find(domFile, "submitButton").click();
        Mapper.waitForElementToBeInvisible(domFile, "submitButton");
    }

    /**
     * validation on SnB page for premium ad and non-premium ad
     */
    public boolean validatePremiumAdAndAdOnSnbPage() {

        Mapper.waitForElementToBeVisible(domFile, "premiumAd");
        List<WebElement> premiumAdStore = Mapper.finds(domFile, "premiumAd");
        List<WebElement> adStore = Mapper.finds(domFile, "allAd");
        if (premiumAdStore.size() > 0) {
            if (adStore.size() > premiumAdStore.size()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Function for selecting Electronic & Appliances
     */
    public void clickElectronicAndAppliancesCategory() {
        Mapper.find(domFile, "electronicAndAppliancesCategory").click();
    }

    /**
     * Function for clicking on Buy Now option
     */
    public void clickEscrowBuyNowOption() {
        Mapper.find(domFile, "buyNowOption").click();
    }

    /**
     * function for validating super premium ads
     */
    public boolean validateSuperPremiumAd() {
        if (!(Mapper.find(domFile, "superPreimumAdOption").isDisplayed()))
            return false;
        return true;
    }

    /**
     * function for selecting quikr certified option
     */
    public void selectQuikrCertifiedPhones() {
        Mapper.find(domFile, "quikrCertifiedOption").click();
        Mapper.waitForElementToBeInvisible(domFile, "quikrCertifiedOption");
    }

    /**
     * function for selecting exchange phone option
     */
    public void selectExchangePhones() {
        Mapper.find(domFile, "exchangePhoneOption").click();
        Mapper.waitForElementToBeInvisible(domFile, "exchangePhoneOption");
    }


    public String gettextBikesandScooters() {

        String title = Mapper.find(domFile, "BikesandScooters").getText();

        return title;
    }

    public boolean verifyHousesApartments() {
        Mapper.waitForElementToBeVisible(domFile,"HousesApartmentsforRent");
        if(Mapper.find(domFile,"HousesApartmentsforRent").isDisplayed()){
            return true;
        }
        else
        return false;
    }

    public String gettextHousesApartmentsforRent() {
        String title = Mapper.find(domFile, "HousesApartmentsforRent").getText();

        return title;
    }

    public String gettextHousesApartmentsforSale() {
        String title = Mapper.find(domFile, "HousesApartmentsforSale").getText();

        return title;
    }


    public String gettextLaptopsandComputers() {
        String title = Mapper.find(domFile, "LaptopsandComputers").getText();

        return title;
    }

    public String gettextHomeOfficeFurniture() {
        String title = Mapper.find(domFile, "HomeOfficeFurniture").getText();

        return title;
    }

    public String gettextHomeKitchenAppliances() {
        String title = Mapper.find(domFile, "HomeKitchenAppliances").getText();

        return title;
    }

    public void cityPopupClose() {
        Mapper.waitForElementToBeClickable(domFile,"CityPopUp");
        Mapper.find(domFile, "CityPopUp").click();
    }

    public  void cityPopUpHomesClose()
    {
        Mapper.find(domFile,"CityPopUpHomes").click();
    }
    public boolean ispaginationavailable() {
        List<WebElement> pagination=Mapper.finds(domFile, "pagination");
        if (pagination.size()>=1) {
            return true;
        } else
            return false;
    }

    public void clickBrandFilter(String element) {
        Mapper.findAndReplace(domFile,"mobile_brand_snb",new String[]{element}).click();

    }

    public String matchFilterandSearchResults() {
        String text = Mapper.find(domFile, "top_of_pagead").getText();
        return text;
    }

    public void clickAdsWithPhotos() {
        Mapper.find(domFile, "ads_with_photos").click();
    }

    public boolean isimagesDisplayedOnAds()
    {
        boolean photo=false;
        List<WebElement> photos = Mapper.finds(domFile, "photos_ads");
        for (int i = 0; i <= photos.size(); i++)
        {
            if (Mapper.find(domFile, "photos_ads").isDisplayed())
            {
                photo=true;
            }
            else
            {
                photo=false;
            }
        }
        return photo;
    }


    /*Need to revisit
    */
    public boolean checkMatchingAdonsnb()
    {
        List<WebElement> ads=Mapper.finds(domFile, "googleads");
        if(ads.size()>0)
        {
            return true;
        }
        else
            return false;
    }

    public boolean checkadswithReplySNB()
    {
     List<WebElement> replyads=Mapper.finds(domFile,"adswith_reply");
        if (replyads.size()>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public int returnAdNumberhavingLongestTitle()
    {
        int i,k=0;
        List<WebElement> adstitle=Mapper.finds(domFile,"adTitle");
        List<String> adtext=new ArrayList<String>();

        for(i=0;i<adstitle.size()-1;i++)
        {
            adtext.add(adstitle.get(i).getText().toString());
        }

        for(i=0;i<adtext.size()-1;i++)
        {
            if (adtext.get(i).toString().length()>50 && adtext.get(i).toString().endsWith("..."))
            {
                System.out.println(adtext.get(i).toString());
                System.out.println("Ad id"+i);
                k=i;
                break;
             }
        }

            return k;
    }


    public void clickAdswithReplySNB()
    {
        List<WebElement> replyads=Mapper.finds(domFile,"adswith_reply");
        try{
        replyads.get(1).isDisplayed();
            replyads.get(1).click();
        }

        catch (Exception e){
            replyads.get(0).isDisplayed();
                replyads.get(0).click();

        }

    }

    //Since it is not being used anywhere and if it is , it should not since we have more accurate and robust methods to achieve this.
    //using in verifydownloadAppPopUponReplyandChatSNB
    public void clickReplybuttonOnAdswithReplySNB(){
        List<WebElement> replybuttonOnAds=Mapper.finds(domFile,"adswith_replybutton");
        replybuttonOnAds.get(0).click();
    }

    public boolean clickPremiumAdswithReplySNB() {

        List<WebElement> premiumad=Mapper.finds(domFile, "premium_ads_with_reply");
        if(premiumad.size()>0)
        {
            premiumad.get(0).click();
            return true;
        }
        else
            return false;
    }

    public void clickReply()
    {
        WebElement reply = Mapper.find(domFile, "Reply");
        reply.click();
    }

    public String searchcategorytext()
    {
        String categorytext=Mapper.find(domFile,"CategoryHeading").getText();
        return categorytext;
    }

    public boolean downloadAppPopUp(){
        if (Mapper.find(domFile,"downloadAppPopUP").isDisplayed()){
            return true;
        }
        else
            return false;
    }

    public boolean isRecommendedForYouSectionavailable(){
        return isElementPresent("RecommendedAdsTitleSNB");
    }

    public String gettextGoQuikrHomeLinkRecommendedAdsSNB(){
        return Mapper.find(domFile,"RecommendedAdsGoToQuikrHomeLink").getText();
    }

    public String isSNBPageloaded(){
        Mapper.waitForElementToBeVisible(domFile,"createAlertButton");
        String textcreateAlert=Mapper.find(domFile,"createAlertButton").getText();

        return textcreateAlert;
    }

    public void toggleOnlineUsers()
    {
        String url = getCurrentLocation();
        openUrl(url+"?online=1");
    }

    public boolean validateAllOnlineUsers()
    {
        boolean retVal=false;
        List<WebElement> elm = Mapper.finds(domFile,"offlineUsers");
        for (int i=0;i<elm.size();i++)
        {
            if (elm.get(i).isDisplayed()==true)
            {
                return false;
            }
            else
            {
                retVal = true;
            }
        }
        return retVal;
    }

    public String clickMakeAnOfferAdTitle()
    {
        String adTitle = null;
        try {
            Thread.sleep(5000);
            if (Mapper.waitForElementToBeVisible(domFile, "makeAnOfferButton") == true) {
                logger.info("Got visible....");
                if (Mapper.find(domFile, "makeAnOfferButton").isDisplayed() == true) {
                    logger.info("Got displayed....");
                    adTitle = Mapper.find(domFile,"makeAnOfferButton").getText();
                    logger.info("Got the title :: "+adTitle);
                    Mapper.find(domFile, "makeAnOfferButton").click();
                    waitForPageToLoad(adTitle.split(" ")[0]);
                    logger.info("Executed wait for page to load....");
                } else {
                    logger.info("First ad in snb is not displayed.");
                }
            } else {
                logger.info("Either there are no ads on SNB or some other problem which you need to check!");
            }
        }catch (Exception e){}
        return adTitle;
    }

    public String clickBuyNowAdTitle()
    {
        String adTitle = null;
        try {
            Thread.sleep(5000);
            if (Mapper.waitForElementToBeVisible(domFile, "buyNowButton") == true) {
                logger.info("Got visible....");
                if (Mapper.find(domFile, "buyNowButton").isDisplayed() == true)
                {
                    logger.info("Got displayed....");
                    adTitle = Mapper.find(domFile, "buyNowButton").getText();
                    logger.info("Got the title :: "+adTitle);
                    Mapper.find(domFile, "buyNowButton").click();
                    waitForPageToLoad(adTitle.split(" ")[0]);
                    logger.info("Executed wait for page to load....");
                } else {
                    logger.info("First ad in snb is not displayed.");
                }
            } else {
                logger.info("Either there are no ads on SNB or some other problem which you need to check!");
            }
        }catch (Exception e){}
        logger.info(adTitle);
        return adTitle;
    }
}
