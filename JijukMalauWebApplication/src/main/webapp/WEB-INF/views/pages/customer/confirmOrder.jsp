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

                                <div class="form-group row justify-content-between">
                                    <div class="form-group">
                                        <a>Order id:</a>
                                        <a id="orderId">${order.id}</a>
                                    </div>
                                    <div class="form-group">
                                        <a  id="date">Date:</a>
                                        <a>${order.date}</a>
                                    </div>
                                </div>
                            </form> 
                            <table id="example" class="table table-striped table-bordered" style="width:100%">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Category</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Total Price</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${items.itemList}" var="item">
                                    <tr>
                                        <td>${item.name}</td>
                                        <td>${item.category}</td>
                                        <td>${item.quantity}</td>
                                        <td>${item.price}</td>
                                        <td>${item.totalPrice}</td>
                                    </tr>
                                 </c:forEach>
                                </tbody>
                            </table>
                            <script src="js/order.js"></script>
                        </div>
                    </div>
                    <form:form action="${pageContext.request.contextPath}/Shop/confirm" method="GET">
                    <button type="submit" class="btn  btn-lg float-right " id="btnLogin" value="confirm"><a>Confirm order</a></button>
                    </form:form>
            </div>
        </div>
    </div>
</main>
</body>
</html>