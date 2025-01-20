package controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.City;
import models.UserType;
import utils.AppUtil;
import utils.EmailSender;
import utils.EmailTemplates;

@WebServlet("/signup.do")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        try {
            String name = request.getParameter("name").trim();
            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();
            String contact = request.getParameter("contact").trim();
            
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || contact.isEmpty()) {
                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                return;
            }
            
            Integer cityId = Integer.parseInt(request.getParameter("city_id"));
            Integer userTypeId = Integer.parseInt(request.getParameter("user_type_id"));

            String verificationCode = AppUtil.generateEmailVerificationCode();
            User user = new User(name, email, password, new City(cityId), contact, new UserType(userTypeId), verificationCode);
            
            if (user.saveUser()) {
                String from = context.getInitParameter("from_email");
                String fromPassw = context.getInitParameter("from_email_password");
                String subject = "Email Verification Mail";

                EmailSender.sendEmail(from, fromPassw, email, subject, EmailTemplates.generateWelcomeMail(name, email, verificationCode));
                
                String userPath = context.getRealPath("/WEB-INF/uploads/" + UserType.types[userTypeId - 1]);
                File userDir = new File(userPath, email);
                if (!userDir.exists()) {
                    userDir.mkdirs(); 
                }
            } else {
                request.setAttribute("error", "Failed to register user. Try again.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input for city or user type.");
        } catch (Exception e) {
            request.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
