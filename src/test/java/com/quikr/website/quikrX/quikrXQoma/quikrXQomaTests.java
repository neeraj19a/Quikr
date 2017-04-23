package com.quikr.website.quikrX.quikrXQoma;

import com.quikr.website.quikrX.QuikrXTestBase;
import com.quikr.website.quikrX.quikrXQomaPage.QuikrXQomaHomePage;
import com.quikr.website.quikrX.quikrXQomaPage.QuikrXQomaOrderPage;
import com.quikr.website.quikrX.quikrXQomaPage.QuikrXQomaReplacementPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by francis.s@quikr on 23/12/15.
 */
public class quikrXQomaTests extends QuikrXTestBase {


    @Test
    public void replacementFlow(){

        QuikrXQomaHomePage qomaHomePage = new QuikrXQomaHomePage(driver);
        QuikrXQomaOrderPage qomaOrderPage = new QuikrXQomaOrderPage(driver);
        QuikrXQomaReplacementPage qomaReplacementPage = new QuikrXQomaReplacementPage(driver);

        navigatethirdparty(driver, qomaEnv);
        qomaHomePage.qomalogin();
        qomaHomePage.clickViewSubOrder();
        qomaHomePage.selectDate();
        qomaHomePage.selectDelivered();
        String orderId = qomaHomePage.getFirstOrder();
        String subOrder = qomaHomePage.getFirstSubOrder();
        qomaHomePage.searchByOrderid(orderId);
        qomaHomePage.clickOrderId(orderId);
        qomaOrderPage.setStatus(subOrder,testData.get("replaceRequest"));
        Assert.assertEquals(testData.get("replaceRequest"),qomaOrderPage.getStatus(subOrder));
        qomaOrderPage.clickReplacementTab();
        qomaReplacementPage.searchByOrderId(orderId);
        qomaHomePage.clickOrderId(orderId);
        qomaReplacementPage.setStatus(subOrder,testData.get("recievedByQc"));
        Assert.assertEquals(testData.get("recievedByQc"),qomaReplacementPage.getStatus(subOrder));
        qomaReplacementPage.setStatus(subOrder,testData.get("qcPassed"));
        Assert.assertEquals(testData.get("qcPassed"),qomaReplacementPage.getStatus(subOrder));
        qomaReplacementPage.clickPlaceReplace(subOrder);
        setStatusForReplace(testData.get("seller"),"");
        String newsub = qomaReplacementPage.getReplacedSubOrder(subOrder);
        qomaHomePage.clickViewSubOrder();
        qomaHomePage.searchBySubOrderId(newsub);

        Assert.assertEquals("Call confirmed",qomaHomePage.getStatusByOrderId(newsub));
        Assert.assertEquals("PREPAID",qomaHomePage.getpayModeByOrderId(newsub));

    }








}
