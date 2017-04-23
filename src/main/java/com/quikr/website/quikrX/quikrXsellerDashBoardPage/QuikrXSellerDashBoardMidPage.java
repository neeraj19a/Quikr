package com.quikr.website.quikrX.quikrXsellerDashBoardPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 27/10/15.
 */
public class QuikrXSellerDashBoardMidPage  extends PageBase {


    private static final String domFile = getProperties().get("SELLER_DASHBOARD_DOM_FILE");

    public QuikrXSellerDashBoardMidPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }



    public void clickUpdateDispatch(String mid){
        Mapper.findAndReplace(domFile, "updateDispatch",new String[]{mid}).click();
    }

    public void updateStatusMid(){
        Mapper.find(domFile,"updateStatusToDispatch").click();
    }

    public void clickMidHistoryEX(){
        Mapper.find(domFile,"midHistoryEx").click();
    }

    public void clickMidHistoyCe(){
        Mapper.find(domFile,"midHistoryCE").click();
    }

    public void midSelectAll(){
        Mapper.find(domFile,"generateMidSelectAll").click();
    }

    public void generateMidclick(String subOrderId){
        Mapper.findAndReplace(domFile, "generateMidBySubOrder", new String[]{subOrderId}).click();
    }

    public void generateSingleMid(){
        Mapper.find(domFile, "generateSingleMid").click();
    }

    public void clickSubmit(){
        Mapper.find(domFile,"submitDelivery").click();
    }

    public void uploadFile(File file){
        Mapper.find(domFile,"uploadPdf").sendKeys(file.getAbsolutePath());
    }

    public void eccape(){
        Mapper.find(domFile,"uploadPdf").sendKeys(Keys.ESCAPE);
    }

}
