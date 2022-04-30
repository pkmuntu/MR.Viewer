
<%@include file="admin_header.jsp"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>

<script language="JavaScript"
	src="<c:url value="/resources/js/validation.js"/>">
	
</script>

<noscript>
	<h1 style="color: red; text-align: center">Enable Java script</h1>
</noscript>

<style>
span {
	color: #3456EE;
}
</style>
</head>
<body>
	<h2 style="color: #3456EE; text-align: center">
		<u>${conformation}</u>
	</h2>
	<h2 style="color: #3456EE; text-align: center">
		<u>ADD MOVIE</u>
	</h2>

	<form:form modelAttribute="movieForm" enctype="multipart/form-data"
		method="POST" action="upload" onsubmit="return validate(this)">
		<p style="color: red; text-align: center">
			<form:errors path="*" />
			<span id="mNameErr"></span> <span id="mCastErr"></span> <span
				id="mCategoryErr"></span> <span id="mPosterErr"></span> <span
				id="movieErr"></span>

		</p>
		<table border="1" align="center" bgcolor="#99F6B3 ">
			<tr>
				<td>Movie name ::</td>
				<td><form:input path="movieName" required="required" /></td>
			</tr>
			<tr>
				<td>Movie cast ::</td>
				<td>cast1<form:input path="movieCast" required="required" />
					cast2<form:input path="movieCast" required="required" /> cast3<form:input
						path="movieCast" /></td>
			</tr>
			<tr>
				<td>Movie category ::</td>
				<td><form:radiobutton path="category" value="ACTION"
						required="required" />ACTION <form:radiobutton path="category"
						value="THRILLER" />THRILLER <form:radiobutton path="category"
						value="DRAMA" />DRAMA <form:radiobutton path="category"
						value="CRIME" />CRIME <form:radiobutton path="category"
						value="COMEDY" />COMEDY <form:radiobutton path="category"
						value="ADVENTURE" />ADVENTURE <form:radiobutton path="category"
						value="SCI_FI" />SCI_FI</td>
			</tr>
			<tr>
				<td>Movie poster ::</td>
				<td><input type="file" name="moviePoster" required="required" />(max
					size ::200kb& PNG format)</td>
			</tr>
			<tr>
				<td>Movie ::</td>
				<td><input type="file" name="movie" required="required" />(
					max size:20MB)</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Upload"></td>
			</tr>
		</table>

	</form:form>
</body>
</html>
