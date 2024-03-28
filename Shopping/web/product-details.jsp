<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
        <h1 style="text-align: center">Product Details</h1>
        <div class="row">
            <div class="col-md-6">
                <img src="${product.image}" class="card-img-top" alt="Shoe 1">
        </div>
        <div class="col-md-6">
            <h2>${product.name}</h2>
           <p> <strong>Description:</strong> ${product.description}</p>
            <p>$${product.price}</p>
            <form action="add-cart" method="POST">
                <input type="hidden" name="productId" value="${product.id}">
                <div class="mb-3">
                    <label for="size" class="form-label">Size:</label>
                    <select class="form-select" name="size" id="size" required>
                        <option value="" disabled selected>Select size</option>
                        <c:forEach items="${listSizes}" var="size">
                            <option value="${size.id}">${size.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="color" class="form-label">Color:</label>
                    <select class="form-select" name="color" id="color" required>
                        <option value="" disabled selected>Select color</option>
                        <c:forEach items="${listColors}" var="color">
                            <option value="${color.id}">${color.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${account.role != 1}">
                <button type="submit" class="btn btn-primary">Add to Cart</button>
                </c:if>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
