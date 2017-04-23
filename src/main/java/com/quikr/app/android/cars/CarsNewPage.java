package com.quikr.app.android.cars;

import com.quikr.app.android.AppAndroidPageBase;
import com.quikr.app.android.Constants.MobileConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra on 10/11/15.
 */
public class CarsNewPage extends AppAndroidPageBase {

    public CarsNewPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ANDROID_CARS_NEW_DOM_FILE");

    /**
     * swipe on cars chp to scrool down
     */
    public void swipeCarsChp() {
        Mapper.waitForElementToBeVisible(domFile, "MSPFromCHP");
        Mapper.VerticalSwipe(domFile, "searchChp", "MSPFromCHP");
    }

    /**
     * validate elements present on chp before swiping
     */

    public boolean vallidateElementsOnChpbeforeSwipe() {
        return (isElementPresent("bikesFromCHP") & isElementPresent("carsFromCHP") & isElementPresent("commercialVechilesFromCHP") & isElementPresent("searchChp"));
    }

    /**
     * validate elements on chp after scrolling down
     */
    public boolean validateElementsOnCHPAfterScrolling() {
        return (isElementPresent("popolarAdFromCHP"));
    }

    /**
     * validate snb menu
     */
    public boolean validateSnbMenu() {

        return (isElementPresent("Sort") & isElementPresent("snbMSP") & isElementPresent("snbInspected"));
    }

    /**
     * open Ads from snb
     */
    public void openAdFromSnb(int i) {
        Mapper.waitForElementToBeVisible(domFile, "snbAdTitle");
        Mapper.finds(domFile, "snbAdTitle").get(i).click();
    }

    /**
     * click on chat button to open caht
     */
    public void clickOnChatButton() {
        Mapper.waitForElementToBeVisible(domFile, "chat");
        Mapper.find(domFile, "chat").click();
    }

    /**
     * extract price from snb
     */
    public String snbPrice(int i) {
        String price = Mapper.finds(domFile, "snbPrice").get(i).getText();
        return price;
    }

    /**
     * validate inspected logo is displayed when inspected filter is on
     */
    public boolean validateInspetedLogo() {
        return isElementPresent("snbInspectedCertified");
    }

    /**
     * click on inspected filter on snb menu to get inspected ads only
     */
    public void clickOnInspectedCheckBoxFromSnbMenu() {
        Mapper.waitForElementToBeVisible(domFile, "snbInspected");
        Mapper.find(domFile, "snbInspected").click();
    }

    /**
     * click on filter button , select filter options
     */
    public void clickOnFilterButton()

    {
        Mapper.waitForElementToBeVisible(domFile, "Filter");
        Mapper.find(domFile, "Filter").click();
    }

    /**
     * validate show more filters appear on filter page
     */
    public boolean validateShowMoreFiltersAppear() {
        return isElementPresent("showMoreFilters");
    }

    /**
     * validate show less filters appear on filter page after clicking show more filters
     */
    public boolean validateShowLessFiltersAppearAfterClickingShowMore() {
        return isElementPresent("ShowlessFilters");
    }

    /**
     * Apply filters
     */
    public void clcikOnApplyFilters() {
        Mapper.waitForElementToBeVisible(domFile, "applyFilters");
        Mapper.find(domFile, "applyFilters").click();
    }

    /**
     * click on search field on chp to search ad
     */
    public void clickOnSearchOnChp() {
        Mapper.waitForElementToBeVisible(domFile, "searchChp");
        Mapper.find(domFile, "searchChp").click();
    }

    /**
     * click on search results to gets ads
     */
    public void SubmitSearch() {
        Mapper.waitForElementToBeVisible(domFile, "searchResults");
        Mapper.find(domFile, "searchResults").click();
    }


    /**
     * ckeck all the fields of carnation report
     */
    public List<String> validateSortingForPrice() {
        //Storing all Elements on NO fikar options in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "snbPrice");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options) {
            list.add(e.getText().replaceAll("\\D", ""));
        }
//        Thread.sleep(2000);
        Mapper.VerticalSwipe(domFile, "snbCategory", "snbPrice");
        List<WebElement> options1 = Mapper.finds(domFile, "snbPrice");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : options1) {
            list1.add(e.getText().replaceAll("\\D", ""));
        }

        Mapper.VerticalSwipe(domFile, "snbCategory", "snbPrice");
        List<WebElement> options2 = Mapper.finds(domFile, "snbPrice");
        List<String> list2 = new ArrayList<String>();
        for (WebElement e : options2) {
            list2.add(e.getText().replaceAll("\\D", ""));
        }

        Mapper.VerticalSwipe(domFile, "snbCategory", "snbPrice");
        List<WebElement> options3 = Mapper.finds(domFile, "snbPrice");
        List<String> list3 = new ArrayList<String>();
        for (WebElement e : options3) {
            list3.add(e.getText().replaceAll("\\D", ""));
        }


        List<String> finallist = new ArrayList<String>(list);
        finallist.addAll(list1);
        finallist.addAll(list2);
        finallist.addAll(list3);

        //Removing Duplicates from strings
//        HashSet hs = new HashSet();
//        hs.addAll(finallist);
//        finallist.clear();
//        finallist.addAll(hs);
//        for (String obj3 : finallist)
//            System.out.println(obj3);

        //check if all options are present
