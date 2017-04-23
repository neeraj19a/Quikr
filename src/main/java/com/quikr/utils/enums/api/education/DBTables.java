package com.quikr.utils.enums.api.education;

/**
 * Created by akhil.singla on 27/9/16.
 */
public enum DBTables
{
    ABROAD_CIY_DATA("abroad_city_data"),
    ABROAD_COUNTRY_COURSE_DATA("abroad_country_course_data"),
    ABROAD_COUNTRY_DETAILS("abroad_country_details"),
    ABROAD_COUNTRY_EXAM_MAPPING("abroad_country_exam_mapping"),
    ABROAD_COURSE_CATEGORY("abroad_course_category"),
    ABROAD_COURSE_EXAM_MAPPING("abroad_course_exam_mapping"),
    ABROAD_COURSE_MASTER("abroad_course_master"),
    ABROAD_INSTITUTE_ALUMINI_DETAILS("abroad_institute_alumini_details"),
    ABROAD_INSTITUTE_CONTACT_DETAILS("abroad_institute_contact_details"),
    ABROAD_INSTITUTE_COURSE_MAPPING("abroad_institute_course_mapping"),
    ABROAD_INSTITUTE_EXAM_MAPPING("abroad_institute_exam_mapping"),
    ABROAD_INSTITUTE_MASTER("abroad_institute_master"),
    ABROAD_INSTITUTE_MEDIA("abroad_institute_media"),
    ABROAD_INSTITUTE_USER_DETAILS("abroad_institute_user_details"),
    BUSINESS_CONTACT_DETAILS("business_contact_details"),
    BUSINESS_LISTINGS("business_listings"),
    BUSINESS_SERVICE_MAPPING("business_service_mapping"),
    BUSINESS_SERVICE_LIST("business_services_list"),
    BUSINESS_USER_DETAILS("business_user_details"),
    COURSE_CATEGORY("course_category"),
    COURSE_EXAM_MAPPING("course_exam_mapping"),
    COURSE_MASTER("course_master"),
    ENTRANCE_EXAMS("entrance_exams"),
    FACILITY_MASTER("facility_master"),
    INSTITUTE_CONTACT_DETAIL("institute_contact_detail"),
    INSTITUTE_COURSE_MAPPING("institute_course_mapping"),
    INSTITUTE_FACILITIES("institute_facilities"),
    INSTITUTE_MASTER("institute_master"),
    INSTITUTE_PHOTO_BROUCHRE("institute_photo_brochure"),
    PRESCHOOL_CONTACT_DETAILS("preschool_contact_details"),
    PRESCHOOL_FACILITIES("preschool_facilities"),
    PRESCHOOL_MASTER("preschool_master"),
    SCHOOL_BOARD_MAPPING("school_board_mapping"),
    SCHOOL_CONTACT_DETAILS("school_contact_details"),
    SCHOOL_FACILITIES("school_facilities"),
    SCHOOL_MASTER("school_master"),
    TOP_RECRUITERS("top_recruiters"),
    USER_DETAILS("user_details");

    private String realName;
    private DBTables(String realName)
    {
        this.realName = realName;
    }

    public String toString()
    {
        return this.realName;
    }
}
