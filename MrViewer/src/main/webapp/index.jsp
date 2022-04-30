<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
 <link rel="stylesheet" href="myresources/css/welcome1.css">
</head>
<body>

<style>
body {
background-color:aqua;
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

<div class="topnav">

  <a href="login1">AnyTimeHelp</a>
 <h2 class="top"> MR.Viewer</h2>
</div>

<div align="center" style="padding: 50px;" > 
  <h1 class="h11">Welcome To Mr. Viewer Page</h1>

</div>

<div align="center" style="padding: 50px,50px, 30px, 30px">

<form:form action="welcome" method="POST">
<table>
<tr>
<a href="admin"/>
</tr><br>
<h3>Login</h3>
<a href="addAccount">?New User</a>
</table>
</form:form>
  
</div>

</body>
</html>




<!-- 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome!!!!!</title>
</head>
<body>
<a href="welcome1">Welcome</a>
</body>
</html> -->