package com.quikr.website.escrow.escrowMyDoorStep;

import com.quikr.website.PageBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by francis.s@quikr on 15/6/16.
 */
public class EscrowMyDoorStepPage extends PageBase {

    private static final String domFile = getProperties().get("Escrow_MY_DOORSTEP_DOM_FILE");
    private final static Log LOGGER = LogFactory.getLog(EscrowMyDoorStepPage.class.getName());


    public EscrowMyDoorStepPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public void clickSellingTab(){
       Mapper.find(domFile,"sellingTab").click();
       waitForPageToLoad("getSellersOfferForm");
    }

    public void clickBuyingTab(){
        Mapper.find(domFile,"buyingTab").click();
        waitForPageToLoad("getBuyersOfferForm");
    }

    public int getTotalAdCountText(){
       return Integer.parseInt(Mapper.find(domFile,"totalAdsCount").getText().split("Total of ")[1].split("ads")[0].trim());
    }

    public void clickAdById(String adId){
        Mapper.findAndReplace(domFile,"openAdById",new String[]{adId}).click();
        waitForPageToLoad(adId);
    }

    public int offerReceivedCount(String adId){
        return Integer.parseInt(Mapper.findAndReplace(domFile,"offersReceivedCount",new String[]{adId}).getText());
    }

    public boolean noOfferTextDisplayed(String adId){
        return Mapper.findAndReplace(domFile,"offersReceivedCount",new String[]{adId}).isDisplayed();
    }

    public boolean editNowlinkDisplayed(String adId){
        return Mapper.findAndReplace(domFile,"editNowById",new String[]{adId}).isDisplayed();
    }

    public void clickEditNowLink(String adId){
        Mapper.findAndReplace(domFile,"editNowLinkById",new String[]{adId}).click();
    }

    public int offersDivPresent(){
      return  Mapper.finds(domFile,"allOffersDiv").size();
    }

    public String getAdIdFromViewOffer(){
        try {
            List<WebElement> list = Mapper.finds(domFile, "adIdByViewOffer");
            if (list.size() > 1) {
                for (WebElement ele : list) {
                    return ele.getText().replace("Ad ID: ", "").trim();
                }
            } else
                return list.get(0).getText().replace("Ad ID: ", "").trim();
        }catch (Exception e){
            return null;
        }
        return null;
    }


    public String getAdIdFromEditLink(){
        try {
            List<WebElement> list = Mapper.finds(domFile, "adIdByEditLink");
            if (list.size() > 1) {
                for (WebElement ele : list) {
                    return ele.getText().replace("Ad ID: ", "").trim();
                }
            } else
                return list.get(0).getText().replace("Ad ID: ", "").trim();
        }catch (Exception e){
            return null;
        }
        return null;
    }

}
