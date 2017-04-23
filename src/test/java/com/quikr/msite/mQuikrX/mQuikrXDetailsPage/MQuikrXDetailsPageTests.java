package com.quikr.msite.mQuikrX.mQuikrXDetailsPage;

import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mQuikrX.mQuikrXTestBase;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.quikrX.quikrXCartPage.QuikrxNewCartPage;
import com.quikr.website.quikrX.quikrXNewDetailsPage.QuikrXNewDetailsPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by francis.s@quikr on 22/3/16.
 */
public class MQuikrXDetailsPageTests extends mQuikrXTestBase {

    private final static Log LOGGER = LogFactory.getLog(MQuikrXDetailsPageTests.class.getName());
    private HashMap<String, String> testData = getTestData(getProperties().get("mQUIKRX_TESTDATA_FILE"));

    @BeforeMethod
    public void preSetUp() {

        MHomePage homePage = new MHomePage(driver);
        homePage.selectCity("Bangalore");
        waitForPageToLoad("bangalore");
        homePage.clickQuikrX();
        waitForPageToLoad("QuikrX");

    }

    @Title("Test for ui validation certified phones")
    @Description("Validate ui for details page for certified phone")
    @Test
    public void certifiedDetailsPageUiValidation(){

        QuikrXNewDetailsPage msiteDetailsPage = new QuikrXNewDetailsPage(driver);

        String url = getTestDataWithReplace(testData.get("newUiLink"),testData.get("newUiCity"));
        navigatethirdparty(url+"1503");
        waitForPageToLoad("1503");

        Assert.assertNotNull(msiteDetailsPage.getItemName(),"name filed empty");
        Assert.assertNotNull(msiteDetailsPage.priceAfterDisc(),"price field empty");
        msiteDetailsPage.enterPincode(testData.get("validPinCode"));
        msiteDetailsPage.clickPincodeCheck();
        Assert.assertEquals(msiteDetailsPage.getPincodeMessage(),testData.get("pincodeSuccess"));
        Assert.assertTrue(msiteDetailsPage.techSpecDiv(), "tech spec is not displayed");

    }

    @Title("Test for ui validation exchange phones")
    @Description("Validate ui for details page for exchange phone")
    @Test
    public void exchangeDetailsPageUiValidation(){

        QuikrXNewDetailsPage msiteDetailsPage = new QuikrXNewDetailsPage(driver);

        String url = getTestDataWithReplace(testData.get("newUiLink"),testData.get("newUiCity"));
        navigatethirdparty(url+"1218");
        waitForPageToLoad("1218");

        Assert.assertNotNull(msiteDetailsPage.getItemName(),"name filed empty");
        Assert.assertNotNull(msiteDetailsPage.priceAfterDisc(),"price field empty");
        msiteDetailsPage.selectBrand(testData.get("phoneBrandName"));;
        msiteDetailsPage.selectModel(testData.get("phoneModelName2"));
        msiteDetailsPage.enterPincode(testData.get("validPinCode"));
        msiteDetailsPage.clickPincodeCheck();
        Assert.assertEquals(msiteDetailsPage.getPincodeMessage(),testData.get("pincodeSuccess"));
        Assert.assertTrue(msiteDetailsPage.techSpecDiv(), "tech spec is not displayed");

    }

    @Title("Test for different pincode validation")
    @Description("Test for both accepted and non accepted pincodes")
    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class, groups = "QuikrXHighPrio")
    public void pinCodeTest(String category, String itemId){

        LOGGER.info("validating pincode on details page");
        QuikrXNewDetailsPage msiteDetailsPage = new QuikrXNewDetailsPage(driver);

        String url = getTestDataWithReplace(testData.get("newUiLink"),testData.get("newUiCity"));
        navigatethirdparty(url+itemId);
        waitForPageToLoad(itemId);
        msiteDetailsPage.enterPincode(testData.get("validPinCode"));
        msiteDetailsPage.clickPincodeCheck();
        Assert.assertEquals(msiteDetailsPage.getPincodeMessage(),testData.get("pincodeSuccess"));
        msiteDetailsPage.enterPincode(testData.get("pincodeNoDelivery"));
        msiteDetailsPage.clickPincodeCheck();
        Assert.assertEquals(msiteDetailsPage.getPincodeMessage(),testData.get("pincodeNA"));
        msiteDetailsPage.enterPincode(testData.get("invalidPinCode"));
        msiteDetailsPage.clickPincodeCheck();
        Assert.assertEquals(msiteDetailsPage.getPincodeMessage(),testData.get("invalidPincodeMsg"));
    }


    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = "QuikrXHighPrio")
    public void addToCartWithOutPincode(String category, String itemId) {

        LOGGER.info("validating primary seller add to cart before pincode on details page");
        QuikrXNewDetailsPage msiteDetailsPage = new QuikrXNewDetailsPage(driver);

        String url = getTestDataWithReplace(testData.get("newUiLink"),testData.get("newUiCity"));
        navigatethirdparty(url+itemId);
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            msiteDetailsPage.clickIDontHavePhone();
            msiteDetailsPage.exchangeAddTocartPrim();
        } else
            msiteDetailsPage.certiAddPrimaryToCart();
        String actual = msiteDetailsPage.getPincodeToolTip();
        Assert.assertEquals(actual, testData.get("pinCodeToolTip"), "pincode tool tip not displayed");

    }

    @Test
    public void syncAndScanDisplayedTest(){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi("1541", "bangalore");
        waitForPageToLoad("1541");
        detailsPage.selectBrand("Xolo");
        detailsPage.selectModel("A500 S");
        enterPincode(testData.get("validPinCode"));
        detailsPage.clickGoodCondition();
        detailsPage.clickInHand();
        Assert.assertTrue(detailsPage.syncAndScanDisaplyed(),"sync and scan not displayed");

    }

    @Test
    public void withOutSyncAndScanTest(){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi("1541", "bangalore");
        waitForPageToLoad("1541");
        detailsPage.selectBrand("Xolo");
        detailsPage.selectModel("A500 S");
        enterPincode(testData.get("validPinCode"));
        detailsPage.clickGoodCondition();
        detailsPage.clickNotInhand();
        Assert.assertFalse(detailsPage.syncAndScanDisaplyed(),"sync and scan not displayed");
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        Assert.assertNotNull(cartPage.getExchangePrice("1541"));


    }

    @Test
    public void badConditionPhoneTest(){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi("1541", "bangalore");
        waitForPageToLoad("1541");
        detailsPage.selectBrand("Xolo");
        detailsPage.selectModel("A500 S");
        enterPincode(testData.get("validPinCode"));
        detailsPage.clickBadCondition();
        Assert.assertEquals(testData.get("exchangeDecline"),detailsPage.exchangeError(),"exchange decline error");
    }



}
