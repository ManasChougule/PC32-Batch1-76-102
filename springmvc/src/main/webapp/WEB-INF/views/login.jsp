<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login page</title>
	</head>
	<body>
		<form:form action="authenticate" modelAttribute="objUser">
			User Name : <form:input path="username"></form:input>
			Password : <form:password path="password"></form:password>
			<form:button>Login</form:button>
		</form:form>
	</body>
</html>