package com.quikr.website.education.educationHome;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

public class EducationHomePage extends PageBase
{
    // const string
    private static final String domFile = getProperties().get("EDUCATION_HOME_DOM_FILE");

    public EducationHomePage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    public void clickBannerFindNow()
    {
        Mapper.find(domFile,"bannerFindNow").click();
    }

    public void clickCarouselBanner(int bannerNum) {
        List<WebElement> elm  = Mapper.finds(domFile,"corousalBanner");
        elm.get(bannerNum).click();
        clickBannerFindNow();
    }

    public void clickEducationLearningSubCat(int subCatNum){
        List<WebElement> elm = Mapper.finds(domFile,"eduSubCategory");
        elm.get(subCatNum).click();
    }

    public void clickOnArticles(int articleNum){
        if (Mapper.waitForElementToBeVisible(domFile,"articles")==true) {
            List<WebElement> elm = Mapper.finds(domFile, "articles");
            elm.get(articleNum).click();
        }else{
            logger.info("Articles link never became visible....");
        }
    }

    public void clickPostAd(){
        Mapper.find(domFile,"postAdBtn").click();
    }

}
