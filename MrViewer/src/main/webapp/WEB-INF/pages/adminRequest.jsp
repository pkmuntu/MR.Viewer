<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="admin_header.jsp"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=submit] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
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
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: pink;
}

.topnav a {
	float: Right;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a.active {
	background-color: #04AA6D;
	color: white;
}
</style>

	
	${confirmation}
	<h3>Request List</h3>

	<c:if test="${!empty requestmodel}">
		<h2>List UserModels</h2>
		<table align="left" border="1">
			<tr>
				<th>Request Id</th>
				<th>User ID</th>
				<th>Description</th>
				<th>Comment</th>
				<th>Actions on Row</th>
			</tr>

			<c:forEach items="${requestmodel}" var="rtmodel">
				<tr>
					<td>${rtmodel.requestId}</td>
					<td>${rtmodel.userId}</td>
					<td>${rtmodel.description12}</td>
					<td>${rtmodel.comment12}</td>
					<c:url value="/approve" var="approving"></c:url>

					<td align="center"><a
						href="${approving}?requestId=${rtmodel.requestId}">Approve</a> <!--  <a href="${deleting}?userId=${usermodel.userId}">Reject</a></td> -->
				</tr>
			</c:forEach>



		</table>
	</c:if>

	<!--
<div class="container">
  <form action="anyTimeHelp">
    <label for="userId">User Id</label>
    <input type="text" id="userId" name="userId" placeholder="Enter User Id.." required>

    <label for="email">User Email</label>
    <input type="text" id="email" name="email" placeholder="Enter User Email.." required>

    <label for="country">Description</label>
    <select id="country" name="country" required>
      <option value="australia">Forgot My password</option>
      <option value="canada">Change My Active State</option>
    </select>

    <label for="subject">Comment</label>
    <textarea id="subject" name="subject" placeholder="Write something.." style="height:200px" required></textarea>

    <input type="submit" value="Sent Request">
  </form>
</div>
  -->


</body>
</html>