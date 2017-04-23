package com.quikr.website.quikrX;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.website.TestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.quikrX.quikrXCartPage.QuikrxNewCartPage;
import com.quikr.website.quikrX.quikrXCheckoutPage.QuikrXCheckoutPage;
import com.quikr.website.quikrX.quikrXHomePage.QuikrXHomePage;
import com.quikr.website.quikrX.quikrXNewDetailsPage.QuikrXNewDetailsPage;
import com.quikr.website.quikrX.quikrXQomaPage.QuikrXQomaOrderPage;
import com.quikr.website.quikrX.quikrXQomaPage.QuikrXQomaReplacementPage;
import com.quikr.website.quikrX.quikrXsellerDashBoardPage.QuikrXSellerDashBoardMidPage;
import com.quikr.website.quikrX.quikrXsellerDashBoardPage.QuikrXSellerDashBoardPage;
import com.quikr.website.roundCubeEmail.RoundCubeEmailPage;
import org.apache.commons.lang3.text.WordUtils;
import org.json.simple.JSONObject;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaType;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;


/**
 * Created by francis.s@quikr on 8/10/15.
 */
public class QuikrXTestBase extends TestBase {

    public HashMap<String, String> testData = getTestData(getProperties().get("QUIKRX_TESTDATA_FILE"));
    private ScreenRecorder screenRecorder;

    public String dashBoardEnv,sellerUserName,sellerPassword,qomaEnv;
    private  String URL = null;
    private String itemPrice, totalPrice, exchangeDiscount, oldPhonePrice=null;
    public static Properties configProperties = new Properties();
    InputStream input = null;


    @BeforeClass(alwaysRun = true)
    public void BeforeClass() throws IOException {
        String env = System.getProperty("env");
        input = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        configProperties.load(input);
        String apiEnv = configProperties.getProperty("apiEnv");

        if(env != null && env.equalsIgnoreCase("integration") || apiEnv.equalsIgnoreCase(("qa")))
        {
            dashBoardEnv=testData.get("qaSellerDashBoard");
            URL = "http://192.168.124.44:9003/";
            qomaEnv=testData.get("qomaQa");
            sellerUserName=testData.get("qaSellerUserName");
            sellerPassword=testData.get("qaSellerPassword");
        }
        else
        {
            dashBoardEnv=testData.get("devSellerDashBoard");
            qomaEnv=testData.get("qomaDev");
            URL = "http://192.168.124.53:7206/";
            sellerPassword=testData.get("sellerLoginPassword");
            sellerUserName=testData.get("sellerLoginEmail");
        }
    }


    public void verifyHomePageTitle() {
        QuikrXHomePage quikrXHomePage = new QuikrXHomePage(driver);

        String pageTitle = quikrXHomePage.getTitle();
        String expected = getTestDataWithReplace(testData.get("expectedPageTitle"), WordUtils.capitalize(testData.get("CitySelect")));
        Assert.assertEquals(expected.substring(0, 20), pageTitle.substring(0, 20), "Mismatch in title");
    }

    public void openProductByItem(String itemId) {

        QuikrXApiBase apiBase = new QuikrXApiBase();

        apiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
        navigatethirdparty(driver, getTestDataWithReplace(testData.get("priceUrl"), testData.get("cityName")) + itemId);
        waitForPageToLoad(itemId);
        if(enableProductIfDisabled(itemId)){
            navigatethirdparty(driver, getTestDataWithReplace(testData.get("priceUrl"), testData.get("cityName")) + itemId);
        }
    }

    public void openProductByItem(String itemId, String category) {
        QuikrXApiBase apiBase = new QuikrXApiBase();

        apiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
        navigatethirdparty(driver, getTestDataWithReplace(testData.get("priceUrl"), testData.get("CitySelect")) + itemId);
        waitForPageToLoad(itemId);
        if(enableProductIfDisabled(itemId)){
            navigatethirdparty(driver, getTestDataWithReplace(testData.get("priceUrl"), testData.get("CitySelect")) + itemId);
        }

    }

