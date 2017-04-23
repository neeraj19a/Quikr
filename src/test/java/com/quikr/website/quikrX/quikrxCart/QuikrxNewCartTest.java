package com.quikr.website.quikrX.quikrxCart;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXCartPage.QuikrxNewCartPage;
import com.quikr.website.quikrX.quikrXCheckoutPage.QuikrXCheckoutPage;
import com.quikr.website.quikrX.quikrXLandingPage.QuikrXLandingPage;
import com.quikr.website.quikrX.quikrXNewDetailsPage.QuikrXNewDetailsPage;
import com.quikr.website.quikrX.quikrXNewListingPage.QuikrXNewListingPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by francis.s@quikr on 7/1/16.
 */
public class QuikrxNewCartTest extends QuikrXTestBase {

    private final static Log LOGGER = LogFactory.getLog(QuikrxNewCartTest.class.getName());
    String city = testData.get("newUiCity");

    @BeforeMethod(alwaysRun = true)
    public void preTestmethod(){

        HomePage homePage = new HomePage(driver);
        homePage.selectCity(city);
        waitForPageToLoad(city);
        homePage.clickNewQuikrX();
    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = {"QuikrXHighPrio"})
        public void uiValidation(String category, String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xolo");
            detailsPage.selectModel("A500 S");
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertNotNull(cartPage.getExchangePrice(itemId));
        } else {
            enterPinCode();
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");
        }

