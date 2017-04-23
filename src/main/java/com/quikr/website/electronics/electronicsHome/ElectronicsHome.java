package com.quikr.website.electronics.electronicsHome;
import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 /**
 * Created by Manvendra Singh on 29/7/15.
 */
public class ElectronicsHome extends PageBase{
    public ElectronicsHome(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("Electronics_Home_DOM_FILE");
    //function to reach on SnB page in Electronics subcategory
    public boolean FilterApplyByBrandName()
    {
        boolean flag=false;
        Mapper.waitForElementToBeVisible(domFile,"Laptops");
        Mapper.find(domFile,"Laptops").click();
        Mapper.find(domFile,"BrandName_Acer").click();
        if(Mapper.waitForElementToBeVisible(domFile, "PageDetail")==true) {
            List<WebElement> BrandNamePageDetail = Mapper.finds(domFile, "PageDetail");
            List<String> AllElements = new ArrayList<>();
            for (WebElement e : BrandNamePageDetail) {
                AllElements.add(e.getText());
            }

            for (int i = 0; i < AllElements.size(); i++) {

                if (!(AllElements.get(i).contains("Acer") || AllElements.get(i).contains("acer") || AllElements.get(i).contains("ACER") || AllElements.get(i).contains("Laptop"))) {
                    flag=false;
                }
                else
                    flag=true;
            }
        }
        return flag;

    }

    public void clickLaptopsComputeers()
    {
        WebElement element=Mapper.finds(domFile, "Laptops_Computers").get(0);
        element.click();
    }

    public void clickHomeKitchenAppliances()
    {
        WebElement element=Mapper.finds(domFile, "Home_Kitchen Appliances").get(1);
        element.click();
    }

    public void clickTV_DVD_Multimedia(){
                Mapper.find(domFile,"TV_DVD_Multimedia").click();
            }
    }

