<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Edit</h1>
	<form action="edit-contact-form" method="post">
		<label for="name">Name: </label>
		<input type="text" name="name" value=${name} readonly="readonly">
		<br> <br>
		
		<label for="phone">Phone: </label>
		<input type="text" name="phone" value=${phone}>
		<br> <br>

		<button>Edit</button>
	</form>
</body>
</html>