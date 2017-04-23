package com.quikr.website.escrow.oms;

import com.quikr.website.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by gurinder.singh on 10/12/15.
 */
public class OmsTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("OMS_TESTDATA_FILE"));

    @BeforeMethod()
    public void openUrl()
    {
        new OmsPage(driver).openOMSUrl();
    }

   @Test
   public void paymentModeTest()
   {
       OmsPage OmsPage = new OmsPage(driver);
       OmsPage.login(testData.get("username"),testData.get("password"));
       OmsPage.gotoPaymentMode();
       OmsPage.searchByPaymentMode("online");
       Assert.assertTrue(OmsPage.dataPresentInPaymentModeTable(),"Could not find data on OMS Console");
   }

    @Test
    public void orderStatus()
    {
        OmsPage OmsPage = new OmsPage(driver);
        OmsPage.login(testData.get("username"),testData.get("password"));
        OmsPage.gotoOrderStatus();
        OmsPage.searchByOrderStatus("online_Payment_Success");
        Assert.assertTrue(OmsPage.dataPresentInPaymentModeTable(),"Could not find data on OMS Console");
    }
    @Test
    public void navigateThrough()
    {
        String buyerEmail="web1@mail.quikrtest.com";
        String buyerMobile="9997747347";

        String sellerEmail="web1@mail.quikrtest.com";
        String sellerMobile="9997747347";

        OmsPage OmsPage = new OmsPage(driver);
        OmsPage.login(testData.get("username"),testData.get("password"));
//Navigating through Order Status
        OmsPage.gotoOrderStatus();
        OmsPage.searchByOrderStatus("seller_rejected_offer");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("Seller_Accepted_Offer");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("Seller_Countered_the_offer");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("buyer_paid_on_seller_accept_offer");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("buyer_paid_on_seller_counter_offer");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("Buyer_withdraw_offer");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("Buyer_accepted_on_Counter_Offer");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("online_Payment_Failed");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("online_Payment_Success");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByOrderStatus("Scheduled_for_COD");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");

//Navigate through Created Date
        OmsPage.gotoCreatedDate();
        OmsPage.searchByCreatedDate(OmsPage.yesterdaysDate(),OmsPage.todaysDate());
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
//Navigate through Buyer Details
        OmsPage.gotoBuyerDetailsByEmail();
        OmsPage.searchByBuyerEmail(buyerEmail);
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");

        OmsPage.gotoBuyerDetailsByContactNumber();
        OmsPage.searchByBuyerMobile(buyerMobile);
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
//Navigate through Seller Details
        OmsPage.gotoSellerDetailsByEmail();
        OmsPage.searchBySellerEmail(sellerEmail);
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");

        OmsPage.gotoSellerDetailsByContactNumber();
        OmsPage.searchBySellerMobile(sellerMobile);
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");

//Navigate through Payment Mode
        OmsPage.gotoPaymentMode();
        OmsPage.searchByPaymentMode("NotAvailable");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByPaymentMode("online");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
        OmsPage.searchByPaymentMode("COD");
        Assert.assertTrue(OmsPage.searchComplete(),"Script moved on before Search could complete");
    }

}
