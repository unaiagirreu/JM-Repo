<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<h3>Log in</h3>
<span>${message}</span>
<form:form action="${pageContext.request.contextPath}/Login/Login" method="POST" modelAttribute="login" >
<table>
	<tr>
		<td><label>User name:</label></td>
		<td><form:input path="username" required="required"/></td> 		
	</tr>
	<tr>
		<td><label>Password:</label></td>
		<td><form:input path="password" type="password" required="required"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="Log in"/>
		</td>
	</tr>
</table>
</form:form>
