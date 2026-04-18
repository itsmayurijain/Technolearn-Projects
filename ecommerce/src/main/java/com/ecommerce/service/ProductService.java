
package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductInterface;

@Service
public class ProductService {

    @Autowired
    ProductInterface productRepository;

    // add product
    public Product addProduct(Product product) {
    	product.setAvailable(product.getStockQuantity() > 0);
        return productRepository.save(product);
    }

    // get all products
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}


