package com.quikr.website.quikrX.quikrXsellerDashBoard;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXCartPage.QuikrxNewCartPage;
import com.quikr.website.quikrX.quikrXCheckoutPage.QuikrXCheckoutPage;
import com.quikr.website.quikrX.quikrXNewDetailsPage.QuikrXNewDetailsPage;
import com.quikr.website.quikrX.quikrXsellerDashBoardPage.QuikrXCatalogManagerDashBoardPage;
import com.quikr.website.quikrX.quikrXsellerDashBoardPage.QuikrXSellerDashBoardPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Francis (francis.s@quikr.com) on 25/9/15.
 */
public class QuikrXSellerDashBoardTests extends QuikrXTestBase{

    String city = testData.get("newUiCity");

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        QuikrXSellerDashBoardPage sellerHome = new QuikrXSellerDashBoardPage(driver);
        QuikrXCatalogManagerDashBoardPage catalogManager = new QuikrXCatalogManagerDashBoardPage(driver);

        navigatethirdparty(driver, dashBoardEnv);
        sellerHome.login("catalogmanager_u1@quikr.com", "quikr@123");
        catalogManager.clickConfig();
    }


   @Test(dataProviderClass = Data.class, dataProvider = "incentivization")
   public void uiElementsTest(String productType,String category,String mode,String value){

       QuikrXCatalogManagerDashBoardPage catalogManager = new QuikrXCatalogManagerDashBoardPage(driver);

       Map<String,String> map = new HashMap<String, String>();
       catalogManager.updateProductType(productType);
       catalogManager.updateCategoryLevel(category);
       catalogManager.updateMode(mode);
       catalogManager.updateValue(value);
       catalogManager.SubmitFile();
       catalogManager.successDispalyed();
       map = catalogManager.getValue(productType);
       Assert.assertEquals(map.get("mode"),mode,"mode mismatch");
       Assert.assertEquals(map.get("value"),value,"value mismatch");
   }


    @Test(dataProviderClass = Data.class, dataProvider = "unboxed")
    public void UnboxedIncentivization(String productType,String category,String mode,String value,String itemId) {

        QuikrXCatalogManagerDashBoardPage catalogManager = new QuikrXCatalogManagerDashBoardPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage checkoutPage = new QuikrXCheckoutPage(driver);
        QuikrXApiBase apiBase = new QuikrXApiBase();

        catalogManager.updateProductType(productType);
        catalogManager.updateCategoryLevel(category);
        catalogManager.updateMode(mode);
        catalogManager.updateValue(value);
        catalogManager.SubmitFile();
        catalogManager.successDispalyed();
        apiBase.updateProductInventory("20", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
        openProductNewUi(itemId,city);
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.addToCart("1000");
        waitForPageToLoad("Cart");
        double cartFinalprice = Double.parseDouble(cartPage.getTotalPrice());
        cartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        if(category.equalsIgnoreCase("None")) {
            Assert.assertFalse(checkoutPage.isOnlineDiscountDisplayed());
        }else {
            Assert.assertTrue(checkoutPage.isOnlineDiscountDisplayed());
            if (!mode.equalsIgnoreCase("fixed")) {
                value = calulateDiscountPercentage(Double.parseDouble(value), cartFinalprice).trim();
            }
            Assert.assertEquals(value, checkoutPage.getOnlineDiscount());
        }

    }

    @Test(dataProviderClass = Data.class, dataProvider = "cod")
    public void onlinePaymentCodTest(String productType,String category,String mode,String value,String itemId){

        QuikrXCatalogManagerDashBoardPage catalogManager = new QuikrXCatalogManagerDashBoardPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage checkoutPage = new QuikrXCheckoutPage(driver);
        QuikrXApiBase apiBase = new QuikrXApiBase();

        catalogManager.updateProductType(productType);
        catalogManager.updateCategoryLevel(category);
        catalogManager.updateMode(mode);
        catalogManager.updateValue(value);
        catalogManager.SubmitFile();
        catalogManager.successDispalyed();
        apiBase.updateProductInventory("20", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
        openProductNewUi(itemId,city);
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.addToCart("1000");
        waitForPageToLoad("Cart");
        cartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        Assert.assertTrue(checkoutPage.isOnlineDiscountDisplayed());
        checkoutPage.fillCheckOutDetails("test@test.com","Test","560016","Test","9632587415");
        checkoutPage.selectNetBanking();
        Assert.assertTrue(checkoutPage.isOnlineDiscountEnabled());
        checkoutPage.seclectCard();
        Assert.assertTrue(checkoutPage.isOnlineDiscountEnabled());
        checkoutPage.selectCod();
        Assert.assertFalse(checkoutPage.isOnlineDiscountDisplayed());
    }


}