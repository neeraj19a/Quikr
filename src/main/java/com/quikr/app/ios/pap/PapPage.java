package com.quikr.app.ios.pap;


import com.quikr.app.android.AppAndroidPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 30/9/15.
 */
public class PapPage extends AppAndroidPageBase {
    public PapPage(AppiumDriver driver){super(domFile,driver);}
    private static final String domFile = getProperties().get("IOS_PAP_DOM_FILE");


    /**
     * function to click on Next
     */
    public void clickOnNextButton()
    {
        Mapper.find(domFile,"nextButton").click();
    }

    /**
     * function to select locality in ad post
     */
    public void clickOnLocalityInAdPost()
    {
        Mapper.waitForElementToBeVisible(domFile,"selectLocality");
        Mapper.find(domFile,"selectLocality").click();
    }

    /**
     * function to select locality name
     */
    public void selectLocalityName()
    {
        Mapper.find(domFile,"localityName").click();
    }

    /**
     * function to provide ad title
     */
    public void setAdTitle(String str)
    {
        //Mapper.waitForElementToBeVisible(domFile,"adTitle");
        Mapper.waitForElementToBeClickable(domFile,"adTitle");
        Mapper.find(domFile,"adTitle").sendKeys(str);
    }

    /**
     * function to provide description
     */
    public void setAdDescription(String str)
    {
        Mapper.waitForElementToBeVisible(domFile,"adDescription");
        if(!isElementPresent("adDescription"))
        {
            Mapper.scroll("adDescription");
        }
        Mapper.find(domFile,"adDescription").clear();
        Mapper.find(domFile,"adDescription").sendKeys(str);
    }

    /**
     * function to select you are type
     */
    public void selectYouAre(String youRType)
    {
        Mapper.scroll("Individual");
        Mapper.find(domFile,youRType).click();
    }

    /**
     * function to select conditions type
     */
    public void selectCondition(String conditionType)
    {
        Mapper.find(domFile,"conditionTypeName").click();
       // Mapper.find(domFile,conditionType).click();
    }

    /**
     * function to select product type
     */
    public void selectProductType(String productType)
    {
        Mapper.find(domFile,productType).click();
    }

    /**
     * function click on brand name
     */
    public void clickOnBrandName()
    {
        Mapper.find(domFile,"brandName").click();
    }

    /**
     * function to select brand name
     */
    public void selectBrandName(String brandNameOption)
    {
        System.out.println(Mapper.find(domFile,brandNameOption).getText());
        Mapper.find(domFile,brandNameOption).click();
    }

    /**
     * function to click on apply button
     */
    public void selectApplyButton()
    {
        Mapper.find(domFile,"applyButton").click();
    }

    /**
     * function to click on optional details
     */
    public void clickOnOptionalDetails()
    {
        Mapper.find(domFile,"optionalDetails").click();
    }

    /**
     * function to click on optional details for mobilesAndTablets category
     */
    public void clickOnMobilesOptionalDetails()
    {
        Mapper.find(domFile,"optionalDetailsForMobiles").click();
    }

    /**
     * function to provide price
     */
    public void setPrice(String priceAmount)
    {
        //Mapper.scroll("price");
        Mapper.find(domFile,"price").sendKeys(priceAmount);
    }

    /**
     * function to provide price
     */
    public void setPriceForElectronicsInPostAd(String priceAmount)
    {
        //Mapper.scroll("price");
        Mapper.find(domFile,"priceForElectronics").sendKeys(priceAmount);
    }

    /**
     * function to set price for mobile and tablets category
     */
    public void setPriceForMobileCategory(String price)
    {
      //  Mapper.scroll("mobilePrice");
        Mapper.find(domFile,"mobilePrice").sendKeys(price);
    }

    /**
     * function to click on screen size
     */
    public void clickOnScreenSize()
    {
        Mapper.find(domFile,"screenSize").click();
    }

    /**
     * function to select screen size
     */
    public void selectScreenSize()
    {
        Mapper.find(domFile,"screenSizeName").click();
    }

    /**
     * function to click on Also includes button
     */
    public void clickOnAlsoIncludes()
    {
        Mapper.waitForElementToBeVisible(domFile,"alsoInclude");
        Mapper.find(domFile,"alsoInclude").click();
    }

    /**
     * function to select Also included option name
     */
    public void selectAlsoIncludesOptionName(String alsoIncludesOptionNameForElectronicsCategory)
    {
        Mapper.waitForElementToBeVisible(domFile,alsoIncludesOptionNameForElectronicsCategory);
        Mapper.find(domFile,alsoIncludesOptionNameForElectronicsCategory).click();
    }

