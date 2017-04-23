package com.quikr.api.escrow;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.quikr.api.ApiBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.SignatureException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by gurinder.singh on 11/12/15.
 */
public class EscrowApiBase extends ApiBase {
    String URL;
    String source = "Desktop";
    private final static Log LOGGER = LogFactory.getLog(EscrowApiBase.class.getName());
    private final static String requestDataFile = "escrowRequestData";
    String Env = null;
    public final static String APP_DEVELOPER_EMAIL = "rajat.kachhara@quikr.com";
    public final static String APP_ID = "722";
    public final static String APP_SECRET = "6aa6d8e3e080739b034a69f2bd98bc40";


    public EscrowApiBase() {
        super(requestDataFile);
    }

    /**
     * update Delivery
     *
     * @param adId
     * @return
     */

    public JsonNode preShip(String adId, String paymentMode, String transactionId, String Amount, String OrderId, String WaybillNo) {
        JSONObject preShipJson = new JSONObject();
        LinkedHashMap<Object, Object> m1 = new LinkedHashMap<Object, Object>();
        LinkedHashMap<Object, Object> m2 = new LinkedHashMap<Object, Object>();
        LinkedHashMap<Object, Object> m3 = new LinkedHashMap<Object, Object>();
        LinkedHashMap<Object, Object> m4 = new LinkedHashMap<Object, Object>();
        LinkedHashMap<Object, Object> m5 = new LinkedHashMap<Object, Object>();

//        adDetails
        m1.put("adId", adId);
        m1.put("Ad_Posted_on", epochTime());
        m1.put("Ad_City", "Bangalore");
        m1.put("productPriceListed", "0");
        m1.put("adTitle", "Ad Title for an automation script");
        m1.put("adDescription", "Ad Description for an automation script");
        m1.put("adImageURL", "http://teja7.kuikr.com");
//        cashBackDetails
        m2.put("cashbackToSeller", "0");
        m2.put("cashbackToBuyer", "0");
//        buyerPaymentDetails
        m3.put("paymentMode", paymentMode);
        m3.put("transactionID", transactionId);
        m3.put("paymentDate", epochTime());
        m3.put("paymentAmount", Amount);
        m3.put("paymentGatewayStatusCode", "0");
        m3.put("paymentStatusMessage", "Transaction Successful");
        m3.put("PaymentGatewayReferenceID", "244hfx");
//        buyerDetails
        m4.put("name", "BuyerAuto");
        m4.put("emailId", "web20@mail.quikrtest.com");
        m4.put("phoneNo", "7829169640");
        m4.put("pincode", "560020");
        m4.put("address", "Quikr Inc");
//        sellerDetails
        m5.put("name", "SellerAuto");
        m5.put("emailId", "web19@mail.quikrtest.com");
        m5.put("phoneNo", "8123136162");
        m5.put("pincode", "560019");
        m5.put("address", "Quikr Inc");

        preShipJson.put("adDetails", m1);
        preShipJson.put("cashBackDetails", m2);
        preShipJson.put("buyerPaymentDetails", m3);
        preShipJson.put("buyerDetails", m4);
        preShipJson.put("sellerDetails", m5);
        preShipJson.put("categoryName", "Clothing - Garments");
        preShipJson.put("subcategoryName", "Home \\u0026 Lifestyle");
        preShipJson.put("weightCategory", "Light");
        preShipJson.put("orderId", OrderId);
        preShipJson.put("wayBillNo", WaybillNo);

        String jsonRequest = preShipJson.toString();
        JsonNode result = parseResponse(postMethodEscrow("http://delhiveryc2c.smartinfield.com:8093/api/ext/orders/", jsonRequest, "updateOrderStatusPostShip"));
        int code = result.get("statusCode").asInt();
        if (code / 100 == 4) {
            LOGGER.fatal("deleteQuote method failed " + code + result.get("errorMessage").asText());
            return errorMessage(result.get("errorMessage").asText(), code);
        }
        return result;
    }

    /**
     * update order status PostShip
     *
     * @param adId
     * @return
     */

