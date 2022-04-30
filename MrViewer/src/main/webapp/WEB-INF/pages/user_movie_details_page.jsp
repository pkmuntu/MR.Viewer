<html>
<%@include file="user_header.jsp"%>
<body text="#FFFFFF">
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ page isELIgnored="false"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="tags"%>
	<table cellspacing="1" cellpadding="5" width="90%" height="90%"
		align="center">
		<tr align="center">
			<td bordercolor="#33667F"><a
				href="watchMovie?url=${movie.movieUrl}"><img
					src="<c:url value="/resources/images/${movie.moviePosterUrl}" />"
					width="550" height="500"></a></td>
			<td align="left">
				<h2>${movie.movieName}</h2> <br>
				<h5>Stars::${movie.movieCasts}</h5>
				<h5>Genres::${movie.category}</h5>
				<h5>
					<c:choose>
						<c:when test="${likeFlag}">
							<h3>
								<a href="removeLike?movieId=${movie.movieId}"><img
									src="<c:url value="/resources/images/afterLike.jfif" />"
									width="80" height="60"> ${likeCount}</a>
							</h3>
						</c:when>
						<c:otherwise>
							<h3>
								<a href="addLike?movieId=${movie.movieId}"><img
									src="<c:url value="/resources/images/beforeLike.jfif" />"
									width="80" height="60"> ${likeCount}</a>
							</h3>
						</c:otherwise>
					</c:choose>
				</h5>
				<h5>
					<a href="watchMovie?url=${movie.movieUrl}">Watch Movie </a>
				</h5>
			</td>
		<tr>
	</table>

</body>