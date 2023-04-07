package com.techelevator.model;

import java.math.BigDecimal;

public class CartItem {
    int userId;
    String productName;
    int productId;
    BigDecimal price;
    int cartItemId;
    int quantity;

    public CartItem(){}

    public CartItem(int userId, String productName, int productId, BigDecimal price, int cartItemId, int quantity) {
        this.userId = userId;
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.cartItemId = cartItemId;
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
