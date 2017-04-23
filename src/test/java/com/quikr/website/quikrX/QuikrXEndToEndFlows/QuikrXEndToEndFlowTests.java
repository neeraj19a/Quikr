package com.quikr.website.quikrX.QuikrXEndToEndFlows;

import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXCartPage.QuikrxNewCartPage;
import com.quikr.website.quikrX.quikrXCheckoutPage.QuikrXCheckoutPage;
import com.quikr.website.quikrX.quikrXLandingPage.QuikrXLandingPage;
import com.quikr.website.quikrX.quikrXNewDetailsPage.QuikrXNewDetailsPage;
import com.quikr.website.quikrX.quikrXNewListingPage.QuikrXNewListingPage;
import com.quikr.website.quikrX.quikrXOrders.QuikrXMyOrdersPage;
import com.quikr.website.quikrX.quikrXQomaPage.QuikrXQomaHomePage;
import com.quikr.website.quikrX.quikrXQomaPage.QuikrXQomaOrderPage;
import com.quikr.website.quikrX.quikrXsellerDashBoardPage.QuikrXSellerDashBoardPage;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by francis.s@quikr on 2/11/15.
 */
public class QuikrXEndToEndFlowTests extends QuikrXTestBase {

    private final static Log LOGGER = LogFactory.getLog(QuikrXEndToEndFlowTests.class.getName());

