package com.quikr.website.cars.carsSNB;

import com.quikr.website.cars.CarsPageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Saurabh on 15/11/15.
 */
public class CarsSNBPage extends CarsPageBase
{

    public CarsSNBPage(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String domFile = getProperties().get("CARS_SNB_PAGE_DOM_FILE");

    public String returnSNBSearchResultsHeading(){
        return Mapper.find(domFile,"snbSearchResultsHeadingText").getText();
    }

    public List<String> returnSnbSearchResultProductsName(){
        List<WebElement> snbProducts=Mapper.finds(domFile,"snbSearchResultsProductsName");
        List<String> snbProductsName=new ArrayList<>();
        for(WebElement element:snbProducts){
            snbProductsName.add(element.getText());
        }
        return snbProductsName;
    }

    public void navigateToSecondPage(){
        if(Mapper.waitForElementToBeClickable(domFile,"paginationSecondPage")==true){
            Mapper.find(domFile,"paginationSecondPage").click();
        }
        else {
            logger.info("Looks like Pagination for 2nd Page is not available");
        }
    }

    public void navigateToQuikrHomePagefromSNB(){
        if(Mapper.waitForElementToBeClickable(domFile,"snbQuikrHomeIcon")==true){
            Mapper.find(domFile,"snbQuikrHomeIcon").click();
        }
        else {
            logger.info("Looks like Quikr Home Paage Icon is not available on SNB Page");
        }
    }

    public boolean validateSnbProductsName(String searchText){
        boolean flag=false;

        List<String> snbProuctsName=returnSnbSearchResultProductsName();
        for (String check:snbProuctsName){
            logger.info("Product Name " + check + " contains " + searchText + "--> " + check.contains(searchText));
            if(check.contains(searchText)){
                flag= true;
            }
            else {
                logger.info(check+" Snb Result does not contain Search Text -->"+searchText);
                flag= false;
            }
        }
        return flag;
    }

    public boolean validateSnbProductsName(String searchText,String searchText2){
        boolean flag=false;

        List<String> snbProuctsName=returnSnbSearchResultProductsName();
        logger.info("Here are SNB Products-->" + snbProuctsName);
        for (String check:snbProuctsName){
            logger.info("Product Name "+check+" contains "+searchText+"--> "+check.contains(searchText));
            if(check.contains(searchText)||check.contains(searchText2)){
                flag= true;
            }
            else {
                logger.info(check+" Snb Result does not contain Search Text -->"+searchText);
                flag= false;
            }
        }
        return flag;
    }

    public void clickBrand()
    {
        Mapper.waitForElementToBeVisible(domFile, "brandFilter");
        Mapper.find(domFile, "brandFilter").click();
    }

    public void selectBrand(String brandToSelect)
    {
        clickBrand();

        Mapper.waitForElementToBeVisible(domFile, "brandFilterTextbox",20);
        Mapper.find(domFile, "brandFilterTextbox").click();
        Mapper.find(domFile, "brandFilterTextbox").sendKeys(brandToSelect);

        Mapper.waitForElementToBeVisible(domFile, "brandFilterSelect", new String[]{brandToSelect});
        Mapper.findAndReplace(domFile, "brandFilterSelect", new String[]{brandToSelect}).click();
    }

    public String brandsSelected()
    {
        String brandsSelected=null;

        WebElement brands=Mapper.find(domFile, "breadcrumbFilters");
            brandsSelected=(brands.getText().trim());

        return brandsSelected;
    }

    public int numberofShortListIcon(){
        List<WebElement> shortlistIcons=Mapper.finds(domFile,"snbSearchResultsProductsShortlist");
        return  shortlistIcons.size();
    }

    public boolean validateSortByLeastDriven(){
        boolean flag=false;
        List<WebElement> kms=Mapper.finds(domFile,"snbSearchResultsPrimeFeaturesKms");
        List<WebElement> kmsInspected=Mapper.finds(domFile,"snbSearchResultsPrimeFeaturesKmsInspected");

        List<Integer> kmsInNumber=new ArrayList<>();
        List<Integer> kmsInNumberInspected=new ArrayList<>();

        for(WebElement element:kms){
            logger.info("Here Element text is "+element.getText());
            if(!element.getText().equals("kms")&& !element.getText().equals("km")){
            kmsInNumber.add((Integer.parseInt(element.getText().replaceAll(" kms", "").replaceAll(",", ""))));
            }
            else {
                continue;
            }
        }


        for(WebElement element:kmsInspected){
            logger.info("Here Element text is "+element.getText());
            if(!element.getText().equals("kms")&& !element.getText().equals("km")){
                kmsInNumberInspected.add((Integer.parseInt(element.getText().replaceAll(" kms","").replaceAll(",",""))));
            }
            else {
                continue;
            }
        }
        kmsInNumber.removeAll(kmsInNumberInspected);
        logger.info("Kms are-->" + kmsInNumber);

        List<Integer> sortedCopyfoKms=new ArrayList<Integer>(kmsInNumber);
        Collections.sort(sortedCopyfoKms);

        for(int i=0;i<kmsInNumber.size();i++){
            if(kmsInNumber.get(i).equals(sortedCopyfoKms.get(i))){
                flag=true;
            }
            else{
                logger.info("Looks like KMs are not sorted-->Expected value is "+sortedCopyfoKms.get(i)+" but actual value is-->"+kmsInNumber.get(i));
            return false;
            }
        }
        return flag;
    }

    public boolean validateKmsSnbResultsarebetweenSlider(int kmsSlider){
        int countTrue=0;
        int countFalse=0;

        List<WebElement> kms=Mapper.finds(domFile,"snbSearchResultsPrimeFeaturesKms");
        List<Integer> kmsInNumber=new ArrayList<>();
        for(WebElement element:kms) {
            if (!element.getText().trim().equals("kms") && !element.getText().trim().equals("km")) {
                    logger.info("Here Element text is " + element.getText());
                    if (element.getText().contains("kms") ) {
                        kmsInNumber.add((Integer.parseInt(element.getText().replaceAll(" kms", "").replaceAll(",", ""))));
                    } else if (element.getText().contains("km") ) {
                        kmsInNumber.add((Integer.parseInt(element.getText().replaceAll("km", "").replaceAll(",", ""))));

                    }
                else {
                        continue;
                    }
            }
            else {
                continue;
            }
        }

        logger.info("Kms are-->" + kmsInNumber);

        for(int check:kmsInNumber){
            if(check<kmsSlider){
                countTrue++;
            }
            else {
                logger.info(check+"is not less than-->"+kmsSlider);
                countFalse++;
            }
        }
        logger.info("SNB Results with proper Kms are-->"+countTrue);
        logger.info("SNB Results with improper Kms are-->" + countFalse);

        return countTrue>countFalse;
    }

    public boolean validateSortByPrice(String sortBy){
        boolean flag=false;
        List<WebElement> price=Mapper.finds(domFile,"snbSearchResultsPrice");
        List<Integer> priceOfAds=new ArrayList<>();
        for(WebElement element:price){
            priceOfAds.add(Integer.parseInt(element.getText().replaceAll(",","")));
        }

        logger.info("Prices of Ads before Removal of Premium Ads-->" + priceOfAds);

        List<WebElement> pricePremium=Mapper.finds(domFile,"snbSearchResultsPricePremium");
        List<Integer> priceOfAdsPremium=new ArrayList<>();
        for(WebElement element:pricePremium){
            if(element.getText().contains(",")){
            priceOfAdsPremium.add(Integer.parseInt(element.getText().replaceAll(",","")));
            }
        }

        List<WebElement> priceInspected=Mapper.finds(domFile,"snbSearchResultsPriceInspected");
        List<Integer> priceOfAdsInspected=new ArrayList<>();
        for(WebElement element:priceInspected){
            if(element.getText().contains(",")){
                priceOfAdsInspected.add(Integer.parseInt(element.getText().replaceAll(",","")));
            }
        }

        priceOfAds.removeAll(priceOfAdsInspected);
        logger.info("Prices of Ads after Removal of Inspected Ads-->" + priceOfAds);


        List<Integer> copyofSortedPriceOfAds=new ArrayList<>(priceOfAds);
        if(sortBy.contains("Highest")){
        Collections.sort(copyofSortedPriceOfAds,Collections.reverseOrder());
        }
        else {
            Collections.sort(copyofSortedPriceOfAds);
        }

        for(int i=0;i<priceOfAds.size();i++){
            if(priceOfAds.get(i).equals(copyofSortedPriceOfAds.get(i))){
                flag=true;
            }
            else
            {
                logger.info("Looks like price of Ad is not Sorted Expected Value is-->"+copyofSortedPriceOfAds.get(i)+" but actual value is -->"+priceOfAds.get(i)+" at position "+(i+1));
                return false;
            }
        }
        return flag;
    }

    public boolean validatePrimeFeatures(){

        boolean flag=false;
        List<WebElement> primeFeatures=Mapper.finds(domFile,"snbSearchResultsPrimeFeatures");
        logger.info("Prime Features Size is -->"+primeFeatures.size());
        List<String> domElements=new ArrayList<>();
        domElements.add("snbSearchResultsPrimeFeaturesKms");
        domElements.add("snbSearchResultsPrimeFeaturesFuel");
        domElements.add("snbSearchResultsPrimeFeaturesOwner");

        List<String> featuresText=new ArrayList<>();
        if(primeFeatures.size()==30) {
            for (String elements : domElements) {
                List<WebElement> primeFeature = Mapper.finds(domFile, elements);
                for (WebElement element : primeFeature) {
                    featuresText.add(element.getText());
                }

                logger.info("Here after adding Prime Features-->"+featuresText);

                for (String check : featuresText) {
                    if(elements.equals("snbSearchResultsPrimeFeaturesKms")) {
                        if (check.contains("kms")) {
                            flag = true;
                        } else {
                            logger.info(check + " does not contains text kms");
                            return false;
                        }
                    }

                    else if(elements.equals("snbSearchResultsPrimeFeaturesFuel")) {
                        if (check.toLowerCase().contains("petrol")||check.toLowerCase().contains("diesel")||check.toLowerCase().contains("cng")) {
                            flag = true;
                        } else {
                            logger.info(check + " does not contains text diesel or cng or petrol");
                            return false;
                        }
                    }
                    if(elements.equals("snbSearchResultsPrimeFeaturesOwner")) {
                        if (check.toLowerCase().contains("owner")) {
                            flag = true;
                        } else {
                            logger.info(check + " does not contains text owner");
                            return false;
                        }
                    }

                }
                featuresText.clear();
                logger.info("Here are clearing"+featuresText);
            }
        }
        else{
            logger.info("Prime Features Size is not proper-->" + primeFeatures.size() + "it should be 30");
        }
        return flag;
    }

    public boolean validateSnbResultswithFuelType(String fuelType){

        int countTrue=0;
        int countFalse=0;
        if(Mapper.waitForElementToBeVisible(domFile,"snbSearchResultsPrimeFeaturesFuel")==true){
            List<WebElement> fuel=Mapper.finds(domFile,"snbSearchResultsPrimeFeaturesFuel");
            List<String> fuelName=new ArrayList<>();
            for(WebElement element:fuel)
            {
                fuelName.add(element.getText());
            }

            for(String check:fuelName){
                if(check.contains(fuelType)){
                    countTrue++;
                }
                else {
                    countFalse++;
                    logger.info(check+" does not contain-->"+fuelType+" SNB Search results are not proper");
                }
            }
        }
        logger.info("No of SNB Results that contain-->" + fuelType + " are -->" + countTrue);
        logger.info("No of SNB Results that does not contain-->" + fuelType + " are -->" + countFalse);
        return countTrue>countFalse;
    }

    public boolean validateSortByLatestModel(){

        boolean flag=false;
        List<WebElement> model=Mapper.finds(domFile,"snbSearchResultsProductsName");
        List<Integer> modelYear=new ArrayList<>();
        for(WebElement element:model){
            logger.info("Element is--> "+element.getText());
            logger.info("Year is--> "+element.getText().substring(element.getText().length()-4,element.getText().length()));
            if(element.getText().substring(element.getText().length()-4,element.getText().length()).matches("\\d{4}")) {
                modelYear.add(Integer.parseInt(element.getText().substring(element.getText().length() - 4, element.getText().length())));
            }
            else {
                continue;
            }
        }
        logger.info("Cars with Model Year are -->" + modelYear);

        List<Integer> copySortedModelYear=new ArrayList<>(modelYear);
        Collections.sort(copySortedModelYear, Collections.reverseOrder());

        for(int i=0;i<modelYear.size();i++){
            if(modelYear.get(i).equals(copySortedModelYear.get(i))){
                flag=true;
            }
            else {
                logger.info("Looks like the Model is not proper Expected Value is--> "+copySortedModelYear.get(i)+" but actual value is -->"+modelYear.get(i)+" at position "+(i+1));
                return false;
            }
        }
        return flag;
    }

    public boolean isAllAdsHaveProperPrice(){
        boolean flag=false;
        List<WebElement> price=Mapper.finds(domFile,"snbSearchResultsPrice");
        List<Integer> priceOfAds=new ArrayList<>();
        for(WebElement element:price){
            if(!element.getText().contains("Ask")) {
                priceOfAds.add((Integer.parseInt(element.getText().replaceAll(",", ""))));
            }
            else {
                continue;
            }
        }

        logger.info("Here are prices of Ads-->" + priceOfAds);
        for(Integer check:priceOfAds){
            if(check>0){
                flag= true;
            }
            else
            {
                logger.info("Looks like price is not proper-->"+check);
                flag= false;
            }
        }
        return flag;
    }

    public boolean areChatandReplyButtonAvailable(){
        List<WebElement> chatButtons=Mapper.finds(domFile,"snbSearchResultsChatButton");
        List<WebElement> replyButtons=Mapper.finds(domFile,"snbSearchResultsReplyButton");
        List<WebElement> chatButtonsVisible=new ArrayList<>();
        List<WebElement> replyButtonsVisible=new ArrayList<>();

        for(WebElement element:chatButtons){
            if (element.isDisplayed()){
                chatButtonsVisible.add(element);
            }
        }

        for(WebElement element:replyButtons){
            if (element.isDisplayed()){
                replyButtonsVisible.add(element);
            }
        }

        logger.info("No. of Chat Options present on SNB page are-->"+chatButtonsVisible.size());
        logger.info("No. of Reply Options present on SNB page are-->" +replyButtonsVisible.size());

        if(chatButtonsVisible.size()>0||replyButtonsVisible.size()>0){
            return true;
        }
        else
            return false;
    }

    public boolean isUserOnlineStatusproper(){
        boolean flag=false;
        List<WebElement> onlineStatus=Mapper.finds(domFile,"snbSearchResultsUserOnlineStatus");
        List<String> onlineorOfflineStatus=new ArrayList<>();
        if(onlineStatus.size()>0||onlineStatus.size()==30){
            flag=true;
        }
        else {
            logger.info("No. of Online or Offline users are not correct-->"+onlineStatus.size());
            return false;
        }

        for(WebElement element:onlineStatus){
            onlineorOfflineStatus.add(element.getText());
        }

        for (String checkText:onlineorOfflineStatus){
            if(checkText.toLowerCase().contains("online")||checkText.toLowerCase().contains("offline")){
                flag=true;
            }
            else {
                logger.info("User Online or Offline Status are not proper-->"+checkText);
                return false;
            }
        }
        return flag;
    }


    public boolean isPostedStatusproper(){
        boolean flag=false;
        List<WebElement> postedStatus=Mapper.finds(domFile,"snbSearchResultsPostedStatus");
        List<String> postedAdStatus=new ArrayList<>();
        if(postedStatus.size()>0||postedStatus.size()==30){
            flag=true;
        }
        else {
            logger.info("No. of Posted status are not correct-->"+postedStatus.size());
            return false;
        }

        for(WebElement element:postedStatus){
            postedAdStatus.add(element.getText());
        }

        for (String checkText:postedAdStatus){
            if(checkText.toLowerCase().contains("ago")&&(checkText.toLowerCase().matches("(.*)day(.*)||(.*)minute(.*)||(.*)hour(.*)||(.*)month(.*)||(.*)year(.*)||(.*)second(.*)"))){
                flag=true;
            }
            else {
                logger.info("Posted Status are not proper-->"+checkText);
                return false;
            }
        }
        return flag;
    }

    public boolean validateGoogleAds(){
        List<WebElement> googleAds=Mapper.finds(domFile,"googleAds");
        if(googleAds.size()>=4){
            logger.info("Google Ads are-->" + googleAds.size());
            return true;
        }
        else {
            logger.info("Looks like Google Ads are no in proper number-->"+googleAds.size());
            return false;

        }
    }

    public boolean priceRangeFilter(String price){
        if(Mapper.waitForElementToBeClickable(domFile, "priceFilter")==true){
            Mapper.find(domFile, "priceFilter").click();
            Mapper.findAndReplace(domFile, "priceFilterSelect", new String[]{price}).click();
            waitForPageToLoad(price);
            closeCityPopup();
            if(Mapper.findAndReplace(domFile,"priceFilterSelected",new String[]{price})!=null){
                logger.info("Price filter checked"+Mapper.findAndReplace(domFile,"priceFilterSelected",new String[]{price}).getText());
                return true;
            }
            else {
                logger.info("Looks like Price filter not checked");
                return false;
            }
        }
        else {
            logger.info("Looks like priceFilter is not visible");
            return false;
        }
    }

    public boolean isPriceFilterSelected(String price){
        if(Mapper.findAndReplace(domFile, "priceFilterSelected", new String[]{price})!=null){
            logger.info("Price filter checked" + Mapper.findAndReplace(domFile, "priceFilterSelected", new String[]{price}).getText());
            return true;
        }
        else {
            logger.info("Looks like Price filter not checked");
            return false;
        }
    }

    public boolean validateFilterBreadCrumbnotPresent(){
        if(Mapper.find(domFile, "filterBreadCrumbNotPresent")==null){
            return true;
        }
        else {
            return false;
        }
    }

    public List getFilterBreadCrumbText(){
        List<WebElement> breadCrumb=Mapper.finds(domFile,"breadcrumb");
        List<String> breadCrumbText=new ArrayList<>();
        for(WebElement element:breadCrumb){
            breadCrumbText.add(element.getText());
        }
        logger.info("Size of BreadCrumb is "+breadCrumbText.size());
        return breadCrumbText;
    }


    public List getBreadFiltersCrumbText(){
        List<WebElement> breadCrumb=Mapper.finds(domFile,"breadcrumbFilters");
        List<String> breadCrumbText=new ArrayList<>();
        for(WebElement element:breadCrumb){
            breadCrumbText.add(element.getText());
        }
        return breadCrumbText;
    }

    public void clearAllFiltersfromBreadCrumb(){
        WebElement element = Mapper.find(domFile,"filterBreadCrumb");
        getElementintoView(element);
        element.click();
    }

    public boolean validatePagination(){

        boolean flag=false;
        if(Mapper.waitForElementToBeVisible(domFile, "pagination")==true){
            List<WebElement> pages=Mapper.finds(domFile,"pagination");
            if(pages.size()>0){
                flag=true;
                if(Mapper.find(domFile,"paginationText").getText().matches("Page 1 of(.*)")){
                    flag=true;
                }
                else {
                    flag=false;
                    logger.info("Looks like Pagination Text is not proper Expected Page 1 of  but actual is "+Mapper.find(domFile,"paginationText").getText());
                }
            }
            else
            {
                logger.info("Looks like pagination is not proper Pagination Links size is -->"+pages.size());
                return  false;
            }
        }
        return flag;
    }

    public String isInspectedPresent(){
        String text=null;
        if(Mapper.waitForElementToBeVisible(domFile, "snbInspected")==true){
            text=Mapper.find(domFile,"snbInspectedOnly").getText();
        }
        else {
            logger.info("Looks like Inspected Option is not coming on SNB");
        }
        return text;
    }

    public boolean validatetoggleInspectedOnSNB(){
        boolean flag=false;
        if( Mapper.waitForElementToBeClickable(domFile,"snbInspected")==true){
            Mapper.find(domFile,"snbInspected").click();
        }
        else {
            logger.info("Looks like Inspected Option is not coming on SNB");
        }
        waitForPageToLoad("Inspected");
        if (Mapper.find(domFile,"inspectionRatingFilter").getText().contains("Inspection Rating")){
            flag=true;
        }
        else{
            logger.info("inspectionRatingFilter not loaded properly on SNB");
            return false;
        }

        logger.info("No of Inspected Ads are -->"+Mapper.finds(domFile,"snbSearchResultsInspectedCertificate").size());
        if(Mapper.finds(domFile,"snbSearchResultsInspectedCertificate").get(0).getText().contains("Inspected")&& Mapper.finds(domFile,"snbSearchResultsInspectedCertificateIcon").size()!=0){
            flag=true;
        }
        else {
            logger.info("Inspected Text is not present on Searched Products");
            return false;
        }
            return flag;
    }

    public void sortBy(String option){
        if(Mapper.waitForElementToBeClickable(domFile, "snbSortByDropDown")==true){
            Mapper.find(domFile, "snbSortByDropDown").click();
            Mapper.findAndReplace(domFile, "snbSortByDropDownOption", new String[]{option}).click();
        }
        else
            logger.info("Looks like Sort Drop Down is not visible");
    }

    public boolean selectFuelType(String fuelType){
        if(Mapper.waitForElementToBeClickable(domFile,"fuelFilter")==true){
            Mapper.find(domFile, "fuelFilter").click();
            Mapper.findAndReplace(domFile, "fuelFilterSelect", new String[]{fuelType}).click();
            if(Mapper.findAndReplace(domFile, "fuelFilterSelected", new String[]{fuelType})!=null){
                return  true;
            }
            else {
                logger.info("Looks like Filter Fuel Type-->"+fuelType+" is not selected");
                return false;
            }
        }
        else {
            logger.info("Fuel Type Filter is not available Pls Check");
            return false;
        }
    }

    public void selectFiltersFromMore(String moreFilter,String value){
        if(Mapper.waitForElementToBeClickable(domFile, "moreButton")==true)
        {
            Mapper.find(domFile, "moreButton").click();

            switch (moreFilter){

                case "resetFilter":
                    Mapper.find(domFile,"cancelFilter").click();
                    break;
                case "premium":
                    Mapper.find(domFile, "premiumAdsFilterSelect").click();
                    break;
                case "postedBy":
                    Mapper.findAndReplace(domFile, "postedByFilterSelect", new String[]{value}).click();
                    break;
                case "carType":
                    Mapper.findAndReplace(domFile, "carTypeFilterSelect", new String[]{value}).click();
                    break;
                case "transmission":
                    Mapper.findAndReplace(domFile, "transmissionFilterSelect", new String[]{value}).click();
                    break;
                case "owner":
                    sleep(2000);
                    Mapper.findAndReplace(domFile,"ownerFilterSelect",new String[]{value}).click();
                    break;
                case "moveSlider":
                    WebElement element=Mapper.find(domFile,"kmsDrivenSlider");
                    moveSlider(element);
                    break;
                case "yearOfMake":
                    Mapper.find(domFile,"yearOfMakeButton").click();
                    Mapper.findAndReplace(domFile,"yearOfMake", new String[]{value}).click();
                    break;
            }

                if(Mapper.waitForElementToBeClickable(domFile, "applyFilter")==true)
                {
                    Mapper.find(domFile,"applyFilter").click();
                }
                else {
                    logger.info("Looks like Apply Button not visible");
                }
            }

        else {
            logger.info("Looks Like More is not available in Filters");
        }
    }

    public int numberOfPremiumAds(){
        List<WebElement> premiumAds=new ArrayList<>();
        if(Mapper.waitForElementToBeVisible(domFile, "premiumLabelforAds")==true){
            premiumAds=Mapper.finds(domFile,"premiumLabelforAds");
        }
        else {
            logger.info("Looks like Premium Ads are not visible");
        }

        return premiumAds.size();
    }


    public void clickonAdwithProperPrice(){
        int i=0;
        List<WebElement> price=Mapper.finds(domFile,"snbSearchResultsPrice");
        List<Integer> priceOfAds=new ArrayList<>();
        for(WebElement element:price){
            if(!element.getText().trim().contains("Ask For Price")){
            priceOfAds.add((Integer.parseInt(element.getText().replaceAll(",",""))));
            }
            i++;
            break;
        }

        logger.info("Here are prices of Ads-->" + priceOfAds);
        logger.info("Clicking ON Ad-->"+Mapper.findAndReplace(domFile, "snbSearchResultsAdNumber", new String[]{Integer.toString(i)}));
        Mapper.findAndReplace(domFile, "snbSearchResultsAdNumber", new String[]{Integer.toString(i)}).click();

    }

    public boolean validateTupleSection()
    {
        boolean tupleSectionStatus = false;

        Mapper.waitForElementToBeVisible(domFile, "adTupple");
        List<WebElement> tupleSection = Mapper.finds(domFile, "adTupple");

        if (tupleSection.size()==36) {
            tupleSectionStatus = true;
        }
        else {
            logger.info("Looks like tupleSection size is not proper, it should be 36 but it is-->" + tupleSection.size());
        }

        return  tupleSectionStatus;
    }

    public void selectCityFromSNB(String cityToSelect){

            Mapper.waitForElementToBeVisible(domFile, "changeCityLink",10);
            Mapper.find(domFile, "changeCityLink").click();

            Mapper.waitForElementToBeVisible(domFile, "citySearchTextBox",10);
            Mapper.find(domFile, "citySearchTextBox").click();
            Mapper.find(domFile, "citySearchTextBox").sendKeys(cityToSelect);

            if(Mapper.waitForElementToBeVisible(domFile, "citySelect", new String[]{cityToSelect.toLowerCase()})==true) {
                sleep(3000);
                Mapper.findAndReplace(domFile, "citySelect", new String[]{cityToSelect.toLowerCase()}).click();
            }

            else if(!Mapper.waitForElementToBeInvisible(domFile, "cityPopUpCloseBtnCars",10))
            {
                Mapper.find(domFile, "citySearchTextBox").clear();
                Mapper.find(domFile, "citySearchTextBox").sendKeys(cityToSelect);

                Mapper.waitForElementToBeVisible(domFile, "citySelect", new String[] {cityToSelect.toLowerCase()});
                Mapper.findAndReplace(domFile, "citySelect", new String[] {cityToSelect.toLowerCase()}).click();
            }
        }

    public void selectMinBudgetSignUpSection(String price){
        if(Mapper.waitForElementToBeClickable(domFile, "minBudgetButton")==true){
            Mapper.find(domFile,"minBudgetButton").click();
            Mapper.findAndReplace(domFile,"minBudgetSignUpSection",new String []{price}).click();
        }
        else {
            logger.info("minBudgetButton is not clickable");
        }
    }

    public void selectMaxBudgetSignUpSection(String price){
        if(Mapper.waitForElementToBeClickable(domFile,"maxBudgetButton")==true){
            Mapper.find(domFile,"maxBudgetButton").click();
            Mapper.findAndReplace(domFile,"maxBudgetSignUpSection",new String []{price}).click();
        }
        else {
            logger.info("maxBudgetButton is not clickable");
        }
    }

    public void setEmailinSignUpQuikrAlerts(String email){
        if(Mapper.waitForElementToBeClickable(domFile,"email")==true){
            Mapper.find(domFile,"email").click();
            Mapper.find(domFile, "email").sendKeys(email);
        }
        else {
            logger.info("Looks like email Text box is not Clickable");
        }
    }

    public void setMobileinSignUpQuikrAlerts(String mobile){
        if(Mapper.waitForElementToBeClickable(domFile,"mobile")==true){
            Mapper.find(domFile,"mobile").click();
            Mapper.find(domFile,"mobile").sendKeys(mobile);
        }
        else {
            logger.info("Looks like mobile Text box is not Clickable");
        }
    }

    public void clickCreateAlertButtonQuikrAlerts(){
        if(Mapper.waitForElementToBeClickable(domFile,"createAlertButton")==true){
            Mapper.find(domFile,"createAlertButton").click();
        }
        else {
            logger.info("Looks like createAlertButton is not Clickable");
        }
    }

    public String getTextAfterAlertSubmission(){
        Mapper.waitForElementToBeVisible(domFile, "textAfterCreatingAlert");
        logger.info("Text after creating Alert is-->"+Mapper.find(domFile,"textAfterCreatingAlert").getText());
        return Mapper.find(domFile,"textAfterCreatingAlert").getText();
    }

    public String getTextLookBeyondSection(){
        Mapper.waitForElementToBeVisible(domFile, "lookBeyondText");
        logger.info("Text after creating Alert is-->" + Mapper.find(domFile, "lookBeyondText").getText());
        return Mapper.find(domFile,"lookBeyondText").getText();
    }

    public boolean validateLookBeyondSection(String brand){
        boolean flag=true;
        for(int i=1;i<=2;i++){
            if(Mapper.findAndReplace(domFile,"lookBeyondModel",new String[]{Integer.toString(i)}).getAttribute("href").contains(brand)&&Mapper.findAndReplace(domFile, "lookBeyondModel", new String[]{Integer.toString(i)}).getText().contains(brand)){
                flag=true;
                if(Mapper.findAndReplace(domFile,"lookBeyondModelImage",new String[]{Integer.toString(i)}).getAttribute("title").contains(brand)==true){
                    flag=true;
                    if(Mapper.findAndReplace(domFile,"lookBeyondModelPrice",new String[]{Integer.toString(i)}).getText().matches("\\d*\\.?\\d+\\w?[L|Cr]")){
                        flag=true;
                        if(Mapper.findAndReplace(domFile,"lookBeyondModelViewDetail",new String[]{Integer.toString(i)}).getText().trim().contains("View Detail")){
                            flag=true;
                            if(Mapper.find(domFile,"viewAllCarsButton").getText().trim().contains("View all New Cars")) {
                                flag = true;
                            }
                            else {
                                logger.info("View All Cars Button not present on Look Beyond Section");
                                return false;
                            }
                        }
                        else {
                            logger.info("View Details button not present on Look Beyond Section");
                            return false;
                        }
                    }
                    else {
                        logger.info("Looks like price of Models is not correct for Look Beyond Section");
                        return false;
                    }
                }

                else {
                    logger.info("Title doesnot contain"+brand+"for image-->"+i);
                    return false;
                }
            }
            else {
                logger.info("Href doesnot contain"+brand+"for anchor tag at-->"+i);
                return false;
            }

        }
        return flag;
    }

    public boolean validateFindBestPriceCars(String brand){
        boolean flag=false;
        if(Mapper.waitForElementToBeVisible(domFile,"findBestPriceCarsHeading")==true){
            if(Mapper.find(domFile,"findBestPriceCarsHeading").getText().trim().equals("Find the best price for Hyundai Cars")){
                flag=true;
                Mapper.find(domFile,"findBestPriceCarsSelectModel").click();
                WebElement model=Mapper.find(domFile,"findBestPriceCarsModelOptionSelect");
                String modelText=model.getText();
                model.click();
                Mapper.find(domFile,"findBestPriceCarsYear").click();
                WebElement year=Mapper.find(domFile,"findBestPriceCarsSelectYearOption");
                String yearText=year.getText();
                year.click();

                Mapper.find(domFile,"findBestPriceCarsVariant").click();
                Mapper.find(domFile,"findBestPriceCarsVariantSelectOption").click();
                Mapper.find(domFile,"findBestPriceCarsKms").click();
                Mapper.find(domFile,"findBestPriceCarsKmsSelectOption").click();
                if(Mapper.waitForElementToBeVisible(domFile,"findBestPriceCarsResultText")==true){
                    flag=true;
                    if(Mapper.find(domFile,"findBestPriceCarsResultText").getText().equals("Quikr recommended price for a good condition "+yearText+" "+brand+" "+modelText+" is")){
                        flag=true;
                    }
                    else {
                        logger.info("Expected is -->"+"Quikr recommended price for a good condition "+yearText+" "+brand+" "+modelText+" is");
                        logger.info("Here is MSP Text-->"+Mapper.find(domFile,"findBestPriceCarsResultText").getText());
                        return false;
                    }
                }
                else {
                    logger.info("Looks like MSP Text is not coming Pls Check");
                    return false;
                }
            }
            else {
                logger.info("findBestPriceCarsHeading is not Proper pls check");
                return false;
            }
        }
        else {
            logger.info("Looks like findBestPriceCarsHeading is not visible ");
        }
        return flag;
    }

    public boolean validateReplyAdSNB(String replyContent,String replyMobile, String replyEmail){

        boolean flag=false;
        List<WebElement> reply=Mapper.finds(domFile, "ad_Reply");
        logger.info("No of reply Buttons are-->"+reply.size());
        getElementintoView(reply.get(0));
        reply.get(0).click();
        Mapper.find(domFile,"replyContent").sendKeys(replyContent);
        Mapper.find(domFile,"replyMobile").sendKeys(replyMobile);
        Mapper.find(domFile,"replyEmail").sendKeys(replyEmail);
        if(Mapper.find(domFile,"captchaDisplayed").isDisplayed()) {
          logger.info("Captcha is coming cant execute Reply");
            return  false;

        /*    WebElement element = Mapper.find(domFile, "captcha");
            readCaptcha(element);
        */}
        Mapper.find(domFile,"replySendButton").click();
        sleep(3000);
            if(Mapper.find(domFile,"replySentSuccessMessage").getText().trim().equals("Your reply has been sent ")){
                flag=true;
            }
            else {
                logger.info("Looks like the Reply Sent Success Message is not proper pls checkmessage-->"+Mapper.find(domFile,"replySentSuccessMessage").getText());
                return false;
            }


        return flag;
    }

}