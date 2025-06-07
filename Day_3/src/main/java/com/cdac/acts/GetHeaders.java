package com.cdac.acts;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class GetHeaders
 */
@WebServlet("/GetHeaders")
public class GetHeaders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> headers = request.getHeaderNames();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.println("<html><head>" + "<title>Headers</title>"
				+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css\" rel=\"stylesheet\"> "
				+ "</head><body class='container text-center'><h1 class='display-1'>Request Headers</h1>"
				+ "<table class='table table-striped'><thead><tr><th>#</th><th class='display-6' style='width: 40rem;'>Header name</th><th class='display-6'>Value</th></thead><tbody>");
		int count = 1;
		while (headers.hasMoreElements()) {
			String headerName = headers.nextElement();
			String headerValue = request.getHeader(headerName);
			out.println("<tr>");
			out.println("<td>" + count++ + "</td>");
			out.println("<td>" + headerName + "</td>");
			out.println("<td>" + headerValue + "</td>");
			out.println("</tr>");
		}
		out.println("</tbody></table></body></html>");
	}

}