    public JsonNode updateOrderStatusPostShip(String adId, String offerId, String transactionId, EscrowEnumData.overall_status overall_status,
                                              String paymentMode, EscrowEnumData.scheduling_status scheduling_status,
                                              EscrowEnumData.item_direction item_direction, String WaybillNo) {
        JSONObject postShipJson = new JSONObject();
        JsonNode result = null;
        String Envr = null;
        postShipJson.put("adDetails.ad_City", "Bangalore");
        postShipJson.put("storage_days", "0");
        postShipJson.put("buyerDetails.state", "Karnataka");
        postShipJson.put("created_on", epochTime());
        postShipJson.put("overall_status", overall_status.name());
        postShipJson.put("schedule.last_seller_pref.date", epochTime());
        postShipJson.put("seller_status.contact_status", "-");
        postShipJson.put("buyerDetails.pincode", "600096");
        postShipJson.put("buyerDetails.city", "Bangalore");
        postShipJson.put("order_category", "C2C");
        postShipJson.put("schedule.last_seller_pref.slot.enabled", "true");
        postShipJson.put("buyerDetails.pincode_obj.City", "Bangalore");
        postShipJson.put("adDetails.productPriceListed", "5000");
        postShipJson.put("buyerDetails.pincode_obj.Pincode", "560020");
        postShipJson.put("adDetails.adDescription", "Ad Description for an automation script");
        postShipJson.put("buyerPaymentDetails.paymentMode", paymentMode);
        postShipJson.put("schedule.last_seller_pref.slot.start_time", "1000");
        postShipJson.put("buyerDetails.name", "AutomationBuyer");
        postShipJson.put("sellerDetails.city", "Bangalore");
        postShipJson.put("cancelled_reason", "NA");
        postShipJson.put("sellerDetails.pincode_obj._id", "563b72b844aeac63577f3c86");
        postShipJson.put("buyerPaymentDetails.paymentGatewayStatusCode", "0");
        postShipJson.put("sellerDetails.pincode_obj.Pincode", "600018");
        postShipJson.put("adDetails.ad_Posted_on", epochTime());
        postShipJson.put("product_category2", "Home \u0026 Lifestyle");
        postShipJson.put("buyer_status.scheduling_status", scheduling_status.name());
        postShipJson.put("sellerDetails.state", "Karnataka");
        postShipJson.put("buyerPaymentDetails.paymentGatewayReferenceID", "8dzbbz");
        postShipJson.put("seller_status.scheduled_time_to", "1300");
        postShipJson.put("client_weight_category", "Heavy");
        postShipJson.put("item_status", "delivered");
        postShipJson.put("schedule.last_seller_pref.slot.end_time", "1300");
        postShipJson.put("buyerDetails.emailId", "web20@mail.quikrtest.com");
        postShipJson.put("order_status", "Regular");
        postShipJson.put("client_code", "quikr");
        postShipJson.put("buyerDetails.pincode_obj.State", "Karnataka");
        postShipJson.put("schedule.last_seller_pref.slot.duration", "180");
        postShipJson.put("sellerDetails.pincode_obj.City", "Chennai");
        postShipJson.put("storage_utilized", "false");
        postShipJson.put("buyerPaymentDetails.paymentDate", epochTime());
        postShipJson.put("adDetails.adImageURL", "http,//teja7.kuikr.com/i4/20151130/Wooden-dining-table-with-two-chairs-ak_L981616461-1448862421.jpeg (17KB)");
        postShipJson.put("cashBackDetails.cashbackToBuyer", "0");
        postShipJson.put("sellerDetails.emailId", "web19@mail.quikrtest.com");
        postShipJson.put("weight_category_by", "Default");
        postShipJson.put("sellerDetails.isConfirmed", "true");
        postShipJson.put("schedule.last_seller_pref.slot.title", "10,00 AM - 1,00 PM");
        postShipJson.put("buyerDetails.pincode_obj._id", "563b72c644aeac63577f3cd4");
        postShipJson.put("item_direction", item_direction.name());
        postShipJson.put("buyerDetails.address", "Quikr Buyer");
        postShipJson.put("sellerDetails.pincode", "560019");
        postShipJson.put("buyerDetails.pincode_obj.C2C_HUB", "Bangalore");
        postShipJson.put("rto_count", "0");
        postShipJson.put("schedule.last_seller_pref.remarks", "");
        postShipJson.put("delivery_attempts", "0");
        postShipJson.put("buyerDetails.isConfirmed", "true");
        postShipJson.put("awb_no", WaybillNo);
        postShipJson.put("material_direction", "to_buyer");
        postShipJson.put("buyerPaymentDetails.paymentStatusMessage", "Transaction Successful");
        postShipJson.put("buyerPaymentDetails.paymentAmount", "200");
        postShipJson.put("product_category", "Home - Office Furniture");
        postShipJson.put("_id", "565c2c446764cb034abec2e5");
        postShipJson.put("sellerDetails.pincode_obj.C2C_HUB", "Chennai");
        postShipJson.put("adDetails.adTitle", "Ad Title for an automation script");
        postShipJson.put("adDetails.adId", adId);
        postShipJson.put("seller_status.scheduled_date", epochTime());
        postShipJson.put("seller_status.scheduled_time_from", "1000");
        postShipJson.put("buyerPaymentDetails.transactionID", transactionId);
        postShipJson.put("sellerDetails.name", "AutomationSeller");
        postShipJson.put("sellerDetails.pincode_obj.State", "Karnataka");
        postShipJson.put("seller_status.scheduled_at", "10,00 AM - 1,00 PM");
        postShipJson.put("client_ref_no", offerId);
        postShipJson.put("sellerDetails.pincode_obj.enabled", "true");
        postShipJson.put("cashBackDetails.cashbackToSeller", "0");
        postShipJson.put("buyerDetails.pincode_obj.enabled", "true");
        postShipJson.put("weight_category", "Heavy");
        postShipJson.put("buyerDetails.phoneNo", "7829169640");
        postShipJson.put("schedule.status", "Tentative");
        postShipJson.put("schedule.last_seller_pref.slot._id", "56272292883ed42d416ee648");
        postShipJson.put("sellerDetails.address", "Seller Address Automation");
        postShipJson.put("seller_status.scheduling_status", scheduling_status.name());
        postShipJson.put("sellerDetails.phoneNo", "8123136162");
        postShipJson.put("schedule.last_seller_pref.slot.order", "20");
        postShipJson.put("buyer_status.contact_status", "Not Responding");
        postShipJson.put("order_type", "Regular");
//Newly updated changes in API
        postShipJson.put("event_seq_no", "4");
        postShipJson.put("eventID", "9999999999999");

        String jsonRequest = postShipJson.toString();
        Envr = System.getProperty("DbEnv") == null ? getProperties().get("DbEnv") : System.getProperty("DbEnv");
        //Env = getProperties().get("DbEnv").toString();
        switch (Envr) {
            case "stage":    //.33 staging server
                result = parseResponse(postMethodEscrow("http://192.168.124.55:7878/api/v1/updateOrderStatusPostShip", jsonRequest, "updateOrderStatusPostShip"));
                break;
            case "integration":    //.43 integration server
                result = parseResponse(postMethodEscrow("http://192.168.124.45:7878/api/v1/updateOrderStatusPostShip", jsonRequest, "updateOrderStatusPostShip"));
                break;
            default:
                Assert.assertTrue(false, "Env value not found in config.properties file");
        }

        int code = result.get("statusCode").asInt();
        if (code / 100 != 2) {
            LOGGER.fatal("updateOrderStatusPostShip failed " + code + result.get("errorMessage").asText());
            return errorMessage(result.get("errorMessage").asText(), code);
        }
        return result;
    }

    /**
     * update order status PostShip
     *
     * @param adId
     * @return
     */

