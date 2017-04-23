package com.quikr.app.android.escrowTests;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.app.android.vap.VapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

public class EscrowPostAdTests  extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_ESCROW_TESTDATA_FILE"));
    String AdId=null;

    @Title("PostAd : Mandatory Field Check")
    @Description("To check the behaviour of mandatory fields")
    @Test(groups = {"Stage","Prod"})
    public void postAdMandatoryFieldCheck()
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.postYourAd();
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("brandMandateErrorMsg")),"Brand mandate error message NOT present");
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("titleMandateErrorMsg")),"Title mandate error message NOT present");
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("descriptionMandateErrorMsg")),"Description mandate error message NOT present");
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("priceMandateErrorMsg")),"Price mandate error message NOT present");
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("addressMandateErrorMsg")),"Address mandate error message NOT present");
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("localityMandateErrorMsg")),"Locality mandate error message NOT present");
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("pincodeMandateErrorMsg")),"Pincode mandate error message NOT present");
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("emailMandateErrorMsg")),"Email mandate error message NOT present");
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("mobileMandateErrorMsg")),"Mobile mandate error message NOT present");
    }
    @Title("PostAd : Title and Description Length validation")
    @Description("To check the behaviour of Length validation in Title and Description fields")
    @Test(groups = {"Stage","Prod"})
    public void postAdTitleAndDescriptionLengthCheck()
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectCondition(testData.get("condition"));
        postAdV2Page.selectBrand(testData.get("selectBrand"),testData.get("Mobilebrand"));
        postAdV2Page.selectModel(testData.get("SelectModel"),testData.get("Mobilesmodel"));
        postAdV2Page.enterTitle(testData.get("titleLength"));
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("lengthError")),"Title length validation message NOT present");
        postAdV2Page.enterDescription(testData.get("descriptionLength"));
        Assert.assertTrue(postAdV2Page.ifMandateErrorPresent(testData.get("lengthError")),"Description length validation message NOT present");
    }

    @Title("PostAd : Minimum Price: Check if present")
    @Description("To check presence of Minimum price textfield for escrow categories : Mobiles")
    @Test(groups = {"Stage","Prod"})
    public void minimumPriceFieldCheckForAllMobilesCategories()
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        Assert.assertTrue(postAdV2Page.validateMinimumPrice(testData.get("minimumPrice")),"Minimum Price field present");
        Assert.assertTrue(postAdV2Page.validateMinimumPriceForRemainingCategories(testData.get("minimumPrice"),testData.get("mobilesSubCats")),"Minimum price field present for rest SubCats");
    }

    @Title("PostAd : Minimum Price: Check if present")
    @Description("To check presence of Minimum price textfield and Sell For Me for escrow categories : Electronics")
    @Test(groups = {"Stage","Prod"})
    public void minimumPriceFieldCheckForAllElectronicsCategories()
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("electronicscat"));
        postAdV2Page.selectSubCat(testData.get("electronicsSubCat"));
        postAdV2Page.waitForPapToLoad();
        Assert.assertTrue(postAdV2Page.validateMinimumPrice(testData.get("minimumPrice")),"Minimum Price field present");
        Assert.assertTrue(postAdV2Page.validateMinimumPriceForRemainingCategories(testData.get("minimumPrice"),testData.get("electronicsSubCats")),"Minimum price field present for rest SubCats");
    }

    @Title("PostAd : Minimum Price: Check if present")
    @Description("To check presence of Minimum price textfield and Sell For Me for escrow categories : Homes & Appliances")
    @Test(groups = {"Stage","Prod"})
    public void minimumPriceFieldCheckForAllHomeAppliancesCategories()
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("homecat"));
        postAdV2Page.selectSubCat(testData.get("homeSubCat"));
        postAdV2Page.waitForPapToLoad();
        Assert.assertTrue(postAdV2Page.validateMinimumPrice(testData.get("minimumPrice")),"Minimum Price field present");
        Assert.assertTrue(postAdV2Page.validateMinimumPriceForRemainingCategories(testData.get("minimumPrice"),testData.get("homeSubCats")),"Minimum price field present for rest SubCats");
    }

