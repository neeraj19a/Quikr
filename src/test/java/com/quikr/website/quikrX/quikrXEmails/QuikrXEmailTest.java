package com.quikr.website.quikrX.quikrXEmails;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXApiTestBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.api.quikrX.dataProviders.QuikrXDataProvider;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.roundCubeEmail.RoundCubeEmailPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by quikr on 12/5/16.
 */
public class QuikrXEmailTest extends QuikrXTestBase{



    @Test
    public void createStqLeadMails (){

        RoundCubeEmailPage emailPage = new RoundCubeEmailPage(driver);
        QuikrXApiBase apiBase = new QuikrXApiBase();

        apiBase.createStqLead("monetizationweb1@mail.quikrtest.com",9632587412l);


        checkNewEmail("monetizationweb1@mail.quikrtest.com",2,"Sell your phone faster. At no additional cost!");
        Assert.assertEquals(emailPage.getFirstEmailTitle(),"Sell your phone faster. At no additional cost!");
        Assert.assertEquals(emailPage.getFirstEmailFromName(),"QuikrX","sender details incorrect");
        emailPage.openFirstEmail();

    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "endToendFlows")
    public void placeOrderMyOrderValidationTest(String sellerName, String itemId, String email,String category,String exchangeProd){


        QuikrXApiBase quikrXApiBase = new QuikrXApiBase();
        QuikrXApiTestBase testBase = new QuikrXApiTestBase();
        RoundCubeEmailPage emailPage = new RoundCubeEmailPage(driver);

        Map<String,String> map = testBase.placeOrder(sellerName,itemId,email,category, QuikrXEnumData.paymentMehtod.CREDIT_CARD,exchangeProd);
        map.putAll(testBase.getStatusFromMyorder(quikrXApiBase.myOrders("emailId", email, QuikrXEnumData.myordersStatus.ACTIVE)));
        Assert.assertEquals(map.get("status"),QuikrXEnumData.subOrderStatus.PENDING.toString(),"Mismatch in status");
        quikrXApiBase.updateQuoteItemStatus(map.get("orderId"), QuikrXEnumData.subOrderStatus.CALL_CONFIRMED);
        checkNewEmail("monetizationweb1@mail.quikrtest.com",2,"Your Order (Order ID ="+map.get("orderId")+" ) has been placed on Quikr");
        Assert.assertEquals(emailPage.getFirstEmailTitle(),"Your Order (Order ID ="+map.get("orderId")+" ) has been placed on Quikr");
        Assert.assertEquals(emailPage.getFirstEmailFromName(),"Quikr Service","sender details incorrect");
        emailPage.openFirstEmail();

    }



}
