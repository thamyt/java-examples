<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

	<meta charset="UTF-8">
	<title>Servlet Form-Based Authentication Demo (XML configuration)</title>
</head>
<body>
	<h1>Servlet Form-Based Authentication Demo (XML configuration)</h1>
	<p/>
	<c:if test="${empty pageContext.request.userPrincipal.name}">
	<a href="secure/login">Login</a>
	</c:if>
	<c:if test="${not empty pageContext.request.userPrincipal.name}">
	Welcome <c:out value="${pageContext.request.userPrincipal.name}" /> | <a href="logout">Logout</a>
	</c:if>
	
	<h2>Options</h2>
	<ol>
		<li><a href="public">Public Page</a></li>
		<c:if test="${requestScope.isCashier || requestScope.isSupervisor}">
			<li><a href="secure/cashier">Cashier Page</a></li>
		</c:if>
		<c:if test="${requestScope.isSupervisor}">
			<li><a href="secure/supervisor">Supervisor Page</a></li>
		</c:if>
		<c:if test="${requestScope.isManager}">
			<li><a href="secure/manager">Manager Page</a></li>
		</c:if>
		<c:if test="${requestScope.isDirector}">
			<li><a href="secure/director">Director Page</a></li>
		</c:if>
	</ol>
</body>
</html>
