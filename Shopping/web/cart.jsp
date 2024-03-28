<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  
    .quantity-controls {
        display: flex;
    }
    .quantity-controls button {
        background-color: transparent;
        border: none;
        cursor: pointer;
        font-size: 1rem;
    }
    .quantity-controls button:focus {
        outline: none;
    }
    .quantity-display {
        padding: 0 0.5rem;
    }
    .delete-btn {
        color: red;
        cursor: pointer;
    }
    .error-message {
    background-color: red;
    color: white;
    padding: 10px;
    margin-bottom: 10px;
}
</style>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.all.min.js"></script>
<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
    <%
        String mess = request.getParameter("mess");
        if (mess != null && !mess.isEmpty()) {
    %>
  <div class="message error-message">
    <%= mess %>
</div>
    <%
        }
    %>
    <h1>Cart</h1>
   <form action="check-out" method="POST">
    <table class="table">
        <thead>
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th></th>
            </tr>
        </thead>

        <tbody>
            <c:if test="${empty cartMap[account.id]}">
                <tr>
                    <td colspan="5" style="color: red">You have no order</td>
                </tr>
            </c:if>
            <c:forEach items="${cartMap[account.id]}" var="product">
                <tr>
                    <td>${product.name} - Color: ${product.colorName} - Size: ${product.sizeName}</td>
                    <td>$${product.price}</td>
                    <td class="quantity-cell">
                        <div class="quantity-controls">
                            <input type="hidden" name="productId[]" value="${product.id}">
                            <input type="hidden" name="productName[]" value="${product.name}">
                            <input type="hidden" name="productPrice[]" value="${product.price}">
                            <input type="hidden" name="productQuantity[]" value="${product.quantity}">
                            <input type="hidden" name="productColor[]" value="${product.colorId}">
                            <input type="hidden" name="productSize[]" value="${product.sizeId}">
                            <span class="quantity-display">${product.quantity}</span>
                        </div>
                    </td>
                    <td class="total-cell">$${product.price * product.quantity}</td>
                    <td>
                        <button type="button" class="delete-btn" data-product-id="${product.id}" onclick="confirmDelete(this)">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="text-end">
        <h4 id="cart-total">Total: $${total}</h4>
        <input type="hidden" id="total-input" name="total" value="${total}">
        <button type="submit" class="btn btn-primary">Checkout</button>
    </div>
</form>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<script>
    window.addEventListener('DOMContentLoaded', calculateCartTotal)

    function calculateCartTotal() {
        var total = 0;
        var totalCells = document.querySelectorAll('.total-cell');
        totalCells.forEach(function (cell) {
            total += parseFloat(cell.innerText.substring(1));
        });
        document.getElementById('cart-total').innerText = 'Total: $' + total.toFixed(2);
        document.getElementById('total-input').value = total.toFixed(2);
    }

    function confirmDelete(button) {
        Swal.fire({
            title: 'Confirmation',
            text: 'Are you sure you want to delete this product from the cart?',
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

        // Send an AJAX request to remove the product from the cart
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "remove-cart", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var row = button.parentNode.parentNode;
//                setTimeout(function () {
//
                row.parentNode.removeChild(row);
                calculateCartTotal();
//
//                }, 1000);
                Swal.fire({
                    title: 'Success',
                    text: 'Product deleted successfully.',
                    icon: 'success'
                });
            }
        };
        xhr.send("productId=" + encodeURIComponent(productId));
    }
</script>