    public int[] postAdWareHouseBeforeDeliver(String adId, int status, int flagreason) {
        JSONObject postShipJson = new JSONObject();
        JsonNode result = null;
        String Envr = null;
        int[] statusflag = new int[10];
        postShipJson.put("source", "ADMIN");
        postShipJson.put("adId", adId);
        postShipJson.put("title", "Mobile phone in excellent condition with android os");
        postShipJson.put("description", "Mobile phone in excellent condition with android os Mobile phone in excellent condition with android os");
        postShipJson.put("subcatid", 1043);
        postShipJson.put("attributes", "WareHouse_Enabled:1");
        postShipJson.put("status", status);
        postShipJson.put("flagReason", flagreason);
        postShipJson.put("masterNickName", "admin");
        postShipJson.put("deleteReason", "");
        postShipJson.put("adActionType", "NEW");
        postShipJson.put("miniPostAdValue", "0");
        postShipJson.put("mltDuplicateAd", "");
        postShipJson.put("adminPublishCount", "0");
        postShipJson.put("wrongAdtype", "0");
        postShipJson.put("languageCode", "en");


        String jsonRequest = postShipJson.toString();
        Envr = System.getProperty("DbEnv") == null ? getProperties().get("DbEnv") : System.getProperty("DbEnv");

        switch (Envr) {
            case "stage":    //.33 staging server
                result = parseResponse(postMethodEscrow("http://192.168.124.55:7878/api/v1/content/publishAd", jsonRequest, "postAdWareHouseBeforeDeliver"));
                if (status == 0 && flagreason == 0) {
                    statusflag[0] = result.get("modifiedFields").get("flagReason").asInt();
                    statusflag[1] = result.get("modifiedFields").get("status").asInt();
                    System.out.println("Result from Stage== " + statusflag[0] + "and" + statusflag[1]);
                } else {
                    statusflag[0] = 0;
                    statusflag[1] = 0;
                    System.out.println("Second WareHouse API done successfully");
                }
                break;

            case "integration":    //.43 integration server
                result = parseResponse(postMethodEscrow("http://192.168.124.45:7878/api/v1/content/publishAd", jsonRequest, "postAdWareHouseBeforeDeliver"));
                if (status == 0 && flagreason == 0) {
                    statusflag[0] = result.get("modifiedFields").get("flagReason").asInt();
                    statusflag[1] = result.get("modifiedFields").get("status").asInt();
                    System.out.println("Result from Stage== " + statusflag[0] + "and" + statusflag[1]);
                } else {
                    statusflag[0] = 0;
                    statusflag[1] = 0;
                }
                break;

            default:
                Assert.assertTrue(false, "Env value not found in config.properties file");
        }

        int code = result.get("statusCode").asInt();
        if (code / 100 != 2) {
            LOGGER.fatal("updateOrderStatusPostShip failed " + code + result.get("errorMessage").asText());
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        return statusflag;
    }


    public String getId(String ConfigId) {
        String AdId = getConfigData(ConfigId);
        int UpdateAdId = Integer.valueOf(AdId) + 1;
        setConfigData(ConfigId, Integer.toString(UpdateAdId));
        return AdId;
    }

    private JsonNode errorMessage(String errorMessage, int status) {

        ObjectNode res = JsonNodeFactory.instance.objectNode();
        res.put("errorMessage", errorMessage);
        res.put("statusCode", status);

        return res;
    }

    public String epochTime() {
        long epoch = (System.currentTimeMillis() / 1000);
        return Long.toString(epoch);
    }

    public void publishAd(String AdId) {
        String Envr = null;
        HttpResponse resp = null;
        String json = "{\n" + "\"source\":\"Admin\",\n" + "\"adId\":\"" + AdId + "\",\n" + "\"status\":0\n" + "}";
        Envr = System.getProperty("DbEnv") == null ? getProperties().get("DbEnv") : System.getProperty("DbEnv");

        switch (Envr) {
            case "stage":    //.33 staging server
                resp = postMethodEscrow("http://192.168.124.53:7000/editAdByAdmin", json, "publishAd");
                System.out.println("dev==" + resp);
                break;
            case "integration":    //.43 integration server
                resp = postMethodEscrow("http://192.168.124.44:9000/editAdByAdmin", json, "publishAd");
                System.out.println("int==" + resp);
                break;
            default:
                Assert.assertTrue(false, "Env value not found in config.properties file");
        }
        //API takes time to get publish hence adding sleep here
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publishAdWareHouse(String AdId) {
        String Envr = null;
        HttpResponse resp = null;


        JSONObject postAdRequest = new JSONObject();

        postAdRequest.put("source", "Admin");
        postAdRequest.put("adId", AdId);
        postAdRequest.put("status", 0);

        String json =postAdRequest.toString();    /*"{\n" + "\"source\":\"Admin\",\n" + "\"adId\":\"" + AdId + "\",\n" + "\"status\":0\n" + "}";*/
        Envr = System.getProperty("DbEnv") == null ? getProperties().get("DbEnv") : System.getProperty("DbEnv");

        switch (Envr) {
            case "stage":    //.33 staging server
                resp = postMethodEscrow("http://192.168.124.53:7000/editAdByAdmin", json, "publishAd");
                try {
                    System.out.println("dev==" + resp.getEntity().getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "integration":    //.43 integration server
                resp = postMethodEscrow("http://192.168.124.44:9000/editAdByAdmin", json, "publishAd");
                System.out.println("int==" + resp);
                break;
            default:
                Assert.assertTrue(false, "Env value not found in config.properties file");
        }
    }
    //Epoch time

    /**
     * post Ad
     *
     * @param
     * @return
     */
    public JsonNode postAd(String email, String Mobile, String cityId, String Amount, String isWarehouse) {

        JSONObject postAdRequest = new JSONObject();
        LinkedList<String> images = new LinkedList<>();

        postAdRequest.put("source", "app");
        postAdRequest.put("email", email);
        postAdRequest.put("userMobile", Mobile);
        postAdRequest.put("loggedin", "false");
        postAdRequest.put("loggedInUserId", "");
        postAdRequest.put("isGroupUser", "false");
        postAdRequest.put("groupName", "");
        postAdRequest.put("httpReferrer", "http:\\//bangalore.quikr.com/post-classifieds-ads\\/?postadcategoryid=227");
        postAdRequest.put("referrer", "http:\\//bangalore.quikr.com\\/post-classifieds-ads\\/?postadcategoryid=227");
        postAdRequest.put("remoteAddr", "192.168.2.127");
        postAdRequest.put("userAgent", "Mozilla/5.0 (Windows NT 6.1; rv:34.0) Gecko/20100101 Firefox/34.0 AlexaToolbar/alxf-2.21");
        postAdRequest.put("cityId", cityId);
        switch (cityId) {

            case "23"://Bangalore
                postAdRequest.put("subcatid", "227");
                break;
            case "31"://Mumbai
                postAdRequest.put("subcatid", "1043");
                break;
            case "25": //Chennai
                postAdRequest.put("subcatid", "431");
                break;
            case "33": //Pune
                postAdRequest.put("subcatid", "1247");
                break;
        }

        postAdRequest.put("location", "");

        String Ad_type = "offer";
        String You_are = "Individual";
        String Condition = "Used";
        String Price = Amount;
        String WareHouse_Enabled = isWarehouse;
        String Brand_name = "Apple";
        String No_of_Sims = "Dual";
        String attributes = "Ad_Type:" + Ad_type + ";" + "You_are:" + You_are + ";" +
                "Condition:" + Condition + ";" + "Price:" + Price + ";" +
                "WareHouse_Enabled:" + WareHouse_Enabled + ";" +
                "Brand_name:" + Brand_name + ";" + "No_of_Sims:" + No_of_Sims + ";";
        postAdRequest.put("attributes", attributes);
        postAdRequest.put("title", "5 month old " + Brand_name + " mobile for sale at a price of " + Amount);
        postAdRequest.put("description", "5 month old " + Brand_name + " mobile for sale at a price of " + Amount +
                " The device is in" + Condition + " and sold by " + You_are);

        images.add("i4_ak_753832050-1425986360.jpeg");
        postAdRequest.put("images", images);

        postAdRequest.put("usrprivacypostad", "false");
        postAdRequest.put("alertpostad", "true");
        postAdRequest.put("verifiedLead", "false");
        postAdRequest.put("serverName", "bangalore.quikr.com");
        postAdRequest.put("masterNickname", "null");
        postAdRequest.put("titleAutoGenerate", "false");
        postAdRequest.put("mandVerifyJobs", "false");
        postAdRequest.put("demail", "sfxgajiwtsmohit1gosqyikr@quikr.com");

        String jsonRequest = postAdRequest.toString();
        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.53:7000/postAd", jsonRequest, "postAd"));
        int code = result.get("statusCode").asInt();

        if (code / 100 != 2) {
            LOGGER.fatal("postAd method failed " + code);
            return errorMessage("PostAd failed", code);
        }
        return result;

    }

    public JsonNode postAdWithMinPrice(String email, String Mobile, String cityId, String ListPrice, String Minprice, String isWarehouse) {

        JSONObject postAdRequest = new JSONObject();
        LinkedList<String> images = new LinkedList<>();

        postAdRequest.put("source", "app");
        postAdRequest.put("email", email);
        postAdRequest.put("userMobile", Mobile);
        postAdRequest.put("loggedin", "false");
        postAdRequest.put("loggedInUserId", "");
        postAdRequest.put("isGroupUser", "false");
        postAdRequest.put("groupName", "");
        postAdRequest.put("httpReferrer", "http:\\//bangalore.quikr.com/post-classifieds-ads\\/?postadcategoryid=227");
        postAdRequest.put("referrer", "http:\\//bangalore.quikr.com\\/post-classifieds-ads\\/?postadcategoryid=227");
        postAdRequest.put("remoteAddr", "192.168.2.127");
        postAdRequest.put("userAgent", "Mozilla/5.0 (Windows NT 6.1; rv:34.0) Gecko/20100101 Firefox/34.0 AlexaToolbar/alxf-2.21");
        postAdRequest.put("cityId", cityId);
        switch (cityId) {

            case "23"://Bangalore
                postAdRequest.put("subcatid", "227");
                break;
            case "31"://Mumbai
                postAdRequest.put("subcatid", "1043");
                break;
            case "25": //Chennai
                postAdRequest.put("subcatid", "431");
                break;
            case "33": //Pune
                postAdRequest.put("subcatid", "1247");
                break;
        }

        postAdRequest.put("location", "");
        String Ad_type = "offer";
        String You_are = "Individual";
        String Condition = "Used";
        String Price = ListPrice;
        String MinPrice = Minprice;

        String WareHouse_Enabled = isWarehouse;
        String Brand_name = "Apple";
        String No_of_Sims = "Dual";
        String attributes = "Ad_Type:" + Ad_type + ";" + "You_are:" + You_are + ";" +
                "Condition:" + Condition + ";" + "Price:" + Price + ";" + "Reserved_Price:" + MinPrice + ";" +
                "WareHouse_Enabled:" + WareHouse_Enabled + ";" +
                "Brand_name:" + Brand_name + ";" + "No_of_Sims:" + No_of_Sims + ";";
        postAdRequest.put("attributes", attributes);
        postAdRequest.put("title", "5 month old " + Brand_name + " mobile for sale at a price of " + ListPrice);
        postAdRequest.put("description", "5 month old " + Brand_name + " mobile for sale at a price of " + ListPrice +
                " The device is in" + Condition + " and sold by " + You_are);

        images.add("i4_ak_753832050-1425986360.jpeg");
        postAdRequest.put("images", images);

        postAdRequest.put("usrprivacypostad", "false");
        postAdRequest.put("alertpostad", "true");
        postAdRequest.put("verifiedLead", "false");
        postAdRequest.put("serverName", "bangalore.quikr.com");
        postAdRequest.put("masterNickname", "null");
        postAdRequest.put("titleAutoGenerate", "false");
        postAdRequest.put("mandVerifyJobs", "false");
        postAdRequest.put("demail", "sfxgajiwtsmohit1gosqyikr@quikr.com");

        String jsonRequest = postAdRequest.toString();
        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.53:7000/postAd", jsonRequest, "postAd"));
        int code = result.get("statusCode").asInt();

        if (code / 100 != 2) {
            LOGGER.fatal("postAd method failed " + code);
            return errorMessage("PostAd failed", code);
        }
        return result;

    }

    public JsonNode reviewAd(String AdId) {

        JSONObject reviewRequest = new JSONObject();
        LinkedList<String> images = new LinkedList<>();

        reviewRequest.put("source", "Admin");
        reviewRequest.put("adId", AdId);
        images.add("");
        reviewRequest.put("images", images);
        reviewRequest.put("status", 0);
        reviewRequest.put("flagReason", 0);
        reviewRequest.put("masterNickName", "mohit");
        reviewRequest.put("deleteReason", "");
        reviewRequest.put("adActionType", "");
        reviewRequest.put("miniPostAdValue", 3);
        reviewRequest.put("mltDuplicateAd", "");
        reviewRequest.put("adminPublishCount", 2);
        reviewRequest.put("wrongAdtype", 2);
        reviewRequest.put("imageUpdated", true);

        String jsonRequest = reviewRequest.toString();

        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.53:7000/editAdByAdmin", jsonRequest, "reviewAd"));
        int code = result.get("statusCode").asInt();
        if (code / 100 != 2) {
            LOGGER.fatal("Review on Post Ad Failed " + code);
            return errorMessage("Review on Post Ad Failed", code);
        }
        return result;
    }

    public JsonNode makeAnOffer(String AdId, String price, String buyerMobile, String buyerEmail) {

        JSONObject makeAnOfferRequest = new JSONObject();

        makeAnOfferRequest.put("api_key", "CHAT_ANDROID");
        makeAnOfferRequest.put("adid", AdId);
        makeAnOfferRequest.put("price", price);
        makeAnOfferRequest.put("buyer_mobile", buyerMobile);
        makeAnOfferRequest.put("buyer_email", buyerEmail);
        makeAnOfferRequest.put("buyer_comment", "API Automation");

        String jsonRequest = makeAnOfferRequest.toString();

        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.55:7878/api/v1/createAnOffer", jsonRequest, "makeAnOffer"));

        int code = result.get("statusCode").asInt();

        if (code / 100 != 2) {

            LOGGER.fatal("Make an Offer has Failed" + code);
            return errorMessage("Make An Offer has Failed", code);
        }

        return result;
    }

    public JsonNode acceptOffer(String offerId) {

        JSONObject acceptOfferRequest = new JSONObject();

        acceptOfferRequest.put("offer_id", offerId);
        acceptOfferRequest.put("status", "7");

        String jsonRequest = acceptOfferRequest.toString();

        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.55:7878/api/v1/updateOfferStatus", jsonRequest, "accept offer"));

        int code = result.get("statusCode").asInt();
        if (code / 100 != 2) {

            LOGGER.fatal("Accept Offer with status" + code + result.get("errorMessage").asText());
            return errorMessage("Accept Offer has Failed", code);
        }
        return result;
    }

    public JsonNode acceptCounterOffer(String offerId, String counterPrice) {

        JSONObject acceptOfferRequest = new JSONObject();

        acceptOfferRequest.put("offer_id", offerId);
        acceptOfferRequest.put("status", "8");
        acceptOfferRequest.put("price", counterPrice);

        String jsonRequest = acceptOfferRequest.toString();

        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.55:7878/api/v1/updateOfferStatus", jsonRequest, "accept Counter offer"));

        int code = result.get("statusCode").asInt();

        if (code / 100 != 2) {

            LOGGER.fatal("Accept Offer with status" + code + result.get("errorMessage").asText());
            return errorMessage("Accept Counter Offer has Failed", code);
        }
        return result;
    }

    public JsonNode createPaymentRecord(String offerId, int amount, String userEmail, String paymentMode) {

        JSONObject createPaymentRecordRequest = new JSONObject();

        createPaymentRecordRequest.put("offerId", offerId);
        createPaymentRecordRequest.put("amount", amount * 100);
        createPaymentRecordRequest.put("mode", paymentMode);
        createPaymentRecordRequest.put("userEmail", userEmail);

        String jsonRequest = createPaymentRecordRequest.toString();

        JsonNode result = parseResponseSingleValue(postMethodEscrow("http://192.168.124.55:7878/api/v1/createPaymentRecord", jsonRequest, "accept offer"));

        int code = result.get("statusCode").asInt();

        if (code / 100 != 2) {

            LOGGER.fatal("Accept Offer with status" + code + result.get("errorMessage").asText());
            return errorMessage("Create Payment Record has Failed", code);
        }
        return result;
    }

    public JsonNode updatePaymentRecord(String transactionId) {

        JSONObject updatePaymentRecordRequest = new JSONObject();

        updatePaymentRecordRequest.put("transactionId", transactionId);
        updatePaymentRecordRequest.put("shmartRefId", "676545434213567");
        updatePaymentRecordRequest.put("status_code", "0");
        updatePaymentRecordRequest.put("status_msg", "Transaction Successful");

        String jsonRequest = updatePaymentRecordRequest.toString();

        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.55:7878/api/v1/updatePaymentRecord", jsonRequest, "accept offer"));

        int code = result.get("statusCode").asInt();

        if (code / 100 != 2) {

            LOGGER.fatal("Accept Offer with status" + code + result.get("errorMessage").asText());
            return errorMessage("Update Payment Record has Failed", code);
        }
        return result;
    }

    public JsonNode createWarehouseOrder(String email, String mobile, String cityId, String adId) {

        JSONObject createWarehouseOrderRequest = new JSONObject();

        createWarehouseOrderRequest.put("email", email);
        createWarehouseOrderRequest.put("mobile", mobile);
        createWarehouseOrderRequest.put("adId", adId);
        createWarehouseOrderRequest.put("title", "This automation test suit for API");
        createWarehouseOrderRequest.put("cityId", cityId);

        String jsonRequest = createWarehouseOrderRequest.toString();

        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.55:7878/api/v1/createWarehouseOrder", jsonRequest, "create Warehouse Order"));

        int code = result.get("statusCode").asInt();

        if (code / 100 != 2) {

            LOGGER.fatal("Accept Offer with status" + code + result.get("errorMessage").asText());
            return errorMessage("Create Warehouse Order has failed", code);
        }
        return result;

    }

    public JsonNode postAdElectronics(String email, String Mobile, String cityId, String Amount, String isWarehouse) {

        JSONObject postAdRequest = new JSONObject();
        LinkedList<String> images = new LinkedList<>();
        postAdRequest.put("source", "app");
        postAdRequest.put("email", email);
        postAdRequest.put("userMobile", Mobile);
        postAdRequest.put("loggedin", "false");
        postAdRequest.put("loggedInUserId", "");
        postAdRequest.put("isGroupUser", "false");
        postAdRequest.put("groupName", "");
        postAdRequest.put("httpReferrer", "http://bangalore.quikr.com/post-classifieds-ads/?postadcategoryid=227");
        postAdRequest.put("referrer", "http://bangalore.quikr.com/post-classifieds-ads/?postadcategoryid=227");
        postAdRequest.put("remoteAddr", "192.168.2.127");
        postAdRequest.put("userAgent", "Mozilla/5.0 (Windows NT 6.1; rv:34.0) Gecko/20100101 Firefox/34.0 AlexaToolbar/alxf-2.21");

        postAdRequest.put("cityId", cityId);

        switch (cityId) {

            case "23"://Bangalore
                postAdRequest.put("subcatid", "215");
                break;
            case "31"://Mumbai
                postAdRequest.put("subcatid", "419");
                break;
            case "25": //Chennai
                postAdRequest.put("subcatid", "1031");
                break;
            case "33": //Pune
                postAdRequest.put("subcatid", "1235");
                break;
        }

        String Ad_type = "offer";
        String You_are = "Individual";
        String condition = "Used";
        String Price = Amount;
        String WareHouse_Enabled = isWarehouse;
        String Brand_name = "Dell";
        String Product_Type = "Desktop";
        String attributes = "Ad_Type:" + Ad_type + ";" + "You_are:" + You_are + ";" + "Condition:" + condition + ";" +
                "Price:" + Price + ";" + "Product_Type:" + Product_Type + ";" + "Brand_name:" + Brand_name + ";" +
                "WareHouse_Enabled:" + WareHouse_Enabled + ";";
        ;

        postAdRequest.put("title", "Automation " + Brand_name + Product_Type + "Sale");
        postAdRequest.put("description", "Automation " + Brand_name + Product_Type + " Sale" + " By" + You_are + " with condition" + condition);
        postAdRequest.put("location", "0");

        postAdRequest.put("attributes", attributes);

        images.add("i4_ak_753832050-1425986360.jpeg");
        postAdRequest.put("images", images);


        postAdRequest.put("usrprivacypostad", "false");
        postAdRequest.put("alertpostad", "true");
        postAdRequest.put("verifiedLead", "false");
        postAdRequest.put("serverName", "bangalore.quikr.com");
        postAdRequest.put("masterNickname", "null");
        postAdRequest.put("titleAutoGenerate", "false");
        postAdRequest.put("mandVerifyJobs", "false");
        postAdRequest.put("demail", "sfxgajiwtsmohit1gosqyikr@quikr.com");

        String jsonRequest = postAdRequest.toString();

        System.out.println(jsonRequest);

        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.53:7000/postAd", jsonRequest, "postAd"));

        int code = result.get("statusCode").asInt();
        System.out.println(result.toString());
        if (code / 100 != 2) {
            LOGGER.fatal("postAdElectronics method failed " + code);
            return errorMessage("PostAd Electronics has failed", code);
        }
        return result;

    }

    public JsonNode postAdHome(String email, String Mobile, String cityId, String Amount, String isWarehouse) {

        JSONObject postAdRequest = new JSONObject();
        LinkedList<String> images = new LinkedList<>();

        System.out.println("Entering metood");


        postAdRequest.put("source", "app");
        postAdRequest.put("email", email);
        postAdRequest.put("userMobile", Mobile);
        postAdRequest.put("loggedin", "false");
        postAdRequest.put("loggedInUserId", "");
        postAdRequest.put("isGroupUser", "false");
        postAdRequest.put("groupName", "");
        postAdRequest.put("httpReferrer", "http://bangalore.quikr.com/post-classifieds-ads/?postadcategoryid=227");
        postAdRequest.put("referrer", "http://bangalore.quikr.com/post-classifieds-ads/?postadcategoryid=227");
        postAdRequest.put("remoteAddr", "192.168.2.127");
        postAdRequest.put("userAgent", "Mozilla/5.0 (Windows NT 6.1; rv:34.0) Gecko/20100101 Firefox/34.0 AlexaToolbar/alxf-2.21");

        postAdRequest.put("cityId", cityId);

        switch (cityId) {

            case "23"://Bangalore
                postAdRequest.put("subcatid", "218");
                break;
            case "31"://Mumbai
                postAdRequest.put("subcatid", "1136");
                break;
            case "25": //Chennai
                postAdRequest.put("subcatid", "422");
                break;
            case "33": //Pune
                postAdRequest.put("subcatid", "18222234365");
                break;
        }

        String Ad_type = "offer";
        String You_are = "Individual";
        String condition = "Used";
        String Price = Amount;
        String WareHouse_Enabled = isWarehouse;

        String Furniture_Type = "Cabinets";
        String attributes = "Ad_Type:" + Ad_type + ";" + "You_are:" + You_are + ";" + "Condition:" + condition + ";" +
                "Price:" + Price + ";" + "Furniture_Type:" + Furniture_Type + ";" + "WareHouse_Enabled:" + WareHouse_Enabled + ";";
        ;

        postAdRequest.put("title", "Automation " + Furniture_Type + " is for Sale");
        postAdRequest.put("description", "Automation " + Furniture_Type + " Sale" + " By " + You_are + " which is in " + condition + " condition");
        postAdRequest.put("location", "0");

        postAdRequest.put("attributes", attributes);

        images.add("i4_ak_753832050-1425986360.jpeg");
        postAdRequest.put("images", images);

        postAdRequest.put("usrprivacypostad", "false");
        postAdRequest.put("alertpostad", "true");
        postAdRequest.put("verifiedLead", "false");
        postAdRequest.put("serverName", "bangalore.quikr.com");
        postAdRequest.put("masterNickname", "null");
        postAdRequest.put("titleAutoGenerate", "false");
        postAdRequest.put("mandVerifyJobs", "false");
        postAdRequest.put("demail", "sfxgajiwtsmohit1gosqyikr@quikr.com");

        String jsonRequest = postAdRequest.toString();

        System.out.println(jsonRequest);

        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.53:7000/postAd", jsonRequest, "postAd"));

        int code = result.get("statusCode").asInt();
        System.out.println(result.toString());
        if (code / 100 != 2) {
            LOGGER.fatal("postAdHome method failed " + code);
            return errorMessage("PostAd for H&L has failed", code);
        }
        return result;

    }


    public String getOfferId(int adId) {
        JSONObject postShipJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JsonNode result = null;
        String Envr = null;
        String offerid = null;

        Envr = System.getProperty("DbEnv") == null ? getProperties().get("DbEnv") : System.getProperty("DbEnv");

        switch (Envr) {
            case "stage":    //.33 staging server
                result = parseResponse(getMethod("http://192.168.124.55:7878/api/v1/getMyOffersListForAdId?adId=" + adId, "offerid"));
                offerid = result.get("offerList").get("offerId").toString();
                System.out.println("Result from Stage == " + offerid);
            case "integration":    //.43 integration server
                result = parseResponse(getMethod("http://192.168.124.45:7878/api/v1/getMyOffersListForAdId?adId=" + adId, "offerid"));
                System.out.println("http://192.168.124.45:7878/api/v1/getMyOffersListForAdId?adId=" + adId);
                System.out.println(result.toString());
                result = result.get("offerList");
                offerid = result.get(0).get("offerId").toString();
                System.out.println("Result from Stage == " + offerid);
            default:
                Assert.assertTrue(false, "Env value not found in config.properties file");
        }
        int code = result.get("statusCode").asInt();
        if (code / 100 != 2) {
            LOGGER.fatal("updateOrderStatusPostShip failed " + code + result.get("errorMessage").asText());
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        return offerid;
    }

    private static String hmacSha1(String iString, String iSecretKey)
            throws SignatureException {
        String result;
        LOGGER.info(iString + iSecretKey);
        try {
            String aType = "HmacSHA1";

            SecretKeySpec aSecret = new SecretKeySpec(iSecretKey.getBytes(), aType);
            Mac aMac = Mac.getInstance(aType);
            aMac.init(aSecret);
            byte[] aBytes = aMac.doFinal(iString.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : aBytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            result = sb.toString();
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    public static String getCurrentDate() {
        Date aDate = new Date();
        SimpleDateFormat aTimeStampFormatter = new SimpleDateFormat(
                "yyyy-MM-dd");
        String aTimeStamp = aTimeStampFormatter.format(aDate);
        System.out.println(aTimeStamp);
        return aTimeStamp;

    }


    public String[] tokenGenerator() {
        JSONObject postShipJson = new JSONObject();
        JsonNode result = null;
        String aSignaturefirst = null;
        String Envr = null;
        String[] json = new String[10];
        Date date = new Date();
        String modDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        try {
            aSignaturefirst = hmacSha1("rajat.kachhara@quikr.com" + "722" + modDate, "6aa6d8e3e080739b034a69f2bd98bc40");
            System.out.println("First Signature==" + aSignaturefirst);
        } catch (Exception e) {

        }
        postShipJson.put("appId", APP_ID);
        postShipJson.put("signature", aSignaturefirst);

        String jsonRequest = postShipJson.toString();
        Envr = System.getProperty("DbEnv") == null ? getProperties().get("DbEnv") : System.getProperty("DbEnv");

        switch (Envr) {
            case "production":
                result = parseResponse(postMethodEscrow("https://api.quikr.com/app/auth/access_token", jsonRequest, "tokenid"));
                LOGGER.info(result.toString());
                json[0] = result.get("tokenId").toString();
                json[1] = result.get("token").toString();
                System.out.println( json[0]  +  json[1]);
                break;
            default:
                Assert.assertTrue(false, "Env value not found in config.properties file");
        }

       /* int code = result.get("statusCode").asInt();
        if (code / 100 != 2) {
            LOGGER.fatal("updateOrderStatusPostShip failed " + code + result.get("errorMessage").asText());
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }*/
        return json;
    }

    public String getOfferTokenId(String adId) {
        String tokenId = null;
        String aSignature2 = null;
        JsonNode result = null;
        String offerid = null;
        JSONObject postShipJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String[] tokenVal = new String[10];
        String Envr = null;
        String token = null;
        try {
            tokenVal = tokenGenerator();
            tokenId = tokenVal[0];
            token = tokenVal[1].replace("\"", "");
            LOGGER.info("TokenID== " + tokenId + "token= " + token);
            Date date = new Date();
            String modDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
             aSignature2 = hmacSha1("722" + "rajat.kachhara@quikr.com" + modDate, token);
            LOGGER.info("Signature2==" + aSignature2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String path = "https://api.quikr.com/api/v1/getMyOffersListForAdId?adId=" + adId;
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(path);

        // add request header
        request.addHeader("X-Quikr-Client", "DesktopSite");
        request.addHeader("X-Quikr-App-Id", APP_ID);
        request.addHeader("X-Quikr-Token-Id", tokenId);
        request.addHeader("X-Quikr-Signature-v2", aSignature2);
        HttpResponse response = null;
        try {
            response = client.execute(request);
            LOGGER.info(" Api executed");
        } catch (Exception e) {
            LOGGER.info("Api failed");
            e.printStackTrace();
        }
        result = parseResponse(response);
        System.out.println("https://api.quikr.com/api/v1/getMyOffersListForAdId?adId=" + adId);
        System.out.println(result.toString());
        int code = result.get("statusCode").asInt();
        result = result.get("offerList");
        offerid = result.get(0).get("offerId").toString();
        System.out.println("Result from Stage == " + offerid);

       /* if (code / 100 != 2) {
            LOGGER.fatal("updateOrderStatusPostShip failed " + code + result.get("errorMessage").asText());
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }*/

        return offerid;
    }


    public String createOfferProd(String adId) {

        String endpoint = "https://api.quikr.com/api/v1/createAnOffer";
        JSONObject json = new JSONObject();
        json.put("api_key", "CHAT_ANDROID");
        json.put("adid", adId);
        json.put("price", "40000");
        json.put("buyer_mobile", "9832789238");
        json.put("buyer_email", "shifterjobtwo@gmail.com");
        json.put("buyer_comment", "Automation Android");
        String jsonData = json.toString();
        JsonNode response = parseResponse(postMethodProd(endpoint, jsonData, "createOfferProd"));
        LOGGER.info("This is the response of Create offer api : "+response);
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("message").asText();
            LOGGER.fatal("createOfferProd failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code).asText();
        }
        return response.get("offer_id").asText();
    }
    public String getOfferStatusHistoryProd(String offerId) {

        String endpoint = "https://api.quikr.com/api/v1/offerStatusHistory?offer_id="+offerId;
        JsonNode response = parseResponse(getMethodProd(endpoint, "getOfferStatusHistoryProd"));
        LOGGER.info("This is the response of getOfferStatusHistoryProd api : "+response);
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("errorMessage").asText();
            LOGGER.fatal("createOfferProd failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code).asText();
        }
        return response.get("offerStatusHistory").get(0).get("status").asText();
    }
    public String updateOfferProd(String offerId,String status) {
        String statusCode;
        if(status.equalsIgnoreCase("accept")){
            statusCode="7";
        }
        else statusCode="8";

        String endpoint = "https://api.quikr.com/api/v1/updateOfferStatus";
        JSONObject json = new JSONObject();
        json.put("offer_id", offerId);
        json.put("status", statusCode);

        String jsonData = json.toString();
        JsonNode response = parseResponse(postMethodProd(endpoint, jsonData, "createOfferProd"));
        System.out.println("---------------------\n"+response.asText());
        int code = response.get("statusCode").asInt();
        if (code / 100 == 4) {
            String erroMessage = response.get("message").asText();
            LOGGER.fatal("createOfferProd failed with response code " + response.get("statusCode").toString());
            return errorMessage(erroMessage, code).asText();
        }
        return response.get("offer_id").asText();
    }


    public HttpResponse postMethodProd(String path, String jsonData, String apiName) {
        String tokenId = null;
        String aSignature2 = null;
        String[] tokenVal = new String[10];
        String token = null;
        try {
            tokenVal = tokenGenerator();
            tokenId = tokenVal[0];
            token = tokenVal[1].replace("\"", "");

            LOGGER.info("TokenID== " + tokenId + "token= " + token);
            Date date = new Date();
            String modDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            aSignature2 = hmacSha1("722" + "rajat.kachhara@quikr.com" + modDate, token);
            LOGGER.info("Signature2==" + aSignature2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        HttpPost post = new HttpPost(path);
        post.setHeader("X-Quikr-Client", "DesktopSite");
        post.setHeader("X-Quikr-App-Id", APP_ID);
        post.setHeader("X-Quikr-Token-Id", tokenId);
        post.setHeader("X-Quikr-Signature-v2", aSignature2);
        post.setHeader("Content-Type","application/json");

        HttpResponse response = null;

        try {
            post.setEntity(new StringEntity(jsonData));
            HttpClient client = HttpClientBuilder.create().build();
            response = client.execute(post);
        } catch (Exception e) {
            LOGGER.info(apiName + " failed");
            e.printStackTrace();
        }
        return response;
    }
    public HttpResponse getMethodProd(String path, String apiName) {
        String tokenId = null;
        String aSignature2 = null;
        String[] tokenVal = new String[10];
        String token = null;
        try {
            tokenVal = tokenGenerator();
            tokenId = tokenVal[0];
            token = tokenVal[1].replace("\"", "");

            LOGGER.info("TokenID== " + tokenId + "token= " + token);
            Date date = new Date();
            String modDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            aSignature2 = hmacSha1("722" + "rajat.kachhara@quikr.com" + modDate, token);
            LOGGER.info("Signature2==" + aSignature2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        HttpGet get = new HttpGet(path);
        get.setHeader("X-Quikr-Client", "DesktopSite");
        get.setHeader("X-Quikr-App-Id", APP_ID);
        get.setHeader("X-Quikr-Token-Id", tokenId);
        get.setHeader("X-Quikr-Signature-v2", aSignature2);
        get.setHeader("Content-Type","application/json");

        HttpResponse response = null;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            response = client.execute(get);
        } catch (Exception e) {
            LOGGER.info(apiName + " failed");
            e.printStackTrace();
        }
        return response;
    }

    /**
     * post Ad With Auction
     *
     * @param
     * @return
     */
    public JsonNode postAdEscrow(String email, String Mobile, String cityId, String Amount, String loggedInId) {

        JSONObject postAdRequest = new JSONObject();

        JSONObject auction = new JSONObject();
        LinkedList<String> images = new LinkedList<>();
        String entityTypeId = "101";
        String duration = "7";
        String auctionId = "";
        String event = "save";
        String minPrice = "8000";

        auction.put("entityTypeId", entityTypeId);
        auction.put("duration", duration);
        auction.put("auctionId", auctionId);
        auction.put("event", event);
        auction.put("minPrice", minPrice);


        postAdRequest.put("adExtras", auction);
        postAdRequest.put("source", "app");
        postAdRequest.put("email", email);
        postAdRequest.put("userMobile", Mobile);
        postAdRequest.put("loggedin", "false");
        postAdRequest.put("loggedInUserId", loggedInId);
        postAdRequest.put("isGroupUser", "false");
        postAdRequest.put("groupName", "");
        postAdRequest.put("httpReferrer", "http://bangalore.quikr.com/post-classifieds-ads/?postadcategoryid=227");
        postAdRequest.put("referrer", "http://bangalore.quikr.com/post-classifieds-ads/?postadcategoryid=227");
        postAdRequest.put("remoteAddr", "192.168.2.127");
        postAdRequest.put("userAgent", "Mozilla/5.0 (Windows NT 6.1; rv:34.0) Gecko/20100101 Firefox/34.0 AlexaToolbar/alxf-2.21");
        postAdRequest.put("cityId", cityId);
        switch (cityId) {

            case "23"://Bangalore
                postAdRequest.put("subcatid", "227");
                break;
            case "31"://Mumbai
                postAdRequest.put("subcatid", "1043");
                break;
            case "25": //Chennai
                postAdRequest.put("subcatid", "431");
                break;
            case "33": //Pune
                postAdRequest.put("subcatid", "1247");
                break;
        }

        postAdRequest.put("location", "Koramangala");

        String Ad_type = "offer";
        String You_are = "Individual";
        String Condition = "Used";
        String Price = Amount;
        String Brand_name = "Apple";
        String Auction_enabled="1";
        String No_of_Sims = "Dual";
        String adStyle="B";
        String auctionAdStyle = "B";
        String attributes = "Ad_Type:offer;You_are:Individual;Condition:Used;Price:13000;Brand_name:Apple;No_of_Sims:Dual;You_are:Individual;Equipment_Type:Treadmill;Locality:xyz;Title:5 month old mobile for sale;Description:5 year old mobile for sale 5 year old mobile for sale 5 year old mobile for sale;Invoice:;CFGroupId:;CFGroupName:;Ad_Type:offer;auction_enabled:1;C2C_Enabled:1;Storemore_Barcode:,adStyle:B;auctionAdStyle:B";
        postAdRequest.put("attributes", attributes);
        images.add("i4_ak_753832050-1425986360.jpeg");
        postAdRequest.put("images", images);
        postAdRequest.put("title", "5 year old mobile for sale");
        postAdRequest.put("description", "5 year old mobile for sale 5 year old mobile for sale 5 year old mobile for sale");
        postAdRequest.put("usrprivacypostad", "false");
        postAdRequest.put("alertpostad", "true");
        postAdRequest.put("verifiedLead", "false");
        postAdRequest.put("serverName", "bangalore.quikr.com");
        postAdRequest.put("masterNickname", "null");
        postAdRequest.put("titleAutoGenerate", "false");
        postAdRequest.put("mandVerifyJobs", "false");
        postAdRequest.put("demail", "sfxgajiwtsmohit1gosqyikr@quikr.com");

        String jsonRequest = postAdRequest.toString();
        System.out.println("JSON========="+jsonRequest);
        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.53:7000/platform/v2/postAd", jsonRequest, "postAd"));
       /* int code = result.get("statusCode").asInt();

        if (code / 100 != 2) {
            LOGGER.fatal("postAd method failed " + code);
            return errorMessage("PostAd failed", code);
        }*/
        return result;

    }


    /**
     * post Ad With Auction
     *
     * @param
     * @return
     */
    public JsonNode submitBid(String price, String auctionID, String userId, String buyerCityId, String buyerEmail,String mobile) {

        JSONObject bid = new JSONObject();

        bid.put("offeredPrice", price);
        bid.put("auctionId", auctionID);
        bid.put("userId", userId);
        bid.put("buyerCityId", buyerCityId);
        bid.put("buyerEmail", buyerEmail);
        bid.put("buyerMobile", mobile);

        String jsonRequest = bid.toString();
        System.out.println("JSON========="+jsonRequest);
        JsonNode result = parseResponse(postMethodEscrow("http://192.168.124.55:7878/escrow/v1/createBid", jsonRequest, "bid"));
        if(result.get("status").toString().contains("ERROR"))
            return errorMessage(result.get("message").toString(), 500);
        return result;
    }

    public JsonNode getCompleteAdById(String adId) {
        System.out.println("http://192.168.124.53:7000/getCompleteAdById?adId="+adId);
        JsonNode result = parseResponse(getMethod("http://192.168.124.53:7000/getCompleteAdById?adId="+adId,"getAdById"));
        return result;
    }

    public int dateDifference(String s1,String s2){

        SimpleDateFormat format =new SimpleDateFormat("yyyy/mm/dd");
        Date d1=null;
        Date d2=null;
        try {
            d1=format.parse(s1);
            d2=format.parse(s2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

    }
}
