package com.quikr.website.realEstate.realEstatePAP;

import com.quikr.website.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by Sanyam on 3/11/15.
 */
public class RealEstatePAPTests extends TestBase{

    private HashMap<String, String> testData = getTestData(getProperties().get("REAL_ESTATE_TESTDATA_FILE"));
    // test case 1 --Verify the Post Ad button is landing to PAP Form
    @Test
    public void verifyPostAdButton()
    {
        RealEstatePAPPage RealEstatePAPPage =new RealEstatePAPPage(driver);
        System.out.println(testData.get("cityname"));
        RealEstatePAPPage.citySelectionLayer(testData.get("cityname"));
        RealEstatePAPPage.clickPostAdbutton();
        Assert.assertTrue(RealEstatePAPPage.verifyPAPURL(), "Post Ad Page Landing URL not matching with Expected ");
        RealEstatePAPPage.selectPropertyCategory();
        RealEstatePAPPage.selectAdType();
        //Assertion to check the  Project name appearing
        RealEstatePAPPage.selectProjectname(testData.get("papprojectname"));
        //Assertion to check the value of the Unit Type
        RealEstatePAPPage.selectUnittype();
        RealEstatePAPPage.inputPrice(testData.get("papprice"));
        //RealEstatePAPPage.submitFirstForm();
        RealEstatePAPPage.secondFormTransition();
        RealEstatePAPPage.thirdformTransition();
        RealEstatePAPPage.inputAdTitle(testData.get("adtitle"));
        RealEstatePAPPage.inputAdDescription(testData.get("addesc"));
        RealEstatePAPPage.submitPAPForm();
        RealEstatePAPPage.submitUserForm(testData.get("radio"), testData.get("email"), testData.get("phone"));
    }


    /*@Test
    public void verifyFirstPostAdForm()
    {
        RealEstatePAPPage postAdPage=new RealEstatePAPPage(driver);

    }*/
}
