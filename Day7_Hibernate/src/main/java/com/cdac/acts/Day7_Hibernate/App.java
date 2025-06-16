package com.cdac.acts.Day7_Hibernate;

import java.util.Scanner;

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
//        	User amazonUser = context.getBean(User.class);
//        	User facebookUser = context.getBean(User.class);
//        	System.out.println("Amazon User: " + amazonUser);
//        	System.out.println("Facebook User: " + facebookUser);

        	
//        	Account amazonAccount = new AmazonAccount(); // don't  programmatically create, use bean present in spring container
//        	Account amazonAccount =  context.getBean(AmazonAccount.class);
//        	System.out.println("Amazon Account Created: " + amazonAccount.getUserAssociatedWithAccount());
//        
//        	Account facebookAccount =  context.getBean(FacebookAccount.class);
//        	System.out.println("Facebook Account Created: " + facebookAccount.getUserAssociatedWithAccount());
        	
        	
        	// create user dynamically 
        	Scanner sc = new Scanner(System.in);
            // Get details for Amazon Account
            System.out.println("Enter Amazon User Details:");
            System.out.print("Username: "); String amazonUsername = sc.nextLine();
            System.out.print("Password: "); String amazonPassword = sc.nextLine();
            System.out.print("Email: "); String amazonEmail = sc.nextLine();
            System.out.print("Name: "); String amazonName = sc.nextLine();
            System.out.print("City: "); String amazonCity = sc.nextLine();

            // Create Amazon User dynamically
            User amazonUser = context.getBean(User.class, amazonUsername, amazonPassword, amazonEmail, amazonName, amazonCity);
//            Account amazonAccount = new AmazonAccount(amazonUser);
            Account amazonAccount = context.getBean(AmazonAccount.class);
            amazonAccount.setUser(amazonUser);
            System.out.println("Amazon Account Created: " + amazonAccount.getUserAssociatedWithAccount());

            // Get details for Facebook Account
            System.out.println("Enter Facebook User Details:");
            System.out.print("Username: "); String facebookUsername = sc.nextLine();
            System.out.print("Password: "); String facebookPassword = sc.nextLine();
            System.out.print("Email: "); String facebookEmail = sc.nextLine();
            System.out.print("Name: "); String facebookName = sc.nextLine();
            System.out.print("City: "); String facebookCity = sc.nextLine();

            // Create Facebook User dynamically
            User facebookUser = context.getBean(User.class, facebookUsername, facebookPassword, facebookEmail, facebookName, facebookCity);
//            Account facebookAccount = new FacebookAccount(facebookUser); 
            Account facebookAccount = context.getBean(FacebookAccount.class);
            facebookAccount.setUser(facebookUser);
            System.out.println("Facebook Account Created: " + facebookAccount.getUserAssociatedWithAccount());
            
        }
        catch(Exception e){
			e.printStackTrace();
		}
    }
}
