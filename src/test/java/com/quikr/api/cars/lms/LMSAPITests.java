package com.quikr.api.cars.lms;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.quikr.api.cars.CarsApiBase;
import com.quikr.api.cars.LMSAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 4/7/16.
 */
public class LMSAPITests {


    public static Response response;
    CarsApiBase carsApiBase = new CarsApiBase();

    private HashMap<String, String> testData = getTestData(getProperties().get("LMSAPI_TESTDATA_FILE"));

    @Test
    public void testGetAllCitiesApi() throws Exception {
        RestAssured.baseURI = "http://192.168.124.53:9000";
        LMSAPI lmsapi= new LMSAPI();

        response=lmsapi.returnGetAllCitiesApiResponse();

        Assert.assertEquals(lmsapi.validateTopCitiesApiResponse(response),17,"Looks Like Top City are not coming in proper exact number");
        Assert.assertTrue(lmsapi.validateAllCitiesApiResponse(response)==963 || lmsapi.validateAllCitiesApiResponse(response)==962,"Looks Like All Cities are not coming in proper exact number");
        Assert.assertTrue(lmsapi.validateIdandCityNamefromDatabase(response),"Looks Like Proper Api Response and DB Response is not matching");
    }

    @Test
    public void testLoginUserApi() throws Exception {
        LMSAPI lmsapi= new LMSAPI();

        response=lmsapi.returnPostLoginUserResponse();
        Assert.assertEquals(lmsapi.getUidfromLoginUserApi(response), lmsapi.getuIdLmsUserDatabase(testData.get("lmsUser")), "Looks Like Login user is not returning Proper Response Pls Check");
    }

    @Test
    public void testSignUpUserApi() throws Exception {
        LMSAPI lmsapi= new LMSAPI();

        response=lmsapi.returnPostSignUpUserResponse();
        String uidfromApi=lmsapi.returnUidfromSignUpuserApi(response);
        Assert.assertTrue(lmsapi.checkUidofUserPresentinDatabase(response), "Looks like User is not getting stored in the Database with Sepcified UID");
    }


    @Test
    public void testCreatePermissionApi(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        response=lmsapi.returnCreatePermissionApiResponse();
        Assert.assertTrue(lmsapi.returnStatusCreatePermissionApiResponse(response), "Looks Like Create Permission is not working");
    }

    @Test
    public void testCreateRoleApi(){

        LMSAPI lmsapi=new LMSAPI();

        int availablePermission=lmsapi.returnAvailableMakePermissionIdsFromDatabase();
        response=lmsapi.getCreateRoleApiResponse(availablePermission);
        int roleIdfromApi=lmsapi.returnRoleIdfromApi(response);
        int roleIdfromDatabase=lmsapi.returnRoleIdCoresspondingPermissionIdDatabase(availablePermission);

        Assert.assertEquals(roleIdfromApi, roleIdfromDatabase, "Looks Like the Role is not getting created in Table 'lms_role_permission' ");
    }

    @Test
    public void testGetAllPermissionApi(){

        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        response=lmsapi.returnGetAllPermissionApiResponse();
        //Getting permissionDescription from Api Response
        String permissionDescriptionfromApi=lmsapi.getPermissionDetailsFromApi(response).get("permissionDescription").toString();
        List<String> dbResponse=lmsapi.getPermissionDetailsFromDatabase(response).get(0);

        //Getting permissionDescription from Database
        String permissionDescriptionfromDatabase=dbResponse.get(2);
        Assert.assertTrue(permissionDescriptionfromApi.contains(permissionDescriptionfromDatabase), "Looks like Response is not proper reflected from Database");
    }

