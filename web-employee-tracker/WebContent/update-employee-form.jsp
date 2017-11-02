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
		<h1>SC Company SRL | Update Employee</h1>
	</div>
	
	<h3>Update Employee</h3>
	
	<form action="EmployeeControllerServlet" method="get">
		<input type="hidden" name="command" value="UPDATE"/>
		<input type="hidden" name="employeeId" value="${EMPLOYEE.id}" />
		
		<table>
			<tbody>
				<tr>
					<td><label>Password:</label></td>
					<td><input type="text" name="pass" value="${EMPLOYEE.pass}"/></td>
				</tr>
				<tr>
					<td><label>First Name:</label></td>
					<td><input type="text" name="firstName" value="${EMPLOYEE.firstName}"/></td>
				</tr>
				<tr>
					<td><label>Last Name:</label></td>
					<td><input type="text" name="lastName" value="${EMPLOYEE.lastName}"/></td>
				</tr>
				<tr>
					<td><label>Age:</label></td>
					<td><input type="text" name="age" value="${EMPLOYEE.age}"/></td>
				</tr>
				<tr>
					<td><label>Email:</label></td>
					<td><input type="text" name="email" value="${EMPLOYEE.email}"/></td>
				</tr>
				<tr>
					<td><label>Salary:</label></td>
					<td><input type="text" name="salary" value="${EMPLOYEE.salary}"/></td>
				</tr>
				<tr>
					<td><label>Old Employee:</label></td>
					<td><input type="text" name="oldEmployee" value="${EMPLOYEE.oldEmployee}"/></td>
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
		<a href="EmployeeControllerServlet">Back to List</a>
	</p>
</body>
</html>