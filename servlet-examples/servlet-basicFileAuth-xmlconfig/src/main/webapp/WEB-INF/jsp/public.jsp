<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

	<meta charset="UTF-8">
	<title>Public Page</title>
</head>
<body>
	<h1>Public Page</h1>
	<br>
	<h2>Welcome <c:out value="${pageContext.request.userPrincipal.name}" /></h2>
	<br>
		
	<a href="">Home</a>
</body>
</html>
