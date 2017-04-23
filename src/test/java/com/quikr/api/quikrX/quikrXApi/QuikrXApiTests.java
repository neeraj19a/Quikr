package com.quikr.api.quikrX.quikrXApi;


import com.fasterxml.jackson.databind.JsonNode;
import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXApiTestBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.api.quikrX.dataProviders.QuikrXDataProvider;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * created by francis.s@quikr.com
 */
public class QuikrXApiTests extends QuikrXApiTestBase {
    String quoteId = null;
    String sessionId = null;
    String sellerId = quikrXApiBase.getRequestData("sellerId");
    String productId = quikrXApiBase.getRequestData("productId");

    private final static Log LOGGER = LogFactory.getLog(QuikrXApiBase.class.getName());

    @BeforeClass
    public void setUp() {

        sessionId = quikrXApiBase.createSession();
        quikrXApiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE, sellerId, productId);
        quoteId = quikrXApiBase.addProductCertified(sessionId, sellerId, productId,"").get("AddQuikrXProductResponse").get("success").get("quote").get("id").asText();
    }

    @Test(enabled = true)
    public void testGetQuoteById() {

        LOGGER.info("executing getQuoteByid");

        JsonNode response = quikrXApiBase.getQuoteById(quoteId);
//		System.out.println(response.toString());
        String actualSeller = response.get("cart").get("newItems").get(0).get("sellerName").asText();
        String actualSellerid = response.get("cart").get("newItems").get(0).get("sellerId").asText();

        Assert.assertNotNull(actualSeller, "seller details missing");
        Assert.assertEquals(actualSellerid, sellerId, "sellerid mismatch");

        LOGGER.info("executed getQuoteByid");

    }

    @Test(enabled = true)
    public void testUpdatePrice() {

        LOGGER.info("executing priceUpdate");
        Assert.assertEquals(getUpdatedPrice("15899"), "15899.0", "updated price not matching");
        LOGGER.info("executed priceUpdate");
    }

    @Test(enabled = true)
    public void validateGetQuoteByIdAfterUpdate() {

        LOGGER.info("executing getQuotebyId after price change");
        String afterUpdate = getUpdatedPrice("16000");
        Assert.assertEquals(Float.parseFloat(afterUpdate), Float.parseFloat(getQuoteByIdPrice(quoteId)), "updated price not matching");
        LOGGER.info("executed getQuotebyId after price change");

    }

    public String getQuoteByIdPrice(String quote) {
        JsonNode response = quikrXApiBase.getQuoteById(quote);
        return response.get("cart").get("newItems").get(0).get("sellerPrice").get("value").asText();

    }

    public String getUpdatedPrice(String updatedPrice) {

        JsonNode response = quikrXApiBase.updatePrice(updatedPrice, sellerId, productId,"");
        return response.get("updateSellerPriceResponse").get("success").get("updatedSellingPrice").asText();
    }

    @Test
    public void certifieditemDiscountTest() {

        String priceAfterDicount = null;
        JsonNode response = quikrXApiBase.getProductSellerDetails(productId);
        int sku_dicount = response.get("getProductSellerDetailsResponse").get("success").get("sku_discount").asInt();
        String price = response.get("getProductSellerDetailsResponse").get("success").get("sellers").get(sellerId).get("seller_price").asText().replace(".00", "");
        int itemPrice = Integer.parseInt(price);

        String sessId = quikrXApiBase.createSession();
        LOGGER.info("sessId " + sessId);
        String custId = quikrXApiBase.addEmailToSession(sessId, "testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com").get("customerId").toString();
        LOGGER.info("custId " + custId);
        quikrXApiBase.associateUser(sessId, custId).get("state");
        quikrXApiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE, sellerId, productId);
        response = quikrXApiBase.addProductCertified(sessId, sellerId, productId,"");
        String qouteId = response.get("AddQuikrXProductResponse").get("success").get("quote").get("id").asText();
        priceAfterDicount = response.get("AddQuikrXProductResponse").get("success").get("quote").get("charges").get("grandTotal").get("value").asText();
        Assert.assertEquals(calulateDiscountPercentage(sku_dicount, itemPrice), priceAfterDicount, "mimatch in discount prices for item id " + productId);
        quikrXApiBase.deleteQuote(qouteId, sessId);

    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "endToendFlows")
    public void replacementSameSellerTest(String sellerName, String itemId, String email, String category, String exchangeProd) {

        LOGGER.info("executing replacement same seller test");
        Map<String, String> map = placeOrder(sellerName, itemId, email, category, QuikrXEnumData.paymentMehtod.CREDIT_CARD, exchangeProd);
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        JsonNode response = quikrXApiBase.placeReplaceMentOrder(map.get("subOrderId"), sellerId, "Seller");
        String newOrderId = response.get("PlaceReplacementOrderResponse").get("success").get("orderId").asText();
        response = quikrXApiBase.getOrderById(newOrderId).get("GetOrderDetailsResponse").get("success").get("quote");
        String replacesellerId = response.get("cart").get("newItems").get(0).get("sellerId").asText();
        String newFinalPrice = response.get("cart").get("newItems").get(0).get("netAmount").get("value").asText();
        String paymentMethod = response.get("paymentMethods").get(0).asText();
        Assert.assertEquals(map.get("totalPrice"), newFinalPrice, "price mismatch in replacement");
        Assert.assertEquals(sellerName, replacesellerId, "seller mismatch in replacement");
        Assert.assertEquals(paymentMethod, "PREPAID", "payment method not prepaid");
        LOGGER.info("executed replacement same seller test");

    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "replacement")
    public void replacementDifferentSeller(String sellerName, String itemId, String email, String category, String exchangeProd) {

        LOGGER.info("executing replacement different seller test");

        Map<String, String> map = placeOrder(sellerName, itemId, email, category, QuikrXEnumData.paymentMehtod.CREDIT_CARD, exchangeProd);
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
            quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        quikrXApiBase.updateProductInventory("0", QuikrXEnumData.inventoryUpdateType.ABSOLUTE, sellerName, itemId);
        String newSeller = updateInventory(itemId, sellerName);
        JsonNode response = quikrXApiBase.placeReplaceMentOrder(map.get("subOrderId"), newSeller, "Quikr");
        String newOrderId = response.get("PlaceReplacementOrderResponse").get("success").get("orderId").asText();
        response = quikrXApiBase.getOrderById(newOrderId).get("GetOrderDetailsResponse").get("success").get("quote");
        Assert.assertNotNull(response.get("cart").get("newItems").get(0).get("sellerId").asText(), "sellerId is not present");
        Assert.assertNotNull(response.get("cart").get("newItems").get(0).get("netAmount").get("value").asText(), "price is empty");
        Assert.assertEquals(response.get("paymentMethods").get(0).asText(), "PREPAID", "payment method not prepaid");
        quikrXApiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE, sellerName, itemId);

    }

    private String updateInventory(String itemId, String sellerId) {

        String newSeller = null;
        JsonNode response = quikrXApiBase.getProductSellerDetails(itemId).get("getProductSellerDetailsResponse").get("success").get("sellers");
        Iterator<Map.Entry<String, JsonNode>> nodeIterator = response.fields();
        while (nodeIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodeIterator.next();
            if (!entry.getKey().equals(sellerId))
                newSeller = entry.getKey();
        }
        if (response.get(newSeller).get("seller_inventory").asInt() < 4) {
            quikrXApiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE, newSeller, itemId);
        }

        return newSeller;
    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "replacement")
    public void inventoryReductionTest(String sellerName, String itemId, String email, String category, String exchangeProd) {

        int preOrderInventory = getInventoryForSeller(sellerName, itemId);
        Map<String, String> map = placeOrder(sellerName, itemId, email, category, QuikrXEnumData.paymentMehtod.CREDIT_CARD, exchangeProd);
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));
        int presaleInventory = getInventoryForSeller(sellerName, itemId);
        Assert.assertEquals(preOrderInventory,presaleInventory,"mismatch in inventory for pending status");
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        int postSaleInventory = getInventoryForSeller(sellerName, itemId);
        Assert.assertEquals(postSaleInventory, presaleInventory - 1, "inventory reduction failed");

    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "replacement")
    public void inventoryIncreaseTest(String sellerName, String itemId, String email, String category, String exchangeProd) {

        Map<String, String> map = placeOrder(sellerName, itemId, email, category, QuikrXEnumData.paymentMehtod.CREDIT_CARD, exchangeProd);
        quikrXApiBase.deleteQuote(map.get("quoteId"), map.get("sessId"));
        int presaleInventory = getInventoryForSeller(sellerName, itemId);
        map.putAll(getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        quikrXApiBase.cancelSubOrder(map.get("subOrderId"), map.get("custId"));
        int postSaleInventory = getInventoryForSeller(sellerName, itemId);
        Assert.assertEquals(postSaleInventory, presaleInventory, "inventory increase failed");
    }

}
