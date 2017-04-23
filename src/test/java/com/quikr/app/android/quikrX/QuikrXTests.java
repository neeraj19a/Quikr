package com.quikr.app.android.quikrX;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra singh on 1/9/15.
 */
public class QuikrXTests extends AppAndroidTestBase
{


    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_QuikrX_TESTDATA_FILE"));

    //    /**
//     * //The QuikrX option should be available on the home screen for specific locations
//     * swatantra singh
//     */
//    @Test
//    public void ValidateQuikrXOptionOnHomePage()
//    {
//        Hompage hompage = new Hompage(driver);
//        QuikrXPage quikrXPage=new QuikrXPage(driver);
//        hompage.swipeToQuikrX();
//        Assert.assertTrue(quikrXPage.validateQuikrXbuttonOnHomepage());
//    }
//
//    /**
//     *  Clicking anywhere on the QuikrX tile or on the"Go" button ,user should be directed to QuikrX screen
//     *  swatantra singh
//     */
//
//    @Test
//    public void validateQuikrXhomeScreenOnclickinGOButton()
//    {
//        Hompage hompage = new Hompage(driver);
//        QuikrXPage quikrXPage=new QuikrXPage(driver);
//        hompage.selectcategoriesFromHomePageCategoryMenu();
//        hompage.swipeToQuikrX();
//        quikrXPage.clickonQuikrXOnHomePage();
//        Assert.assertTrue(quikrXPage.validateOnClickingQuikrXUserreditectedToQuikrXScreen());
//
//    }
    /*
     The quikrX screen should display "Exchange" and "Certified" option and should be as per the mocks.
     swatantra singh
     */
    @Test(dataProvider = "BuyWithExchangeCities",dataProviderClass = Data.class)
    public void ValidateBuyWithExchangeAndCertifiedForGivenCities(String cities)
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.selectCity();
        headerPage.searchCity(cities);
        hompage.selectelementWithoutScroll(cities, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        Assert.assertTrue(quikrXPage.validateExchangeAndCertifiedOptionOnQuikrXpage());
    }
    /**
     * On clicking on "Exchange" user should be directed to The SnB screen where the New phones are listed
     * swatantra singh
     */
    @Test
    public void ValidateSNBonClickingExchange()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonExchangePhone();
        quikrXPage.hideOverlayLayout();
        String text1=quikrXPage.GetQuikrXheaderText();
        Assert.assertEquals(testData.get("ExchengeSNBValidation"), text1);
    }
//    /**
//     * The "Select a Brand new phone " banner should be displayed at the top of SnB listings
//     * swatantra singh
//     */
//    @Test
//    public void validateLabelOnQuikrXOnSelectingExchange()
//    {
//        Hompage hompage = new Hompage(driver);
//        QuikrXPage quikrXPage=new QuikrXPage(driver);
//        hompage.selectcategoriesFromHomePageCategoryMenu();
//        hompage.selectelementWithoutScroll(testData.get("Mobiles"), QuikrAppEnums.Home_Categories);
//        quikrXPage.openQuikrXfromMobilesAndTabs();
//        quikrXPage.clickonExchangePhone();
//        quikrXPage.hideOverlayLayout();
//        String labelText=quikrXPage.validateOnclickingExchangeANDSelectNewPhonesAppear();
//        Assert.assertEquals(testData.get("ExchangeLabelText"),labelText);
//    }

    /**
     * Clicking on "Buy" user should be directed to The SnB screen where the Certified used phones are listed
     * swatantra singh
     */
    @Test
    public void ValidateSNBonClickingCertifiedPhones()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        String certified=quikrXPage.GetQuikrXheaderText();
        Assert.assertEquals(testData.get("CertifiedSNBValidation"), certified);

    }