    @Test(dataProvider = "e2e", dataProviderClass = Data.class)
    public void mvpeCertExchangeEndToEndTest(String category) {


        QuikrXMyOrdersPage myOrdersPage = new QuikrXMyOrdersPage(driver);
        HomePage homePage = new HomePage(driver);
        HeaderPage header = new HeaderPage(driver);
        QuikrXQomaHomePage qomaHomePage = new QuikrXQomaHomePage(driver);
        QuikrXQomaOrderPage qomaOrderPage = new QuikrXQomaOrderPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXSellerDashBoardPage sellerHome = new QuikrXSellerDashBoardPage(driver);

        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        header.letsLogin("randomPageLoginResponsive",testData.get("CitySelect"),testData.get("loginEmail"),testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        LOGGER.info("logged in as "+testData.get("loginEmailProd"));

        //Clear cart if not empty
        header.clickCart();
        waitForPageToLoad("Cart");
        if(!quikrXCartPage.isCartEmpty())
            clearCart();
        quikrXCartPage.clickContinueShopping();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        String orderId = placeOrder(category);

        goToHomePage(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelectUrl"));
        header.clickMyQuikrX();
        String subOrderId =  myOrdersPage.getSubOrderForOrderId(orderId);

        //Go to qoma validate palced order details , set call confirmed
        navigatethirdparty(driver, qomaEnv);
        qomaHomePage.qomalogin();
        qomaHomePage.clickViewSubOrder();
        qomaHomePage.searchByOrderid(orderId);
        qomaHomePage.clickOrderId(orderId);
        validateQomaOrderDetails(subOrderId, "Pending", category);
        qomaOrderPage.setStatus(subOrderId, "Call confirmed");
        Assert.assertEquals("Call confirmed", qomaOrderPage.getStatus(subOrderId));

        //verify order is in confirmed status on my orders page
        goToHomePage(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelectUrl"));
        header.clickMyQuikrX();
        Assert.assertTrue(myOrdersPage.validateStatusByOrderId(orderId, testData.get("confirmedState")), "Confirmed order not displayed");

        //set status to pack slip in sellerDashBoard
        navigatethirdparty(driver, dashBoardEnv);
        sellerHome.login(sellerUserName, sellerPassword);
        sellerHome.updateStatusPackSlip(subOrderId);

        //Verify pack slip is dispalyed in qoma
        verifyOrderStatusQoma(qomaHomePage,orderId, subOrderId, testData.get("packSlip"));

        //set status to manifest generated in seller dash board
        navigatethirdparty(driver, dashBoardEnv);
        generateManifest(category, subOrderId);

        //verify status is manifest generated in qoma
        verifyOrderStatusQoma(qomaHomePage,orderId, subOrderId, testData.get("manifestGen"));

        //set status to disapatch in seller Dash baord
        navigatethirdparty(driver, dashBoardEnv);
        setDispatch(category, subOrderId);

        goToHomePage(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelectUrl"));
        header.clickMyQuikrX();
        Assert.assertTrue(myOrdersPage.validateStatusByOrderId(orderId, testData.get("dispatchedState")), "Dispatched  status mismatch");

        //verify dispatch on qoma
        verifyOrderStatusQoma(qomaHomePage,orderId, subOrderId, testData.get("dispatched"));

        //set status to delivered
        qomaOrderPage.setStatus(subOrderId, testData.get("delivered"));

        //verify status in my order page
        goToHomePage(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("CitySelectUrl"));
        header.clickMyQuikrX();
        myOrdersPage.goTo("deliveredOrderTab");
        Assert.assertTrue(myOrdersPage.validateStatusByOrderId(orderId, testData.get("deliveredState")), "Delivered order not present in delivered tab");

    }


    private void verifyOrderStatusQoma(QuikrXQomaHomePage qomaHomePage,String orderId, String subOrderId, String status) {

        QuikrXQomaOrderPage qomaOrderPage = new QuikrXQomaOrderPage(driver);

        navigatethirdparty(driver, qomaEnv);
        qomaHomePage.qomalogin();
        qomaHomePage.clickViewSubOrder();
        qomaHomePage.searchByOrderid(orderId);
        qomaHomePage.clickOrderId(orderId);
        Assert.assertEquals(status, qomaOrderPage.getStatus(subOrderId));
    }


    @Test(groups ={"prodE2eTest"})
    @Title("Sign in place order")
    @Description("Sign in place order cancel order")
    public void frontEndFlow() {

        HomePage homePage = new HomePage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage  = new QuikrXCheckoutPage(driver);
        QuikrXQomaHomePage qomaHomePage = new QuikrXQomaHomePage(driver);
        QuikrXQomaOrderPage qomaOrderPage = new QuikrXQomaOrderPage(driver);
        HeaderPage header = new HeaderPage(driver);
        QuikrXMyOrdersPage myOrdersPage = new QuikrXMyOrdersPage(driver);

        //Select city and sign in
        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        headerPage.letsLogin("randomPageLoginResponsive",testData.get("CitySelect"),testData.get("loginEmailProd"),testData.get("loginpassword"));
        waitForPageToLoad("MyQuikr");
        LOGGER.info("logged in as "+testData.get("loginEmailProd"));


        //Clear cart if not empty
        goToHomePage(testData.get("CitySelect"));
        waitForPageToLoad("quikr");
        homePage.clickNewQuikrX();
        waitForPageToLoad("QuikrX");
        landingPage.clickExchangeTab();
        landingPage.clickSeeAllExchangePhone();
        waitForPageToLoad("Exchange");
        headerPage.clickCart();
        waitForPageToLoad("Cart");
        if(!quikrXCartPage.isCartEmpty())
            clearCart();
        quikrXCartPage.clickContinueShopping();
        waitForPageToLoad("Refurbished-Mobile-Phones");

        //Add to cart lowest price phone
        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("order=priceAsc");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdIdZ");
        String itemId = getCurrentUrl().split("ProdIdZ")[1].trim();
        LOGGER.info("item is used  "+ itemId );
        detailsPage.enterPincode("620001");
        detailsPage.clickPincodeCheck();
        detailsPage.certiAddPrimaryToCart();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkOut");

        //place order as cod
        try {

            quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        }catch (Exception e){
            driver.navigate().refresh();
            waitForPageToLoad("checkOut");
            quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
        }
        String orderId = quikrXCheckoutPage.getOrderId();
        if (!orderId.isEmpty())
              LOGGER.info("order placed succesfully");
        else
              LOGGER.error("Order failed ", new Throwable());

        //check order in myorder section
        header.clickMyQuikrX();
        waitForPageToLoad("myorders");
        sleep(4000);
        String subOrderId =  myOrdersPage.getSubOrderForOrderId(orderId);

        if (!subOrderId.isEmpty())
            LOGGER.info("order id found  "+ subOrderId);
        else
            LOGGER.error("Order failed ", new Throwable());

        //cancel order from Qoma
        navigatethirdparty(driver,"http://qoma.kuikr.com/");
        waitForPageToLoad("qoma");
        qomaHomePage.qomalogin();
        qomaHomePage.clickViewSubOrder();
        qomaHomePage.searchByOrderid(orderId);
        qomaHomePage.clickOrderId(orderId);
        qomaOrderPage.setStatus(subOrderId, "Call cancelled");
        LOGGER.info("order cancelled from qoma succesfully");

        //verify call cancelled in my orders
        navigatethirdparty(driver,"http://www.quikr.com/QuikrX/myorders?city=www");
        myOrdersPage.goTo("cancelledOrderTab");
        waitForPageToLoad("Cancelled");
        Assert.assertTrue(myOrdersPage.validateStatusByOrderId(orderId,testData.get("cancelledState")), "cancelled order not present in cancelled tab");

    }


    @Test(groups ={"prodE2eTest"})
    @Title("Guest place order")
    @Description("Guest place order cancel order")
    public void guestFlowIdontHavePhoneCard(){


        HomePage homePage = new HomePage(driver);
        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage checkoutPage = new QuikrXCheckoutPage(driver);



        //Select city and sign in
        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.clickNewQuikrX();
        waitForPageToLoad("QuikrX");
        landingPage.clickExchangeTab();
        landingPage.clickSeeAllExchangePhone();
        waitForPageToLoad("Exchange");

        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("order=priceAsc");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdIdZ");
        String itemId = getCurrentUrl().split("ProdIdZ")[1].trim();
        LOGGER.info("item is used  "+ itemId );
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.clickIDontHavePhone();
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkOut");

        checkoutPage.fillCheckOutDetails("testuser" + RandomStringUtils.randomAlphanumeric(5) + "@testdomain.com", "test user", "", "Test address dont verify", "9986098765");
        checkoutPage.seclectCard();
        Assert.assertTrue(checkoutPage.validateCardFields(),"card details fields missing");
        checkoutPage.selectNetBanking();
        Assert.assertTrue(checkoutPage.validateNetBankingFields(),"net banking fields missing");

    }


    @Test(groups ={"prodE2eTest"})
    @Title("Guest place order")
    @Description("Guest place order cancel order")
    public void guestFlowIdontHavePhone(){


        HomePage homePage = new HomePage(driver);
        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);

        //Select city and sign in
        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.clickNewQuikrX();
        waitForPageToLoad("QuikrX");
        landingPage.clickExchangeTab();
        landingPage.clickSeeAllExchangePhone();
        waitForPageToLoad("Exchange");

        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("order=priceAsc");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdIdZ");
        String itemId = getCurrentUrl().split("ProdIdZ")[1].trim();
        LOGGER.info("item is used  "+ itemId );
        detailsPage.clickIDontHavePhone();
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        sleep(3000);
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkOut");


        //place order as cod
        String orderId = placeorder();

        //cancel order from Qoma
        cancelOrder(orderId);


    }



    @Test(groups ={"prodE2eTest"})
    @Title("Guest place order")
    @Description("Guest place order cancel order")
    public void guestFlowExchange(){


        HomePage homePage = new HomePage(driver);
        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage checkoutPage = new QuikrXCheckoutPage(driver);



        //Select city and sign in
        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.clickNewQuikrX();
        waitForPageToLoad("QuikrX");
        landingPage.clickExchangeTab();
        landingPage.clickSeeAllExchangePhone();
        waitForPageToLoad("Exchange");

        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("order=priceAsc");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdIdZ");
        String itemId = getCurrentUrl().split("ProdIdZ")[1].trim();
        LOGGER.info("item is used  "+ itemId );
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        sleep(5);
        detailsPage.selectBrand(testData.get("phoneBrandName"));;
        detailsPage.selectModel();
        detailsPage.exchangeAddTocartPrim();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkOut");

        checkoutPage.fillCheckOutDetails("testuser" + RandomStringUtils.randomAlphanumeric(5) + "@testdomain.com", "test user", "", "Test address dont verify", "9986098765");
        checkoutPage.seclectCard();
        Assert.assertTrue(checkoutPage.validateCardFields(),"card details fields missing");
        checkoutPage.selectNetBanking();
        Assert.assertTrue(checkoutPage.validateNetBankingFields(),"net banking fields missing");


//        //place order as cod
//        String orderId = placeorder();
//
//        //cancel order from Qoma
//        cancelOrder(orderId);


    }



    private void cancelOrder(String orderId){

        QuikrXQomaHomePage qomaHomePage = new QuikrXQomaHomePage(driver);
        QuikrXQomaOrderPage qomaOrderPage = new QuikrXQomaOrderPage(driver);

        navigatethirdparty(driver,"http://qoma.kuikr.com/");
        waitForPageToLoad("qoma");
        qomaHomePage.qomalogin();
        qomaHomePage.clickViewSubOrder();
        qomaHomePage.searchByOrderid(orderId);
        String subOrderId=qomaHomePage.getSubOrderIdByOrderId();
        qomaHomePage.clickOrderId(orderId);
        qomaOrderPage.setStatus(subOrderId, "Call cancelled");
        LOGGER.info("order cancelled from qoma succesfully");

    }


   private String placeorder(){

       QuikrXCheckoutPage quikrXCheckoutPage  = new QuikrXCheckoutPage(driver);

       try {
           quikrXCheckoutPage.fillCheckOutDetails("testuser" + RandomStringUtils.randomAlphanumeric(5) + "@testdomain.com", "test user", "", "Test address dont verify", "9986098765");
           quikrXCheckoutPage.selectCod();
       }catch (Exception e){
           driver.navigate().refresh();
           waitForPageToLoad("checkOut");
           quikrXCheckoutPage.fillCheckOutDetails("testuser" + RandomStringUtils.randomAlphanumeric(5) + "@testdomain.com", "test user", "", "Test address dont verify", "9986098765");
           quikrXCheckoutPage.selectCod();
       }

       sleep(4000);
       quikrXCheckoutPage.clickOrderConfirmation();
       waitForPageToLoad("checkout");
       String orderId = quikrXCheckoutPage.getOrderId();
       if (!orderId.isEmpty())
           LOGGER.info("order placed succesfully");
       else
           LOGGER.error("Order failed ", new Throwable());

       return orderId;
   }


    @Test(groups ={"prodE2eTest"})
    @Title("Place an accessories order")
    @Description("Guest place accessories order cancel order")
    public void accessoriesEndToEnd(){


        HomePage homePage = new HomePage(driver);
        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);

        //Select city and sign in
        homePage.selectCity(testData.get("CitySelect"));
        waitForPageToLoad(testData.get("cityUrl"));
        homePage.clickNewQuikrX();
        waitForPageToLoad("QuikrX");
        landingPage.clickExchangeTab();
        landingPage.clickSeeAllExchangePhone();
        waitForPageToLoad("Exchange");
        listingPage.clickAccessoryTab();
        waitForPageToLoad("Accessory");

        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("order=priceAsc");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdIdZ");
        String itemId = getCurrentUrl().split("ProdIdZ")[1].trim();
        LOGGER.info("item is used  "+ itemId );
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        detailsPage.certiAddPrimaryToCart();
        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkOut");

        //place order as cod
        String orderId = placeorder();

        //cancel order from Qoma
        cancelOrder(orderId);


    }




}
