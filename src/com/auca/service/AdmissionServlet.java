package com.auca.service;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdmissionServlet extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process admission form data
        String userName = (String) request.getSession().getAttribute("userName");

        System.out.println(userName);
        // Send confirmation email
        sendConfirmationEmail(userName);

        // Redirect to a confirmation page
        response.sendRedirect("welcome.html");
        
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale("en")); // Default to English
        String userLanguage = (String) request.getSession().getAttribute("userLanguage");
        if (userLanguage != null) {
            messages = ResourceBundle.getBundle("messages", new Locale(userLanguage));
        }

    }

    private void sendConfirmationEmail(String recipientEmail) {
        // Configure email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");


        // sender email credentials
        String username = "techornell@gmail.com";
        String password = "hieo oqyj ltfj drdm";

        // Create session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Creating MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Admission Confirmation");
            message.setText("Thank you for submitting your admission.");
            Transport.send(message);
            System.out.println("Confirmation email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
