package com.quikr.api.cars;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.quikr.utils.Database;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.jayway.restassured.RestAssured.given;
import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

/**
 * Created by quikr on 30/6/16.
 */
public class NewCarsAPI extends CarsApiBase{

    public static Response response;

    private HashMap<String, String> testData = getTestData(getProperties().get("NEWCARSAPI_TESTDATA_FILE"));
    public Response returnORPDropDownsApiResponse() {

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        param("responseKey", "variant").
                        param("make", "Honda").
                        param("model", "City").
                        when().
                        get("/cnb/newcars/getOnRoadFilter").
                        then().
                        statusCode(200).
                        extract().response();

        return response;
    }

    public Response returnSimilarCarsApiResponse(){
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        param("brand", "Mahindra").
                        param("model", "Scorpio").
                        param("variant", "S2").
                        param("count", "100").
                        when().
                        get("/cnb/newcars/similarCars").
                        then().
                        statusCode(200).
                        body(testData.get("SimilarCarsApiResponseBrand"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseModel"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseVariant"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseExShowroomPrice"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseMileageCity"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseDisplacementCC"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponsePrimaryFuelType"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseTransmission"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseSimilarityScore"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseBodyStyle"), notNullValue()).
                        body(testData.get("SimilarCarsApiResponseImages"), notNullValue()).
                        extract().response();
        return response;
    }

    public Response returnFilterApiResponse(){
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        param("car_make", "Maruti Suzuki").
                        param("responseKey", "car_model").
                        when().
                        get("/cnb/newcars/filters").
                        then().
                        statusCode(200).
                        extract().response();
        return response;

    }


    public Response returnFilterElasticResponse(){

        File elasticFilterApiQuery=new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/elasticFilterApiQuery.json");
        response=
                given().
                        when().
                        body(elasticFilterApiQuery).
                        post("http://192.168.124.33:9211/new_cars_v1/_search").
                        then().
                        statusCode(200).
                        extract().response();

        return response;
    }

    public boolean validateFilterApiandElasticResponse(Response filterApiResponse,Response filterElasticResponse){


        ArrayList<String> similarCarModelfromApi = filterApiResponse.path("FilterValuesResponse.FilterValues.car_model");
        Collections.sort(similarCarModelfromApi);

        ArrayList<String> carModelsFromElastic=filterElasticResponse.path(testData.get("ElasticResponseCarModel"));
        Set<String> carModelsSetfromElastic=new HashSet<>(carModelsFromElastic);
        boolean flag;
        for(String check:carModelsSetfromElastic){
            similarCarModelfromApi.remove(check);
        }

        //Flag Checks if Similar Car Model are present
        flag=similarCarModelfromApi.isEmpty();
        System.out.println(similarCarModelfromApi.size());
        System.out.println(carModelsSetfromElastic.size());

        return flag;

    }

    public Response returnNewCarsComparisonApiResponse(){
        String variant="40 TFSI Premium";
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/newcars/newCarComparisonV2?cars=Audi_A3_" + variant + ",Audi_A3_35 TDI Premium Sunroof").
                        then().
                        statusCode(200).
                        extract().response();
        return response;
    }

    public String returnFuelTypeNewCarsComparisonApiResponse(Response newCarsComparisonApiResponse){
        String variantId=getVariantIdFromVariantNameDatabase();
        String fuelTypefromApi=newCarsComparisonApiResponse.path(testData.get("NewCarsComparisonPrimaryFuelType") + variantId);
        return fuelTypefromApi;
    }

    public String  returnFuelTypefromElasticResponse(Response responsefromElasticwithVariant){

        ArrayList<String> fuelTypeElastic=responsefromElasticwithVariant.path(testData.get("ElasticResponsePrimaryFuelType"));

        String fuelType=fuelTypeElastic.get(0);
        return fuelType;
    }

    public Float returnShowroomPricefromCarsComparisonApi(Response newCarsComparisonApiResponse){
        String variantId=getVariantIdFromVariantNameDatabase();
        return newCarsComparisonApiResponse.path("newCarComparisonResponse.newCarComparison.comparisonInfo."+variantId+".price");
    }

    public Response returnResponsefromElasticwithVariant(){
        File showroomPriceFromElastic=new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/ElasticCarsComparisonQuery.json");
        response =
                given().
                        when().
                        body(showroomPriceFromElastic).
                        post("http://192.168.124.33:9211/new_cars_v1/_search").
                        then().
                        statusCode(200).extract().response();

        return response;
    }

    public Object returnShowroomPricefromElastic(Response responsefromElasticwithVariant){
        ArrayList<Integer> showRoomPrice=responsefromElasticwithVariant.path(testData.get("ElasticResponseShowroomPrice"));

        return showRoomPrice.get(0);
    }

    public String ElasticResponsePrimaryFuelType(){
        response=returnResponsefromElasticwithVariant();
        String showRoomPrice=response.path(testData.get("ElasticResponsePrimaryFuelType"));

        return showRoomPrice;
    }
    public Response returnNewCarsPopularComparisonApiResponse(){
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/newcars/newCarPopularComparison").
                        then().
                        statusCode(200).
                        extract().response();
        return response;
    }

    public ArrayList<String> returnCarModelsPopularComparison(Response newCarsPopularComparisonApiResponse){

        HashMap<String, String> popularComparison = newCarsPopularComparisonApiResponse.path("newCarComparisonResponse.newCarComparison.popularComparison.1");
        //Storing carModels and carModelsAttributes are getting extracted from HashMap and getting stored seperately in List
        ArrayList<String> carModels = new ArrayList<>();
        Set<String> set = popularComparison.keySet();
        for (String s : set) {
            carModels.add(s);
            break;
        }
        return carModels;
    }

    public ArrayList<String> returnCarModelsAttributesPopularComparison(Response newCarsPopularComparisonApiResponse){

        HashMap<String, String> popularComparison = newCarsPopularComparisonApiResponse.path("newCarComparisonResponse.newCarComparison.popularComparison.1");
        //Storing carModels and carModelsAttributes are getting extracted from HashMap and getting stored seperately in List
        ArrayList<String> carModelsAttributes = new ArrayList<>();
        Set<String> set = popularComparison.keySet();
        for (String s : set) {
            carModelsAttributes.add(popularComparison.get(s));
            break;
        }
        return carModelsAttributes;
    }

    public String returnCarIdsPopularComparison(Response newCarsPopularComparisonApiResponse){

        HashMap<String, HashMap<String,Object>> popularComparison = newCarsPopularComparisonApiResponse.path("newCarComparisonResponse.newCarComparison.popularComparison.1");
        System.out.println(popularComparison);
        HashMap<String,HashMap<String,Object>>ok=new HashMap<String ,HashMap<String, Object>>(popularComparison);
        String carId=null;
        for(Map.Entry<String,HashMap<String,Object>> cool:ok.entrySet()){
            carId=cool.getValue().get("carId").toString();
        }
        return carId;
    }
    public Response returnUpComingCarsApiResponse(){
        File file = new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/upcomingcars.json");
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(file).with().contentType("application/json").
                        when().post("/cnb/newcars/v2/search").
                        then().
                        statusCode(200).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].carMake", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].carModel", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].price", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].images", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].fuelType", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].transmission", notNullValue()).
                        extract().response();
        return response;
    }

    public ArrayList returnUpcomingCarsModelsfromApi(){
        response=returnUpComingCarsApiResponse();

        ArrayList<Object> ok=new ArrayList<>();
            ok.add(response.path("SearchAndBrowseResponse.SearchResponse.carSearchData.carModel"));
        logger.info("Response from API-->"+ok.get(0));
        return ok;
    }

    public boolean isElasticandApiResponseforUpcomingMatching(){
        int countTrue=0;
        int countFalse=0;

        NewCarsAPI newCarsAPI=new NewCarsAPI();
        ArrayList<ArrayList<Object>> ok=newCarsAPI.brandsfromElasticResponse();
        ArrayList<Object> cool=ok.get(0);

        ArrayList<ArrayList<Object>> my=newCarsAPI.returnUpcomingCarsModelsfromApi();
        ArrayList<Object> check=my.get(0);


        for(Object word:check){
            if(cool.contains(word)){
                countTrue++;
            }
            else {
                countFalse++;
            }
        }

        logger.info("CountTrue is-->"+countTrue);
        logger.info("CountFalse is-->"+countFalse);


        return countTrue>countFalse;

    }


    public Response returnUpComingCarsElasticResponse(){
        File upComingCarsElastic = new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/elasticUpComingCarsQuery.json");

        response =
                given().
                        when().
                        body(upComingCarsElastic).
                        post("http://192.168.124.33:9211/new_cars_v1/_search").
                        then().
                        statusCode(200).extract().response();
        logger.info("Elastic Response-->"+response.path("hits.hits._source.car_model"));
        return response;
    }

    public ArrayList brandsfromElasticResponse(){
        response=returnUpComingCarsElasticResponse();
        ArrayList<Object> carModels=new ArrayList<>();
                carModels.add(response.path(testData.get("ElasticResponseCarModel")));
        return carModels;
    }
    public Response returnSearchApiResponse(){

        File file = new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/search.json");
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(file).with().contentType("application/json").
                        when().post("/cnb/newcars/v2/search").
                        then().
                        statusCode(200).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].carMake", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].carModel", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].price", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].images", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].fuelType", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].transmission", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carSearchData[0].enginePower", notNullValue()).
                        extract().response();
        return response;

    }

    public Response returnEmiApiResponse(){
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        param("brand", "Honda").
                        param("model", "City").
                        param("variant", "SV Diesel").
                        param("cityId", "23").
                        when().
                        get("/cnb/newcars/emiValue").
                        then().
                        statusCode(200).
                        body("EMICalulationResponse.EMIResponse.exShowRoomPrice", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.minDownPayment", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.maxDownPayment", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.minInterestRate", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.maxInterestRate", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.minLoanPeriod", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.maxLoanPeriod", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.downPayment", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.interestRate", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.loanPeriod", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.onRoadPrice", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.emiAmount", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.interestAmount", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.totalAmount", notNullValue()).
                        body("EMICalulationResponse.EMIResponse.delhiPrice", notNullValue()).
                        extract().response();

        return response;
    }

    public void returnImageApiResponse(){
        given().
                header("X-Quikr-Client", "cars").
                param("brand", "Mercedes Benz").
                param("model", "E Class").
                param("imageTag", "Interior,Exterior").
                when().
                get("/cnb/newcars/getCarImages").
                then().
                statusCode(200).
                body("CarImageResponse.CarImages.Exterior.imageUrl", notNullValue()).
                body("CarImageResponse.CarImages.Exterior.imageCaption", notNullValue()).
                body("CarImageResponse.CarImages.Exterior.imageTags", notNullValue()).
                body("CarImageResponse.CarImages.Exterior.imageAltTag[0]", containsString("Mercedes")).
                body("CarImageResponse.CarImages.Exterior.imageTitle[0]", containsString("Mercedes"));

    }

    public Response returnOnRoadPricefromApi(){
        response=
                given().
                        header("X-Quikr-Client", "cars").
                        param("brand", "Honda").
                        param("model", "City").
                        param("variant", "SV Diesel").
                        param("cityId", "23").
                        when().
                        get("/cnb/newcars/getOnRoadPrice").
                        then().
                        statusCode(200).
                        body("OnRaodPriceResponse.onRoadPrice.exshowroom_price", notNullValue()).
                        body("OnRaodPriceResponse.onRoadPrice.registration_charge", notNullValue()).
                        body("OnRaodPriceResponse.onRoadPrice.lifetime_tax", notNullValue()).
                        body("OnRaodPriceResponse.onRoadPrice.insurance_premium", notNullValue()).
                        body("OnRaodPriceResponse.onRoadPrice.onroad_price", notNullValue()).
                        body("OnRaodPriceResponse.onRoadPrice.variant", equalToIgnoringCase("SV Diesel")).extract().response();
        return response;

    }

    public Response returnVariantPageApiResponse(){
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        param("brand", "Honda").
                        param("model", "City").
                        param("variant", "SV Diesel").
                        when().
                        get("/cnb/newcars/variantPage").
                        then().
                        statusCode(200).
                        body("VariantPageResponse.VariantPage.similarityScore", notNullValue()).
                        body("VariantPageResponse.VariantPage.design_and_dimensions.seating_capacity", notNullValue()).
                        extract().response();

        return response;
    }

    public Response returnCarModelApiResponse(){
        String brand="Honda";
        String model="City";

        response=
                given().
                        header("X-Quikr-Client", "cars").
                        param("brand", brand).
                        param("model", model).
                        when().
                        get("/cnb/newcars/modelPage").

                        then().
                        statusCode(200).
                        body(testData.get("CarModelApiMinPrice"), notNullValue()).
                        body(testData.get("CarModelApiEngineCapacity"), notNullValue()).
                        body(testData.get("CarModelApiMileage"), notNullValue()).
                        body(testData.get("CarModelApiColors"), notNullValue()).
                        body(testData.get("CarModelApiImages"), notNullValue()).
                        body(testData.get("CarModelApiCarMake"), hasItems(brand)).
                        body(testData.get("CarModelApiCarModel"), notNullValue()).
                        body(testData.get("CarModelApiCarVariant"), notNullValue()).
                        body(testData.get("CarModelApiCarPrice"), notNullValue()).
                        body(testData.get("CarModelApiMaxPower"), notNullValue()).
                        body(testData.get("CarModelApiFuelType"), notNullValue()).
                        body(testData.get("CarModelApiCarMileage"), notNullValue()).
                        body(testData.get("CarModelApiStatus"), notNullValue()).extract().response();

        return response;

    }

    public Response carModelElasticResponse(){
        String brand="Honda";
        String model="City";
        response=
                RestAssured.when().
                        get("http://192.168.124.33:9211/new_cars_v1/_search?q=car_make:" +
                                brand + "&q=car_model:" + model + "&_source_include=car_model").
                        then().
                        statusCode(200).
                        extract().response();
        return response;

    }

    public boolean validateModelApiandElasticResponse(Response carModelApiResponse,Response carModelElasticResponse){

        ArrayList<String> carmodels=carModelApiResponse.path(testData.get("CarModelApiCarModel"));
        Collections.sort(carmodels);

        boolean flag=false;


        ArrayList<String> models=carModelElasticResponse.path(testData.get("ElasticResponseCarModel"));
        Collections.sort(models);
        for(int i=0;i<carmodels.size();i++){
            for(int j=0;j<models.size();j++){
                if(!carmodels.get(i).equalsIgnoreCase(models.get(j))){
                    flag=false;
                    logger.info("Values are mismatching here"+carmodels.get(i)+" and "+models.get(i));
                }
                else {
                    flag=true;
                }
            }
        }

        return flag;
    }

    public Response returnAttributesApiResponse(){
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        param("responseKey", "car_make").
                        when().
                        get("/cnb/newcars/getAttributeValues").
                        then().
                        statusCode(200).
                        body(testData.get("AttributeApiValue"), notNullValue()).
                        body(testData.get("AttributeApiImageURL"), notNullValue()).
                        contentType(ContentType.JSON).  // check that the content type return from the API is JSON
                        extract().response();

        return response;
    }

    public boolean validateAttributeApiwithDatabase(Response attributesApiResponse){

        List<String> values = attributesApiResponse.path(testData.get("AttributeApiValue"));

        List<String> dbResponse= null;
        try {
            dbResponse = getAttributeValues();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean flag=false;
        for(int i=0;i<values.size();i++){
            for (int j=0;j<dbResponse.size();j++){
                if(!values.get(i).equalsIgnoreCase(dbResponse.get(i))){
                    logger.info("MisMatch among"+dbResponse.get(i)+"and "+values.get(i));
                    flag=false;
                }
                else {
                    flag=true;
                }
            }
        }

        return flag;
    }

    public boolean isValuesandImagesEqualAttributeApi(Response attributesApiResponse){

        List<String> values = attributesApiResponse.path(testData.get("AttributeApiValue"));
        List<String> imageurl = attributesApiResponse.path(testData.get("AttributeApiImageURL"));

        boolean flag=false;
        for(int i=0;i<values.size();i++){
            for (int j=0;j<imageurl.size();j++){
                if(!StringUtils.containsIgnoreCase(imageurl.get(i),values.get(i).toLowerCase())){
                    flag=false;
                }
                else {
                    flag=true;
                }
            }
        }

        return flag;

    }

    public Response returnSimilarCarsElasticResponse(){

        File elasticSimilarCarsQuery=new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/elasticSimilarCarsQuery.json");
        response =
                given().
                        when().
                        body(elasticSimilarCarsQuery).
                        post("http://192.168.124.33:9211/new_cars_v1/_search").
                        then().
                        statusCode(200).extract().response();

        return response;
    }

    public Response returnVariantElasticResponse(){

        File elasticVariantApiQuery=new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/elasticVariantApiQuery.json");
        response =
                given().
                        body(elasticVariantApiQuery).with().contentType("application/json").
                        when().post("http://192.168.124.33:9211/new_cars_v1/_search").
                        then().
                        statusCode(200).
                        extract().response();
        return response;
    }

    public List<String> returnAutoSuggestionApiResponse(String brand, String size,String brandApiBody,String modelApiBody){
        List<String> autoSuggestion=
        given().
                header("X-Quikr-Client", "cars").
                param("searchText", brand).
                param("size", size).
                when().
                get("/cnb/newcars/autosuggest").
                then().
                statusCode(200).
                body(brandApiBody, notNullValue()).
                body(modelApiBody, notNullValue()).
                extract().
                path(brandApiBody);

        return autoSuggestion;
    }

    public boolean validateSimilarCarsApiandElasticResponse(Response similarCarsApiResponse,Response similarCarsElasticResponse){

        ArrayList<String> similarCarResponse = similarCarsApiResponse.path(testData.get("SimilarCarsApiResponse"));
        System.out.println("Here is similarCarResponse -->" + similarCarResponse.size());
        Assert.assertTrue(similarCarResponse.size() == 100, "Looks like similarCarResponse are returned in InCorrect Number");
        ArrayList<String> similarCarResponseVariantsApi = similarCarsApiResponse.path(testData.get("SimilarCarsApiResponseVariant"));
        Collections.sort(similarCarResponseVariantsApi);
        logger.info("similarCarResponseVariantsApi is -->" + similarCarResponseVariantsApi);

        ArrayList<String> car_trimElastic = similarCarsElasticResponse.path(testData.get("ElasticResponseCarTrim"));
        Collections.sort(car_trimElastic);
        logger.info("car_trimElastic is -->"+car_trimElastic);
        int isAllSimilarCarspresent=0;
        for(String check:similarCarResponseVariantsApi){
            if(car_trimElastic.contains(check)){
                isAllSimilarCarspresent++;
            }
            else
            {
                logger.info("\n"+check+"  is not present in "+car_trimElastic);
                isAllSimilarCarspresent--;
            }
        }
        logger.info("Number of cars matching is API response and Elastic are-->"+isAllSimilarCarspresent);
        return isAllSimilarCarspresent>0;
    }

    public Response postNotifyMeApiResponse(){

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject=null;
        try {

            Object obj = parser.parse(new FileReader(
                    "src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/testNotifyMe.json"));

            jsonObject= (org.json.simple.JSONObject) obj;
            jsonObject.put("name",getRandomString(10)).toString();
            jsonObject.put("emailId",getRandomString(9) + "@gmail.com").toString();
            jsonObject.put("mobile","9"+getRandomInteger(9) ).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().post("/cnb/newcars/notifyMe").
                        then().
                        statusCode(200).
                        extract().response();
    return response;
    }

    public String returnNotifyMeSubscribeMessage(Response postNotifyMeApiResponse,String path){

        HashMap<String,String > totalResponse=postNotifyMeApiResponse.path(path);
        Set<String> set=totalResponse.keySet();
        String message="";
        for(String ok: set){
            message=totalResponse.get(ok);
            break;
        }
        return message;
    }

    public Response postBrowerApiResponse(){
        File file = new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/newCars/browseAPI.json");
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(file).with().contentType("application/json").
                        when().post("/cnb/newcars/search").
                        then().
                        statusCode(200).
                        body("SearchAndBrowseResponse.SearchResponse.carMake", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.carModel", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.price", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.images", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.mileage", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.fuelType", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.transmission", notNullValue()).
                        body("SearchAndBrowseResponse.SearchResponse.enginePower", notNullValue()).
                        extract().response();

        return response;
    }

    public int onRoadPricefromDatabase() throws IOException, ClassNotFoundException {

        db = new Database();
        db.initializeDbDomain();

        String columnName = "price";
        int price = 0;
        price = Integer.parseInt(db.GetResultQueryExecutor("cars", "SELECT * FROM `car_city_price` WHERE make='HONDA'AND model='City'AND variant='SV Diesel' AND cityId='23'", columnName));
        return price;
    }

    public String  getVariantIdFromVariantNameDatabase()  {

        db = new Database();
        db.initializeDbDomain();

        String  variantId= null;
        try {
            variantId = db.GetResultQueryExecutor("cars", "SELECT variant_id FROM `car_variant` WHERE variant='40 TFSI Premium'");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variantId;
    }

    public List<String> getAttributeValues() throws IOException, ClassNotFoundException {

        db = new Database();
        db.initializeDbDomain();

        List<List<String>> attributesValues = null;
        attributesValues = db.GetResultQueryExecutorAsList("cars", "SELECT make FROM `car_make` where make_id IN (SELECT DISTINCT(make_id) FROM car_variant WHERE status = 'Production') ORDER BY `car_make`.`popularity` ASC");
        final List<String> resultList = new ArrayList<String>(64000);
        final List<List<String>> mainList = attributesValues;
        for (List<String> subList : mainList) {
            resultList.addAll(subList);
        }

        return resultList;
    }

    public  List<String>  returnPopularCarsComparisonDatabaseResponse(){

        db = new Database();
        db.initializeDbDomain();

        List<List<String>> attributesValues = null;
        try {
            attributesValues = db.GetResultQueryExecutorAsList("cars", "SELECT `first_car_id`,`second_car_id` FROM `new_cars_popular_comparison` order by count DESC LIMIT 1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final List<String> resultList = new ArrayList<String>(64000);
        final List<List<String>> mainList = attributesValues;
        for (List<String> subList : mainList) {
            resultList.addAll(subList);
        }

        return resultList;

    }

}
