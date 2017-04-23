package com.quikr.msite.mHorizontal.mHorizontalMSP;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mElectronics.mElectronicsSNB.MElectronicsSnBPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mHorizontal.mMSP.MMSPPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 12/11/15.
 */
public class MHorizontalMSPTests extends MTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("mHORIZONTAL_TESTDATA_FILE"));

    /* MS-659:Verify 'MSP' page navigation
    */
    @Test(groups = "horizontal")
    public void validateMSPPageRedirect(){
        MHomePage mHomePage=new MHomePage(driver);
        MMSPPage mMSPPage =new MMSPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMSP();
        waitForPageToLoad(testData.get("mspURL"));
        Assert.assertTrue(mMSPPage.isMSPPageavailable(), "Failed to Load MSP Page");
    }

    /*MS-660:Verify the contents of the MSP page
    */
    @Test(groups = "horizontal")
    public void validateMSPContents(){
        MHomePage mHomePage=new MHomePage(driver);
        MMSPPage mMSPPage =new MMSPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMSP();
        waitForPageToLoad(testData.get("mspURL"));
        Assert.assertTrue(mMSPPage.isMSPPageavailable(), "Failed to Load MSP Page");
        Assert.assertTrue(mMSPPage.isPostAdButtonMSPavailable(), "Failed To Load Post Ad Button on MSP");
        Assert.assertTrue(mHomePage.isSelectCityavailable(),"Failed to Load Select City on MSP Page");
    }

    /*MS-557:Verify Sell/want option are present
    */
    @Test(groups = "horizontal")
    public void validateSellandWantMSPPage(){
        MHomePage mHomePage=new MHomePage(driver);
        MMSPPage mMSPPage =new MMSPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMSP();
        waitForPageToLoad(testData.get("mspURL"));
        Assert.assertTrue(mMSPPage.isBuyavailable() && mMSPPage.isSellavailable(), "Failed to Load Buy and Sell on MSP Page");
    }

    /*MS-558:Verify all the category and sub categories are present
    */
    @Test(groups = "horizontal")
    public void validateCategoriesandSubCategoriesMSPPage(){
        MHomePage mHomePage=new MHomePage(driver);
        MMSPPage mMSPPage =new MMSPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMSP();
        waitForPageToLoad(testData.get("mspURL"));
        Assert.assertEquals(mMSPPage.allcategories().get(0), "Select Category", "Failed to Load Select Category");
        Assert.assertEquals(mMSPPage.allcategories().get(1), "Cars & Bikes", "Failed to Load Cars & Bikes");
        Assert.assertEquals(mMSPPage.allcategories().get(2), "Home & Lifestyle","Failed to Load Home & Lifestyle");
        Assert.assertEquals(mMSPPage.allcategories().get(3), "Mobile Phones", "Failed to Load Mobile Phones");
        Assert.assertEquals(mMSPPage.allcategories().get(4), "Electronics & Technology", "Failed to Load Electronics & Technology");
        Assert.assertEquals(mMSPPage.allSubcategoriesCarsandBikes().get(0), "Select Sub-Category", "Failed to Load Select Sub-Category");
        Assert.assertEquals(mMSPPage.allSubcategoriesCarsandBikes().get(1), "Bikes & Scooters", "Failed to Load Bikes & Scooters");
        Assert.assertEquals(mMSPPage.allSubcategoriesCarsandBikes().get(2), "Cars", "Failed to Load Cars");
        Assert.assertEquals(mMSPPage.allSubcategoriesHomeandLifeStyle().get(0), "Select Sub-Category", "Failed to Load Select Sub-Category");
        Assert.assertEquals(mMSPPage.allSubcategoriesHomeandLifeStyle().get(1), "Air Conditioners", "Failed to Load Select Sub-Category");
        Assert.assertEquals(mMSPPage.allSubcategoriesMobilePhones().get(0), "Select Sub-Category", "Failed to Load Select Sub-Category");
        Assert.assertEquals(mMSPPage.allSubcategoriesMobilePhones().get(1), "Mobile Phones", "Failed to Load Mobile Phones");
        Assert.assertEquals(mMSPPage.allSubcategoriesElectronicsandTechnology().get(0), "Select Sub-Category", "Failed to Load Select Sub-Category");
        Assert.assertEquals(mMSPPage.allSubcategoriesElectronicsandTechnology().get(1), "TV - DVD - Multimedia", "Failed to Load TV - DVD - Multimedia");
        Assert.assertEquals(mMSPPage.allSubcategoriesElectronicsandTechnology().get(2), "Home - Kitchen Appliances", "Home - Kitchen Appliances");
    }

    /*MS-662:Verify MSP for Buy option
    */
    @Test(groups = "horizontal")
    public void validatePostAdButtonBuyMSPPage() {
        MHomePage mHomePage = new MHomePage(driver);
        MMSPPage mMSPPage = new MMSPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMSP();
        waitForPageToLoad(testData.get("mspURL"));
        mMSPPage.clickBuyMSP();
        mMSPPage.selectCategoryMSP(testData.get("category"));
        mMSPPage.selectSubCategoryMSP(testData.get("subCategory"));
        mMSPPage.selectBrandMSP(testData.get("brand"));
        mMSPPage.selectModelMSP(testData.get("model"));
        mMSPPage.selectYearMSP(testData.get("year"));
        mMSPPage.clickCalculateButtonMSP();
        Assert.assertTrue(mMSPPage.isMatchingAdsButtonMSPavailable(), "Failed to Load View Matching Ads Button on MSP Page after Calculating MSP for Product Buying");
    }


    /*MS-663:Verify 'View Matching Ads' option
    */
    @Test(groups = "horizontal")
    public void validateMatchingAdsMSPPage() {
        MHomePage mHomePage = new MHomePage(driver);
        MMSPPage mMSPPage = new MMSPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMSP();
        waitForPageToLoad(testData.get("mspURL"));
        mMSPPage.clickBuyMSP();
        mMSPPage.selectCategoryMSP(testData.get("category"));
        mMSPPage.selectSubCategoryMSP(testData.get("subCategory"));
        mMSPPage.selectBrandMSP(testData.get("brand"));
        mMSPPage.selectModelMSP(testData.get("model"));
        mMSPPage.selectYearMSP(testData.get("year"));
        mMSPPage.clickCalculateButtonMSP();
        Assert.assertTrue(mMSPPage.isMatchingAdsButtonMSPavailable(), "Failed to Load View Matching Ads Button on MSP Page after Calculating MSP for Product Buying");
        mMSPPage.clickMatchingAds();
        Assert.assertTrue(waitForPageToLoad(testData.get("MatchingAdsBikes")),"Failed to Load Matching Ads Page");
    }

    /* MS-664:Verify MSP for Sell option
    */
    @Test(groups = "horizontal")
    public void validatePostAdButtonSellMSPPage() {
        MHomePage mHomePage = new MHomePage(driver);
        MMSPPage mMSPPage = new MMSPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMSP();
        waitForPageToLoad(testData.get("mspURL"));
        mMSPPage.clickSellMSP();
        mMSPPage.selectCategoryMSP(testData.get("category"));
        mMSPPage.selectSubCategoryMSP(testData.get("subCategory"));
        mMSPPage.selectBrandMSP(testData.get("brand"));
        mMSPPage.selectModelMSP(testData.get("model"));
        mMSPPage.selectYearMSP(testData.get("year"));
        mMSPPage.clickCalculateButtonMSP();
        Assert.assertTrue(mMSPPage.isMSPResultBoxDisplayed(), "Failed to Load MSP Result Box");
        Assert.assertTrue(mMSPPage.isPostAdSellMSPbuttonavailable(), "Failed to Load Post Free Ad Button on MSP Page after Calculating MSP for Product Selling");
    }

    /* MS-665:Verify 'Post Free Ad' button access on MSP page
  */
    @Test(groups = "horizontal")
    public void validatePostAdButtonPAPRedirectionMSPPage() {
        MHomePage mHomePage = new MHomePage(driver);
        MMSPPage mMSPPage = new MMSPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        //mHomePage.clickCloseMostTrustedBrandPopUp();

        mHomePage.clickMSP();
        waitForPageToLoad(testData.get("mspURL"));
        mMSPPage.clickSellMSP();
        mMSPPage.selectCategoryMSP(testData.get("category"));
        mMSPPage.selectSubCategoryMSP(testData.get("subCategory"));
        mMSPPage.selectBrandMSP(testData.get("brand"));
        mMSPPage.selectModelMSP(testData.get("model"));
        mMSPPage.selectYearMSP(testData.get("year"));
        mMSPPage.clickCalculateButtonMSP();
        Assert.assertTrue(mMSPPage.isMSPResultBoxDisplayed(), "Failed to Load MSP Result Box");
        Assert.assertTrue(mMSPPage.isPostAdSellMSPbuttonavailable(), "Failed to Load Post Free Ad Button on MSP Page after Calculating MSP for Product Selling");
        mMSPPage.clickPostAdButtonSellMSP();
        Assert.assertTrue(waitForPageToLoad(testData.get("papPage")),"Failed to Load Post Ad Page");
    }

}
