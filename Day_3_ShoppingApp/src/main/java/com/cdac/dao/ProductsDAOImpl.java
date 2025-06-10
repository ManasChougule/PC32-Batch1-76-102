package com.cdac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.cdac.constants.ShoppingAppConstants;

import com.cdac.pojo.Product;

public class ProductsDAOImpl implements ProductsDAO {

    private static final String URL = "jdbc:mysql://localhost/user";
    private static final String USER = "root";
    private static final String PASSWORD = "cdac";

    public ProductsDAOImpl() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) throws Exception {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(ShoppingAppConstants.FETCHPRODUCTS)) {

            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setProductId(rs.getInt("productId"));
                    p.setProductName(rs.getString("productName"));
                    p.setProductDescription(rs.getString("productDescription"));
                    p.setProductPrice(rs.getDouble("productPrice"));
                    p.setProductImageUrl(rs.getString("ProductImageUrl"));
                    p.setCategoryId(rs.getInt("categoryId"));

                    products.add(p);
                }
            }
        }

        return products;
    }

    @Override
    public boolean addProduct(Product product) throws Exception {
        String sql = "INSERT INTO products (productName, productDescription, productPrice, productImageUrl, categoryId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductDescription());
            ps.setDouble(3, product.getProductPrice());
            ps.setString(4, product.getProductImageUrl());
            ps.setInt(5, product.getCategoryId());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
