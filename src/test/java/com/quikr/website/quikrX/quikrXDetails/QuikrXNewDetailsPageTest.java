package com.quikr.website.quikrX.quikrXDetails;

import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXCartPage.QuikrxNewCartPage;
import com.quikr.website.quikrX.quikrXLandingPage.QuikrXLandingPage;
import com.quikr.website.quikrX.quikrXNewDetailsPage.QuikrXNewDetailsPage;
import com.quikr.website.quikrX.quikrXNewListingPage.QuikrXNewListingPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by francis.s@quikr on 4/12/15.
 */
public class QuikrXNewDetailsPageTest extends QuikrXTestBase {


    private final static Log LOGGER = LogFactory.getLog(QuikrXNewDetailsPageTest.class.getName());
    String city = testData.get("newUiCity");

    @BeforeMethod(alwaysRun = true)
    public void openItem(){

        HomePage homePage = new HomePage(driver);
        QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);

        waitForPageToLoad("quikr");
        homePage.selectCity(city);
        waitForPageToLoad(city);
        homePage.clickNewQuikrX();
        waitForPageToLoad("quikrx");

        landingPage.clickSeeAllrefurbishedPhone();
        waitForPageToLoad("Refurbished-Mobile-Phones");

    }


    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = {"QuikrXHighPrio"})
    public void validatePageUi(String category, String itemId){


        LOGGER.info("validating new details page ui elements");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);
        Assert.assertNotNull(detailsPage.getItemName(),"name filed empty");
        Assert.assertNotNull(detailsPage.priceAfterDisc(),"price field empty");

        detailsPage.enterPincode(testData.get("validPinCode"));
        detailsPage.clickPincodeCheck();
        if(category.equalsIgnoreCase("certified"))
        {
//            Assert.assertTrue(detailsPage.quikrCertifiedPresent(), "quikr certified is not disaplayed");
        }else
        {
            detailsPage.selectBrand(testData.get("phoneBrandName"));;
            detailsPage.selectModel(testData.get("phoneModelName2"));

        }
