package com.quikr.website.quikrX.quikrXSTQ;

import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.home.HomePage;
import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXStqPages.QuikrGuaranteedPurchasePage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by quikr on 8/7/16.
 */
public class QuikrXGruaranteedPurchaseTests extends QuikrXTestBase {


    private final static Log LOGGER = LogFactory.getLog(QuikrXGruaranteedPurchaseTests.class.getName());
    String city = testData.get("newUiCity");

    @BeforeMethod(alwaysRun = true)
    public void openItem(){

        HomePage homePage = new HomePage(driver);

        homePage.selectCity(city);
        waitForPageToLoad(city);
        navigatethirdparty(driver,"http://"+city+".quikr.com/QuikrX/guaranteed-purchase/sell-through-quikr");
        waitForPageToLoad("sell-through-quikr");

    }


    @Test(dataProvider = "acceptedPhones", dataProviderClass = Data.class)
    @Title("Guaranteed purchase price")
    @Description("for accepted brand and model verify guaranteed purchase price is displayed")
    public void endToEndFlow(String brand,String model, String condition, String city){


        QuikrGuaranteedPurchasePage quikrGuaranteed = new QuikrGuaranteedPurchasePage(driver);

        quikrGuaranteed.selectBrand(brand);
        quikrGuaranteed.selectModel(model);
        quikrGuaranteed.selectCondition(condition);
        quikrGuaranteed.selectCity(city);
        quikrGuaranteed.clickGetPrice();
        Assert.assertNotNull(quikrGuaranteed.getPrice());

    }










}
