package com.quikr.utils.enums.website;

/**
 * Created by quikr on 5/8/15.
 */
public enum QuikrEnums
{
    PREMIUM_TOP_AD,
    PREMIUM_URGENT_AD,
    PREMIUM_BOTH_AD,
    PREMIUM_PAYMENT_ONLINE,
    PREMIUM_PAYMENT_CHEQUE,
    PREMIUM_PAYMENT_ADCREDITS,
    PREMIUM_PAYMENT_QUIKR_POINT,
    PREMIUM_PAYMENT_MOBILE,
    ROLE,
    EDUCATION,
    EXPERIENCE;

    //category name
    public enum CategoryName {
        CARS_AND_BIKES("Cars & Bikes"),
        MOBILES_AND_TABLETS("Mobiles & Tablets"),
        ELECTRONICS_AND_APPLIANCES("Electronics & Appliances"),
        REAL_ESTATE("Real Estate"),
        HOME_AND_LIFESTYLE("Home & Lifestyle"),
        JOBS("Jobs"),
        SERVICES("Services"),
        ENTERTAINMENT("Entertainment"),
        PETS_AND_PET_CARE("Pets & Pet Care"),
        EDUCATION_AND_TRAINING("Education & Training"),
        COMMUNITY("Community"),
        EVENTS("Events"),
        MATRIMONIAL("Matrimonial"),
        FURNITURE_AND_DECOR(""),
        QUIKRX("QuikrX"),
        CARS("Cars"),
        BIKES("Bikes"),
        OTHERS("Others"),
        All_CATEGORIES("All Categories");

        private String realName;
        private CategoryName(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }

    //Mobile and Tablets sub-category
    public enum MobileTabletSubCat
    {
        MOBILE_PHONES("Mobile Phones"),
        ACCESSORIES("Accessories"),
        TABLETS("Tablets");

        private String realName;
        private MobileTabletSubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }

    //Electronics and Appliances sub-category
    public enum ElectronicAppliancesSubCat {
        HOME_KITCHEN_APPLIANCES("Home - Kitchen Appliances"),
        LAPTOP_COMPUTERS("Laptops - Computers"),
        TV_DVD_MULTIMEDIA("TV - DVD - Multimedia"),
        COMPUTER_PERIPHERALS("Computer Peripherals"),
        VIDEO_GAMES_CONSOLES("Video Games - Consoles"),
        TOOLS_MACHINERY_INDUSTRIAL("Tools - Machinery - Industrial"),
        CAMERAS_DIGICAMS("Cameras - Digicams"),
        MUSIC_SYSTEMS_HOME_THEATRE("Music Systems - Home Theatre"),
        INVERTERS_UPS_GENERATORS("Inverters, UPS & Generators"),
        FAX_EPABX_OFFICE_EQUIPMENT("Fax, EPABX, Office Equipment"),
        CAMERA_ACCESSORIES("Camera Accessories"),
        SECURITY_EQUIPMENT_PRODUCTS("Security Equipment - Products"),
        IPODS_MP3_PLAYERS("iPods, MP3 Players"),
        OFFICE_SUPPLIES("Office Supplies"),
        EVERYTHING_ELSE("Everything Else");

        private String realName;
        private ElectronicAppliancesSubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }

    //Home and Lifestyle sub-category
    public enum HomeLifestyleSubCat {
        HOME_OFFICE_FURNITURE("Home - Office Furniture"),
        HOME_DECOR_FURNISHINGS("Home Decor - Furnishings"),
        HOUSEHOLD("Household"),
        ANTIQUES_HANDICRAFTS("Antiques - Handicrafts"),
        FOOTWEAR("Footwear"),
        PAINTINGS("Paintings"),
        KITCHENWARE("Kitchenware"),
        BABY_INFANT_PRODUCTS("Baby - Infant Products"),
        TOYS_GAMES("Toys - Games"),
        KIDS_LEARNING("Kids Learning"),
        WHOLESALE_BULK("Wholesale - Bulk"),
        EVERYTHING_ELSE("Everything Else"),
        CLOTHING_GARMENTS("Clothing - Garments"),
        SPORT_FITNESS_EQUIPMENT("Sport - Fitness Equipment"),
        HEALTH_BEAUTY_PRODUCTS("Health - Beauty Products"),
        FASHION_ACCESSORIES("Fashion Accessories"),
        WATCHES("Watches"),
        BAGS_LUGGAGE("Bags - Luggage"),
        JEWELLERY("Jewellery"),
        GIFTS_STATIONARY("Gifts - Stationary"),
        MUSICAL_INSTRUMENTS("Musical Instruments"),
        COINS_STAMPS("Coins - Stamps"),
        BOOKS_MAGAZINES("Books - Magazines"),
        MUSIC_MOVIES("Music - Movies"),
        COLLECTIBLES("Collectibles");

