package com.endava.mocks;

import java.util.List;

import com.endava.restaurant.entity.Product;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMock {

    public static Product getMockedProduct() {
        return Product.builder()
                .id(1)
                .name("Cheesecake")
                .price(12.0)
                .build();
    }

    public static List<Product> getProducts() {
        return List.of(
                Product.builder()
                        .name("Cheesecake")
                        .price(12.0)
                        .build(),
                Product.builder()
                        .name("Soup")
                        .price(15.0)
                        .build()
        );
    }

    public static List<List<Product>> getProductsList() {
        return List.of(
                getProducts()
        );
    }

}
