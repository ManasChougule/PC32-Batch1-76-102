package com.cdac;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection dbconnect = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbconnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","cdac");
			Statement statement = dbconnect.createStatement();
			ResultSet result = statement.executeQuery("Select username,email from user");
			
			PrintWriter out  = response.getWriter();
			
			out.println("<table style='border: 2px solid black;'>");
			out.println("<thead><tr><th>username</th><th>email</th></tr><tbody>");
			while(result.next()) {
				String username = result.getString("UserName");
				String email = result.getString("email");
				
				System.out.println("useranme:" + username);
				System.out.println("email:" + email);
				
				out.println("<tr><td>" + username + "</td>");
				out.println("<td>"+email+"</td></tr>");
			}
			out.println("</tbody></table>");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new ServletException ("error..",e);
		}
	}

	
}
