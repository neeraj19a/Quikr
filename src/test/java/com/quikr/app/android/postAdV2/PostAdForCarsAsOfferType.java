package com.quikr.app.android.postAdV2;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra on 22/2/16.
 */
public class PostAdForCarsAsOfferType extends AppAndroidTestBase {
    private HashMap<String, String> testData=getTestData(getProperties().get("ANDROID_POSTADV2"));

    //  @Test
    public void Test()
//    {012
    {
        PapPage papPage=new PapPage(driver);
        PostAdV2Page postAdV2Page=new PostAdV2Page (driver);
        Hompage hompage=new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        Integer[]cord= postAdV2Page.getCordinates();
        int flag=postAdV2Page.checkHomePAgeVarient();
        hompage.clickonPostAd();
        System.out.println("flag =" + flag);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectelementWithoutScroll(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        papPage.verticalSwipeWithCordinates(cord[2], cord[1] - 130);
        postAdV2Page.selectelementWithoutScroll(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.sendInPutTextWithScroll(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("carsPrice"));
        postAdV2Page.hideKeyBoardAfterEnteringPriceForCars();
        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        if (flag==1)
            papPage.verticalSwipeWithCordinates(cord[2], cord[1] - 110);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        if (flag==0)
            papPage.verticalSwipeWithCordinates(cord[2], cord[1] - 110);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectVarient"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectYear"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Carsyear"), QuikrAppEnums.CATEGORY_LOCATION);
        if (flag==1)
            papPage.verticalSwipeWithCordinates(cord[2], cord[1] - 110);
        postAdV2Page.sendTextByKeybord(testData.get("enterDistanceTravelled"), QuikrAppEnums.PostAD_InputText, "2000");
        postAdV2Page.hideKeyBoardAfterEnteringDistance();
        if (flag==1)
            papPage.verticalSwipeWithCordinates(cord[2], cord[1] - 110);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectColor"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectFuelType"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.PostAd_RadioButton);
        if (flag==1)
            papPage.verticalSwipeWithCordinates(cord[2], cord[1] - 110);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("AdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("desc"));
        postAdV2Page.navigateBack();
        if (flag==0)
            papPage.verticalSwipeWithCordinates(cord[2], cord[1] - 110);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.verticalSwipeWithCordinates(cord[1], cord[0]);
        postAdV2Page.sendInPutText(testData.get("enterName"), QuikrAppEnums.PostAD_InputText, testData.get("name"));
        postAdV2Page.postYourAd();
        postAdV2Page.skipPostAdCarousalCard();
        Assert.assertTrue(postAdV2Page.validatePostAd());


    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To   Post Ad  For cars Category")
    @Test
    public void PostAdForCarsCategory()

    {

        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectElements(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectVarient"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectYear"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Carsyear"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectFuelType"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.scroll("Enter Ad title");
        postAdV2Page.selectelementWithoutScroll(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.enterDistance("200000");
        postAdV2Page.enterprice("2000");
        postAdV2Page.navigateBack();
        postAdV2Page.enterDescription(testData.get("desc"));
        postAdV2Page.enterTitle(testData.get("AdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.scroll(testData.get("SelectLocality"));
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.verticalSwipeWithCordinates(cord[0]+100, cord[1]);
        papPage.verticalSwipeWithCordinates(cord[0] + 70, cord[1]);
        postAdV2Page.enterNumber(mobileNumber());
        postAdV2Page.enterEmail(authPageMobileLogin.EmailId());
        postAdV2Page.enterName();
        postAdV2Page.postYourAd();
        postAdV2Page.skipPostAdCarousalCard();
        Assert.assertTrue(postAdV2Page.validatePostAd());



    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To   Post Ad as StartFresh For cars Category")
    //@Test
    public void PostAdAsStartFresh()
    {

        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.navigateBack();
        postAdV2Page.clickOnCanclePostAd();
        hompage.clickonPostAd();
        postAdV2Page.postAdAsStartFresh();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.sendInPutTextWithScroll(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("carsPrice"));
        postAdV2Page.hideKeyBoardAfterEnteringPriceForCars();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectVarient"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectYear"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Carsyear"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectFuelType"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("enterDistanceTravelled"), QuikrAppEnums.PostAD_InputText, "2000");
        postAdV2Page.hideKeyBoardAfterEnteringDistance();
        postAdV2Page.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.sendInPutTextWithScroll(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("AdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("desc"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectelementWithoutScroll(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.selectElements(testData.get("selectColor"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.postYourAd();
        postAdV2Page.skipPostAdCarousalCard();
        Assert.assertTrue(postAdV2Page.validatePostAd());



    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad  as Continue For Cars Category")
  //   @Test
    public void PostAdAsContinue()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.navigateBack();
        postAdV2Page.clickOnCanclePostAd();
        hompage.clickonPostAd();
        postAdV2Page.postAdAsContine();
        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.sendInPutTextWithScroll(testData.get("enterPrice"), QuikrAppEnums.PostAD_InputText, testData.get("carsPrice"));
        postAdV2Page.hideKeyBoardAfterEnteringPriceForCars();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectelementWithoutScroll(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectVarient"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectYear"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Carsyear"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectFuelType"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        papPage.verticalSwipeWithCordinates(cord[0], cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("enterDistanceTravelled"), QuikrAppEnums.PostAD_InputText, "2000");
        postAdV2Page.hideKeyBoardAfterEnteringDistance();
        postAdV2Page.selectelementWithoutScroll(testData.get("owner"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.sendInPutTextWithScroll(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("AdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("desc"));
        postAdV2Page.navigateBack();
        postAdV2Page.handleHideKeyBord();
        postAdV2Page.selectelementWithoutScroll(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.selectElements(testData.get("selectColor"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("color"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("emailId"), QuikrAppEnums.PostAD_InputText, authPageMobileLogin.EmailId());
        postAdV2Page.navigateBack();
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, mobileNumber());
        postAdV2Page.navigateBack();
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.postYourAd();
        postAdV2Page.skipPostAdCarousalCard();
        Assert.assertTrue(postAdV2Page.validatePostAd());




    }
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Preview Posted Ad For cars Category")
    @Test
    public void previewPostAd()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectElements(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectVarient"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectYear"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Carsyear"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectFuelType"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.scroll("Enter Ad title");
        postAdV2Page.selectelementWithoutScroll(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.enterDistance("200000");
        postAdV2Page.enterprice("2000");
        postAdV2Page.navigateBack();
        postAdV2Page.enterDescription(testData.get("desc"));
        postAdV2Page.enterTitle(testData.get("AdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.scroll(testData.get("SelectLocality"));
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.verticalSwipeWithCordinates(cord[0] + 100, cord[1]);
        postAdV2Page.enterNumber(mobileNumber());
        postAdV2Page.enterEmail(authPageMobileLogin.EmailId());
        postAdV2Page.enterName();
        postAdV2Page.postYourAd();
        postAdV2Page.skipPostAdCarousalCard();
        //using soft assert for assertion in between tests
        SoftAssert s_assert = new SoftAssert();
        s_assert.assertTrue(postAdV2Page.validatePostAd());
        papPage.selectViewMyAdButton();
        snbPage.hideOverlayLayout();
        Assert.assertTrue(papPage.validatePreviewPostAd(), "can't able to see preview ad post");
        papPage.selectDoneButtonAtVap();
        Assert.assertTrue(papPage.validatePostAd(), "After preview post ad done the msg of congratulation can't reflected");
    }
    /**
     * validate eXchange Of cars after post ad
     */
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("validate eXchange Of cars after post ad")
    @Test
    public void validateExchangeCarsAfterPostAd()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        SnbPage snbPage=new SnbPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.selectElements(testData.get("selectBrand"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectModel"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("model"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectVarient"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("variant"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectYear"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("Carsyear"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.selectelementWithoutScroll(testData.get("selectFuelType"), QuikrAppEnums.PostAd_SelectFromDropDown);
        papPage.selectelementWithoutScroll(testData.get("fuel"), QuikrAppEnums.CATEGORY_LOCATION);
        postAdV2Page.scroll("Enter Ad title");
        postAdV2Page.selectelementWithoutScroll(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.enterDistance("200000");
        postAdV2Page.enterprice("2000");
        postAdV2Page.navigateBack();
        postAdV2Page.enterDescription(testData.get("desc"));
        postAdV2Page.enterTitle(testData.get("AdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.scroll(testData.get("SelectLocality"));
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.verticalSwipeWithCordinates(cord[0] + 100, cord[1]);
        postAdV2Page.enterNumber(mobileNumber());
        postAdV2Page.enterEmail(authPageMobileLogin.EmailId());
        postAdV2Page.enterName();
        postAdV2Page.postYourAd();
        postAdV2Page.skipPostAdCarousalCard();
        //using soft assert for assertion in between tests
        SoftAssert s_assert = new SoftAssert();
        postAdV2Page.moveToExchangeCarPage();
        papPage.clickOnExchangeCarsAfterPostAd();
        papPage.skipAds();
        papPage.clickOnusedcarsForExchangeAfterPostAd();
        papPage.selectExchangeBrand();
        hompage.selectelementWithoutScroll(testData.get("brand"), QuikrAppEnums.HOMEPAGE_CATEGORY);
        papPage.clickOnDoneBUttonforExchangeCArs();
        Assert.assertTrue(papPage.validateOnOnselectingUsedCarsUserIsRedirectedToUSedCarsPAge(), "on clicking Done button on Exchange page user is not redirected to exchange cars page");
//        papPage.clickonexploreButton();
//        snbPage.hideOverlayLayout();
//        String title=carsNewPage.snbAdTitle();
//        System.out.println(title);
//        Assert.assertTrue(title.contains(testData.get("brand")),"brand selected during Exchange is not reflected  on Snb");
    }


}