    @Test
    public void testAssignRolesApi(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        String uId=lmsapi.returnUIdfromDatabase();
        String roleId=lmsapi.returnRoleIdfromLmsRolePermissionDatabase();

        response=lmsapi.postAssignRoleResponse(uId,roleId);
        Assert.assertTrue(lmsapi.returnStatusPostAssignRoleResponse(response),"Looks like Either Status is not True in API response or Role is not getting assigned");
        int roleIdfromDatabase=lmsapi.returnRoleIdfromLmsUserRoleCorrespondingtoUid(uId);
        Assert.assertEquals(Integer.parseInt(roleId), roleIdfromDatabase, "Looks Like Role is not getting Associated with Uid in lms_user_role Table");
    }

    @Test
    public void testChangePasswordApi(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        String newPassword="1234567";
        response=lmsapi.postChangePassword(newPassword);
        String newPasswordInDatabase=lmsapi.returnPasswordofLmsuserfromDatabase(testData.get("lmsUserforTesting"));
        Assert.assertEquals(newPassword,newPasswordInDatabase,"Looks like New Password is not getting saved in Database Pls Check");

    }

    @Test
    public void testGetPermissionByUserIdApi(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        String uId=lmsapi.getuIdLmsUserDatabase(testData.get("lmsUserforTesting"));
        response=lmsapi.returnGetPermissionByUserIdApiResponse(uId);
        int roleIdFromlms_user_role=lmsapi.returnRoleIdfromLmsUserRoleCorrespondingtoUid(uId);
        int roleIdFromApi=response.path(testData.get("GetPermissionByUserIdroleId"));
        Assert.assertEquals(roleIdFromApi,roleIdFromlms_user_role,"Looks Like Role is not matching between Datbase and API");
        int permissionId=response.path(testData.get("GetPermissionByUserIdpermissionId"));
        int roleIdFromlms_role_permission=lmsapi.returnRoleIdCoresspondingPermissionIdDatabase(permissionId);
        Assert.assertEquals(roleIdFromApi,roleIdFromlms_role_permission,"Permission Id is not macthing between DB and API");
    }

    @Test
    public void testAssignPermissionToUserApi(){

        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        String u_id=lmsapi.getuIdLmsUserDatabase(testData.get("lmsUserforTesting"));
        int[] permissionsNegativeCase={0,0};
        response=lmsapi.returnPostAssignPermissionToUserApi(u_id,permissionsNegativeCase);
        System.out.println(response.path("AssignPermissionsToUserResponse"));
        String checkStatusFalse=response.path("AssignPermissionsToUserResponse.errors[0].code");
        Assert.assertEquals(checkStatusFalse, "INVALID_MODEL_PERMISSION", "Permission is getting Assigned to User even with only Model Permission");

        int[] permissions={1,3};
        response=lmsapi.returnPostAssignPermissionToUserApi(u_id,permissions);
        boolean checkStatusTrue=(boolean)response.path("AssignPermissionsToUserResponse.AssignPermissionsToUserData.status");
        Assert.assertTrue(checkStatusTrue, "Status is not coming True in API,Looks like Permission Is Not getting Assigned to User");
    }

    @Test
    public  void testGetCreateBulkPermission(){

        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        response=lmsapi.getCreateBulkPermissionResponse();
        boolean status=response.path(testData.get("GetBulkCreatePermissionsResponseStatus"));
        Assert.assertTrue(status,"Looks like Create Bulk Permission API is not working Pls Check");
    }

    @Test
    public void testGetMakeswithId(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        response=lmsapi.getMakeswithId();
        List<String> listofMakesFromApi=response.path("makes.make");
        List<List<String>> cool=lmsapi.getAllMakefromCarMakeDatabase();
        ArrayList<String> listofMakesfromDb=new ArrayList<>();

        for (int i=0;i<=cool.size()-1;i++){
            listofMakesfromDb.add(cool.get(i).get(0));
        }

        Collections.sort(listofMakesfromDb);
        Collections.sort(listofMakesFromApi);

        Assert.assertTrue(listofMakesFromApi.equals(listofMakesfromDb),"Response of Api And Database is not matching");
    }

