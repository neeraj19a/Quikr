package com.quikr.api.escrow;

/**
 * Created by quikr on 2/2/16.
 */
public class EscrowEnumData {

    public enum overall_status{

        PENDING("PENDING"),
        DELIVERED("DELIVERED"),
        CANCELLED("CANCELLED"),
        RETURNED("RETURNED");
        private String val;
        overall_status(String val){
            this.val=val;
        }
    }

    public enum scheduling_status{

        CONFIRMED_SCHEDULED("CONFIRMED_SCHEDULED"),
        TENTATIVE_SCHEDULED("TENTATIVE_SCHEDULED"),
        TO_BE_SCHEDULED("TO_BE_SCHEDULED");
        private String val;
        scheduling_status(String val){
            this.val = val;
        }
    }

    public enum item_status{
        TO_BE_PICKED("TO_BE_PICKED"),
        IN_TRANSIT("IN_TRANSIT"),
        STORAGE("STORAGE"),
        DELIVERED("DELIVERED"),
        CANCELLED("CANCELLED"),
        RETURNED("RETURNED"),
        In_Feed("In_Feed");
        private String val;
        item_status(String val){
            this.val = val;
        }
    }

    public enum item_direction{

        To_Seller("To_Seller"),
        To_Buyer("To_Buyer");
        private String val;
        item_direction(String val){
            this.val = val;
        }
    }





}
