package com.quikr.api.education.educationCourseAPITests;

import com.quikr.api.education.Data;
import com.quikr.new_api.education.CourseAPI;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by akhil.singla on 22/9/16.
 */
public class EducationCourseAPITests extends CourseAPI
{
    private final static Log LOGGER = LogFactory.getLog(EducationCourseAPITests.class.getName());

    @Test(dataProvider = "generateCourseIds", dataProviderClass = Data.class)
    public void verifyCourseName(int instituteId, int courseId)
    {
        LOGGER.info("Verifying course API for institute id ::" + instituteId + " and course id:: " + courseId);
        SoftAssert sAssert = new SoftAssert();
        Response response = executeGetWithHeaders(new Headers(new Header("X-Quikr-Client", "Education")), "http://192.168.124.33:9055/institute/course/get?instituteId=" + instituteId + "&courseId=" + courseId);
        sAssert.assertTrue(response.statusCode() == 200, "Getting status code :: " + response.statusCode());
        Response elasticResponse = getElasticResult(instituteId);

        //verify course affiliation
        String affiliation = getFromAPIAffiliation(response);
        sAssert.assertNotNull(affiliation, "Getting affiliation as null for institute id ::" + instituteId + " and course id:: " + courseId);
        sAssert.assertTrue(affiliation.equals(getFromElasticAffiliation(elasticResponse, courseId)), "There is a mismatch betweeen affiliation in API and Elastic");

        //verify course eligibility
        String eligibility = getFromAPIEligibility(response);
        sAssert.assertNotNull(eligibility, "Getting eligibility as null for institute id ::" + instituteId + " and course id:: " + courseId);
        sAssert.assertTrue(eligibility.equals(getFromElasticEligibility(elasticResponse, courseId)), "There is a mismatch betweeen eligibility in API and Elastic");


        //verify course name
        String courseName = getFromAPICourseName(response);
        sAssert.assertNotNull(courseName, "Getting courseName as null for institute id ::" + instituteId + " and course id:: " + courseId);
        sAssert.assertTrue(courseName.equals(getFromElasticCourseName(elasticResponse, courseId)), "There is a mismatch betweeen courseName in API and Elastic");

        //verify course type
        String courseType = getFromAPICourseType(response);
        sAssert.assertNotNull(courseType, "Getting courseType as null for institute id ::" + instituteId + " and course id:: " + courseId);
        sAssert.assertTrue(courseType.equals(getFromElasticCourseType(elasticResponse, courseId)), "There is a mismatch betweeen courseType in API and Elastic");

        //verify degree type
        String degreeType = getFromAPIDegreeType(response);
        sAssert.assertNotNull(degreeType, "Getting degreeType as null for institute id ::" + instituteId + " and course id:: " + courseId);
        sAssert.assertTrue(degreeType.equals(getFromElasticDegreeType(elasticResponse, courseId)), "There is a mismatch betweeen degreeType in API and Elastic");

        //verify duration
        int duration = getFromAPICourseDuration(response);
        sAssert.assertNotNull(duration, "Getting duration as null for institute id ::" + instituteId + " and course id:: " + courseId);
        sAssert.assertTrue(duration == getFromElasticCourseDuration(elasticResponse, courseId), "There is a mismatch betweeen duration in API and Elastic");

        //verify duration unit name
        String durationUnit = getFromAPIDurationUnit(response);
        sAssert.assertNotNull(durationUnit, "Getting durationUnit as null for institute id ::" + instituteId + " and course id:: " + courseId);
        sAssert.assertTrue(durationUnit.equals(getFromElasticDurationUnit(elasticResponse, courseId)), "There is a mismatch betweeen durationUnit in API and Elastic");

        sAssert.assertAll();
    }
}
