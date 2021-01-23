<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

	<meta charset="UTF-8">
	<title>Login Form</title>
</head>
<body>
	<h2>Hello, please log in:</h2>
	
	<form method="post" action="j_security_check">
	<table>
		<tr>
			<td>Please type your user name: </td>
			<td><input type="text" name="j_username" size="20"/></td>
		</tr>
		<tr>
			<td>Please type your password: </td>
			<td><input type="password" name="j_password" size="20"/></td>
		</tr>
	</table>
	<p></p>
	<input type="submit" value="Submit"/>
	&nbsp;
	<input type="reset" value="Reset"/>
	&nbsp;
	<a href=""><input type="button" value="Cancel" /></a>
	</form>
</body>
</html>