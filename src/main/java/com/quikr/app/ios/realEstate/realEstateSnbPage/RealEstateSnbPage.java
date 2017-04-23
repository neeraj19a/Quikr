package com.quikr.app.ios.realEstate.realEstateSnbPage;


import com.quikr.app.ios.AppiOSPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 20/11/15.
 */
public class RealEstateSnbPage extends AppiOSPageBase{
    public RealEstateSnbPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_REALESTATESNBPAGE_DOM_FILE");

    /**
     * function to click on sort button in snb page of real estate
     */
    public void clickOnSortButtonInRealEstateSnbPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"sortButton");
        Mapper.find(domFile,"sortButton").click();
    }

    /**
     * function to select lowest price button on top
     */
    public void selectLowestPrice()
    {
        Mapper.waitForElementToBeVisible(domFile,"lowestPriceButton");
        Mapper.find(domFile,"lowestPriceButton").click();
    }

    /**
     * function to validate Residential Filter tabs
     */
    public boolean validateResidentialFilterTabs()
    {
        return (isElementPresent("property") && isElementPresent("amenities") && isElementPresent("furnishing"));
    }

    /**
     * function to select property filter tab
     */
    public void selectPropertyTab()
    {
        Mapper.find(domFile,"property").click();
    }

    /**
     * function to select amenities tab in Residential
     */
    public void selectAmenitiesTab()
    {
        Mapper.find(domFile,"amenities").click();
    }

    /**
     * function to select furnishing tab in Residential
     */
    public void selectFurnishingTab()
    {
        Mapper.find(domFile,"furnishing").click();
    }

    /**
     * function to select filter button on real estate snb page
     */
    public void selectFilterButtonOnRealSnbPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"filterButton");
        Mapper.find(domFile,"filterButton").click();
    }

    /**
     * function to validate Commercial Filter tab
     */
    public boolean validateCommercialFilterTabs()
    {
        return (isElementPresent("property") && isElementPresent("amenities"));
    }

    /**
     * function to validate Agriculture filter tabs
     */
    public boolean validateAgricultureFilterTabs()
    {
        return (isElementPresent("property") &&  !isElementPresent("amenities") &&  !isElementPresent("furnishing"));
    }

    /**
     * function to click on locality button in filter option in real estate
     */
    public void clickOnLocalityButton()
    {
        Mapper.find(domFile,"clickOnLocalityButton").click();
    }

    /**
     * function to select locality option name
     */
    public boolean validateLocalityOptionName()
    {
        Mapper.find(domFile,"localityName");
        return isElementPresent("localityName");
    }

    /**
     * function to select reset button
     */
    public void selectResetButton()
    {
        Mapper.find(domFile,"resetButton").click();
    }

    /**
     * function to validate option in filter for posted by
     */
    public boolean validateCommercialOptionsOrResidentialPostedBy()
    {
        return (isElementPresent("individual") && isElementPresent("broker") && isElementPresent("builder"));
    }

    /**
     * function to validate all option in posted by in commercial category for rent
     */
    public boolean validateCommercialOptionsPostedByForRent()
    {
     return (isElementPresent("individual") && isElementPresent("broker"));
    }

    /**
     * function to validate filter all  UI options for  agriculture
     */
    public boolean validateAgricultureFilterUiAllFilterOptions()
    {
//        if (isElementPresent("priceRange") & isElementPresent("locality") & isElementPresent("adPostDate"))
//        {
            Mapper.scroll("Posted by");
            Mapper.waitForElementToBeVisible(domFile,"photoPreference");
            System.out.println(Mapper.find(domFile, "photoPreference").getText());
            System.out.println(Mapper.find(domFile,"postedBy").getText());
           // return (Mapper.waitForElementToBeVisible(domFile,"photoPreference") && Mapper.waitForElementToBeVisible(domFile,"postedBy"));
            //return (isElementPresent("photoPreference") & isElementPresent("postedBy"));
            if (isElementPresent("photoPreference") & isElementPresent("postedBy"))
            {
                return true;
            }
       // }
        return false;

        //incomp
    }

    /**
     * function to validate max price range in real estate filter options
     */
    public boolean validateMaxPriceForResidentialOrCommercialOrAgriculture()
    {
        return isElementPresent("maxPrice");
    }

    /**
     * function to validate mi price range in real estate filter options
     */
    public boolean validateMinPriceForResidentialOrCommercialOrAgriculture()
    {
        return isElementPresent("minPrice");
    }

    /**
     * function to validate max price for rent in commercial category
     */
    public boolean validateMaxPriceInFilterInCommercialForRent()
    {
        return isElementPresent("maxPriceInCommercialForRent");
    }

    /**
     * function to validate max area for rent in commercial category
     */
    public boolean validateMaxAreaInFilterInCommercialCategoryForRent()
    {
        return isElementPresent("maxAreaInCommercialForRent");
    }

    /**
     * function to validate min area for residential category
     */
    public boolean validateMinAreaForResidentialCategory()
    {
        return isElementPresent("minAreaInResidentialCategory");
    }


    /**
     * function to validate all property option's in commercial
     */
    public boolean validateAllPropertyOptionsInCommercial()
    {
        return (isElementPresent("plot") && isElementPresent("office") && isElementPresent("shop") && isElementPresent("buildingOrComplex"));
    }

    /**
     * function to validate gender options in residential
     */
    public boolean validateGenderOptionsInResidential()
    {
        return (isElementPresent("male") && isElementPresent("female"));
    }

    /**
     * function to validate max price for residential category for pg/flats
     */
    public boolean validateMaxPriceInResidentialForPgOrFlats()
    {
        return isElementPresent("maxPriceInResidentialForPgOrFlats");
    }

    /**
     * function to validate property type options  in residential for pg/flat
     */
    public boolean validatePropertyTypeInResidentialForPgOrFlat()
    {
        return (isElementPresent("pgOption") && isElementPresent("flat"));
    }

    /**
     * function to validate property type in residential for buy
     */
    public boolean validatePropertyTypeOptionsInResidentialForBuy()
    {
        return (isElementPresent("apartment") && isElementPresent("villa") && isElementPresent("plot") && isElementPresent("BuilderFloor"));
    }

    /**
     * function to select residential property type option
     */
    public void selectResidentialPropertyTypeOptionName(int i)
    {
        System.out.println(Mapper.findAndReplace(domFile, "selectResidentialPropertyTypeOptionNameRadioButton", new String[]{Integer.toString(i)}).getText());
        Mapper.findAndReplace(domFile, "selectResidentialPropertyTypeOptionNameRadioButton", new String[]{Integer.toString(i)}).click();
        System.out.println(Mapper.findAndReplace(domFile, "selectResidentialPropertyTypeOptionNameRadioButton", new String[]{Integer.toString(i)}).getText());

    }

    /**
     * function to validate residential property type option after reset
     */
    public boolean validateResidentialPropertyTypeOptionNameAfterReset(int i)
    {
        String str=Mapper.findAndReplace(domFile, "selectResidentialPropertyTypeOptionNameRadioButton", new String[]{Integer.toString(i)}).getAttribute("value");
        if(str.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**
     * function to select Amenities property type option name
     */
    public void selectAmenitiesPropertyTypeOptionName(int i)
    {
        System.out.println(Mapper.findAndReplace(domFile, "amenitiesPropertyTypeOptionName", new String[]{Integer.toString(i)}).getAttribute("value"));
        System.out.println(Mapper.findAndReplace(domFile, "amenitiesPropertyTypeOptionName", new String[]{Integer.toString(i)}).getText());
        Mapper.findAndReplace(domFile, "amenitiesPropertyTypeOptionName", new String[]{Integer.toString(i)}).click();
        System.out.println(Mapper.findAndReplace(domFile, "amenitiesPropertyTypeOptionName", new String[]{Integer.toString(i)}).getAttribute("value"));
    }

    /**
     * function to validate Amenities property type option name after reset
     */
    public boolean validateAmenitiesPropertyTypeOptionNameAfterReset(int i)
    {
        String str=Mapper.findAndReplace(domFile, "amenitiesPropertyTypeOptionName", new String[]{Integer.toString(i)}).getAttribute("value");
        if(str.isEmpty())
        {
            return true;
        }
        return false;
    }


    /**
     * function to select furnishing property type option name
     */
    public void selectFurnishingPropertyTypeOptionName(int i)
    {
        System.out.println(Mapper.findAndReplace(domFile, "furnishingPropertyTypeOptionName", new String[]{Integer.toString(i)}).getText());
        Mapper.findAndReplace(domFile, "furnishingPropertyTypeOptionName", new String[]{Integer.toString(i)}).click();
        System.out.println(Mapper.findAndReplace(domFile, "furnishingPropertyTypeOptionName", new String[]{Integer.toString(i)}).getText());

    }

    /**
     * function to validate furnishing property type option Name after reset
     */
    public boolean validateFurnishingPropertyTypeOptionName(int i)
    {
        String str=Mapper.findAndReplace(domFile, "furnishingPropertyTypeOptionName", new String[]{Integer.toString(i)}).getAttribute("value");
        if(str.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**
     * function to select cross button on filter page
     */
    public void selectCrossButtonOnFilterPage()
    {
        Mapper.find(domFile,"selectCrossButtonOnFilterPage").click();
    }

    public int getAdCount(String locality) throws Exception
    {
        Thread.sleep(3000);
        return Mapper.finds(domFile,locality).size();
    }

    /**
     * function to validate No Of Bed-Rooms
     */
    public boolean validateNoOfBedRooms()
    {
        return (isElementPresent("one") && isElementPresent("two") && isElementPresent("three") && isElementPresent("four") && isElementPresent("4Plus"));
    }

    /**
     * function to select room in filter option
     */
    public void selectRoomInResidentialFilterOption()
    {
        Mapper.find(domFile,"two").click();
    }

    /**
     * function to validate tap on room
     */
    public boolean validateTapOnRoomIsHighLightedOrNot(int i)
    {
        String str=Mapper.findAndReplace(domFile, "clickOnRoom", new String[]{Integer.toString(i)}).getAttribute("value");
        if(str.isEmpty())
        {
            return false;
        }
        return true;
    }

    /**
     * function to click on back button on snb page of realEstate
     */
    public void clickOnBackButtonOnRealEstateSnbPage()
    {
        Mapper.find(domFile,"backButtonOnRealEstate").click();
    }

    /**
     * function to click on allow button in location access pop up
     */
    public void selectAllowButtonInLocationAccessPopup()
    {
        Mapper.find(domFile,"allowButton").click();
    }
}
