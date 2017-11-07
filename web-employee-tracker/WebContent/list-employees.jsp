<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Employee Tracker App</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<script>
	function logout() {
		var result = confirm("Are you sure you want to logout?");
		if(result == true) {
			window.location.href='login.jsp'
		}
	}
</script>
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
			
			<c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">
			
				<!-- set up a link for each row -->
				<c:url var="tempLink" value="AdminControllerServlet">
					<c:param name="command" value="LOAD" />>
					<c:param name="employeeId" value="${tempEmployee.id}" />
				</c:url>
				
				<!-- set up a link to delete a employee -->
				<c:url var="deleteLink" value="AdminControllerServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="employeeId" value="${tempEmployee.id}" />
				</c:url>
								
				<tr>
					<td>${tempEmployee.pass}</td>
					<td>${tempEmployee.firstName}</td>
					<td>${tempEmployee.lastName}</td>
					<td>${tempEmployee.age}</td>
					<td>${tempEmployee.email}</td>
					<td>${tempEmployee.salary}</td>
					<td>${tempEmployee.oldEmployee}</td>
					<td><a href="${tempLink}">Update</a> | 
						<a href="${deleteLink}"
						onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false"
						>Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		
		<!-- put button: Logout -->
		<input type="button" value="Logout" onclick="return logout()" class="logout" />
		
	</div>
</body>
</html>