<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Customer Dashboard</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        </head>

        <body>
            <div class="container mt-4">
                <div class="app-header">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h1>Customer Dashboard</h1>
                            <p class="lead mb-0">Welcome, ${sessionScope.user.fullName}</p>
                        </div>
                        <div>
                            <a href="logout" class="btn btn-outline-light">Logout</a>
                        </div>
                    </div>
                </div>

                <div class="content-card">
                    <h3>File a New Complaint</h3>
                    <form action="addComplaint" method="post" class="mb-5">
                        <div class="mb-3">
                            <textarea name="description" class="form-control" rows="4"
                                placeholder="Describe your issue here..." required></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit Complaint</button>
                    </form>
                </div>

                <div class="content-card">
                    <h3>My Complaints</h3>
                    <table class="table table-striped table-hover align-middle">
                        <thead>
                            <tr>
                                <th>Complaint ID</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Date Filed</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="complaint" items="${customerComplaints}">
                                <tr>
                                    <td>${complaint.complaintId}</td>
                                    <td><a href="viewComplaint?id=${complaint.complaintId}">${complaint.description}</a>
                                    </td>
                                    <td>
                                        <span class="badge 
                                    ${complaint.status == 'Pending' ? 'text-bg-warning' : ''}
                                    ${complaint.status == 'In Progress' ? 'text-bg-info' : ''}
                                    ${complaint.status == 'Resolved' ? 'text-bg-success' : ''}">
                                            ${complaint.status}
                                        </span>
                                    </td>
                                    <td>${complaint.createdAt}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </body>

        </html>