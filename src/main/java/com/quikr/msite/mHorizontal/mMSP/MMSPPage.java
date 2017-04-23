package com.quikr.msite.mHorizontal.mMSP;

import com.quikr.msite.MPageBase;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 5/11/15.
 */
public class MMSPPage extends MPageBase {

    public MMSPPage(AppiumDriver driver)
    {
        super(domFile, driver);
    }

    private static final String domFile = getProperties().get("mMSPPAGE_DOM_FILE");

    public boolean isMSPPageavailable(){
        boolean flag=false;
        if(isElementPresent("buyMSP")==true) {
            flag = true;
        }
        else {
            return false;
        }

        if(isElementPresent("sellMSP")==true) {
            flag = true;
        }
        else {
            return false;
        }

        if(isElementPresent("categoryMSP")==true) {
            flag = true;
        }
        else {
            return false;
        }

        if(isElementPresent("CalculateButtonMSP")==true) {
            flag = true;
        }
        else {
            return false;
        }
        return flag;

    }


    public boolean isMSPResultBoxDisplayed(){
        if(Mapper.waitForElementToBeVisible(domFile, "mspResultBox")==true){
            return true;
        }
        else
            return false;
    }
    public boolean isBuyavailable(){
        return isElementPresent("buyMSP");
    }

    public void clickBuyMSP(){
        Mapper.waitForElementToBeClickable(domFile,"buyMSP");
        Mapper.find(domFile,"buyMSP").click();
    }

    public boolean isSellavailable(){
        return isElementPresent("sellMSP");
    }

    public void clickSellMSP(){
        Mapper.waitForElementToBeClickable(domFile,"sellMSP");
        Mapper.find(domFile,"sellMSP").click();
    }

    public void selectCategoryMSP(String categoryname){
        Mapper.waitForElementToBeClickable(domFile, "Category");
        Select category=new Select(Mapper.find(domFile,"Category"));
        category.selectByVisibleText(categoryname);
    }

    public void selectSubCategoryMSP(String subcategoryname){
        Mapper.waitForElementToBeClickable(domFile, "mspSubCategory");
        Select subcategory=new Select(Mapper.find(domFile,"mspSubCategory"));
        subcategory.selectByVisibleText(subcategoryname);
    }

    public void selectBrandMSP(String brandname){
        Mapper.waitForElementToBeClickable(domFile, "mspBrandNameSelectBox");
        Select brand=new Select(Mapper.find(domFile,"mspBrandNameSelectBox"));
        brand.selectByVisibleText(brandname);
    }

    public void selectModelMSP(String modelname){
        Mapper.waitForElementToBeClickable(domFile, "mspModelSelectBox");
        Select model=new Select(Mapper.find(domFile,"mspModelSelectBox"));
        model.selectByVisibleText(modelname);
    }

    public void selectYearMSP(String year){
        Mapper.waitForElementToBeClickable(domFile, "mspYearSelectBox");
        Select selectyear=new Select(Mapper.find(domFile,"mspYearSelectBox"));
        selectyear.selectByVisibleText(year);
    }

    public boolean isPostAdButtonMSPavailable(){
        if(Mapper.find(domFile,"PostAdButtonMSP")!=null){
            return true;
        }
        else
            return false;
    }

    public boolean isPostAdSellMSPbuttonavailable(){
        Mapper.waitForElementToBeVisible(domFile,"PostAdButtonMSPonSellMSP");
        return isElementPresent("PostAdButtonMSPonSellMSP");
    }

    public void clickPostAdButtonSellMSP(){
        if(Mapper.waitForElementToBeVisible(domFile,"PostAdButtonMSPonSellMSP")==true) {
            Mapper.find(domFile, "PostAdButtonMSPonSellMSP").click();
        }
    }

    public void clickCalculateButtonMSP(){
        Mapper.waitForElementToBeClickable(domFile, "CalculateButtonMSP");
        Mapper.find(domFile,"CalculateButtonMSP").click();
    }


    public boolean isMatchingAdsButtonMSPavailable(){
        Mapper.waitForElementToBeVisible(domFile, "MatchingAdsButtonMSP");
        return isElementPresent("MatchingAdsButtonMSP");
    }


    public void clickMatchingAds(){
        if(Mapper.waitForElementToBeVisible(domFile, "MatchingAdsButtonMSP")==true){
            Mapper.find(domFile,"MatchingAdsButtonMSP").click();
        }
    }

    public ArrayList<String> allcategories(){
        ArrayList<String> categoriesname=new ArrayList<>();
        Mapper.find(domFile,"Category").click();
        for(int i=1;i<=5;i++){
            WebElement category=Mapper.findAndReplace(domFile,"Categories",new String[]{Integer.toString(i)});
            categoriesname.add(category.getText().toString());
        }
        return categoriesname;
    }

    public ArrayList<String> allSubcategoriesCarsandBikes(){
        ArrayList<String> subcategoriesname=new ArrayList<>();
        Mapper.find(domFile,"buyMSP").click();
        Mapper.find(domFile, "Category").click();
        Mapper.findAndReplace(domFile, "Categories", new String[]{Integer.toString(2)}).click();
        Mapper.find(domFile,"mspSubCategory").click();
        for(int i=1;i<=3;i++){
            WebElement carandbikescategory=Mapper.findAndReplace(domFile,"mspSubCategories",new String[]{Integer.toString(i)});
            subcategoriesname.add(carandbikescategory.getText().toString());
        }
        return subcategoriesname;
    }

    public ArrayList<String> allSubcategoriesHomeandLifeStyle(){
        ArrayList<String> subcategoriesname=new ArrayList<>();
        Mapper.find(domFile,"buyMSP").click();
        Mapper.find(domFile, "Category").click();
        Mapper.findAndReplace(domFile, "Categories", new String[]{Integer.toString(3)}).click();
        Mapper.find(domFile,"mspSubCategory").click();
        for(int i=1;i<=2;i++){
            WebElement homeandlifeStylesubcategory=Mapper.findAndReplace(domFile,"mspSubCategories",new String[]{Integer.toString(i)});
            subcategoriesname.add(homeandlifeStylesubcategory.getText().toString());
        }
        return subcategoriesname;
    }

    public ArrayList<String> allSubcategoriesMobilePhones(){
        ArrayList<String> subcategoriesname=new ArrayList<>();
        Mapper.find(domFile,"buyMSP").click();
        Mapper.find(domFile, "Category").click();
        Mapper.findAndReplace(domFile, "Categories", new String[]{Integer.toString(4)}).click();
        Mapper.find(domFile,"mspSubCategory").click();
        for(int i=1;i<=2;i++){
            WebElement mobilephonessubcategory=Mapper.findAndReplace(domFile,"mspSubCategories",new String[]{Integer.toString(i)});
            subcategoriesname.add(mobilephonessubcategory.getText().toString());
        }
        return subcategoriesname;
    }

    public ArrayList<String> allSubcategoriesElectronicsandTechnology(){
        ArrayList<String> subcategoriesname=new ArrayList<>();
        Mapper.find(domFile,"buyMSP").click();
        Mapper.find(domFile, "Category").click();
        Mapper.findAndReplace(domFile, "Categories", new String[]{Integer.toString(5)}).click();
        Mapper.find(domFile,"mspSubCategory").click();
        for(int i=1;i<=3;i++){
            WebElement homeandlifeStylesubcategory=Mapper.findAndReplace(domFile,"mspSubCategories",new String[]{Integer.toString(i)});
            subcategoriesname.add(homeandlifeStylesubcategory.getText().toString());
        }
        return subcategoriesname;
    }




}

