package com.quikr.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.quikr.utils.PropertyReader.getProperties;


/**
 * Created by gurinder.singh@quikr.com on 8/1/16.
 */
public class Database {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DbUrl = null;
    String User;
    String Password;
    String Env = null;
    private final static Log LOGGER = LogFactory.getLog(Database.class.getName());

    public void initializeDbDomain() {
        int flag;
        Env = System.getProperty("DbEnv") == null ? getProperties().get("DbEnv") : System.getProperty("DbEnv");

        switch (Env) {
            case "stage":    //.33 staging server
                System.out.println("In stage DB");
                DbUrl = getProperties().get("DbUrlForStage");
                User = getProperties().get("DbUserForStage");
                Password = getProperties().get("DbPassForStage");
                flag = 1;
                break;

            case "integration":    //.43 integration server
                System.out.println("In Integration DB");
                DbUrl = getProperties().get("DbUrlForIntegration");
                User = getProperties().get("DbUserForIntegration");
                Password = getProperties().get("DbPassForIntegration");
                flag = 1;
                break;

            case "production":    //. production Server
                DbUrl = getProperties().get("DbUrlForProduction");
                User = getProperties().get("DbUserForProduction");
                Password = getProperties().get("DbPassForProduction");
                flag = 1;
                break;

            default:
                flag = 0;
                Assert.assertTrue(flag == 1, "DbDomain value in config.properties file is not a valid match with switch case");
        }
    }

    public String GetResultQueryExecutor(String dbName, String queryStatement) throws ClassNotFoundException, IOException {
        ResultSet retVal = null;
        long id = 0;
        Connection conn = null;
        Statement stmt = null;
        String value = null;
        int flag = 1; //0=indicates no failure, 1=indicates success of all query execution
        initializeDbDomain();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DbUrl + dbName, User, Password);

            stmt = conn.createStatement();

            String sql = queryStatement;
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                rs.last();
                rs.getRow();
                value = rs.getString(1);
            }
