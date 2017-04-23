package com.quikr.website.quikrX.quikrXQomaPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 23/12/15.
 */
public class QuikrXQomaReplacementPage extends PageBase {

    private static final String domFile = getProperties().get("QUIKRX_QOMA_DOM_FILE");

    public QuikrXQomaReplacementPage( RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public void clickUpdateStatusBySubOrderId(String suborderId){
        Mapper.findAndReplace(domFile,"replacementUpdateSatusBySub",new String[]{suborderId}).click();
    }

    public void searchByOrderId(String orderId){

        Mapper.find(domFile,"orderIdInputField").clear();
        Mapper.find(domFile,"orderIdInputField").sendKeys(orderId);
        Mapper.find(domFile,"replaceSearchButton").click();

    }

    public void setStatus(String status){
        Select select = new Select(Mapper.find(domFile,"statusDropDown"));
        select.selectByVisibleText(status);
    }

    public void clickUpdate(){
        Mapper.find(domFile, "updateStatusButton").click();
    }

    public void clickPlaceReplace(String suborderId){
        Mapper.findAndReplace(domFile, "placeReplaceButton",new String[]{suborderId}).click();
    }

    public void selectOwner(String owner){
        Select select = new Select(Mapper.find(domFile,"replacementOwner"));
        select.selectByVisibleText(owner);
    }

    public void selectSeller(String seller){
        Select select = new Select(Mapper.find(domFile,"replacementOwner"));
        if(seller.equalsIgnoreCase("")||seller.isEmpty()){
            select.selectByIndex(1);
        }else
         select.selectByVisibleText(seller);
    }

    public void clickUpdateStatusByOrderId(String suborderId){
        Mapper.findAndReplace(domFile,"replacementUpdateSatusBySub",new String[]{suborderId}).click();
    }

    public void setStatus(String suborderId, String status){

        clickUpdateStatusByOrderId(suborderId);
        Select select = new Select(Mapper.find(domFile,"statusDropDown"));
        select.selectByVisibleText(status);
        Mapper.find(domFile, "updateStatusButton").click();
    }

    public String getStatus(String suborderId){
        return Mapper.findAndReplace(domFile, "replstatusBySubOrderId", new String[]{suborderId}).getText().trim();
    }

    public void submitNewRepOrder(){
         Mapper.find(domFile,"subNewSellerRep").click();
    }

    public String getReplacedSubOrder(String suborderId){
      return  Mapper.findAndReplace(domFile,"newSubOrder", new String[]{suborderId}).getText().trim();
    }

}
