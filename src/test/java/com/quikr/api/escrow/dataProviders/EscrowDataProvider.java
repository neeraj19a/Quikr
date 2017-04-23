package com.quikr.api.escrow.dataProviders;

import com.quikr.api.escrow.EscrowEnumData;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.DataProvider;

/**
 * Created by quikr on 20/12/15.
 */
public class EscrowDataProvider {
//   static EscrowApiBase ApiBase = new EscrowApiBase();
    static String adId = RandomStringUtils.randomNumeric(9);
    static String transactionId = RandomStringUtils.randomNumeric(10);
    static String offerid = RandomStringUtils.randomNumeric(12);
    static String WaybillNo = RandomStringUtils.randomNumeric(7);

    @DataProvider(name = "endToendShipping")
    public static Object[][] endToendShippingMethods() {
        return new Object[][]{
//              {adId, paymentMode, transactionId, Amount, offerId, WaybillNo, overallStatus,schedulingStatus,itemDirection
//                {ApiBase.getId("AdIdEscrow"), "0", ApiBase.getId("TransactionIdEscrow"),"1200",ApiBase.getId("OfferIdEscrow"), ApiBase.getId("WaybillNoEscrow"),"Delivered","to_be_scheduled","to_buyer"}
//                {RandomStringUtils.randomNumeric(9), "0", RandomStringUtils.randomNumeric(10),"1200",RandomStringUtils.randomNumeric(12),RandomStringUtils.randomNumeric(7),"Delivered","to_be_scheduled","to_buyer"}
                {adId, "0",transactionId,"1200",offerid,WaybillNo,"PENDING","TO_BE_SCHEDULED","to_buyer"},
                {adId, "0",transactionId,"1200",offerid,WaybillNo,"CANCELLED","TENTATIVE_SCHEDULED","TO_SELLER"},
                {adId, "0",transactionId,"1200",offerid,WaybillNo,"RETURNED","CONFIRMED_SCHEDULED","TO_SELLER"},
                {adId, "0",transactionId,"1200",offerid,WaybillNo,"DELIVERED","CONFIRMED_SCHEDULED","to_buyer"}
        };
    }

    @DataProvider(name = "postWarehouseAdData")
    public static Object[][] postWarehouseAdData(){
        return new Object[][]{
                {"H&L","web9@mail.quikrtest.com", "9090909090","5500","23","1","9090909010","web10@mail.quikrtest.com",
                        "4500","pre-paid",EscrowEnumData.overall_status.DELIVERED,EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED,EscrowEnumData.item_direction.To_Buyer},
                {"E&A","web9@mail.quikrtest.com", "9090909090","5500","23","1","9090909010","web10@mail.quikrtest.com",
                        "4500","pre-paid",EscrowEnumData.overall_status.DELIVERED,EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED,EscrowEnumData.item_direction.To_Buyer},
                {"H&L","web9@mail.quikrtest.com", "9090909090","5500","31","1","9090909010","web10@mail.quikrtest.com",
                        "4500","pre-paid",EscrowEnumData.overall_status.DELIVERED,EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED,EscrowEnumData.item_direction.To_Buyer},
                {"E&A","web9@mail.quikrtest.com", "9090909090","5500","31","1","9090909010","web10@mail.quikrtest.com",
                        "4500","pre-paid",EscrowEnumData.overall_status.DELIVERED,EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED,EscrowEnumData.item_direction.To_Buyer}
        };
    }
    @DataProvider(name = "postAdMAODataData")
    public static Object[][] postAdMAOData(){
        return new Object[][]{
                {"Mobile","web11@mail.quikrtest.com","9089098789","4000","23","0","web10@mail.quikrtest.com","8990899089","3000","pre-paid",
                        EscrowEnumData.overall_status.DELIVERED,EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED,EscrowEnumData.item_direction.To_Buyer},
//                {"E&A","web9@mail.quikrtest.com","9089098789","4000","23","0","web10@mail.quikrtest.com","8990899089","3000","pre-paid",
  //                      EscrowEnumData.overall_status.CANCELLED,EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED,EscrowEnumData.item_direction.To_Seller},
    //            {"H&L","web9@mail.quikrtest.com","9089098789","4000","23","0","web10@mail.quikrtest.com","8990899089","3000","pre-paid",
      //                  EscrowEnumData.overall_status.DELIVERED,EscrowEnumData.scheduling_status.CONFIRMED_SCHEDULED,EscrowEnumData.item_direction.To_Buyer}
        };
    }

}
