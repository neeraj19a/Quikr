package com.quikr.app.android.dataProvider;

import org.testng.annotations.DataProvider;

/**
 * Created by Manvendra Singh on 18/8/15.
 */
public class Data {

    @DataProvider(name = "getCategories")
    public static Object[][] homePageCategories() {
        return new Object[][]{{"Cars"}, {"Mobiles"}, {"Electronics"}, {"Real Estate"}, {"Home"}, {"Jobs"}, {"Services"}, {"Entertainment"}, {"Education"}, {"Pets"}, {"Community"}, {"Matrimonial"}};
    }

    @DataProvider(name = "getCategoriesforSorting")
    public static Object[][] homePageCategoriesForSorting() {
        return new Object[][]{{"Cars"}, {"Mobiles"}, {"Electronics"}, {"Home"}, {"Pets"}};
    }

    @DataProvider(name = "getCategoriesForJobs")
    public static Object[][] jobsCategoriesData() {
        return new Object[][]{{"Full Time Jobs"}, {"Part Time Jobs"}, {"Placement - Recruitment Agencies"}, {"Work From Home"}, {"Freelancers"}, {"Non-Profit NGOs"}, {"Summer Trainees - Interns"}, {"Other Jobs"}};
    }

    @DataProvider(name = "getSubcategoriesForServices")
    public static Object[][] servicesCategories() {
        return new Object[][]{{"Maids & Housekeeping"}, {"Cooks"}, {"Drivers"}, {"Baby Sitters - Nanny"}, {"Interior Design"}, {"Movers & Packers"}, {"Electronics - Appliances Repair"}, {"Pest Control"}, {"Home Cleaning"}};
    }

    /**
     * used in shortlisting non-premium ads and validating same under My shortlist
     *
     * @return
     */
    @DataProvider(name = "getCategoriesforPrremiunAndNonPremium")
    public static Object[][] Shortlistcategories() {
        return new Object[][]{{"Cars"}, {"Mobiles"}, {"Electronics"}, {"Entertainment"}, {"Education"}, {"Pets"}, {"Matrimonial"}};
    }

    /**
     * Android statics category
     */
    @DataProvider(name = "androidSataicsCategory")
    public static Object[][] verticals() {
        return new Object[][]{{"Cars"}, {"REAL"}, {"JOBS"}, {"Mobiles"}, {"SERVICES"}};
    }

    @DataProvider(name = "EscrowHighTouchcities")
    public static Object[][] HIghTouchEscrowCities() {
        return new Object[][]{{"Mumbai",}, {"Bangalore",}};
    }

    @DataProvider(name = "NonEscrowcities")
    public static Object[][] NonEscrowCities() {
        return new Object[][]{{"Kochi"}};    }

    @DataProvider(name = "ClusterCities")
    public static Object[][] ClusterCities() {
        return new Object[][]{{"Delhi"},{"Gurgaon"}};
        }

    @DataProvider(name = "ClusterCitiesOverlay")
    public static Object[][] ClusterCitiesOverlay() {
        return new Object[][]{{"Delhi","HomesAndLifestyle"},{"Delhi","ElectronicsAndAppliances"},{"Gurgaon","HomesAndLifestyle"},{"Gurgaon","ElectronicsAndAppliances"}};}

    @DataProvider(name = "TopCitiesOverlay")
    public static Object[][] TopCitiesOverlay() {
        return new Object[][]{{"Bangalore","HomesAndLifestyle"},{"Bangalore","ElectronicsAndAppliances"},{"Hyderabad","HomesAndLifestyle"},{"Hyderabad","ElectronicsAndAppliances"}};}

    @DataProvider(name = "MCitiesOverlay")
    public static Object[][] MCitiesOverlay() {
        return new Object[][]{{"Bikaner","HomesAndLifestyle"},{"Bikaner","ElectronicsAndAppliances"},{"Warangal","HomesAndLifestyle"},{"Warangal","ElectronicsAndAppliances"}};}

    @DataProvider(name = "NCitiesOverlay")
    public static Object[][] NCitiesOverlay() {
        return new Object[][]{{"Surat","HomesAndLifestyle"},{"Surat","ElectronicsAndAppliances"},{"Ludhiana","HomesAndLifestyle"},{"Ludhiana","ElectronicsAndAppliances"}};}

    @DataProvider(name = "ChatCities")
    public static Object[][] ChatCities() {
        return new Object[][]{{"Delhi"}};
        }

