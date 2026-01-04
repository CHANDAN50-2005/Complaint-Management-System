package com.myapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.myapp.dao.ComplaintDao;
import com.myapp.model.Complaint;
import com.myapp.model.User;

@WebServlet("/addComplaint")
public class AddComplaintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ComplaintDao complaintDao;

    public void init() {
        complaintDao = new ComplaintDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String description = request.getParameter("description");

        Complaint newComplaint = new Complaint();
        newComplaint.setCustomerId(user.getUserId());
        newComplaint.setDescription(description);

        try {
            complaintDao.createComplaint(newComplaint);
            response.sendRedirect("dashboard"); // Redirect to the dashboard to see the updated list
        } catch (SQLException e) {
            throw new ServletException("Cannot add complaint", e);
        }
    }
}