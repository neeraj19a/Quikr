package com.quikr.api.quikrX.quikrXApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXApiTestBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.api.quikrX.dataProviders.QuikrXDataProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by francis.s@quikr on 1/2/16.
 */
public class QuikrXApiMoreTests extends QuikrXApiTestBase {

    private final static Log LOGGER = LogFactory.getLog(QuikrXApiMoreTests.class.getName());

    @Test
    public void getProductTests() {

        LOGGER.info("executing getProductTests");

        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        JsonNode result = quikrXApi.getProduct("1123");

        Assert.assertNotNull(result.get("preferred_seller_price"), "preferred seller price is empty");
        Assert.assertNotNull(result.get("preferred_seller_inventory"), "preferred seller inventory is empty");
        Assert.assertNotNull(result.get("sellers"), "seller list is empty");
        Assert.assertNotNull(result.get("sellers_rank"), "seller rank is empty");
        Assert.assertNotNull(result.get("preferred_seller_id"), "preferred seller id is empty");
        Assert.assertNotNull(result.get("subcategory_name"), "subcategory name is empty");

        LOGGER.info("executed getProductTests");
    }

    @Test
    public void updateProductSellerStatusTest() {

        LOGGER.info("executing updateProductSeller status");

        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        JsonNode result = quikrXApi.updateProductSellerStatus("true", "1000", "1218");
        boolean status = result.get("updateProductSellerStatusResponse").get("success").get("enabled").asBoolean();
        Assert.assertTrue(status, "mismatch");
        result = quikrXApi.updateProductSellerStatus("false", "1000", "1218");
        status = result.get("updateProductSellerStatusResponse").get("success").get("enabled").asBoolean();
        Assert.assertFalse(status, "mismatch");

    }

    @Test
    public void verifySellerStatus() {

        LOGGER.info("executing verifySellerStatus");

        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        JsonNode result = quikrXApi.updateProductSellerStatus("true", "1000", "1122");
        Iterator<Map.Entry<String, JsonNode>> nodeIterator = quikrXApi.getProductSellerDetails("1122").get("getProductSellerDetailsResponse").get("success").get("sellers").fields();
        while (nodeIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodeIterator.next();
            if (entry.getKey().equals("1000")) {
                Assert.assertTrue(entry.getKey().equals("1000"), "seller not enabled");
            }
        }

        result = quikrXApi.updateProductSellerStatus("false", "1000", "1122");
        nodeIterator = quikrXApi.getProductSellerDetails("1122").get("getProductSellerDetailsResponse").get("success").get("sellers").fields();
        while (nodeIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodeIterator.next();
            if (!entry.getKey().equals("1000"))
                Assert.assertFalse(entry.getKey().equals("1000"), "seller should not be enabled");
        }

        LOGGER.info("executed verifySellerStatus");

    }


    @Test
    public void categoryProductPreferredSellerPrice() {

        LOGGER.info("executing categoryProductPreferredSellerPrice");

        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        JsonNode looper = null;
        JsonNode result = quikrXApi.categoryProduct("64");
        result = result.get("docs");
        for (int count = 0; count <= result.size() - 1; count++) {
            looper = result.get(count);
            if (!looper.get("preferred_seller_price").asText().equals("null")) {
                int itemId = looper.get("entity_id").asInt();
                if (looper.get("entity_id").asInt() == itemId) {
                    float prefSellerPrice = Float.parseFloat(looper.get("preferred_seller_price").asText());
                    float afterSkudiscount = Float.parseFloat(looper.get("product_price_after_skudiscount").asText());
                    float skuDiscount = Float.parseFloat(looper.get("sku_discount").asText());
                    Assert.assertEquals("" + afterSkudiscount, calulateDiscountPercentage(skuDiscount, prefSellerPrice), "mismatch in price discount");
                    break;
                }
            }
        }
        LOGGER.info("executed categoryProductPreferredSellerPrice");
    }

