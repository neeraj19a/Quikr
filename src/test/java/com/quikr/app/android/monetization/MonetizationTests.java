package com.quikr.app.android.monetization;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra singh on 8/9/15.
 */
public class MonetizationTests extends AppAndroidTestBase {


    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_MONEY_TESTDATA_FILE"));

    /**
     * Through Post ad
     *Android  213: Make Your Ad Premium" page should be displayed after posting an ad and tap "continue" button from Post Ad success page
     Android  238: Select Mode of Payment" screen should be displayed after tapping "Proceed to  Payment" button
     Android  241: Verify options displayed in "Select Mode of Payment" screen
     Android: 243: Mobile Billing screen should be displayed after tapping "Mobile Billing" item from "Select Mode Of Payment" screen
     Android: 245: Verify value displayed in "Amount to be Paid" field in Mobile Billing screen
     Android: 245: Tapping "Pay Now" button in Mobile Billing screen should display "Confirm Mobile Number" screen
     Android  258: Tap "Credit/ Debit Card" item from "Choose a Payment mode" screen without signing in and verify
     Android  261: Verify value displayed in "Amount to be Paid" field in Credit/ Debit Card screen
     Android- 279:  "Net Banking" screen should be displayed tapping "Net Banking" item from "Choose Payment mode" screen
     Android- 281:  Verify value displayed in "Amount to be Paid" field in Net Banking screen
     Android  283:  Verify Banks displayed in "Select From Popular Banks" section
     Android  632:  PayTm option in Choose Payment Mode screen
     */
    @Stories("Monetization")
    @Test(groups = "horizontal")
    public void ValidateMAkeAdPremiumOptionAfterPostingAd()
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
        postAdV2Page.selectElements(testData.get("condition"), QuikrAppEnums.PostAd_RadioButton);
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
        postAdV2Page.scroll("Enter Ad title");
        postAdV2Page.selectelementWithoutScroll(testData.get("postAdAs"), QuikrAppEnums.PostAd_RadioButton);
        postAdV2Page.enterprice("2000");
        postAdV2Page.enterDistance("200000");
        postAdV2Page.navigateBack();
        postAdV2Page.enterDescription(testData.get("desc"));
        postAdV2Page.enterTitle(testData.get("AdTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.scroll(testData.get("SelectLocality"));
        postAdV2Page.selectelementWithoutScroll(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        papPage.verticalSwipeWithCordinates(cord[0] + 100, cord[1]);
        papPage.verticalSwipeWithCordinates(cord[0] + 70, cord[1]);
        postAdV2Page.enterNumber(mobileNumber());
        postAdV2Page.enterEmail(authPageMobileLogin.EmailId());
        postAdV2Page.enterName();
        postAdV2Page.postYourAd();
        postAdV2Page.skipPostAdCarousalCard();
        Assert.assertTrue(postAdV2Page.validatePostAd());
        int amount=postAdV2Page.priceDisplayedInMakePremiumPlan();
        papPage.clickOnContinueToMAkeAdPemium();
        //if plan value is more than 200 than user should land on Mobilebilling payment method

        if(amount<200)
        {
            papPage.clickOnPayNowButtonMobileBill();
            Assert.assertTrue(papPage.validateMobileBillPayment());
            papPage.navigateBack();
            papPage.selectPaymentMethodAsCardPAymney();
            Assert.assertTrue(papPage.validatecreditDebitBillPayScreen());
            System.out.println(amount);
            int amount1=Integer.parseInt(papPage.ValidatePAymentAmount());
            System.out.println(amount1);
            Assert.assertEquals(amount1, amount);
            //validate Card fields
            Assert.assertTrue(papPage.validateCardFields());
            //Android 279
            papPage.selectPaymentMethodAsNetBAnking();
            Assert.assertTrue(papPage.validateNetBankingBillPayScreen());
            int amount2=Integer.parseInt(papPage.ValidatePAymentAmount());
            Assert.assertEquals(amount2,amount);
            papPage.selectPaymentMethodPaytm();
            Assert.assertTrue(papPage.validatePaytmpaymentScreen());
        }
        //if plan value is more than 200 and less400 than user should land on Paytm payment method

        else if(amount>200&&amount<400)
        {
            Assert.assertTrue(papPage.validatePaytmpaymentScreen());
            papPage.selectPaymentMethodAsMobileBilling();
            papPage.clickOnPayNowButtonMobileBill();
            Assert.assertTrue(papPage.validateMobileBillPayment());
            papPage.navigateBack();
            papPage.selectPaymentMethodAsCardPAymney();
            Assert.assertTrue(papPage.validatecreditDebitBillPayScreen());
            System.out.println(amount);
            int amount1=Integer.parseInt(papPage.ValidatePAymentAmount());
            System.out.println(amount1);
            Assert.assertEquals(amount1, amount);
            //validate Card fields
            Assert.assertTrue(papPage.validateCardFields());
            //Android 279
            papPage.selectPaymentMethodAsNetBAnking();
            Assert.assertTrue(papPage.validateNetBankingBillPayScreen());
            int amount2=Integer.parseInt(papPage.ValidatePAymentAmount());
            Assert.assertEquals(amount2, amount);

        }
        //if plan value is more than 400 than user should land on credit card payment method
        else if(amount>400)
        {
            Assert.assertTrue(papPage.validatecreditDebitBillPayScreen());
            System.out.println(amount);
            int amount1=Integer.parseInt(papPage.ValidatePAymentAmount());
            System.out.println(amount1);
            Assert.assertEquals(amount1, amount);
            //Android 279
            papPage.selectPaymentMethodAsNetBAnking();
            Assert.assertTrue(papPage.validateNetBankingBillPayScreen());
            int amount2=Integer.parseInt(papPage.ValidatePAymentAmount());
            Assert.assertEquals(amount2,amount);
            papPage.selectPaymentMethodPaytm();
            Assert.assertTrue(papPage.validatePaytmpaymentScreen());
        }


    }
    /**Android 214
     * From my ads
     * "Make Your Ad Premium" screen should be displayed after tapping "Premium" button from From My Ads screen
     */
    @Stories("Monetization")

    @Test(groups = "horizontal")
    public void validateMakePremiumFromMyAds()
    {
        MenuPage menuePage=new MenuPage(driver);
        PapPage papPage=new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyAds"), QuikrAppEnums.CATEGORY_MENU);
        menuePage.MakePremiumFromMyAds();
        int amount=postAdV2Page.priceDisplayedInMakePremiumPlan();
        papPage.clickOnContinueToMAkeAdPemium();
        //if plan value is more than 200 than user should land on Mobilebilling payment method

        if(amount<200)
        {
            papPage.clickOnPayNowButtonMobileBill();
            Assert.assertTrue(papPage.validateMobileBillPayment());
            papPage.navigateBack();
            papPage.selectPaymentMethodAsCardPAymney();
            Assert.assertTrue(papPage.validatecreditDebitBillPayScreen());
            System.out.println(amount);
            int amount1=Integer.parseInt(papPage.ValidatePAymentAmount());
            System.out.println(amount1);
            Assert.assertEquals(amount1, amount);
            //validate Card fields
            Assert.assertTrue(papPage.validateCardFields());
            //Android 279
            papPage.selectPaymentMethodAsNetBAnking();
            Assert.assertTrue(papPage.validateNetBankingBillPayScreen());
            int amount2=Integer.parseInt(papPage.ValidatePAymentAmount());
            Assert.assertEquals(amount2,amount);
            papPage.selectPaymentMethodPaytm();
            Assert.assertTrue(papPage.validatePaytmpaymentScreen());
        }
        //if plan value is more than 200 and less400 than user should land on Paytm payment method

        else if(amount>200&&amount<400)
        {
            Assert.assertTrue(papPage.validatePaytmpaymentScreen());
            papPage.selectPaymentMethodAsMobileBilling();
            papPage.clickOnPayNowButtonMobileBill();
            Assert.assertTrue(papPage.validateMobileBillPayment());
            papPage.navigateBack();
            papPage.selectPaymentMethodAsCardPAymney();
            Assert.assertTrue(papPage.validatecreditDebitBillPayScreen());
            System.out.println(amount);
            int amount1=Integer.parseInt(papPage.ValidatePAymentAmount());
            System.out.println(amount1);
            Assert.assertEquals(amount1, amount);
            //validate Card fields
            Assert.assertTrue(papPage.validateCardFields());
            //Android 279
            papPage.selectPaymentMethodAsNetBAnking();
            Assert.assertTrue(papPage.validateNetBankingBillPayScreen());
            int amount2=Integer.parseInt(papPage.ValidatePAymentAmount());
            Assert.assertEquals(amount2, amount);

        }
        //if plan value is more than 400 than user should land on credit card payment method
        else if(amount>400)
        {
            Assert.assertTrue(papPage.validatecreditDebitBillPayScreen());
            System.out.println(amount);
            int amount1=Integer.parseInt(papPage.ValidatePAymentAmount());
            System.out.println(amount1);
            Assert.assertEquals(amount1, amount);
            //Android 279
            papPage.selectPaymentMethodAsNetBAnking();
            Assert.assertTrue(papPage.validateNetBankingBillPayScreen());
            int amount2=Integer.parseInt(papPage.ValidatePAymentAmount());
            Assert.assertEquals(amount2,amount);
            papPage.selectPaymentMethodPaytm();
            Assert.assertTrue(papPage.validatePaytmpaymentScreen());
        }

    }

}




