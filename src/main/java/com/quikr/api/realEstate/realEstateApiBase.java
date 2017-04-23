package com.quikr.api.realEstate;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by quikr on 2/12/15.
 */
public class realEstateApiBase {

    //static String apiUrl="http://192.168.124.53:9000/realestate/v1/amenities?id=896";

    public String getPropValues(String apiname)
    {
        Properties apiproperties=new Properties();
        InputStream inputStream=null;
        String propertyfile="realEstateAPI.properties";
        String requestURL="";

        inputStream=realEstateApiBase.class.getClassLoader().getResourceAsStream("requestData/"+propertyfile);
        if(inputStream==null) {
            System.out.println("Sorry, unable to find property file " + propertyfile);
            return null;
        }
        try {
            apiproperties.load(inputStream);
            System.out.println("http://"+apiproperties.get("domain")+""+apiproperties.get(apiname));
            requestURL="http://"+apiproperties.get("domain")+""+apiproperties.get(apiname);
            return requestURL;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream!=null)
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
        }
        return null;
    }
    public ArrayList executeGet(String apiUrl)
    {

        HttpClient httpClient=new DefaultHttpClient();
        HttpGet request=new HttpGet(apiUrl);
        ArrayList<Object> listOfObjects = new ArrayList<Object>();

        int responseStatus;

        request.addHeader("X-Quikr-Client", "REALESTATE.DESKTOP");

        try {
            HttpResponse httpResponse=httpClient.execute(request);
            System.out.println("Sending Get request to URL "+apiUrl);

            responseStatus=httpResponse.getStatusLine().getStatusCode();
            System.out.println("Response Code of the API " + responseStatus);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line = "";

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println("Respone Result of the API " + result);

            JSONObject responseObject=new JSONObject(result.toString());
            if((responseStatus!=0)||(responseObject!=null)) {
                listOfObjects.add(responseStatus);
                listOfObjects.add(responseObject);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return listOfObjects;

    }
   /* public static void main(String args[])
    {
        apiUrl=getPropValues("projectsnippetapi");
        executeGet(apiUrl);
    }*/
}
