package com.quikr.api.cars.newCars;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.quikr.api.cars.CarsApiBase;
import com.quikr.api.cars.CarsApiDataProvider;
import com.quikr.api.cars.NewCarsAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

//import org.json.simple.JSONObject;

/**
 * Created by quikr on 21/6/16.
 */
public class NewCarsAPITests {

    public static Response responseApi;
    public static Response responseElastic;
    CarsApiBase carsApiBase=new CarsApiBase();

    private HashMap<String, String> testData = getTestData(getProperties().get("NEWCARSAPI_TESTDATA_FILE"));

    @Test(dataProvider = "autosuggestdata", dataProviderClass = CarsApiDataProvider.class)
    public void testAutoSuggest(String brand, String size) throws Exception {

        RestAssured.baseURI = carsApiBase.apiUrl;
        System.out.println("Trying with brand-->" + brand + " and size-->" + size);

        List<String> brands =new NewCarsAPI().returnAutoSuggestionApiResponse(brand, size, testData.get("AutoSuggestApiBrand"), testData.get("AutoSuggestApiModel"));

        if (brand.equalsIgnoreCase("mar")) {
            Assert.assertEquals(brands.size(), 50, "Looks like we are not getting all brands according to Size mentioned in Parameter");
        } else if (brand.equalsIgnoreCase("hnda")) {
            Assert.assertTrue(brands.size() > 1, "Looks like we are not getting all brands according to Size mentioned in Parameter");
        }
    }

    @Test
    public void testAttributeValue() throws Exception {
        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();

        responseApi=newCarsAPI.returnAttributesApiResponse();
        List<String> values = responseApi.path(testData.get("AttributeApiValue"));
        List<String> imageurl = responseApi.path(testData.get("AttributeApiImageURL"));
        Assert.assertEquals(values.size(), imageurl.size(), "Looks Like Values and Image URL are not equal in numbers");

        boolean flag=newCarsAPI.validateAttributeApiwithDatabase(responseApi);
        Assert.assertTrue(flag, "Looks Like values are not getting Proper returned from DB");
        System.out.println(values);
        System.out.println(imageurl);
        boolean isvalueandImagematching=newCarsAPI.isValuesandImagesEqualAttributeApi(responseApi);
        Assert.assertTrue(isvalueandImagematching,"Looks Like Image URL is not getting printed properly for the brand");
    }


    @Test
    public void testCarModelPage() throws IOException {

        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();

        responseApi=newCarsAPI.returnCarModelApiResponse();
        responseElastic=newCarsAPI.carModelElasticResponse();
        boolean isModelApiandElasticResponsematching=newCarsAPI.validateModelApiandElasticResponse(responseApi,responseElastic);
        Assert.assertTrue(isModelApiandElasticResponsematching,"Looks Like values of Models are not matching from Elastic");
    }

