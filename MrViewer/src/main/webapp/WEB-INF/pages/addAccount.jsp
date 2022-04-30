<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #ff80ff;
}

form {
	border: 3px solid #f1f1f1;
}

.topnav a {
	float: Right;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

select {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 2px solid #ccc;
	box-sizing: border-box;
}

input[type=text], input[type=password], input[type=email] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 2px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #04AA6D;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 40%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

.formbody {
	border-color: aqua;
	align-content: center;
	padding-left: 28%;
	padding-right: 28%;
	padding-bottom: 40%;
}

.top {
	box-sizing: border-box;
}

.home {
	align: right;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
	.addbody {
		background-color: purple;
	}
}
</style>

<div class="topnav">
	<a href="welcome1" class="home">Home</a>
	<h2 class="top">MR.Viewer</h2>
</div>
</head>
<body>

	${confirmation}

	<div class="formbody">
		<h2>Add Account</h2>


		<form action="save" method="post" modelAttribute="command">
			<div class="imgcontainer">
				<img src="<c:url value="/resources/images/img.png" />" alt="Avatar"
					class="avatar">
			</div>

			<div class="container">
				<label for="email"><b>Email</b></label> <input type="email"
					placeholder="Enter Email" name="email" required>
				<!-- <form:errors path="email"/> -->

				<label for="userName"><b>User Name</b></label> <input type="text"
					placeholder="Enter Username" name="userName" required>
				<!-- <form:errors path="userName"/> -->

				<label for="psw"><b>Password</b></label> <input type="password"
					placeholder="Enter Password" name="password" required>
				<!-- <form:errors path="password"/> -->

				<label for="mobileNumber"><b>Mobile Number</b></label> <input
					type="text" placeholder="Enter Mobile Number" name="mobileNumber"
					maxlength="10" pattern="\d{10}"
					title="Please enter exactly 10 digits" required>
				<!-- <form:errors path="mobileNumber"/>   maxlength="10" pattern="\d{10}" title="Please enter exactly 10 digits"-->

				<label for="userActive"><b>Active Status</b></label> <select
					name="userActive" id="userActive">
					<option value="Offline">Offline</option>
					<option value="Online">Online</option>
				</select><br> <label for="role"><b>Role</b></label> <select name="role"
					id="role">
					<option value="Admin">Admin</option>
					<option value="User">User</option>
				</select><br>
				<!--   <input type="text" placeholder="Enter Active" name="userActive" id="userActive" required> -->
				<!-- <form:errors path="userActive"/> 
   
      <label for="role"><b>Role</b></label>
    <input type="text" placeholder="Enter Role" name="role" required>
    <!-- <form:errors path="role"/> -->

				<!--
        <label for="userActive"><b>Active Status</b></label>
    <input type="text" placeholder="Enter Active" name="userActive" id="userActive">
    <form:errors path="userActive"/>
    
      <label for="role"><b>Role</b></label>
    <input type="text" placeholder="Enter Role" name="role">
    <form:errors path="role"/>
    -->

				<button type="submit">Create Account</button>
				<label> <input type="checkbox" checked="checked"
					name="remember"> Remember me
				</label>
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<!--  <button type="button" class="cancelbtn"><a href="welcome1">Cancel</a></button> -->
				<a class="cancelbtn" href="admin">?LogIn</a><br>
			</div>
		</form>
	</div>

</body>
</html>
