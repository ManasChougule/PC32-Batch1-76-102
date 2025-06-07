package com.cdac.acts;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/GetBrowser")
public class GetBrowser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userAgent = request.getHeader("user-agent");
		PrintWriter out = response.getWriter();
//		if (userAgent.indexOf("Firefox") != -1) {
//			out.append("<h1>You Are using Mozilla FireFox</h1>");
//			return;
//		}
//		
//		if (userAgent.indexOf("Edg/") != -1) {
//			out.append("<h1>You Are using Mozilla Edge</h1>");
//			return;
//		}
//		
//		out.append("<h1>You Are using Non Standard Browser</h1>");	
	

		if (userAgent.toLowerCase().contains("firefox")) {
		    out.append("<h1>You Are using Mozilla Firefox</h1>");
		    return;
		}

		if (userAgent.toLowerCase().contains("chrome") && userAgent.toLowerCase().contains("safari") && !userAgent.toLowerCase().contains("edg/")) {
		    out.append("<h1>You Are using Google Chrome</h1>");
		    return;
		}
		
		if (userAgent.toLowerCase().contains("edg/")) { // Ensure Edge detection works
		    out.append("<h1>You Are using Microsoft Edge</h1>");
		    return;
		}


		out.append("<h1>You Are using a Non-Standard Browser</h1>");


	}
}
