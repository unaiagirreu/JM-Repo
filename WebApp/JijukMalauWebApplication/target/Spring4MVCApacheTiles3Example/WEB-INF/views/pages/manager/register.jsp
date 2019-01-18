<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<h3>Register page</h3>
<span>${message}</span>
<form:form action="${pageContext.request.contextPath}/Register/register" method="GET" modelAttribute="register" >
<table>
	<tr>
		<td><label>User name: </label></td>
		<td><form:input path="username" required="required"/></td> 		
	</tr>
	<tr>
		<td><label>Password: </label></td>
		<td><form:input path="password" type="password" required="required"/></td>
	</tr>
	<tr>
		<td><label>Email: </label></td>
		<td><form:input path="email" type="email" required="required"/></td>
	</tr>
	<tr>
		<td><label>Confirm email: </label></td>
		<td><form:input path="emailConfirm" type="email" required="required"/></td>
		<td><label>Name: </label></td>
		<td><form:input path="name" required="required"/></td>
	</tr>
	<tr>
		<td><label>Country: </label></td>
		<td><form:input path="country" required="required"/></td>
		<td><label>Surname: </label></td>
		<td><form:input path="surname" required="required"/></td>
	</tr>
	<tr>
		<td><label>City: </label></td>
		<td><form:input path="city" required="required"/></td>
		<td><label>	Postal code: </label></td>
		<td><form:input path="postalCode" required="required"/></td>
	</tr>
	<tr>
		<td><label>Street: </label></td>
		<td><form:input path="street" required="required"/></td>
		<td><label>	Portal number: </label></td>
		<td><form:input path="portalNumber" required="required"/></td>
	</tr>
	<tr>
		<td><label>Floor number: </label></td>
		<td><form:input path="floor" required="required"/></td>
		<td><label>	Letter: </label></td>
		<td><form:input path="letter" required="required"/></td>
	</tr>	
	<tr>
		<td colspan="2">
			<input type="submit" value="Register" required="required"/>
		</td>
	</tr>
</table>
</form:form>