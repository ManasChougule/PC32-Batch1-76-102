package com.cdac.acts.listeners;
 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener { 
	public static SessionFactory sessionFactory;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			sessionFactory = new Configuration().configure("firstApp.cfg.xml").buildSessionFactory();
			event.getServletContext().setAttribute("hibernateFactory", sessionFactory);
			System.out.println("Hibernate SessionFactory initialized on server start!");
		}catch(Exception e) {
			 e.printStackTrace();
		}
	}
	

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("Hibernate SessionFactory closed on server stop!");
        }
    }
}

// or instead you can write this code in DemoServlet in init method and keep loadOnStartUp=true