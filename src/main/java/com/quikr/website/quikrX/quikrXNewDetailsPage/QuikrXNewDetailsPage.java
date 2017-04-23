package com.quikr.website.quikrX.quikrXNewDetailsPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by francis.s@quikr on 20/11/15.
 */
public class QuikrXNewDetailsPage extends PageBase {

    private static final String domFile = getProperties().get("QUIKRX_NEW_DETAILS_DOM_FILE");

    public QuikrXNewDetailsPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }


    public String getItemName(){
        return Mapper.find(domFile,"itemName").getText().trim();
    }

    public String priceAfterDisc(){
        return Mapper.find(domFile,"itemPrice").getText().replace("₹","").replace(",","").trim();
    }

    public String discountPercentage(){
        return Mapper.find(domFile,"discPercentage").getText().replace("Save","").replace("%","").trim();
    }

    public String priceBeforeDisc(){
        return Mapper.find(domFile,"priceBeforeDisc").getText().replace("₹","").replace(",","").trim();
    }

    public boolean ewDisplayed(){
       return Mapper.find(domFile,"extendedWarrantyDiv").isDisplayed();
    }

    public void enterPincode(String pincode){
        executeScript("document.getElementById('pincodeInput').setAttribute('value', '"+pincode+"')");
    }

    public void clickPincodeCheck(){
        executeScript("document.getElementById('pincodeBtn').click()");
        Mapper.waitForElementToBeVisible(domFile,"pinocodeMessage");
    }

    public String getPincodeMessage(){
        Mapper.waitForElementToBeVisible(domFile,"pinocodeMessage");
        return Mapper.find(domFile,"pinocodeMessage").getText().trim();
    }



    public void selectEw(){
        Mapper.find(domFile,"selectEctendedWaranty").click();
    }

    public boolean quikrCertifiedPresent(){
        return Mapper.find(domFile,"quikrCertifiedNote").isDisplayed();
    }

    public void addToCart(String sellerId){

        Mapper.findAndReplace(domFile,"addToCart",new String[]{sellerId}).click();
    }

    public String unboaxedMeansString(){
        return Mapper.find(domFile,"unboxedMeans").getText().trim();
    }

    public String getCallUsText(){
        Mapper.getActionObject().moveToElement(Mapper.find(domFile,"callUs")).click().build().perform();
        return Mapper.find(domFile,"callUsText").getText();
    }

    public String getMailUsText(){
        Mapper.getActionObject().moveToElement(Mapper.find(domFile,"mailUs")).click().build().perform();
        return Mapper.find(domFile,"mailUsText").getText();
    }

    public boolean recommendedDivDisplayed(){
        return Mapper.find(domFile,"reccommendedDiv").isDisplayed();
    }

    public boolean techSpecDiv(){
        return Mapper.find(domFile,"techSpecsDiv").isDisplayed();
    }

    public boolean moreSellerDiv(){
        return Mapper.find(domFile,"moreSellerDiv").isDisplayed();
    }

    public void selectBrand(String brand){
        Mapper.find(domFile,"body").sendKeys(Keys.HOME);
        Mapper.find(domFile,"brandDropDownHolder").click();
        Mapper.findAndReplace(domFile,"brandDropDown",new String[]{brand}).click();
    }

    public void selectModel(String model){
        Mapper.find(domFile,"body").sendKeys(Keys.HOME);
        Mapper.find(domFile,"modelDropDownHolder").click();
        Mapper.findAndReplace(domFile,"modelDropDown",new String[]{model}).click();
    }

    public void selectModel(){
        Mapper.find(domFile,"body").sendKeys(Keys.HOME);
        Mapper.find(domFile,"modelDropDownHolder").click();
        Mapper.find(domFile,"firstModel").click();
    }

    public void certiAddPrimaryToCart(){
        Mapper.find(domFile,"primaryAddToCartButton").click();
    }

    public String getPincodeToolTip(){
        Mapper.waitForElementToBeVisible(domFile,"pincodeToolTip");
        return Mapper.find(domFile,"pincodeToolTip").getText().trim();
    }

    public void exchangeAddTocartPrim(){
        Mapper.find(domFile,"exchangePrimaryCartButton").click();
    }

    public void clickIDontHavePhone(){
        Mapper.find(domFile,"iDontHavePhoneToExchange").click();
    }

    public String getExchangeItemPrice(){
       return Mapper.find(domFile,"exchangeItemPrice").getText().replace(",","").trim();
    }

    public String getPriceAfterExchange(){
        return Mapper.find(domFile,"priceAfterExchange").getText().replace(",","").trim();
    }

    public boolean graphicsCertified(){
        try {
            return Mapper.find(domFile,"certifiedGraphicsDiv").isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isExchangePresent(){
        try {
            return Mapper.find(domFile,"exchangeDiv").isDisplayed();
        }catch (Exception e){
            return false;
        }

    }

    public String certifiedTootTip(){
        Mapper.getActionObject().moveToElement(Mapper.find(domFile,"certifiedTextLink")).build().perform();
        return Mapper.find(domFile,"certifiedToolTip").getText();
    }

    public int getQualityCheckCount(){
        List<WebElement> checkList = Mapper.finds(domFile,"qualityCheckList");
        return checkList.size();
    }

    public boolean isCertifiedBadgeDisplayed(){
        return Mapper.find(domFile,"certifiedImg").isDisplayed();
    }

    public boolean seeMorePresent(){
        return Mapper.find(domFile,"seeMoreLink").isDisplayed();
    }

    public void clickSeeMore(){
        Mapper.find(domFile,"seeMoreLink").click();
    }

    public List<WebElement> getSeeMoreAttribute(){
        List<WebElement> checkList = Mapper.finds(domFile,"qualityCheckList");
        return checkList;
    }

    public String getSellerCount(){

        try {
            return Mapper.find(domFile,"sellerCount").getText().split("by")[1].split("more")[0].trim();
        }catch (Exception e){
            return "0";
        }
    }

    public boolean isItemOos(){
       return Mapper.find(domFile,"itemoos").isDisplayed();
    }


    public void clickGoodCondition(){
        Mapper.find(domFile,"goodRadioButton").click();
    }

    public void clickBadCondition(){
        Mapper.find(domFile,"badRadioButton").click();
    }

    public void clickInHand(){
        Mapper.find(domFile,"yesInHand").click();
    }

    public void clickNotInhand(){
        Mapper.find(domFile,"noInHand").click();
    }

    public boolean syncAndScanDisaplyed(){
      return   Mapper.find(domFile,"syncAndScan").isDisplayed();
    }

    public String exchangeError(){
        return Mapper.find(domFile,"exchangeError").getText().trim();
    }

}