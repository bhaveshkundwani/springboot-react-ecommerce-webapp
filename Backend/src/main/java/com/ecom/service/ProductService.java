package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(int id) {
//      return productRepository.findById(id).orElse(new Product());
//      return productRepository.findById(id).orElse(null);
		
      return productRepository.findById(id).orElse(new Product(-1));
	}
}
