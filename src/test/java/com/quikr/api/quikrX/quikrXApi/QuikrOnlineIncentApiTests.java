package com.quikr.api.quikrX.quikrXApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.quikr.api.quikrX.QuikrXApiTestBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.api.quikrX.dataProviders.QuikrXDataProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by quikr on 19/2/16.
 */
public class QuikrOnlineIncentApiTests extends QuikrXApiTestBase{


    private final static Log LOGGER = LogFactory.getLog(QuikrOnlineIncentApiTests.class.getName());


    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "skuDiscountTests")
    public void listingPageSkuDiscount(String itemId) {

        LOGGER.info("executing listingPageSkuDiscount");

        quikrXApiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);

        JsonNode response = quikrXApiBase.getProduct(itemId);
        float priceAfterSku = Float.parseFloat(response.get("product_price_after_skudiscount").asText());
        float prefSeller = Float.parseFloat(response.get("preferred_seller_price").asText());
        float skuDiscount = Float.parseFloat(response.get("sku_discount").asText());
        float actualPriceAfterSku = Float.parseFloat(calulateDiscountPercentage(skuDiscount,prefSeller));
        Assert.assertEquals(priceAfterSku,actualPriceAfterSku);

        LOGGER.info("executed listingPageSkuDiscount");

    }

//    Bug , TODO: Uncomment after fix
//    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "onlineDiscountTests")
    public void listingPagePrepaidDiscount(String itemId,float percentage) {

        LOGGER.info("executing listingPagePrepaidDiscount");

        JsonNode response = quikrXApiBase.getProduct(itemId);
        float priceAfterOnline = Float.parseFloat(response.get("product_price_after_online_discount").asText());
        float priceAfterSku = Float.parseFloat(response.get("product_price_after_skudiscount").asText());

        float actualPriceAfteronline = Float.parseFloat(calulateDiscountPercentage(percentage,priceAfterSku));
        Assert.assertEquals(priceAfterOnline,actualPriceAfteronline);

        LOGGER.info("executed listingPagePrepaidDiscount");

    }

    @Test
    public void newPhoneMrpTest(){

        LOGGER.info("executing new phone mrp for new phones");

        JsonNode response = quikrXApiBase.updatePrice("500","1000","","1000");

    }



}
