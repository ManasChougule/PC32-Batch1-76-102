<%@ page import="entity.User"%>
<%@ page language="java" contentType = "text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Welcome Page </title>
	</head>
	<body>
	<% User user = (User)request.getAttribute("objUser"); %>
		<h1>${msg} <%=user.getUsername() %>  </h1>
	</body>
</html>