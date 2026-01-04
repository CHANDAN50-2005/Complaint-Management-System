package com.myapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.myapp.dao.ComplaintDao;
import com.myapp.model.Complaint;
import com.myapp.model.User;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ComplaintDao complaintDao;

    public void init() {
        complaintDao = new ComplaintDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            // If no user is logged in, redirect to the login page
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            List<Complaint> complaints = complaintDao.getComplaintsByCustomerId(user.getUserId());
            request.setAttribute("customerComplaints", complaints);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve complaints", e);
        }
    }
}