//        Assert.assertTrue(detailsPage.recommendedDivDisplayed(), "recommended section not disaplyed");
        Assert.assertTrue(detailsPage.techSpecDiv(), "tech spec is not displayed");
    }

    @Test
    public void validateDiscount() {


        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        listingPage.getDicountItem();
        waitForPageToLoad("ProdId");

        Assert.assertNotNull(detailsPage.priceBeforeDisc(),"item price field is empty");
        Assert.assertNotNull(detailsPage.discountPercentage(),"dicount precentage is missing");

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class, groups = {"QuikrXHighPrio"})
    public void pinCodeTest(String category, String itemId){

        LOGGER.info("validating pincode on details page");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);
        detailsPage.enterPincode(testData.get("validPinCode"));
        detailsPage.clickPincodeCheck();
        Assert.assertEquals(detailsPage.getPincodeMessage(),testData.get("pincodeSuccess"));
        detailsPage.enterPincode(testData.get("pincodeNoDelivery"));
        detailsPage.clickPincodeCheck();
        Assert.assertEquals(detailsPage.getPincodeMessage(),testData.get("pincodeNA"));
        detailsPage.enterPincode(testData.get("invalidPinCode"));
        detailsPage.clickPincodeCheck();
        Assert.assertEquals(detailsPage.getPincodeMessage(),testData.get("invalidPincodeMsg"));
    }


    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class)
    public void callMailUsTest(String category, String itemId){

        LOGGER.info("validating pincode on details page");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi(itemId,city);
        waitForPageToLoad(itemId);
        if(category.equalsIgnoreCase("exchange")) {
            Assert.assertEquals(detailsPage.getMailUsText(),testData.get("mailUsExc"),"exchange email incorrect");
        }else{
            Assert.assertEquals(detailsPage.getMailUsText(),testData.get("mailUsCerti"),"certified email incorrect");
        }
        Assert.assertEquals(detailsPage.getCallUsText(), testData.get("callUsPhone"), "call us phone number incorrect");

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class)
    public void moreSellerTest(String category, String itemId){

        LOGGER.info("validating pincode on details page");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);


        if (category.equalsIgnoreCase("exchange")) {
            listingPage.clickExchangeTab();
            waitForPageToLoad("Exchange-Mobile-Phones");
        }else{
            listingPage.clickClearFilters();
            listingPage.clickCertifiedTab();
        }

        listingPage.openItemWithMoreSeller();
        waitForPageToLoad("sellersList");
        Assert.assertNotNull(detailsPage.moreSellerDiv(),"more seller module missing");

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = "QuikrXHighPrio")
    public void addToCartWithOutPincode(String category, String itemId) {

        LOGGER.info("validating primary seller add to cart before pincode on details page");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi(itemId, city);
        waitForPageToLoad(itemId);
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.clickIDontHavePhone();
            detailsPage.exchangeAddTocartPrim();
        } else
            detailsPage.certiAddPrimaryToCart();
        String actual = detailsPage.getPincodeToolTip();
        Assert.assertEquals(actual, testData.get("pinCodeToolTip"), "pincode tool tip not displayed");

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = {"QuikrXHighPrio"})
    public void addToCartWithPincode(String category, String itemId) {

        LOGGER.info("validating primary seller add to cart after pincode on details page");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrxNewCartPage  cartPage = new QuikrxNewCartPage(driver);

        openProductNewUi(itemId, city);
        waitForPageToLoad(itemId);
        String expectedItemName = detailsPage.getItemName();
        detailsPage.enterPincode(testData.get("validPinCode"));
        detailsPage.clickPincodeCheck();
        if (category.equalsIgnoreCase("exchange")) {
            detailsPage.clickIDontHavePhone();
            detailsPage.exchangeAddTocartPrim();
        } else {
            detailsPage.certiAddPrimaryToCart();
        }
        String actualItemname = cartPage.getTitle(itemId).replace("- ...","");
        Assert.assertNotNull(expectedItemName.contains(actualItemname),"item missing in cart");

    }

    @Test(groups = {"QuikrXHighPrio"})
    public void exchangeAddToCart() {

        LOGGER.info("validating exchange funcationality details page");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi("983", city);
        waitForPageToLoad("983");
        detailsPage.enterPincode(testData.get("validPinCode"));
        detailsPage.clickPincodeCheck();
        detailsPage.selectBrand("HTC");
        detailsPage.selectModel("Desire C");
        int priceAfterDisc = Integer.parseInt(detailsPage.priceAfterDisc());
        int exchangePrice = Integer.parseInt(detailsPage.getExchangeItemPrice());
        int finalPrice = Integer.parseInt(detailsPage.getPriceAfterExchange());

        Assert.assertEquals(finalPrice,(priceAfterDisc - exchangePrice),"exchange price is not same as final price");

    }

    @Test
    public void oneRupeeScenario(){

        LOGGER.info("validating one rupee scenario details page");
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        navigatethirdparty(driver,getTestDataWithReplace(testData.get("quikrXhomeUrl"),city));
        waitForPageToLoad("QuikrX");
        listingPage.clickExchangeTab();
        listingPage.clickSortOptions("Low to High");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdId");
        detailsPage.enterPincode(testData.get("validPinCode"));
        detailsPage.clickPincodeCheck();
        detailsPage.selectBrand(testData.get("phoneBrandName"));
        detailsPage.selectModel(testData.get("phoneModelName3"));
        int finalPrice = Integer.parseInt(detailsPage.getPriceAfterExchange());

        Assert.assertEquals(finalPrice,1,"price is not 1 rupee");


    }


    //    #@Test
    public void graphicsForcertified(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        waitForPageToLoad("QuikrX");
        listingPage.clickClearFilters();
        waitForPageToLoad("Refurbished-Mobile-Phones");
        listingPage.clickCertifiedTab();
        waitForPageToLoad("Exchange-Mobile-Phones");
        listingPage.categoryFilter();
        listingPage.selectcategoryFilterValue("Unboxed");
        waitForPageToLoad("condition");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdId");
        Assert.assertTrue(detailsPage.graphicsCertified(),"certified graphics div  missing");
    }

    //    @Test
    public void graphicsForExchange(){

        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        waitForPageToLoad("QuikrX");
        listingPage.clickExchangeTab();
        listingPage.openFirstitem();
        waitForPageToLoad("ProdId");
        Assert.assertTrue(!detailsPage.graphicsCertified(),"certified graphics div  missing");
    }

    @Test(groups = "QuikrXHighPrio")
    public void pinCodeExchange(){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi("1218","pune");
        detailsPage.enterPincode("560016");
        detailsPage.clickPincodeCheck();
        Assert.assertTrue(detailsPage.isExchangePresent(), "pincode for non exchangable city no exchange present");
        detailsPage.enterPincode("641001");
        detailsPage.clickPincodeCheck();
        Assert.assertFalse(detailsPage.isExchangePresent(), "pincode for non exchangable city no exchange present");

    }

    @Test
    public void certifiedCheckUiListValidation(){

        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi("1503", city);
        Assert.assertTrue(detailsPage.isCertifiedBadgeDisplayed(),"certified badge not displayed");;
        Assert.assertTrue(detailsPage.graphicsCertified(),"new checkList not displayed");

    }
    @Test
    public void certifiedCheckLisToolTip(){

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi("1503", city);
        String actual = detailsPage.certifiedTootTip();
        Assert.assertEquals(actual,testData.get("checkListToolTip"),"tool tip text mismatch");

    }

    //TODO need to get item for less than 10
