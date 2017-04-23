package com.quikr.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

/**
 * Created by akhil.singla
 */

public class XMLReader
{
    /**
     * get all dom elements present in XML corresponding to elementName
     * @param fileName
     * @param elementName
     * @return map consisting all dom elements representing elementName
     */
    protected HashMap<String, String> getDOMElements(String fileName, String elementName)
    {
        HashMap<String, String> domElements = new HashMap<String, String>();
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/main/java/com/quikr/" + fileName));

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for(int i=0; i<nodeList.getLength(); i++)
            {
                Node parentElementNode = nodeList.item(i);
                if(parentElementNode.getNodeName().equals("element"))
                {
                    String attName = parentElementNode.getAttributes().item(0).getTextContent();

                    if(attName.equals(elementName))
                    {
                        NodeList childDomNodes =  parentElementNode.getChildNodes();
                        for(int j=0; j<childDomNodes.getLength(); j++)
                        {
                            String childNodeName = childDomNodes.item(j).getNodeName();
                            String childNodeContent = childDomNodes.item(j).getTextContent();
                            if(childNodeName.equals("xpath"))
                            {
                                domElements.put("xpath", childNodeContent);
                            }
                            else if(childNodeName.equals("id"))
                            {
                                domElements.put("id", childNodeContent);
                            }
                            else if(childNodeName.equals("css"))
                            {
                                domElements.put("css", childNodeContent);
                            }
                            else if(childNodeName.equals("class"))
                            {
                                domElements.put("class", childNodeContent);
                            }
                            else if(childNodeName.equals("tag"))
                            {
                                domElements.put("tag", childNodeContent);
                            }
                            else if(childNodeName.equals("partialLinkText"))
                            {
                                domElements.put("partialLinkText", childNodeContent);
                            }
                            else if(childNodeName.equals("linkText"))
                            {
                                domElements.put("linkText", childNodeContent);
                            }
                            else if(childNodeName.equals("name"))
                            {
                                domElements.put("name", childNodeContent);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return domElements;
    }

    /**
     * get login data
     * @param name
     * @return map containing username and password
     */
    public static synchronized HashMap<String, String> getLogins(String name)
    {
        HashMap<String, String> loginElements = new HashMap<String, String>();
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/main/resources/logins.xml"));

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for(int i=0; i<nodeList.getLength(); i++)
            {
                Node parentElementNode = nodeList.item(i);
                if(parentElementNode.getNodeName().equals("login"))
                {
                    String attName = parentElementNode.getAttributes().item(0).getTextContent();

                    if(attName.equals(name))
                    {
                        NodeList childDomNodes =  parentElementNode.getChildNodes();
                        for(int j=0; j<childDomNodes.getLength(); j++)
                        {
                            String childNodeName = childDomNodes.item(j).getNodeName();
                            String childNodeContent = childDomNodes.item(j).getTextContent();
                            if(childNodeName.equals("username"))
                            {
                                loginElements.put("username", childNodeContent);
                            }
                            else if(childNodeName.equals("password"))
                            {
                                loginElements.put("password", childNodeContent);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return loginElements;
    }

    /**
     * get test data for a given test data xml
     * @param testDataFile
     * @return map consisting all test data for given file
     */
    public static synchronized HashMap<String, String> getTestData(String testDataFile)
    {
        HashMap<String, String> testData = new HashMap<String, String>();

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/test/java/com/quikr/" + testDataFile));

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for(int i=0; i<nodeList.getLength(); i++)
            {
                Node parentElementNode = nodeList.item(i);
                if(parentElementNode.getNodeName().equals("testData"))
                {
                    String name = parentElementNode.getAttributes().item(0).getTextContent();
                    String value = parentElementNode.getTextContent();
                    testData.put(name,value);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return testData;
    }

    /**
     * function to update XML value
     * @param value
     * @param values
     */
    protected String updateMapValue(String value, String[] values)
    {
        StringBuffer result= new StringBuffer("");
        int k=0;

        if(value != null)
        {
            for(int i=0; i<value.length(); i++)
            {
                if(k == values.length)
                {
                    result.append(value.substring(i,value.length()));
                    break;
                }

                if(value.charAt(i) == '$')
                {
                    result.append(values[k]);
                    k++;
                }
                else
                {
                    result.append(Character.toString(value.charAt(i)));
                }
            }
        }

       return result.toString();
    }
}
