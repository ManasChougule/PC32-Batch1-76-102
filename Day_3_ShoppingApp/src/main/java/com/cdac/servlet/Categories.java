package com.cdac.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import com.cdac.dao.CategoryDao;
import com.cdac.dao.CategoryDaoImpl;
import com.cdac.pojo.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/categories")
public class Categories extends HttpServlet {
	private static final long serialVersionUID = 4025262752016377480L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		CategoryDao obj = new CategoryDaoImpl();
		Iterator<Category> listCategories = null;

		try {
			listCategories = obj.getCategories();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

		out.println("<h1 style='text-align:center; color:green'>Welcome " + request.getParameter("name").toUpperCase() + "</h1>");
		
		out.println("<div style=\"display: flex; justify-content: center; text-align: center; margin-top: 15%;\">\r\n"
				+ "        <table border=\"1\" style='border-collapse:collapse;'>\r\n"
				+ "            <thead>\r\n"
				+ "                <tr>\r\n"
				+ "                    <td>ID</td>\r\n"
				+ "                    <td>Name</td>\r\n"
				+ "                    <td>Description</td>\r\n"
				+ "                    <td>Image</td>\r\n"
				+ "                </tr>\r\n"
				+ "            </thead>\r\n"
				+ "            <tbody>\r\n"
				);
		
		listCategories.forEachRemaining(category -> {
			out.println("<tr>");
			out.println("<td>" + category.getCategoryId() + "</td>");
			out.println("<td>" + category.getCategoryName() + "</td>");
			out.println("<td>" + category.getCategoryDescription() + "</td>");
			out.println("<td><a href='#'><img src='Images/" + category.getCategoryImageUrl() + "' style='height:50px'/></a></td>");
			out.println("</tr>");
		});
		
		out.println("</tbody>\r\n"
				+ "</table>\r\n"
				+ "</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}