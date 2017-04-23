package com.quikr.msite.mElectronics.mElectronicsSNB;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 6/11/15.
 */
public class MElectronicsSnBPage extends MPageBase {

    private static final String domFile = getProperties().get("mElectronics_SnB_DOM_FILE");

    public MElectronicsSnBPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    public boolean isSnBPageavailable() {
        Mapper.waitForElementToBeVisible(domFile, "Adlist");
        return isElementPresent("Adlist");
    }

    public boolean iscontentsonSnBPageavailable() {
        boolean flag = false;
        if (isElementPresent("sort") == true) {
            flag = true;
        } else {
            return false;
        }
        if (isElementPresent("filter") == true) {
            flag = true;
        } else {
            return false;
        }

        if (isElementPresent("SearchInput") == true) {
            flag = true;
        } else {
            return false;
        }

        if (isElementPresent("Adlist") == true) {
            flag = true;
        } else {
            return false;
        }

        return flag;
    }

    public void clickSort(String sorttype) {
        Mapper.waitForElementToBeClickable(domFile, "sort");
        Mapper.find(domFile, "sort").click();
        Mapper.findAndReplace(domFile, "sorttype", new String[]{sorttype}).click();
    }

    public boolean validateSortLowestPrice() {

        ArrayList<Integer> allads = new ArrayList<>();
        List<WebElement> alladswebelement;
        List<WebElement> premiumadswebelement;

        ArrayList<Integer> premiumads = new ArrayList<>();
        alladswebelement = Mapper.finds(domFile, "PricesallAds");
        for (int i = 0; i < alladswebelement.size(); i++) {
            allads.add(Integer.parseInt(alladswebelement.get(i).getText().replaceAll("\\D", "")));
        }

        premiumadswebelement = Mapper.finds(domFile, "PricesPremiumAds");
        for (int i = 0; i < premiumadswebelement.size(); i++) {
            premiumads.add(Integer.parseInt(premiumadswebelement.get(i).getText().replaceAll("\\D", "")));
        }

        System.out.println("Here " + allads.removeAll(premiumads));
        System.out.println("Here " + allads.removeAll(premiumads));
        allads.removeAll(premiumads);

        List copy = new ArrayList(allads);
        Collections.sort(copy);
        //return copy.equals(allads);
        for (int i = 0; i < copy.size(); i++) {

            if ((int) copy.get(i) != (int) allads.get(i)) {

                System.out.println(copy.get(i) + " " + allads.get(i));
                return false;
            }

        }
        return true;
    }


    public boolean validateSortHighestPrice() {
        ArrayList<Integer> allads = new ArrayList<>();
        List<WebElement> alladswebelement;
        List<WebElement> premiumadswebelement;
        ArrayList<Integer> premiumads = new ArrayList<>();
        alladswebelement = Mapper.finds(domFile, "PricesallAds");
            for (int i = 0; i < alladswebelement.size(); i++) {
                allads.add(Integer.parseInt(alladswebelement.get(i).getText().replaceAll("\\D", "")));
    }
        premiumadswebelement = Mapper.finds(domFile, "PricesPremiumAds");
            for (int i = 0; i < premiumadswebelement.size(); i++) {
                premiumads.add(Integer.parseInt(premiumadswebelement.get(i).getText().replaceAll("\\D", "")));
    }
        System.out.println("Here "+alladswebelement);

        allads.removeAll(premiumads);
        allads.removeAll(premiumads);
        System.out.println("Finally Here"+allads);

        List copy = new ArrayList(allads);
        Collections.sort(copy);
        Collections.reverse(copy);

        System.out.println("Copy is here" + copy);
    //return copy.equals(allads);
        for (int i =0; i <copy.size(); i++) {
            if ((int) copy.get(i) != allads.get(i)) {
                System.out.println(copy.get(i) + " " + allads.get(i));
            return false;
        }
    }
    return true;
}

    /*Need to Revisit keeping Prints */
    public boolean validateMostRecentSort(){
        List<WebElement> allads;
        List<WebElement> premiumads;
        ArrayList<String> alladsposttime=new ArrayList<>();
        ArrayList<String> premiumadsposttime=new ArrayList<>();
        ArrayList<Integer> time=new ArrayList<>();


        allads=Mapper.finds(domFile, "TimeofAllAdsPost");
        premiumads=Mapper.finds(domFile,"TimeofPremiumAdsPost");

        for(int i=0;i<allads.size();i++)
        {
            alladsposttime.add(allads.get(i).getText().toString());
        }

        for(int i=0;i<premiumads.size();i++)
        {
            premiumadsposttime.add(premiumads.get(i).getText().toString());
        }

        System.out.println("Size of ads "+alladsposttime);
        System.out.println("Size of premium ads "+premiumadsposttime);

        System.out.println("ABle to remove-->" + alladsposttime.removeAll(premiumadsposttime));

        Iterator iterator =  alladsposttime.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }

