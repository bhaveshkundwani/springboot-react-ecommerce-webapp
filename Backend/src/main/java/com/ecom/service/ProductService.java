package com.ecom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	public Product addOrUpdateProduct(Product product, MultipartFile imageFile) throws IOException {

        if(imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
        }

        return productRepository.save(product);
    }
}
