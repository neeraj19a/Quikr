package com.quikr.api.escrow.EscrowEndToEnd;

import com.fasterxml.jackson.databind.JsonNode;
import com.quikr.api.escrow.EscrowApiBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

/**
 * Created by quikr on 20/12/15.
 */
public class EscrowShippingEndToEnd {

    EscrowApiBase apiBase = new EscrowApiBase();
    private final Log LOGGER = LogFactory.getLog(EscrowShippingEndToEnd.class.getName());

   /** @Test(dataProviderClass = EscrowDataProvider.class, dataProvider = "endToendShipping")
    public void shippingStatusTest(String adId,String paymentMode,String transactionId,String Amount,String offerId,String WaybillNo, String overallStatus, String schedulingStatus, String itemDirection){

        LOGGER.info("Test Pre & Post Delivery");

        LOGGER.info("\nAdId : "+adId+"\nofferId : "+offerId+"\nTransactionId : "+transactionId+"\nWaybill : "+WaybillNo);

        apiBase.preShip(adId,paymentMode,transactionId,Amount,offerId,WaybillNo);
        apiBase.updateOrderStatusPostShip(adId,offerId,transactionId,overallStatus,paymentMode, schedulingStatus,itemDirection,WaybillNo);
        LOGGER.info("Test executed with overall Status -- "+ overallStatus);

    }*/
    @Test
    public void postAdTest(){

        LOGGER.info("Post an Ad");
        JsonNode response = apiBase.postAd("web15@mail.quikrtest.com", "9739698490", "23","3000","0");
        LOGGER.info("Ad id-" + response.get("Ad").get("id"));
        System.out.println(response.get("Ad").get("id").toString());

        apiBase.reviewAd(response.get("Ad").get("id").toString());
        System.out.println("Review Ad executed");
        String offerId = apiBase.makeAnOffer(response.get("Ad").get("id").toString(), "4000", "8080808080", "web10@mail.quikrtest.com").get("offer_id").asText();
        System.out.println(offerId);
        apiBase.acceptOffer(offerId);
        String TransactionId = apiBase.createPaymentRecord(offerId,4000,"web10@mail.quikrtest.com","pre-paid").get("TransactionId").asText();
        System.out.println(TransactionId);
        apiBase.updatePaymentRecord(TransactionId);


    }

    @Test
    public void makeAnOfferTest(){

        String offerId = apiBase.makeAnOffer("1408357034","4000","8080808080","web10@mail.quikrtest.com").get("offer_id").asText();
        apiBase.acceptOffer(offerId);




    }

    @Test
    public void acceptOfferTest(){

        String price = "5000";

        JsonNode response = apiBase.createPaymentRecord("200000030731", Integer.parseInt(price), "web11@mail.quikrtest.com", "pre-paid");
        System.out.println(response.get("TransactionId").asText());
        apiBase.updatePaymentRecord(response.get("TransactionId").asText());
    }


}
