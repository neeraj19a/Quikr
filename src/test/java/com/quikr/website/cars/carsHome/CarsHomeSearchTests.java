package com.quikr.website.cars.carsHome;

import com.quikr.website.cars.CarsTestBase;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Saurabh on 22/10/15.
 */
public class CarsHomeSearchTests extends CarsTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("CARS_TESTDATA_FILE"));

}