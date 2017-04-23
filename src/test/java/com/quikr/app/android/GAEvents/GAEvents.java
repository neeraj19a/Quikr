package com.quikr.app.android.GAEvents;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.Constants.MobileConstants;
import com.quikr.app.android.header.HeaderPage;
import com.quikr.app.android.home.Hompage;
import com.quikr.app.android.pap.PapPage;
import com.quikr.app.android.postAdV2.PostAdV2Page;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by rohanbajaj on 15/03/16.
 */
public class GAEvents extends AppAndroidTestBase
{
    private HashMap<String, String> testData=getTestData(getProperties().get("ANDROID_GAEvents"));
    @Features("Post AD AS Offer Type")
    @Stories("PostAd")
    @Title("User should Be able To Post Ad For Services")
    @Test()
    public void postAdforServices()
    {
        PapPage papPage = new PapPage(driver);
        PostAdV2Page postAdV2Page = new PostAdV2Page(driver);
        Hompage hompage = new Hompage(driver);

        executeCommand(new String[]{"adb shell setprop log.tag.GAv4 DEBUG"});

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Before execute");
                executeCommand(new String[]{"bash", "-c", "adb logcat -s GAv4 > b.txt"});
                System.out.println("After execute");
            }
        }) ;
        thread.start();

        hompage.clickonPostAd();
        papPage.selectelementWithoutScroll(testData.get("servicescategory"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        papPage.selectelementWithoutScroll(testData.get("servicessubcategory1"), QuikrAppEnums.Quikr_PAP_CAT_SUBCAT_NEW);
        postAdV2Page.waitForPapToLoad();
        papPage.clickOnUploadImage();
        papPage.clickOnCapturePhoto();
        papPage.navigateBack();
        papPage.clickOnUploadImage();
        papPage.selectgallery();
        for (int i=0;i<8;i++)
        {
            papPage.selectImageFromGalery(i);
            Assert.assertTrue(papPage.checkIfImageSelected(i).equalsIgnoreCase("true"));
        }
        papPage.uploadImageFromGallery();
        papPage.tapOnCropImage();
        papPage.tapOnRotateImage();
        papPage.tapOnBeautifyImage();
        //   papPage.setAdTitle(testData.get("AdTitle"));
        //  papPage.setAdDescription(testData.get("desc"));
        papPage.navigateBack();
        Integer[] cord = postAdV2Page.getCordinates();
        postAdV2Page.swipeToPostAd(cord[0] + 50, cord[1]);
        postAdV2Page.sendInPutText(testData.get("enterAdTitle"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadTitle"));
        postAdV2Page.navigateBack();
        postAdV2Page.sendInPutText(testData.get("enterDescription"), QuikrAppEnums.PostAD_InputText, testData.get("servicesadDescription"));
        postAdV2Page.navigateBack();
        postAdV2Page.selectElements(testData.get("SelectLocality"), QuikrAppEnums.PostAd_SelectFromDropDown);
        postAdV2Page.selectelementWithoutScroll(testData.get("location"), QuikrAppEnums.PAP_NEW_LOCALITY);
        postAdV2Page.swipeToPostAd(cord[0] + 100, cord[1]);
        postAdV2Page.sendTextByKeybord(testData.get("mobileNumber"), QuikrAppEnums.PostAD_InputText, "9955788758");
//        postAdV2Page.postYourAd();
//        Assert.assertTrue(papPage.validatePostAd());
        ArrayList<String> output1 = command("awk -F 'el=' '{print $2}' b.txt| cut -d , -f1| grep -v ^$",".");
        System.out.println("Output 1:"+output1.size());

        ArrayList<String> papEventLabels = new ArrayList<String>();
        int count =0, i=0;
        for (String line : output1)
        {
            System.out.println("Line 1:"+line);
            if(line.contains("quikr_pap"))
            {
//                papEventLabels.set(count, line);
                Assert.assertEquals(line, MobileConstants.papGAEventLabels.get(i));
                i++;
            }
            count++;
        }


//        ArrayList<String> output2 = command("awk -F 'ea=' '{print $2}' b.txt| cut -d , -f1| grep -v ^$",".");
//        ArrayList<String> papEventAction = new ArrayList<String>();
//        count =0;i=0;
//        for (String line : output2)
//        {
//            if(line.contains("quikr_pap"))
//            {
//                Assert.assertEquals(line, MobileConstants.papGAEventAction.get(i));
//                i++;
//            }
//            count++;
//        }
//
//        ArrayList<String> output3 = command("awk -F 'ec=' '{print $2}' b.txt| cut -d , -f1| grep -v ^$",".");
//        ArrayList<String> papEventCategory = new ArrayList<String>();
//        count =0;
//        for (String line : output3)
//        {
//            if(line.contains("quikr"))
//            {
//                Assert.assertEquals(line, MobileConstants.papGAEventCategory.get(i));
//                i++;
//            }
//            count++;
//        }
    }

    @Stories("SNB")
    @Title("Verify Chat button and the chat presence is displayed in the following S&B page")
    @Test
    public void chatIsPresentInRecentlyViewdAdForPopularAds()
    {
        Hompage hompage = new Hompage(driver);
        HeaderPage headerPage=new HeaderPage(driver);
        executeCommand(new String[]{"adb shell setprop log.tag.GAv4 DEBUG"});

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Before execute");
                executeCommand(new String[]{"bash", "-c", "adb logcat -s GAv4 > a.txt"});
                System.out.println("After execute");
            }
        });
        thread.start();

        hompage.skipBannerMsg();
        hompage.selectLanguageOnLaunch();
        hompage.skipLoginOnLaunch();
        hompage.skipChatPopUp();
        headerPage.checkCity();
        hompage.swipeVertically();
        hompage.clickOnMoreButtonToOpenAdsFromHOmePage(0);
//        snbPage.hideOverlayLayout();
//        snbPage.openAdFromSnb(1);
//        snbPage.hideOverlayLayout();
//        snbPage.explicitWait(3);
//        snbPage.navigateBack();
//        snbPage.selectMenuButtonAtSnbPage();
//        menuPage.selectElements(testData.get("recentlyViewed"), QuikrAppEnums.CATEGORY_MENU);

        ArrayList<String> output1 = command("awk -F 'el=' '{print $2}' a.txt| cut -d , -f1| grep -v ^$",".");
        System.out.println("Output 1:"+output1.size());

        ArrayList<String> homeEventLabels = new ArrayList<String>();
        int count =0, i=0;
        for (String line : output1)
        {
            System.out.println("Line 1:" + line);
            if (line.contains("quikr_hp"))
            {
                Assert.assertTrue(MobileConstants.hpGAEventLabels.contains(line));
                i++;
            }
            count++;
        }

    }





}
