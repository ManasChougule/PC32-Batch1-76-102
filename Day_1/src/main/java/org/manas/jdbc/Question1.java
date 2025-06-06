package org.manas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;



public class Question1 {
//public static  Iterator<User> getUsers(PreparedStatement psFetch){
//	try(ResultSet result = psFetch.executeQuery();) {
//		ArrayList<User> users = new ArrayList<>();
//		while(result.next()) {
//			users.add(new User(result.getString("user_name"), result.getInt("user_id"), result.getString("email")));
//		}
//		return users.iterator();
//	} catch (SQLException e) {
//		e.printStackTrace();
//		return null;
//	}
//}
	
	public static  void printUsers(PreparedStatement psFetch){
		try(ResultSet result = psFetch.executeQuery();) {
	        while (result.next()) { 
	            System.out.println(result.getString("user_name") + " " +
	                               result.getInt("user_id") + " " +
	                               result.getString("email"));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createUser(PreparedStatement psInsert, Scanner sc) {
		System.out.println("Enter new username to insert");
		String name = sc.nextLine();
		System.out.println("Enter id");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter email");
		String email = sc.nextLine();
		
		try {
			psInsert.setString(1, name);
			psInsert.setString(2, email);
			psInsert.setInt(3, id);
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateUser(PreparedStatement psUpdate, Scanner sc) {
		System.out.println("Enter username to update");
		String name = sc.nextLine();
		System.out.println("Enter id");
		int id = sc.nextInt();
		sc.nextLine();
		
		try {
			psUpdate.setString(1, name);
			psUpdate.setInt(2, id);
			psUpdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try(  
				Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy_db" , "root", "cdac");  
				Scanner sc = new Scanner(System.in);
				PreparedStatement psInsert = dbConnection.prepareStatement("insert into dummy_table values (?, ?, ?)");
				PreparedStatement psUpdate = dbConnection.prepareStatement("update dummy_table set user_name = ? where user_id = ?");
				PreparedStatement psFetch = dbConnection.prepareStatement("select * from dummy_table");
				PreparedStatement psDelete = dbConnection.prepareStatement("delete from dummy_table");
			){
			
			createUser(psInsert, sc);
			
//			Iterator<User> usersIterator = getUsers(psFetch);
//			while(usersIterator.hasNext()) {
//				System.out.println(usersIterator.next());
//			}
			
			printUsers(psFetch);
			
			updateUser(psUpdate, sc);
			
			printUsers(psFetch);
			
			psDelete.executeUpdate();
			
			System.out.println("Deleted all records");
			
			printUsers(psFetch);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
