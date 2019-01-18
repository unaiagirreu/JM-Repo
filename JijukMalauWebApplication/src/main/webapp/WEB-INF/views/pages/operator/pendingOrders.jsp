<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
<main class="my-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-8 align-items-center">
                    <div class="card">
                        <div class="card-header text-info"><a>Order</a></div>
                        <div class="card-body"> 
                            <h1><a>Orders</a></h1>
                            <table id="example" class="table table-striped table-bordered" style="width:100%">
                            	<c:forEach items="${orders}" var="order">
                                <thead>
                                <tr>
                                    <th>Order id: ${order.id}</th>
                                    <th>Status: ${order.status}</th>
                                    <th>Date: ${order.date}</th>
                                    <th>Price: ${order.price}<a>&euro;</a></th>
                                </tr>
                                </thead>
                                <c:forEach items="${order.productList}" var="product">
                                <tbody>
                                    <tr>
                                        <td>${product.name}</td>
                                        <td>${product.status}</td>
                                        <td>${product.description}</td>
                                        <td>${product.price}<a>&euro;</a></td>
                                    </tr>
                                </tbody>
                            </c:forEach>
                            </c:forEach>
                            </table>
                            <script src="js/order.js"></script>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>