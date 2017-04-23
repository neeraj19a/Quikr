package com.quikr.app.android.postAdV2;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra on 26/2/16.
 */
public class HoriZontalPostAdAsWantTypes  extends AppAndroidTestBase{
    private HashMap<String, String> testData=getTestData(getProperties().get("ANDROID_POSTADV2"));

    @Features("Post AD AS Want Type")
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
        postAdV2Page.swipeToPostAd(cord[0], cord[1]);
        postAdV2Page.selectelementWithoutScroll(testData.get("wantType"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("Mobilebrand"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("Mobilesmodel"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("MobileAdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("mobiltAdDesc"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        papPage.verticalSwipeWithCordinates(cord[0] + 50, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.hideKeyBoardIfMinimumPriceIsNotVisible();
        postAdV2Page.scroll(testData.get("address"));
        postAdV2Page.sendTextByKeybord(testData.get("address"), QuikrAppEnums.PostAD_InputText, testData.get("userAddress"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("pin"), QuikrAppEnums.PostAD_InputText, testData.get("userPin"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");

    }
    @Features("Post AD AS Want Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For Electronics Category")
    @Test
    public void postAdForElectronics()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("ElectronicsCAt"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("electronicsSubCAt"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.swipeToPostAd(cord[0] + 20, cord[1]);
        postAdV2Page.selectelementWithoutScroll(testData.get("wantType"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.selectelementWithoutScroll(testData.get("productType"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Desktop"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("electroniceBrandWantType"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
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
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectelementWithoutScroll(testData.get("screen"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("screenSize"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.selectelementWithoutScroll(testData.get("RAm"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("ramSize"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.scroll(testData.get("address"));
        postAdV2Page.sendTextByKeybord(testData.get("address"), QuikrAppEnums.PostAD_InputText, testData.get("userAddress"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("pin"), QuikrAppEnums.PostAD_InputText, testData.get("userPin"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAdEscrow(), "user is not able to post Ad");

    }
    @Features("Post AD AS Want Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For REAL ESTATE Category Without number")
    //@Test()
    public void postAdforRealEState() {


        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AlertPage alertPage=new AlertPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("realEstatCat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("realEstateSubcat"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectelementWithoutScroll(testData.get("relEstateWantTypeAd"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.explicitWait(5);
        postAdV2Page.selectElements(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.sendTextByKeybord(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("mobilesPrice"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectelementWithoutScroll(testData.get("noOfRooms"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("realEstaterooms"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.selectelementWithoutScroll(testData.get("area"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("wantTypeArea"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.selectElements(testData.get("furnished"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("realEstatefurnished"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("realEstateAdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] + 20, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("realEstatedesc"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.swipeToPostAd(cord[0] + 150, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.swipeToPostAd(cord[0], cord[1]);
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for Real Estate without number" );

    }
    @Features("Post AD AS Want Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For Services")
    @Test()
    public void postAdforServices() {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AlertPage alertPage=new AlertPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        postAdV2Page.waitForPapToLoad();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectelementWithoutScroll(testData.get("servicesWantTypeAd"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.swipeToPostAd(cord[0] + 50, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadDescription"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for Real Estate");
    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For Jobs Category")
    @Test()
    public void postAdforJobs()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        AlertPage alertPage=new AlertPage(driver);
        hompage.clickonPostAd();
        postAdV2Page.waitForPapToLoad();
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("JOBS"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectelementWithoutScroll(testData.get("jobsWantType"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.selectElements(testData.get("role"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("jobsrole"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("education"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("jobseducationOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("experience"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("jobsexperienceOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("curentSalary"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("jobscurrentSalaryOptionName"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("relocate"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("relocationOption"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("jobsadTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] + 20, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("jobsadDescription"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        alertPage.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.CATEGORY_ALERT);
        alertPage.selectCheckBox();
        postAdV2Page.swipeToPostAd(cord[0] + 120, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.postYourAd();
        Assert.assertTrue(papPage.validatePostAd(), "user is not able to post ad for jobs ");
    }


}
