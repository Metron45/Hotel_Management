<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="Register_User">
		User Name:  <input type="text" name="username" required="required">
		Name: 		<input type="text" name="name" required="required">
		Address: 	<input type="text" name="address" required="required">
		Phone: 		<input type="text" name="phone" required="required">
		Description:<input type="text" name="description">
		Password: 	<input type="password" name="password" required="required">
		Register As Hotel?: <input type="checkbox" name="account_type">
		<input type="submit" value="Register">
	</form>
</body>
</html>