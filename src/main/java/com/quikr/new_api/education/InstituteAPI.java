package com.quikr.new_api.education;

import com.quikr.new_api.ApiBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Created by akhil.singla on 19/9/16.
 */
public class InstituteAPI extends ApiBase
{
    public String getFromElasticInstituteName(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("_source.instituteName");
    }

    public String getFromElasticInstituteId(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("_source.instituteId");
    }

    public String getFromElasticInstituteAddress(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("_source.address");
    }

    public String getFromElasticInstituteLocation(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("_source.location");
    }

    public String getFromElasticInstituteWebsite(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("_source.website");
    }

    public String getFromElasticAboutInstitute(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("_source.aboutInstitute");
    }

    public String getFromAPIInstituteName(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("instituteName");
    }

    public String getFromAPIInstituteId(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("instituteId");
    }

    public String getFromAPIInstituteAddress(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("address");
    }

    public String getFromAPIInstituteLocation(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("location");
    }

    public String getFromAPIInstituteWebsite(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("website");
    }

    public String getFromAPIAboutInstitute(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("aboutInstitute");
    }
}