<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Employee Tracker App</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="header">
		<h1>SC Company SRL | The list of employees </h1>
	</div>
	
	<br>
	
	<div id="content">
	
		<!-- put button: Add Employee -->
		
		<input type="button" value="Add Employee" onclick="window.location.href='add-employee-form.jsp'; return false;" class="add-employee-button" />
	
		<table>
			<tr>
				<th>Password</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Age Employee</th>
				<th>Email</th>
				<th>Salary</th>
				<th>Old Employee</th>
				<th>Action</th>
			</tr>
			
			<c:forEach var="tempStudent" items="${STUDENT_LIST}">
			
				<tr>
					<td>${tempStudent.pass}</td>
					<td>${tempStudent.firstName}</td>
					<td>${tempStudent.lastName}</td>
					<td>${tempStudent.age}</td>
					<td>${tempStudent.email}</td>
					<td>${tempStudent.salary}</td>
					<td>${tempStudent.oldEmployee}</td>
					<td>Update | Delete</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>