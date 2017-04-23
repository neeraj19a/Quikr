package com.quikr.website.horizontal.pap;

import com.quikr.utils.enums.website.QuikrEnums;
import com.quikr.website.TestBase;
import com.quikr.website.dataProvider.Data;
import com.quikr.website.horizontal.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 30/5/16.
 */
public class PetsandPetCarePapTests extends TestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("PAP_TESTDATA_FILE"));

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PetsandPetCarewithPets(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        PetsandPetCarePap petsandPetCarewithPets=new PetsandPetCarePap(driver);


        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.PETS_AND_PET_CARE, QuikrEnums.PetsSubCat.PETS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" 2 Month old Labrador pup for Sale in MG Road" + getRandomString(10);
        papPage.selectAdType(adType);
        papPage.inputAdTitle(adTitle);
        petsandPetCarewithPets.selectRandomPetType(adType);
        papPage.inputPrice("9000");
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PetsandPetCarewithPetCareAccessories(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        PetsandPetCarePap petsandPetCarewithPets=new PetsandPetCarePap(driver);

        waitForPageToLoad("quikr.com"); homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.PETS_AND_PET_CARE, QuikrEnums.PetsSubCat.PET_CARE_ACCESSORIES);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide Pet Care - Accessories at reasonable cost ,huge variety outlets in Whitefield, Indiranagar,Kormangala" + getRandomString(10);
        papPage.selectAdType(adType);
        papPage.inputAdTitle(adTitle);
        adType=2;
        petsandPetCarewithPets.selectRandomPetType(adType);
        papPage.inputPrice("9000");
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PetsandPetCarewithPetAdoption(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        PetsandPetCarePap petsandPetCarewithPets=new PetsandPetCarePap(driver);

        waitForPageToLoad("quikr.com");
        waitForPageToLoad("quikr.com"); homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.PETS_AND_PET_CARE, QuikrEnums.PetsSubCat.PET_ADOPTION);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We running NGO for street dogs and provide Pet Adoption services" + getRandomString(10);
        papPage.selectAdType(adType);
        papPage.inputAdTitle(adTitle);
        petsandPetCarewithPets.selectRandomPetType(adType);
        papPage.inputPrice("9000");
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(),"Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PetsandPetCarewithPetTrainingGrooming(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        PetsandPetCarePap petsandPetCarewithPets=new PetsandPetCarePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.PETS_AND_PET_CARE, QuikrEnums.PetsSubCat.PET_TRAINING_AND_GROOMING);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide  Pet Training & Grooming services near Marathalli, Indira Nagar" + getRandomString(10);
        papPage.selectAdType(adType);
        papPage.inputAdTitle(adTitle);
        petsandPetCarewithPets.selectRandomPetType(2);
        papPage.inputPrice("6000");
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PetsandPetCarewithPetFoods(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        PetsandPetCarePap petsandPetCarewithPets=new PetsandPetCarePap(driver);

        waitForPageToLoad("quikr.com");
        homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.PETS_AND_PET_CARE, QuikrEnums.PetsSubCat.PET_FOODS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" We provide Pet Foods at your Doorstep near Marathalli, Indira Nagar" + getRandomString(10);
        papPage.selectAdType(adType);
        papPage.inputAdTitle(adTitle);
        petsandPetCarewithPets.selectRandomPetType(2);
        papPage.inputPrice("6000");
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "Looks Like Ad was not posted Successfully Pls Check");
    }

    @Test(dataProvider = "adType1",dataProviderClass = Data.class)
    public void PetsandPetCarewithPetClinics(int adType){
        HomePage homePage=new HomePage(driver);
        PAPPage papPage=new PAPPage(driver);
        PetsandPetCarePap petsandPetCarewithPets=new PetsandPetCarePap(driver);

        waitForPageToLoad("quikr.com"); homePage.clickPostFreeAdButton();
        papPage.selectSubCategory(QuikrEnums.CategoryName.PETS_AND_PET_CARE, QuikrEnums.PetsSubCat.PET_CLINICS);
        waitForPageToLoad("post-classifieds-ads/?postadcategoryid");
        String adTitle=getRandomString(5)+" America's Biggest Pet Clinic Chain Opened a New Pet Clinic in Marathalli, Indira Nagar" + getRandomString(10);
        papPage.selectAdType(adType);
        papPage.inputAdTitle(adTitle);
        petsandPetCarewithPets.selectRandomPetType(2);
        papPage.inputPrice("6000");
        papPage.enterCity("Bangalore");
        papPage.selectLocality();
        papPage.inputDescription(adTitle);
        String email=getRandomString(10)+"@gmail.com";
        papPage.enterEmail(email);
        papPage.postTheAd();
        Assert.assertTrue(papPage.adPostedSucessfully(), "Looks Like Ad was not posted Successfully Pls Check");
    }
}
