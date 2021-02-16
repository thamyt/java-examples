<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

    <title>Spring MVC MultiServlets Java Config Demo</title>
</head>
<body>
    <h1>Spring MVC MultiServlets Java Config Demo</h1>
   
   	<ul>
	   	<li><a href="jsp">Demo (Jsp)</a></li>
	   	<li><a href="thymeleaf">Demo (Thymeleaf)</a></li>
	   	<li><a href="freemarker">Demo (Freemarker)</a></li>
   	</ul>  
</body>
</html>