        for (int i=0;i<alladsposttime.size();i++) {
            if (alladsposttime.get(i).toString().contains("sec") &&alladsposttime.get(i).toString().length()==14){
                System.out.println("At position "+ i +"Seconds are -->"+alladsposttime.get(i).toString().substring(7,9)+" Text is "+alladsposttime.get(i).toString());
                time.add(i,Integer.parseInt(alladsposttime.get(i).toString().substring(7,9)));
            }
            else if (alladsposttime.get(i).toString().contains("min") &&alladsposttime.get(i).toString().length()==12 || alladsposttime.get(i).toString().contains("mins") && alladsposttime.get(i).toString().length()==13) {
                System.out.println("At position "+ i +"minutes are -->"+alladsposttime.get(i).toString().substring(7,8)+" Text is "+alladsposttime.get(i).toString());
                time.add(i,Integer.parseInt(alladsposttime.get(i).toString().substring(7,8)) * 60);
            }

            else if (alladsposttime.get(i).toString().contains("mins") && alladsposttime.get(i).toString().length()==14) {
                time.add(i,Integer.parseInt(alladsposttime.get(i).toString().substring(7,9)) * 60);
            }

        }
        System.out.println("Time is " + time);
        System.out.println("Size of time is" + time.size());
        for (int i=0;i<time.size();i++) {
            System.out.println("After Now Time is"+time.get(i));
        }

