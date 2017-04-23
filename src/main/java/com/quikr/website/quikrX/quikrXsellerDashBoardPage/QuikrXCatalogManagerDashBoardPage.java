package com.quikr.website.quikrX.quikrXsellerDashBoardPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 25/1/16.
 */
public class QuikrXCatalogManagerDashBoardPage extends PageBase {

    private static final String domFile = getProperties().get("SELLER_DASHBOARD_DOM_FILE");

    public QuikrXCatalogManagerDashBoardPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public void clickConfig(){
        Mapper.find(domFile,"config").click();

    }

    public void successDispalyed(){
        Mapper.waitForElementToBeVisible(domFile,"succMessage");
        Mapper.find(domFile,"closeButton").click();
    }

    public void updateProductType(String type){
        Select sel = new Select(Mapper.find(domFile,"productType"));
        sel.selectByVisibleText(type);
    }

    public void updateCategoryLevel(String type){
        Select sel = new Select(Mapper.find(domFile,"catLevel"));
        sel.selectByVisibleText(type);
    }

    public void updateMode(String type){
        Select sel = new Select(Mapper.find(domFile,"mode"));
        sel.selectByVisibleText(type);
    }

    public void updateValue(String value){
        Mapper.find(domFile,"value").clear();
        Mapper.find(domFile,"value").sendKeys(value);
    }

    public void SubmitFile(){
        Mapper.find(domFile,"update").click();
    }

    public Map<String,String> getValue(String prodType){

        Map<String,String> map = new HashMap<String, String>();
        Select sel = new Select(Mapper.find(domFile,"productType"));
        sel.selectByVisibleText(prodType);
        sel = new Select(Mapper.find(domFile,"mode"));
        map.put("mode",sel.getFirstSelectedOption().getText());
        map.put("value",Mapper.find(domFile,"value").getAttribute("value").replace(".00","").trim());
        return map;
    }


}
