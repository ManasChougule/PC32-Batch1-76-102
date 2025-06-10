package com.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/auth")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append(
				"<html><thead><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css\" rel=\"stylesheet\" />")
				.append("<title>Login</title></thead>")
				.append("<body class='container d-flex justify-content-center align-items-center flex-column mt-5'>"
						+ "<h1>Login Form</h1>"
						+ "<form action='./auth' method='POST' class='mb-3' style='width: 500px'>"
						+ "<div class=\"mb-3\">\r\n" + "<labelclass=\"form-label\">Username</label>\r\n"
						+ "<input type=\"text\" class=\"form-control\" name='username'>\r\n" + "</div>\r\n"
						+ "<label for=\"inputPassword5\" class=\"form-label\">Password</label>\r\n"
						+ "<input type=\"password\" id=\"inputPassword5\" class=\"form-control\" aria-describedby=\"passwordHelpBlock\" name='password'>\r\n"
						+ "<div id=\"passwordHelpBlock\" class=\"form-text mb-3\">\r\n"
						+ "  Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.\r\n"
						+ "</div>" + "<button type='submit' class='btn btn-primary w-100'>Submit</button></form>")
				.append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    System.out.println("From Post!!");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy_db", "root", "cdac");
	             PreparedStatement pstm = conn.prepareStatement("SELECT * FROM cdac WHERE username = ? AND password = ?")) {

	            pstm.setString(1, username);
	            pstm.setString(2, password);
	            ResultSet res = pstm.executeQuery();

	            if (res.next()) {
//	                response.sendRedirect("catalog");
	            	response.getWriter().append("The End");
	                return;
	            }

	            response.getWriter().append("<h1 style='color: red;'>Invalid Username and Password</h1>");

	        } catch (Exception e) {
	            response.setStatus(500);
	            response.getWriter().append("<h2>Server Error: </h2>" + e.getMessage());
	        }
	    } catch (ClassNotFoundException e) {
	        response.setStatus(500);
	        response.getWriter().append("<h2>Database Driver Not Found!</h2>");
	    }
	}


}
