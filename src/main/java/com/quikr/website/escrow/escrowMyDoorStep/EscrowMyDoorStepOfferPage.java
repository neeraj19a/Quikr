package com.quikr.website.escrow.escrowMyDoorStep;

import com.quikr.website.PageBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by francis.s@quikr on 15/6/16.
 */
public class EscrowMyDoorStepOfferPage extends PageBase{

    private static final String domFile = getProperties().get("Escrow_MY_DOORSTEP_OFFER_DOM_FILE");
    private final static Log LOGGER = LogFactory.getLog(EscrowMyDoorStepOfferPage.class.getName());

    public EscrowMyDoorStepOfferPage(RemoteWebDriver driver) {
        super(domFile, driver);
    }


    public int getOfferCount(){
       return  Integer.parseInt(Mapper.find(domFile,"offersCount").getText());
    }

    public int getAcceptedCount(){
        return  Integer.parseInt(Mapper.find(domFile,"counterCount").getText());
    }

    public int getCounterCount(){
        return  Integer.parseInt(Mapper.find(domFile,"counterCount").getText());
    }

    public void clickCounterByOfferId(String offerId){
        Mapper.findAndReplace(domFile,"counterOfferByOfferId",new String[]{offerId}).click();
        waitForPageToLoad("action_type=counter");
    }

    public void clickAcceptOfferByOfferId(String offerId){
        Mapper.findAndReplace(domFile,"acceptOfferByOfferId",new String[]{offerId}).click();
        waitForPageToLoad("action_type=accept");
    }

    public String getOfferedPriceByOfferId(String offerId){
       return Mapper.findAndReplace(domFile,"offeredPriceByOfferidId",new String[]{offerId}).getText().replace(",","").replace("₹","").trim();
    }

}
