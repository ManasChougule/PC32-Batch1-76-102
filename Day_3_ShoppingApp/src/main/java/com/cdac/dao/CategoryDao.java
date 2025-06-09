package com.cdac.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import com.cdac.pojo.Category;

public interface CategoryDao {
	public Iterator<Category> getCategories() throws SQLException, ClassNotFoundException, IOException;
}