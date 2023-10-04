package com.auca.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet{
	private String name = "ornellakankazi@gmail.com";
    private String password = "ornella123";

    public void service(HttpServletRequest request, HttpServletResponse response) {
        String newPassword = request.getParameter("newPassword");

        if (newPassword != null) {
            // Password reset logic
            String confirmPassword = request.getParameter("confirmPassword");

            if (newPassword.equals(confirmPassword)) {
                // Set the new password
                password = newPassword;

                // Redirect the user back to the login page after resetting the password
                try {
                    response.sendRedirect("login.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
            	try {
                    response.sendRedirect("fgPassword.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            String userName = request.getParameter("name");
            String userPassword = request.getParameter("password");

            if (name.equals(userName) && password.equals(userPassword)) {
                // Successful login, redirect to welcome page
                try {
                	request.getSession().setAttribute("userName", userName);
                    response.sendRedirect("admission.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Redirect to the "Forgot Password" page
                try {
                    response.sendRedirect("fgPassword.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
