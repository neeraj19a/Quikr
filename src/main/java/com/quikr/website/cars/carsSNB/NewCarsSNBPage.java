package com.quikr.website.cars.carsSNB;

import com.quikr.website.cars.CarsPageBase;
import com.quikr.website.cars.carsVAP.NewCarsVapPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 10/8/16.
 */
public class NewCarsSNBPage extends CarsPageBase {

    public NewCarsSNBPage(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String domFile = getProperties().get("NEWCARS_SNB_PAGE_DOM_FILE");

    public int numberofProductsFromNewCarsSearchResult(){

        List<WebElement> ls=Mapper.finds(domFile,"carsNamefromSNBSearchResult");

        return  ls.size();
    }

    public int numberofNewCarsAdsSNBSearchResults(){
     List<WebElement> newCarsSnbAds=Mapper.finds(domFile,"newCarsAdsSNBSearchResult");
        return  newCarsSnbAds.size();
    }

    public boolean validateNewCarsImageOnSNB(){

        int countTrue=0;
        int countFalse=0;
        List<WebElement> ls=Mapper.finds(domFile,"carsNamefromSNBSearchResult");
        List<String> newCarsName=new ArrayList<>();
        for(WebElement element:ls){
             newCarsName.add(element.getText());
        }

        logger.info("New Cars from SNB Search results are-->"+newCarsName);

        for(WebElement element:ls){
            String firstNameofCar=element.getText();
            WebElement carImage=null;
            carImage=Mapper.findAndReplace(domFile,"carsImageAccordingToProductName",new String[]{firstNameofCar});
            if(carImage!=null){
                //System.out.println(carImage.getAttribute("src").replaceAll("[-_]",""));
                if(carImage.getAttribute("src").replaceAll("[-_]","").contains(firstNameofCar.replaceAll(" ","")+"/1nr.jpg")) {
                    countTrue++;
                }
                else {
                    logger.info("Image attribute for Car-->" + carImage.getAttribute("src") + " is not proper");
                    countFalse++;
                }
            }
            else {
                logger.info("Looks like carImage is not proper-->"+carImage);
                countFalse++;
            }
        }

        logger.info("Number of Results with proper Image Attributes are-->"+countTrue);
        logger.info("Number of Results with improper Image Attributes are-->"+countFalse);

        return countTrue>countFalse;
    }

    public boolean validateProperPriceforNewCarsSNBResults(){

        int countTrue=0;
        int countFalse=0;
        List<WebElement> priceElement=Mapper.finds(domFile,"priceofNewCarsSnbResult");
        List<String> pricesOfNewCars=new ArrayList<>();
        for(WebElement element:priceElement){
            pricesOfNewCars.add(element.getText());
        }

        logger.info("Prices of New Cars are -->"+pricesOfNewCars);

        for(String check:pricesOfNewCars){
            if(check.matches("\\d+\\.?\\d?\\d?\\s?\\w?[L|C|Cr]")){
                countTrue++;
            }
            else {
                countFalse++;
            }
        }

        logger.info("Number of proper Amount of Cars are-->"+countTrue);
        logger.info("Number of improper Amount of Cars are-->"+countFalse);

        return  countTrue>countFalse;
    }

    public boolean validateNewCarsAttributesfromSnbResults(){

        int countTrue=0;
        int countFalse=0;
        List<WebElement> attributesName=Mapper.finds(domFile,"newCarsSnbResultAttributes");

        for(int i=0;i<attributesName.size();i++){

                if(attributesName.get(i).getText().trim().equals("ENGINE") && attributesName.get(++i).getText().trim().equals("FUEL")
                            && attributesName.get(++i).getText().trim().equals("TRANSMISSION")
                            && attributesName.get(++i).getText().trim().equals("MILEAGE")) {

                        countTrue++;
                    }
                    else {
                        countFalse++;
                        logger.info("Looks like attribute is not proper at index-->" + i + " value of attributes is " + attributesName.get(i).getText().trim()
                                + attributesName.get(++i).getText().trim() + " and i is-->" + i + ""
                                + attributesName.get(++i).getText().trim() + " and i is-->" + i + ""
                                + attributesName.get(++i).getText().trim() + " and i is-->" + i);
                    }
                }


        logger.info("Snb Results with proper attributes are-->"+countTrue);
        logger.info("Snb Results with improper attributes are-->"+countFalse);

        return countTrue>countFalse;

    }

    public boolean validateNewCarsSnbResultAttributesValues(){

        int countTrue=0;
        int countFalse=0;

        List<WebElement> attributes=Mapper.finds(domFile,"newCarsSnbResultAttributesValues");
        List<String> attributesValues=new ArrayList<>();
        for(WebElement element:attributes){
            attributesValues.add(element.getText());
        }
        logger.info("Attributes has Values-->" + attributesValues);

        for(int i=0;i<attributesValues.size();i++){
            if(attributesValues.get(i).matches("\\d+\\s+\\w[cc]") && attributesValues.get(++i).trim().matches("Petrol|Diesel") &&
                        attributesValues.get(++i).matches("Automatic|Manual") &&
                        attributesValues.get(++i).matches("\\d+\\.?\\d+\\s+\\w?(kmpl)|N/A")) {
                    countTrue++;
                } else {
                System.out.println("Value is "+i);
                    countFalse++;
                    logger.info("Looks like Attributes Values is not proper at index-->"+i+" value of attribute is"+attributesValues.get(++i)
                            +" value of attributes-->"+attributesValues.get(++i)
                            +" values of attributes-->"+attributesValues.get(++i)
                            +" values of attributes-->"+attributesValues.get(++i));
                }
            }

        logger.info("Results with proper results are-->"+countTrue);
        logger.info("Results with improper results are-->"+countFalse);

        return countTrue>countFalse;
    }

    public int numberofRoadPriceButtons(){
        List<WebElement> roadPriceButton=Mapper.finds(domFile,"onRoadPriceButtonforSNBResults");

        logger.info("Number of Road Price Button-->" + roadPriceButton.size());
        return  roadPriceButton.size();
    }

    public boolean validateMostPopularComparisonImages(){

        boolean flag=false;
        if(Mapper.find(domFile,"mostPopularComparisonsSNBResults")!=null){
            flag=true;
            int countBrokenImage=0;
            List<WebElement> imagesOfMostPoularCarsComparison=Mapper.finds(domFile,"mostPopularComparisonsImagesSNBResults");
            for(WebElement element:imagesOfMostPoularCarsComparison){
                if(element.getAttribute("src").isEmpty()){
                    countBrokenImage++;
                }
            }
            if(countBrokenImage>0){
                logger.info("Images of Most Popular Cars are broken-->"+countBrokenImage);
                return false;
            }
        }
        else {

            return false;
        }
        return flag;
    }

    public boolean numberOfVSOnMostPopularCarsComparison(){
        List<WebElement> vsElements=Mapper.finds(domFile,"mostPopularComparisonsVS");
        int count=0;
        for(WebElement element:vsElements){
            if(element.getText().equals("VS")){
                count++;
            }
        }
        if(count==2) {
            return true;
        }
        else
            return false;
    }

    public boolean validateMostPopularCarsName(){
        int countTrue=0;
        List<WebElement> mostPopularCarsName=Mapper.finds(domFile,"mostPopularCarsComparisonName");
        for(WebElement element:mostPopularCarsName){
            if(element.getText()!=null){
                countTrue++;
            }
        }
        if (countTrue==4){
            return true;
        }
        else
            return false;
    }

    public boolean validateMostPopularCarsPrice(){
        int countTrue=0;
        List<WebElement> price=Mapper.finds(domFile,"mostPopularCarsComparisonPrice");
        for(WebElement element:price){
            if(element.getText().matches("\\d*\\.?\\d+\\s+\\w?[L|Cr]")){
                countTrue++;
            }
        }
        if(countTrue==4){
            return true;
        }
        else
            return false;
    }

    public void validateGetOnRoadPrice(String nameOfLead,String mobileOfLead,String emailOfLead){
        Mapper.finds(domFile,"onRoadPriceButtonforSNBResults").get(1).click();
        waitForPageToLoad("on-road-price");
        Mapper.find(domFile, "variantDropDown").click();
        WebElement firstOption=Mapper.finds(domFile,"variantDropDownOption").get(0);
        getElementintoView(firstOption);
        firstOption.click();
        sleep(3000);
        Mapper.find(domFile, "selectCityDropDown").click();
        WebElement cityFirstOption=Mapper.finds(domFile,"selectCityDropDownOptions").get(0);
        getElementintoView(cityFirstOption);
        cityFirstOption.click();
        Mapper.find(domFile, "getOnRoadPriceButtonVariant").click();
        sleep(3000);
        if(Mapper.find(domFile,"leadCaptureDetailsEnabled").getAttribute("aria-hidden").equals("false")){
            logger.info("leadCaptureDetails Box is displayed");
            Mapper.find(domFile,"leadCaptureName").sendKeys(nameOfLead);
            Mapper.find(domFile,"leadCaptureMobile").sendKeys(mobileOfLead);
            Mapper.find(domFile,"leadCaptureEmail").sendKeys(emailOfLead);
            Mapper.find(domFile,"leadCaptureSubmitButton").click();
            Mapper.find(domFile,"leadCaptureSubmitButton").click();

        }
        else {
            logger.info("leadCaptureDetails Box is not displayed");

        }
    }

    public boolean isCheckAnotherCarworking(){
        boolean flag=false;

        if(waitForPageToLoad("quikr.com/cars/new+on-road-price") && Mapper.find(domFile,"getOnRoadPricePageHeading").getText().trim().equals("On Road Price Calculator"))
        {
            flag=true;
        }
        else {
            logger.info("Looks like Price Calculator Page not opened");
            return false;
        }
        return flag;
    }

    public boolean validateToggleShowUpcomingCars(){

        boolean flag=false;
        Mapper.find(domFile,"upcomingCarsToggleSwitch").click();
        if(waitForPageToLoad("quikr.com/cars/upcoming") &&  heading().contains("UpcomingCarsin") && validateBreadCrumbText("Upcoming Cars")){
            flag=true;
        }
        else {
            logger.info("Look like Upcoming Cars Toggle was not clicked or Upcoming Cars page was not loaded");
            return false;
        }
        return flag;
    }

    public boolean validateBreadCrumbText(String expectedBreadCrumbText){

        List<WebElement> breadCrumbs=Mapper.finds(domFile,"breadCrumbText");
        List<String> breadCrumbText=new ArrayList<>();

        for(WebElement element:breadCrumbs){
            breadCrumbText.add(element.getText());
        }

        if(breadCrumbText.contains(expectedBreadCrumbText)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean validateNotifyMe(String name,String email,String mobile,String city){
        boolean flag=false;
        Mapper.finds(domFile,"notifyMeOnNewUpcomingCars").get(0).click();
        sleep(3000);
        if(Mapper.find(domFile,"notifyMeLeadDataPopUpOnNewUpcomingCars")!=null){
            flag=true;
            Mapper.find(domFile,"leadCaptureNotifyMeName").click();
            Mapper.find(domFile,"leadCaptureNotifyMeName").sendKeys(name);
            Mapper.find(domFile,"leadCaptureNotifyMeEmail").sendKeys(email);
            Mapper.find(domFile,"leadCaptureNotifyMeMobile").sendKeys(mobile);
            Mapper.find(domFile,"leadCaptureNotifyMeCity").sendKeys(city);
            Mapper.find(domFile,"notifyMeSubmitButton").click();
            sleep(3000);
            logger.info("Text is-->" + Mapper.find(domFile, "notifyMeThankYouMessage").getText());
            if (Mapper.find(domFile,"notifyMeThankYouMessage").getText().contains("Thank your for the details")){
                flag=true;
            }
            else {
                logger.info("Text of Notify Me Thank You message is not proper");
                return false;
            }

        }
        else {
            logger.info("Looks like Notify Me Pop up not coming");
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

    public boolean isPriceSort(String sortByPrice){

        int countTrue=0;
        int countFalse=0;

        List<WebElement> price=Mapper.finds(domFile,"priceofNewCarsSnbResult");
        List<Double> priceOfAdsinLacs=new ArrayList<>();
        for(WebElement element:price){
            if(element.getText().contains("L")){
            priceOfAdsinLacs.add((Double.parseDouble(element.getText().replaceAll("[A-Z]", "").replaceAll(" ", ""))));}

        }
        logger.info("Price of Ads"+priceOfAdsinLacs);

        if(sortByPrice.contains("Lowest")) {
            List<Double> sortedPrice = new ArrayList<>(priceOfAdsinLacs);
            Collections.sort(sortedPrice);

            logger.info("Sorted Price" + sortedPrice);
            for (int i = 0; i < priceOfAdsinLacs.size(); i++) {
                if (priceOfAdsinLacs.get(i).equals(sortedPrice.get(i))) {
                    countTrue++;
                } else {
                    logger.info("Price not matching at-->"+i+" actual price is-->"+priceOfAdsinLacs.get(i)+"expected price is-->"+sortedPrice.get(i));
                    countFalse++;
                }
            }
        }
        else if(sortByPrice.contains("Highest")){
            List<Double> sortedPrice = new ArrayList<>(priceOfAdsinLacs);
            Collections.sort(sortedPrice,Collections.reverseOrder());

            logger.info("sortedPrice" + sortedPrice);
            for (int i = 0; i < priceOfAdsinLacs.size(); i++) {
                if (priceOfAdsinLacs.get(i).equals(sortedPrice.get(i))) {
                    countTrue++;
                } else {
                    logger.info("Price not matching at-->"+i+" actual price is-->"+priceOfAdsinLacs.get(i)+"expected price is-->"+sortedPrice.get(i));
                    countFalse++;
                }
            }
        }
        return countTrue>countFalse;
    }

    public boolean navigationToModelPageForUpcomingCars(NewCarsVapPage newCarsVapPage){

        int countFalse=0;
        int countFeaturesFalse=0;
        int countButtonsFalse=0;
        int countImagesFalse=0;

        List<WebElement> models=Mapper.finds(domFile,"carsNamefromSNBSearchResult");
        List<String> modelsName=new ArrayList<>();
        logger.info("Number of Upcoming cars are-->"+models.size());
        for(int i=0;i<=1;i++){
            //models=Mapper.finds(domFile,"carsNamefromSNBSearchResult");
            logger.info("Text is-->"+models.get(i).getText());
            modelsName.add(models.get(i).getText());
            getElementintoView(models.get(i));
            logger.info("Trying to click-->"+models.get(i));
            models.get(i).click();
            logger.info("Here -->" + modelsName.get(i).split(" ")[0] + "+" + modelsName.get(i).split(" ")[1]);
            waitForPageToLoad(modelsName.get(i).split(" ")[0]+"+"+modelsName.get(i).split(" ")[1]);
            if(heading().contains(modelsName.get(i).replaceAll(" ",""))){
                logger.info("Heading seems to be correct");
                boolean features=newCarsVapPage.validateUpcomingCarsFeatures();
                boolean buttonsonUpcomingCars=newCarsVapPage.validateButtonsOnUcomingCarsVapPage();
                boolean imagesonUpcomingCarsVAP=newCarsVapPage.validateImagesonUpcomingCarsVAP(modelsName.get(i).split(" ")[1]);
                if(!features){
                    logger.info("Features seems to be broken");
                    countFeaturesFalse++;
                }
                if(!buttonsonUpcomingCars){
                    logger.info("Button seems to be broken");
                    countButtonsFalse++;
                }
                if(!imagesonUpcomingCarsVAP){
                    logger.info("Images on Upcoming Cars seems to be improper");
                    countImagesFalse++;
                }
                navigateTo().back();
                logger.info("Going back to SNB Page");
                waitForPageToLoad("quikr.com/cars/upcoming");
                closeCityPopup();
            }
            else {
                logger.info("Heading is-->"+heading()+"but expected is"+modelsName.get(i).replaceAll(" ",""));
                countFalse++;
                navigateTo().back();
                waitForPageToLoad("quikr.com/cars/upcoming");
            }
            break;
        }
        logger.info("countFalse-->"+countFalse);
        logger.info("countButtonsFalse-->"+countButtonsFalse);
        logger.info("countFeaturesFalse-->"+countFeaturesFalse);
        logger.info("countImagesFalse-->"+countImagesFalse);

        return (countFalse==0&&countButtonsFalse==0&&countFeaturesFalse==0 && countImagesFalse==0);
    }

    public void navigatetoCarsVAPPage(){
        WebElement firstSNBResult=Mapper.finds(domFile,"carsNamefromSNBSearchResult").get(0);
        logger.info("Snb First Ad is-->"+firstSNBResult.getText());
        firstSNBResult.click();
    }

    public void selectBrandNewSNBPage(String brand){
        Mapper.find(domFile,"newCarsSNBBrandNameDropDown").click();
        Mapper.find(domFile,"newCarsSNBBrandNameTextBox").sendKeys(brand);
        Mapper.findAndReplace(domFile,"newCarsSNBBrandNameCheckBox",new String[]{brand}).click();
    }

}
