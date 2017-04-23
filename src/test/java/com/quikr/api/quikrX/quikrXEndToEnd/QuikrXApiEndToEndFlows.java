package com.quikr.api.quikrX.quikrXEndToEnd;


import com.quikr.api.quikrX.QuikrXApiTestBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.api.quikrX.dataProviders.QuikrXDataProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * created by francis.s@quikr.com
 */
public class QuikrXApiEndToEndFlows extends QuikrXApiTestBase {

    private final  Log LOGGER = LogFactory.getLog(QuikrXApiEndToEndFlows.class.getName());

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "endToendPaymentMethods")
    public void placeOrderPaymentMethodsTest(String sellerName, String itemId, String email,String category,
                                                                          QuikrXEnumData.paymentMehtod payment,String exchangeProd){

        LOGGER.info("test placing order and order confirmation");

        Map<String,String> map = placeOrder(sellerName,itemId,email,category,payment,exchangeProd);
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));

        LOGGER.info("Test executed with payment method -- "+ payment.toString());

    }


    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "endToendFlows")
    public void placeOrderMyOrderValidationTest(String sellerName, String itemId, String email,String category,String exchangeProd){


        LOGGER.info("test placing order and my order validation");

        Map<String,String> map = placeOrder(sellerName,itemId,email,category,QuikrXEnumData.paymentMehtod.CREDIT_CARD,exchangeProd);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        Assert.assertEquals(map.get("status"),QuikrXEnumData.subOrderStatus.PENDING.toString(),"Mismatch in status");
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        Assert.assertEquals(map.get("status"),QuikrXEnumData.subOrderStatus.CALL_CONFIRMED.toString(),"Mismatch in status");
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));

        LOGGER.info("test execute for category -- "+ category);
    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "endToendFlows")
    public void placeOrderCancelOrderTest(String sellerName, String itemId, String email,String category,String exchangeProd){

        LOGGER.info("test placing order and cancelling order");

        Map<String,String>  map = placeOrder(sellerName,itemId,email,category,QuikrXEnumData.paymentMehtod.COD,exchangeProd);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        Assert.assertEquals(map.get("status"),QuikrXEnumData.subOrderStatus.PENDING.toString(),"Mismatch in status");
        quikrXApiBase.cancelSubOrder(map.get("subOrderId"), map.get("custId"));
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.CANCELLED)));
        Assert.assertEquals(map.get("status"), QuikrXEnumData.subOrderStatus.CUSTOMER_DECLINED.toString(), "Mismatch in status");
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));

        LOGGER.info("test executed for cancelling order for -- " + category);
    }

    @Test(dataProvider = "oneRupeeScenario", dataProviderClass = QuikrXDataProvider.class)
    public void oneRupeeScenarioTest(String sellerName, String itemId, String email,String category,
                                      String exchangeProd){

        LOGGER.info("test one rupee scenario ");

        quikrXApiBase.updatePrice("50000", sellerName, exchangeProd,"2500");
        Map<String,String> map = placeOrder(sellerName,itemId,email,category, QuikrXEnumData.paymentMehtod.COD,exchangeProd);
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        Assert.assertEquals("1.00",map.get("totalPrice"),"mismatch in price");
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));

        LOGGER.info("test executed for one rupee scenario  -- ");

    }


    @Test(dataProvider = "iDontHaveaPhone", dataProviderClass = QuikrXDataProvider.class)
    public void exchangeIdonthavePhoneTest(String sellerName, String itemId, String email,String category,
                                     String exchangeProd){

        LOGGER.info("test one rupee scenario ");

        Map<String,String> map = placeOrder(sellerName,itemId,email,category, QuikrXEnumData.paymentMehtod.COD,exchangeProd);
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        Assert.assertEquals(map.get("status"),QuikrXEnumData.subOrderStatus.CALL_CONFIRMED.toString(),"Mismatch in status");
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));

        LOGGER.info("test executed for one rupee scenario  -- ");
    }

    @Test(dataProvider = "cancelOrder", dataProviderClass = QuikrXDataProvider.class)
    public void cancelAfterPackSlip(String sellerName, String itemId, String email,String category,
                                    String exchangeProd){

        LOGGER.info("test placing order and cancelling order");

        Map<String,String>  map = placeOrder(sellerName,itemId,email,category,QuikrXEnumData.paymentMehtod.COD,exchangeProd);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.DISPATCHED);
        String actualError = quikrXApiBase.cancelSubOrder(map.get("subOrderId"), map.get("custId")).get("errorMessage").asText();
        Assert.assertEquals(actualError,"Invalid suborder status for this operation","cancel suborder after pack slip failed");

        LOGGER.info("test executed for cancelling order for -- " + category);

    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "endToendFlows")
    public void deliveredToAmMyOrders(String sellerName, String itemId, String email,String category,String exchangeProd){


        LOGGER.info("test placing order set order to Delivered to Am verify present in my orders");

        Map<String,String> map = placeOrder(sellerName,itemId,email,category,QuikrXEnumData.paymentMehtod.CREDIT_CARD,exchangeProd);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        if(QuikrXEnumData.paymentMehtod.COD.toString().equalsIgnoreCase("cod"))
            Assert.assertEquals(map.get("status"),QuikrXEnumData.subOrderStatus.PENDING.toString(),"Mismatch in status");
        else
            quikrXApiBase.updateSuborderStatus(map.get("subOrderId"),QuikrXEnumData.subOrderStatus.CALL_CONFIRMED.toString());
        quikrXApiBase.updateSuborderStatus(map.get("subOrderId"),QuikrXEnumData.subOrderStatus.DELIVERED_TO_AM.toString());
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        Assert.assertEquals(map.get("status"),QuikrXEnumData.subOrderStatus.DELIVERED_TO_AM.toString(),"Mismatch in status");
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));

        LOGGER.info("test execute for my orders -- ");
    }
}
