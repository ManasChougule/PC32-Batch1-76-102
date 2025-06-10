package com.cdac;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) == null) {
			response.sendRedirect("Login");
			return;
		}
		
		String username = (String) request.getSession(false).getAttribute("username");

		response.setContentType("text/html; charset=UTF-8"); // Ensures UTF-8 in response
		response.setCharacterEncoding("UTF-8"); // Explicitly sets encoding
		response.getWriter().println("<h1>Welcome to Home 😊😊 " + username +  " <a href='Logout'>Logout</a></h1>");

	}

}
