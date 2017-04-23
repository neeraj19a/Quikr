package com.quikr.api.cars;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;

/**
 * Created by quikr on 21/6/16.
 */
public class CarsApiDataProvider {

    @DataProvider(name="autosuggest")
    public static Object[][] data(){
        ArrayList<String> ls=new ArrayList<>();
        ls.add("{\"AutoSuggestResponse\":{\"MetaData\":{\"requestId\":\"dynamicParameter\"},\"AutoSuggestResponse\":[{\"model\":\"Amaze\",\"brand\":\"Honda\"},{\"model\":\"City\",\"brand\":\"Honda\"},{\"model\":\"Jazz\",\"brand\":\"Honda\"},{\"model\":\"BRV\",\"brand\":\"Honda\"},{\"model\":\"Mobilio\",\"brand\":\"Honda\"},{\"model\":\"Brio\",\"brand\":\"Honda\"},{\"model\":\"CRV\",\"brand\":\"Honda\"}]}}");
        ls.add("{\"AutoSuggestResponse\":{\"MetaData\":{\"requestId\":\"dynamicParameter\"},\"AutoSuggestResponse\":[{\"model\":\"Baleno\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Ciaz\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Vitara Brezza\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Swift\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Alto K10\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Alto 800\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Celerio\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Swift Dzire\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Ertiga\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Eeco\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"S Cross\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Wagon R 1.0\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Omni\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Ritz\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Stingray\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Gypsy\",\"brand\":\"Maruti Suzuki\"},{\"model\":\"Vanquish\",\"brand\":\"Aston Martin\"},{\"model\":\"V8 Vantage\",\"brand\":\"Aston Martin\"},{\"model\":\"Rapide\",\"brand\":\"Aston Martin\"},{\"model\":\"V12 Vantage\",\"brand\":\"Aston Martin\"},{\"model\":\"DB9\",\"brand\":\"Aston Martin\"}]}}\n");
        return new Object[][]{
                {"hnda","50",ls.get(0)},
                {"maruti","50",ls.get(1)},
        };
    }

    @DataProvider(name="autosuggestdata")
    public static Object[][] dataAutoSuggest(){
        return new Object[][]{
                {"hnda","50"},
                {"mar","50"},
        };

    }

}
