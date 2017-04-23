package com.quikr.website.escrow.oms;

import com.quikr.website.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by gurinder.singh on 10/12/15.
 */
public class OmsPage extends PageBase
{

    public OmsPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("ESCROW_OMS_DOM_FILE");

    public String todaysdate = todaysDate();

    public void openOMSUrl()
    {
        navigateTo().to("http://192.168.124.53:9793/login");
    }

    public String todaysDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//dd/MM/yyyy
        Date now = new Date();
        return dateFormat.format(now);
    }

    public String yesterdaysDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    public void login(String username, String password)
    {
        Mapper.waitForElementToBeVisible(domFile, "usernameTextfield");
        Mapper.find(domFile, "usernameTextfield").sendKeys(username);
        Mapper.find(domFile, "passwordTextfield").sendKeys(password);
        Mapper.find(domFile, "loginButton").click();
        Mapper.waitForElementToBeVisible(domFile, "quikrHeaderLogo");
    }

    public void clickSearch(int ind)
    {
        List<WebElement> elms = Mapper.finds(domFile,"searchButton");
        elms.get(ind).click();
    }

    public void clickDownload()
    {
        Mapper.find(domFile, "downloadButton").click();
    }

    public void selectFromDate(String fromDate, int ind)
    {
        if (fromDate!=todaysDate())
        {
            List<WebElement> elms = Mapper.finds(domFile, "fromDateTextfield");
            elms.get(ind).clear();
            elms.get(ind).sendKeys(fromDate);
        }
    }

    public void selectToDate(String toDate, int ind)
    {
        if (toDate!=todaysDate()) {
            List<WebElement> elms = Mapper.finds(domFile, "toDateTextfield");
            elms.get(ind).clear();
            elms.get(ind).sendKeys(toDate);
        }
    }

    public void selectOrderStatusDropdown(String status)
    {
        Mapper.waitForElementToBeClickable(domFile, "orderStatusDropdown");
        Select statusSelect = new Select(Mapper.find(domFile, "orderStatusDropdown"));
        statusSelect.selectByVisibleText(status);
    }

    public void selectPaymentModeDropdown(String paymentMode)
    {
        Mapper.waitForElementToBeClickable(domFile, "paymentModeDropdown");
        Select statusSelect = new Select(Mapper.find(domFile, "paymentModeDropdown"));
        statusSelect.selectByVisibleText(paymentMode);
    }

    public void selectAppointmentTypeDropdown(String AppType)
    {
        Mapper.waitForElementToBeClickable(domFile, "appointmentFixedDropdown");
        Select statusSelect = new Select(Mapper.find(domFile, "appointmentFixedDropdown"));
        statusSelect.selectByVisibleText(AppType);
    }

    //    Navigation Code

    public void gotoOrderStatus()
    {
        Mapper.find(domFile, "orderStatusTab").click();
    }

    public void gotoCreatedDate()
    {
        Mapper.find(domFile, "createdDateTab").click();
    }

    public void gotoBuyerDetailsByEmail()
    {
        if (Mapper.find(domFile, "byEmailMenuBuyer").isDisplayed())
        {
            Mapper.find(domFile, "byEmailMenuBuyer").click();
        } else {
            Mapper.find(domFile, "buyerDetailsTab").click();
            Mapper.find(domFile, "byEmailMenuBuyer").click();
        }
    }

    public void gotoBuyerDetailsByContactNumber()
    {
        if (Mapper.find(domFile, "byPhoneMenuBuyer").isDisplayed())
        {
            Mapper.find(domFile, "byPhoneMenuBuyer").click();
        } else {
            Mapper.find(domFile, "buyerDetailsTab").click();
            Mapper.find(domFile, "byPhoneMenuBuyer").click();
        }
    }

    public void gotoSellerDetailsByEmail() {
        if (Mapper.find(domFile, "byEmailMenuseller").isDisplayed())
        {
            Mapper.find(domFile, "byEmailMenuseller").click();
        } else
        {
            Mapper.find(domFile, "sellerDetailsTab").click();
            Mapper.find(domFile, "byEmailMenuseller").click();
        }
    }

    public void gotoSellerDetailsByContactNumber()
    {
        if (Mapper.find(domFile, "byPhoneMenuseller").isDisplayed())
        {
            Mapper.find(domFile, "byPhoneMenuseller").click();
        }
        else {
            Mapper.find(domFile, "sellerDetailsTab").click();
            Mapper.find(domFile, "byPhoneMenuseller").click();
        }
    }

    public void gotoUniqueIdByAdId() {
        if (Mapper.find(domFile, "byAdIDMenu").isDisplayed())
        {
            Mapper.find(domFile, "byAdIDMenu").click();
        }
        else {
            Mapper.find(domFile, "uniqueIDTab").click();
            Mapper.find(domFile, "byAdIDMenu").click();
        }
    }

    public void gotoUniqueIdByOfferId()
    {
        if (Mapper.find(domFile, "byOfferIDMenu").isDisplayed())
        {
            Mapper.find(domFile, "byOfferIDMenu").click();
        }
        else {
            Mapper.find(domFile, "uniqueIDTab").click();
            Mapper.find(domFile, "byOfferIDMenu").click();
        }
    }

    public void gotoUniqueIdByTransactionId()
    {
        if (Mapper.find(domFile, "byTransactionIDMenu").isDisplayed())
        {
            Mapper.find(domFile, "byTransactionIDMenu").click();
        }
        else {
            Mapper.find(domFile, "uniqueIDTab").click();
            Mapper.find(domFile, "byTransactionIDMenu").click();
        }
    }

    public void gotoPaymentMode()
    {
        Mapper.waitForElementToBeClickable(domFile,"paymentModeTab");
        Mapper.find(domFile, "paymentModeTab").click();
    }

    public void gotoAppointmentType()
    {
        Mapper.waitForElementToBeClickable(domFile,"appointmentTypeTab");
        Mapper.find(domFile, "appointmentTypeTab").click();
    }

    public void gotoAdvanceSearch()
    {
        Mapper.waitForElementToBeClickable(domFile,"advanceSearchTab");
        Mapper.find(domFile, "advanceSearchTab").click();
    }


    public void searchByOrderStatus(String Status)
    {
        Mapper.waitForElementToBeVisible(domFile, "orderStatusDropdown");
        selectOrderStatusDropdown(Status);
        selectFromDate(yesterdaysDate(),0);
        selectToDate(todaysDate(),0);
        clickSearch(0);
    }

    public boolean searchComplete(){
        boolean flag=false;
        Mapper.waitForElementToBeVisible(domFile,"searchMessage");
        String text = Mapper.find(domFile,"searchMessage").getText();
        if (text.contains("There")){
            flag = true;
        }
        return flag;
    }

    public void searchByCreatedDate(String fromdate, String todate)
    {
        Mapper.waitForElementToBeVisible(domFile, "fromDateTextfield");
        selectFromDate(fromdate,3);
        selectToDate(todate,3);
        clickSearch(3);
    }

    public void searchByBuyerEmail(String buyerEmail)
    {
        Mapper.waitForElementToBeVisible(domFile, "buyerEmailTextfield");
        Mapper.find(domFile, "buyerEmailTextfield").sendKeys(buyerEmail);
        selectFromDate(yesterdaysDate(),4);
        selectToDate(todaysdate,4);
        clickSearch(4);
    }

    public void searchByBuyerMobile(String buyerMobile)
    {
        Mapper.waitForElementToBeVisible(domFile, "buyerMobileTextfield");
        Mapper.find(domFile, "buyerMobileTextfield").sendKeys(buyerMobile);
        selectFromDate(yesterdaysDate(),5);
        selectToDate(todaysdate,5);
        clickSearch(5);
    }

    public void searchBySellerEmail(String sellerEmail)
    {
        Mapper.waitForElementToBeVisible(domFile, "sellerEmailTextfield");
        Mapper.find(domFile, "sellerEmailTextfield").sendKeys(sellerEmail);
        selectFromDate(todaysdate,6);
        selectToDate(todaysdate,6);
        clickSearch(6);
    }

    public void searchBySellerMobile(String sellerMobile)
    {
        Mapper.waitForElementToBeVisible(domFile, "sellerMobileTextfield");
        Mapper.find(domFile, "sellerMobileTextfield").sendKeys(sellerMobile);
        selectFromDate(todaysdate,7);
        selectToDate(todaysdate,7);
        clickSearch(7);
    }

    public void searchByAdId(String AdId)
    {
        Mapper.waitForElementToBeVisible(domFile, "adIDTextfield");
        Mapper.find(domFile, "adIDTextfield").sendKeys(AdId);
        clickSearch(8);
    }

    public void searchByOfferId(String OfferId)
    {
        Mapper.waitForElementToBeVisible(domFile, "offerIDTextfield");
        Mapper.find(domFile, "offerIDTextfield").sendKeys(OfferId);
        clickSearch(9);
    }

    public void searchByTransactionId(String TransactionId)
    {
        Mapper.waitForElementToBeVisible(domFile, "transactionIDTextfield");
        Mapper.find(domFile, "transactionIDTextfield").sendKeys(TransactionId);
        clickSearch(10);
    }

    public void searchByPaymentMode(String PaymentMode)
    {
        Mapper.waitForElementToBeVisible(domFile, "paymentModeDropdown");
        selectPaymentModeDropdown(PaymentMode);
        selectFromDate(yesterdaysDate(),1);
        selectToDate(todaysdate,1);
        clickSearch(1);
    }

    public void searchByAppointmentType(String AppType)
    {
        Mapper.waitForElementToBeVisible(domFile, "appointmentFixedDropdown");
        selectAppointmentTypeDropdown(AppType);
        selectFromDate(todaysdate,2);
        selectToDate(todaysdate,2);
        clickSearch(2);
    }

    public void searchByAdvancedSearch()
    {
    }

    public void logout()
    {
        Mapper.find(domFile, "myAccountMenu").click();
        Mapper.find(domFile, "logoutMenuButton").click();
    }

    //Data Assertions
    public boolean dataPresentInPaymentModeTable()
    {
        String csvFile = "/home/quikr/Desktop/APIAutomation/TestData.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        boolean flag = false;

        try {
            Map<String, String> sourceMap = new HashMap<String, String>();
            Map<String, String> UIMap = new HashMap<String, String>();

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(cvsSplitBy);
                sourceMap.put(data[0], data[1]);
            }

            List<WebElement> offers = Mapper.finds(domFile, "offerIdColumn");
            List<WebElement> AdIds = Mapper.finds(domFile, "adIdColumn");

            for (int i = 0; i < AdIds.size(); i++)
            {
                UIMap.put(offers.get(i).getText(), AdIds.get(i).getText());
            }
            if (UIMap.entrySet().containsAll(sourceMap.entrySet()))
            {
                flag = true;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}