    @Test
    public void sortPriceCategoryProduct() {

        LOGGER.info("executing sortPriceCategoryProduct");

        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        JsonNode looper = null;
        JsonNode result = quikrXApi.categoryProduct("64");
        result = result.get("docs");
        for (int count = 0; count <= result.size() - 1; count++) {
            looper = result.get(count);
            if(!looper.get("preferred_seller_price").asText().equals("null")){
                int itemId = looper.get("entity_id").asInt();
                if (looper.get("entity_id").asInt() == itemId) {
                    float prefSellerPrice = Float.parseFloat(looper.get("preferred_seller_price").asText().trim());
                    float afterSkudiscount = Float.parseFloat(looper.get("price").asText().trim());
                    Assert.assertNotEquals(prefSellerPrice, afterSkudiscount, "afterSkuDiscount price is incorrect");
                    break;
                }
            }
        }
        LOGGER.info("executed sortPriceCategoryProduct");
    }


    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "skuDiscount")
    public void updateProductSkuDiscount(String itemId, String title, String brand, String sellerId, String sku, String subCategory) {

        LOGGER.info("executing updateProductSkuDiscount");
        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        JsonNode result = quikrXApi.updateProduct(itemId, title, brand, sellerId, sku, subCategory);
        String skuDiscount = quikrXApi.getProduct(itemId).get("sku_discount").asText();
        Assert.assertEquals(sku, skuDiscount, "mismatch in sku discount");

        LOGGER.info("executed updateProductSkuDiscount");
    }

    @Test
    public void exchangeAndroid(){

        LOGGER.info("executing exchangeAndroid");
        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        JsonNode result = quikrXApi.exchangeAndroid("59");
        result = result.get("getBrandModelDetailsResponse").get("success");
        Assert.assertFalse(result.isNull(),"Exchange android failed");

        LOGGER.info("executed exchangeAndroid");

    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "exchangeAndroid")
    public void exchangeAndroidInValidCategoryId(String catId,String expected){

        LOGGER.info("executing exchangeAndroidInValidCategoryId");
        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        JsonNode result = quikrXApi.exchangeAndroid(catId);
        String actual = result.get("errorMessage").asText();
        Assert.assertEquals(expected,actual,"Exchange android failed");

        LOGGER.info("executed exchangeAndroidInValidCategoryId");

    }


    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "B2CBrands")
    public void getB2CBrandModel(String brand,String model,String areaId,boolean flag){

        LOGGER.info("executing getB2CBrandModel");
        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        JsonNode result = quikrXApi.getB2CBrandModel(brand,model,"","Excellent",areaId);
        if (flag)
            Assert.assertNotNull(result.get("getB2CBrandModelResponse").get("success").get("b2CBrandProductMap"),"valid model and doc failed");
        else
            Assert.assertEquals(result.get("errorMessage").asText(),"Invalid brand and model","invalid model and doc failed\"");
    }

    @Test
    public void validateCategoryBrandDetails(){

        LOGGER.info("executing validateCategoryBrandDetails");
        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        Iterator<Map.Entry<String, JsonNode>> categoryResult = quikrXApi.categoryProduct("59").get("facet_count").get("attr_brand_name_value").fields();
        List<String> category = new LinkedList<>();
        List<String> exchangeAnd = new LinkedList<>();;

        while (categoryResult.hasNext()) {

            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) categoryResult.next();
            if(!entry.getKey().equalsIgnoreCase("I do not have a used phone"))
                category.add(entry.getKey());
        }
        Iterator<Map.Entry<String, JsonNode>> exchangeResult = quikrXApi.exchangeAndroid("59").get("getBrandModelDetailsResponse").get("success").fields();
        while (exchangeResult.hasNext()) {

            Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) exchangeResult.next();
            exchangeAnd.add(entry.getKey());


        }

        Assert.assertTrue(exchangeAnd.containsAll(category),"brands missing in android exchange");

        LOGGER.info("executed validateCategoryBrandDetails");
    }

