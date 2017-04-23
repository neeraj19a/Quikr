package com.quikr.website.horizontal.chat;

import com.quikr.website.TestBase;

import java.util.HashMap;

/**
 * Created by akhil.singla on 28/1/16.
 */
public class ChatTestBase extends TestBase
{
    String adId = null;
    String adUserName = null;
    String city = null;
    HashMap<String, String> logCred = getLoginCredentials();
    String username = logCred.get("username");
    String password = logCred.get("password");

    public void adCreation(String city)
    {
        String adDetails = createTestEscrowAd(driver, adId, adUserName,city);
        adId = adDetails.split(" ")[0];
        adUserName = adDetails.split(" ")[1];
    }
}
