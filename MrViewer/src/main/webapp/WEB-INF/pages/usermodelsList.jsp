<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All UserList</title>
</head>
<body>

	<style>
body {
	background-color: aqua;
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #ff80ff;
}

.topnav a {
	float: Right;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}
</style>

	<%@include file="admin_header.jsp"%>

	<h1>Welcome Admin ${sessionScope.UserId} -
		${sessionScope.UserName}</h1>

	${confirmation}
	<c:if test="${!empty usermodels}">
		<h2>List UserModels</h2>
		<table align="left" border="1">
			<tr>
				<th>User ID</th>
				<th>User Name</th>
				<th>User Email</th>
				<th>Actions on Row</th>
			</tr>

			<c:forEach items="${usermodels}" var="usermodel">
				<tr>
					<td>${usermodel.userId}</td>
					<td>${usermodel.userName}</td>
					<td>${usermodel.email}</td>
					<c:url value="/edit" var="editing"></c:url>
					<c:url value="/delete" var="deleting"></c:url>

					<td align="center"><a
						href="${editing}?userId=${usermodel.userId}">Edit</a> <a
						href="${deleting}?userId=${usermodel.userId}">Delete</a></td>
				</tr>
			</c:forEach>

		</table>
	</c:if>

</body>
</html>