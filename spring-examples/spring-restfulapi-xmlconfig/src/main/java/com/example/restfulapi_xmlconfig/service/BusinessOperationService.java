package com.example.restfulapi_xmlconfig.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restfulapi_xmlconfig.model.Product;
import com.example.restfulapi_xmlconfig.repository.ProductRepository;

@Service
public class BusinessOperationService {
	@Autowired
	private ProductRepository productRepository;
	
	
	@Transactional
	public Product createProduct(Product product) {
		// save the product
		product = productRepository.save(product);
		return product;
	}
	
	@Transactional(readOnly=true)
	public List<Product> getProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}
	
	@Transactional(readOnly=true)
	public Optional<Product> getProduct(int id) {
		Optional<Product> product = productRepository.findById(id);
		return product;
	}
	
	@Transactional
	public void updateProduct(Product product) {
		// save the product
		product = productRepository.save(product);
	}
	
	@Transactional
	public void deleteProduct(int id) {
		// save the product
		productRepository.deleteById(id);
	}
}
