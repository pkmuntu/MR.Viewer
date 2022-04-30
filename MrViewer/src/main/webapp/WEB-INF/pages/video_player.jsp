<html>
<head>
</head>
<body>
	<%@taglib uri="http://www.springframework.org/tags" prefix="tags"%>
	<tags:url var="loadVideoFile" value="/loadVideoFile" />
	<%@ page isELIgnored="false"%>
	<video width="1330" height="600" controls>
		<source src="${loadVideoFile}/${url}" type="video/mp4">
	</video>
</body>
</html>