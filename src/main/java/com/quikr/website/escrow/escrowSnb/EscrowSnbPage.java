package com.quikr.website.escrow.escrowSnb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.quikr.utils.InitiateDriver;
import com.quikr.website.PageBase;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by gurinder.singh@quikr.com
 */
public class EscrowSnbPage extends PageBase {
    public EscrowSnbPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("ESCROW_SNB_DOM_FILE");

    public void search(String AdId) {
        Mapper.waitForElementToBeVisible(domFile, "searchBar");
        Mapper.find(domFile, "searchBar").sendKeys(AdId);
        Mapper.find(domFile, "searchButton").isEnabled();
        Mapper.waitForElementToBeClickable(domFile, "searchButton");
        Mapper.find(domFile, "searchButton").click();
    }

    public boolean isSearchSuccess(String searchItem) {

        Mapper.waitForElementToBeVisible(domFile, "searchBreadcrumb",25);
        if (Mapper.find(domFile, "searchBreadcrumb").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean buyNowOnly() {
        Mapper.waitForElementToBeVisible(domFile, "buyNowOnly");
        if (Mapper.find(domFile, "buyNowOnly").isDisplayed())
           return true;
        else
            return false;


    }

    public void clickBuyNow() {
        try {
            Mapper.find(domFile, "buyNowOnly").click();
        }catch(Exception e){
            Mapper.find(domFile, "buyNowOnly").click();
        }

    }

    public void selectAd() {
        Mapper.scrollElementIntoView(domFile,"searchBreadcrumb");
        Mapper.waitForElementToBeVisible(domFile, "searchBreadcrumb",20);
            Mapper.find(domFile, "searchBreadcrumb").click();

    }

    public boolean adSoldTag() {
        Mapper.waitForElementToBeVisible(domFile, "adSold", 20);
        if (Mapper.find(domFile, "adSold").isDisplayed())
            return true;
        else
            return false;
    }


    public void selectMakeAnOffer(String AdId) {
        Mapper.scrollElementIntoView(domFile, "maoButton");
        Mapper.waitForElementToBeVisible(domFile, "maoButton",20);
        Mapper.find(domFile, "maoButton").click();
        /*List<WebElement> allMaobuttons = Mapper.finds(domFile, "maoButton");
        for (int i = 0; i < allMaobuttons.size(); i++)
            if (allMaobuttons.get(i).getAttribute("adid").equalsIgnoreCase(AdId)) {
                allMaobuttons.get(i).click();
                break;
            }*/
    }

    public boolean isMAOOpen() {
        Mapper.waitForElementToBeVisible(domFile, "maoPopUpHeader",20);
        if (Mapper.find(domFile, "maoPopUpHeader").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean isBuyNowPresent() {
        Mapper.waitForElementToBeVisible(domFile, "buyNowButton");
        if (Mapper.find(domFile, "buyNowButton").isDisplayed()) {
            return true;
        } else return false;
    }


    public boolean bidButtonOnSnB() {
        Mapper.waitForElementToBeVisible(domFile, "bidButtonSnB");
        if (Mapper.find(domFile, "bidButtonSnB").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public String nextBidVal(){
        String bidVal="";
        String result=Mapper.find(domFile, "bidButtonSnB").getAttribute("data-ref");
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNode = mapper.readValue(result, ObjectNode.class);
            bidVal=jsonNode.get("op").toString();
        }  catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         return  bidVal;
    }
    public boolean lastBidpricelabelSnB() {
        Mapper.waitForElementToBeVisible(domFile, "lastBidPriceSnB",25);
        if (Mapper.find(domFile, "lastBidPriceSnB").isDisplayed()) {

            return true;
        } else
            return false;
    }

    public boolean auctionLabelSnB() {
        Mapper.waitForElementToBeVisible(domFile, "auctionLabel",25);
        if (Mapper.find(domFile, "auctionLabel").isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void bidNowClick(){
        Mapper.waitForElementToBeVisible(domFile, "bidButtonSnB",20);
        Mapper.find(domFile, "bidButtonSnB").click();
    }

    public boolean bidPopUpSnB() {
        Mapper.waitForElementToBeVisible(domFile, "bidPopUp",25);
        if (Mapper.find(domFile, "bidPopUp").isDisplayed()) {
            return true;
        } else
            return false;
    }

        public boolean lessThanPreviousBidAmount(String nextBidval){
        Mapper.waitForElementToBeVisible(domFile, "bidPriceTextfield",25);
        Mapper.find(domFile,"bidPriceTextfield").click();
        Mapper.find(domFile,"bidPriceTextfield").clear();
        Mapper.find(domFile,"bidPriceTextfield").sendKeys(nextBidval);
        Mapper.find(domFile,"bidderEmailTextfield").click();
        Mapper.waitForElementToBeVisible(domFile,"lessThanEnteredBidErrorMsg",25);
            if (Mapper.find(domFile,"lessThanEnteredBidErrorMsg").isDisplayed()) {
                return true;
            } else
                return false;
        }

    public void submitBid(String nextBidval,String email,String mobile){
        Mapper.find(domFile,"bidPriceTextfield").click();
        Mapper.find(domFile,"bidPriceTextfield").clear();
        Mapper.find(domFile,"bidPriceTextfield").sendKeys(nextBidval);
        Mapper.find(domFile,"bidderEmailTextfield").click();
        Mapper.find(domFile,"bidderEmailTextfield").clear();
        Mapper.find(domFile,"bidderEmailTextfield").sendKeys(email);
        Mapper.find(domFile,"bidderMobileTextfield").click();
        Mapper.find(domFile,"bidderMobileTextfield").clear();
        Mapper.find(domFile,"bidderMobileTextfield").sendKeys(mobile);
       /* Mapper.find(domFile,"bidderCityTextfield").click();
        Mapper.find(domFile,"bidderCityTextfield").clear();
        Mapper.find(domFile,"bidderCityTextfield").sendKeys("Bangalore");
        Mapper.waitForElementToBeVisible(domFile,"bidderCitySuggestion",20);
        Mapper.find(domFile,"bidderCitySuggestion").click();*/
        Mapper.find(domFile,"placeBid").click();
        Mapper.waitForElementToBeVisible(domFile,"bidClose");
        Mapper.find(domFile,"bidClose").click();
    }
}
