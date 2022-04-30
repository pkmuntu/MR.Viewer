<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	align: center;
	padding-left: 28%;
	padding-right: 28%
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	align: center;
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



	${confirmation}

	<h2>Welcome User ${sessionScope.UserName}</h2>

	<h3>Contact Form</h3>

	<div class="container">
		<form action="userRequest" method="Post">

			<label for="userId">User ID</label> <input type="hidden" id="userId"
				name="userId" value="${userId}" required> <label
				for="description12">Description</label> <select id="country"
				name="description12" required>
				<option>Change My Active Status</option>
			</select> <label for="comment12">Comment</label>
			<textarea id="subject" name="comment12"
				placeholder="Write something.." style="height: 200px" required></textarea>

			<input type="submit" value="Sent Request">
		</form>
	</div>

</body>
</html>



<!--  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

-->
