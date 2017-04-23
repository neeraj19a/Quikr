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


/**
 * Created by francis.s@quikr on 1/4/16.
 */
public class QuikrXGradationTests extends QuikrXApiTestBase {

    private final static Log LOGGER = LogFactory.getLog(QuikrXGradationTests.class.getName());

//    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "gradeItems")
    public void getProductGrade(String itemId, int gradeValueActual, String gradeNameActual) {


        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        JsonNode result = quikrXApi.getProduct(itemId);
        int gradeValue = result.get("product_grade_id").asInt();
        String gradeName = result.get("product_grade_name").asText();
        Assert.assertEquals(gradeValueActual, gradeValue, "mismatch in grade value");
        Assert.assertEquals(gradeNameActual, gradeName, "mismatch in grade name");

        LOGGER.info("get product executed for --" + itemId);

    }

    @Title("Test to condition filter")
    @Description("validate filter for Execellent Good and fair condition")
    @Test (dataProviderClass = QuikrXDataProvider.class, dataProvider = "gradeFilter")
    public void productGradeFilter( String filterValue, String gradeNameActual) {



        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        JsonNode result = quikrXApi.categoryProductGradation("64", filterValue).get("docs");

        int count=0;
        while(count!=result.size()) {

           String gradeId = result.get(count).get("product_grade_id").asText();
           Assert.assertEquals(filterValue,gradeId,"grade filter not working");
           count++;

        }
        LOGGER.info("filter test executed for --" + gradeNameActual );
    }

    @Title("gradtion in b2c brand and model api")
    @Description("verify price is approriate for different condtions")
    @Test(dataProviderClass = QuikrXDataProvider.class, dataProvider = "b2cGrdation")
    public void B2cBrandGradation(String brand,String model,String score,String condition,int deductPercentage){

        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        JsonNode response = quikrXApi.getB2CBrandModel(brand,model,score,condition,"22");

        float syncScanPrice = response.get("getB2CBrandModelResponse").get("success").get("productPrice").asInt();
        int gradationPrice = response.get("getB2CBrandModelResponse").get("success").get("gradationPrice").asInt();
        int actualPrice = roundOff(calulateDiscountPercentage(deductPercentage,syncScanPrice));
        Assert.assertEquals(gradationPrice,actualPrice,"mismatch in gradation prices");

    }

}
