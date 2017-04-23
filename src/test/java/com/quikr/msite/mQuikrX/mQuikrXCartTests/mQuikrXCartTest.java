package com.quikr.msite.mQuikrX.mQuikrXCartTests;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXEnumData;
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

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by francis.s@quikr on 17/5/16.
 */
public class mQuikrXCartTest extends mQuikrXTestBase {

    private final static Log LOGGER = LogFactory.getLog(mQuikrXCartTest.class.getName());
    private HashMap<String, String> testData = getTestData(getProperties().get("mQUIKRX_TESTDATA_FILE"));

    @BeforeMethod
    public void preSetUp() {

        MHomePage homePage = new MHomePage(driver);
        homePage.selectCity("Bangalore");
        waitForPageToLoad("bangalore");
        homePage.clickQuikrX();
        waitForPageToLoad("QuikrX");

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = {"QuikrXHighPrio","prodTest"})
    public void uiValidationTest(String category, String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);


        openProductNewUi(itemId,testData.get("city"));
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            selctExchangeEnterPincode("Xolo","A500 S",testData.get("validPinCode"));
            Assert.assertTrue(detailsPage.syncAndScanDisaplyed(),"sync and scan not displayed");
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertNotNull(cartPage.getExchangePrice(itemId));
        } else {
            enterPincode(testData.get("validPinCode"));
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");
        }

        Assert.assertNotNull(cartPage.getTitle(itemId));
        Assert.assertNotNull(cartPage.getActualPrice(itemId));
        Assert.assertNotNull(cartPage.getCategory(itemId));
        Assert.assertNotNull(cartPage.getDeliveryIn(itemId));
        Assert.assertNotNull(cartPage.getTitle(itemId));
        Assert.assertNotNull(cartPage.getTotalPrice());
        Assert.assertNotNull(cartPage.getSellerName(itemId));

    }


    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class)
    public void verifyCategoryTextTest(String category, String itemId) {

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId, testData.get("city"));
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            selctExchangeEnterPincode("Xolo","A500 S",testData.get("validPinCode"));
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertEquals(cartPage.getCategory(itemId), testData.get("exchCategory"), "mismatch in category string");

        } else {
            enterPincode(testData.get("validPinCode"));
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");
            Assert.assertEquals(cartPage.getCategory(itemId), testData.get("certCategory"), "mismatch in category string");
        }

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = "QuikrXHighPrio")
    public void validateDetailsAgainstDetailsPage(String category,String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId,testData.get("city"));
        String itemTitle =detailsPage.getItemName();
        String expdiscPrice = detailsPage.priceAfterDisc();

        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            selctExchangeEnterPincode("Xolo","A500 S",testData.get("validPinCode"));
            String exchangePrice = detailsPage.getExchangeItemPrice();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertTrue(itemTitle.contains(cartPage.getTitle(itemId).replace("...","")),"mismatch in item title");
            Assert.assertEquals(cartPage.getActualPrice(itemId),expdiscPrice,"mismatch in price after discount");
            Assert.assertEquals(cartPage.getExchangePrice(itemId),exchangePrice,"mismatch in exchange price");

        } else {
            enterPincode(testData.get("validPinCode"));
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");

            Assert.assertTrue(itemTitle.contains(cartPage.getTitle(itemId).replace("...","")),"mismatch in item title");
            Assert.assertEquals(cartPage.getActualPrice(itemId),expdiscPrice,"mismatch in price after discount");
        }

    }

    @Test(dataProvider = "newpriceChange", dataProviderClass = Data.class)
    public void validatePriceChangeNotification(String category,String itemId, String newPrice, String expectedNotification){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);
        QuikrXApiBase quikrXApi = new QuikrXApiBase();

        quikrXApi.updateProductInventory("25", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
        openProductNewUi(itemId,testData.get("city"));
        waitForPageToLoad(itemId);

        if (category.equalsIgnoreCase("exchange")) {
            selctExchangeEnterPincode("Xolo","A500 S",testData.get("validPinCode"));
            detailsPage.addToCart("1000");
            waitForPageToLoad("Cart");
            quikrXApi.updatePrice(newPrice,"1000",itemId,"");
            driver.navigate().refresh();
            waitForPageToLoad("Cart");
            Assert.assertTrue(cartPage.getNotificationText(itemId).contains(expectedNotification),"mismatch in notification text");


        } else {
            enterPincode(testData.get("validPinCode"));
            detailsPage.addToCart("1000");
            waitForPageToLoad("Cart");
            quikrXApi.updatePrice(newPrice,"1000",itemId,"");
            driver.navigate().refresh();
            waitForPageToLoad("Cart");
            Assert.assertTrue(cartPage.getNotificationText(itemId).contains(expectedNotification),"mismatch in notification text");

        }

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class)
    public void placeOrder(String category, String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId,testData.get("city"));
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            selctExchangeEnterPincode("Xolo","A500 S",testData.get("validPinCode"));
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            cartPage.clickPlaceOrder();
            waitForPageToLoad("checkout");

        } else {
            enterPincode(testData.get("validPinCode"));
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");

            cartPage.clickPlaceOrder();
            waitForPageToLoad("checkout");
        }

    }

 }