/* Need dev fix for this test
    @Title("PostAd : Minimum Price: Check presence of benefits pop up")
    @Description("To check presence if Minimum price Benefits Pop up is displayed on tapping Know More")
    @Test(groups = {"Stage","Prod"})
    public void minimumPriceBenefitsCheck()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectElements(testData.get("minPriceKnowMore"), QuikrAppEnums.PostAd_EscrowExtraText);
    }
*/

    @Title("PostAd : Without Minimum Price")
    @Description("To check if an Ad can be posted WITHOUT entering Minimum price")
    @Test(groups = {"Stage","Prod"})
    public void postAdWithoutMinimumPrice()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectCondition(testData.get("condition"));
        postAdV2Page.selectBrand(testData.get("selectBrand"),testData.get("Mobilebrand"));
        postAdV2Page.selectModel(testData.get("SelectModel"),testData.get("Mobilesmodel"));
        postAdV2Page.enterTitle(testData.get("MobileAdTitle"));
        postAdV2Page.enterDescription(testData.get("mobiltAdDesc"));
        postAdV2Page.enterprice(testData.get("mobilesPrice"));
        postAdV2Page.enterAddress(testData.get("userAddress"));
        postAdV2Page.selectLocality(testData.get("location"));
        postAdV2Page.enterPincode(testData.get("userPin"));
        postAdV2Page.enterEmail(testData.get("sellerEmail"));
        postAdV2Page.enterNumber(testData.get("userNumber"));
        postAdV2Page.postYourAd();
        postAdV2Page.selectPostAdWOPhotos();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
    }

    @Title("PostAd : With minimum price")
    @Description("To check if an Ad can be posted WITH Minimum price")
    @Test(groups = {"Stage","Prod"})
    public void postAdWithMinPrice()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectCondition(testData.get("condition"));
        postAdV2Page.selectBrand(testData.get("selectBrand"),testData.get("Mobilebrand"));
        postAdV2Page.selectModel(testData.get("SelectModel"),testData.get("Mobilesmodel"));
        postAdV2Page.enterTitle(testData.get("MobileAdTitle"));
        postAdV2Page.enterDescription(testData.get("mobiltAdDesc"));
        postAdV2Page.enterprice(testData.get("mobilesPrice"));
        postAdV2Page.enterMinprice(testData.get("minimumPrice"));
        postAdV2Page.enterAddress(testData.get("userAddress"));
        postAdV2Page.selectLocality(testData.get("location"));
        postAdV2Page.enterPincode(testData.get("userPin"));
        postAdV2Page.enterEmail(testData.get("sellerEmail"));
        postAdV2Page.enterNumber(testData.get("userNumber"));
        postAdV2Page.postYourAd();
        postAdV2Page.selectPostAdWOPhotos();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
    }

    @Title("PostAd : Check Absence of minimum price in Non Escrow cities")
    @Description("To check if Minimum price field is not displayed under Non-Escrow cities")
    @Test(groups = {"Stage","Prod"},dataProvider = "NonEscrowcities",dataProviderClass = Data.class)
    public void minPriceNotPresentInNonEscrowCity(String NonEscrowCities)
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        hompage.selectCity();
        headerPage.searchCity(NonEscrowCities);
        hompage.selectelementWithoutScroll(NonEscrowCities, QuikrAppEnums.Hompage_SelectCity);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        Assert.assertFalse(postAdV2Page.validateMinimumPrice(testData.get("minimumPrice")),"Minimum Price field present");
    }

    @Title("PostAd : With minimum price : Edit Ad : Check if minimum price is populated")
    @Description("To check if an Ad which was posted with a minimum price, upon editing, auto populates the previously entered Minimum price")
    @Test(groups = {"Stage","Prod"})
    public void minPricePreFilledOnEditingMinPriceAd()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        VapPage vapPage = new VapPage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectCondition(testData.get("condition"));
        postAdV2Page.selectBrand(testData.get("selectBrand"),testData.get("Mobilebrand"));
        postAdV2Page.selectModel(testData.get("SelectModel"),testData.get("Mobilesmodel"));
        postAdV2Page.enterTitle(testData.get("MobileAdTitle"));
        postAdV2Page.enterDescription(testData.get("mobiltAdDesc"));
        postAdV2Page.enterprice(testData.get("mobilesPrice"));
        postAdV2Page.enterMinprice(testData.get("mobilesMinPrice"));
        postAdV2Page.enterAddress(testData.get("userAddress"));
        postAdV2Page.selectLocality(testData.get("location"));
        postAdV2Page.enterPincode(testData.get("userPin"));
        postAdV2Page.enterEmail(testData.get("sellerEmail"));
        postAdV2Page.enterNumber(testData.get("userNumber"));
        postAdV2Page.postYourAd();
        postAdV2Page.selectPostAdWOPhotos();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
        postAdV2Page.selectViewMyAd();
        postAdV2Page.exitAd();
        postAdV2Page.handleOverlay();
        vapPage.clickEditAd();
        Assert.assertTrue(postAdV2Page.validateMinimumPriceAmount(testData.get("mobilesMinPrice")),"Minimum price not present on editing");
    }

    @Title("Sync & Scan : Run the Test will generate a report. View Report for all attributes tested.")
    @Description("Verify Run Sync&Scan test comes for Android.")
    @Test(groups = {"Stage","Prod"})
    public void checkSyncScanTestAttributes()
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        Assert.assertTrue(postAdV2Page.validateSyncScanRunTest(),"Run Sync&Scan Test NOT present");
        postAdV2Page.runQuikrScanner();
        Assert.assertTrue(postAdV2Page.validateScannerRunSuccess(),"Scanner Run Successfull");
        postAdV2Page.viewScannerReport();
        Assert.assertTrue(postAdV2Page.validateScannerReportOpen(),"Scanner Report is Open");
    }

    @Title("Sync & Scan : Present or Not")
    @Description("Verify Run Sync&Scan test comes for Android.")
    @Test(groups = {"Stage","Prod"})
    public void checkSyncScanPresent()
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        Assert.assertTrue(postAdV2Page.validateSyncScanRunTest(),"Run Sync&Scan Test NOT present");
    }

    @Title("Sync & Scan : Verify Run the Test for other category not present")
    @Description("Verify Run the Test for other category not present")
    @Test(groups = {"Stage","Prod"})
    public void checkSyncScanNotPresent()
    {
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("electronicscat"));
        postAdV2Page.selectSubCat(testData.get("electronicsSubCat"));
        postAdV2Page.waitForPapToLoad();
        Assert.assertFalse(postAdV2Page.validateSyncScanRunTest(),"Run Sync&Scan Test IS present");
    }

    @Title("Sync & Scan : Edit the ad and verify True value strip with S&S score comes.")
    @Description("Post Ad : Perform Scan : EditAd : Verify SnS report present")
    @Test(groups = {"Stage","Prod"} )
    public void checkScannerReportPresentOnEditAd(){
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        VapPage vapPage = new VapPage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectCondition(testData.get("condition"));
        postAdV2Page.selectBrand(testData.get("selectBrand"),testData.get("Mobilebrand"));
        postAdV2Page.selectModel(testData.get("SelectModel"),testData.get("Mobilesmodel"));
        postAdV2Page.enterTitle(testData.get("MobileAdTitle"));
        postAdV2Page.enterDescription(testData.get("mobiltAdDesc"));
        postAdV2Page.enterprice(testData.get("mobilesPrice"));
        postAdV2Page.enterMinprice(testData.get("mobilesMinPrice"));
        postAdV2Page.enterAddress(testData.get("userAddress"));
        postAdV2Page.selectLocality(testData.get("location"));
        postAdV2Page.enterPincode(testData.get("userPin"));
        postAdV2Page.enterEmail(testData.get("sellerEmail"));
        postAdV2Page.enterNumber(testData.get("userNumber"));
//        Assert.assertTrue(postAdV2Page.validateSyncScanRunTest(),"Run Sync&Scan Test NOT present");
        postAdV2Page.runQuikrScanner();
        postAdV2Page.postYourAd();
        postAdV2Page.selectPostAdWOPhotos();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
        postAdV2Page.selectViewMyAd();
        postAdV2Page.exitAd();
        postAdV2Page.handleOverlay();
        vapPage.clickEditAd();
        Assert.assertTrue(postAdV2Page.validateSyncScanStripeOnEditAd(),"Minimum price not present on editing");

    }

    @Title("PostAd : Back button scenarios")
    @Description("Post Ad : Back button, Exit PostAd, Start fresh Ad, Continue Same ad")
    @Test(groups = {"Stage","Prod"})
    public void postAdBackButtonTests(){
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("electronicscat"));
        postAdV2Page.selectSubCat(testData.get("electronicsSubCat"));
        postAdV2Page.waitForPapToLoad();
