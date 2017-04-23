package com.quikr.utils.database.education;

import com.quikr.utils.database.DbBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.sql.*;

/**
 * Created by akhil.singla on 27/9/16.
 */
public class EducationDB extends DbBase
{
    private final static Log LOGGER = LogFactory.getLog(EducationDB.class.getName());
    private Connection connection = null;

    public EducationDB()
    {
        try
        {
            if(connection == null || connection.isClosed())
            {
                connection = DriverManager.getConnection(url + "quikr_education", userName, password);
            }
        } catch (SQLException ex)
        {
            LOGGER.info("Getting SQLException: " + ex.getMessage());
            LOGGER.info("Getting SQLState: " + ex.getSQLState());
            LOGGER.info("Getting ErrorCode: " + ex.getErrorCode());
        }
    }

    public void closeConnection()
    {
        try
        {
            connection.close();
            LOGGER.info("Connection closed");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