    @DataProvider(name = "AuctionCities")
    public static Object[][] AuctionCities() {
        return new Object[][]{{"Pune"}};
        }

    @DataProvider(name= "EscrowFlowPrerequisite")
    public static Object[][] FlowPrerequisite() {
        return new Object[][]{{"Precondition"},{"ActualTest"}};
    }
    @DataProvider(name = "EscrowC2CCities")
    public static Object[][] Escrowc2cCities() {
        return new Object[][]{{"Hyderabad", "Adibatla"}, {"Chennai", "Adyar"}, {"Vizag", "Bheemli"}, {"Coimbatore", "Anna Nagar"}, {"Delhi", "Aiims"}, {"Gurgaon", "Basai"}, {"Ahmedabad", "Acher"}, {"Pune", "Akurdi"}, {"Chandigarh", "Phase V"}, {"Lucknow", "Aashiana"}, {"Ludhiana", "Bajra"}, {"Bhopal", "Akabarpur"}, {"Jaipur", "Airport"}, {"Indore", "Bijalpur"}, {"Surat", "Amroli"}, {"Vadodara", ""}, {"Kanpur", "Barra"}};
    }

    @DataProvider(name = "escrowTest3132")
    public static Object[][] test3132() {
        return new Object[][]{{"postAd"}};
    }

    @DataProvider(name = "lowTouchCity")
    public static Object[][] lowTouchCity() {
        return new Object[][]{{"Delhi"}, {"Ahmedabad"}, {"Hyderabad"}};
    }

    @DataProvider(name = "EscrowHighTouchcitiesWithLocality")
    public static Object[][] highTouchCityWithLocality() {
        return new Object[][]{{"Mumbai", "Agripada"}, {"Bangalore", "80 Ft. Road"}};
    }

    @DataProvider(name = "jobsSubcat")
    public static Object[][] jobsSubcategory() {
        return new Object[][]{{"Full Time Jobs"}, {"Work From Home"}, {"Part Time Jobs"}, {"Internships"}};
    }

    @DataProvider(name = "CrasSubCat")
    public static Object[][] CarsSubcategory() {
        return new Object[][]{{"Bikes & Scooters"}, {"Cars"}};

    }

    @DataProvider(name = "category")
    public static Object[][] Jobs() {
        return new Object[][]
                {

                        {"Cars"},
                        {"Mobiles"},
                        {"ELECTRONICS"},
                        {"ENTERTAINMENT"},
                        {"EDUCATION"},
                        //{"PETS"},
                        //{"MATRIMONIAL"},
                        {"Homes"},
                        {"JOBS"},
                        {"SERVICES"},
                        {"Events"},
                        {"Lifestyle"}

                };
    }

    @DataProvider(name = "getCarsSubcats")
    public static Object[][] carsSubcategory() {
        return new Object[][]{
                {"Bicycles"},
                {"Spare Parts"},
                {"Other Vehicles"}
        };
    }

    @DataProvider(name = "exceptCarsSubcats")
    public static Object[][] carsSubcat() {
        return new Object[][]{
                {"Cars"},
                {"Bikes & Scooters"},
                {"Bicycles"},
                {"Commercial Vehicles"},
                {"Spare Parts - Accessories"},
                {"Other Vehicles"}
        };
    }

    @DataProvider(name = "getPriceTrendsCarsSubcats")
    public static Object[][] carsSubCat() {
        return new Object[][]{
                {"Cars"},
                {"BikesAndScooters"},
                {"Commercial Vehicles"}
        };
    }

    @DataProvider(name = "servicesBookNowCategories")
    public static Object[][] servicesBookNowCategories() {
        return new Object[][]{
                {"Plumber"},
                {"Electrician"},
                {"Carpenter"},
                {"maidOnDemand"},
//                {"carCare"}
        };
    }

    @DataProvider(name = "servicesEvaluateAndChooseCategories")
    public static Object[][] servicesEvaluateAndChooseCategories() {
        return new Object[][]{
                {"Plumber"},
                {"Electrician"},
                {"Carpenter"},
                {"maidOnDemand"},
                {"carCare"},
        };
    }

    @DataProvider(name = "imageTutorialText")
    public static Object[][] quikrImageTutorialText() {
        return new Object[][]{{new String[]{"Buy or Sell anything", "Search & Browse", "Communicate", "Quikr Nxt"}}};

    }

    @DataProvider(name = "LanguageOptions")
    public static Object[][] quikrLanguageOptions() {
        return new Object[][]{{new String[]{"English", "Hindi", "Kannada", "Marathi", "Telugu", "Tamil"}}};

    }

