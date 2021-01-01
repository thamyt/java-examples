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
	<h1>EJB Web Client Test</h1>

	<form action="currency" method="post">
		<table>
			<tr>
				<td>Currency (From Remote EJB) :</td>
				<td>
					<select id="currencyRemote" name="currencyRemote">
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
					<select id="currencyLocal" name="currencyLocal">
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
					<input type="submit"  value="Get Currency Rate"></input>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>