        Assert.assertNotNull(cartPage.getTitle(itemId));
        Assert.assertNotNull(cartPage.getActualPrice(itemId));
        Assert.assertNotNull(cartPage.getCategory(itemId));
        Assert.assertNotNull(cartPage.getDeliveryIn(itemId));
        Assert.assertNotNull(cartPage.getTitle(itemId));
//        Assert.assertNotNull(cartPage.getOldPrice(itemId));
        Assert.assertNotNull(cartPage.getTotalPrice());
        Assert.assertNotNull(cartPage.getSellerName(itemId));

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class)
    public void verifyCategoryText(String category, String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xolo");
            detailsPage.selectModel("A500 S");
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertEquals(cartPage.getCategory(itemId),testData.get("exchCategory"),"mismatch in category string");

        } else {
            enterPinCode();
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");
            Assert.assertTrue(cartPage.getCategory(itemId).contains(testData.get("certCategory")),"mismatch in category string");
        }



    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class)
    public void verifyDeliveryDays(String category, String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xolo");
            detailsPage.selectModel("A500 S");
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertEquals(cartPage.getDeliveryIn(itemId),testData.get("exchDelivery"),"mismatch in category string");
            cartPage.clickRemoveitem(itemId);
            openProductNewUi(itemId,city);
            detailsPage.clickIDontHavePhone();
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertEquals(cartPage.getDeliveryIn(itemId),testData.get("certDelivery"),"mismatch in category string");

        } else {
            enterPinCode();
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");
            Assert.assertEquals(cartPage.getDeliveryIn(itemId),testData.get("certDelivery"),"mismatch in category string");
        }

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = "QuikrXHighPrio")
    public void validateDetailsAgainstDetailsPage(String category,String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId,city);
        String itemTitle =detailsPage.getItemName();
        String expdiscPrice = detailsPage.priceAfterDisc();

        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xolo");
            detailsPage.selectModel("A500 S");
            String exchangePrice = detailsPage.getExchangeItemPrice();
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertTrue(itemTitle.contains(cartPage.getTitle(itemId).replace("...","")),"mismatch in item title");
            Assert.assertEquals(cartPage.getActualPrice(itemId),expdiscPrice,"mismatch in price after discount");
            Assert.assertEquals(cartPage.getExchangePrice(itemId),exchangePrice,"mismatch in exchange price");

        } else {
            enterPinCode();
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");

            Assert.assertTrue(itemTitle.contains(cartPage.getTitle(itemId).replace("...","")),"mismatch in item title");
            Assert.assertEquals(cartPage.getActualPrice(itemId),expdiscPrice,"mismatch in price after discount");
       }

   }


    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class)
    public void validateOrderSummaryAgainstDetailsPage(String category,String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId,city);
        String itemTitle =detailsPage.getItemName();
        int expdiscPrice = Integer.parseInt(detailsPage.priceAfterDisc());

        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xolo");
            detailsPage.selectModel("A500 S");
            enterPinCode();
            int exchangePrice =Integer.parseInt(detailsPage.getExchangeItemPrice());
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");

            Assert.assertNotNull(cartPage.getTitleOrderSummary(itemTitle),"mismatch in order summary title");
            Assert.assertEquals(cartPage.getOrdderSummarPrice(itemTitle),(expdiscPrice-exchangePrice)+"","mismatch in price after discount");

        } else {
            enterPinCode();
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");

            Assert.assertNotNull(cartPage.getTitleOrderSummary(itemTitle),"mismatch in order summary title");
            Assert.assertEquals(cartPage.getOrdderSummarPrice(itemTitle),expdiscPrice+"","mismatch in price after discount");
        }

    }


    @Test(dataProvider = "newpriceChange", dataProviderClass = Data.class)
    public void validatePriceChangeNotification(String category,String itemId, String newPrice, String expectedNotification){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);
        QuikrXApiBase quikrXApi = new QuikrXApiBase();
        HeaderPage headerPage = new HeaderPage(driver);

        quikrXApi.updateProductInventory("25", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);

        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xolo");
            detailsPage.selectModel("A500 S");
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            quikrXApi.updatePrice(newPrice,"1000",itemId,"");
            headerPage.clickCart();
            waitForPageToLoad("Cart");
            Assert.assertTrue(cartPage.getNotificationText(itemId).contains(expectedNotification),"mismatch in notification text");


        } else {
            enterPinCode();
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");
            quikrXApi.updatePrice(newPrice,"1000",itemId,"");
            headerPage.clickCart();
            waitForPageToLoad("Cart");
            Assert.assertTrue(cartPage.getNotificationText(itemId).contains(expectedNotification),"mismatch in notification text");

        }

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = "QuikrXHighPrio")
    public void verifyRemoveItemFromCart(String category, String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xolo");
            detailsPage.selectModel("A500 S");
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            cartPage.clickRemoveitem(itemId);
            Assert.assertTrue(cartPage.isCartEmpty());

        } else {
            enterPinCode();
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");

            cartPage.clickRemoveitem(itemId);
            Assert.assertTrue(cartPage.isCartEmpty());
        }

    }

    @Test
    public void emptyCartContinueLink(){

        HeaderPage headerPage = new HeaderPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        headerPage.clickCart();
        waitForPageToLoad("Cart");
        cartPage.clickContinueShopping();
        Assert.assertNotNull(listingPage.getfirstLisitngActualPrice());

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class)
    public void placeOrder(String category, String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage checkOutPage = new QuikrXCheckoutPage(driver);

        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xolo");
            detailsPage.selectModel("A500 S");
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            String totalPrice = cartPage.getTotalPrice();
            cartPage.clickPlaceOrder();
            waitForPageToLoad("checkout");
            Assert.assertEquals(checkOutPage.getTotalPrice(),totalPrice,"mismatch in total price");


        } else {
            enterPinCode();
            detailsPage.certiAddPrimaryToCart();
            waitForPageToLoad("Cart");

            String totalPrice = cartPage.getTotalPrice();
            cartPage.clickPlaceOrder();
            waitForPageToLoad("checkout");
            Assert.assertEquals(checkOutPage.getTotalPrice(),totalPrice,"mismatch in total price");
        }

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = {"prodTest"})
    public void uiValidationProd(String category, String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);
        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        waitForPageToLoad("QuikrX");
        if (!category.equalsIgnoreCase("exchange")){
        landingPage.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished");
        }else{
            landingPage.clickExchangeTab();
            landingPage.clickSeeAllExchangePhone();
            waitForPageToLoad("Exchange");
        }
        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("order=priceAsc");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdIdZ");
        itemId = getCurrentUrl().split("ProdIdZ")[1].trim();
        LOGGER.info("item is used  "+ itemId );
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.selectBrand("Xiaomi");
            sleep(5);
            detailsPage.selectModel();
            enterPinCode();
            detailsPage.exchangeAddTocartPrim();
            waitForPageToLoad("Cart");
            Assert.assertNotNull(cartPage.getExchangePrice(itemId));
        } else {
            enterPinCode();
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

}