    public void openProductNewUi(String itemId, String city){
        QuikrXApiBase apiBase = new QuikrXApiBase();

        apiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
        navigatethirdparty(driver, getTestDataWithReplace(testData.get("newUiLink"), city) + itemId);
        waitForPageToLoad(itemId);
        if(enableProductIfDisabled(itemId)){
            navigatethirdparty(driver, getTestDataWithReplace(testData.get("newUiLink"), city) + itemId);
        }
    }

    public boolean enableProductIfDisabled(String itemId){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrXSellerDashBoardPage sellerPage = new QuikrXSellerDashBoardPage(driver);
        QuikrXApiBase apiBase = new QuikrXApiBase();

        String category = apiBase.getProduct(itemId).get("subcategory_name").asText();

        if(category.equalsIgnoreCase("new")){
            category="exchange";
        }else {
            category="certified";
        }

        if(detailsPage.isItemOos()){
            navigatethirdparty(driver, dashBoardEnv);
            waitForPageToLoad("seller");

            if(getCurrentUrl().contains("login"))
                sellerPage.login(sellerUserName, sellerPassword);
            waitForPageToLoad("orders");
            if(!sellerPage.isProductEnabled(category,itemId)){
                sellerPage.enableItem(itemId);
                return true;
            }

        }
        return false;

    }

    public void goToHomePage(String city) {
        navigatethirdparty(driver, getTestDataWithReplace(testData.get("homeUrl"), city));

    }



