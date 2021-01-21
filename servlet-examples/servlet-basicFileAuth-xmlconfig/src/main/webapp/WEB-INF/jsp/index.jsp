<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

	<meta charset="UTF-8">
	<title>Servlet Basic Authentication Demo (XML configuration)</title>
</head>
<body>
	<h1>Servlet Basic Authentication Demo (XML configuration)</h1>
	
	<h2>Options</h2>
	<ol>
		<c:if test="${empty pageContext.request.userPrincipal.name}">
			<li>Login (Pending)</li>
		</c:if>
		<c:if test="${not empty pageContext.request.userPrincipal.name}">
			<li>Logout (Pending)</li>
		</c:if>
		<li><a href="public">Public Page</a></li>
		<li><a href="secure/cashier">Cashier Page</a></li>
		<li><a href="secure/supervisor">Supervisor Page</a></li>
		<li><a href="secure/manager">Manager Page</a></li>
		<li><a href="secure/director">Director Page</a></li>
	</ol>
</body>
</html>
