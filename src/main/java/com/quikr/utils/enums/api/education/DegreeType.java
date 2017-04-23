package com.quikr.utils.enums.api.education;

/**
 * Created by akhil.singla on 26/9/16.
 */
public enum DegreeType
{
    DUAL_DEGREE("Dual Degree", 1),

    DIPLOMA("Diploma", 2),

    DOCTORATE_DEGREE("Doctorate Degree", 3),

    POST_GRADUATE_DEGREE("Post Graduate Degree", 4),

    POST_DOCTORATE_DEGREE("Post Doctorate Degree", 5),

    UNDER_GRADUATE_DEGREE("Under Graduate Degree", 6),

    CERTIFICATION("Certification", 7),

    VOCATIONAL("Vocational", 8),

    POST_GRADUATE_DIPLOMA("Post Graduate Diploma", 9);

    private String realName;
    private int value;
    private DegreeType(String realName, int value)
    {
        this.realName = realName;
        this.value = value;
    }

    public String toString()
    {
        return this.realName;
    }

    public int getIntValue()
    {
        return value;
    }

    public static String getStringValue(int value)
    {
        switch (value)
        {
            case 1:
                return DUAL_DEGREE.toString();
            case 2:
                return DIPLOMA.toString();
            case 3:
                return DOCTORATE_DEGREE.toString();
            case 4:
                return POST_GRADUATE_DEGREE.toString();
            case 5:
                return POST_DOCTORATE_DEGREE.toString();
            case 6:
                return UNDER_GRADUATE_DEGREE.toString();
            case 7:
                return CERTIFICATION.toString();
            case 8:
                return VOCATIONAL.toString();
            case 9:
                return POST_GRADUATE_DIPLOMA.toString();
        }

        return null;
    }
}