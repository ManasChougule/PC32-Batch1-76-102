package com.cdac.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.cdac.dao.UserDaoImpl;
import com.cdac.pojo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		PrintWriter out = response.getWriter();

		if (null == name || null == password || name.equals("") || password.equals("")) {
			out.println("<h1 style='text-align:center'>Username and password can't be null</h1>");
			return;
		}

		try {
			if (new UserDaoImpl().authenticate(new User(name, password))) {
				request.getRequestDispatcher("/categories.jsp").forward(request, response);
//				request.getRequestDispatcher("categories").forward(request, response);
				//response.sendRedirect("categories");
			}
			else
				out.println("<h1 style='color:red; text-align:center;'>Incorrect username or password</h1>");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}