//            System.out.println("value = " + value);
        } catch (SQLException se) {
            flag = 0;
            se.printStackTrace();
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            Assert.assertTrue(flag == 1, "Error!! Query execution failed...");
        }
        return value;
    }


    public void InsertIntoTable(String dbName, String queryStatement) throws ClassNotFoundException, IOException {
        ResultSet retVal = null;
        long id = 0;
        Connection conn = null;
        Statement stmt = null;
        String value = null;
        int flag = 1; //0=indicates no failure, 1=indicates success of all query execution
        initializeDbDomain();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DbUrl + dbName, User, Password);

            stmt = conn.createStatement();

            String sql = queryStatement;
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            flag = 0;
            se.printStackTrace();
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            Assert.assertTrue(flag == 1, "Error!! Query execution failed...");
        }
    }
    public String GetResultQueryExecutor(String dbName, String queryStatement,String columnName) throws ClassNotFoundException, IOException {
        Connection conn = null;
        Statement stmt = null;
        String value = null;
        int flag = 1; //0=indicates no failure, 1=indicates success of all query execution
        initializeDbDomain();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DbUrl + dbName, User, Password);

            stmt = conn.createStatement();

            String sql = queryStatement;
            ResultSet rs = stmt.executeQuery(sql);

            if (rs != null) {
                rs.beforeFirst();
                rs.last();
                rs.getRow();
                value = rs.getString(columnName);
            }
            System.out.println("value = " + value);
        } catch (SQLException se) {
            flag = 0;
            se.printStackTrace();
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            Assert.assertTrue(flag == 1, "Error!! Query execution failed...");
        }
        return value;
    }


    public List <List <String> > GetResultQueryExecutorAsList(String dbName, String queryStatement) throws ClassNotFoundException, IOException {
        Connection conn = null;
        Statement stmt = null;
        List <List <String> > result = new ArrayList<>();
        int flag = 1; //0=indicates no failure, 1=indicates success of all query execution
        initializeDbDomain();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DbUrl + dbName, User, Password);

            stmt = conn.createStatement();

            String sql = queryStatement;
            ResultSet resultset = stmt.executeQuery(sql);//from DB
            int numcols = resultset.getMetaData().getColumnCount();

            while (resultset.next()) {
                List <String> row = new ArrayList<>(numcols); // new list per row

                for (int i=1; i<= numcols; i++) {  // don't skip the last column, use <=
                    row.add(resultset.getString(i));
                    //System.out.print(resultset.getString(i) + "\t");
                }
                result.add(row); // add it to the result
                //System.out.println();
            }

        } catch (SQLException se) {
            flag = 0;
            se.printStackTrace();
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            Assert.assertTrue(flag == 1, "Error!! Query execution failed...");
        }
        return result;
    }


    public List <List <Integer> > GetResultQueryExecutorAsIntegerList(String dbName, String queryStatement) throws ClassNotFoundException, IOException {
        Connection conn = null;
        Statement stmt = null;
        List <List <Integer> > result = new ArrayList<>();
        int flag = 1; //0=indicates no failure, 1=indicates success of all query execution
        initializeDbDomain();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DbUrl + dbName, User, Password);

            stmt = conn.createStatement();

            String sql = queryStatement;
            ResultSet resultset = stmt.executeQuery(sql);//from DB
            int numcols = resultset.getMetaData().getColumnCount();

            while (resultset.next()) {
                List <Integer> row = new ArrayList<>(numcols); // new list per row

                for (int i=1; i<= numcols; i++) {  // don't skip the last column, use <=
                    row.add(resultset.getInt(i));
                    //System.out.print(resultset.getString(i) + "\t");
                }
                result.add(row); // add it to the result
                //System.out.println();
            }

        } catch (SQLException se) {
            flag = 0;
            se.printStackTrace();
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            Assert.assertTrue(flag == 1, "Error!! Query execution failed...");
        }
        return result;
    }
    //Generic Method to Get List of Values from DB
    public List<List<String>> getListValuesFromDatabase(String dbName, String sqlQuery){

        List<List<String>> objects= new ArrayList<>();
        try {
            objects=GetResultQueryExecutorAsList(dbName, sqlQuery);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<List<Integer>> getListIntegerValuesFromDatabase(String dbName, String sqlQuery){

        List<List<Integer>> objects= new ArrayList<>();
        try {
            objects=GetResultQueryExecutorAsIntegerList(dbName, sqlQuery);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public String getOfferId(String AdId) {
        String offerId = null;
        try {
            String query = "SELECT `id` FROM `escrow_offer` WHERE `adid`='" + AdId + "';";
            offerId = GetResultQueryExecutor("escrow_c2c", query);
            System.out.println("Offer ID for is : : " + offerId);
            LOGGER.info("Offer ID for is : : " + offerId);
        } catch (IOException io) {
            System.out.println("IOException in getOfferId");
            io.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            System.out.println("ClassNotFoundException in getOfferId");
            cnf.printStackTrace();
        }
        return offerId;
    }

    public String getAdDescription(String AdId){
        String tpcDescription = null;
        try {
            String query = "SELECT `tpc_description` FROM `babel_topic` WHERE `tpc_id`='" + AdId + "';";
            tpcDescription = GetResultQueryExecutor("kijiji_presentation", query);
        } catch (IOException io) {
            System.out.println("IOException in getAdDescription");
            io.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            System.out.println("ClassNotFoundException in getAdDescription");
            cnf.printStackTrace();
        }
        return tpcDescription;
    }

    public boolean ifSold(String AdId) {
        String status = null;
        try {
            String query = "SELECT `original_tpc_id` FROM `babel_topic` WHERE `tpc_id`='" + AdId + "';";
            status = GetResultQueryExecutor("kijiji_presentation", query);
        } catch (IOException io) {
            System.out.println("IOException in getOfferId");
            io.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            System.out.println("ClassNotFoundException in getOfferId");
            cnf.printStackTrace();
        }
        if (status.equals("1"))
            return true;
        else
            return false;
    }

    public Map<String, String> getAdDetails(String AdId) {
        String resultFromDb = null;
        Map<String, String> adDescvalues = new HashMap<String, String>();

        try {
            String query = "SELECT `tpc_description` FROM `babel_topic` WHERE `tpc_id`='" + AdId + "';";
            resultFromDb = GetResultQueryExecutor("kijiji_presentation", query);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] keyValuePair = resultFromDb.split("\n");
        for (int i = 0; i < keyValuePair.length; i++) {
            String[] keyValSeperation = keyValuePair[i].split(":");
            adDescvalues.put(keyValSeperation[0], keyValSeperation[1]);
        }
        return adDescvalues;
    }


    public boolean checkWareHouseStatus(String AdId) {
        String status = null;
        try {
            String query = "SELECT `ad_properties` FROM `ad_details` WHERE `adid`='" + AdId + "';";
            status = GetResultQueryExecutor("escrow_c2c", query);

        } catch (IOException io) {
            System.out.println("IOException in wareHouse status");
            io.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            System.out.println("ClassNotFoundException in wareHouseStatus");
            cnf.printStackTrace();
        }
        if (status.equals("1"))
            return true;
        else
            return false;
    }

    public String awbWareHouse(String AdId) {
        String awbVal = null;
        try {
            String query = "SELECT `comment` FROM `ad_status_history` WHERE `adid`='" + AdId + "';";
            awbVal = GetResultQueryExecutor("escrow_c2c", query);
        } catch (IOException io) {
            System.out.println("IOException in wareHouse status");
            io.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            System.out.println("ClassNotFoundException in wareHouseStatus");
            cnf.printStackTrace();
        }

        return awbVal;
    }

    public String wareHouseDetail(String AdId) {
        String adStatus = null;
        try {
            String query = "SELECT `ad_status` FROM `ad_details` WHERE `adid`='" + AdId + "';";
            adStatus = GetResultQueryExecutor("escrow_c2c", query);
            LOGGER.info("Delivery Status== " + adStatus);
        } catch (IOException io) {
            System.out.println("IOException in wareHouse status");
            io.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            System.out.println("ClassNotFoundException in wareHouseStatus");
            cnf.printStackTrace();
        }

        return adStatus;
    }


    public String[] deliveryFeedbackDBCheck(String AdId) {
        List<String> delivery = new ArrayList<>();
        String arr[] = new String[5];
        try {
            String query = "SELECT `is_item_picked`,`on_time_pickup`,`is_buyer`,`comment` FROM " +
                    "`delhivery_feedback` WHERE `adid`='" + AdId + "' order by `updated_time` DESC LIMIT 1;";
            delivery = getResultQuery("escrow_c2c", query, AdId);
            for (int i = 0; i < delivery.size(); i++) {
                arr[i] = delivery.get(i);
            }
        } catch (Exception io) {
            System.out.println("IOException in wareHouse status");
            io.printStackTrace();
        }
        return arr;
    }
    public boolean validatewarehousePropertyInAdDetails(String AdId){
        String tpcDescription = null;
        try {
            String query = "SELECT `ad_properties` FROM `ad_details` WHERE `adid`='" + AdId + "';";
            tpcDescription = GetResultQueryExecutor("escrow_c2c", query);
        } catch (IOException io) {
            System.out.println("IOException in getAdDescription");
            io.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            System.out.println("ClassNotFoundException in getAdDescription");
            cnf.printStackTrace();
        }
        if (tpcDescription.toString().equals("1")) {
            return true;
        }  else return false;

    }

    public ArrayList getResultQuery(String dbName, String query, String AdId) {

        initializeDbDomain();
        ResultSet retVal = null;
        ResultSetMetaData columnName = null;
        int numberOfColumns = 0;

        Connection conn = null;
        Statement stmt = null;

        ArrayList list = null;
        HashMap row = null;
        int flag = 1; //0=indicates no failure, 1=indicates success of all query execution

        initializeDbDomain();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DbUrl + dbName, User, Password);

            stmt = conn.createStatement();

            String sql = query;
            retVal = stmt.executeQuery(sql);

            if (retVal != null) {
                columnName = retVal.getMetaData();
                numberOfColumns = columnName.getColumnCount();
                list = new ArrayList();
                //row = new HashMap();
                while (retVal.next()) {
                       for (int i = 1; i <= numberOfColumns; i++) {

                        String column=columnName.getColumnName(i);
                        list.add(retVal.getString(column));

                    }

                }
                Thread.sleep(5000);
            }

        } catch (SQLException se) {
            flag = 0;
            se.printStackTrace();
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        } finally {
            try {
                retVal.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }


            Assert.assertTrue(flag == 1, "Error!! Query execution failed...");
        }
        return list;


    }

    //Generic method to return String value from Database
    public String returnStringValuefromDatabase(String dbName, String sqlQuery){

        initializeDbDomain();
        String value=null;
        try {
            value=GetResultQueryExecutor(dbName, sqlQuery);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    //Generic method to return int value from Database
    public int returnIntValuefromDatabase(String dbName, String sqlQuery){

        initializeDbDomain();
        int value=0;
        try {
            value=(Integer.parseInt(GetResultQueryExecutor(dbName, sqlQuery)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public String [] auctionId(String AdId) {
        List<String> delivery = new ArrayList<>();
        String arr[] = new String[10];
        try {
            String query = "SELECT `auction_id`,`status`,`start_time`,`end_time`,`payment_end_date`,`total_bids`,`max_bid_id` FROM " +
                    "`auction` WHERE `reference_id`='" + AdId + "';";
            delivery = getResultQuery("escrow_c2c", query, AdId);
            for (int i = 0; i < delivery.size(); i++) {
                arr[i] = delivery.get(i);
            }
        } catch (Exception io) {
            System.out.println("IOException in wareHouse status");
            io.printStackTrace();
        }
        return arr;
    }

    public String [] bidId(String auctionId) {
        List<String> delivery = new ArrayList<>();
        String arr[] = new String[20];
        try {
            String query = "SELECT `id`,`status` FROM " +
                    "`bid` WHERE `auction_id`='" + auctionId + "';";
            delivery = getResultQuery("escrow_c2c", query, auctionId);
            for (int i = 0; i < delivery.size(); i++) {
                arr[i] = delivery.get(i);
            }
        } catch (Exception io) {
            System.out.println("IOException in wareHouse status");
            io.printStackTrace();
        }
        return arr;
    }

}
