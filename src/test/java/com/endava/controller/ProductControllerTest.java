package com.endava.controller;

import static com.endava.mocks.ProductMock.getProducts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.endava.restaurant.constants.Constants;
import com.endava.restaurant.controller.ProductController;
import com.endava.restaurant.entity.Product;
import com.endava.restaurant.exception.ProductException;
import com.endava.restaurant.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    public static final String VALID_CATEGORY = "Desert";

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    void test_getProducts_withSuccess() {
        when(productService.getProducts(VALID_CATEGORY)).thenReturn(getProducts());

        final ResponseEntity<List<Product>> result = productController.getProducts(VALID_CATEGORY);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        //System.out.println(result.getBody());
        assertEquals(2, result.getBody().size());
    }

    @Test
    void test_getProducts_failure() {
        when(productService.getProducts(VALID_CATEGORY)).thenThrow(new ProductException(Constants.Error.INVALID_CATEGORY));

        final ProductException exception =
                assertThrows(ProductException.class, () -> productController.getProducts(VALID_CATEGORY));

        assertEquals(Constants.Error.INVALID_CATEGORY, exception.getMessage());
    }
}