        private String realName;
        private HomeLifestyleSubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }

    //Entertainment sub-category
    public enum EntertainmentSubCat {
        ACTING_MODELLING_ROLES("Acting - Modeling Roles"),
        MODELLING_AGENCIES("Modeling Agencies"),
        ACTING_SCHOOLS("Acting Schools"),
        PHOTOGRAPHERS_CAMERAMAN("Photographers - Cameraman"),
        MUSICIANS("Musicians"),
        SCRIPT_WRITERS("Script Writers"),
        STUDIO_LOCATIONS_FOR_HIRE("Studios - Locations for hire"),
        MAKE_UP_HAIR("Make Up - Hair"),
        ACTOR_MODEL_PORTFOLIOS("Actor - Model Portfolios"),
        ART_DIRECTORS_EDITORS("Art Directors - Editors"),
        SOUND_ENGINEERS("Sound Engineers"),
        FASHION_DESIGNERS_STYLISTS("Fashion Designers - Stylists"),
        SET_DESIGNERS("Set Designers"),
        OTHER_ENTERTAINMENT("Other Entertainment");

        private String realName;
        private EntertainmentSubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }

    //Pets and pet care sub-category
    public enum PetsSubCat {
        PETS("Pets"),
        PET_CARE_ACCESSORIES("Pet Care - Accessories"),
        PET_ADOPTION("Pet Adoption"),
        PET_TRAINING_AND_GROOMING("Pet Training & Grooming"),
        PET_FOODS("Pet Foods"),
        PET_CLINICS("Pet Clinics");

        private String realName;
        private PetsSubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }

    //Education and learning sub-category
    public enum EducationLearningSubCat {
        CAREER_COUNSELLING("Career Counseling"),
        COACHING_AND_TUITIONS("Coaching & Tuitions"),
        COMPETITIVE_EXAMS_COACHING("Competitive Exams Coaching"),
        DANCE_MUSIC_CLASSES("Dance - Music Classes"),
        DISTANCE_LEARNING_COURSES("Distance Learning Courses"),
        ENTRANCE_EXAM_COACHING("Entrance Exam Coaching"),
        HOBBY_CLASSES("Hobby Classes"),
        PLAY_SCHOOLS_CRECHE("Play Schools - Creche"),
        PROFESSIONAL_AND_SHORT_TERM_COURSES("Professional & Short Term Courses"),
        SCHOOL_TUITIONS("School Tuitions"),
        STUDY_ABROAD("Study Abroad"),
        TEXT_BOOKS_AND_STUDY_MATERIAL(" Text books & Study Material"),
        WORKSHOPS("Workshops"),
        CERTIFICATIONS_AND_TRAINING("Certifications & Training");

        private String realName;
        private EducationLearningSubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }

    //Community and events sub-category
    public enum CommunitySubCat {
        ANNOUNCEMENTS("Announcements"),
        CAR_POOL_BIKE_RIDE("Car Pool - Bike Ride"),
        CHARITY_DONATE_NGO("Charity - Donate - NGO"),
        EVENTS("Events"),
        LOST_FOUND("Lost - Found"),
        TENDER_NOTICES("Tender Notices");

        private String realName;
        private CommunitySubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }


    //Events sub-category
    public enum EventsSubCat {
        EVENT_MANAGEMENT("Event Management"),
        LIVE_EVENTS("Live Events");
        private String realName;
        private EventsSubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }

    //Matrimonial sub-category
    public enum MatrimonialSubCat {
        GROOMS("Grooms"),
        BRIDES("Brides"),
        WEDDING_PLANNERS("Wedding Planners");

        private String realName;
        private MatrimonialSubCat(String realName)
        {
            this.realName = realName;
        }

        public String toString()
        {
            return this.realName;
        }
    }
}