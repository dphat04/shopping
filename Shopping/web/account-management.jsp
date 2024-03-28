
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
            <title>Account Management</title>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
            <style>
                table {
                    width: 100%;
                    border-collapse: collapse;
                }

                th, td {
                    padding: 8px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                }

                .active {
                    color: green;
                }

                .blocked {
                    color: red;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>Account Management</h1>
                <h2 style="text-align: center">Account List</h2>
            <c:if test="${not empty Errmessage}">
                <div class="alert alert-danger">
                    ${Errmessage}
                </div>
            </c:if>
            <c:if test="${not empty SussMessage}">
                <div class="alert  alert-success">
                    ${SussMessage}
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-12">

                    <table class="table">
                        <thead>
                            <tr>
                                <th>User Name</th>
                                <th>Email</th>
                                <th>Create At</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Action</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="a" items="${accounts}">
                                <tr>
                                    <td>${a.username}</td>
                                    <td>${a.email}</td>
                                    <td>${a.createdAt}</td>

                                    <td>
                                        <c:if test="${a.role eq 1}">
                                            <span class="active">Admin</span>
                                        </c:if>
                                        <c:if test="${a.role ne 1}">
                                            <span class="blocked">User</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${a.status eq 1}">
                                            <span class="active">Active</span>
                                        </c:if>
                                        <c:if test="${a.status ne 1}">
                                            <span class="blocked">Block</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${a.status eq 1}">
                                                <a class="btn btn-sm btn-danger" onclick="return confirmBlock(this)" href="block-user?accountId=${a.id}">Block</a>
                                            </c:when>
                                            <c:when test="${a.status eq 0 and a.role ne 1}">
                                                <a class="btn btn-sm btn-success" onclick="return confirmUnblock(this)" href="block-user?accountId=${a.id}">Unblock</a>
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Add Product Modal -->



        <nav  class="mt-3 text-center" aria-label="Page navigation example">
            <ul class="pagination d-inline-flex">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}&amp;pageSize=${pageSize}">&laquo; Previous</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="pageNumber">
                    <c:choose>
                        <c:when test="${pageNumber == currentPage}">
                            <li class="page-item active">
                                <span class="page-link">${pageNumber}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="?page=${pageNumber}&amp;pageSize=${pageSize}">${pageNumber}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}&amp;pageSize=${pageSize}">Next &raquo;</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>

</html>
