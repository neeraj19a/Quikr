package com.quikr.app.android.quikrX;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Swatantra singh on 1/9/15.
 */
public class QuikrXPage extends AppAndroidPageBase {


    public QuikrXPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_QuikrX_DOM_FILE");

    /**
     * Check if QuikrX button is present on home screen
     * @return
     */

    public boolean validateQuikrXbuttonOnHomepage()
    {
        if (isElementPresent("quikrX"))
            return true;
        else
            return false;
    }

    /**
     * click on QuikrX GO button on home Screen to open QuikrX HomeScreen
     */
    public void clickonQuikrXOnHomePage()
    {
        Mapper.waitForElementToBeVisible(domFile, "quikrX");
        Mapper.find(domFile,"quikrX").click();
    }

    /**
     * on clicking on QuikrX text or Go button user should be redirected to quikrX home Screen
     * @return
     */
    public boolean validateOnClickingQuikrXUserreditectedToQuikrXScreen()
    {

        if (isElementPresent("QuikrXhomePage"))
            return true;
        else
            return false;
    }

    /**
     * verify if Exchange & Certified options appear on QuikrX homeScreen
     * @return
     */
    public boolean validateExchangeAndCertifiedOptionOnQuikrXpage()
    {
        Mapper.waitForElementToBeVisible(domFile, "QuikrXexchange");
        Mapper.scroll("Certified");
        return  (isElementPresent("QuikrXexchange") && isElementPresent("BuyCertified"));

    }

    public  void clickonExchangePhone()
    {
        Mapper.waitForElementToBeVisible(domFile, "QuikrXexchange");
        Mapper.find(domFile,"QuikrXexchange").click();
    }


    /**
     * validate on clicking on Exchange & BUY button ...usr rediredted to Snb where select new phones lable appear
     * @return
     */
    public String validateOnclickingExchangeANDSelectNewPhonesAppear()
    {
        Mapper.waitForElementToBeVisible(domFile,"selectNewPhones");
        String LabelText=Mapper.find(domFile,"selectNewPhones").getText().toString();
        return LabelText;
    }

    /**
     * Extract the header text to validate test cases on QuikrX
     * @return
     */
    public String GetQuikrXheaderText()
    {
        Mapper.waitForElementToBeVisible(domFile,"QuikrXSnbHeader");
        String text=Mapper.find(domFile,"QuikrXSnbHeader").getText().toString();
        return text;

    }

    public  void clickonCertifiedPhone()
    {

        Mapper.waitForElementToBeVisible(domFile,"QuikrXexchange");
        Mapper.scroll("Certified");
        Mapper.find(domFile,"BuyCertified").click();
    }

    /**
     * click on spinner icon to switch between Exchange phones & Buy Certified phones pages
     */
    public void ClickOnSpinnerToSwitchBetweenPages()
    {
        Mapper.waitForElementToBeVisible(domFile, "spinner");
        Mapper.find(domFile,"spinner").click();
    }

    /**
     * select exchanged phones after clicking on spinner
     */
    public  void selectExchangePhonesFromSpinner()
    {
        Mapper.waitForElementToBeVisible(domFile,"ExchangeOldSpinner");
        Mapper.find(domFile,"ExchangeOldSpinner").click();

    }
    /**
     * select Buy certified phones after clicking on spinner
     */
    public  void selectBuyCertifiedPhonesFromSpinner()
    {
        Mapper.find(domFile,"BuyCertifiedSpinner").click();
    }

    /**
     * clcik on cart icon to view cart
     */
    public void clickOnCart()
    {
        Mapper.waitForElementToBeVisible(domFile, "cart");
        Mapper.find(domFile,"cart").click();
    }

    /**
     * validate if cart iscon is present on snb page
     * @return
     */
    public boolean validateCartIconIsPresentOnSnb()
    {
        if (isElementPresent("cart"))
            return true;
        else
            return false;

    }

    /**
     * validate Sort Icon On SNB and Sort options on clicking Sort icon
     */
    public boolean validateSortAndSortOptions()
    {
        return  (isElementPresent("sorthighToLow")& isElementPresent("sortLowTohigh")&isElementPresent("sortPopular"));


    }

    /**
     * validate filter Icon On SNB and Filter options on clicking Filter icon
     */
    public boolean validateFilterAndFilterOptions()
    {
        if (isElementPresent("filter"))
            Mapper.find(domFile,"filter").click();
        if (isElementPresent("filterBrand"))
            return true;
        else
            return false;
    }

    /**
     * open ad from Snb page
     */

