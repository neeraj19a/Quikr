package com.quikr.api.education;

/**
 * Created by akhil.singla on 19/9/16.
 */
public class Data
{
    @org.testng.annotations.DataProvider(name = "generateInstituteIds")
    public static Object[][] generateInstituteIds()
    {
        int count = 500;
        Object[][] instituteIds = new Object[count][1];

        for(int i=0; i<count; i++)
        {
            instituteIds[i][0] = i+1;
        }

        return instituteIds;
    }

    @org.testng.annotations.DataProvider(name = "generateCourseIds")
    public static Object[][] generateCourseIds()
    {
        int count = 1;
        Object[][] courseIds = new Object[count][2];

        for(int i=0; i<count; i++)
        {
            courseIds[i][0] = i+1;
            courseIds[i][1] = i+1;
        }

        return courseIds;
    }
}
