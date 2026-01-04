<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Complaint Details</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        </head>

        <body>
            <div class="container mt-4">
                <div class="app-header">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <h1>Complaint Details</h1>
                        </div>
                        <div>
                            <c:choose>
                                <c:when
                                    test="${sessionScope.user.role eq 'ADMIN' or sessionScope.user.role eq 'Admin'}">
                                    <a href="admin-dashboard" class="btn btn-outline-light">Back to Dashboard</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="dashboard" class="btn btn-outline-light">Back to Dashboard</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <div class="content-card">
                    <h3>Original Complaint</h3>
                    <p><strong>Status:</strong>
                        <span class="badge 
                    ${complaint.status == 'Pending' ? 'text-bg-warning' : ''}
                    ${complaint.status == 'In Progress' ? 'text-bg-info' : ''}
                    ${complaint.status == 'Resolved' ? 'text-bg-success' : ''}">
                            ${complaint.status}
                        </span>
                    </p>
                    <p class="lead">${complaint.description}</p>
                    <small class="text-muted">Filed on: ${complaint.createdAt}</small>
                </div>

                <div class="content-card">
                    <h3>Conversation</h3>
                    <hr>
                    <div class="chat-container">
                        <c:forEach var="response" items="${responses}">
                            <div class="message-row">
                                <c:choose>
                                    <c:when test="${sessionScope.user.userId == response.userId}">
                                        <div class="chat-bubble sent">
                                            <strong>You</strong>
                                            <small class="text-white-50">(${response.createdAt})</small>
                                            <p class="mb-0">${response.responseText}</p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="chat-bubble received">
                                            <strong>${response.authorFullName}</strong>
                                            <small class="text-muted">(${response.createdAt})</small>
                                            <p class="mb-0">${response.responseText}</p>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </div>

                    <hr>
                    <h4 class="mt-4">Add Your Response</h4>
                    <form action="addResponse" method="post">
                        <input type="hidden" name="complaintId" value="${complaint.complaintId}">
                        <div class="mb-3">
                            <textarea name="responseText" rows="4" class="form-control" required
                                placeholder="Type your response here..."></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit Response</button>
                    </form>
                </div>
            </div>
        </body>

        </html>