package com.myapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.myapp.dao.UserDao;
import com.myapp.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            User user = userDao.validateUser(email, password);
            
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Check user's role and redirect accordingly
                String role = user.getRole();
                if (role != null && "ADMIN".equalsIgnoreCase(role.trim())) {
                    // Redirect to admin dashboard
                    response.sendRedirect("admin-dashboard");
                } else {
                    // Redirect to customer dashboard
                    response.sendRedirect("dashboard");
                }
            } else {
                // User is not valid, redirect back to login page with an error
                response.sendRedirect("index.jsp?error=1");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error during login", e);
        }
    }
}