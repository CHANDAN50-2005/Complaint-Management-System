package com.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.myapp.model.Complaint;
import com.myapp.util.DBUtil;

public class ComplaintDao {

    // Method to create a new complaint
    public void createComplaint(Complaint complaint) throws SQLException {
        String sql = "INSERT INTO complaints (customer_id, description, status) VALUES (?, ?, 'Pending')";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaint.getCustomerId());
            stmt.setString(2, complaint.getDescription());
            stmt.executeUpdate();
        }
    }

    // Method to get all complaints for a specific customer
    public List<Complaint> getComplaintsByCustomerId(int customerId) throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints WHERE customer_id = ? ORDER BY created_at DESC";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, customerId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Complaint complaint = new Complaint();
                    complaint.setComplaintId(rs.getInt("complaint_id"));
                    complaint.setCustomerId(rs.getInt("customer_id"));
                    complaint.setDescription(rs.getString("description"));
                    complaint.setStatus(rs.getString("status"));
                    complaint.setCreatedAt(rs.getTimestamp("created_at"));

                    complaints.add(complaint);
                }
            }
        }
        return complaints;
    }
    
    // Method to get all complaints for the admin
    public List<Complaint> getAllComplaints() throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints ORDER BY created_at DESC";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(rs.getInt("complaint_id"));
                complaint.setCustomerId(rs.getInt("customer_id"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getString("status"));
                complaint.setCreatedAt(rs.getTimestamp("created_at"));

                complaints.add(complaint);
            }
        }
        return complaints;
    }
    
    // Method to update a complaint's status
    public void updateComplaintStatus(int complaintId, String status) throws SQLException {
        String sql = "UPDATE complaints SET status = ? WHERE complaint_id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, complaintId);
            stmt.executeUpdate();
        }
    }
    
    // Method to search complaints

    
    // Method to get a single complaint by its ID
    public Complaint getComplaintById(int complaintId) throws SQLException {
        Complaint complaint = null;
        String sql = "SELECT * FROM complaints WHERE complaint_id = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, complaintId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    complaint = new Complaint();
                    complaint.setComplaintId(rs.getInt("complaint_id"));       
                    complaint.setCustomerId(rs.getInt("customer_id")); 
                    complaint.setDescription(rs.getString("description"));
                    complaint.setStatus(rs.getString("status"));
                    complaint.setCreatedAt(rs.getTimestamp("created_at"));

                }
            }
        }
        return complaint;
    }
}