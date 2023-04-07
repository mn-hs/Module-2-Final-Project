package com.techelevator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Cart {
    private List<CartItem> cart;
    private BigDecimal subtotal;
    private BigDecimal total;
    private BigDecimal tax;

    public Cart(){}

    public Cart(List<CartItem> cart, BigDecimal subtotal, BigDecimal total) {
        this.cart = cart;
        this.subtotal = subtotal;
        this.total = total;
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = new BigDecimal(0);

        for (CartItem item : cart){
            BigDecimal quantity = new BigDecimal(item.getQuantity());
            BigDecimal priceQuantity = item.getPrice().multiply(quantity);
            subtotal = subtotal.add(priceQuantity);
        }
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        BigDecimal total = getSubtotal();

        BigDecimal cartTax = getSubtotal().multiply(tax);

        total = total.add(cartTax);

        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public void add(CartItem item){
        cart.add(item);
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
}
