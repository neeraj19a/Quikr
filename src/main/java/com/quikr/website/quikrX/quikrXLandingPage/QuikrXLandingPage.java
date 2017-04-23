package com.quikr.website.quikrX.quikrXLandingPage;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by quikr on 12/2/16.
 */
public class QuikrXLandingPage extends PageBase {

    // const string
    private static final String domFile = getProperties().get("QUIKRX_LANDING_PAGE_DOM_FILE");

    public QuikrXLandingPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }

    public boolean isLandingPage(){
        return Mapper.find(domFile,"").isDisplayed();
    }

    public void clickRefurbishedTab(){
        Mapper.find(domFile,"refurbishedTab").click();
    }

    public void clickRefurbishedMsiteTab(){
        Mapper.find(domFile,"refurbishedTabMsite").click();
    }

    public void clickUnboxedMsiteTab(){
        Mapper.find(domFile,"certifiedTabMsite").click();
    }

    public void clickNewMsiteTab(){
        Mapper.find(domFile,"newTabMsite").click();
    }

    public void clickUnboxedTab(){
        Mapper.find(domFile,"unboxedTab").click();
    }

    public void clickExchangeTab(){
        Mapper.find(domFile,"exchangeTab").click();
    }

    public String refurbishedSavetext(){
         return Mapper.find(domFile,"refurbishedSaveText").getText();
    }

    public String unboxedSavetext(){
        return Mapper.find(domFile,"unboxedSaveText").getText();
    }

    public String exchangeSavetext(){
        return Mapper.find(domFile,"exchangeSaveText").getText();
    }

    public String refurbishedDeftext(){
        return Mapper.find(domFile,"refurbisheddef").getText();
    }

    public String unboxedDeftext(){
        return Mapper.find(domFile,"unboxedDef").getText();
    }

    public String exchangeDeftext(){
        return Mapper.find(domFile,"exchangeDef").getText();
    }

    public boolean exchangeCheckListPresent(){
        return Mapper.find(domFile,"exchangeDesc").isDisplayed();
    }

    public boolean unboxedCheckListPresent(){
        return Mapper.find(domFile,"unboxedDec").isDisplayed();
    }

    public boolean refurbishedcheckListPresent(){
        return Mapper.find(domFile,"refurbishedDes").isDisplayed();
    }

    public void clickSeeAllUnboxedPhone(){
        Mapper.find(domFile,"seeAllUnboxed").click();
    }

    public void clickSeeAllrefurbishedPhone(){
        Mapper.find(domFile,"seeAllRefurbished").click();
    }

    public void clickSeeAllExchangePhone(){
        Mapper.find(domFile,"seeAllExchange").click();
    }

    public boolean seeAllTrendingPresent(){
        return Mapper.find(domFile,"trendingPhoneList").isDisplayed();
    }

    public void clickSeeAllTrending(){
        Mapper.find(domFile,"seeAllTrending").click();
    }

    public void clickAnyTrendingMobile(){
        Mapper.find(domFile,"firstTrending").click();
    }

    public void clickSeeAllBrands(){
        Mapper.find(domFile,"seeAllBrand").click();
    }

    public boolean shopByBrandDisplayed(){
        return Mapper.find(domFile,"brandList").isDisplayed();
    }

    public Map<String,String> brandUrls(){

        Map<String,String> map = new HashMap<>();
        List<WebElement> list =  Mapper.finds(domFile,"brandUrls");
        for (WebElement s:list){
            String brandName = s.getAttribute("href").split("Phones/")[1].split("/w")[0];
            map.put(brandName,s.getAttribute("href"));
        }
        return map;
    }

    public Map<String,String> priceFilter(String category){
        Map<String,String> map = new HashMap<>();
        List<WebElement> list = Mapper.finds(domFile,"phonesByPrice");
        for (WebElement s:list){
            String filterName = s.getAttribute("href").split("Phones/")[1].split("/"+category)[0].trim();
            map.put(filterName,s.getAttribute("href"));
        }
        return map;
    }

    public boolean isfilterScrollerDisplayed(){
        return Mapper.find(domFile,"filterScroller").isDisplayed();
    }

    public List<String> phonesUnder1000(){

        List<String> url = new LinkedList<>();
        List<WebElement> list =  Mapper.finds(domFile,"phonesUnder10kpriceList");
        for (WebElement s:list){
            url.add(s.getText().replace("₹","").replace(",","").trim());
        }
        return url;
    }

    public void clickAllApplePhones(){
        Mapper.find(domFile,"allApples").click();
    }

    public void clickPrimaryCta(){
        Mapper.find(domFile,"seAllRefurbishedCta").click();
    }

    public boolean isPrimaryCtaDisplayed(){
        return Mapper.find(domFile,"seAllRefurbishedCta").isDisplayed();
    }

    public String getPrimaryCtaText(){
        return Mapper.find(domFile,"seAllRefurbishedCta").getText();
    }


    public void openfirstApple(){
        Mapper.find(domFile,"appleFirst").click();
    }

    public void clickSeeAllAndroids(){
        Mapper.find(domFile,"seeAllAndroids").click();
    }

   public boolean isAndroidDisplayed(){
       return Mapper.find(domFile,"firstAndroid").isDisplayed();
   }

   public void openFirstAndroid(){
       Mapper.find(domFile,"firstAndroid").click();
   }

    public Map<String,String> sliderCategoryUrls(){
        Map<String,String> map = new HashMap<>();
        List<WebElement> list = Mapper.finds(domFile,"sliderCategoriesList");
        for (WebElement s:list){
            String filterName;
            if(s.getAttribute("href").contains("order=priceAsc")){
                filterName="Low to High";
                map.put(filterName, s.getAttribute("href"));
            }
            else {
                filterName = s.getAttribute("href").split("-Phones/")[1].split("/w")[0].trim();
                map.put(filterName, s.getAttribute("href"));
            }
        }
        return map;
    }

}