    public JSONObject updateQuoteItemStatus(String orderNumber, String status) {

        try {
            JSONObject updateQuoteItemStatus = new JSONObject();
            updateQuoteItemStatus.put("orderId", orderNumber);
            updateQuoteItemStatus.put("status", status);
            String jsonData = updateQuoteItemStatus.toString();

            return parseResponse(postMethod(URL + "updateQuoteItemStatus", jsonData));
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }


    public void generateManifest(String category, String subOrderId) {

        QuikrXSellerDashBoardMidPage midPage = new QuikrXSellerDashBoardMidPage(driver);
        QuikrXSellerDashBoardPage dashBoardPage = new QuikrXSellerDashBoardPage(driver);

        if (category.equalsIgnoreCase("exchange")) {

             waitForPageToLoad("home");
             String url = getCurrentUrl().replace("home","generatemid/?category=Exchange");
             navigatethirdparty(driver,url);
            waitForPageToLoad("Exchange");

        } else {
            waitForPageToLoad("home");
            String url = getCurrentUrl().replace("home","generatemid/?category=Certified");
            navigatethirdparty(driver,url);
            waitForPageToLoad("Certified");
        }

        midPage.midSelectAll();
        midPage.generateMidclick(subOrderId);
        midPage.generateSingleMid();
        midPage.clickSubmit();
        pressEscape();
    }


    public void setDispatch(String category, String subOrderId) {

        QuikrXSellerDashBoardMidPage midPage = new QuikrXSellerDashBoardMidPage(driver);
        QuikrXSellerDashBoardPage dashBoardPage = new QuikrXSellerDashBoardPage(driver);
        File file = null;

        if(!dashBoardPage.isPendingVisible())
            dashBoardPage.openAllOrders();
        dashBoardPage.clickPending();
        waitForPageToLoad("home");
        String mid = dashBoardPage.getMidBySubOrder(subOrderId);

        if (category.equalsIgnoreCase("exchange")) {

            dashBoardPage.clickExchange();
            midPage.clickMidHistoryEX();

        } else {
            dashBoardPage.clickCertified();
            midPage.clickMidHistoyCe();
        }

        try {
            file = new File(QuikrXTestBase.class.getClassLoader().getResource("quikrXtestDocument.pdf").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        midPage.clickUpdateDispatch(mid);
        midPage.uploadFile(file);
        midPage.updateStatusMid();
        Alert alert = midPage.switchTo().alert();
        alert.dismiss();
    }

    public void pressEscape() {
        try {
            Thread.sleep(5000);
            Robot robo = new Robot();
            robo.keyPress(KeyEvent.VK_ESCAPE);
            robo.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (Exception e) {
        }
    }


    public String calulatePercentagePrice() {

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        String percentage = detailsPage.discountPercentage();
        String price = detailsPage.priceBeforeDisc();

        DecimalFormat decim = new DecimalFormat("0");
        float per = 100 - Float.parseFloat(percentage);
        float priceInt = Float.parseFloat(price);
        return decim.format(Math.round((priceInt * per) / 100));
    }

    /**
     * Method to place an order on cod
     * @return
     */
    public String placeOrder(String category){


        QuikrxNewCartPage quikrXCartPage = new QuikrxNewCartPage(driver);
        QuikrXCheckoutPage quikrXCheckoutPage = new QuikrXCheckoutPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrXApiBase api = new QuikrXApiBase();


            if (category.equalsIgnoreCase("certified")) {

                api.updateProductInventory("6", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",testData.get("item1"));
                openProductNewUi(testData.get("item1"),testData.get("CitySelect"));
                waitForPageToLoad(testData.get("item1"));
                detailsPage.enterPincode("560016");
                detailsPage.clickPincodeCheck();
                detailsPage.certiAddPrimaryToCart();
            }
            else {
                openProductNewUi(testData.get("item2"),testData.get("CitySelect"));
                waitForPageToLoad(testData.get("item2"));
                detailsPage.selectBrand(testData.get("phoneBrandName"));
                detailsPage.selectModel();
                detailsPage.enterPincode("560016");
                detailsPage.clickPincodeCheck();
                detailsPage.exchangeAddTocartPrim();
            }

        waitForPageToLoad("Cart");
        quikrXCartPage.clickPlaceOrder();
        waitForPageToLoad("checkout");
        totalPrice = quikrXCheckoutPage.getTotalPrice();
        itemPrice = quikrXCheckoutPage.getItemPrice();
        if(category.equalsIgnoreCase("exchange")) {
            oldPhonePrice = quikrXCheckoutPage.getExchangeprice();
            exchangeDiscount = quikrXCheckoutPage.getExchangeDiscount();
        }else
            exchangeDiscount ="0";
             try{
                 quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
             }catch (Exception e){
                 driver.navigate().refresh();
                 waitForPageToLoad("checkOut");
                 quikrXCheckoutPage.placeOrderViaCod(testData.get("fullName"), testData.get("pinCode"), testData.get("address"), testData.get("phoneNumber"));
             }

        return quikrXCheckoutPage.getOrderId();
    }

    public void validateQomaOrderDetails(String subOrderId, String status,String category){

        QuikrXQomaOrderPage qomaOrderPage = new QuikrXQomaOrderPage(driver);
        int oldPhone=0;

        if(oldPhonePrice != null && !oldPhonePrice.isEmpty()){
            oldPhone=Integer.parseInt(oldPhonePrice);
        }

        Assert.assertEquals(testData.get("loginEmail"),qomaOrderPage.getcutomerEmail(),"mismatch in email Id");
        Assert.assertEquals(testData.get("phoneNumber"),qomaOrderPage.getcutomerMob(),"mismatch in phone number");
        Assert.assertEquals(testData.get("fullName"), qomaOrderPage.getcutomerName(),"mismatch in customer name");
        validateOrderedDate(qomaOrderPage.getOrderedOn());
        Assert.assertEquals(totalPrice,qomaOrderPage.getTotalPrice(),"mismatch in total price");
//        int netAmount = Integer.parseInt(itemPrice)-oldPhone-Integer.parseInt(exchangeDiscount);
//        Assert.assertEquals(""+ netAmount,qomaOrderPage.getnetAmount(subOrderId),"mismatch in net amount");
        if(category.equalsIgnoreCase("exchange"))
            Assert.assertEquals(oldPhonePrice,qomaOrderPage.getExchangeDiscount(subOrderId));
        Assert.assertEquals(status, qomaOrderPage.getStatus(subOrderId),"mismatch in status");
    }

    public void methodToSetDisaptch(String subOrder,String category){

        HeaderPage header = new HeaderPage(driver);
        QuikrXSellerDashBoardPage sellerPage = new QuikrXSellerDashBoardPage(driver);

        navigatethirdparty(driver, dashBoardEnv);
        waitForPageToLoad("seller");

        if(getCurrentUrl().contains("login"))
            sellerPage.login(sellerUserName, sellerPassword);

        sellerPage.updateStatusPackSlip(subOrder);
        generateManifest("certified", subOrder);
        waitForPageToLoad(testData.get("succCode"));
        pressEscape();
        setDispatch(category,subOrder);
        goToHomePage(testData.get("CitySelect"));
        header.clickMyQuikrX();
    }

    public void validateOrderedDate(String orderedDate){

        SimpleDateFormat month = new SimpleDateFormat("MMM");
        SimpleDateFormat date = new SimpleDateFormat("d");
        Date todate = new Date();
        System.out.println(month.format(todate));
        System.out.println(date.format(todate));
        Assert.assertTrue(orderedDate.contains(month.format(todate)),"mismatch in ordered month");
        Assert.assertTrue(orderedDate.contains(date.format(todate)),"mismatch in ordered date");

    }

    public void openNewTab(){

        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
    }

    public void setStatusForReplace(String owner, String seller){

        QuikrXQomaReplacementPage qomaReplacementPage = new QuikrXQomaReplacementPage(driver);

        qomaReplacementPage.selectOwner(owner);
        qomaReplacementPage.selectSeller(seller);
        qomaReplacementPage.submitNewRepOrder();
    }

    public void enterPinCode(){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        detailsPage.enterPincode(testData.get("validPinCode"));
        detailsPage.clickPincodeCheck();
    }

    public String calulateDiscountPercentage(double percentage,double price){

        DecimalFormat decim = new DecimalFormat("0.00");
        return decim.format((percentage/100)*price);
    }

    public void clearCart(){
        QuikrxNewCartPage cartPage = new QuikrxNewCartPage(driver);

        List<WebElement> lis = cartPage.itemsInCart();
        List<String> itemVal = new LinkedList<>();
        try {
            for (int index = 0; index <= lis.size() - 1; index++) {
                itemVal.add(lis.get(index).getAttribute("id").split("cartItem_")[1]);
            }
            for (int index = 0; index <= itemVal.size() - 1; index++) {
                cartPage.clickRemoveitem(itemVal.get(index));
            }
        }catch (Exception e){

        }

     }

    /**
     * check for new email
     * @param emailId
     * @param waitTime
     * @param emailTitle
     */
    public void checkNewEmail(String emailId,int waitTime,String emailTitle){

        RoundCubeEmailPage emailPage = new RoundCubeEmailPage(driver);
        System.out.println("Checking new email from roundCube");
        emailPage.openRoundCube();
        emailPage.enterEmail(emailId);
        emailPage.enterPassword("password");
        emailPage.submitLogin();
        emailPage.waitForPageToLoad("INBOX");
        emailPage.waitForMail(waitTime,emailTitle);
        System.out.println("new email found");

    }


    /**
     * Start Recording screen, save to ../Documents
     * @throws Exception
     */
    public void startRecording()
    {
        File file = new File("/home/quikr/Documents");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0,0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        try {
        this.screenRecorder = new ScreenRecordingVideoUtil(gc, captureSize,
                new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, FormatKeys.MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, file, "MyVideo");

            this.screenRecorder.start();
        }catch (Exception e){

        }

    }

    /**
     * Stop recording screen
     * @throws Exception
     */
    public void stopRecording()
    {
        try {
            this.screenRecorder.stop();
        }catch (Exception e){

        }

    }


}