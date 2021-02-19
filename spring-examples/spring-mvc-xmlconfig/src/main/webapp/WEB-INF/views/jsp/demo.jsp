<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title><c:out value="${title}"/></title>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
        crossorigin="anonymous" />  
</head>
<body>
    <h1><c:out value="${title}"/> (JSP)</h1>
   	
	<table id="menu" class="table">
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
				<td>To test the Spring security feature. You will see this link even if you haven't login.</td>
				<td><a href="jsp/admin">Demo</a></td>
			</tr>
			<tr>
				<td>3.</td>
				<td>To test the Spring security feature. You will not see this link if you haven't login.</td>
				<td>
					<sec:authorize access="isAuthenticated()">
						<a href="jsp/admin">Demo</a>
					</sec:authorize>
				</td>
			</tr>
			<tr>
				<td>4.</td>
				<td>To test the Spring security feature. You will not see this link if you doesn't have admin role.</td>
				<td>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<a href="jsp/admin">Demo</a>
					</sec:authorize>
				</td>
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