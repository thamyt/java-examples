package com.example.restfulapi_xmlconfig.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulapi_xmlconfig.model.Product;
import com.example.restfulapi_xmlconfig.service.BusinessOperationService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private BusinessOperationService boService;
	
	@GetMapping(produces = "application/json")
    public List<Product> getBook() {
        return boService.getProducts();
    }
	
	@GetMapping(value="/{id}", produces = "application/json")
    public Product getBook(@PathVariable int id) {
		Optional<Product> product = boService.getProduct(id);
		if( product.isPresent() ) {
			return product.get();
		}
		else {
			return null;
		}        
    }
	

//    private Product findProductById(int id) {
//        // ...
//    	Product p = new Product();
//    	p.setId(1);
//    	p.setName("Product 1");
//    	p.setQty(100);
//    	p.setDescription("Product 1 description");
//    	p.setUrlImage("resources/images/product/product_0001.png");
//    	return p;
//    }
}
