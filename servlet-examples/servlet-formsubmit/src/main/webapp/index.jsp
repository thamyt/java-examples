<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Servlet Submit Form Demo</title>
</head>
<body>

	<h1>Servlet Submit Form Demo</h1>

	<form action="LoginServlet" method="post">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstname"></input></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastname"></input></td>
			</tr>
		</table>	
		<input type="submit"  value="Login"></input>
	</form>
</body>
</html>