//        check if Not cancelling post ad remins on post ad page
        postAdV2Page.navigateBack();
        postAdV2Page.cancelPosting(false);
        Assert.assertTrue(postAdV2Page.backToPostAd(),"Back to Post Free Ad page");
//       check if cancelling post ad takes to home page
        postAdV2Page.navigateBack();
        postAdV2Page.cancelPosting(true);
        Assert.assertFalse(hompage.onHomePage(),"Back to Home Page");
//        check if clicking post ad again asks to start fresh Or continue
        hompage.clickonPostAd();
        postAdV2Page.postAdAsContine();
        Assert.assertTrue(postAdV2Page.backToPostAd(),"Back to Post Free Ad page");
    }

    @Title("PostAd : Create Auction Ad")
    @Description("PostAd : Verify if able to post Auction Ad")
    @Test(groups = {"Stage","Prod"},dataProvider = "AuctionCities",dataProviderClass = Data.class)
    public void postAuctionAd(String auctionCity){
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        hompage.selectCity();
        headerPage.searchCity(auctionCity);
        hompage.selectelementWithoutScroll(auctionCity, QuikrAppEnums.Hompage_SelectCity);
        hompage.clickonPostAd();
        postAdV2Page.selectCat(testData.get("mobilescat"));
        postAdV2Page.selectSubCat(testData.get("mobilesSubCat"));
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectCondition(testData.get("condition"));
        postAdV2Page.selectBrand(testData.get("selectBrand"),testData.get("Mobilebrand"));
        postAdV2Page.selectModel(testData.get("SelectModel"),testData.get("Mobilesmodel"));
        postAdV2Page.enterTitle(testData.get("MobileAdTitle"));
        postAdV2Page.enterDescription(testData.get("mobiltAdDesc"));
        postAdV2Page.enterprice(testData.get("mobilesPrice"));
        postAdV2Page.enterAddress(testData.get("userAddress"));
        postAdV2Page.selectLocality(testData.get("location"));
        postAdV2Page.enterPincode(testData.get("userPin"));
        postAdV2Page.enterEmail(testData.get("sellerEmail"));
        postAdV2Page.enterNumber(testData.get("userNumber"));
        postAdV2Page.selectAuctionPeriod(testData.get("auctionPeriod"));
        postAdV2Page.enterAuctionAmount(testData.get("auctionAmount"));
        postAdV2Page.postYourAd();
        postAdV2Page.selectPostAdWOPhotos();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
    }
}
