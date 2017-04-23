package com.quikr.msite.mJobs.mJobsSNB;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 30/11/15.
 */
public class MJobsSNBPage extends MPageBase {

    private static final String domFile = getProperties().get("mJobs_SNB_DOM_FILE");
    public MJobsSNBPage(AppiumDriver driver){
        super(domFile, driver);
    }


    public void clickApplyFirstJob(){
        Mapper.waitForElementToBeClickable(domFile,"ApplyButtonFirstJob");
        Mapper.find(domFile,"ApplyButtonFirstJob").click();
    }

    public boolean isApplyFormavailable(){
        boolean flag;

        if(isElementPresent("MobileNumber"))
        {
            flag=true;
        }
        else
        flag=false;

        if(isElementPresent("Locality"))
        {
            flag=true;
        }
        else
            flag=false;

        if(isElementPresent("Submit"))
        {
            flag=true;
        }
        else
            flag=false;

        return flag;
    }

    public void clickFirstAd(){
        Mapper.waitForElementToBeClickable(domFile,"ClickFirstAd");
        Mapper.find(domFile,"ClickFirstAd").click();
    }


    public void clickApplyButton()
    {
        if (Mapper.find(domFile,"ApplyButton")!=null)
        {
            Mapper.find(domFile,"ApplyButton").click();
        }
        else
        {
            logger.info("Apply button not present. Please check!");
        }
    }

    public void inputApplicantName(String appName)
    {
        Mapper.find(domFile,"ApplicantNameInputTextfield").sendKeys(appName);
    }

    public void selectApplicantLocality(String loc)
    {
        Select appLoc = new Select(Mapper.find(domFile,"ApplicantLocality"));
        appLoc.selectByValue(loc);
    }



    public void clickFilterIconSNBJobs(){
        Mapper.waitForElementToBeVisible(domFile, "FilterIconSNB");
        Mapper.find(domFile,"FilterIconSNB").click();
    }

    public void selectFilterbyName(String Filtername){
        Mapper.findAndReplace(domFile, "SelectFilterByNameSNB", new String[]{Filtername}).click();
    }

    /**
     * This function is used to select City from the City Filter by City Name as parameter
      * @param CityName
     */
    public void selectCityFilterByCityName(String CityName){
        Mapper.findAndReplace(domFile, "SelectCityFilterSNB", new String[]{CityName}).click();

    }

    public void clickCheckBoxFilter(String Filtername, Integer numberofoptions){
        for(int i=1;i<=numberofoptions;i++){
           WebElement checkBox=Mapper.findAndReplace(domFile,"SelectCheckBoxFilterSNB",new String[]{Filtername,Integer.toString(i)});
            checkBox.click();
        }
    }

    public void clickApplyFilter(){
        Mapper.waitForElementToBeVisible(domFile,"ApplyFilterSNB");
        Mapper.waitForElementToBeClickable(domFile,"ApplyFilterSNB");
        Mapper.find(domFile,"ApplyFilterSNB").click();
    }

    public String getNumberofJobsfromSNBSearch(){
        return Mapper.find(domFile,"NumberofJobs").getText();
    }

    public void clickSortRecent(){
        Mapper.waitForElementToBeVisible(domFile,"SortDropDown");
        Mapper.waitForElementToBeClickable(domFile, "SortDropDown");
        Mapper.find(domFile,"SortDropDown").click();
        Mapper.waitForElementToBeClickable(domFile, "SortRecent");
        Mapper.find(domFile,"SortRecent").click();

    }

    public void clickSortOldest(){
        Mapper.waitForElementToBeVisible(domFile,"SortDropDown");
        Mapper.waitForElementToBeClickable(domFile, "SortDropDown");
        Mapper.find(domFile,"SortDropDown").click();
        Mapper.waitForElementToBeClickable(domFile, "SortOldest");
        Mapper.find(domFile,"SortOldest").click();
    }


    public boolean validateRecentSort(){

        boolean flag=false;
        List<WebElement> dateofallads;
        List<WebElement> dateofpremiumads;

        List<String> alldates=new ArrayList<>();
        List<String> premiumdates=new ArrayList<>();


        dateofallads=Mapper.finds(domFile,"DateofAllAds");
        dateofpremiumads=Mapper.finds(domFile,"DateofPremiumAds");

        String [] months={"ToHandleZeroIndex", "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        for(int i=0;i<dateofallads.size()-1;i++){
            alldates.add(dateofallads.get(i).getText());

            }

        //System.out.println("Here are ALL dates"+alldates);

        for(int i=0;i<dateofpremiumads.size()-1;i++){
            premiumdates.add(dateofpremiumads.get(i).getText());
        }

        //System.out.println("Here are Premium dates"+premiumdates);

        alldates.removeAll(premiumdates);
        alldates.removeAll(premiumdates);

        /*for(int i=0;i<alldates.size();i++){
            System.out.println("Here are the final dates"+alldates.get(i).toString());
        }
*/
        for(int i=0;i<alldates.size()-1;i++){
            /*System.out.println("Here are substring at i --> "+i + Integer.parseInt(alldates.get(i).toString().substring(0,2)));
            System.out.println("Here are substring at i +1 --> "+(i+1)+" " + Integer.parseInt(alldates.get(i+1).toString().substring(0,2)));
            */if(Integer.parseInt(alldates.get(i).toString().substring(0,2))>=Integer.parseInt(alldates.get(i+1).toString().substring(0,2))){
                flag=true;
            }
            else{
               return false;
            }


            /*System.out.println("Here are substring Months at i --> "+i + alldates.get(i).toString().substring(3,6));
            System.out.println("Here are substring Months at i +1 --> " + (i + 1) + " " + alldates.get(i + 1).toString().substring(3, 6));
            */if(Arrays.asList(months).indexOf(alldates.get(i).substring(3,6))>=Arrays.asList(months).indexOf(alldates.get(i+1).substring(3,6))){
                flag=true;
            }
            else
            {
                return false;
            }
        }

        return flag;
    }


    }