    /**
     * function to provide name
     */
    public void setName(String name)
    {
        Mapper.waitForElementToBeVisible(domFile,"userName");
        Mapper.find(domFile,"userName").clear();
        Mapper.find(domFile,"userName").sendKeys(name);
    }

    /**
     * function to provide email id
     */
    public void setEmail(String emailName)
    {
        Mapper.find(domFile,"email").clear();
        Mapper.find(domFile,"email").sendKeys(emailName);
    }

    /**
     * function to provide mobile no
     */
    public void setMobileNo(String mbNo)
    {
        Mapper.find(domFile,"mobileNo").clear();
        Mapper.find(domFile,"mobileNo").sendKeys(mbNo);
    }

    /**
     * function to click on post free ad button
     */
    public void selectPostAdButton()
    {
        Mapper.hideKeyboard();
        Mapper.waitForElementToBeVisible(domFile,"postAdButton");
        Mapper.find(domFile,"postAdButton").click();
    }

    /**
     * function to validate post free ad
     */
    public String validatePostAd()
    {
        Mapper.waitForElementToBeVisible(domFile,"successMsg");
        String str=Mapper.find(domFile,"successMsg").getText();
        return str;
    }

    /**
     * function to click on next button
     */
    public void selectNextButtonForcibly() {
//        List<WebElement> cells = Mapper.finds(domFile, "cellList");
//        //findElementsByClassName("UIATableCell");
//        System.out.println(cells);
//        System.out.println(cells.size());
//       // WebElement lastCell = cells.get(cells.size() - 1);
//        System.out.println(Mapper.finds(domFile,"requiredName").get(cells.size() - 1));
//        WebElement nxtButton= Mapper.finds(domFile, "requiredName").get(cells.size()-1);
//        nxtButton.click();
//        WebElement nextButton = Mapper.find(domFile,"requiredname");
//        WebElement lastCell = cells.get(cells.size() - 1);
        // lastcell.findElements(By.clasnames("uiabutton"))
        //By.className("UIAButton")
        // nextButton.click();

//        List <WebElement> cells = driver.findElementsByClassName("UIATableCell");
//        WebElement lastCell = cells.get(cells.size()-1);
//        System.out.println(lastCell.getAttribute("name"));
//        WebElement nextButton = lastCell.findElement(By.className("UIAButton"));
//        nextButton.click();
        List<WebElement> cells = Mapper.finds(domFile, "cellList");

        System.out.println(cells);
        System.out.println(cells.size());
        System.out.println(cells.get(cells.size() - 1).findElement(By.className("UIAButton")).getText());
        cells.get(cells.size() - 1).findElement(By.className("UIAButton")).click();

 //       WebElement lastCell = cells.get(cells.size() - 1);
//        List<WebElement> childs = Mapper.findChilds(lastCell);
//
//        System.out.println(childs.size());
//
//        for (int i = 0; i < childs.size(); i++) {
//            System.out.println("inside loop");
//            System.out.println(childs.get(i));
//            try {
//
//                if (childs.get(i).getAttribute("class").equalsIgnoreCase("UIAButton")) {
//                    System.out.println(childs.get(i));
//                    childs.get(i).click();
//                    break;
//                }
//            } catch (Exception e) {
//
//            }
//        }
    }

    /**
     * function to validate incomplete ad titile
     */
    public boolean validatePostAdWithIncompleteAdTitle()
    {
        return isElementPresent("msgAfterIncompleteAdTitle");
    }

    /**
     * function to validate post ad with incomplete  ad description
     */
    public boolean validatePostAdWithIncompleteAdDescriptions()
    {
        return isElementPresent("msgAfterIncompleteAdDescriptions");
    }

    /**
     * function to click on model
     */
    public void clickOnModel()
    {
        Mapper.find(domFile,"model").click();
    }

    /**
     * function to select model name
     */
    public void selectModelOptionName()
    {
        Mapper.find(domFile,"modelOptionName").click();
    }

    /**
     * function to click on Operating system
     */
    public void clickOnOperatingSystem()
    {
        Mapper.find(domFile,"operatingSystem").click();
    }

    /**
     * function to select operating system option name
     */
    public void selectOperatingOptionName()
    {
        Mapper.find(domFile,"operatingOptionName").click();
    }

    /**
     * function to click on NoOfSims
     */
    public void clickOnNoOfSims()
    {
        Mapper.find(domFile,"noOfSims").click();
    }

    /**
     * function to select no of sims option
     */
    public void selectNoOfSimsOption()
    {
        Mapper.find(domFile,"noOfSimsOption").click();
    }

    /**
     * function to click on optional detail for mobile
     */
    public void clickOnOptionalDetailForMobiles()
    {
        Mapper.find(domFile,"optionalDetailsForMobiles").click();
    }