//    /**
//     * The "15 days replacement guarantee" banner should be displayed at the top of SnB listings
//     * swatantra singh
//     */
//    @Test
//    public void validateLabelOnQuikrXOnSelectingCertified()
//    {
//        Hompage hompage = new Hompage(driver);
//        QuikrXPage quikrXPage=new QuikrXPage(driver);
//        hompage.selectcategoriesFromHomePageCategoryMenu();
//        hompage.selectelementWithoutScroll(testData.get("Mobiles"), QuikrAppEnums.Home_Categories);
//        quikrXPage.openQuikrXfromMobilesAndTabs();
//        quikrXPage.clickonCertifiedPhone();
//        quikrXPage.hideOverlayLayout();
//        String labelText=quikrXPage.validateOnclickingExchangeANDSelectNewPhonesAppear();
//        Assert.assertEquals(testData.get("certifiedLable"),labelText);
//    }

    /**
     * swatantra singh
     * Users should be able to switch between "Exchange/certified" options from the spinner menu in SnB screens of both Exchange and Certified screens(Android)
     */

    @Test
    public void validateChangeOfCategoryFromSpinner()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.ClickOnSpinnerToSwitchBetweenPages();
        quikrXPage.selectExchangePhonesFromSpinner();
        String text1=quikrXPage.GetQuikrXheaderText();
        Assert.assertEquals(testData.get("ExchengeSNBValidation"), text1);
        quikrXPage.ClickOnSpinnerToSwitchBetweenPages();
        quikrXPage.selectBuyCertifiedPhonesFromSpinner();
        String certified=quikrXPage.GetQuikrXheaderText();
        Assert.assertEquals(testData.get("CertifiedSNBValidation"), certified);

    }
    /**
     * Cart icon should be besides the spinner menu (Android)
     * swatantra singh
     */
    @Test
    public void validateCartIconPresenceOnSNB()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        Assert.assertTrue(quikrXPage.validateCartIconIsPresentOnSnb());
    }

    @Test
    /**
     * 851 : The sort option should be available at the top before the listing(Android) in SnB
     *  818:The "Sort" should have the following options(Android)
     1)popularity
     2)Price: High to low
     3)Price:Low to high
     swatantra
     */
    public void validateSortIconAndOptions()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        Assert.assertTrue(quikrXPage.validateSortAndSortOptions());
    }

    @Test
    /**
     * validate filter icon And Filter options
     */
    public void validateFilterAndFilterOPtions()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        Assert.assertTrue(quikrXPage.validateFilterAndFilterOptions());
    }

    /**
     *Test link_ANDROID_QuikrX: 828 ,830,831,834
     *
     */
    @Test
    public void validateVapDetailsOnExchange()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonExchangePhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        quikrXPage.sendPinCode(testData.get("pin"));
        Assert.assertTrue(quikrXPage.validateVAPdetailsOnExchange());
        quikrXPage.scroll(testData.get("seller"));
        Assert.assertTrue(quikrXPage.ValidateExchangePhoneBrandOnVAP());
    }

    /**
     *TestLink _ANDROID_QuikrX :835,836,838,840
     */
    @Test
    public void validateVapDetailsOnCertifiedPhones()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);;
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        quikrXPage.sendPinCode(testData.get("pin"));
        Assert.assertTrue(quikrXPage.validateVAPdetailsOnCertifiedPhones());
        quikrXPage.scroll(testData.get("specification"));
        Assert.assertTrue(quikrXPage.ValidateCertifiedPhoneDetailsFieldVAP());
    }

    /**
     * 846:When user clicks on the Continue button,users should be directed to the Payment screen
     */
    @Test
    public void validateRedirectionToPaymentScreen()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        quikrXPage.loginEmail(testData.get("email"));
