package com.quikr.utils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by akhil.singla on 23/12/15.
 */
public class HarAnalyzer
{
    private BrowserMobProxy browserMobProxy;

    public HarAnalyzer(BrowserMobProxy proxyServer)
    {
        browserMobProxy = proxyServer;
    }

    /**
     * get har file containing network information
     *
     * @return
     */
    public Har getHar() {
        return browserMobProxy.getHar();
    }

    /**
     * save har file
     *
     * @param harFile
     */
    public void saveHar(Har harFile, String location)
    {
        try
        {
            File file = new File(location);
            harFile.writeTo(file);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * function to get full request urls matching given string
     * @param har
     * @param matchingString
     * @return
     */
    public List<String> getRequestUrlsFull(Har har, String matchingString)
    {
        List<HarEntry> harEntries = har.getLog().getEntries();
        List<String> urls = new LinkedList<String>();

        for (HarEntry harEntry : harEntries)
        {
            HarRequest harRequest = harEntry.getRequest();
            String url = harRequest.getUrl();
            if(url.contains(matchingString))
            {
                urls.add(url);
            }
        }

        return urls;
    }
}