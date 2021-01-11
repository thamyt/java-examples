<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Submit Demo</title>
</head>
<body>
	<h1>Form Submit Demo</h1>
	<s:form action = "formsubmit!submit" method = "post" enctype = "multipart/form-data">
    	<s:hidden name = "secret" value = "abracadabra"/>
        <s:textfield key = "person.firstname" name = "firstname" />
        <s:textfield key = "person.lastname" name = "lastname" />
        <!--  
        <s:datetextfield format="dd/MM/yyyy" key = "person.dob" name = "dob" />
        <s:radio key="person.gender" label = "Gender" name = "gender" list="{'male','female'}" />
        <s:checkboxlist key="person.hobbies" label = "Hobbies" name = "hobbies" list = "{'sports','tv','shopping'}" /> 
        <s:textfield key = "person.mobile_no" name = "mobile_no" />
        <s:textfield key = "person.home_no" name = "home_no" />
        <s:password key = "person.password" name = "password" />
        <s:select key="person.occupation" name="occupation" label="Occupation" list="{'Salesman','Engineer','Manager'}" headerValue="Please select your occupation"/>
        <s:label for = "photo" value = "Photo"/>
        <s:file key="person.photo" name = "photo" accept = "text/html,text/plain" />
        <s:textarea key = "email.remark" name = "remark" />
        -->
        <s:token />
        <s:submit key = "submit" />
	</s:form>	
</body>
</html>
