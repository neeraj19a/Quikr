package com.quikr.website.jobs.jobsSNB;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 5/10/15.
 */
public class JobsSNBPage extends PageBase {

    private static final String domFile = getProperties().get("JOBS_SNB_DOM_FILE");

    public JobsSNBPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    public boolean isJobsSNBPageDisplayed(){
        List<WebElement> snbWebElements=Mapper.finds(domFile, "validSNBPage");
        if(snbWebElements.size()>0){
            return true;
        }
        else
            return false;
    }

    public void clickChatButtonSNBAds(){
        List<WebElement> chatbutton= Mapper.finds(domFile,"chatbuttonAds");
        chatbutton.get(0).click();
    }

    public void setChatNameJobsSNB(String name){
        Mapper.find(domFile, "nameTextBoxonChat").sendKeys(name);
    }

    public void clickChatNowbutton(){
        Mapper.find(domFile,"chatNowButton").click();
    }

    public String chatErrorMessage(){
        String errormessage=Mapper.find(domFile,"chatError").getText();
        return errormessage;
    }

    public void navigatefromJobsSNBPagetoJobsHomePage(){
        navigateTo().back();
    }

    public void clickAdTitleJobs(){
        Mapper.waitForElementToBeVisible(domFile, "AdsTitleSNB");
        List<WebElement> ads=Mapper.finds(domFile, "AdsTitleSNB");
        ads.get(0).click();
    }


    public String getAdTitleJobs(){
        Mapper.waitForElementToBeVisible(domFile, "AdsTitleSNB");
        List<WebElement> ads=Mapper.finds(domFile, "AdsTitleSNB");
        String title=ads.get(0).getText();
        return title.substring(0, 3);
    }

    public String getCityFromAds() {
        return Mapper.find(domFile, "CityOfAdsSNBPage").getText();
    }

