package com.bhavya.businesslogic;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class FileReadTest {
    @Test
    public void testCall() {
        Connection con = null;

        try {
            // Create JDBC connection to the mysql database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demodb", "root", "Bhavya@1");
            Statement stmt = con.createStatement();
            String sql = "delete from sensordata where SensorId = -1";
            int rslt = stmt.executeUpdate(sql);

            FileRead fileRead = new FileRead("src/test/resources/sensor1/sensor-fault-detection.csv");
            fileRead.call();
            ResultSet rs = stmt.executeQuery("select * from sensordata where SensorId = -1");
            int sensorId = 0;
            float value = 0f;
            int count = 0;

            while(rs.next()) {
                count++;
                sensorId = rs.getInt("sensorid");
                value = rs.getFloat("value");
            }

            assertEquals(sensorId, -1);
            assertEquals(value, 200.0f, 0.0001);
            assertEquals(count, 1);
        }
        catch(Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            }
            catch(SQLException sqle) {
                System.err.println("Error message " + sqle.getMessage());
            }
        }
    }
}