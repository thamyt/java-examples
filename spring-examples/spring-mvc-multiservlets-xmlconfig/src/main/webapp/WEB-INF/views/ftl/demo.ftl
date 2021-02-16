<!-- 
	freemarker macros have to be imported into a namespace. 
	We strongly	recommend sticking to 'spring' 
-->
<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <h1>${title} (Free marker)</h1>
   		
   	<table id="menu">
		<thead>
			<tr>
				<th>No.</th>
				<th>Description</th>
				<th>Link</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1.</td>
				<td>To test the Freemarker looping feature</td>
				<td><a href="freemarker/loop">Demo</a></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th>2.</th>
				<th>TBD</th>
				<th>Demo</th>
			</tr>
		</tfoot>
	</table>
   	
    <a href="<@spring.url '/'/>">Home</a>
</body>
</html>