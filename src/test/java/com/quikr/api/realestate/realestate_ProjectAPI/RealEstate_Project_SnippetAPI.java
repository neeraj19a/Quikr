package com.quikr.api.realestate.realestate_ProjectAPI;

import com.quikr.api.realEstate.realEstateApiBase;
import org.testng.Assert;

import java.util.ArrayList;
/**
 * Created by quikr on 2/12/15.
 */
public class RealEstate_Project_SnippetAPI extends realEstateApiBase {

    String apiUrl="";
    String statusCode="";
    org.json.JSONObject apiresponse;
    org.json.JSONObject dataresponse;
    String apiMessage="";
    Integer projectID;
    String projectName="";
    String latitude="";
    String longitude="";
    String imageUrl="";
    String cityAddress="";
    String localityAddress="";
    String cityId="";
    String category="";
    String description="";
    ArrayList<Object> response=new ArrayList<Object>();

    public static void main(String[] args)
    {
        RealEstate_Project_SnippetAPI realEstateObject=new RealEstate_Project_SnippetAPI();
        realEstateObject.requestProjectAPI();
        realEstateObject.ValidateProjectAPIResponse();
    }
    public void requestProjectAPI()
    {
        if((apiUrl.isEmpty()) || (apiUrl==null)) {
            apiUrl = getPropValues("projectsnippetapi");
            //**************Executing the Http Get Request to the Project API*****response holds the ArrayList with responseStatus & responseObject
            response = executeGet(apiUrl);
        }
        statusCode=response.get(0).toString();
        apiresponse=(org.json.JSONObject)response.get(1);
        Assert.assertEquals(statusCode, "200");
        dataresponse=(org.json.JSONObject)apiresponse.get("data");


    }
    public void ValidateProjectAPIResponse()
    {
        System.out.println("apiresponse" + apiresponse);
        //Assert.assertEquals(getApiMessage(), "success");
        Assert.assertTrue(getProjectID() != 0 || getProjectID() != null);
        Assert.assertNotNull(getProjectName());

        Assert.assertNotNull(getLocalityAddress());
        Assert.assertNotNull(getCityAddress());
        Assert.assertNotNull(getCityId());

    }

    public String getApiMessage() {
        apiMessage=apiresponse.getString("message");
        return apiMessage;
    }

    public Integer getProjectID() {
        projectID=dataresponse.getInt("id");
        return projectID;
    }

    public String getProjectName() {
        projectName=dataresponse.getString("name");
        return projectName;
    }

    public String getLatitude() {
        latitude=dataresponse.getString("latitude");
        return latitude;
    }

    public String getLongitude() {
        longitude=dataresponse.getString("longitude");
        return longitude;
    }

    public String getImageUrl() {
        imageUrl=dataresponse.getString("imageUrl");
        return imageUrl;
    }

    public String getCityAddress() {
        org.json.JSONObject addressjson=(org.json.JSONObject)dataresponse.get("address");
        cityAddress=addressjson.getString("city");

        return cityAddress;
    }

    public String getLocalityAddress() {
        org.json.JSONObject addressjson=(org.json.JSONObject)dataresponse.get("address");
        localityAddress=addressjson.getString("locality");
        return localityAddress;
    }

    public String getCityId() {
        org.json.JSONObject addressjson=(org.json.JSONObject)dataresponse.get("address");
        cityId=addressjson.getString("cityId");
        return cityId;
    }

    public String getCategory() {
        category=dataresponse.getString("category");
        return category;
    }

    public String getDescription() {
        description=dataresponse.getString("description");
        return description;
    }
}