//
//        int flag = 0;
//        for (int i = 0; i < finallist.size(); i++)
//        {
//
//            for (int j = 0; j < MobileConstants.NoFikarOptions.size(); j++)
//            {
//                if (finallist.get(i).equals(MobileConstants.NoFikarOptions.get(j)))
//                {
//                    flag = 1;
//                    System.out.println(finallist.get(i) + MobileConstants.NoFikarOptions.get(j));
//                    break;
//                }
//            }
//        }
//        System.out.println(flag);
//        if (flag==1)
//            return true;
//        else return false;

        return finallist;
    }

    /**
     * open Ads from VAP
     */
    public void openAdFromVap() {
        Mapper.waitForElementToBeVisible(domFile, "vapAdTitleCars");
        Mapper.find(domFile, "vapAdTitleCars").click();
    }

    /**
     * get vap ad title
     */
    public String AdTitle() {
        String adTitle = Mapper.find(domFile, "vapAdTitleCars").getText().toString();
        return adTitle;
    }

    /**
     * swipe on snb to view more ADs
     */

    public void swipeOnCarsSNB() {
        Mapper.waitForElementToBeVisible(domFile, "snbMenuHeader");
        Mapper.VerticalSwipe(domFile, "snbMenuHeader", "Filter");
    }

    /**
     * validate Calaulate Msp is present on Cars Chp
     */
    public boolean validateMSPButtonOnCarsChp() {
        return (isElementPresent("MSPFromCHP"));
    }

    /**
     * validate Calaulate Msp is present on Cars snb
     */
    public boolean validateMSPButtonOnCarsSNB() {
        Mapper.waitForElementToBeVisible(domFile, "mspFromSnb");
        String msp = Mapper.finds(domFile, "mspFromSnb").get(1).getText();
        return (msp.equalsIgnoreCase("MSP"));
    }

    /**
     * select brand form Msp
     */
    public void selectBrand() {
        Mapper.waitForElementToBeVisible(domFile, "mspBrand");
        Mapper.find(domFile, "mspBrand").click();
    }

    /**
     * select model form Msp
     */
    public void selectModel() {
        Mapper.waitForElementToBeVisible(domFile, "mspModel");
        Mapper.find(domFile, "mspModel").click();
    }

    /**
     * select varient form Msp
     */
    public void selectVarient() {
        Mapper.waitForElementToBeVisible(domFile, "mspVarient");
        Mapper.find(domFile, "mspVarient").click();
    }

    /**
     * select year form Msp
     */
    public void selectYearOfPurchase() {
        Mapper.waitForElementToBeVisible(domFile, "mspYear");
        Mapper.find(domFile, "mspYear").click();
    }

    /**
     * select kmDriven form Msp
     */
    public void selectKmDriven() {
        Mapper.waitForElementToBeVisible(domFile, "mspKmDriven");
        Mapper.find(domFile, "mspKmDriven").click();
    }

    /**
     * select submitbutton form Msp
     */
    public void calculateMSP() {
        Mapper.waitForElementToBeVisible(domFile, "calculateMsp");
        Mapper.find(domFile, "calculateMsp").click();
    }

    /**
     * select bikes on MSP
     */
    public void selectBikesOnMspPAge() {
        Mapper.waitForElementToBeVisible(domFile, "mspBikes");
        Mapper.find(domFile, "mspBikes").click();
    }

    /**
     * select cars on MSP
     */
    public void selectCarsOnMspPAge() {
        Mapper.waitForElementToBeVisible(domFile, "MspCars");
        Mapper.find(domFile, "MspCars").click();
    }

    /**
     * validate MSP field when Bikes is selected
     */

    public boolean validateBikesFieldOnMSPCalculationPage() {
        return (!isElementPresent("mspVarient"));
    }

    /**
     * select Msp from Cars snb
     */
    public void selectMspFromSnb() {
        Mapper.waitForElementToBeVisible(domFile, "mspFromSnb");
        Mapper.finds(domFile, "mspFromSnb").get(1).click();

    }

    /**
     * validate cars and Bikes present on MSP home Page
     */

    public boolean validateCarsAndBikesOnMspHomePage() {
        return (isElementPresent("MspCars") & isElementPresent("mspBikes"));
    }

    /**
     * validate calculated msp
     */
    public boolean validateMsp() {
        return (isElementPresent("ValidateMsp"));
    }

    /**
     * re calculate msp using Calculate msp again button
     */

    public void calaulateMspAgain() {
        Mapper.waitForElementToBeVisible(domFile, "calculateMspAgain");
        Mapper.find(domFile, "calculateMspAgain").click();
    }

    /**
     * validate inspected toggle buttonsnb menu
     */
    public boolean validateInspectedtoggleButtonSnbMenu() {

        return (isElementPresent("snbInspected"));
    }

    /**
     * validate Inspected report on avp
     */
    public boolean verifyInspectionReportAppearOnVap() {
        return isElementPresent("vapInspectionReport");

    }

    /**
     * click on Msp Button on cars CHP
     */
    public void clickOnMspButtonOnChp() {
        Mapper.waitForElementToBeVisible(domFile, "MSPFromCHP");
        Mapper.find(domFile, "MSPFromCHP").click();
    }

    /**
     * click on Get inspected from chp
     */
    public void clickOnGetInspectedButtonOnChp() {
        Mapper.waitForElementToBeVisible(domFile, "MSPFromCHP");
        Mapper.VerticalSwipe(domFile, "searchChp", "MSPFromCHP");
        Mapper.waitForElementToBeVisible(domFile, "getInspetedFromCHP");
        Mapper.find(domFile, "getInspetedFromCHP").click();

    }

    /**
     * validate inspected button on chp is clickcble
     */

    public boolean validateGetInspectedButtonOnChpIsCliclable() {
        return isElementPresent("validateInspectedButtonOnChp");
    }

    /**
     * validate read reviews butoon present on CHP
     */

    public boolean validateReadReviewButtonIsPresent() {
        return isElementPresent("readReviews");
    }

    /**
     * click on read review button on chp
     */
    public void clickOnReadReviewsButtonOnChp() {
        Mapper.waitForElementToBeVisible(domFile, "MSPFromCHP");
        Mapper.VerticalSwipe(domFile, "searchChp", "MSPFromCHP");
        Mapper.VerticalSwipe(domFile, "city", "Filter");
        Mapper.waitForElementToBeVisible(domFile, "readReviews");
        Mapper.find(domFile, "readReviews").click();

    }

    /**
     * validate read review button is clickable
     */
    public boolean validateReadReviewsButtonIsClickable() {
        return isElementPresent("Reviews");
    }

    /**
     * click on read news button on chp
     */
    public void clickOnReadNewsButtonOnChp() {
        Mapper.waitForElementToBeVisible(domFile, "MSPFromCHP");
        Mapper.VerticalSwipe(domFile, "searchChp", "MSPFromCHP");
        Mapper.VerticalSwipe(domFile, "city", "Filter");
        Mapper.waitForElementToBeVisible(domFile, "ReadNews");
        Mapper.find(domFile, "ReadNews").click();

    }

    /**
     * validate read news button is clickable
     */
    public boolean validateReadNewsButtonIsClickable() {
        Mapper.waitForElementToBeVisible(domFile, "News");
        return isElementPresent("News");
    }

    /**
     * click on watch videos button on chp
     */
    public void clickOnWatchVideosButtonOnChp() {
        Mapper.waitForElementToBeVisible(domFile, "MSPFromCHP");
        Mapper.VerticalSwipe(domFile, "searchChp", "MSPFromCHP");
        Mapper.VerticalSwipe(domFile, "city", "Filter");
        Mapper.waitForElementToBeVisible(domFile, "watchVideos");
        Mapper.find(domFile, "watchVideos").click();

    }

    /**
     * validate read news button is clickable
     */
    public boolean validateWatchVideosButtonIsClickable() {
        Mapper.waitForElementToBeVisible(domFile, "videos");
        return isElementPresent("videos");
    }

    /**
     * swipe to create alert on cars snb
     */
    public void scrolToCreateAlert() {
        Mapper.scroll("Set a Quikr Alert for Chevrolet ads in Bangalore.");
    }

    /**
     * click on create alert fronm Cars Snb
     */
    public void clickOnCreateAlert() {

        if (!isElementPresent("createAlertCarsSnb"))
            Mapper.VerticalSwipe(domFile, "snbMenuHeader", "Filter");
        Mapper.find(domFile, "createAlertCarsSnb").click();
    }

    /**
     * provide mobile number in create alert form
     */
    public void provideMobileNumberForAleartCreation() {
        Mapper.waitForElementToBeVisible(domFile, "alertPhoneNumber");
        Mapper.find(domFile, "alertPhoneNumber").sendKeys("9955199163");
    }

    /**
     * provide email  in create alert form
     */
    public void provideEmailForAleartCreation() {
        Mapper.waitForElementToBeVisible(domFile, "alertEmail");
        Mapper.find(domFile, "alertEmail").sendKeys("swatquikr@gmail.com");
    }

    /**
     * submit alert
     */
    public void submitAlertRequest() {
        if (!isElementPresent("submitAlert"))
            navigateBack();
        Mapper.find(domFile, "submitAlert").click();
    }

    /**
     * validate alert created
     */
    public boolean validateAlertCreated() {
        Mapper.waitForElementToBeVisible(domFile, "Filter");
        return (!isElementPresent("alertEmail") && !isElementPresent("alertPhoneNumber"));
    }

    /**
     * select brand on filter page
     */
    public void selectBrandOnFilterPage() {
        Mapper.waitForElementToBeVisible(domFile, "filterBrandAndFuel");
        Mapper.finds(domFile, "filterBrandAndFuel").get(0).click();
    }

    /**
     * validate create alert main text should contain Brand selected in filter
     */
    public String validateCreateLertMainText() {
        if (!isElementPresent("createAlertCarsSnb"))
            Mapper.VerticalSwipe(domFile, "snbMenuHeader", "Filter");
        String brand = Mapper.find(domFile, "createAlertCarsSnb").getText();
        return brand;

    }

    /**
     * select bikes from CHP
     */
    public void clickOnBikesFromCHP() {
        Mapper.waitForElementToBeVisible(domFile, "bikesFromCHP");
        Mapper.find(domFile, "bikesFromCHP").click();
    }

    /**
     * vlidate inspected filter on filter page
     */

    public boolean inspectedFilterIsPresent() {
        return isElementPresent("inspectedFilter");
    }

    /**
     * Inspection Report Tab instead of No Fikar tab  should apear
     */
    public String inspectionReportText() {
        Mapper.waitForElementToBeVisible(domFile, "vapInspectionReport");
        String InspectionReport = Mapper.find(domFile, "vapInspectionReport").getText();
        return InspectionReport;
    }

    /**
     * inspected logo is present on vap
     */
    public boolean inspectedLogoIsPresentOnVap() {
        return isElementPresent("vapInspected");
    }

    /**
     * click on inspection report
     */
    public void clickOnInspectionReport() {
        Mapper.waitForElementToBeVisible(domFile, "vapInspectionReport");
        Mapper.find(domFile, "vapInspectionReport").click();
    }

    /**
     * get carnation report
     */
    public boolean carnationReport() {
        //Storing localities in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "carnationtext");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options) {
            list.add(e.getText());
        }
        Mapper.scroll("Disclaimer");
        List<WebElement> options1 = Mapper.finds(domFile, "carnationtext");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : options1) {
            list1.add(e.getText());
        }
        List<String> finallist = new ArrayList<String>(list);
        finallist.addAll(list1);
        HashSet hs = new HashSet();
        hs.addAll(finallist);
        finallist.clear();
        finallist.addAll(hs);
        int flag = 0;
        for (int i = 0; i < finallist.size(); i++) {
            for (int j = 0; j < finallist.size(); j++)
                if (finallist.get(i).equalsIgnoreCase(MobileConstants.BikesCarnationOptions.get(j)))
                    System.out.println(finallist.get(i) + MobileConstants.BikesCarnationOptions.get(j));
            flag = 1;
            break;
        }
        if (flag == 1)
            return true;
        else
            return false;

    }

    /**
     * extract text of corresponding filter Switch
     */
    public String textOfFilterSwitch(int i) {
        Mapper.waitForElementToBeVisible(domFile, "filterSwithch");
        String filterText = Mapper.finds(domFile, "filterSwithch").get(i).getText();
        return filterText;
    }

    /**
     * selct filter from filters
     */
    public void clickOnFilterSwitch(int i) {
        Mapper.waitForElementToBeVisible(domFile, "filterSwithch");
        Mapper.finds(domFile, "filterSwithch").get(i).click();

    }

    /**
     * reset filters
     */
    public void resetFilters() {
        Mapper.waitForElementToBeVisible(domFile, "resetFilter");
        Mapper.find(domFile, "resetFilter").click();
    }

    /**
     * search specific cars from cars home page
     */
    public void searchCarsFromChp(String cars) {
        Mapper.waitForElementToBeVisible(domFile, "redirectToSearchPage");
        Mapper.find(domFile, "redirectToSearchPage").click();
        Mapper.waitForElementToBeVisible(domFile, "searchText");
        Mapper.find(domFile, "searchText").sendKeys(cars);
    }

    /**
     * select options from Auto Suggest
     */
    public void selectAutoSuggestedOptions() {
        Mapper.waitForElementToBeVisible(domFile, "carsSearchAutoSuggest");
        Mapper.find(domFile, "carsSearchAutoSuggest").click();
    }

    /**
     * If Get Quote button is found click on it
     */
    public void clickOnGetQoute() {
        System.out.print("Before clicking get quote");
        Mapper.waitForElementToBeVisible(domFile, "GetQuote");
        Mapper.find(domFile, "GetQuote").click();
        System.out.print("After clicking get quote");
    }

    /**
     * Returns true if Get Quote is present, else returns false
     *
     * @return
     */
    public boolean validateGetQuote() {
        return isElementPresent("GetQuote");
    }

    /**
     * Enters the name field of Get Quote dialog Box
     */
    public void fillDialogBoxName(String name) {
        Mapper.waitForElementToBeVisible(domFile, "GetQuoteDialogBoxName");
        Mapper.find(domFile, "GetQuoteDialogBoxName").sendKeys(name);
    }

    /**
     * Enters the phone field of Get Quote dialog Box
     *
     * @param phone
     */

    public void fillDialogBoxPhone(String phone) {
        Mapper.waitForElementToBeVisible(domFile, "GetQuoteDialogBoxPhone");
        Mapper.find(domFile, "GetQuoteDialogBoxPhone").sendKeys(phone);
    }

    /**
     * Enters email field of Get Quote dialog Box
     *
     * @param email
     */
    public void fillDialogBoxemail(String email) {
        Mapper.waitForElementToBeVisible(domFile, "GetQuoteDialogBoxEmailAddress");
        Mapper.find(domFile, "GetQuoteDialogBoxEmailAddress").sendKeys(email);
    }

    /**
     * Taps on Get Quotes button
     */
    public void tapGetQuotesButton() {
        Mapper.waitForElementToBeVisible(domFile, "GetQuoteSubmitButton");
        Mapper.find(domFile, "GetQuoteSubmitButton").click();
    }

    /**
     * Swipes VAP page horizontally for inspected Ads
     */
    public void swipeCarsVapHorizontally() {
        int X1=Mapper.find(domFile, "inspectedText").getLocation().getX();
        int X2=Mapper.find(domFile, "vapAdTitleCars").getLocation().getX();
        int Y=Mapper.find(domFile, "vapAdTitleCars").getLocation().getY();
        horizontalSwipeWithCordinates(X2,Y+10,X1);

       // Mapper.horizontalSwipe(domFile, "vapAdTitleCars", "inspectedText");
            }

    /**
     * Swipes VAP horizontally for non inspected Ads
     */
    public void swipeNonInspectedCarsVapHorizontally() {
        Mapper.horizontalSwipe(domFile, "vapAdTitleCars", "VapProfilePic");
    }

    /**
     * Validates  policy bazaar page
     */
    public boolean validatePolicyBazaarPage() {
        return isElementPresent("policyBazaarPage");
    }

    /**
     * Validates the error message when all the mandatory fields are empty when tapped on get quotes button
     */
    public boolean validateErrorWhenAllFieldsEmpty() {
        return (isElementPresent("PolicyBazarErrorEnterYourName") & isElementPresent("PolicyBazarErrorEnterPhoneNumber") & isElementPresent("PolicyBazarErrorEnterEmailAddress"));
    }

    /**
     * Validates the error message when 2 mandatory fields are empty when tapped on get quotes button
     */
    public boolean validateErrorWhenNameEmpty() {
        return isElementPresent("PolicyBazarErrorEnterYourName");
    }

    public boolean validateErrorWhenPhoneEmpty() {
        return isElementPresent("PolicyBazarErrorEnterPhoneNumber");
    }

    public boolean validateErrorWhenEmailEmpty() {
        return isElementPresent("PolicyBazarErrorEnterEmailAddress");
    }

    /**
     * Returns Text of the error message Enter 10 digit phone number
     *
     * @return
     */
    public String validateInvalidPhoneNumber() {
        return Mapper.find(domFile, "PolicyBazarErrorEnterPhoneNumber").getText();
    }

    /**
     * Returns Text of the error message Enter valid email address
     */
    public String validateInvalidEmailAddress() {
        return Mapper.find(domFile, "PolicyBazarErrorEnterEmailAddress").getText();
    }

    /**
     * Validates prefilled email of Get quote
     */

    public String getPrefilledEmailGetQuote() {
        Mapper.waitForElementToBeVisible(domFile, "getQuoteEmailAddress");
        return Mapper.find(domFile, "getQuoteEmailAddress").getText();
    }

    /**
     * Validates Ad title for cars in VAP
     */