    public void OpenAdFromSNb(int i)
    {
        Mapper.waitForElementToBeVisible(domFile,"adTitle");
        Mapper.finds(domFile,"adTitle").get(i).click();
    }

    /**
     * validate pincode
     */
    public  void sendPinCode(String pin)
    {
        Mapper.waitForElementToBeVisible(domFile,"pinCode");
        Mapper.find(domFile, "pinCode").sendKeys(pin);

    }

    /**
     * validate VAPdetails()
     */
    public boolean validateVAPdetailsOnExchange()
    {
        if (isElementPresent("vapPrice") & isElementPresent("pinCode")  & isElementPresent("cart"))
            return true;
        else
            return false;
    }

    /**
     * after user enters proper pin exchange phone should appear
     * @return
     */
    public boolean ValidateExchangePhoneBrandOnVAP()
    {
        if(isElementPresent("QuikrXEcxhangeold"))
            return true;
        else
            return false;
    }

    /**
     * validate QuikrX certified phone Vap Details NOn-clickable
     * @return
     */
    public boolean validateVAPdetailsOnCertifiedPhones()
    {
        if (isElementPresent("quikrCertifiedFreeDelivery") & isElementPresent("quikrCertifiedReplecement")  & isElementPresent("QuikkrXCertifiedTrusted"))
            return true;
        else
            return false;
    }
    /**
     * after user enters proper pin certified phone details should appear
     * @return
     */
    public boolean ValidateCertifiedPhoneDetailsFieldVAP()
    {
        if (isElementPresent("uikrXdetailtvCertifiedUsedPhones"))
            return true;
        else
            return false;
    }

    /**
     * select brand of the phone User Wants to exchange
     */
    public void selectBrand()
    {
        Mapper.waitForElementToBeVisible(domFile, "ExchangeBrandName");
        Mapper.find(domFile,"QuikrXEcxhangeold").click();
    }

    /**
     * selectModel of the phone user wants to exchange
     */
    public void selectModel()
    {
        Mapper.waitForElementToBeVisible(domFile,"Model");
        Mapper.find(domFile,"Model").click();

    }
    /**
     * click on Buy Now option
     */
    public  void clickOnBuyNow()
    {
        Mapper.find(domFile,"BuyNow").click();
    }

    /**
     * provide login Email
     * @param email
     */
    public void loginEmail(String email)
    {
        Mapper.waitForElementToBeVisible(domFile,"QuikrXloginEmail");
        Mapper.find(domFile,"QuikrXloginEmail").sendKeys(email);
    }

    /**
     * provide Password
     * @param password
     */
    public void loginPassword(String password)
    {
        Mapper.waitForElementToBeVisible(domFile,"QuikrXloginPassword");
        Mapper.find(domFile,"QuikrXloginPassword").sendKeys(password);
    }
    /**
     * submit login credentials
     */
    public void clickonContinueOnLogin()
    {
        if (!isElementPresent("SubmitLoginInfo"))
            navigateBack();
        Mapper.find(domFile,"SubmitLoginInfo").click();

    }

    /**
     * provide reciver name
     */
    public void provideDeliveryName(String name)
    {
        Mapper.find(domFile,"name").sendKeys(name);
    }

    /**
     * provide delivery address pin
     * @param pin
     */
    public void provideDeliveryPin(String pin)
    {
        Mapper.find(domFile,"cartPin").sendKeys(pin);
    }

    /**
     * provide delivery address
     * @param address
     */
    public void provideDeliveryAddress(String address)
    {
        if (!isElementPresent("address"))
            navigateBack();
        Mapper.find(domFile,"address").sendKeys(address);
    }

    /**
     * provide delivery phone number
     * @param phone
     */
    public void provideDeliverycontact(String phone)
    {
        if (!isElementPresent("phone"))
            navigateBack();
        Mapper.find(domFile,"phone").sendKeys(phone);
    }

