package com.quikr.website.quikrX.quikrXCheckOut;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXCartPage.QuikrxNewCartPage;
import com.quikr.website.quikrX.quikrXCheckoutPage.QuikrXCheckoutPage;
import com.quikr.website.quikrX.quikrXNewDetailsPage.QuikrXNewDetailsPage;
import com.quikr.website.quikrX.quikrXNewListingPage.QuikrXNewListingPage;
import com.quikr.website.quikrX.quikrXsellerDashBoardPage.QuikrXSellerDashBoardPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by quikr on 8/10/15.
 */
public class QuikrXCheckOutTests extends QuikrXTestBase{


    @BeforeClass(alwaysRun = true)
    public void setUp(){
        QuikrXApiBase apiBase = new QuikrXApiBase();

        apiBase.updateProductInventory("25", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000","1503");
        apiBase.updateProductInventory("25", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000","982");
    }

    /**
     * Testcase - WEB-170:Delivery Details
     */
    @Test(groups = "QuikrXHighPrio")
    public void deliveryDetails() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        loginPlaceOrder("1503");
        Assert.assertTrue(quikrXCheckoutPage.validateDeliveryDetails(), "Delivery Details not shown on Checkout Page");
    }

    /**
     * Testcase - WEB-143:Verify that the order summery is displayed on the right hand side of checkout page
     */
    @Test
    public void orderSummary() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        loginPlaceOrder("1503");
        Assert.assertTrue(quikrXCheckoutPage.validateOrderSummary(), "Order Summary not shown on Checkout Page");
    }

