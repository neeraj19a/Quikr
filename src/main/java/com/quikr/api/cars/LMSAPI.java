package com.quikr.api.cars;

import com.jayway.restassured.response.Response;
import com.quikr.utils.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by quikr on 4/7/16.
 */
public class LMSAPI extends CarsApiBase {

    public static Response response;
    private HashMap<String, String> testData = getTestData(getProperties().get("LMSAPI_TESTDATA_FILE"));

    public Response returnGetAllCitiesApiResponse() {

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/realestate/v1/cities").
                        then().
                        body("topCities[0].id", notNullValue()).
                        body("topCities[0].name", notNullValue()).
                        statusCode(200).
                        extract().response();

        return response;
    }

    public int validateTopCitiesApiResponse(Response getAllCitiesResponse) {

        ArrayList<String> topCities = getAllCitiesResponse.path("topCities");
        int sizetopCities = topCities.size();

        return sizetopCities;
    }

    public int validateAllCitiesApiResponse(Response getAllCitiesResponse) {

        ArrayList<String> allCities = getAllCitiesResponse.path("allCities");
        int sizeallCities = allCities.size();

        return sizeallCities;
    }


    public boolean validateIdandCityNamefromDatabase(Response getAllCitiesResponse) {

        db = new Database();
        db.initializeDbDomain();

        ArrayList<String> topCities = getAllCitiesResponse.path("topCities");
        int topCitiesSize = topCities.size();
        ArrayList<Integer> id = new ArrayList<>();
        for (int i = 0; i < topCitiesSize; i++) {
            id.add((Integer.parseInt(getAllCitiesResponse.path("topCities[" + i + "].id").toString())));
        }

        logger.info("Ids are-->"+id);
        ArrayList<String> nameofCitesfromApi = new ArrayList<>();
        for (int i = 0; i < topCitiesSize; i++) {
            nameofCitesfromApi.add(getAllCitiesResponse.path("topCities[" + i + "].name").toString());
        }

        logger.info("Name of Cities from API"+nameofCitesfromApi);

        String area_name = null;
        ArrayList<String> citiesFromDb = new ArrayList<>();
        for (int i = 0; i < topCitiesSize; i++) {
            try {
                area_name = db.GetResultQueryExecutor("kijiji_presentation", "SELECT area_name FROM `babel_area` WHERE `area_level` = 1 and premium_enabled='1' and area_id='" + id.get(i) + "' ");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            citiesFromDb.add(area_name);
        }
        int k=0;
        for(int i=0;i<citiesFromDb.size();i++){
            if(citiesFromDb.get(i).equals("")||citiesFromDb.get(i).equals(" ")){
                k=i;
            }
        }
        citiesFromDb.remove(k);

        logger.info("Cities from DB are-->"+citiesFromDb);
        int countTrue=0;
        int countFalse=0;

        for(String check:nameofCitesfromApi){
            if (citiesFromDb.contains(check)){
                countTrue++;
            }
            else
                countFalse++;
        }
        logger.info("countTrue-->"+countTrue);
        logger.info("countFalse-->"+countFalse);

        return countTrue>countFalse;
    }

    public Response returnPostLoginUserResponse() {

        File loginUserLms = new File("src/main/java/com/quikr/api/cars/carsApiJsonRequests/lms/loginUserLMS.json");
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(loginUserLms).with().contentType("application/json").
                        when().
                        post(apiUrl+"/cnb/lms/user/login").
                        then().
                        body("LmsUserLoginResponse.LmsUserLoginData.status", notNullValue()).
                        body("LmsUserLoginResponse.LmsUserLoginData.uid",notNullValue()).
                        body("LmsUserLoginResponse.LmsUserLoginData.emailId", notNullValue()).
                        body("LmsUserLoginResponse.LmsUserLoginData.displayName", notNullValue()).
                        statusCode(200).extract().response();

        return response;
    }

    public String getUidfromLoginUserApi(Response postLoginUserResponse){

        String u_id=postLoginUserResponse.path("LmsUserLoginResponse.LmsUserLoginData.uid");
        return u_id;
    }

    public String getuIdLmsUserDatabase(String emailId) {

        db = new Database();
        db.initializeDbDomain();

        String u_id = null;
        try {
            u_id=db.GetResultQueryExecutor("cars", "SELECT u_id FROM `lms_user` where email_id='"+emailId+"'");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return u_id;
    }

    public Response returnPostSignUpUserResponse() {

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject=null;
        try {

            Object obj = parser.parse(new FileReader(
                    "src/main/java/com/quikr/api/cars/carsApiJsonRequests/lms/signUpuserLMS.json"));

            jsonObject= (org.json.simple.JSONObject) obj;
            jsonObject.put("loginId",getRandomString(10)).toString();
            jsonObject.put("loginPassword",getRandomString(10)+"N9").toString();
            jsonObject.put("username",getRandomString(10)).toString();
            jsonObject.put("emailId",getRandomString(9) + "@gmail.com").toString();
            jsonObject.put("mobileNumber","9"+getRandomInteger(9) ).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().
                        post(apiUrl+"/cnb/lms/user/signup").
                        then().
                        body("LmsUserSignupResponse.LmsUserSignupData.uid", notNullValue()).
                        body("LmsUserSignupResponse.LmsUserSignupData.status",notNullValue()).
                        statusCode(200).extract().response();
        logger.info("Response of SignUp--->"+response.path("LmsUserSignupResponse"));

        return response;
    }

    public String returnUidfromSignUpuserApi(Response postSignUpUserResponse){

        String uid=postSignUpUserResponse.path("LmsUserSignupResponse.LmsUserSignupData.uid");
        logger.info("Uid from Api is-->"+uid);
        return uid;
    }

    public boolean checkUidofUserPresentinDatabase(Response postSignUpUserResponse){

        String uid=returnUidfromSignUpuserApi(postSignUpUserResponse);
        db = new Database();
        db.initializeDbDomain();

        String emailId = null;
        try {
            emailId=db.GetResultQueryExecutor("cars", "SELECT email_id FROM `lms_user` where u_id='"+uid+"'");
            logger.info("EMail id-->"+emailId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !emailId.isEmpty();

    }


    public Response returnCreatePermissionApiResponse() {

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/lms/permission/bulk/create").
                        then().
                        body("BulkCreatePermissionsResponse.BulkCreatePermissionsData.status", notNullValue()).
                        statusCode(200).
                        extract().response();

        return response;
    }

    public boolean returnStatusCreatePermissionApiResponse(Response createPermissionApiResponse){
    boolean status=createPermissionApiResponse.path("BulkCreatePermissionsResponse.BulkCreatePermissionsData.status");
        return status;
    }

    public Response returnGetAllPermissionApiResponse(){

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/lms/permission/get/all").
                        then().
                        body("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionId", notNullValue()).
                        body("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionType", notNullValue()).
                        body("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionTypeValue", notNullValue()).
                        body("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionDescription", notNullValue()).
                        body("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionTypeValueString", notNullValue()).
                        statusCode(200).
                        extract().response();

        return response;
    }

    public HashMap<String,Object> getPermissionDetailsFromApi(Response getAllPermissionApiResponse){

        //Creating a HashMap and storing all the values for the Keys
        HashMap<String,Object> hm=new HashMap<>();
        hm.put("permissionId",getAllPermissionApiResponse.path("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionId"));
        hm.put("permissionType",getAllPermissionApiResponse.path("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionType"));
        hm.put("permissionTypeValue",getAllPermissionApiResponse.path("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionTypeValue"));
        hm.put("permissionDescription",getAllPermissionApiResponse.path("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionDescription"));
        hm.put("permissionTypeValueString", getAllPermissionApiResponse.path("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionTypeValueString"));
        hm.put("isPermissionActive",getAllPermissionApiResponse.path("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].isPermissionActive"));
        return hm;

    }


    public List<List<String>> getPermissionDetailsFromDatabase(Response getAllPermissionApiResponse){

        int permission_id= getAllPermissionApiResponse.path("GetAllPermissionsResponse.GetAllPermissionsData.lmsMakePermissions[0].permissionId");

        db = new Database();
        List<List<String>> objects= new ArrayList<>();

            objects=db.getListValuesFromDatabase("cars", "SELECT * FROM `lms_permission` WHERE permission_id='" + permission_id + "'");
        return objects;
    }

    public Response getCreateRoleApiResponse(int permissionId){

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject=null;
        try {

            Object obj = parser.parse(new FileReader(
                    "src/main/java/com/quikr/api/cars/carsApiJsonRequests/lms/createRole.json"));
            //Creating Json Array
            JSONArray jsonArray=new JSONArray();
            jsonArray.add(0,permissionId);
            jsonObject= (org.json.simple.JSONObject) obj;
            jsonObject.put("permissionIds",jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().
                        post(apiUrl+"/cnb/lms/role/create").
                        then().
                        body("CreateRoleResponse.CreateRoleData.status", notNullValue()).
                        statusCode(200).extract().response();

        return response;
    }

    public int returnAvailableMakePermissionIdsFromDatabase(){

        Database db=new Database();
        int permissionId=db.returnIntValuefromDatabase("cars", "SELECT permission_id FROM `lms_permission` where permission_id IN (SELECT permission_id FROM `lms_role_permission` ) AND lms_permission.permission_type='MAKE' LIMIT 1");

        return permissionId;
    }

    public int returnRoleIdfromApi(Response getCreateRoleApiResponse){

        int roleIdApi= getCreateRoleApiResponse.path("CreateRoleResponse.CreateRoleData.roleId");
        return roleIdApi;
    }

    public int returnRoleIdCoresspondingPermissionIdDatabase(int permissionId){

        Database db=new Database();
            int role_id=db.returnIntValuefromDatabase("cars", "SELECT role_id FROM `lms_role_permission` where permission_id='" + permissionId + "'");
        return role_id;
    }

    public Response postAssignRoleResponse(String uId,String roleId){

        org.json.simple.JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("userId",uId);
            //Creating Json Array
            JSONArray jsonArray=new JSONArray();
            jsonArray.add(0, roleId);
            jsonObject.put("roleIds", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().
                        post(apiUrl+"/cnb/lms/role/assign").
                        then().
                        body("AssignRolesToUserResponse.AssignRolesToUserData.status", notNullValue()).
                        statusCode(200).extract().response();

        return response;
    }

    public String returnUIdfromDatabase(){

        Database db=new Database();
            String u_id=db.returnStringValuefromDatabase("cars", "SELECT u_id FROM `lms_user` LIMIT 1");
        return u_id;
    }

    public String returnRoleIdfromLmsRolePermissionDatabase(){

        Database db=new Database();
        String role_id=db.returnStringValuefromDatabase("cars", "SELECT role_id FROM `lms_role_permission` LIMIT 1 ");
        return role_id;
    }

    public boolean returnStatusPostAssignRoleResponse(Response postAssignRoleResponse){

        boolean status=postAssignRoleResponse.path("AssignRolesToUserResponse.AssignRolesToUserData.status");
        return status;
    }

    public int returnRoleIdfromLmsUserRoleCorrespondingtoUid(String uId){

        Database db=new Database();
          int  role_id=db.returnIntValuefromDatabase("cars", "SELECT role_id FROM `lms_user_role` WHERE u_id='" + uId + "'");
        return role_id;
    }

    public String returnPasswordofLmsuserfromDatabase(String emailId){

        Database db=new Database();

        String  password=db.returnStringValuefromDatabase("cars", "SELECT login_passwd FROM `lms_user`WHERE email_id='" + emailId + "'");
        return password;
    }

    public Response postChangePassword(String newPassword){

        org.json.simple.JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("emailId", "testingcarapi@quikr.com");
            jsonObject.put("newPassword",newPassword);
            jsonObject.put("confirmNewPassword",newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().
                        post(apiUrl + "/cnb/lms/user/changePassword").
                        then().
                        body("LmsUserChangePasswordResponse.LmsUserChangePasswordData.status", notNullValue()).
                        body("LmsUserChangePasswordResponse.LmsUserChangePasswordData.uid", notNullValue()).
                        statusCode(200).extract().response();

        return response;
    }

    public Response returnGetPermissionByUserIdApiResponse(String uId){
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/lms/permission/byUserId?userId=" + uId).
                        then().
                        body(testData.get("GetPermissionByUserIdroleName"), notNullValue()).
                        body(testData.get("GetPermissionByUserIdroleDescription"), notNullValue()).
                        body(testData.get("GetPermissionByUserIdroleType"), notNullValue()).
                        body(testData.get("GetPermissionByUserIdpermissionId"), notNullValue()).
                        body(testData.get("GetPermissionByUserIdpermissionTypeValue"), notNullValue()).
                        body(testData.get("GetPermissionByUserIdpermissionDescription"), notNullValue()).
                        body(testData.get("GetPermissionByUserIdpermissionTypeValueString"), notNullValue()).
                        statusCode(200).
                        extract().response();

        return response;

    }

    public int returnPermissionIdfromLmsPermissionDatabase(){
        Database db=new Database();

        int permission_id=db.returnIntValuefromDatabase("cars","SELECT permission_id FROM `lms_permission` WHERE permission_description='Permission for model : Stile' and permission_type='MODEL'");
        return permission_id;
    }

    public Response returnPostAssignPermissionToUserApi(String uId, int[] parameteres){

        org.json.simple.JSONObject jsonObject=new JSONObject();
        int permissionId = returnPermissionIdfromLmsPermissionDatabase();

            JSONArray jsonArray = new JSONArray();

            if (permissionId == 2 && parameteres[0]!=0) {
                jsonObject.put("userId", uId);
                jsonArray.add(0, parameteres[0]);
                jsonArray.add(1, permissionId);
                jsonArray.add(2, parameteres[1]);
                jsonObject.put("permissionIds", jsonArray);

            }

            else if (permissionId == 2 && parameteres[0]==0) {
                jsonObject.put("userId", uId);
                jsonArray.add(0, permissionId);
                jsonObject.put("permissionIds", jsonArray);

            }
            else {
                logger.info("Looks like Permission for model : Stile does not have permission 'MODEL' in Database Pls Check");
            }

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().
                        post(apiUrl + "/cnb/lms/permission/assign").
                        then().
                        extract().response();

        return response;
    }

    public Response getCreateBulkPermissionResponse(){

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/lms/permission/bulk/create").
                        then().
                        statusCode(200).
                        extract().response();

        return response;
    }

    public Response getMakeswithId(){

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/newcars/allMakes").
                        then().
                        statusCode(200).
                        body("makes.makeId", notNullValue()).
                        body("makes.make", notNullValue()).
                        body("makes.popularity", notNullValue()).
                        extract().response();
        return response;
    }

    public List<List<String>> getAllMakefromCarMakeDatabase(){

        Database db=new Database();

        List<List<String>> objects=db.getListValuesFromDatabase("cars","SELECT make FROM `car_make` ");
        return objects;
    }

    public Response returnGetModelswithIdResponse(){

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/newcars/allModels?makeId=18").
                        then().
                        statusCode(200).
                        body("status", notNullValue()).
                        body("models.modelId", notNullValue()).
                        body("models.popularity", notNullValue()).
                        body("models.model", notNullValue()).
                        body("models.carType", notNullValue()).

                        extract().response();
        return response;
    }

    public List<Integer> returnModelIdsfromGetModelswithIdApi(Response getModelswithIdResponse){

        List<Integer> modelIds=getModelswithIdResponse.path("models.modelId");
        return modelIds;
    }

    public List<List<Integer>> returnModelIdsfromDatabase(){

        Database db=new Database();

        List<List<Integer>> objects=db.getListIntegerValuesFromDatabase("cars", "SELECT model_id FROM `car_model` WHERE make_id='18' ");
        return objects;

    }

    public boolean responseofGetModelswithIdApiAndDatbaseAreMatching(){

        List<Integer> modelIdfromApi=returnModelIdsfromGetModelswithIdApi(response);
        List<List<Integer >> modelsffromDatabase=returnModelIdsfromDatabase();

        List<Integer> listofModelIdfromDb=new ArrayList<>();

        for (int i=0;i<=modelsffromDatabase.size()-1;i++){
            listofModelIdfromDb.add(modelsffromDatabase.get(i).get(0));
        }

        boolean flag=false;
        for(int i=0;i<=modelIdfromApi.size()-1;i++){
            if(modelIdfromApi.get(i).equals(listofModelIdfromDb.get(i))){
                flag=true;
            }
            else {
                logger.info("These values are not matching"+ modelIdfromApi.get(i)+"and "+listofModelIdfromDb.get(i));
                flag=false;
            }
        }
        return flag;
    }

    public Response returnGetActiveDealersApiResponse() {
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/dealer/activeLmsDealers").
                        then().
                        statusCode(200).
                        body("status", notNullValue()).
                        body("dealer.dealerId", notNullValue()).
                        body("dealer.name", notNullValue()).
                        body("dealer.uinqueId", notNullValue()).
                        body("dealer.address", notNullValue()).
                        body("dealer.landmark", notNullValue()).
                        body("dealer.pin", notNullValue()).
                        body("dealer.city", notNullValue()).
                        body("dealer.cityId", notNullValue()).
                        body("dealer.state", notNullValue()).
                        body("dealer.geoLocation", notNullValue()).
                        body("dealer.geoLocation.latitude", notNullValue()).
                        body("dealer.geoLocation.longitude", notNullValue()).
                        body("dealer.dealerContactPersons.contactId", notNullValue()).
                        body("dealer.dealerContactPersons.name", notNullValue()).
                        body("dealer.dealerContactPersons.email1", notNullValue()).
                        body("dealer.dealerContactPersons.number1", notNullValue()).
                        body("dealer.oemRegion", notNullValue()).
                        body("dealer.makeVoList.makeId", notNullValue()).
                        body("dealer.makeVoList.make", notNullValue()).
                        body("dealer.makeVoList.popularity", notNullValue()).
                        body("dealer.makeVoList.makeLogoImage", notNullValue()).
                        extract().response();

        //logger.info(response.path("dealer"));
        return response;
    }

    public List<Integer> returnListofActiveDealersIdfromApi(Response getActiveDealersApiResponse){

        List<Integer> listofDealersIdfromApi=getActiveDealersApiResponse.path("dealer.dealerId");
        return listofDealersIdfromApi;
    }
    public List<Integer> returnListofActiveDealerIdsfromDatabase(){

        Database db=new Database();
        List<List<Integer>> dealers_id=db.getListIntegerValuesFromDatabase("cars", "SELECT dealer_id FROM `cnb_dealer` WHERE is_lms_active='1'");
        List<Integer> listofDealersid=new ArrayList<>();
        for (int i=0;i<=dealers_id.size()-1;i++){
            listofDealersid.add(dealers_id.get(i).get(0));
        }
        return listofDealersid;
    }

    public List<List<Integer>> returnListofActiveDealerContactIdsfromDatabase(){

        List<Integer> listofDealersFromDatabase=returnListofActiveDealerIdsfromDatabase();
        Database db=new Database();

        List<List<Integer>> dealers_contactid=new ArrayList<>();
        for (int i=0;i<listofDealersFromDatabase.size();i++){
            //adding dealers Contact Id from Database
            dealers_contactid.add(i,db.getListIntegerValuesFromDatabase("cars", "SELECT  dealer_contact_id FROM `cnb_dealer_contact` WHERE dealer_id='" + listofDealersFromDatabase.get(i) + "'").get(0));
        }
        return dealers_contactid;
    }

    public boolean isApiResponseandDatabaseResponseMatching(){
        response=returnGetActiveDealersApiResponse();
        boolean flag=false;
        List<List<Integer>> contactIdfromApi=response.path("dealer.dealerContactPersons.contactId");
        List<Integer> contactIdApi=contactIdfromApi.get(0);

        List<List<Integer>> contactIdfromDatabase=returnListofActiveDealerContactIdsfromDatabase();
        List<Integer> contactIdDatabase=contactIdfromDatabase.get(0);

        for(int i=0;i<contactIdApi.size();i++){
            if(contactIdApi.get(i).equals(contactIdDatabase.get(i))){
                flag=true;
            }

        else {
                logger.info(contactIdApi.get(i)+"does not match with "+(contactIdDatabase.get(i)));
            flag=false;
        }
        }
        return flag;
    }

    public Response returnGetAllAttributeValuesApiResponse(){

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/lms/getAllRuleAttributeValue?attributeId=1").
                        then().
                        statusCode(200).
                        body("RuleAttributeAdd[0].id", notNullValue()).
                        body("RuleAttributeAdd[0].attrValueStr", notNullValue()).
                        body("RuleAttributeAdd[0].attrReferenceId", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.id", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.name", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.ruleAttributeType", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.referencedClassNamePath", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.serviceClassNamePath", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.referencedValueFieldName", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.referencedIdFieldName", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.qualifiedLeadField", notNullValue()).
                        body("RuleAttributeAdd[0].ruleAttribute.rawLeadField", notNullValue()).
                        extract().response();
        return response;
    }

    public List<String > returnIdsfromGetAllAttributeValuesApi(Response getAllAttributeValuesApiResponse){

        getAllAttributeValuesApiResponse=returnGetAllAttributeValuesApiResponse();
        List<String > ids=new ArrayList<>();
        ids.add(getAllAttributeValuesApiResponse.path("RuleAttributeAdd.id").toString());

        return ids;
    }

    public List<List<String>> getValuesofIdfromRuleAttrValueDatabase(){

        Database db=new Database();
        List<List<String>> idsfromDb=db.getListValuesFromDatabase("cars","SELECT id FROM `rule_attr_value` WHERE rule_attr_id='1'");
        return  idsfromDb;
    }

    public List<List<String>> getRuleAttrFromDatabase(Response response){

        Database db=new Database();
        List<List<String>> result=db.getListValuesFromDatabase("cars","SELECT * FROM `rule_attr` WHERE id='"+response.path("RuleAttributeAdd[0].id")+"'");
        return  result;
    }

    public List<String> getRuleAttrFromGetAllAttributeValuesApi(Response response){

        List<String> ruleAttrFromApi=new ArrayList<>();
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.id").toString());
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.name").toString());
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.ruleAttributeType").toString());
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.referencedClassNamePath").toString());
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.serviceClassNamePath").toString());
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.referencedValueFieldName").toString());
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.referencedIdFieldName").toString());
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.qualifiedLeadField").toString());
        ruleAttrFromApi.add(response.path("RuleAttributeAdd[0].ruleAttribute.rawLeadField").toString());

        return ruleAttrFromApi;
    }

    public Response returnRawLeadPostApiResponse(){

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject=null;
        try {

            Object obj = parser.parse(new FileReader(
                    "src/main/java/com/quikr/api/cars/carsApiJsonRequests/lms/RawLead.json"));

            jsonObject= (org.json.simple.JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().
                        post(apiUrl + "/cnb/lms/rawlead").
                        then().
                        body("RawLeadAdd.status", notNullValue()).
                        statusCode(200).extract().response();

        return response;
    }

    public int getUserIdfromCnbRawLeadDatabase(){

        Database db=new Database();
        int user_id=db.returnIntValuefromDatabase("cars","SELECT user_id FROM `cnb_raw_lead` WHERE ad_id='1400000000' order by id DESC LIMIT 1");
        return  user_id;
    }

    public List<List<String>> returnCnbLeaduserDetailsDatabase(){

        Database db=new Database();
        List<List<String>> user_details=db.getListValuesFromDatabase("cars", "SELECT * FROM `cars`.`cnb_lead_user` WHERE `id` = '" + getUserIdfromCnbRawLeadDatabase() + "' ");
        return  user_details;
    }
    public Response returnPostUpdateLeadStatus(String comment,String qualifiedId, String u_Id){

        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject=null;
        try {

            Object obj = parser.parse(new FileReader(
                    "src/main/java/com/quikr/api/cars/carsApiJsonRequests/lms/updateLeadStatus.json"));

            jsonObject= (org.json.simple.JSONObject) obj;
            jsonObject.put("qualifiedLeadId",qualifiedId);
            jsonObject.put("uId",u_Id);
            jsonObject.put("comment",comment);
            jsonObject.put("leadStatus","Test_Drive_Scheduled");
            jsonObject.put("actionTime","1464343066");
            logger.info("Trying with Json "+jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().
                        post(apiUrl + "/cnb/lms/qualifiedleads/updateLead").
                        then().
                        body("UpdateLeadStatus.status", notNullValue()).
                        statusCode(200).extract().response();

        return response;

    }

    public int getPermissionIdFromLms_Permission(){

        Database db=new Database();
        int permission_Id = db.returnIntValuefromDatabase("cars", "SELECT permission_id FROM `lms_permission` WHERE `permission_description`='Permission for Lead : ALL'");
        logger.info("permission_Id is "+permission_Id);
        return  permission_Id;
    }
    public void insertPermissionfortheUser(String u_Id, int permission_Id){

        Database db=new Database();
        try {
            db.InsertIntoTable("cars", "insert into `lms_user_permission` values('" + u_Id + "','" + permission_Id + "')");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<List<String>> returnCnb_qualified_lead_update(){

        Database db=new Database();
        List<List<String>> lead_id_and_u_id = db.getListValuesFromDatabase("cars", "SELECT lead_id ,u_id, comment FROM `cnb_qualified_lead_update` where u_id IS NOT NULL ORDER by id DESC LIMIT 1");
        logger.info("Lead id and Uid is "+lead_id_and_u_id);
        return  lead_id_and_u_id;
    }

    public boolean isCnb_qualified_Lead_Updated_In_Database(String comment){

        Database db=new Database();
        String commentinDb= db.returnStringValuefromDatabase("cars", "SELECT u_id FROM `cnb_qualified_lead_update` where comment='" + comment + "'");
        return  commentinDb.length()>0;
    }

    public Response returnGetCompleteDetails(int lead_id) {

        response =
                given().
                        header("X-Quikr-Client", "cars").
                        when().
                        get("/cnb/lms/qualifiedleads/getCompleteLeadDetails?leadId="+lead_id+"").
                        then().
                        statusCode(200).
                        body("getCompleteLeadDetails.status", notNullValue()).
                        body("getCompleteLeadDetails.QualifiedLead.id", notNullValue()).
                        body("getCompleteLeadDetails.QualifiedLead.vendorId", notNullValue()).
                        body("getCompleteLeadDetails.QualifiedLead.rawLeadId", notNullValue()).
                        body("getCompleteLeadDetails.QualifiedLead.make.makeId", notNullValue()).
                        body("getCompleteLeadDetails.QualifiedLead.cityId.id", notNullValue()).
                        extract().response();
        return response;
    }

    public List<List<Integer>> returnCnb_qualified_leadDetails(int lead_id){

        Database db=new Database();
        List<List<Integer>> lead_details= db.getListIntegerValuesFromDatabase("cars", "SELECT vendor_id,raw_lead_id,make_id,city_id,rule_id FROM `cnb_qualified_lead` WHERE id='" + lead_id + "'");
        return lead_details;
    }

    public Response returnGetPaginatedLeadsWithFilters(String u_Id,int pageSize,int pageNumber){

        org.json.simple.JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("sortKey", "CAMPAIGNNAME");
            jsonObject.put("sortOrder","desc");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response =
                given().
                        header("X-Quikr-Client", "cars").
                        body(jsonObject).with().contentType("application/json").
                        when().
                        post(apiUrl + "/cnb/lms/qualifiedleads/getPaginatedLeadsForUid?uid=" + u_Id + "&pageSize=" + pageSize + "&pageNumber=" + pageNumber + "").
                        then().
                        body("getAllQualifiedLeadsForUser.status", notNullValue()).
                        statusCode(200).extract().response();
        return response;
    }

    public int returnCnb_qualified_leadCount(){

        Database db=new Database();
        int lead_Count= db.returnIntValuefromDatabase("cars", "SELECT count(*) FROM `cnb_qualified_lead`");
        return lead_Count;
    }

    public List<List<Integer>> returnCnb_qualified_leadDetailsForPaginatedFromDatabase(int lead_id){

        Database db=new Database();
        List<List<Integer>> lead_details= db.getListIntegerValuesFromDatabase("cars", "SELECT vendor_id,raw_lead_id,make_id,model_id,city_id FROM `cnb_qualified_lead` WHERE id='"+lead_id+"'");
        return lead_details;
    }
}


