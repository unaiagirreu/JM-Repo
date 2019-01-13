<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${pageContext.request.contextPath}/static/scripts/ayax.js"></script>
<h2>Home page: it will be the shop</h2>
<span id="lista"></span>
<div class="container-fluid">
<div class="row">
<div class="card col-sm-2">
        <div class="card-header text-center">
          <a>Filters</a>
        </div>
        <div class="card-body">
          <div class="form-group">
            <a> Select a category:</a><br>
              <select class="form-control" id="sel1">
                <option>Screen</option>
                <option>Laptop</option>
                <option>Desktop computer</option>
                <option>Printer</option>
                <option>Periferal</option>
            </select>
            <a>Select the price:</a><br>
            <div class="price">
              <a id="min">1€ </a><!--
          --><div class="slidercontainer">
                <input type="range" min="1" max="999" value="500" class="slider" id="myRange"><br>
                <a class="rangeValue">Value: <span id="demo">500</span>$</a>
            </div><!--
          --><a id="max"> 999€</a>
          </div>
          <div class="searchButton text-center">
            <a href="#" class="btn btn-primary">Search</a>
          </div>
        </div>
      </div>
    </div>
        <div class="card col-sm-10">
    <!---------------------------------------- Search ---------------------------------------->

      <div class="card-header text-center">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search our products">
            <div class="input-group-append">
              <button class="btn btn-secondary" type="button">
                <i class="fa fa-search"></i>
              </button>
            </div>
          </div>
        </div>
<div class="container">
<div class="row">
	<c:forEach items="${items.itemList}" var="item">
	<!-- <form:form action="${pageContext.request.contextPath}/Shop/addItem" method="POST" modelAttribute="orderEntry" >-->
		<div class="card col-12 col-sm-6 col-md-4">
              <figure class="figure">
                <h3>${item.name}</h3>
                <img src="images/hmi.jpg" class="figure-img img-fluid rounded" alt="A generic square placeholder image with rounded corners in a figure.">
                <figcaption class="figure-caption">${item.description}</figcaption>
                 <div class="row align-items-center input-group justify-content-center">
                 	<input type="number" class="input-number" value="1" min="1" max="50">
                  <form:form action="${pageContext.request.contextPath}/Shop/addItem" method="POST" modelAttribute="orderEntry" >
                   <%request.setAttribute("${orderEntry.id}","${item.id}");%>
                  <button type="submit" class="btn btn-link add" value="addItem">Buy</button>
                  </form:form>
                 </div>
              </figure>
        </div>
       <!-- </form:form>-->
	 </c:forEach>
</div></div></div></div></div>