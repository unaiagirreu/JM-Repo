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
                            <form name="my-form" onsubmit="return validform()" action="success.php" method="">

                                  <div class="form-row col-auto justify-content-around">
                                    <div class="form-group col-12 col-md-6 srow align-items-center">
                                        <a>Name:</a>
                                        <a id="name">${user.name}</a>
                                    </div>
                                    <div class="form-group col-12 col-md-6 srow align-items-center">
                                        <a>Last name:</a>
                                        <a id="lastName">${user.surname}</a>
                                    </div>
                                </div>
                               <div class="form-group row justify-content-start">
                                    <a>E-Mail Address:</a>
                                    <a id="email">${user.mail}</a>
                                </div>
                                   <div class="form-group row justify-content-start">
                                    <a>Username:</a>
                                    <a id="email">${user.username}</a>
                                </div>
                                <a for="email_address">Direction:</a>
                                <div class="form-group row justify-content-center border-top border-info">
                                    <a>${user.address}</a>
                                </div>
                            </form> 
                            <h1><a>Orders</a></h1>
                            <table id="example" class="table table-striped table-bordered" style="width:100%">
                            	<c:forEach items="${user.orders}" var="order">
                                <thead>
                                <tr>
                                    <th>Order id: ${order.id}</th>
                                    <th>Status: ${order.status}</th>
                                    <th>Date: ${order.date}</th>
                                    <th>Price: ${order.price}</th>
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