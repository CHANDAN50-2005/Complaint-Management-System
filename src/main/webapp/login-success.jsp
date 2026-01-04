<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Success</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div>
                        <h1>Success!</h1>
                    </div>
                    <div class="text-center">
                        <h2>Login Successful!</h2>
                        <p class="lead">Welcome, ${sessionScope.user.fullName}</p>
                    </div>
                </div>
            </div>
        </div>
    </body>

    </html>