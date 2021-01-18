<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Info</title>
</head>
<body>
	<h1>My Info</h1>
	
	<table>
		<tr>
			<td>User ID</td>
			<td><c:out value="${userid}" /></td>
		</tr>
		<tr>
			<td>FirstName</td>
			<td><c:out value="${fname}" /></td>
		</tr>
		<tr>
			<td>LastName</td>
			<td><c:out value="${lname}" /></td>
		</tr>
	</table>
	<br/>
	<a href="">Home Page</a>
</body>
</html>