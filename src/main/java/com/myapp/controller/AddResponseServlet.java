package com.myapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.myapp.dao.ResponseDao;
import com.myapp.model.Response;
import com.myapp.model.User;

@WebServlet("/addResponse")
public class AddResponseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ResponseDao responseDao;

    public void init() {
        responseDao = new ResponseDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int complaintId = Integer.parseInt(request.getParameter("complaintId"));
        String responseText = request.getParameter("responseText");

        Response newResponse = new Response();
        newResponse.setComplaintId(complaintId);
        newResponse.setUserId(user.getUserId());
        newResponse.setResponseText(responseText);

        try {
            responseDao.addResponse(newResponse);
            // Redirect back to the details page to see the new response
            response.sendRedirect("viewComplaint?id=" + complaintId);
        } catch (SQLException e) {
            throw new ServletException("Database error while adding response", e);
        }
    }
}