<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

	<title>Custom Login</title>
</head>
<body>
  	<div style="max-width: 400px; margin: 0 auto;">  	
	   	<h1><img src="resources/images/logo.png" width="64" height="64" />Custom Login Form</h1>
		<form action="login" method="post" >
			<!-- Need to explicitly added the CSRF token for JSP -->
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
		
	    	<p>
	        	User Name: <input type="text" name="username" required />  
	    	</p>
	    	<p>
	        	Password: <input type="password" name="password" required />
	    	</p>
	    	<p>
	        	<input type="submit" value="Login" />
	    	</p>
		</form>
	</div>   
</body>
</html>