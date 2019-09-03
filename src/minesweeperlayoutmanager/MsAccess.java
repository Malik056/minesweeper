/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperlayoutmanager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author jawad.waheed
 */

class MsAccess {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    MsAccess() {

        // variables
        // Step 1: Loading or registering Oracle JDBC driver class
        
        startConnection();

    }

    public void startConnection()
    {
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        }
        catch(ClassNotFoundException cnfex) {

            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        // Step 2: Opening database connection
        try {

            String msAccDB = System.getProperty("user.dir")+"//DB//db1.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB; 

            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL); 

            // Step 2.B: Creating JDBC Statement 
            statement = connection.createStatement();

            // Step 2.C: Executing SQL & retrieve data into ResultSet
//                resultSet = statement.executeQuery("SELECT * FROM Player");
//
//                System.out.println("ID\tName\t");
//                System.out.println("==\t================\t===\t=======");
//
//                // processing returned data and printing into console
//                while(resultSet.next()) {
//                    System.out.println(resultSet.getInt(1) + "\t" + 
//                            resultSet.getString(2) + "\t");
//                }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {


        }

    }

    
    
    public Statement getStatement()
    {
        return statement;
    }
    
    
    public void closeConnection()
    {
           // Step 3: Closing database connection
                try {
                    if(null != connection) {

                        // cleanup resources, once after processing
                        resultSet.close();
                        statement.close();

                        // and then finally close connection
                        connection.close();
                    }
                }
                catch (SQLException sqlex) {
                    sqlex.printStackTrace();
                }
    }
        
        
}