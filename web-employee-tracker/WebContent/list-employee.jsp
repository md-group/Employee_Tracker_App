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
		<h1>SC Company SRL | The employee data </h1>
	</div>
	
	<h3>Employee Data</h3>
	
	<div id="content">
	
		<table>
			<tr>
				<th>Password</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Age Employee</th>
				<th>Email</th>
				<th>Salary</th>
				<th>Old Employee</th>
			</tr>
			
			<c:forEach var="uniqueEmployee" items="${UNIQUE_EMPLOYEE}">

				<tr>
					<td>${uniqueEmployee.pass}</td>
					<td>${uniqueEmployee.firstName}</td>
					<td>${uniqueEmployee.lastName}</td>
					<td>${uniqueEmployee.age}</td>
					<td>${uniqueEmployee.email}</td>
					<td>${uniqueEmployee.salary}</td>
					<td>${uniqueEmployee.oldEmployee}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		
		<!-- put button: Logout -->
		<input type="button" value="Logout" onclick="return logout()" class="logout" />
	</div>
</body>
</html>