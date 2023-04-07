package com.techelevator.controller;

import com.techelevator.model.Cart;
import com.techelevator.model.CartItemInsert;
import com.techelevator.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(method = RequestMethod.GET)
    public Cart getCart(Principal principal){
        return cartService.getCart(principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public boolean addToCart(@RequestBody @Valid CartItemInsert cartItem, Principal principal){
        return cartService.addToCart(cartItem, principal);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/items/{itemId}", method = RequestMethod.DELETE)
    public boolean removeItemFromCart(@PathVariable int itemId, Principal principal){
        return cartService.removeItem(itemId, principal);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.DELETE)
    public boolean clearCart(Principal principal){
        return cartService.clearCart(principal);
    }

}
