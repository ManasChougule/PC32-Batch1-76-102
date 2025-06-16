package com.cdac.constants;

public class ShoppingAppConstants {
	public static final String URL = "jdbc:mysql://appdb:4000/user";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "cdac";
	public static final String JDBCDRIVER = "com.mysql.cj.jdbc.Driver";
	
	public static final String AUTHENTICATEQUERY = "select name, password from user where name = ? and password = ?";
	public static final String GETCATEGORIESQUERY = "select * from category";
	public static final String FETCHPRODUCTS = "SELECT * FROM Product WHERE categoryId = ?";
	
	private ShoppingAppConstants() {
		
	}
	
	static {
		/*
		try (FileOutputStream fos = new FileOutputStream("application.properties")) {
            String content = "url=jdbc:mysql://localhost:3306/advjava\nusername=root\npassword=root";
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

//		System.out.println(new File("app.properties").getAbsolutePath());
		
//		Properties property = new Properties();
//		property.load(new FileInputStream("application.properties"));
		
//		String url = property.getProperty("url");
//		String username = property.getProperty("username");
//		String password = property.getProperty("password");
	}
}