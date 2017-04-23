package com.quikr.app.android.postAdV2;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra on 19/2/16.
 */
public class HoriZontalPostAdAsOfferTypes extends AppAndroidTestBase {

    private HashMap<String, String>testData=getTestData(getProperties().get("ANDROID_POSTADV2"));

    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For Mobiles & Tablets Category")
    @Test
    public void postAdForMobiles()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AlertPage alertPage=new AlertPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("mobilesSubCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
        postAdV2Page.enterDescription(testData.get("mobiltAdDesc"));
        postAdV2Page.enterTitle(testData.get("MobileAdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Mobilebrand"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Mobilesmodel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.verticalSwipeWithCordinates(cord[0] + 50, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.hideKeyBoardIfMinimumPriceIsNotVisible();
        postAdV2Page.sendTextByKeybord(testData.get("minimumPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectelementWithoutScroll(testData.get("noOfSims"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.waitForPapToLoad();
        WebElement postAdCategoryPage = postAdV2Page.getPostAdScrollView();
        postAdV2Page.loopScrollToElementByName(postAdCategoryPage,"mobilestabletscategory", true,1000).click();
        WebElement postAdSubCategoryPage = postAdV2Page.getPostAdCategoryScrollView();
        postAdV2Page.loopScrollToElementByName(postAdSubCategoryPage,"mobilesubcategory", true,1000).click();
        //papPage.selectelementWithoutScroll(testData.get("mobiles"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        //papPage.selectelementWithoutScroll(testData.get("mobilesSubCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        //Integer[] cord = postAdV2Page.getCordinates();
        WebElement postAdScrollView = postAdV2Page.getPostAdScrollView();
        postAdV2Page.loopScrollToElementByName(postAdScrollView,testData.get("condition"),true,1000).click();
        //postAdV2Page.selectelementWithoutScroll(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "brand", true, 1000).click();
        //postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Mobilebrand"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Mobilesmodel"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "title", true, 1000).sendKeys(testData.get("MobileAdTitle"));
        //postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("MobileAdTitle"));
        postAdV2Page.hideKeyboard();
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "description", true, 1000).sendKeys(testData.get("mobiltAdDesc"));
        //postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("mobiltAdDesc"));
        postAdV2Page.hideKeyboard();
        //postAdV2Page.handleHideKeyBord();
        //papPage.verticalSwipeWithCordinates(cord[0] + 50, cord[1]);
        postAdV2Page.loopScrollToElementByName(postAdScrollView,"enterPriceXpath",true,1000).sendKeys(testData.get("mobilesPrice"));
        //postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        //postAdV2Page.navigateBack();
        postAdV2Page.hideKeyboard();
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "minimumPrice", true, 1000).sendKeys(testData.get("mobilesPrice"));
        //postAdV2Page.sendTextByKeybord(testData.get("minimumPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.hideKeyboard();
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "noOfSims", true, 1000).click();
        //postAdV2Page.selectelementWithoutScroll(testData.get("noOfSims"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("sim"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "yearOfPurchase", true, 1000).click();
        //postAdV2Page.selectelementWithoutScroll(testData.get("yearOfPurchase"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("year"), QuikrAppEnums.CATEGORY_LOCATION);
//        postAdV2Page.selectElements(testData.get("invoice"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("invoiceStatus"), QuikrAppEnums.CATEGORY_LOCATION);
//        postAdV2Page.selectElements(testData.get("defects"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        alertPage.selectelementWithoutScroll(testData.get("defectsinMobile"), QuikrAppEnums.CATEGORY_ALERT);
//        alertPage.selectCheckBox();
        postAdV2Page.scroll("Address *");
        postAdV2Page.sendTextByKeybord(testData.get("address"), QuikrAppEnums.PostAD_InputText, testData.get("userAddress"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectElements(testData.get("yearOfPurchase"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "invoice", true, 1000).click();
        //postAdV2Page.selectelementWithoutScroll(testData.get("invoice"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("invoiceStatus"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "defects", true, 1000).click();
        //postAdV2Page.selectelementWithoutScroll(testData.get("defects"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("defectsinMobile"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "address", true, 1000).sendKeys(testData.get("userAddress"));
        //postAdV2Page.sendTextByKeybord(testData.get("address"), QuikrAppEnums.PostAD_InputText, testData.get("userAddress"));
        //postAdV2Page.navigateBack();
        postAdV2Page.hideKeyboard();
        postAdV2Page.loopScrollToElementByName(postAdScrollView,"locality",true,1000).click();
        //postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        //papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
        postAdV2Page.loopScrollToElementByName(postAdScrollView,"pincode",true,1000).sendKeys(testData.get("userPin"));
        //postAdV2Page.sendTextByKeybord(testData.get("pin"), QuikrAppEnums.PostAD_InputText, testData.get("userPin"));
        postAdV2Page.hideKeyboard();
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "email", true, 1000).sendKeys(authPageMobileLogin.EmailId());
        //postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.hideKeyboard();
        postAdV2Page.loopScrollToElementByName(postAdScrollView, "mobile", true, 1000).sendKeys(mobileNumber());
        //postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.hideKeyboard();
        postAdV2Page.loopScrollToElementByName(postAdScrollView,"postYourAdButton",true,1000).click();
        //postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        //postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");


    }

    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For Electronics Category")
    @Test
    public void postAdForElectronics()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("ElectronicsCAt"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("electronicsSubCAt"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.selectelementWithoutScroll(testData.get("productType"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Desktop"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("electronicsbrand"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("MobileAdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("mobiltAdDesc"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.swipeToPostAd(cord[0] + 40, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.sendTextByKeybord(testData.get("minimumPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectelementWithoutScroll(testData.get("screen"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("screenSize"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.swipeToPostAd(cord[0], cord[1]);
        postAdV2Page.selectelementWithoutScroll(testData.get("RAm"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("ramSize"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.scroll(testData.get("address"));
        postAdV2Page.sendTextByKeybord(testData.get("address"), QuikrAppEnums.PostAD_InputText, testData.get("userAddress"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.sendTextByKeybord(testData.get("pin"), QuikrAppEnums.PostAD_InputText, testData.get("userPin"));
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");


//
//        postAdV2Page.selectelementWithoutScroll(testData.get("Desktop"), QuikrAppEnums.PostAd_RadioButton);
//        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
//        postAdV2Page.selectelementWithoutScroll(testData.get("screen"), QuikrAppEnums.PostAd_SelectFromDropDown);
//        postAdV2Page.selectelementWithoutScroll(testData.get("screenSize"), QuikrAppEnums.CATEGORY_LOCATION);
//
//        postAdV2Page.sendInPutTextWithScroll(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
//        postAdV2Page.hideKeyBoardIfMinimumPriceIsNotVisible();
//
//        postAdV2Page.hideKeyBoardIfBrandIsNotVisible();
//        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
//
//
    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For Jobs Category")
    @Test(dataProvider ="jobsSubcat",dataProviderClass = Data.class ,groups = "horizontal")
    public void postAdforJobs(String jobsSubcat)
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(jobsSubcat, QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectElements(testData.get("role"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("jobsrole"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("education"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("jobseducationOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("experience"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("jobsexperienceOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("compensensation"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("jobscurrentSalaryOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("jobsadTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] + 20, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("jobsadDescription"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 120, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for jobs Subcat=" + jobsSubcat);
    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For REAL ESTATE Category Without number")
    //@Test()
    public void postAdforRealEStateWithoutNumber() {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.waitForPapToLoad();
        papPage.selectelementWithoutScroll(testData.get("realEstatCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("realEstateSubcat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectElements(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectelementWithoutScroll(testData.get("noOfRooms"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("realEstaterooms"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.sendTextByKeybord(testData.get("area"), QuikrAppEnums.PostAD_InputText, testData.get("realEstatearea"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectElements(testData.get("furnished"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("realEstatefurnished"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("realEstateAdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("realEstatedesc"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 150, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] , cord[1]);
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for Real Estate without number" );

    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For REAL ESTATE Category With number")
    @Test()
    public void postAdforRealEStateWithNumber() {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("realEstatCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("realEstateSubcat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectElements(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectelementWithoutScroll(testData.get("noOfRooms"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("realEstaterooms"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.sendTextByKeybord(testData.get("area"), QuikrAppEnums.PostAD_InputText, testData.get("realEstatearea"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectElements(testData.get("furnished"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("realEstatefurnished"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("realEstateAdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("realEstatedesc"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 150, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for Real Estate with number" );

    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For Services")
    @Test()
    public void postAdforServices() {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.swipeToPostAd(cord[0] + 50, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadDescription"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for Real Estate");
    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("Carousal screen should be displayed appropriately in Post Ad success screen only 1 card")
    @Description("Carousal screen should be displayed appropriately in Post Ad success screen when there is only 1 card (Post an Ad in categories other than Cars & Bikes) ")
    @Test()
    public void  verifyPostAdCarousalScreenForSingleCard() {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        AuthPageMobileLogin authPageMobileLogin = new AuthPageMobileLogin(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.swipeToPostAd(cord[0] + 50, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadDescription"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAdSuccessPremiumCard(),"on posting ad MAke premium card not displayed");
    }
}
