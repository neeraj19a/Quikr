package com.quikr.new_api.education;

import com.quikr.new_api.ApiBase;
import com.quikr.utils.enums.api.education.CourseType;
import com.quikr.utils.enums.api.education.DegreeType;
import com.quikr.utils.enums.api.education.DurationUnit;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Created by akhil.singla on 21/9/16.
 */
public class CourseAPI extends ApiBase
{
    public String getFromElasticAffiliation(Response response, int courseId)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return (String)getValueForKeyFromList(jsonPath.getList("_source.courses"), "affiliation", "courseId", courseId);
    }

    public String getFromElasticEligibility(Response response, int courseId)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return (String)getValueForKeyFromList(jsonPath.getList("_source.courses"), "eligibility", "courseId", courseId);
    }

    public String getFromElasticCourseName(Response response, int courseId)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return (String)getValueForKeyFromList(jsonPath.getList("_source.courses"), "courseName", "courseId", courseId);
    }

    public String getFromElasticCourseType(Response response, int courseId)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        int value = (Integer)getValueForKeyFromList(jsonPath.getList("_source.courses"), "courseType", "courseId", courseId);
        return CourseType.getStringValue(value);
    }

    public String getFromElasticDegreeType(Response response, int courseId)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        int value = (Integer)getValueForKeyFromList(jsonPath.getList("_source.courses"), "degreeType", "courseId", courseId);
        return DegreeType.getStringValue(value);
    }

    public Integer getFromElasticCourseDuration(Response response, int courseId)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return (Integer)getValueForKeyFromList(jsonPath.getList("_source.courses"), "duration", "courseId", courseId);
    }

    public String getFromElasticDurationUnit(Response response, int courseId)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        int value = (Integer)getValueForKeyFromList(jsonPath.getList("_source.courses"), "durationUnit", "courseId", courseId);
        return DurationUnit.getStringValue(value);
    }

    public String getFromAPICourseId(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("instituteCourseDetails.courseId");
    }

    public String getFromAPIAffiliation(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("instituteCourseDetails.affiliation");
    }

    public String getFromAPIEligibility(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("instituteCourseDetails.eligibility");
    }

    public String getFromAPICourseName(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("courseDetails.courseName");
    }

    public String getFromAPICourseType(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("courseDetails.courseType");
    }

    public String getFromAPIDegreeType(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("courseDetails.degreeType");
    }

    public int getFromAPICourseDuration(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getInt("courseDetails.duration");
    }

    public String getFromAPIDurationUnit(Response response)
    {
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("");
        return jsonPath.getString("courseDetails.durationUnit");
    }
}