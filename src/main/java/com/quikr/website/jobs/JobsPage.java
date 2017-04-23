package com.quikr.website.jobs;

import com.quikr.website.PageBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by Manvendra Singh on 31/7/15.
 */
public class JobsPage extends PageBase
{
    public JobsPage(RemoteWebDriver driver)
    {
        super(domFile, driver);
    }

    // const string
    private static final String domFile = getProperties().get("JOBS_DOM_FILE");

    /**
     * click on sub category of jobs
     * @param category
     */
    public void selectJobsCategory(String category)
    {
        Mapper.findChildElement(domFile, category, "categoryList", "a", "title").click();
    }

    public String getTextOfJobCategory()
    {
        return Mapper.find(domFile,"categoryName").getText();
    }

    public void findJobsFromRightNavFilters()
    {
        Mapper.find(domFile, "findButton").click();
    }

    public void selectPartTimeJobsOption()
    {
        Mapper.find(domFile, "partTimeJobsOption").click();
    }

    public void searchJobs(String jobrole){
        Mapper.find(domFile,"jobsSearchBox").click();
        Mapper.findAndReplace(domFile, "jobRole", new String[]{jobrole}).click();
        Mapper.find(domFile,"jobSearch").click();
    }
}