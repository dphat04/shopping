

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
            <title>Checkout</title>
            <style>
                .container {
                    display: flex;
                    justify-content: space-between;
                }
                .shipping-info {
                    width: 55%;
                }
                .order-details {
                    width: 35%;
                }
                .product-image {
                    max-width: 50px;
                    max-height: 50px;
                    margin-right: 10px;
                }
            </style>
        </head>
        <body>
            <form action="order" method="post">
                <div class="container">
                    <div class="shipping-info">
                        <h2>Shipping Information</h2>
                        <div class="mb-3">
                            <label for="name" class="form-label">Full Name</label>
                            <input type="hidden" class="form-control" name="accountId" required value="${acc.id}">
                        <input type="text" class="form-control" id="name" required value="${acc.fullname}">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email Address</label>
                        <input type="email" class="form-control" id="email" value="${acc.email}" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" value="${acc.address}" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Place Order</button>
                </div>
                <div class="order-details">
                    <h2 style="text-align: center">Order Details</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>Size</th>
                                <th>Color</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${productIds}" var="product" varStatus="status">
                                <tr>
                                    <td>
                                        ${productNames[status.index]}
                                    </td>
                                    <td>${sizeNames[status.index]}</td>
                                    <td>${colorNames[status.index]}</td>
                                    <td>${productPrices[status.index]}</td>
                                    <td>${productQuantities[status.index]}</td>
                                    <td>${productPrices[status.index] * productQuantities[status.index]}</td>
                                    <td style="visibility: hidden">
                                        <input type="hidden" name="productId" value="${productIds[status.index]}">
                                        <input type="hidden" name="quantity" value="${productQuantities[status.index]}">
                                        <input type="hidden" name="total" value="${productPrices[status.index] * productQuantities[status.index]}">
                                        <input type="hidden" name="size" value="${productSizeIds[status.index]}">
                                        <input type="hidden" name="color" value="${productColorIds[status.index]}">
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3" class="text-end"><strong>Total</strong></td>
                                <td>${total}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>