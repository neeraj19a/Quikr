package com.quikr.website.dataProvider;

import org.testng.annotations.DataProvider;
/**
 * Created by akhil.singla on 6/8/15.
 */
public class Data
{
    @DataProvider(name = "getCategoriesForJobs")
    public static Object[][] jobsCategoriesData()
    {
        return new Object[][]{{"Full Time Jobs"}, {"Part Time Jobs"}, {"Freelancers"}, {"Non-Profit NGOs"}, {"Other Jobs"}, {"Work From Home"}, {"Placement - Recruitment Agencies"}, {"Summer Trainees - Interns"}};
    }

    @DataProvider(name = "priceChange")
    public static Object[][] priceChange() {

        return new Object[][]{
                {"certified", "1132", "Rocket Kommerce LLP", "2342","-Ooops... Looks like the < Apple iPhone 4S (32 GB) > price has increased from Rs. 2,342 to Rs. 4,325 since you last checked", "4325"},
                {"certified", "1132", "Rocket Kommerce LLP", "2342","-Congratulations, we just dropped the < Apple iPhone 4S (32 GB) > price from Rs. 2,342 to Rs. 1,800 and you saved Rs. 542" , "1800"},
                {"exchange", "969", "Rocket Kommerce LLP", "2342", "-Ooops... Looks like the < Samsung Galaxy Star (Noble Black, 4 GB) > price has increased from Rs. 2,342 to Rs. 4,325 since you last checked", "4325"},
                {"exchange", "969", "Rocket Kommerce LLP", "2342", "-Congratulations, we just dropped the < Samsung Galaxy Star (Noble Black, 4 GB) > price from Rs. 2,342 to Rs. 1,800 and you saved Rs. 542", "1800"}
        };
    }

    @DataProvider(name = "cartOos")
    public static Object[][] oos() {

        return new Object[][]{
                {"certified", "1132", "Rocket Kommerce LLP", "Apple iPhone 4S (32 GB)"},
                {"exchange", "969", "Rocket Kommerce LLP", "Samsung Galaxy Star (Noble Black, 4 GB)"}
        };
    }

    @DataProvider(name = "checkOutpriceChange")
    public static Object[][] checkOutpriceChange() {

        return new Object[][]{
                {"certified", "1132", "Rocket Kommerce LLP", "2342","*Price increased since you last checked 2342.00","4325",false},
                {"certified", "1132",  "Rocket Kommerce LLP", "2342", " ", "1800",true},
                {"exchange", "967", "Rocket Kommerce LLP", "2342","*Price increased since you last checked 2342.00","4325",false},
                {"exchange", "967",  "Rocket Kommerce LLP", "2342", " ", "1800",true},
        };
    }

    @DataProvider(name = "discountItems")
    public static Object[][] discountTest() {

        return new Object[][]{
                {"certified", "1150", "10"},
                {"exchange", "982", "10"},
        };
    }

    @DataProvider(name = "e2e")
    public static Object[][] endToEnd() {

        return new Object[][]{
                {"certified"},
                {"exchange"},
        };
    }

    @DataProvider(name = "checkCarsMsp")
    public  static  Object[][] checkCarMsp() {

        return new Object[][]{
            {"Audi","A6","2013","2.0 TDI Premium Plus (Diesel)","20000-50000"},
            {"BMW","3 Series","2009","320D (Diesel)","0-5000"}, //This is validating only ZERO case//
            {"Fiat","Punto","2010","1.2 Active (Petrol)","50000-200000"},
        };
    }
        @DataProvider (name = "checkBikesMsp")
    public static Object[][] checkBikeMsp(){

            return new Object[][]{
                    {"Honda","Activa","2014",},
                    {"Mahindra","Centuro","2011"},
                    {"Yamaha","Gladiator","2010"},
            };
        }

    @DataProvider(name = "quikrXItems")
    public static Object[][] quikrXItems() {

        return new Object[][]{
                {"certified", "1529"},
                {"exchange", "982"},
        };
    }

