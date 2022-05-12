package com.endava.restaurant.service.impl;

import static com.endava.mocks.ProductMock.getProducts;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.List;

import com.endava.restaurant.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.endava.mocks.ProductMock;
import com.endava.restaurant.constants.Constants;
import com.endava.restaurant.entity.Product;
import com.endava.restaurant.exception.ProductException;
import com.endava.restaurant.repository.CategoryRepository;
import com.endava.restaurant.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    public static final String VALID_CATEGORY = "Desert";
    public static final String INVALID_CATEGORY = "Invalid";

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @Captor
    ArgumentCaptor<Product> productCaptor;

    @Test
    void getProducts_withValidCategory_returnsFilteredEmptyProductList() {
        when(categoryRepository.existsCategoryByName(VALID_CATEGORY)).thenReturn(true);

        List<Product> products = productService.getProducts(VALID_CATEGORY);

        assertNotNull(products);
    }

    @Test
    void getProducts_withValidCategory_returnsFilteredProductList() {
        when(categoryRepository.existsCategoryByName(VALID_CATEGORY)).thenReturn(true);
        when(productRepository.findByCategory_Name(VALID_CATEGORY)).thenReturn(getProducts());

        List<Product> products = productService.getProducts(VALID_CATEGORY);

        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    void saveProduct_successfully() {
        Product product = ProductMock.getMockedProduct();

        productService.saveProduct(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void saveEnhancedProduct_successfully() {
        Product product = ProductMock.getMockedProduct();
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);

        productService.saveEnhancedProduct(product);

        verify(productRepository, times(1)).save(productCaptor.capture());
        verify(productRepository, atMostOnce()).save(any());
        verify(productRepository, times(1)).save(productCaptor.getValue());
        assertEquals(22.0, productCaptor.getValue().getPrice());
    }


}
