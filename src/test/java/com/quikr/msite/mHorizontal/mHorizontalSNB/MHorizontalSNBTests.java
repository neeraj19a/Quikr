package com.quikr.msite.mHorizontal.mHorizontalSNB;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mElectronics.mElectronicsHome.MElectronicsHomePage;
import com.quikr.msite.mElectronics.mElectronicsSNB.MElectronicsSnBPage;
import com.quikr.msite.mHorizontal.mHeader.MHeaderPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mHorizontal.mSNB.MSNBPage;
import com.quikr.msite.mHorizontal.mSearch.MSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 12/11/15.
 */
public class MHorizontalSNBTests extends MTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("mHORIZONTAL_TESTDATA_FILE"));

    /*
MS-571:SnB: Verify 'SnB' page navigation
*/
    @Test(groups = "horizontal")
    public void validateSnBPage(){
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickElectronicsandAppliances();
        waitForPageToLoad(testData.get("validpage"));
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page");
    }

    /* MS-572:SnB: Verify the contents on the SnB page
    */
    @Test(groups = "horizontal")
    public void validateContentsonSnBPage() {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        waitForPageToLoad(testData.get("validpage"));
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        Assert.assertTrue(mElectronicsSnBPage.iscontentsonSnBPageavailable(), "Failed to Load Sort, Filter , SearchBox ,Adlist on SnB Page");
    }

    /*MS-573:SnB: Verify 'Search' functionality
    */
    @Test(groups = "horizontal")
    public void validateSearchFunctionality() {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);
        MSearchPage mSearchPage=new MSearchPage(driver);
        MHeaderPage mHeaderPage=new MHeaderPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.search();
        waitForPageToLoad(testData.get("validpage"));
        mSearchPage.searchKeyword(testData.get("mobile"));

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page when perfomed Search from Home Page");

        mHeaderPage.peformsearch(testData.get("mobile"));

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page when performed search from SNB Page");
        mHeaderPage.goToQuikrHome();
        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        Assert.assertTrue(mElectronicsSnBPage.iscontentsonSnBPageavailable(), "Failed to Load Sort, Filter , SearchBox ,Adlist on SnB Page ");

        mHeaderPage.peformsearch(testData.get("mobile"));

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page when performed search from Category");

    }

    /*MS-574:SnB: Verify 'Sort' functionality
    */
    @Test(groups = "horizontal")
    public void validateSortFunctionality() {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("ComputersandPeripherals"));

        mElectronicsSnBPage.clickSort(testData.get("lowestpricesorttype"));
        waitForPageToLoad(testData.get("validpage"));

        Assert.assertTrue(mElectronicsSnBPage.validateSortLowestPrice(), "Prices are not sorted for lowest Price Filter ");
        mElectronicsSnBPage.clickSort(testData.get("highestpricesorttype"));
        waitForPageToLoad(testData.get("validpage"));

        Assert.assertTrue(mElectronicsSnBPage.validateSortHighestPrice(), "Prices are not sorted for highest Price Filter");
        mElectronicsSnBPage.clickSort(testData.get("mostrecentsorttype"));
        waitForPageToLoad(testData.get("validpage"));

        Assert.assertTrue(mElectronicsSnBPage.validateMostRecentSort(), "Most Reecent Sort Failed");
        mElectronicsSnBPage.clickSort(testData.get("withpicturesorttype"));
        waitForPageToLoad(testData.get("validpage"));

        Assert.assertTrue(mElectronicsSnBPage.validateSortwithPictures(),"Failed to Sort with pictures");
    }

    /*
    MS-575:SnB: Verify 'Filter' functionality
     */
    @Test(groups = "horizontal")
    public void validateFilterFunctionality()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        mElectronicsSnBPage.clickFilter();
        Assert.assertTrue(waitForPageToLoad("filter"), "Fiter page was not loaded. Please check!");

        mElectronicsSnBPage.clickUsedConditionFilterPage();
        mElectronicsSnBPage.clickIndividualFilterPage();
        mElectronicsSnBPage.selectProductType(testData.get("ProdTypeSnbFilter"));
        mElectronicsSnBPage.applyFilter();

        mElectronicsSnBPage.clickOnAnAd();

        Assert.assertTrue(mElectronicsSnBPage.validateFilters(), "Filter results not as expected. Please check!");
    }

    /*
    MS-655:Verify Buy Now on SnB page
     */
    @Test
    public void verifyBuyNow()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));
        waitForPageToLoad("Laptops-Computers");

        mElectronicsSnBPage.clickBuyNow();
        Assert.assertTrue(mElectronicsSnBPage.validateBuyNowWindow(), "The buy window probably didn't show up. Please check!");
    }

    /*
    MS-588:Make an offer on SNB
     */
    @Test
    public void verifyMakeAnOffer()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        mElectronicsSnBPage.clickMakeAnOffer();
        mElectronicsSnBPage.enterOfferPrice(testData.get("offerPrice"));
        mElectronicsSnBPage.enterMail(testData.get("JunkMail"));
        mElectronicsSnBPage.enterPhNum(testData.get("profilePhNum"));
        mElectronicsSnBPage.clickSendMyOfferButton();

        Assert.assertTrue(mElectronicsSnBPage.validateMakeAnOfferSuccess(),"Make an offer didn't work properly. Confirmation message is missing. Please check!");
    }

    /*
    MS-675:Chat button on SnB
     */
    @Test
    public void verifyChatButton()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage =new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));
        waitForPageToLoad("Laptops-Computers");

        mElectronicsSnBPage.clickChatButton();
        mElectronicsSnBPage.sendQues(testData.get("ques"));
        mElectronicsSnBPage.sendEmail(testData.get("JunkMail"));
        mElectronicsSnBPage.sendPhNum(testData.get("profilePhNum"));
        mElectronicsSnBPage.clickSubmit();
        Assert.assertTrue(mElectronicsSnBPage.verifyChatNowSuccess(),"Unable to Initiate Chat");
    }

    /*
    MS-669:SnB: Verify SnB in all vernacular languages
     */
    @Test
    public void verifySnbInVernacLang()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MSNBPage msnbPage = new MSNBPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage=new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickHindiLink();

        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickLaptopAndComputers();
        //waitForPageToLoad("Laptops---Computers");

        Assert.assertTrue(msnbPage.checkIfSnbPageTranslatedToVernac(),"The snb page didn't get translated. Please check!");

    }

    /*MS-670:Search: Verify 'Search' functionality in all vernacular languages
    */
    @Test(groups = "horizontal")
    public void validateSearchFunctionalityVernac() {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsSnBPage mElectronicsSnBPage = new MElectronicsSnBPage(driver);
        MSearchPage mSearchPage = new MSearchPage(driver);
        MHeaderPage mHeaderPage = new MHeaderPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickLanguage(3);

        mHomePage.search();
        waitForPageToLoad(testData.get("validpage"));
        mSearchPage.searchKeyword("மொபைல்");

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page when perfomed Search from Home Page In Tamil");
        mHeaderPage.peformsearch("மொபைல்");

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page when performed search from SNB Page in Tamil");
        mHeaderPage.goToQuikrHome();

        mHomePage.clickLanguage(4);

        waitForPageToLoad(testData.get("validpage"));
        mHomePage.search();
        waitForPageToLoad(testData.get("validpage"));
        waitForPageToLoad(testData.get("homepage"));
        mSearchPage.searchKeyword("మొబైల్");

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page when perfomed Search from Home Page In Telugu");
        mHeaderPage.peformsearch("మొబైల్்");

        Assert.assertTrue(mElectronicsSnBPage.isSnBPageavailable(), "Failed to Load SnB Page when performed search from SNB Page in Telugu");

    }


}