//            quikrXPage.clickOnIhaveQuikrAccount();
//            quikrXPage.loginPassword(testData.get("password"));
        quikrXPage.clickonContinueOnLogin();
        quikrXPage.provideDeliveryName(testData.get("name"));
        quikrXPage.provideDeliveryAddress(testData.get("address"));
        quikrXPage.provideDeliverycontact(testData.get("number"));
        quikrXPage.ClickonContinueToPayment();
        Assert.assertTrue(quikrXPage.paymentPage());

    }

    /**
     *The Log in page has following options:
     1."Email id" text field
     2. "Password" text field
     3. Options to Continue as Guest and for User with a quikr account
     */
    @Test
    public void validateLoginScreen()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        Assert.assertTrue(quikrXPage.validateLoginScreen());
    }


    /**
     *The Address page should have following options:
     1. It should have options to enter Name, Phone no, Pincode and Address
     */
    @Test
    public void validateDeliveryScreen()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        quikrXPage.loginEmail(testData.get("email"));
//        quikrXPage.navigateBack();
//            quikrXPage.clickOnIhaveQuikrAccount();
//            quikrXPage.loginPassword(testData.get("password"))
// ;
        quikrXPage.clickonContinueOnLogin();
        Assert.assertTrue(quikrXPage.validateDeliveryScreen());
    }

    /**
     * user is able to plase order for certified phones as login and guest user
     *It should show order as "succesful" and have a button "Continue to Shopping".The UI should be as per mocks
     */
      @Test(dataProvider = "UserStatus",dataProviderClass = Data.class)
    public void validateOrderSuccessPage(String userStatus)
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        if (userStatus.equalsIgnoreCase("login"))
        {
            quikrXPage.login(testData.get("password"), testData.get("email"));
            quikrXPage.explicitWait(10);
            quikrXPage.navigateBack();
        }
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        if (!(userStatus.equalsIgnoreCase("login")))
        {
            quikrXPage.loginEmail(testData.get("email"));
            quikrXPage.clickonContinueOnLogin();
        }
        quikrXPage.provideDeliveryName(testData.get("name"));
        quikrXPage.provideDeliveryAddress(testData.get("address"));
        quikrXPage.provideDeliverycontact(testData.get("number"));
        quikrXPage.ClickonContinueToPayment();
        quikrXPage.clickOnPlaceOrder();
        Assert.assertTrue(quikrXPage.userIsRedirectedToPaymentPage(), "On clicking Place Order User is not Redirected to Payment PAge");

    }

    //@Test
    public void UnboxedAds()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.scroll("UNBOXED");
        Assert.assertTrue(quikrXPage.validateUnboxedPhones());
        quikrXPage.openUnboxedDeviceAd(0);
        String unboxedText = quikrXPage.VapAdTitle();
        Assert.assertTrue(unboxedText.contains("Unboxed"));
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        quikrXPage.waitForLoginPage();
        quikrXPage.clickOnCart();
        String cartAdTitle = quikrXPage.UNBoxedAdTitleOncart(0);
        Assert.assertTrue(unboxedText.contains(cartAdTitle));

    }


    //
    @Test
    public void validateProtectMySmartphoneCheckbox()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        quikrXPage.scroll(testData.get("protectSmartphone"));
        //  Assert.assertTrue(quikrXPage.validateProtectMySmartphoneCheckbox(), "Protect My Smartphone Checkbox not present");
    }



    @Test
    public void validateCheckedProtectMySmartphoneCheckbox() throws Exception
    {Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        int productPrice = quikrXPage.getSmartphonePriceOnVap();
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.scroll(testData.get("protectSmartphone"));
        if(quikrXPage.checkProtectMySmartphoneCheckbox())
        {
            int warrantyPrice = quikrXPage.getWarrantyPriceOnVap();
            int vapPrice = quikrXPage.getTotalPriceOnVap();
            quikrXPage.clickOnBuyNow();
            quikrXPage.clickOnCartIcon();
            int totalPrice = quikrXPage.getTotalPriceOnCart();
            Assert.assertTrue(quikrXPage.isProductPresentInCart(), "Product not present in cart");
            Assert.assertTrue(quikrXPage.isWarrantyPresentInCart(), "Warranty not present in cart");
            Assert.assertEquals(productPrice + warrantyPrice, vapPrice, "Price mismatch in vap page");
            Assert.assertEquals(productPrice + warrantyPrice, totalPrice, "Price mismatch in cart page");
        }
    }

    @Test
    public void validateUncheckedProtectMySmartphoneCheckbox() throws Exception
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        int productPrice = quikrXPage.getSmartphonePriceOnVap();
        // Assert.assertFalse(quikrXPage.isTotalPriceOnVapVisible(), "Price mismatch in vap page");
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        quikrXPage.clickOnCartIcon();
        int totalPrice = quikrXPage.getTotalPriceOnCart();
        Assert.assertTrue(quikrXPage.isProductPresentInCart(), "Product not present in cart");
