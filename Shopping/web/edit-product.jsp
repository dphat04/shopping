

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
            <title>Edit Product</title>
        </head>
        <body>
            <div class="container">
                <h1  style="text-align: center">Edit Product</h1>
                <form action="edit-product" method="post">


                    <input type="hidden" class="form-control"name="id" value="${product.id}" required>

                <div class="mb-3">
                    <label for="name" class="form-label">Product Name</label>
                    <input type="text" class="form-control"name="name" value="${product.name}" required>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="number" step="0.01" class="form-control"name="price" value="${product.price}" required>
                </div>
                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity</label>
                    <input type="number" step="0.01" class="form-control"  name="quantity" value="${product.quantity}" required>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Image</label>
                    <input type="text" step="0.01" class="form-control"  name="image" value="${product.image}" required>
                </div>
                <div class="mb-3">
                    <label for="category" class="form-label">Category</label>
                    <select class="form-control"  name="category" required>
                        <option value=""> ${product.categoryName}</option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.categoryId}" >${category.name}</option>
                        </c:forEach>

                    </select>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" step="0.01" class="form-control"  name="description" value="${product.description}" required>
                </div>
                <div class="mb-3">
                    <label for="colors" class="form-label">Colors</label><br>
                    <c:forEach items="${color}" var="currentColor">
                        <label class="form-check-label">
                            <input type="checkbox" class="form-check-input" name="colors" value="${currentColor.id}" <c:if test="${product.colors.contains(currentColor.name)}">checked</c:if>> ${currentColor.name}
                            </label><br>
                    </c:forEach>
                </div>

                <div class="mb-3">
                    <label for="sizes" class="form-label">Sizes</label><br>
                    <c:forEach items="${size}" var="currentSize">
                        <label class="form-check-label">
                            <input type="checkbox" class="form-check-input" name="sizes" value="${currentSize.id}" <c:if test="${product.sizes.contains(currentSize.name)}">checked</c:if>> ${currentSize.name}
                            </label><br>
                    </c:forEach>
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

