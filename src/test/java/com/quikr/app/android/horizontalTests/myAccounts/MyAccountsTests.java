package com.quikr.app.android.horizontalTests.myAccounts;

import com.quikr.app.android.AppAndroidTestBase;
import com.quikr.app.android.alert.AlertPage;
import com.quikr.app.android.auth.AuthPageMobileLogin;
import com.quikr.app.android.chat.ChatPage;
import com.quikr.app.android.menu.MenuPage;
import com.quikr.utils.enums.app.QuikrAppEnums;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by swatantra singh on 27/4/16.
 */
public class MyAccountsTests extends AppAndroidTestBase {
    private HashMap<String, String> testData = getTestData(getProperties().get("ANDROID_HORIZONTAL_TESTDATA_FILE"));

    /**
     * Verify the header of My Ads
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOdMyAds()
    {
        MenuPage menuePage=new MenuPage(driver);
        menuePage.clickOnMenuIcon();
        menuePage.selectMyAds();
        Assert.assertTrue(menuePage.getHeaderText().equalsIgnoreCase("My Ads"));
        Assert.assertTrue(menuePage.verifyMyAdsHeader());
    }
    /**
     * Verify the header of My Chats if user not logged in
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfMyChatUserNotLoggedIn()
    {
        ChatPage chatPage=new ChatPage(driver);MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        //authPageMobileLogin.mobileLoginAuth(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyChats"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(chatPage.verifyHeaderOfChatScreenIfUserIsNotLoggedIn());

    }
    /**
     * Verify the header of My Chats if user  logged in
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public void verifyHeaderOfMyChatUserLoggedIn()
    {
        ChatPage chatPage=new ChatPage(driver);MenuPage menuePage = new MenuPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        authPageMobileLogin.mobileLogin(testData.get("email"), testData.get("password"));
        menuePage.clickOnMenuIcon();
        menuePage.selectelementWithoutScroll(testData.get("MyChats"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(chatPage.verifyHeaderOfChatScreenIfUserIsLoggedIn());

    }
    /**
     * Verify the header of My Alerts
     */
    @Stories("HEADER UI")
    @Test(groups = "Header")
    public  void verifyHeaderOfAlert()
    {
        MenuPage menuPage = new MenuPage(driver);
        AlertPage alertPage = new AlertPage(driver);
        AuthPageMobileLogin authPageMobileLogin=new AuthPageMobileLogin(driver);
        menuPage.clickOnMenuIcon();
        menuPage.selectelementWithoutScroll(testData.get("alert"), QuikrAppEnums.CATEGORY_MENU);
        Assert.assertTrue(alertPage.verifyHeaderElements());


    }


}