    @Test
    public void testVariantPage() {
        RestAssured.baseURI = carsApiBase.apiUrl;
        responseApi=new NewCarsAPI().returnVariantPageApiResponse();

        HashMap<String, String> engine = responseApi.path(testData.get("VariantPageApiEngine"));
        int engineparameters = engine.size();
        Assert.assertEquals(engineparameters, 17, "Looks like Engine parameters are not proper in Number");

        HashMap<String, String> drivetrain = responseApi.path(testData.get("VariantPageApiDrivetrain"));
        int drivetrainparameters = drivetrain.size();
        Assert.assertEquals(drivetrainparameters, 10, "Looks like drivetrain parameters are not Proper in Number");

        HashMap<String, String> safety_and_security = responseApi.path(testData.get("VariantPageApiSafetyAndSecurity"));
        int safety_and_securityparameters = safety_and_security.size();
        Assert.assertEquals(safety_and_securityparameters, 11, "Looks like safety_and_security parameters are not Proper in Number");

        HashMap<String, String> braking_and_traction = responseApi.path(testData.get("VariantPageApiBrakingAndTraction"));
        int braking_and_tractionparameters = braking_and_traction.size();
        Assert.assertEquals(braking_and_tractionparameters, 11, "Looks like safety_and_security parameters are not Proper in Number");

        HashMap<String, String> comfort_and_convenience = responseApi.path(testData.get("VariantPageApiComfortAndConvenience"));
        int comfort_and_convenienceparameters = comfort_and_convenience.size();
        Assert.assertEquals(comfort_and_convenienceparameters, 14, "Looks like comfort_and_convenience parameters are not Proper in Number");

        HashMap<String, String> seats_and_upholstery = responseApi.path(testData.get("VariantPageApiSeatsAndUpholstery"));
        int seats_and_upholsteryparameters = seats_and_upholstery.size();
        Assert.assertEquals(seats_and_upholsteryparameters, 24, "Looks like seats_and_upholstery parameters are not Proper in Number");

        HashMap<String, String> interior = responseApi.path(testData.get("VariantPageApiInterior"));
        int interiorparameters = interior.size();
        Assert.assertEquals(interiorparameters, 20, "Looks like interior parameters are not Proper in Number");

        HashMap<String, String> exterior = responseApi.path(testData.get("VariantPageApiExterior"));
        int exteriorparameters = exterior.size();
        Assert.assertEquals(exteriorparameters, 16, "Looks like exterior parameters are not Proper in Number");

        HashMap<String, String> instrumentation = responseApi.path(testData.get("VariantPageApiInstrumentation"));
        int instrumentationparameters = instrumentation.size();
        Assert.assertEquals(instrumentationparameters, 14, "Looks like instrumentation parameters are not Proper in Number");

        HashMap<String, String> entertainment = responseApi.path(testData.get("VariantPageApiEntertainment"));
        int entertainmentparameters = entertainment.size();
        Assert.assertEquals(entertainmentparameters, 17, "Looks like entertainment parameters are not Proper in Number");

        int displacement_cc=responseApi.path(testData.get("VariantPageApiDisplacement_cc"));
        String tyres_front=responseApi.path(testData.get("VariantPageApiTyresFront"));

        //Elastic Response
        responseElastic=new NewCarsAPI().returnVariantElasticResponse();

        ArrayList<String> displacement_ccElastic=responseElastic.path("hits.hits._source.engine.displacement_cc");
        ArrayList<String> tyres_frontELastic=responseElastic.path(" hits.hits._source.drivetrain.tyres_front");
        Assert.assertEquals(displacement_cc,displacement_ccElastic.get(0),"Looks like values of displacement_cc are not matching in ELastic");

        Assert.assertEquals(tyres_front,tyres_frontELastic.get(0),"Looks like values of tyres_front are not matching in ELastic");
    }

