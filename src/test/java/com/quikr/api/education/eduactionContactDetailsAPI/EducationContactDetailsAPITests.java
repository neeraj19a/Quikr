package com.quikr.api.education.eduactionContactDetailsAPI;

import com.quikr.api.education.Data;
import com.quikr.new_api.education.ContactDetailsAPI;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

/**
 * Created by akhil.singla on 27/9/16.
 */
public class EducationContactDetailsAPITests extends ContactDetailsAPI
{
    private final static Log LOGGER = LogFactory.getLog(EducationContactDetailsAPITests.class.getName());

    @Test//(dataProvider = "generateInstituteIds", dataProviderClass = Data.class)
    public void verifyContactDetails()
    {
        //fixing it to 2 for the time being
        int instituteType = 2;
        int instituteId =1;
        LOGGER.info("Verifying contact API for institute id ::" + instituteId + " and institute type:: " + instituteType);
        SoftAssert sAssert = new SoftAssert();
        Response response = executePostWithHeaders(new Headers(new Header("X-Quikr-Client", "Education"), new Header("Content-Type", "application/json")), "http://192.168.124.44:9055/education/getcontactdetails", new File("src/main/resources/requestJson/education/getContactDetails.json"));
        sAssert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
        Response elasticResponse = getElasticResult(instituteId);

        //verify contact name
        String contactName = getFromAPIContactName(response);
        sAssert.assertNotNull(contactName, "Getting contactName as null for institute id ::" + instituteId + " and institute type:: " + instituteType);
        sAssert.assertTrue(contactName.equals(getFromElasticContactName(elasticResponse)), "There is a mismatch betweeen contact name in API and Elastic");

        //verify contact email
        String contactEmail = getFromAPIEmail(response);
        sAssert.assertNotNull(contactEmail, "Getting email as null for institute id ::" + instituteId + " and institute type:: " + instituteType);
        sAssert.assertTrue(contactEmail.equals(getFromElasticEmail(elasticResponse)), "There is a mismatch betweeen email in API and Elastic");

        sAssert.assertAll();
    }
}