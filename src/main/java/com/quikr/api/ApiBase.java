package com.quikr.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * created by francis.s@quikr.com
 */
public class ApiBase
{

    static String URL = null;
    public static Properties testProperties = new Properties();
    public static Properties configProperties = new Properties();
    InputStream input = null;
    private final static Log LOGGER = LogFactory.getLog(ApiBase.class.getName());

    public ApiBase(String requestDataPath){

        initPropFiles(requestDataPath);
        URL= getEnvUrl (getConfigData("apiEnv"));
        LOGGER.info(requestDataPath);
    }

    /**
     * post method
     * @param path
     * @param jsonData
     * @param methodName
     * @return HttpResponse
     */

    public  HttpResponse postMethod(String path, String jsonData,String methodName) {

        HttpPost post = new HttpPost(URL+path);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("X-Quikr-Client", "DesktopSite");
        post.setHeader("Accept", "application/json");
        post.setHeader("X-Stream", "true");
        HttpResponse response = null;

        try {
            post.setEntity(new StringEntity(jsonData));
            HttpClient client = HttpClientBuilder.create().build();
            response=client.execute(post);

        } catch (Exception e) {
            LOGGER.info(methodName + " failed");
            e.printStackTrace();
        }
        return response;

    }
    public  HttpResponse postMethodEscrow(String path, String jsonData,String methodName) {

        HttpPost post = new HttpPost(path);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("X-Quikr-Client","Monetization.Api");
//        post.setHeader("X-Quikr-Client", "DesktopSite");
        post.setHeader("X-Quikr-Client", "Monetization.Api");
//        post.setHeader("Accept", "application/json");
//        post.setHeader("X-Stream", "true");
        HttpResponse response = null;

        try {
            post.setEntity(new StringEntity(jsonData));
            HttpClient client = HttpClientBuilder.create().build();
            response=client.execute(post);

        } catch (Exception e) {
            LOGGER.info(methodName + " failed");
            e.printStackTrace();
        }
        return response;

    }


    /**
     * get method
     * @param path
     * @param methodName
     * @return HttpResponse
     */
    public  HttpResponse getMethod(String path,String methodName) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL+path);

        // add request header
        request.addHeader("X-Quikr-Client", "DesktopSite");
//        request.addHeader("X-Quikr-Client","Monetization.Api");
        HttpResponse response=null;
        try {
            response = client.execute(request);
            LOGGER.info(methodName + " executed");
        } catch (Exception e) {
            LOGGER.info(methodName + " failed");
            e.printStackTrace();
        }
        return response;
    }

    private void initPropFiles(String requestDataPath) {

        try {
            input  = this.getClass().getClassLoader().getResourceAsStream("requestData/"+requestDataPath+".properties");
            testProperties.load(input);
            input = this.getClass().getClassLoader().getResourceAsStream("config.properties");
            configProperties.load(input);
        } catch (IOException exp) {

            exp.printStackTrace();
        }
    }

    /**
     * method to get static data for requests from requestData.properties
     * @param key
     * @return String
     */
    public  String getRequestData(String key){

        return testProperties.getProperty(key);

    }

    /**
     * method to get config data (urls)
     * @param key
     * @return String
     */
    public String getConfigData(String key){
        return configProperties.getProperty(key);
    }

    public void setConfigData(String key, String value)
    {
        configProperties.setProperty(key,value);
    }

    private String getEnvUrl(String env ){
        if(env.equalsIgnoreCase("dev")){
            return configProperties.getProperty("apidevUrl");
        }else if(env.equalsIgnoreCase("qa")){
            return configProperties.getProperty("apiQaUrl");
        }
       return null;
    }


    /**
     * parse httpResponse to JsonNode
     * @param response
     * @return JsonNode
     */
    public  JsonNode parseResponse(HttpResponse response) {

        BufferedReader rd = null;
        String code=null;
        JSONObject jsonObject=new JSONObject();
        JSONObject jsonerrorObject=new JSONObject();

        JsonNode node = null;

        try {
            code =  response.getStatusLine().getStatusCode()+"";


                rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {

                    result.append(line);
                    jsonObject = (JSONObject) JSONValue.parse(result.toString());
                    if (jsonObject == null) {
                        jsonerrorObject.put("errorMessage", result.toString());
                        jsonerrorObject.put("statusCode", code);
                        node = new ObjectMapper().readValue(jsonerrorObject.toString(), JsonNode.class);
                        return node;
                    }
                }


                jsonObject.put("statusCode",code);

            node = new ObjectMapper().readValue(jsonObject.toString(), JsonNode.class);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            //jsonObject.put("statsCode",code);
            e.printStackTrace();

        }
        return node;
    }

    public JsonNode parseResponseSingleValue(HttpResponse response){

        BufferedReader rd = null;
        String code=null;
        JSONObject jsonObject=new JSONObject();
        JSONObject jsonerrorObject=new JSONObject();

        JsonNode node = null;
        try {
            code =  response.getStatusLine().getStatusCode()+"";
            rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {

                result.append(line);

                jsonObject.put("TransactionId",result.toString());
                //jsonObject   = (JSONObject) JSONValue.parse(result.toString());
                if(jsonObject==null){
                    jsonerrorObject.put("errorMessage",result.toString());
                    //jsonerrorObject.put("statusCode",code);
                    //node = new ObjectMapper().readValue(jsonerrorObject.toString(), JsonNode.class);
                }
            }

            jsonObject.put("statusCode",code);

            node = new ObjectMapper().readValue(jsonObject.toString(), JsonNode.class);


        } catch (Exception e) {
            // TODO Auto-generated catch block

        }
        return node;
    }

}
