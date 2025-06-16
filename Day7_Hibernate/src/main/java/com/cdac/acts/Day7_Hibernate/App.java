package com.cdac.acts.Day7_Hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.cdac.acts.Day7_Hibernate.Account.Account;
import com.cdac.acts.Day7_Hibernate.Account.AmazonAccount;
import com.cdac.acts.Day7_Hibernate.Account.FacebookAccount;
import com.cdac.acts.Day7_Hibernate.configurations.BeanConfigurations;
import com.cdac.acts.Day7_Hibernate.entities.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try(AbstractApplicationContext context = new AnnotationConfigApplicationContext(BeanConfigurations.class)) {
        	User amazonUser = context.getBean(User.class);
        	User facebookUser = context.getBean(User.class);
        	System.out.println("Amazon User: " + amazonUser);
        	System.out.println("Facebook User: " + facebookUser);
        	
        	Account amazonAccount = new AmazonAccount();
        	System.out.println("Amazon Account Created: " + amazonAccount.getUserAssociatedWithAccount());
        	
        	Account facebookAccount = new FacebookAccount();
        	System.out.println("Facebook Account Created: " + facebookAccount.getUserAssociatedWithAccount());
        }
        catch(Exception e){
			e.printStackTrace();
		}
    }
}
