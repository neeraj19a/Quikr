package com.quikr.app.android.pap;

import com.quikr.app.android.AppAndroidPageBase;
import com.quikr.utils.Database;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by swatantra singh on 14/8/15.
 */
public class PapPage extends AppAndroidPageBase {
    public PapPage(AppiumDriver driver){super(domFile, driver);}
    private static final String domFile = getProperties().get("ANDROID_PAP_DOM_FILE");

    /**
     * provide ad title
     * @param title
     * @return
     */
    public void setAdTitle(String title,String cat)
    {
        switch (cat) {
            case "Jobs":
                Mapper.waitForElementToBeVisible(domFile, "adTitle");
                Mapper.find(domFile,"adTitle").sendKeys(title);
                break;
            case "Real Estate":
                Mapper.waitForElementToBeVisible(domFile, "adTitle");
                Mapper.find(domFile,"adTitle").sendKeys(title);
                break;
            case "Services":
                Mapper.waitForElementToBeVisible(domFile, "adTitle");
                Mapper.find(domFile,"adTitle").sendKeys(title);
                break;
            case"Mobiles & Tablets":
                Mapper.waitForElementToBeVisible(domFile, "adTitle");
                Mapper.find(domFile,"adTitle").sendKeys(title);
                break;
            case "Electronics & Appliances":
                if(!isElementPresent("adTitle"))
                    Mapper.scroll("Enter Ad Title");
                Mapper.find(domFile,"adTitle").sendKeys(title);
                break;
            case "Cars & Bikes":
                if(!isElementPresent("adTitle"))
                    Mapper.scroll("Enter Ad Title");
                Mapper.find(domFile,"adTitle").sendKeys(title);



        }


    }

    /**
     * Post ad description
     * @param desc
     * @return
     */
    public void setAdDescription(String desc,String cat)
    {
        switch (cat)

        {
            case "Jobs":
                if(!isElementPresent("AdDescription"))
                    Mapper.scroll("Ad Description ");
                Mapper.find(domFile,"AdDescription").sendKeys(desc);
                break;

            case "Real Estate":
                Mapper.scroll("Ad description");
                Mapper.find(domFile,"AdDescription").sendKeys(desc);
                break;
            case "Services":
                Mapper.waitForElementToBeVisible(domFile, "AdDescription");
                Mapper.find(domFile,"AdDescription").sendKeys(desc);
                break;
            case"Mobiles & Tablets":
                Mapper.scroll("Ad Description");
                Mapper.find(domFile,"AdDescription").sendKeys(desc);
                break;
            case "Electronics & Appliances":
                Mapper.scroll("Ad Description ");
                Mapper.find(domFile,"AdDescription").sendKeys(desc);
                break;
            case "Cars & Bikes":
                if(!isElementPresent("AdDescription"))
                    Mapper.scroll("Ad Description ");
                Mapper.find(domFile,"AdDescription").sendKeys(desc);


        }


    }

    /**
     * select Ad location
     * @return
     */
    public void clickonAdlocation()
    {


        if(!isElementPresent("location"))
        {
            Mapper.scroll("Locality");
        }
        Mapper.find(domFile,"location").click();

    }

    /**
     * vanigate to next page on PAP
     * @return
     */
    public PapPage navigateToNextPage()
    {
        Mapper.scroll("Next");
        Mapper.find(domFile,"papNextpage").click();
        return this;
    }

    /**click on category
     * swatantra singh
     * @return
     */
    public PapPage ClickonCategory()
    {
        Mapper.waitForElementToBeVisible(domFile, "PapCategory");
        Mapper.find(domFile,"PapCategory").click();
        return  this;
    }

    /**click on sub category
     * swatantra singh
     * @return
     */
    public PapPage ClickonSubCategory()
    {
        Mapper.waitForElementToBeVisible(domFile, "PapSubcategory");
        Mapper.find(domFile,"PapSubcategory").click();
        return  this;
    }

    /**
     * select post Ad as individual
     * @return
     */
    public PapPage PostAdAsIndividual()
    {
//        Mapper.scroll("Individual");
        Mapper.waitForElementToBeVisible(domFile,"Individual");
        Mapper.find(domFile, "Individual").click();

        return this;
    }

    /**
     * select used condition
     * @return
     */
    public  PapPage selectConditionAsUsed()
    {
        if(!isElementPresent("Used"))
            Mapper.scroll("Used");
        Mapper.find(domFile,"Used").click();
        return this;
    }

    /**
     * set product price
     * @param price
     * @return
     */
    public PapPage setPrice(String price)
    {
        Mapper.find(domFile, "price").sendKeys(price);
        return this;
    }

    /**swatantra singh
     * select brand
     * @return
     */
    public PapPage selectBrand()
    {
        if(!isElementPresent("brand"))
        {
            navigateBack();
        }
        Mapper.find(domFile,"brand").click();
        return this;
    }

    /**
     * select model
     * swaantra singh
     * @return
     */
    public PapPage selectModel(String model)
    {
        Mapper.waitForElementToBeVisible(domFile, "model");
        Mapper.find(domFile,"model").click();
        return this;
    }

