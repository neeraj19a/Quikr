package com.quikr.msite.mHorizontal.mSNB;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 19/2/16.
 */
public class MSNBPage extends MPageBase
{
    private static final String domFile = getProperties().get("mSNB_DOM_FILE");

    public MSNBPage(AppiumDriver driver) {
        super(domFile, driver);
    }

    public void clickFirstAd()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"adFromSnbList")==true)
        {
            Mapper.find(domFile,"adFromSnbList").click();
        }
        else
        {
            logger.info("Ads are not listed on the SNB page. Please check!");
        }
    }

    public void clickChatButton()
    {
        if (Mapper.waitForElementToBeVisible(domFile,"chatButton")==true)
        {
            Mapper.find(domFile,"chatButton").click();
            if(!Mapper.find(domFile,"chatForm").isDisplayed()){
                Mapper.find(domFile,"chatButton").click();
            }
        } else {
            logger.info("There is either no chat Button available onscreen or its invisible. Please check!");
        }
    }

    public void sendQues(String ques)
    {
        if (Mapper.waitForElementToBeVisible(domFile,"chatMsgBoxQuestion")==true)
        {
            Mapper.find(domFile,"chatMsgBoxQuestion").sendKeys(ques);
        }
        else
        {
            logger.info("Question box didn't get displayed. Please check!");
        }
    }

    public void sendEmail(String email)
    {
        Mapper.find(domFile,"chatMsgBoxEmail").sendKeys(email);
    }

    public void sendPhNum(String phnNum)
    {
        if(Mapper.waitForElementToBeClickable(domFile,"chatMsgBoxPhNum")==true) {
            Mapper.find(domFile,"chatMsgBoxPhNum").click();
            Mapper.find(domFile, "chatMsgBoxPhNum").sendKeys(phnNum);

        }
    }

    public boolean verifyChatNowSuccess(){
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"chatMsgBoxConfirmation")==true)
        {
            retVal = true;
        }
        else
        {
            return false;
        }
        return retVal;
    }

    public void clickSubmit()
    {
        Mapper.find(domFile,"chatMsgBoxSubmitBtn").click();
    }

    public boolean checkIfSnbPageTranslatedToVernac()
    {
        boolean retVal = false;
        if (Mapper.waitForElementToBeVisible(domFile,"sortLabel")==true)
        {
            String sortLabel = Mapper.find(domFile,"sortLabel").getText();
            if (sortLabel.equals("फिल्टर")==true)
            {
                retVal=true;
            }
            else
            {
                return false;
            }
        }
        return retVal;
    }


    public void clickFirstMakeanOfferAdSNB(){
        if(Mapper.waitForElementToBeClickable(domFile,"MakeAnOfferAdSNB")==true) {
            Mapper.find(domFile, "MakeAnOfferAdSNB").click();
        }
    }

    public void clickAdwithBuyNowOption(){
        if(Mapper.waitForElementToBeClickable(domFile,"adWithBuyNowOption")==true){
            Mapper.find(domFile,"adWithBuyNowOption").click();
        }
        else {
            logger.info("Ads with Buy Now Option is not present on SNB Page");
        }
    }
}
