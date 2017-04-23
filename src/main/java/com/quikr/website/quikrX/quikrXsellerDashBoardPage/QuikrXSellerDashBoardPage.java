package com.quikr.website.quikrX.quikrXsellerDashBoardPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import static com.quikr.utils.PropertyReader.getProperties;
/**
 * Created by francis (francis.s@quikr.com) on 30/9/15.
 */
public class QuikrXSellerDashBoardPage extends PageBase {

   public boolean loggedInStatus = false;

    private static final String domFile = getProperties().get("SELLER_DASHBOARD_DOM_FILE");

    public QuikrXSellerDashBoardPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    /**
     * login to quikrXsellerDashBoard
     */
    public void login(String userName,String password) {

        Mapper.find(domFile, "userName").sendKeys(userName);

        Mapper.find(domFile, "password").sendKeys(password);
        Mapper.find(domFile, "submit").click();
        loggedInStatus = true;
    }

    /**
     * change price of a given item Id
     * @param category
     * @param itemId
     * @param price
     */
    public void changePrice(String category, String itemId, String price) {

        selectCategory(category);

        if (Mapper.findAndReplace(domFile, "status", new String[]{itemId}).isEnabled()) {

            Mapper.findAndReplace(domFile, "price", new String[]{itemId}).clear();
            Mapper.findAndReplace(domFile, "price", new String[]{itemId}).sendKeys(price);
            Mapper.findAndReplace(domFile, "updateCatelog", new String[]{itemId}).click();


        } else {
            Assert.fail("item is not published");
        }
    }

    /**
     * set a given iten id to below safety stock
     * @param category
     * @param itemId
     */
    public void setItemOutOfStock(String category, String itemId) {

        selectCategory(category);

        if (Mapper.findAndReplace(domFile, "status", new String[]{itemId}).isEnabled()) {

            int safetyStock = Integer.parseInt(Mapper.findAndReplace(domFile, "safetyStock", new String[]{itemId}).getText());
            Mapper.findAndReplace(domFile, "inventory", new String[]{itemId}).clear();
            if (safetyStock==0) safetyStock=1;
            Mapper.findAndReplace(domFile, "inventory", new String[]{itemId}).sendKeys("" + (safetyStock - 1));
            Mapper.findAndReplace(domFile, "updateCatelog", new String[]{itemId}).click();

        } else {
            Assert.fail("item is not published");
        }
    }

    /**
     * change a item status to seller enabled/disabled
     * @param category
     * @param itemId
     * @param status
     */
    public void changeProductStatus(String category, String itemId, String status) {

        selectCategory(category);
        if (Mapper.findAndReplace(domFile, "status", new String[]{itemId}).isEnabled()) {

            Mapper.findAndReplace(domFile, "status", new String[]{itemId}).click();
            Mapper.findAndReplace(domFile, "updateCatelog", new String[]{itemId}).click();
        }

    }

    /**
     * check if a given item is published
     * @param category
     * @param itemId
     * @return
     */
    public boolean isProductEnabled(String category, String itemId) {

        selectCategory(category);
        int safetyStock= Integer.parseInt(Mapper.findAndReplace(domFile, "inventory", new String[]{itemId}).getAttribute("value"));
        String stateValue = Mapper.findAndReplace(domFile, "status", new String[]{itemId}).getAttribute("currentstatus");
        return stateValue=="0" && safetyStock!=0;

    }


    public void enableItem(String itemId){
        Mapper.findAndReplace(domFile, "status", new String[]{itemId}).click();
        Mapper.findAndReplace(domFile, "updateCatelog", new String[]{itemId}).click();

    }


    private void selectCategory(String category) {

        if (category.equalsIgnoreCase("exchange")) {

            Mapper.find(domFile, "exchangeProduct").click();
            Mapper.find(domFile,"editexchangeProduct").click();

        } else {
            Mapper.find(domFile, "certifiedProduct").click();
            Mapper.find(domFile,"editcertifiedProduct").click();
        }
    }

    /**
     * get price of gievn item
     * @param category
     * @param itemId
     * @return
     */
    public String getItemPrice(String category, String itemId) {

        selectCategory(category);
        if (Mapper.findAndReplace(domFile, "status", new String[]{itemId}).isEnabled()) {
            return Mapper.findAndReplace(domFile, "price", new String[]{itemId}).getAttribute("value");
        } else {
            logger.info("item is not enabled");
            return null;
        }
    }

    /**
     * Add a new item
     * @param category
     * @param itemId
     * @param price
     * @param inventory
     */
    public void newItemEntry(String category, String itemId, String price,int inventory){

        selectCategory(category);
        if (Mapper.findAndReplace(domFile, "status", new String[]{itemId}).isEnabled()) {

            int safetyStock = Integer.parseInt(Mapper.findAndReplace(domFile, "safetyStock", new String[]{itemId}).getText());
            if(inventory>safetyStock) {
                Mapper.findAndReplace(domFile, "inventory", new String[]{itemId}).clear();
                Mapper.findAndReplace(domFile, "inventory", new String[]{itemId}).sendKeys(""+inventory);
            }else{
                Assert.fail("item will not be published as inventry is less than safety stock");
            }
            Mapper.findAndReplace(domFile, "price", new String[]{itemId}).clear();
            Mapper.findAndReplace(domFile, "price", new String[]{itemId}).sendKeys(price);
           if (Mapper.findAndReplace(domFile, "status", new String[]{itemId}).getAttribute("currentstatus").equalsIgnoreCase("0"))
               Mapper.findAndReplace(domFile, "status", new String[]{itemId}).click();
            Mapper.findAndReplace(domFile, "updateCatelog", new String[]{itemId}).click();

        } else {
            Assert.fail("item is already published");
        }

    }


    public void openAllOrders(){
        Mapper.find(domFile, "allOrders").click();
    }

    public void updateStatusPackSlip(String subOrderId){

        if(Mapper.findAndReplace(domFile, "statusBySubOrder", new String[]{subOrderId}).getText().equalsIgnoreCase("Call confirmed")){
            navigateTo().refresh();
            waitForPageToLoad("");
            Mapper.findAndReplace(domFile, "updateStatusByOrderId", new String[] {subOrderId}).click();
            Mapper.find(domFile,"imeiNo").sendKeys("123456789009876");
            Mapper.find(domFile,"submitStatus").click();

        }
    }

    public void clickGenerateMidEx(){
        Mapper.waitForElementToBeClickable(domFile,"generateMidEx");
        Mapper.find(domFile,"generateMidEx").click();
    }

    public boolean isGenerateMidExVisible(){
        try {
          return  Mapper.find(domFile,"generateMidEx").isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isGenerateMidCeVisible(){
        try {
            return  Mapper.find(domFile,"generateMidCe").isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void clickGenerateMidCe(){
        Mapper.waitForElementToBeClickable(domFile,"generateMidCe");
        Mapper.find(domFile,"generateMidCe").click();
    }

    public void clickExchange(){
        Mapper.find(domFile, "exchangeProduct").click();
    }

    public void clickCertified(){
        Mapper.find(domFile, "certifiedProduct").click();
    }

    public void clickPending(){
        Mapper.find(domFile,"pendingOrder").click();
    }

    public String getMidBySubOrder(String subOrderId){
       return  Mapper.findAndReplace(domFile, "midBySubOrder", new String[]{subOrderId}).getText();
    }

    public boolean isPendingVisible(){
        return Mapper.find(domFile,"pendingOrder").isDisplayed();
    }


}