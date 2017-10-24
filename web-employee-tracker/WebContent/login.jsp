<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<script>
	function validate() {
		var username = document.form.username.value;
		var password = document.form.password.value;
		
		if(username==null || username=="") {
			alert("Username cannot be blank");
			return false;
		}else if(password==null || password=="") {
			alert("Pasword cannot be blank");
			return false;
		}
	}
</script>
</head>
<body>
	<div style="text-align:center; background-color:#0066ff; color:white; height:45px">
		<h1>SC Company SRL | Login Page</h1>
	</div>
	
	<br>
	
	<div style="text-align:center; position:relative; top:200px">
		<form name="form" action="LoginServlet" method="post" onsubmit="return validate()">
		<span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage") %></span>
		<table align="center">
			<tr>
				<td align="left">User: </td>
				<td><input type="text" name="username" style="width:150px"/></td>
			</tr>
			<tr>
				<td>Password: </td>
				<td><input type="password" name="password" style="width:150px"/></td>
			</tr>
		</table>
			<br><input type="submit" value="Login"/>
		</form>
	</div>

</body>
</html>