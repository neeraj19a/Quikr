package com.quikr.api.education.educationAPI;

import com.quikr.new_api.ApiBase;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by akhil.singla on 9/9/16.
 */
public class EducationAPITests extends ApiBase
{
    @Test
    public void verifyCategory()
    {
        Response response = executeGetWithHeaders(new Headers(new Header("X-Quikr-Client", "Education")),"http://192.168.124.44:9055/course/allcategory?categoryId=1");
        Assert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
    }

    @Test
    public void verifyBusinessListingDetail()
    {
        Response response = executeGetWithHeaders(new Headers(new Header("X-Quikr-Client", "Education")), "http://192.168.124.44:9055/businesslisting/get?businessId=1");
        Assert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
    }

    @Test
    public void verifyContactDetails()
    {
        Response response = executePostWithHeaders(new Headers(new Header("X-Quikr-Client", "Education"), new Header("Content-Type", "application/json")), "http://192.168.124.44:9055/education/getcontactdetails", new File("src/main/resources/requestJson/education/getContactDetails.json"));
        Assert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
    }

    @Test
    public void verifySearch()
    {
        Response response = executePostWithHeaders(new Headers(new Header("X-Quikr-Client", "Education"), new Header("Content-Type","application/json")), "http://192.168.124.44:9055/education/search", new File("src/main/resources/requestJson/education/educationSearch.json"));
        Assert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
    }

    @Test
    public void verifyBusinessListingContactDetails()
    {
        Response response = executePostWithHeaders(new Headers(new Header("X-Quikr-Client", "Education"), new Header("Content-Type","application/json")), "http://192.168.124.44:9055/businesslisting/getcontactdetails", new File("src/main/resources/requestJson/education/businessListingContactDetails.json"));
        Assert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
    }

    @Test
    public void verifyDownloadBroucher()
    {
        Response response = executePostWithHeaders(new Headers(new Header("X-Quikr-Client", "Education"), new Header("Content-Type","application/json")), "http://192.168.124.44:9055/institute/downloadbrochure", new File("src/main/resources/requestJson/education/instituteDownloadBrochure.json"));
        Assert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
    }
}