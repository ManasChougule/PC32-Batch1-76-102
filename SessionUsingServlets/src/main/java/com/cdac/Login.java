package com.cdac;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession(false) != null) {
			response.sendRedirect("Home");
			return;
		}
		request.getRequestDispatcher("DemoLogin.html").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection dbconnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "cdac");
			PreparedStatement ps = dbconnect.prepareStatement("select username,password from user where username = ? and password = ?");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			ps.setString(1, username);
			ps.setString(2, password);
            response.setContentType("text/html; charset=UTF-8"); // Ensures UTF-8 in response
			response.setCharacterEncoding("UTF-8"); // Explicitly sets encoding

			ResultSet result = ps.executeQuery();
			if(result.next()) {
				HttpSession session =request.getSession(true);
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(10 * 60); // default time is 30 min or can add in web.xml :- session config - timeout :- 30
				response.sendRedirect("Home");
			}
			else {
				response.getWriter().println("<h1>Invalid Username or password 💔🥲</h1>");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new ServletException("Error..", e);
		}

	}

}
