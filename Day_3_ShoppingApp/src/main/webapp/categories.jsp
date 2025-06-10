<%@ page
	import="java.util.Iterator, com.cdac.pojo.Category,  com.cdac.dao.CategoryDao, com.cdac.dao.CategoryDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String name = request.getParameter("name");
CategoryDao obj = new CategoryDaoImpl();
Iterator<Category> listCategories = null;

try {
	listCategories = obj.getCategories();
} catch (Exception e) {
	e.printStackTrace();
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Categories Page</title>
</head>
<body>

	<h1 style="text-align: center; color: green">
		Welcome
		<%=name.toUpperCase()%>
	</h1>

	<div
		style="display: flex; justify-content: center; text-align: center; margin-top: 15%;">
		<table border="1" style="border-collapse: collapse;">
			<thead>
				<tr>
					<th>IDd</th>
					<th>Name</th>
					<th>Description</th>
					<th>Image</th>
				</tr>
			</thead>
			<tbody>
				<%
				while (listCategories.hasNext()) {
					Category category = listCategories.next();
				%>
				<tr>
					<td><%=category.getCategoryId()%></td>
					<td><a
						href="<%=request.getContextPath()%>/ProductsWeb?categoryId=<%=category.getCategoryId()%>">
							<%=category.getCategoryName()%></td>
						</a>
					</td>
					<td><%=category.getCategoryDescription()%></td>
					<td><img src="Images/<%=category.getCategoryImageUrl()%>" style="height: 50px"></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

</body>
</html>
