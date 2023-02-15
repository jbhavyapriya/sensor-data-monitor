package com.bhavya.businesslogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

/**
 * This class reads the files from the directory, inserts sensor data into the
 * mysql table
 *
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */
public class FileRead implements Callable {
    private String fileName;

    public FileRead(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * This method is used to read the data from the files, process the data
     * and insert the sensor data into mysql table.
     *
     * @return String returns SUCCESS if the file read and insert table operations are successful,
     *                FAILURE otherwise
     * @throws Exception  If an exception occurs while reading the file or inserting data into
     *                    the table
     */
    public String call() throws Exception {
        String status;
        BufferedReader reader = null;
        Connection con = null;

        try {
            // Create JDBC connection to the mysql database
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demodb","root","Bhavya@1");
             Statement stmt=con.createStatement();

            reader = new BufferedReader(new FileReader(fileName));
            String line;

            // Read file line by line and process data
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                boolean isHeader = false;
                String ts = null;
                int sensorid = 0;
                float values = 0f;

                while (tokenizer.hasMoreElements()) {
                    ts = tokenizer.nextToken();
                    if (ts.equals("Timestamp")) {
                        isHeader = true;
                        break;
                    }
                    sensorid=Integer.parseInt(tokenizer.nextToken());
                    values=Float.parseFloat(tokenizer.nextToken());

                }

                if (!isHeader) {
                    // insert statement into table
                    String sql = "INSERT INTO sensordata (Timestamp, SensorId, Value) VALUES ('" + ts + "'," + sensorid + "," + values + ");";
                    System.out.println("SQL statement ****** " + sql + " ts : " + ts + " sensor id " + sensorid + " value: " + values);
                    int rslt = stmt.executeUpdate(sql);
                }

            }
            status = "SUCCESS";
        }
        catch(Exception e) {
            System.err.println("Error : " + e.getMessage());
            status = "FAILURE";
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (con != null) {
                    con.close();
                }

            }
            catch(IOException ioe) {
                System.err.println("Error message " + ioe.getMessage());
            }
            catch(SQLException sqle) {
                System.err.println("Error message " + sqle.getMessage());

            }

        }

        System.out.println("Status ****** " + status);
        return status;
    }
}