    /**
     * selet variant
     * @return
     */
    public PapPage selectvariant()
    {
        Mapper.waitForElementToBeVisible(domFile, "variant");
        Mapper.find(domFile, "variant").click();
        return this;
    }

    /**
     * swatantra singh
     * @return
     */
    public PapPage selectYear()
    {
        Mapper.waitForElementToBeVisible(domFile,"year");
        Mapper.find(domFile,"year").click();
        return this;
    }

    /**
     *
     * @param distance
     * @return
     */
    public PapPage selectDistanceDriven(String distance)
    {
        Mapper.find(domFile,"distanceDriven").sendKeys(distance);
        return this;
    }

    /**
     *
     * @return
     */
    public void selectColor()
    {
        if (!isElementPresent("color"))
            Mapper.scroll("Color");
        Mapper.find(domFile,"color").click();

    }

    /**
     *
     * @return
     */
    public PapPage selectFuelType()
    {
        Mapper.find(domFile,"fuel").click();
        return this;
    }

    /**
     *
     * @return
     */
    public void selectOwners()
    {
        if (!isElementPresent("owners"))
            Mapper.find(domFile,"owners").click();

    }

    /**
     *
     * @param name
     * @return
     */
    public void provideName(String name)
    {
        if (!isElementPresent("PapName"))
            Mapper.scroll("Name");
        Mapper.find(domFile,"PapName").sendKeys(name);

    }

    /**
     *
     *
     * @param email
     * @return
     */
    public PapPage provideEmail(String email)
    {
        Mapper.find(domFile,"email").sendKeys(email);
        return this;
    }

    /**
     *
     * @param number
     * @return
     */
    public  PapPage sendNumber(String number)
    {
        Mapper.scroll("Mobile no");
        Mapper.find(domFile,"number").sendKeys(number);
        return this;
    }

    /**
     *
     * @return
     */
    public PapPage submitAd()
    {
        if (!isElementPresent("submitAd"))
            navigateBack();
        Mapper.find(domFile, "submitAd").click();
        return this;
    }

    /**
     *
     * @return
     */
    public boolean validatePostAd()
    {
        Mapper.waitForElementToBeVisible(domFile, "validatePostAd");
        if (isElementPresent("validatepostAd"))
            return true;
        return false;
    }

    /**swatantra
     * validate incomplete ad error msg
     * @return
     */
    public boolean validateIncompleteTitle()
    {
        if (isElementPresent("incompletetitle"))
            return true;
        return false;
    }

    /**swatantra
     * validate postAd with incomplete description msg
     * @return
     */
    public boolean validateIncompleteDescription()
    {
        if (isElementPresent("incompleteDesc"))
            return true;
        return false;
    }

    /**swatantra
     * validate postAd without Category error msg
     * @return
     */
    public boolean validateAdWithoutCategory()
    {
        if (isElementPresent("withoutCategory"))
            return true;
        return false;
    }

    /**
     * validate usr can not post ad without subcategory
     * swatantra singh
     * @return
     */
    public boolean validateAdWithoutSubCategory()
    {
        if (isElementPresent("withoutSubCategory"))
            return true;
        return false;
    }

    public void cancelPostingAd()
    {
        Mapper.find(domFile,"CancelPostinAd").click();
    }
    public void postAdAsStartFresh()
    {
        Mapper.waitForElementToBeVisible(domFile, "postAdStartFresh");
        Mapper.find(domFile,"postAdStartFresh").click();
    }

    /**
     * continue posting ad from where you left
     */
    public void  postAdAsContine()
    {
        Mapper.waitForElementToBeVisible(domFile, "postAdwithContinue");
        Mapper.find(domFile,"postAdwithContinue").click();
    }

    /**
     * function to select view my ad
     */
    public void selectViewMyAdButton()
    {
        Mapper.find(domFile, "viewMyAd").click();
        Mapper.waitForElementToBeInvisible(domFile, "viewMyAd");
    }

    /**
     * function to validate preview ad post
     */
    public boolean validatePreviewPostAd()
    {
        return (isElementPresent("editAd") && isElementPresent("done"));
    }

    /**
     * function to select done button
     */
    public void selectDoneButtonAtVap()
    {
        Mapper.find(domFile,"done").click();
        Mapper.waitForElementToBeInvisible(domFile, "done");
    }


    /**
     * click on edit Ad option to edit existing ads
     */
    public void clickOnEditAd()
    {
        Mapper.find(domFile,"editAd").click();
    }

    /**
     * function to validate that role field is not display for other category except job
     */
    public boolean validateRoleForOtherCategoryExceptJobs()
    {
        if(isElementPresent("iAmAnEmployer"))
        {
            return false;
        }
        else if(isElementPresent("iNeedAJob"))
        {
            return false;
        }
        else if(isElementPresent("role"))
        {
            return false;
        }
        return true;
    }

