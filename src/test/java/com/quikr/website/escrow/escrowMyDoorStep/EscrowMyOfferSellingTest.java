package com.quikr.website.escrow.escrowMyDoorStep;

import com.quikr.website.escrow.EscrowTestBase;
import com.quikr.website.horizontal.header.HeaderPage;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by francis.s@quikr on 15/6/16.
 */
public class EscrowMyOfferSellingTest extends EscrowTestBase {


    @BeforeMethod
    public void setUp(){

        HomePage homePage = new HomePage(driver);
        HeaderPage header = new HeaderPage(driver);

        homePage.selectCity(testData.get("sellerCity"));
        waitForPageToLoad(testData.get("sellerCity"));
        header.clickSignIn(testData.get("sellerCity"));
        waitForPageToLoad("SignIn");
        //TODO change emails
        header.nonResponsiveLogin("myordersinteg@test.com", "password");
        waitForPageToLoad(testData.get("sellerCity"));
        header.clickMyDoorStepOffer();
        waitForPageToLoad("getSellersOfferForm");
    }

    @Title("Test total ads count")
    @Description("verify ads count match with disaplyed count string")
    @Test
    public void adsCountTest(){

        EscrowMyDoorStepPage myOfferPage = new EscrowMyDoorStepPage(driver);

        int offersPresent = myOfferPage.offersDivPresent();
        int offersCountDisplayed = myOfferPage.getTotalAdCountText();
        Assert.assertEquals(offersPresent,offersCountDisplayed,"mismatch in displayed count and present ads");

    }

    @Title("test viewOffer and edit link")
    @Description("view offer should not be displayed when edit link and vice versa")
    @Test
    public void validateViewOfferEditNowlink(){

        EscrowMyDoorStepPage myOfferPage = new EscrowMyDoorStepPage(driver);

        String adId = myOfferPage.getAdIdFromViewOffer();
        if(!adId.isEmpty()){
         Assert.assertNotNull(myOfferPage.offerReceivedCount(adId),"number of offer recieved not displayed");
        }else{
          Assert.assertTrue(myOfferPage.editNowlinkDisplayed(adId),"edit not link not displayed");
        }

        adId = myOfferPage.getAdIdFromEditLink();
        if(!adId.isEmpty()){
            Assert.assertTrue(myOfferPage.editNowlinkDisplayed(adId),"edit not link not displayed");
        }else{
            Assert.assertNotNull(myOfferPage.offerReceivedCount(adId),"number of offer received not displayed");
        }


    }


}
