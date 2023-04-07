package com.techelevator.dao;

import com.techelevator.model.CartItemInsert;
import com.techelevator.model.CartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCartDao implements CartItemDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CartItem> getCartContents(int userId) {
        List<CartItem> cart = new ArrayList<>();
        String sql = "SELECT ci.cart_item_id, ci.user_id, ci.product_id, ci.quantity, p.name, p.price \n" +
                "FROM cart_item AS ci\n" +
                "LEFT JOIN product AS p ON ci.product_id = p.product_id\n" +
                "WHERE user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            CartItem cartItem = mapRowToItem(results);
            cart.add(cartItem);
        }

        return cart;
    }

    @Override
    public String getStateCode(int userId) {
        String stateCode =  null;


        String sql = "SELECT state_code FROM users\n" +
                "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            stateCode = results.getString("state_code");
        }

        return stateCode;
    }

    @Override
    public boolean addToCart(CartItemInsert cartItem, int userId) {
        String updateSql = "UPDATE cart_item SET quantity = ?\n" +
                "WHERE user_id = ? AND product_id = ?;";
        String insertSql = "INSERT INTO cart_item(user_id, product_id, quantity)\n" +
                "VALUES((SELECT user_id FROM users WHERE user_id = ?),\n" +
                "\t  (SELECT product_id FROM product WHERE product_id = ?),\n" +
                "\t ?)";
        int quantity = 0;
        int rowsUpdated = 0;
        boolean succesful = false;
        List<CartItem> cart = getCartContents(userId);

       for (CartItem item : cart){
           if (item.getProductId() == cartItem.getProductId()){
               quantity = item.getQuantity();
           }
       }

       if (quantity > 0){
           rowsUpdated =  jdbcTemplate.update(updateSql, quantity += cartItem.getQuantity(), userId,
                   cartItem.getProductId());
       } else {
           rowsUpdated = jdbcTemplate.update(insertSql, userId, cartItem.getProductId(), cartItem.getQuantity());
       }

       if (rowsUpdated > 0){
           succesful = true;
       }

       return succesful;
    }

    @Override
    public boolean removeItem(int productId, int userId) {
        int rowsUpdated = 0;
        boolean succesful = false;

        String sql = "DELETE FROM cart_item\n" +
                "WHERE product_id = ? AND user_id = ?;";
        rowsUpdated = jdbcTemplate.update(sql, productId, userId);

        if (rowsUpdated > 0){
            succesful = true;
        }
        return succesful;
    }

    @Override
    public boolean clearCart(int userId) {
        int rowsUpdated = 0;
        boolean successful = false;

        String sql = "DELETE FROM cart_item\n" +
                "WHERE user_id = ?;";
        rowsUpdated = jdbcTemplate.update(sql, userId);

        if (rowsUpdated > 0){
            successful = true;
        }

        return successful;
    }

    private CartItem mapRowToItem(SqlRowSet results){
        CartItem cartItem = new CartItem();
        cartItem.setUserId(results.getInt("user_id"));
        cartItem.setProductName(results.getString("name"));
        cartItem.setProductId(results.getInt("product_id"));
        cartItem.setPrice(results.getBigDecimal("price"));
        cartItem.setCartItemId(results.getInt("cart_item_id"));
        cartItem.setQuantity(results.getInt("quantity"));
        return cartItem;
    }
}
