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
        Configuration hibernateConfiguration = null;
        SessionFactory hibernateFactory = null;
        Session hibernateSession = null;
        try {
        	hibernateConfiguration = new Configuration();
        	hibernateConfiguration.configure("firstApp.cfg.xml");
        	hibernateFactory = hibernateConfiguration.buildSessionFactory();
        	hibernateSession = hibernateFactory.openSession();
        	
        	try(Scanner scanner = new Scanner(System.in)) {
        		
//        		User user = new User();
//        		System.out.println("Enter username");
//        		String username = scanner.next();
//
//        		System.out.println("Enter password");
//        		String password = scanner.next();
//
//        		System.out.println("Enter email");
//        		String email = scanner.next();
        		hibernateSession.beginTransaction();
//        		hibernateSession.persist(user);
//        		hibernateSession.getTransaction().commit();
        		
        		
        		User user2 = new User();
        		hibernateSession.load(user2, "user2"); // populates object in place,  loads user2 as a proxy object, 
        		System.out.println("user2: " + user2);
        		
//	    		user2 = hibernateSession.get(User.class, "user2"); // fetches the actual object or returns null if not found
//	    		System.out.println("user2: " + user2);
        		hibernateSession.close(); // now user2 is a detached entity


	    		 user2.setEmail("user3@gmail.com");
	    		 Session hibernateSession2 = hibernateFactory.openSession(); // creates new session
	    		 hibernateSession2.beginTransaction();
	    		 User user3 = hibernateSession2.merge(user2);  // can merge detached entity also (to save user3 need to use persist)
	    		 System.out.println("user3: " + user3+"  user2: "+user2);

	    		hibernateSession2.getTransaction().commit();
        		hibernateSession2.close();
//        		System.out.println("User registered");
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        finally {
//        	if(hibernateSession != null) {
//        		hibernateSession.close();
//        	}
//        	
        	if(hibernateFactory != null) {
        		hibernateFactory.close();
        	}
        }
    }
}





