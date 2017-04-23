package com.quikr.website.horizontal.pap;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 30/5/16.
 */
public class MatrimonyPapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("PAP_TESTDATA_FILE"));

    @Test
    public void PostAdMatrimonialwithGrooms(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        MatrimonialPap matrimonialPap=new MatrimonialPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MATRIMONIAL, QuikrEnums.MatrimonialSubCat.GROOMS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" Looking for a Groom from a decent family "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        matrimonialPap.selectCommonMatrimonialOptions();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "Looks Like Ad was not posted Successfully Pls Check");
    }


    @Test
    public void PostAdMatrimonialwithBrides(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        MatrimonialPap matrimonialPap=new MatrimonialPap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MATRIMONIAL, QuikrEnums.MatrimonialSubCat.BRIDES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" Looking for a Bride from a decent family "+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        matrimonialPap.selectCommonMatrimonialOptions();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test
    public void PostAdMatrimonialwithWeddingPlanners(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.MATRIMONIAL, QuikrEnums.MatrimonialSubCat.WEDDING_PLANNERS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" Looking for a reputed WEDDING PLANNER"+getRandomString(10);
        papPage.inputAdTitle(adTitle);
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }
}
