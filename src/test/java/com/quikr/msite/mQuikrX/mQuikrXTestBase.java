package com.quikr.msite.mQuikrX;

import com.quikr.api.quikrX.QuikrXApiBase;
import com.quikr.api.quikrX.QuikrXEnumData;
import com.quikr.msite.MTestBase;
import com.quikr.website.quikrX.quikrXNewDetailsPage.QuikrXNewDetailsPage;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;

import static com.quikr.utils.PropertyReader.getProperties;
import static com.quikr.utils.XMLReader.getTestData;

/**
 * Created by quikr on 17/5/16.
 */
public class mQuikrXTestBase extends MTestBase {

    private HashMap<String, String> testData = getTestData(getProperties().get("mQUIKRX_TESTDATA_FILE"));
    private final static Log LOGGER = LogFactory.getLog(mQuikrXTestBase.class.getName());

    public void openProductNewUi(String itemId, String city){
        QuikrXApiBase apiBase = new QuikrXApiBase();
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        apiBase.updateProductInventory("10", QuikrXEnumData.inventoryUpdateType.ABSOLUTE,"1000",itemId);
        navigatethirdparty(getTestDataWithReplace(testData.get("newUiLink"), city) + itemId);
        waitForPageToLoad(itemId);
        if(detailsPage.isItemOos()){
            LOGGER.fatal("item is out of stock");
            Assert.assertFalse(true);
        }
    }

    public void enterPincode(String pincode){

        QuikrXNewDetailsPage msiteDetailsPage = new QuikrXNewDetailsPage(driver);

        msiteDetailsPage.enterPincode(pincode);
        msiteDetailsPage.clickPincodeCheck();

    }

    public void selctExchangeEnterPincode(String brand,String model,String pincode){
        QuikrXNewDetailsPage detailsPage = new QuikrXNewDetailsPage(driver);

        detailsPage.selectBrand(brand);
        detailsPage.selectModel(model);
        detailsPage.clickGoodCondition();
        detailsPage.clickInHand();
        enterPincode(pincode);

    }






}
