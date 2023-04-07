package com.techelevator.dao;

import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> searchProducts(String productSku, String name){
        List<Product> filtered = new ArrayList<>();
        SqlRowSet results = null;
        String sql = null;

        if (productSku != null && name != null){
            sql = "SELECT * FROM product " +
                    "WHERE name ILIKE ? AND product_sku ILIKE ?";
            results = jdbcTemplate.queryForRowSet(sql, name, productSku);
        } else if (productSku != null){
            sql = "SELECT * FROM product " +
                    "WHERE product_sku ILIKE ?";
            productSku = "%" + productSku + "%";
            results = jdbcTemplate.queryForRowSet(sql, productSku);
        } else if (name != null){
            sql = "SELECT * FROM product " +
                    "WHERE name ILIKE ?";
            results = jdbcTemplate.queryForRowSet(sql, name);
        } else {
            sql = "SELECT * FROM product;";
            results = jdbcTemplate.queryForRowSet(sql);
        }

        while (results.next()){
            Product product = mapRowToProduct(results);
            filtered.add(product);
        }

        return filtered;
    }

    @Override
    public Product getSingleProduct(int productId) {
        Product product = null;
        String sql = "SELECT * FROM product\n" +
                "WHERE product_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        if (results.next()){
            product = mapRowToProduct(results);
        }
        return product;
    }

    private Product mapRowToProduct(SqlRowSet results){
        Product product = new Product();
        product.setProductId(results.getInt("product_id"));
        product.setProductSku(results.getString("product_sku"));
        product.setName(results.getString("name"));
        product.setDescription(results.getString("description"));
        product.setPrice(results.getBigDecimal("price"));
        product.setImage_name(results.getString("image_name"));
        return product;
    }
}
