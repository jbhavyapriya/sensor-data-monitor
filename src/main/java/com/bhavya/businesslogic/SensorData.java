package com.bhavya.businesslogic;

import com.bhavya.db.DBConn;
import jdk.nashorn.internal.parser.JSONParser;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
/**
 * This class retrieves distinct timestamps and sensor data from the database.
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */
public class SensorData {
    /**
     * This method is used to access and fetch the distinct timestamps from the database.
     * This timestamp gets added to the arraylist.
     * @return List of distinct timestamps
     *
     */
    public List getDistinctTimestamps()
    {
        Connection con = null;
        ArrayList<String> data = new ArrayList<String>();


        try{
            //Get mysql database connection
            con = DBConn.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select distinct Timestamp from sensordata");

            while(rs.next()) {
                Timestamp ts = rs.getTimestamp("Timestamp");
                data.add(ts.toString());
                System.out.println(ts.toString() );
            }
        }catch(Exception e){ System.out.println(e.getMessage());}
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            }
            catch(SQLException sqle) {
                System.out.println("Error while closing the connection  "+sqle.getMessage());
            }
        }

        return data;
    }

    /**
     * This method is used to access and fetch all the sensor details like id,timestamp,SensorId,Value.
     * All of these details gets added to the arraylist.
     */
    public List getSensorData(String timestamp){
        Connection con = null;
        ArrayList<SensorDetails> data = new ArrayList<SensorDetails>();


        try{
            //Get mysql database connection
            con = DBConn.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from sensordata where Timestamp='" + timestamp + "'");

            while(rs.next()) {
                SensorDetails sensorDetails = new SensorDetails();
                Timestamp ts = rs.getTimestamp("Timestamp");
                sensorDetails.setTimestamp(ts.toString());
                sensorDetails.setSensorId(rs.getInt("SensorId"));
                sensorDetails.setValue(rs.getFloat("Value"));
                data.add(sensorDetails);
                System.out.println(rs.getTimestamp("Timestamp").toString() + "  " + rs.getInt("SensorId") + "  " + rs.getFloat("Value"));
            }
        }catch(Exception e){ System.out.println(e);}
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            }
            catch(SQLException sqle) {

            }
        }

        return data;
    }

    public static void main(String[] args) {
        SensorData sensorData = new SensorData();
        List data = sensorData.getDistinctTimestamps();
    }
}
