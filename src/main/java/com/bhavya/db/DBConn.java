package com.bhavya.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a connection between the java and mysql by making use of jdbc driver
 *
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */

public class DBConn
{
    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demodb", "root", "Bhavya@1");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return con;
    }
}