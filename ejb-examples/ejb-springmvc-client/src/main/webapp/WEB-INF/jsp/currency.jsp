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

	<h1>Currency - Test local/remote EJB stateless bean</h1>

	<form action="currency" method="post">
		<table>
			<tr>
				<td>Currency (From Remote EJB) :</td>
				<td>
					<select id="remoteCurrency" name="remoteCurrency">
						<option value="???" <c:if test="${remoteCurrency == '???'}"> selected </c:if>>Select the currency</option>
						<c:forEach var="item" items="${remoteCurrencies}">
							<option value="<c:out value='${item}' />"
								<c:if test="${remoteCurrency == item}"> selected </c:if>>
								<c:out value="${item}" />
							</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<c:choose>
    					<c:when test="${not empty remoteRate}">
        					Rate: <c:out value="${remoteRate}"/>
    					</c:when>    
    					<c:otherwise>
        				    Rate: ---
    				    </c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Currency (From Local EJB) :</td>
				<td>
					<select id="localCurrency" name="localCurrency">
						<option value="???" <c:if test="${localCurrency == '???'}"> selected </c:if>>Select the currency</option>
						<c:forEach var="item" items="${localCurrencies}">
							<option value="<c:out value='${item}' />"
								<c:if test="${localCurrency == item}"> selected </c:if>>
								<c:out value="${item}" />
							</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<c:choose>
    					<c:when test="${not empty localRate}">
        					Rate: <c:out value="${localRate}"/>
    					</c:when>    
    					<c:otherwise>
        				    Rate: ---
    				    </c:otherwise>
					</c:choose>
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