//    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "B2CBrandsWithScore")
    public void getB2CBrandModelWithScore(String brand,String model,String areaId,String score,double discount,String productId){

        LOGGER.info("executing B2CBrandsWithScore");
        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        long wholeNumber;
        double decimal;

        JsonNode result = quikrXApi.getB2CBrandModel(brand,model,score,"Good",areaId);
        Assert.assertNotNull(result.get("getB2CBrandModelResponse").get("success").get("b2CBrandProductMap"),"valid model and doc failed");
        int b2cPrice = result.get("getB2CBrandModelResponse").get("success").get("productPrice").asInt();
        float actualPrice = Float.parseFloat(quikrXApi.getProduct(productId).get("price").asText());
        double expectedPrice = actualPrice+(discount/100)*actualPrice;
        wholeNumber = (long) expectedPrice;
        decimal = expectedPrice-wholeNumber;
        if(decimal>0.5 || decimal==0.05){
            expectedPrice=Math.ceil(expectedPrice);
        }else{
            expectedPrice=Math.floor(expectedPrice);
        }
        int res = (int) expectedPrice;
        Assert.assertEquals(res,b2cPrice,"mismatch in old phone price");

        LOGGER.info("executed B2CBrandsWithScore");

    }

//    @Test
    public void getPopularTest() throws InterruptedException {

        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        String itemId="";
        String catId = "64";
        boolean res=false;

        JsonNode result = quikrXApi.getPopularProducts(catId);
        result = result.get("popularProductsResponse").get("success").get("products").get(catId);
        itemId =  result.get(0).get("productId").asText();
        quikrXApi.updateProductInventory("1", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);

        Thread.sleep(10000); //Need sleep for delay in api
        result = quikrXApi.getPopularProducts(catId);
        result = result.get("popularProductsResponse").get("success").get("products").get(catId);
        for(int count=0;count<=result.size()-1;count++){
            if(result.get(count).get("productId").asText().equals(itemId)){
                res=true;
                break;
            }
        }
        quikrXApi.updateProductInventory("6", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
       Assert.assertFalse(res,"oos item is displayed");
    }

    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "exchangeFlow")
    public void exchangeProductChangeTest(String sellerName, String itemId, String email){

        LOGGER.info("executing change exchange product after add to cart");

        String firstExchangeItem="1258";
        String secongExchangeItem="1359";

        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        String quoteId=null;

        String sessId =  quikrXApi.createSession();

        LOGGER.info("sessId " + sessId);
        String custId = quikrXApi.addEmailToSession(sessId, email).get("customerId").asText();
        LOGGER.info("custId "+custId);

        quikrXApi.associateUser(sessId, custId).get("state");
        quikrXApi. updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE, sellerName, itemId);
        quikrXApi.updatePrice("5000", sellerName, itemId,"");
        quikrXApi.updateProductSellerStatus("true",sellerName,itemId);
        JsonNode result = quikrXApi.addProductExchange(sessId, sellerName, itemId, firstExchangeItem,email).get("AddQuikrXProductWithExchangeResponse").get("success").get("quote");
        quoteId = result.get("id").asText();
        Assert.assertEquals(firstExchangeItem,result.get("cart").get("oldItems").get(0).get("productId").asText());
        LOGGER.info("quoteId "+quoteId);
        String price = result.get("cart").get("newItems").get(0).get("finalPrice").get("value").asText();
        result = quikrXApi.addProductExchange(sessId, sellerName, itemId, secongExchangeItem,email).get("AddQuikrXProductWithExchangeResponse").get("success").get("quote");
        Assert.assertEquals(quoteId,result.get("id").asText());
        System.out.println(result.get("cart").get("newItems").get(0).get("finalPrice").get("value").asText());
        Assert.assertEquals(secongExchangeItem,result.get("cart").get("oldItems").get(0).get("productId").asText());

    }

}