    @Test
    public void testOnRoadPrice()
    {

        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();
        responseApi=newCarsAPI.returnOnRoadPricefromApi();

        int exshowroom_price=responseApi.path(testData.get("OnRaodPriceApiExshowroomPrice"));
        try {
            Assert.assertEquals(newCarsAPI.onRoadPricefromDatabase(),exshowroom_price,"Not equal");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImage() throws IOException {

        RestAssured.baseURI = carsApiBase.apiUrl;
        new NewCarsAPI().returnImageApiResponse();

        }

    @Test
    public void testEMI() throws IOException {

        RestAssured.baseURI = carsApiBase.apiUrl;

        responseApi=new NewCarsAPI().returnEmiApiResponse();
        HashMap<String, String> EMIResponse = responseApi.path(testData.get("EMICalulationApiEMIResponse"));
        Assert.assertEquals(EMIResponse.size(), 15, "Looks like number of parameters in Response are not correct");
    }

    @Test
    public void testORPDropDowns() {

        RestAssured.baseURI = carsApiBase.apiUrl;

        NewCarsAPI newCarsAPI=new NewCarsAPI();

        responseApi = newCarsAPI.returnORPDropDownsApiResponse();
        ArrayList<String> variant = responseApi.path(testData.get("ORPDropDownsApiVariant"));
        Assert.assertTrue(variant.size() > 0, "Looks like Variant are not getting returned");

    }

    @Test
    public void testSimilarCars() {

        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();

        responseApi=newCarsAPI.returnSimilarCarsApiResponse();
        responseElastic=new NewCarsAPI().returnSimilarCarsElasticResponse();
        Assert.assertTrue(newCarsAPI.validateSimilarCarsApiandElasticResponse(responseApi,responseElastic),"Looks Like Response of Api and Elastic is not matching");
    }

    @Test
    public void testFilter() {

        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();

        responseApi=newCarsAPI.returnFilterApiResponse();
        ArrayList<String> similarcar_make = responseApi.path(testData.get("FilterValuesApiCarMake"));
        ArrayList<String> similarCarModel = responseApi.path(testData.get("FilterValuesApiCarModel"));
        Assert.assertTrue(similarcar_make.size() > 0, "Looks like similar Car Make are returned in InCorrect Number");
        Assert.assertTrue(similarCarModel.size() > 0, "Looks like similarCarModel are returned in InCorrect Number");

        responseElastic=new NewCarsAPI().returnFilterElasticResponse();
        boolean isFilterApiandElasticResponseMatching=newCarsAPI.validateFilterApiandElasticResponse(responseApi, responseElastic);
        Assert.assertTrue(isFilterApiandElasticResponseMatching,"Looks like Models getting returned from Elastic and Api are not matching");
    }

    @Test
    public void testNewCarsComparison() {

        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();

        responseApi=newCarsAPI.returnNewCarsComparisonApiResponse();
        Float showroomPriceApi=newCarsAPI.returnShowroomPricefromCarsComparisonApi(responseApi);

        responseElastic=newCarsAPI.returnResponsefromElasticwithVariant();
        Object showRoomPriceElastic=newCarsAPI.returnShowroomPricefromElastic(responseElastic);
        Assert.assertEquals(showRoomPriceElastic,showroomPriceApi,"Looks Like Elastic and Api Showroom Price are not Matching");
        String fuelTypefromApi=newCarsAPI.returnFuelTypeNewCarsComparisonApiResponse(responseApi);
        String fuelTypeElastic=newCarsAPI.returnFuelTypefromElasticResponse(responseElastic);
        Assert.assertEquals(fuelTypeElastic,fuelTypefromApi,"Looks Like fuel Type is not matching betwen Elastic and API");
    }
    @Test
    public void testNewCarsPopularComparison() {

        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();
        responseApi=newCarsAPI.returnNewCarsPopularComparisonApiResponse();

        ArrayList<String> carModels=newCarsAPI.returnCarModelsPopularComparison(responseApi);
        ArrayList<String> carModelsAttributes=newCarsAPI.returnCarModelsAttributesPopularComparison(responseApi);

        //To find number of attributes from the returned List carModelsAttributes it Returns--> displacementCC=796, model=Alto 800, carId=4673,
        String[] carModelsAttributesCount = carModelsAttributes.toString().split(",");
        Assert.assertTrue(carModels.size() > 0, "Looks Like No comparison Model is getting returned");
        Assert.assertTrue(carModelsAttributesCount.length == 10, "Looks like incorrect number of attributes of popularComparison are getting returned ");
        String carId=newCarsAPI.returnCarIdsPopularComparison(responseApi);
        List<String> ok=newCarsAPI.returnPopularCarsComparisonDatabaseResponse();
        Assert.assertTrue(ok.contains(carId), "Looks like Populat Cars not getting Comparison properly");
    }

    @Test
    public void upcomingCars() throws IOException {

        RestAssured.baseURI = carsApiBase.apiUrl;
        responseApi=new NewCarsAPI().returnUpComingCarsApiResponse();
        ArrayList<String> totalResponse=responseApi.path(testData.get("CarsApiSearch"));
        Assert.assertTrue(totalResponse.size() == 5, "Looks like More response is getting returned than the requested parameters");

        Assert.assertTrue(new NewCarsAPI().isElasticandApiResponseforUpcomingMatching(),"Looks like not matching The Variant between Api and Elastic Response");
    }

    @Test
    public void testNotifyMe() throws IOException {

        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();
        responseApi=newCarsAPI.postNotifyMeApiResponse();
        String message=newCarsAPI.returnNotifyMeSubscribeMessage(responseApi,testData.get("NotifyMeApiResponse"));

        Assert.assertEquals(message, "Successfuly subscribed", "Looks Like User is not subscribed");
    }

    @Test
    public void testSearch(){

        RestAssured.baseURI = carsApiBase.apiUrl;
        responseApi=new NewCarsAPI().returnSearchApiResponse();
        ArrayList<String> totalResponse=responseApi.path(testData.get("CarsApiSearch"));
        Assert.assertTrue(totalResponse.size() == 10, "Looks like More response is getting returned than the requested parameters");
    }

    @Test
    public void testbrowseAPI(){

        RestAssured.baseURI = carsApiBase.apiUrl;
        NewCarsAPI newCarsAPI=new NewCarsAPI();
        responseApi=newCarsAPI.postBrowerApiResponse();

        ArrayList<String> searchResponse=responseApi.path(testData.get("BrowseApiResponse"));
        String responseAttributes=searchResponse.toString();
        System.out.println(responseAttributes);
        String[] lengthofAttributes=responseAttributes.split(",");
        Assert.assertTrue(lengthofAttributes.length == 10, "Looks Like Browse Api Response is not returning properly ");
    }
}