    /**
     * function to click on view your ad
     */
    public void clickOnViewYourAdButton()
    {
        Mapper.waitForElementToBeInvisible(domFile,"viewYourAdButton");
//        System.out.println(Mapper.finds(domFile, "viewYourAdButton").get(0).getText());
//        System.out.println(Mapper.finds(domFile,"viewYourAdButton").get(1).getText());
//        System.out.println(Mapper.finds(domFile,"viewYourAdButton").get(2).getText());
//        System.out.println(Mapper.finds(domFile, "viewYourAdButton").get(3).getText());
//        System.out.println(Mapper.finds(domFile, "viewYourAdButton").get(4).getText());
//        System.out.println(Mapper.finds(domFile, "viewYourAdButton").get(5).getText());
//        System.out.println(Mapper.finds(domFile, "viewYourAdButton").get(6).getText());
//        Mapper.finds(domFile, "viewYourAdButton").get(2).click();
        List<WebElement> buttons = Mapper.finds(domFile, "viewYourAdButton");
        WebElement viewYourAdButton = null;
        System.out.println(buttons);
        System.out.println(buttons.size());
        for (int i=0;i<buttons.size();i++)
        {
            if (buttons.get(i).getText().equalsIgnoreCase("View Your Ad"))
            {
                System.out.println(buttons.get(i).getText());
                viewYourAdButton = buttons.get(i);
                break;
            }
        }
        viewYourAdButton.click();
    }

    /**
     * function to select save changes button
     */
    public void selectSaveChangesButton()
    {
        Mapper.scroll("Save Changes");
        Mapper.find(domFile,"saveChangesButton").click();
    }

    /**
     * function to validate edit ad from preview screen
     */
    public boolean validateEditAdFromPreviewScreen()
    {
        Mapper.waitForElementToBeVisible(domFile,"msgAfterEditAdSubmission");
        return isElementPresent("msgAfterEditAdSubmission");
    }

    /**
     * function validate post ad with out locality
     */
    public boolean validatePostAdWithOutLocality()
    {
//        Mapper.waitForElementToBeVisible(domFile,"msgAfterClickOnNextButton");
        return isElementPresent("msgAfterClickOnNextButton");
    }


    /**
     * function to click on mor button in my ads
     */
    public void clickOnMoreButtonInMyAds(int i)
    {
        //Mapper.find(domFile,"moreButtonInMyAds").click();
        Mapper.findAndReplace(domFile, "moreButtonInMyAds", new String[]{Integer.toString(i)}).click();
    }

    /**
     * function to select delete ad button
     */
    public void selectDeleteAdButton()
    {
        Mapper.find(domFile,"deleteAdButton").click();
    }
    /**
     * function to delete button
     */
    public void selectDeleteButton()
    {
//        Mapper.find(domFile,"clickOnTransparentScreen").click();
//        MobileElement cell = (MobileElement) Mapper.find(domFile,"myAdUIATableList");
//        String cellName = cell.getAttribute("name");
//        cell.swipe(SwipeElementDirection.LEFT, 1000);
//        Point location = cell.getLocation();
//        Dimension size = cell.getSize();
//        Point deleteBtnLocation = new Point (location.getX() + size.getWidth()-10, location.getY() + size.getHeight()/2);
//        Mapper.tap(1, deleteBtnLocation.getX(), deleteBtnLocation.getY(), 1000);
//        Mapper.find(domFile,"deleteButton").click();
//       // Mapper.waitForElementToBeVisible(domFile,"okButton");

        Mapper.find(domFile,"deleteButton").click();
    }

    /**
     * function to validate deleted ad
     */
    public boolean validateDeletedAd()
    {
       // Mapper.waitForElementToBeVisible(domFile,"okButton");
        Mapper.waitForElementToBeVisible(domFile,"msgAfterClickOnDeleteButton");
        return isElementPresent("msgAfterClickOnDeleteButton");
    }

    /**
     * function to validate active ad status correctly reflected
     */
    public boolean validateActiveAdStatusCorrectlyReflected()
    {
       // Mapper.find(domFile,"clickOnTransparentScreen").click();
        List<WebElement> myadsList = Mapper.finds(domFile,"myAdCellList");
        Boolean found = false;
        for(int i=0; i<myadsList.size(); i++){
            List<WebElement> texts = myadsList.get(i).findElements(By.className("UIAStaticText"));
            for(int j=0; j<texts.size();j++){
                if(texts.get(j).getText().contains("Active")){
                    found = true;
                    break;
                }
            }
            if(found){
                break;
            }
        }
        return found;
    }


    /**
     * function to click o no of rooms for post an ad for realEstate
     */
    public void clickOnNoOfRooms()
    {
        Mapper.scroll("noOfRooms");
        Mapper.find(domFile,"noOfRooms").click();
    }

