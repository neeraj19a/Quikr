package com.quikr.api.quikrX;


import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by francis.s@quikr.com on 13/11/15.
 */
public class QuikrXApiTestBase {

    private final static Log LOGGER = LogFactory.getLog(QuikrXApiTestBase.class.getName());
    protected QuikrXApiBase quikrXApiBase = new QuikrXApiBase();

    public Map<String,String> placeOrder(String sellerName, String itemId, String email,String category,QuikrXEnumData.paymentMehtod payment,String exchangeProd){


        String quoteId=null;
        Map<String,String> map = new HashMap();

        String sessId =  quikrXApiBase.createSession();
        map.put("sessId",sessId);
        LOGGER.info("sessId " + sessId);
        String custId = quikrXApiBase.addEmailToSession(sessId, email).get("customerId").asText();
        LOGGER.info("custId "+custId);
        map.put("custId", custId);
        quikrXApiBase.associateUser(sessId, custId).get("state");
        quikrXApiBase. updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE, sellerName, itemId);
        quikrXApiBase.updatePrice("5000", sellerName, itemId,"");
        quikrXApiBase.updateProductSellerStatus("true",sellerName,itemId);

        if(category.equalsIgnoreCase("certified"))
            quoteId = quikrXApiBase.addProductCertified(sessId, sellerName, itemId,email).get("AddQuikrXProductResponse").get("success").get("quote").get("id").asText();
        else
            quoteId = quikrXApiBase.addProductExchange(sessId, sellerName, itemId, exchangeProd,email).get("AddQuikrXProductWithExchangeResponse").get("success").get("quote").get("id").asText();

        map.put("quoteId",quoteId);
        quikrXApiBase.associateUser(sessId, custId);
        quikrXApiBase.addAddressQuoteId(quoteId, custId);
        quikrXApiBase.updatePaymentMethodToQuote(quoteId, payment);
        String orderId =  quikrXApiBase.placeOrder(sessId, quoteId).get("orderId").asText();
        map.put("orderId",orderId);
        LOGGER.info("orderId "+orderId);
        return  map;

    }

    public Map<String,String> getStatusFromMyorder(JsonNode response){

        Map<String,String> map = new HashMap();
        response = response.get("GetOrderDetailsResponse").get("GetOrderDetails").get("orderDetailsData").get(0);
        map.put("status", response.get("latestStatus").asText());
        map.put("subOrderId",response.get("subOrderId").asText());
        map.put("totalPrice",response.get("totalPrice").asText());
        return map;
    }

    public String calulateDiscountPercentage(int percentage,int price){

        DecimalFormat decim = new DecimalFormat("0.00");
        float per = 100 - percentage;
        float priceInt = price;
        return  decim.format(Math.ceil((priceInt * per) / 100));
    }

    public String calulateDiscountPercentage(float percentage,float price){

        DecimalFormat decim = new DecimalFormat("0.0");
        float res =price-((percentage/100)*price);
        return ""+res;
    }

    public int roundOff(String intput){

       float val = Float.parseFloat(intput);
       double res = val - (int)val;
        int send;
       if(res>0.5)
          send= (int)Math.ceil(val);
        else
           send= (int)Math.floor(val);

        return send;

    }

    public int getInventoryForSeller(String seller, String itemId){

        JsonNode response  = quikrXApiBase.getProductSellerDetails(itemId).get("getProductSellerDetailsResponse").get("success").get("sellers");
        int inventory=0;
        Iterator<Map.Entry<String, JsonNode>> nodeIterator = response.fields();
        while (nodeIterator.hasNext()){
            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodeIterator.next();
            if(entry.getKey().equals(seller))
                inventory= response.get(seller).get("seller_inventory").asInt();
        }
        return inventory;
    }

}
