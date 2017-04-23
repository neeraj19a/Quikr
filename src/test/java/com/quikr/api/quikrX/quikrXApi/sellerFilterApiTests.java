package com.quikr.api.quikrX.quikrXApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXApiTestBase;
import com.quikr.api.quikrX.dataProviders.QuikrXDataProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by francis.s@quikr on 18/3/16.
 */
public class sellerFilterApiTests extends QuikrXApiTestBase {

    private final static Log LOGGER = LogFactory.getLog(sellerFilterApiTests.class.getName());


    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "sellersFilter")
    @Title("Test to check if seller filter is applied")
    @Description("Add seller id as filter and verify result")
    public void sellerIdFilterTest(String category, String sellerId) {

        QuikrXApiBase apiBase = new QuikrXApiBase();

        JsonNode result = apiBase.categoryProduct(category, sellerId).get("docs");
        for (int count = 0; count <= result.size() - 1; count++) {
            Iterator<String> itemIds = result.get(count).fieldNames();
            while (itemIds.hasNext()) {
                String fieldName = itemIds.next();
                if (fieldName.equalsIgnoreCase("preferred_seller_id")) {
                    Assert.assertEquals(result.get(count).get(fieldName).asText(), sellerId, "filtered seller not displayed");

                }

            }
        }
    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "sellersFilter")
    @Title("Test to check if seller filter details is applied")
    @Description("Add seller id as filter and verify preferred seller details")
    public void sellerIdFilterDetailsTest(String category, String sellerId) {

        QuikrXApiBase apiBase = new QuikrXApiBase();

        String seller_inventory = null;
        String seller_price = null;
        String seller_name = null;
        JsonNode result = apiBase.categoryProduct(category, sellerId).get("docs");
        //compare the result of category product with productSellerDetails
        for (int count = 0; count <= result.size() - 1; count++) {
            Iterator<String> itemIds = result.get(count).fieldNames();
            while (itemIds.hasNext()) {
                String fieldName = itemIds.next();
                String itemId = null;
                if (fieldName.equalsIgnoreCase("id")) {
                    itemId = result.get(count).get(fieldName).asText();
                    JsonNode sellerDetailsRes = apiBase.getProductSellerDetails(itemId).get("getProductSellerDetailsResponse").get("success").get("sellers");
                    Iterator<Map.Entry<String, JsonNode>> nodeIterator = sellerDetailsRes.fields();
                    while (nodeIterator.hasNext()) {
                        Map.Entry<String, JsonNode> entry = nodeIterator.next();
                        if (entry.getKey().equals(sellerId)) {
                            seller_inventory = sellerDetailsRes.get(entry.getKey()).get("seller_inventory").asText();
                            seller_price = sellerDetailsRes.get(entry.getKey()).get("seller_price").asText();
                            seller_name = sellerDetailsRes.get(entry.getKey()).get("seller_name").asText();
                        }

                    }
                    if (result.get(count).get("id").asText().equals(itemId)) {
                        Assert.assertEquals(seller_inventory, result.get(count).get("preferred_seller_inventory").asText());
                        Assert.assertEquals(seller_price, result.get(count).get("preferred_seller_price").asText());
                        Assert.assertEquals(seller_name, result.get(count).get("preferred_seller_name").asText());

                    }
                }

            }
        }
    }

}