    /**
     * Testcase - WEB-144:signInDetails has Continue as guest and Sign In with Quikr
     */
    @Test
    public void signInDetailsOptions() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        placeOrderWithOutLogin("1503");
        Assert.assertTrue(quikrXCheckoutPage.validateContinueAsGuestAndSignIn(testData.get("signInEmail")), "SignIn details don't have ontinue as guest and Sign In with Quikr options");
    }

    /**
     * Testcase - WEB-145:User Should be able to continue as guest user
     */
    @Test
    public void continueAsGuestOption() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        placeOrderWithOutLogin("1503");
        Assert.assertTrue(quikrXCheckoutPage.validateContinueAsGuest(testData.get("signInEmail")), "SignIn details don't have ontinue as guest and Sign In with Quikr options");
    }

    /**
     * Testcase - WEB-146:User should be able to sign in with quikr account
     */
    @Test
    public void signInOption() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        placeOrderWithOutLogin("1503");
        Assert.assertTrue(quikrXCheckoutPage.validateSignInOnCheckoutPage(testData.get("loginEmail"), testData.get("loginpassword")), "Sign In with Quikr option is failed");
    }

    /**
     * Testcase - WEB-148:User is able to place the order
     */
    @Test
    public void userPlaceOrder() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        loginPlaceOrder("1503");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Place Order is Failed");
    }

    /**
     * Testcase - WEB-149:Verify the order success message is displayed
     */
    @Test
    public void placeOrderSuccess() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        loginPlaceOrder("1503");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Place Order is Failed");
    }

    /**
     * Testcase - WEB-140:place order from cart
     */
    @Test
    public void placeOrderFromCart() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        loginPlaceOrder("1503");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Place Order from Cart is Failed");
    }

    /**
     * Testcase - Sort by higher price and purchase via COD
     */
    @Test
    public void sortByHigherPriceAndBuyPreOwnedPhone() {

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        //homePage.SelectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickSignIn(testData.get("cityUrl"));
        waitForPageToLoad("SignIn");
        headerPage.nonResponsiveLogin(testData.get("loginEmail"), testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        navigatethirdparty(driver,getTestDataWithReplace(testData.get("quikrXhomeUrl"),testData.get("CitySelect")));
        waitForPageToLoad("QuikrX");
        listingPage.clickCertifiedTab();
        listingPage.clickSortOptions("High to Low");
        waitForPageToLoad("QuikrX");
        listingPage.openFirstitem();
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.certiAddPrimaryToCart();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Preowned phone sort by Higher price and Place Order is Failed");
    }
//
    /**
     * Testcase - Sort by lower price and purchase via COD
     */
    @Test
    public void sortByLowerPriceAndBuyPreOwnedPhone() {

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        //homePage.SelectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickSignIn(testData.get("cityUrl"));
        waitForPageToLoad("SignIn");
        headerPage.nonResponsiveLogin(testData.get("loginEmail"), testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        navigatethirdparty(driver,getTestDataWithReplace(testData.get("quikrXhomeUrl"),testData.get("CitySelect")));
        waitForPageToLoad("QuikrX");
        listingPage.clickCertifiedTab();
        listingPage.clickSortOptions("High to Low");
        waitForPageToLoad("QuikrX");
        listingPage.openFirstitem();
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.certiAddPrimaryToCart();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Preowned phone sort by Lower price and Place Order is Failed");
    }
//
    /**
     * Testcase - Exchange Phone Sort by lower price and purchase via COD
     */
    @Test
    public void sortByLowerPriceAndBuyExchangePhone() {

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        //homePage.SelectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickSignIn(testData.get("cityUrl"));
        waitForPageToLoad("SignIn");
        headerPage.nonResponsiveLogin(testData.get("loginEmail"), testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        navigatethirdparty(driver,getTestDataWithReplace(testData.get("quikrXhomeUrl"),testData.get("CitySelect")));
        waitForPageToLoad("QuikrX");
        listingPage.clickExchangeTab();
        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("QuikrX");
        listingPage.openFirstitem();
        detailsPage.selectBrand(testData.get("phoneBrandName"));
        detailsPage.selectModel(testData.get("phoneModelName"));
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Exchange phone sort by Lower price and Place Order is Failed");
    }

    /**
     * Testcase - Exchange Phone Sort by Higher price and purchase via COD
     */
    @Test
    public void sortByHigherPriceAndBuyExchangePhone() {

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        //homePage.SelectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickSignIn(testData.get("cityUrl"));
        waitForPageToLoad("SignIn");
        headerPage.nonResponsiveLogin(testData.get("loginEmail"), testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        navigatethirdparty(driver,getTestDataWithReplace(testData.get("quikrXhomeUrl"),testData.get("CitySelect")));
        waitForPageToLoad("QuikrX");
        listingPage.clickExchangeTab();
        listingPage.clickSortOptions("High to Low");
        waitForPageToLoad("QuikrX");
        listingPage.openFirstitem();
        detailsPage.selectBrand(testData.get("phoneBrandName"));
        detailsPage.selectModel(testData.get("phoneModelName"));
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Exchange phone sort by Higher price and Place Order is Failed");
    }


    /**
     * Testcase - Verify the Various fields in credit/Debit card form
     */
    @Test(groups = {"QuikrXHighPrio"})
    public void verifyCreditDebitCardform() {

        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        loginPlaceOrder("1503");
        Boolean validateCardBanking = quikrXCheckoutPage.placeOrderViaDebitCreditcard(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(validateCardBanking, "Place order via card failed");
    }

    /**
     * Testcase - Place order -Exchange phone-COD
     */
    @Test(groups = {"QuikrXHighPrio"})
    public void placeOrderExchangePhoneToCartWithLogin() {

        HeaderPage headerPage = new HeaderPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        exchangeLogin("982");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Place Order is Failed");
    }

    /**
     * Testcase - Place order -Combination of Exchange and certified -COD
     */
    @Test(groups = {"QuikrXHighPrio"})
    public void placeOrderExchangePhoneAndCertifiedToCartWithLogin()  {

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        //homePage.SelectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickSignIn(testData.get("cityUrl"));
        waitForPageToLoad("SignIn");
        headerPage.nonResponsiveLogin(testData.get("loginEmail"), testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        openProductNewUi("982","bangalore");
        waitForPageToLoad("982");
        detailsPage.selectBrand(testData.get("phoneBrandName"));
        detailsPage.selectModel(testData.get("phoneModelName"));
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        openProductNewUi("1503",testData.get("CitySelect"));
        waitForPageToLoad("1503");
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.certiAddPrimaryToCart();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Place Order is Failed");
        goToHomePage(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelect"));
        headerPage.clickCart();
        waitForPageToLoad("Cart");
        Assert.assertTrue(quikrXCartPage.isCartEmpty(), "Cart isn't empty after placing order");
    }


    /**
     * Testcase - Buy new phone without exchange without login
     */
    @Test
    public void buyNewPhoneWithoutExchangeWithoutLogin() {

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        //homePage.SelectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        openProductNewUi("982","bangalore");
        waitForPageToLoad("982");
        detailsPage.clickIDontHavePhone();
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        quikrXCheckoutPage.placeOrderAsGuestUser(testData.get("signInEmail"), testData.get("password"), testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        Assert.assertTrue(quikrXCheckoutPage.validatePlaceOrder(), "Buy new phone without exchange Failed");
    }

   /**
    * Testcase - Sce_012,Sce_013 - Out of Stock notificiation on checkOut
    */
    @Test(dataProvider = "checkOutpriceChange", dataProviderClass = Data.class, groups = "mvp")
    public void priceChangeNotificationCheckOut(String category, String itemId,
                                                String sellerName, String oldPrice,
                                                String expectedString, String priceChange, boolean oos) {

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXSellerDashBoardPage sellerPage = new QuikrXSellerDashBoardPage(driver);

        navigatethirdparty(driver, dashBoardEnv);
        sellerPage.login(sellerUserName, sellerPassword);
        if (!sellerPage.isProductEnabled(category, itemId)) {
            sellerPage.newItemEntry(category, itemId, oldPrice, 6);
        } else {
            sellerPage.changePrice(category, itemId, oldPrice);
        }
        openProductNewUi(itemId,"bangalore");
//        String dicsount = detailsPage.discountPercentage();
        String oosExpectedText="";
        if(!detailsPage.getSellerCount().equalsIgnoreCase("0"))
            oosExpectedText = (testData.get("checkOutOutOfStock1"))+testData.get("checkOutOutOfStock2") + " "+ detailsPage.getSellerCount() + testData.get("checkOutOutOfStock3");
        else
            oosExpectedText=testData.get("checkOutSingleooS");
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        if(category.equalsIgnoreCase("exchange")){
            detailsPage.clickIDontHavePhone();
            detailsPage.addToCart("1000");
        }else
            detailsPage.addToCart("1000");
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        quikrXCartPage.body().sendKeys(Keys.CONTROL+"t");
        navigatethirdparty(driver, dashBoardEnv);
        if(!oos){
            sellerPage.changePrice(category, itemId, priceChange);
            quikrXCartPage.body().sendKeys(Keys.CONTROL + "w");
            refreshPage();
            Assert.assertEquals(quikrXCheckoutPage.getPriceChangeText(), expectedString, "mismatch in price notification in checkout Page");
        }else{
            sellerPage.setItemOutOfStock(category, itemId);
            expectedString=oosExpectedText;
            quikrXCartPage.body().sendKeys(Keys.CONTROL + "w");
            refreshPage();
            System.out.println(quikrXCheckoutPage.getOosChangeText());
            Assert.assertEquals(quikrXCheckoutPage.getOosChangeText(), expectedString, "mismatch in price notification in checkout Page");
        }

    }

    @Test
        public void verifyPriceDiscount(){

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        openProductNewUi("1503",testData.get("CitySelect"));
        waitForPageToLoad("1503");
        String discountedPrice = calulatePercentagePrice();
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.certiAddPrimaryToCart();
        quikrXCartPage.clickPlaceOrder();
        String totalPrice = quikrXCheckoutPage.getTotalPrice();
        Assert.assertEquals(totalPrice,discountedPrice,"price not as discounted price");

    }


    private void loginPlaceOrder(String itemId){

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);

        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.letsLogin("randomPageLoginResponsive",testData.get("CitySelect"),testData.get("loginEmail"),testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        headerPage.clickCart();
        waitForPageToLoad("Cart");
        if(!quikrXCartPage.isCartEmpty())
           clearCart();
        openProductNewUi(itemId,testData.get("CitySelect"));
        waitForPageToLoad(itemId);
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.certiAddPrimaryToCart();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();

    }

    private void placeOrderWithOutLogin(String itemId){

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);

        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        openProductNewUi(itemId,testData.get("CitySelect"));
        waitForPageToLoad(itemId);
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.certiAddPrimaryToCart();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();

    }

    private void exchangeLogin(String itemId){

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);

        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.clickSignIn(testData.get("cityUrl"));
        waitForPageToLoad("SignIn");
        headerPage.nonResponsiveLogin(testData.get("loginEmail"), testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        openProductNewUi(itemId,"bangalore");
        detailsPage.selectBrand(testData.get("phoneBrandName"));
        detailsPage.selectModel(testData.get("phoneModelName"));
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
    }

}
