<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h2>Home page: it will be the shop</h2>

<table>
	<c:forEach items="${items.itemList}" var="item">
		<tr>
			<td><c:out value="${item.name}"/></td>
			<td><c:out value="${item.description}"/></td>
			<td><c:out value="${item.category}"/></td>
			<td><input type="submit" id="${item.id}" class="add" value="Add to list"/>
		</tr>
	</c:forEach>
</table>