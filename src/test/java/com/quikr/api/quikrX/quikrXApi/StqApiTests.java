package com.quikr.api.quikrX.quikrXApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXApiTestBase;
import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Created by francis.s@quikr on 12/5/16.
 */
public class StqApiTests extends QuikrXApiTestBase{


    @Test
    public void stqLeadAPi(){

        QuikrXApiBase apiBase = new QuikrXApiBase();

        JsonNode response = apiBase.createStqLead("monetizationweb1@mail.quikrtest.com",9986582147l);
        Assert.assertNotNull(response.get("createSTQLeadResponse").get("success").get("leadId").asText(),"create stq lead api failed");

    }


}