    @DataProvider(name = "quikrXLandingPage")
    public static Object[][] quikrXLandingPage() {

        return new Object[][]{
                {"refurbished","See All Refurbished Phones","Save upto 50%","Used phones that are thoroughly tested with minor repairs and cosmetic uplifts with warranty"},
                {"unboxed","See All Unboxed Phones","Save upto 35%","Unused or partially used phones that were returned by users. As the boxes were opened, Quikr authorized vendors re-box the phone with original accessories after thorough testing and certification.They make phone as good as new."},
                {"new","See All New Phones","Save upto 20%","Exchange your old phone at a great price for a new phone."}
        };
    }


    @DataProvider(name="MandatoryCitiesLogin")
    public  static Object[][] cities(){

        return  new Object[][]{
            {"Nashik","nashik"},{"Gandhinagar","gandhinagar"}
        };
    }

    @DataProvider(name="NewUICities")
    public  static Object[][] citiesApp(){

        return  new Object[][]{
                {"Pune","pune"},
                {"Hyderabad","hyderabad"},
                {"Vijayawada","vijayawada"},
                {"Guntur","guntur"},
                {"Aarani","aarani"},
        };
    }

    @DataProvider(name="LowTouchCities")
    public  static Object[][] LowTouchCities(){

        return  new Object[][]{
                {"Lucknow"},
                {"Ahmedabad"},
                {"Chennai"},
                {"Hyderabad"},
        };
    }

    @DataProvider(name = "newpriceChange")
    public static Object[][] newpriceChange() {

        return new Object[][]{
                {"certified", "1140",  "50000","Oops.. Looks like the price has increased from"},
                {"exchange", "982",  "50000","Oops.. Looks like the price has increased from"},
                {"certified", "1140",  "500","Congratulations, we just dropped the price from"},
                {"exchange", "982", "500","Congratulations, we just dropped the price from"}
        };
    }

    @DataProvider(name="incentivization")
    public static Object[][] incentivization(){
        return new Object[][]{
                {"Unboxed","Item Level","Fixed","200"},
                {"Refurbished","Category Level","Percentage","10"},
                {"New","None","Fixed","0"}
        };
    }

    @DataProvider(name="unboxed")
    public static Object[][] unboxed(){
        return new Object[][]{
                {"Unboxed","Category Level","Percentage","10","1503"},
                {"Unboxed","Item Level","Fixed","100","1503"},
                {"Unboxed","None","Fixed","100","1503"},
                {"Refurbished","Category Level","Percentage","10","1154"},
                {"Refurbished","Item Level","Fixed","100","1154"},
                {"Refurbished","None","Fixed","100","1154"}

        };
    }

    @DataProvider(name="cod")
    public static Object[][] cod(){
        return new Object[][]{
                {"Unboxed","Category Level","Percentage","10","1503"},
        };
    }

    @DataProvider(name="jobCategoriesForJobPap")
    public  static Object[][] jobCategoriesForJobPap(){

        return  new Object[][]{
                {"Work from home"},
                {"Part time"},
                {"Full time"},
                {"Internship"},
        };
    }

    @DataProvider(name = "postadCitiesMsite")
    public static Object[][] postadCities(){

        return new Object[][]{
                {"Bangalore"},
                {"Jaipur"},
                {"Mumbai"},
                {"Patna"},
                {"Lucknow"},
                {"Bhubaneswar"},

        };
    }



    @DataProvider(name="jobSubCatForAlertCreation")
    public  static Object[][] jobSubCatForAlertCreation(){

        return  new Object[][]{
                {1},
                {2},
                {3},
                {5},
                {7},
        };
    }

    @DataProvider(name="conditionFilter")
    public static Object[][] conditionFilter(){
        return new Object[][]{
                {"Excellent","like_new"},
                {"Fair","fair"},
                {"Good","good"}
        };
    }

    @DataProvider(name = "adType")
    public static Object[][] adType(){
        return new Object[][]{
                {1},
                {2},
                {3},

        };
    }

    @DataProvider(name = "adType1")
    public static Object[][] adType1(){
        return new Object[][]{
                {1},
                {2},

        };
    }

    @DataProvider(name="testD")
    public static Object[][] testD(){
        return new Object[][]{
                {"SampleText"},
                {"Again sample"}
        };
    }


    @DataProvider(name="acceptedPhones")
    public static Object[][] AcceptedPhones(){
        return new  Object[][]{
                {"Apple","iPhone 4 16 GB","Excellent","Bangalore"},
                {"Lenovo","A706","Good","Ahmedabad"}


        };
    }

}