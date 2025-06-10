package com.cdac.dao;

import java.util.List;

import com.cdac.pojo.Product;


public interface ProductsDAO {
    List<Product> getProductsByCategory(int categoryId) throws Exception;

    boolean addProduct(Product product) throws Exception;
}
