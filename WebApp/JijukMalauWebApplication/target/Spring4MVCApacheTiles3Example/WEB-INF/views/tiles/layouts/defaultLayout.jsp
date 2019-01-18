<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><tiles:getAsString name="title" /></title>
	
	<!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/css/styleMaster.css"  rel="stylesheet"></link>
    
    <!-- Font Awesome-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    
    <!-- Monoton Font-->
    <link href="https://fonts.googleapis.com/css?family=Monoton" rel="stylesheet">
		
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- Contact us map -->
	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.4.0/dist/leaflet.css"
   integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA==" crossorigin=""/>
	<script src="https://unpkg.com/leaflet@1.4.0/dist/leaflet.js" 
	integrity="sha512-QVftwZFqvtRNi0ZyCtsznlKSWOStnDORoefr1enyq5mVL4tmKB3S/EnC3rRJcxCPavG10IcrVGSmPh6Qw5lwrg==" crossorigin=""></script>
</head>
<body>
	<!--Scripts for Boostrap 4 -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    
	<header id="header">
		<tiles:insertAttribute name="header" />	
	</header>
	
	<section id="sidemenu">
		<tiles:insertAttribute name="menu" />
	</section>
	
	<section id="site-content">
		<tiles:insertAttribute name="body" />
	</section>
	
	<footer id="footer">
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>