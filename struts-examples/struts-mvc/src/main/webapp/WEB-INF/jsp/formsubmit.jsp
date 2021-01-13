<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
<head>
<sx:head />
<meta charset="UTF-8">
<title>Form Submit Demo</title>
</head>
<body>
	<h1>Form Submit Demo</h1>
	<s:form action="formsubmit-submit" method="post" enctype="multipart/form-data">
		<s:fielderror></s:fielderror>
    	<s:hidden name="secret" value="abracadabra"/>
        <s:textfield key="person.firstName" />
        <s:textfield key="person.lastName" />
        <sx:datetimepicker key="person.dob" label="Format (dd/MMM/yyyy)" displayFormat="dd/MMM/yyyy" />
        <s:radio key="person.gender" list="{'male','female'}" />
        <s:checkboxlist key="person.hobbies" list="{'sports','tv','shopping'}" />
        <s:textfield key="person.mobileNo" />
        <s:textfield key="person.homeNo" />
        <s:password key="person.password" /> 
        <s:select headerKey="-1"
        		  headerValue="--- Please Select ---"
        	 	  key="person.occupation" 
        	 	  list="occupationList" />
        <s:file key="person.photo" name="myFile" accept="image/*" />
        <s:textarea key="person.remarks" />
        <s:token />
        <s:submit key="submit" />
	</s:form>	
</body>
</html>