        List copy=new ArrayList(time);
        Collections.sort(copy);
        for(int i=0;i<copy.size();i++) {
            if (copy.get(i)!=time.get(i)){
                System.out.println(copy.get(i) + " " + allads.get(i));
                return false;
            }

        }
        return true;
    }

    public void clickFilter()
    {
        Mapper.find(domFile,"filter").click();
    }

    public void clickUsedConditionFilterPage()
    {
        Mapper.find(domFile,"usedButtonFilterPage").click();
    }

    public void clickIndividualFilterPage()
    {
        Mapper.find(domFile,"individualButtonFilterPage").click();
    }

    public void selectProductType(String dropdownValue)
    {
        Select prodType = new Select(Mapper.find(domFile,"productTypeDropdown"));
        prodType.selectByValue(dropdownValue);
    }

    public void applyFilter()
    {
        Mapper.find(domFile, "ApplyFilter").click();
    }

    public void clickAdNumber(int AdNumber){
        Mapper.findAndReplace(domFile, "AdNumber", new String[]{Integer.toString(AdNumber)}).click();
    }

    public void clickOnAnAd()
    {
        Mapper.find(domFile, "firstAd").click();
    }

    public boolean validateFilters() {
        boolean finalVal = false;
        List<WebElement> elm = Mapper.finds(domFile, "filteredResultData");
        List<String> elmStr = new ArrayList();
        for (int i = 0; i < elm.size(); i++) {
            elmStr.add(i, elm.get(i).getText());
        }

        if (elmStr.contains("Used") && elmStr.contains("Owner") && elmStr.contains("Desktop")) {
            finalVal = true;
        } else {
            logger.info("ERROR!");
            return false;
        }
        return finalVal;
    }

    public boolean validateSortwithPictures(){

            boolean flag=false;
        List<WebElement> allads;
        List<WebElement> premiumads;
        ArrayList<String> alladsposttime=new ArrayList<>();
        ArrayList<String> premiumadsposttime=new ArrayList<>();
        ArrayList<Integer> time=new ArrayList<>();


        allads=Mapper.finds(domFile, "TimeofAllAdsPost");
        premiumads=Mapper.finds(domFile,"TimeofPremiumAdsPost");

        for(int i=0;i<=allads.size();i++)
        {
            alladsposttime.add(allads.get(i).getText().toString());
        }

        for(int i=0;i<=premiumads.size();i++)
        {
            premiumadsposttime.add(premiumads.get(i).getText().toString());
        }

        System.out.println("Size of ads "+alladsposttime);
        System.out.println("Size of premium ads "+premiumadsposttime);

        System.out.println("ABle to remove-->" + alladsposttime.removeAll(premiumadsposttime));

        Iterator iterator =  alladsposttime.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }

        for (int i=0;i<=alladsposttime.size();i++) {
            if (alladsposttime.get(i).toString().contains("sec") &&alladsposttime.get(i).toString().length()==14){
                System.out.println("At position "+ i +"Seconds are -->"+alladsposttime.get(i).toString().substring(7,9)+" Text is "+alladsposttime.get(i).toString());
                time.add(i,Integer.parseInt(alladsposttime.get(i).toString().substring(7,9)));
            }
            else if (alladsposttime.get(i).toString().contains("min") &&alladsposttime.get(i).toString().length()==12 || alladsposttime.get(i).toString().contains("mins") && alladsposttime.get(i).toString().length()==13) {
                System.out.println("At position "+ i +"minutes are -->"+alladsposttime.get(i).toString().substring(7,8)+" Text is "+alladsposttime.get(i).toString());
                time.add(i,Integer.parseInt(alladsposttime.get(i).toString().substring(7,8)) * 60);
            }

            else if (alladsposttime.get(i).toString().contains("mins") && alladsposttime.get(i).toString().length()==14) {
                time.add(i,Integer.parseInt(alladsposttime.get(i).toString().substring(7,9)) * 60);
            }

        }
        System.out.println("Time is " + time);
        System.out.println("Size of time is" + time.size());
        for (int i=0;i<=time.size();i++) {
            System.out.println("After Now Time is"+time.get(i));
        }

        List copy=new ArrayList(time);
        Collections.sort(copy);
        for(int i=0;i<=copy.size();i++) {
            if (copy.get(i)!=time.get(i)){
                System.out.println(copy.get(i) + " " + allads.get(i));
                flag=false;
            }
            else {
                flag = true;
            }

                if(isAdswithoutImagesavailabe()){
                    flag=true;
                }
                else {
                    flag=false;
                }

            }
            return flag;
        }


    public boolean isAdswithoutImagesavailabe(){
        List<WebElement> adswithtImages=Mapper.finds(domFile, "AdswithoutImages");
        if(adswithtImages.size()>0){
            return false;
        }
        else
            return true;

    }

    public boolean validateChatIconsavailable(){
        List<WebElement> chaticons=Mapper.finds(domFile,"ChatIcons");
        if(chaticons.size()>0){
            return true;
        }
        else
            return false;
    }

    public void clickAdswithChatOption(){
        Mapper.waitForElementToBeClickable(domFile,"AdswithChat");
        Mapper.find(domFile,"AdswithChat").click();
    }

    public void clickBuyNow()
    {
        if (Mapper.waitForElementToBeClickable(domFile, "BuyNowButton")==true)
        {
            Mapper.find(domFile,"BuyNowButton").click();
        }
        else
        {
            logger.info("Buy now button is either not visible or the Snb page has no buy now buttons!!!! Please check!");
        }
    }

    public boolean validateBuyNowWindow()
    {
        boolean retVal = false;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Mapper.waitForElementToBeVisible(domFile, "BuyNowLabel")==true)
        {
            String label = Mapper.find(domFile,"BuyNowLabel").getText();
            if (label.equalsIgnoreCase("BUY NOW")==true)
            {
                retVal = true;
            }else
            {
                logger.info("Chat window didn't open! Please check.");
                return false;
            }
        }
        return retVal;
    }

    public void clickMakeAnOffer()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"MakeAnOffer")==true)
        {
            Mapper.find(domFile,"MakeAnOffer").click();
        }else
        {
            logger.info("Make an offer button is either not present or not visible. Please check!");
        }
    }

    public void enterOfferPrice(String offerPrice)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"MakeAnOfferPrice")==true)
        {
            Mapper.find(domFile,"MakeAnOfferPrice").sendKeys(offerPrice);
        }
    }

    public void enterMail(String mail)
    {
        Mapper.find(domFile,"MakeAnOfferEmail").sendKeys(mail);
    }

    public void enterPhNum(String num)
    {
        Mapper.find(domFile,"MakeAnOfferMob").clear();
        Mapper.find(domFile,"MakeAnOfferMob").sendKeys(num);
    }

    public boolean validateMakeAnOfferSuccess()
    {
        boolean retVal = false;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Mapper.waitForElementToBeVisible(domFile,"MakeAnOfferConfirmMsg")==true)
        {
            String confText = Mapper.find(domFile,"MakeAnOfferConfirmMsg").getText();
            if (confText.contains("Your offer has been sent"))
            {
                retVal = true;
            }
            else
            {
                return false;
            }
        }
        return retVal;
    }

    public void clickSendMyOfferButton()
    {
        Mapper.find(domFile,"SendOfferButton").click();
    }

    public void clickChatButton()
    {
        if (Mapper.waitForElementToBeClickable(domFile,"chatButton")==true)
        {
            Mapper.find(domFile, "chatButton").click();
        }
        else if (Mapper.waitForElementToBeVisible(domFile,"chatMsgBoxQuestion")==false)
        {
            Mapper.find(domFile, "chatButton").click();
        }

        else
        {
            logger.info("There is either no chat Button available onscreen or its invisible. Please check!");
        }
    }

    public boolean verifyChatNowSuccess(){
        boolean retVal = false;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Mapper.waitForElementToBeVisible(domFile, "chatMsgBoxConfirmation")==true)
        {
            retVal = true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public void sendQues(String ques)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"chatMsgBoxQuestion")==true)
        {
            Mapper.find(domFile,"chatMsgBoxQuestion").sendKeys(ques);
        }
        else
        {
            logger.info("Question box didn't get displayed. Please check!");
        }
    }

    public void sendEmail(String email)
    {
        Mapper.find(domFile,"chatMsgBoxEmail").sendKeys(email);
    }

    public void sendPhNum(String phnNum)
    {
        Mapper.find(domFile,"chatMsgBoxPhNum").sendKeys(phnNum);
    }

    public void clickSubmit()
    {
        Mapper.find(domFile,"chatMsgBoxSubmitBtn").click();
    }


}
