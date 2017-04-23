package com.quikr.website.quikrX.quikrXOrders;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by francis.s@quikr on 9/10/15.
 */
public class QuikrXMyOrdersPage extends PageBase {

    public QuikrXMyOrdersPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("QUIKRX_MYORDER_DOM_FILE");


    public boolean validateLandingPage() {
        return Mapper.find(domFile, "myquikrIcon").isDisplayed();
    }

    public boolean validatetabsPresent() {
        try {
            Mapper.find(domFile, "activeOrdertab").isDisplayed();
            Mapper.find(domFile, "deliveredOrderTab").isDisplayed();
            Mapper.find(domFile, "cancelledOrderTab").isDisplayed();
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    public boolean validateNoCancelledIteminActive(){
        Mapper.find(domFile, "activeOrdertab").click();
        if(isElementPresent("Your order has been cancelled!"))
            return false;
        else
            return true;
    }

    public boolean validateNoCancelledIteminDelivered(){
        Mapper.find(domFile, "deliveredOrderTab").click();
        if(isElementPresent("Your order has been cancelled!"))
            return false;
        else
            return true;
    }

    public boolean validateNoActiveIteminDelivered(){
        Mapper.find(domFile, "activeOrdertab").click();
        if(isElementPresent("Your order has been placed!"))
            return false;
        else
            return true;
    }

    public boolean validateNoActiveIteminCancelled(){
        Mapper.find(domFile, "cancelledOrderTab").click();
        if(isElementPresent("Your order has been placed!"))
            return false;
        else
            return true;
    }

    public boolean validateOrderId(String tab){

        Mapper.find(domFile, tab).click();
        if(Mapper.find(domFile,"orderNumber").getText()==null)
            return true;
        else
            return false;
    }

    public boolean validateUiSellerName(){

         if(isElementPresent("sellerName")) {
             if (Mapper.find(domFile, "sellerName").getText() == null)
                 return false;
             else
                 return true;
         }
        return true;
    }

    public boolean validateUiSellerName(String tab,String sellerName,String orderId){

        Mapper.find(domFile, tab).click();
        if(Mapper.find(domFile,"sellerName").getText().trim().equalsIgnoreCase(sellerName))
            return true;
        else
            return false;
    }

    public boolean validateItemData(String tab){

        Mapper.waitForElementToBeVisible(domFile, "itemDetails");
        if(!tab.equals(" "))
             Mapper.find(domFile, tab).click();
        boolean state=false;
        List<WebElement> list  = Mapper.finds(domFile, "itemDetails");
        for (WebElement details:list){
            if(details.getText()==null)
                state=false;
            else
                state=true;
        }
        return state;
    }

    public void cancelOrder(String orderId){
        Mapper.findAndReplace(domFile, "cancelByOrderId", new String[]{orderId}).click();
        cancel(orderId);
    }

    public String getStatus(String orderId){

       return  Mapper.findAndReplace(domFile, "statusByOrderId", new String[]{orderId}).getText();

    }

    public void cancel(String orderId){
        executeScript("arguments[0].click();",Mapper.findAndReplace(domFile, "cancelPopUp", new String[]{orderId}));
    }
    public boolean isOrderPresent(String orderId){

       return Mapper.findAndReplace(domFile, "itemByOrderId", new String[]{orderId}).isDisplayed();

    }

    public boolean validateStatusByOrderId(String orderId,String status){

        String state= Mapper.findAndReplace(domFile, "statusByOrderId", new String[]{orderId}).getText();
        if (state.equalsIgnoreCase(status) )
            return true;
        else
            return false;
    }

    public boolean ordersPresent(){

        try {
            Mapper.find(domFile, "emptyOrderHeader").isDisplayed();
            return false;
        }catch (Exception e){
            return true;
        }
    }

    public void goTo(String tab){
        Mapper.find(domFile, tab).click();
    }

    public boolean checkIfExtendedWArranty(String orderId){

        if(Mapper.findAndReplace(domFile,"extendedWarranty", new String[]{orderId}).getText().equalsIgnoreCase("Onsite Assure Warranty"))
         return true;
        else
            return false;
    }

   public void enterDetailsGuestTracking(String orderId, String suborderId){

       if(suborderId.isEmpty()||suborderId.equalsIgnoreCase("")){
           Mapper.find(domFile, "orderId").sendKeys(orderId);
       }else
       {
           Mapper.find(domFile, "subOrderId").sendKeys(suborderId);
       }
       Mapper.find(domFile, "viewOrderButton").click();

   }


  public String getSubOrderForOrderId(String orderId){
     return Mapper.findAndReplace(domFile,"subOrderIdByORderId",new String[]{orderId}).getText();
  }

  public void clickTrackingLink(String subOrder){
     Mapper.findAndReplace(domFile,"trackLink",new String[]{subOrder}).click();
  }

  public String getConfStatus(String subOrder){
      return Mapper.findAndReplace(domFile,"placeOrderStatus",new String[]{subOrder}).getText();
    }

  public String getDispatchStatus(String subOrder){
      return  Mapper.findAndReplace(domFile,"dispatchStatus",new String[]{subOrder}).getText();
    }

  public String getDispatchedDate(String subOrder){
      return  Mapper.findAndReplace(domFile,"dispatchedDate",new String[]{subOrder}).getText().split("On")[1].trim();
  }
}