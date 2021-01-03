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

	<h1>EJB Web Client Test</h1>

	<form action="currency" method="post">
		<table>
			<tr>
				<td>Currency (From Remote EJB) :</td>
				<td>
					<select id="remoteCurrency" name="remoteCurrency">
						<c:forEach var="item" items="${remoteCurrencies}">
							<option value="<c:out value='${item}' />"
								<c:if test="${param.selectValue == item})"> selected </c:if>>
								<c:out value="${item}" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Currency (From Local EJB) :</td>
				<td>
					<select id="localCurrency" name="localCurrency">
						<c:forEach var="item" items="${localCurrencies}">
							<option value="<c:out value='${item}' />"
								<c:if test="${param.selectValue == item})"> selected </c:if>>
								<c:out value="${item}" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${context}"><input type="button" value="Home" /></a>
					<input type="submit"  value="Get Currency Rate"></input>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>