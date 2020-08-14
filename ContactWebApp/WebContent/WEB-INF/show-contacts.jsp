<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Show Contacts</h1>

	<c:forEach items="${contacts}" var="contact" varStatus ="status"> 
		<form action="show-contact-form" method="post">
			<label for="name">Name: </label>
			<input type="text" name="name" readonly="readonly" value=${contact.name}>
			
			<label for="phone">Phone: </label>
			<input type="text" name="phone" readonly="readonly" value=${contact.phone}>
	
			<button>Edit</button>
			<br> <br>
		</form>
	</c:forEach>
</body>
</html>