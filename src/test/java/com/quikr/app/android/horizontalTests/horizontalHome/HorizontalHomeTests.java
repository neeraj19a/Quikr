package com.quikr.app.android.horizontalTests.horizontalHome;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.dataProvider.Data;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.app.android.snb.SnbPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Swatantra on 28/1/16.
 */
public class HorizontalHomeTests extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HORIZONTAL_TESTDATA_FILE"));

    /**
     * validate tutorial image appears on launch
     */
    @Stories("App Launch")
    @Title("mage tutorial is displayed with skip button")
    @Test
    public void ImageTutorialLinkISPresent()
    {
        Hompage hompage=new Hompage(driver);
        Assert.assertTrue(hompage.validateImageTutorialLinkIsPresent(), "Image tutorial link is not present on launch");

    }

    /**
     * validate tutorial image texts
     */
    @Stories("App Launch")
    @Test(dataProvider = "imageTutorialText",dataProviderClass = Data.class)
    public void VAlidateTutorialImageText(String tutorialText[])
    {
        Hompage hompage=new Hompage(driver);
        List<String>text=hompage.ImageTutorialText();
        System.out.println(text);
        for (int i=0;i<tutorialText.length;i++) {
            System.out.println(tutorialText[i]);
            Assert.assertTrue(tutorialText[i].equalsIgnoreCase(text.get(i)),"proper Image Not displayed in image Tutorial"+"---Expected ->"+tutorialText[i]+"& Actual ->"+text.get(i));
        }
    }
    /**31
     * validate all languahe options on app launch
     */
    @Stories("App Launch")
   // @Test(dataProvider = "LanguageOptions",dataProviderClass = Data.class)
    public void validateLanguageOptionsDisplayed(String QuikrLanguage[]) {
        Hompage hompage = new Hompage(driver);
        hompage.skipBannerMsg();
        hompage.skipLoginOnLaunch();
        List<String> language = hompage.languageOptions();
        System.out.println(language);
        for (int i = 0; i < QuikrLanguage.length; i++) {
            System.out.println(QuikrLanguage[i]);
            Assert.assertTrue(QuikrLanguage[i].equalsIgnoreCase(language.get(i)),"All language options not present"+"---Expected ->"+QuikrLanguage[i]+"& Actual ->"+language.get(i));
        }
    }
    /**
     * english language is selected by default
     */
    @Stories("App Launch")
   // @Test
    public  void validateEnglishLanguageIsSelectedByDefault()
    {
        Hompage hompage = new Hompage(driver);
        hompage.skipBannerMsg();
        hompage.skipLoginOnLaunch();
        Assert.assertTrue(hompage.englishLanguageIsSelectedByDefault(), "english language is not selected by default");

    }
    /**
     *validate all the elements are present in Menu Dropdown
     */
    @Stories("Menu Drawer")
    @Title("\"Verify the order of the options in the side menu:\n")
    @Test(dataProvider = "MenuDropDownOptions",dataProviderClass = Data.class)
    public void validateMenuListElements(String menuElements[])
    {
        Hompage hompage = new Hompage(driver);
        MenuPage menuPage=new MenuPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.skipBannerMsg();
        hompage.skipLoginOnLaunch();
        //hompage.selectLanguageOnLaunch();
        hompage.skipChatPopUp();
        headerPage.checkCity();
        menuPage.clickOnMenuIcon();
        List<String>menuDropDownElements=menuPage.menuListElements();
        for (int i=0;i<menuElements.length;i++)
        {
            Assert.assertTrue(menuElements[i].equalsIgnoreCase(menuDropDownElements.get(i)),"index missmatch for menu elements"+">>Expected="+menuElements[i]+">>Actual="+menuDropDownElements.get(i));
        }

    }
    /**
     * validate Popular,NearByAnd REcomended Ads Are are Present
     */
    @Stories("Homepage")
    @Title("validate Popular,NearByAnd REcomended Ads Are are Present")
    @Test(dataProvider = "AdTypesOnHomePage",dataProviderClass = Data.class)
    public void validatePopularNearByAndRecomendedAdsArePresent(String adtypes[])throws Exception
    {
        Hompage hompage = new Hompage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        hompage.skipBannerMsg();
        hompage.skipLoginOnLaunch();
        //hompage.selectLanguageOnLaunch();
        hompage.skipChatPopUp();
        headerPage.checkCity();
        List<String>AdTypes=hompage.AdTypesOnHomePage();
        for(int i=0;i< adtypes.length;i++)
        {
            Assert.assertTrue(AdTypes.get(i).equalsIgnoreCase(adtypes[i]));
        }
    }
//    /**
//     * home page is selected by default
//     */
//    @Stories("App Launch")
//    @Title("On every App launch \"Home\" tab should be selected by default")
//    @Test
//    public void homeTabIsSelectedByDefault()
//    {
//        Hompage hompage = new Hompage(driver);
//        HeaderPage headerPage=new HeaderPage(driver);
//        hompage.skipBannerMsg();
//        hompage.selectLanguageOnLaunch();
//        hompage.skipLoginOnLaunch();
//        hompage.skipChatPopUp();
//        headerPage.checkCity();
//        Assert.assertTrue(hompage.homePageIsSelected(),"home page is not selected by default");
//
//    }
//    /**
//     * Verify category tab is displayed after home on home screen.
//     */
//    @Stories("App Launch")
//    @Title("Verify category tab is displayed after home on home screen.")
//    @Test
//    public void categoryTabIsDisplayedAfterHome()
//    {
//        Hompage hompage = new Hompage(driver);
//        HeaderPage headerPage=new HeaderPage(driver);
//        hompage.skipBannerMsg();
//        hompage.selectLanguageOnLaunch();
//        hompage.skipLoginOnLaunch();
//        hompage.skipChatPopUp();
//        headerPage.checkCity();
//        List<String>category=hompage.Categories();
//        System.out.println(category);
//        Assert.assertTrue(category.get(0).equalsIgnoreCase("home")&&category.get(1).equalsIgnoreCase("categories"),"category tab is not displayed after home");
//
//    }
    /**
     * Ads viewed from Popular ads, SNB, Chat , should be displayed in Recently Viewed ads
     */
    @Stories("SNB")
    @Title("Verify Chat button and the chat presence is displayed in the following S&B page")
    //@Test
    public void chatIsPresentInRecentlyViewdAdForPopularAds()
    {
        Hompage hompage = new Hompage(driver);
        MenuPage menuPage=new MenuPage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        SnbPage snbPage=new SnbPage(driver);
        hompage.skipBannerMsg();
        hompage.skipLoginOnLaunch();
        hompage.skipChatPopUp();
        headerPage.checkCity();
        hompage.swipeVertically();
        hompage.clickOnMoreButtonToOpenAdsFromHOmePage(0);
        snbPage.hideOverlayLayout();
        snbPage.openAdFromSnb(1);
        snbPage.hideOverlayLayout();
        snbPage.explicitWait(3);
        snbPage.navigateBack();
        snbPage.selectMenuButtonAtSnbPage();
        menuPage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);


    }



}
