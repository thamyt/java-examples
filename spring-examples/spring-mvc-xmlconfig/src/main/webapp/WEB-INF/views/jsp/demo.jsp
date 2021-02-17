<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

    <title><c:out value="${title}"/></title>
</head>
<body>
    <h1><c:out value="${title}"/> (JSP)</h1>
   	
	<table id="menu">
		<thead>
			<tr>
				<th>No.</th>
				<th>Description</th>
				<th>Link</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1.</td>
				<td>To test the JSP looping feature</td>
				<td><a href="jsp/loop">Demo</a></td>
			</tr>
			<tr>
				<td>2.</td>
				<td>To test the Spring security feature</td>
				<td><a href="jsp/admin">Demo</a></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th>2.</th>
				<th>TBD</th>
				<th>Demo</th>
			</tr>
		</tfoot>
	</table>
   	
    <a href="">Home</a>
   
</body>
</html>