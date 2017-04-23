package com.quikr.app.android.postAdV2;

import com.quikr.app.android.AppAndroidPageBase;
import com.quikr.utils.enums.app.QuikrAppEnums;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra on 19/2/16.
 */
public class PostAdV2Page extends AppAndroidPageBase {
    public PostAdV2Page(AppiumDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("POSTADV2PAGE_DOMELEMENTS");

    /**
     * swipe on postAd Page
     */
    public Integer[] getCordinates() {
        // for long swipe from botton to up use "Y1-Y2"
        //for small swipe use "y3-Y2"
        Mapper.waitForElementToBeVisible(domFile, "categoryTitle");
        int Y2 = Mapper.find(domFile, "headerActionBAr").getLocation().getY();
        int Y1 = Mapper.find(domFile, "categoryTitle").getLocation().getY();
        Integer cordinates[] = {Y1, Y2,};
        return cordinates;

    }

    public void sendTitle() {
        Mapper.waitForElementToBeVisible(domFile, "title");
        Mapper.finds(domFile, "title").get(0).sendKeys("hdhgfasjfaiegkjs fdkidfuiudlj");
    }

    /**
     * wait for post ad page to load
     */
    public void waitForPapToLoad() {
        if (!isElementPresent("selectcategoryOnPAP"))
            Mapper.waitForElementToBeVisible(domFile, "selectcategoryOnPAP", 30);
    }

    /**
     * send input text to post Ad attribute
     */
    public int checkHomePAgeVarient() {
        int flag = 0;
        if (isElementPresent("homePageVariantCheck"))
            flag = 1;
        return flag;

    }

    /**
     * hide keyboard after entering distance
     */
    public void hideKeyBoardAfterEnteringDistance() {
        if (!isElementPresent("color"))
            navigateBack();
    }

    /**
     * post your ad
     */
    public void postYourAd() {
        Mapper.scroll("Post Your Ad");
        if (!isElementPresent("postYourAdButton"))
            hideKeyboard();
        Mapper.find(domFile, "postYourAdButton").click();
    }

    /**
     * aclidate post ad success
     *
     * @return
     */
    public boolean validatePostAd() {
        Mapper.waitForElementToBeVisible(domFile, "validatepostAd", 10);
        return isElementPresent("validatepostAd");
    }

    /**
     * skip post ad helper to move to make ad premium
     */
    public void skipPostAdCarousalCard() {
        if (isElementPresent("skiplookingForExchange")) {
            Mapper.find(domFile, "skiplookingForExchange").click();
        }
    }

    /**
     *
     */
    public void swipeToPostAd(int y1, int y2) {
//        if (!isElementPresent("postYourAdButton")) {
//            navigateBack();
        explicitWait(2);
        verticalSwipeWithCordinates(y1, y2);
//        }

    }

    /**
     * send name
     */
    public void sendInputTextname(String name) {
        Mapper.waitForElementToBeVisible(domFile, "name");
        Mapper.find(domFile, "name").click();
        Mapper.find(domFile, "name").sendKeys(name);
    }

    public void postAdAsStartFresh() {
        Mapper.waitForElementToBeVisible(domFile, "postAdStartFresh");
        Mapper.find(domFile, "postAdStartFresh").click();
    }

    /**
     * continue posting ad from where you left
     */
    public void postAdAsContine() {
        Mapper.waitForElementToBeVisible(domFile, "postAdwithContinue");
        Mapper.find(domFile, "postAdwithContinue").click();
    }

    public void clickOnCanclePostAd() {
        Mapper.waitForElementToBeVisible(domFile, "QuikrPositiveAkcnowledgwment");
        Mapper.find(domFile, "QuikrPositiveAkcnowledgwment").click();
    }

    /**
     * hide keyboard if mimimum price is not visible
     */
    public void hideKeyBoardIfMinimumPriceIsNotVisible() {
        if (!isElementPresent("minimumPrice"))
            navigateBack();
    }

    /**
     *
     */
    public void hideKeyBoardIfBrandIsNotVisible() {
        if (!isElementPresent("BrandName"))
            navigateBack();
    }

    /**
     * send mobile number
     */

    public void enterMobileNumber(String text) {
        if (!isElementPresent("mobileNumber"))
            navigateBack();
        Mapper.find(domFile, "mobileNumber").sendKeys(text);
    }

    /**
     * many times test is entered without keyboard being displayed, use this function to handle navigate back after entering text in case of keyBoard not displayed
     */
    public void handleHideKeyBord() {
        if (isElementPresent("hideKeyBordHAndler"))
            Mapper.find(domFile, "hideKeyBordHAndler").click();
    }

    /**
     * hide keyBoard after entering price
     */
    public void hideKeyBoardAfterEnteringPriceForCars() {
        if (!isElementPresent("year"))
            navigateBack();

    }

    /**
     * change language on pap
     */
    public void clickOnChangheLanguageIcon() {
        Mapper.waitForElementToBeVisible(domFile, "languageChangePAP");
        Mapper.find(domFile, "languageChangePAP").click();
    }

    /**
     * verify message on clicking change language on pap
     */
    public boolean verifyPopUpMsgOnClickingChangeLanguageButton() {
        Mapper.waitForElementToBeVisible(domFile, "popUpMessage");
        return (Mapper.find(domFile, "popUpMessage").getText().trim().equalsIgnoreCase("Changing the language will clear the form data. Do you want to proceed?".trim()));

    }

    /**
     * verify on clicking change language button (Yes& No Button appears)
     */
    public boolean verifyActionAfterClickingLAnguageSelectionButton() {
        return (isElementPresent("proceedToChangeLAnguage") && isElementPresent("cancelChangingLanguage"));
    }

    /**
     * click on yes button after clicking language change button to proceed to select language
     */
    public void clickOnYesButtonToSelectLanguage() {
        Mapper.waitForElementToBeVisible(domFile, "proceedToChangeLAnguage");
        Mapper.find(domFile, "proceedToChangeLAnguage").click();
    }

    /**
     * extract header text
     */
    public String headerText() {
        Mapper.waitForElementToBeVisible(domFile, "headerText");
        return Mapper.find(domFile, "headerText").getText();

    }

    /**
     * wait for category paage to load
     */
    public void waitForCategorypagetoLoad() {
        Mapper.waitForElementToBeVisible(domFile, "papCatTitle", 15);
    }

    /**
     * select category
     */
    public void selectcategory() {
        Mapper.waitForElementToBeVisible(domFile, "selectcategoryOnPAP", 15);
        Mapper.finds(domFile, "selectcategoryOnPAP").get(0).click();
    }

    /**
     *
     */
    public String getTextFromTextBoxOnPap(int i) {
        Mapper.waitForElementToBeVisible(domFile, "postAdTestBox");
        return Mapper.finds(domFile, "postAdTestBox").get(i).getText();
    }


    public boolean validateMinimumPrice(String field) {
        boolean flag = false;
        try {
            Mapper.scroll(field);
            List<WebElement> elements = Mapper.finds(domFile, "postAdTestBox");
            List<String> list = new ArrayList<String>();
            for (WebElement e : elements) {
                list.add(e.getText());
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toLowerCase().contains(field.toLowerCase().trim()) || equals(field.toLowerCase().trim())) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean validateSellForMe(String field) {
        boolean flag = false;
        List<WebElement> elements = Mapper.finds(domFile, "headerText");
        List<String> list = new ArrayList<String>();
        for (WebElement e : elements) {
            list.add(e.getText());
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().contains(field.toLowerCase().trim()) || equals(field.toLowerCase().trim())) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean validateSyncScanRunTest() {
        Mapper.scroll("Post Your Ad");
        if (Mapper.waitForElementToBeVisible(domFile, "runTestSyncScan", 5)) {
            return true;
        } else
            return false;
    }

    public boolean validateSyncScanStripeOnEditAd(){
        Mapper.scroll("Save");
        if (Mapper.find(domFile,"viewScannerReport").isDisplayed())
        {
            return true;
        }
        else
            return false;
    }

    public boolean validateScannerRunSuccess() {
        if (Mapper.waitForElementToBeVisible(domFile, "scannerScoreText", 30)) {
            return true;
        } else
            return false;
    }

    public void viewScannerReport() {
        Mapper.waitForElementToBeVisible(domFile, "viewScannerReport");
        Mapper.find(domFile, "viewScannerReport").click();
    }

    public boolean validateScannerReportOpen() {
        if (Mapper.waitForElementToBeVisible(domFile, "scannerScoreInReport", 20)) {
            return true;
        } else
            return false;
    }

    public void runQuikrScanner() {
        try {
            Mapper.waitForElementToBeVisible(domFile, "runTestSyncScan");
            Mapper.find(domFile, "runTestSyncScan").click();
            Mapper.waitForElementToBeVisible(domFile, "proceedToRunScanner");
            Mapper.find(domFile, "proceedToRunScanner").click();
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateMinimumPriceAmount(String amount) {
        boolean flag = false;
        Mapper.scroll("Minimum Price");
        List<WebElement> elements = Mapper.finds(domFile, "postAdTestBox");
        List<String> list = new ArrayList<String>();
        for (WebElement e : elements) {
            list.add(e.getText());
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().contains(amount.toLowerCase().trim()) || equals(amount.toLowerCase().trim())) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean validateWarehouseField(String field) {
        boolean flag = false;
        Mapper.scroll(field);
        List<WebElement> elements = Mapper.finds(domFile, "checkbox");
        List<String> list = new ArrayList<String>();
        for (WebElement e : elements) {
            list.add(e.getText());
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().contains(field.toLowerCase().trim()) || equals(field.toLowerCase().trim())) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean validateMinimumPriceForRemainingCategories(String field, String remainingCats) {
        boolean flag = false;
        String[] array = remainingCats.split(",,");
        for (int i = 0; i < array.length - 1; i++) {
            Mapper.scroll("Category Details");
            selectelementWithoutScroll(array[i], QuikrAppEnums.PostAd_category);
            Mapper.waitForElementToBeClickable(domFile, "papCatTitle");
            selectElements(array[i + 1], QuikrAppEnums.PostAd_categorylist);
            System.out.println("Checking Minimum price for category : " + array[i + 1]);

            waitForPapToLoad();

            Mapper.scroll(field);
            List<WebElement> elements = Mapper.finds(domFile, "postAdTestBox");
            List<String> list = new ArrayList<String>();
            for (WebElement e : elements) {
                list.add(e.getText());
            }
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).toLowerCase().contains(field.toLowerCase().trim()) || equals(field.toLowerCase().trim())) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public boolean validateWarehouseFieldForRemainingCategories(String field, String remainingCats) {
        boolean flag = false;
        String[] array = remainingCats.split(",,");
        for (int i = 0; i < array.length - 1; i++) {
            Mapper.scroll("Category Details");
            selectelementWithoutScroll(array[i], QuikrAppEnums.PostAd_category);
            Mapper.waitForElementToBeClickable(domFile, "papCatTitle");
            selectElements(array[i + 1], QuikrAppEnums.PostAd_categorylist);
            System.out.println("Checking Warehouse for category : " + array[i + 1]);

            waitForPapToLoad();

            Mapper.scroll(field);
            List<WebElement> elements = Mapper.finds(domFile, "checkbox");
            List<String> list = new ArrayList<String>();
            for (WebElement e : elements) {
                list.add(e.getText());
            }
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).toLowerCase().contains(field.toLowerCase().trim()) || equals(field.toLowerCase().trim())) {
                    flag = true;
                }
            }
        }
        return flag;
    }
    /**
     * click on sub categoy to Select subCAt
     */
    /**
     * select category
     */
    public void selectsubCategory() {
        Mapper.waitForElementToBeVisible(domFile, "selectcategoryOnPAP", 15);
        Mapper.finds(domFile, "selectcategoryOnPAP").get(1).click();
    }

    /**
     * edit mobile number in edit ad
     */
    public void editMobileNumber(String number) {
        List<WebElement> elements = Mapper.finds(domFile, "postAdTestBox");
        List<String> list = new ArrayList<String>();
        for (WebElement e : elements) {
            list.add(e.getText());
        }
        //System.out.println(list.size());
        Mapper.finds(domFile, "postAdTestBox").get(list.size() - 1).click();
        setTextFromKeyBoard(number);
    }

    /**
     * click on view my ads
     */
    public void selectViewMyAd() {
        Mapper.waitForElementToBeClickable(domFile, "viewMyAd");
        Mapper.find(domFile, "viewMyAd").click();
        Mapper.waitForElementToBeInvisible(domFile, "viewMyAd");
    }

    /**
     * check price displayed in make premium plan
     */
    public int priceDisplayedInMakePremiumPlan() {
        Mapper.waitForElementToBeVisible(domFile, "premiumPlanImage1");
        String cost = (Mapper.find(domFile, "premiumPlanImage1").getText());
        String sub = cost.substring(1, 4);
        int cost1 = Integer.parseInt(sub);
        return cost1;
    }

    /**
     * get error msg text
     *
     * @return
     */
    public String errorMessageText() {
        Mapper.waitForElementToBeVisible(domFile, "errorMessagePostAd", 10);
        return (Mapper.find(domFile, "errorMessagePostAd").getText());
    }

    /**
     * validate post ad header
     */
    public boolean verifyPostAdHeader() {
        return (isElementPresent("navigateBAckOnHeader") && isElementPresent("postAdHeaderText"));
    }

    /**
     * experiment
     */
    public void enterDistance(String dist) {
        Mapper.find(domFile, "kmDrivenXpath").sendKeys(dist);
    }

    public void enterTitle(String title) {
        Mapper.find(domFile, "AdTitleXpath").sendKeys(title);
        hideKeyboard();
    }

    public void enterDescription(String desc) {
        Mapper.find(domFile, "AdDescriptionXpath").click();
        setTextFromKeyBoard(desc);
        hideKeyboard();
    }

    public void enterprice(String price) {
        Mapper.find(domFile, "enterPriceXpath").sendKeys(price);
        hideKeyboard();
    }

    public void enterMinprice(String minprice) {
        Mapper.find(domFile, "enterMinPriceXpath").sendKeys(minprice);
        hideKeyboard();
    }

    public void enterAddress(String Address) {
        Mapper.scroll("Address *");
        Mapper.find(domFile, "address").sendKeys(Address);
        hideKeyboard();
    }

    public void selectLocality(String Locality) {
        try {
            Mapper.find(domFile,"localityDropdownOne").click();
        }
        catch (NullPointerException e)
        {
            Mapper.find(domFile, "localityDropdown").click();
            e.printStackTrace();
        }
        selectelementWithoutScroll(Locality, QuikrAppEnums.PAP_NEW_LOCALITY);
    }

    public void selectAuctionPeriod(String period){
        Mapper.scroll("Run Auction For *");
        Mapper.find(domFile,"auctionPeriodDropdownOne").click();
        Mapper.findAndReplace(domFile,"auctionPeriodValue",new String[]{period}).click();
    }

    public void enterAuctionAmount(String amount){
        Mapper.find(domFile, "enterAuctionAmount").sendKeys(amount);
        hideKeyboard();
    }

    public void enterPincode(String Pincode) {
        Mapper.scroll("Pincode *");
        Mapper.find(domFile, "pincode").sendKeys(Pincode);
        hideKeyboard();
    }

    public void enterName() {
        Mapper.find(domFile, "enterName").sendKeys("rahul dutt");
    }

    public void enterNumber(String num) {
        Mapper.find(domFile, "enterMobileNumber").sendKeys(num);
        hideKeyboard();
    }

    public void enterEmail(String email) {
        Mapper.find(domFile, "enterEmail").sendKeys(email);
        hideKeyboard();
    }

    public boolean ifMandateErrorPresent(String field) {
        boolean flag = false;
        Mapper.scroll(field);
        List<WebElement> elements = Mapper.finds(domFile, "errorMessage");
        List<String> list = new ArrayList<String>();
        for (WebElement e : elements) {
            list.add(e.getText());
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toLowerCase().contains(field.toLowerCase().trim()) || equals(field.toLowerCase().trim())) {
                flag = true;
            }
        }
        return flag;
    }

    public void cancelPosting(boolean action) {
        if (action == true) {
            Mapper.find(domFile, "cancellPostingYes").click();
        } else {
            Mapper.find(domFile, "cancellPostingNo").click();
        }
    }

    public WebElement getPostAdScrollView() {
        return Mapper.find(domFile, "postAdScreen");
    }

    public WebElement getPostAdCategoryScrollView() {
        return Mapper.find(domFile, "categorylist");
    }

    public boolean backToPostAd() {
        if (Mapper.find(domFile,"papCategoryDetailsHeader").isDisplayed()){
            return true;
        }
        else return false;
    }

    public void moveToExchangeCarPage() {
        Mapper.waitForElementToBeClickable(domFile, "radioButton");
        Mapper.find(domFile, "radioButton").click();
    }

    //Methods to select Category/Sub Cat and fill details on Mobiles Ad
    public void selectCat(String CatName) {
        selectelementWithoutScroll(CatName, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
    }

    public void selectSubCat(String SubCatName) {
        selectelementWithoutScroll(SubCatName, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
    }

    public void selectCondition(String Condition) {
        selectElements(Condition, QuikrAppEnums.PostAd_RadioButton);
    }

    public void selectBrand(String Field, String Brand) {
        selectElements(Field, QuikrAppEnums.PostAd_SelectFromDropDown);
        selectelementWithoutScroll(Brand, QuikrAppEnums.CATEGORY_LOCATION);
    }

    public void selectModel(String Field, String Model) {
        selectElements(Field, QuikrAppEnums.PostAd_SelectFromDropDown);
        selectelementWithoutScroll(Model, QuikrAppEnums.CATEGORY_LOCATION);
    }

    public void selectPostAdWOPhotos() {
        Mapper.find(domFile, "withoutPhoto").click();
    }

    public void exitAd(){
        try {
            Mapper.waitForElementToBeVisible(domFile, "adView", 5);
            if (Mapper.find(domFile, "adView").isDisplayed()) {
                navigateBack();
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void handleOverlay(){
        Mapper.waitForElementToBeVisible(domFile,"overlay",5);
        if (Mapper.find(domFile,"overlay").isDisplayed());{
        navigateBack();
        }
    }
}
