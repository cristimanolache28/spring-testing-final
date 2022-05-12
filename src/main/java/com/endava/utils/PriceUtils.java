package com.endava.utils;

import static com.endava.restaurant.constants.Constants.Error.INVALID_DISCOUNT;

import java.util.List;

import com.endava.restaurant.entity.Product;
import com.endava.restaurant.exception.ProductException;

public class PriceUtils {


    private PriceUtils() {
    }

    public static Double applyDiscount(Double price, Integer discount) {
        if (discount > 45 || discount < 1) {
            throw new ProductException(INVALID_DISCOUNT);
        }
        return price - (discount * price) / 100;
    }

    public static Double getTotalPrice(List<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }
}
