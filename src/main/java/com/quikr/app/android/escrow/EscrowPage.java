package com.quikr.app.android.escrow;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra singh on 17/12/15.
 */
public class EscrowPage extends AppAndroidPageBase {
    public EscrowPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_ESCROW_DOM_FILE");

    /**
     * select more button on mobiles page
     */
    public void selectMoreButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"more");
        Mapper.find(domFile,"more").click();
    }

    public void selectFirstPopularProduct(){
        Mapper.waitForElementToBeVisible(domFile,"popularProductImage");
        Mapper.finds(domFile,"popularProductImage").get(1).click();
    }

    /**
     * click on make an offeer button from snb
     */
    public void clickOnMakeAnOfferOrChatFromSnb()
    {
        Mapper.waitForElementToBeVisible(domFile,"makeAnOfferORChat");
        Mapper.find(domFile,"makeAnOfferORChat").click();
    }
    /**
     * clcik on sort button on mobiles snb
     */
    public void clickOnSortButton()
    {
        Mapper.waitForElementToBeVisible(domFile, "sort");
        Mapper.find(domFile,"sort").click();
    }
    /**
     * send offer price on make an offer form
     */
    public void setOfferPrice(String price)
    {
        Mapper.waitForElementToBeVisible(domFile,"offerPrice");
        Mapper.find(domFile,"offerPrice").clear();
        Mapper.find(domFile,"offerPrice").sendKeys(price);
    }
    /**
     * clck on makeOfffer on offer page
     */
    public void clickOnMakeOfferOnOfferPage(){
        Mapper.waitForElementToBeVisible(domFile, "sendOffer");
        Mapper.find(domFile,"sendOffer").click();
    }
    /**
     * cancle making offer from offer page
     */
    public  void cancleOfferFromOfferPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "cancle");
        Mapper.find(domFile,"cancle").click();
    }
    /**
     * after clcik make offer provide email
     */
    public void provideEmail(String email)throws Exception
    {
        Thread.sleep(3000);
        Mapper.waitForElementToBeVisible(domFile, "makeAnofferEmail");
        Mapper.find(domFile,"makeAnofferEmail").click();
        Mapper.find(domFile,"makeAnofferEmail").sendKeys(email);
    }
    /**
     * after clcik make offer provide number
     */
    public  void setNumberAfterMakingOffer(String number)
    {
        Mapper.waitForElementToBeVisible(domFile, "MakeAnOfferMobileNumber");
        Mapper.find(domFile,"MakeAnOfferMobileNumber").clear();
        Mapper.find(domFile,"MakeAnOfferMobileNumber").sendKeys(number);
    }
    /**
     * submit personal info after making offer
     */
    public void submitPersonalInfoAfterMAkeAnOffer()
    {
        Mapper.waitForElementToBeVisible(domFile, "submitOffer");
        Mapper.find(domFile,"submitOffer").click();
//        WebElement cord=Mapper.find(domFile,"submitOffer");
//        int x=cord.getLocation().getX();
//        int y=cord.getLocation().getY();
//        System.out.println(x + "&" + y);
//        Mapper.tap(1, 886, 430, 2);
    }
    /**
     * validate offer hint message is present in chat box after make an offer
     */
    public  boolean validateHintMessageAfterMAkeAnOffer()
    {
        return isElementPresent("offerHintText");
    }
    /**
     * validate redirecting to makeAn Offer page
     */
    public boolean validateRdirectionToMakeAnOffer()
    {
        return isElementPresent("validateMakeAnOfferPage");
    }
    /**
     * clcik on make an offer
     */
    public void  clickOnMakeANOffer()
    {
        Mapper.waitForElementToBeVisible(domFile,"makeAnOfferSnb");
        Mapper.finds(domFile,"makeAnOfferSnb").get(0).click();
    }
    /**
     * validate make an offer ad contains listed price
     */
    public  boolean validateListedPriceIsPresent()
    {
        Mapper.waitForElementToBeVisible(domFile,"makeAnOfferListedPrice");
        String price=Mapper.find(domFile,"makeAnOfferListedPrice").getText();
        return (price.contains("Listed price"));
    }
    /**
     * clcick on make an offer From VAP
     */
    public void clickOnMakeAnOfferFromVap()
    {
        Mapper.waitForElementToBeVisible(domFile,"MakeAnOfferVAP");
        Mapper.find(domFile,"MakeAnOfferVAP").click();
    }
    /**
     * validate Make an offer button is present OnVAP
     */
    public boolean MakeAnOfferButtonIsPresentOnVAp()
    {
        return (isElementPresent("MakeAnOfferVAP")||isElementPresent("VApBuy"));

    }
    /**
     * clcik on terms and condition
     */
    public void clickOnTermsAndConditions()
    {
        if (!isElementPresent("termsAndConditions"))
            navigateBack();
        Mapper.find(domFile,"termsAndConditions").click();
    }
    /**
     * validate terms and condition button is not clickable
     */
    public  boolean validateTermsAndConditionButtonIsNotClickable()
    {
        return isElementPresent("termsAndConditions");
    }
    /**
     * click On Change City from MakeOfferPage
     */
    public void clickOnCityOnMAkeOfferPage()
    {
        if (!isElementPresent("MakeAnOfferSelectCity"))
            navigateBack();
        Mapper.find(domFile,"MakeAnOfferSelectCity").click();
    }
    public List CityFieldPopulated()
    {
        List<WebElement> priceList = Mapper.finds(domFile, "MakeAnOfferSelectCity");
        List<String> list = new ArrayList<String>();
        for (WebElement e : priceList) {
            list.add(e.getText());
        }
        System.out.println(list + "SIZE =" + list.size());
        return list;
    }
    /**
     * Make na offer button is present on chat
     */
    public boolean validateMakeAnofferButtonIsPresentOnChat()
    {
        return isElementPresent("makeAnOfferFromChat");
    }
    /**
     * click on make an offer on chat
     */
    public void clickOnMAkeAnOfferFromChat()
    {
        Mapper.waitForElementToBeVisible(domFile,"makeAnOfferFromChat");
        Mapper.find(domFile,"makeAnOfferFromChat").click();
    }
    /**
     * clcik on chat from vap
     *
     */
    public void clickOnChatFronVAp()
    {
        Mapper.waitForElementToBeVisible(domFile,"vapChat");
        Mapper.find(domFile,"vapChat").click();
    }

    public void makeOfferFromChat()
    {
        Mapper.waitForElementToBeVisible(domFile, "sendOfferFromChat");
        Mapper.find(domFile,"sendOfferFromChat").click();
    }
    /**
     * send offer price frorm cht
     */
    public void sendOfferPriceFromChat(String price)
    {
        Mapper.waitForElementToBeVisible(domFile,"chatOfferPrice");
        Mapper.find(domFile,"chatOfferPrice").clear();
        Mapper.find(domFile,"chatOfferPrice").sendKeys(price);
    }
    public void navigateBackToHOme()
    {
        Mapper.waitForElementToBeVisible(domFile, "navigteback");
        Mapper.find(domFile,"navigteback").click();
    }
    /**
     * extract low touch tesxt
     */
    public String getLowTouchText()
    {
        Mapper.scroll("Post Your Ad");
        String lowTouchText=Mapper.find(domFile,"LowTouchText").getText();
        return lowTouchText;
    }

    /**
     * select current on vap for escrow categories
     */
    public void selectCityAsCurrentCityOnVapEscrow()
    {

        if (isElementPresent("escrowVApCurrentCity"))
        {
            Mapper.find(domFile, "escrowVApCurrentCity").click();
            Mapper.find(domFile, "escrowFilterApply").click();
        }
//        else if (isElementPresent("snbOverlay"))
//        {
//            System.out.println("SNB OVERLAY NOT DISPLYED"+this.getClass().getSimpleName());
//            Mapper.find(domFile, "snbOverlay").click();
//        }
    }
    /**
     * fetch escrow vap ad title
     */
    public String escrowSnbTitle()
    {
        Mapper.waitForElementToBeVisible(domFile, "escrowSnbAdTitle");
        String snbAdTitle=Mapper.finds(domFile, "escrowSnbAdTitle").get(1).getText();
        return snbAdTitle;
    }
    /**
     * fetching cordinates for swaping on VAp
     */
    public List swipeCordinatesForVAp()
    {
        int Ycordinate=0;

        Mapper.waitForElementToBeVisible(domFile,"WscrowvapPrice");
        Ycordinate=Mapper.find(domFile, "WscrowvapPrice").getLocation().getY();

        int X1cordinate=Mapper.find(domFile,"vapChat").getLocation().getX();
        int X2cordinate=Mapper.find(domFile,"MakeAnOfferVAP").getLocation().getX();
        List<Integer> cordinates = new ArrayList<Integer>();
        cordinates.add(Ycordinate);
        cordinates.add(X1cordinate);
        cordinates.add(X2cordinate);
        return cordinates;

    }
    /**
     * fetch vap description
     */
    public String escrowAdDescription()
    {
        Mapper.waitForElementToBeVisible(domFile, "vapAdDescription");
        return (Mapper.find(domFile,"vapAdDescription").getText());
    }
    /**
     * fetch sort options
     */
    public List sortOPtions()
    {
        List<WebElement> sort=Mapper.finds(domFile, "sortDropDownOPtions");
        List<String>options=new ArrayList<>();
        for (WebElement e : sort) {
            options.add(e.getText());
        }
        return options;
    }
    /**
     * on snb page select sort button
     */
    public void clickOnSortButtonOnSnb()
    {

        Mapper.waitForElementToBeVisible(domFile,"snbSortAndAlert",10);
        Mapper.finds(domFile, "snbSortAndAlert").get(0).click();
    }
    /**
     * fetch locality from snb
     */
    public String SnbLocality()
    {
        Mapper.waitForElementToBeVisible(domFile, "snbLocality");
        return Mapper.find(domFile,"snbLocality").getText();
    }
    /**
     * select filters locality on snb
     */
    public void clickOnSelectLocalityFilter()
    {
        Mapper.waitForElementToBeVisible(domFile,"selecctLocalityFilter");
        Mapper.findAndReplace(domFile,"selecctLocalityFilter",new String[]{"Localities"}).click();
    }
    /**
     * select filter brand on snb
     */
    public void clickOnSelectBrandFilter()
    {
        Mapper.waitForElementToBeVisible(domFile, "filterLocalities");
        Mapper.finds(domFile,"filterLocalities").get(1).click();
    }
    /**
     * select mobiles subcat on Mobiles CHP
     */

    public void selectMobilesSubcat()
    {
        closeFullScreenInterstitialFromCategoryPage();
        Mapper.waitForElementToBeVisible(domFile,"selectSubCatMobiles",10);
        Mapper.find(domFile, "selectSubCatMobiles").click();
    }
    public void selectQuikrScannerTestedFilter(){
        Mapper.waitForElementToBeVisible(domFile,"quikrScannerTested");
        Mapper.finds(domFile,"quikrScannerTested").get(0).click();
    }
    public void applyFilter(){
        Mapper.find(domFile, "applyFilter").click();
    }

    /**
     * select filters  on snb
     */
    public void selectFiltersOnFilterPage(String text)
    {
        Mapper.waitForElementToBeVisible(domFile,"selecctLocalityFilter");
        Mapper.findAndReplace(domFile,"selecctLocalityFilter",new String[]{text}).click();

    }

    public void closeFullScreenInterstitialFromCategoryPage()
    {
        if(!isElementPresent("titlebar"))
            navigateBack();
    }
}
