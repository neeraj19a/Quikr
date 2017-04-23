package com.quikr.website.realEstate.realEstateHome;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 31/8/15.
 */
public class RealEstateHomePage extends PageBase{
    public RealEstateHomePage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("REAL_ESTATE_HOME_DOM_FILE");

    /*function to click city from Real Estate Page
    */
    public RealEstateHomePage selectCityQuikrHomes()
    {   Mapper.waitForElementToBeClickable(domFile,"selectcity");
        Mapper.find(domFile,"selectcity").click();
        Mapper.find(domFile,"citySelect").click();
        return this;
    }

    public void clickHouseApartments()
    {
        WebElement element=Mapper.finds(domFile,"Houseapartments").get(0);
        element.click();

    }

    /*
    Let me know if any one is using this method, will fix it. Any ways I have put selectCity() method in pagebase.
    Kindly use from there.
     */
//    public RealEstateHomePage selectCity(String city)
//    {
//        Mapper.waitForElementToBeVisible(domFile,"SelectCityDropDownIcon");
//        Mapper.waitForElementToBeClickable(domFile, "SelectCityDropDownIcon");
//        Mapper.find(domFile,"SelectCityDropDownIcon").click();
//        Mapper.findAndReplace(domFile,"quikrHomesselectcity",new String[]{city}).click();
//
//        return this;
//    }

    public RealEstateHomePage clickloginQuikrHomes(){
        Mapper.waitForElementToBeClickable(domFile,"loginQuikrHomes");
        Mapper.find(domFile, "loginQuikrHomes").click();
        return this;
    }

    public String verifyLogintext()
    {
        Mapper.waitForElementToBeVisible(domFile, "loginPopUpQuikrHomes");
        String text=Mapper.find(domFile,"loginPopUpQuikrHomes").getText();
        return text;
    }

    public boolean verifyAfterLoginIcon()
    {
        Mapper.waitForElementToBeVisible(domFile, "afterLoginImage");
        if(Mapper.find(domFile,"afterLoginImage").isDisplayed()){
            return true;
        }
        else {
            return false;
        }
    }

    public void clickPostFreeAd(){
        Mapper.waitForElementToBeClickable(domFile,"PostFreeAdbutton");
        Mapper.find(domFile,"PostFreeAdbutton").click();
    }

    public void postadBasicInformation(String city,String ProjectName,String area,String price,String rate,String locality, String streetaddress){
        Mapper.waitForElementToBeClickable(domFile, "basicinformation_typeofad");
        Mapper.find(domFile,"basicinformation_typeofad").click();
        Mapper.find(domFile,"typeofad_sell").click();
        Mapper.find(domFile,"basicinformation_city").click();
        Mapper.find(domFile,"basicinformation_city").sendKeys(city);
        Mapper.find(domFile,"basicinformation_project").click();
        Mapper.find(domFile,"basicinformation_project").sendKeys(ProjectName);
        Mapper.find(domFile,"basicinformation_TypeofProperty").click();
        Mapper.find(domFile,"basicinformation_area").click();
        Mapper.find(domFile,"basicinformation_area").sendKeys(area);
        Mapper.find(domFile,"basicinformation_saleprice").sendKeys(price);
        Mapper.find(domFile,"basicinformation_rate").sendKeys(rate);
        Mapper.find(domFile,"basicinformation_Locality").click();
        Mapper.find(domFile,"basicinformation_Locality").sendKeys(locality);
        Mapper.find(domFile,"propertydetails_Streetaddress").sendKeys(streetaddress);
        Mapper.waitForElementToBeClickable(domFile, "basicinformation_continue");
        Mapper.find(domFile,"basicinformation_continue").click();

    }
    public void postadPropertyDetails(String floornumber,String buildingname,String flatnumber) {

        WebElement bedroom = Mapper.finds(domFile, "propertydetails_bedroom").get(0);
        bedroom.click();
        Mapper.find(domFile, "propertydetails_bedroom").click();
        Mapper.find(domFile,"propertydetails_bathroom").click();
        Mapper.find(domFile,"propertydetails_numberofbathroom").click();
        Mapper.find(domFile,"propertydetails_balcony").click();
        Mapper.find(domFile,"propertydetails_numberofbalcony").click();
        Mapper.find(domFile,"propertydetails_floorNumber").sendKeys(floornumber);
        Mapper.find(domFile,"propertydetails_buildingname").sendKeys(buildingname);
        Mapper.find(domFile,"propertydetails_flatnumber").click();
        Mapper.find(domFile,"propertydetails_flatnumber").sendKeys(flatnumber);
        Mapper.find(domFile,"propertydetails_unitfacing").click();
        Mapper.find(domFile,"propertydetails_communityfeatures").click();
        Mapper.find(domFile,"propertydetails_unitfeatures").click();
    }

