

package com.quikr.api.quikrX;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.quikr.api.ApiBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class QuikrXApiBase extends ApiBase {

    String URL;
    String source = "Desktop";
    private final static Log LOGGER = LogFactory.getLog(QuikrXApiBase.class.getName());
    private final static String requestDataFile = "quikrXrequestData";

    public QuikrXApiBase() {
        super(requestDataFile);
    }

    /**
     * create a session
     *
     * @return sessionId
     * @throws Exception
     */
    public String createSession() {

        //Create Session
        String sessionId = null;

        JsonNode response = parseResponse(getMethod("createSession", "createSession"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("createSession method failed " + code);
        } else {
            sessionId = response.get("id").asText();
        }
        return sessionId;
    }

    /**
     * add customerId to session
     *
     * @param Sessionid
     * @param customerId
     * @return
     * @throws IOException
     */
    public JsonNode associateUser(String Sessionid, String customerId) {


        JSONObject Customer = new JSONObject();
        Customer.put("customerId", customerId);
        Customer.put("sessId", Sessionid);
        String jsonData = Customer.toString();
        JsonNode response = parseResponse(postMethod("addCustomerIdToSession", jsonData, "associateUser"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("associateUser method failed " + code + "----" + response.get("errorMessage").asText());
            return errorMessage(response.get("errorMessage").asText(), code);
        }
        return response;

    }

    /**
     * Add certified product
     *
     * @param Sessionid
     * @param sellerId
     * @param productId
     * @return
     */
    public JsonNode addProductCertified(String Sessionid, String sellerId, String productId,String emailId) {

//        removeItemFromCart(Sessionid,productId);

        JSONObject product = new JSONObject();
        product.put("sessId", Sessionid);
        LinkedHashMap<Object, Object> m1 = new LinkedHashMap<Object, Object>();
        LinkedList<String> l1 = new LinkedList<String>();
        LinkedList<Map<Object, Object>> l2 = new LinkedList<Map<Object, Object>>();
        m1.put("productId", productId);
        m1.put("qty", "1");
        m1.put("parentid", "");
        m1.put("type", "NEW");
        m1.put("childIds", l1);
        m1.put("sellerId", sellerId);
        l1.add("125");
        product.put("items", l2);

        l2.add(m1);
        String jsonData = product.toString();

        JsonNode result = parseResponse(postMethod("quikrX/v2/addProducts", jsonData, "addProductCertified"));
        int code = result.get("statusCode").asInt();

        if (code / 100 == 4 || code / 100 == 5 ) {
            JsonNode error = result.get("AddQuikrXProductResponse").get("errors").get(0).get("message");
            if (error.asText().equalsIgnoreCase("Product is already present")) {
                String quoteId = addEmailToSession(Sessionid, emailId).get("quoteId").asText();
                String newCartId = getQuoteById(quoteId).get("cart").get("newItems").get(0).get("id").asText();
                removeItemFromCart(Sessionid, new String[]{newCartId});
                return addProductCertified(Sessionid, sellerId, productId, emailId);
            }
            LOGGER.fatal("addProductCertified method failed " + code + "--" + error.asText());
            return errorMessage(error.asText(), code);
        }
        return result;

    }


    /**
     * Add exchange product to cart
     *
     * @param Sessionid
     * @param sellerId
     * @param productId
     * @param exchangeProd
     * @return
     */
    public JsonNode addProductExchange(String Sessionid, String sellerId, String productId, String exchangeProd,String emailId) {
        JSONObject product = new JSONObject();
        product.put("sessId", Sessionid);
        LinkedHashMap m1 = new LinkedHashMap();
        LinkedHashMap m2 = new LinkedHashMap();
        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();
        m1.put("productId", productId);
        m1.put("qty", "1");
        m1.put("parentid", "");
        m1.put("type", "NEW");
        m1.put("sellerId", sellerId);
        m2.put("productId", exchangeProd);
        m2.put("qty", "1");
        m2.put("parentid", productId);
        m2.put("type", "EXCHANGE");
        m2.put("sellerId",sellerId);
        product.put("items", l2);
        l2.add(m1);
        l2.add(m2);

        String jsonData = product.toString();
        JsonNode result = parseResponse(postMethod("quikrX/v2/addProductsWithExchange", jsonData, "addProductExchange"));
        int code = result.get("statusCode").asInt();
        if (code / 100 == 4 || code / 100 == 5 ) {
            JsonNode error = result.get("AddQuikrXProductWithExchangeResponse").get("errors").get(0).get("message");
            if(error.asText().equalsIgnoreCase("Product is already present")){
                String quoteId = addEmailToSession(Sessionid,emailId).get("quoteId").asText();
                String newCartId = getQuoteById(quoteId).get("cart").get("newItems").get(0).get("id").asText();
                String oldCartId = getQuoteById(quoteId).get("cart").get("oldItems").get(0).get("id").asText();
               removeItemFromCart(Sessionid,new String[]{newCartId,oldCartId});
                return addProductExchange(Sessionid, sellerId, productId, exchangeProd,emailId);
            }
            LOGGER.fatal("addProductExchange method failed " + code + "--" + error.asText());
            return errorMessage(error.asText(), code);
        }
        return result;

    }

    /**
     * Add address to quote, get address from quikrXrequestData.properties file
     *
     * @param quoteId
     * @param customerId
     * @return
     */
    public JsonNode addAddressQuoteId(String quoteId, String customerId) {

        String jsonData = getRequestData("address").replace("customerId", customerId).replace("actualQoute", quoteId);

        JsonNode response = parseResponse(postMethod("quikrX/v2/addAddresses", jsonData, "addAddressQuoteId"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4 || code / 100 == 5 ) {
            JsonNode error = response.get("AddQuikrXAddressesResponse").get("errors").get(0).get("message");
            LOGGER.fatal("addAddressQuoteId method failed " + code + "--" + error.asText());
            return errorMessage(error.asText(), code);
        }
        return response;
    }

    /**
     * Add emailid to Session
     *
     * @param sessionId
     * @param emailId
     * @return
     */
    public JsonNode addEmailToSession(String sessionId, String emailId) {
        //Adding email id to session id
        JSONObject setEmailtoSession = new JSONObject();
        setEmailtoSession.put("email", emailId);
        setEmailtoSession.put("sessId", sessionId);
        String jsonData = setEmailtoSession.toString();

        return parseResponse(postMethod("addEmailIdToSession", jsonData, "addEmailToSession"));

    }

    /**
     * update payment method to the created quote
     *
     * @param quoteId
     * @param
     * @return
     */
    public JsonNode updatePaymentMethodToQuote(String quoteId, QuikrXEnumData.paymentMehtod type) {
        //updatePaymentMethodToQuote

        JSONObject updatePaymentMethodToQuote = new JSONObject();
        LinkedList<String> l5 = new LinkedList<String>();
        updatePaymentMethodToQuote.put("quoteId", quoteId);
        updatePaymentMethodToQuote.put("paymentModeList", l5);
        l5.add(type.toString());
        String jsonData = updatePaymentMethodToQuote.toString();

        return parseResponse(postMethod("updatePaymentMethodToQuote", jsonData, "updatePaymentMethodToQuote"));

    }

    /**
     * place order of added product
     *
     * @param sessionId
     * @param quoteId
     * @return
     */
    public JsonNode placeOrder(String sessionId, String quoteId) {
        JSONObject placeOrder = new JSONObject();
        placeOrder.put("quoteId", quoteId);
        placeOrder.put("sessId", sessionId);
        placeOrder.put("source", "Desktop");
        String jsonData = placeOrder.toString();
        JsonNode result = parseResponse(postMethod("placeOrder", jsonData, "placeOrder"));
        int code = result.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("placeOrder method failed " + code + "--" + result.get("errorMessage").asText());
            return errorMessage(result.get("errorMessage").asText(), code);
        }
        return result;

    }

    /**
     * update status of an orderId
     *
     * @param orderNumber
     * @param status
     * @return
     */

    public JsonNode updateQuoteItemStatus(String orderNumber, QuikrXEnumData.subOrderStatus status) {
        if (source.equals("Android") == false) {
            JSONObject updateQuoteItemStatus = new JSONObject();
            updateQuoteItemStatus.put("orderId", orderNumber);
            updateQuoteItemStatus.put("status", status.toString());
            String jsonData = updateQuoteItemStatus.toString();
            JsonNode result = parseResponse(postMethod("updateQuoteItemStatus", jsonData, "updateQuoteItemStatus"));
            int code = result.get("statusCode").asInt();
            if (code / 100 == 4) {
                String erroMessage = result.get("UpdateStatusResponse").get("errors").get(0).get("message").asText();
                LOGGER.info("updateQuoteItemStatus failed with response code " + result.get("statusCode").toString());
                return errorMessage(erroMessage, code);

            }

        }

        return null;

    }

    /**
     * Delete an gote associated with session and orderId
     *
     * @param quoteid
     * @param sessionid
     * @return
     */

    public JsonNode deleteQuote(String quoteid, String sessionid) {
        JSONObject deleteQuote = new JSONObject();
        deleteQuote.put("quoteId", quoteid);
        deleteQuote.put("sessionId", sessionid);
        String jsonData = deleteQuote.toString();

        JsonNode result = parseResponse(postMethod("deleteQuote", jsonData, "deleteQuote"));
        int code = result.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("deleteQuote method failed " + code + result.get("errorMessage").asText());
            return errorMessage(result.get("errorMessage").asText(), code);
        }
        return result;
    }

    /**
     * get quote of a quoteId
     *
     * @param quoteID
     * @return
     * @throws IOException
     */
    public JsonNode getQuoteById(String quoteID) {
        JsonNode result = parseResponse(getMethod("getQuoteById?quoteId=" + quoteID, "getQuoteById"));
        int code = result.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("getQuoteById method failed " + code + result.get("errorMessage").asText());
            return errorMessage(result.get("errorMessage").asText(), code);
        }
        return result;
    }

    /**
     * update price of a given item and seller
     *
     * @param price
     * @param sellerId
     * @param productId
     * @return
     */

    public JsonNode updatePrice(String price, String sellerId, String productId,String mrp) {

        JSONObject body = new JSONObject();

        body.put("productId", productId);
        body.put("sellerId", sellerId);
        body.put("sellingPrice", price);
        body.put("updatedBy", sellerId);
        body.put("mrp",mrp);


        String jsonData = body.toString();
        JsonNode response = parseResponse(postMethod("quikrX/updateSellerPrice", jsonData, "updatePrice"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("updateSellerPriceResponse").get("errors").get(0).get("message").asText();
            LOGGER.fatal("updatePrice failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;
    }

    /**
     * update inventory of a given item and seller id
     *
     * @param inventory
     * @param
     * @param sellerId
     * @param productId
     * @return
     */
    public JsonNode updateProductInventory(String inventory, QuikrXEnumData.inventoryUpdateType type, String sellerId, String productId) {

        JSONObject body = new JSONObject();

        body.put("productId", productId);
        body.put("sellerId", sellerId);
        body.put("inventoryCount", inventory);
        body.put("inventoryUpdateType", type.toString());
        body.put("updatedBy", sellerId);


        String jsonData = body.toString();
        JsonNode response = parseResponse(postMethod("quikrX/updateSellerInventory", jsonData, "updateProductInventory"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("updateSellerInventoryResponse").get("errors").get(0).get("message").asText();
            LOGGER.fatal("updateProductInventory failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;
    }


    /**
     * update status of a product for a given seller
     *
     * @param status
     * @param sellerId
     * @param productId
     * @return
     */
    public JsonNode updateProductSellerStatus(String status, String sellerId, String productId) {

        JSONObject body = new JSONObject();

        body.put("productId", productId);
        body.put("sellerId", sellerId);
        body.put("status", status);
        body.put("updatedBy", sellerId);

        String jsonData = body.toString();
        JsonNode response = parseResponse(postMethod("quikrX/updateProductSellerStatus", jsonData, "updateProductSellerStatus"));
        return response;
    }

    /**
     * get order details for email/customer id
     *
     * @param idType
     * @param id
     * @param type
     * @return
     */
    public JsonNode myOrders(String idType, String id, QuikrXEnumData.myordersStatus type) {

        JsonNode response = parseResponse(getMethod("/quikrX/getOrderDetails?idType=" + idType + "&id=" + id +
                "&pageNumber=0&pageSize=100&myOrderStatus=" + type.toString(), "myOrders"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("GetOrderDetailsResponse").get("errors").get(0).get("message").asText();
            LOGGER.fatal("myOrders failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;

    }

    /**
     * cancel order by suborder and cutommer id
     *
     * @param subOrder
     * @param customerId
     * @return
     */
    public JsonNode cancelSubOrder(String subOrder, String customerId) {

        JSONObject body = new JSONObject();

        body.put("subOrderId", subOrder);
        body.put("cancellationReason", "I wanted a different model");
        body.put("commentText", "Cancelled through automation");

        String jsonData = body.toString();
        JsonNode response = parseResponse(postMethod("quikrX/cancelSubOrders", jsonData, "cancelSubOrder"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("cancelSubOrdersResponse").get("errors").get(0).get("message").asText();
            LOGGER.fatal("cancelSubOrder failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;
    }

    /**
     * get seller details for a given product id
     *
     * @param itemId
     * @return
     */
    public JsonNode getProductSellerDetails(String itemId) {

        JsonNode response = parseResponse(getMethod("/quikrX/getProductSellerDetails?productId=" + itemId, "getProductSellerDetails"));

        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("getProductSellerDetailsResponse").get("errors").get(0).get("message").asText();
            LOGGER.info("getProductSellerDetails failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;
    }

    public JsonNode getProduct(String itemId) {

        JsonNode response = parseResponse(getMethod("product?id=" + itemId, "getProduct"));
        return response;
    }

    /**
     * place an replacement order with suorderId
     *
     * @param subOrder
     * @return
     */
    public JsonNode placeReplaceMentOrder(String subOrder, String sellerId, String owner) {

        JSONObject body = new JSONObject();

        body.put("subOrderId", subOrder);
        body.put("sellerId", sellerId);
        body.put("replacementOwner", owner);
        String jsonData = body.toString();
        JsonNode response = parseResponse(postMethod("/quikrX/placeReplacementOrder", jsonData, "placeReplaceMentOrder"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("PlaceReplacementOrderResponse").get("errors").get(0).get("message").asText();
            LOGGER.info("placeReplaceMentOrder failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;

    }

    private JsonNode errorMessage(String errorMessage, int status) {

        ObjectNode res = JsonNodeFactory.instance.objectNode();
        res.put("errorMessage", errorMessage);
        res.put("statusCode", status);

        return res;
    }

    /**
     * get order details of a given order Id
     *
     * @param orderId
     * @return
     */
    public JsonNode getOrderById(String orderId) {

        JsonNode response = parseResponse(getMethod("quikrX/getOrderById?orderId=" + orderId, "getOrderById"));

        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("GetOrderDetailsRespons").get("errors").get(0).get("message").asText();
            LOGGER.info("getOrderById failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;
    }

    /**
     * Api to update a given productid
     * @param productId
     * @param productName
     * @param brand
     * @param sellerId
     * @param skuDiscount
     * @return
     */
    public JsonNode updateProduct(String productId,String productName,String brand,String sellerId,String skuDiscount,String subCategory){

        JSONObject body = new JSONObject();

        body.put("productId", productId);
        body.put("productName", productName);
        body.put("brand", brand);
        body.put("productStatus", "PUBLISHED");
        body.put("safetyStock", "5");
        body.put("updatedBy", sellerId);
        body.put("subCategoryId", subCategory);
        body.put("skuDiscount", skuDiscount);
        body.put("isExtendedWarrantyEnabled", "false");
        body.put("isExtendedWarrantyFree", "false");
        body.put("extendedWarrantyPercentage", "1.00");
        body.put("newPhonePrice", "10000");
        body.put("onlineDiscountType", "1");
        body.put("onlineDiscountValue", "0.00");
        body.put("onlineDiscountEnabled", "false");

        String jsonData = body.toString();
        JsonNode response = parseResponse(postMethod("quikrX/updateProduct", jsonData, "updateProduct"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("updateProductMasterResponse").get("errors").get(0).get("message").asText();
            LOGGER.fatal("cancelSubOrder failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;

    }

    /**
     * update a given product category
     * @param category
     * @return
     */
    public JsonNode categoryProduct(String category){

        JSONObject body = new JSONObject();
        String jsonData = getRequestData("categoryProduct").replace("inputCategory",category);

        JsonNode response = parseResponse(postMethod("categoryproducts", jsonData, "categoryproducts"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("categoryProduct failed with response code " + response.get("statusCode").toString());
            return null;
        }
        return response;
    }

    /**
     * update a given product category
     * @param category
     * @return
     */
    public JsonNode categoryProduct(String category,String sellerId){

        JSONObject body = new JSONObject();
        String jsonData = getRequestData("categoryProductWithSellerId").replace("inputCategory",category).replace("sellerIdValue",sellerId);

        JsonNode response = parseResponse(postMethod("categoryproducts", jsonData, "categoryproducts"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("categoryProduct failed with response code " + response.get("statusCode").toString());
            return null;
        }
        return response;
    }

    /**
     * update a given product category
     * @param category
     * @return
     */
    public JsonNode categoryProductGradation(String category,String gradationValue){

        JSONObject body = new JSONObject();
        String jsonData = getRequestData("categoryProductGrad").replace("inputCategory",category).replace("gradIdValue",gradationValue);

        JsonNode response = parseResponse(postMethod("categoryproducts", jsonData, "categoryproducts"));
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("categoryProduct failed with response code " + response.get("statusCode").toString());
            return null;
        }
        return response;
    }


    public JsonNode exchangeAndroid(String catId){

        JsonNode response = parseResponse(getMethod("quikrX/getBrandModelDetails?categoryId="+catId+"","exchangeAndroid"));

        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("getBrandModelDetailsResponse").get("errors").get(0).get("message").asText();
            LOGGER.info("getProductSellerDetails failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;

    }

    public JsonNode getB2CBrandModel(String brand,String model,String score,String condition,String areaId){

        JsonNode response = parseResponse(getMethod("quikrX/getB2CBrandModel?brand="+brand+"&model="+model+"&score="+score+"&condition="+condition+"&source=Desk+&areaId="+areaId,"getB2CBrandModel"));

        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("getB2CBrandModelResponse").get("errors").get(0).get("message").asText();
            LOGGER.info("getProductSellerDetails failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;

    }

    /**
     * get popular products with category id
     * @param catId
     * @return
     */
    public JsonNode getPopularProducts(String catId){

        JsonNode response = parseResponse(getMethod("/quikrX/getPopularProducts?categories="+catId+"&size=5","exchangeAndroid"));

        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("getBrandModelDetailsResponse").get("errors").get(0).get("message").asText();
            LOGGER.info("getProductSellerDetails failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;

    }

    /**
     * Remove item from the cart
     * @param sessionId
     * @param items
     * @return
     */
    public JsonNode removeItemFromCart(String sessionId,String[] items){

        JSONObject body = new JSONObject();
        LinkedList<String> l = new LinkedList<>();
        l.add(items[0]);

        body.put("sessionId", sessionId);
        body.put("cartItemIds",l);
        String jsonData = body.toString();

        JsonNode response = parseResponse(postMethod("/removeCartItems",jsonData,"removeCartItems"));

        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("errors").get(0).get("message").asText();
            LOGGER.info("removeItemFromCart failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
     return response;

    }

    /**
     * update status of a subOrder id
     * @param subOrder
     * @param status
     * @return
     */
    public JsonNode updateSuborderStatus(String subOrder, String status){

        JSONObject body = new JSONObject();

        body.put("subOrderId", subOrder);
        body.put("subOrderStatus", status);
        body.put("userId", "2");
        body.put("comment", "automation");
        body.put("dispatchCount", 1);


        String jsonData = body.toString();

        JsonNode response = parseResponse(postMethod("/quikrX/updateSuborderStatus",jsonData,"updateSuborderStatus"));

        int code = response.get("statusCode").asInt();
        if (code / 100 == 4 || code / 100 == 5 ) {
            String erroMessage = response.get("UpdateSuborderStatusResponse").get("errors").get(0).get("message").asText();
            LOGGER.info("getProductSellerDetails failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;

    }

    public JsonNode createStqLead(String email, long phoneNumber){

        JSONObject body = new JSONObject();

        body.put("name", "Automation test");
        body.put("brand", "Apple");
        body.put("model", "Iphone 6");
        body.put("price", 6549);
        body.put("emailId", email);
        body.put("phoneNumber", phoneNumber);
        body.put("city", "Bangalore");
        body.put("pinCode", "560016");
        body.put("grade", "Good");
        body.put("postAdPrice", 45698);
        body.put("leadSource", "Desktop");
        body.put("address", "Bangalore city");

        String jsonData = body.toString();
        JsonNode response = parseResponse(postMethod("quikrX/v1/createSTQLead",jsonData,"createSTQLead"));

        int code = response.get("statusCode").asInt();
        if (code / 100 == 4 || code / 100 == 5 ) {
            String erroMessage = response.get("createSTQLeadResponse").get("errors").get(0).get("message").asText();
            LOGGER.info("createSTQLead failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code);

        }
        return response;

    }



}
