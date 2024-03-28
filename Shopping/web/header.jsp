<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            .navbar-nav {
                display: flex;
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
    <body>
        <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #fff; margin-bottom: 20px;">
            <div class="container">
                <ul class="navbar-nav">
                    <a class="navbar-brand" href="home" style="color: #000; font-size: 1.5rem; font-weight: bold;">Clothes Shop</a>
                    <c:if test="${account.role == 1}">
                        <li class="nav-item">
                            <a class="nav-link" href="product-management" style="color: #000;">Product Management</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="account-management" style="color: #000;">Account Management</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="order-management" style="color: #000;">Order Management</a>
                        </li>

                    </c:if>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <c:if test="${account != null}">
                        <li class="nav-item">
                        <a class="nav-link" style="color: #000;">Welcome: ${account.fullname} </a>
                    </li>
                    </c:if>
                    <c:if test="${account.role != 1}">

                        <li class="nav-item">
                            <a class="nav-link" href="cart.jsp" style="color: #000;">
                                <span class="position-relative">
                                   <i class="bi bi-cart"></i>
                                    <c:set var="cartSize" value="${sessionScope.cartSize}" />
                                    <span class="badge bg-danger" style="font-size: 0.8rem; position: absolute; top: -8px; right: -8px; width: 16px; height: 16px; border-radius: 50%; text-align: center; line-height: 16px; color: #fff;">${cartSize}</span>
                                </span>
                            </a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="user-profile" style="color: #000; font-size: 1.5rem;">
                            <i class="bi bi-person"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="log-out" style="color: #000; font-size: 1.5rem;">
                            <i class="bi bi-box-arrow-right"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

