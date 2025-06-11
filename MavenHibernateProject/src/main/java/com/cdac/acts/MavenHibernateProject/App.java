package com.cdac.acts.MavenHibernateProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cdac.acts.entities.User;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Configuration hibernateConfiguration = null;
        SessionFactory hibernateFactory = null;
        Session hibernateSession = null;
        try {
        	hibernateConfiguration = new Configuration();
        	hibernateConfiguration.configure("firstApp.cfg.xml");
        	hibernateFactory = hibernateConfiguration.buildSessionFactory();
        	hibernateSession = hibernateFactory.openSession();
        	
        	try(Scanner scanner = new Scanner(System.in)) {
        		System.out.println("Enter username");
        		String username = scanner.next();
        		
        		System.out.println("Enter password");
        		String password = scanner.next();
        		
        		System.out.println("Enter email");
        		String email = scanner.next();
        		
        		User user = new User(username, password, email);
        		
        		hibernateSession.beginTransaction();
        		hibernateSession.persist(user);
        		hibernateSession.getTransaction().commit();
        		
        		System.out.println("User registered");
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        finally {
        	if(hibernateSession != null) {
        		hibernateSession.close();
        	}
        	
        	if(hibernateFactory != null) {
        		hibernateFactory.close();
        	}
        }
    }
}
