<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-employee-style.css">

</head>
<body>
	
	<div id="header">
		<h1>SC Company SRL | Add Employee</h1>
	</div>
	
	<h3>Add Employee</h3>
	
	<form action="AdminControllerServlet" method="get">
		<input type="hidden" name="command" value="ADD"/>
		
		<table>
			<tbody>
				<tr>
					<td><label>Password:</label></td>
					<td><input type="text" name="pass"/></td>
				</tr>
				<tr>
					<td><label>First Name:</label></td>
					<td><input type="text" name="firstName"/></td>
				</tr>
				<tr>
					<td><label>Last Name:</label></td>
					<td><input type="text" name="lastName"/></td>
				</tr>
				<tr>
					<td><label>Age:</label></td>
					<td><input type="text" name="age"/></td>
				</tr>
				<tr>
					<td><label>Email:</label></td>
					<td><input type="text" name="email"/></td>
				</tr>
				<tr>
					<td><label>Salary:</label></td>
					<td><input type="text" name="salary"/></td>
				</tr>
				<tr>
					<td><label>Old Employee:</label></td>
					<td><input type="text" name="oldEmployee"/></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>
			</tbody>
		</table>
	</form>
	<div style="clear: both;"></div>
	<p>
		<a href="AdminControllerServlet">Back to List</a>
	</p>
</body>
</html>