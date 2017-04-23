package com.quikr.website.quikrX.quikrXQomaPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 2/11/15.
 */
public class QuikrXQomaHomePage extends PageBase {

    private static final String domFile = getProperties().get("QUIKRX_QOMA_DOM_FILE");

    public QuikrXQomaHomePage(RemoteWebDriver driver) {
        super(domFile, driver);
    }
    private boolean isLoggedIn = false;

    //Login to qoma
    public void qomalogin(){

        if(!isLoggedIn) {
            waitForPageToLoad("accounts.quikr.com");
            Mapper.find(domFile, "userName").sendKeys("francis.s@quikr.com");
            Mapper.find(domFile, "password").sendKeys("Open123!#@!");
            Mapper.find(domFile, "submit").click();
            isLoggedIn=true;
        }

    }

    public void clickViewSubOrder(){

        Mapper.find(domFile,"viewSuborder").click();

    }

    public void searchBySubOrderId(String orderId){

        Mapper.find(domFile,"subOrderIdInputField").clear();
        Mapper.find(domFile,"subOrderIdInputField").sendKeys(orderId);
        Mapper.find(domFile,"searchButton").click();

    }

    public void searchByOrderid(String subOrderId){

        Mapper.find(domFile,"orderIdInputField").clear();
        Mapper.find(domFile,"orderIdInputField").sendKeys(subOrderId);
        Mapper.find(domFile,"searchButton").click();

    }

    public void searcByEmailId(String emailId){

        Mapper.find(domFile,"orderIdInputField").clear();
        Mapper.find(domFile,"orderIdInputField").sendKeys(emailId);
        Mapper.find(domFile,"searchButton").click();

    }

    public void searchByMobile(String mobileNumber){

        Mapper.find(domFile,"orderIdInputField").clear();
        Mapper.find(domFile,"orderIdInputField").sendKeys(mobileNumber);
        Mapper.find(domFile,"searchButton").click();

    }

    public void clickOrderId(String orderId){
        openUrl(Mapper.findAndReplace(domFile, "orderIdlink", new String[]{orderId}).getAttribute("href"));
    }

    public String getStatusByOrderId(String suborder){
        return Mapper.findAndReplace(domFile,"getStatus",new String[]{suborder}).getText().trim();
    }

    public String getpayModeByOrderId(String suborder){
        return Mapper.findAndReplace(domFile,"getPayMode",new String[]{suborder}).getText().trim();
    }

    public void selectDelivered(){

        Mapper.find(domFile,"allstatusDropDown").click();
        Mapper.find(domFile,"selectDelivered").click();
        Mapper.find(domFile,"clicksearch").click();
    }

    public String getFirstOrder(){
        return Mapper.find(domFile,"firstOrder").getText().trim();
    }

    public String getFirstSubOrder(){
        return Mapper.find(domFile,"firstSubOrder").getText().trim();
    }

    public void selectDate(){

       Mapper.find(domFile,"date").click();
       Mapper.find(domFile,"oldDate").click();

    }


    public String getSubOrderIdByOrderId(){

        try{
            if (Mapper.find(domFile,"sellernameSecRow").getText().equalsIgnoreCase(""))
                return Mapper.find(domFile,"subOrderIdFirstrow").getText();
            else
                return Mapper.find(domFile,"subOrderIdSecrow").getText();

        }catch (Exception e){
            return Mapper.find(domFile,"subOrderIdFirstrow").getText();
        }
    }


}