    @Test
    public void testGetModelswithIdApi(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        response=lmsapi.returnGetModelswithIdResponse();

        List<Integer> modelIdfromApi=lmsapi.returnModelIdsfromGetModelswithIdApi(response);
        List<List<Integer >> modelsffromDatabase=lmsapi.returnModelIdsfromDatabase();

        List<Integer> listofModelIdfromDb=new ArrayList<>();

        for (int i=0;i<=modelsffromDatabase.size()-1;i++){
            listofModelIdfromDb.add(modelsffromDatabase.get(i).get(0));
        }
        boolean flag=lmsapi.responseofGetModelswithIdApiAndDatbaseAreMatching();
        Assert.assertTrue(flag, "Look like All Values Between Database and Api are not matching");
        Assert.assertTrue(modelIdfromApi.equals(listofModelIdfromDb),"Looks Like Api not Returning Proper Model Id , Ad Model Id in Api Rewsponse are not matching with Database response");
    }

    @Test
    public void testGetActiveDealersApi(){

        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        response= lmsapi.returnGetActiveDealersApiResponse();
        List<Integer> listofDealersIdFromApi=lmsapi.returnListofActiveDealersIdfromApi(response);
        List<Integer> listofDealersFromDatabase=lmsapi.returnListofActiveDealerIdsfromDatabase();
        Assert.assertTrue(listofDealersIdFromApi.equals(listofDealersFromDatabase), "Look like All Values Between Database and Api are not matching");

       Assert.assertTrue(lmsapi.isApiResponseandDatabaseResponseMatching(), "Looks like Values of Api and Database Contact Ids are not matching");
    }

    @Test
    public void testGetAllAttributeValues(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        response=lmsapi.returnGetAllAttributeValuesApiResponse();
        List<String> idsfromApi=lmsapi.returnIdsfromGetAllAttributeValuesApi(response);

        //To Get Ids from String idsfromApi.get(0)
        String[] idsSplitApi=idsfromApi.get(0).split(",");
        List<List<String>> idsfromDb=lmsapi.getValuesofIdfromRuleAttrValueDatabase();
        List<String> ids=new ArrayList<>();
        for (int i=0;i<idsfromDb.size()-1;i++){
            ids.add(idsfromDb.get(i).get(0));
        }

        int numberOfIdsfromDatabase=ids.size();
        //Checking Equal number of Ids returened from Database and Api
        Assert.assertEquals(idsSplitApi.length, numberOfIdsfromDatabase + 1, "Looks like the response of Api and Database is not matching");

        List<String> getRuleAttributeDatabase=lmsapi.getRuleAttrFromDatabase(response).get(0);
        List<String> getRuleAttrFromGetAllAttributeValuesApi=lmsapi.getRuleAttrFromGetAllAttributeValuesApi(response);
        //Checking if Same Rule Attr returned from Api and Database
        Assert.assertEquals(getRuleAttrFromGetAllAttributeValuesApi, getRuleAttributeDatabase, "Values of Api and Database are not matching for ruleAttribute");
    }

    @Test
    public void testRawLeadApi(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        response=lmsapi.returnRawLeadPostApiResponse();
        Assert.assertEquals(response.path("RawLeadAdd.status"), "success", "Looks Like Raw Lead is not getting created");
        List<String> user_detailsDatabase=lmsapi.returnCnbLeaduserDetailsDatabase().get(0);
        Assert.assertTrue(user_detailsDatabase.get(4).equals("email1@test.com"), "Looks Like record is not getting stored properly");
    }

    @Test
    public void testUpdateLeadStatus(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        String randomComment=carsApiBase.getRandomString(10);
        int permission_id=lmsapi.getPermissionIdFromLms_Permission();
        String u_Id=lmsapi.returnCnb_qualified_lead_update().get(0).get(1);
        lmsapi.insertPermissionfortheUser(u_Id,permission_id);
        String qualified_Id=lmsapi.returnCnb_qualified_lead_update().get(0).get(0);
        response=lmsapi.returnPostUpdateLeadStatus(randomComment,qualified_Id,u_Id);
        boolean isapiResponseSuccess=(boolean)response.path("UpdateLeadStatus.success");
        Assert.assertTrue(isapiResponseSuccess,"Looks like Api Response is not success, Pls Check");
        Assert.assertTrue(lmsapi.isCnb_qualified_Lead_Updated_In_Database(randomComment),"Looks like Lead is not getting updated as Value is not updated in table cnb_qualified_lead_update`");

    }

