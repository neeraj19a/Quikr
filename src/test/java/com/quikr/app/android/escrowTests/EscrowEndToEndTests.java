package com.quikr.app.android.escrowTests;

import com.quikr.api.escrow.EscrowApiBase;
import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.app.android.quikrDoorstep.QuikrDoorstepPage;
import com.quikr.app.android.vap.VapPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 7/6/16.
 */
public class EscrowEndToEndTests extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_ESCROW_TESTDATA_FILE"));
    String AdId=null;

    @Title("PostAd : Without Minimum Price")
    @Description("To check if an Ad can be posted WITHOUT entering Minimum price")
    @Test(groups = {"Prod"})
    public void sellerAcceptOfferEtoE()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        AlertPage alertPage=new AlertPage(driver);
        VapPage vapPage=new VapPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        EscrowApiBase escrowApiBase = new EscrowApiBase();
        QuikrDoorstepPage quikrDoorstepPage = new QuikrDoorstepPage(driver);

//        hompage.clickonPostAd();
//        papPage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
//        papPage.selectelementWithoutScroll(testData.get("mobilesSubCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
//        postAdV2Page.waitForPapToLoad();
//        Integer[] cord = postAdV2Page.getCordinates();
//        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
//        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
//        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("Mobilebrand"), QuikrAppEnums.CATEGORY_LOCATION);
//        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("Mobilesmodel"), QuikrAppEnums.CATEGORY_LOCATION);
//        postAdV2Page.scroll(testData.get("enterAdTitle"));
//        postAdV2Page.enterTile(testData.get("MobileAdTitle"));
//        postAdV2Page.navigateBack();
//        postAdV2Page.enterDescription(testData.get("mobiltAdDesc"));
//        postAdV2Page.navigateBack();
//        postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
//        postAdV2Page.navigateBack();
////        postAdV2Page.hideKeyBoardIfMinimumPriceIsNotVisible();
////        postAdV2Page.sendTextByKeybord(testData.get("minimumPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
////        postAdV2Page.navigateBack();
//        postAdV2Page.handleHideKeyBord();
//        postAdV2Page.selectelementWithoutScroll(testData.get("noOfSims"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("sim"), QuikrAppEnums.CATEGORY_LOCATION);
//        postAdV2Page.selectElements(testData.get("yearOfPurchase"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
////        postAdV2Page.selectElements(testData.get("invoice"), QuikrAppEnums.PostAd_SelectFromDropDown);
////        postAdV2Page.selectelementWithoutScroll(testData.get("invoiceStatus"), QuikrAppEnums.CATEGORY_LOCATION);
////        postAdV2Page.selectElements(testData.get("defects"), QuikrAppEnums.PostAd_SelectFromDropDown);
////        alertPage.selectelementWithoutScroll(testData.get("defectsinMobile"), QuikrAppEnums.CATEGORY_ALERT);
////        alertPage.selectCheckBox();
//        postAdV2Page.scroll("Address *");
//        postAdV2Page.sendTextByKeybord(testData.get("address"), QuikrAppEnums.PostAD_InputText, testData.get("userAddress"));
//        postAdV2Page.navigateBack();
//        postAdV2Page.handleHideKeyBord();
//        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
//        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
//        postAdV2Page.sendTextByKeybord(testData.get("pin"), QuikrAppEnums.PostAD_InputText, testData.get("userPin"));
//        postAdV2Page.navigateBack();
//        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
//        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
//        postAdV2Page.navigateBack();
//        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
//        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
//        postAdV2Page.postYourAd();
//        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
//        postAdV2Page.selectViewMyAd();
////        String AdId=vapPage.getAdId();
//        System.out  .println(AdId);


        String AdId = "271541756"; //Ad posted from oneplus2 @ shifterjob @ 9832789237
        String OfferId = escrowApiBase.createOfferProd(AdId);
        System.out.println("Offer Id : \n"+OfferId);
//        headerPage.gotoMyAccountSideMenuDrawer();
        headerPage.gotoNotificationsSideMenuDrawer();
