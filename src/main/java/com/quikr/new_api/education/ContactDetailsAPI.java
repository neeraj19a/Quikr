package com.quikr.new_api.education;

import com.quikr.new_api.ApiBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Created by akhil.singla on 25/9/16.
 */
public class ContactDetailsAPI extends ApiBase
{
    public String getFromElasticContactName(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("_source.contactDetails.contactName");
    }

    public String getFromElasticEmail(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("_source.contactDetails.email");
    }

    public String getFromAPIContactName(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("contactName");
    }

    public String getFromAPIEmail(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("email");
    }
}
