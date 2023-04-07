package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItemInsert {

    @NotNull
    private int userId;
    @NotNull
    private int productId;
    @NotNull
    @Min(1)
    private int quantity;

    public CartItemInsert(){}

    public CartItemInsert(int user_id, int product_id, int quantity) {
        this.userId = user_id;
        this.productId = product_id;
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int product_id) {
        this.productId = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
