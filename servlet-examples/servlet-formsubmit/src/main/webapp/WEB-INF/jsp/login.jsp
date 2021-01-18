<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Submit Form Demo - Login</title>
</head>
<body>

	<h1>Servlet Submit Form Demo - Login</h1>
	
	<c:if test="${not empty VALIDATION_ERRORS}" >
		<ul>
		<c:forEach var = "error" items="${VALIDATION_ERRORS}">
			<li><c:out value = "${error}"/></li>
      	</c:forEach>
      	</ul>
	</c:if>
	<form action="LoginServlet" method="post">
		<table>
			<tr>
				<td>User ID:</td>
				<td><input type="text" name="userid" value="${userid}"></input></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value="${password}"></input></td>
			</tr>
		</table>	
		<input type="submit"  value="Login"></input>
		<a href=""><input type="button"  value="Cancel" /></a>
	</form>
</body>
</html>