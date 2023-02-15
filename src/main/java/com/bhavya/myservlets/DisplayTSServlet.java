package com.bhavya.myservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.bhavya.businesslogic.SensorData;
import java.util.List;

/**
 * This servlet class retrieves distinct timestamps from the mysql database and forwards it to the display timestamps page
 *
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */

public class DisplayTSServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
          SensorData sensorData = new SensorData();
          List list = sensorData.getDistinctTimestamps();
		HttpSession session = req.getSession();
		session.setAttribute("distinctTimestamps", list);//putting object in session
		

          RequestDispatcher requestDispatcher = req
                    .getRequestDispatcher("displaytimestamps.jsp");//extension should be jsp
          requestDispatcher.forward(req, res);//forwarding from servlet to jsp
		
    }

}
