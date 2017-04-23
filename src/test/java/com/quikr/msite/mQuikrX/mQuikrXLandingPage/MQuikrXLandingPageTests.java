package com.quikr.msite.mQuikrX.mQuikrXLandingPage;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.website.quikrX.quikrXLandingPage.QuikrXLandingPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by quikr on 26/2/16.
 */
public class MQuikrXLandingPageTests extends MTestBase {

    private final static Log LOGGER = LogFactory.getLog(MQuikrXLandingPageTests.class.getName());

    @BeforeMethod
    public void preSetup(){

        MHomePage homePage = new MHomePage(driver);
        homePage.selectCity("Bangalore");
        homePage.clickQuikrX();
        waitForPageToLoad("QuikrX");

      }

    @Test
    public void quikrXLandingPageUiValidation() {

       QuikrXLandingPage landingPage = new QuikrXLandingPage(driver);

        String[] categoryList = {"refurbished", "unboxed", "new"};


        for (int count = 0; count <= categoryList.length - 1; count++) {

            LOGGER.info("validating UI for " + categoryList[count] + " landing page");
            if (categoryList[count].equalsIgnoreCase("refurbished")) {
                landingPage.clickRefurbishedMsiteTab();
                Assert.assertTrue(landingPage.refurbishedcheckListPresent(), "check list not displayed");
                Assert.assertNotNull(landingPage.refurbishedDeftext(), " refurbished description text mismatch");
            } else if (categoryList[count].equalsIgnoreCase("unboxed")) {
                landingPage.clickUnboxedMsiteTab();
                Assert.assertTrue(landingPage.unboxedCheckListPresent(), "unboxed checklist missing");
                Assert.assertNotNull(landingPage.unboxedDeftext(), " unboxed description text mismatch");
            } else {
                landingPage.clickNewMsiteTab();
                Assert.assertTrue(landingPage.exchangeCheckListPresent(), "exchange checklist missing");
                Assert.assertNotNull(landingPage.exchangeDeftext(), " exchange description text mismatch");
            }

//            Assert.assertTrue(landingPage.isAndroidDisplayed(), "all android phones not disaplyed");
            Assert.assertTrue(landingPage.shopByBrandDisplayed(), "shop by brand not displayed");
            Assert.assertTrue(landingPage.seeAllTrendingPresent(), "see all trending not displayed");
            Assert.assertTrue(landingPage.isfilterScrollerDisplayed(), "new filter category missing");
            Assert.assertTrue(landingPage.isPrimaryCtaDisplayed(), "primay cta missing");
            Assert.assertNotNull(landingPage.getPrimaryCtaText(), "mismatch in primary cta text");
            LOGGER.info("ui validation of " + categoryList[count] + " page done");

        }

    }



}
