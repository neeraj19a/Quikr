package com.quikr.app.android.realEstate.realEstateVapPage;

import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 8/10/15.
 */
public class RealEstateVapPage extends AppAndroidPageBase {


    public RealEstateVapPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }
    private static final String domFile = getProperties().get("ANDROID_REALESTATEVAP_DOM_FILE");

    /**
     *  validate Locality on vap
     */

    public String vapLocality()
    {
        Mapper.waitForElementToBeVisible(domFile,"VapAdLocality");
        String vaplocality=Mapper.find(domFile, "VapAdLocality").getText();
        return vaplocality;
    }
    /**
     * validate no of rooms on vap
     */
    public String vapRooms()
    {
        Mapper.waitForElementToBeVisible(domFile,"vapNoOfRoomsArea");
        String VapRooms=Mapper.find(domFile, "vapNoOfRoomsArea").getText();
        return VapRooms;
    }
    /**
     * validate call & reply button is present on vap
     */
    public boolean validateCallAndReplyOnVap()
    {
        return (isElementPresent("callbutton")&&isElementPresent("chatOnVap"));
    }

    /**
     * validate reply button on clicking it
     */

    public boolean validateChat()
    {
        return isElementPresent("validateChat");
    }
    /**
     * validate user type On VAp
     */
    public boolean validateSellerTypeOnVap()
    {
        return( isElementPresent("sellerType")|isElementPresent("vapSellerName"));
    }
    /**
     * click on reply Button
     */
    public void clickOnchatButton()
    {
        Mapper.find(domFile,"chatOnVap").click();
    }
    /**
     * reply to poster
     */
    public void chatName(String name)
    {
        Mapper.waitForElementToBeVisible(domFile,"chatName");
        Mapper.find(domFile,"chatName").sendKeys(name);
    }
    /**
     * reply to poster with email
     */
    public void replyEmail(String email)
    {
        Mapper.waitForElementToBeVisible(domFile,"replyEmail");
        Mapper.find(domFile,"replyEmail").sendKeys(email);
    }
    /**
     * reply to poster with number
     */
    public void chatNumber(String number)
    {
        Mapper.waitForElementToBeVisible(domFile,"replyMobile");
        Mapper.find(domFile,"replyMobile").sendKeys(number);
    }
    /**
     * submit reply
     */
    public void submitReply()
    {
        Mapper.find(domFile,"submitReply").click();
    }
    /**
     * valite reply after posting reply
     */
    public boolean validateReplyAfterReplying()
    {
        return isElementPresent("SubmitreplySuccess");
    }
    /**
     * click on more button on vap
     */
    public void clickOnMoreButtonOnVAp()
    {
        //Mapper.VerticalSwipe(domFile,"favouriteButton","chatOnVap");
        Mapper.scroll("Property Type");
        Mapper.find(domFile,"moreButton").click();
    }
    /**
     * validate on clickin more button ,More[+] should expand and less[-]button should appear
     */
    public boolean validateOnClickingMoreButtonLessButtonAppears()
    {
        //Mapper.VerticalSwipe(domFile,"favouriteButton","chatOnVap");
        Mapper.scroll("Property Type");
        return isElementPresent("lessButton");
    }
    /**
     * tap on call button on Vap
     */

    public void clickOnCallbutton()
    {
        Mapper.waitForElementToBeVisible(domFile,"callbutton");
        Mapper.find(domFile,"callbutton").click();
    }
    /**
     * validate on clicking Call Button user is able to amke call
     */
    public boolean onClickingCallButtonUserIsAbleToMakeCall()
    {
        return( !isElementPresent("chatOnVap")&!isElementPresent("favouriteButton"));
    }
    /**
     * vcalidate on clicking submit chat user is redirected to caht page
     */

    public  boolean validateChatAction()
    {
        return isElementPresent("validateChatAction");
    }

    /**
     * Swipes VAP page horizontally
     */
    public void swipeVapHorizontally()
    {
        Mapper.horizontalSwipe(domFile,"vapPrice","vapseller");
    }

    public boolean isVapImageDisplayed()
    {
        Mapper.waitForElementToBeVisible(domFile, "vapImage");
        return isElementPresent("vapImage");
    }

    public boolean validateAdTitleAndDescriptionPresent()
    {
        return (isElementPresent("vapTitle") && isElementPresent("vapDescription"));
    }

    public boolean validatePropertyType()
    {

//        Mapper.scroll("Property Type");
        return isElementPresent("vapPropertyType");
    }

    public boolean validateAboutTheOwner()
    {
//        Mapper.scroll("About the Owner");
        return isElementPresent("aboutTheOwnerCard");
    }
    public void dismissShareAdPopUpOnVAp()
    {
        if (isElementPresent("dismissShareAd"))
            Mapper.find(domFile,"dismissShareAd").click();
    }

}
