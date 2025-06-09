package com.cdac.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.cdac.connection.DriverConnection;
import com.cdac.constants.ShoppingAppConstants;
import com.cdac.pojo.Category;

public class CategoryDaoImpl implements CategoryDao {
	
	@Override
	public Iterator<Category> getCategories() throws SQLException, ClassNotFoundException, IOException {
		Connection conn = DriverConnection.getConnection();

		Statement categoryStatement = conn.createStatement();
		ResultSet categoryrResult = categoryStatement.executeQuery(ShoppingAppConstants.GETCATEGORIESQUERY);
		
		List<Category> listCategory = new ArrayList<>();
		Category obj = null;
		
		while(categoryrResult.next()) {
			obj = new Category();
			obj.setCategoryId(categoryrResult.getInt("categoryId"));
			obj.setCategoryName(categoryrResult.getString("categoryName"));
			obj.setCategoryDescription(categoryrResult.getString("categoryDescription"));
			obj.setCategoryImageUrl(categoryrResult.getString("categoryImageUrl"));
			listCategory.add(obj);
		}

		if (categoryrResult != null)
			categoryrResult.close();

		if (categoryStatement != null)
			categoryStatement.close();

		if (conn != null)
			conn.close();
		
		return listCategory.iterator();
	}
}