    /**
     * click on continue to redirect to payment page
     */
    public void ClickonContinueToPayment()
    {
        if (!isElementPresent("ContinueOrder"))
            navigateBack();
        Mapper.find(domFile,"ContinueOrder").click();
    }
    /**
     * verify that after user selects delivery address user is redirected to payment page
     */
    public boolean paymentPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "placeOrder");
        if (isElementPresent("placeOrder"))
            return true;
        else
            return false;
    }
    /**
     * click on place order
     */
    public void clickOnPlaceOrder()
    {
        Mapper.waitForElementToBeVisible(domFile,"placeOrder");
        Mapper.find(domFile,"placeOrder").click();
    }
    /**
     * login as existing user
     */

    public void clickOnIhaveQuikrAccount()
    {
        Mapper.find(domFile,"existingUser").click();
    }
    /**
     * validate login screen
     */
    public boolean validateLoginScreen()
    {
        if (isElementPresent("existingUser")&isElementPresent("QuikrXloginEmail")&isElementPresent("SubmitLoginInfo"))
            return true;
        else
            return false;
    }
    /**
     * validate delivery screen
     */
    public boolean validateDeliveryScreen()
    {
        if (isElementPresent("name")&isElementPresent("cartPin")&isElementPresent("address")&isElementPresent("phone"))
            return true;
        else
            return false;
    }
    /**
     * validate Success order page
     */
    public boolean validateSuccessOrderPage()
    {
        if (isElementPresent("validateorderSuccess"))
            return true;
        else
            return false;
    }
    /**
     * chek for unboxed phones
     */
    public boolean validateUnboxedPhones()
    {
        return isElementPresent("unboxed");
    }
    /**
     * open unboxed device ad
     */
    public void openUnboxedDeviceAd(int i)
    {
        Mapper.waitForElementToBeVisible(domFile,"unboxed");
        Mapper.finds(domFile,"unboxed").get(i).click();
    }

    /**
     * count number of Unboxed ads displayed on SNB
     * @return
     */
    public int CountNumberofAds() {
        Mapper.waitForElementToBeVisible(domFile, "unboxed");
        List<WebElement> count = Mapper.finds(domFile, "unboxed");
        List<String> list = new ArrayList<String>();
        for (WebElement e : count)
        {
            list.add(e.getText());
        }
        int Adcount=list.size();
        System.out.println(Adcount);
        return Adcount;
    }
    /**
     * validate vap unboxed Ad title
     */
    public String VapAdTitle()
    {
        String UnboxedTitle=Mapper.find(domFile,"VpAdtitle").getText();
        return UnboxedTitle;
    }

    /**
     * cart ad title
     * @param i
     * @return
     */
    public String UNBoxedAdTitleOncart(int i)
    {
        Mapper.waitForElementToBeVisible(domFile,"cartAdTitle");
        String cartAdTitle=Mapper.finds(domFile,"cartAdTitle").get(i).getText();
        return cartAdTitle;
    }
    /**
     * wait for login page to load
     */
    public void waitForLoginPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"QuikrXloginEmail");

    }


    public boolean validateProtectMySmartphoneCheckbox()
    {
        return isElementPresent("protectMySmartphoneCheckbox");
    }

    public boolean checkProtectMySmartphoneCheckbox()
    {
        if(isElementPresent("protectMySmartphoneCheckbox"))
        { Mapper.find(domFile, "protectMySmartphoneCheckbox").click();
            return true;}
        return false;
    }

    public void clickOnCartIcon()
    {
        Mapper.waitForElementToBeVisible(domFile,"QuikrXloginEmail");
        Mapper.find(domFile, "cart").click();
    }

    public boolean isProductPresentInCart()
    {
        return isElementPresent("cartAdTitle");
    }

    public boolean isWarrantyPresentInCart()
    {
        return isElementPresent("warrantyText");
    }

    /**
     * validate ad title at order summary page
     */
    public String adTitleAtOrderPage()
    {
        String orderPageAdTitle = Mapper.find(domFile, "OrderPageAdTitle").getText();
        return orderPageAdTitle;
    }

    public boolean isTotalPriceOnVapVisible()
    {
        return isElementPresent("totalVapPrice");
    }

    public int getSmartphonePriceOnVap() throws Exception
    {
        String price = Mapper.find(domFile, "vapPrice").getText();
        String actualPrice = price.substring(2, price.length()).replace(",", "");
        int vapPrice =  Integer.parseInt(actualPrice);
        System.out.println("Product price on VAP:"+ vapPrice);
        return vapPrice;
    }

    public int getWarrantyPriceOnVap() throws Exception
    {
        String price = Mapper.find(domFile, "productWarrantyPriceOnVap").getText();
        String actualPrice = price.substring(2, price.length()).replace(",", "");
        int warrantyPrice =  Integer.parseInt(actualPrice);
        System.out.println("Warranty price on VAP:"+ warrantyPrice);
        return warrantyPrice;
    }

    public int getTotalPriceOnCart()
    {
        String price = Mapper.find(domFile, "totalPrice").getText();
        String actualPrice = price.substring(2, price.length()).replace(",", "");
        int totalPrice =  Integer.parseInt(actualPrice);
        System.out.println("Total price on cart:"+ totalPrice);
        return totalPrice;
    }

    public int getTotalPriceOnVap()
    {
        String price = Mapper.find(domFile, "totalVapPrice").getText();
        System.out.println(price);
        String actualPrice = price.substring(3,price.length()).replace(",", "");
        int totalPrice =  Integer.parseInt(actualPrice);
        System.out.println("Total price on vap:"+ totalPrice);
        return totalPrice;
    }

    public void hideOverlayLayout()
    {
        if (isElementPresent("snbOverlay"))
            navigateBack();
    }

    /**
     * open QuikrX from Mobiles And tablets
     */

    public void openQuikrXfromMobilesAndTabs()
    {
        Mapper.waitForElementToBeVisible(domFile,"quikrX");
        Mapper.find(domFile,"quikrX").click();
    }
    /**
     * validate Buy Without exchange is present as Banner msg in Mobiles & tabs
     */
    public boolean validateBuyWithoutExchangeInBannerMsgONMobileAndTabletsScreen()
    {
        Mapper.waitForElementToBeVisible(domFile,"quikrX");
        String bannerMsg= Mapper.find(domFile,"quikrX").getText();
        if (bannerMsg.contains("Buy without Exchange"))
            return true;
        else
            return false;
    }

    /**
     * validate Seller info is Displayed on Snb
     */
    public boolean validateSellerInfoOnSnb()
    {
        Mapper.waitForElementToBeVisible(domFile,"adTitle");
        List<WebElement> Adcount = Mapper.finds(domFile, "adTitle");
        List<String> list = new ArrayList<String>();
        for (WebElement e : Adcount)
        {
            list.add(e.getText());
        }
        int snbAdcount=list.size();
        System.out.println(snbAdcount);

        Mapper.waitForElementToBeVisible(domFile,"sellerSnb");
        List<WebElement> sellerCount = Mapper.finds(domFile, "sellerSnb");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : sellerCount)
        {
            list1.add(e.getText());
        }
        int snbSellerCount=list1.size();
        System.out.println(snbSellerCount);
        //total of 4 Ad titles and 3 seller info are are displayed at UI
        if ((snbAdcount-1)==snbSellerCount)
            return true;
        else
            return false;

    }
    /**
     * validate Buy Without Exchange Appears
     */
    public boolean validateBuyWithoutExchangeOnQuikrXCHP()
    {
        return isElementPresent("buyWithoutExchange");
    }

    /**
     * click on buy without Exchange
     */
    public  void clickOnBuyWithoutExchange()
    {

        Mapper.waitForElementToBeVisible(domFile, "buyWithoutExchange");
        Mapper.find(domFile,"buyWithoutExchange").click();
    }
    /**
     * validate is user is redirected to payemnt Page
     */
    public boolean userIsRedirectedToPaymentPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"paymentPAgeHeader");
        String paymentText=Mapper.find(domFile,"paymentPAgeHeader").getText();
        return (paymentText.equalsIgnoreCase("Payment Options"));
    }
    /**
     * open ad with more than one seller from Snb
     */
    public void openAdwithMultiSellerFromSnb()throws Exception
    {
        if (isElementPresent("sellerCountSnb"))
            Mapper.find(domFile,"sellerCountSnb").click();
        else
        {
            swipeVertically("filter","sort");
            if (!isElementPresent("sellerCountSnb"))
                System.out.println("no matching Ads with Multiple Seller");
            else
                Mapper.find(domFile,"sellerCountSnb").click();
        }

    }
    /**
     * select seller from multiple seller on vap
     */
    public void selectSellerBuyWithoutExchange()throws Exception
    {
        Mapper.waitForElementToBeVisible(domFile,"pinCode");
        swipeVertically("vapPrice","paymentPAgeHeader");
        Mapper.find(domFile,"sellerInfoVAp").click();
    }
    /**
     * click opn buy button from seller page
     */
    public void clickOnBuyButtonOnSellerPAge()
    {
        Mapper.waitForElementToBeVisible(domFile, "buyFromMultiSeller");
        Mapper.find(domFile,"buyFromMultiSeller").click();
    }
    public void selectSellerForCertifiedPhones()throws Exception
    {
        Mapper.waitForElementToBeVisible(domFile,"pinCode");
        swipeVertically("vapPrice", "paymentPAgeHeader");
        explicitWait(2);
        swipeVertically("warrantyText", "paymentPAgeHeader");
        Mapper.find(domFile,"sellerInfoVAp").click();
    }

}

