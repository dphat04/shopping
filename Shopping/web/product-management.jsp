
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="pd" class="dal.ProductDAO" />
<jsp:include page="header.jsp"></jsp:include>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
            <title>Product Management</title>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>
        </head>
        <body>
            <div class="container">
                <h1>Product Management</h1>
                <h2 style="text-align: center">Product List</h2>
            <c:if test="${not empty param.mess}">

                <h3 style="text-align: center; color: green">     ${param.mess}</h3>
            </c:if>


            <div class="row">
                <div class="col-md-12">
                    <div class="d-flex justify-content-between mb-3">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCateModal">Add Category</button>
                        <button style="float: right" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductModal">Add Product</button>
                    </div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Color</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Category</th>
                                <th>Image</th>
                                <th>Description</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>${product.name}</td>
                                    <td>
                                        <ul>
                                            <c:forEach var="size" items="${product.sizes}">
                                                <li>${size}</li>
                                                </c:forEach>
                                        </ul>
                                    </td>
                                    <td>
                                        <ul>
                                            <c:forEach var="color" items="${product.colors}">
                                                <li>${color}</li>
                                                </c:forEach>
                                        </ul>
                                    </td>
                                    <td>${product.price}</td>
                                    <td>${product.quantity}</td>
                                    <td>${product.categoryName}</td>
                                    <td><img src="${product.image}" alt="Product Image" width="100" height="100"></td>
                                    <td>${product.description}</td>
                                    <td>
                                        <a class="btn btn-sm btn-primary" href="edit-product?id=${product.id}">Edit</a>
                                        <button type="button" class="delete-btn" data-product-id="${product.id}" onclick="confirmDelete(this)">Delete</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Add Product Modal -->
        <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addProductModalLabel">Add Product</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="add-product" method="post">
                            <div class="mb-3">
                                <label for="name" class="form-label">Product Name</label>
                                <input type="text" name="name" class="form-control" id="name" required>
                            </div>
                            <div class="mb-3">
                                <label for="price" class="form-label">Price</label>
                                <input type="number" step="0.01" class="form-control" name="price" id="price" required>
                            </div>
                            <div class="mb-3">
                                <label for="quantity" class="form-label">Quantity</label>
                                <input type="number" step="0.01" class="form-control" name="quantity" id="quantity" required>
                            </div>
                            <div class="mb-3">
                                <label for="image" class="form-label">Description</label>
                                <input type="text" step="0.01" class="form-control" name="description" id="image" required>
                            </div>
                            <div class="mb-3">
                                <label for="image" class="form-label">Image</label>
                                <input type="text"class="form-control" name="image" id="image" required>
                            </div>
                            <div class="mb-3">
                                <label for="category" class="form-label">Category</label>
                                <select class="form-control" id="category" name="category" required>
                                    <option value="">Select Category</option>
                                    <c:forEach items="${listC}" var="c">
                                        <option value="${c.categoryId}">${c.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="colors" class="form-label">Colors</label><br>
                                <c:forEach items="${color}" var="color">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="colors" value="${color.id}"> ${color.name}
                                    </label><br>
                                </c:forEach>
                            </div>
                            <div class="mb-3">
                                <label for="sizes" class="form-label">Sizes</label><br>
                                <c:forEach items="${size}" var="size">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="sizes" value="${size.id}"> ${size.name}
                                    </label><br>
                                </c:forEach>
                            </div>
                            <button type="submit" class="btn btn-primary">Add</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="addCateModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addProductModalLabel">Add Category</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="add-category" method="post">
                            <div class="mb-3">
                                <label for="name"  class="form-label">Category Name</label>
                                <input type="text" name="name" class="form-control" id="name" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Add</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>

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
        <script>
            function confirmDelete(button) {
                Swal.fire({
                    title: 'Confirmation',
                    text: 'Are you sure you want to delete this product?',
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
                var productId = button.getAttribute("data-product-id");

                // Send an AJAX request to delete the product
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "delete-product?id=" + encodeURIComponent(productId), true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var row = button.parentNode.parentNode;
                        row.parentNode.removeChild(row);
//                        calculateCartTotal();
                        Swal.fire({
                            title: 'Success',
                            text: 'Product deleted successfully.',
                            icon: 'success'
                        });
                    }
                };
                xhr.send();
            }


            function openAddProductModal() {
                var addProductModal = document.getElementById('addProductModal');
                var modal = new bootstrap.Modal(addProductModal);
                modal.show();
            }
            function openAddCateModal() {
                var addProductModal = document.getElementById('addCateModal');
                var modal = new bootstrap.Modal(addProductModal);
                modal.show();
            }
        </script>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.all.min.js"></script>

</html>