    @DataProvider(name = "MenuDropDownOptions")
    public static Object[][] menuItems() {
        return new Object[][]{{new String[]{"Home", "My Account", "My Ads","My Doorstep Offers", "My Chats","My Alerts", "Notifications", "Shortlisted Ads", "Language Settings", "Check Product MSP", "Recently Viewed Ads", "Rate Us", "About Quikr", "Share App", "Feedback"}}};

    }

    @DataProvider(name = "CategoriesAndSubCAtegories")
    public static Object[][] catsAndSubcats() {
        return new Object[][]{
                {"Cars & Bikes", new String[]{"Cars", "Bikes & Scooters", "Bicycles", "Commercial Vehicles", "Spare Parts - Accessories", "Other Vehicles"}},
                {"Mobiles & Tablets", new String[]{"Mobile Phones", "Tablets", "Accessories",}},
                {"Electronics & Appliances", new String[]{"Laptops - Computers", "Home - Kitchen Appliances", "TV - DVD - Multimedia", "Computer Peripherals", "Camera Accessories", "Cameras - Digicams", "Fax, EPABX, Office Equipment", "Inverters, UPS & Generators", "iPods, MP3 Players", "Music Systems - Home Theatre", "Office Supplies", "Security Equipment - Products", "Tools - Machinery - Industrial", "Video Games - Consoles", "Everything Else"}},
                {"Real Estate", new String[]{"Houses - Apartments for Rent", "Houses - Apartments for Sale", "Land - Plot For Sale", "Flatmates", "Commercial Property for Rent", "Commercial Property for Sale", "Paying Guest - Hostel", "Service Apartments", "Vacation Rentals - Timeshare", "Villas/Bungalows for Rent", "Villas/Bungalows for Sale", "Residential - Builder floors For Sale", "Residential - Builder floors For Rent"}},
//                {"Home & Lifestyle", new String[]{"Home - Office Furniture", "Clothing - Garments", "Antiques - Handicrafts", "Baby - Infant Products", "Bags - Luggage", "Barter - Exchange", "Books - Magazines", "Coins - Stamps", "Collectibles", "Discounted - Sale Items", "Fashion Accessories", "Gifts - Stationary", "Health - Beauty Products", "Home Decor - Furnishings", "Household", "Jewellery", "Music - Movies", "Musical Instruments", "Paintings", "Sport - Fitness Equipment", "Toys - Games", "Watches", "Wholesale - Bulk", "Everything Else"}},
                {"Jobs", new String[]{"Full Time Jobs", "Work From Home", "Part Time Jobs", "Internships"}},
//                {"Services", new String[]{"Airline - Train - Bus Tickets", "Movers & Packers", "Business Offers", "Astrology - Numerology", "Investment - Financial Planning", "Loans - Insurance", "Courier Services", "Health - Fitness", "Catering -Tiffin Services", "Vehicle Rentals - Taxi Services", "Computer Repair and Service", "Maids & Housekeeping", "Motor Service - Repair", "Motor Service - Repair", "Parlours and Salons", "Interior Design", "Travel Agents", "Drivers", "Driving Schools", "Cooks", "Advertising - Design", "Vacation - Tour Packages", "Restaurants - Coffee Shops", "Hotels - Resorts", "Taxation - Audit", "Event -Party Planners - DJ", "Lawyers - Advocates", "Baby Sitters - Nanny", "Doctors", "Household Repair", "Electronics - Appliances Repair", "Plumbers", "Electricians", "DTH & Set Top Boxes", "Carpenters - Furniture Work", "Retail", "Vaastu", "Pest Control", "Home Cleaning", "Internet/Broadband", "Architect", "Everything Else"}},
                {"Entertainment", new String[]{"Acting - Modeling Roles", "Modeling Agencies", "Musicians", "Photographers - Cameraman", "Actor - Model Portfolios", "Acting Schools", "Make Up - Hair", "Script Writers", "Fashion Designers - Stylists", "Art Directors - Editors", "Studios - Locations for hire", "Sound Engineers", "Set Designers", "Other Entertainment"}},
                {"Education & Learning", new String[]{"Coaching & Tuitions", "Distance Learning Courses", "Hobby Classes", "Career Counseling", "Dance - Music Classes", "Play Schools - Creche", "Professional & Short Term Courses", "Text books & Study Material", "Workshops"}},
                {"Pets & Pet Care", new String[]{"Pets", "Pet Adoption", "Pet Care - Accessories", "Pet Foods", "Pet Training & Grooming", "Pet Clinics",}},
                {"Community & Events", new String[]{"Charity - Donate - NGO", "Announcements", "Car Pool - Bike Ride", "Tender Notices", "Lost - Found", "Events"}},
                {"Matrimonial", new String[]{"Grooms", "Brides", "Wedding Planners"}}


        };
    }

