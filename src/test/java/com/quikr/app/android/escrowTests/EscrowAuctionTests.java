package com.quikr.app.android.escrowTests;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.escrow.EscrowPage;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by gurinder.singh on 28/7/16.
 */
public class EscrowAuctionTests   extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_ESCROW_TESTDATA_FILE"));

    @Title("All Auction tests on SnB")
    @Description("1. Verify the Bid Now Form Should open on SnB\n" +
            "2. Verify the Bid should be successfull\n" +
            "3. Verify Bid amount increases everytime a new bid is placed according to 2%\n" +
            "4. Verify last bid amount is updated\n" +
            "5. Verify bid amount is populated ")
    @Test(groups = {"Prod"},dataProvider = "AuctionCities",dataProviderClass = Data.class)
    public void AuctionsSnBTests(String AuctionCities)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        int LastBidAmount;
        int PopulatedAmount;

        hompage.selectCity();
        headerPage.searchCity(AuctionCities);
        hompage.selectelementWithoutScroll(AuctionCities, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.scrollToBidNowAd();
        snbPage.tapBidNow();
        Assert.assertTrue(snbPage.ifBidNowFormOpen(),"Bid Now Form Open");
        snbPage.enterBidMobileNumber(testData.get("buyerMobile"));
        snbPage.tapBidNow();
        snbPage.tapBidNow();
        //verify amount populates
        Assert.assertTrue(snbPage.ifAmountPopulates(),"Bid amount populates");
        //save bid amount populated to check increase post bid placement
        LastBidAmount= snbPage.getLastBidAmount();
        //save last bid amount to check if its replaced with new bid placed/ if updated post bid
        PopulatedAmount=snbPage.getPopulatedAmount();
        Assert.assertTrue(snbPage.ifBidAmountIncreasesOnEveryBid(LastBidAmount,PopulatedAmount),"Bid Amount Changes to new bid amount price");
    }

    @Title("All Auction tests on SnB")
    @Description("1. Verify the Bid Now Form Should open on VAP\n" +
            "2. Verify the Bid should be successfull\n" +
            "3. Verify Bid amount increases everytime a new bid is placed according to 2%\n" +
            "4. Verify last bid amount is updated\n" +
            "5. Verify bid amount is populated ")
    @Test(groups = {"Prod"},dataProvider = "AuctionCities",dataProviderClass = Data.class)
    public void AuctionsVAPTests(String AuctionCities)
    {
        Hompage hompage = new Hompage(driver);
        EscrowPage escrowPage = new EscrowPage(driver);
        SnbPage snbPage = new SnbPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        int LastBidAmount;
        int PopulatedAmount;

        hompage.selectCity();
        headerPage.searchCity(AuctionCities);
        hompage.selectelementWithoutScroll(AuctionCities, QuikrAppEnums.Hompage_SelectCity);
        hompage.selectelementWithoutScroll(testData.get("mobilesAndTAbs"), QuikrAppEnums.Home_Categories);
        escrowPage.selectMobilesSubcat();
        snbPage.hideOverlayLayout();
        escrowPage.selectCityAsCurrentCityOnVapEscrow();
        snbPage.scrollToBidNowAd();
        snbPage.selectAuctionAd();
        snbPage.tapBidNow();
        Assert.assertTrue(snbPage.ifBidNowFormOpen(),"Bid Now Form Open");
        snbPage.enterBidMobileNumber(testData.get("buyerMobile"));
        snbPage.tapBidNow();
        snbPage.tapBidNow();
        //verify amount populates
        Assert.assertTrue(snbPage.ifAmountPopulates(),"Bid amount populates");
        //save bid amount populated to check increase post bid placement
        LastBidAmount= snbPage.getLastBidAmount();
        //save last bid amount to check if its replaced with new bid placed/ if updated post bid
        PopulatedAmount=snbPage.getPopulatedAmount();
        Assert.assertTrue(snbPage.ifBidAmountIncreasesOnEveryBid(LastBidAmount,PopulatedAmount),"Bid Amount Changes to new bid amount price");
    }

}
