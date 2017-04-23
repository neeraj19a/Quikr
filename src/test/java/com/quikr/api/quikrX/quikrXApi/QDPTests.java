package com.quikr.api.quikrX.quikrXApi;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXApiTestBase;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by quikr on 5/4/16.
 */
public class QDPTests extends QuikrXApiTestBase{


    private final static Log LOGGER = LogFactory.getLog(QDPTests.class.getName());


    @Test
    public void addProductQdpTest(){

        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        String sessionId = quikrXApi.createSession();
        String result = quikrXApi.addProductCertified(sessionId,"1000","1s213","").get("errorMessage").asText().trim();
        Assert.assertEquals(result,"Internal Server Error Occurred");
    }

    @Test
    public void addExchangeProductQdpTest(){

        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        String sessionId = quikrXApi.createSession();
        String result = quikrXApi.addProductExchange(sessionId,"1000","1s213","","").get("errorMessage").asText().trim();
        Assert.assertEquals(result,"Internal Server Error Occurred");
    }

    @Test
    public void addAddressQdpTest(){

        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        String sessionId = quikrXApi.createSession();
        String customerId = quikrXApi.addEmailToSession(sessionId,"testuser"+ RandomStringUtils.randomAlphanumeric(5)+"@testdomain.com").get("customerId").asText();
        String quoteId = quikrXApi.addProductCertified(sessionId,"1000","1125","").get("AddQuikrXProductResponse").get("success").get("quote").get("id").asText();
        String result = quikrXApi.addAddressQuoteId("",customerId).get("errorMessage").asText().trim();
        Assert.assertEquals(result,"Quote id is mandatory");

    }



}