    @Test
    public void testGetCompleteDetails(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        int lead_id=1;
        response=lmsapi.returnGetCompleteDetails(lead_id);
        List<Integer> leadDetailsFromApi=new ArrayList<>();
        leadDetailsFromApi.add((Integer)response.path("getCompleteLeadDetails.QualifiedLead.vendorId"));
        leadDetailsFromApi.add((Integer) response.path("getCompleteLeadDetails.QualifiedLead.rawLeadId"));
        leadDetailsFromApi.add((Integer)response.path("getCompleteLeadDetails.QualifiedLead.make.makeId"));
        leadDetailsFromApi.add((Integer) response.path("getCompleteLeadDetails.QualifiedLead.cityId.id"));
        leadDetailsFromApi.add((Integer) response.path("getCompleteLeadDetails.QualifiedLead.ruleId"));

        List<Integer> leadDetailsfromDb=lmsapi.returnCnb_qualified_leadDetails(lead_id).get(0);
        Assert.assertTrue(leadDetailsFromApi.equals(leadDetailsfromDb),"Looks like the response from Db and Api are not matching");
    }

    @Test
    public void testGetPaginatedLeadswithFilters(){
        RestAssured.baseURI = carsApiBase.apiUrl;
        LMSAPI lmsapi=new LMSAPI();

        String emailId="admin@quikr.com";
        String u_id=lmsapi.getuIdLmsUserDatabase(emailId);
        response=lmsapi.returnGetPaginatedLeadsWithFilters(u_id,100,0);
        String statusFromApi=response.path("getAllQualifiedLeadsForUser.status");
        Assert.assertEquals(statusFromApi, "Ok", "Looks like Status is not Ok in Api response pls check");
        int qualified_Lead_countfromDatabase=lmsapi.returnCnb_qualified_leadCount();
        int qualifiedLeadcountFromApi=(Integer)response.path("getAllQualifiedLeadsForUser.QualifiedLeadsCount");
        Assert.assertEquals(qualified_Lead_countfromDatabase, qualifiedLeadcountFromApi, "Looks like the count of Qualified lead from Api and Database is not matching");

        int lead_Id=((Integer)response.path("getAllQualifiedLeadsForUser.QualifiedLeads[0].id"));

        List<Integer > qualifiedLeadDetailsfromApi=new ArrayList<>();
                qualifiedLeadDetailsfromApi.add((Integer) response.path("getAllQualifiedLeadsForUser.QualifiedLeads[0].vendor_id"));
                qualifiedLeadDetailsfromApi.add((Integer) response.path("getAllQualifiedLeadsForUser.QualifiedLeads[0].raw_lead_id"));
                qualifiedLeadDetailsfromApi.add((Integer) response.path("getAllQualifiedLeadsForUser.QualifiedLeads[0].make.makeId"));
                qualifiedLeadDetailsfromApi.add((Integer) response.path("getAllQualifiedLeadsForUser.QualifiedLeads[0].model.modelId"));
                qualifiedLeadDetailsfromApi.add((Integer) response.path("getAllQualifiedLeadsForUser.QualifiedLeads[0].city.id"));

        List<Integer> qualifiedLeadDetailsfromDatabase=lmsapi.returnCnb_qualified_leadDetailsForPaginatedFromDatabase(lead_Id).get(0);
        Assert.assertTrue(qualifiedLeadDetailsfromApi.equals(qualifiedLeadDetailsfromDatabase),"Looks like Qualified Details between Api and Database is not matching");
    }
}