    @DataProvider(name = "sortingOptions")
    public static Object[][] SortOptions() {
        return new Object[][]{{new String[]{"Recently Posted", "Nearest", "Lowest Price", "Highest Price"}}
        };
    }
    @DataProvider(name = "AdTypesOnHomePage")
    public  static Object[][]AdTypes()
    {
        return new Object[][]{{new String[]{"Popular Ads","Nearby Ads","Recommended Ads"}}};
    }
    @DataProvider(name = "HomePageAdTypes")
    public  static Object[][]homepageAds()
    {
        return new Object[][]{{"Popular Ads"},{"Nearby Ads"},{"Recommended Ads"}};
    }
    /**
     * providing recomended Ads,Shortlit,Recenly viewed
     */
    @DataProvider(name = "chatPresenceRecomendedShortlistAndRecently")
    public  static Object[][]chatPresence()
    {
        return new Object[][]{{"Shortlisted Ads"}//{"Recently Viewed Ads"}
         ,{"Recommended Ads"}};
    }
    /*
    user status login and guest
     */
    @DataProvider(name = "UserStatus")
    public static Object[][]userStatus()
    {
        return new Object[][]{{"Login"},//{"Guest"}
        };
    }

    /**
     * list of cities where buy with exchange is applicable
     * @return
     */
    @DataProvider(name = "BuyWithExchangeCities")
    public static Object[][]cities()
    {
        return new Object[][]{{"Ahmedabad"},{"Bangalore"},{"Hyderabad"},{"Kalyan"},{"Mumbai"},{"NaviMumbai"},{"Panvel"},{"Thane"},{"Gurgaon"},{"Delhi"},{"Faridabad"}};
    }
    /**
     * list of language
     */

    @DataProvider(name = "language")
    public static Object[][]languageOoptions()
    {
        return new Object[][]{{"English",new String[]{"Sort","Chat"}},{"Hindi",new String[]{"क्रमित करें","अभी चैट करें"}},{"Kannada",new String[]{"ಸಾರ್ಟ್ ಮಾಡಿ","ಈಗ ಚಾಟ್ ಮಾಡಿ"}},{"Marathi",new String[]{"वर्गवारी करा","आता चॅट करा"}},{"Telugu",new String[]{"వేరు చేయి","ఇప్పుడు చాట్ చేయండి"}},{"Tamil",new String[]{"பிரிக்கவும்","இப்போது சாட் செய்யவும்"}}};
    }
    @DataProvider(name = "smartSearchItems")
    public static Object[][] searchItems() {
        return new Object[][]
                {

                        {"honda"},{"Bed"},{"Laptop"},{"samsung"}

                };
    }
    @DataProvider(name = "horizontalCategories")
    public static Object[][] categories() {
        return new Object[][]
                {

                        {"ENTERTAINMENT"},{"EDUCATION"},{"PETS"},{"COMMUNITY"}

                };
    }
    @DataProvider(name = "myAccountOptionsDomElement")
    public static Object[][]domElements()
    {
        return new Object[][]{{new String[]{"Cart","Orders","Quikr Doorstep"}}};
    }
    @DataProvider(name = "postAdLanguageChange")
    public static Object[][]papLanguage()
    {
        return new Object[][]{{"Hindi","विज्ञापन पोस्ट करें"},{"Kannada","ಉಚಿತ ಜಾಹೀರಾತು ನೀಡಿ "},{"Marathi","जाहिरात पोस्ट करा"},{"Telugu","పోస్ట్ ప్రకటన"},{"Tamil","இலவச விளம்பரத்தை பதிகையிடவும்"}};
    }
    @DataProvider(name = "MobileLoginUserCreationErrorMsgFlow")
    public static Object[][] UserCreation() {
        return new Object[][]
                {

                        {"registeredEmail", new String[]{"dhirajbothra04@gmail.com"}}, {"registeredMobile", new String[]{"7406635055"}}
                        //{"RegisteriedEmailAndMobile", new String[]{"dhirajbothra04@gmail.com", "9955199165"}


                };
    }


}