    public boolean validateDateSortingRecent()
    {
        boolean finalVal=false;
        Mapper.find(domFile, "filterLabel");
        Mapper.find(domFile,"sortByDefaultPopular").click();
        WebElement elm = Mapper.find(domFile, "sortByRecent");
        elm.click();
        List<WebElement> dateElms = Mapper.finds(domFile,"adPostingDates");//has the whole date string text  <- 16 Oct 2015>
        List dateStrings = new ArrayList(); //has <16 oct 2015> i.e its trimmed
        String[] Months = {"ToHandleZeroIndex", "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        for (int i=0; i<dateElms.size();i++)
        {
            dateStrings.add(dateElms.get(i).getText().toString().substring(2));
        }

        if (dateStrings.get(0).toString().length()==11)
        {
            for (int i=0;i<dateStrings.size()-1;i++)
            {
                int intDateOne = Integer.parseInt(dateStrings.get(i).toString().substring(0,2));
                int intDateTwo = Integer.parseInt(dateStrings.get(i+1).toString().substring(0,2));
                if (intDateOne>=intDateTwo)
                {
                    finalVal=true;
                }else
                {
                    logger.info("Dates are not sorted in recent manner.");
                    return false;
                }

                String strMonthOne = dateStrings.get(i).toString().substring(3,6);
                String strMonthTwo = dateStrings.get(i+1).toString().substring(3,6);
                logger.info(">>>"+dateStrings.get(i).toString().substring(3,6));
                logger.info(">>>"+dateStrings.get(i+1).toString().substring(3,6));
                if (Arrays.asList(Months).indexOf(strMonthOne)>=Arrays.asList(Months).indexOf(strMonthTwo))
                {
                    finalVal=true;
                }else
                {
                    logger.info("Months are not sorted in recent manner.");
                    return false;
                }
            }
        }
        else
        {
            for (int i=0;i<dateStrings.size()-1;i++)
            {
                int intDateOne = Integer.parseInt(dateStrings.get(i).toString().substring(0,1));
                int intDateTwo = Integer.parseInt(dateStrings.get(i+1).toString().substring(0,1));
                if (intDateOne>=intDateTwo)
                {
                    finalVal=true;
                }else
                {
                    logger.info("Dates are not sorted in recent manner.");
                    return false;
                }

                String strMonthOne = dateStrings.get(i).toString().substring(3, 5);
                String strMonthTwo = dateStrings.get(i+1).toString().substring(3, 5);
                if (Arrays.asList(Months).indexOf(strMonthOne)>Arrays.asList(Months).indexOf(strMonthTwo))
                {
                    finalVal=true;
                }else
                {
                    logger.info("Months are not sorted in recent manner.");
                    return false;
                }
            }
        }
        return finalVal;
    }

    public boolean checkBreadCrumb()
    {
        boolean finalVal=true;
        if (Mapper.find(domFile,"breadcrumbs").isDisplayed())
        {
            finalVal=true;
        }else
        {
            logger.info("Breadcrumbs are not being displayed.");
        }
        return finalVal;
    }

    public String isFreeTextSearchReflectsonSNB(){
        String breadcrumbtext=Mapper.find(domFile,"breadcrumbJobType").getText();
        return breadcrumbtext;

    }
    public boolean validateSnbHeaders()
    {
        boolean finalVal=false;
        Mapper.waitForElementToBeVisible(domFile, "filterLabel");

        if (Mapper.find(domFile,"selectLocality").isDisplayed())
        {
            finalVal=true;
        }else
        {
            logger.info("Select Locality is not being displayed.");
        }

        if (Mapper.find(domFile,"selectSalary").isDisplayed())
        {
            finalVal=true;
        }else
        {
            logger.info("Select Salary is not being displayed.");
        }

        Mapper.find(domFile,"moreButton").click();
        if (Mapper.find(domFile,"selectExperience").isDisplayed())
        {
            finalVal=true;
        }else
        {
            logger.info("Select Experience is not being displayed.");
        }
        Mapper.find(domFile,"moreButton").click();

        finalVal= checkBreadCrumb();

        if (Mapper.find(domFile,"adCountString").isDisplayed())
        {
            finalVal=true;
        }else
        {
            logger.info("Ad count not being displayed.");
        }

        if (Mapper.find(domFile,"sortByDefaultPopular").isDisplayed())
        {
            finalVal=true;
        }else
        {
            logger.info("Sort option not being displayed.");
        }

        if (Mapper.find(domFile,"moreButton").isDisplayed())
        {
            finalVal=true;
        }else
        {
            logger.info("More button not being displayed.");
        }
        return finalVal;
    }

    public boolean validateDateSortingOldest()
    {
        boolean finalVal=false;
        Mapper.find(domFile, "filterLabel");
        Mapper.find(domFile,"sortByDefaultPopular").click();
        WebElement elm = Mapper.find(domFile, "sortByOldest");
        elm.click();
        List<WebElement> dateElms = Mapper.finds(domFile,"adPostingDates");//has the whole date string text  <- 16 Oct 2015>
        List dateStrings = new ArrayList(); //has <16 oct 2015> i.e its trimmed
        String[] Months = {"ToHandleZeroIndex", "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        for (int i=0; i<dateElms.size();i++)
        {
            dateStrings.add(dateElms.get(i).getText().toString().substring(2));
        }

        if (dateStrings.get(0).toString().length()==11)
        {
            for (int i=0;i<dateStrings.size()-1;i++)
            {
                int intDateOne = Integer.parseInt(dateStrings.get(i).toString().substring(0,2));
                int intDateTwo = Integer.parseInt(dateStrings.get(i+1).toString().substring(0,2));
                logger.info(intDateOne+"###"+intDateTwo);
                if (intDateOne<=intDateTwo)
                {
                    finalVal=true;
                }else
                {
                    finalVal=false;
                    logger.info("Dates are not sorted in recent manner.");
                }

                String strMonthOne = dateStrings.get(i).toString().substring(3,6);
                String strMonthTwo = dateStrings.get(i+1).toString().substring(3,6);
                logger.info(">>>"+dateStrings.get(i).toString().substring(3,6));
                logger.info(">>>"+dateStrings.get(i+1).toString().substring(3,6));
                if (Arrays.asList(Months).indexOf(strMonthOne)<=Arrays.asList(Months).indexOf(strMonthTwo))
                {
                    finalVal=true;
                }else
                {
                    finalVal=false;
                    logger.info("Months are not sorted in recent manner.");
                }
            }
        }
        else
        {
            for (int i=0;i<dateStrings.size()-1;i++)
            {
                int intDateOne = Integer.parseInt(dateStrings.get(i).toString().substring(0,1));
                int intDateTwo = Integer.parseInt(dateStrings.get(i+1).toString().substring(0,1));
                if (intDateOne<=intDateTwo)
                {
                    finalVal=true;
                }else
                {
                    finalVal=false;
                    logger.info("Dates are not sorted in recent manner.");
                }

                String strMonthOne = dateStrings.get(i).toString().substring(3, 5);
                String strMonthTwo = dateStrings.get(i+1).toString().substring(3, 5);
                if (Arrays.asList(Months).indexOf(strMonthOne)<=Arrays.asList(Months).indexOf(strMonthTwo))
                {
                    finalVal=true;
                }else
                {
                    finalVal=false;
                    logger.info("Months are not sorted in recent manner.");
                }
            }
        }
        return finalVal;
    }

    public int getAdCount()
    {
        return Integer.parseInt(Mapper.find(domFile, "adCountString").getText().toString());
    }

    public boolean verifyResetButton()
    {
        boolean finalVal = false;
        int initialAdCount = getAdCount();
        Mapper.find(domFile,"selectLocality").click();
        Mapper.find(domFile,"selectLocalityFirstOption").click();
        int finalAdCount = getAdCount();
        Mapper.find(domFile,"resetButton").click();
        int resetCount = getAdCount();
        if (initialAdCount==resetCount)
        {
            finalVal=true;
        }
        return finalVal;
    }

    public boolean isAdAllDetailsdiplayed(){
        return Mapper.find(domFile,"AdswithAllDetails").isDisplayed();
    }

    public boolean isApplyButtonavailable(){
        List<WebElement> applybutton=Mapper.finds(domFile, "applyButton");
        if(applybutton.size()>0){
            return true;
        }
        else
            return false;
    }

    public String clickApplyButton(int i){
        List<WebElement> applybutton=Mapper.finds(domFile, "applyButton");
        applybutton.get(i).click();
        return Mapper.find(domFile,"ApplyJobTitle").getText();
    }


    public void clickAdwithAlldetails(){
        List<WebElement> adswithalldetails=Mapper.finds(domFile, "AdswithAllDetails");
        adswithalldetails.get(0).click();
    }

    public void clickPremiumAds(){
        Mapper.find(domFile,"PremiumAd").click();
    }

    public void selectOptionFromCompanyFilter()
    {
        Mapper.waitForElementToBeVisible(domFile,"moreButton");
        Mapper.find(domFile,"moreButton").click();
        Mapper.waitForElementToBeVisible(domFile, "companyFilter");
        Mapper.find(domFile,"companyFilter").click();
        Mapper.waitForElementToBeVisible(domFile, "companyFilterFirstOption");
        Mapper.find(domFile,"companyFilterFirstOption").click();
        Mapper.find(domFile,"doneButton").click();
    }

    public  boolean validateSnbResults(String searchKey)
    {
        int countTrue=0;
        int countFalse=0;
        boolean finalVal=false;

        Mapper.waitForElementToBeVisible(domFile,"filterLabel");
        Mapper.waitForElementToBeVisible(domFile, "adCountString");
        Mapper.find(domFile, "adCountString");
        List<WebElement> adTitles = Mapper.finds(domFile, "AdsTitleSNB");

        for (int i=0;i<adTitles.size();i++)
        {
//            System.out.println(adTitles.get(i).getText());
            if (adTitles.get(i).getText().contains(searchKey))
            {
                countTrue=countTrue+1;
            }else
            {
                countFalse=countFalse+1;
            }
        }
//        System.out.println(countTrue + "T#F" + countFalse);
        if (countTrue>adTitles.size()/4)
        {
            finalVal=true;
        }
        return finalVal;
    }

    public boolean isselectCityFromCityFilterVisible() {
        try {
            if (Mapper.find(domFile,"cityFilterSNB").isDisplayed());
            return true;
        }
        catch (Exception e){
            return false;
        }

            }

    public void selectCityFromCityFilter(String city)
    {
        Mapper.waitForElementToBeVisible(domFile, "cityFilterSNB");
        Mapper.find(domFile,"cityFilterSNB").click();
        Mapper.findAndReplace(domFile, "selectcityByNameFilterSNB", new String[]{city}).click();

    }

    public String selectOptionFromLocalityFilter()
    {
        Mapper.waitForElementToBeVisible(domFile, "selectLocality");
        Mapper.find(domFile,"selectLocality").click();
        Mapper.waitForElementToBeVisible(domFile, "selectLocalityFirstOption");
        String localityText = Mapper.find(domFile,"selectLocalityFirstOption").getAttribute("data-label");
        Mapper.find(domFile, "selectLocalityFirstOption").click();
        return localityText;
    }

    public boolean validateLocalitySnbResults(String localityFromLocalityFilter)
    {
        boolean finalVal = false;
        Mapper.waitForElementToBeVisible(domFile,"adCountString");
        List <WebElement> elms = Mapper.finds(domFile, "localityLabelsInSnbAds");

        for (int i=0;i<elms.size();i++)
        {
            if (elms.get(i).getText().toString().contains(localityFromLocalityFilter))
            {
                finalVal=true;
            }
        }
        return finalVal;
    }


    public String selectOptionFromSalaryFilter() {
        Mapper.waitForElementToBeVisible(domFile, "selectSalary");
        Mapper.find(domFile, "selectSalary").click();
        Mapper.waitForElementToBeVisible(domFile, "selectSalarySecondOption");
        String salText = Mapper.find(domFile, "selectSalarySecondOption").getAttribute("value");
        Mapper.find(domFile, "selectSalarySecondOption").click();
        return salText;
    }
    public  void clickApplyButtonPremiumJobwithAllDetails(){
        List<WebElement> apply=Mapper.finds(domFile, "PremiumAdsapplyButtonJobwithAllDetails");
        apply.get(0).click();
    }

    public  void clickApplyButtonPremiumandUrgentJobwithAllDetails(){
        Mapper.waitForElementToBeVisible(domFile, "PremiumandUrgentAdsapplyButtonJobwithAllDetails");
        List<WebElement> apply=Mapper.finds(domFile, "PremiumandUrgentAdsapplyButtonJobwithAllDetails");
        apply.get(0).click();
    }

    public boolean isApplyJobFormOpen(){
        return Mapper.find(domFile,"applyFormHeading").isDisplayed();
    }

    public boolean isPremiumBandavailable(){
        return Mapper.find(domFile,"premiumBandonSNB").isDisplayed();
    }

    public boolean isPremiumandUrgentBandavailable(){
        return isElementPresent("premiumandUrgentBandonSNB");
    }

    public boolean jobImageSNB(){
        return isElementPresent("jobImageSNB");
    }

    public String getSortByText(){
        return Mapper.find(domFile,"sortBy").getText();
    }

    public void clickLocality(){
        Mapper.find(domFile,"selectLocality").click();
    }

    public void     selectDropDownOptionsLocation(){
        List<WebElement> filterdropdown;
        for(int i=0;i<=9;i++){
            filterdropdown = Mapper.finds(domFile,"selectLocalityAllDropDownOptions");
            filterdropdown.get(i).click();
            Mapper.find(domFile, "selectLocality").click();
        }
    }

    public void clickSalary(){
        Mapper.find(domFile,"selectSalary").click();
    }

    public void selectDropDownOptionsSalary(){
        List<WebElement> filterdropdown;
        for(int i=0;i<=9;i++){
            filterdropdown = Mapper.finds(domFile,"selectSalaryAllDropDownOptions");
            filterdropdown.get(i).click();
            Mapper.find(domFile, "selectSalary").click();
        }
    }

    public void clickJobType()
    {
        Mapper.find(domFile,"selectJobType").click();
    }

    public void selectDropDownOptionsJobType()
    {
        List<WebElement> filterdropdown;
        for(int i=0;i<=3;i++){
            filterdropdown = Mapper.finds(domFile,"selectJobTypeAllDropDownOptions");
            filterdropdown.get(i).click();
            Mapper.find(domFile, "selectJobType").click();
        }
    }

    public void clickMoreButton(){
        Mapper.find(domFile,"moreButton").click();
    }

    public boolean isJobSearchResultsDisplayJobs()  {
        String text=Mapper.find(domFile, "NumberorCountOfJobs").getText();
        int temp=Integer.parseInt(text);
        if(temp<0){
            return false;
        }
        else
            return true;

    }

    public boolean validateSalarySearchResults()
    {
        int countTrue = 0;
        int countFalse = 0;
        boolean finalVal = false;
        List<WebElement> elms = Mapper.finds(domFile, "SalaryLabelsInSnbAds");

        //Checks if the count of result is zero. in this case no didn't of further validation. Returns true here itself, else proceed forward.
        if (Mapper.find(domFile,"adCountString")!=null)
        {
            String adCount = Mapper.find(domFile,"adCountString").getText();
            if (adCount.equals("0"))
            {
                return true;
            }
        }else
        {
            logger.info("Ad count string is null. Probably SNB page didn't load properly. Check please.");
            return false;
        }

        for (int i=0; i<elms.size();i++)
        {
            if (elms.get(i).getText().toString().contains("Less than Rs. 1L"))
            {
                countTrue+=1;
            }else
            {
                countFalse+=1;
            }
        }

        if (countTrue>countFalse)
        {
            finalVal=true;
        }
        return finalVal;
    }

    public void selectOptionFromExpFilter()
    {
        Mapper.waitForElementToBeVisible(domFile, "selectExperience");
        Mapper.find(domFile,"selectExperience").click();
        Mapper.waitForElementToBeVisible(domFile, "selectExpSecondOption");
        String exp = Mapper.find(domFile,"selectExpSecondOption").getAttribute("value").toString();
        Mapper.find(domFile,"selectExpSecondOption").click();
    }

    public void selectOptionFromJobsType()
    {
        Mapper.waitForElementToBeVisible(domFile,"selectJobType");
        Mapper.find(domFile,"selectJobType").click();
        Mapper.waitForElementToBeVisible(domFile, "JobTypeLabels");
        Mapper.find(domFile,"JobTypeLabels").click();
    }

    public boolean checkFilters()
    {
        boolean finalVal = false;
        if (Mapper.find(domFile,"selectLocality").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            return false;
        }

        if (Mapper.find(domFile,"selectJobType").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            return false;
        }

        if (Mapper.find(domFile,"selectSalary").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            return false;
        }

        Mapper.find(domFile,"moreButton").click();

        Mapper.waitForElementToBeVisible(domFile,"selectExperience");
        if (Mapper.find(domFile,"selectExperience").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            return false;
        }

        if (Mapper.find(domFile,"educationFilter").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            return false;
        }

        if (Mapper.find(domFile, "companyFilter").isDisplayed())
        {
            finalVal=true;
        }
        else
        {
            return false;
        }
        return finalVal;
    }

    public boolean validateResetWithJobFilter()
    {
        boolean retVal = false;
        //Mapper.find(domFile,"JobTypeFromHomePage").click();
        //Mapper.find(domFile,"resetButton").click();
        System.out.println(Mapper.find(domFile,"jobTypeFilter").getText());
        if (Mapper.find(domFile,"jobTypeFilter").getText().contains("Work From Home"))
        {
            retVal=true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public boolean isResetButtonDisplayed()
    {
        boolean retVal = false;
        if (Mapper.find(domFile,"resetButton")!=null)
        {
            retVal = true;
        }
        else
        {
            logger.info("Reset button returned null.");
            return false;
        }
        return retVal;
    }

    public boolean checkGoogleAds()
    {
        boolean retVal = false;
        if (Mapper.find(domFile,"topMostGoogleAd")!=null)
        {
            retVal=true;
        }
        else
        {
            logger.info("Top most google ad not displayed.");
            return false;
        }

        if (Mapper.find(domFile,"middleGoogleAd")!=null)
        {
            retVal=true;
        }
        else
        {
            logger.info("Mid google ad not displayed.");
            return false;
        }

        if (Mapper.find(domFile,"lowerMostGoogleAd")!=null)
        {
            retVal=true;
        }
        else
        {
            logger.info("bottom most google ad not displayed.");
            return false;
        }
        return retVal;
    }

    public boolean isJobSearchResultRelatedtoJobRole(String rolesearch){
        List<WebElement> roles=Mapper.finds(domFile, "RoleOfJobsearchResultSNBS");
        List<String >rolenames=new ArrayList<>();
        int count=0;
        for(int i=0;i<roles.size()-1;i++){
            rolenames.add(roles.get(i).getText());
        }

        for(int j=0;j<rolenames.size()-1;j++){
            if(rolenames.get(j).contains(rolesearch))
            {
                count++;
            }
        }

        if(count>rolenames.size()/4){
            return true;
        }
        else
            return false;


    }

    public boolean isScrollUpIconSNBworking(){

        Mapper.scrollVerticallWithCords(10, 3400);
        Mapper.waitForElementToBeVisible(domFile, "ScrollUpIconSNBPage");
        if(Mapper.find(domFile,"ScrollUpIconSNBPage")!=null){
            Mapper.find(domFile,"ScrollUpIconSNBPage").click();
        }
        Mapper.waitForElementToBeInvisible(domFile, "ScrollUpIconSNBPageVisibleState");
        if(Mapper.find(domFile,"ScrollUpIconSNBPageVisibleState")==null){
            return true;
        }
        else
            return false;
    }

    public void fillApplyForm1WithoutLogin(String Name, String MobNumber, String EmailId){
        Mapper.waitForElementToBeClickable(domFile, "applyFormName");
        Mapper.find(domFile,"applyFormName").sendKeys(Name);
        Mapper.find(domFile,"applyFormMobile").sendKeys(MobNumber);
        Mapper.find(domFile,"applyFormEmailId").sendKeys(EmailId);
        Mapper.find(domFile,"applyFormLocality").click();
        Mapper.find(domFile,"applyFormSelectLocality").click();
        Mapper.find(domFile,"applyFormSubmitButton").click();
    }


    public void fillApplyForm1WithLogin(String Name, String MobNumber){
        Mapper.waitForElementToBeClickable(domFile, "applyFormName");
        Mapper.find(domFile,"applyFormName").sendKeys(Name);
        Mapper.find(domFile,"applyFormMobile").sendKeys(MobNumber);
        Mapper.find(domFile,"applyFormLocality").click();
        Mapper.find(domFile,"applyFormSelectLocality").click();
        Mapper.find(domFile,"applyFormSubmitButton").click();

    }

    public boolean isCheckBoxForPresentonApplyForm(){
        return isElementPresent("CheckBoxMandatoryFields");
    }
    public void fillApplyForm2(){
        Mapper.waitForElementToBeClickable(domFile, "applyForm2Option1");
        Mapper.find(domFile,"applyForm2Option1").click();
        Mapper.find(domFile,"applyForm2Option1Value").click();
        Mapper.find(domFile,"applyForm2Option1").click();
        Mapper.waitForElementToBeClickable(domFile, "applyForm2Option2");
        Mapper.find(domFile,"applyForm2Option2").click();
        Mapper.find(domFile,"applyForm2Option2Value").click();
        Mapper.waitForElementToBeClickable(domFile, "applyForm2Option3");
        Mapper.find(domFile,"applyForm2Option3").click();
        Mapper.find(domFile,"applyForm2Option3Value").click();
        Mapper.find(domFile,"applyForm2Option3").click();
        Mapper.waitForElementToBeClickable(domFile, "applyForm2Option4");
        Mapper.find(domFile,"applyForm2Option4").click();
        Mapper.find(domFile,"applyForm2Option4Value").click();
        Mapper.waitForElementToBeVisible(domFile, "applyForm2Option5");
        Mapper.waitForElementToBeClickable(domFile, "applyForm2Option5");
        Mapper.find(domFile,"applyForm2Option5").click();
        Mapper.waitForElementToBeClickable(domFile, "applyForm2Option5Value");
        Mapper.find(domFile,"applyForm2Option5Value").click();
        Mapper.find(domFile,"applyForm2Option5").click();
        Mapper.waitForElementToBeClickable(domFile, "applyForm2SubmitButton");
        Mapper.find(domFile,"applyForm2SubmitButton").click();
    }

    public boolean isApplyForm1Prefilled(String Name, String MobNumber, String EmailId){

        boolean flag=false;
        if (Mapper.waitForElementToBeVisible(domFile,"applyFormName")==true) {
            System.out.println("Name :: " + Mapper.find(domFile, "applyFormName").getText());
            if (Mapper.find(domFile, "applyFormName").getText().contains(Name)) {
                flag = true;
            } else {
                return false;
            }


            Mapper.waitForElementToBeVisible(domFile, "applyFormMobile");
            System.out.println("Mobile :: " + Mapper.find(domFile, "applyFormMobile").getText());
            if (Mapper.find(domFile, "applyFormMobile").getText().contains(MobNumber)) {
                flag = true;
            } else {
                return false;
            }
            Mapper.waitForElementToBeVisible(domFile, "applyFormEmailId");
            System.out.println("EMail :: " + Mapper.find(domFile, "applyFormEmailId").getText());

            if (Mapper.find(domFile, "applyFormEmailId").getText().contains(EmailId)) {
                flag = true;
            } else {
                return false;
            }
        }
        return flag;
    }


    public boolean isJobApplied(){
        if(Mapper.find(domFile,"ThankYouMessage").getText().contains("Thank"))
        return true;
        else
            return false;
    }

    public String gettextBeforeJobApply(){
        Mapper.waitForElementToBeVisible(domFile,"ApplyJobTitle");
        return Mapper.find(domFile, "ApplyJobTitle").getText();
    }
    public String getTextAppliedJob(String titleofAd){
        //Mapper.waitForElementToBeVisible(domFile,"AppliedJobTitle");
        return Mapper.findAndReplace(domFile, "AppliedJobTitle", new String[]{titleofAd}).getText();

    }

    public boolean isAppliedButtonavailable(int i){

        List<WebElement> appliedbutton=Mapper.finds(domFile, "applyButton");
        if(appliedbutton.get(i).isDisplayed()) {
            return true;
        }
        else
            return false;
    }

    public void clickRecentAds(){
        Mapper.find(domFile, "SortFilter").click();
        Mapper.waitForElementToBeVisible(domFile, "SortRecent");
        Mapper.waitForElementToBeClickable(domFile, "SortRecent");
        Mapper.find(domFile,"SortRecent").click();
    }

    public boolean isEntireLocalityPresent(){
        Mapper.find(domFile,"applyFormLocality").click();
        Mapper.waitForElementToBeVisible(domFile, "NumberofLocalityApplyForm");
        List<WebElement> numberofloaclity=Mapper.finds(domFile, "NumberofLocalityApplyForm");
        if(numberofloaclity.size()==1){
           return true;
        }
        else
        {
            return false;
        }
    }
}
