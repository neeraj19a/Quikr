package com.quikr.utils.database;

import java.sql.SQLException;

import static com.quikr.utils.PropertyReader.getProperties;

/**
 * Created by akhil.singla on 27/9/16.
 */
public class DbBase
{
    private String env = System.getProperty("DbEnv") == null ? getProperties().get("DbEnv") : System.getProperty("DbEnv");
    protected String url;
    protected String userName = null;
    protected String password = null;

    public DbBase()
    {
        switch (env)
        {
            case "stage":
                url = getProperties().get("DbUrlForStage");
                userName = getProperties().get("DbUserForStage");
                password = getProperties().get("DbPassForStage");
                break;
            case "integration":
                url = getProperties().get("DbUrlForIntegration");
                userName = getProperties().get("DbUserForIntegration");
                password = getProperties().get("DbPassForIntegration");
                break;
        }

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}