    /**
     * function to select I am An employer while post an ad for job
     */
    public void selectIAmAnEmployer()
    {
        Mapper.find(domFile, "iAmAnEmployer").click();
    }

    /**
     * function to select I need  a job while post an ad for job
     */
    public void selectINeedAJob()
    {
        Mapper.finds(domFile,"iNeedAJob").get(1).click();
    }

    /**
     * function to validate role field is displayed for Offer ad
     */
    public boolean validateRoleFieldForJobCategory()
    {
        return (isElementPresent("role"));

    }

    /**
     * function to select role while post an ad for jobs
     */
    public void selectRoleForJobPostAd()
    {
        if(!isElementPresent("role"))
            Mapper.scroll("Role");
        Mapper.find(domFile,"role").click();
    }

    /**
     * function to validate role option list is display or not while select role option
     */
    public boolean validateRoleOptionsList()
    {
        return isElementPresent("roleOptionList");
    }


    /**
     * function to click on education button
     */
    public void selectEducationButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"educationButton");
        Mapper.find(domFile, "educationButton").click();
    }

    /**
     * function to click on Experience button
     */
    public void selectExperienceButton()
    {
        if (!isElementPresent("experienceButton"))
            Mapper.scroll("Experience");
        Mapper.find(domFile,"experienceButton").click();
    }

    /**
     * function to click on current salary button
     */
    public void selectCurrentSalaryButton()
    {
//        Mapper.scroll("Current Salary");
        Mapper.waitForElementToBeVisible(domFile,"currentSalary");
        Mapper.find(domFile,"currentSalary").click();
    }

    /**
     * function to validate selected role in post ad is display in edit ad
     */
    public boolean validateRoleFieldInEditAd()
    {
        return isElementPresent("roleOptionName");
    }

    public void clickOnPostFreeAd()
    {
        Mapper.waitForElementToBeVisible(domFile,"postFreeAdButton");
        Mapper.find(domFile, "postFreeAdButton").click();
    }

    public void setAdType(String adType)
    {
        Mapper.find(domFile, adType).click();
    }

    public void dontSyncPhonebook()
    {
        Mapper.find(domFile, "dontSyncPhonebook").click();
    }

    public void  clickOnPositiveOkButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"QuikrPositiveAkcnowledgwment");
        Mapper.find(domFile, "QuikrPositiveAkcnowledgwment").click();
    }

    /**
     * select subcategory on pap
     * @param i
     */
    public String selectSubCAtFromPap(int i)
    {
        if (isElementPresent("subcatTextid")) {
            String subcat = Mapper.finds(domFile, "subcatTextid").get(i).getText().toString();
            Mapper.finds(domFile, "subcatTextid").get(i).click();
            return subcat;
        }
        else {
            String subcat = Mapper.finds(domFile, "PostAdAsWantSubcatText").get(i).getText().toString();
            Mapper.finds(domFile, "PostAdAsWantSubcatText").get(i).click();
            return subcat;
        }
    }
    /**
     * on selecting subCat as commercial vechile . function to select vechile type
     */
    public void selectVechile()
    {
        Mapper.waitForElementToBeVisible(domFile, "vechileType");
        Mapper.find(domFile,"vechileType").click();

    }
    /**
     * select post As want type
     */
    public  void postAdAsWantType()
    {
        Mapper.waitForElementToBeVisible(domFile,"PostAdAsWant");
        Mapper.find(domFile,"PostAdAsWant").click();
    }
    /**
     * After sucessfully posting Ad click on continue Button to proced further to make ads premium
     */
    public void clickOnContinueToMAkeAdPemium()
    {
        Mapper.waitForElementToBeVisible(domFile,"validatepostAd");
        Mapper.find(domFile,"validatepostAd").click();
    }
    /**
     * validate user is redirected to make ad premium screen when clickon Continue afer posting Ads
     */
    public String MakeAdPremium()
    {

        String premium =Mapper.find(domFile,"PremiumPayment").getText().toString();
        Mapper.find(domFile,"PremiumPayment").click();
        return premium;
    }
    /**
     * validate payment screen after clicking on pproced to payment
     */
    public String ValidatePAymentScreen()
    {
        Mapper.waitForElementToBeVisible(domFile, "validattePremiumSreen");
        String paymentText=Mapper.find(domFile,"validattePremiumSreen").getText().toString();
        return paymentText;
    }
    /**
     * verify all payment methodas displayed at payment screen
     */

    public boolean validatePaymentMethods()
    {
        Mapper.waitForElementToBeVisible(domFile, "validattePremiumSreen");
        return (isElementPresent("paytm")&isElementPresent("NetBanking")&isElementPresent("debitCreditNew")&isElementPresent("Paytm"));
    }

    /**
     * select payment method as mobile billing
     */

    public void selectPaymentMethodAsMobileBilling()
    {
        Mapper.waitForElementToBeVisible(domFile, "mobileBillingNew");
        Mapper.find(domFile,"mobileBillingNew").click();
    }
    public void selectPaymentMethodAsCardPAymney()
    {
        Mapper.waitForElementToBeVisible(domFile,"debitCreditNew");
        Mapper.find(domFile,"debitCreditNew").click();
    }
    public void selectPaymentMethodAsNetBAnking()
    {
        Mapper.waitForElementToBeVisible(domFile,"NetBanking");
        Mapper.find(domFile,"NetBanking").click();
    }
    /**
     * select 1st premium plan
     */
    public void selectPlan()
    {
        Mapper.waitForElementToBeVisible(domFile,"selectPremiumPAln1");
        Mapper.find(domFile,"selectPremiumPAln1").click();
    }
    /**
     *
     * validate amount on payment
     */
    public String ValidatePAymentAmount()
    {
        Mapper.waitForElementToBeVisible(domFile, "Amountvalidation");
        String Amount=Mapper.find(domFile,"Amountvalidation").getText().replaceAll("\\D","");
        return Amount;
    }
    /**
     * click on pay Now button to proceed to payment
     */
    public  void clickOnPayNowButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"MakePayment");
        Mapper.find(domFile,"MakePayment").click();
    }
    /**
     * validate payment
     */

    public boolean validateMobileBillPayment()
    {
        Mapper.waitForElementToBeVisible(domFile, "mobileBillPaymentWebView");
        return (isElementPresent("mobileBillPaymentWebView"));
    }
    /**
     * validate CArd fields
     */
    public boolean validateCardFields()
    {
        return (isElementPresent("cardNumber")&isElementPresent("nameOnCard")&isElementPresent("cardVAlidityMonthYear")&&isElementPresent("cardCCV"));
    }
    /**
     * verify if online payment/netbanking is selected then list of popular banks is reflected
     */
    public String verifyPopularBanks()
    {
        String banks=Mapper.find(domFile,"SelectBank").getText().toString();
        return banks;
    }
    /**
     * select Payment method As paytm
     */
    public void selectPaymentMethodPaytm()
    {
        Mapper.waitForElementToBeVisible(domFile,"paytm");
        Mapper.find(domFile,"paytm").click();
    }
    /**
     * validate paytm payment screen
     */
    public boolean validatePaytmpaymentScreen()
    {
        return(isElementPresent("payTmMobile")&&isElementPresent("paytmOtp")&&isElementPresent("paytmPayNow"));
    }

    public void editAdTitle()
    {

//        String text = Mapper.find(domFile, "adTitle").getText();
//        int maxChars = text.length();
//        for (int i = 0; i < maxChars; i++)
        Mapper.find(domFile, "adTitle").clear();
        Mapper.find(domFile, "adTitle").sendKeys("Editing test ad automation" + new Timestamp(System.currentTimeMillis()));

    }

    /**
     *
     */
    public void  hideKeyboardExceptionHandler()
    {
        if (isElementPresent("QuikrPositiveAkcnowledgwment"))
            Mapper.find(domFile,"QuikrPositiveAkcnowledgwment").click();
    }

    /**
     * post ad as landlord i.e offer type
     */
    public void PostAdAsLandLordOfferType()
    {
        Mapper.waitForElementToBeVisible(domFile,"realEstateOfferTypeAd");
        Mapper.find(domFile,"realEstateOfferTypeAd").click();
    }

    /**
     * post ad as tenant i.e want type
     */
    public void PostAdAsTenantWantType()
    {
        Mapper.waitForElementToBeVisible(domFile,"realEstateWantTypeAd");
        Mapper.find(domFile,"realEstateWantTypeAd").click();
    }
    /**
     * post ad as Broker
     */
    public void PostAdAsBroker()
    {
        Mapper.waitForElementToBeVisible(domFile, "realEstateBroker");
        Mapper.find(domFile,"realEstateBroker").click();
    }
    /**
     * post ad as Builder
     */
    public void PostAdAsBuilder()
    {
        Mapper.waitForElementToBeVisible(domFile,"realEstateBuilder");
        Mapper.find(domFile,"realEstateBuilder").click();
    }

    /**
     * set number of rooms
     */
    public void clickOnNumberOfRooms()
    {
        if (!isElementPresent("realEstateNoOfRooms"))
            Mapper.scroll("No. of Rooms");
        Mapper.find(domFile,"realEstateNoOfRooms").click();
    }


    /**
     * set  Area
     */
    public void clickOnSelectArea(String area)
    {
        Mapper.waitForElementToBeVisible(domFile,"realEstateArea");
        Mapper.find(domFile,"realEstateArea").sendKeys(area);
    }
    /**
     * set status furnished
     */
    public void clickOnFurnished()
    {
        if (!isElementPresent("realEstateFurnisheh"))
            navigateBack();
        Mapper.find(domFile,"realEstateFurnisheh").click();
    }
    /**
     * check if after using navigate back user is on same page to different
     */

    public void handleNavigationBAckAfterSettingPrice()
    {
        if (!isElementPresent("realEstateNoOfRooms"))
            Mapper.find(domFile,"papNextpage").click();
    }

    /**
     * validate role field is displayed after posting ad for JOBS
     */
    public boolean validateRoleAfterPostingAd(String postAdRole)
    {
        String role=null;
        Mapper.waitForElementToBeVisible(domFile,"jobsAdSuccessPageLeftText");
        // extracting index of element Role on view Ad page
        List<WebElement> elements=Mapper.finds(domFile,"jobsAdSuccessPageLeftText");
        List<String> list = new ArrayList<String>();
        for (WebElement e :elements)
        {
            list.add(e.getText());
        }
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).contains("Role"))
            {
                int retval = i;
                String roleRightSideText = Mapper.finds(domFile, "jobsAdSuccessPageRightText").get(retval).getText();
                role=roleRightSideText;
            }

        }
        return (role.equalsIgnoreCase(postAdRole));
    }

    /**
     * check all subCategories for jobs are present
     */
    public List checkJobsSubCategories()
    {
        Mapper.waitForElementToBeVisible(domFile,"papNewCat");
        List<WebElement> subcatList = Mapper.finds(domFile,"papNewCat");
        List<String> list = new ArrayList<String>();
        for (WebElement e : subcatList) {
            list.add(e.getText());
        }
        List<String> Jobssubcategories = new ArrayList<String>();
        for(int i=0; i<list.size(); i++)
        {
            String SubcatValue = (list.get(i));
            Jobssubcategories.add(SubcatValue);
        }
        return Jobssubcategories;
    }
    /**
     * skip post ad helper to move to make ad premium
     */
    public void skipPostAdHelper()
    {
        if (isElementPresent("skiplookingForExchange"))
            Mapper.find(domFile,"skiplookingForExchange").click();
    }
    /**
     * validate repost ad with edit success/failure msg
     */
    public boolean validateRepostAdSuccessMsg()
    {
        Mapper.waitForElementToBeVisible(domFile,"repostadMsg");
        String msg=Mapper.find(domFile,"repostadMsg").getText();
        if (msg.contains("Try again"))
            return false;
        else
            return true;
    }
    public String validateRepostAdSuccessMsgtext()
    {
        Mapper.waitForElementToBeVisible(domFile,"repostadMsg");
        String msg=Mapper.find(domFile,"repostadMsg").getText();
        return msg;
    }
    /**
     * for escrow Ads opt for QuikrWareHouse
     */
    public void
    selectQuikrWareHouse()
    {
        if (!isElementPresent("quikrWarehouseCheckBox"))
            navigateBack();
        Mapper.find(domFile,"quikrWarehouseCheckBox").click();
    }
    /**
     * select minimum price same as escrow price
     */
    public void selectMinimumPriceSameAsProductPrice()
    {
        Mapper.waitForElementToBeVisible(domFile,"escrowSamePrice");
        Mapper.find(domFile,"escrowSamePrice").click();
    }
    /**
     * select Escrow mobile Brand
     */
    public void selectMobileBrand()
    {
        Mapper.waitForElementToBeVisible(domFile,"EscrowMobileBrand");
        Mapper.find(domFile,"EscrowMobileBrand").click();
    }
    /**
     * select escrow mobile Os
     */
    public void selectMobileOs()
    {

        Mapper.waitForElementToBeVisible(domFile,"EcrowMobileOs");
        Mapper.find(domFile,"EcrowMobileOs").click();
    }
    /**
     * select mobile sim
     */
    public void selectMobileSim()
    {
        Mapper.waitForElementToBeVisible(domFile,"EscorwMobileSim");
        Mapper.find(domFile,"EscorwMobileSim").click();
    }
    /**
     *select quikr storage
     */
    public void selectQuikrStorage()
    {
        Mapper.waitForElementToBeVisible(domFile,"selectWareHouse");
        Mapper.find(domFile,"selectWareHouse").click();
    }
    /**
     * proceed to provide persona; oinfo for quikr storage
     */
    public void proceedToProvidePersonalInfo()
    {
        Mapper.waitForElementToBeVisible(domFile,"sellThrouhQuikrOkButton");
        Mapper.find(domFile,"sellThrouhQuikrOkButton").click();
    }
    /**
     * provide seller name
     */
    public void setSellerName(String name)
    {
        Mapper.waitForElementToBeVisible(domFile, "escrowPickUpName");
        Mapper.find(domFile,"escrowPickUpName").sendKeys(name);
    }
    /**
     * provide seller Email
     */
    public void setSellerEmail(String email)
    {
        Mapper.waitForElementToBeVisible(domFile,"escrowPickupEmail");
        Mapper.find(domFile,"escrowPickupEmail").sendKeys(email);
    }
    /**
     * provide seller pin
     */
    public void setSellerPinCode(String pin)
    {
        Mapper.waitForElementToBeVisible(domFile,"escrowpickUpPIN");
        Mapper.find(domFile,"escrowpickUpPIN").sendKeys(pin);
    }
    /**
     * provide seller address
     */
    public void setSellerAddress(String Address)
    {
        Mapper.waitForElementToBeVisible(domFile,"escrowPickUpSellerAddress");
        Mapper.find(domFile,"escrowPickUpSellerAddress").sendKeys(Address);

    }
    /**
     * provide seller phone number
     */
    public void setSellerPhoneNUmber(String phone)
    {

        if(!isElementPresent("escrowPickUpPhone"))
            navigateBack();
        Mapper.find(domFile,"escrowPickUpPhone").sendKeys(phone);
    }
    /**
     * submit seller pickup details
     */
    public void submitSellerPickUpInfo()
    {
        if (!isElementPresent("escrowSubmitPickupInfo"))
            navigateBack();
        Mapper.find(domFile,"escrowSubmitPickupInfo").click();
    }
    /**
     * provide seller Account details NAme
     */
    public void setAccountHolderName(String name)
    {
        Mapper.waitForElementToBeVisible(domFile,"escrowAccountHolderName");
        Mapper.find(domFile,"escrowAccountHolderName").sendKeys(name);
    }
    /**
     * provide seller account number
     */
    public void setSellerAccountNumber(String accountNumber)
    {
        Mapper.waitForElementToBeVisible(domFile,"escrowAccountNumber");
        Mapper.find(domFile,"escrowAccountNumber").sendKeys(accountNumber);
    }
    /**
     * re enter seller account number
     */
    public void reEnterAccountNumber(String accountNumber)
    {
        Mapper.waitForElementToBeVisible(domFile,"escrowReEnterAccountNumber");
        Mapper.find(domFile,"escrowReEnterAccountNumber").sendKeys(accountNumber);
    }
    /**
     * bank IFSC code
     */
    public void setBankIFSCCode(String ifsc)
    {
        Mapper.waitForElementToBeVisible(domFile,"escrowIFSC");
        Mapper.find(domFile,"escrowIFSC").sendKeys(ifsc);
    }
    /**
     * submit bank details
     */
    public void submitBankDetails()
    {
        if (!isElementPresent("submitAccountDetails"))
            navigateBack();
        Mapper.find(domFile,"submitAccountDetails").click();
    }
    /**
     * click on select city from PAP
     */
    public void selectCityPAP()
    {
        Mapper.find(domFile,"spinner").click();

    }
    /**
     * wait for locality to load
     */
    public void waiyForLocalityToLoad()
    {
        Mapper.waitForElementToBeVisible(domFile, "localityWait");
    }

    /**
     * validate escrow benefitLink is present after posting ad
     */
    public boolean escrowBenefitLinkIsPresent()
    {
        return isElementPresent("escrowBenifitLink");
    }
    /**
     * Verify Warehouse strip is displayed in Post Ad for High Touch cities, Escrow categories
     */
    public boolean wareHouseStorageIsPresent()
    {
        return isElementPresent("quikrWarehouseCheckBox");
    }
    /**
     * select photos from galery
     */
    public void selectImageFromGalery(int i)
    {
        Mapper.waitForElementToBeVisible(domFile,"galleryImage");
        Mapper.finds(domFile,"galleryImage").get(i).click();
    }
    /**
     * click on update image from gallery
     */
    public void uploadImageFromGallery()
    {
        Mapper.waitForElementToBeVisible(domFile,"updateImages");
        Mapper.find(domFile, "updateImages").click();
    }
    /**
     * check if only 8 images selection are allowed
     */
    public  String checkIfImageSelected(int i)
    {
        String checked=Mapper.finds(domFile, "galleryImageCheckBox").get(i).getAttribute("checked");
        System.out.println(checked);
        return checked;
    }
    /**
     * choose option select image from gallery
     */
    public void selectgallery()
    {
        Mapper.waitForElementToBeVisible(domFile,"selectImageFromGAllery");
        Mapper.find(domFile,"selectImageFromGAllery").click();
    }
    public void clickOnUploadImage()
    {
        Mapper.waitForElementToBeVisible(domFile,"imageUpLoadPAp");
        Mapper.find(domFile, "imageUpLoadPAp").click();

    }
    /**
     * get category on PAP
     */
    public String getPapCategory()
    {
        Mapper.waitForElementToBeVisible(domFile,"PapCategory");
        String cat=Mapper.find(domFile,"PapCategory").getText();
        return cat;
    }
    /**
     * provide escrow address post ad
     */
    public void papEscrowCategoryAddress(String Address)
    {
        if (!isElementPresent("escrowPostAdAddress"))
            Mapper.scroll("Address");
        Mapper.find(domFile,"escrowPostAdAddress").sendKeys(Address);
    }

    /**
     * provide escrow address post ad
     */
    public void papEscrowCategorypin(String Address)
    {
        if (!isElementPresent("escrowPostAdPin"))
            Mapper.scroll("escrowPostAdPin");
        Mapper.find(domFile,"escrowPostAdPin").sendKeys(Address);
    }
    public void clickOnEscrowEscreenSize()
    {
        Mapper.find(domFile,"escrowSreenSize").click();
    }
    public void papEscrowSelectDesktop()
    {
        if (!isElementPresent("Desktopescrow"))
            Mapper.scroll("Desktop");
        Mapper.find(domFile,"Desktopescrow").click();
    }
    public boolean validatePostAdEscrow()
    {
        Mapper.waitForElementToBeVisible(domFile, "escrowPostadValidate");
        if (isElementPresent("escrowPostadValidate"))
            return true;
        return false;
    }

    public boolean validateMinPriceInDb(String AdId){
        Database db = new Database();
        if(db.getAdDescription(AdId).contains("Reserved_Price")){
            return true;
        }
        else return false;
    }

    public boolean validatewarehouseEnableInDB(String AdId){
        Database db = new Database();
        if (db.getAdDescription(AdId).contains("WareHouse_Enabled:1")) {
            return true;
        }
        else return false;
    }
    /**
     * validate redirection to payment screen after postAd
     */
    public  boolean validateRTedirectionTopaymentScreenAfterPostAd()
    {
        return isElementPresent("validateRedirectionToPaymentScreen");
    }

    public boolean validateMObileBillPayScreen()
    {
        return isElementPresent("mobileBillPayNow");
    }
    public boolean validateNetBankingBillPayScreen()
    {
        return isElementPresent("netBankingPAy");
    }
    public boolean validatePaytmBillPayScreen()
    {
        return(isElementPresent("payTmMobile")&&isElementPresent("paytmOtp")&&isElementPresent("paytmPayNow"));
    }
    public boolean validatecreditDebitBillPayScreen()
    {
        return (isElementPresent("cardVAlidityMonthYear")&&isElementPresent("nameOnCard"));
    }
    public  void clickOnPayNowButtonMobileBill()
    {
        Mapper.waitForElementToBeVisible(domFile,"mobileBillPayNow");
        Mapper.find(domFile,"mobileBillPayNow").click();
    }

    public void scrollDownOnPapPage()
    {
        Mapper.VerticalSwipe(domFile, "Individual", "PapCategory");
    }
    public void editAdTitle(String title)
    {
        Mapper.scroll("Enter Ad Title");
        Mapper.find(domFile,"adTitle").clear();
        Mapper.find(domFile,"adTitle").sendKeys(title);

    }
    /**
     * click on Ecxhange cars after posting ads
     */
    public void clickOnExchangeCarsAfterPostAd()
    {
        Mapper.waitForElementToBeVisible(domFile, "exchangeCArs", 20);
        Mapper.find(domFile,"exchangeCArs").click();
    }
    /**
     * select used cars to exchange
     */
    public void clickOnusedcarsForExchangeAfterPostAd()
    {
        Mapper.waitForElementToBeVisible(domFile,"usedCArs");
        Mapper.find(domFile,"usedCArs").click();
    }
    /**
     * select exchange Brand
     */
    public void selectExchangeBrand()
    {
        Mapper.waitForElementToBeVisible(domFile,"exchangeAndBuyBrand");
        Mapper.find(domFile,"exchangeAndBuyBrand").click();
    }
    /**
     * submit exchange
     */
    public void clickOnDoneBUttonforExchangeCArs()
    {
        Mapper.find(domFile,"findExchangeOrNewCar").click();
    }
    /**
     * validate used cars
     */
    public boolean validateOnOnselectingUsedCarsUserIsRedirectedToUSedCarsPAge()
    {
        return (isElementPresent("numberOfExchangeCArsAvailable")&&isElementPresent("nameOfCarAvailableForExchange")&&isElementPresent("exchangeDetails"));
    }
    /**
     * clcik on explore buttom to view used cars
     */
    public void clickonexploreButton()
    {
        Mapper.waitForElementToBeVisible(domFile,"expolreAds");
        Mapper.find(domFile,"expolreAds").click();
    }
    /**
     * skip ads
     */
    public void skipAds()
    {
        if (isElementPresent("closeAds"))
            Mapper.find(domFile,"closeAds").click();
    }
    /**
     * fetch post ad subcat
     */
    public List postAdSubCat() {
        //Storing allMenu elements  in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, "papNewCat");
        List<String> list = new ArrayList<String>();
        for (WebElement e : options)
        {
            list.add(e.getText());
        }
        explicitWait(3);
        int downElemet = Mapper.find(domFile, "papNewCat").getLocation().getY();
        int upElement = Mapper.find(domFile, "papCategoryHeader").getLocation().getY();
        verticalSwipeWithCordinates(downElemet + 20, upElement);
        verticalSwipeWithCordinates(downElemet + 20, upElement);
        List<WebElement> options1 = Mapper.finds(domFile, "papNewCat");
        List<String> list1 = new ArrayList<String>();
        for (WebElement e : options1)
        {
            list1.add(e.getText());
        }
//merging tow lists in oreder and removing duplicates
        for (int j = 0; j < list1.size(); j++)
        {
            if (!(list.contains(list1.get(j))))
                list.add(list1.get(j));
        }
        System.out.println(list);
        return list;
    }

    public boolean validateAttributeMissingMessageForEmail()
    {
        Mapper.scroll("POST YOUR AD");
        Mapper.find(domFile, "submitAd").click();
        Mapper.waitForElementToBeVisible(domFile, "papMissingAttributeMsg");
        return isElementPresent("papMissingAttributeMsg");
    }
    /**
     * swipe to top on edit ad page
     */
    public void swipeToTopOnEditAdPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "adTitle");
        int downElemet = Mapper.find(domFile, "papCategoryHeader").getLocation().getY();
        int upElement = Mapper.find(domFile, "adTitle").getLocation().getY();
        verticalSwipeWithCordinates(downElemet + 170, upElement);

    }
    /**
     * fetch Attributes of post AD
     */
    public List postAdAttributes(String cat)
    {

        explicitWait(2);
        List<WebElement>postAdAttribute=Mapper.finds(domFile, "radoiElement");
        List<String>attributes=new ArrayList<>();
        for (WebElement e:postAdAttribute)
        {
            attributes.add(e.getText());
        }
        return attributes;
    }
    /**
     * validate ware house is present
     */
    public boolean wareHouseOptionISPresent()
    {
        return (isElementPresent("quikrWarehouseCheckBox"));
    }
    /**
     * get X&Y cordinates for swaping on post Ad
     */
    public Integer[] cordinatesForVerticalSwipeOnPAP()
    {
        Mapper.waitForElementToBeVisible(domFile, "papCategoryHeader");
        int y1=Mapper.find(domFile,"papCategoryHeader").getLocation().getY();
        int y2=Mapper.find(domFile,"postAdImageCamTitle").getLocation().getY();
        Integer[]cordinate={y1,y2};
        return cordinate;

    }
    /**
     * real estate attribute
     */
    public boolean validateRealestateAttribute(String validationData)
    {
        return (Mapper.finds(domFile,"iNeedAJob").get(1).getText().trim().equalsIgnoreCase(validationData));
    }
    /**
     * validate postAd success card 1
     */
    public boolean validatePostAdSuccessPremiumCard()
    {
        return isElementPresent("postAdSuccesPremiumCard");
    }

    /**
     * get X&Y cordinates for swaping on post Ad
     */
    public Integer[] cordinatesForHorizontalSwipeOnPAP()
    {
        Mapper.waitForElementToBeVisible(domFile,"skiplookingForExchange",20);
        int y=Mapper.find(domFile,"skiplookingForExchange").getLocation().getY();
        int x1=Mapper.find(domFile,"skiplookingForExchange").getLocation().getX();
        int x2=Mapper.find(domFile,"exchangeCArs").getLocation().getX();
        Integer[]cordinate={y,x1,x2};
        return cordinate;

    }
    /**
     * validate on posting as 1st carsoul card
     */
    public boolean validateExchangeCarousalCard()
    {
        return (isElementPresent("skiplookingForExchange")&&isElementPresent("exchangeCArs"));
    }
    /**
     * get X&Y cordinates for swaping on post Ad
     */
    public Integer[] cordinatesForHorizontalSwipecarousalPrremiCardOnPAP()
    {
        Mapper.waitForElementToBeVisible(domFile,"premium3rdPlan",20);
        int y=Mapper.find(domFile,"premium3rdPlan").getLocation().getY();
        int x1=Mapper.find(domFile,"premium3rdPlan").getLocation().getX();
        int x2=Mapper.find(domFile,"premium1stPlan").getLocation().getX();
        Integer[]cordinate={y,x1,x2};
        return cordinate;

    }
    /**
     * validate on clicking yes button user is redirected to exchangePage
     */
    public boolean onSelectingYesButtonUserIsRedirectedToExchangePage()
    {
        return(isElementPresent("exchangeDetails"));
    }
    /**
     * fetch elements into list for validation
     */
    public List fetchElementsIntoList(String DomElement)
    {
        //Storing allMenu elements  in ARRAy LISt

        List<WebElement> options = Mapper.finds(domFile, DomElement);
        List<String> list = new ArrayList<String>();
        for (WebElement e : options)
        {
            list.add(e.getText());
        }

        return list;
    }

    public void clickOnCapturePhoto()
    {
        Mapper.waitForElementToBeVisible(domFile,"capturePhoto");
        Mapper.find(domFile,"capturePhoto").click();
    }

    public void tapOnCropImage()
    {
        Mapper.waitForElementToBeVisible(domFile, "cropImage");
        Mapper.find(domFile, "cropImage").click();
        Mapper.find(domFile, "done").click();
    }

    public void tapOnRotateImage()
    {
        Mapper.waitForElementToBeVisible(domFile, "rotateImage");
        Mapper.find(domFile, "rotateImage").click();
    }

    public void tapOnBeautifyImage()
    {
        Mapper.waitForElementToBeVisible(domFile, "beautifyImage");
        Mapper.find(domFile, "beautifyImage").click();
        Mapper.find(domFile, "doneOption").click();
    }

    public String getSelectedCity()
    {
        Mapper.waitForElementToBeVisible(domFile, "spinner");
        return Mapper.find(domFile, "spinner").getText();
    }

}
