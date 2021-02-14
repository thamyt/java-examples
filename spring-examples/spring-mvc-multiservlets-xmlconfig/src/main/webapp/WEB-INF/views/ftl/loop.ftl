<!-- 
	freemarker macros have to be imported into a namespace. 
	We strongly	recommend sticking to 'spring' 
-->
<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <title>Spring MVC XML Config Demo</title>
</head>
<body>
    <h1>Spring MVC XML Config Demo (Free marker) - Loop </h1>
   
   	<h2>Users Information</h2>
	<table id="menu">
		<thead>
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>DOB (DD/MM/YYYY)</th>
				<th>Height(m)</th>
			</tr>
		</thead>
		<tbody>
			<#list users as user>
			<tr>
				<td>${user?counter}</td>
				<td>${user.firstName + ' ' + user.lastName}</td>
				<td>${user.dob?string["dd/MM/yyyy"]}</td>
				<td>${user.height?string["0.00"]}</td>
			</tr>
			</#list>
		</tbody>
		<tfoot>
			<tr>
				<th>Total</th>
				<th>${users?size}</th>
				<th>TBD</th>
			</tr>
		</tfoot>
	</table>
   	
    <a href="<@spring.url '/freemarkerdemo'/>">Home</a>
</body>
</html>