<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Admin Dashboard</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        </head>

        <body>
            <div class="container mt-4">
                <div class="app-header">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h1>Admin Dashboard</h1>
                            <p class="lead mb-0">Welcome, ${sessionScope.user.fullName}</p>
                        </div>
                        <div>
                            <a href="logout" class="btn btn-outline-light">Logout</a>
                        </div>
                    </div>
                </div>

                <div class="content-card">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h3>All Customer Complaints</h3>
                    </div>

                    <table class="table table-striped table-hover align-middle">
                        <thead class="table-light">
                            <tr>
                                <th>ID</th>
                                <th>Customer ID</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Date Filed</th>
                                <th style="min-width: 250px;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="complaint" items="${allComplaints}">
                                <tr>
                                    <td>${complaint.complaintId}</td>
                                    <td>${complaint.customerId}</td>
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
                                    <td>
                                        <form action="updateStatus" method="post"
                                            class="d-flex align-items-center gap-2">
                                            <input type="hidden" name="complaintId" value="${complaint.complaintId}">

                                            <select name="status" class="form-select form-select-sm">
                                                <option value="Pending" ${complaint.status=='Pending' ? 'selected' : ''
                                                    }>
                                                    Pending</option>
                                                <option value="In Progress" ${complaint.status=='In Progress'
                                                    ? 'selected' : '' }>In Progress</option>
                                                <option value="Resolved" ${complaint.status=='Resolved' ? 'selected'
                                                    : '' }>
                                                    Resolved</option>
                                            </select>

                                            <button type="submit" class="btn btn-primary btn-sm">Update</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </body>

        </html>