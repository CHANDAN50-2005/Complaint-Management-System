package com.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.myapp.model.Response;
import com.myapp.util.DBUtil;

public class ResponseDao {

    // Method to add a new response to the database
    public void addResponse(Response response) throws SQLException {
        String sql = "INSERT INTO responses (complaint_id, user_id, response_text) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, response.getComplaintId());
            stmt.setInt(2, response.getUserId());
            stmt.setString(3, response.getResponseText());
            stmt.executeUpdate();
        }
    }

    // Method to get all responses for a specific complaint
    public List<Response> getResponsesByComplaintId(int complaintId) throws SQLException {
        List<Response> responses = new ArrayList<>();
        // This query joins the responses and users tables to get the author's full name
        String sql = "SELECT r.*, u.full_name FROM responses r JOIN users u ON r.user_id = u.user_id WHERE r.complaint_id = ? ORDER BY r.created_at ASC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaintId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Response response = new Response();
                    response.setResponseId(rs.getInt("response_id"));
                    response.setComplaintId(rs.getInt("complaint_id"));
                    response.setUserId(rs.getInt("user_id"));
                    response.setResponseText(rs.getString("response_text"));
                    response.setCreatedAt(rs.getTimestamp("created_at"));
                    response.setAuthorFullName(rs.getString("full_name")); // Get author's name from the JOIN
                    responses.add(response);
                }
            }
        }
        return responses;
    }
}