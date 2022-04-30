<html>
<%@taglib uri="http://www.springframework.org/tags" prefix="tags"%>
<tags:url var="admin" value="/admin" />
<tags:url var="userData" value="/userDataPage" />
<tags:url var="logout" value="/logout" />
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="#">MovieApp</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${admin}/home">Home </a></li>

				<li class="nav-item active"><a class="nav-link"
					href="${admin}/addMoviePage">Upload Movie</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="${userData}">getUserData </a></li>

				<li class="nav-item active"><a class="nav-link "
					href="${logout}">Logout</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="${admin}/searchMovie">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search" name="name">
				<button style="color: #031525"
					class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
</body>
</html>
