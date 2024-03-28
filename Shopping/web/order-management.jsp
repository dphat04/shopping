
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
            <title>Orders Management</title>
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
                .pending {
                    color: burlywood;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>Orders Management</h1>
                <h2 style="text-align: center">Orders List</h2>
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
                                <th>Orders</th>
                                <th>User</th>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Create At</th>
                                <th>Status</th>
                                <th>Action</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty orders}">
                                <tr>
                                    <td colspan="5" style="color: red">Have no order in your system</td>
                                </tr>
                            </c:if>
                            <c:forEach var="a" items="${orders}">
                                <tr>
                                    <td>${a.id}</td>
                                    <td>${a.customerFullName}</td>
                                    <td>${a.productName}</td>
                                    <td>${a.quantity}</td>
                                    <td>${a.price}</td>
                                    <td>${a.createdAt}</td>
                                    <td>
                                        <c:if test="${a.status eq 0}">
                                            <span class="pending">Pending</span>
                                        </c:if>
                                        <c:if test="${a.status eq 1}">
                                            <span class="active">Confirmed</span>
                                        </c:if>
                                        <c:if test="${a.status eq 2}">
                                            <span class="blocked">Canceled</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${a.status eq 0}">
                                                <!--<a class="btn btn-sm btn-success" onclick="return confirmBlock(this)" href="confirm-order?orderId=${a.id}">Confirm</a>-->
                                                <button type="button" class="btn  btn-success" data-order-id="${a.id}" onclick="confirmDelete(this)">Confirm</button>
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
    <script>
        function confirmDelete(button) {
            Swal.fire({
                title: 'Confirmation',
                text: 'Are you sure you want to confirm this order?',
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

        function deleteProduct(button) {
            var productId = button.getAttribute("data-order-id");

            // Send an AJAX request to delete the product
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "confirm-order?orderId=" + encodeURIComponent(productId), true);
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

</html>
