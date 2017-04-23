package com.quikr.website.quikrX.quikrXCartPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 7/1/16.
 */
public class QuikrxNewCartPage extends PageBase {

    private static final String domFile = getProperties().get("QUIKRXCARTPAGE_DOM_FILE");

    public QuikrxNewCartPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public String getTitle(String itemId){
        return Mapper.findAndReplace(domFile,"itemNameById",new String[]{itemId}).getText();
    }

    public String getCategory(String itemId){
        return Mapper.findAndReplace(domFile,"itemCategoryById",new String[]{itemId}).getText();
    }

    public WebElement body(){
        return Mapper.find(domFile,"body");
    }


    public String getSellerName(String itemId){
        return Mapper.findAndReplace(domFile,"sellerById",new String[]{itemId}).getText();
    }


    public String getDeliveryIn(String itemId){
        return Mapper.findAndReplace(domFile,"deliveryDays",new String[]{itemId}).getText();
    }


    public String getActualPrice(String itemId){
        return Mapper.findAndReplace(domFile,"itemActualPrice",new String[]{itemId}).getText().replace("₹","").replace(",","").trim();
    }


    public String getOldPrice(String itemId){
        try{
            return Mapper.findAndReplace(domFile,"itemOldPrice",new String[]{itemId}).getText().replace("₹","").replace(",","").trim();
        }
    catch (NullPointerException e) {
        return Mapper.findAndReplace(domFile, "itemOldPrice", new String[]{itemId}).getText().replace("₹", "").trim();

     }
    }


    public String getDiscountPercentage(String itemId){
        return Mapper.findAndReplace(domFile,"offerlabel",new String[]{itemId}).getText().replace("% OFF","").trim();
    }


    public String getExchangePrice(String itemId){
        return Mapper.findAndReplace(domFile,"exchangePrice",new String[]{itemId}).getText().replace("₹","").replace(",","").replace("-","").replace("(Exchange Value)","").trim();
    }

    public String getTitleOrderSummary(String itemName){
        return Mapper.findAndReplace(domFile,"itemTitleOrderSum",new String[]{itemName}).getText();
    }

    public String getOrdderSummarPrice(String itemName){
        return Mapper.findAndReplace(domFile,"itemPriceOrderSum",new String[]{itemName}).getText().replace("₹","").replace(",","").trim();
    }

    public String getTotalPrice(){
        return Mapper.find(domFile,"totalPrice").getText().replace("₹","").replace(",","").trim();
    }

    public void clickRemoveitem(String itemId){
        Mapper.findAndReplace(domFile,"removeItem",new String[]{itemId}).click();
    }

    public void clickPlaceOrder(){
        Mapper.find(domFile,"newplaceOrderButton").click();
    }

    public boolean isExtendedWarrantyDisp(){
       return Mapper.find(domFile,"extendedWarranty").isDisplayed();
    }

    public String getExtendedWarrantyPrice(String itemId) {
        return Mapper.findAndReplace(domFile, "extendedWarrantyPrice", new String[]{itemId}).getText();
    }

    public void clickContinueShopping(){
        Mapper.find(domFile,"continueShopping").click();
    }

    public String getNotificationText(String itemId){
        return Mapper.findAndReplace(domFile,"priceChangeNotification",new String[]{itemId}).getText();
    }

    public boolean isCartEmpty(){
        try{
        Mapper.waitForElementToBeVisible(domFile,"emptyCartText");
        return Mapper.find(domFile,"emptyCartText").isDisplayed();
        }catch (Exception e ){
            return false;
        }

    }

    public List<WebElement> itemsInCart(){
       return Mapper.finds(domFile,"itemsIncart");

    }

}
