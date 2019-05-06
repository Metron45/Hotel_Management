<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form method="post" action="LoginCheck">
		User Name: <input type="text" name="username">
		Password: <input type="password" name="password">
		<input type="submit" value="Login">
	</form>
	<form method="post" action="Redirect_Register_User">
		<input type="submit" value="Register User">
	</form>
</body>
</html>