<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Custom Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
        crossorigin="anonymous" />
</head>
<body>
<div class="container-fluid text-center">
    <div>
    	<h1><img class="img-fluid" 
    			 src="<@spring.url '/resources/images/logo.png'/>" 
    			 width="64" 
    			 height="64" />Custom Login Form</h1>
    </div>
    <form action="<@spring.url '/login'/>" method="post" style="max-width: 350px; margin: 0 auto;">
       	<!-- Need to explicitly added the CSRF token for Freemarker -->
       	<#if _csrf??>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</#if>	
     	
     	<#if RequestParameters.error?? &&
     	     Session.SPRING_SECURITY_LAST_EXCEPTION?? && 
     	     Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
    		<p class="text-danger">${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
     	</#if>
        
        <#if RequestParameters.logout??>
            <p class="text-warning">You have been logged out.</p>
        </#if>
         
        <div class="border border-secondary p-3 rounded">
            <p>
                <input type="text" name="username" class="form-control" placeholder="Username" required autofocus/>
            </p>
            <p>
                <input type="password" name="password" class="form-control" placeholder="Password" required />
            </p>
            <p>
                <input type="checkbox" name="remember-me" />&nbsp;Remember Me
            </p>
            <p>
                <input type="submit" value="Login" class="btn btn-primary" />
            </p>
        </div>
    </form>      
</div>
</body>
</html>