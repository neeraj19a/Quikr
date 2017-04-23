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
 * Created by quikr on 1/6/16.
 */
public class ElectronicsAndAppTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("PAP_TESTDATA_FILE"));

    @Test
    public void HomeAndKitchenAppliancesSellPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.HOME_KITCHEN_APPLIANCES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        epap.SelectApplicanceType();
        epap.selectBrandName();
        papPage.selectCondition();
        papPage.inputPrice("5000");
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.postTheAd();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.enterAddress();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void HomeAndKitchenAppliancesBuyPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.HOME_KITCHEN_APPLIANCES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        epap.SelectApplicanceType();
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        epap.selectBrandName();
        papPage.inputAdTitle(testData.get("AdTitleMobilePhones"));
        papPage.selectCondition();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        papPage.inputDescription(testData.get("AdDescriptionMobilePhones"));
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void LaptopsAndComputersSellPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.LAPTOP_COMPUTERS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("Brand new dell laptop with i5 on sale. Contact me");
        papPage.selectCondition();
        papPage.inputPrice("5000");
        epap.selectProductType("sell");
        epap.selectBrandName();
        papPage.inputDescription("Brand new dell laptop with i5 on sale. Contact me");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.postTheAd();
        papPage.inputDescription("Brand new dell laptop with i5 on sale. Contact me");
        papPage.enterAddress();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void LaptopsAndComputersBuyPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.LAPTOP_COMPUTERS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("Brand new dell laptop with i5 on sale. Contact me");
        papPage.selectCondition();
        epap.selectProductType("buy");
        epap.selectBrandName();
        papPage.selectCondition();
        papPage.inputDescription("Brand new dell laptop with i5 on sale. Contact me");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription("Brand new dell laptop with i5 on sale. Contact me");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void TvDvdSellPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.TV_DVD_MULTIMEDIA);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("Brand new dvd player of sony on sale. Contact me");
        epap.selectProductType("sell");
        papPage.selectCondition();
        epap.selectBrandName();
        papPage.inputPrice("5000");
        papPage.inputDescription("Its a brand new Sony dvd player, in excellent condition. COntact me for information.");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.postTheAd();
        papPage.inputDescription("Its a brand new Sony dvd player, in excellent condition. COntact me for information.");
        papPage.enterAddress();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void TvDvdBuyPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.TV_DVD_MULTIMEDIA);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("Brand new dvd player of sony on sale. Contact me");
        epap.selectProductType("buy");
        papPage.inputAdTitle("Brand new dvd player of sony on sale. Contact me");
        papPage.selectCondition();
        epap.selectBrandName();
        papPage.selectCondition();
        papPage.inputDescription("Its a brand new Sony dvd player, in excellent condition. COntact me for information.");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription("Its a brand new Sony dvd player, in excellent condition. COntact me for information.");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void ComputerPeripheralsSellPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.COMPUTER_PERIPHERALS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("Excellent conditions computer spare parts on sale. COntact me for info");
        epap.selectPeripheralType("sell");
        papPage.selectCondition();
        papPage.inputPrice("2000");
        papPage.inputDescription("Excellent conditions computer spare parts on sale. COntact me for info");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.postTheAd();
        papPage.inputDescription("Excellent conditions computer spare parts on sale. COntact me for info");
        papPage.enterAddress();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void ComputerPeripheralsBuyPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.COMPUTER_PERIPHERALS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("Excellent conditions computer spare parts on sale. COntact me for info");
        epap.selectPeripheralType("buy");
        papPage.inputAdTitle("Excellent conditions computer spare parts on sale. COntact me for info");
        papPage.selectCondition();
        papPage.inputDescription("Excellent conditions computer spare parts on sale. COntact me for info");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription("Excellent conditions computer spare parts on sale. COntact me for info");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void VideoGamesConsolesSellPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.VIDEO_GAMES_CONSOLES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("Excellent conditions ps4 with game pads on sale. COntact me for info");
        papPage.selectCondition();
        epap.selectBrandName();
        papPage.inputPrice("20000");
        papPage.inputDescription("Excellent conditions ps4 with game pads on sale. COntact me for info");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.postTheAd();
        papPage.inputDescription("Excellent conditions ps4 with game pads on sale. COntact me for info");
        papPage.enterAddress();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void VideoGamesConsolesBuyPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.VIDEO_GAMES_CONSOLES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("Excellent conditions ps4 with game pads on sale. COntact me for info");
        papPage.selectCondition();
        papPage.inputDescription("Excellent conditions ps4 with game pads on sale. COntact me for info");
        epap.selectBrandName();
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription("Excellent conditions ps4 with game pads on sale. COntact me for info");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void ToolsMachinerySellPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.TOOLS_MACHINERY_INDUSTRIAL);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("If you need any factory related tools and machines contact me");
        papPage.selectCondition();
        papPage.inputPrice("3000");
        papPage.inputDescription("If you need any factory related tools and machines contact me");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.postTheAd();
        papPage.inputDescription("If you need any factory related tools and machines contact me");
        papPage.enterAddress();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void ToolsMachineryBuyPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.TOOLS_MACHINERY_INDUSTRIAL);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("If you need any factory related tools and machines contact me");
        papPage.selectCondition();
        papPage.inputDescription("If you need any factory related tools and machines contact me");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.inputDescription("If you need any factory related tools and machines contact me");
        papPage.postTheAd();
        Assert.assertTrue(epap.ifAdPostedSuccessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void CamerasDigicamsBuyPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.CAMERAS_DIGICAMS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("I have a nice sony dslr to sell off. Interested");
        epap.selectProductType("buy");
        papPage.inputAdTitle("I have a nice sony dslr to sell off. Interested");
        papPage.selectCondition();
        epap.selectBrandName();
        papPage.selectCondition();
        papPage.inputDescription("A 50 megapixels Sony dslr for sell. Get in touch soon.");
        papPage.selectSellerType();
        String email=getRandomString(10) + "@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription("A 50 megapixels Sony dslr for sell. Get in touch soon.");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void CamerasDigicamsSellPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.CAMERAS_DIGICAMS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("I have a nice sony dslr to sell off. Interested");
        epap.selectProductType("sell");
        papPage.selectCondition();
        epap.selectBrandName();
        papPage.inputPrice("30000");
        papPage.inputDescription("A 50 megapixels Sony dslr for sell. Get in touch soon.");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        papPage.inputDescription("A 50 megapixels Sony dslr for sell. Get in touch soon.");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void MusicSystemsSell(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.MUSIC_SYSTEMS_HOME_THEATRE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("I have a nice JBL music system  to sell off. Interested");
        papPage.selectCondition();
        epap.selectBrandName();
        papPage.inputPrice("20000");
        papPage.inputDescription("I have a nice JBL music system  to sell off. Interested");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        papPage.inputDescription("I have a nice JBL music system  to sell off. Interested");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void MusicSystemsBuy(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.MUSIC_SYSTEMS_HOME_THEATRE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("I have a nice JBL music system  to sell off. Interested");
        papPage.selectCondition();
        //papPage.selectCondition();
        papPage.inputDescription("I have a nice JBL music system  to sell off. Interested");
        epap.selectBrandName();
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription("I have a nice JBL music system  to sell off. Interested");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void CameraAccessoriesSellPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.CAMERA_ACCESSORIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("2 Month old Nikon DSLR Lens Cap for Sale in MG Road");
        epap.selectAccessoryType();
        papPage.inputAdTitle("2 Month old Nikon DSLR Lens Cap for Sale in MG Road");
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription("2 Month old Nikon DSLR Lens Cap for Sale in MG Road");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        papPage.inputDescription("2 Month old Nikon DSLR Lens Cap for Sale in MG Road");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void CameraAccessoriesBuyPap(){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.CAMERA_ACCESSORIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("2 Month old Nikon DSLR Lens Cap for Sale in MG Road");
        epap.selectAccessoryType();
        papPage.inputAdTitle("2 Month old Nikon DSLR Lens Cap for Sale in MG Road");
        papPage.selectCondition();
        papPage.inputDescription("2 Month old Nikon DSLR Lens Cap for Sale in MG Road");
        papPage.selectSellerType();
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.postTheAd();
        papPage.inputDescription("2 Month old Nikon DSLR Lens Cap for Sale in MG Road");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void iPodsMP3PlayersSellPap() {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.IPODS_MP3_PLAYERS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("2 Year old 32GB 4th Gen iPod Touch for sale in MG Road");
        papPage.selectCondition();
        epap.selectBrandName();
        papPage.inputPrice("20000");
        papPage.inputDescription("2 Year old 32GB 4th Gen iPod Touch for sale in MG Road");
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        papPage.inputDescription("2 Year old 32GB 4th Gen iPod Touch for sale in MG Road");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void iPodsMP3PlayersBuyPap() {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.IPODS_MP3_PLAYERS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("2 Year old 32GB 4th Gen iPod Touch for sale in MG Road");
        papPage.selectCondition();
        epap.selectBrandName();
        papPage.selectCondition();
        papPage.inputDescription("2 Year old 32GB 4th Gen iPod Touch for sale in MG Road");
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.inputDescription("2 Year old 32GB 4th Gen iPod Touch for sale in MG Road");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void FaxEPABXOfficeEquipmentSellPap() {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.FAX_EPABX_OFFICE_EQUIPMENT);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        papPage.inputAdTitle("I am looking for Fax, EPABX, Office Equipment");
        epap.selectProductType("sell");
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription("I am looking for Fax, EPABX, Office Equipment");
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.inputDescription("I am looking for Fax, EPABX, Office Equipment");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void FaxEPABXOfficeEquipmentBuyPap() {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.FAX_EPABX_OFFICE_EQUIPMENT);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        papPage.inputAdTitle("I am looking for Fax, EPABX, Office Equipment");
        epap.selectProductType("buy");
        papPage.inputDescription("I am looking for Fax, EPABX, Office Equipment");
        papPage.selectCondition();
        papPage.selectSellerType();
        String email = getRandomString(11) + "@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.inputDescription("I am looking for Fax, EPABX, Office Equipment");
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void OfficeSuppliesSellPap() {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.OFFICE_SUPPLIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        String inputDescription="I am looking for Office Supplies on immediate basis, Pls reach me";
        papPage.inputAdTitle(inputDescription);
        papPage.selectCondition();
        papPage.inputPrice("20000");
        papPage.inputDescription(inputDescription);
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560029");
        papPage.postTheAd();
        papPage.inputDescription(inputDescription);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }

    @Test
    public void OfficeSuppliesBuyPap() {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.OFFICE_SUPPLIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(2);
        String inputDescription="I am looking for Office Supplies on immediate basis, Pls reach me";
        papPage.inputAdTitle(inputDescription);
        papPage.selectCondition();
        papPage.inputDescription(inputDescription);
        papPage.selectSellerType();
        String email = getRandomString(10) + "@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.inputDescription(inputDescription);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }



    @Test
        public void EverythingElseSellPap() {
        HomePage homePage = new HomePage(driver);
        PAPPage papPage = new PAPPage(driver);
        ElectronicsAndAppPap epap = new ElectronicsAndAppPap(driver);

        waitForPageToLoad(testData.get("quikrUrl"));
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.ELECTRONICS_AND_APPLIANCES, QuikrEnums.ElectronicAppliancesSubCat.EVERYTHING_ELSE);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        papPage.selectAdType(1);
        String inputDescription="I am looking for Security Equipment - Products on immediate basis, Pls reach me";
        papPage.inputAdTitle(inputDescription);
        papPage.inputPrice("5000");
        papPage.inputDescription(inputDescription);
        String email = getRandomString(10) + "@gmail.com";
        papPage.enterEmail(email);
        papPage.enterMobileNumber();
        papPage.enterCity("Bangalore");
        papPage.enterAddress();
        papPage.selectLocality();
        papPage.enterPincode("560028");
        papPage.inputDescription(inputDescription);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "The ad was not posted successfully. Please check!");
    }
}
