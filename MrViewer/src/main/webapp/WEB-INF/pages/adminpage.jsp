<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@include file="admin_header.jsp"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
</head>
<body>
	<h1>Welcome Admin ${sessionScope.UserId} -
		${sessionScope.UserName}</h1>
	<br>
	<a href="getAllUser">Get All UserDetails</a>
	<br>
	<a href="getAllUsersRequestList">Get All User Request Details</a>
</body>
</html>