package com.quikr.app.android.realEstate.realEstateVap;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.realEstate.realEstateHomePage.RealEstateHomePage;
import com.quikr.app.android.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.android.realEstate.realEstateVapPage.RealEstateVapPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra on 8/10/15.
 */
public class RealEstateVapTests extends AppAndroidTestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_REALESTATEHOME_TESTDATA_FILE"));

    /**Android Sanity :2451-52,2458,
     *Android 1337-38:Verify the Locality searched is displayed in SNB and VAP screens
     * Android 1346
     * 1351
     * 1352
     * 1336:price format

     */
    @Test
    public void validateLocalityONSnbAndVap() throws Exception
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        RealEstateVapPage realEstateVapPage=new RealEstateVapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.SendSearchText(testData.get("search"));
        realEstateHomePage.selectelementWithoutScroll(testData.get("locality"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        String snbLocality=realEstateSnbPage.AdLocalityOnSnb();
        String snbRooms=realEstateSnbPage.snbRooms();
        String price=realEstateSnbPage.validatePriceFormat().trim();
        //Assert.assertTrue(price.contains("₹")&&price.contains(","));
        char c=(price.charAt(price.length()-4));
        int ascii=(int)c;
        Assert.assertEquals(ascii,44);
        Assert.assertEquals(testData.get("locality").toLowerCase(), snbLocality.toLowerCase());
        realEstateSnbPage.openAdFromSnb();
        Assert.assertTrue(realEstateVapPage.isVapImageDisplayed(), "Vap image not displayed,");
        String vapLocality=realEstateVapPage.vapLocality();
        String vapRooms=realEstateVapPage.vapRooms();
        Assert.assertTrue(vapLocality.toLowerCase().contains(testData.get("locality").toLowerCase()));
        Assert.assertTrue(vapRooms.contains(snbRooms));
        Assert.assertTrue(realEstateVapPage.validateCallAndReplyOnVap(), "Call and reply on vap not visible on vap");
        Assert.assertTrue(realEstateVapPage.validateSellerTypeOnVap(), "Seller type not visible on vap");
        Assert.assertTrue(realEstateVapPage.validateAdTitleAndDescriptionPresent(), "Ad title and description not visible on vap");
        realEstateVapPage.swipeVertically("vapDescription", "vapImage");
        Assert.assertTrue(realEstateVapPage.validatePropertyType(), "Property type not visible on vap");
//        realEstateVapPage.swipeVertically("callbutton", "vapDescription");
//        Assert.assertTrue(realEstateVapPage.validateAboutTheOwner(), "About the owner not visible on vap");


    }
    /**scroll not working on RealEstateVAp
     * Android Sanity;2454
     * Tap on 'More[+]' and 'More[-]' in the VAP screen and verify
     */
    // @Test
    public void checkOnTappingMoreButtonDescriptionExpand()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        RealEstateVapPage realEstateVapPage=new RealEstateVapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        realEstateSnbPage.openAdFromSnb();
        realEstateVapPage.clickOnMoreButtonOnVAp();
        Assert.assertTrue(realEstateVapPage.validateOnClickingMoreButtonLessButtonAppears(),"on clicking more button description field doesn't expand");
    }


    /**
     * Android sanity:2459
     *Tap on 'CALL' button in VAP and verif
     */
    @Test
    public void validateOnclickingCallButtonUserIsAbleToMakeCall()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        RealEstateVapPage realEstateVapPage=new RealEstateVapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        realEstateSnbPage.openAdFromSnb();
        realEstateVapPage.clickOnCallbutton();
        Assert.assertTrue(realEstateVapPage.onClickingCallButtonUserIsAbleToMakeCall(),"on clicking Call button user is not redirected to call");
    }
    /**
     * Android Sanity 2460:
     * Tap on 'REPLY' button in VAP screen and verify
     */
    @Test
    public void VerifyChatButtonAction()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        RealEstateVapPage realEstateVapPage=new RealEstateVapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        realEstateSnbPage.openAdFromSnb();
        realEstateVapPage.clickOnchatButton();
        Assert.assertTrue(realEstateVapPage.validateChat());

    }
    /**
     * Android Sanity:2461
     * verify user can reply to a poster
     */
    @Test
    public void userCanChatWithPoster()
    {
        SnbPage snbPage=new SnbPage(driver);
        Hompage hompage = new Hompage(driver);
        RealEstateSnbPage realEstateSnbPage = new RealEstateSnbPage(driver);
        RealEstateHomePage realEstateHomePage = new RealEstateHomePage(driver);
        RealEstateVapPage realEstateVapPage=new RealEstateVapPage(driver);
        hompage.selectelementWithoutScroll(testData.get("realestate"), QuikrAppEnums.Home_Categories);
        realEstateHomePage.clickOnResidential();
        realEstateHomePage.clickOnSearch();
        realEstateHomePage.selectelementWithoutScroll(testData.get("autosuggestsubcat"), QuikrAppEnums.RealEstate_Search_Autosuggest);
        snbPage.hideOverlayLayout();
        realEstateSnbPage.openAdFromSnb();
        realEstateVapPage.clickOnchatButton();
        realEstateVapPage.chatNumber(testData.get("number"));
        realEstateVapPage.submitReply();
        Assert.assertTrue(realEstateVapPage.validateChatAction(),"on submiting chat user is not redirected to chat page ");
    }
}
