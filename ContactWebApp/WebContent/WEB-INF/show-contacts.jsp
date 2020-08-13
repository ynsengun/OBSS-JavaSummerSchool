<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.company.Contact"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Show Contacts</h1>

	<%
	ArrayList<Contact> contacts = (ArrayList<Contact>) request.getAttribute("contacts");
	for (Contact contact : contacts) {
	%>
		<form action="show-contact-form" method="post">
			<label for="name">Name: </label>
			<input type="text" name="name" readonly="readonly" value="<%=contact.getName()%>">
			
			<label for="phone">Phone: </label>
			<input type="text" name="phone" readonly="readonly" value="<%=contact.getPhone()%>">
	
			<button>Edit</button>
			<br> <br>
		</form>
	<%
	}
	%>
</body>
</html>