//    @Test
    public void checkListSeeMore(){

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi("1506", city);
        int checkListCount = detailsPage.getQualityCheckCount();
        if(checkListCount>10){
            Assert.assertTrue(detailsPage.seeMorePresent(),"see more link not present for more than 10");
        }
        detailsPage.clickSeeMore();
        List<WebElement> list = detailsPage.getSeeMoreAttribute();
        for(int count =0;count<=list.size()-1;count++){
            if(!list.get(count).getAttribute("style").equalsIgnoreCase("display: list-item;")){
                Assert.assertTrue(false,"not all checklist displayed");
            }
        }
    }

    //TODO need to get item for less than 10
//    @Test
    public void certifiedNoSeeMore(){

        HomePage homePage = new HomePage(driver);
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        openProductNewUi("1504", city);
        String actual = detailsPage.certifiedTootTip();
        Assert.assertFalse(detailsPage.seeMorePresent(),"see more is displayed for less than 10");

    }

    @Test(dataProvider = "quikrXItems", dataProviderClass = Data.class,groups = {"prodTest"})
    public void validateProdPageUi(String category, String temId){


        LOGGER.info("validating new details page ui elements");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        if(category.equalsIgnoreCase("exchange"))
            listingPage.clickExchangeTab();

        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("order=priceAsc");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdIdZ");
        String itemId = getCurrentUrl().split("ProdIdZ")[1].trim();
        LOGGER.info("item is used  "+ itemId );

        Assert.assertNotNull(detailsPage.getItemName(),"name filed empty");
        Assert.assertNotNull(detailsPage.priceAfterDisc(),"price field empty");

        detailsPage.enterPincode(testData.get("validPinCode"));
        detailsPage.clickPincodeCheck();
        if(category.equalsIgnoreCase("exchange"))
        {
            detailsPage.selectBrand(testData.get("phoneBrandName"));;
            detailsPage.selectModel();

        }

        Assert.assertTrue(detailsPage.techSpecDiv(), "tech spec is not displayed");
    }


    @Test
    public void exchangeProdAddToCart() {

        LOGGER.info("validating exchange funcationality details page");
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);
        QuikrXNewListingPage listingPage = new QuikrXNewListingPage(driver);

        listingPage.clickExchangeTab();
        waitForPageToLoad("Exchange-Mobile-Phones");

        listingPage.clickSortOptions("Low to High");
        waitForPageToLoad("order=priceAsc");
        listingPage.openFirstitem();
        waitForPageToLoad("ProdIdZ");
        String itemId = getCurrentUrl().split("ProdIdZ")[1].trim();
        LOGGER.info("item is used  "+ itemId );
        detailsPage.enterPincode(testData.get("validPinCode"));
        detailsPage.clickPincodeCheck();
        sleep(5000);
        detailsPage.selectBrand(testData.get("phoneBrandName"));;
        detailsPage.selectModel();
        sleep(5000);
        int priceAfterDisc = Integer.parseInt(detailsPage.priceAfterDisc());
        int exchangePrice = Integer.parseInt(detailsPage.getExchangeItemPrice());
        int finalPrice = Integer.parseInt(detailsPage.getPriceAfterExchange());
        Assert.assertEquals(finalPrice,1,"price is not 1 rupee");
        Assert.assertTrue((priceAfterDisc - exchangePrice)<2, "mismatch in exhange item price");

    }


}
