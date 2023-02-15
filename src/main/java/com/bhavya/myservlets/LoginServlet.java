package com.bhavya.myservlets;

import com.bhavya.businesslogic.SensorData;
import com.mysql.cj.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This servlet class handles login functionality. If authentication fails, it will display
 * error message
 *
 * @author  J Bhavya Priya
 * @version 1.0
 * @since   2023-02-15
 */

public class LoginServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName=req.getParameter("userName");
        String pass=req.getParameter("userPassword");
        HttpSession session = req.getSession();


        if((userName != null && userName.equals("happy")) && (pass != null && pass.equals("happy")))
        {
            RequestDispatcher requestDispatcher = req
                    .getRequestDispatcher("DisplayTSServlet");//extension should be jsp
            requestDispatcher.forward(req,res);
        }
        else{
            session.setAttribute("error","INVALID CREDENTIALS");//putting object in session
            RequestDispatcher requestDispatcher = req
                    .getRequestDispatcher("login.jsp");
            requestDispatcher.include(req,res);
        }
    }
}
