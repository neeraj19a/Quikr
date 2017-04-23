package com.quikr.app.android.horizontalTests.horizontalPostAd;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.Constants.MobileConstants;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 11/1/16.
 */
public class HorizontalPostAd extends AppAndroidTestBase {


    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HORIZONTAL_TESTDATA_FILE"));

    /**
     * validate subcategories corresponding to specific categories
     */
    @Stories("PostAd")
    @Title(" validate subcategories corresponding to specific categories ")
    @Test(dataProvider = "CategoriesAndSubCAtegories",dataProviderClass =Data.class)
    public void validateSubcatsForPOstAd(String cat,String subCat[])
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        if (cat.equals("Pets & Pet Care")|cat.equals("Community & Events")|cat.equals("Matrimonial"))
            papPage.selectElements(cat, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        else
            papPage.selectelementWithoutScroll(cat, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        List<String>postaAdSubcat=papPage.postAdSubCat();
        System.out.println(postaAdSubcat);
        for (int i=0;i<subCat.length;i++)
        {
            Assert.assertTrue(subCat[i].equalsIgnoreCase(postaAdSubcat.get(i)), "index missmatch for menu elements" + ">>Expected=" + subCat[i] + ">>Actual=" + postaAdSubcat.get(i));
        }

    }

    /**
     * Should be able to change ad Type while posting an Ad.
     All attributes related to the ad type selected should be displayed
     */
    @Stories("PostAd")
    @Title("User Should be able to change ad Type while posting an Ad.")
    @Test()
    public void validateAttributesUpOnChangingAdTypePOstAd() {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        Integer[]cordinate=papPage.cordinatesForVerticalSwipeOnPAP();
        for (int i = 0; i < MobileConstants.postAdCategories.size(); i++) {
            papPage.verticalSwipeWithCordinates(cordinate[0] + 130, cordinate[1]);
            papPage.verticalSwipeWithCordinates(cordinate[0] + 130, cordinate[1]);
            papPage.ClickonCategory();
            papPage.selectelementWithoutScroll(MobileConstants.postAdCategories.get(i), QuikrAppEnums.CATEGORY_LOCATION);
            papPage.ClickonSubCategory();
            papPage.selectelementWithoutScroll(MobileConstants.postAdSubCategories.get(i), QuikrAppEnums.CATEGORY_LOCATION);

           switch (i)
           {
               case 0:
                   papPage.verticalSwipeWithCordinates( cordinate[1],cordinate[0]+20);
                   List<String> postAdAttributes = papPage.postAdAttributes(MobileConstants.postAdCategories.get(i));
                   System.out.println(postAdAttributes);
                   Assert.assertTrue(postAdAttributes.contains(testData.get("carsAttribute")));
                   break;
               case 1:
                   papPage.verticalSwipeWithCordinates( cordinate[1],cordinate[0]+20);
                   List<String> postAdAttributes1 = papPage.postAdAttributes(MobileConstants.postAdCategories.get(i));
                   System.out.println(postAdAttributes1);
                   Assert.assertTrue(postAdAttributes1.contains(testData.get("mobilesAttribute")));
                   break;
               case 2:
                   papPage.verticalSwipeWithCordinates( cordinate[1],cordinate[0]);
                   Assert.assertTrue(papPage.wareHouseOptionISPresent());
                   break;
               case 3:
                   Assert.assertTrue(papPage.validateRealestateAttribute(testData.get("realEstateAttribute")));

           }
        }
    }


    /**
     *  JOBS POST AD
     */


    @Stories("PostAd")
@Title("User should Be able To Post Ad For Jobs Category")
    @Test(dataProvider ="jobsSubcat",dataProviderClass = Data.class ,groups = "horizontal")
    public void verifyPostAnAdForJobs(String subcat)
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(subcat, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Jobs";
        System.out.println(papPage.getPapCategory());
        papPage.selectINeedAJob();
        papPage.selectRoleForJobPostAd();
        papPage.selectelementWithoutScroll(testData.get("jobsrole"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectEducationButton();
        papPage.selectelementWithoutScroll(testData.get("jobseducationOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectExperienceButton();
        papPage.selectelementWithoutScroll(testData.get("jobsexperienceOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectCurrentSalaryButton();
        papPage.selectelementWithoutScroll(testData.get("jobscurrentSalaryOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("jobsadTitleForAdPost"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("jobsadDescriptionForAdPost"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("nameOfPerson"));
        papPage.sendNumber(testData.get("number"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for jobs Subcat=" + subcat);
//        papPage.selectViewMyAdButton();
//        Assert.assertTrue(papPage.validateRoleAfterPostingAd(testData.get("jobsrole")),"value of role is not displayed on vap success page");

        //Real Estate Post Ad
    }
    @Stories("PostAd")
@Title("Post Ad Without Number As Offer Type For REalEstate")
    @Test(groups = "horizontal")
    public void postAdWithoutNumberOfferType() {

        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("realEstatesubCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("realEstaterent"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Real Estate";
        System.out.println(papPage.getPapCategory());
        papPage.PostAdAsIndividual();
        papPage.scroll("Price");
        papPage.setPrice(testData.get("price"));
        papPage.clickOnNumberOfRooms();
        papPage.selectelementWithoutScroll(testData.get("realEstaterooms"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.clickOnSelectArea(testData.get("realEstatearea"));
        papPage.clickOnFurnished();
        papPage.selectelementWithoutScroll(testData.get("realEstateSemiFurnished"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("realEstateAdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("realEstatedesc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd());


    }
    /**'
     * REAL ESTATE
     */

    /**
     * Android 1316:Verify posting the ad without providing mobile No. (both Offering and Wanted)
     */
    @Stories("PostAd")
    @Title("Post Ad Without Number As Want Type For REalEstate")

    @Test(groups = "horizontal")
    public void postAdWithoutNumberWantType() {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("realEstatesubCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("realEstaterent"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Real Estate";
        System.out.println(papPage.getPapCategory());
        papPage.PostAdAsTenantWantType();
        papPage.PostAdAsIndividual();
        papPage.scroll("Price");
        papPage.setPrice(testData.get("price"));
        papPage.selectElements("No. of Rooms", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("realEstaterooms"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Area Sq Ft", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("realEstatearea"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.selectelementWithoutScroll("Furnished", QuikrAppEnums.PAP_Radio_elements);
        alertPage.selectelementWithoutScroll(testData.get("realEstatefurnished"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.setAdTitle(testData.get("realEstateAdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("realEstatedesc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd(),"user is not able to post as Want type without number");
    }
    //services
    @Stories("PostAd")
    @Title("Post Ad  For Services")

    @Test(groups = "horizontal")
    public void verifyPostFreeAd()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String categoty="Services";
        papPage.setAdTitle(testData.get("servicesadTitle"), categoty);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("servicesadDescription"), categoty);
        papPage.navigateBack();
        papPage.scroll("Locality");
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd());
    }
    /**
     * User should be able to edit Ad from Preview screen
     */
    // @Test
    public void userIsAbleToEditAdFromPreviewScreen()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        papPage.skipPostAdHelper();
        Assert.assertTrue(papPage.validatePostAd());
        papPage.selectViewMyAdButton();
        Assert.assertTrue(papPage.validatePreviewPostAd(), "can't able to see preview ad post");
        papPage.clickOnEditAd();
        papPage.editAdTitle(testData.get("EditAdTitle"));
        papPage.navigateBack();
        papPage.scroll(testData.get("SaveAD"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd(),"user is not able to edit ad After posting an Ad");

    }
    /**
     * Carousal screen should be displayed appropriately in Post Ad success screen when there is only 1 card (Post an Ad in categories other than Cars & Bikes)
     */
    @Stories("PostAd")
    @Title("Carousal screen should be displayed appropriately in Post Ad success screen only 1 card")
@Description("Carousal screen should be displayed appropriately in Post Ad success screen when there is only 1 card (Post an Ad in categories other than Cars & Bikes) ")
    @Test
    public void verifyPostAdCarousalScreenForSingleCard()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String categoty="Services";
        papPage.setAdTitle(testData.get("servicesadTitle"), categoty);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("servicesadDescription"), categoty);
        papPage.navigateBack();
        papPage.scroll("Locality");
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validatePostAd());
        Assert.assertTrue(papPage.validatePostAdSuccessPremiumCard(),"on posting ad MAke premium card not displayed");
    }
/**
 *Carousal screen should be displayed appripriately in Post Ad success screen when there are more than 1 card (Post an Ad in Cars & Bikes)

 - Should be able to swipe between cards
 - Tapping on "No" button for Cars card should display the next ("Make Premium") Card
 - Tapping on "Yes" in Cars card should display the appropriate screen
 */
@Stories("PostAd")
@Title("Carousal screen should be displayed appropriately in Post Ad success screen more than 1card")
@Description("Carousal screen should be displayed appripriately in Post Ad success screen when there are more than 1 card (Post an Ad in Cars & Bikes)")
@Test
    public void verifyPostAdCarousalScreenForMoreThanOneCard()
    {
        Hompage hompage = new Hompage(driver);
        PapPage papPage = new PapPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("car"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        String category="Cars & Bikes";
        System.out.println(category);
        papPage.PostAdAsIndividual();
        papPage.selectConditionAsUsed();
        papPage.setPrice(testData.get("price"));
        papPage.selectBrand();
        papPage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectModel(testData.get("model"));
        papPage.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectvariant();
        papPage.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectYear();
        papPage.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.scroll("Enter Kms Driven");
        papPage.selectDistanceDriven(testData.get("distance"));
        papPage.selectColor();
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectFuelType();
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.selectOwners();
        papPage.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.setAdTitle(testData.get("AdTitle"), category);
        papPage.navigateBack();
        papPage.setAdDescription(testData.get("desc"), category);
        papPage.navigateBack();
        papPage.clickonAdlocation();
        papPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.provideName(testData.get("name"));
        papPage.submitAd();
        Assert.assertTrue(papPage.validateExchangeCarousalCard());
        Integer[]cordiantes=papPage.cordinatesForHorizontalSwipeOnPAP();
        papPage.horizontalSwipeWithCordinates(cordiantes[1], cordiantes[0], cordiantes[1]);
        Assert.assertTrue(papPage.validatePostAd(),"on clicking no buttonm user not redirected to Premium Card");
        Integer[]cordiantes1=papPage.cordinatesForHorizontalSwipecarousalPrremiCardOnPAP();
        papPage.horizontalSwipeWithCordinates(cordiantes1[1], cordiantes1[0], cordiantes1[2]);
        papPage.clickOnExchangeCarsAfterPostAd();
        papPage.skipAds();
        Assert.assertTrue(papPage.onSelectingYesButtonUserIsRedirectedToExchangePage(),"on clicking yes button user not redirected to exchange page");




    }

}
