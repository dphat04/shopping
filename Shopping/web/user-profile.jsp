

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
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
        .pending {
            color: burlywood;
        }
    </style>
    <title>User profile</title>
    <div class="container">
        <h1>User Profile</h1>
        <div class="row">
            <div class="col-md-4">
                <div class="card mb-4">
                    <form action="edit-profile" method="POST">
                        <div class="card-body">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">
                                ${errorMessage}
                            </div>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success">
                                ${successMessage}
                            </div>
                        </c:if>
                        <h5 class="card-title">Personal Information</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">
                                <strong>Name:</strong> 
                                <input type="text" name="name" value="${a.fullname}" style="display: block; width: 100%;">
                            </li>
                            <li class="list-group-item">
                                <strong>Email:</strong> 
                                <input type="text" name="email" value="${a.email}" style="display: block; width: 100%;">
                            </li>
                            <li class="list-group-item">
                                <strong>Address:</strong> 
                                <input type="text" name="address" value="${a.address}" style="display: block; width: 100%;">
                            </li>
                            <li class="list-group-item">
                                <input class="btn btn-secondary" type="submit" name="submit" value="Submit" style="display: block; margin: 0 auto;">
                            </li>
                        </ul>
                    </div>
                </form>
            </div>
            <h5 class="card-title">
                <a href="#" onclick="toggleChangePasswordForm()">Change Password</a>
            </h5>
            <form id="changePasswordForm" action="change-password" method="POST" style="display: none;">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <strong>Current Password:</strong> 
                        <input type="password" name="currentPassword" style="display: block; width: 100%;">
                    </li>
                    <li class="list-group-item">
                        <strong>New Password:</strong> 
                        <input type="password" name="newPassword" style="display: block; width: 100%;">
                    </li>
                    <li class="list-group-item">
                        <strong>Confirm New Password:</strong> 
                        <input type="password" name="confirmNewPassword" style="display: block; width: 100%;">
                    </li>
                    <li class="list-group-item">
                        <input class="btn btn-secondary" type="submit" name="changePassword" value="Change Password" style="display: block; margin: 0 auto;">
                    </li>
                </ul>
            </form>
        </div>

        <!--<div class="col-md-4">-->
        <!--<div class="card mb-4">-->
        <!--<div class="card-body">-->

        <!--</div>-->
        <!--</div>-->

        <div class="col-md-8">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Order History</h5>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Product</th>
                                <th>Date</th>
                                <th>Total</th>
                                <th>Status</th>
                                <th>Acction</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty orders}">
                                <tr>
                                    <td colspan="5" style="color: red">You have no order</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${orders}" var="o">
                                <tr>
                                    <td>${o.id}</td>
                                    <td>${o.productName}</td>
                                    <td>${o.createdAt}</td>
                                    <td>${o.price}</td>
                                    <td> <c:if test="${o.status eq 0}">
                                            <span class="pending">Pending</span>
                                        </c:if>
                                        <c:if test="${o.status eq 1}">
                                            <span class="active">Confirmed</span>
                                        </c:if>
                                        <c:if test="${o.status eq 2}">
                                            <span class="blocked">Canceled</span>
                                        </c:if></td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${o.status eq 0}">
                                                <button type="button" class="btn  btn-danger" data-order-id="${o.id}" onclick="confirmDelete(this)">cancel</button>
                                            </c:when>

                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                    <nav class="mt-3 text-center" aria-label="Page navigation example">
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
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>

<script>
    function confirmDelete(button) {
        Swal.fire({
            title: 'Confirmation',
            text: 'Are you sure you want to cancel this order?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes',
            cancelButtonText: 'Cancel'
        }).then((result) => {
            if (result.isConfirmed) {
                deleteProduct(button);
            }
        });
    }
    function toggleChangePasswordForm() {
        var form = document.getElementById("changePasswordForm");
        if (form.style.display === "none") {
            form.style.display = "block";
        } else {
            form.style.display = "none";
        }
    }

    function deleteProduct(button) {
        var productId = button.getAttribute("data-order-id");

        // Send an AJAX request to delete the product
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "cancel-order?orderId=" + encodeURIComponent(productId), true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                setTimeout(function () {

                    window.location.reload();

                }, 2000);
                Swal.fire({
                    title: 'Success',
                    text: 'Product deleted successfully.',
                    icon: 'success'
                });
            }
        };
        xhr.send();
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
</html>