    /**
     * function to select no of rooms
     */
    public void selectNoOfRooms()
    {
        Mapper.find(domFile,"selectNoOfRooms").click();
    }

    /**
     * function to click on area sq ft
     */
    public void clickOnAreaSqFt()
    {
        Mapper.scroll("areaSqFt");
        Mapper.find(domFile,"areaSqFt").click();
    }

    /**
     * function to select area sq ft
     */
    public void selectAreaSqFt(String str)
    {
      //  Mapper.find(domFile,"selectAreaSqFt").click();
        Mapper.find(domFile,"areaSqFt").sendKeys(str);
    }

    /**
     * function to click on ad type
     */
    public void selectAdTypeForRealEstate()
    {
        Mapper.find(domFile,"adTypeName").click();
    }

    /**
     * function to validate if email id is not providing at the time of ad post
     */
    public boolean validateEmailNotProvided()
    {
        return isElementPresent("msgInPopUp");
    }

    /**
     * function to click on year
     */
    public void clickOnYear()
    {
        Mapper.find(domFile,"clickOnYear").click();
    }

    /**
     * function to click on search bar
     */
    public void clickOnSearchBarForYearSelection()
    {
        Mapper.find(domFile,"searchBar").click();
    }

    /**
     * function to set any required text at time of ad post in serach box
     */
    public void setRequiredTextInSearchBoxAtAdPost(String str)
    {
        Mapper.find(domFile,"searchRequiredTextInSearchBar").sendKeys(str);
    }

    /**
     * function to select required text same as in search box at the time of ad post
     */
    public void selectRequiredTextAsInSearchBox(int i)
    {
        Mapper.findAndReplace(domFile, "requiredTextOptionClick", new String[]{Integer.toString(i)}).click();
       // Mapper.find(domFile,"yearName").click();
    }

    /**
     * function to set the kms driven
     */
    public void setKmsDriven(String kmsDriven)
    {
        Mapper.find(domFile,"clickOnKms").sendKeys(kmsDriven);
    }

    /**
     * function to click on optional details for car
     */
    public void clickOnOptionalDetailsForCars()
    {
        Mapper.find(domFile,"clickOnOptionalDetailsForCars").click();
    }

    /**
     * function to set the price for car
     */
    public void setPriceForCars(String priceAmount)
    {
        Mapper.find(domFile,"carPrice").sendKeys(priceAmount);
    }

    /**
     * function to set the price for bikes
     */
    public void setPriceForBikes(String priceAmount)
    {
        Mapper.find(domFile,"bikePrice").sendKeys(priceAmount);
    }

    /**
     * function to click on Variant or version
     */
    public void clickOnVariantOrVersion()
    {
        Mapper.find(domFile,"clickOnVariantOrVersion").click();
    }

    /**
     * function to click on color
     */
    public void clickOnColor()
    {
        Mapper.find(domFile,"clickOnColor").click();
    }

    /**
     * function to click on fuel type
     */
    public void clickOnFuelType()
    {
        Mapper.scroll("Fuel Type");
        Mapper.find(domFile,"clickOnFuelType").click();
    }

    /**
     * function to click on no of owners
     */
    public void clickOnNoOfOwners()
    {
        Mapper.find(domFile,"clickOnNoOfOwners").click();
    }

    /**
     * function to click on role in job
     */
    public void clickOnRoleInJob()
    {
        Mapper.find(domFile,"clickOnRole").click();
    }

    /**
     * function to click on education
     */
    public void clickOnEducationInJobs()
    {
        Mapper.find(domFile,"clickOnEducation").click();
    }

    /**
     * function to click on experience
     */
    public void clickOnExperience()
    {
        Mapper.waitForElementToBeVisible(domFile,"clickOnExperience");
        Mapper.find(domFile,"clickOnExperience").click();
    }

    /**
     * function to click on optional details for bikes
     */
    public void clickOnOptionalDetailsForBikes()
    {
        Mapper.find(domFile,"clickOnOptionalDeatailsOfBikes").click();
    }

    /**
     * function to validate pop up if try to post ad without mobile no
     */
    public boolean validatePopupMsgForPostAdWithoutMbNo()
    {
        return isElementPresent("popupMsgForMbNo");
    }

    /**
     * function to set pin code at the time of post and ad
     */
    public void setPinCodeInPostAd(String str,int i)
    {
        Mapper.findAndReplace(domFile, "setPinCode", new String[]{Integer.toString(i)}).sendKeys(str);
    }

    /**
     * function to set the address at the time of post ad
     */
    public void setAddressInPostAd(String str,int i)
    {
        Mapper.findAndReplace(domFile, "setAddress", new String[]{Integer.toString(i)}).sendKeys(str);

    }

}
