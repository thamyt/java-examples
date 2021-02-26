package com.example.restfulapi_xmlconfig.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulapi_xmlconfig.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
