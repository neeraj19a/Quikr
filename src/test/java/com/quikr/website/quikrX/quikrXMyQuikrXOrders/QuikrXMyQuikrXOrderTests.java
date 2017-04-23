package com.quikr.website.quikrX.quikrXMyQuikrXOrders;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXApiTestBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.api.quikrX.dataProviders.QuikrXDataProvider;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXOrders.QuikrXMyOrdersPage;
import com.quikr.website.roundCubeEmail.RoundCubeEmailPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by francis.s@quikr on 15/10/15.
 */
public class QuikrXMyQuikrXOrderTests extends QuikrXTestBase {


    @Test(groups = "QuikrXHighPrio")
    public void validateQuikrXUiElements(){
        QuikrXMyOrdersPage myOrdersPage = new QuikrXMyOrdersPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage header = new HeaderPage(driver);

        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelect"));
        header.letsLogin("randomPageLoginResponsive",testData.get("CitySelect"),testData.get("loginEmail"),testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        goToHomePage(testData.get("CitySelect"));
        header.clickMyQuikrX();
        waitForPageToLoad("myorders");

        if(!myOrdersPage.ordersPresent()){
            refreshPage();
        }

        Assert.assertTrue(myOrdersPage.validateLandingPage());
        Assert.assertTrue(myOrdersPage.validateItemData("deliveredOrderTab"), "Sub Order details missing in delivered tab");
        Assert.assertTrue(myOrdersPage.validateNoActiveIteminDelivered(), "Active order found in Delivered tab");
        Assert.assertTrue(myOrdersPage.validateNoCancelledIteminActive(), "Cancelled order found in active tab");
        Assert.assertTrue(myOrdersPage.validateItemData("cancelledOrderTab"), "Sub Order details missing in cancelled tab");
        Assert.assertTrue(myOrdersPage.validateNoActiveIteminCancelled(), "Active order found in cancelled tab");
        Assert.assertTrue(myOrdersPage.validateItemData("activeOrdertab"), "Sub Order details missing in active tab");
        Assert.assertTrue(myOrdersPage.validateNoCancelledIteminActive(), "Cancelled order found in actibe tab");
        Assert.assertTrue(myOrdersPage.validateUiSellerName(),"seller details missing in delivered tab");
    }

    @Test
    public void validateCancelOrder() throws Exception{

        QuikrXMyOrdersPage myOrdersPage = new QuikrXMyOrdersPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage header = new HeaderPage(driver);
        QuikrXApiBase apiBase = new QuikrXApiBase();

        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelectUrl"));
        header.letsLogin("randomPageLoginResponsive",testData.get("CitySelect"),testData.get("loginEmail"),testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        goToHomePage(testData.get("CitySelect"));
        String orderId = placeOrder("certified");
        goToHomePage(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelectUrl"));
        apiBase.updateQuoteItemStatus(orderId, QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        homePage.clickNewQuikrX();
        waitForPageToLoad("QuikrX");
        header.clickMyQuikrX();
        waitForPageToLoad("myorders");
        Assert.assertEquals(myOrdersPage.getStatus(orderId), testData.get("confirmedState"), "Suborder is not not confirmed");
        myOrdersPage.cancelOrder(orderId);
        Thread.sleep(5000l);
        header.switchTo().parentFrame();
        waitForPageToLoad("myorders");
        myOrdersPage.goTo("cancelledOrderTab");
        Assert.assertTrue(myOrdersPage.validateStatusByOrderId(orderId,testData.get("cancelledState")), "cancelled order not present in cancelled tab");

    }


     @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "endToendFlows")
    public void validateguestTracking(String sellerName, String itemId, String email,String category,String exchangeProd) {

         QuikrXMyOrdersPage myOrdersPage = new QuikrXMyOrdersPage(driver);
         QuikrXApiBase quikrXApiBase = new QuikrXApiBase();
         QuikrXApiTestBase testBase = new QuikrXApiTestBase();
         RoundCubeEmailPage emailPage = new RoundCubeEmailPage(driver);

          Map<String,String> map = testBase.placeOrder(sellerName,itemId,email,category, QuikrXEnumData.paymentMehtod.CREDIT_CARD,exchangeProd);
          map.putAll(testBase.getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
          quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
          checkNewEmail("monetizationweb1@mail.quikrtest.com",2,"Your Order (Order ID ="+map.get("orderId")+" ) has been placed on Quikr");
          emailPage.openFirstEmail();
          navigatethirdparty(driver,(emailPage.quikrXTrsckOrderLink()));
          waitForPageToLoad("guest");
          myOrdersPage.enterDetailsGuestTracking(map.get("orderId"), "");
          Assert.assertTrue(myOrdersPage.validateItemData(" "), "item fields missing");
          Assert.assertTrue(myOrdersPage.validateUiSellerName(), "seller field is empty");

     }

    @Test
    public void validateDispatchedDelivered(){

        QuikrXMyOrdersPage myOrdersPage = new QuikrXMyOrdersPage(driver);
        QuikrXApiBase apiBase = new QuikrXApiBase();

        HomePage homePage = new HomePage(driver);
        HeaderPage header = new HeaderPage(driver);

        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelect"));
        header.letsLogin("randomPageLoginResponsive",testData.get("CitySelect"),testData.get("loginEmail"),testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        goToHomePage(testData.get("CitySelect"));
        String orderId =  placeOrder("certified");
        goToHomePage(testData.get("CitySelect"));
        updateQuoteItemStatus(orderId, "call_confirmed");
        header.clickMyQuikrX();
        waitForPageToLoad("myorders");
        Assert.assertTrue(myOrdersPage.validateStatusByOrderId(orderId,testData.get("confirmedState")), "Confirmed order not displayed");
        String subOrder = myOrdersPage.getSubOrderForOrderId(orderId);
        apiBase.updateSuborderStatus(subOrder,"DISPATCHED");
        refreshPage();
        Assert.assertTrue(myOrdersPage.validateStatusByOrderId(orderId,testData.get("dispatchedState")), "Status not set to dispatch");
        updateQuoteItemStatus(orderId, "delivered");
        myOrdersPage.goTo("deliveredOrderTab");
        Assert.assertTrue(myOrdersPage.validateStatusByOrderId(orderId,testData.get("deliveredState")), "Delivered order not present in delivered tab");
    }

    @Test
    public void validateMyOrderTracking(){

        QuikrXMyOrdersPage myOrdersPage = new QuikrXMyOrdersPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage header = new HeaderPage(driver);
        QuikrXApiBase apiBase = new QuikrXApiBase();
        Date date = new Date();

        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelect"));
        header.letsLogin("randomPageLoginResponsive",testData.get("CitySelect"),testData.get("loginEmail"),testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        goToHomePage(testData.get("CitySelect"));
        String orderId =  placeOrder("certified");
        goToHomePage(testData.get("CitySelect"));
        apiBase.updateQuoteItemStatus(orderId, QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        header.clickMyQuikrX();
        String subOrder = myOrdersPage.getSubOrderForOrderId(orderId);
        myOrdersPage.clickTrackingLink(subOrder);
        Assert.assertEquals(myOrdersPage.getConfStatus(subOrder), testData.get("confirmedState"), "Confirmed status mismatch");
        apiBase.updateSuborderStatus(subOrder,"DISPATCHED");
        refreshPage();
        waitForPageToLoad("myorders");
        myOrdersPage.clickTrackingLink(subOrder);
        Assert.assertEquals(myOrdersPage.getDispatchStatus(subOrder), testData.get("dispatchedState"), "Dispatched  status mismatch");
        SimpleDateFormat todayDate = new SimpleDateFormat("MMM dd");
        Assert.assertEquals(myOrdersPage.getDispatchedDate(subOrder),todayDate.format(date),"mismatch in date");
    }


}
