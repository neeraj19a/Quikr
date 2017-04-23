package com.quikr.app.android.postAdV2;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.HashMap;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Swatantra singh on 1/3/16.
 */
public class PostAdLanguageChangeTests extends AppAndroidTestBase {
    private HashMap<String, String> testData=getTestData(getProperties().get("ANDROID_POSTADV2"));


    //@Test
    @Features("Post AD Vernac")
    @Stories("PostAd")
    public void validatePopUpMsgOnClickingLanguageButton()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.clickOnChangheLanguageIcon();
        Assert.assertTrue(postAdV2Page.verifyPopUpMsgOnClickingChangeLanguageButton(),"popup msg not generated after clicking language button");
    }
    @Features("Post AD Vernac")
    @Stories("PostAd")
   // @Test
    public void verifyOnClickingLanguageButtonProceedForwadAndCancelButtonAppears()
    {
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        PapPage papPage=new PapPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.clickOnChangheLanguageIcon();
        Assert.assertTrue(postAdV2Page.verifyActionAfterClickingLAnguageSelectionButton(), "on clicking language button ,Yes & No button not displayed");

    }
    @Features("Post AD Vernac")
    @Stories("PostAd")
 //   @Test(dataProvider = "LanguageOptions",dataProviderClass = Data.class)
    public void validateLanguagesOptionsOnPAP(String []QuikrLanguage)
    {
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        PapPage papPage=new PapPage(driver);
        Hompage hompage=new Hompage(driver);
        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("cars"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.clickOnChangheLanguageIcon();
        postAdV2Page.clickOnYesButtonToSelectLanguage();
        List<String> language = hompage.languageOptions();
        System.out.println(language);
        for (int i = 0; i < QuikrLanguage.length; i++) {
            System.out.println(QuikrLanguage[i]);
            Assert.assertTrue(QuikrLanguage[i].equalsIgnoreCase(language.get(i)),"All language options not present"+"---Expected ->"+QuikrLanguage[i]+"& Actual ->"+language.get(i));
        }

    }
    @Features("Post AD Vernac")
    @Stories("PostAd")
  //  @Test(dataProvider = "postAdLanguageChange",dataProviderClass = Data.class)
    public void validateLandingPageAfterSelectingLanguage(String lang,String convertedLang)
    {
        PostAdV2Page postAdV2Page=new PostAdV2Page(driver);
        Hompage hompage=new Hompage(driver);
        hompage.clickonPostAd();
        postAdV2Page.waitForPapToLoad();
        postAdV2Page.waitForCategorypagetoLoad();
        postAdV2Page.clickOnChangheLanguageIcon();
        postAdV2Page.clickOnYesButtonToSelectLanguage();
        System.out.println(lang);
        hompage.selectelementWithoutScroll(lang, QuikrAppEnums.HOMEPAGE_CATEGORY);
        hompage.selectLanguageOnLaunch();
        System.out.println(convertedLang);
        postAdV2Page.waitForCategorypagetoLoad();
        System.out.println(postAdV2Page.headerText());
        Assert.assertTrue(postAdV2Page.headerText().equals(convertedLang), "not changed for language" + lang);
    }

}
