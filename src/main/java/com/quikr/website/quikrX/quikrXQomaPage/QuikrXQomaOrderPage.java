package com.quikr.website.quikrX.quikrXQomaPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 2/11/15.
 */
public class QuikrXQomaOrderPage extends PageBase {

    private static final String domFile = getProperties().get("QUIKRX_QOMA_DOM_FILE");

    public QuikrXQomaOrderPage( RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public String getOrderedOn(){
        return Mapper.find(domFile,"ordererdOn").getText();
    }

    public String getcutomerName(){
        return Mapper.find(domFile,"customerName").getText();
    }

    public String getcutomerEmail(){
        return Mapper.find(domFile,"customerEmail").getText();
    }

    public String getcutomerMob(){
        return Mapper.find(domFile,"customerMobile").getText();
    }

    public String getTotalPrice(){
        return Mapper.find(domFile,"totalPrice").getText().split("Rs. ")[1].replace(",","").trim();
    }

    public String getPriceBySuborderId(String suborderId){
        return Mapper.findAndReplace(domFile, "priceBySuborderId", new String[]{suborderId}).getText().replaceAll(",","");
    }

    public String getExchangeDiscount(String suborderId){
        return Mapper.findAndReplace(domFile, "exchangeDiscBySubOrderId", new String[]{suborderId}).getText().replaceAll(",","");
    }

    public String getnetAmount(String suborderId){
        return Mapper.findAndReplace(domFile, "netAmountBySubOrderId", new String[]{suborderId}).getText().replaceAll(",","");
    }

    public String getStatus(String suborderId){
        return Mapper.findAndReplace(domFile, "statusBySubOrderId", new String[]{suborderId}).getText().trim();
    }

    public void clickUpdateStatusByOrderId(String suborderId){
        Mapper.findAndReplace(domFile,"updateStatusSubOrderId",new String[]{suborderId}).click();
    }

    public void setStatus(String suborderId, String status){

        clickUpdateStatusByOrderId(suborderId);
        sleep(4000);
        Select select = new Select(Mapper.find(domFile,"statusDropDown"));
        select.selectByVisibleText(status);
        if(status.equalsIgnoreCase("delivered")){
            executeScript("document.getElementsByName('paid_by_cash')[0].setAttribute('value', '2313')");
        }else if (status.equalsIgnoreCase("Call cancelled")){
            executeScript("document.getElementById('reason').value='Test orders'");
        }
        Mapper.find(domFile, "updateStatusButton").click();
    }

    public void clickReplacementTab(){
        Mapper.find(domFile,"replacementTab").click();
    }


}