//        authPageMobileLogin.clickOnLogin();
//        authPageMobileLogin.enterLoginEmailNumber(testData.get("sellerEmail"));
//        authPageMobileLogin.enterLoginpassword(testData.get("sellerPassword"));
//        authPageMobileLogin.submitLoginCredentials();
//        headerPage.clickOnQuikrNext();
//        headerPage.tapMyOffers();
//        quikrDoorstepPage.selectViewOffers();
        quikrDoorstepPage.acceptOfferNotifications();
        Assert.assertTrue(escrowApiBase.getOfferStatusHistoryProd(OfferId).contentEquals("7"),"Offer Accepted");

    }

    @Title("PostAd : Without Minimum Price")
    @Description("To check if an Ad can be posted WITHOUT entering Minimum price")
    @Test(groups = {"Prod"})
    public void buyerMakeOfferEtoE() throws InterruptedException {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AlertPage alertPage=new AlertPage(driver);
        VapPage vapPage=new VapPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        HeaderPage headerPage = new HeaderPage(driver);

        String AdId="270067473"; //Ad posted from motoG @ rawadosa31 @ 8858585858
        headerPage.selectHeaderSearchIcon();
        headerPage.searchTextFromHomePage(AdId);
        Thread.sleep(5000);


//        hompage.clickonPostAd();
//        papPage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
//        papPage.selectelementWithoutScroll(testData.get("mobilesSubCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
//        postAdV2Page.waitForPapToLoad();
//        Integer[] cord = postAdV2Page.getCordinates();
//        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
//        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
//        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("Mobilebrand"), QuikrAppEnums.CATEGORY_LOCATION);
//        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("Mobilesmodel"), QuikrAppEnums.CATEGORY_LOCATION);
//        postAdV2Page.scroll(testData.get("enterAdTitle"));
//        postAdV2Page.enterTile(testData.get("MobileAdTitle"));
//        postAdV2Page.navigateBack();
//        postAdV2Page.enterDescription(testData.get("mobiltAdDesc"));
//        postAdV2Page.navigateBack();
//        postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
//        postAdV2Page.navigateBack();
////        postAdV2Page.hideKeyBoardIfMinimumPriceIsNotVisible();
////        postAdV2Page.sendTextByKeybord(testData.get("minimumPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
////        postAdV2Page.navigateBack();
//        postAdV2Page.handleHideKeyBord();
//        postAdV2Page.selectelementWithoutScroll(testData.get("noOfSims"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("sim"), QuikrAppEnums.CATEGORY_LOCATION);
//        postAdV2Page.selectElements(testData.get("yearOfPurchase"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
////        postAdV2Page.selectElements(testData.get("invoice"), QuikrAppEnums.PostAd_SelectFromDropDown);
////        postAdV2Page.selectelementWithoutScroll(testData.get("invoiceStatus"), QuikrAppEnums.CATEGORY_LOCATION);
////        postAdV2Page.selectElements(testData.get("defects"), QuikrAppEnums.PostAd_SelectFromDropDown);
////        alertPage.selectelementWithoutScroll(testData.get("defectsinMobile"), QuikrAppEnums.CATEGORY_ALERT);
////        alertPage.selectCheckBox();
//        postAdV2Page.scroll("Address *");
//        postAdV2Page.sendTextByKeybord(testData.get("address"), QuikrAppEnums.PostAD_InputText, testData.get("userAddress"));
//        postAdV2Page.navigateBack();
//        postAdV2Page.handleHideKeyBord();
//        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
//        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
//        postAdV2Page.sendTextByKeybord(testData.get("pin"), QuikrAppEnums.PostAD_InputText, testData.get("userPin"));
//        postAdV2Page.navigateBack();
//        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
//        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
//        postAdV2Page.navigateBack();
//        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
//        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
//        postAdV2Page.postYourAd();
//        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");
//        postAdV2Page.selectViewMyAd();
////        String AdId=vapPage.getAdId();
//        System.out.println(AdId);

    }

}
