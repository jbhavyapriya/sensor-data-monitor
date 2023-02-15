package com.bhavya.myservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.bhavya.businesslogic.SensorData;
import java.util.List;

/**
 * This servlet class retrieves all the sensor data from the mysql database and forwards it to the sensor details page
 *
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */

public class DisplaySensorDataServlet extends HttpServlet{ 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
        //Reads selected timestamp from the dropdown
		String ts = req.getParameter("timestamp");
        SensorData sensorData = new SensorData();
        List list = sensorData.getSensorData(ts);
		HttpSession session = req.getSession();
		session.setAttribute("sensorDetails", list);
		session.setAttribute("selectedTs", ts);//Displays the selected Timestamp in the dropdown menu
		

          RequestDispatcher requestDispatcher = req
                    .getRequestDispatcher("sensordetails.jsp");//extension should be jsp
          requestDispatcher.forward(req, res);//forwarding from servlet to jsp
		
    }

}
