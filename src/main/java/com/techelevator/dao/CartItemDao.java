package com.techelevator.dao;

import com.techelevator.model.CartItem;
import com.techelevator.model.CartItemInsert;

import java.util.List;

public interface CartItemDao {

    List<CartItem> getCartContents(int userId);

    String getStateCode(int userId);

    boolean addToCart(CartItemInsert cartItem, int userId);

    boolean removeItem(int productId, int userId);

    boolean clearCart(int userId);
}
