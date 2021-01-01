<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calculator</title>
</head>
<body>
	<h1>Calculator - Test local EJB stateless bean</h1>
	
	<form action="CalculatorServlet" method="post">
		<table>
			<tr>
				<td><input id="value1" name="value1" /></td>
				<td>
					<select id="operand" name="operand">
						<option value="Select the operand" selected>Select the operand</option>
						<option value="+">+</option>
						<option value="-">-</option>
						<option value="*">*</option>
						<option value="/">/</option>
					</select>
				</td>
				<td><input id="value2" name="value2" /></td>
			</tr>
			<c:if test="${not empty result}" >
				<tr>
					<td>Result is <c:out value="${result}" /></td>
				</tr>
			</c:if>
			<c:if test="${not empty error}" >
				<tr>
					<td>Error: <c:out value="${error}" /></td>
				</tr>
			</c:if>
			<tr>
				<td>
					<a href="./"><input type="button" value="Home" /></a>
					<input type="submit"  value="Compute" />
				</td>
			</tr>
		</table>
	</form>	

</body>
</html>