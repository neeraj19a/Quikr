package com.quikr.utils.enums.api.education;

/**
 * Created by akhil.singla on 26/9/16.
 */
public enum CourseType
{
    CORRESPONDENCE("Correspondence", 1),

    PART_TIME("Part Time", 2),

    eLEARNING("E-learning", 3),

    FULL_TIME("Full Time", 4),

    EXECUTIVE("Executive", 5);

    private String realName;
    private int value;
    private CourseType(String realName, int value)
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
                return CORRESPONDENCE.toString();
            case 2:
                return PART_TIME.toString();
            case 3:
                return eLEARNING.toString();
            case 4:
                return FULL_TIME.toString();
            case 5:
                return EXECUTIVE.toString();
        }

        return null;
    }
}
