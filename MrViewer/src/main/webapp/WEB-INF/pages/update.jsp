<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <%@page isELIgnored="false"%> -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management</title>
</head>
<body>
<%@include file="admin_header.jsp"%>
${confirmation}
	<h2>Update User Data</h2>
	<form:form method="POST" action="update" modelAttribute="command">
		<table>
			<tr>
				<td><form:label path="userId">User ID:</form:label></td>
				<td><form:input path="userId" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="email">User Email:</form:label></td>
				<td><form:input type="email" path="email"
						value="${UserDto.email}" /></td>
			</tr>
			<tr>
				<td><form:label path="userName">User Name:</form:label></td>
				<td><form:input path="userName" value="${UserDto.userName}" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:input type="password" path="password"
						value="${UserDto.password}" /></td>
			</tr>
			<tr>
				<td><form:label path="mobileNumber">Mobile Number:</form:label></td>
				<td><form:input path="mobileNumber" type="text" value="${UserDto.mobileNumber}" 
				placeholder="Enter Mobile Number" maxlength="10" pattern="\d{10}" title="Please enter exactly 10 digits"/></td>
				<!-- <td><form:input path="mobileNumber"
						value="${UserDto.mobileNumber}" /></td> -->
			</tr>
			<tr>
				<td><form:label path="userActive" for="userActive">User Active Status:</form:label></td>
				<td><select name="userActive" id="userActive">
					<option value="Offline">Offline</option>
					<option value="Online">Online</option>
				</select></td>
				<!-- <td><form:input path="userActive" value="${UserDto.userActive}" /></td>  -->
				
				
			</tr>
			<tr>
				<td><form:label path="role">User Role:</form:label></td>
				<td><select name="role" id="role">
					<option value="Admin">Admin</option>
					<option value="User">User</option>
				</select></td>
				<!-- <td><form:input path="role" value="${UserDto.role}" /></td> -->
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="update" /></td>
			</tr>

		</table>

	</form:form>
</body>
</html>