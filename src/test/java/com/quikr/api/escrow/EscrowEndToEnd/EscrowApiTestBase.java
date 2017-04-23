package com.quikr.api.escrow.EscrowEndToEnd;

import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.api.escrow.EscrowEnumData;
import com.quikr.utils.Database;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by gurinder.singh on 11/12/15.
 */
public class EscrowApiTestBase {

    private final static Log LOGGER = LogFactory.getLog(EscrowApiTestBase.class.getName());
    protected EscrowApiBase escrowApiBase = new EscrowApiBase();
    Database db = new Database();
    ArrayList<HashMap> row = new ArrayList<>();

    public Map<String,String> shipping(String AdId, String Status){


        String quoteId=null;
        Map<String,String> map = new HashMap();

//        String custId = escrowApiBase.preShip(AdId).toString();
//        LOGGER.info("custId "+custId);

        return  map;

    }

    public Map<String,String> postAdEndToEnd(String category,String sEmail,String sMobile,String postedPrice,String cityId,String isWarehouse,String bEmail,
                                                        String bMobile,String offeredPrice,String paymentMode,EscrowEnumData.overall_status overall_status,
                                             EscrowEnumData.scheduling_status scheduling_status, EscrowEnumData.item_direction item_direction){

        Map<String,String> map = new HashMap();
        String adId = null;

        switch (category){

            case "Mobile":
                adId = escrowApiBase.postAd(sEmail, sMobile, cityId, postedPrice, isWarehouse).get("Ad").get("id").asText();
                break;
            case "E&A":
                adId = escrowApiBase.postAdElectronics(sEmail,sMobile,cityId,postedPrice,isWarehouse).get("Ad").get("id").asText();
                break;
            case "H&L":
                adId = escrowApiBase.postAdHome(sEmail, sMobile, cityId, postedPrice, isWarehouse).get("Ad").get("id").asText();
                break;
        }

        LOGGER.info(category);
        map.put("adId", adId);
        LOGGER.info("Ad Id:" + adId);
        escrowApiBase.reviewAd(adId);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String offerId = escrowApiBase.makeAnOffer(adId,offeredPrice,bMobile,bEmail).get("offer_id").asText();
        map.put("offerId", offerId);
        LOGGER.info("Offer Id:" + offerId);
        escrowApiBase.acceptOffer(offerId);

        String transactionId = escrowApiBase.createPaymentRecord(offerId,Integer.parseInt(offeredPrice),bEmail,paymentMode).get("TransactionId").asText();
        map.put("transactionId", transactionId);
        LOGGER.info("Transaction Id:" + transactionId);
        escrowApiBase.updatePaymentRecord(transactionId);
        map.put("adProperties", isWarehouse);

        row = db.getResultQuery("escrow_c2c", "select * from orders_initiated_for_pickup_and_delivery where offer_id =" + map.get("offerId").toString(),adId);
        map.put("awb", row.get(0).get("waybillno").toString());
        LOGGER.info("Waybill No: " + row.get(0).get("waybillno"));
        if(row.get(0).get("json_sent")!=null){
            map.put("preshipJSON",row.get(0).get("json_sent").toString());
            escrowApiBase.updateOrderStatusPostShip(adId, offerId, transactionId, overall_status, paymentMode, scheduling_status, item_direction.To_Buyer, map.get("awb"));
        }else{
            map.put("preshipJSON",null);
        }
        return map;
    }


    public Map<String,String> postAdWarehouseEndToEnd(String sEmail,String bEmail,String sMobile,String bMobile,String postedPrice,
                                             String offeredPrice,String cityId,String isWarehouse,String paymentMode,
                                                      EscrowEnumData.overall_status overall_status,EscrowEnumData.scheduling_status scheduling_status,
                                                      EscrowEnumData.item_direction item_direction){

        Map<String,String> map = new HashMap();

        String adId = escrowApiBase.postAd(sEmail,sMobile,cityId,postedPrice,isWarehouse).get("Ad").get("id").asText();
        map.put("adId", adId);
        LOGGER.info("Ad Id:" + adId);
        System.out.println(adId);

        escrowApiBase.reviewAd(adId);

        escrowApiBase.createWarehouseOrder(sEmail, sMobile, cityId, adId);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        escrowApiBase.updateOrderStatusPostShip(adId, adId, "", overall_status, paymentMode, scheduling_status, item_direction, "awb");

        String offerId = escrowApiBase.makeAnOffer(adId,offeredPrice,bMobile,bEmail).get("offer_id").asText();
        map.put("offerId", offerId);
        LOGGER.info("Offer Id:" + offerId);
        System.out.println(offerId);

        escrowApiBase.acceptOffer(offerId);

        String transactionId = escrowApiBase.createPaymentRecord(offerId,Integer.parseInt(offeredPrice),bEmail,paymentMode).get("TransactionId").asText();
        map.put("transactionId", transactionId);
        LOGGER.info("Transaction Id:" + transactionId);
        System.out.println(transactionId);

        return map;

    }

    public Map<String,String> postWarehouseAd(String category,String sEmail, String sMobile, String postedPrice, String cityId,String isWarehouse,
                                EscrowEnumData.overall_status overall_status, EscrowEnumData.scheduling_status scheduling_status,
                                EscrowEnumData.item_direction item_direction){

        Map<String,String> map = new HashMap();
        db = new Database();
        db.initializeDbDomain();
        String adId = null;

        switch (category){

            case "E&A":
                adId = escrowApiBase.postAdElectronics(sEmail,sMobile,cityId,postedPrice,isWarehouse).get("Ad").get("id").asText();
                break;
            case "H&L":
                adId = escrowApiBase.postAdHome(sEmail, sMobile, cityId, postedPrice, isWarehouse).get("Ad").get("id").asText();
                break;
        }

        LOGGER.info("Post Ad successful");

        map.put("adId", adId);

        escrowApiBase.createWarehouseOrder(sEmail, sMobile, cityId, adId);

        LOGGER.info("Create warehouse executed");

        List<HashMap> row = db.getResultQuery("escrow_c2c","SELECT * FROM `ad_status_history` WHERE adid ="+adId,adId);

        String awb = row.get(0).get("comment").toString();

        map.put("awb", awb);

        escrowApiBase.updateOrderStatusPostShip(adId, "", "", overall_status, "pre-paid", scheduling_status, item_direction, awb);

        LOGGER.info("Postship for warehouse executed");

        escrowApiBase.reviewAd(adId);

        LOGGER.info("Review Ad executed");
        return map;

    }

    public Map<String,String> smokeEnvironment(){

        Map<String,String> map = new HashMap();
        String postAdStatus = escrowApiBase.postAd("","","","","").get("statusCode").asText();
        String escrowApiStatus = escrowApiBase.makeAnOffer("","","","").get("statusCode").asText();
        map.put("PlatformAPI",postAdStatus);
        map.put("EscrowAPI",escrowApiStatus);
        return map;
    }


}