    public void clickavailablefrom(){
        Mapper.waitForElementToBeClickable(domFile,"propertydetails_availablefrom");
        Mapper.find(domFile,"propertydetails_availablefrom").click();
    }

    public void clickContinueButtonPropertyDetails(){
        Mapper.waitForElementToBeClickable(domFile, "propertydetails_continue");
        Mapper.find(domFile,"propertydetails_continue").click();
    }

    public void postadPhotoandMedia(String title,String addesc){
        Mapper.find(domFile,"photomedia_adtitle").sendKeys(title);
        Mapper.find(domFile,"photomedia_addescription").sendKeys(addesc);
    }

    public void clickContinueButtonPhotomedia(){
        Mapper.waitForElementToBeClickable(domFile, "photomedia_continue");
        Mapper.find(domFile,"photomedia_continue").click();
    }
    public String verifyPostAdMessage(){
        String postadmsg=Mapper.find(domFile,"postad_message").getText();
        return postadmsg;
    }

    public boolean verifyAutoSuggestionQuikrHomes(String query)
    {
        Mapper.find(domFile, "quikrHomesSearchBox").click();
        Mapper.find(domFile, "quikrHomesSearchBox").sendKeys(query);
        List<WebElement> listofSuggestedWords = Mapper.finds(domFile,"quikrHomesSearchBox_autosuggestion");
        List<String> allSuggestions = new ArrayList<>();
        for (WebElement e : listofSuggestedWords)
        {
            allSuggestions.add(e.getText());
        }

        Iterator<String> ite = allSuggestions.iterator();
        while (ite.hasNext()) {
            if (ite.next().substring(0,3).equalsIgnoreCase(query))
            {
                return true;
            }
        }

        return false;
    }


    public void clickAutoSuggestionQuikrHomes(String query) {
        Mapper.find(domFile, "quikrHomesSearchBox").click();
        Mapper.find(domFile, "quikrHomesSearchBox").sendKeys(query);
        List<WebElement> listofSuggestedWords = Mapper.finds(domFile, "quikrHomesSearchBox_autosuggestion");
        listofSuggestedWords.get(0).click();
    }

    public boolean verifySNBPageloaded(){
        if (Mapper.find(domFile,"snb_filter").isDisplayed())
        {
            return true;
        }
        else
        return false;
    }


    public boolean verifySNBPageFlitersandMaps(){

        WebElement property_type=Mapper.find(domFile,"property_type");
        WebElement budget=Mapper.find(domFile, "budget");
        WebElement beds=Mapper.find(domFile,"beds");
        WebElement Area=Mapper.find(domFile,"Area");
        WebElement postedby=Mapper.find(domFile,"postedby");
        WebElement google_maps_div=Mapper.find(domFile,"google_maps_div");
        WebElement google_maps_attributes=Mapper.find(domFile,"google_maps_attributes");
        WebElement google_maps_inner_div=Mapper.find(domFile,"google_maps_inner_div");

        if ((property_type.isDisplayed() && budget.isDisplayed() && property_type.isDisplayed()&& beds.isDisplayed()&&Area.isDisplayed()
                &&postedby.isDisplayed() &&google_maps_div.isDisplayed() &&google_maps_attributes.isDisplayed()&&google_maps_inner_div.isDisplayed()))
        {
            return true;
        }
        else
            return false;
    }

    public boolean timefind() {
        WebElement parentNode  = Mapper.find(domFile, "carousel");
        List<WebElement> childElementsCarousel = Mapper.findChilds(parentNode);
        int i=1;
        int k=4;
        boolean result = false;

        while(!childElementsCarousel.get(i).getAttribute("class").contains("active"))
        {
            i++;
        }

        while(k>1)
        {
            if (i == 4) {
                i = 1;
            } else {
                i++;
            }
            long unixTime = System.currentTimeMillis() / 1000L;
            Mapper.findAndReplace(domFile, "sliderPath", new String[] { Integer.toString(i) });
            result = Mapper.waitForElementToBeVisible(domFile,"sliderPath",10);
            long unixTime1 = System.currentTimeMillis() / 1000L;
            k--;
            if(unixTime1>unixTime){
                return true;
            }
            else
                return false;
        }

        return  result;
    }

    public boolean verifyReplyads(){
        if (Mapper.finds(domFile, "quikhomes_replyads").get(0).isDisplayed()){
            return true;
        }
        else
        return false;
    }

}

