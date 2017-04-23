package com.quikr.api.education.educationInstituteAPITests;

import com.quikr.api.education.Data;
import com.quikr.new_api.education.InstituteAPI;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by akhil.singla on 19/9/16.
 */
public class EducationInstituteAPITests extends InstituteAPI
{
    private final static Log LOGGER = LogFactory.getLog(EducationInstituteAPITests.class.getName());

    @Test(dataProvider = "generateInstituteIds", dataProviderClass = Data.class)
    public void verifyInstituteAPI(int instituteId)
    {
        LOGGER.info("Verifying institute API for institute id :: " + instituteId);
        SoftAssert sAssert = new SoftAssert();
        Response response = executeGetWithHeaders(new Headers(new Header("X-Quikr-Client", "Education")),"http://192.168.124.33:9055/education/get?id=" + instituteId+ "&instituteType=2");
        sAssert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
        Response elasticResponse = getElasticResult(instituteId);

        //verify institute name
        String instituteName = getFromAPIInstituteName(response);
        sAssert.assertNotNull(instituteName, "Getting instituteName as null for institute id :: " + instituteId);
        sAssert.assertTrue(instituteName.equals(getFromElasticInstituteName(elasticResponse)), "There is a mismatch between instituteName in Elastic and API response");

        //verify institute id
        String id = getFromAPIInstituteId(response);
        sAssert.assertNotNull(id, "Getting instituteId as null for institute id :: " + instituteId);
        sAssert.assertTrue(id.equals(getFromElasticInstituteId(elasticResponse)), "There is a mismatch between InstituteId in Elastic and API response");

        //verify institute address
        String instituteAddress = getFromAPIInstituteAddress(response);
        sAssert.assertNotNull(instituteAddress, "Getting instituteAddress as null for institute id :: " + instituteId);
        sAssert.assertTrue(instituteAddress.equals(getFromElasticInstituteAddress(elasticResponse)), "There is a mismatch between instituteAddress in Elastic and API response");

        //verify institute location
        String instituteLocation = getFromAPIInstituteLocation(response);
        sAssert.assertNotNull(instituteLocation, "Getting instituteLocation as null for institute id :: " + instituteId);
        sAssert.assertTrue(instituteLocation.equals(getFromElasticInstituteLocation(elasticResponse)), "There is a mismatch between instituteLocation in Elastic and API response");

        //verify institute website
        String instituteWebsite = getFromAPIInstituteWebsite(response);
        sAssert.assertNotNull(instituteWebsite, "Getting instituteWebsite as null for institute id :: " + instituteId);
        sAssert.assertTrue(instituteWebsite.equals(getFromElasticInstituteWebsite(elasticResponse)), "There is a mismatch between instituteWebsite in Elastic and API response");

        //verify about institute
        String instituteAbout = getFromAPIAboutInstitute(response);
        sAssert.assertNotNull(instituteAbout, "Getting 'About Institute' as null for institute id :: " + instituteId);
        sAssert.assertTrue(instituteAbout.equals(getFromElasticAboutInstitute(elasticResponse)), "There is a mismatch between 'About Institute' in Elastic and API response");

        sAssert.assertAll();
    }
}