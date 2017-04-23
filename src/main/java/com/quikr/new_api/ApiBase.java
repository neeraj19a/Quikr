package com.quikr.new_api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

/**
 * Created by akhil.singla on 8/9/16.
 */
public class ApiBase
{
    public Response executeGet(String url)
    {
        return get(url);
    }

    public Response executeGetWithHeaders(Headers headers, String url)
    {
        return given().headers(headers).when().get(url);
    }

    public Response executeGetWithParameters(HashMap<String, ?> params, String url)
    {
        return given().params(params).when().get(url);
    }

    public JsonNode convertToJsonNode(File file)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, JsonNode.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public Response executePostWithHeaders(Headers headers, String url, File file)
    {
        return given().headers(headers).body(file).when().post(url);
    }

    public Response getElasticResult(int i)
    {
        return executeGet("http://192.168.124.44:9211/education/instituteMaster/" + i);
    }

    public Object getValueForKeyFromList(List<Object> list, String keyName, String identifierKey, String identifierValue)
    {
        for(Object map : list)
        {
            if(((HashMap)map).get(identifierKey).equals(identifierValue))
            {
                return ((HashMap)map).get(keyName);
            }
        }

        return null;
    }

    public Object getValueForKeyFromList(List<Object> list, String keyName, String identifierKey, int identifierValue)
    {
        for(Object map : list)
        {
            int value = ((HashMap<Object, Integer>) map).get(identifierKey).intValue();
            if( value == identifierValue)
            {
                return ((HashMap)map).get(keyName);
            }
        }

        return null;
    }
}