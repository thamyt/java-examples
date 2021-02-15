<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

    <title>Spring MVC Java Config Demo</title>
</head>
<body>
    <h1>Spring MVC Java Config Demo (JSP) - Loop </h1>

    <h2>Users Information</h2>
	<table id="menu">
		<thead>
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>DOB (DD/MM/YYYY)</th>
				<th>Height(m)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user" varStatus="status" >
				<tr>
					<td><c:out value = "${status.count}"/></td>
					<td><c:out value = "${user.firstName} ${user.lastName}"/></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${user.dob}" /></td>
					<td><fmt:formatNumber type="number" pattern=".00" value="${user.height}" /></td>
				</tr>
      		</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>Total</th>
				<th><c:out value = "${users.size()}"/></th>
				<th>TBD</th>
			</tr>
		</tfoot>
	</table>
   	
    <a href="jsp">Home</a>
   
</body>
</html>