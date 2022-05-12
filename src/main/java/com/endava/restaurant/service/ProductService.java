package com.endava.restaurant.service;

import java.util.List;

import com.endava.restaurant.entity.Product;

public interface ProductService {

    List<Product> getProducts(String category);

    Product getProduct(String id);

    void saveProduct(Product product);

    void saveEnhancedProduct(Product product);
}
