<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Struts MVC Example</title>
</head>
<body>
	<h1>Struts MVC Example</h1>

	<s:url></s:url>

	<h2>Options</h2>
	<ol>
	    <li><a href = "<s:url action = "formsubmit-display" />" >Form Submit Demo</a></li>
		<li><a href = "<s:url action = "calculator" />" >Calculator - Test local EJB stateless bean</a></li>
		<li><a href = "<s:url action = "currency" />" >Currency - Test local/remote EJB stateless bean</a></li>
	</ol>

</body>
</html>
