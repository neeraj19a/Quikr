package com.quikr.api.quikrX;

/**
 * Created by quikr on 12/11/15.
 */
public class QuikrXEnumData {

   public enum subOrderStatus{

       PENDING,
       CALL_CONFIRMED,
       DELIVERED,
       CUSTOMER_DECLINED,
       DISPATCHED,
       DELIVERED_TO_AM
   }

    public enum inventoryUpdateType{

        ABSOLUTE,
        INCREMENTAL,
        DECREMENTAL
    }

    public enum myordersStatus{

        ACTIVE,
        CANCELLED,
        DELIVERED

    }

    public enum paymentMehtod{

        COD,
        PREPAID,
        DEBIT_CARD,
        CREDIT_CARD,
        NETBANKING
    }


}
