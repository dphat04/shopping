<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
        <style>
            .navbar-nav {
                flex-direction: row;
                align-items: center;
            }

            .navbar-nav .nav-link {
                padding-right: 0.5rem;
                padding-left: 0.5rem;
            }
            .badge {
                position: absolute;
                top: -8px;
                right: -8px;
                padding: 4px 6px;
                border-radius: 50%;
                background-color: red;
                color: white;
            }
        </style>

    </head>
    <title>Login</title>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 mt-5">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">Login</h3>
                        <%
    String messageSuccess = request.getParameter("messageSuccess");
    if (messageSuccess != null && !messageSuccess.isEmpty()) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <%= messageSuccess %>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty message}">
                            <div class="alert alert-danger" role="alert">
                                ${message}
                            </div>
                        </c:if>
                        <form action="login" method="POST">
                            <div class="mb-3">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Login</button>
                            </div>
                        </form>
                        <br>
                        <p style="text-align: center"> Don't have account ?
                        <div class="d-grid">
                            <a href="register" class="btn btn-primary">Register</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
