package com.quikr.app.ios.services.servicesSnbPage;

import com.quikr.app.ios.AppiOSTestBase;
import com.quikr.app.ios.home.HomePage;
import com.quikr.app.ios.realEstate.realEstateSnbPage.RealEstateSnbPage;
import com.quikr.app.ios.services.servicesHomePage.ServicesHomePage;
import com.quikr.app.ios.snb.SnbPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Manvendra Singh on 2/29/16.
 */
public class ServicesSnbPageTests extends AppiOSTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("IOS_SERVICESPAGE_TESTDATA_FILE"));

    /**
     * S_009
     *
     * Verify clicking on Skip Action button
     */
    @Title("Verify clicking on Skip Action button")
    @Test(groups = "horizontal")
    public void verifySkipActionButtonFunctionality()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);
        RealEstateSnbPage realEstateSnbPage=new RealEstateSnbPage(driver);
        SnbPage snbPage= new SnbPage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("servicesCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("servicesSubcategoryName"));
        Assert.assertTrue(servicesHomePage.validateQuikrConnectPageOptions(), "all the quikr connect options can't match ");
        Assert.assertTrue(servicesHomePage.validateSkipAndConnectNowButtonOnQuikrConnectPage(),"missing skip or connect now button on quikr connect page");
        servicesHomePage.clickOnSkipButton();
        if (homePage.checkDontAllowButton())
        {
            realEstateSnbPage.selectAllowButtonInLocationAccessPopup();
        }
        Assert.assertTrue(snbPage.validateSnbPageForSortAndFilterButton(),"not redirected on snb page or sort or filter button are missing");
    }

    /**
     * S_011  , S_012
     *
     * Verify the Quikr connect form
     *
     * Verify clicking on 'Connect' Action button
     */

    @Title("verify quikr connect form")
    @Test(groups = "horizontal")
    public void verifyQuikrConnectForm()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("servicesCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("servicesSubcategoryName"));
        Assert.assertTrue(servicesHomePage.validateQuikrConnectPageOptions(), "all the quikr connect options can't match ");
        Assert.assertTrue(servicesHomePage.validateSkipAndConnectNowButtonOnQuikrConnectPage(),"missing skip or connect now button on quikr connect page");
        servicesHomePage.selectConnectNowButton();
       // Assert.assertTrue(servicesHomePage.validateQuikrConnectForm(),"desired subcategory are not present on quikr connect page or not redirected on quikr connect page");
        servicesHomePage.setNameOnServicesQuikrConnectPage(testData.get("userName"));
        servicesHomePage.setMobileNoOnServicesQuikrConnectPage(testData.get("mobileNo"));
        Assert.assertTrue(servicesHomePage.validateSubmitButtonOnQuikrConnectForm(),"submit button is not present");
        servicesHomePage.selectSubmitButtonOnQuikrConnectPage();
       // Assert.assertTrue(servicesHomePage.validateValidationCodeErrorMsg(),"validation error msg is not display");
    }

    /**
     * S_013
     *
     * Verify the Mandatory fields
     */
    @Title("Verify the Mandatory fields in quikr connect form")
    @Test(groups = "horizontal")
    public void verifyMandatoryFieldsInQuikrConnectForm()
    {
        HomePage homePage =new HomePage(driver);
        ServicesHomePage servicesHomePage=new ServicesHomePage(driver);

        homePage.selectCityName();
        homePage.selectCategoryOrSubcategory(testData.get("servicesCategory"));
        homePage.selectCategoryOrSubcategory(testData.get("servicesSubcategoryName"));
        Assert.assertTrue(servicesHomePage.validateQuikrConnectPageOptions(), "all the quikr connect options can't match ");
        Assert.assertTrue(servicesHomePage.validateSkipAndConnectNowButtonOnQuikrConnectPage(),"missing skip or connect now button on quikr connect page");
        servicesHomePage.selectConnectNowButton();
        Assert.assertTrue(servicesHomePage.validateSubmitButtonOnQuikrConnectForm(),"submit button is not present");
        servicesHomePage.selectSubmitButtonOnQuikrConnectPage();
        Assert.assertTrue(servicesHomePage.validateMsgNotProvidingMbNoInQuikrConnectFormPage(),"mobile is provided or quikr connect page is not redirected");
    }

}
