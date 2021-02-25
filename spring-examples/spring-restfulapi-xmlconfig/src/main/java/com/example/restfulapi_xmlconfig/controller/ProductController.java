package com.example.restfulapi_xmlconfig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulapi_xmlconfig.model.Product;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@GetMapping(value="/{id}", produces = "application/json")
    public Product getBook(@PathVariable int id) {
        return findProductById(id);
    }

    private Product findProductById(int id) {
        // ...
    	Product p = new Product();
    	p.setId(1);
    	p.setName("Product 1");
    	p.setQty(100);
    	p.setDescription("Product 1 description");
    	p.setUrlImage("resources/images/product/product_0001.png");
    	return p;
    }
}
