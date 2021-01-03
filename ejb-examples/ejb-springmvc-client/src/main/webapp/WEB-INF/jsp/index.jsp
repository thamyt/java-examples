<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EJB Web Client</title>
</head>
<body>
	<c:set var="context" value="<%=request.getContextPath()%>" />

	<h1>EJB SpringMVC Client</h1>

	<h2>Options</h2>
	<ol>
		<li><a href="${context}/calculator">Calculator - Test local EJB stateless bean</a></li>
		<li><a href="${context}/currency">Currency - Test local/remote EJB stateless bean</a></li>
	</ol>

</body>
</html>