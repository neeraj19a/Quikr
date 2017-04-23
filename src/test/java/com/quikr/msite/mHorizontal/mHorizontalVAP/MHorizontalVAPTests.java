package com.quikr.msite.mHorizontal.mHorizontalVAP;

import com.quikr.msite.MTestBase;
import com.quikr.msite.mElectronics.mElectronicsHome.MElectronicsHomePage;
import com.quikr.msite.mElectronics.mElectronicsSNB.MElectronicsSnBPage;
import com.quikr.msite.mHorizontal.mHome.MHomePage;
import com.quikr.msite.mHorizontal.mSNB.MSNBPage;
import com.quikr.msite.mHorizontal.mVAP.MVAPPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 19/2/16.
 */
public class MHorizontalVAPTests extends MTestBase
{

    private HashMap<String, String> testData = getTestData(getProperties().get("mHORIZONTAL_TESTDATA_FILE"));

    /*
    MS-657:Verify VAP page access
     */
    @Test
    public void verifyVapAccess()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MSNBPage msnbPage = new MSNBPage(driver);
        MVAPPage mvapPage = new MVAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage=new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));
       
        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        msnbPage.clickFirstAd();

        Assert.assertTrue(mvapPage.verifyVapPage(), "Vap page didn't load. Please check!");
    }

    /*
    MS-658:Verify user Profile option on VAP page
     */
    @Test
    public void verifyUserProfileOption()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MSNBPage msnbPage = new MSNBPage(driver);
        MVAPPage mvapPage = new MVAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage=new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        msnbPage.clickFirstAd();

        mvapPage.clickViewProfileButton();
        Assert.assertTrue(mvapPage.verifyUserProfilePage(),"User Profile page didn't load up. Please check!");
    }

    /*
    MS-656:Verify Buy Now option on VAP page
     */
    @Test
    public void verifyBuyNowOption()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MSNBPage msnbPage = new MSNBPage(driver);
        MVAPPage mvapPage = new MVAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage=new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        msnbPage.clickAdwithBuyNowOption();
        waitForPageToLoad(testData.get("homepage"));

        mvapPage.clickBuyNow();
        Assert.assertTrue(mvapPage.verifyBuyNowLabel(),"The buy Now window didn't open. Please check!");
    }

    /*
    MS-589:Make an offer on VAP
    An issue, need to fix.
     */
    @Test
    public void verifyMakeAnOffer()
    {
        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MSNBPage msnbPage = new MSNBPage(driver);
        MVAPPage mvapPage = new MVAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage=new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();
        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        msnbPage.clickFirstMakeanOfferAdSNB();

        mvapPage.clickMakeAnOffer();

        mvapPage.enterOfferPrice(testData.get("offerPrice"));
        mvapPage.enterMail(testData.get("JunkMail"));
        mvapPage.enterPhNum(testData.get("profilePhNum"));
        mvapPage.clickSendMyOfferButton();

        Assert.assertTrue(mvapPage.validateMakeAnOfferSuccess(), "Make an offer didn't work properly. Confirmation message is missing. Please check!");
    }


    @Title("MS-673:VAP: Verify VAP in all vernacular languages")
    @Test
    public void validateVAPVernac(){

        MHomePage mHomePage = new MHomePage(driver);
        MElectronicsHomePage mElectronicsHomePage =new MElectronicsHomePage(driver);
        MSNBPage msnbPage = new MSNBPage(driver);
        MVAPPage mvapPage = new MVAPPage(driver);
        MElectronicsSnBPage mElectronicsSnBPage=new MElectronicsSnBPage(driver);

        waitForPageToLoad(testData.get("homepage"));

        mHomePage.clickCloseMostTrustedBrandPopUp();
        mHomePage.clickElectronicsandAppliances();

        mElectronicsHomePage.clickELectronicsandAppliacesCategory(testData.get("LaptopsandComputers"));

        msnbPage.clickFirstMakeanOfferAdSNB();

        waitForPageToLoad(testData.get("homepage"));
        mHomePage.clickLanguage(2);
        waitForPageToLoad(testData.get("homepage"));

        Assert.assertEquals(mvapPage.gettexttitleVapVernac(), "विवरण", "Failed to Load VAP page in Hindi");
        mHomePage.clickLanguage(3);
        waitForPageToLoad(testData.get("homepage"));

        Assert.assertEquals(mvapPage.gettexttitleVapVernac(), "வர்ணனை", "Failed to Load VAP page in Tamil");
        mHomePage.clickLanguage(4);
        waitForPageToLoad(testData.get("homepage"));

        Assert.assertEquals(mvapPage.gettexttitleVapVernac(), "వివరణ", "Failed to Load VAP page in Telugu");

    }

}
