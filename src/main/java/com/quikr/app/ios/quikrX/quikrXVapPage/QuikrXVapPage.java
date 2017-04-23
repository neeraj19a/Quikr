package com.quikr.app.ios.quikrX.quikrXVapPage;

import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 15/10/15.
 */
public class QuikrXVapPage extends com.quikr.app.ios.AppiOSPageBase {

    public QuikrXVapPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_QUIKRXVAPPAGE_DOM_FILE");

    /**
     * function to set pin code
     */
    public void setPinCode(String str)
    {
       // Mapper.scroll("Verify");
        Mapper.find(domFile,"setPinCode").click();
      //  Mapper.waitForElementToBeVisible(domFile,"verify");
        Mapper.find(domFile,"setPinCode").sendKeys(str);

    }

    /**
     * function to click on verify button
     */
    public void clickOnVerifyButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"verify");
        Mapper.find(domFile,"verify").click();
    }

    /**
     * function to validate verify button on vap
     */
    public boolean validateVerifyButtonOnVapPageOfQuikrX()
    {
        Mapper.waitForElementToBeVisible(domFile,"verify");
        return isElementPresent("verify");
    }

    /**
     * function to validate cart option
     */
    public boolean validateCartOption()
    {
        return isElementPresent("cartButton");
    }

    /**
     * function to validate ad to cart option
     */
    public boolean validateAdToCartOption()
    {
        return isElementPresent("adToCartButton");
    }

    /**
     * function to click on cart button
     */
    public void clickOnCartButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"cartButton");
        Mapper.find(domFile,"cartButton").click();
        Mapper.waitForElementToBeInvisible(domFile,"cartButton");
    }

    /**
     * function to click on cart button after added in cart
     */
    public void clickOnCartButtonAfterAdditionOfItem()
    {
        Mapper.waitForElementToBeVisible(domFile,"cartButtonAfterAddedItem");
        Mapper.finds(domFile,"cartButtonAfterAddedItem").get(1).click();
    }

    /**
     * function to click on back button
     */
    public void clickOnBackButtonOnVapPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"backButton");
        System.out.println(Mapper.find(domFile,"backButton").getText());
       // Mapper.finds(domFile,"backButton").get(0).click();
        Mapper.find(domFile,"backButton").click();
    }

    /**
     * function to click on select brand name on vap page
     */
    public void clickOnBrandNameOnVapPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"brandNameOnVapPage");
        Mapper.find(domFile,"brandNameOnVapPage").click();
    }

    /**
     * function to select brand name option on vap page
     */
    public void selectBrandNameOptionOnVapPage(String brandNameOptionOnVapPage)
    {
        Mapper.find(domFile,brandNameOptionOnVapPage).click();

    }

    /**
     * function click on select model
     */
    public void clickOnModelOnVapPage()
    {
        Mapper.scroll("selectModel");
        Mapper.find(domFile,"selectModel").click();
    }

    /**
     * function select model name
     */
    public void selectModelName(String modelNameOption)
    {
        Mapper.find(domFile,modelNameOption).click();
    }

    /**
     * function to validate price of new device and exchange value and you pay option on vap page of quikrX
     */
    public boolean validateQuikrXVapPageOptions()
    {
        Mapper.scroll("Specifications");
        System.out.println(Mapper.find(domFile, "specifications").getText());
        System.out.println(Mapper.find(domFile,"youPay").getText());
        return (isElementPresent("priceOfNewDevice") && isElementPresent("exchangeValue") && isElementPresent("youPay") && isElementPresent("specifications"));
    }

    /**
     * function  click on buy now button
     */
    public void clickOnBuyNowButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"buyNowButton");
        Mapper.find(domFile,"buyNowButton").click();
        Mapper.waitForElementToBeInvisible(domFile,"buyNowButton");
    }

    /**
     * function to validate buy now button
     */
    public boolean validateBuyNowButton()
    {
        return isElementPresent("buyNowButton");
    }

    /**
     * function to click on continue button on checkout page
     */
    public void selectContinueButtonOnCheckoutPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"continueButtonOnCheckoutPage");
        Mapper.find(domFile,"continueButtonOnCheckoutPage").click();
    }


    /**
     * function to set the email id in checkout page
     */
    public void setEmailOnCheckoutPage(String str)
    {
        Mapper.waitForElementToBeVisible(domFile,"setEmail");
//        Mapper.finds(domFile,"setEmail").get(0).clear();
//        System.out.println( Mapper.finds(domFile,"setEmail").get(0).getText());
//        Mapper.finds(domFile,"setEmail").get(0).sendKeys(str);
        Mapper.find(domFile,"setEmail").clear();
        Mapper.find(domFile,"setEmail").sendKeys(str);
    }

    /**
     * function to click on continue button on my cart page
     */
    public void selectContinueButtonOnMyCartPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"continueButtonOnMyCartPage");
        Mapper.find(domFile,"continueButtonOnMyCartPage").click();
    }

    /**
     * function to set on payment page
     */
    public void setNameOnCheckoutPage2(String str)
    {
        Mapper.find(domFile,"UserName").sendKeys(str);
    }

    /**
     * function to set pin code on payment page
     */
    public void setPinCodeOnCheckoutPage2(String str)
    {
        Mapper.find(domFile,"pinCode").sendKeys(str);
    }

    /**
     * function to set phone no on payment page
     */
    public void setPhoneNoOnCheckoutPage2(String str)
    {
        Mapper.find(domFile,"phoneNo").sendKeys(str);
    }

    /**
     * function to set address on payment page
     */
    public void setAddressOnCheckoutPage2(String str)
    {
        Mapper.find(domFile,"address").sendKeys(str);
    }

    /**
     * function to select continue button on payment page
     */
    public void selectContinueButtonOnPaymentPage()
    {
        Mapper.scroll("Continue");
        Mapper.find(domFile,"continueButtonOnPaymentPage").click();
    }

    /**
     * function to select place order  button on checkout page3
     */
    public void selectCheckoutPage3()
    {
        Mapper.find(domFile,"placeOrderButton").click();
    }

    /**
     * function to validate successfull msg after placing an order
     */
    public boolean validateMsgAfterPlacingAnOrder()
    {
        return (isElementPresent("successMsgAfterPlacingAnOrder") && isElementPresent("continueShoppingButton"));
    }

    /**
     * function to validate checkout page1
     */
    public boolean validateCheckoutPage1()
    {
        Mapper.waitForElementToBeVisible(domFile,"continueAsGuest");
        return (isElementPresent("continueAsGuest") && isElementPresent("iHaveAQuikrAccount")) ;
    }

    /**
     * function to validate checkout page2
     */
    public boolean validateCheckoutPage2()
    {
        return  (isElementPresent("UserName")  && isElementPresent("pinCode") && isElementPresent("phoneNo") && isElementPresent("address"));
    }

    /**
     * function to click on back button on checkout page
     */
    public void clickOnBackButtonOncheckoutPage()
    {
        Mapper.find(domFile,"backButtonOnCheckoutPage").click();
    }

    /**
     * function to validate toast msg if brand name is not selected
     */
    public boolean validateToastMsgForBrandNameNotSelected()
    {
        return isElementPresent("msgForForBrandNameNotSelected");
    }

    /**
     * function to validate toast msg if model name not selected
     */
    public boolean validateToastMsgForModelNameNotSelected()
    {
        return isElementPresent("msgForForModelNameNotSelected");
    }

    /**
     * function to click on term and condition on vap page for quikrX
     */
    public void clickOnTermAndCondition()
    {
        Mapper.find(domFile,"clickOnTermAndConditionButton").click();
    }

    /**
     * function to validate term and condition on term & condition page
     */
    public boolean validateTermAndConditionPage()
    {
        Mapper.waitForElementToBeVisible(domFile,"termAndConditionText");
        return isElementPresent("termAndConditionText");
    }

    /**
     * function to validate specification option on quikrX vap page
     */
    public boolean validateSpecificationOptions()
    {
        Mapper.scroll("Specifications");
        return (isElementPresent("brandNameText") && isElementPresent("modelNameText") && isElementPresent("OperatingSystemText") && isElementPresent("noOfSimsText") && isElementPresent("screenSizeText") && isElementPresent("processorText")
                    && isElementPresent("formText") && isElementPresent("primaryCameraText") && isElementPresent("ramText"));
    }

    /**
     * function to click on I do not have phone to exchange button
     */
    public void clickOniDoNotHavePhoneToExchange()
    {
        Mapper.find(domFile,"clickOniDoNotHavePhoneToExchangeButton").click();
    }
}
