
<%@include file="admin_header.jsp"%>
<body text="#FFFFFF">
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ page isELIgnored="false"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="tags"%>

	<table cellspacing="1" cellpadding="15" border="1" height="100%"
		align="center">
		<tr>
			<td width="55%" bordercolor="#33667F" align="center"><tags:url
					var="admin" value="/admin" />
				<h2>${movie.movieName}</h2> <br> <a
				href="${admin}/watchMovie?url=${movie.movieUrl}"><img
					src="<c:url value="/resources/images/${movie.moviePosterUrl}" />"
					width="300" height="300"></a>
				<h5>Stars::${movie.movieCasts}</h5>
				<h5>Genres::${movie.category}</h5>
				<h5>
					<img src="<c:url value="/resources/images/like.jfif" />" width="80"
						height="50">::${likeCount}
				</h5>
				<h5>
					<a href="${admin}/watchMovie?url=${movie.movieUrl}">Watch Movie
					</a>
				</h5>
				<h5>
					<a href="${admin}/delete?movieId=${movie.movieId}">Delete Movie
					</a>
				</h5></td>
			<td width="45%">
				<h1 style="color: #3456EE; text-align: center">
					<u>${confirmation}</u>
				</h1>
				<h1 style="color: #3456EE; text-align: center">
					<u>Update Movie</u>
				</h1> <form:form modelAttribute="movieForm" enctype="multipart/form-data"
					method="POST" action="update">
					<p style="color: red; text-align: center">
						<form:errors path="*" />
					</p>
					<table border="1" align="center" bgcolor="#99F6B3 ">
						<tr>
							<form:hidden path="movieId" />
							<td>Movie name ::</td>
							<td><form:input path="movieName" /></td>
						</tr>
						<tr>
							<td>Movie category ::</td>
							<td><form:radiobutton path="category" value="ACTION" />ACTION
								<form:radiobutton path="category" value="THRILLER" />THRILLER <form:radiobutton
									path="category" value="DRAMA" />DRAMA <form:radiobutton
									path="category" value="CRIME" />CRIME <form:radiobutton
									path="category" value="COMEDY" />COMEDY <form:radiobutton
									path="category" value="ADVENTURE" />ADVENTURE <form:radiobutton
									path="category" value="SCI_FI" />SCI_FI</td>
						</tr>
						<tr>
							<td>Movie poster ::</td>
							<td><input type="file" name="moviePoster" />(max size
								::200kb& PNG format)</td>
						</tr>
						<tr>
							<td>Movie ::</td>
							<td><input type="file" name="movie" />( max size:20MB)</td>
						</tr>

						<tr>
							<td colspan="2"><input type="submit" value="Upload"></td>
						</tr>
					</table>

				</form:form>
			</td>
		<tr>
	</table>

</body>
</html>