package com.endava.restaurant.service.impl;

import static com.endava.restaurant.constants.Constants.Error.INVALID_CATEGORY;

import java.util.List;

import org.springframework.stereotype.Service;

import com.endava.restaurant.entity.Product;
import com.endava.restaurant.exception.ProductException;
import com.endava.restaurant.repository.CategoryRepository;
import com.endava.restaurant.repository.ProductRepository;
import com.endava.restaurant.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> getProducts(String category) {
        if (categoryRepository.existsCategoryByName(category)) {
            return productRepository.findByCategory_Name(category);
        }
        throw new ProductException(INVALID_CATEGORY, category);
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findProductById(Integer.valueOf(id));
    }

    @Override
    public void saveProduct(Product product) {

        productRepository.save(product);
    }

    @Override
    public void saveEnhancedProduct(Product product) {
        product.setPrice(product.getPrice() + 10);

        productRepository.save(product);
    }
}