//        Assert.assertFalse(quikrXPage.isWarrantyPresentInCart(), "Warranty present in cart");
        Assert.assertEquals(productPrice, totalPrice, "Price mismatch in cart page");
    }

    @Test
    public void validateOrderSumaryPAge()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(1);
        String unboxedText=quikrXPage.VapAdTitle();
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        quikrXPage.loginEmail(testData.get("email"));
        quikrXPage.clickonContinueOnLogin();
        quikrXPage.provideDeliveryName(testData.get("name"));
        quikrXPage.provideDeliveryAddress(testData.get("address"));
        quikrXPage.provideDeliverycontact(testData.get("number"));
        quikrXPage.ClickonContinueToPayment();
        String adTitleAtOrderPage=quikrXPage.adTitleAtOrderPage();
        System.out.println(adTitleAtOrderPage + unboxedText);
        Assert.assertTrue(unboxedText.equalsIgnoreCase(adTitleAtOrderPage));
    }
    /**
     * validate banner msg buy without exchange on quikr x home screen
     */
    @Test
    public void validateBannerMsgBuyWithouExchange()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        Assert.assertTrue(quikrXPage.validateBuyWithoutExchangeInBannerMsgONMobileAndTabletsScreen());
    }
    /**
     * validate seller info is displayed on snb Ads displayed
     */

    @Test
    public void validateSellerInfoDisplayedOnSnb()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"),QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        System.out.println(quikrXPage.validateSellerInfoOnSnb());
        Assert.assertTrue( quikrXPage.validateSellerInfoOnSnb());
    }
    /**
     * validate BUy without exchange appears on QuikrX CHP
     */

    @Test
    public void validateBuyWithoutExchangeOption()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        Assert.assertTrue(quikrXPage.validateBuyWithoutExchangeOnQuikrXCHP());
    }
    /**
     * validate adding buy without Exchange to cart
     */
    @Test
    public void validateAddingBuyWithoutExchangeToCart()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickOnBuyWithoutExchange();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(0);
        String AdTitle = quikrXPage.VapAdTitle();
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        quikrXPage.waitForLoginPage();
        quikrXPage.clickOnCart();
        String cartAdTitle = quikrXPage.UNBoxedAdTitleOncart(0);
        Assert.assertTrue(AdTitle.contains(cartAdTitle));


    }
    /**
     * Able to add Buy With Exchange Product to Cart
     */
    //@Test
    public void userIsAbleToAddBuyWithgExchangeToCart()
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonExchangePhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(0);
        String AdTitle = quikrXPage.VapAdTitle();
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.selectBrand();
        quikrXPage.selectModel();
        quikrXPage.clickOnBuyNow();
        quikrXPage.waitForLoginPage();
        quikrXPage.clickOnCart();
        String cartAdTitle = quikrXPage.UNBoxedAdTitleOncart(0);
        Assert.assertTrue(AdTitle.contains(cartAdTitle));

    }
    /**
     * Able to Place an order as guest user for Buy Without Exchange Product
     */
    @Test(dataProvider = "UserStatus",dataProviderClass = Data.class)
    public void userIsAbleToPlaceOrder(String userStatus)
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        if (userStatus.equalsIgnoreCase("login"))
        {
            quikrXPage.login(testData.get("password"), testData.get("email"));
            quikrXPage.explicitWait(10);
            quikrXPage.navigateBack();
        }
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickOnBuyWithoutExchange();
        quikrXPage.hideOverlayLayout();
        quikrXPage.OpenAdFromSNb(0);
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.clickOnBuyNow();
        if (!(userStatus.equalsIgnoreCase("login")))
        {
            quikrXPage.loginEmail(testData.get("email"));
            quikrXPage.clickonContinueOnLogin();
        }
        quikrXPage.provideDeliveryName(testData.get("name"));
        quikrXPage.provideDeliveryAddress(testData.get("address"));
        quikrXPage.provideDeliverycontact(testData.get("number"));
        quikrXPage.ClickonContinueToPayment();
        quikrXPage.clickOnPlaceOrder();
        Assert.assertTrue(quikrXPage.userIsRedirectedToPaymentPage(), "On clicking Place Order User is not Redirected to Payment PAge");

    }
    /**
     * Verify Placing an order  as a logged in user for Certified city against single seller Certified Product and Buy Without Exchange product
     */
    @Test(dataProvider = "UserStatus",dataProviderClass = Data.class)
    public void BuyWithoutExchangeAgainstMultiSeller(String userStatus)throws Exception
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        if (userStatus.equalsIgnoreCase("login"))
        {
            quikrXPage.login(testData.get("password"), testData.get("email"));
            quikrXPage.explicitWait(10);
            quikrXPage.navigateBack();
        }
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickOnBuyWithoutExchange();
        quikrXPage.hideOverlayLayout();
        quikrXPage.openAdwithMultiSellerFromSnb();
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.selectSellerBuyWithoutExchange();
        quikrXPage.clickOnBuyButtonOnSellerPAge();
        if (!(userStatus.equalsIgnoreCase("login")))
        {
            quikrXPage.loginEmail(testData.get("email"));
            quikrXPage.clickonContinueOnLogin();
        }
        quikrXPage.provideDeliveryName(testData.get("name"));
        quikrXPage.provideDeliveryAddress(testData.get("address"));
        quikrXPage.provideDeliverycontact(testData.get("number"));
        quikrXPage.ClickonContinueToPayment();
        quikrXPage.clickOnPlaceOrder();
        Assert.assertTrue(quikrXPage.userIsRedirectedToPaymentPage(), "On clicking Place Order User is not Redirected to Payment PAge upon clicking Buy button from seller Page");
    }

    /**
     * Verify Placing an order as a guest user for Certified city against multi seller Certified Product and Buy Without Exchange product
     * @param userStatus
     * @throws Exception
     */
    @Test(dataProvider = "UserStatus",dataProviderClass = Data.class)
    public void BuyCertifiedPhonesAgainstMultiSeller(String userStatus)throws  Exception
    {
        Hompage hompage = new Hompage(driver);
        QuikrXPage quikrXPage=new QuikrXPage(driver);
        if (userStatus.equalsIgnoreCase("login"))
        {
            quikrXPage.login(testData.get("password"), testData.get("email"));
            quikrXPage.explicitWait(10);
            quikrXPage.navigateBack();
        }
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        quikrXPage.openQuikrXfromMobilesAndTabs();
        quikrXPage.clickonCertifiedPhone();
        quikrXPage.hideOverlayLayout();
        quikrXPage.openAdwithMultiSellerFromSnb();
        quikrXPage.sendPinCode(testData.get("pin"));
        quikrXPage.selectSellerForCertifiedPhones();
        quikrXPage.clickOnBuyButtonOnSellerPAge();
        if (!(userStatus.equalsIgnoreCase("login"))) {
            quikrXPage.loginEmail(testData.get("email"));
            quikrXPage.clickonContinueOnLogin();
        }
        quikrXPage.provideDeliveryName(testData.get("name"));
        quikrXPage.provideDeliveryAddress(testData.get("address"));
        quikrXPage.provideDeliverycontact(testData.get("number"));
        quikrXPage.ClickonContinueToPayment();
        quikrXPage.clickOnPlaceOrder();
        Assert.assertTrue(quikrXPage.userIsRedirectedToPaymentPage(), "On clicking Place Order User is not Redirected to Payment PAge");

    }


}
