package com.techelevator.dao;

import com.techelevator.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> searchProducts(String sku, String name);

    Product getSingleProduct(int ProductId);
}
