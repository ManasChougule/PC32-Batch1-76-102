package com.cdac.acts.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.cdac.acts.entities.User;

@WebServlet("/hello")  // Servlet URL Mapping
public class DemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	SessionFactory hibernateFactory= (SessionFactory) getServletContext().getAttribute("hibernateFactory");
    	Session hibernateSession = hibernateFactory.openSession();
    	hibernateSession.beginTransaction();
    	
    	// with createQuery no need to write query in entity 
//    	List<User> users = hibernateSession.createQuery("FROM User", User.class).getResultList();
//    	
//    	Query<User> query = hibernateSession.createQuery("FROM User WHERE name = :uname", User.class);
//    	query.setParameter("uname", "user1");
//    	User user = query.getSingleResult();
//    	
//    	// Fetch specific columns (name & email)
//    	Query<Object[]> columnQuery = hibernateSession.createQuery("SELECT name, email FROM User WHERE name = :uname", Object[].class);
//    	columnQuery.setParameter("uname", "user1");
//    	List<Object[]> results = columnQuery.getResultList();
//
//    	for (Object[] row : results) {
//    	    String name = (String) row[0];
//    	    String email = (String) row[1];
//    	    System.out.println("Name: " + name + ", Email: " + email);
//    	}
    	
    	
    	// createNamedQuery works with both NamedQuery in entity & NamedNativeQuery also
    	//  Fetch all users (Predefined query)
    	Query<User> queryAll = hibernateSession.createNamedQuery("getAllUsers", User.class); // Finds the Named Query "getAllUsers" inside the User entity,  Retrieves the query string (e.g., "FROM User"). 3️⃣ Executes HQL (FROM User) against the database. 4️⃣ Maps results to User.class (fetching full entity objects)
    	List<User> users = queryAll.getResultList();

    	//  Fetch single user safely using named query
    	Query<User> queryByName = hibernateSession.createNamedQuery("getUserByName", User.class);
    	queryByName.setParameter("uname", "user1");
    	List<User> resultList = queryByName.getResultList();
    	User user = resultList.isEmpty() ? null : resultList.get(0);
    	
    	//  Fetch specific columns (name & email) using named query
    	Query<Object[]> columnQuery = hibernateSession.createNamedQuery("getUserColumns", Object[].class);
    	columnQuery.setParameter("uname", "user1");
    	List<Object[]> results = columnQuery.getResultList();

    	// Process result
    	for (Object[] row : results) {
    	    String name = (String) row[0];
    	    String email = (String) row[1];
    	    System.out.println("Name: " + name + ", Email: " + email);
    	}

    	// createNativeQuery use when you need to execute raw SQL queries directly 
    	Query<Object[]> query = hibernateSession.createNativeQuery("SELECT name, email FROM users WHERE name = ?", Object[].class);
    	query.setParameter(1, "user1");
    	results = query.getResultList();
    	
       
    	
        
        
    }
}
