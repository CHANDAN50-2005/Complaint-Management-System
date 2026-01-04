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
import com.myapp.dao.ComplaintDao;
import com.myapp.dao.ResponseDao;
import com.myapp.model.Complaint;
import com.myapp.model.Response;

@WebServlet("/viewComplaint")
public class ComplaintDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ComplaintDao complaintDao;
    private ResponseDao responseDao;

    public void init() {
        complaintDao = new ComplaintDao();
        responseDao = new ResponseDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get the complaint ID from the URL parameter
        int complaintId = Integer.parseInt(request.getParameter("id"));
        
        try {
            // 2. Fetch the specific complaint from the database
            Complaint complaint = complaintDao.getComplaintById(complaintId);
            
            // 3. Fetch all responses for that complaint
            List<Response> responses = responseDao.getResponsesByComplaintId(complaintId);
            
            // 4. Set the complaint and responses as attributes to be used in the JSP
            request.setAttribute("complaint", complaint);
            request.setAttribute("responses", responses);
            
            request.setAttribute("complaintId", complaintId); // Pass the ID for the form
            
            // 5. Forward the request to the JSP page for display
            RequestDispatcher dispatcher = request.getRequestDispatcher("complaint-details.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve complaint details", e);
        }
    }
}