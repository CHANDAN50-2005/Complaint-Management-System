package com.myapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.myapp.dao.ComplaintDao;

@WebServlet("/updateStatus")
public class UpdateStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ComplaintDao complaintDao;

    public void init() {
        complaintDao = new ComplaintDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int complaintId = Integer.parseInt(request.getParameter("complaintId"));
        String status = request.getParameter("status");
        
        try {
            complaintDao.updateComplaintStatus(complaintId, status);
            response.sendRedirect("admin-dashboard?message=Status Updated");
        } catch (SQLException e) {
            throw new ServletException("Database error while updating status", e);
        }
    }
}