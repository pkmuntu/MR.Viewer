<%@include file="user_header.jsp"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="tags"%>
<body text="#ffffff" link="#ff0000">
	<br>
	<div class="container">
		<c:choose>
			<c:when test="${!empty listMovies && !empty recordCount}">

				<div class="row">
					<div class="col-sm">
						<c:forEach var="movie" items="${listMovies}">
							<p hidden>${count=count+1}</p>

							<a href="getMovie?movieId=${movie.movieId}"> <img
								src="<c:url value="/resources/images/${movie.moviePosterUrl}" />"
								alt="loading..." width="250" height="180" />
							</a>
							<br> ${movie.movieName}
						
						
						
					
					</div>
					<div class="col-sm">
						</c:forEach>
					</div>
				</div>
				<br>
				<p style="text-align: center">
					<c:if test="${pageNo!=1}">
						<tags:url var="getMovies" value="getMovies?pageNo=" />
						<a href="${getMovies}${pageNo-1}"> previous </a>
					</c:if>
					<c:forEach var="i" begin="${pageNo}" end="${pageNo+3}">
						<tags:url var="getMovies" value="getMovies?pageNo=" />
             [ <a href="${getMovies}${i}"> ${i} </a> ]  &nbsp;&nbsp;&nbsp;
        </c:forEach>
					<a href="${getMovies}${pageNo+1}"> Next </a>
				</p>
			</c:when>
			<c:otherwise>
				<h3 style="color: #EF676D; text-align: center">Records not
					found</h3>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>