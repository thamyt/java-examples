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
	
	Hi <s:property value = "person.firstName"/> <s:property value = "person.lastName"/>
	<br>
	Below is your particulars you have submitted.
	
	<table>
		<tr>
			<td>First Name: </td>
			<td><s:property value = "person.firstName"/></td>
		</tr>
		<tr>
			<td>Last Name: </td>
			<td><s:property value = "person.lastName"/></td>
		</tr>
		<tr>
			<td>Date of Birth: </td>
			<td><s:date name = "person.dob" format = "dd/MM/yyyy" /></td>
		</tr>
		<tr>
			<td>Gender: </td>
			<td><s:property value = "person.gender"/></td>
		</tr>
		<tr>
			<td>Hobbies: </td>
			<td><s:property value = "person.hobbies"/></td>
		</tr>
		<tr>
			<td>Mobile No.: </td>
			<td><s:property value = "person.mobileNo"/></td>
		</tr>
		<tr>
			<td>Home No.: </td>
			<td><s:property value = "person.homeNo"/></td>
		</tr>
		<tr>
			<td>Password: </td>
			<td><s:property value = "person.password"/></td>
		</tr>
		<tr>
			<td>Occupation: </td>
			<td><s:property value = "person.occupation"/></td>
		</tr>
		<tr>
			<td>Photo: </td>
			<td><s:property value = "person.photo"/></td>
		</tr>
		<tr>
			<td>Remarks: </td>
			<td><s:property value = "person.remarks"/></td>
		</tr>
	</table>
	
	<a href = "<s:url action = "index" />" >Home</a>
</body>
</html>
