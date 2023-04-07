package com.techelevator.controller;

import com.techelevator.dao.JdbcProductDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class UnauthenticatedController {

    private ProductDao dao;

    public UnauthenticatedController(JdbcProductDao jdbcProductDao){
        this.dao = jdbcProductDao;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Product> getProducts(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String productSku){
        return dao.searchProducts(productSku, name);
    }


    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getSingleProduct(@PathVariable int id){return dao.getSingleProduct(id); }
}
