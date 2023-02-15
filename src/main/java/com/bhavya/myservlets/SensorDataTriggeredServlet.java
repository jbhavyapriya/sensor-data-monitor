package com.bhavya.myservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.bhavya.businesslogic.SensorDataTrigger;

/**
 * In this class we schedules the jobs using quartz scheduler.
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */
public class SensorDataTriggeredServlet extends HttpServlet{ 

    public void init() throws ServletException
    {
		try {
			SensorDataTrigger.triggerSensorDataCollection();
		}
		catch (Exception e) {
			System.err.println("Error ********: " + e.getMessage());
		}
    }
}
