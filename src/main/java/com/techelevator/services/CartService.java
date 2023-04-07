package com.techelevator.services;

import com.techelevator.dao.JdbcCartDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.model.Cart;
import com.techelevator.model.CartItemInsert;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;

@Component
public class CartService {

    private JdbcCartDao cartDao;
    private JdbcUserDao userDao;
    private TaxService taxService;

    public CartService(JdbcUserDao userDao, JdbcCartDao cartDao, TaxService taxService){
        this.cartDao = cartDao;
        this.userDao = userDao;
        this.taxService = taxService;
    }

    public Cart getCart(Principal principal){
        Cart cart = new Cart();
        User user = getUser(principal);
        cart.setCart(cartDao.getCartContents(user.getId()));
        String stateCode = user.getStateCode();
        BigDecimal tax = taxService.getSalesTax(stateCode);
        cart.setTax(tax);
        cart.setSubtotal(cart.getSubtotal());
        cart.setTotal(cart.getTotal());
        return cart;
    }

    public boolean addToCart(CartItemInsert cartItem, Principal principal){
        User user = getUser(principal);
        return cartDao.addToCart(cartItem, user.getId());
    }

    public boolean removeItem(int productId, Principal principal){
        User user = getUser(principal);
        return cartDao.removeItem(productId, user.getId());
    }

    public boolean clearCart(Principal principal){
        User user = getUser(principal);
        return cartDao.clearCart(user.getId());
    }

    private User getUser(Principal principal) {
        String username = principal.getName();
        User user = userDao.findByUsername(username);
        return user;
    }
}
