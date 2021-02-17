<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

    <title><c:out value="${title}"/></title>
</head>
<body>
    <h1><c:out value="${title}"/> (JSP) - Admin Page</h1>
    
    <sec:authorize access="isAuthenticated()">
    <h2>Hi <sec:authentication property="name"/></h2>
	</sec:authorize>
   	
   	<h3>This page to the spring security feature.</h3>

    <a href="">Home</a>
   
</body>
</html>