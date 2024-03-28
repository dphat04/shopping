<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<div class="container">
    <form>
        <div class="input-group">
            <input type="text" class="form-control" name="search" placeholder="Search for products">
            <input type="hidden" name="page" value="${currentPage}">
            <button class="btn btn-primary" type="submit">Search</button>
        </div>

        <div class="card">
            <div class="card-header">
                Filter and Sort
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="category" class="form-label">Category:</label>
                        <select class="form-select" name="category" id="category">
                            <option value="">All</option>
                            <c:forEach items="${listC}" var="c">
                                <option value="${c.categoryId}" <c:if test="${param.category == c.categoryId}">selected</c:if>>${c.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="sort" class="form-label">Sort:</label>
                        <select class="form-select" name="sort" id="sort">
                            <option value="asc" <c:if test="${param.sort == 'asc'}">selected</c:if>>Lowest to Highest</option>
                            <option value="desc" <c:if test="${param.sort == 'desc'}">selected</c:if>>Highest to Lowest</option>
                            <option value="new" <c:if test="${param.sort == 'new'}">selected</c:if>>Newest Product</option>
                            <option value="sale" <c:if test="${param.sort == 'sale'}">selected</c:if>>Best Seller</option>
                            </select>
                        </div>


                        <div class="col-md-3 mb-3">
                            <label for="color" class="form-label">Color:</label>
                            <select class="form-select" name="color" id="color">
                                <option value="">All</option>
                            <c:forEach items="${listColors}" var="color">
                                <option value="${color.id}" <c:if test="${param.color == color.id}">selected</c:if>>${color.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="size" class="form-label">Size:</label>
                        <select class="form-select" name="size" id="size">
                            <option value="">All</option>
                            <c:forEach items="${listSizes}" var="size">
                                <option value="${size.id}" <c:if test="${param.size == size.id}">selected</c:if>>${size.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Apply</button>
            </div>
        </div>
    </form>

    <div class="container">
        <h1 style="text-align: center; margin-top: 30px">NEWEST PRODUCT</h1>
        <div class="row">
            
            <c:forEach items="${list3}" var="p">

                <div class="col-md-4 mt-3">
                    <div class="card">
                      <a href="product-details?productId=${p.id}">
                            <img style="max-height: 255px" src="${p.image}" class="card-img-top" alt="Product Image 1">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title">${p.name}</h5>
                            <p class="card-text">Price: $${p.price}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <h1 style="text-align: center; margin-top: 30px">ALL PRODUCT</h1>

        <div class="row">
            <c:forEach items="${listP}" var="product">
                <div class="col-md-4 mt-3">
                    <div class="card">
                        <a href="product-details?productId=${product.id}">
                            <img style="max-height: 255px" src="${product.image}" class="card-img-top" alt="Product Image">
                        </a>
                        <div class="card-body">
                            <h5 class="card-title">${product.name}</h5>
                            <p class="card-text">Price: $${product.price}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <nav class="mt-3" aria-label="Page navigation example">
            <ul class="pagination">
                <!-- Disable the "Previous" link if on the first page -->
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;color=${param.color}&amp;size=${param.size}&amp;page=${currentPage - 1}">Previous</a>
                    </li>
                </c:if>

                <!-- Display the page numbers as links -->
                <c:forEach var="pageNum" begin="1" end="${totalPages}">
                    <li class="page-item <c:if test='${pageNum == currentPage}'>active</c:if>">
                        <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;color=${param.color}&amp;size=${param.size}&amp;page=${pageNum}">${pageNum}</a>
                    </li>
                </c:forEach>

                <!-- Disable the "Next" link if on the last page -->
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;color=${param.color}&amp;size=${param.size}&amp;page=${currentPage + 1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>

<%@ include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