//    public String getAdTitleVap(){
//        Mapper.waitForElementToBeVisible(domFile,"vapAdTitleCars");
//        return Mapper.find(domFile,"vapAdTitleCars").getText();
//    }
//
//    public void swipeCarsImageHorizontally(){
//        Mapper.horizontalSwipe(domFile,"VapProfilePic","VAPPrice");
//    }
    public boolean swipeTillVapChanges() {
        Mapper.waitForElementToBeVisible(domFile, "vapAdTitleCars");
        String vapTitle = Mapper.find(domFile, "vapAdTitleCars").getText();
        while (Mapper.find(domFile, "vapAdTitleCars").getText().equals(vapTitle)) {
            Mapper.horizontalSwipe(domFile, "VAPPrice", "VapProfilePic");
        }

        String newVapTitle = Mapper.find(domFile, "vapAdTitleCars").getText();
        return (vapTitle.equals(newVapTitle));
    }


    /**
     * Returns the prefix string of get quote button
     */

    public String prefixTextGetQuote() {
        Mapper.waitForElementToBeVisible(domFile, "prefixTextGetQuoteButton");
        return Mapper.find(domFile, "prefixTextGetQuoteButton").getText();
    }

    /**
     * Returns true if prefix element of get quote is present
     */

    public boolean prefixElementGetQuote() {
        Mapper.waitForElementToBeVisible(domFile, "prefixTextGetQuoteButton");
        return isElementPresent("prefixTextGetQuoteButton");
    }

    /**
     * Gets the string View "number" from cars VAP
     */
    public boolean getViewNumberVap() {
        Mapper.waitForElementToBeVisible(domFile, "viewNumberVap");
        return isElementPresent("viewNumberVap");
    }

    /**
     * Taps on Vie number button in VAP
     */

    public void tapViewNumberVap() {
        Mapper.waitForElementToBeVisible(domFile, "viewNumberVap");
        Mapper.find(domFile, "viewNumberVap").click();
    }

    /**
     * Returns true if contact details element is present
     */

    public boolean verifyContactDetailsElement() {
        Mapper.waitForElementToBeVisible(domFile, "viewNumberContactDetails");
        return isElementPresent("viewNumberContactDetails");
    }

    /**
     * Returns the element "Car Details"
     */

    public boolean carDetailsText() {
        Mapper.waitForElementToBeVisible(domFile, "CarDetailsTab");
        return isElementPresent("CarDetailsTab");
    }

    /**
     * Clicks on tab Battery
     */

    public void tapBatteryTab() {
        Mapper.scroll("Battery");
        Mapper.waitForElementToBeVisible(domFile, "BatteryTabVap");
        Mapper.find(domFile, "BatteryTabVap").click();
    }

    /**
     * Clicks on Transmission Tab
     */

    public void tapTransmissionTab() {
        Mapper.waitForElementToBeVisible(domFile, "TransmissionTabVap");
        Mapper.find(domFile, "TransmissionTabVap").click();
    }

    /**
     * Clicks on Accessories tab
     */

    public void tapAccessoriesTab() {
        Mapper.waitForElementToBeVisible(domFile, "AccessoriesTabVap");
        Mapper.find(domFile, "AccessoriesTabVap").click();
    }

    /**
     * Clicks on Docs tab
     */

    public void tapDocsTab() {
        Mapper.waitForElementToBeVisible(domFile, "DocsTabVap");
        Mapper.find(domFile, "DocsTabVap").click();
    }

    /**
     * Returns true if car details tab is present
     *
     * @return
     */
    public boolean validateCarsDetailsTab() {
        return isElementPresent("CarDetailsTab");
    }


    /**
     * swipe vertically on cars vap
     */
    public void swipeOnCarsVapVertically() {
        Mapper.VerticalSwipe(domFile, "carsVApShortlist", "carsVapChat");
    }

    /**
     * Does a free text on cars search bar
     */
    public void freeTextinCarsSearchBar(String text) {
        Mapper.waitForElementToBeVisible(domFile, "searchText");
        Mapper.find(domFile, "searchText").sendKeys(text);
    }

    /**
     * Clicks on choose search bar
     */

    public void clickOnChooseSearchBar() {
        Mapper.waitForElementToBeVisible(domFile, "chooseSearchBar");
        explicitWait(4);
        Mapper.find(domFile, "chooseSearchBar").click();
    }

    /**
     * Taps on Honda City in Auto Suggest
     */
    public void tapOnAutoSuggest() {
        Mapper.waitForElementToBeVisible(domFile, "carsSearchAutoSuggest");
        Mapper.find(domFile, "carsSearchAutoSuggest").click();
    }

    /**
     * Validates SnB page
     */

    public boolean validateSnb() {
        Mapper.waitForElementToBeVisible(domFile, "snbInspected");
        return isElementPresent("snbInspected");
    }

    /**
     * scrolls and returns true if price range button is present
     */
    public boolean isPriceRangeButtonPresent() {
//        Mapper.scroll("PRICE TRENDS FOR");
        return isElementPresent("priceRangeButton1");
    }

    public boolean scrollAndCheckIfPriceRangeButtonPresent() {
        Mapper.scroll("PRICE TRENDS FOR");
        return isElementPresent("priceRangeButton1");
    }


    /**
     * Scrolls and returns true if price range heading is not present
     */
    public boolean priceRangeheadingNotPresent() {
        Mapper.scroll("DESCRIPTION");
        return !(isElementPresent("priceRangeButton1"));
    }

    /**
     * Clicks on required subCat of cars
     */
    public void selectSubCat(String subCat) {
        Mapper.waitForElementToBeVisible(domFile, "snbCategory");
        Mapper.find(domFile, "snbCategory").click();
        Mapper.find(domFile, subCat).click();
    }


    /**
     * Clicks on price trends sub cats
     */

    public void selectPriceTrendsSubcat() {
        Mapper.waitForElementToBeVisible(domFile, "snbCategory");
        Mapper.find(domFile, "snbCategory").click();

    }

    /**
     * Returns true when zero ads are there for price trends
     */
        public boolean priceTrendsZeroAds() {
//        Mapper.scroll("PRICE TRENDS FOR");
        String priceTrends1 = Mapper.find(domFile, "priceRangeButton1").getText();
        String priceTrends2 = Mapper.find(domFile, "priceRangeButton2").getText();
        String priceTrends3 = Mapper.find(domFile, "priceRangeButton3").getText();
        return (priceTrends1.equals("0") || priceTrends2.equals("0") || priceTrends3.equals("0"));
    }

    /**
     * Swipes price trends horizontally
     */
    public void swipePriceTrendsHorizontally() {
        Mapper.waitForElementToBeVisible(domFile, "priceRangeButton1");
        Mapper.horizontalSwipe(domFile, "priceRangeButton1", "priceRangeButton3");
    }

    /**
     * Swipes from the bottom of the user profile horizontally
     */
    public void swipeFromBottomVapHorizontally() {
        Mapper.scroll("DESCRIPTION");
        Mapper.horizontalSwipe(domFile, "userDetailsEmail", "userDetailsProfilePic");
    }

    /**
     * Taps on price trends button
     */

    public void tapOnPriceTrendsButton(String buttonName) {
//        Mapper.scroll("DESCRIPTION");
        Mapper.find(domFile, buttonName).click();
    }

    /**
     * Gets text and returns true if 0 ads found in First price range button
     */

    public boolean priceTrendsZeroAds1() {
//        Mapper.scroll("DESCRIPTION");
        String priceTrends1 = Mapper.find(domFile, "priceRangeButton1").getText();
        return priceTrends1.equals("0");
    }

    /**
     * Gets text and returns true if 0 ads found in second price range button
     */

    public boolean priceTrendsZeroAds2() {
//        Mapper.scroll("DESCRIPTION");
        String priceTrends2 = Mapper.find(domFile, "priceRangeButton1").getText();
        return priceTrends2.equals("0");
    }

    /**
     * Gets text and returns true if 0 ads found in Third price range button
     */

    public boolean priceTrendsZeroAds3() {
//        Mapper.scroll("DESCRIPTION");
        String priceTrends3 = Mapper.find(domFile, "priceRangeButton1").getText();
        return priceTrends3.equals("0");
    }

    /**
     * Returns true if inspected toggle is present
     */

    public boolean isInspectedTogglePresent() {
        Mapper.waitForElementToBeVisible(domFile, "snbInspected");
        return isElementPresent("snbInspected");
    }

    /**
     * Returns true if priceTrends button is present
     */

    public boolean getAllPriceTrendsButtons() {
        Mapper.waitForElementToBeVisible(domFile, "priceRangeButton1");
        return isElementPresent("priceRangeButton1") && isElementPresent("priceRangeButton2") && isElementPresent("priceRangeButton3");
    }

    /**
     * Taps on Inspection Link in home screen for cars
     */

    public void tapOnInspectionLinkInHomeScreen() {
        Mapper.waitForElementToBeVisible(domFile, "InspectionLink");
        Mapper.find(domFile, "InspectionLink").click();
    }

    /**
     * returns true is cars & bikes is present in the post free Ad page
     */

    public boolean getElementFromPostFreeAdPage() {
        Mapper.waitForElementToBeVisible(domFile, "carsAndBikesElementInPostFreeAd");
        return isElementPresent("carsAndBikesElementInPostFreeAd");
    }

    /**
     * Taps on Static screen post Ad button
     */

    public void tapOnStaticPostAdButton() {
        Mapper.waitForElementToBeVisible(domFile, "staticScreenPostAdNowButton");
        Mapper.find(domFile, "staticScreenPostAdNowButton").click();
    }

    /**
     * Taps on MSP button in SnB
     */
    public void tapOnMspInSnb() {
        Mapper.waitForElementToBeVisible(domFile, "mspFromSnb");
        Mapper.finds(domFile, "mspFromSnb").get(1).click();
    }

    /**
     * Taps on MSP bikes
     */

    public void tapOnMspBikes() {
        Mapper.waitForElementToBeVisible(domFile, "mspBikes");
        Mapper.find(domFile, "mspBikes").click();
    }

    /**
     * Taps on the Brand Name Field for MSP
     */

    public void tapOnMspBrandNameBikes() {
        Mapper.waitForElementToBeVisible(domFile, "mspBrand");
        Mapper.find(domFile, "mspBrand").click();
    }

    /**
     * Scrolls and Selects suzuki brand from drop down for MSP
     */

    public void selectSuzukiBrand(String data) {
        Mapper.scroll(data);
        Mapper.find(domFile, data).click();
    }

    /**
     * Taps on the Model Field for MSP
     */

    public void tapOnMspModelNameBikes() {
        Mapper.waitForElementToBeVisible(domFile, "mspModel");
        Mapper.find(domFile, "mspModel").click();

    }

    /**
     * Selects model Bandit from MSP for Bikes
     */

    public void selectBanditModel(String data) {
        Mapper.scroll(data);
        Mapper.find(domFile, data).click();
    }

    /**
     * Taps on year field for bikes in MSP
     */

    public void tapOnMspYearBikes() {
        Mapper.waitForElementToBeVisible(domFile, "mspYear");
        Mapper.find(domFile, "mspYear").click();
    }

    /**
     * Selects yesr for MSP Bikes
     */

    public void selectYearMspBikes() {
        Mapper.waitForElementToBeVisible(domFile, "YearMspBikes");
        Mapper.find(domFile, "YearMspBikes").click();
    }

    /**
     * Taps on Check MSP button
     */

    public void tapOnCheckMspButton() {
        Mapper.waitForElementToBeVisible(domFile, "calculateMsp");
        Mapper.find(domFile, "calculateMsp").click();
    }

    /**
     * Returns true if Create Alert Button is present
     */

    public boolean getCreateAlertButtonMsp() {
        Mapper.waitForElementToBeVisible(domFile, "CreateAlertButtonInMsp");
        return isElementPresent("CreateAlertButtonInMsp");
    }

    /**
     * Taps on I am Selling in Msp Page
     */

    public void tapOnIAmSelling() {
        Mapper.waitForElementToBeVisible(domFile, "MspSellButton");
        Mapper.find(domFile, "MspSellButton").click();
    }

    /**
     * Returns true if Post Free Ad button is present in MSP result page
     */

    public boolean validatePostFreeAdButtonInMspResultPage() {
        Mapper.waitForElementToBeVisible(domFile, "MspPostFreeAdButton");
        return isElementPresent("MspPostFreeAdButton");
    }

    /**
     * Selects model Fiero from MSP for Bikes
     */

    public void selectFieroModel(String data) {
        Mapper.scroll(data);
        Mapper.find(domFile, data).click();
    }

    /**
     * Selects year for fiero in MSP
     */

    public void selectYearFiero() {
        Mapper.waitForElementToBeVisible(domFile, "FieroYearSelectMsp");
        Mapper.find(domFile, "FieroYearSelectMsp").click();
    }

    /**
     * Taps on FindCars Button for Bikes
     */

    public void tapOnFindCarsBikes() {
        Mapper.waitForElementToBeVisible(domFile, "FindCarsButtonBikes");
        Mapper.find(domFile, "FindCarsButtonBikes").click();
    }

    /**
     * Taps on brand name field in MSP for cars
     */
    public void tapOnCarBrandName() {
        Mapper.waitForElementToBeVisible(domFile, "mspBrand");
        Mapper.find(domFile, "mspBrand").click();
    }

    /**
     * Returns true if Find cars/Bikes Button is present
     */
    public String getMspResult() {
        Mapper.waitForElementToBeVisible(domFile, "MspResultTitle");
        return Mapper.find(domFile, "MspResultTitle").getText();
    }

    /**
     * Selects brand Audi for cars
     */
    public void selectAudiFromBrandName() {
        Mapper.waitForElementToBeVisible(domFile, "AudiBrandName");
        Mapper.find(domFile, "AudiBrandName").click();
    }

    /**
     * Taps on Model field in MSP for cars
     */
    public void tapOnModelCars() {
        Mapper.waitForElementToBeVisible(domFile, "mspModel");
        Mapper.find(domFile, "mspModel").click();
    }

    /**
     * Taps on A4 from dropdown
     */
    public void selectA4Model() {
        Mapper.waitForElementToBeVisible(domFile, "A4Model");
        Mapper.find(domFile, "A4Model").click();
    }

    /**
     * Taps on year field in MSP for Cars
     */

    public void tapOnYearCars() {
        Mapper.waitForElementToBeVisible(domFile, "mspYear");
        Mapper.find(domFile, "mspYear").click();
    }

    /**
     * Taps on Variant in Cars for MSP
     */
    public void tapOnVariantCars() {
        Mapper.waitForElementToBeVisible(domFile, "mspVarient");
        Mapper.find(domFile, "mspVarient").click();
    }

    /**
     * Taps on variant name for cars in MSP
     */
    public void selectVariantNameCars() {
        Mapper.waitForElementToBeVisible(domFile, "VariantName");
        Mapper.find(domFile, "VariantName").click();
    }

    /**
     * Taps on KMS driven for cars in MSP
     */
    public void tapOnKmsDrivenCarsMsp() {
        Mapper.waitForElementToBeVisible(domFile, "mspKmDriven");
        Mapper.find(domFile, "mspKmDriven").click();
    }

    /**
     * Taps on KmsDrivenValue
     */
    public void selectKmsDrivenValue() {
        Mapper.waitForElementToBeVisible(domFile, "KmsDrivenValue");
        Mapper.find(domFile, "KmsDrivenValue").click();
    }

    /**
     * Gets the text I m buying in MSP evaluation page
     */
    public String getIamBuyingMsp() {
        Mapper.waitForElementToBeVisible(domFile, "IamBuyingMsp");
        return Mapper.find(domFile, "IamBuyingMsp").getText();
    }

    /**
     * Gets the text I m Selling in MSP evaluation page
     */
    public String getIamSellingMsp() {
        Mapper.waitForElementToBeVisible(domFile, "IamSellingMsp");
        return Mapper.find(domFile, "IamSellingMsp").getText();
    }

    /**
     * Returns true if the element looking for a specific model is present
     */
    public boolean getUsedCarsElement() {
        Mapper.waitForElementToBeVisible(domFile, "usedCarsLookingForaSpecificModel");
        return isElementPresent("usedCarsLookingForaSpecificModel");
    }

    /**
     * Taps on dismiss callout
     */

    public void tapOnDismissCallout() {
        Mapper.waitForElementToBeVisible(domFile, "newCarsCallOutDismiss");
        Mapper.find(domFile, "newCarsCallOutDismiss").click();
    }

    /**
     * Returns the text looking for specific car in New cars tab
     */
    public String getNewCarsTabText() {
        Mapper.waitForElementToBeVisible(domFile, "NewCarsText");
        return Mapper.find(domFile, "NewCarsText").getText();
    }

    /**
     * Taps on new cars tab
     */

    public void tapOnNewCarsTab() {
        Mapper.waitForElementToBeVisible(domFile, "newCarsTab");
        Mapper.find(domFile, "newCarsTab").click();
    }

    /**
     * click on yes button after posting Ad to txchane/Buy New Cars
     */
    public void clickOnYesButtonAfterPostingAd() {
        Mapper.waitForElementToBeVisible(domFile, "exchangeCArs");
        Mapper.find(domFile, "exchangeCArs").click();
    }

    /**
     * fetch snb ad title
     */
    public String snbAdTitle() {
        Mapper.waitForElementToBeVisible(domFile, "snbAdTitle");
        return (Mapper.find(domFile, "snbAdTitle").getText());
    }

    /**
     * Taps on New Cars search bar
     */

    public void tapOnNewCarsSearchBar() {
        Mapper.waitForElementToBeVisible(domFile, "redirectToSearchPage");
        Mapper.find(domFile, "redirectToSearchPage").click();
    }

    /**
     * Writes letter A in  search bar
     */

    public void writeLetterInSearchBar() {
        Mapper.waitForElementToBeVisible(domFile, "searchText");
        Mapper.find(domFile, "searchText").sendKeys("a");
    }

    /**
     * Returns true if auto suggestion list is present
     */

    public boolean getAutoSuggestionElement() {
        Mapper.waitForElementToBeVisible(domFile, "carsSearchAutoSuggest");
        return isElementPresent("carsSearchAutoSuggest");
    }

    /**
     * Returns true if popular brands is present
     */
    public boolean getPopularBrandElement() {
        Mapper.waitForElementToBeVisible(domFile, "popularBrands");
        return isElementPresent("popularBrands");
    }

    /**
     * Returns true if recent or auto suggestion is present
     */
    public boolean getRecentSearchElement() {
        return isElementPresent("newCarsRecentSearches");
    }

    /**
     * Taps on popular Ad
     */
    public void tapOnPopularAd() {
        Mapper.waitForElementToBeVisible(domFile, "newCarsAutuSuggestion");
        Mapper.find(domFile, "newCarsAutuSuggestion").click();
    }

    /**
     * Taps on Find Cars for new cars
     */
    public void tapOnFindCars() {
        Mapper.waitForElementToBeVisible(domFile, "searchResults");
        Mapper.find(domFile, "searchResults").click();
    }

    /**
     * Validates NewCars title
     */
    public boolean getNewCarsTitle() {
        Mapper.waitForElementToBeVisible(domFile, "newCarsTitle");
        return isElementPresent("newCarsTitle");
    }

    /**
     * Taps on clear Button
     */
    public void tapOnClearButton() {
        Mapper.waitForElementToBeVisible(domFile, "clearButton");
        Mapper.find(domFile, "clearButton").click();
    }

    /**
     * Taps on filter buttons
     */

    public void tapOnNewCarsFilterIcon() {
        Mapper.waitForElementToBeVisible(domFile, "Filter");
        Mapper.find(domFile, "Filter").click();
    }

    /**
     * Returns true if all the filter elements are present
     */

    public int getNewCarsFilterElements() {
        return Mapper.finds(domFile, "filterBrandAndFuel").size();

    }

    /**
     * Returns true if price slider is present
     */

    public boolean getNewCarsPriceSlider() {
        Mapper.finds(domFile, "newCarsPriceSlider");
        return isElementPresent("newCarsPriceSlider");
    }

    /**
     * Taps on Brand of new filters
     */
    public void tapOnNewCarFilterBrand() {
        Mapper.waitForElementToBeVisible(domFile, "filterBrandAndFuel");
        Mapper.finds(domFile, "filterBrandAndFuel").get(0).click();
    }

    /**
     * taps on second option in brands
     */

    public void selectFilterOptionNewCars() {
        Mapper.waitForElementToBeVisible(domFile, "filterOptionsNewCars");
        Mapper.finds(domFile, "filterOptionsNewCars").get(1).click();
    }

    /**
     * Taps on done button for new cars filters
     */
    public void tapOnDoneButtonFilterOfNewCars() {
        Mapper.waitForElementToBeVisible(domFile, "doneButtonNewCarsFilters");
        Mapper.find(domFile, "doneButtonNewCarsFilters").click();
    }

    /**
     * Gets Aston Martin Text
     */

    public String getAstonMartinText() {
        Mapper.waitForElementToBeVisible(domFile, "AstonMartinText");
        return Mapper.find(domFile, "AstonMartinText").getText();
    }

    /**
     * Taps on sort for new cars
     */
    public void tapOnSortButton() {
        Mapper.waitForElementToBeVisible(domFile, "newCarsSortButton");
        Mapper.find(domFile, "newCarsSortButton").click();
    }

    /**
     * Returns true if sort options are present
     */

    public boolean getSortOptions() {
        return isElementPresent("sortOption1")&& isElementPresent("sortOption2");
    }

    /**
     * Taps on Lowest Sort
     */
    public void tapOnLowestSort(){
        Mapper.waitForElementToBeVisible(domFile,"sortOption2");
        Mapper.find(domFile,"sortOption2").click();
    }

    /**
     * Taps on Highest Sort
     */
    public void tapOnHighestSort(){
        Mapper.waitForElementToBeVisible(domFile,"sortOption1");
        Mapper.find(domFile,"sortOption1").click();
    }

    /**
     * Validation of Lowest price sort in ascending order
     */
    public boolean validateAscendingSortOnSnbPageForNewCars()
    {
        List<WebElement> priceList = Mapper.finds(domFile,"priceNewCar");
        List<String> list = new ArrayList<String>();
        for (WebElement e : priceList) {
            list.add(e.getText().replaceAll("\\D", ""));  // Will Keep only digits, rest all will be removed
        }
        System.out.println(list);
        List<Integer> listOriginal = new ArrayList<Integer>();
        List<Integer> listNew = new ArrayList<Integer>();
        for(int i=0; i<list.size(); i++){
            int testValue = Integer.parseInt(list.get(i));
            listNew.add(testValue);
            listOriginal.add(testValue);

        }
        Collections.sort(listNew);
        for(int i=0; i<list.size(); i++){
            if ((listOriginal.get(i)).equals(listNew.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Validation of Highest price sort in ascending order
     */

    public boolean validateDescendingSortOnSnbPageForNewCars()
    {
        List<WebElement> priceList = Mapper.finds(domFile,"priceNewCar");
        List<String> list = new ArrayList<String>();
        for (WebElement e : priceList)
        {
            list.add(e.getText().replaceAll("\\D", ""));  // Will Keep only digits, rest all will be removed
        }
        System.out.println(list);
        List<Integer> listOriginal = new ArrayList<Integer>();
        List<Integer> listNew = new ArrayList<Integer>();
        for(int i=0; i<list.size(); i++)
        {
            int testValue = Integer.parseInt(list.get(i));
            listNew.add(testValue);
            listOriginal.add(testValue);

        }
        Collections.sort(listNew);
        int j=list.size()-1;
        for(int i=0; i<list.size(); i++)
        {
            if ((listOriginal.get(i)).equals(listNew.get(j)))
            {
                return true;
            }
            j--;
        }
        return false;
    }

    /**
     * Taps on first Ad in Snb
     */

    public void tapOnSnbAd(){
        Mapper.waitForElementToBeVisible(domFile,"newCarsSnb");
        Mapper.find(domFile,"newCarsSnb").click();
    }

    /**
     * Returns true if Vap Tab info is present
     */

    public boolean getVapTableInfoElement(){
        Mapper.waitForElementToBeVisible(domFile,"newCarsVapCarInfo");
        return isElementPresent("newCarsVapCarInfo");
    }

    /**
     * Writes letter Mah in  search bar
     */

    public void writeLetterInSearchBar1() {
        Mapper.waitForElementToBeVisible(domFile, "searchText");
        Mapper.find(domFile, "searchText").sendKeys("Mah");
    }

    /**
     * Scrolls to the text transmission and taps on variant
     */

    public void tapOnVariant(){
        Mapper.waitForElementToBeVisible(domFile,"variant");
        Mapper.find(domFile,"variant").click();

    }

    /**
     * Returns true if Mileage element is present
     */

    public boolean getDetailedVapElement(){
        return isElementPresent("detailedVapElement");
    }
    /**
     *  Taps on specifications tab for new Cars
     */

    public void tapOnSpecificationsTab(){
        Mapper.waitForElementToBeVisible(domFile,"specificationsTab");
        Mapper.find(domFile,"specificationsTab").click();
    }

    /**
     * Taps on features tab for new Cars
     */

    public void tapOnfeaturesTab(){
        Mapper.waitForElementToBeVisible(domFile,"featuresTab");
        Mapper.find(domFile,"featuresTab").click();
    }

    /**
     * Gets the text comfort & convenience
     */

    public boolean getComfortText(){
        Mapper.waitForElementToBeVisible(domFile,"featureTabAttribute");
        return isElementPresent("featureTabAttribute");
    }

    /**
     * Taps on Get on road price
     */

    public void tapOnGetOnRoadPrice(){
        Mapper.waitForElementToBeVisible(domFile,"onroadpriceButton");
        Mapper.find(domFile,"onroadpriceButton").click();
    }

    /**
     * Returns true if ex showroom price element is present
     */

    public boolean getExShowroompriceElement(){
        return isElementPresent("exShowroomPrice");
    }

    /**
     * Taps on Get on road price of another car
     */

    public void tapOnGetOnroadPriceOfAnotherCar(){
        Mapper.waitForElementToBeVisible(domFile,"getOnRoadPriceOfAnotherCar");
        Mapper.find(domFile,"getOnRoadPriceOfAnotherCar").click();
    }

    /**
     * Returns true if Brand name, Model Name,Variant name and City fields are present
     */

    public boolean isBrandModelVariantCityPresent(){
        return isElementPresent("mspBrand")&& isElementPresent("mspModel")&&isElementPresent("mspVarient")&& isElementPresent("getOnRoadPriceCity");
    }

    /**
     * Taps on more button of most popular Near You
     */

    public void tapOnMoreMostPopularNearYou(){
        Mapper.waitForElementToBeVisible(domFile,"MoreButtonMostPopular");
        Mapper.find(domFile,"MoreButtonMostPopular").click();
    }

    /**
     * Returns true if Most popular near you is present
     */

    public boolean getMostPopularNearYou(){
        Mapper.waitForElementToBeVisible(domFile,"mostPopularNearYou");
        return  isElementPresent("mostPopularNearYou");
    }

    /**
     * Returns true if icon is present prefix to brands in Most Popular near you
     */

    public boolean getIconElement(){
        Mapper.waitForElementToBeVisible(domFile,"brandLogo");
        return isElementPresent("brandLogo");
    }

    /**
     * Returns true if suffix number is present
     */

    public boolean getSuffixNumber(){
        Mapper.waitForElementToBeVisible(domFile,"brandSuffix");
        return  isElementPresent("brandSuffix");
    }

    /**
     * Taps on most popular brand
     */

    public void tapsOnMostPopularBrand(){
        Mapper.waitForElementToBeVisible(domFile,"mostPopularBrand");
        Mapper.find(domFile,"mostPopularBrand").click();
    }

    /**
     * gets the text of 1st popular Ad
     */

    public String getPopularAdText(){
        Mapper.waitForElementToBeVisible(domFile,"popularAdsText");
//        System.out.println("$$$$$"+Mapper.finds(domFile,"popularAdsText").get(0).getText());
        return Mapper.finds(domFile,"popularAdsText").get(0).getText();
    }

    /**
     * Gets the text of brand name in filters
     */

    public String getBrandNameTextInFilters(int i)
    {
//        swipeVertically("onlineUsersFilter","inspectedFilter");
//        Mapper.waitForElementToBeVisible(domFile, "inspectedFilter");
        Mapper.waitForElementToBeVisible(domFile, "filterBrandAndFuel");
        System.out.println("#####"+Mapper.finds(domFile,"filterBrandAndFuel").get(i).getText());
        return Mapper.finds(domFile,"filterBrandAndFuel").get(i).getText();
    }

    /**
     * Returns true if price trends icon is present
     */

    public boolean validatePriceTrendsIcon(){
        Mapper.waitForElementToBeVisible(domFile,"priceTrendsIcon");
        return  isElementPresent("priceTrendsIcon");
    }

    /**
     * Tap on Price Trends icon
     */

    public void tapPriceTrendsIcon() {
        Mapper.waitForElementToBeVisible(domFile, "priceTrendsIcon");
        Mapper.find(domFile,"priceTrendsIcon").click();
    }

    /**
     *  Returns true if EMI Button is present
     */

    public boolean isEmiButtonPresent(){
        Mapper.waitForElementToBeVisible(domFile,"calculateEmiButton");
        return isElementPresent("calculateEmiButton");
    }

    /**
     * Taps on calculateEmi button
     */

    public void tapOnCalculateEmiButton(){
        Mapper.waitForElementToBeVisible(domFile,"calculateEmiButton");
        Mapper.find(domFile,"calculateEmiButton").click();
    }

    /**
     * Returns true if Emi calculator element is present
     */

    public boolean isEmiCalculatorPresent(){
        Mapper.waitForElementToBeVisible(domFile,"emiTitle");
        return isElementPresent("emiTitle");
    }

    /**
     * Taps on Popular ads text
     */

    public void tapOnPopularAds(){
        Mapper.waitForElementToBeVisible(domFile,"newCarsAutuSuggestion");
        Mapper.find(domFile,"newCarsAutuSuggestion").click();
    }

    /**
     * Gets the text of first Ad in Snb
     */

    public String getSnbText(){
        Mapper.waitForElementToBeVisible(domFile,"selectedBrandInSnb");
        System.out.println("snbText"+ Mapper.finds(domFile,"selectedBrandInSnb").get(0).getText());
        return Mapper.finds(domFile,"selectedBrandInSnb").get(0).getText();
    }

    /**
     * gets the text Maruti suzuki
     */

    public String getMarutiSuzuki(){
        Mapper.waitForElementToBeVisible(domFile,"popularAdTextMaruti");
        System.out.println("getMaruti"+ Mapper.find(domFile,"popularAdTextMaruti").getText());
        return Mapper.find(domFile,"popularAdTextMaruti").getText();
    }
}
//
//Mapper.waitForElementToBeVisible(domFile,"GetQuoteDialogBoxPhone");
//        Mapper.waitForElementToBeVisible(domFile,"GetQuoteDialogBoxEmailAddress");
//        Mapper.waitForElementToBeVisible(domFile,"GetQuoteSubmitButton");
