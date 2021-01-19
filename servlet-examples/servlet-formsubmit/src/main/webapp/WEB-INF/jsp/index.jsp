<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

	<meta charset="UTF-8">
	<title>Servlet Submit Form Demo</title>
</head>
<body>
	<h1>Servlet Submit Form Demo</h1>
	
	<h2>Options</h2>
	<ol>
		<c:if test="${empty userid}">
			<li><a href="LoginServlet">Login</a></li>
		</c:if>
		<c:if test="${not empty userid}">
			<li><a href="secure/LogoutServlet">Logout</a></li>
			<li><a href="secure/InfoServlet">MyInfo</a></li>
		</c:if>
	</ol>
</body>
</html>