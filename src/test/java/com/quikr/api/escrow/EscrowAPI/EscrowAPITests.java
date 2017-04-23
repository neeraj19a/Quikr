package com.quikr.api.escrow.EscrowAPI;

import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.api.escrow.EscrowEndToEnd.EscrowApiTestBase;
import com.quikr.api.escrow.EscrowEnumData;
import com.quikr.api.escrow.dataProviders.EscrowDataProvider;
import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.utils.Database;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by quikr on 31/1/16.
 */
public class EscrowAPITests extends EscrowApiTestBase{

    private final static Log LOGGER = LogFactory.getLog(QuikrXApiBase.class.getName());
    EscrowApiBase base = new EscrowApiBase();
    EscrowApiTestBase testBase = new EscrowApiTestBase();
    Map ExpectedAdResult = new HashMap();
    Map smokeTestResult = new HashMap();

    ArrayList<HashMap> dbAdResult = new ArrayList();
    ArrayList<HashMap> dbOfferResult = new ArrayList();
    ArrayList<HashMap> dbPaymentResult = new ArrayList();
    ArrayList<HashMap> dbPostshipResult = new ArrayList();



    Database db = new Database();


    @BeforeClass
    public void smokeCheckTest(){

        smokeTestResult = testBase.smokeEnvironment();
        Assert.assertNotEquals(smokeTestResult.get("PlatformAPI"),"500", "PlatformAPI Server is down");
        LOGGER.info(smokeTestResult.get("PlatformAPI"));
        Assert.assertNotEquals(smokeTestResult.get("EscrowAPI"), "500", "EscrowAPI Server is down");
        LOGGER.info(smokeTestResult.get("EscrowAPI"));
    }



    @Test(dataProviderClass = EscrowDataProvider.class, dataProvider = "postAdMAODataData")
    public void postAdMAOEndToEndTest(String category,String sEmail,String sMobile,String sPrice,String cityId,String isWarehouseEnabled,
                                              String bEmail,String bMobile,String bPrice,String paymentMode,EscrowEnumData.overall_status overall_status,
                                      EscrowEnumData.scheduling_status scheduling_status,EscrowEnumData.item_direction item_direction){

        LOGGER.info("executing post ad flow");
        ExpectedAdResult = testBase.postAdEndToEnd(category, sEmail, sMobile, sPrice, cityId, isWarehouseEnabled,
                bEmail, bMobile, bPrice, paymentMode, overall_status, scheduling_status, item_direction);

        dbAdResult = db.getResultQuery("escrow_c2c", "Select * from ad_details where adid = " + ExpectedAdResult.get("adId").toString(),"");
        Assert.assertEquals(dbAdResult.get(0).get("adid").toString(), ExpectedAdResult.get("adId"), "Ad id do not match");
        Assert.assertEquals(dbAdResult.get(0).get("ad_properties").toString(), "0", "Ad property is not 0");

        Assert.assertEquals(dbAdResult.get(0).get("escrow_status").toString(), "1", "escrow status is not 1");
        Assert.assertEquals(dbAdResult.get(0).get("warehouse_contact_id").toString(), "0", "Warehouse contact id is not zero");
        Assert.assertEquals(((BigDecimal)dbAdResult.get(0).get("listing_price")).intValue(),Integer.parseInt(sPrice),"Ad price do not match");
        db = new Database();

        dbOfferResult = db.getResultQuery("escrow_c2c","Select * from escrow_offer where id = "+ExpectedAdResult.get("offerId").toString(),"");
        Assert.assertEquals(((BigDecimal) dbOfferResult.get(0).get("offered_price")).intValue(), Integer.parseInt(bPrice), "Offered price do not match");
        Assert.assertEquals(((BigDecimal) dbOfferResult.get(0).get("final_price")).intValue(), Integer.parseInt(bPrice), "Final price do not match");
        Assert.assertEquals(dbOfferResult.get(0).get("offer_properties").toString(),"0","Offer Payment is not online");
        Assert.assertEquals(dbOfferResult.get(0).get("status").toString(), "9", "Offer status does not match");
        db = new Database();
        dbPaymentResult = db.getResultQuery("escrow_c2c","Select * from escrow_payments where id = "+ExpectedAdResult.get("transactionId").toString(),"");
        Assert.assertEquals(dbPaymentResult.get(0).get("status").toString(),"1", "Escrow payment status do not match");
        Assert.assertEquals(dbPaymentResult.get(0).get("shmart_status_code").toString(), "0", "shmart status code do not match");

        int amount = Integer.parseInt(bPrice)*100;
        Assert.assertEquals(Integer.parseInt(dbPaymentResult.get(0).get("amount").toString()), amount, "Amount do not match");

        if(ExpectedAdResult.get("preshipJSON")!=null){
            dbPostshipResult = db.getResultQuery("escrow_c2c","select * from order_fulfillment_details where client_ref_no = 200000032146","");//+ExpectedAdResult.get("offerId").toString());
            Assert.assertNotNull(dbPostshipResult.get(0));

            Assert.assertEquals(dbPostshipResult.get(0).get("overall_status").toString(), overall_status.name(), "Overall status mismatch");
            Assert.assertEquals(dbPostshipResult.get(0).get("awb_no").toString(), ExpectedAdResult.get("awb").toString(), "Awb number mismatch for postship");
        }else{
            LOGGER.info("pre-ship has Failed");
        }
       LOGGER.info("postAdMAOTest Executed successfully");
    }


    @Test(dataProviderClass = EscrowDataProvider.class, dataProvider = "postWarehouseAdData")
    public void postWarehouseAdTest(String category,String sEmail, String sMobile,String postedPrice,String cityId,String isWarehouse,
                                    String bMobile,String bMail,String offeredPrice,String paymentMode,
                                    EscrowEnumData.overall_status overall_status,EscrowEnumData.scheduling_status scheduling_status,EscrowEnumData.item_direction item_direction){

        LOGGER.info("Creating Warehouse Ad");

        db = new Database();

        ExpectedAdResult = testBase.postWarehouseAd(category,sEmail,sMobile,postedPrice,cityId,isWarehouse,overall_status,scheduling_status,item_direction);

        LOGGER.info(ExpectedAdResult.get("adId").toString());
        LOGGER.info(ExpectedAdResult.get("awb").toString());

        dbAdResult = db.getResultQuery("escrow_c2c","Select * from ad_details where adid ="+ ExpectedAdResult.get("adId").toString(),"");

        Assert.assertEquals(dbAdResult.get(0).get("adid").toString(),ExpectedAdResult.get("adId").toString(), "Ad id do not match!!");

        //Assert.assertEquals(dbAdResult.get(0).get("ad_status").toString(), "50", "Ad status is incorrect!");

        Assert.assertEquals(dbAdResult.get(0).get("ad_properties").toString(), "1", "Ad property is not Warehouse");

        base.makeAnOffer(dbAdResult.get(0).get("adid").toString(),offeredPrice,bMobile,bMail);



    }
}
