package com.quikr.app.ios.dataProvider;

import org.testng.annotations.DataProvider;

/**
 * Created by Manvendra Singh on 2/4/16.
 */
public class Data {

    @DataProvider(name = "jobsSubCat")
    public static Object[][] jobsSubcategory() {
        return new Object[][]{{"Full Time Jobs"}, {"Work From Home"}, {"Part Time Jobs"}, {"Internships"}};
    }

    @DataProvider(name = "servicesSubCat")
    public static Object[][] servicesSubcategory() {
        return new Object[][]{{"Plumbers"}, {"Carpenters - Furniture Work"}};
    }
}
